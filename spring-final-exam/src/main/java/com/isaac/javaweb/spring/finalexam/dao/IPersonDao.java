package com.isaac.javaweb.spring.finalexam.dao;

import org.apache.ibatis.annotations.Select;
import com.isaac.javaweb.spring.finalexam.meta.User;

public interface IPersonDao {

	@Select ("Select * from person where userName=#{userName}")
	public User getUserInfoFromDao(String userName);

}
