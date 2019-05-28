package ru.pft.addressbook;

import org.testng.annotations.Test;

public class DeleteGroup extends TestBase {

  @Test
  public void testDeleteGroup() {

    gotoGroupPage();
    selectGroup();
    deleteGroup();
    returnToGroupPage();

  }


}
