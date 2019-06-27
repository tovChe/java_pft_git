package ru.pft.addressbook.model;

import java.util.Objects;

public class PersonData {
  private int id;
  private String personName;
  private String personLastName;
  private String mobilePhone;
  private String homePhone;
  private String workPhone;
  private String email;
  private String groupName;

  public int getId() {
    return id;
  }

  public PersonData withId(int id) {
    this.id = id;
    return this;
  }

  public String getPersonName() {
    return personName;
  }

  public String getPersonLastName() {
    return personLastName;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }

  public String getHomePhone() {
    return homePhone;
  }

  public String getWorkPhone() {
    return workPhone;
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

  public PersonData withMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
    return this;
  }
  public PersonData withHomePhone(String homePhone) {
    this.homePhone = homePhone;
    return this;
  }
  public PersonData withWorkPhone(String workPhone) {
    this.workPhone = workPhone;
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
    return id == that.id &&
            Objects.equals(personName, that.personName) &&
            Objects.equals(personLastName, that.personLastName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, personName, personLastName);
  }

  @Override
  public String toString() {
    return "PersonData{" +
            "personName='" + personName + '\'' +
            ", personLastName='" + personLastName + '\'' +
            '}';
  }


}
