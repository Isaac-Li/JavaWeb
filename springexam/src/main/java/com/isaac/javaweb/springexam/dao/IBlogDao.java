package com.isaac.javaweb.springexam.dao;

import org.apache.ibatis.annotations.Insert;

import com.isaac.javaweb.springexam.meta.Blog;

public interface IBlogDao {

	@Insert("insert into Blog (blogTitle, blogContent) values(#{blogTitle}, #{blogContent})")
	public void InsertBlogData(Blog blog);
}
