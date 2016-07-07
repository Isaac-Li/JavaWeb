package com.isaac.javaweb.spring.finalexam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isaac.javaweb.spring.finalexam.dao.IPersonDao;
import com.isaac.javaweb.spring.finalexam.meta.User;
import com.isaac.javaweb.spring.finalexam.service.IPersonService;

@Service("PersonServiceImpl")
public class PersonServiceImpl implements IPersonService {

	@Autowired
	IPersonDao personDao;
	
	@Override
	@Transactional(readOnly = true)
	public User getUserInfo(User user) {
		User user1=this.personDao.getUserInfoFromDao(user);		
		return user1;
	}

}
