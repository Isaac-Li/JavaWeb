package com.isaac.javaweb.spring.finalexam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.isaac.javaweb.spring.finalexam.dao.IPersonDao;
import com.isaac.javaweb.spring.finalexam.meta.User;
import com.isaac.javaweb.spring.finalexam.service.IPerson;

@Component("personInfo")
public class IPersonImpl implements IPerson {

	@Autowired
	IPersonDao personDao;
	
	@Override
	public User getUserInfo(User user) {
		User user1=this.personDao.getUserInfoFromDao(user);		
		return user1;
	}

}
