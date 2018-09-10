package cc.messcat.dao.column;

import java.util.List;

import cc.messcat.entity.EnterpriseColumn;

/**
 * @author Messcat
 * @version 1.1
 */
public interface EpColumnDao {

	public EnterpriseColumn get(Long id);

	public void save(EnterpriseColumn enterpriseColumn);

	public void update(EnterpriseColumn enterpriseColumn);

	public void delete(EnterpriseColumn enterpriseColumn);

	public void delete(Long id);

	public List findAll();

	public List findByFatherAndState(Long father, String state, String order);

	public List<EnterpriseColumn> findSubColumn(Long father);

	public List findAllColumn();

	public boolean isNameUnique(String names, Long father);

	public EnterpriseColumn getColumnByFrontNum(String s);

	public List getColumnByFrontNumNotNull();

	public List findAjaxByFatherAndState(Long father, String state, String order);

	// 根据父级栏目获取最大栏目排序
	public Long getMaxOrderByFather(Long father);

	// 根据模板类型查找栏目数量
	public Long findByPageTypeId(Long id);

	public List findColumnByIds(Long[] ids);

	// 由上级id 查二三级类别
	public List findColumnByFatherId(Long id);

	/**
	 * 查找栏目类型为产品的栏目
	 * @return
	 */
	public List<EnterpriseColumn> findProductColumn();
	
	/**
	 * 查询父Id
	 * @param id
	 * @return
	 */
	public EnterpriseColumn findFatherId(Long id);
}