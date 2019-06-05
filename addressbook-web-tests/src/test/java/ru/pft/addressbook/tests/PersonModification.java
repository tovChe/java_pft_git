package ru.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import ru.pft.addressbook.model.PersonData;

public class PersonModification extends TestBase {

  @Test
  public void personModification() {

    if (!app.getPersonHelper().isThereAPerson()) {
      app.getPersonHelper().createPerson(new PersonData("Tester", "Testovoy",
              "89999999999", "test@test.com", "Test group name"), true);
    }
    app.getPersonHelper().editPerson();
    app.getPersonHelper().fillPersonForm(new PersonData("Tester Meister", "Lenin",
            "+79189999999", "tester@yahoooo.com", null), false);
    app.getGroupHelper().updateForm();
    app.getNavigationHelper().returnHomePage();

    if (app.getPersonHelper().isElementPresent(By.name("selected[]"))) {
      System.out.println("Person is modified!!!");
    }
  }
}
