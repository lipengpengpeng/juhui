package cc.messcat.gjfeng.dao.product;

import java.io.Serializable;
import java.util.List;

import cc.messcat.gjfeng.dao.BaseHibernateDao;
import cc.messcat.gjfeng.entity.GjfProductAttr;

public interface GjfProductAttrDao extends BaseHibernateDao<Serializable>{

	/**
	 * @描述 根据商品id查询商品属性
	 * @author Karhs
	 * @date 2017年1月17日
	 * @param proId
	 * @return
	 */
	public List<GjfProductAttr> findProAttrByProId(Long proId);
	
	/**
	 * @描述 根据商品id和商品属性查询商品属性值
	 * @author Karhs
	 * @date 2017年1月17日
	 * @param proId
	 * @param attrId
	 * @return
	 */
	public List<GjfProductAttr> findProAttrValueByAttrId(Long proId,Long attrId);
}
