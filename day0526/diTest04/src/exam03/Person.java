package exam03;

public class Person {
	private String name;
	private int age;
	
	
	
	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}



	public void info() {
		System.out.println("�̸�: " +name + "����: " + age );
	}
}
