package ru.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.pft.addressbook.model.GroupData;
import ru.pft.addressbook.model.PersonData;
import ru.pft.addressbook.model.Persons;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreatePerson extends TestBase {

  @DataProvider()
  public Iterator<Object[]> validPersonsFromXml() throws IOException {

    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/persons.xml")));
    String xml = "";
    String line = reader.readLine();
    while (line != null) {
      xml += (line);
      line = reader.readLine();
    }
    XStream xStream = new XStream();
    xStream.processAnnotations(PersonData.class);
    List<PersonData> persons = (List<PersonData>) xStream.fromXML(xml);
    return persons.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
  }

  @DataProvider()
  public Iterator<Object[]> validPersonsFromJson() throws IOException {

    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/persons.json")));
    String json = "";
    String line = reader.readLine();
    while (line != null) {
      json += (line);
      line = reader.readLine();
    }
    Gson gson = new Gson();
    List<PersonData> persons = gson.fromJson(json, new TypeToken<List<PersonData>>() {
    }.getType());
    return persons.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
  }

  //@Test
  @Test(dataProvider = "validPersonsFromJson")

  public void testCreatePerson(PersonData person) {

    app.goTo().homePage();
    Persons before = app.person().all();
    //PersonData addedPerson = before.iterator().next();
    File photo = new File("src/test/resources/tovChe.jpg");
    if (!photo.exists()) {
      System.out.println(photo.exists());
      return;
    }
    app.person().create(person, true);
    app.goTo().homePage();
    assertThat(app.person().count(), equalTo(before.size() + 1));
    Persons after = app.person().all();
    person.withId(after.stream().max(Comparator.comparingInt(o -> o.getId())).get().getId());
    before.add(person);
    assertThat(after, equalTo(before.withAdded(person)));
  }

  @Test (enabled = false)
  public void currentDir(){
    File file = new File("src/test/resources/tovChe.jpg");
    System.out.println(file);
    System.out.println(file.getAbsolutePath());
    System.out.println(file.exists());
  }
}
