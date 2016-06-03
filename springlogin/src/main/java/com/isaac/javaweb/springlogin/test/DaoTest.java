package com.isaac.javaweb.springlogin.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.isaac.javaweb.springlogin.meta.User;
import com.isaac.javaweb.springlogin.service.IgetUserInfo;
import com.isaac.javaweb.springlogin.service.impl.GetUserInfo;



public class DaoTest {
	public static void main(String[] args) {
		ApplicationContext context= new ClassPathXmlApplicationContext("application-context.xml");
			
		IgetUserInfo userInfo=(IgetUserInfo)context.getBean("GetUserInfo", GetUserInfo.class);

		User user=new User();
		user.setUserName("test3");
		System.out.println(userInfo.getUserPassword(user));
		
		((ConfigurableApplicationContext) context).close();

		}
}
