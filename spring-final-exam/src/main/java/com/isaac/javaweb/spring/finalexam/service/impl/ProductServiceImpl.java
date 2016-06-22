package com.isaac.javaweb.spring.finalexam.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.isaac.javaweb.spring.finalexam.dao.IContentDao;
import com.isaac.javaweb.spring.finalexam.meta.Product;
import com.isaac.javaweb.spring.finalexam.service.IProductService;

@Component("productInfo")
public class ProductServiceImpl implements IProductService {

	@Autowired
	private IContentDao contentDao;
	
	@Override
	public List<Product> getAllContentInfo() { 
		return contentDao.getAllContentInfoFromDao();
	}

	@Override
	public void addContent(Product product) {
		contentDao.addContent(product);
	}

}
