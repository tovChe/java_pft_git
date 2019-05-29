package ru.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.pft.addressbook.model.GroupData;

public class GroupHelper extends HelperBase {

  public GroupHelper(WebDriver wd) {
    super(wd);
  }

  public void createGroup() {
    click(By.linkText("groups"));
    click(By.name("new"));
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

}
