// Decompiled by DJ v2.9.9.60 Copyright 2000 Atanas Neshkov  Date: 2010-5-13 14:33:05
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   UsersManagerDaoImpl.java

package cc.messcat.service.style;

import java.util.List;

import cc.messcat.bases.BaseManagerDaoImpl;
import cc.messcat.entity.WebSkinColor;
import cc.messcat.gjfeng.common.bean.Pager;

// Referenced classes of package org.stnet.service.enterprice.system:
//            UsersManagerDao

public class WebSkinColorManagerDaoImpl extends BaseManagerDaoImpl implements WebSkinColorManagerDao {

	public WebSkinColorManagerDaoImpl() {
	}

	public WebSkinColor getWebSkinColorById(Long id) {
		return webSkinColorDao.get(id);
	}

	public void addWebSkinColor(WebSkinColor webSkinColor) {
		webSkinColorDao.save(webSkinColor);
	}

	public void deleteWebSkinColor(Long id) {
		webSkinColorDao.delete(id);
	}

	public Pager findWebSkinColor(int pageSize, int pageNo, String statu) {
		Pager pager = webSkinColorDao.getObjectListByClass(pageSize, pageNo, WebSkinColor.class, statu);

		return pager;
	}

	public void updateWebSkinColor(WebSkinColor webSkinColor) {
		webSkinColorDao.update(webSkinColor);
	}

	public List findWebSkinColor() {
		return this.webSkinColorDao.findAll();
	}

	public boolean isNameUnique(String names, String orgName) {
		if (!names.equals(orgName))
			return webSkinColorDao.isNameUnique(names);
		else
			return true;
	}

	public List findWebSkinColor(Long webSkinId) {
		return this.webSkinColorDao.findAllByWebSkinId(webSkinId);
	}

	public WebSkinColor getWebSkinColorByWebSkinId(Long webSkinId) {
		return this.webSkinColorDao.getWebSkinColorByWebSkinId(webSkinId);
	}

}