package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@GetMapping("/menu1")
	public String menu1() {
		return "menu1";
	}
	
	@GetMapping("/menu2")
	public String menu2() {
		return "menu2";
	}
	
	@GetMapping("/member/a")
	public String a() {
		System.out.println("a 입니다.");
		return "member a";
	}
	
	@GetMapping("/member/b")
	public String b() {
		System.out.println("b 입니다.");
		return "member b";
	}
	
}
