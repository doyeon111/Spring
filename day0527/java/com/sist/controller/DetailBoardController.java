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
	public void detail(Model model, int no) { // �޼ҵ忡�ٰ� �󼼺��� �� �۹�ȣ�� �޾ƿ��� , ���������� �ϱ� ���ؼ� model�̶�� ���� ����� �� �ִ�.
		model.addAttribute("b", dao.getBoard(no));
	}
}
