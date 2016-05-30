package com.isaac.javaweb.springweb.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SpringWebController {
	
	@RequestMapping(value="/mvc")
	public void HelloMVC(HttpServletResponse rep) throws IOException{
		rep.getWriter().write("hello mvc");
	}
	
	@RequestMapping(value="/query")
	public void QueryAccountByUserID(HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.getWriter().write(request.getParameter("userID"));
	}
	

}
