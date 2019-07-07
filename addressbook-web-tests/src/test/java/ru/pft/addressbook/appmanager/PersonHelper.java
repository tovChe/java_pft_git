package ru.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.pft.addressbook.model.PersonData;
import ru.pft.addressbook.model.Persons;

import java.io.File;
import java.io.IOException;
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
    attach(By.name("photo"), personData.getPhoto());

    if (creation) {
      if (personData.getGroups().size() != 0) {
        Assert.assertTrue(personData.getGroups().size() == 1);
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(personData.getGroups().iterator().next().getGroupName());
      }
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

  public void selectPersonByID(int id) {
    click(By.xpath(String.format("//*[@id=\"%s\"]",id)));
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
    fillPersonForm(person, false);
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
      String allPhones = cells.get(5).getText();
      String allEmails = cells.get(4).getText();
      String address = cells.get(3).getText();
      PersonData person = new PersonData().withId(id)
              .withName(name)
              .withLastName(lastName)
              .withAllEmails(allEmails)
              .withAllPhones(allPhones)
              .withAddress(address);
      personCache.add(person);
    }
    return personCache;
  }

  public void initPersonModificationWithId(int id) {
    wd.findElement(By.xpath(String.format("//input[@value = '%s']/../../td[8]/a", id))).click();
  }

  public PersonData infoFromEditForm(PersonData person) {
    initPersonModificationWithId(person.getId());
    String name = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    wd.navigate().back();

    return new PersonData().withId(person.getId())
            .withLastName(lastName).withName(name)
            .withEmail(email).withEmail2(email2).withEmail3(email3)
            .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work)
            .withAddress(address);
  }

  public void approvedAddGroup(PersonData personAdd) {
    wd.findElement(By.tagName("h1")).getText().equals("Groups");
    Assert.assertTrue(isElementPresent(By.linkText("group page \"" + personAdd.getGroups().iterator().next().getGroupName() +"\"")));

  }

  public void approvedDelGroup(PersonData deletedPerson) {
    wd.findElement(By.tagName("h1")).getText().equals("Groups");
    Assert.assertTrue(isElementPresent(By.linkText("group page \"" + deletedPerson.getGroups().iterator().next().getGroupName() +"\"")));
  }


  public void addToGroup(PersonData personAdd) {
    Assert.assertEquals(personAdd.getGroups().size(), 1);
    Select groupSelector = new Select(wd.findElement(By.name("to_group")));
    groupSelector.selectByVisibleText(personAdd.getGroups().iterator().next().getGroupName());
    select(personAdd);
    click(By.name("add"));
    approvedAddGroup(personAdd);
  }

  public void delFromGroup(PersonData deletedPerson) {
    Assert.assertEquals(deletedPerson.getGroups().size(), 1);
    selectPersonByID(deletedPerson.getId());
    click(By.name("remove"));
    approvedDelGroup(deletedPerson);
  }

  public void personGroupPage(PersonData deletedPerson) {

    Select select = new Select(wd.findElement(By.name("group")));
    select.selectByVisibleText(deletedPerson.getGroups().iterator().next().getGroupName());

  }
}
