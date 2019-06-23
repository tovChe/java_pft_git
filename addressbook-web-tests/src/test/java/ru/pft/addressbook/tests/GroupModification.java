package ru.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.pft.addressbook.model.GroupData;
import ru.pft.addressbook.model.Groups;

public class GroupModification extends TestBase {

  @BeforeMethod
  public void preconditions() {
    app.goTo().groupPage();
    if (app.group().list().size() == 0) {
      app.group().create(new GroupData().withName("Test group name").withHeader("Test header").withFooter("Test footer"));
    }
  }

  @Test
  public void groupModification() {

    Groups before = app.group().all();
    GroupData modifiedGroup = before.iterator().next();
    GroupData group = new GroupData().withId(modifiedGroup.getId()).withHeader("Test header").withFooter("Test footer").withName("Test group name");
    app.group().modify(group);
    app.goTo().groupPage();
    Groups after = app.group().all();
    Assert.assertEquals(after.size(), before.size());

    before.remove(modifiedGroup);
    before.add(group);
    Assert.assertEquals(before, after);
    MatcherAssert.assertThat(after, CoreMatchers.equalTo(before.without(modifiedGroup).withAdded(group)));
  }


}
