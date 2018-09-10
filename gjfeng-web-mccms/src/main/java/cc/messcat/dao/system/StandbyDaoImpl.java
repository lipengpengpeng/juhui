package cc.messcat.dao.system;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;

import cc.messcat.bases.BaseDaoImpl;
import cc.messcat.entity.Standby;
import cc.messcat.gjfeng.common.bean.Pager;

public class StandbyDaoImpl extends BaseDaoImpl implements StandbyDao {

	public void save(Standby standby) {
		getHibernateTemplate().save(standby);
	}
	
	public void update(Standby standby) {
		getHibernateTemplate().update(standby);
	}
	
	public void delete(Standby standby) {
		getHibernateTemplate().delete(standby);
	}

	public void delete(Long id) {
		getHibernateTemplate().delete(this.get(id));
	}
	
	public Standby get(Long id) {
		return (Standby) getHibernateTemplate().get(Standby.class, id);
	}

	@SuppressWarnings("unchecked")
	public List findAll() {
		return getHibernateTemplate().find("from Standby");
	}

	@SuppressWarnings("unchecked")
	public Pager getPager(int pageSize, int pageNo) {
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(Standby.class);
		int rowCount = ((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
		criteria.setProjection(null);
		int startIndex = pageSize * (pageNo - 1);
		criteria.setFirstResult(startIndex);
		criteria.setMaxResults(pageSize);
		List result = criteria.list();
		
		return new Pager(pageSize, pageNo, rowCount, result);
	}

	public Standby getStandbyById(Long id) {
		return (Standby)getHibernateTemplate().get(Standby.class, id);
	}

	public Standby getFirstEntity(){
		
		Standby standby = null;
		
		List list = this.getHibernateTemplate().find("from Standby");
		if(list.size()>0)
			standby = (Standby)list.get(0);
		return standby;
		
	}

}