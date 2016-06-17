package com.isaac.javaweb.spring.finalexam.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@RequestMapping(value="/login")
	public String FirstLogin(){
		return "login";
	}
}
