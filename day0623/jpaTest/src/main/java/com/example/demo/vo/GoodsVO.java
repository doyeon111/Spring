package com.example.demo.vo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.Data;
import lombok.Setter;

@Entity //이 클래스와 매칭되는 테이블이 필요하다는 의미(테이블 자동으로 만들어줌)
@Data 
@Table(name="goods") //테이블 이름을 정해주기
public class GoodsVO {
	@Id //주식별자라는 의미
	private int no;
	
	private String name;
	private int price;
	private int qty;
	private String fname;
}
