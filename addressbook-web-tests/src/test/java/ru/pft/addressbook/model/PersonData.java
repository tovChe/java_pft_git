package ru.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "addressbook")
public class PersonData {

  @Id
  @Column(name = "id")
  private int id;

  @Expose
  @Column(name = "firstname")
  private String personName;

  @Expose
  @Column(name = "lastname")
  private String personLastName;

  @Expose
  @Column(name = "mobile")
  @Type(type = "text")
  private String mobilePhone;

  @Expose
  @Column(name = "home")
  @Type(type = "text")
  private String homePhone;

  @Expose
  @Column(name = "work")
  @Type(type = "text")
  private String workPhone;

  @Transient
  private String allPhones;

  @Expose
  @Column(name = "email")
  @Type(type = "text")
  private String email;

  @Transient
  private String allEmails;

  @Expose
  @Column(name = "email2")
  @Type(type = "text")
  private String email2;

  @Expose
  @Type(type = "text")
  @Column(name = "email3")
  private String email3;

  @Expose
  @Column(name = "address")
  @Type(type = "text")
  private String address;

  @Expose
  @Column(name = "photo")
  @Type(type = "text")
  private String photo;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "address_in_groups", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
  private Set<GroupData> groups = new HashSet<GroupData>();

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

  public String getAllPhones() {
    return allPhones;
  }

  public String getEmail() {
    return email;
  }

  public String getEmail2() {
    return email2;
  }

  public String getEmail3() {
    return email3;
  }

  public String getAllEmails() {
    return allEmails;
  }

  public String getAddress() {
    return address;
  }

  public File getPhoto() {
    return new File(photo);
  }

  public PersonData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }

  public PersonData withAddress(String address) {
    this.address = address;
    return this;
  }

  public PersonData withName(String personName) {
    this.personName = personName;
    return this;
  }

  public PersonData withLastName(String personLastName) {
    this.personLastName = personLastName;
    return this;
  }

  public Groups getGroups() {
    return new Groups(groups);
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

  public PersonData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public PersonData withEmail(String email) {
    this.email = email;
    return this;
  }

  public PersonData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public PersonData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public PersonData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
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
            "id=" + id +
            ", personName='" + personName + '\'' +
            ", personLastName='" + personLastName + '\'' +
            ", mobilePhone='" + mobilePhone + '\'' +
            ", homePhone='" + homePhone + '\'' +
            ", workPhone='" + workPhone + '\'' +
            ", allPhones='" + allPhones + '\'' +
            ", email='" + email + '\'' +
            ", allEmails='" + allEmails + '\'' +
            ", email2='" + email2 + '\'' +
            ", email3='" + email3 + '\'' +
            ", address='" + address + '\'' +
            ", photo='" + photo + '\'' +
            ", groups=" + groups +
            '}';
  }

  public PersonData inGroup(GroupData group) {
    groups.add(group);
    return this;
  }
  public PersonData delGroup() {
    groups.remove(getGroups().iterator().next());
    return this;
  }
}
