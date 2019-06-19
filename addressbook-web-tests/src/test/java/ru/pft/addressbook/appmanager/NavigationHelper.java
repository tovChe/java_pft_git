package ru.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {

  public NavigationHelper(WebDriver wd) {
    super(wd);
  }

  public void returnToGroupPage() {
    click(By.linkText("group page")); //.admin > a:nth-child(1)
  }

  public void returnHomePage() {

    if (isElementPresent(By.id("maintable"))){
      return;
    }
    click(By.cssSelector("#nav > ul:nth-child(1) > li:nth-child(1) > a:nth-child(1)")); //#nav > ul:nth-child(1) > li:nth-child(1) > a:nth-child(1)
  }

  public void gotoGroupPage() {

    if (isElementPresent(By.tagName("h1"))
            && wd.findElement(By.tagName("h1")).getText().equals("Groups")
            && isElementPresent(By.name("new"))) {
      return;
    }
    click(By.linkText("groups"));
  }
}
