// Decompiled by DJ v2.9.9.60 Copyright 2000 Atanas Neshkov  Date: 2010-5-13 14:30:42
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   UsersManagerDao.java

package cc.messcat.service.style;

import java.util.List;

import cc.messcat.entity.WebSkinColor;
import cc.messcat.gjfeng.common.bean.Pager;

public interface WebSkinColorManagerDao {

	public abstract WebSkinColor getWebSkinColorById(Long long1);

	public abstract Pager findWebSkinColor(int i, int j, String s);

	public abstract void addWebSkinColor(WebSkinColor webSkinColor);

	public abstract void updateWebSkinColor(WebSkinColor webSkinColor);

	public abstract void deleteWebSkinColor(Long long1);

	public abstract List findWebSkinColor();

	public abstract List findWebSkinColor(Long webSkinId);

	public abstract WebSkinColor getWebSkinColorByWebSkinId(Long webSkinId);

	public abstract boolean isNameUnique(String s, String s1);

}