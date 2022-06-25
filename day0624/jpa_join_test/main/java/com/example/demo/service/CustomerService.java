package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CustomerDAO;
import com.example.demo.vo.Customer;

import lombok.Setter;

@Service
@Setter
public class CustomerService {
	@Autowired
	private CustomerDAO dao;
	
	public List<Customer> findAll() {
		return dao.findAll();
	}
	
	public void save(Customer c) {
		dao.save(c);
	}
	
	public Customer getCustomer(int custid) {
		return dao.getOne(custid);
	}
	
	public void delete(int custid) {
		dao.deleteById(custid);
	}
	
}
