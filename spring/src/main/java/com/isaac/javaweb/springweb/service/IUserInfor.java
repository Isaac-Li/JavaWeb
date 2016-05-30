package com.isaac.javaweb.springweb.service;

import java.util.List;

import com.isaac.javaweb.springweb.meta.User;

public interface IUserInfor {
	public List<User> getAllUserInfo();
	public User getUserInfoById(Long userId);
}
