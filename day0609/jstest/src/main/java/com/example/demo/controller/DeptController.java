package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dao.DeptDAO;
import com.example.demo.vo.DeptVO;

import lombok.Setter;

@Controller
@Setter
public class DeptController {
	@Autowired
	private DeptDAO dao;
	
	@RequestMapping("/listDept")
	@ResponseBody
	public List<DeptVO> list() {
		System.out.println("컨트롤러 동작함!!");
		return dao.findAll();
	}
}
