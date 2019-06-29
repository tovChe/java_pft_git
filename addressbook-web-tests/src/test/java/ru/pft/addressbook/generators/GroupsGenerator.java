package ru.pft.addressbook.generators;

import ru.pft.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class GroupsGenerator {
  public static void main(String[] args) throws IOException {
    int count = Integer.parseInt(args[0]);
    File file = new File(args[1]);

    List<GroupData> groups = generateGroups(count);
    save(groups, file);

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
