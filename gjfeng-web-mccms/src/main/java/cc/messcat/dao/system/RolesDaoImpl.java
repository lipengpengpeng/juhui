// Decompiled by DJ v2.9.9.60 Copyright 2000 Atanas Neshkov  Date: 2010-5-13 14:13:04
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   RolesDaoImpl.java

package cc.messcat.dao.system;

import java.util.List;

import cc.messcat.bases.BaseDaoImpl;
import cc.messcat.entity.Roles;

// Referenced classes of package org.stnet.dao.enterprice.system:
//            RolesDao

public class RolesDaoImpl extends BaseDaoImpl implements RolesDao {

	public RolesDaoImpl() {
	}

	public void delete(Roles roles) {
		getHibernateTemplate().delete(roles);
	}

	public void delete(Long id) {
		getHibernateTemplate().delete(get(id));
	}

	public List findAll() {
		List find = getHibernateTemplate().find("from Roles");
		getSessionFactory().getCurrentSession().flush();
		return find;
	}

	public List findAllBYState(String state) {
		List find = getHibernateTemplate().find("from Roles where state = ?", state);
		getSessionFactory().getCurrentSession().flush();
		return find;
	}

	public Roles get(Long id) {
		return (Roles) getHibernateTemplate().get(Roles.class, id);
	}

	public void save(Roles roles) {
		getSessionFactory().getCurrentSession().clear();
		getHibernateTemplate().save(roles);
	}

	public void update(Roles roles) {
		getSessionFactory().getCurrentSession().clear();
		getHibernateTemplate().saveOrUpdate(roles);
	}

	public boolean isNameUnique(String name) {
		List temp = getHibernateTemplate().find("from Roles where name = ?", name.trim());
		getSessionFactory().getCurrentSession().flush();
		return temp.size() <= 0;
	}
}