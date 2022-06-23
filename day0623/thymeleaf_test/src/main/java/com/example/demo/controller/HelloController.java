package com.example.demo.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
	
	@GetMapping("/hello")
	public void hello(Model model) {
		model.addAttribute("name", "홍길동");
		model.addAttribute("age", 20);
		
		
		ArrayList<String> list = new ArrayList<String>();
		list.add("사과");
		list.add("포도");
		list.add("수박");
		model.addAttribute("list", list);
	}
	
}
