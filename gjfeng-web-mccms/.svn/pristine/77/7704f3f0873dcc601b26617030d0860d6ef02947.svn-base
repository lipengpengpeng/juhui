// Decompiled by DJ v2.9.9.60 Copyright 2000 Atanas Neshkov  Date: 2010-5-13 14:33:05
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   UsersManagerDaoImpl.java

package cc.messcat.service.style;

import java.util.List;

import cc.messcat.bases.BaseManagerDaoImpl;
import cc.messcat.entity.WebSkin;
import cc.messcat.gjfeng.common.bean.Pager;

// Referenced classes of package org.stnet.service.enterprice.system:
//            UsersManagerDao

public class WebSkinManagerDaoImpl extends BaseManagerDaoImpl implements WebSkinManagerDao {

	public WebSkinManagerDaoImpl() {
	}

	public WebSkin getWebSkinById(Long id) {
		return webSkinDao.get(id);
	}

	public void addWebSkin(WebSkin webSkin) {
		webSkinDao.save(webSkin);
	}

	public void deleteWebSkin(Long id) {
		webSkinDao.delete(id);
	}

	public Pager findWebSkin(int pageSize, int pageNo, String statu) {
		Pager pager = webSkinDao.getObjectListByClass(pageSize, pageNo, WebSkin.class, statu);

		return pager;
	}

	public void updateWebSkin(WebSkin webSkin) {
		webSkinDao.update(webSkin);
	}

	public List findWebSkin() {
		return this.webSkinDao.findAll();
	}

	public boolean isNameUnique(String names, String orgName) {
		if (!names.equals(orgName))
			return webSkinDao.isNameUnique(names);
		else
			return true;
	}

}