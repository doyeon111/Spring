package exam01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainTest {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("exam01/beans.xml");
		Person kim1 =(Person) context.getBean("kim");
		Person kim2 =(Person) context.getBean("kim");
		
		//둘은 서로 같은 객체!
		kim1.info();
		kim2.info();
		
		if(kim1 == kim2) {
			System.out.println("서로 같은 객체를 참조합니다.");
		} else {
			System.out.println("서로 다른 객체를 참조합니다.");
		}

		//kim1의 나이가 바뀌면 kim2의 나이도 같이 바뀜
		kim1.setAge(22);
		
		kim1.info();
		kim2.info();
		
	}

}
