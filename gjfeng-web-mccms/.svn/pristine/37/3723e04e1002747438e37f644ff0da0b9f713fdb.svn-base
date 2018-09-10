/*
 * AlterUrlAction.java
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
package cc.messcat.web.system;

import cc.messcat.front.AlterUrlBean;
import cc.modules.commons.PageAction;
import cc.modules.constants.Constants;

public class AlterUrlAction extends PageAction {

	private static final long serialVersionUID = -887654596052275857L;
	private AlterUrlBean alterUrlBean;
	private String count;
	private String message;

	/**
	 * 跳转至显示页面
	 */
	public String execute() throws Exception {
		try {

		} catch (Exception e) {
			addActionMessage("显示页面出错！");
		}
		return Constants.SUCCESS;
	}
	
	/**
	 * 查看有多少条符合条件的记录
	 */
	public String retrieveByCondition() throws Exception {
		try {
			Long count = this.alterUrlManagerDao.retrieveRecordByCondition(alterUrlBean);
			this.count = count.toString();
		} catch (Exception e) {
			addActionMessage("retrieveByOldUrl, fail!");
		}
		return Constants.SUCCESS;
	}
	
	/**
	 * 修改URL
	 */
	public String alterUrl() throws Exception {
		try {
			Long num = this.alterUrlManagerDao.modifyUrl(alterUrlBean);
			this.message = "批量修改URL成功，共更新" + num + "条记录。";
		} catch (Exception e) {
			addActionMessage("alterUrl,fail!");
			this.message = "批量修改URL失败";
		}
		return Constants.SUCCESS;
	}

	public AlterUrlBean getAlterUrlBean() {
		return alterUrlBean;
	}

	public void setAlterUrlBean(AlterUrlBean alterUrlBean) {
		this.alterUrlBean = alterUrlBean;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

}