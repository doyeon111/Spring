package com.sist.controller;

import java.io.OutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sist.dao.BookDAO;
import com.sist.vo.BookVO;

@Controller
public class BookController {
	
	@Autowired //의존관계를 자동으로 설정
	private BookDAO dao;
	
	
	public void setDao(BookDAO dao) {
		this.dao = dao;
	}



	@RequestMapping("/listBook.do")
	public void list(Model model) {
		model.addAttribute("title", "도서목록");
		model.addAttribute("list", dao.listBook());
	}
	
	
	
	@RequestMapping(value = "/insertBook.do", method = RequestMethod.GET)
	public void insertForm() {
		
	}
	@RequestMapping(value = "/insertBook.do", method = RequestMethod.POST)
	public ModelAndView insertSubmit(BookVO b) { //커맨드 오브젝트
		int re = dao.insertBook(b);
		ModelAndView mav = new ModelAndView();
		
		if(re == 1) {
			mav.setViewName("redirect:/listBook.do");
		} else {
			mav.addObject("msg", "도서 등록에 실패하였습니다.");
			mav.setViewName("error");
		}
		
		return mav;
	}

}
