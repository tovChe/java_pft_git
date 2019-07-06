package ru.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.pft.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class GroupsGenerator {

  @Parameter(names = "-c", description = "Groups count")
  public int count;

  @Parameter(names = "-f", description = "Target file")
  public String file;

  @Parameter(names = "-d", description = "Data file format")
  public String format;

  public static void main(String[] args) throws IOException {
    GroupsGenerator generator = new GroupsGenerator();
    JCommander jcommander = new JCommander(generator);
    try {
      jcommander.parse(args);
    } catch (ParameterException ex) {
      jcommander.usage();
    }
    generator.run();
  }

  private void run() throws IOException {
    List<GroupData> groups = generateGroups(count);
    if (format.equals("csv")) {
      saveAsCsv(groups, new File(file));
    } else if (format.equals("xml")) {
      saveAsXml(groups, new File(file));
    } else if (format.equals("json")) {
      saveAsJson(groups, new File(file));
    }
  }

  private void saveAsJson(List<GroupData> groups, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(groups);
    Writer writer = new FileWriter(file);
    writer.write(json);
    writer.close();
  }

  private static void saveAsXml(List<GroupData> groups, File file) throws IOException {
    XStream xStream = new XStream();
    xStream.processAnnotations(GroupData.class);
    String xml = xStream.toXML(groups);
    Writer writer = new FileWriter(file);
    writer.write(xml);
    writer.close();
  }

  private static List<GroupData> generateGroups(int count) {
    List<GroupData> groups = new ArrayList<>(count);
    for (int i = 0; i < count; i++) {
      groups.add(new GroupData().withName(String.format("Test group name %s", i))
              .withHeader(String.format("test header %s", i))
              .withFooter(String.format("test footer %s", i)));
    }
    return groups;
  }

  private static void saveAsCsv(List<GroupData> groups, File file) throws IOException {
    Writer writer = new FileWriter(file);
    for (GroupData group : groups) {
      writer.write(String.format("%s;%s;%s\n", group.getGroupName(), group.getHeader(), group.getTextFooter()));
    }
    writer.close();
  }
}
