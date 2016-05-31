package com.isaac.javaweb.springweb.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.isaac.javaweb.springweb.meta.User;
import com.isaac.javaweb.springweb.service.impl.UserInfoImpl;

@Controller
public class SpringWebController {
	@Autowired
	private UserInfoImpl userinfo;

	@RequestMapping(value="/mvc")
	public void HelloMVC(HttpServletResponse rep) throws IOException{
		rep.getWriter().write("hello mvc");
	}
	
	@RequestMapping(value="/query")
	public String checkAccept(@RequestHeader("Accept") String reqHeader){
		System.out.println(reqHeader);
		if(reqHeader.contains("html")){			
			return "forward:/api/query/displaywithfreemarker";
		}			
		return "forward:/api/querybyuserid";
	}
	
	@RequestMapping(value="/queryalluser")	
	public  String checkAcceptForAllAccount(@RequestHeader("Accept") String reqHeader)  {
		if(reqHeader.contains("html")){			
			return "forward:/api/queryalluser/displaywithfreemarker";
		}
			
		return "forward:/api/queryalluserforjason";		
	}
	
	@RequestMapping(value="/querybyuserid")
	public @ResponseBody User QueryAccountByUserID( @RequestParam("userID") String userID) {
		User user=userinfo.getUserInfoById(Long.valueOf(userID));
		return user;
	}
	
	@RequestMapping(value="/queryalluserforjason")	
	public  @ResponseBody List<User> QueryAllAccountForJson()  {
		List<User> users=userinfo.getAllUserInfo();
		return  users;
	}
	
	@RequestMapping(value="/query/displaywithfreemarker")
	public ModelAndView QueryAccountByUserIDForFreeMarker(@RequestParam("userID") String userID) {
		User user=userinfo.getUserInfoById(Long.valueOf(userID));
		ModelAndView mav = new ModelAndView();
		mav.addObject(user);
		mav.setViewName("userinfo");
		return mav;
	}
	
	@RequestMapping(value="/queryalluser/displaywithfreemarker")	
	public  ModelAndView QueryAllAccountForFreeMarker()  {
		List<User> users=userinfo.getAllUserInfo();
		ModelAndView mav = new ModelAndView();
		mav.addObject("usersList", users);
		mav.setViewName("usersinfor");
		return  mav;
	}
}
