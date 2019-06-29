package ru.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
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
    save(groups, new File(file));
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

  private static void save(List<GroupData> groups, File file) throws IOException {
    Writer writer = new FileWriter(file);
    for (GroupData group : groups) {
      writer.write(String.format("%s;%s;%s\n", group.getGroupName(), group.getHeader(), group.getTextFooter()));
    }
    writer.close();
  }
}
