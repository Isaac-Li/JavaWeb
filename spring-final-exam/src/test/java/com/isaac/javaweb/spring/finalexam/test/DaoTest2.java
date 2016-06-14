package com.isaac.javaweb.spring.finalexam.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.isaac.javaweb.spring.finalexam.meta.User;
import com.isaac.javaweb.spring.finalexam.service.IPerson;
import com.isaac.javaweb.spring.finalexam.service.impl.IPersonImpl;


public class DaoTest2 {
	public static void main(String[] args) {
		ApplicationContext context= new ClassPathXmlApplicationContext("application-context.xml");
			
		IPerson person=(IPerson)context.getBean("personInfo", IPersonImpl.class);
		
		User user=person.getUserInfo("buyer");
		System.out.println(user.getPassword());
		System.out.println(user.getUserType());
			
		((ConfigurableApplicationContext) context).close();

		}
}
