package com.sist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sist.dao.BoardDAO;
import com.sist.vo.BoardVO;

@Controller
@RequestMapping("/insertBoard.do") //공통으로 사용될 경우 이 부분에 작성
public class InsertBoardController {
	
	private BoardDAO dao; //dao를 멤버변수로 선언
	
	
	
	public void setDao(BoardDAO dao) { //setter
		this.dao = dao;
	}

	//get방식으로 올 때 동작
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView form() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("insertBoard");
		
		//부모 글번호를 0이라고 상태유지
		int no = 0;
		mav.addObject("no", no);
		return mav;
	}
	
//	public void form(Model model) { //ModelAndView 가 아닌 void를 써줘도 insertBoard.jsp에 자동으로 가게됨.
//	
//	int no = 0;
//	model.addAttribute("no", no); //model을 매개변수로 만들어서 상태유지 가능
//	
//}
//-------------------------------------------------------------------------------------------------
	
	//post 방식으로 올때 동작
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView submit(BoardVO b) { //정보를 받아오기 위해서 Vo의 이름만 적어주면 사용자의 정보를 모두 담아온다.(vo 변수의 순서와 form에 적어준 순서와 일치해야 받아올 수 있음)
		ModelAndView mav = new ModelAndView();
		
		//답글
		int pno = b.getNo();
		int no = dao.getNextNo();
		
		//일단 부모글이라고 보고 처리한다.
		int b_ref = no;
		int b_step = 0;
		int b_level = 0;
		
		b.setNo(no);
		b.setB_ref(b_ref);
		b.setB_step(b_step);
		b.setB_level(b_level);
		
		int re = dao.insertBoard(b);
		
		if(re == 1) {
			mav.addObject("msg", "게시물 등록에 성공하였습니다.");
		} else {
			mav.addObject("msg", "게시물 등록에 실패하였습니다.");
		}
		
		mav.setViewName("insertBoardOK");
		
		return mav;
		
		
	}
}
