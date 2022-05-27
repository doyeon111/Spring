package com.sist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.sist.dao.BoardDAO;
import com.sist.vo.BoardVO;

@Controller
@RequestMapping("/insertBoard.do") //�������� ���� ��� �� �κп� �ۼ�
public class InsertBoardController {
	
	private BoardDAO dao; //dao�� ��������� ����
	
	
	
	public void setDao(BoardDAO dao) { //setter
		this.dao = dao;
	}

	//get������� �� �� ����
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView form() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("insertBoard");
		
		//�θ� �۹�ȣ�� 0�̶�� ��������
		int no = 0;
		mav.addObject("no", no);
		return mav;
	}
	
//	public void form(Model model) { //ModelAndView �� �ƴ� void�� ���൵ insertBoard.jsp�� �ڵ����� ���Ե�.
//	
//	int no = 0;
//	model.addAttribute("no", no); //model�� �Ű������� ���� �������� ����
//	
//}
//-------------------------------------------------------------------------------------------------
	
	//post ������� �ö� ����
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView submit(BoardVO b) { //������ �޾ƿ��� ���ؼ� Vo�� �̸��� �����ָ� ������� ������ ��� ��ƿ´�.(vo ������ ������ form�� ������ ������ ��ġ�ؾ� �޾ƿ� �� ����)
		ModelAndView mav = new ModelAndView();
		
		//���
		int pno = b.getNo();
		int no = dao.getNextNo();
		
		//�ϴ� �θ���̶�� ���� ó���Ѵ�.
		int b_ref = no;
		int b_step = 0;
		int b_level = 0;
		
		b.setNo(no);
		b.setB_ref(b_ref);
		b.setB_step(b_step);
		b.setB_level(b_level);
		
		int re = dao.insertBoard(b);
		
		if(re == 1) {
			mav.addObject("msg", "�Խù� ��Ͽ� �����Ͽ����ϴ�.");
		} else {
			mav.addObject("msg", "�Խù� ��Ͽ� �����Ͽ����ϴ�.");
		}
		
		mav.setViewName("insertBoardOK");
		
		return mav;
		
		
	}
}
