package ru.pft.addressbook.tests;

import org.hamcrest.MatcherAssert;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.pft.addressbook.appmanager.ApplicationManager;
import ru.pft.addressbook.model.GroupData;
import ru.pft.addressbook.model.Groups;
import ru.pft.addressbook.model.PersonData;
import ru.pft.addressbook.model.Persons;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

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
  public void startTestLog(Method m) {
    logger.debug("Start logging " + m.getName());
  }

  @AfterMethod(alwaysRun = true)
  public void stopTestLog(Method m) {
    logger.debug("Stop logging " + m.getName());
  }

  protected void verifyGroupListUI() {
    if (Boolean.getBoolean("verifyListUI")) {
      Groups dbGroups = app.db().groups();
      Groups uiGroups = app.group().all();
      assertThat(uiGroups, equalTo(dbGroups.stream().map((g) -> new GroupData()
              .withId(g.getId())
              .withName(g.getGroupName()))
              .collect((Collectors.toSet()))));
    }
  }
  protected void verifyPersonListUI() {
    if (Boolean.getBoolean("verifyListUI")) {
      Persons dbPersons = app.db().persons();
      Persons uiPersons = app.person().all();
      assertThat(uiPersons, equalTo(dbPersons.stream().map((p) -> new PersonData()
              .withId(p.getId())
              .withName(p.getPersonName())
              .withLastName(p.getPersonLastName()))
              .collect(Collectors.toSet())));
    }
  }
}
