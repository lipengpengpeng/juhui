package cc.messcat.dao.data;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;

import cc.messcat.bases.BaseDaoImpl;
import cc.messcat.entity.BackupHistory;
import cc.messcat.gjfeng.common.bean.Pager;

public class DataHandlerDaoImpl extends BaseDaoImpl implements DataHandlerDao {

	public BackupHistory get(Long id) {
		return (BackupHistory) getHibernateTemplate().get(BackupHistory.class, id);
	}

	public void delete(BackupHistory backupHistory) {
		getHibernateTemplate().delete(backupHistory);
	}

	public void delete(Long id) {
		getHibernateTemplate().delete(this.get(id));
	}

	public void save(BackupHistory backupHistory) {
		getHibernateTemplate().save(backupHistory);
	}

	public void update(BackupHistory backupHistory) {
		getHibernateTemplate().update(backupHistory);
	}

	@SuppressWarnings("unchecked")
	public Pager getBackupHistoryPager(int pageSize, int pageNo) {

		final Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		final Criteria criteria = session.createCriteria(BackupHistory.class);
		final int rowCount = ((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
		criteria.setProjection(null);
		criteria.addOrder(Order.desc("dateTime"));
		final int startIndex = pageSize * (pageNo - 1);
		criteria.setFirstResult(startIndex);
		criteria.setMaxResults(pageSize);
		final List result = criteria.list();
		

		return new Pager(pageSize, pageNo, rowCount, result);

	}

}
