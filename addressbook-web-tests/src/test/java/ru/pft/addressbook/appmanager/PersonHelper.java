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
    type(By.name("mobile"), personData.getMobilePhone());
    type(By.name("home"), personData.getHomePhone());
    type(By.name("work"), personData.getWorkPhone());
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
    personCache = null;
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
    personCache = null;
    click(By.linkText("home"));
  }

  public void modify(PersonData person) {
    initPersonModificationWithId(person.getId());
    fillPersonForm(new PersonData().withName("Tester Meister").withLastName("Lenin").withMobilePhone("+79189999999").withEmail("tester@yahoooo.com"), false);
    updateForm();
    personCache = null;
  }

  public int count() {
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

  private Persons personCache = null;

  public Persons all() {
    if (personCache != null) {
      return new Persons(personCache);
    }
    personCache = new Persons();
    List<WebElement> rows = wd.findElements(By.cssSelector("tr[name = 'entry']"));
    for (WebElement row : rows) {
      List<WebElement> cells = row.findElements(By.tagName("td"));
      int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("value"));
      String name = cells.get(2).getText();
      String lastName = cells.get(1).getText();
      String[] phones = cells.get(5).getText().split("\n");
      PersonData person = new PersonData().withId(id).withName(name).withLastName(lastName)
              .withMobilePhone(phones[0]).withHomePhone(phones[1]).withWorkPhone(phones[2]);
      personCache.add(person);
    }
    return personCache;
  }

  public void initPersonModificationWithId(int id) {
    wd.findElement(By.xpath(String.format("//input[@value = '%s']/../../td[8]/a", id))).click();
  }

  public PersonData infoFromEditForm(PersonData person) {
    initPersonModificationWithId(person.getId());
    //String name = wd.findElement(By.cssSelector("td:nth-child(3)")).getText();
    //String lastName = wd.findElement(By.cssSelector("td:nth-child(2)")).getText();
    String name = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");

    return person;
  }
}
