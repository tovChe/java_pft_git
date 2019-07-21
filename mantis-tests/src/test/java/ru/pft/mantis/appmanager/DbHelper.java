package ru.pft.mantis.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.pft.mantis.model.User;
import ru.pft.mantis.model.Users;

import java.util.List;

/**
 * Класс для работы с БД.
 *
 * @author tovChe
 * @version 1.5
 */
public class DbHelper {

  private final SessionFactory sessionFactory;

  public DbHelper(ApplicationManager app) {

    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
  }

   /**
   * <p>Получаем список контактов из БД.</p>
   *
   * @return result - список контактов с ограничением deprecated
   */
  public Users users() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<User> result = session.createQuery("from User where id != 1").list();
    session.getTransaction().commit();
    session.close();
    return new Users(result);
  }

   /**
   * <p>Обновляет список контактов.</p>
   *
   * @param users Принимает контакт добавленный в группу
   */
  public void userNextQuery(User users) {
    Session session = sessionFactory.openSession();
    session.refresh(users); // обновление ранее полученного списка контактов
    // Также возможно сделать  так же как public Persons persons()
    session.close();
  }
}

