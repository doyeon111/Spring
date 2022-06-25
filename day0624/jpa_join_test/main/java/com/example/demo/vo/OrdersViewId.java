package com.example.demo.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable //name과 bookname이 합쳐져서 주식별자로 사용(복합키), 복합id 설정을 위하여 @Embeddable 클래스 생성
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdersViewId implements Serializable {
	
	@Column(name = "name") //컬럼이름 설정
	private String name;
	
	@Column(name = "bookname")
	private String bookname;
	
}
