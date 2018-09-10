package cc.messcat.dao.dynamic;

import java.util.List;

import cc.messcat.bases.BaseDaoImpl;
import cc.messcat.entity.EnterpriseLinks;

/**
 * @author Messcat
 * @version 1.1
 *
 */
public class EpLinksDaoImpl extends BaseDaoImpl implements EpLinksDao {

	public EpLinksDaoImpl() {
	}

	public void delete(EnterpriseLinks enterpriseLinks) {
		getHibernateTemplate().delete(enterpriseLinks);
	}

	public void delete(Long id) {
		getHibernateTemplate().delete(get(id));
	}

	public List findAll() {
		List find = getHibernateTemplate().find("from EnterpriseLinks");
		return find;
	}

	public void save(EnterpriseLinks enterpriseLinks) {
		getHibernateTemplate().save(enterpriseLinks);
	}

	public void update(EnterpriseLinks enterpriseLinks) {
		getSessionFactory().getCurrentSession().clear();
		getHibernateTemplate().update(enterpriseLinks);
	}

	public EnterpriseLinks get(Long id) {
		return (EnterpriseLinks) getHibernateTemplate().get(EnterpriseLinks.class, id);
	}

	//获取所有的友情链接分类
	public List getDistinctFrontNum() {
		return this.getHibernateTemplate().find("SELECT DISTINCT frontNum FROM EnterpriseLinks");
	}

	/**
	 * 获取所有的友情链接根据分类
	 * */
	public List findAllByFrontNum(String frontNum) {
		return this.getHibernateTemplate().find("FROM EnterpriseLinks where frontNum =? ORDER BY orderColumn ", frontNum);
	}
}
