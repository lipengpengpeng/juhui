// Decompiled by DJ v2.9.9.60 Copyright 2000 Atanas Neshkov  Date: 2010-5-13 14:12:54
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   UsersDao.java

package cc.messcat.dao.style;

import java.util.List;

import cc.messcat.entity.WebSkinColor;
import cc.messcat.gjfeng.common.bean.Pager;

public interface WebSkinColorDao {

	public WebSkinColor get(Long long1);

	public void save(WebSkinColor webSkinColor);

	public void update(WebSkinColor webSkinColor);

	public void delete(WebSkinColor webSkinColor);

	public void delete(Long long1);

	public List findAll();

	public List findAllByWebSkinId(Long webSkinId);

	public boolean isNameUnique(String s);

	public Pager getObjectListByClass(int i, int j, Class class1, String s);

	public WebSkinColor getWebSkinColorByWebSkinId(Long webSkinId);

}