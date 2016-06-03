package com.isaac.javaweb.springexam.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.isaac.javaweb.springexam.meta.Blog;
import com.isaac.javaweb.springexam.service.IinsertBlogItem;
import com.isaac.javaweb.springexam.service.impl.InsertBlogItemImpl;



public class DaoTest {

	public static void main(String[] args) {
	ApplicationContext context= new ClassPathXmlApplicationContext("application-context.xml");
		
	IinsertBlogItem insertBlog=(IinsertBlogItem)context.getBean("InsertBlogItem", InsertBlogItemImpl.class);

	Blog blog=new Blog();
	blog.setBlogTitle("title1");
	blog.setBlogContext("context ttt");
	insertBlog.InsertBlogItem(blog);
	
		((ConfigurableApplicationContext) context).close();

	}

}
