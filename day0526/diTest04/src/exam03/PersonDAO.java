package exam03;

public class PersonDAO {
	//����� person�� year�� �������ִ�.
	private Person person;
	private int year;
	
	public PersonDAO(Person person, int year) {
		super();
		this.person = person;
		this.year = year;
	}
	
	public void insert() {
		System.out.println("����Ͽ����ϴ�.");
		person.info();
		System.out.println("year: " + year);
	}
	
}
