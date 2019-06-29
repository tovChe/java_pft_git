package ru.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.pft.addressbook.model.PersonData;
import ru.pft.addressbook.model.Persons;

import java.io.File;
import java.util.Comparator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreatePerson extends TestBase {

  @Test
  public void testCreatePerson() {

    app.goTo().homePage();
    Persons before = app.person().all();
    PersonData addedPerson = before.iterator().next();
    File photo = new File("src/test/resources/tovChe.jpg");
    if (! photo.exists()){
      System.out.println(photo.exists());
      return;
    }
    PersonData person = new PersonData().withId(addedPerson.getId()).withName("Tester").withLastName("Testovoy")
            .withMobilePhone("89999999999").withHomePhone("222").withWorkPhone("333").withEmail("test@test.com").withPhoto(photo);
    app.person().create(person, true);

    app.goTo().homePage();
    assertThat(app.person().count(), equalTo(before.size() + 1));
    Persons after = app.person().all();

    person.withId(after.stream().max(Comparator.comparingInt(o -> o.getId())).get().getId());
    before.add(person);
    assertThat(after, equalTo(before.withAdded(addedPerson)));

  }
}
