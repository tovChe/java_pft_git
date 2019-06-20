package ru.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

public class GroupHelper extends HelperBase {

  public GroupHelper(WebDriver wd) {
    super(wd);
  }

  public void create(GroupData groupData) {
    //click(By.linkText("groups"));
    click(By.name("new"));
    fillGroupForm(groupData);
    submitForm();
    click(By.linkText("group page"));
  }

  public void modify(List<GroupData> before, GroupData group) {
    select(before.size() - 1);
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
}
