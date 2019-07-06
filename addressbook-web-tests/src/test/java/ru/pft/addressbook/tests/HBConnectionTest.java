package ru.pft.addressbook.tests;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.pft.addressbook.model.GroupData;

import java.util.List;

public class HBConnectionTest {

  private SessionFactory sessionFactory;

  @BeforeClass
  protected void setUp() throws Exception {
    // A SessionFactory is set up once for an application!
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();
    try {
      sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
    }
    catch (Exception e) {
      // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
      // so destroy it manually.
      e.printStackTrace();
      StandardServiceRegistryBuilder.destroy( registry );
    }
  }

  @Test
  public void hbConnectionTest() {
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List result = session.createQuery("from ru.pft.addressbook.model.GroupData").list();
    for ( GroupData group : (List<GroupData>)result ) {
      System.out.println(group);
    }
    session.getTransaction().commit();
    session.close();
  }
}
