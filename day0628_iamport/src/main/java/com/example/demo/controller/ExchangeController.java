package com.example.demo.controller;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExchangeController {

	@GetMapping("/money")
	public String money() {
		String r = "OK";

		try {

			Document doc = Jsoup.connect("https://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query=%ED%99%98%EC%9C%A8").get();

			//#Layer110 > table > tbody > tr > td
			//select 는 찾아달라는 의미
			//System.out.println(doc);
			Elements e = doc.select("#_cs_foreigninfo > div > div.api_cs_wrap > div > div.c_rate > div.rate_table_bx._table > table > tbody > tr:nth-child(6) > td:nth-child(2) > span");
			System.out.println("선택된 요소: " + e);
			r = ("환율: " + e.text());
			
		} catch (Exception ex) {
			System.out.println("예외발생: " + ex.getMessage());
		}

		return r;
	}
}
