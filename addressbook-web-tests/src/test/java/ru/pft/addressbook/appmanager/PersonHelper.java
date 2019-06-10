package ru.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.pft.addressbook.model.PersonData;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class PersonHelper extends HelperBase {

  public PersonHelper(WebDriver wd) {
    super(wd);
  }

  public void addNewPerson() {
    click(By.linkText("add new"));
  }

  public void fillPersonForm(PersonData personData, boolean creation) {

    type(By.name("firstname"), personData.getPersonName());
    type(By.name("lastname"), personData.getPersonLastName());
    type(By.name("mobile"), personData.getTelNumber());
    type(By.name("email"), personData.getEmail());

    if (creation) {
      new Select(wd.findElement(By.name("new_group")));
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void editPerson(int i) {
    click(By.xpath("//img[@alt='Edit']"));
  }

  /*public void acceptConfirmation(String message) {
    assertTrue(closeAlertAndGetItsText().matches(message));
  }

  private String closeAlertAndGetItsText() {
    boolean acceptNextAlert = true;
    try {
      Alert alert = wd.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }*/
  public void deletePerson() {
    click(By.xpath("//input[@value='Delete']"));
    wd.switchTo().alert().accept();
    click(By.linkText("home"));
  }

  public void selectPerson(int i) {
    click(By.name("selected[]"));
  }

  public void selectAll() {
    click(By.id("MassCB"));
  }

  public void selectPersonByID(String id) {
    click(By.id(id));
  }

  public boolean isThereAPerson() {
    return isElementPresent(By.name("selected[]"));
  }

  public void createPerson(PersonData personData, boolean creation) {
    addNewPerson();
    fillPersonForm(personData, creation);
    submitForm();
    click(By.linkText("home page"));
  }

  public int getPersonCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<PersonData> getPersonList() {
    List<PersonData> persons = new ArrayList<>();
    List<WebElement> elements = wd.findElements(By.name("selected[]"));
    for (WebElement element : elements){
      String name = element.getText();
      PersonData person = new PersonData(name, null,null,null,null);
      persons.add(person);
    }
    return persons;
  }
}

