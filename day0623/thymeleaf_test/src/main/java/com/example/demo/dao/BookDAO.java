package com.example.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.BookManager;
import com.example.demo.vo.BookVO;

@Repository
public class BookDAO {
	
	public List<BookVO> listBook() {
		return BookManager.listBook();
	}
	
	public BookVO getBook(int bookid) {
		return BookManager.getBook(bookid);
	}
	
	public int updateBook(BookVO b) {
		return BookManager.updateBook(b);
	}
	
	public int deleteBook(int bookid) {
		return BookManager.deleteBook(bookid);
	}
}
