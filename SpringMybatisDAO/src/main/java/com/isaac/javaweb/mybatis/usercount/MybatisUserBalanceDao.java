package com.isaac.javaweb.mybatis.usercount;
//@Results({
//@Result(property="userId", column="userId"),
//@Result(property="userName", column="userName"),
//@Result(property="balance", column="balance")
//})

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface MybatisUserBalanceDao {
		
	@Select("Select * from UserBalance")
	public List<User> getUserList();
	
	@Select("Select balance from UserBalance where userId=#{userId}")
	public double getUserBalance(User user);
	
	@Update("update UserBalance set balance=#{balance} where userId=#{userId}")
	public void updateMoney(User user);

}
