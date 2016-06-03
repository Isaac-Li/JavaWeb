package com.isaac.javaweb.springexam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.isaac.javaweb.springexam.dao.IBlogDao;
import com.isaac.javaweb.springexam.meta.Blog;
import com.isaac.javaweb.springexam.service.IinsertBlogItem;

@Component("InsertBlogItem")
public class InsertBlogItemImpl implements IinsertBlogItem {

	@Autowired
	private IBlogDao iblogDao;
	
	
	public IBlogDao getIblogDao() {
		return iblogDao;
	}


	public void setIblogDao(IBlogDao iblogDao) {
		this.iblogDao = iblogDao;
	}


	public void InsertBlogItem(Blog blog) {	
		this.iblogDao.InsertBlogData(blog);
	}

}
