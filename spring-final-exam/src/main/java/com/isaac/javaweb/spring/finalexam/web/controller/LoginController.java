package com.isaac.javaweb.spring.finalexam.web.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.isaac.javaweb.spring.finalexam.meta.User;
import com.isaac.javaweb.spring.finalexam.service.IPerson;



@Controller
public class LoginController {

	@Autowired
	IPerson person;
	
	@RequestMapping(value="/login")
	public String FirstLogin(){
		return "login";
	}
	
	@RequestMapping(value="/postlogin")
	public @ResponseBody Object PostLogin(HttpServletRequest request,HttpServletResponse response, Model model){
		
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
		
		
		setCookieAndSession(request, response);
		model.addAttribute("items", person.getUserInfo(user));  
		model.addAttribute("code", 200);
		return model;

	}
	
	//if success login, set useName cookie, and password session.
	public void setCookieAndSession(HttpServletRequest request, HttpServletResponse response){
		//create user name cookie
		Cookie usernameCookie=new Cookie("userName",request.getParameter("userName"));	
		usernameCookie.setMaxAge(60*30);
		response.addCookie(usernameCookie);
				
		//create password session
		HttpSession session =request.getSession();		
		session.setAttribute("password", request.getParameter("password"));

	}
	
	@RequestMapping(value="/logout")
	public String LogoutAndshowHomePage(HttpServletRequest request, HttpServletResponse response){

		//create user name cookie
		Cookie usernameCookie=new Cookie("userName",request.getParameter("userName"));	
		usernameCookie.setMaxAge(0);
		response.addCookie(usernameCookie);
		
		//get session
		HttpSession session =request.getSession(false);		
						
		if(session!=null){
			session.invalidate();
		}
		
		request.setAttribute("type", 0);
		
		return "forward:/";
	}
	
	

}
