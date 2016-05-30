package com.isaac.javaweb.mybatis.usercount;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public class SimpleService {
	private MybatisUserBalanceDao mybatisUserBalanceDao;

	public void setMybatisUserBalanceDao(MybatisUserBalanceDao mybatisUserBalanceDao) {
		this.mybatisUserBalanceDao = mybatisUserBalanceDao;
	}
	
	public List<User> getUserList(){
		return this.mybatisUserBalanceDao.getUserList();
	}

	public void transferMoney(Long srcUserId, Long targetUserId, double count){
		User user1=new User();
		user1.setUserId(srcUserId);
		double balance1=this.mybatisUserBalanceDao.getUserBalance(user1);
		balance1=balance1-count;
		user1.setBalance(balance1);
		
		User user2=new User();
		user2.setUserId(targetUserId);
		double balance2=this.mybatisUserBalanceDao.getUserBalance(user2);
		balance2=balance2+count;
		user2.setBalance(balance2);
		
		
		this.mybatisUserBalanceDao.updateMoney(user1);
		//throwException();		
		this.mybatisUserBalanceDao.updateMoney(user2);
	}
	
	public void throwException(){
		throw new RuntimeException("Exception when transfer money"); 
	}
	
}
