package ru.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

	private final Properties properties;
	private WebDriver wd;

	private String browser;
	private RegistrationHelper registrtationHelper;
	private FtpHelper ftp;
	private MailHelper mailHelper;
	private JamesHelper jamesHelper;
	private AdminHelper adminHelper;
	private DbHelper dbHelper;
	private NavigationHelper navHelper;

	public ApplicationManager(String browser) {
		this.browser = browser;
		properties = new Properties();
	}

	public void init() throws IOException {
		String target = System.getProperty("target", "local");
		properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
	}

	public void stop() {
		if (wd != null) {
			wd.quit();
		}
	}

	public HttpSession newSession() {
		return new HttpSession(this);
	}

	public String getProperty(String key) {
		return properties.getProperty(key);
	}

	public RegistrationHelper registration() {
		if (registrtationHelper == null) {
			registrtationHelper =  new RegistrationHelper(this);
		}
		return registrtationHelper;
	}

	public FtpHelper ftp() {
		if (ftp == null) {
			ftp = new FtpHelper(this);
		}
		return ftp;
	}

	public WebDriver getDriver() {
		if (wd == null) {
			if (browser.equals(BrowserType.FIREFOX)) {
				wd = new FirefoxDriver();
			} else if (browser.equals(BrowserType.CHROME)) {
				wd = new ChromeDriver();
				wd.manage().window().maximize();
			} else if (browser.equals(BrowserType.IE)) {
				wd = new InternetExplorerDriver();
			} else {
				System.out.println("Use for tests only IE, Chrome, Firefox");
			}

			wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			wd.get(properties.getProperty("web.baseUrl"));

		}
		return wd;
	}
	public MailHelper mail() {
		if (mailHelper == null) {
			mailHelper = new MailHelper(this);
		}
		return mailHelper;
	}
	
	public JamesHelper james() {
		if(jamesHelper == null) {
			jamesHelper = new JamesHelper(this);
		}
		return jamesHelper;
	}
	public AdminHelper admin() {
		if(adminHelper == null) {
			adminHelper = new AdminHelper(this);
		}
		return adminHelper;
	}
	public DbHelper db() {
		if(dbHelper == null) {
			dbHelper = new DbHelper(this);
		}
		return dbHelper;
	}
	public NavigationHelper goTo() {
		if(navHelper == null) {
			navHelper = new NavigationHelper(this);
		}
		return navHelper;
	}
}
