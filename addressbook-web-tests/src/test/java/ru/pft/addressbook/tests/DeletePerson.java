package ru.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.pft.addressbook.model.PersonData;

public class DeletePerson extends TestBase {

  @Test
  public void testDelPersonRecorder() throws Exception {

    app.getNavigationHelper().returnHomePage();

    if (!app.getPersonHelper().isThereAPerson()) {
      app.getPersonHelper().createPerson(new PersonData("Tester", "Testovoy",
              "89999999999", "test@test.com", "Test group name"), true);
    }
    int before = app.getPersonHelper().getPersonCount();
    app.getPersonHelper().selectPerson();
    app.getPersonHelper().deletePerson();
    //app.getNavigationHelper().returnHomePage();
    int after = app.getPersonHelper().getPersonCount();
    Assert.assertEquals(after, before - 1);
    System.out.println("Person is deleted!");

  }
}
