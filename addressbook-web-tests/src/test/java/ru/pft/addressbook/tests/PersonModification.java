package ru.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.pft.addressbook.model.PersonData;

public class PersonModification extends TestBase {

  @Test
  public void personModification() {

    app.getPersonHelper().editPerson();
    app.getPersonHelper().fillPersonForm(new PersonData("Tester Meister", "Lenin", "+79189999999", "tester@yahoooo.com"));
    app.getGroupHelper().updateForm();

  }
}
