package com.isaac.javaweb.springlogin.web.controller;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.isaac.javaweb.springlogin.meta.User;
import com.isaac.javaweb.springlogin.service.impl.GetUserInfo;

@Controller
public class LoginController {
	
	@Autowired
	private GetUserInfo getUserInfo;
	
	@RequestMapping(value="/query")
	public ModelAndView checkAccept(HttpServletRequest request,HttpServletResponse response){
	
		String usernameFromCookie="";
		String userName=request.getParameter("userName");
		String password=request.getParameter("userPassword");
		
		User user=new User();
		user.setUserName(userName);

		//create user name cookie
		Cookie usernameCookie=new Cookie("userName",userName);	
		usernameCookie.setMaxAge(60*30);
		response.addCookie(usernameCookie);
		
		//create session
		HttpSession session =request.getSession();		
		String userpasswordFromsession="";
		

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
			
			userpasswordFromsession=(String)session.getAttribute("userPassword");
		}
		
		//check userName is null or not
		if(userName=="" && usernameFromCookie=="")
		{

			return getFtlName(user, false);
		}
		
		//check userName and userPassword are valid or not
		if(userName.equals(usernameCookie) && password.equals(userpasswordFromsession))
		{
			return getFtlName(user, true);
		}
		
		//get user information from Dao

		user.setUserPassword(getUserInfo.getUserPassword(user));
		
		if(user.getUserPassword().equals(password))
		{
			session.setAttribute("userPassword", password);
			return getFtlName(user, true);
		}
		else
		{
			return getFtlName(user, false);
		}
		
	}
	
	public ModelAndView getFtlName(User user, Boolean bSuccess){
		ModelAndView mav = new ModelAndView();
		mav.addObject(user);
		
		if(bSuccess)
		{
			mav.setViewName("success");
		}else
		{
			mav.setViewName("fail");
		}
		
		return mav;
	}

}
