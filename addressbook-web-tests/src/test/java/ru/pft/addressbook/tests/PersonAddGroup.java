package ru.pft.addressbook.tests;

import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.pft.addressbook.model.GroupData;
import ru.pft.addressbook.model.Groups;
import ru.pft.addressbook.model.PersonData;
import ru.pft.addressbook.model.Persons;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.*;

public class PersonAddGroup extends TestBase {


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
  }

  @Test
  public void testPersonAddGroup() {

    Persons personBefore = app.db().persons();
    Groups groupsBefore = app.db().groups();

    PersonData personAdd = personBefore.iterator().next();
    GroupData groupsToAdd = groupsBefore.iterator().next();

    app.goTo().homePage();
    app.person().addToGroup(personAdd.inGroup(groupsToAdd));

    app.db().personNextQuery(personAdd);
    app.db().groupsNextQuery(groupsToAdd);

    assertThat(personAdd.getGroups(), hasItem(groupsToAdd));
    assertThat(groupsToAdd.getPersons(), hasItem(personAdd));


  }
}
