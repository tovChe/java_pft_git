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
    wd.get("http://localhost/addressbook/");
    login("admin", "secret");
  }

  private void login(String name, String password) {
    wd.findElement(By.name("user")).clear();
    wd.findElement(By.name("user")).sendKeys("admin");
    wd.findElement(By.name("pass")).clear();
    wd.findElement(By.name("pass")).sendKeys("secret");
    wd.findElement(By.xpath("//input[@value='Login']")).click();

  }

  @Test
  public void testCreateGroup() throws Exception {
    createGroup();
    fillGroupForm();
    submitForm();
    returnToGroupPage();
  }

  private void fillGroupForm() {
    GroupData gdata = new GroupData("Lopar", "Zaverda", "TestGroup Name");
    wd.findElement(By.name("group_footer")).click();
    wd.findElement(By.name("group_footer")).sendKeys(gdata.textFooter);
    wd.findElement(By.name("group_header")).click();
    wd.findElement(By.name("group_header")).sendKeys(gdata.header);
    wd.findElement(By.name("group_name")).sendKeys(gdata.groupName);
  }

  private void createGroup() {
    wd.findElement(By.linkText("groups")).click();
    wd.findElement(By.name("new")).click();
  }

//  private void fillGroupForm(GroupData gdata) {
//    wd.findElement(By.name("group_footer")).click();
//    wd.findElement(By.name("group_footer")).sendKeys(gdata.textFooter);
//    wd.findElement(By.name("group_header")).click();
//    wd.findElement(By.name("group_header")).sendKeys(gdata.header);
//    wd.findElement(By.name("group_name")).sendKeys(gdata.groupName);
//  }

  private void submitForm() {
    wd.findElement(By.name("submit")).click();
  }

  private void returnToGroupPage() {
    wd.findElement(By.linkText("group page")).click();
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
