package cc.messcat.dao.system;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import cc.messcat.bases.BaseDaoImpl;
import cc.messcat.entity.Users;
import cc.messcat.gjfeng.common.bean.Pager;

// Referenced classes of package org.stnet.dao.enterprice.system:
//            UsersDao

public class UsersDaoImpl extends BaseDaoImpl implements UsersDao {

	public UsersDaoImpl() {
	}

	public Users get(String loginName) {
		List users = getHibernateTemplate().find("from Users as u where u.loginName = ?", loginName);
		if (users.size() > 0)
			return (Users) users.get(0);
		else
			return null;
	}

	public Users get(String username, String password) {
		List find = getHibernateTemplate().find("from Users where loginName = ? and password = ? and state = 1",
			new String[] { username, password });
		if (find.size() > 0)
			return (Users) find.get(0);
		else
			return null;
	}

	public void delete(Users users) {
		getHibernateTemplate().delete(users);
	}

	public void delete(Long id) {
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		StringBuffer sql = new StringBuffer();
		sql.append("delete Users where id=" + id);
		Query query = session.createQuery(sql.toString());
		query.executeUpdate();
	}

	public List findAll() {
		List find = getHibernateTemplate().find("from Users Order by id");
		getSessionFactory().getCurrentSession().flush();
		return find;
	}

	public Users get(Long id) {
		return (Users) getHibernateTemplate().get(Users.class, id);
	}

	public void save(Users users) {
		getHibernateTemplate().save(users);
	}

	public void update(Users users) {
		getSessionFactory().getCurrentSession().clear();
		getHibernateTemplate().saveOrUpdate(users);
	}

	public boolean isNameUnique(String name) {
		List temp = getHibernateTemplate().find("from Users where loginName = ?", name.trim());
		getSessionFactory().getCurrentSession().flush();
		return temp.size() <= 0;
	}

	/*
	 * 获取所有的Users
	 */
	public Pager getAllUsers(int pageSize, int pageNo, Class classObject, String state, String area) {
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(classObject);
		if (!"-1".equals(state))
			criteria.add(Restrictions.eq("state", state));
		if (!"-1".equals(area)) {
			criteria.add(Restrictions.eq("area", area));
			//			List<Integer> values =new ArrayList();
			//			values.add(4);
			//			criteria.add(Restrictions.in("usersRoleses",values));
		}
		criteria.addOrder(Order.asc("id"));
		int rowCount = ((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
		criteria.setProjection(null);
		int startIndex = pageSize * (pageNo - 1);
		criteria.setFirstResult(startIndex);
		criteria.setMaxResults(pageSize);
		List result = criteria.list();
		

		return new Pager(pageSize, pageNo, rowCount, result);
	}

	public Users getUserByUnit(String area) {
		List temp = getHibernateTemplate().find("from Users where area = ?", area.trim());
		getSessionFactory().getCurrentSession().flush();
		if (temp.size() > 0)
			return (Users) temp.get(0);
		return null;
	}
}