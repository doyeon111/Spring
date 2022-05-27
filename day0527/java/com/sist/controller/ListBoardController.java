package com.sist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sist.dao.BoardDAO;

//�������� ����! �Ϲ� �ڹ� Ŭ����ó�� ! ==> pojo
@Controller //����� ��û�� ���� ���Ǵ� ��Ʈ�ѷ�
public class ListBoardController {
	
	private BoardDAO dao; //��Ʈ�ѷ��� dao�� ����� ������ �Ѵ�. (list ����� �ҷ����� ���ؼ�)
	
	
	
	public void setDao(BoardDAO dao) { //setter�� ���ؼ� �ʱ�ȭ�ϵ��� �Ѵ�.
		this.dao = dao;
	}



	//modelAndView�� ��ȯ
	@RequestMapping("listBoard.do") //�ڵ鷯 ���� ����, listBoard�޼ҵ带 ���۽�Ű�ڴٴ� �ǹ�
	public ModelAndView list() {
		int pageNUM=1; //�ϴ� ������ ��ȣ�� 1
		ModelAndView mav = new ModelAndView();
		mav.addObject("title", "�Խù����"); //��������
		mav.addObject("list", dao.listBoard(pageNUM)); //�Խù� ����� ��������
		mav.setViewName("listBoard");
		return mav;
	}
}
