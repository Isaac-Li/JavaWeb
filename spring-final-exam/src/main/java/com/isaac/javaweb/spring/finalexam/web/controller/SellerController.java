package com.isaac.javaweb.spring.finalexam.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.isaac.javaweb.spring.finalexam.meta.User;

@Controller
public class SellerController {

	@RequestMapping(value="/login")
	public String EditContent(){
		
		return "/html/login.html";
	}
}
