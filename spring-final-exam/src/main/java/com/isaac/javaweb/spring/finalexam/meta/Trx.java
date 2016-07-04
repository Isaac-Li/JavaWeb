package com.isaac.javaweb.spring.finalexam.meta;

public class Trx {
	private Integer trxid;
	private Integer trxcontentId;
	private Integer personId;
	private Integer price;
	private Long time;
	
	
	public Integer getTrxid() {
		return trxid;
	}
	public void setTrxid(Integer trxid) {
		this.trxid = trxid;
	}
	
	public Integer getTrxcontentId() {
		return trxcontentId;
	}
	public void setTrxcontentId(Integer trxcontentId) {
		this.trxcontentId = trxcontentId;
	}
	public Integer getPersonId() {
		return personId;
	}
	public void setPersonId(Integer personId) {
		this.personId = personId;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Long getTime() {
		return time;
	}
	public void setTime(Long time) {
		this.time = time;
	}
	
	
}
