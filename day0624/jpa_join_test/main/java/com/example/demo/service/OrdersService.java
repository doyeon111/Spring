package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.OrdersDAO;
import com.example.demo.vo.Orders;

import lombok.Setter;

@Service
@Setter
public class OrdersService {
	@Autowired
	private OrdersDAO dao;
	
	
	public int getNextNo() {
		return dao.getNextNo();
	}
	
	//우리가 만든 insert문을 사용
	public void insert(Orders o) {
		dao.insert(o);
	}
	
	//jpa가 제공하는 쿼리문 사용
	public List<Orders> findAll() {
		return dao.findAll();
	}
	
}
