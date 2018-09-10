/*
 * PageTypeManagerDao.java
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
package cc.messcat.service.system;

import java.util.List;

import cc.messcat.entity.PageType;
import cc.messcat.front.PageTypeOut;
import cc.messcat.gjfeng.common.bean.Pager;

public interface PageTypeManagerDao {

	public void addPageType(PageType pageType);
	
	public void modifyPageType(PageType pageType);
	
	public void removePageType(PageType pageType);
	
	public boolean removePageType(Long id);
	
	public PageType retrievePageType(Long id);
	
	public List<PageType> retrieveAllPageTypes();
	
	public Pager retrievePageTypesPager(int pageSize, int pageNo);

	//是否显示Link模板
	public String isShowLink();
	
	//将PageType列表转换为PageTypeOut对象
	public PageTypeOut pageTypesToPageTypeOut(List<PageType> pageTypes);
}