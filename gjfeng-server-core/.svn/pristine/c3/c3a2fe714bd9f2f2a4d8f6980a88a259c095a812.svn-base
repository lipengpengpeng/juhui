package cc.messcat.gjfeng.dao.product;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import cc.messcat.gjfeng.dao.BaseHibernateDaoImpl;
import cc.messcat.gjfeng.entity.GjfProductAttr;

@Repository("gjfProductAttrDao")
public class GjfProductAttrDaoImpl extends BaseHibernateDaoImpl<Serializable> implements GjfProductAttrDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<GjfProductAttr> findProAttrByProId(Long proId) {
		String hql = "select ga from GjfProductAttr ga where ga.productId.id=? group by ga.attrValueId.attrId.id order by ga.orderBy asc";
		return getCurrentSession().createQuery(hql).setParameter(0, proId).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GjfProductAttr> findProAttrValueByAttrId(Long proId, Long attrId) {
		String hql = "select ga from GjfProductAttr ga where ga.productId.id=? and ga.attrValueId.attrId.id=? group by ga.attrValueId.id order by ga.orderBy asc";
		return getCurrentSession().createQuery(hql).setParameter(0, proId).setParameter(1, attrId).list();
	}
	
}
