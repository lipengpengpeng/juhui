package cc.messcat.gjfeng.dao.product;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import cc.messcat.gjfeng.common.util.BeanUtil;
import cc.messcat.gjfeng.common.util.StringUtil;
import cc.messcat.gjfeng.dao.BaseHibernateDaoImpl;

@Repository("gjfProductCommentDao")
public class GjfProductCommentDaoImpl extends BaseHibernateDaoImpl<Serializable> implements GjfProductCommentDao{

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List getProductCommet(Integer pageNo, Integer pageSize, Long proId,String state) {
		StringBuffer hql=new StringBuffer();
		hql.append("select m.IMG_HEAD_URL_,m.MOBILE_,m.NICK_NAME_,c.ID_,c.CONTENT_,c.COM_IMG_,c.COM_SCORE_,c.COM_TIME_,p.SCORE_ from gjf_product_comment as c,gjf_product_info as p,gjf_member_info as m where ")
		         .append("c.PRODUCT_ID_=p.ID_ and c.MEMBER_ID_=m.ID_ and c.PRODUCT_ID_=:proId");
		if(StringUtil.isNotBlank(state) && Integer.parseInt(state)==1){
			hql.append(" and c.COM_SCORE_<=2");
		}
		/*if(StringUtil.isNotBlank(state) && Integer.parseInt(state)==2){*/
			hql.append(" order by c.COM_TIME_ desc");
//		}
		List list=getCurrentSession().createSQLQuery(hql.toString()).setParameter("proId", proId).setMaxResults(pageSize).setFirstResult((pageNo - 1) * pageSize).list();
		String[] param={"imgHeadUrl","mobile","nickName","productCommentId","content","comImg","comScore","comTime","proScore"};
		return BeanUtil.changeObjectArrayToMaps(list, param);
	}

	@Override
	public Long countProComment(Long proId,String state) {
		Long bd=0L;
		if(StringUtil.isNotBlank(state) && Integer.parseInt(state)==1){
			 bd=(Long) getCurrentSession().createQuery("select count(*) from GjfProductComment as p where p.product.id=:proId").setParameter("proId", proId).uniqueResult();
		}else{
			 bd=(Long) getCurrentSession().createQuery("select count(*) from GjfProductComment as p where p.product.id=:proId and p.comScore<=2").setParameter("proId", proId).uniqueResult();
		}
		
		return bd;
	}

}
