package ru.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupModification extends TestBase {

  @BeforeMethod
  public void preconditions() {
    app.goTo().groupPage();
    if (app.group().list().size() == 0) {
      app.group().create(new GroupData("Test header", "Test footer", "Test group name"));
    }
  }

  @Test
  public void groupModification() {

    List<GroupData> before = app.group().list();
    GroupData group = new GroupData(before.get(before.size() - 1).getId(), "Test header", "Test footer", "Test group name");
    app.group().modify(before, group);
    app.goTo().groupPage();
    List<GroupData> after = app.group().list();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(group);
    Comparator<? super GroupData> byID = Comparator.comparingInt(GroupData::getId);
    before.sort(byID);
    after.sort(byID);
    Assert.assertEquals(before, after);
  }


}
