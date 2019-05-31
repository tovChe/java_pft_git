package ru.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.pft.addressbook.model.GroupData;

public class GroupModification extends TestBase {

  @Test
  public void groupModification() {

    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().editGroup();
    app.getGroupHelper().fillGroupForm(new GroupData("Test header", "Test footer", "Test group name"));
    app.getGroupHelper().updateForm();
  }

}
