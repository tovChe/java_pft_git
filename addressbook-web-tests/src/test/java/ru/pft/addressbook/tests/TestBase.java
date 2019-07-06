package ru.pft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.pft.addressbook.appmanager.ApplicationManager;

import java.io.IOException;
import java.lang.reflect.Method;

public class TestBase {

  static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));
  Logger logger = LoggerFactory.getLogger(CreateGroup.class);

  @BeforeSuite
  public void setUp() throws IOException {
    app.init();
  }

  @AfterSuite
  public void tearDown() {
    app.stop();
  }

  @BeforeMethod
  public void startTestLog(Method m){
    logger.debug("Start logging " + m.getName());
  }
  @AfterMethod(alwaysRun = true)
  public void stopTestLog(Method m){
    logger.debug("Stop logging " + m.getName());
  }

}
