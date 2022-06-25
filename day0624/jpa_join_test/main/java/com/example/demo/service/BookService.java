package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.BookDAO;
import com.example.demo.vo.Book;

import lombok.Setter;

@Service
@Setter
public class BookService {
	@Autowired
	private BookDAO dao;
	
	public List<Book> findAll() {
		return dao.findAll();
	}
	
	//pk가 없으면 insert 있으면 update 실행
	public void save(Book b) {
		dao.save(b);
	}
	
	public Book getBook(int bookid) {
		return dao.getOne(bookid);
	}
	
	public void delete(int bookid) {
		dao.deleteById(bookid);
	}
	
}
