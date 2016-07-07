package com.isaac.javaweb.spring.finalexam.web.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
	
	private Logger log = Logger.getLogger(getClass());
	
	@RequestMapping(value="/buy")
	public void buyProducts(@ModelAttribute("loginuser") User user,Model model, @RequestBody List<ProductByBuyer> productinsettlelist){
		model.addAttribute("user", user); 
		
		Trx buyertrx=new Trx();
		Product product=new Product();
		
		for(ProductByBuyer productinsettle: productinsettlelist){
			buyertrx.setTrxcontentId(productinsettle.getId());
			buyertrx.setPersonId(user.getUserid());
		
			product.setContentid(productinsettle.getId());
			product=productservice.getContentInfo(product);
			buyertrx.setPrice(product.getPrice());
			Date date=new Date();
			buyertrx.setTime(date.getTime());	
			
			for(int i=0; i<productinsettle.getNumber();i++){
				trxservice.addTrx(buyertrx);
			}
		}
		
		model.addAttribute("code", 200);
	}
	
	@ExceptionHandler(Exception.class)    
    public void exceptionHandler(Exception ex,HttpServletResponse response,HttpServletRequest request) throws IOException{    
        log.error(ex.getMessage(), ex);  
        response.sendRedirect(request.getContextPath()+"/html/error.html");  

    }  

}
