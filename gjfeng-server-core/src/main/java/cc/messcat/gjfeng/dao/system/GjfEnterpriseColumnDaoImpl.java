package cc.messcat.gjfeng.dao.system;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cc.messcat.gjfeng.common.util.BeanUtil;
import cc.messcat.gjfeng.common.vo.app.ResultVo;
import cc.messcat.gjfeng.dao.BaseHibernateDaoImpl;
import cc.messcat.gjfeng.entity.GjfEnterpriseColumn;
import cc.messcat.gjfeng.entity.GjfEnterpriseNews;

@Repository("gjfEnterpriseColumnDao")
public class GjfEnterpriseColumnDaoImpl extends BaseHibernateDaoImpl<Serializable> implements GjfEnterpriseColumnDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<GjfEnterpriseColumn> findIndexColumn(String goodsType, Long pageTypeId) {
		String frontNum=goodsType.equals("0") ? "O2O_SHOP" : "ONLINE_SHOP";
		String hql = "select c from GjfEnterpriseColumn c where c.state='1' and c.typeColumn.id=? and c.isValidInNav='1' and c.father in(";
		hql += "select c1.id from GjfEnterpriseColumn c1 where c1.frontNum=?) order by orderColumn asc";
		return getCurrentSession().createQuery(hql).setParameter(0, pageTypeId).setParameter(1, frontNum).list();
	}
	
	/**
	 * 获取京东首页栏目
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<GjfEnterpriseColumn> findJdIndexColumn(){
		String frontNum="JDSHOP";
		String hql = "select c from GjfEnterpriseColumn c where c.state='1' and c.typeColumn.id=? and c.isValidInNav='1' and c.father in(";
		hql += "select c1.id from GjfEnterpriseColumn c1 where c1.frontNum=?)";
		return getCurrentSession().createQuery(hql).setParameter(0, 5L).setParameter(1, frontNum).list();
	}

	/**
	 * 获取产品供应链栏目
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<GjfEnterpriseColumn> findProductModelColumn(String frontNum, String isValidInNav) {
		
		String hql = "select c from GjfEnterpriseColumn c where c.state='1' and c.typeColumn.id=? and c.isValidInNav=? and c.father in(";
		hql += "select c1.id from GjfEnterpriseColumn c1 where c1.frontNum=?)";
		return getCurrentSession().createQuery(hql).setParameter(0, 5L).setParameter(1, isValidInNav).setParameter(2, frontNum).list();
	}

	/**
	 * 获取产品供应链栏目
	 * @param frontNum
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<GjfEnterpriseNews> findEnterpriseNewsList(Integer pageNo, Integer pageSize, String keyWord,Long columnId) {
		Map<String, Object> parMap = new HashMap<String, Object>();
		StringBuffer hql=new StringBuffer();
		hql.append("from GjfEnterpriseNews where state='1' and enterpriseColumn.id=:columnId");
		parMap.put("columnId", columnId);
		if(BeanUtil.isValid(keyWord)){
			hql.append(" title like: keyWord");			
			parMap.put("keyWord", "%" + keyWord.replace("%", "\\%") + "%");
		} 
		hql.append(" order by initTime desc");
		List<GjfEnterpriseNews> newsList=getCurrentSession().createQuery(hql.toString()).setProperties(parMap)
				                    .setFetchSize((pageNo-1)*pageSize).setMaxResults(pageSize).list();
		return newsList;
	}

}