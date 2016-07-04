package com.isaac.javaweb.spring.finalexam.meta;

public class ProductForWeb {
	private Integer id;
	private Double price;
	private String title;
	private String image;
	private String summary;
	private String detail;
	private Boolean isBuy;
	private Boolean isSell;
	private Integer buyNum;
	private Double buyPrice;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public Boolean getIsBuy() {
		return isBuy;
	}
	public void setIsBuy(Boolean isBuy) {
		this.isBuy = isBuy;
	}
	public Boolean getIsSell() {
		return isSell;
	}
	public void setIsSell(Boolean isSell) {
		this.isSell = isSell;
	}
	public Integer getBuyNum() {
		return buyNum;
	}
	public void setBuyNum(Integer buyNum) {
		this.buyNum = buyNum;
	}
	public Double getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(Double buyPrice) {
		this.buyPrice = buyPrice;
	}
	
	
}
