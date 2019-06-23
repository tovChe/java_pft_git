package ru.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.pft.addressbook.model.PersonData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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


  public void delete() {
    click(By.xpath("//input[@value='Delete']"));
    wd.switchTo().alert().accept();
    click(By.linkText("home"));
  }

  public void select(int person) {
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

  public void create(PersonData personData, boolean creation) {
    addNewPerson();
    fillPersonForm(personData, creation);
    submitForm();
    click(By.linkText("home"));
  }

  public void modify(Set<PersonData> before) {
    editPerson(before.size() - 1);
    fillPersonForm(new PersonData().withName("Tester Meister").withLastName("Lenin").withTelNumber("+79189999999").withEmail("tester@yahoooo.com"), false);
    updateForm();
  }

  public int getPersonCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<PersonData> list() {
    List<PersonData> persons = new ArrayList<>();
    List<WebElement> elements = wd.findElements(By.cssSelector("tr[name = 'entry']"));
    for (WebElement element : elements) {
      String name = element.findElement(By.cssSelector("td:nth-child(3)")).getText();
      String lastName = element.findElement(By.cssSelector("td:nth-child(2)")).getText();
      PersonData person = new PersonData().withName(name).withLastName(lastName);
      persons.add(person);
    }
    return persons;
  }

  public Set<PersonData> all() {
    Set<PersonData> persons = new HashSet<>();
    List<WebElement> elements = wd.findElements(By.cssSelector("tr[name = 'entry']"));
    for (WebElement element : elements) {
      String name = element.findElement(By.cssSelector("td:nth-child(3)")).getText();
      String lastName = element.findElement(By.cssSelector("td:nth-child(2)")).getText();
      PersonData person = new PersonData().withName(name).withLastName(lastName);
      persons.add(person);
    }
    return persons;
  }
}
