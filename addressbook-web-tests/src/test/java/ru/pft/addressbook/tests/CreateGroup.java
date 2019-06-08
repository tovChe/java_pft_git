package ru.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.pft.addressbook.model.GroupData;

public class CreateGroup extends TestBase {


  @Test
  public void testCreateGroup() {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().createGroup(new GroupData("Test header", "Test footer", "Test group name"));
    app.getNavigationHelper().returnToGroupPage();
  }

}
