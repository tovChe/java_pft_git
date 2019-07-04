package ru.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.pft.addressbook.model.Groups;
import ru.pft.addressbook.model.PersonData;
import ru.pft.addressbook.model.Persons;

import java.sql.*;

public class TestDBConnect {

  @Test
  public void testDBConnect() {
    Connection conn = null;

    try {
      conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook?user=root&password=&serverTimezone=UTC");

      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT firstname FROM addressbook");
      Persons persons = new Persons();
      while (rs.next()) {
        persons.add(new PersonData().withName(rs.getString("firstname")));
      }
      System.out.println(persons);

    } catch (SQLException ex) {

      System.out.println("SQLException: " + ex.getMessage());
      System.out.println("SQLState: " + ex.getSQLState());
      System.out.println("VendorError: " + ex.getErrorCode());
    }
  }
}
