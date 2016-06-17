package com.isaac.javaweb.spring.finalexam.test;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.context.ApplicationContext;

import com.isaac.javaweb.spring.finalexam.dao.IContentDao;
import com.isaac.javaweb.spring.finalexam.dao.IPersonDao;
import com.isaac.javaweb.spring.finalexam.meta.Content;
import com.isaac.javaweb.spring.finalexam.meta.User;
import com.isaac.javaweb.spring.finalexam.service.IPerson;
import com.isaac.javaweb.spring.finalexam.service.impl.IPersonImpl;

@ContextConfiguration(locations = "classpath:application-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class DaoTest {

	@Autowired
    ApplicationContext ctx;
	
	@Resource
	IPersonDao perDao;
	
	@Resource 
	IContentDao contentDao;
	
	
	@Test
	public void ServiceTest1(){
		IPerson person=(IPerson)ctx.getBean("personInfo", IPersonImpl.class);
		User user=person.getUserInfo("buyer");
		System.out.println(user.getPassword());
		System.out.println(user.getUserType());
		System.out.println(user.getUserid());			
	}
	
	//if the user does not exist, check the result
	@Test
	public void DaoTest1(){
		User user=perDao.getUserInfoFromDao("buyer1");
		Assert.assertNull(user);		
	}
	
	//get buyer information
	@Test
	public void DaoTest2(){
		User user=perDao.getUserInfoFromDao("buyer");
		Assert.assertEquals("buyer's password", "37254660e226ea65ce6f1efd54233424", user.getPassword());
		Assert.assertEquals("buyer's type", 0, user.getUserType().intValue());
	}
	
	//get seller information
	@Test
	public void DaoTest3(){
		User user=perDao.getUserInfoFromDao("seller");
		Assert.assertEquals("buyer's password", "981c57a5cfb0f868e064904b8745766f", user.getPassword());
		Assert.assertEquals("buyer's type", 1, user.getUserType().intValue());
	}
	
	//get context information
	@Test
	public void ContentDaoTest1(){
		Content content=contentDao.getAllContentInfoFromDao();
		Assert.assertNull(content);
	}
}