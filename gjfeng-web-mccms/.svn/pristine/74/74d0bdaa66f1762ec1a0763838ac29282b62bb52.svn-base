package cc.messcat.dao.system;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;

import cc.messcat.bases.BaseDaoImpl;
import cc.messcat.entity.McParameter;
import cc.messcat.gjfeng.common.bean.Pager;

public class McParameterDaoImpl extends BaseDaoImpl implements McParameterDao {

	public void save(McParameter mcParameter) {
		getHibernateTemplate().save(mcParameter);
	}
	
	public void update(McParameter mcParameter) {
		getHibernateTemplate().update(mcParameter);
	}
	
	public void delete(McParameter mcParameter) {
		getHibernateTemplate().delete(mcParameter);
	}

	public void delete(Long id) {
		getHibernateTemplate().delete(this.get(id));
	}
	
	public McParameter get(Long id) {
		return (McParameter) getHibernateTemplate().get(McParameter.class, id);
	}

	@SuppressWarnings("unchecked")
	public List findAll() {
		return getHibernateTemplate().find("from McParameter");
	}

	@SuppressWarnings("unchecked")
	public Pager getPager(int pageSize, int pageNo) {
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(McParameter.class);
		int rowCount = ((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
		criteria.setProjection(null);
		int startIndex = pageSize * (pageNo - 1);
		criteria.setFirstResult(startIndex);
		criteria.setMaxResults(pageSize);
		List result = criteria.list();
		
		return new Pager(pageSize, pageNo, rowCount, result);
	}

	public List<McParameter> findAllWorkOkParameter() {
		return (List<McParameter>)getHibernateTemplate().find("from McParameter where wr_ok=1 order by no_order");
	}


}