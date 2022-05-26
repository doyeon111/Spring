package exam03;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainTest {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("exam03/beans03.xml");
		Person p = (Person) context.getBean("p");
		PersonDAO dao = (PersonDAO) context.getBean("dao");
		dao.insert();

	}

}
