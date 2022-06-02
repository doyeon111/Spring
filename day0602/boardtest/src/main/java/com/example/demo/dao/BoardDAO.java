package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.DBManager;
import com.example.demo.vo.BoardVO;

@Repository
public class BoardDAO {

	public List<BoardVO> listBoard(HashMap map) {
		return DBManager.listBoard(map);
	}
	
	public BoardVO findByNo(int no) {
		return DBManager.findByNo(no);
	}
	
	public int insertBoard(BoardVO b) {
		return DBManager.insertBoard(b);
	}
	
	public int getNextNo() {
		return DBManager.getNextNo();
	}
	
	public int updateBoard(BoardVO b) {
		return DBManager.updateBoard(b);
	}
	
	public int deleteBoard(int no, String pwd) {
		return DBManager.deleteBoard(no, pwd);
	}
	
	public void updateStep(int b_ref, int b_step) {
		DBManager.updateStep(b_ref, b_step);
	}
	
	public int getTotalRecord() {
		return DBManager.getTotalRecord();
	}
}
