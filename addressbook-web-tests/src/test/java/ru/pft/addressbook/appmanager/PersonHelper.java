package ru.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.pft.addressbook.model.PersonData;
import ru.pft.addressbook.model.Persons;

import java.util.ArrayList;
import java.util.List;

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

  public void editPerson(PersonData person) {
    click(By.xpath("//img[@alt='Edit']"));
  }


  public void delete() {
    click(By.xpath("//input[@value='Delete']"));
    wd.switchTo().alert().accept();
    click(By.linkText("home"));
  }

  public void select(PersonData person) {
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

  //  public void modify(Set<PersonData> before) {
//    editPerson(before.size() - 1);
//    fillPersonForm(new PersonData().withName("Tester Meister").withLastName("Lenin").withTelNumber("+79189999999").withEmail("tester@yahoooo.com"), false);
//    updateForm();
//  }
  public void modify(PersonData person) {
    //editPerson(person.withName("Tester").withLastName("Testovoy"));
    initPersonModificationWithId(person.getId());
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

  public Persons all() {
    Persons persons = new Persons();
    List<WebElement> elements = wd.findElements(By.cssSelector("tr[name = 'entry']"));
    for (WebElement element : elements) {
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      String name = element.findElement(By.cssSelector("td:nth-child(3)")).getText();
      String lastName = element.findElement(By.cssSelector("td:nth-child(2)")).getText();
      PersonData person = new PersonData().withId(id).withName(name).withLastName(lastName);
      persons.add(person);
    }
    return persons;
  }

  public void initPersonModificationWithId(int id) {
    wd.findElement(By.xpath(String.format("//input[@value = '%s']/../../td[8]/a", id))).click();
    //click(By.cssSelector("input[value = '" + id + "']"));
  }
}
