/*
 * PageTypeDaoImpl.java
 * 
 * Create by MCGT
 * 
 * Create time 2013-08-20
 * 
 * Version 1.0
 * 
 * Copyright 2013 Messcat, Inc. All rights reserved.
 * 
 */
package cc.messcat.dao.system;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.HibernateCallback;

import cc.messcat.bases.BaseDaoImpl;
import cc.messcat.entity.PageType;
import cc.messcat.gjfeng.common.bean.Pager;

public class PageTypeDaoImpl extends BaseDaoImpl implements PageTypeDao {

	public void save(PageType pageType) {
		getHibernateTemplate().save(pageType);
	}
	
	public void update(PageType pageType) {
		getHibernateTemplate().update(pageType);
	}
	
	public void delete(PageType pageType) {
		getHibernateTemplate().delete(pageType);
	}

	public void delete(Long id) {
		getHibernateTemplate().delete(this.get(id));
	}
	
	public PageType get(Long id) {
		return (PageType) getHibernateTemplate().get(PageType.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<PageType> findAll() {
		return getHibernateTemplate().find("from PageType");
	}

	@SuppressWarnings("unchecked")
	public Pager getPager(final int pageSize, final int pageNo) {
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(PageType.class);
		int rowCount = ((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
		criteria.setProjection(null);
		
		
		List<PageType> result = 
			getHibernateTemplate().executeFind(new HibernateCallback() {
				public List<PageType> doInHibernate(Session session)
						throws HibernateException, SQLException {
					StringBuilder sql = new StringBuilder("SELECT * FROM gjf_page_type ORDER BY ")
							.append("(SELECT CASE TEMPLATE_TYPE ")
							.append("WHEN 'mostlist' THEN 'a' ")
							.append("WHEN 'list' THEN 'b' ")
							.append("WHEN 'content' THEN 'c' ")
							.append("WHEN 'link' THEN 'd' ")
							.append("WHEN 'product' THEN 'e' ")
							.append("WHEN 'other' THEN 'f' ")
							.append("ELSE TEMPLATE_TYPE END)");
					SQLQuery query = session.createSQLQuery(sql.toString())
											.addEntity(PageType.class);
					query.setFirstResult(pageSize * (pageNo - 1))
						 .setMaxResults(pageSize);
					return query.list();
				}
			});
		return new Pager(pageSize, pageNo, rowCount, result);
	}

	/**
	 * 根据SQL语句查找列表
	 */
	@SuppressWarnings("unchecked")
	public List<PageType> findListBySql(String sql) {
		return getHibernateTemplate().find("from PageType where " + sql);
	}

}