package ru.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.pft.addressbook.model.GroupData;

public class GroupHelper extends HelperBase {

  public GroupHelper(WebDriver wd) {
    super(wd);
  }

  public void createGroup(GroupData groupData) {
    //click(By.linkText("groups"));
    click(By.name("new"));
    fillGroupForm(groupData);
    submitForm();
    click(By.linkText("group page"));

  }

  public void deleteGroup() {
    click(By.name("delete"));
  }

  public void selectGroup() {
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

}
