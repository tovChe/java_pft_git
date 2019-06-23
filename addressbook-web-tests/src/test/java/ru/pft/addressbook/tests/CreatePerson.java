package ru.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.pft.addressbook.model.PersonData;
import ru.pft.addressbook.model.Persons;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreatePerson extends TestBase {

  @Test
  public void testCreatePerson() {

    app.goTo().homePage();
    Persons before = app.person().all();
    PersonData person = new PersonData().withName("Tester").withLastName("Testovoy")
            .withTelNumber("89999999999").withEmail("test@test.com").withGroup("Test group name");
    app.person().create(person, true);
    Persons after = app.person().all();

    before.add(person);
    Assert.assertEquals(before, after);
    assertThat(before, equalTo(after.withAdded(person)));
  }
}
