package com.isaac.javaweb.spring.finalexam.test;


import java.util.List;

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
import com.isaac.javaweb.spring.finalexam.meta.Product;
import com.isaac.javaweb.spring.finalexam.meta.User;
import com.isaac.javaweb.spring.finalexam.service.IPersonService;
import com.isaac.javaweb.spring.finalexam.service.impl.PersonServiceImpl;


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
		IPersonService person=(IPersonService)ctx.getBean("PersonServiceImpl", IPersonService.class);
		User user=new User();
		user.setUserName("buyer");
		user=person.getUserInfo(user);
		System.out.println(user.getPassword());
		System.out.println(user.getUserType());
		System.out.println(user.getUserid());			
	}
	
	//if the user does not exist, check the result
	@Test
	public void DaoTest1(){
		User user=new User();
		user.setUserName("buyer1");
		user=perDao.getUserInfoFromDao(user);
		Assert.assertNull(user);		
	}
	
	//get buyer information
	@Test
	public void DaoTest2(){
		User user=new User();
		user.setUserName("buyer");
		user=perDao.getUserInfoFromDao(user);
		Assert.assertEquals("buyer's password", "37254660e226ea65ce6f1efd54233424", user.getPassword());
		Assert.assertEquals("buyer's type", 0, user.getUserType().intValue());
	}
	
	//get seller information
	@Test
	public void DaoTest3(){
		User user=new User();
		user.setUserName("seller");
		user=perDao.getUserInfoFromDao(user);
		Assert.assertEquals("buyer's password", "981c57a5cfb0f868e064904b8745766f", user.getPassword());
		Assert.assertEquals("buyer's type", 1, user.getUserType().intValue());
	}
	
	
//	@Test
//	public void contentDaoTest1(){
//		Product product=new Product();
//		product.setId(72);
//		product=contentDao.getContentInfoFromDao(product);
//		byte[] contents=product.getIcon();
//
//		System.out.println(contents.length+" :"+new String(contents));
//		System.out.println(product.getText().toString());
//		System.out.println(product.getBrief().toString());
//	}
	
	@Test
	public void contentDaoTest2(){
		Product product=new Product();
		product.setContentid(72);
		product=contentDao.getContentInfoFromDao(product);
		
		int iprice=product.getPrice()+100;		
		product.setPrice(iprice);

		System.out.println(product.getTrxes().isEmpty());
		if(product.getTrxes().isEmpty()){		
			System.out.println("is null::"+product.getTrxes());
		}
//		System.out.println(product.getBrief()+" "+product.getTitle()+" "+product.getPrice()+" ");
//		System.out.println("product.getTrxes()::"+product.getTrxes());
//		System.out.println("trx price::"+product.getTrxes().get(0).getPrice());

	}
	
	@Test
	public void contentDaoTest3(){
		
		
		List<Product> productList=contentDao.getAllContentInfoFromDao();
		
		for(Product product:productList){
//			System.out.println(product.getPrice().toString());
//			System.out.println("product.getTrxes()::"+product.getTrxes());
//			System.out.println("trx::"+product.getTrxes().get(0).getTrxcontentId());
		}
		


	}

}
