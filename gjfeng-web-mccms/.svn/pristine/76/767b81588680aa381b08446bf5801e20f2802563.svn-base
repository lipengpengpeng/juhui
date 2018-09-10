// Decompiled by DJ v2.9.9.60 Copyright 2000 Atanas Neshkov  Date: 2010-5-13 14:12:21
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   RolesAuthoritiesDaoImpl.java

package cc.messcat.dao.system;

import java.util.List;

import cc.messcat.bases.BaseDaoImpl;
import cc.messcat.entity.RolesAuthorities;

public class RolesAuthoritiesDaoImpl extends BaseDaoImpl implements RolesAuthoritiesDao {

	public RolesAuthoritiesDaoImpl() {
	}

	public void delete(RolesAuthorities rolesAuthorities) {
		getHibernateTemplate().delete(rolesAuthorities);
	}

	public void delete(Long id) {
		getHibernateTemplate().delete(get(id));
	}

	public List findAll() {
		List find = getHibernateTemplate().find("from RolesAuthorities");
		return find;
	}

	public RolesAuthorities get(Long id) {
		return (RolesAuthorities) getHibernateTemplate().get(RolesAuthorities.class, id);
	}

	public void save(RolesAuthorities rolesAuthorities) {
		getHibernateTemplate().save(rolesAuthorities);
	}

	public void update(RolesAuthorities rolesAuthorities) {
		getSessionFactory().getCurrentSession().clear();
		getHibernateTemplate().saveOrUpdate(rolesAuthorities);
	}

	public List findByRolesId(Long id) {
		return getHibernateTemplate().find("from RolesAuthorities where roles.id = ?", id);
	}

	public List findByAuthId(Long id) {
		return getHibernateTemplate().find("from RolesAuthorities where authorities.id = ?", id);
	}
}