package ru.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.pft.addressbook.model.PersonData;

import java.util.Comparator;
import java.util.List;

public class CreatePerson extends TestBase {

  @Test
  public void testCreatePerson() {

    app.getNavigationHelper().returnHomePage();
    List<PersonData> before = app.getPersonHelper().getPersonList();
    if (!app.getPersonHelper().isThereAPerson()) {
      app.getPersonHelper().createPerson(new PersonData("Tester", "Testovoy",
              "89999999999", "test@test.com", "Test group name"), true);
      System.out.println("Person was created!!!");
    }

    PersonData person = new PersonData("Tester", "Testovoy",
            "89999999999", "test@test.com", "Test group name");
    app.getPersonHelper().createPerson(person, true);
    List<PersonData> after = app.getPersonHelper().getPersonList();

    Comparator<? super PersonData> byPersonName = Comparator.comparing(PersonData::getPersonName);

    before.sort(byPersonName);
    after.sort(byPersonName);
    Assert.assertEquals(before.size() + 1, after.size());


  }
}
