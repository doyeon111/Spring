package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.DBManager;
import com.example.demo.vo.GoodsVO;

@Repository
public class GoodsDAO {

	public List<GoodsVO> listGoods(HashMap map) {
		return DBManager.listGoods(map);
	}
	
	
	public int insertGoods(GoodsVO g) {
		return DBManager.insertGoods(g);
	}
	
	public int getNextNo() {
		return DBManager.getNextNo();
	}
	
	public GoodsVO findByNo(int no) {
		return DBManager.findByNo(no);
	}
	
	public int updateGoods(GoodsVO g) {
		return DBManager.updateGoods(g);
	}
	
	public int deleteGoods(int no) {
		return DBManager.deleteGoods(no);
	}
	
	public int getTotalRecord() {
		return DBManager.getTotalRecord();
	}
}
