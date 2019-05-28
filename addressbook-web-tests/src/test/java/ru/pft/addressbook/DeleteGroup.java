package ru.pft.addressbook;

import org.testng.annotations.Test;

public class DeleteGroup extends TestBase {

  @Test
  public void testDeleteGroup() {

    app.gotoGroupPage();
    app.selectGroup();
    app.deleteGroup();
    app.returnToGroupPage();

  }


}
