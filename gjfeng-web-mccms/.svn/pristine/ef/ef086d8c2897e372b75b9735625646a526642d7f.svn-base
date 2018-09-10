// Decompiled by DJ v2.9.9.60 Copyright 2000 Atanas Neshkov  Date: 2010-5-13 14:29:26
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   RolesAuthoritiesManagerDaoImpl.java

package cc.messcat.service.system;

import java.util.Iterator;
import java.util.List;

import cc.messcat.bases.BaseManagerDaoImpl;
import cc.messcat.entity.RolesAuthorities;
import cc.messcat.gjfeng.common.bean.Pager;

// Referenced classes of package org.stnet.service.enterprice.system:
//            RolesAuthoritiesManagerDao

public class RolesAuthoritiesManagerDaoImpl extends BaseManagerDaoImpl implements RolesAuthoritiesManagerDao {

	public RolesAuthoritiesManagerDaoImpl() {
	}

	public void addRolesAuthorities(RolesAuthorities rolesAuthorities) {
		rolesAuthoritiesDao.save(rolesAuthorities);
	}

	public void deleteRolesAuthorities(Long id) {
		rolesAuthoritiesDao.delete(id);
	}

	public Pager findRolesAuthorities(int pageSize, int pageNo, String statu) {
		Pager pager = rolesAuthoritiesDao.getObjectListByClass(pageSize, pageNo, RolesAuthorities.class, statu);
		return pager;
	}

	public RolesAuthorities getRolesAuthorities(Long id) {
		return rolesAuthoritiesDao.get(id);
	}

	public void updateRolesAuthorities(RolesAuthorities rolesAuthorities) {
		rolesAuthoritiesDao.update(rolesAuthorities);
	}

	public List findRolesAuthorities() {
		List find = rolesAuthoritiesDao.findAll();
		return find;
	}

	public boolean isNameUnique(String name, String orgName) {
		return true;
	}

	public List findAllBYState(String state) {
		return null;
	}

	public List findAllRolesId(Long roleId) {
		List temp = rolesAuthoritiesDao.findByRolesId(roleId);
		RolesAuthorities ra;
		for (Iterator iterator = temp.iterator(); iterator.hasNext(); ra.setAuthorities(authoritiesDao.get(ra.getAuthorities()
			.getId())))
			ra = (RolesAuthorities) iterator.next();

		return temp;
	}

	public void deleteRolesAuthoritiesByRoles(Long roleId) {
		List ra = rolesAuthoritiesDao.findByRolesId(roleId);
		RolesAuthorities temp;
		for (Iterator iterator = ra.iterator(); iterator.hasNext(); rolesAuthoritiesDao.delete(temp))
			temp = (RolesAuthorities) iterator.next();

	}
}