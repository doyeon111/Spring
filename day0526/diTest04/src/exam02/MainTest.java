package exam02;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainTest {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("exam02/beans02.xml");
		Dept d = (Dept) context.getBean("d");
		d.info();

	}

}
