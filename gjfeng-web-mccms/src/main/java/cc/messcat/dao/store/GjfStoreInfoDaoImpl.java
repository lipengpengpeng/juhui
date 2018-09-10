/*
 * GjfStoreInfoDaoImpl.java
 * 
 * Create by MCGT
 * 
 * Create time 2017-01-16
 * 
 * Version 1.0
 * 
 * Copyright 2013 Messcat, Inc. All rights reserved.
 * 
 */
package cc.messcat.dao.store;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;

import cc.messcat.bases.BaseDaoImpl;
import cc.messcat.entity.GjfStoreInfo;
import cc.messcat.gjfeng.common.bean.Pager;

public class GjfStoreInfoDaoImpl extends BaseDaoImpl implements GjfStoreInfoDao {

	public void save(GjfStoreInfo gjfStoreInfo) {
		getHibernateTemplate().save(gjfStoreInfo);
	}
	
	public void update(GjfStoreInfo gjfStoreInfo) {
		getHibernateTemplate().update(gjfStoreInfo);
	}
	
	public void delete(GjfStoreInfo gjfStoreInfo) {
		getHibernateTemplate().delete(gjfStoreInfo);
	}

	public void delete(Long id) {
		getHibernateTemplate().delete(this.get(id));
	}
	
	public GjfStoreInfo get(Long id) {
		return (GjfStoreInfo) getHibernateTemplate().get(GjfStoreInfo.class, id);
	}

	@SuppressWarnings("rawtypes")
	public List findAll() {
		return getHibernateTemplate().find("from GjfStoreInfo");
	}

	@SuppressWarnings("rawtypes")
	public Pager getPager(int pageSize, int pageNo) {
		Session session = this.getSession();
		Criteria criteria = session.createCriteria(GjfStoreInfo.class);
		int rowCount = ((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
		criteria.setProjection(null);
		int startIndex = pageSize * (pageNo - 1);
		criteria.setFirstResult(startIndex);
		criteria.setMaxResults(pageSize);
		List result = criteria.list();
		return new Pager(pageSize, pageNo, rowCount, result);
	}

	@Override
	public GjfStoreInfo findSelfSupport() {
		Session session = this.getSession();
		String hql = "from GjfStoreInfo where storePro = '1'";
		Query query = session.createQuery(hql);
		return (GjfStoreInfo) query.uniqueResult();
	}

}