package cc.messcat.service.collection;

import java.util.List;

import cc.messcat.entity.EnterpriseColumn;
import cc.messcat.entity.EnterpriseNews;
import cc.messcat.gjfeng.common.bean.Pager;

/**
 * @author Messcat
 * @version 1.1
 *
 */
public interface EpNewsManagerDao {

	public Pager findEpNews(int pageSize, int pageNo, String state);

	public Pager findEpNews(int pageSize, int pageNo, EnterpriseNews enterprisenews);

	public EnterpriseNews getEpNews(Long long1);

	public void addEpNews(EnterpriseNews enterprisenews);

	public void updateEpNews(EnterpriseNews enterprisenews);

	public void deleteEpNews(Long id);

	public List findFrontEpNews(Long size, Long long2, String s, String s1, String s2, Long long3, String s3);

	public List findFrontLimitNewsByColumn(Long size, Long father);

	public EnterpriseNews getNewsByClass(Long id);

	public List findNews(Long id);

	public List findEpNewsByFrontNum(Long id, EnterpriseColumn enterprisecolumn);

	public EnterpriseNews getEpNewsByColumnId(Long id);

	public List findNews(String title);

	public List findPhotoNews(Long columnId);
	
	public List findNewsByFaterColumn(Long faterColumn);

}