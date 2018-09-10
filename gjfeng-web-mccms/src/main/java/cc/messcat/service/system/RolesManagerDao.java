// Decompiled by DJ v2.9.9.60 Copyright 2000 Atanas Neshkov  Date: 2010-5-13 14:29:36
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   RolesManagerDao.java

package cc.messcat.service.system;

import java.util.List;

import cc.messcat.entity.Roles;
import cc.messcat.gjfeng.common.bean.Pager;

public interface RolesManagerDao {

	public Roles getRoles(Long long1);

	public Pager findRoles(int i, int j, String s);

	public List findAllBYState(String s);

	public void addRoles(Roles roles);

	public void updateRoles(Roles roles);

	public void deleteRoles(Long long1);

	public List findRoles();

	public boolean isNameUnique(String s, String s1);
}