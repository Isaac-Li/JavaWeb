package com.isaac.javaweb.spring.finalexam.web.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.isaac.javaweb.spring.finalexam.meta.Product;
import com.isaac.javaweb.spring.finalexam.meta.ProductByBuyer;
import com.isaac.javaweb.spring.finalexam.meta.Trx;
import com.isaac.javaweb.spring.finalexam.meta.User;
import com.isaac.javaweb.spring.finalexam.service.IProductService;
import com.isaac.javaweb.spring.finalexam.service.ITrxService;

@Controller
@SessionAttributes("loginuser")
public class BuyerController {
	
	@Autowired
	private IProductService productservice;
	
	@Autowired
	private ITrxService trxservice;
	
	@RequestMapping(value="/buy")
	public void buyProducts(@ModelAttribute("loginuser") User user,Model model, @RequestBody List<ProductByBuyer> productinsettlelist){
		model.addAttribute("user", user); 
		
		Trx buyertrx=new Trx();
		Product product=new Product();
		
		for(ProductByBuyer productinsettle: productinsettlelist){
			buyertrx.setContentId(productinsettle.getId());
			buyertrx.setPersonId(user.getUserid());
		
			product.setContentid(productinsettle.getId());
			product=productservice.getContentInfo(product);
			buyertrx.setPrice(product.getPrice());
			Date date=new Date();
			buyertrx.setTime(date.getTime());	
			
			trxservice.addTrx(buyertrx);
		}
	}
}
