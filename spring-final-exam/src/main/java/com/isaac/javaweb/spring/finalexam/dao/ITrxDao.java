package com.isaac.javaweb.spring.finalexam.dao;

import org.apache.ibatis.annotations.Insert;

import com.isaac.javaweb.spring.finalexam.meta.Trx;



public interface ITrxDao {

	@Insert ("Insert into trx (contentId, personId, price, time) "
			+ "values (#{trxcontentId}, #{personId}, #{price}, #{time} ) " )	
	public void addTrxFromDao(Trx trx);
	

}
