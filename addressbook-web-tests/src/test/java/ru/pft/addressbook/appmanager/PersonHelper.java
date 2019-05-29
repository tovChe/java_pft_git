package ru.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.pft.addressbook.model.PersonData;

public class PersonHelper {

  private WebDriver wd;

  public PersonHelper(WebDriver wd) {
    this.wd = wd;
  }

  public void addNewPerson() {
    wd.findElement(By.linkText("add new")).click();
  }

  public void fillPersonForm(PersonData personData) {
    wd.findElement(By.name("firstname")).click();
    wd.findElement(By.name("firstname")).clear();
    wd.findElement(By.name("firstname")).sendKeys(personData.getPersonName());
    wd.findElement(By.name("lastname")).click();
    wd.findElement(By.name("lastname")).clear();
    wd.findElement(By.name("lastname")).sendKeys(personData.getPersonLastName());
    wd.findElement(By.name("mobile")).click();
    wd.findElement(By.name("mobile")).clear();
    wd.findElement(By.name("mobile")).sendKeys(personData.getTelNumber());
    wd.findElement(By.name("email")).click();
    wd.findElement(By.name("email")).clear();
    wd.findElement(By.name("email")).sendKeys(personData.getEmail());
  }
}
