package com.sist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sist.dao.BoardDAO;

//의존하지 않음! 일반 자바 클래스처럼 ! ==> pojo
@Controller //사용자 요청에 의해 사용되는 컨트롤러
public class ListBoardController {
	
	private BoardDAO dao; //컨트롤러가 dao를 멤버로 가지게 한다. (list 목록을 불러오기 위해서)
	
	
	
	public void setDao(BoardDAO dao) { //setter에 의해서 초기화하도록 한다.
		this.dao = dao;
	}



	//modelAndView를 반환
	@RequestMapping("listBoard.do") //핸들러 매핑 설정, listBoard메소드를 동작시키겠다는 의미
	public ModelAndView list() {
		int pageNUM=1; //일단 페이지 번호는 1
		ModelAndView mav = new ModelAndView();
		mav.addObject("title", "게시물목록"); //상태유지
		mav.addObject("list", dao.listBoard(pageNUM)); //게시물 목록을 상태유지
		mav.setViewName("listBoard");
		return mav;
	}
}
