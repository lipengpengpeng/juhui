package cc.messcat.bases;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

import cc.messcat.entity.EnterpriseNews;
import cc.messcat.gjfeng.common.bean.Pager;
import cc.modules.util.DateHelper;
import cc.modules.util.HQLUtil;
import cc.modules.util.ObjValid;
import cc.modules.util.StringUtil;

/**
 * @author Messcat
 * @version 1.1
 */
@SuppressWarnings("unchecked")
public class BaseDaoImpl extends BaseHibernateDaoSupport implements BaseDao {

	public BaseDaoImpl() {
	}

	/**
	 * 添加对象
	 */
	public void save(Object obj) {
		getHibernateTemplate().save(obj);
	}

	/**
	 * 修改对象
	 */
	public void update(Object obj) {
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
	
	/**
	 * 获取相应的entity name
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

	@Override
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
	

	public Pager getObjectListByClass(int pageSize, int pageNo, Class classObject, String statu) {
		Session session = null;
		Pager pager = null;
		try {
			session = getHibernateTemplate().getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(classObject);
			if (!"-1".equals(statu))
				criteria.add(Restrictions.eq("state", statu));
			int rowCount = ((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
			criteria.setProjection(null);
			int startIndex = pageSize * (pageNo - 1);
			criteria.setFirstResult(startIndex);
			criteria.setMaxResults(pageSize);
			List result = criteria.list();
			pager = new Pager(pageSize, pageNo, rowCount, result);
		} catch (Exception ex) {

		} finally {
			session.close();
		}

		return pager;
	}

	public List getInfoByClassAndSize(String classObject, Long size, Long clickTimes, String isprimPhoto, String isCommend,
		String b_or_s, Long columnId, String isIndexPhoto) {
		StringBuffer SQL = new StringBuffer();
		SQL.append("from ").append(classObject).append(" as temp where 1 = 1 ").toString();
		if (!"-1".equals(isprimPhoto))
			SQL.append(" and temp.isPrimPhoto = 1 ");
		if (!"-1".equals(isIndexPhoto))
			SQL.append(" and temp.isIndexPhoto = 1 ");
		if (!"-1".equals(isCommend))
			SQL.append(" and temp.isCommend = 1 ");
		SQL.append(" and temp.state = 1 ");
		if (!"-1".equals(columnId.toString().trim()))
			SQL.append(" and temp.enterpriseColumn.id = ").append(columnId).append(" ").toString();
		String date = (new DateHelper()).getCurrentDate().toString();
		if (!"EnterpriseInfo".equals(classObject)) {
			SQL.append(" and temp.initTime <= '").append(date.trim()).append("' ");
			SQL.append(" and temp.endTime >= '").append(date.trim()).append("' ");
		}
		if (!"-1".equals(clickTimes.toString()))
			SQL.append(" order by temp.clickTimes desc");
		if (!"-1".equals(clickTimes.toString()))
			SQL.append(" ,temp.isTop desc");
		else
			SQL.append(" order by temp.isTop desc");

		SQL.append(" ,temp.id desc");

		List result = getHibernateTemplate().find(SQL.toString());
		result = result.subList(0, (int) (size <= result.size() ? size : result.size()));
		return result;
	}

	public List getLinksAndAdByClassAndSize(Class classObject, Long size) {
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		Criteria criteria = session.createCriteria(classObject);
		criteria.add(Restrictions.eq("state", "1"));
		//		criteria.addOrder(Order.desc("id"));
		criteria.setProjection(null);
		criteria.setFirstResult(0);
		criteria.setMaxResults(Integer.valueOf(size.toString()).intValue());
		List result = criteria.list();
		session.close();
		return result;
	}

	public EnterpriseNews getNews(Long id) {
		List find = getHibernateTemplate().find(
			"from EnterpriseNews as e where e.state = 1 and e.isPrimPhoto = 1 and e.enterpriseColumn.id = ?", id);
		if (find.size() > 0)
			return (EnterpriseNews) find.get(0);
		else
			return null;
	}

	public List findNews(Long id) {
		List find = getHibernateTemplate().find(
			"from EnterpriseNews as e where e.state = 1 and e.enterpriseColumn.id = ?", id);
		return find;
	}
}