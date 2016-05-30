package com.isaac.javaweb.usercount;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestApplication {
	public static void main(String[] args){
		ApplicationContext context= new ClassPathXmlApplicationContext("application-Servelet.xml");
		
		IuserBalanceDao userDao=(IuserBalanceDao) context.getBean("userBalanceDao", IuserBalanceDao.class);
		List<User> userList=userDao.getUserList();
		for(User user:userList){
			System.out.println(user.getUserId()+" "+user.getUserName()+" "+user.getBalance());
		}
		
		try{
			System.out.println("*********Transfer Money Start*************************");
			userDao.transferMoney(Integer.toUnsignedLong(1001),Integer.toUnsignedLong(1002),(double)500);
			System.out.println("*********Transfer Money End  *************************");
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		userList=userDao.getUserList();
		for(User user:userList){
			System.out.println(user.getUserId()+" "+user.getUserName()+" "+user.getBalance());
		}
		
		((ConfigurableApplicationContext) context).close();
	}
}
