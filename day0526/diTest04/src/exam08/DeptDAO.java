package exam08;

public class DeptDAO {
	private Dept dept;

	public DeptDAO(Dept dept) {
		super();
		this.dept = dept;
	}
	
	public void pro() {
		System.out.println("DeptDAO의 pro입니다.");
		dept.info();
	}
}
