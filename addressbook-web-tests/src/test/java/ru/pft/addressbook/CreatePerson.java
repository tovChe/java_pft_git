package ru.pft.addressbook;

import org.testng.annotations.Test;

public class CreatePerson extends TestBase {

  @Test
  public void testCreatePerson() {

    app.addNewPerson();
    app.fillPersonForm(new PersonData("Tester", "Testovoy", "89999999999", "test@test.com"));
    app.submitForm();
    app.returnHomePage();

  }

}
