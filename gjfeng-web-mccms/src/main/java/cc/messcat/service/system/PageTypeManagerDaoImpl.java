/*
 * PageTypeManagerDaoImpl.java
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
import java.util.Set;

import cc.messcat.bases.BaseManagerDaoImpl;
import cc.messcat.entity.EnterpriseColumn;
import cc.messcat.entity.PageType;
import cc.messcat.front.PageTypeOut;
import cc.messcat.gjfeng.common.bean.Pager;
import cc.messcat.service.column.EpColumnManagerDaoImpl;

public class PageTypeManagerDaoImpl extends BaseManagerDaoImpl implements PageTypeManagerDao {
	

	public void addPageType(PageType pageType) {
		this.pageTypeDao.save(pageType);
	}
	
	public void modifyPageType(PageType pageType) {
		// 更新模板类型信息
		this.pageTypeDao.update(pageType);
		// 更新该模板类型所对应的栏目的Url
		Set<EnterpriseColumn> enterpriseColumns = pageType.getEnterpriseColumns();
		for (EnterpriseColumn enterpriseColumn : enterpriseColumns) {
			String linkUrl = EpColumnManagerDaoImpl.buildLinkUrl(enterpriseColumn);
			if(linkUrl != null){
				enterpriseColumn.setLinkUrl(linkUrl);
			}
			//更新栏目信息
			this.epColumnDao.update(enterpriseColumn);
		}
	}
	
	public void removePageType(PageType pageType) {
		this.pageTypeDao.delete(pageType);
	}

	public boolean removePageType(Long id) {
		Long columnAmount = this.epColumnDao.findByPageTypeId(id);
		if(columnAmount == 0L){
			this.pageTypeDao.delete(id);
			return true;
		}else{
			return false;
		}
	}
	
	public PageType retrievePageType(Long id) {
		return (PageType) this.pageTypeDao.get(id);
	}

	public List<PageType> retrieveAllPageTypes() {
		return this.pageTypeDao.findAll();
	}
	
	public Pager retrievePageTypesPager(int pageSize, int pageNo) {
		return this.pageTypeDao.getPager(pageSize, pageNo);
	}

	/**
	 * 是否显示Link模板
	 */
	public String isShowLink() {
		StringBuilder sql = new StringBuilder("templateType = 'link'");
		List<PageType> tempPageType = this.pageTypeDao.findListBySql(sql.toString());
		
		String result = null;
		if (tempPageType.size() > 0) {
			result = "false";
		}else{
			result = "true";
		}
		return result;
	}

	/**
	 * 将PageType列表转换为PageTypeOut对象
	 */
	public PageTypeOut pageTypesToPageTypeOut(List<PageType> pageTypes) {
		PageTypeOut pageTypeOut = new PageTypeOut();
		
		for (PageType pageType : pageTypes) {
			if ("mostlist".equals(pageType.getTemplateType())) {
				pageTypeOut.getMostlistTypes().add(pageType);
			}
			if ("list".equals(pageType.getTemplateType())) {
				pageTypeOut.getListTypes().add(pageType);
			}
			if ("content".equals(pageType.getTemplateType())) {
				pageTypeOut.getContentTypes().add(pageType);
			}
			if ("link".equals(pageType.getTemplateType())) {
				pageTypeOut.setLinkType(pageType);
			}
			if ("product".equals(pageType.getTemplateType())) {
				pageTypeOut.getProductTypes().add(pageType);
			}
			if ("other".equals(pageType.getTemplateType())) {
				pageTypeOut.getOtherTypes().add(pageType);
			}
		}
		return pageTypeOut;
	}

}