/*
 * PageTypeOut.java
 * 
 * Create by Andy Lin
 * 
 * Create time 2013-08-26
 * 
 * Version 1.0
 * 
 * Copyright 2013 Messcat, Inc. All rights reserved.
 * 
 */
package cc.messcat.front;

import java.util.ArrayList;
import java.util.List;

import cc.messcat.entity.PageType;

public class PageTypeOut {
	
	private List<PageType> mostlistTypes;		//mostlist类型列表
	private List<PageType> listTypes;			//list类型列表
	private List<PageType> contentTypes;		//content类型列表
	private PageType linkType;					//link类型（有且只有一个对象）
	private List<PageType> productTypes;		//product类型列表
	private List<PageType> otherTypes;			//other类型列表
	
	public PageTypeOut(){
		mostlistTypes = new ArrayList<PageType>();
		listTypes = new ArrayList<PageType>();
		contentTypes = new ArrayList<PageType>();
		productTypes = new ArrayList<PageType>();
		otherTypes = new ArrayList<PageType>();
	}
	
	public List<PageType> getMostlistTypes() {
		return mostlistTypes;
	}
	
	public void setMostlistTypes(List<PageType> mostlistTypes) {
		this.mostlistTypes = mostlistTypes;
	}
	
	public List<PageType> getListTypes() {
		return listTypes;
	}
	
	public void setListTypes(List<PageType> listTypes) {
		this.listTypes = listTypes;
	}
	
	public List<PageType> getContentTypes() {
		return contentTypes;
	}
	
	public void setContentTypes(List<PageType> contentTypes) {
		this.contentTypes = contentTypes;
	}
	
	public PageType getLinkType() {
		return linkType;
	}

	public void setLinkType(PageType linkType) {
		this.linkType = linkType;
	}

	public List<PageType> getProductTypes() {
		return productTypes;
	}
	
	public void setProductTypes(List<PageType> productTypes) {
		this.productTypes = productTypes;
	}
	
	public List<PageType> getOtherTypes() {
		return otherTypes;
	}
	
	public void setOtherTypes(List<PageType> otherTypes) {
		this.otherTypes = otherTypes;
	}
	
}