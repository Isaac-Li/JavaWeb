package com.isaac.javaweb.spring.finalexam.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.context.ApplicationContext;

import com.isaac.javaweb.spring.finalexam.dao.IPersonDao;
import com.isaac.javaweb.spring.finalexam.meta.User;
import com.isaac.javaweb.spring.finalexam.service.IPerson;
import com.isaac.javaweb.spring.finalexam.service.impl.IPersonImpl;

@ContextConfiguration(locations = "classpath:application-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class ServiceLayerTest {

	@Autowired
    ApplicationContext ctx;
	
	@Resource
	IPersonDao perDao;
	
	@Test
	public void ServiceTest1(){
		IPerson person=(IPerson)ctx.getBean("personInfo", IPersonImpl.class);
		User user=person.getUserInfo("buyer");
		System.out.println(user.getPassword());
		System.out.println(user.getUserType());
			
	}
	
	@Test
	public void DaoTest1(){
		User user=perDao.getUserInfoFromDao("buyer");
		System.out.println(user.getPassword());
		System.out.println(user.getUserType());
	}
}
