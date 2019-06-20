package ru.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

  WebDriver wd;

  private SessionHelper sessionHelper;
  private NavigationHelper navigationHelper;
  private PersonHelper personHelper;
  private GroupHelper groupHelper;
  private String browser;

  public ApplicationManager(String browser) {
    this.browser = browser;
  }

  public void init() {
    if (browser.equals(BrowserType.FIREFOX)) {
      wd = new FirefoxDriver();
    } else if (browser.equals(BrowserType.CHROME)) {
      wd = new ChromeDriver();
    } else if (browser.equals(BrowserType.IE)) {
      wd = new InternetExplorerDriver();
    } else {
      System.out.println("Use for tests only IE, Chrome, Firefox");
    }

    wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    wd.get("http://localhost/addressbook/");
    navigationHelper = new NavigationHelper(wd);
    groupHelper = new GroupHelper(wd);
    personHelper = new PersonHelper(wd);
    sessionHelper = new SessionHelper(wd);
    sessionHelper.login("admin", "secret");
  }

  public void stop() {
    sessionHelper.logout();
    wd.quit();
  }


  public SessionHelper getSessionHelper() {
    return sessionHelper;
  }

  public GroupHelper group() {
    return groupHelper;
  }

  public PersonHelper person() {
    return personHelper;
  }

  public NavigationHelper goTo() {
    return navigationHelper;
  }

}
