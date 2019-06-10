package ru.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.pft.addressbook.model.PersonData;

import java.util.List;

public class PersonModification extends TestBase {

  @Test
  public void personModification() {

    if (!app.getPersonHelper().isThereAPerson()) {
      app.getPersonHelper().createPerson(new PersonData("Tester", "Testovoy",
              "89999999999", "test@test.com", "Test group name"), true);
    }
    List<PersonData> before = app.getPersonHelper().getPersonList();
    app.getPersonHelper().editPerson(before.size() - 1);
    app.getPersonHelper().fillPersonForm(new PersonData("Tester Meister", "Lenin",
            "+79189999999", "tester@yahoooo.com", null), false);
    app.getGroupHelper().updateForm();
    app.getNavigationHelper().returnHomePage();
    List<PersonData> after = app.getPersonHelper().getPersonList();
    Assert.assertEquals(after.size(), before.size());
    System.out.println("Person is modified!!!");
  }
}
