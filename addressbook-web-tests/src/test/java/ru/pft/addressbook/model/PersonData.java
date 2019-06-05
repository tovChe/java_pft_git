package ru.pft.addressbook.model;

public class PersonData {
  private final String personName;
  private final String personLastName;
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
}
