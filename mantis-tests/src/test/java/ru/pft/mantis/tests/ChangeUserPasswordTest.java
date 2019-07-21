package ru.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.pft.mantis.model.MailMessage;
import ru.pft.mantis.model.User;
import ru.pft.mantis.model.Users;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangeUserPasswordTest extends TestBase {

  @BeforeMethod
  public void preconditions() throws IOException {
    if (app.db().users().size() == 0){
      long now = System.currentTimeMillis();
      String user = String.format("user%s", now);
      String password = "password";
      String email = String.format("user%s@localhost.localdomain", now);
      app.mail().start();
      app.registration().start(user, email);
      List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
      String confirmationLink = app.mail().findConfirmationLink(mailMessages, email);
      app.registration().finish(confirmationLink, password);
      //assertTrue(app.newSession().login(user, password));
    }
  }
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public void changeUserPassword() throws IOException, InterruptedException {
    long now = System.currentTimeMillis();
    //String username = String.format("user%s", now);
    String password = String.format("pass%s",now);
    //String email = String.format("user%s@localhost.localdomain", now);
    Users before = app.db().users();
    //app.newSession().login("administrator", "root");
    User modifiedUser = before.iterator().next();
    //User user = new User().withId(modifiedUser.getId()).withUsername(username).withEmail(email).withPassword(password);
    app.admin().login("administrator", "root");
    app.admin().change(modifiedUser);
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 30000);
    String confirmationLink = app.mail().findConfirmationLink(mailMessages, modifiedUser.getEmail());
    app.registration().finish(confirmationLink, password);
    modifiedUser.withPassword(password);
    assertTrue(app.newSession().login(modifiedUser.getUsername(), modifiedUser.getPassword()));

  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }
}

