package ru.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class CreateGroup extends TestBase {

  @Test
  public void testCreateGroup() {
    app.goTo().groupPage();
    List<GroupData> before = app.group().list();
    GroupData group = new GroupData().withName("Test group name").withHeader("Test header").withFooter("est footer");
    app.group().create(group);
    List<GroupData> after = app.group().list();
    Assert.assertEquals(after.size(), before.size() + 1);

    group.withId(after.stream().max(Comparator.comparingInt(GroupData::getId)).get().getId());
    before.add(group);
    Comparator<? super GroupData> byID = Comparator.comparingInt(GroupData::getId);
    before.sort(byID);
    after.sort(byID);
    Assert.assertEquals(before, after);
  }

}
