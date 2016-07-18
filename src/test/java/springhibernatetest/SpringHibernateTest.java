package springhibernatetest;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringHibernateTest {
	ApplicationContext app = null;

	@Before
	public void init() {
		app = new ClassPathXmlApplicationContext(
				new String[] { "spring-hibernate.xml" });
	}

	@Test
	public void test() {
		SessionFactory sessionFactory = (SessionFactory) app
				.getBean("sessionFactory");
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		List list = session.createSQLQuery("select * from user").list();
		transaction.commit();
		session.close();
		System.out.println(list.size());
		
	}
}
