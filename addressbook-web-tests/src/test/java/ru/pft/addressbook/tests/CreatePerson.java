package ru.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.pft.addressbook.model.PersonData;
import ru.pft.addressbook.model.Persons;

import java.util.Comparator;

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

    app.goTo().homePage();
    assertThat(app.person().count(), equalTo(before.size() + 1));

    app.goTo().homePage();
    assertThat(app.person().count(), equalTo(before.size() + 1));
    Persons after = app.person().all();

    person.withId(after.stream().max(Comparator.comparingInt(o -> o.getId())).get().getId());
    before.add(person);

    assertThat(after, equalTo(before.withAdded(person)));
  }
}
