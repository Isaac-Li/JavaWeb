package com.isaac.javaweb.usercount;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class userBalanceDao implements IuserBalanceDao {

	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<User> getUserList() {
		
		return this.jdbcTemplate.query("select * from Userbalance", new RowMapper<User>(){
			public User mapRow(ResultSet rs, int rowNum) throws SQLException{
				User user= new User();
				user.setUserId(rs.getInt("userId"));
				user.setUserName(rs.getString("userName"));
				user.setBalance(rs.getFloat("balance"));
				return user;
			}
		});
	}

	
	public void transferMoney(Long srcUserId, Long targetUserId, double count){
				
		jdbcTemplate.update("update UserBalance set balance=balance-? where userId=?", count, srcUserId );		
		//throwException();					
		jdbcTemplate.update("update UserBalance set balance=balance+? where userId=?", count, targetUserId);		
		
	}
	
	public void throwException(){
		throw new RuntimeException("Exception when transfer money"); 
	}
	
}
