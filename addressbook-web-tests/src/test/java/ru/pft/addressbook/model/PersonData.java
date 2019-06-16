package ru.pft.addressbook.model;

import org.openqa.selenium.WebElement;

import java.util.Objects;

public class PersonData {
  private String personName;
  private String personLastName;
  private final String telNumber;
  private final String email;
  private String groupName;

  public PersonData(String personName, String personLastName, String telNumber, String email, String groupName) {
    this.personName = personName;
    this.personLastName = personLastName;
    this.telNumber = telNumber;
    this.email = email;
    this.groupName = groupName;
  }

  public String getPersonName() {
    return personName;
  }

  public String getPersonLastName() {
    return personLastName;
  }

  public String getTelNumber() {
    return telNumber;
  }

  public String getEmail() {
    return email;
  }

  public String getGroupName() {
    return groupName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PersonData that = (PersonData) o;
    return Objects.equals(personName, that.personName) &&
            Objects.equals(personLastName, that.personLastName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(personName, personLastName);
  }

  @Override
  public String toString() {
    return "PersonData{" +
            "personName='" + personName + '\'' +
            ", personLastName='" + personLastName + '\'' +
            '}';
  }
}
