package ru.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.pft.addressbook.model.PersonData;

import java.util.Comparator;
import java.util.List;

public class CreatePerson extends TestBase {

  @BeforeMethod
  public void preconditions() {
    if (!app.person().isThereAPerson()) {
      app.person().create(new PersonData().withName("Tester").withLastName("Testovoy")
              .withTelNumber("89999999999").withEmail("test@test.com").withGroup("Test group name"), true);
      System.out.println("Person was created!!!");
    }
  }

  @Test
  public void testCreatePerson() {

    app.goTo().homePage();
    List<PersonData> before = app.person().list();
    PersonData person = new PersonData().withName("Tester").withLastName("Testovoy")
            .withTelNumber("89999999999").withEmail("test@test.com").withGroup("Test group name");
    app.person().create(person, true);
    List<PersonData> after = app.person().list();

    Comparator<? super PersonData> personName = Comparator.comparing(PersonData::getPersonName);

    before.sort(personName);
    after.sort(personName);
    Assert.assertEquals(before.size() + 1, after.size());


  }
}
