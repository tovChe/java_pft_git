package ru.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XStreamAlias("group")
@Entity
@Table(name = "group_list")
public class GroupData {

  @XStreamOmitField
  @Id
  @Column(name = "group_id")
  public int id = Integer.MAX_VALUE;

  @Expose
  @Column(name = "group_footer")
  @Type(type = "text")
  public String textFooter;
  @Expose
  @Column(name = "group_header")
  @Type(type = "text")
  public String header;
  @Expose
  @Column(name = "group_name")
  public String groupName;

  @ManyToMany(mappedBy = "groups", fetch = FetchType.EAGER)
  private Set<PersonData> persons = new HashSet<PersonData>();

  public Persons getPersons() {
    return new Persons(persons);
  }

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
            Objects.equals(textFooter, groupData.textFooter) &&
            Objects.equals(header, groupData.header) &&
            Objects.equals(groupName, groupData.groupName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, textFooter, header, groupName);
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
