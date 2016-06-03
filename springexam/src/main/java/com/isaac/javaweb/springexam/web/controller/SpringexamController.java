package com.isaac.javaweb.springexam.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.isaac.javaweb.springexam.meta.Blog;
import com.isaac.javaweb.springexam.service.IinsertBlogItem;

@Controller
public class SpringexamController {
	@Autowired
	private IinsertBlogItem insertBlogItem;
	
	@RequestMapping(value="/insertblog")
	public void InsetBlog(@RequestParam("blogTitle") String blogTitle,@RequestParam("blogContent") String blogContent, 
			HttpServletResponse resp) throws IOException{
				
		if(blogTitle.length()>20 || blogContent.length()>100){	
			resp.getWriter().write("HTTP/1.1 400 Bad Request");
			resp.setStatus(400);
			return;
		}
		
		Blog blog= new Blog();
		blog.setBlogTitle(blogTitle);
		blog.setBlogContext(blogContent);
		insertBlogItem.InsertBlogItem(blog);
		resp.getWriter().write("HTTP/1.1 200 OK");
		resp.setStatus(200);
		return;
	}
	
	

}
