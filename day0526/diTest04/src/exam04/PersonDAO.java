package exam04;

public class PersonDAO {
	//����� person�� year�� �������ִ�.
	private Person person;
	private int year;
	
	
	
	public void setPerson(Person person) {
		this.person = person;
	}



	public void setYear(int year) {
		this.year = year;
	}



	public void insert() {
		System.out.println("����Ͽ����ϴ�.");
		person.info();
		System.out.println("year: " + year);
	}
	
}
