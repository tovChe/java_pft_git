package ru.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.pft.addressbook.model.PersonData;
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
    Set<PersonData> before = app.person().all();
    app.person().modify(before);
    app.goTo().homePage();
    Set<PersonData> after = app.person().all();
    Assert.assertEquals(after.size(), before.size());
    System.out.println("Person is modified!!!");

  }


}
