package com.example.demo.vo;

public class NewsVO {
	private String title;
	private String url;
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public NewsVO(String title, String url) {
		super();
		this.title = title;
		this.url = url;
	}
	public NewsVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
