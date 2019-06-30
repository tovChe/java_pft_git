package ru.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.util.Objects;

@XStreamAlias("group")
public class GroupData {

  @XStreamOmitField
  public int id = Integer.MAX_VALUE;

  @Expose
  public String textFooter;
  @Expose
  public String header;
  @Expose
  public String groupName;

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
