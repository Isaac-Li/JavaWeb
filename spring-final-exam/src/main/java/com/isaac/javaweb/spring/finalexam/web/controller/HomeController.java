package com.isaac.javaweb.spring.finalexam.web.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping (value={"/","/home"})	
	public String showHomePage(){
		System.out.println("aaaa");
		return "home";
	}
}
