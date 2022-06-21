package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor //매개변수를 갖는 생성자
@NoArgsConstructor // 매개변수가 없는 기본생성자
public class MemberVO {
	private String id;
	private String pwd;
	private String name;
	private String role;
}
