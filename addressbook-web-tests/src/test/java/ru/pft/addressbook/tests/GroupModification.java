package ru.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupModification extends TestBase {

  @Test
  public void groupModification() {

    app.getNavigationHelper().gotoGroupPage();
    if (!app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("Test header", "Test footer", "Test group name"));
    }
    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(before.size() - 1);
    app.getGroupHelper().editGroup();
    GroupData group = new GroupData(before.get(before.size() - 1).getId(),"Test header", "Test footer", "Test group name");
    app.getGroupHelper().fillGroupForm(group);
    app.getGroupHelper().updateForm();
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
