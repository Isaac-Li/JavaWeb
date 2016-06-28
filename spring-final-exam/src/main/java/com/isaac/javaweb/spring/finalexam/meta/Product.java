package com.isaac.javaweb.spring.finalexam.meta;

import java.util.List;

public class Product {
	private Integer contentid;
	private Integer price;
	private String title;
	private byte[] icon;
	private String brief;
	private byte[] text;
	private List<Trx> trxes;
	
	
	public List<Trx> getTrxes() {
		return trxes;
	}
	public void setTrxes(List<Trx> trxes) {
		this.trxes = trxes;
	}
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
	
	public String getBrief() {
		return brief;
	}
	public void setBrief(String brief) {
		this.brief = brief;
	}
	public byte[] getIcon() {
		return icon;
	}
	public void setIcon(byte[] icon) {
		this.icon = icon;
	}
	public byte[] getText() {
		return text;
	}
	public void setText(byte[] text) {
		this.text = text;
	}
	
	
	

}
