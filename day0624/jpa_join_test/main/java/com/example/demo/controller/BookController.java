package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.service.BookService;
import com.example.demo.vo.Book;

import lombok.Setter;

@Controller
@Setter
public class BookController {
	@Autowired
	private BookService bs;
	
	@GetMapping("/listBook")
	public void list(Model model) {
		model.addAttribute("list", bs.findAll());
	}
	
	//등록과 수정에 대한 post 방식
	@PostMapping("/saveBook")
	public String save(Book b) {
		bs.save(b); 
		return "redirect:/listBook";
	}
	
	@GetMapping("/updateBook/{bookid}") //uri로 데이터를 전달하는 방식
	public String update(@PathVariable int bookid, Model model) { //상태유지 하고 수정
		model.addAttribute("b", bs.getBook(bookid));
		return "updateBook";
	}
	
	@GetMapping("/deleteBook/{bookid}")
	public String delete(@PathVariable int bookid) {
		bs.delete(bookid); //bs에 있는 delete를 불러옴
		return "redirect:/listBook";
	}
	
}
