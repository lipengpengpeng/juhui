package cc.messcat.gjfeng.dao.product;

import java.io.Serializable;
import java.util.List;

import cc.messcat.gjfeng.dao.BaseHibernateDao;

public interface GjfProductCommentDao extends BaseHibernateDao<Serializable>{
	
	@SuppressWarnings("rawtypes")
	public List getProductCommet(Integer pageNo,Integer pageSize,Long proId,String state);
	
	/**
	 * 统计商品评论数
	 * @param proId
	 * @return
	 */
	public Long countProComment(Long proId,String state);


}
