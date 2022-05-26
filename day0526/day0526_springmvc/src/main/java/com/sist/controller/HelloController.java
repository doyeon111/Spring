package com.sist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {
	
	@RequestMapping("/hello.do")
	public ModelAndView hello() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", "Hello SpringMVC!!");
		mav.setViewName("hello");
		return mav;
	}
}
