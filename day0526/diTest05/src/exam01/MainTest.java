package exam01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainTest {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("exam01/beans.xml");
		Person kim1 =(Person) context.getBean("kim");
		Person kim2 =(Person) context.getBean("kim");
		
		//���� ���� ���� ��ü!
		kim1.info();
		kim2.info();
		
		if(kim1 == kim2) {
			System.out.println("���� ���� ��ü�� �����մϴ�.");
		} else {
			System.out.println("���� �ٸ� ��ü�� �����մϴ�.");
		}

		//kim1�� ���̰� �ٲ�� kim2�� ���̵� ���� �ٲ�
		kim1.setAge(22);
		
		kim1.info();
		kim2.info();
		
	}

}
