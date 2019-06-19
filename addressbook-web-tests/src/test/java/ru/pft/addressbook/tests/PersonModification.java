package ru.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.pft.addressbook.model.PersonData;
import java.util.Comparator;
import java.util.List;

public class PersonModification extends TestBase {

  @BeforeMethod
  public void preconditions(){
    app.getNavigationHelper().returnHomePage();
    if (!app.getPersonHelper().isThereAPerson()) {
      app.getPersonHelper().createPerson(new PersonData("Tester", "Testovoy",
              "89999999999", "test@test.com", "Test group name"), true);
    }
  }
  @Test
  public void personModification() {

    List<PersonData> before = app.getPersonHelper().getPersonList();
    app.getPersonHelper().modifyPerson(before);
    app.getNavigationHelper().returnHomePage();
    List<PersonData> after = app.getPersonHelper().getPersonList();
    Assert.assertEquals(after.size(), before.size());
    System.out.println("Person is modified!!!");

    Comparator<? super PersonData> byPersonName = Comparator.comparing(PersonData::getPersonName);
    before.sort(byPersonName);
    after.sort(byPersonName);
    Assert.assertEquals(after.size(), before.size());
    System.out.println("Checking is green!!!");
  }


}
