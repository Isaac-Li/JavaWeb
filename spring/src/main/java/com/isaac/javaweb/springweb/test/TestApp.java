package com.isaac.javaweb.springweb.test;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import com.isaac.javaweb.springweb.meta.User;
import com.isaac.javaweb.springweb.service.IUserInfor;
import com.isaac.javaweb.springweb.service.impl.UserInfoImpl;

public class TestApp {
	public static void main(String[] args){
		ApplicationContext context= new ClassPathXmlApplicationContext("application-context.xml");
		
		IUserInfor userService=(IUserInfor)context.getBean("UserInfoImpl", UserInfoImpl.class);
		List<User> userList=userService.getAllUserInfo();
		for(User user:userList){
			System.out.println(user.getUserId()+" "+user.getUserName()+" "+user.getBalance());
		}					
		
		((ConfigurableApplicationContext) context).close();
	}
}
