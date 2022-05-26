package exam04;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainTest {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("exam04/beans04.xml");
		PersonDAO dao = (PersonDAO) context.getBean("dao");
		dao.insert();

	}

}
