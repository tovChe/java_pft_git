package ru.pft.addressbook;

import org.testng.annotations.Test;

public class CreateGroup extends TestBase{


  @Test
  public void testCreateGroup() {
    createGroup();
    fillGroupForm();
    submitForm();
    returnToGroupPage();
  }

}
