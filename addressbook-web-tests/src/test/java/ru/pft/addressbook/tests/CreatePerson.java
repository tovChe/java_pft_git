package ru.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import ru.pft.addressbook.model.PersonData;

public class CreatePerson extends TestBase {

  @Test
  public void testCreatePerson() {

    if (!app.getPersonHelper().isThereAPerson()) {
      app.getPersonHelper().createPerson(new PersonData("Tester", "Testovoy",
              "89999999999", "test@test.com", "Test group name"), true);
      System.out.println("Person was created!!!");
    } else if (app.getPersonHelper().isElementPresent(By.name("selected[]"))) {
      System.out.println("Person existed!!!");
    }
    app.getNavigationHelper().returnHomePage();
  }
}
