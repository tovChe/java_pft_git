package ru.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.pft.addressbook.model.PersonData;
import ru.pft.addressbook.model.Persons;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

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
    PersonData person = new PersonData().withName("Tester Meister").withLastName("Lenin");
    app.person().modify(modifiedPerson);
    app.goTo().homePage();
    Persons after = app.person().all();
    Assert.assertEquals(after.size(), before.size());
    MatcherAssert.assertThat(after, CoreMatchers.equalTo(before.without(modifiedPerson).withAdded(person)));
    System.out.println("Person is modified!!!");

  }


}
