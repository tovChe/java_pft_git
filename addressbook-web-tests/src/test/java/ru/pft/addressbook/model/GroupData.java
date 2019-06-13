package ru.pft.addressbook.model;

import java.util.Objects;

public class GroupData {

  public int id;
  public String textFooter;
  public String header;
  public String groupName;

  public GroupData(String header, String textFooter, String groupName) {
    this.id = 0;
    this.header = header;
    this.textFooter = textFooter;
    this.groupName = groupName;
  }
  public GroupData(int id, String header, String textFooter, String groupName) {
    this.id = id;
    this.header = header;
    this.textFooter = textFooter;
    this.groupName = groupName;
  }

  @Override
  public String toString() {
    return "GroupData{" +
            "id=" + id +
            ", groupName='" + groupName + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    GroupData groupData = (GroupData) o;
    return id == groupData.id &&
            Objects.equals(groupName, groupData.groupName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, groupName);
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getId() {
    return id;
  }

  public String getTextFooter() {
    return textFooter;
  }

  public String getHeader() {
    return header;
  }

  public String getGroupName() {
    return groupName;
  }
}
