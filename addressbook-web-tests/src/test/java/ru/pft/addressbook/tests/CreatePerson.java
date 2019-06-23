package ru.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.pft.addressbook.model.PersonData;

import java.util.Set;

public class CreatePerson extends TestBase {

  @Test
  public void testCreatePerson() {

    app.goTo().homePage();
    Set<PersonData> before = app.person().all();
    PersonData person = new PersonData().withName("Tester").withLastName("Testovoy")
            .withTelNumber("89999999999").withEmail("test@test.com").withGroup("Test group name");
    app.person().create(person, true);
    Set<PersonData> after = app.person().all();

    before.add(person);
    Assert.assertEquals(before, after);
  }
}
