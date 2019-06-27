package ru.pft.addressbook.tests;

import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;
import ru.pft.addressbook.model.PersonData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestPersonPhone extends TestBase {

  @Test
  public void testPersonPhones() {
    app.goTo().homePage();
    PersonData person = app.person().all().iterator().next();
    PersonData personInfoFromEditForm = app.person().infoFromEditForm(person);

    assertThat(person.getHomePhone(), equalTo(cleaned(personInfoFromEditForm.getHomePhone())));
    assertThat(person.getMobilePhone(), equalTo(cleaned(personInfoFromEditForm.getMobilePhone())));
    assertThat(person.getWorkPhone(), equalTo(cleaned(personInfoFromEditForm.getWorkPhone())));
  }
  public String cleaned (String phones){
    return phones.replaceAll("\\s","").replaceAll("[-()]","");
  }
}
