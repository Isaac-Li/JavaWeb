package com.isaac.javaweb.springlogin.dao;

import org.apache.ibatis.annotations.Select;

import com.isaac.javaweb.springlogin.meta.User;

public interface IloginDao {

	@Select ("Select userPassword from user where userName=#{userName}")
	public String getUserPassword(User user);
}
