package com.example.demo.vo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "orders")
public class Orders {
	@Id
	private int orderid;
	
	private int saleprice;
	private Date orderdate;
	
	@ManyToOne //orders 테이블은 자식이기 때문에 부모의 컬럼들을 작성해준다.
	@JoinColumn(name = "custid", insertable = true, updatable = true) //custid를 받아서 insert와 update를 할 수 있게 설정
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name = "bookid", insertable = true, updatable = true)
	private Book book;
}
