package ru.pft.mantis.appmanager;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMessage;

import org.apache.commons.net.telnet.TelnetClient;
import org.subethamail.wiser.WiserMessage;

import ru.pft.mantis.model.MailMessage;

public class JamesHelper {

	private ApplicationManager app;

	private TelnetClient telnet;
	private InputStream in;
	private PrintStream out;

	private Session mailSession;
	private Store store;
	private String mailServer;

	public JamesHelper(ApplicationManager app) {
		this.app = app;
		telnet = new TelnetClient();
		mailSession = Session.getDefaultInstance(System.getProperties());
	}

	public boolean doesUserExist(String name) {
		initTelnetSession();
		write("verify " + name);
		String result = readUntil("exist");
		closeTelnetSession();
		return result.trim().equals("User " + name + " exist");
	}

	public void createUser(String name, String passwd) {
		initTelnetSession();
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("adduser ");
		stringBuilder.append(name);
		stringBuilder.append(" ");
		stringBuilder.append(passwd);
		write(stringBuilder.toString());
		String result = readUntil("User " + name + " added");
		closeTelnetSession();
	}

	public void deleteUser(String name) {
		initTelnetSession();
		write("deluser " + name);
		String result = readUntil("User " + name + " deleted");
		closeTelnetSession();
	}

	public void initTelnetSession() {
		mailServer = app.getProperty("mailserver.host");
		int port = Integer.parseInt(app.getProperty("mailserver.port"));
		String login = app.getProperty("mailserver.adminlogin");
		String password = app.getProperty("mailserver.adminpassword");

		try {
			telnet.connect(mailServer, port);
			in = telnet.getInputStream();
			out = new PrintStream(telnet.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}

		readUntil("Login id:");
		write("");
		readUntil("Password:");
		write("");

		readUntil("Login id:");
		write(login);
		readUntil("Password:");
		write(password);

		// Advance to a prompt
		readUntil("Welcome " + login + ". HELP for a list of commands");
	}

	public String readUntil(String pattern) {
		try {
			char lastChar = pattern.charAt(pattern.length() - 1);
			StringBuffer sb = new StringBuffer();
			boolean found = false;
			char ch = (char) in.read();
			while (true) {
				System.out.print(ch);
				sb.append(ch);
				if (ch == lastChar) {
					if (sb.toString().endsWith(pattern)) {
						return sb.toString();
					}
				}
				ch = (char) in.read();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void write(String value) {
		try {
			out.println(value);
			out.flush();
			System.out.println(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void closeTelnetSession() {
		try {
			telnet.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<MailMessage> waitForMail(String username, String password, long timeout) throws MessagingException {
		long now = System.currentTimeMillis();
		while (System.currentTimeMillis() < now + timeout) {
			List<MailMessage> allMailMessages = getAllMail(username, password);
			if (allMailMessages.size() > 0) {
				return allMailMessages;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		throw new Error("No mail");
	}

	private Folder openInbox(String username, String password) throws MessagingException {
		store = mailSession.getStore("pop3");
		store.connect(mailServer, username, password);
		Folder folder = store.getDefaultFolder().getFolder("INBOX");
		folder.open(Folder.READ_WRITE);
		return folder;
	}

	private void closeFolder(Folder folder) throws MessagingException {
		folder.close(true);
		store.close();
	}

	public List<MailMessage> getAllMail(String username, String password) throws MessagingException {
		Folder inboxFolder = openInbox(username, password);
		List<MailMessage> messages = Arrays.asList(inboxFolder.getMessages()).stream().map((m) -> toModelMail(m)).collect(Collectors.toList());
		closeFolder(inboxFolder);
		return messages;
	}

	public static MailMessage toModelMail(Message m) {
		try {
			return new MailMessage(m.getAllRecipients()[0].toString(), (String) m.getContent());
		} catch (MessagingException | IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
