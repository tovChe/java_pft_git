package ru.pft.addressbook;

public class GroupData {

  String textFooter;
  String header;
  String groupName;

  public GroupData(String header, String textFooter, String groupName){
    this.header = header;
    this.textFooter = textFooter;
    this.groupName = groupName;
  }
}
