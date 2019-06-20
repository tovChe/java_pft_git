package ru.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.pft.addressbook.model.PersonData;

import java.util.List;

public class DeletePerson extends TestBase {

  @BeforeMethod
  public void preconditions() {
    app.goTo().homePage();
    if (app.person().list().size() == 0) {
      app.person().create(new PersonData("Tester", "Testovoy",
              "89999999999", "test@test.com", "Test group name"), true);
    }
  }

  @Test
  public void testDeletePerson() throws Exception {

    List<PersonData> before = app.person().list();
    app.person().select(before.size() - 1);
    app.person().delete();
    List<PersonData> after = app.person().list();
    Assert.assertEquals(after.size(), before.size() - 1);
    System.out.println("Person is deleted!");

  }
}
