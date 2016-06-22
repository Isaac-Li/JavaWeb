package com.isaac.javaweb.spring.finalexam.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.isaac.javaweb.spring.finalexam.meta.User;
import com.isaac.javaweb.spring.finalexam.service.IPersonService;
import com.isaac.javaweb.spring.finalexam.service.impl.PersonServiceImpl;



public class DaoTest2 {
	public static void main(String[] args) {
		ApplicationContext context= new ClassPathXmlApplicationContext("application-context.xml");
			
		IPersonService person=(IPersonService)context.getBean("personInfo", PersonServiceImpl.class);
		
		User user=new User();
		user.setUserName("buyer");
		user=person.getUserInfo(user);
		System.out.println(user.getPassword());
		System.out.println(user.getUserType());
			
		((ConfigurableApplicationContext) context).close();

		}
}
