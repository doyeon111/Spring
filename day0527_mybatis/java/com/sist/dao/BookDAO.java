package com.sist.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sist.db.DBManager;
import com.sist.vo.BookVO;

@Repository //ÀÚµ¿½ºÄµ
public class BookDAO {
	public List<BookVO> listBook() {
		return DBManager.listBook();
	}
	
	public int insertBook(BookVO b) {
		return DBManager.insertBook(b);
	}
}
