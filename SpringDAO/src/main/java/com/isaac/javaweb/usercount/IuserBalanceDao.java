package com.isaac.javaweb.usercount;

import java.util.List;

import javax.sql.DataSource;

public interface IuserBalanceDao {
	public void setDataSource(DataSource dataSource);
	public List<User> getUserList();
	public void transferMoney(Long srcUserId, Long targetUserId, double count);
}
