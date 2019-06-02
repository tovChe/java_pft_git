package ru.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.pft.addressbook.model.PersonData;

public class PersonHelper extends HelperBase {

  public PersonHelper(WebDriver wd) {
    super(wd);
  }

  public void addNewPerson() {
    click(By.linkText("add new"));
  }

  public void fillPersonForm(PersonData personData) {

    type(By.name("firstname"), personData.getPersonName());
    type(By.name("lastname"), personData.getPersonLastName());
    type(By.name("mobile"), personData.getTelNumber());
    type(By.name("email"), personData.getEmail());

  }

  public void editPerson() {
    click(By.xpath("//img[@alt='Edit']"));
  }
}
