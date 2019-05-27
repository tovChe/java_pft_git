package ru.pft.addressbook;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class CreateGroup {
  private WebDriver wd;

  @BeforeMethod(alwaysRun = true)
  public void setUp() throws Exception {
    wd = new FirefoxDriver();
    wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    login("admin", "secret");
  }

  private void login(String name, String password) {
    wd.get("http://localhost/addressbook/");
    wd.findElement(By.name("user")).clear();
    wd.findElement(By.name("user")).sendKeys(name);
    wd.findElement(By.name("pass")).clear();
    wd.findElement(By.name("pass")).sendKeys(password);
    wd.findElement(By.xpath("//input[@value='Login']")).click();
  }

  @Test
  public void testCreateGroup() throws Exception {
    wd.findElement(By.linkText("groups")).click();
    createGroup();
    groupName("Group1");
    groupHeader("test1");
    groupFooter("tes2");
    submitForm();
    returnToGroupPage();
  }

  private void returnToGroupPage() {
    wd.findElement(By.linkText("group page")).click();
  }

  private void submitForm() {
    wd.findElement(By.name("submit")).click();
  }

  private void groupFooter(String textFooter) {
    wd.findElement(By.name("group_footer")).click();
    wd.findElement(By.name("group_footer")).sendKeys(textFooter);
  }

  private void groupHeader(String textHeader) {
    wd.findElement(By.name("group_header")).click();
    wd.findElement(By.name("group_header")).sendKeys(textHeader);
  }

  private void groupName(String groupName) {
    wd.findElement(By.name("group_name")).sendKeys(groupName);
  }

  private void createGroup() {
    wd.findElement(By.name("new")).click();
    wd.findElement(By.name("group_name")).click();
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() throws Exception {
    logout();
    wd.quit();
  }

  private void logout() {
    wd.findElement(By.linkText("Logout")).click();
  }

  private boolean isElementPresent(By by) {
    try {
      wd.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }
}
