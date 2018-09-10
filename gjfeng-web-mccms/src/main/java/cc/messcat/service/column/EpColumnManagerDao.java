package cc.messcat.service.column;

import java.util.List;

import cc.messcat.entity.Authorities;
import cc.messcat.entity.EnterpriseColumn;

/**
 * @author Messcat
 * @version 1.1
 */
public interface EpColumnManagerDao {

	public List findEnterpriseColumn();

	public List findAllEnterpriseColumn();

	public EnterpriseColumn getEnterpriseColumn(Long id);

	public void saveEnterpriseColumn(EnterpriseColumn enterprisecolumn);

	public void updateEnterpriseColumn(EnterpriseColumn enterprisecolumn, Authorities auth);

	public void deleteEnterpriseColumn(Long id);

	public List<EnterpriseColumn> findSubColumn(Long father);

	public boolean isNameUnique(String names, String orgName, Long father);

	public EnterpriseColumn getEnterpriseColumn(String state);

	public List findFrontInfoFrontNumNotNull();

	public List findTreeByFather(Long father);

	// 判断是否叶节点
	public boolean isLeafNode(Long columnId);

	// 根据父级栏目查找出本次新增的栏目排序
	public Long getOrderColumnByFather(Long father);

	public List getColumnByIds(Long[] ids);

	public List findColumnByFatherId(Long id);
	
	public List<EnterpriseColumn> findProductColumn();
	
	/**
	 * 查询最顶级父栏目
	 * @param id
	 * @return
	 */
	public EnterpriseColumn findFatherByid(Long id);
	
	
}