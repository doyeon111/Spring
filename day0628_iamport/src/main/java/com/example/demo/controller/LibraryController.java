package com.example.demo.controller;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LibraryController {

	@GetMapping("/seat")
	public String seat() {
		String r = "OK";

		try {

			Document doc = Jsoup.connect("http://mpllc-seat.sen.go.kr/seatinfo/Seat_Info/1_count.asp").get();

			//#Layer110 > table > tbody > tr > td
			//select 는 찾아달라는 의미
			//System.out.println(doc);
			Elements e = doc.select("#Layer110 > table > tbody > tr > td.wating_f");
			System.out.println("선택된 요소: " + e);
			r = ("남아있는 자리: " + e.text());
			
		} catch (Exception ex) {
			System.out.println("예외발생: " + ex.getMessage());
		}

		return r;
	}
}
