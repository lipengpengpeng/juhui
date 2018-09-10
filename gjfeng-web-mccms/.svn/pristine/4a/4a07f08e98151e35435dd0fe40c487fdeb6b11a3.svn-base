/*
 * GjfStoreInfoManagerDaoImpl.java
 * 
 * Create by MCGT
 * 
 * Create time 2017-01-16
 * 
 * Version 1.0
 * 
 * Copyright 2013 Messcat, Inc. All rights reserved.
 * 
 */
package cc.messcat.service.store;

import java.util.List;
import cc.messcat.entity.GjfStoreInfo;
import cc.messcat.gjfeng.common.bean.Pager;
import cc.messcat.bases.BaseManagerDaoImpl;

public class GjfStoreInfoManagerDaoImpl extends BaseManagerDaoImpl implements GjfStoreInfoManagerDao {

	public void addGjfStoreInfo(GjfStoreInfo gjfStoreInfo) {
		this.gjfStoreInfoDao.save(gjfStoreInfo);
	}
	
	public void modifyGjfStoreInfo(GjfStoreInfo gjfStoreInfo) {
		this.gjfStoreInfoDao.update(gjfStoreInfo);
	}
	
	public void removeGjfStoreInfo(GjfStoreInfo gjfStoreInfo) {
		this.gjfStoreInfoDao.delete(gjfStoreInfo);
	}

	public void removeGjfStoreInfo(Long id) {
		this.gjfStoreInfoDao.delete(id);
	}
	
	public GjfStoreInfo retrieveGjfStoreInfo(Long id) {
		return (GjfStoreInfo) this.gjfStoreInfoDao.get(id);
	}
	
	public GjfStoreInfo retrieveSelfSupport() {
		return this.gjfStoreInfoDao.findSelfSupport();
	}

	@SuppressWarnings("unchecked")
	public List retrieveAllGjfStoreInfos() {
		return this.gjfStoreInfoDao.findAll();
	}
	
	public Pager retrieveGjfStoreInfosPager(int pageSize, int pageNo) {
		return this.gjfStoreInfoDao.getPager(pageSize, pageNo);
	}

}