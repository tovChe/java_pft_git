package ru.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.pft.addressbook.model.GroupData;

public class CreateGroup extends TestBase {


  @Test
  public void testCreateGroup() {
    app.createGroup();
    app.fillGroupForm(new GroupData("Test1","Test2", "Test3"));
    app.submitForm();
    app.returnToGroupPage();
  }

}
