package com.isaac.javaweb.spring.finalexam.meta;

import java.sql.Blob;

public class Content {
	private Integer contentid;
	private Integer price;
	private String title;
	private Blob icon;
	private String brief;
	private Blob text;
	
	
	public Integer getContentid() {
		return contentid;
	}
	public void setContentid(Integer contentid) {
		this.contentid = contentid;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Blob getIcon() {
		return icon;
	}
	public void setIcon(Blob icon) {
		this.icon = icon;
	}
	public String getBrief() {
		return brief;
	}
	public void setBrief(String brief) {
		this.brief = brief;
	}
	public Blob getText() {
		return text;
	}
	public void setText(Blob text) {
		this.text = text;
	}
	
	

}
