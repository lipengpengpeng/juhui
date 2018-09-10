// Decompiled by DJ v2.9.9.60 Copyright 2000 Atanas Neshkov  Date: 2010-5-13 14:29:06
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   AuthoritiesManagerDaoImpl.java

package cc.messcat.service.system;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cc.messcat.bases.BaseManagerDaoImpl;
import cc.messcat.entity.Authorities;
import cc.messcat.entity.RolesAuthorities;
import cc.messcat.front.EpAuthorities;
import cc.messcat.gjfeng.common.bean.Pager;

// Referenced classes of package org.stnet.service.enterprice.system:
//            AuthoritiesManagerDao

public class AuthoritiesManagerDaoImpl extends BaseManagerDaoImpl implements AuthoritiesManagerDao {

	public AuthoritiesManagerDaoImpl() {
	}

	public void addAuthorities(Authorities authorities) {
		authoritiesDao.save(authorities);
	}

	public void deleteAuthorities(Long id) {
		List ra = rolesAuthoritiesDao.findByAuthId(id);
		RolesAuthorities r;
		for (Iterator iterator = ra.iterator(); iterator.hasNext(); rolesAuthoritiesDao.delete(r))
			r = (RolesAuthorities) iterator.next();

		authoritiesDao.delete(id);
	}

	public Pager findAuthorities(int pageSize, int pageNo, String statu) {
		Pager pager = authoritiesDao.getObjectListByClass(pageSize, pageNo, Authorities.class, statu);
		return pager;
	}

	public Authorities getAuthorities(Long id) {
		return authoritiesDao.get(id);
	}

	public void updateAuthorities(Authorities authorities) {
		authoritiesDao.update(authorities);
	}

	public List findAuthorities() {
		List find = authoritiesDao.findAll();
		return find;
	}

	public boolean isNameUnique(String name, String orgName) {
		if (!name.equals(orgName))
			return authoritiesDao.isNameUnique(name);
		else
			return true;
	}

	public boolean isDisplayNameUnique(String displayName, String orgName) {
		if (!displayName.equals(orgName))
			return authoritiesDao.isDisplayNameUnique(displayName);
		else
			return true;
	}

	public List findEpAuthorities() {
		List epAuth = new ArrayList();
		int i = 0;
		EpAuthorities temp;
		for (Iterator iterator = authoritiesDao.findAuthoritiesByTypeId(Long.valueOf(0L)).iterator(); iterator.hasNext(); epAuth
			.add(temp)) {
			Authorities ad = (Authorities) iterator.next();
			temp = new EpAuthorities();
			temp.setAuthorities(ad);
			temp.setAuthoritiesList(authoritiesDao.findAuthoritiesByTypeId(ad.getId()));
		}

		return epAuth;
	}

	public Authorities getByName(String name) {
		return this.authoritiesDao.getByName(name);
	}

	/**
	 * 根据权限类型查询
	 */
	public List<Authorities> findAuthoritiesByType(String type) {
		return this.authoritiesDao.findAuthoritiesByType(type);
	}

	/**
	 * 根据权限类型查询除某个ID外的权限列表
	 */
	public List<Authorities> findAuthoritiesByTypeAndId(String type, Long id) {
		return this.authoritiesDao.findAuthoritiesByTypeAndId(type, id);
	}
	
	/**
	 * 根据显示名称查询
	 */
	public Authorities getAuthByDisplayName(String displayName) {
		return this.authoritiesDao.getAuthByDisplayName(displayName);
	}

	/**
	 * 根据权限名与权限类型查找权限
	 */
	public Authorities getByNameAndType(String name, String type) {
		return this.authoritiesDao.getByNameAndType(name, type);
	}
}