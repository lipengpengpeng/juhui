package cc.messcat.dao.system;

import java.util.List;

import cc.messcat.bases.BaseDaoImpl;
import cc.messcat.entity.Authorities;

public class AuthoritiesDaoImpl extends BaseDaoImpl implements AuthoritiesDao {

	public AuthoritiesDaoImpl() {
	}

	public void delete(Authorities authorities) {
		getHibernateTemplate().delete(authorities);
	}

	public void delete(Long id) {
		getHibernateTemplate().delete(get(id));
	}

	public List findAll() {
		List find = getHibernateTemplate().find("from Authorities");
		return find;
	}

	public Authorities get(Long id) {
		return (Authorities) getHibernateTemplate().get(Authorities.class, id);
	}

	public void save(Authorities authorities) {
		getHibernateTemplate().save(authorities);
	}

	public void update(Authorities authorities) {
		//this.getSessionFactory().getCurrentSession().clear();
		getHibernateTemplate().merge(authorities);
	}

	public boolean isNameUnique(String name) {
		List temp = getHibernateTemplate().find("from Authorities where name = ?", name.trim());
		return temp.size() <= 0;
	}

	public Authorities getByName(String name) {
		List temp = getHibernateTemplate().find("from Authorities where name = ?", name.trim());
		if (temp.size() > 0) {
			return (Authorities) temp.get(0);
		}
		return null;
	}

	public boolean isDisplayNameUnique(String displayName) {
		List temp = getHibernateTemplate().find("from Authorities where displayName = ?", displayName.trim());
		return temp.size() <= 0;
	}

	public List findAuthoritiesByTypeId(Long id) {
		List temp = getHibernateTemplate().find("from Authorities where authoritiesId = ?", id);
		return temp;
	}
	
	/**
	 * 根据权限类型查询
	 */
	@SuppressWarnings("unchecked")
	public List<Authorities> findAuthoritiesByType(String type) {
		List<Authorities> temp = getHibernateTemplate().find("from Authorities where authoritiesType = ? order by authoritiesId,id", type);
		return temp;
	}
	
	/**
	 * 根据权限类型查询除某个ID外的权限列表
	 */
	@SuppressWarnings("unchecked")
	public List<Authorities> findAuthoritiesByTypeAndId(String type, Long id) {
		List<Authorities> temp = getHibernateTemplate().find("from Authorities where authoritiesType = ? and id != " + id + " order by authoritiesId,id", type);
		return temp;
	}

	/**
	 * 根据显示名称查询
	 */
	public Authorities getAuthByDisplayName(String displayName) {
		List temp = getHibernateTemplate().find("from Authorities where displayName = ?", displayName.trim());
		if (temp.size() > 0) {
			return (Authorities) temp.get(0);
		}
		return null;
	}
	
	/**
	 * 根据权限名与权限类型查找权限
	 */
	public Authorities getByNameAndType(String name, String type) {
		List temp = getHibernateTemplate().find("from Authorities where name = ? and authoritiesType = " + type, name.trim());
		if (temp.size() > 0) {
			return (Authorities) temp.get(0);
		}
		return null;
	}
}