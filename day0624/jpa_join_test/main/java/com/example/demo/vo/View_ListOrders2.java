package com.example.demo.vo;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "view_listorders2")
public class View_ListOrders2 {
	//2개가 합쳐져서 복합키를 만든 클래스에서 불러옴
	@EmbeddedId //복합키로써 id라는 의미
	private OrdersViewId id; //안에 고객명과 도서명이 있음
	
	private String orderdate;
	private int saleprice;
	private int price;
}
