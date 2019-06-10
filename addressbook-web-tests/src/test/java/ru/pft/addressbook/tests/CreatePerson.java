package ru.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.pft.addressbook.model.GroupData;
import ru.pft.addressbook.model.PersonData;

import java.util.List;

public class CreatePerson extends TestBase {

  @Test
  public void testCreatePerson() {

    List<PersonData> before = app.getPersonHelper().getPersonList();
    if (!app.getPersonHelper().isThereAPerson()) {
      app.getPersonHelper().createPerson(new PersonData("Tester", "Testovoy",
              "89999999999", "test@test.com", "Test group name"), true);
      System.out.println("Person was created!!!");
    } /*else if (app.getPersonHelper().isElementPresent(By.name("selected[]"))) {
      System.out.println("Person existed!!!");
      app.getPersonHelper().deletePerson();
      app.getPersonHelper().createPerson(new PersonData("TesterCreated", "Testovoy",
              "89999999998", "test@test.com", "Test group name"), true);
      System.out.println("Person was recreated!");
    }*/
    app.getPersonHelper().createPerson(new PersonData("Tester", "Testovoy",
            "89999999999", "test@test.com", "Test group name"), true);
    //app.getNavigationHelper().returnHomePage();
    List<PersonData> after = app.getPersonHelper().getPersonList();
    Assert.assertEquals(after.size(), before.size() + 1);
  }
}
