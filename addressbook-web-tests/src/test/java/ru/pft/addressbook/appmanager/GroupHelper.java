package ru.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.pft.addressbook.model.GroupData;
import ru.pft.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GroupHelper extends HelperBase {

  public GroupHelper(WebDriver wd) {
    super(wd);
  }

  public void create(GroupData groupData) {
    click(By.name("new"));
    fillGroupForm(groupData);
    submitForm();
    click(By.linkText("group page"));
  }

  public void modify(GroupData group) {
    selectedById(group.getId());
    editGroup();
    fillGroupForm(group);
    updateForm();
  }

  public void delete(int index) {
    select(index);
    delete();
  }

  public void delete() {
    click(By.name("delete"));
  }

  public void delete(GroupData group) {
    selectedById(group.getId());
    click(By.name("delete"));
  }

  private void selectedById(int id) {
    click(By.cssSelector("input[value = '" + id + "']"));
  }

  public void select(int i) {
    click(By.name("selected[]"));
  }

  public void fillGroupForm(GroupData groupData) {
    type(By.name("group_footer"), groupData.textFooter);
    type(By.name("group_header"), groupData.header);
    type(By.name("group_name"), groupData.groupName);
  }

  public void editGroup() {
    click(By.name("edit"));
  }

  public void updateForm() {
    click(By.name("update"));
  }

  public boolean isThereAGroup() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getGroupCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<GroupData> list() {
    List<GroupData> groups = new ArrayList<>();
    List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
    for (WebElement element : elements) {
      String name = element.getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      GroupData group = new GroupData().withId(id).withName(name);
      groups.add(group);
    }
    return groups;
  }

  public Groups all() {
    Groups groups = new Groups();
    List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
    for (WebElement element : elements) {
      String name = element.getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      GroupData group = new GroupData().withId(id).withName(name);
      groups.add(group);
    }
    return groups;
  }


}
