/*
 * PageTypeAction.java
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
package cc.messcat.web.system;

import java.util.List;

import cc.messcat.entity.PageType;
import cc.modules.commons.PageAction;

public class PageTypeAction extends PageAction {
	
	private Long id;
	
	private PageType pageType;
	
	private List<PageType> pageTypes;
	
	private String isShowLink;
	
	private String message;
	
	@SuppressWarnings("unchecked")
	public String retrieveAllPageTypes() throws Exception {
		try {
			super.pager = this.pageTypeManagerDao.retrievePageTypesPager(pageSize, pageNo);
			this.pageTypes = super.pager.getResultList();
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "success";
	}
	
	public String retrievePageTypeById() throws Exception {
		try {
			this.pageType = this.pageTypeManagerDao.retrievePageType(id);
			this.isShowLink = this.pageTypeManagerDao.isShowLink();
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "editPage";
	}

	public String newPage() throws Exception {
		try {
			this.isShowLink = this.pageTypeManagerDao.isShowLink();
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "newPage";
	}
	
	public String viewPage() throws Exception {
		try {
			this.pageType = this.pageTypeManagerDao.retrievePageType(id);
		} catch (Exception ex) {
			addActionMessage("Load page error!");
		}
		return "viewPage";
	}
	
	public String newPageType() throws Exception {
		try {
			this.pageTypeManagerDao.addPageType(this.pageType);
			addActionMessage("New successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("New fail!");
		}
		return this.retrieveAllPageTypes();
	}
	
	public String editPageType() throws Exception {
		try {
			PageType pageType0 = this.pageTypeManagerDao.retrievePageType(this.id);
			pageType0.setName(this.pageType.getName());
			pageType0.setTemplateType(this.pageType.getTemplateType());
			pageType0.setTemplateUrl(this.pageType.getTemplateUrl());
			pageType0.setFeaturesUrl(this.pageType.getFeaturesUrl());
			pageType0.setIntro(this.pageType.getIntro());

			this.pageTypeManagerDao.modifyPageType(pageType0);
			addActionMessage("Update successfully!");
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("Update fail!");
		}
		return this.retrieveAllPageTypes();
	}
	
	public String delPageType() throws Exception {
		try {
			boolean isSuccess = this.pageTypeManagerDao.removePageType(this.id);
			if(isSuccess){
				addActionMessage("Delete successfully!");
			}else{
				message = "栏目表中还有栏目归属于此页面类型，删除失败！";
				addActionMessage("Delete fail!");
			}
		} catch (Exception ex) {
			this.addActionError(ex.getMessage());
			addActionMessage("Delete fail!");
		}
		return this.retrieveAllPageTypes();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PageType getPageType() {
		return pageType;
	}

	public void setPageType(PageType pageType) {
		this.pageType = pageType;
	}

	public List<PageType> getPageTypes() {
		return pageTypes;
	}

	public void setPageTypes(
			List<PageType> pageTypes) {
		this.pageTypes = pageTypes;
	}

	public String getIsShowLink() {
		return isShowLink;
	}

	public void setIsShowLink(String isShowLink) {
		this.isShowLink = isShowLink;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}