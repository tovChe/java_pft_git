package ru.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.pft.mantis.model.User;

public class AdminHelper extends HelperBase {

  public AdminHelper(ApplicationManager app) {
    super(app);
  }

  public void change(User user) throws InterruptedException {

    app.goTo().editPage();
    selectUserWithId(user.getUsername());
    click(By.xpath("//form[1]//fieldset[1]//span[1]//input[1]"));

  }

  public void selectUserWithId(String username) {
    click(By.xpath(String.format("//td/a[text()='%s']", username)));
  }

  public void login(String username, String password) {
    type(By.name("username"), username);
    click(By.className("btn-success"));
    type(By.name("password"), password);
    click(By.className("btn-success"));
  }
}
