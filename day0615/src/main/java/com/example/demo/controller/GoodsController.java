package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dao.GoodsDAO;
import com.example.demo.vo.GoodsVO;

import lombok.Setter;

@Controller
@Setter
public class GoodsController {
	
	@Autowired
	private GoodsDAO dao;
	
	//json 스트링으로 응답
	@RequestMapping("/listGoods") //요청하면 dao에 있는 findAll을 반환하여 json으로 응답하여 출력
	@ResponseBody //나타날 바디를 응답하겠다는 의미
	public List<GoodsVO> listGoods() {
		return dao.findAll();
	}
}
