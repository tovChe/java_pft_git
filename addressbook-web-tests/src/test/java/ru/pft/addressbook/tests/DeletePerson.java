package ru.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.pft.addressbook.model.PersonData;
import ru.pft.addressbook.model.Persons;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DeletePerson extends TestBase {

  @BeforeMethod
  public void preconditions() {
    app.goTo().homePage();
    if (app.person().all().size() == 0) {
      app.person().create(new PersonData().withName("Tester").withLastName("Testovoy")
              .withTelNumber("89999999999").withEmail("test@test.com").withGroup("Test group name"), true);
    }
  }

  @Test
  public void testDeletePerson() throws Exception {

    app.goTo().homePage();
    Persons before = app.person().all();
    PersonData deletedPerson = before.iterator().next();
    PersonData person = new PersonData().withName("Tester").withLastName("Testovoy");
    app.person().select(deletedPerson);
    app.person().delete();
    Persons after = app.person().all();
    Assert.assertEquals(after.size(), before.size() - 1);

    assertThat(after, equalTo(before.without(person)));
    System.out.println("Person is deleted!");

  }
}
