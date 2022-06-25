package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.service.CustomerService;
import com.example.demo.vo.Customer;

import lombok.Setter;

@Controller
@Setter
public class CustomerController {
	
	@Autowired
	private CustomerService cs;
	
	@GetMapping("/listCustomer")
	public void list(Model model) {
		model.addAttribute("list", cs.findAll());
	}
	
	@PostMapping("/saveCustomer")
	public String save(Customer c) {
		cs.save(c);
		return "redirect:/listCustomer";
	}
	
	@GetMapping("/updateCustomer/{custid}")
	public String update(@PathVariable int custid, Model model) {
		model.addAttribute("c", cs.getCustomer(custid));
		return "updateCustomer";
		
	}
	
	@GetMapping("/deleteCustomer/{custid}")
	public String delete(@PathVariable int custid) {
		cs.delete(custid);
		return "redirect:/listCustomer";
	}
}
