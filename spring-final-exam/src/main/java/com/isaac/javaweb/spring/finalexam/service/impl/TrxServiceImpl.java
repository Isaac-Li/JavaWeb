package com.isaac.javaweb.spring.finalexam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.isaac.javaweb.spring.finalexam.dao.ITrxDao;
import com.isaac.javaweb.spring.finalexam.meta.Trx;
import com.isaac.javaweb.spring.finalexam.service.ITrxService;

@Component("trxInfo")
public class TrxServiceImpl implements ITrxService {
	
	@Autowired
	private ITrxDao trxdao;

	@Override
	public void addTrx(Trx trx) {
		trxdao.addTrxFromDao(trx);		
	}

}
