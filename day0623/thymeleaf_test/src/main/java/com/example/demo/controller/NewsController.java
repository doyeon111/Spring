package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NewsController {
	
	@GetMapping("/news")
	public String news() {
		return "비가옵니다.";
	}
}
