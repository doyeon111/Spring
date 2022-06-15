package com.example.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.CustomerManager;
import com.example.demo.vo.CustomerVO;

@Repository
public class CustomerDAO {

	public List<CustomerVO> findAll() {
		return CustomerManager.findAll();
	}
}
