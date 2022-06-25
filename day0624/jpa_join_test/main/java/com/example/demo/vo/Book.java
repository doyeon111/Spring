package com.example.demo.vo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "book")
public class Book {
	@Id
	private int bookid;
	
	private String bookname;
	private String publisher;
	private int price;
	
	
	//관계에 대한 설정 (자신의 입장을 먼저 이야기 OneToMany!! book은 one!
	//book과 orders 테이블의 관계는 1:n의 관계
	@OneToMany(mappedBy = "book", fetch = FetchType.EAGER) //즉시로딩
	private List<Orders> orders; 
}
