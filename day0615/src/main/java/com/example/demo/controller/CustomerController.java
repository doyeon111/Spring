package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dao.CustomerDAO;
import com.example.demo.vo.CustomerVO;

import lombok.Setter;

@Controller
@Setter
public class CustomerController {
	
	@Autowired
	private CustomerDAO dao;
	
	@RequestMapping("/listCustomer")
	@ResponseBody
	public String listCustomer() { //xml의 String을 반환하기 위해 string으로 설정
		//xml로 응답하기위해 string 만들어줌.
		String r = "";
		List<CustomerVO> list = dao.findAll();
		
		//list만큼 반복하여 xml로 응답
		r += "<customer_list>";
		for(CustomerVO c : list) {
			r += "<customer>";
			r += "<custid>" + c.getCustid() + "</custid>";
			r += "<name>" + c.getName() + "</name>";
			r += "<address>" + c.getAddress() + "</address>";
			r += "<phone>" + c.getPhone() + "</phone>";
			r += "</customer>";
		}
		r += "</customer_list>";
		
		return r;
		
	}
}
