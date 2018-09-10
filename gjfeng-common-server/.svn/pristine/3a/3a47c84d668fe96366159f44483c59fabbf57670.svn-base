/*
 * Copyright (c) 2015 Messcat. All rights reserved.
 * 
 */
package cc.messcat.gjfeng.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BaseHibernateDao<T extends Serializable> {

	T get(final long id);

	List<T> getAll();

	void save(final T entity);

	T update(final T entity);

	void delete(final T entity);

	void deleteById(final long entityId);
	
	/**
	 * 修改对象
	 */
	public void updateObj(Object obj);

	/**
	 * 根据ID删除对象
	 */
	public void delete(Long id, String objName);

	/**
	 * 根据ID查找对象
	 */
	public Object get(Long id, String objName);

	/**
	 * 查找所有对象
	 */
	public List getAll(String objName);

	/**
	 * 根据hql查询对象
	 */
	public List find(String hql);

	/**
	 * @param objName
	 * @param status
	 * @return
	 */
	public List findAllByStatus(String objName, String status);

	/**
	 * 批量修改记录
	 * 
	 * @param entityClass
	 * @param propNames
	 * @param attrs
	 */
	public int update(Class entityClass, Map<String, Object> props, Map<String, Object> attrs);

	/**
	 * 删除对象
	 * 
	 * @param entity
	 */
	public void delete(Object entity);

	/**
	 * 根据entityClass查询最大id
	 * 
	 * @param entityClass
	 * @return
	 */
	public int queryMaxId(Class entityClass);

	/**
	 * 根据entityClass及attrs属性删除记录
	 * 
	 * @param entityClass
	 * @param attrs
	 * @return
	 */
	public int delete(Class entityClass, Map<String, Object> attrs);

	/**
	 * 根据entityClass及attrs属性or逻辑或删除记录
	 * 
	 * @param entityClass
	 * @param attrs
	 * @return
	 */
	public int deleteOr(Class entityClass, Map<String, Object> attrs);

	/**
	 * 根据entityClass及ids查询记录
	 * 
	 * @param entityClass
	 * @param ids
	 * @return
	 */
	public <T> List<T> queryList(Class entityClass, String ids);

	/**
	 * 根据entityClass及不属于ids的记录
	 * 
	 * @param entityClass
	 * @param ids
	 * @return
	 */
	public <T> List<T> queryNotInList(Class entityClass, String ids);

	/**
	 * 根据entityClass及attrs属性查询单个对象
	 * 
	 * @param entityClass
	 * @param attrs
	 * @return
	 */
	public <T> T query(Class<T> entityClass, Map<String, Object> attrs);

	/**
	 * 根据entityClass及attrs属性模糊查询单个对象
	 * 
	 * @param entityClass
	 * @param attrs
	 * @return
	 */
	public <T> T query(Class<T> entityClass, String likeAttr, String likeValue, Map<String, Object>... attrs);

	/**
	 * 根据entityClass及attrs属性统计记录条数
	 * 
	 * @param entityClass
	 * @param attrs
	 * @return
	 */
	public long queryTotalRecord(Class entityClass, Map<String, Object>... attrs);
	
	/**
	 * 根据entityClass及attrs属性统计记录条数或者加根据分组统计
	 * @param entityClass
	 * @param isGroup	是否分组 0：否 1：是
	 * @param likeAttr
	 * @param likeValue
	 * @param groupAttr
	 * @param attrs
	 * @return
	 */
	public long queryTotalRecord(Class entityClass,String isGroup,String likeKey, String likeValue,String groupKey, Map<String, Object>... attrs);

	/**
	 * 根据entityClass按模糊查询统计记录条数 
	 * 
	 * @param entityClass
	 * @param attrs
	 * @return
	 */
	public long queryTotalRecord(Class entityClass, String likeKey, String likeValue, Map<String, Object>... attrs);

	/**
	 * 根据entityClass及attrs属性查询指定页面记录,支持分页
	 */
	public <T> List<T> queryList(Class<T> entityClass, int pageStart, int pageSize, String orderAttr, String orderType,
		Map<String, Object>... attrs);

	/**
	 * 根据entityClass查询指定页面记录,支持分页及模糊查询
	 */
	public <T> List<T> queryList2(Class<T> entityClass, int pageStart, int pageSize, String orderAttr, String orderType,
		String likeAttr, String likeValue,Map<String, Object>... attrs);

	/**
	 * 根据entityClass及attrs属性查询记录
	 */
	public <T> List<T> queryList(Class<T> entityClass, String orderAttr, String orderType, Map<String, Object>... attrs);

	/**
	 * 根据entityClass及attrs属性以or逻辑或关系查询记录
	 */
	public <T> List<T> queryOrList(Class<T> entityClass, String orderAttr, String orderType, Map<String, Object>... attrs);

	/**
	 * 根据entityClass及attrs属性查询记录，支持分组
	 */
	public <T> List<T> queryList(Class<T> entityClass, String orderAttr, String orderType, String groupAttr,
		Map<String, Object>... attrs);
	
	/**
	 * 根据entityClass及attrs属性查询记录，支持分组分页
	 */
	public <T> List<T> queryList(Class<T> entityClass, int pageStart, int pageSize,String orderAttr, String orderType, String groupAttr,
		Map<String, Object>... attrs);
	
	/**
	 * 根据entityClass及attrs属性查询记录，支持分组分页和根据sum/count等进行排序
	 */
	public <T> List<T> queryListByType(Class<T> entityClass, int pageStart, int pageSize,String type,String orderAttr, String orderType, String groupAttr,
		Map<String, Object>... attrs);

	/**
	 * 模糊查询
	 */
	public <T> List<T> likeQueryList(Class<T> entityClass, String orderAttr, String orderType, String likeAttr, String likeValue,
		int... limit);

}
