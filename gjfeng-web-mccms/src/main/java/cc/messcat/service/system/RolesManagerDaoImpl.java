// Decompiled by DJ v2.9.9.60 Copyright 2000 Atanas Neshkov  Date: 2010-5-13 14:30:31
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   RolesManagerDaoImpl.java

package cc.messcat.service.system;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import cc.messcat.bases.BaseManagerDaoImpl;
import cc.messcat.entity.Roles;
import cc.messcat.entity.RolesAuthorities;
import cc.messcat.gjfeng.common.bean.Pager;

// Referenced classes of package org.stnet.service.enterprice.system:
//            RolesManagerDao

public class RolesManagerDaoImpl extends BaseManagerDaoImpl implements RolesManagerDao {

	public RolesManagerDaoImpl() {
	}

	public void addRoles(Roles roles) {
		rolesDao.save(roles);
	}

	public void deleteRoles(Long id) {
		List rai = rolesAuthoritiesDao.findByRolesId(id);
		RolesAuthorities ra;
		for (Iterator iterator = rai.iterator(); iterator.hasNext(); rolesAuthoritiesDao.delete(ra))
			ra = (RolesAuthorities) iterator.next();

		rolesDao.delete(id);
	}

	public Pager findRoles(int pageSize, int pageNo, String statu) {
		Pager pager = rolesDao.getObjectListByClass(pageSize, pageNo, Roles.class, statu);
		Roles roles;
		Set kgdoqi;
		for (Iterator iterator = pager.getResultList().iterator(); iterator.hasNext(); roles.setRolesAuthoritieses(kgdoqi)) {
			roles = (Roles) iterator.next();
			kgdoqi = new HashSet();
			List rai = rolesAuthoritiesDao.findByRolesId(roles.getId());
			RolesAuthorities ra;
			for (Iterator iterator1 = rai.iterator(); iterator1.hasNext(); kgdoqi.add(ra)) {
				ra = (RolesAuthorities) iterator1.next();
				ra.setAuthorities(authoritiesDao.get(ra.getAuthorities().getId()));
			}

		}

		return pager;
	}

	public Roles getRoles(Long id) {
		Roles roles = rolesDao.get(id);
		Set kgdoqi = new HashSet();
		List rai = rolesAuthoritiesDao.findByRolesId(roles.getId());
		RolesAuthorities ra;
		for (Iterator iterator = rai.iterator(); iterator.hasNext(); kgdoqi.add(ra)) {
			ra = (RolesAuthorities) iterator.next();
			ra.setAuthorities(authoritiesDao.get(ra.getAuthorities().getId()));
		}

		roles.setRolesAuthoritieses(kgdoqi);
		return roles;
	}

	public void updateRoles(Roles roles) {
		rolesDao.update(roles);
	}

	public List findRoles() {
		List find = rolesDao.findAll();
		return find;
	}

	public boolean isNameUnique(String name, String orgName) {
		if (!name.equals(orgName))
			return rolesDao.isNameUnique(name);
		else
			return true;
	}

	public List findAllBYState(String state) {
		return rolesDao.findAllBYState(state);
	}
}