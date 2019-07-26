package ru.pft.addressbook.appmanager;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

  WebDriver wd;

  private SessionHelper sessionHelper;
  private NavigationHelper navigationHelper;
  private PersonHelper personHelper;
  private GroupHelper groupHelper;
  private String browser;
  private DbHelper dbHelper;

  public ApplicationManager(String browser) {
    this.browser = browser;
  }

  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    Properties properties = new Properties();
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

    dbHelper = new DbHelper();

    if ("".equals(properties.getProperty("selenium.server"))) {
      if (browser.equals(BrowserType.FIREFOX)) {
        wd = new FirefoxDriver();
      } else if (browser.equals(BrowserType.CHROME)) {
        wd = new ChromeDriver();
      } else if (browser.equals(BrowserType.IE)) {
        wd = new InternetExplorerDriver();
      } else {
        System.out.println("Use for tests only IE, Chrome, Firefox");
      }
    } else {
      DesiredCapabilities capability = new DesiredCapabilities();
      capability.setBrowserName(browser);
      wd = new RemoteWebDriver(new URL(properties.getProperty("selenium.server")), capability);
    }


    wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    wd.get(properties.getProperty("web.baseUrl"));
    navigationHelper = new NavigationHelper(wd);
    groupHelper = new GroupHelper(wd);
    personHelper = new PersonHelper(wd);
    sessionHelper = new SessionHelper(wd);
    sessionHelper.login(properties.getProperty("web.login"), properties.getProperty("web.password"));
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

  public DbHelper db(){
    return dbHelper;
  }

}
