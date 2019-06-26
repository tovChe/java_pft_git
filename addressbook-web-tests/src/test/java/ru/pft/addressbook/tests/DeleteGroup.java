package ru.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.pft.addressbook.model.GroupData;
import ru.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

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
    Groups before = app.group().all();
    GroupData deletedGroup = before.iterator().next();
    app.group().delete(deletedGroup);
    app.goTo().returnToGroupPage();
    assertThat(app.group().count(), equalTo(before.size() - 1));
    Groups after = app.group().all();
    before.remove(deletedGroup);
    assertThat(after, equalTo(before.without(deletedGroup)));
    System.out.println("Group was deleted!!!");
  }
}
