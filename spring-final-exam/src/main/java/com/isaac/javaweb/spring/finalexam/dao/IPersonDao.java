package com.isaac.javaweb.spring.finalexam.dao;

import org.apache.ibatis.annotations.Select;
import com.isaac.javaweb.spring.finalexam.meta.User;

public interface IPersonDao {

	@Select ("Select id as userid, userName, password, nickName, userType from person where userName=#{userName}")
	public User getUserInfoFromDao(User user);

	@Select ("Select p.id, p.userName, p.password, p.nickName, "
			+ " p.userType, con.id, trx.price, con.title, con.icon, "
			+ "con.abstract as brief, con.text from person p left join trx on trx.personId=p.id left join content con on trx.contentId=con.id where p.userName=#{userName}")
	public User getUserContentFromDao(User user);
}
