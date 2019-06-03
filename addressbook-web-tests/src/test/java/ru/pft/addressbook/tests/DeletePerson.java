package ru.pft.addressbook.tests;

import org.testng.annotations.Test;

public class DeletePerson extends TestBase {

  @Test
  public void testDelPersonRecorder() throws Exception {

    app.getPersonHelper().selectPerson();
    app.getPersonHelper().deletePerson();

  }
}
