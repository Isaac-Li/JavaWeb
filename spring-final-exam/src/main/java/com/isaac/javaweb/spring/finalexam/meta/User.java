package com.isaac.javaweb.spring.finalexam.meta;

import java.util.List;

public class User {
	private Integer userid;
	private String userName;
	private String password;
	private String nickName;
	private Integer userType;
	private List<Trx> trxs;
	
	public List<Trx> getTrxs() {
		return trxs;
	}
	public void setTrxs(List<Trx> trxs) {
		this.trxs = trxs;
	}
	
	
	
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	
	
	
	
}
