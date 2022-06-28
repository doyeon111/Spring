package com.example.demo.vo;

public class BookVO {
	private String title;
	private String link;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public BookVO(String title, String link) {
		super();
		this.title = title;
		this.link = link;
	}
	public BookVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
}
