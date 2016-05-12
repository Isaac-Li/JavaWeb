package com.isaac.javaweb;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
 * This class is used to test Container
 */
public class Application {

	public static void main(String[] args) {
		ApplicationContext context= new ClassPathXmlApplicationContext("application-context.xml");
		
		SpringWriter springwriter=context.getBean("SpringWriterService",SpringFileWriterService.class);
		springwriter.write("the text was written by using spring framwork.");
		
		((ConfigurableApplicationContext) context).close();		  
	}

}
