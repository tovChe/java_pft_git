package ru.pft.addressbook.model;

public class GroupData {

  public String textFooter;
  public String header;
  public String groupName;

  public GroupData(String header, String textFooter, String groupName) {
    this.header = header;
    this.textFooter = textFooter;
    this.groupName = groupName;
  }
}
