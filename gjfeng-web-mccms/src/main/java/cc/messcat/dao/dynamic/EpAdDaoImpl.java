// Decompiled by DJ v2.9.9.60 Copyright 2000 Atanas Neshkov Date: 2010-5-13
// 14:07:20
package cc.messcat.dao.dynamic;

import java.util.List;

import cc.messcat.bases.BaseDaoImpl;
import cc.messcat.entity.EnterpriseAd;

/**
 * @author Messcat
 * @version 1.1
 * 
 */
public class EpAdDaoImpl extends BaseDaoImpl implements EpAdDao {

	public EpAdDaoImpl() {
	}

	public void delete(EnterpriseAd enterpriseAd) {
		getHibernateTemplate().delete(enterpriseAd);
	}

	public void delete(Long id) {
		getHibernateTemplate().delete(get(id));
	}

	public List findAll() {
		List find = getHibernateTemplate().find("from EnterpriseAd");
		return find;
	}

	public EnterpriseAd get(Long id) {
		return (EnterpriseAd) getHibernateTemplate().load(EnterpriseAd.class, id);
	}

	public void save(EnterpriseAd enterpriseAd) {
		getHibernateTemplate().save(enterpriseAd);
	}

	public void update(EnterpriseAd enterpriseAd) {
		getSessionFactory().getCurrentSession().clear();
		getHibernateTemplate().saveOrUpdate(enterpriseAd);
	}

	// 获取所有的广告分类
	public List getDistinctFrontNum() {
		return this.getHibernateTemplate().find("SELECT DISTINCT frontNum FROM EnterpriseAd");
	}

	// 获取所有的广告根据分类
	public List findAllByFrontNum(String frontNum) {
		return this.getHibernateTemplate().find("FROM EnterpriseAd where frontNum =? ORDER BY orderColumn ", frontNum);
	}
}