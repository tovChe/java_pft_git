package ru.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class HelperBase {

  protected ApplicationManager app;
  protected WebDriver wd;

  public HelperBase(ApplicationManager app) {
    this.app = app;
    this.wd = app.getDriver();
  }

  protected void click(By locator) {
    wd.findElement(locator).click();
  }

  protected void type(By locator, String text) {
    click(locator);
    if (text != null) {
      String existingText = wd.findElement(locator).getAttribute("value");
      if (!(text == null)) {
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(text);
      }
    } else {
      System.out.println("NULL in text field!");
    }
  }

  protected void attach(By locator, File file) {
    if (!(file == null)) {

      wd.findElement(locator).sendKeys(file.getAbsolutePath());
    } else {
      System.out.println("File not exist!");
    }
  }

  public boolean isElementPresent(By locator) {
    try {
      wd.findElement(locator);
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

  public void submitForm() {
    wd.findElement(By.name("submit")).click();
  }

  public void updateForm() {
    wd.findElement(By.name("update")).click();
  }
}
