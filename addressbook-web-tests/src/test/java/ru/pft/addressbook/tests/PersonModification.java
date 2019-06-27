package ru.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.pft.addressbook.model.PersonData;
import ru.pft.addressbook.model.Persons;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class PersonModification extends TestBase {

  @BeforeMethod
  public void preconditions(){
    app.goTo().homePage();
    if (app.person().all().size() == 0) {
      app.person().create(new PersonData().withName("Tester").withLastName("Testovoy")
              .withTelNumber("89999999999").withEmail("test@test.com").withGroup("Test group name"), true);
    }
  }
  @Test
  public void personModification() {

    app.goTo().homePage();

    Persons before = app.person().all();
    PersonData modifiedPerson = before.iterator().next();
    PersonData person = new PersonData().withId(modifiedPerson.getId()).withName("Tester Meister").withLastName("Lenin");
    app.person().modify(modifiedPerson);
    app.goTo().homePage();
    assertThat(app.person().count(), equalTo(before.size()));
    Persons after = app.person().all();

    assertThat(after, equalTo(before.without(modifiedPerson).withAdded(person)));
    System.out.println("Person is modified!!!");

  }


}
