package ru.pft.addressbook.appmanager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.pft.addressbook.model.PersonData;

import static org.testng.Assert.assertTrue;

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
  }
  public void selectPerson() {
    click(By.name("selected[]"));
  }

  public void selectPersonByID(String id){
    click(By.id(id));
  }
}

