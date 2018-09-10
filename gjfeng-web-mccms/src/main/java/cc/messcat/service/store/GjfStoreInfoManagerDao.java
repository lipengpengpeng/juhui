/*
 * GjfStoreInfoManagerDao.java
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

public interface GjfStoreInfoManagerDao {

	public void addGjfStoreInfo(GjfStoreInfo gjfStoreInfo);
	
	public void modifyGjfStoreInfo(GjfStoreInfo gjfStoreInfo);
	
	public void removeGjfStoreInfo(GjfStoreInfo gjfStoreInfo);
	
	public void removeGjfStoreInfo(Long id);
	
	public GjfStoreInfo retrieveGjfStoreInfo(Long id);
	
	@SuppressWarnings("unchecked")
	public List retrieveAllGjfStoreInfos();
	
	public Pager retrieveGjfStoreInfosPager(int pageSize, int pageNo);
	
	public GjfStoreInfo retrieveSelfSupport();
	
}