package com.isaac.javaweb.spring.finalexam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isaac.javaweb.spring.finalexam.dao.ITrxDao;
import com.isaac.javaweb.spring.finalexam.meta.Trx;
import com.isaac.javaweb.spring.finalexam.service.ITrxService;

@Service("TrxServiceImpl")
public class TrxServiceImpl implements ITrxService {
	
	@Autowired
	private ITrxDao trxdao;

	@Override
	@Transactional
	public void addTrx(Trx trx) {
		trxdao.addTrxFromDao(trx);		
	}

}
