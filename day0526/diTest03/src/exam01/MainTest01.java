package exam01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainTest01 {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("exam01/beans01.xml");
		MemberDAO dao = (MemberDAO) context.getBean("dao");
		dao.insert();

	}

}
