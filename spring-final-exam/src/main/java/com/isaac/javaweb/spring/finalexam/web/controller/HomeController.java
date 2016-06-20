package com.isaac.javaweb.spring.finalexam.web.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.isaac.javaweb.spring.finalexam.meta.User;
import com.isaac.javaweb.spring.finalexam.service.IPerson;

@Controller
public class HomeController {
	@Autowired
	IPerson person;
	
	private Boolean isFirstLogin=true;

	@RequestMapping (value={"/","/home"})	
	public ModelAndView showHomePage(HttpServletRequest request){
		ModelAndView md=new ModelAndView();		
		md.setViewName("index");
		
		User userFromCookie=CheckFirstLogin(request);
		
		//get user information from database
		User user=person.getUserInfo(userFromCookie);

//		if(user!=null){
//			System.out.println(user.getUserName()+" "+user.getUserType()+ " "+user.getPassword());
//			System.out.println(userFromCookie.getPassword());
//			System.out.println(isFirstLogin);
//		}

		if(user==null || !user.getPassword().equals(userFromCookie.getPassword()))
		{
			isFirstLogin=true;
		}
		

		if(isFirstLogin){
			request.setAttribute("type", 0);			
			return md;
		}
		
		md.addObject(user);
		
		request.setAttribute("type", 0);
		
		return md;
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
	
	public User CheckFirstLogin(HttpServletRequest request){
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
		if(usernameFromCookie=="" || userpasswordFromsession==""){			
			isFirstLogin=true;
			user.setUserName("");
			return user;
		}		
		
		user.setUserName(usernameFromCookie);
		user.setPassword(userpasswordFromsession);
		isFirstLogin=false;
		System.out.println(user.getUserName()+" "+user.getPassword());
		
		return user;
		
	}
}
