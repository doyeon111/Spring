package exam01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainTest {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("exam01/beans01.xml");
		BoardDAO dao = (BoardDAO) context.getBean("dao");
		dao.insert();

	}

}
