package ru.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.pft.addressbook.model.GroupData;

public class CreateGroup extends TestBase {


  @Test
  public void testCreateGroup() {
    app.getGroupHelper().createGroup();
    app.getGroupHelper().fillGroupForm(new GroupData("Test1", "Test2", "Test3"));
    app.getNavigationHelper().submitForm();
    app.getNavigationHelper().returnToGroupPage();
  }

}
