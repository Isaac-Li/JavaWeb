package com.isaac.javaweb.spring.finalexam.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.isaac.javaweb.spring.finalexam.meta.User;

@Controller
public class HomeController {

	@RequestMapping (value={"/","/home"})	
	public String showHomePage(HttpServletRequest request){
		request.setAttribute("type", 0);
		return "index";
	}
	
	@RequestMapping (value={"/test"})	
	public ModelAndView showHomePage1(HttpServletRequest request){
		request.setAttribute("type", 0);
		ModelAndView md=new ModelAndView();
		md.setViewName("index");
		User user=new User();
		user.setUserName("test");
		user.setUserType(0);
		md.addObject(user);
		return md;
	}
}
