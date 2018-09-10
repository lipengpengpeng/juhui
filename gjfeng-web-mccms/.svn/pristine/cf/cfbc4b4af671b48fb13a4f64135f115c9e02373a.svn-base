/*
 * PageTypeDao.java
 * 
 * Create by MCGT
 * 
 * Create time 2013-08-20
 * 
 * Version 1.0
 * 
 * Copyright 2013 Messcat, Inc. All rights reserved.
 * 
 */
package cc.messcat.dao.system;

import java.util.List;

import cc.messcat.entity.PageType;
import cc.messcat.gjfeng.common.bean.Pager;

public interface PageTypeDao {

	public void save(PageType pageType);
	
	public void update(PageType pageType);
	
	public void delete(PageType pageType);
	
	public void delete(Long id);
	
	public PageType get(Long id);
	
	public List<PageType> findAll();
	
	public Pager getPager(int pageSize, int pageNo);

	//根据SQL语句查找列表
	public List<PageType> findListBySql(String sql);
	
}