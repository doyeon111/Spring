package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.GoodsDAO;
import com.example.demo.vo.GoodsVO;

import lombok.Setter;

@Service
@Setter
public class GoodsService {
	
	@Autowired
	private GoodsDAO dao;
	
	
	public List<GoodsVO> findAll() { //jpa는 sql문을 굳이 만들지 않아도 알아서 만들어준다.
		return dao.findAll();
	}
	
	//새로운 레코드를 등록하거나 수정할 때에 사용한다.
	//pk가 이미 있으면 수정해주고, 없으면 추가해준다.
	public void save(GoodsVO g) { //insert와 update는 둘 다 매개변수가 같다. (동시처리)
		dao.save(g);
	}
	

}
