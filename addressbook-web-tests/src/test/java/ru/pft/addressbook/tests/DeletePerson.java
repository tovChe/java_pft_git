package ru.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.pft.addressbook.model.PersonData;

import java.util.List;

public class DeletePerson extends TestBase {

  @BeforeMethod
  public void preconditions() {
    app.getNavigationHelper().returnHomePage();
    if (!app.getPersonHelper().isThereAPerson()) {
      app.getPersonHelper().createPerson(new PersonData("Tester", "Testovoy",
              "89999999999", "test@test.com", "Test group name"), true);
    }
  }

  @Test
  public void testDeletePerson() throws Exception {

    List<PersonData> before = app.getPersonHelper().getPersonList();
    app.getPersonHelper().selectPerson(before.size() - 1);
    app.getPersonHelper().deletePerson();
    List<PersonData> after = app.getPersonHelper().getPersonList();
    Assert.assertEquals(after.size(), before.size() - 1);
    System.out.println("Person is deleted!");

  }
}
