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
    app.getNavigationHelper().gotoGroupPage();
    if (!app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("Test header", "Test footer", "Test group name"));
    }
  }

  @Test
  public void groupModification() {

    List<GroupData> before = app.getGroupHelper().getGroupList();
    GroupData group = new GroupData(before.get(before.size() - 1).getId(), "Test header", "Test footer", "Test group name");
    app.getGroupHelper().modifyGroup(before, group);
    app.getNavigationHelper().gotoGroupPage();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(group);
    Comparator<? super GroupData> byID = Comparator.comparingInt(GroupData::getId);
    before.sort(byID);
    after.sort(byID);
    Assert.assertEquals(before, after);
  }


}
