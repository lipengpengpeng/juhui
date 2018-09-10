/*
 * Copyright (c) 2015 Messcat. All rights reserved.
 */
package cc.messcat.gjfeng.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateCallback;

import cc.messcat.gjfeng.common.util.HQLUtil;
import cc.messcat.gjfeng.common.util.ObjValid;
import cc.messcat.gjfeng.common.util.StringUtil;



/**
 * 封装hibernate操作
 * 
 * @author Microcat
 * @version 1.0
 * @param <T>
 */
@SuppressWarnings("unchecked")
public class BaseHibernateDaoImpl<T extends Serializable> extends BaseHibernateDaoSupport implements BaseHibernateDao<T> {

	private Class<T> clazz;

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;
	
	@Resource
	public void setSessionFactory0(SessionFactory sessionFactory){  
		super.setSessionFactory(sessionFactory); 
	}

	/**
	 * Method of getCurrentSession will close session automatically and it's
	 * using current session transaction
	 */
	protected final synchronized  Session getCurrentSession() {
		if (this.sessionFactory == null) {
			throw new HibernateException("Session Create Fail,SessionFactory is null!");
		}
		return this.sessionFactory.getCurrentSession();
	}

	/**
	 * Method of openSession need to close session and open another new session
	 */
	protected final Session getNewSession() {
		return this.sessionFactory.openSession();
	}

	protected final void setClazz(final Class<T> clazzToSet) {
		this.clazz = clazzToSet;
	}

	public final T get(final long id) {
		return (T) getCurrentSession().get(clazz, id);
	}

	public final List<T> getAll() {
		return getCurrentSession().createQuery("from " + clazz.getName()).list();
	}

	public final void save(final T entity) {
		getCurrentSession().saveOrUpdate(entity);
	}

	public final T update(final T entity) {
		getCurrentSession().update(entity);
		return entity;
	}

	public final void delete(final T entity) {
		getCurrentSession().delete(entity);
	}

	public final void deleteById(final long entityId) {
		final T entity = get(entityId);
		delete(entity);
	}
	
	/**
	 * 修改对象
	 */
	public void updateObj(Object obj) {
		getHibernateTemplate().update(obj);
	}

	/**
	 * 根据ID删除对象
	 */
	public void delete(Long id, String objName) {
		getHibernateTemplate().delete(get(id, objName));
	}

	/**
	 * 查找所有对象
	 */
	public List getAll(String objName) {
		List all = getHibernateTemplate().find("from " + objName);
		return all;
	}

	@Override
	public List find(String hql) {
		List all = getHibernateTemplate().find(hql);
		return all;
	}

	/**
	 * @param objName
	 *            对象名
	 * @param status
	 *            状态
	 */
	public List findAllByStatus(String objName, String status) {
		List all = getHibernateTemplate().find("from " + objName + " where status = " + status + " ");
		return all;
	}

	/**
	 * 根据ID查找对象
	 */
	public Object get(Long id, String objName) {
		Object obj = getEntityClass(objName);
		if (obj == null) {
			return null;
		} else {
			return getHibernateTemplate().get(obj.getClass(), id);
		}
	}

	@Override
	public int update(Class entityClass, Map<String, Object> props, Map<String, Object> attrs) {
		Object[] propsValue = props.values().toArray(); // 获取value值的数组
		Object[] attrsValue = attrs.values().toArray();
		Object[] objs = new Object[propsValue.length + attrsValue.length];
		System.arraycopy(propsValue, 0, objs, 0, propsValue.length);
		System.arraycopy(attrsValue, 0, objs, propsValue.length, attrsValue.length);
		return this.updateObjects(HQLUtil.createUpdateHQL(entityClass.getSimpleName(), props.keySet(), attrs.keySet()), objs);
	}

	@Override
	public void delete(Object entity) {
		this.getHibernateTemplate().delete(entity);
	}

	@Override
	public int delete(Class entityClass, Map<String, Object> attrs) {
		return this.updateObjects(HQLUtil.createDeleteHQL(entityClass.getSimpleName(), attrs.keySet()), attrs.values().toArray());
	}

	@Override
	public int deleteOr(Class entityClass, Map<String, Object> attrs) {
		return this.updateObjects(HQLUtil.createDeleteOrHQL(entityClass.getSimpleName(), attrs.keySet()), attrs.values().toArray());
	}

	@Override
	public <T> List<T> queryList(Class entityClass, String ids) {
		return (List<T>) this.getHibernateTemplate().find(HQLUtil.createQueryHQL(entityClass.getSimpleName(), ids),
			HQLUtil.changeToIntegerArray(ids));
	}

	public <T> List<T> queryNotInList(Class entityClass, String ids) {
		if (null == ids || ids.trim().equals("")) {
			return (List<T>) this.getHibernateTemplate().find(HQLUtil.createQueryNotInHQL(entityClass.getSimpleName(), ids));
		} else {
			return (List<T>) this.getHibernateTemplate().find(HQLUtil.createQueryNotInHQL(entityClass.getSimpleName(), ids),
				HQLUtil.changeToIntegerArray(ids));
		}
	}

	@Override
	public <T> T query(Class<T> entityClass, Map<String, Object> attrs) {
		return this.findObject(HQLUtil.createQueryHQL(entityClass.getSimpleName(), attrs.keySet()), attrs.values().toArray());
	}

	@Override
	public <T> T query(Class<T> entityClass, String likeAttr, String likeValue, Map<String, Object>... attrs) {
		String hql;
		if (ObjValid.isValidMap(attrs)) {
			hql = HQLUtil.createLikeQueryHQL(entityClass.getSimpleName(), likeAttr, attrs[0].keySet());
			return this.findObject(hql, new Object[] { likeValue, attrs[0].values().toArray() });
		} else {
			hql = HQLUtil.createLikeQueryHQL(entityClass.getSimpleName(), likeAttr);
			return this.findObject(hql, likeValue);
		}
	}

	@Override
	public long queryTotalRecord(Class entityClass, Map<String, Object>... attrs) {
		String hql;
		if (ObjValid.isValidMap(attrs)) {
			hql = HQLUtil.createQueryTotalRecordHQL(entityClass.getSimpleName(), attrs[0].keySet());
			return this.findObject(hql, attrs[0].values().toArray());
		} else {
			hql = HQLUtil.createQueryTotalRecordHQL(entityClass.getSimpleName());
			return this.findObject(hql);
		}
	}
	
	@Override
	public long queryTotalRecord(Class entityClass,String isGroup, String likeKey, String likeValue, String groupKey, Map<String, Object>... attrs) {
		StringBuffer hql=new StringBuffer();
		if (ObjValid.isValidMap(attrs)) {
			hql.append(HQLUtil.createQueryTotalRecordHQL(entityClass.getSimpleName(), attrs[0].keySet()));
			if(StringUtil.isNotBlank(likeKey)){
				hql.append(" and o.").append(likeKey).append(" like '%").append(likeValue).append("%'");
			}
			if(StringUtil.isNotBlank(isGroup) && "1".equals(isGroup)){
				hql.append(" group by o.").append(groupKey);
			}
			return this.findGroupObject(hql.toString(), attrs[0].values().toArray());
		} else {
			hql.append(HQLUtil.createQueryTotalRecordHQL(entityClass.getSimpleName()));
			if(StringUtil.isNotBlank(likeKey)){
				hql.append(" and o.").append(likeKey).append(" like '%").append(likeValue).append("%'");
			}
			if(StringUtil.isNotBlank(isGroup) && "1".equals(isGroup)){
				hql.append(" group by o.").append(groupKey);
			}
			return this.findGroupObject(hql.toString());
		}
	}

	@Override
	public long queryTotalRecord(Class entityClass, String likeKey, String likeValue, Map<String, Object>... attrs) {
		StringBuffer hql =new StringBuffer();
		if (ObjValid.isValidMap(attrs)) {
			hql.append(HQLUtil.createQueryTotalRecordHQL(entityClass.getSimpleName(), attrs[0].keySet()));
			if(StringUtil.isNotBlank(likeKey)){
				hql.append(" and o.").append(likeKey).append(" like '%").append(likeValue).append("%'");
			}
			return this.findObject(hql.toString(), attrs[0].values().toArray());
		}else{
			hql.append("select count(o) from ").append(entityClass.getSimpleName());
			if(StringUtil.isNotBlank(likeKey)){
				hql.append(" as o where o.").append(likeKey).append(" like ").append(likeValue);
			}
		}
		return this.findObject(hql.toString());
	}

	@Override
	public int queryMaxId(Class entityClass) {
		StringBuffer hql = new StringBuffer("select max(o.id) from ").append(entityClass.getSimpleName()).append(" as o");
		return this.findObject(hql.toString());
	}

	public int queryCount(final String sql, final Object... conditions) {
		Long count = (Long) this.getHibernateTemplate().executeWithNativeSession(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(sql);
				if(conditions != null){
					for (int i = 0; i < conditions.length; i++) {
						query.setParameter(i, conditions[i]);
					}
				}
				return query.uniqueResult();
			}
		});
		return count.intValue();
	}

	@Override
	public <T> List<T> queryList(Class<T> entityClass, int pageStart, int pageSize, String orderAttr, String orderType,
		Map<String, Object>... attrs) {
		String hql;
		if (ObjValid.isValidMap(attrs)) {
			hql = HQLUtil.createQueryListHQL(entityClass.getSimpleName(), orderAttr, orderType, attrs[0].keySet());
			return this.findPageObjects(hql, pageStart, pageSize, attrs[0].values().toArray());
		} else {
			hql = HQLUtil.createQueryListHQL(entityClass.getSimpleName(), orderAttr, orderType);
			return this.findPageObjects(hql, pageStart, pageSize);
		}
	}

	@Override
	public <T> List<T> queryList2(Class<T> entityClass, int pageStart, int pageSize, String orderAttr, String orderType,
		String likeAttr, String likeValue,Map<String, Object>... attrs) {
		StringBuffer hql = new StringBuffer();
		if (ObjValid.isValidMap(attrs)) {
			hql.append(HQLUtil.createQueryLikeListHQL(entityClass.getSimpleName(), orderAttr, orderType,likeAttr,likeValue,attrs[0].keySet()));
			return this.findPageObjects(hql.toString(), pageStart, pageSize, attrs[0].values().toArray());
		} else {
				hql.append("from ").append(entityClass.getSimpleName()).append(" as o where o.").append(likeAttr)
				.append(" like '%"+likeValue+"%' ").append("order by o.").append(orderAttr).append(" ").append(orderType);
				return this.findPageObjects(hql.toString(), pageStart, pageSize, null);
		}
	}

	@Override
	public <T> List<T> queryList(Class<T> entityClass, String orderAttr, String orderType, Map<String, Object>... attrs) {
		String hql;
		if (ObjValid.isValidMap(attrs) && null != attrs) {
			hql = HQLUtil.createQueryListHQL(entityClass.getSimpleName(), orderAttr, orderType, attrs[0].keySet());
			return (List<T>) this.getHibernateTemplate().find(hql, attrs[0].values().toArray());
		} else {
			hql = HQLUtil.createQueryListHQL(entityClass.getSimpleName(), orderAttr, orderType);
			return (List<T>) this.getHibernateTemplate().find(hql);
		}
	}

	@Override
	public <T> List<T> queryOrList(Class<T> entityClass, String orderAttr, String orderType, Map<String, Object>... attrs) {
		String hql;
		if (ObjValid.isValidMap(attrs)) {
			hql = HQLUtil.createQueryOrListHQL(entityClass.getSimpleName(), orderAttr, orderType, attrs[0].keySet());
			return (List<T>) this.getHibernateTemplate().find(hql, attrs[0].values().toArray());
		} else {
			hql = HQLUtil.createQueryListHQL(entityClass.getSimpleName(), orderAttr, orderType);
			return (List<T>) this.getHibernateTemplate().find(hql);
		}
	}

	@Override
	public <T> List<T> queryList(Class<T> entityClass, String orderAttr, String orderType, String groupAttr,
		Map<String, Object>... attrs) {
		String hql;
		if (ObjValid.isValidMap(attrs)) {
			hql = HQLUtil.createQueryListHQL(entityClass.getSimpleName(), orderAttr, orderType, groupAttr, attrs[0].keySet());
			return (List<T>) this.getHibernateTemplate().find(hql, attrs[0].values().toArray());
		} else {
			hql = HQLUtil.createQueryListHQL(entityClass.getSimpleName(), orderAttr, orderType, groupAttr);
			return (List<T>) this.getHibernateTemplate().find(hql);
		}
	}
	
	
	@Override
	public <T> List<T> queryList(Class<T> entityClass, int pageStart, int pageSize,String orderAttr, String orderType, String groupAttr,
		Map<String, Object>... attrs) {
		String hql;
		if (ObjValid.isValidMap(attrs)) {
			hql = HQLUtil.createQueryListHQL(entityClass.getSimpleName(), orderAttr, orderType, groupAttr, attrs[0].keySet());
			//return (List<T>) this.getHibernateTemplate().find(hql, attrs[0].values().toArray());
			return this.findPageObjects(hql, pageStart, pageSize, attrs[0].values().toArray());
		} else {
			hql = HQLUtil.createQueryListHQL(entityClass.getSimpleName(), orderAttr, orderType, groupAttr);
			//return (List<T>) this.getHibernateTemplate().find(hql);
			return this.findPageObjects(hql, pageStart, pageSize);
		}
	}
	
	
	@Override
	public <T> List<T> queryListByType(Class<T> entityClass, int pageStart, int pageSize,String type,String orderAttr, String orderType, String groupAttr,
		Map<String, Object>... attrs) {
		String hql;
		if (ObjValid.isValidMap(attrs)) {
			hql = HQLUtil.createQueryListHQL(entityClass.getSimpleName(),type,orderAttr, orderType, groupAttr, attrs[0].keySet());
			//return (List<T>) this.getHibernateTemplate().find(hql, attrs[0].values().toArray());
			return this.findPageObjects(hql, pageStart, pageSize, attrs[0].values().toArray());
		} else {
			hql = HQLUtil.createQueryListHQL(entityClass.getSimpleName(), type,orderAttr, orderType, groupAttr);
			//return (List<T>) this.getHibernateTemplate().find(hql);
			return this.findPageObjects(hql, pageStart, pageSize);
		}
	}
	
	@Override
	public <T> List<T> likeQueryList(Class<T> entityClass, String orderAttr, String orderType, String likeAttr, String likeValue,
		int... limit) {
		StringBuffer hql = new StringBuffer("from ").append(entityClass.getSimpleName()).append(" as o where o.").append(likeAttr)
			.append(" like ? ");
		hql.append("order by o.").append(orderAttr).append(" ").append(orderType);
		if (limit.length > 0) {
			return this.findLimitObjects(hql.toString(), limit[0], likeValue);
		} else {
			return this.findObjects(hql.toString(), likeValue);
		}
	}
	

	/**
	 * 获取相应的entity name
	 * 
	 * @author sherry
	 * @param objName
	 * @return
	 */
	private Object getEntityClass(String objName) {
		Object obj = null;
		try {
			obj = Class.forName(objName).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}


}
