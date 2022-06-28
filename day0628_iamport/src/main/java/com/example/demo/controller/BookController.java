package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.vo.BookVO;
import com.example.demo.vo.NewsVO;

@Controller
public class BookController {
	// vo를 이용하는 방법(json만들기)
		@GetMapping("/bookList")
		@ResponseBody
		public List<BookVO> bookList() {
			List<BookVO> bookList = new ArrayList<BookVO>();
			try {

				Document doc = Jsoup.connect(
						"https://www.hanbit.co.kr/store/books/new_book_list.html")
						.get();

				Elements list = doc.getElementsByClass("book_tit");
				for (Element e : list) {
					Element a = e.getElementsByTag("a").get(0);
					String title = a.text();
					String link = a.attr("href");
					bookList.add(new BookVO(title, link));

				}

			} catch (Exception e) {
				System.out.println("예외발생: " + e.getMessage());
			}

			return bookList;
		}
}
