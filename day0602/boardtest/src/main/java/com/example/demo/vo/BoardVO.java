package com.example.demo.vo;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class BoardVO {
	private int no;
	private String title;
	private String writer;
	private String pwd;
	private String content;
	private Date regdate;
	private int hit;
	private String fname;
	private int b_ref;
	private int b_step;
	private int b_level;
	private MultipartFile uploadFile; //이름은 insertBoard에 input type="file"에 정해준 이름과 똑같아야함
}
