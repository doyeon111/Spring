package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AllController {
	
	@RequestMapping("/all/test1")
	public String test1() {
		return "test1";
	}
	
	@RequestMapping("/all/test2")
	public String test2() {
		return "test2";
	}
	
	//admin role이 있어야 서비스 가능
	@RequestMapping("/admin/menu1")
	public String menu1() {
		return "menu1";
	}
	
	@RequestMapping("/admin/menu2")
	public String menu2() {
		return "menu2";
	}
	
	//로그인만 해도 서비스 가능
	@RequestMapping("/service1")
	public String service1() {
		return "service1";
	}
		
	@RequestMapping("/service2")
	public String service2() {
		return "service2";
	}
}
