package ru.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.pft.addressbook.model.PersonData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestPersonFields extends TestBase {

  @Test
  public void testPersonPhones() {
    app.goTo().homePage();
    PersonData person = app.person().all().iterator().next();
    PersonData personInfoFromEditForm = app.person().infoFromEditForm(person);

    assertThat(person.getAllPhones(), equalTo(mergePhones(personInfoFromEditForm)));
    assertThat(person.getAllEmails(), equalTo(mergeEmails(personInfoFromEditForm)));
    assertThat(person.getAddress(), equalTo(personInfoFromEditForm.getAddress()));
  }

  @Test
  public void testPersonAddress() {
    app.goTo().homePage();
    PersonData person = app.person().all().iterator().next();
    PersonData personInfoFromEditForm = app.person().infoFromEditForm(person);

    assertThat(person.getAddress(), equalTo(personInfoFromEditForm.getAddress()));
  }

  @Test
  public void testPersonEmail() {
    app.goTo().homePage();
    PersonData person = app.person().all().iterator().next();
    PersonData personInfoFromEditForm = app.person().infoFromEditForm(person);

    assertThat(person.getAllEmails(), equalTo(mergeEmails(personInfoFromEditForm)));

  }

  private String mergeEmails(PersonData person) {
    return Arrays.asList(person.getEmail(), person.getEmail2(), person.getEmail3()).stream()
            .filter((s) -> !s.equals(""))
            .collect(Collectors.joining("\n"));
  }

  private String mergePhones(PersonData person) {
    return Arrays.asList(person.getMobilePhone(), person.getHomePhone(), person.getWorkPhone()).stream()
            .filter((s) -> !s.equals(""))
            .map(TestPersonFields::cleaned)
            .collect(Collectors.joining("\n"));
  }

  public static String cleaned(String phones) {
    return phones.replaceAll("\\s", "").replaceAll("[-()]", "");
  }

}
