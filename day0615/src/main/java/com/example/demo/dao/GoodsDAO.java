package com.example.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.GoodsManager;
import com.example.demo.vo.GoodsVO;

@Repository
public class GoodsDAO {
	
	public List<GoodsVO> findAll() {
		return GoodsManager.findAll();
	}
}
