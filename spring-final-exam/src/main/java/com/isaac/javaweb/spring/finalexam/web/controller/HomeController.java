package com.isaac.javaweb.spring.finalexam.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.isaac.javaweb.spring.finalexam.meta.User;
import com.isaac.javaweb.spring.finalexam.meta.Product;
import com.isaac.javaweb.spring.finalexam.meta.ProductByBuyer;
import com.isaac.javaweb.spring.finalexam.meta.ProductForWeb;
import com.isaac.javaweb.spring.finalexam.meta.Trx;
import com.isaac.javaweb.spring.finalexam.service.IPersonService;
import com.isaac.javaweb.spring.finalexam.service.IProductService;

@Controller
@SessionAttributes("loginuser")
public class HomeController {
	@Autowired
	private IPersonService person;
	
	@Autowired
	private IProductService productservice;
		
	@Autowired
	private HttpServletRequest request;
	
	private Boolean isFirstLogin=true;
	
    //home page
	@RequestMapping (value={"/","/home"})	
	public ModelAndView showHomePage(Model model, @RequestParam(value="type",required=false) String type){
		ModelAndView md=new ModelAndView();		
		md.setViewName("index");
		

		User userFromCookie=CheckFirstLogin();
		
		//get user information from database
		User user=person.getUserInfo(userFromCookie);


		if(user==null || !user.getPassword().equals(userFromCookie.getPassword()))
		{
			isFirstLogin=true;
		}
		

		if(!isFirstLogin){
			model.addAttribute("loginuser", user);
			md.addObject(user);
		}
		
		//parameter type is used for display products, if type==1, while only display the products which are not sold
		//must return number, because index.ftl compare number 
		
		//get all product
		List<ProductForWeb> productList=getAllProductListForWeb();
		
		
		if(type!=null){
			request.setAttribute("type", 1);
			if(productList!=null){
				List<ProductForWeb> nosaleproductList=new ArrayList<ProductForWeb>();
				
				for(ProductForWeb product1:productList){
					if(!product1.getIsBuy()){
						nosaleproductList.add(product1);
						System.out.println(product1.getId());
					}
				}
				
				md.addObject("productList", nosaleproductList);
			}
		}else
		{
			if(productList!=null){
				md.addObject("productList", productList);
			}
			request.setAttribute("type", 0);
		}
		

		return md;
	}
	
	
	public User CheckFirstLogin(){
		User user=new User();
		String usernameFromCookie="";		
		
		//get session
		HttpSession session =request.getSession(false);		
		String userpasswordFromsession="";
		
		if(session==null){
			isFirstLogin=true;
			user.setUserName("");
			return user;
		}
		
		//get cookies, if cookie is not null, get userPassword from session.
		Cookie[] cookies= request.getCookies();
			
		if(cookies !=null)
		{
			for(Cookie cookie: cookies)
			{
				if(cookie.getName().equals("userName"))
				{
					usernameFromCookie=cookie.getValue();
				}
			}
					
			userpasswordFromsession=(String)session.getAttribute("password");
		}
		
		//check usernameFromCookie or userpasswordFromsession which is null or not, if it is empty, return true.
		if(usernameFromCookie.equals("") || userpasswordFromsession.equals("")){			
			isFirstLogin=true;
			user.setUserName("");
			return user;
		}		
		
		user.setUserName(usernameFromCookie);
		user.setPassword(userpasswordFromsession);
		isFirstLogin=false;
		
		return user;
		
	}
	
	@RequestMapping(value="/login")
	public String FirstLogin(){
		return "login";
	}
	
	@RequestMapping(value="/postlogin")
	public @ResponseBody Object PostLogin(HttpServletResponse response, Model model){
		
		User user=new User();
		user.setUserName(request.getParameter("userName"));
		
		//check if the user exists in database or not
		if(person.getUserInfo(user)==null){				
			return user;
		}
		
		//check password is valid or not
		if(!person.getUserInfo(user).getPassword().equals(request.getParameter("password"))){
			return user;
		}
		
		
		setCookieAndSession(response);
		model.addAttribute("loginuser", person.getUserInfo(user));
		
		//for pageLogin.js used
		model.addAttribute("items", person.getUserInfo(user));  
		model.addAttribute("code", 200);
		return model;

	}
	
	//if success login, set useName cookie, and password session.
	public void setCookieAndSession(HttpServletResponse response){
		//create user name cookie
		Cookie usernameCookie=new Cookie("userName",request.getParameter("userName"));	
		usernameCookie.setMaxAge(60*30);
		response.addCookie(usernameCookie);
				
		//create password session
		HttpSession session =request.getSession();		
		session.setAttribute("password", request.getParameter("password"));

	}
	
	@RequestMapping(value="/logout")
	public String LogoutAndshowHomePage(HttpServletResponse response, SessionStatus sessionstatus){

		//create user name cookie
		Cookie usernameCookie=new Cookie("userName",request.getParameter("userName"));	
		usernameCookie.setMaxAge(0);
		response.addCookie(usernameCookie);
		
		//get session
		HttpSession session =request.getSession(false);		
						
		if(session!=null){
			sessionstatus.setComplete();
			session.invalidate();			
		}
		
		request.setAttribute("type", 0);
		
		return "login";
	}
	
	@RequestMapping(value="/settleAccount")
	public String buyerSettleAccount(@ModelAttribute("loginuser") User user,Model model){
		model.addAttribute("user", user); 	
		return "settleAccount";
	}

	public List<ProductForWeb> getAllProductListForWeb(){
		List<Product> productlist=productservice.getAllContentInfo();
		List<ProductForWeb> productforweblist=new ArrayList<ProductForWeb>();
		
		for(Product product:productlist){
			ProductForWeb proforweb=new ProductForWeb();
			proforweb.setSummary(product.getBrief());
			proforweb.setId(product.getContentid());
			
			String tempstring=new String(product.getIcon());		
			proforweb.setImage(tempstring.substring(tempstring.lastIndexOf("/")).trim());			
			
			proforweb.setPrice(product.getPrice()/100.0);
			proforweb.setDetail( new String(product.getText()));
			proforweb.setTitle(product.getTitle());

			//check transaction, if no transaction, isBuy and isShell will be false.
			if(product.getTrxes()==null){
				proforweb.setIsBuy(false);
				proforweb.setIsSell(false);
			}else{						
				proforweb.setIsBuy(true);
				proforweb.setIsSell(true);
			}
			
			productforweblist.add(proforweb);
		
		}
		
		return productforweblist;
		
	}


}
