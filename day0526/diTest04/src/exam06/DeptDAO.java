package exam06;

public class DeptDAO {
	private Dept dept;
	private String title;
	private int cnt;
	public void setDept(Dept dept) {
		this.dept = dept;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	
	public void pro() {
		System.out.println("DeptDAO의 pro입니다.");
		dept.info();
		System.out.println("title: " + title);
		System.out.println("cnt: " + cnt);
	}
}
