package com.isaac.javaweb.spring.finalexam.service;

import java.util.List;

import com.isaac.javaweb.spring.finalexam.meta.Product;

public interface IProductService {

	public List<Product> getAllContentInfo();
	public void addContent(Product product);
	public Product getContentInfo(Product product);
	public int updateContent(Product product);
	public int deleteContent(Product product);
}
