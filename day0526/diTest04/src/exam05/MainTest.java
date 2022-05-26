package exam05;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainTest {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("exam05/beans05.xml");
		DeptDAO dao = (DeptDAO) context.getBean("dao");
		dao.pro();

	}

}
