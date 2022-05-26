package exam08;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import exam08.DeptDAO;

public class MainTest {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("exam08/beans08.xml");
		DeptDAO dao = (DeptDAO) context.getBean("dao");
		dao.pro();
		
	}

}
