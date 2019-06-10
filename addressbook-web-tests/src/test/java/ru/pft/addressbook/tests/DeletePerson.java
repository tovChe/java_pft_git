package ru.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.pft.addressbook.model.PersonData;

import java.util.List;

public class DeletePerson extends TestBase {

  @Test
  public void testDelPersonRecorder() throws Exception {

    app.getNavigationHelper().returnHomePage();

    if (!app.getPersonHelper().isThereAPerson()) {
      app.getPersonHelper().createPerson(new PersonData("Tester", "Testovoy",
              "89999999999", "test@test.com", "Test group name"), true);
    }
    List<PersonData> before = app.getPersonHelper().getPersonList();
    app.getPersonHelper().selectPerson(before.size() - 1);
    app.getPersonHelper().deletePerson();
    List<PersonData> after = app.getPersonHelper().getPersonList();
    Assert.assertEquals(after.size(), before.size() - 1);
    System.out.println("Person is deleted!");

  }
}
