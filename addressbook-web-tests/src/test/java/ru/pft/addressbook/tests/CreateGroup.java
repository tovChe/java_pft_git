package ru.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

public class CreateGroup extends TestBase {


  @Test
  public void testCreateGroup() {
    app.getNavigationHelper().gotoGroupPage();
    List<GroupData> before = app.getGroupHelper().getGroupList();
    GroupData group = new GroupData("Test header", "Test footer", "Test group name");
    app.getGroupHelper().createGroup(group);
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), before.size() + 1);

    int max = 0;
    before.add(group);
    for (GroupData g : after) {

      if (g.getId() > max) {
        max = g.getId();
      }
    }
    group.setId(max);
    Assert.assertEquals(new HashSet<>(before), new HashSet<>(after));
  }

}
