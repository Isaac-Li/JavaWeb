package com.isaac.javaweb.spring.finalexam.service;

import java.util.List;

import com.isaac.javaweb.spring.finalexam.meta.Product;

public interface IProductService {

	public List<Product> getAllContentInfo();
	public void addContent(Product product);
}
