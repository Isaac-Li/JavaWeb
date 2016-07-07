package com.isaac.javaweb.spring.finalexam.meta;

public class ProductByBuyer {
	private Integer id;
	private Integer number;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	@Override
	public String toString() {
		return "ProductByBuyer [id=" + id + ", number=" + number + "]";
	}
	
	
}
