package cc.messcat.dao.collection;

import java.util.List;

import cc.messcat.entity.EnterpriseNews;
import cc.messcat.gjfeng.common.bean.Pager;

/**
 * @author Messcat
 * @version 1.1
 *
 */
public interface EpNewsDao {

	public EnterpriseNews get(Long id);

	public void save(EnterpriseNews enterprisenews);

	public void update(EnterpriseNews enterprisenews);

	public void delete(EnterpriseNews enterprisenews);

	public void delete(Long id);

	public List findAll();

	public Pager getObjectListByClass(int i, int j, Class class1, String s);

	public List findNewsByWhere(String where);

	public List getInfoByClassAndSize(String s, Long long1, Long long2, String s1, String s2, String s3, Long long3, String s4);

	public List findFrontLimitNewsByColumn(Long size, Long father);

	public EnterpriseNews getNews(Long long1);

	public List findNews(Long long1);

	public List findNewsByWhere(Long size, String where);
	
	public List findNewsByFaterColumn(Long faterColumn);
}