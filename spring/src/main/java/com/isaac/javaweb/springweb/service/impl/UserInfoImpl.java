package com.isaac.javaweb.springweb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.isaac.javaweb.springweb.dao.IUserDao;
import com.isaac.javaweb.springweb.meta.User;
import com.isaac.javaweb.springweb.service.IUserInfor;

@Component("UserInfoImpl")
public class UserInfoImpl implements IUserInfor {

	@Autowired
	public IUserDao userDao;

	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	public List<User> getAllUserInfo() {

		return this.userDao.getUserList();
	}

	public User getUserInfoById(Long userId) {
		User user=new User();
		user.setUserId(userId);
		
		user=this.userDao.getUserInfo(user);
		
		return user;
	}

}
