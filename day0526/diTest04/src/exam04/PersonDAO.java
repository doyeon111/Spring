package exam04;

public class PersonDAO {
	//멤버로 person과 year를 가지고있다.
	private Person person;
	private int year;
	
	
	
	public void setPerson(Person person) {
		this.person = person;
	}



	public void setYear(int year) {
		this.year = year;
	}



	public void insert() {
		System.out.println("등록하였습니다.");
		person.info();
		System.out.println("year: " + year);
	}
	
}
