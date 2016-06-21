package com.isaac.javaweb.spring.finalexam.web.controller;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.isaac.javaweb.spring.finalexam.meta.User;

@Controller
@SessionAttributes("loginuser")
public class SellerController {

	@RequestMapping(value="/public")
	public String SellerPublic(@ModelAttribute("loginuser") User user, Model model){		
		model.addAttribute("user", user);
		return "public";
	}
	
	@RequestMapping(value="/publicSubmit")
	public String PublicSubmit(){

		return null;
	}
	
}
