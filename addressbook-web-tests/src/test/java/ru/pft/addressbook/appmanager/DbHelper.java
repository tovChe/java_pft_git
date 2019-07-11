package ru.pft.addressbook.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import ru.pft.addressbook.model.GroupData;
import ru.pft.addressbook.model.Groups;
import ru.pft.addressbook.model.PersonData;
import ru.pft.addressbook.model.Persons;

import java.util.List;

/**
 * Класс для работы с БД.
 *
 * @author tovChe
 * @version 1.5
 */
public class DbHelper {

  private final SessionFactory sessionFactory;

  public DbHelper() {

    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
  }

  /**
   * <p>Получаем список групп из БД.</p>
   *
   * @return result - список групп
   */
  public Groups groups() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List result = session.createQuery("from ru.pft.addressbook.model.GroupData").list();
    for (GroupData group : (List<GroupData>) result) {
      System.out.println(group);
    }
    session.getTransaction().commit();
    session.close();
    return new Groups(result);
  }

  /**
   * <p>Получаем список контактов из БД.</p>
   *
   * @return result - список контактов с ограничением deprecated
   */
  public Persons persons() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<PersonData> result = session.createQuery("from PersonData where deprecated = '0000-00-00'").list();
    session.getTransaction().commit();
    session.close();
    return new Persons(result);
  }

  /**
   * <p>Обновляет список групп.</p>
   *
   * @param groups Принимает группу в которую был добавлен контакт
   */
  public void groupsNextQuery(GroupData groups) {
    Session session = sessionFactory.openSession();
    session.refresh(groups); // обновление ранее полученного списка групп
    // Также возможно сделать так же как public Groups groups()
    session.close();
  }

  /**
   * <p>Обновляет список контактов.</p>
   *
   * @param persons Принимает контакт добавленный в группу
   */
  public void personNextQuery(PersonData persons) {
    Session session = sessionFactory.openSession();
    session.refresh(persons); // обновление ранее полученного списка контактов
    // Также возможно сделать  так же как public Persons persons()
    session.close();
  }
}

