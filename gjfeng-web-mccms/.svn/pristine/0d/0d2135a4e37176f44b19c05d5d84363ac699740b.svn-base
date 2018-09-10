/*
 * AlterUrlManagerDaoImpl.java
 * 
 * Create by Andy Lin
 * 
 * Create time 2013-08-20
 * 
 * Version 1.0
 * 
 * Copyright 2013 Messcat, Inc. All rights reserved.
 * 
 */
package cc.messcat.service.system;

import cc.messcat.bases.BaseManagerDaoImpl;
import cc.messcat.front.AlterUrlBean;


public class AlterUrlManagerDaoImpl extends BaseManagerDaoImpl implements AlterUrlManagerDao  {

	//根据查询条件查找记录数
	public Long retrieveRecordByCondition(AlterUrlBean condition) {
		return this.alterUrlDao.getCountByCondition(condition);
	}

	//更新数据库中的URL记录
	public Long modifyUrl(AlterUrlBean alterUrlBean) {
		Long num = this.alterUrlDao.getCountByCondition(alterUrlBean);
		Long record = this.alterUrlDao.updateUrl(alterUrlBean);
		if(record > 0){
			return num;
		}else{
			return 0L;
		}
	}
	
}