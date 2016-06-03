package com.isaac.javaweb.springlogin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.isaac.javaweb.springlogin.dao.IloginDao;
import com.isaac.javaweb.springlogin.meta.User;
import com.isaac.javaweb.springlogin.service.IgetUserInfo;

@Component("GetUserInfo")
public class GetUserInfo implements IgetUserInfo {

	@Autowired
	private IloginDao loginDao;
	
	
	public IloginDao getLoginDao() {
		return loginDao;
	}


	public void setLoginDao(IloginDao loginDao) {
		this.loginDao = loginDao;
	}


	@Override
	public String getUserPassword(User user) {		
		return this.loginDao.getUserPassword(user);
	}

}
