package exam03;

public class PersonDAO {
	//멤버로 person과 year를 가지고있다.
	private Person person;
	private int year;
	
	public PersonDAO(Person person, int year) {
		super();
		this.person = person;
		this.year = year;
	}
	
	public void insert() {
		System.out.println("등록하였습니다.");
		person.info();
		System.out.println("year: " + year);
	}
	
}
