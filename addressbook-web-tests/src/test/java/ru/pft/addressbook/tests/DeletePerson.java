package ru.pft.addressbook.tests;

import org.testng.annotations.Test;

public class DeletePerson extends TestBase {

  @Test
  public void testDelPersonRecorder() throws Exception {

    app.getPersonHelper().selectPerson();
    app.getPersonHelper().deletePerson();
    app.getPersonHelper().acceptConfirmation("^Delete 1 addresses[\\s\\S]$");
  }
}
