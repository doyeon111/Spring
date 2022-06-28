package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class KakaoLoginController {
	@GetMapping("KakaoLoginOK/{name}/{email}")
	public String kakaoLoginOK(@PathVariable String name, @PathVariable String email, HttpSession session) {
		session.setAttribute("name", name);
		session.setAttribute("email", email);
		return "loginOK";
	}
}
