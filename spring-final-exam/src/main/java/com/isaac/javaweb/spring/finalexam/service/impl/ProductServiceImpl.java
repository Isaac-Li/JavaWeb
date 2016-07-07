package com.isaac.javaweb.spring.finalexam.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isaac.javaweb.spring.finalexam.dao.IContentDao;
import com.isaac.javaweb.spring.finalexam.meta.Product;
import com.isaac.javaweb.spring.finalexam.service.IProductService;

@Service("ProductServiceImpl")
public class ProductServiceImpl implements IProductService {

	@Autowired
	private IContentDao contentDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Product> getAllContentInfo() { 
		return contentDao.getAllContentInfoFromDao();
	}

	@Override
	@Transactional
	public void addContent(Product product) {
		contentDao.addContentFromDao(product);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Product getContentInfo(Product product){		
		return contentDao.getContentInfoFromDao(product);
	}
	
	@Override
	@Transactional
	public int updateContent(Product product){	
		return contentDao.updateContentFromDao(product);
	}

	@Override
	@Transactional
	public int deleteContent(Product product){
		return contentDao.deleteContentFromDao(product);
	}
}
