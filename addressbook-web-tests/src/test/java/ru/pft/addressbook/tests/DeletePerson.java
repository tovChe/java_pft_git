package ru.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.pft.addressbook.model.PersonData;

public class DeletePerson extends TestBase {

  @Test
  public void testDelPersonRecorder() throws Exception {

    if (!app.getPersonHelper().isThereAPerson()) {
      app.getPersonHelper().createPerson(new PersonData("Tester", "Testovoy",
              "89999999999", "test@test.com", "Test group name"), true);
    }
    app.getPersonHelper().selectAll();
    app.getPersonHelper().deletePerson();
    app.getNavigationHelper().returnHomePage();
    System.out.println("All person deleted!");

  }
}
