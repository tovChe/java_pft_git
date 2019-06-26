package ru.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.pft.addressbook.model.GroupData;
import ru.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreateGroup extends TestBase {

  @Test
  public void testCreateGroup() {
    app.goTo().groupPage();
    Groups before = app.group().all();
    GroupData group = new GroupData().withName("Test group name").withHeader("Test header").withFooter("Test footer");
    app.group().create(group);
    assertThat(app.group().count(), equalTo(before.size() + 1));
    Groups after = app.group().all();

    assertThat(after, equalTo(before.withAdded(
            group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

}
