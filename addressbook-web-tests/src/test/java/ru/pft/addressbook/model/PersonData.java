package ru.pft.addressbook.model;

import java.util.Objects;

public class PersonData {
  private String personName;
  private String personLastName;
  private String telNumber;
  private String email;
  private String groupName;

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

  public PersonData withName(String personName) {
    this.personName = personName;
    return this;
  }

  public PersonData withLastName(String personLastName) {
    this.personLastName = personLastName;
    return this;
  }

  public PersonData withGroup(String groupName) {
    this.groupName = groupName;
    return this;
  }

  public PersonData withTelNumber(String telNumber) {
    this.telNumber = telNumber;
    return this;
  }

  public PersonData withEmail(String email) {
    this.email = email;
    return this;
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
