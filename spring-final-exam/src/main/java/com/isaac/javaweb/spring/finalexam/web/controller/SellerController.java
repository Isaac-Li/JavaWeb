package com.isaac.javaweb.spring.finalexam.web.controller;



import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.isaac.javaweb.spring.finalexam.meta.Product;
import com.isaac.javaweb.spring.finalexam.meta.User;
import com.isaac.javaweb.spring.finalexam.service.IProductService;

@Controller
@SessionAttributes("loginuser")
public class SellerController {
	
	@Autowired
	private IProductService productservice;

	@RequestMapping(value="/public")
	public String SellerPublic(@ModelAttribute("loginuser") User user, Model model){		
		model.addAttribute("user", user); 
		return "public";
	}
	
	@RequestMapping(value="/publicSubmit")
	public String PublicSubmit(@ModelAttribute("loginuser") User user,Model model, HttpServletRequest request){
		model.addAttribute("user", user); 		
		
		//create product instance, and set property.
		Product product=new Product();
		product.setTitle(request.getParameter("title"));
		Float floatPrice=Float.parseFloat(request.getParameter("price"))*100;
		product.setPrice(floatPrice.intValue());

		
		product.setBrief(request.getParameter("summary"));
		product.setText(request.getParameter("detail").getBytes());
		
		productservice.addContent(product);

		
		return null;
	}
	
}
