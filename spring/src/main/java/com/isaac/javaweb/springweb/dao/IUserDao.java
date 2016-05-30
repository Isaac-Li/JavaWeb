package com.isaac.javaweb.springweb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.isaac.javaweb.springweb.meta.User;;

public interface IUserDao {
	@Select("Select * from UserBalance")
	public List<User> getUserList();
	
	@Select("Select * from UserBalance where userId=#{userId}")
	public User getUserInfo(User user);
}
