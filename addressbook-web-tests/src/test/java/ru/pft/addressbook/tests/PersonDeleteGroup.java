package ru.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.pft.addressbook.model.GroupData;
import ru.pft.addressbook.model.Groups;
import ru.pft.addressbook.model.PersonData;
import ru.pft.addressbook.model.Persons;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class PersonDeleteGroup extends TestBase {

  @BeforeMethod
  public void preconditions() {
    if (app.db().persons().size() == 0) {
      app.goTo().homePage();
      File photo = new File("src/test/resources/tovChe.jpg");
      app.person().create(new PersonData().withName("Tester")
              .withLastName("Testovoy")
              .withMobilePhone("89999999999")
              .withEmail("test@test.com").withPhoto(photo), true);
    }
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("Test group name").withHeader("test header").withFooter("test footer"));
    }
    PersonData person = app.db().persons().iterator().next();
    if (person.getGroups().size() == 0) {
      app.goTo().homePage();
      app.person().addToGroup(person.inGroup(app.db().groups().iterator().next()));
    }
  }

  @Test
  public void testPersonDeleteGroup() {
    Persons persons = app.db().persons();
    Groups groups = app.db().groups();

    Persons before = persons;
    PersonData personDel = before.iterator().next();

    PersonData deletedPerson = personDel.inGroup(groups.iterator().next());
    app.person().personGroupPage(deletedPerson);
    app.person().delFromGroup(deletedPerson);

    Persons after = app.db().persons();

    assertThat(before, equalTo(after.without(personDel).withAdded(deletedPerson.delGroup())));

  }
}
