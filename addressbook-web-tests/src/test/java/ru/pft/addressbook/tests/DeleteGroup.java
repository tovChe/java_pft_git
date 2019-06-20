package ru.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class DeleteGroup extends TestBase {

  @BeforeMethod
  public void preconditions() {
    app.goTo().groupPage();
    if (app.group().list().size() == 0) {
      app.group().create(new GroupData().withName("Test group name").withHeader("Test header").withFooter("Test footer"));
    }
  }

  @Test
  public void testDeleteGroup() {

    app.goTo().groupPage();
    List<GroupData> before = app.group().list();
    int index = before.size() - 1;
    app.group().delete(index);
    app.goTo().returnToGroupPage();
    List<GroupData> after = app.group().list();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(index);
    Comparator<? super GroupData> byID = Comparator.comparingInt(GroupData::getId);
    before.sort(byID);
    after.sort(byID);
    Assert.assertEquals(before, after);
    System.out.println("Checking is green!!!");
  }
}
