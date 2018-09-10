package cc.messcat.dao.collection;

import java.util.List;

import cc.messcat.bases.BaseDaoImpl;
import cc.messcat.entity.EnterpriseNews;

/**
 * @author Messcat
 * @version 1.1
 * 
 */
public class EpNewsDaoImpl extends BaseDaoImpl implements EpNewsDao {

	public EpNewsDaoImpl() {
	}

	/**
	 * 删除一条新闻--根据对象
	 */
	public void delete(EnterpriseNews enterpriseNews) {
		try {
			getHibernateTemplate().delete(enterpriseNews);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除一条新闻--根据ID
	 */
	public void delete(Long id) {
		try {
			getHibernateTemplate().delete(get(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查找所有新闻新闻
	 */
	@SuppressWarnings("unchecked")
	public List findAll() {
		List find = getHibernateTemplate().find("from EnterpriseNews");
		return find;
	}

	/**
	 * 查看一条新闻--根据ID
	 */
	public EnterpriseNews get(Long id) {
		return (EnterpriseNews) getHibernateTemplate().get(EnterpriseNews.class, id);
	}

	/**
	 * 添加一条新闻--根据对象
	 */
	public void save(EnterpriseNews enterpriseNews) {
		getHibernateTemplate().save(enterpriseNews);
	}

	/**
	 * 修改一条新闻--根据对象
	 */
	public void update(EnterpriseNews enterpriseNews) {
		getHibernateTemplate().merge(enterpriseNews);
	}

	/**
	 * 查找所有新闻--根据SQL句子
	 */
	@SuppressWarnings("unchecked")
	public List findNewsByWhere(Long size, String where) {
		List ecList = getHibernateTemplate().find(
			(new StringBuffer("from EnterpriseNews ")).append(where).append(" order by editTime DESC ").toString());
		int begin = 0;
		Long tempSize = size;
		/**
		 * 截取一定长度的新闻
		 */
		if (-1 != tempSize) {
			int end = (int) (ecList.size() <= tempSize ? ecList.size() : tempSize);
			ecList = ecList.subList(begin, end);
		}

		return ecList;
	}

	public List findFrontLimitNewsByColumn(Long size, Long father) {
		List ecList = getHibernateTemplate()
			.find(
				"select new EnterpriseNews(id,title,initTime,isPrimPhoto,photo,otherURL) from EnterpriseNews where enterpriseColumn.id = ? and state = 1 order by editTime DESC ",
				father);

		int begin = 0;
		Long tempSize = size;
		/**
		 * 截取一定长度的新闻
		 */
		if (-1 != tempSize) {
			int end = (int) (ecList.size() <= tempSize ? ecList.size() : tempSize);
			ecList = ecList.subList(begin, end);
		}
		return ecList;
	}

	@SuppressWarnings("unchecked")
	public List findNewsByWhere(String where) {
		List ecList = getHibernateTemplate().find(
			(new StringBuffer("from EnterpriseNews ")).append(where).append(" order by editTime DESC ").toString());
		return ecList;
	}
	
	@SuppressWarnings("unchecked")
	public List findNewsByFaterColumn(Long faterColumn) {
		List ecList = getHibernateTemplate().find(
				(new StringBuffer("from EnterpriseNews e")).append(" where e.enterpriseColumn.father =?").append(" order by editTime DESC ").toString(),faterColumn);
			return ecList;
	}

}