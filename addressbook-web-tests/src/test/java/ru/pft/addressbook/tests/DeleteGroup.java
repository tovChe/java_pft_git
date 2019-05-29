package ru.pft.addressbook.tests;

import org.testng.annotations.Test;

public class DeleteGroup extends TestBase {

  @Test
  public void testDeleteGroup() {

    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().deleteGroup();
    app.getNavigationHelper().returnToGroupPage();

  }


}
