package cc.messcat.gjfeng.dao.system;

import java.io.Serializable;
import java.util.List;

import cc.messcat.gjfeng.common.vo.app.ResultVo;
import cc.messcat.gjfeng.dao.BaseHibernateDao;
import cc.messcat.gjfeng.entity.GjfEnterpriseColumn;
import cc.messcat.gjfeng.entity.GjfEnterpriseNews;

/**
 * @author Karhs
 * @version 1.1
 */
public interface GjfEnterpriseColumnDao extends BaseHibernateDao<Serializable>{
	
	/**
	 * @描述 查询商品栏目
	 * @author Karhs
	 * @date 2017年1月6日
	 * @param goodsType 0：O2O 1：shop
	 * @return
	 */
	public List<GjfEnterpriseColumn> findIndexColumn(String goodsType,Long pageTypeId);
	
	/**
	 * 获取京东首页栏目
	 * @return
	 */
	public List<GjfEnterpriseColumn> findJdIndexColumn();
	
	/**
	 * 获取产品供应链栏目
	 * @param frontNum
	 * @return
	 */
	public List<GjfEnterpriseColumn> findProductModelColumn(String frontNum,String isValidInNav);
	
	/**
	 * 获取产品供应链栏目
	 * @param frontNum
	 * @return
	 */
	public List<GjfEnterpriseNews>  findEnterpriseNewsList(Integer pageNo,Integer pageSize,String keyWord,Long columnId);
	
}