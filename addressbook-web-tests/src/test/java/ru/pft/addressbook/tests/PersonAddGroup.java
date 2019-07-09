package ru.pft.addressbook.tests;

import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.pft.addressbook.model.GroupData;
import ru.pft.addressbook.model.Groups;
import ru.pft.addressbook.model.PersonData;
import ru.pft.addressbook.model.Persons;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;

public class PersonAddGroup extends TestBase {

  private Persons person ;
  private Groups groups;

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
   person = app.db().persons();
   groups = app.db().groups();
  }

  @Test
  public void testPersonAddGroup() {

    app.goTo().homePage();
    PersonData personAdd = person.iterator().next();

    Groups before = groups;

    app.person().addToGroup(personAdd);
    Groups groupsAdd = personAdd.getGroups();

    Groups after = groups;

    assertThat(before.withAdded(groupsAdd.iterator().next()), equalTo(after));


  }
}
