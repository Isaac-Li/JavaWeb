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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.isaac.javaweb.spring.finalexam.meta.User;
import com.isaac.javaweb.spring.finalexam.service.IPersonService;

@Controller
@SessionAttributes("loginuser")
public class HomeController {
	@Autowired
	private IPersonService person;
	
	private Boolean isFirstLogin=true;

	@RequestMapping (value={"/","/home"})	
	public ModelAndView showHomePage(HttpServletRequest request, Model model){
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
		
		model.addAttribute("loginuser", user);
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
		
		return user;
		
	}
	
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
		
		return "login";
	}
	
}
