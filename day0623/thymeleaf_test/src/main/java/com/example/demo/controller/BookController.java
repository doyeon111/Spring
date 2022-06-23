package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.BookDAO;
import com.example.demo.vo.BookVO;

import lombok.Setter;

@Controller
@Setter
public class BookController {
	
	@Autowired
	private BookDAO dao;
	
	@GetMapping("/listBook")
	public List<BookVO> listBook(Model model) {
		model.addAttribute("list", dao.listBook());
		return dao.listBook();
	}
	
	@GetMapping("/detailBook/{bookid}") //전달받을 변수명도 뒤에 작성해주면 됨
	public ModelAndView detailBook(@PathVariable int bookid, Model model) {
		ModelAndView mav = new ModelAndView("detailBook");
		model.addAttribute("book", dao.getBook(bookid));
		return mav;
	}
	
	@GetMapping("/updateBook/{bookid}")
	public ModelAndView updateBook(@PathVariable int bookid) {
		ModelAndView mav = new ModelAndView("updateBook");
		mav.addObject("book", dao.getBook(bookid)); //상태유지
		return mav;
	}
	
	@PostMapping("/updateBook")
	public ModelAndView updateBookOK(BookVO b) {
		ModelAndView mav = new ModelAndView("redirect:/listBook");
		dao.updateBook(b);
		return mav;
	}
	
	
	@GetMapping("/deleteBook/{bookid}")
	public ModelAndView deleteBookOK(@PathVariable int bookid) {
		ModelAndView mav = new ModelAndView("redirect:/listBook");
		dao.deleteBook(bookid);
		return mav;
	}
	
}
