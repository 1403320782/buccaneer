package hibernatetest;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.Test;

import testbean.User;

@SuppressWarnings("deprecation")
public class HibernateTest {
	@Test
	public void test01() {
		Configuration config = new Configuration();
		Properties properties = new Properties();
		try {
			properties.load(this.getClass().getClassLoader().getResourceAsStream("application.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		config.setProperties(properties);
		ServiceRegistry serviceRegistry = new  ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		System.out.println("serviceRegistry"+serviceRegistry);
		SessionFactory sessionFactory = config.buildSessionFactory(serviceRegistry);
		System.out.println("sessionFactory"+sessionFactory);
		Session session = sessionFactory.openSession();
		System.out.println("session"+session);
		Transaction transaction = session.beginTransaction();
		System.out.println("session"+session);
		List list = session.createSQLQuery("select * from user").list();
		
		session.flush();
		transaction.commit();
		System.out.println(list.size()+"size");
		session.close();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Object object = (Object) iterator.next();
			System.out.println(((User)object).getName());
		}
	}
	
	@Test
	public void test02() {
		Configuration config = new Configuration();
		Properties properties = new Properties();
		try {
			properties.load(this.getClass().getClassLoader().getResourceAsStream("application.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		config.setProperties(properties);
		ServiceRegistry serviceRegistry = new  ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		System.out.println("serviceRegistry"+serviceRegistry);
		SessionFactory sessionFactory = config.buildSessionFactory(serviceRegistry);
		System.out.println("sessionFactory"+sessionFactory);
		Session session = sessionFactory.openSession();
		System.out.println("session"+session);
		Transaction transaction = session.beginTransaction();
		System.out.println("session"+session);
		List list = session.createSQLQuery("select * from user").list();
		
		session.flush();
		transaction.commit();
		System.out.println(list.size()+"size");
		session.close();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Object object = (Object) iterator.next();
			System.out.println(((User)object).getName());
		}
	}
}
