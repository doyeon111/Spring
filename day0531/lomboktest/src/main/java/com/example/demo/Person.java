package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@Setter
//@Getter //setter와 getter를 자동으로 만들어줌
//@AllArgsConstructor //매개변수를 모두 갖는 생성자를 만들어줌
//@NoArgsConstructor //매개변수가 없는 생성자를 만들어준다. (VO에서 직접 작업해줬던 것을 간단하게 해줄 수 있음.)
//@ToString //toString을 만들어준다.

@Data //위의 5가지를 모두 한꺼번에 만들어준다.
public class Person {
	private String name;
	private int age;
	private String addr;
}
