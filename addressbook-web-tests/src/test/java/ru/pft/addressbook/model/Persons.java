package ru.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Persons extends ForwardingSet<PersonData> {
  private Set<PersonData> delegate;
  private int id;

  public Persons(Persons persons) {
    this.delegate = new HashSet<>(persons.delegate);
  }

  public Persons() {
    this.delegate = new HashSet<>();
  }

  public Persons(Collection<PersonData> persons) {
    this.delegate = new HashSet<>(persons);
  }

  public Persons withAdded(PersonData person) {
    Persons persons = new Persons(this);
    persons.add(person);
    return persons;
  }

  public Persons without(PersonData person) {
    Persons persons = new Persons(this);
    persons.remove(person);
    return persons;
  }

  @Override
  protected Set delegate() {
    return delegate;
  }

  public int getId() {
    return id;
  }
}
