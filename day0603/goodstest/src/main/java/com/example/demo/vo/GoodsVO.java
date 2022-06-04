package com.example.demo.vo;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class GoodsVO {
	private int no;
	private String item;
	private int qty;
	private int price;
	private String fname;
	MultipartFile uploadFile;
}
