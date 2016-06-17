package com.isaac.javaweb.spring.finalexam.dao;

import org.apache.ibatis.annotations.Select;

import com.isaac.javaweb.spring.finalexam.meta.Content;


public interface IContentDao {

	@Select ("Select id as contentid, price, title, icon, abstract as brief, text from content")
	public Content getAllContentInfoFromDao();
}
