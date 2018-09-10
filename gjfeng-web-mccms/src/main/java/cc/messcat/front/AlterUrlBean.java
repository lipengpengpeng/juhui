/*
 * AlterUrlBean.java
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
package cc.messcat.front;

public class AlterUrlBean {

	private String tableName;		//表名
	private String oldUrl;			//旧的URL
	private String newUrl;			//新的URL
	
	public String getTableName() {
		return tableName;
	}
	
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	public String getOldUrl() {
		return oldUrl;
	}
	
	public void setOldUrl(String oldUrl) {
		this.oldUrl = oldUrl;
	}
	
	public String getNewUrl() {
		return newUrl;
	}
	
	public void setNewUrl(String newUrl) {
		this.newUrl = newUrl;
	}
	
}
