package exam07;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainTest {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("exam07/beans07.xml");
		DeptDAO dao = (DeptDAO) context.getBean("dao");
		dao.pro();

	}

}
