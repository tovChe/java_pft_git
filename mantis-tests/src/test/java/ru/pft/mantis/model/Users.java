package ru.pft.mantis.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Users extends ForwardingSet<User> {

  private Set<User> delegate;
  private int id;


  @Override
  protected Set<User> delegate() {
    return delegate;
  }
  public Users(Users users) {
    this.delegate = new HashSet<>(users.delegate);
  }

  public Users() {
    this.delegate = new HashSet<>();
  }

  public Users(Collection<User> persons) {
    this.delegate = new HashSet<>(persons);
  }

  public Users withAdded(User person) {
    Users persons = new Users(this);
    persons.add(person);
    return persons;
  }

  public Users without(User person) {
    Users persons = new Users(this);
    persons.remove(person);
    return persons;
  }
  public int getId() {
    return id;
  }
}
