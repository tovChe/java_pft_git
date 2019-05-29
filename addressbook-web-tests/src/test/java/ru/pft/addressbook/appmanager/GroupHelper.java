package ru.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.pft.addressbook.model.GroupData;

public class GroupHelper {

  private WebDriver wd;

  public GroupHelper(WebDriver wd) {
    this.wd = wd;
  }

  public void createGroup() {
    wd.findElement(By.linkText("groups")).click();
    wd.findElement(By.name("new")).click();
  }

  public void deleteGroup() {
    wd.findElement(By.name("delete")).click();
  }

  public void selectGroup() {
    wd.findElement(By.name("selected[]")).click();
  }

  public void fillGroupForm(GroupData groupData) {
    wd.findElement(By.name("group_footer")).click();
    wd.findElement(By.name("group_footer")).sendKeys(groupData.textFooter);
    wd.findElement(By.name("group_header")).click();
    wd.findElement(By.name("group_header")).sendKeys(groupData.header);
    wd.findElement(By.name("group_name")).sendKeys(groupData.groupName);
  }
}
