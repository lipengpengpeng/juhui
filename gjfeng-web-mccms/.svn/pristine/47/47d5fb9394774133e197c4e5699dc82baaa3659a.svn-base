// Decompiled by DJ v2.9.9.60 Copyright 2000 Atanas Neshkov  Date: 2010-5-13 14:33:32
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   UsersRolesManagerDaoImpl.java

package cc.messcat.service.system;

import java.util.List;

import cc.messcat.bases.BaseManagerDaoImpl;
import cc.messcat.entity.UsersRoles;
import cc.messcat.gjfeng.common.bean.Pager;

// Referenced classes of package org.stnet.service.enterprice.system:
//            UsersRolesManagerDao

public class UsersRolesManagerDaoImpl extends BaseManagerDaoImpl implements UsersRolesManagerDao {

	public UsersRolesManagerDaoImpl() {
	}

	public void addUsersRoles(UsersRoles usersRoles) {
		usersRolesDao.save(usersRoles);
	}

	public void deleteUsersRoles(Long id) {
		usersRolesDao.delete(id);
	}

	public Pager findUsersRoles(int pageSize, int pageNo, String statu) {
		Pager pager = usersRolesDao.getObjectListByClass(pageSize, pageNo, UsersRoles.class, statu);
		return pager;
	}

	public UsersRoles getUsersRoles(Long id) {
		return usersRolesDao.get(id);
	}

	public void updateUsersRoles(UsersRoles usersRoles) {
		usersRolesDao.update(usersRoles);
	}

	public List findUsersRoles() {
		List find = usersRolesDao.findAll();
		return find;
	}

	public void deleteUsersRolesByUsersId(Long id) {
		//        List ur = usersRolesDao.findByUsersId(id);
		//        UsersRoles u;
		//        for(Iterator iterator = ur.iterator(); iterator.hasNext(); usersRolesDao.delete(u))
		//            u = (UsersRoles)iterator.next();
		usersRolesDao.deleteUsersRoleByUserId(id);

	}

	public boolean isNameUnique(String name, String orgName) {
		return true;
	}

	public List findAllBYState(String state) {
		return null;
	}
}