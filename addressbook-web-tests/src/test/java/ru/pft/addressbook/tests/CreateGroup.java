package ru.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.pft.addressbook.model.GroupData;
import ru.pft.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CreateGroup extends TestBase {

  @DataProvider()
  public Iterator<Object[]> validGroupsFromXml() throws IOException {
    //List<Object[]> list = new ArrayList<>();
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.xml")));
    String xml = "";
    String line = reader.readLine();
    while (line != null) {
      /*String[] split = line.split(";");
      list.add(new Object[]{new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2])});*/
      xml += (line);
      line = reader.readLine();
    }
    XStream xStream = new XStream();
    xStream.processAnnotations(GroupData.class);
    List<GroupData> groups = (List<GroupData>) xStream.fromXML(xml);
    return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    //return list.iterator();
  }

  @DataProvider()
  public Iterator<Object[]> validGroupsFromJson() throws IOException {

    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.json")));
    String json = "";
    String line = reader.readLine();
    while (line != null) {
      json += (line);
      line = reader.readLine();
    }
    Gson gson = new Gson();
    List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>(){}.getType());
    return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
  }


  @Test(dataProvider = "validGroupsFromJson")
  public void testCreateGroup(GroupData group) {
    app.goTo().groupPage();
    Groups before = app.group().all();
    app.group().create(group);
    assertThat(app.group().count(), equalTo(before.size() + 1));
    Groups after = app.group().all();

    assertThat(after, equalTo(before.withAdded(
            group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

}
