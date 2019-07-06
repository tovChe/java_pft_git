package ru.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.pft.addressbook.model.PersonData;
import ru.pft.addressbook.model.Persons;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class PersonModification extends TestBase {

  @BeforeMethod
  public void preconditions(){
    if (app.db().persons().size() == 0){
      app.goTo().homePage();
      app.person().create(new PersonData().withName("Tester").withLastName("Testovoy")
              .withMobilePhone("89999999999").withEmail("test@test.com").withGroup("Test group name"), true);
    }
   }
  @Test
  public void personModification() {

    app.goTo().homePage();

    Persons before = app.db().persons();
    PersonData modifiedPerson = before.iterator().next();
    File photo = new File("src/test/resources/tovChe.jpg");
    if (!photo.exists()) {
      System.out.println(photo.exists());
      return;
    }

    PersonData person = new PersonData().withId(modifiedPerson.getId())
            .withName("Tester Meister")
            .withLastName("Lenin")
            .withMobilePhone("+7(911)")
            .withHomePhone("222")
            .withWorkPhone("333")
            .withEmail("tester@test.com");
    person.withPhoto(photo);
    app.goTo().homePage();
    app.person().modify(person);
    app.goTo().homePage();
    assertThat(app.person().count(), equalTo(before.size()));
    Persons after = app.db().persons();

    assertThat(after, equalTo(before.without(modifiedPerson).withAdded(person)));
    System.out.println("Person is modified!!!");

  }


}
