package ru.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.pft.addressbook.model.PersonData;
import ru.pft.addressbook.model.Persons;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DeletePerson extends TestBase {

  @BeforeMethod
  public void preconditions() {
    if (app.db().persons().size() == 0) {
      app.goTo().homePage();
      app.person().create(new PersonData().withName("Tester")
              .withLastName("Testovoy")
              .withMobilePhone("89999999999")
              .withEmail("test@test.com"), true);
    }
  }

  @Test
  public void testDeletePerson() throws Exception {

    app.goTo().homePage();
    Persons before = app.db().persons();
    PersonData deletedPerson = before.iterator().next();
    PersonData person = new PersonData().withName("Tester").withLastName("Testovoy");
    app.person().select(deletedPerson);
    app.person().delete();
    assertThat(app.person().count(), equalTo(before.size() - 1));
    Persons after = app.db().persons();

    before.remove(person);
    assertThat(after, equalTo(before.without(deletedPerson)));
    verifyPersonListUI(); // -DverifyListUI=true в конфигурации запуска
    System.out.println("Person is deleted!");

  }
}
