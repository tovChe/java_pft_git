package ru.pft.addressbook.model;

import java.util.Objects;

public class GroupData {

  public int id = Integer.MAX_VALUE;
  public String textFooter;
  public String header;
  public String groupName;

/*  public GroupData(String header, String textFooter, String withGroup) {
    this.id = 0;
    this.header = header;
    this.textFooter = textFooter;
    this.withGroup = withGroup;
  }
  public GroupData(int id, String header, String textFooter, String withGroup) {
    this.id = id;
    this.header = header;
    this.textFooter = textFooter;
    this.withGroup = withGroup;
  }*/

  @Override
  public String toString() {
    return "GroupData{" +
            "id=" + id +
            ", withGroup='" + groupName + '\'' +
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

  public GroupData withId(int id) {
    this.id = id;
    return this;
  }

  public int getId() {
    return id;
  }
  public GroupData withFooter(String textFooter) {
    this.textFooter = textFooter;
    return this;
  }

  public GroupData withHeader(String header) {
    this.header = header;
    return this;
  }

  public GroupData withName(String groupName) {
    this.groupName = groupName;
    return this;
  }

  /*public String getTextFooter() {
    return textFooter;
  }

  public String getHeader() {
    return header;
  }

  public String getGroupName() {
    return withGroup;
  }*/
}
