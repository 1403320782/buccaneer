package springtest;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import testbean.Person;
import testbean.PlaceHolder;
import buccaneer.system.bean.Role;
import buccaneer.system.bean.User;

public class SpringTest {
	@Test
	public void testBean() {
		ApplicationContext app = new ClassPathXmlApplicationContext(new String[]{"spring-hibernate.xml"});
		SessionFactory bean = (SessionFactory) app.getBean("sessionFactory");
		Session currentSession = bean.openSession();
		Transaction beginTransaction = currentSession.beginTransaction();
		User user = (User) currentSession.get(User.class, 1);
		Set<Role> list = new HashSet<Role>();
		Role role = new Role();
		role.setName("test");
		list.add(role);
		user.setList(list);
		currentSession.save(user);
		beginTransaction.commit();
		currentSession.flush();
		currentSession.close();
	}
	@Test
	public void testCommontScan() {
		ApplicationContext app = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
		Person bean = (Person) app.getBean("person");
		System.out.println(bean.getName());
	}
	@Test
	public void testPropertyPlaceholder(){
		ApplicationContext app = new ClassPathXmlApplicationContext(new String[]{"applicationContext.xml"});
		PlaceHolder bean = (PlaceHolder) app.getBean("placeHolder");
		System.out.println(bean.getName());
	}
}
