package com.sist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sist.dao.BoardDAO;

@Controller
public class DetailBoardController {
	
	private BoardDAO dao;
	
	

	public void setDao(BoardDAO dao) {
		this.dao = dao;
	}


	@RequestMapping("/detailBoard.do")
	public void detail(Model model, int no) { // 메소드에다가 상세보기 할 글번호를 받아오기 , 상태유지를 하기 위해서 model이라는 것을 사용할 수 있다.
		model.addAttribute("b", dao.getBoard(no));
	}
}
