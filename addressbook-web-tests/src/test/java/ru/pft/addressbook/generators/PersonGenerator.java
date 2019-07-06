package ru.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.pft.addressbook.model.PersonData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class PersonGenerator {
  @Parameter(names = "-c", description = "Persons count")
  public int count;

  @Parameter(names = "-f", description = "Target file")
  public String file;

  @Parameter(names = "-d", description = "Data file format")
  public String format;

  public static void main(String[] args) throws IOException {
    PersonGenerator generator = new PersonGenerator();
    JCommander jcommander = new JCommander(generator);
    try {
      jcommander.parse(args);
    } catch (Exception ex) {
      jcommander.usage();
    }
    generator.run();
  }

  private void run() throws IOException {
    List<PersonData> persons = generatePersons(count);
    if (format.equals("xml")) {
      saveAsXml(persons, new File(file));
    } else if (format.equals("json")) {
      saveAsJson(persons, new File(file));
    }
  }

  private List<PersonData> generatePersons(int count) {
    List<PersonData> persons = new ArrayList<>(count);
    for (int i = 0; i < count; i++) {
      persons.add(new PersonData().withName(String.format("Tester %s", i))
              .withLastName(String.format("Testovoy %s", i))
              .withMobilePhone(String.format("+7(911)", i))
              .withHomePhone(String.format("222"))
              .withWorkPhone(String.format("333"))
              .withEmail(String.format("tester@tester.com"))
              .withEmail3(String.format("tester3@tester.com"))
              .withAddress(String.format("310000, Город федерального значения, ул. Ленина, д.1")));
    }
    return persons;
  }

  private void saveAsJson(List<PersonData> persons, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(persons);
    Writer writer = new FileWriter(file);
    writer.write(json);
    writer.close();
  }

  private static void saveAsXml(List<PersonData> persons, File file) throws IOException {
    XStream xStream = new XStream();
    xStream.processAnnotations(PersonData.class);
    String xml = xStream.toXML(persons);
    Writer writer = new FileWriter(file);
    writer.write(xml);
    writer.close();
  }
}
