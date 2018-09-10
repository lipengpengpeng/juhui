package cc.messcat.service.collection;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import cc.messcat.bases.BaseManagerDaoImpl;
import cc.messcat.entity.EnterpriseColumn;
import cc.messcat.entity.EnterpriseNews;
import cc.messcat.entity.Users;
import cc.messcat.gjfeng.common.bean.Pager;
import cc.messcat.web.left.Authorize;

/**
 * @author Messcat
 * @version 1.1
 * 
 */
public class EpNewsManagerDaoImpl extends BaseManagerDaoImpl implements EpNewsManagerDao {

	/**
	 * 添加新闻
	 */
	public void addEpNews(EnterpriseNews enterpriseNews) {
		Long userId = (Long) ServletActionContext.getContext().getSession().get("userID");
		Long columnId = enterpriseNews.getEnterpriseColumn().getId();
		if (enterpriseNews.getInitTime() == null || "".equals(enterpriseNews.getInitTime().toString()))
			enterpriseNews.setInitTime(new Date());
		if (enterpriseNews.getEndTime() == null || "".equals(enterpriseNews.getEndTime().toString())) {
			Date d = new Date();
			d.setDate(d.getDate() + 3650);
			enterpriseNews.setEndTime(d);
		}
		enterpriseNews.setUsers(usersDao.get(userId));
		enterpriseNews.setEnterpriseColumn(epColumnDao.get(columnId));
		epNewsDao.save(enterpriseNews);
	}

	/**
	 * 删除新闻 根据ID
	 */
	public void deleteEpNews(Long id) {
		epNewsDao.delete(id);
	}

	public Pager findEpNews(int pageSize, int pageNo, String state) {
		Pager pager = epNewsDao.getObjectListByClass(pageSize, pageNo, EnterpriseNews.class, state);
		return pager(pager);
	}

	public Pager findEpNews(int pageSize, int pageNo, EnterpriseNews enterpriseNews) {
		StringBuffer sb = new StringBuffer(" where 1 = 1 ");

		if (enterpriseNews.getState() != null && !"-1".equals(enterpriseNews.getState()))
			sb.append(" and state = ").append(enterpriseNews.getState().toString()).append(" ").toString();

		List result = null;

		if (enterpriseNews.getEnterpriseColumn() != null && "0".equals(enterpriseNews.getEnterpriseColumn().getId().toString())) {

			Authorize isAuthorize = new Authorize();

			List<EnterpriseColumn> columnList = this.epColumnDao.findAll();

			// 初始化tree
			Map map = new HashMap();
			sb.append(" and (enterpriseColumn.id = 0 ");

			for (EnterpriseColumn ec : columnList) {

				if (isAuthorize.isAuthorize(ec.getNames()) || (map.get(ec.getFather().toString()) != null)) {

					if (ec.getFather() == 0) {
						map.put(String.valueOf(ec.getId()), "true");
					}
					sb.append(" or enterpriseColumn.id = ").append(ec.getId()).append(" ");
				}
			}
			sb.append(" )");

		} else if (enterpriseNews.getEnterpriseColumn() != null
			&& !"-1".equals(enterpriseNews.getEnterpriseColumn().getId().toString())){
			sb.append(" and (enterpriseColumn.id = ").append(enterpriseNews.getEnterpriseColumn().getId().toString());
			
			List<EnterpriseColumn> tempColumn = this.epColumnDao.findSubColumn(enterpriseNews.getEnterpriseColumn().getId());

			EnterpriseColumn e;
			Iterator iterator = tempColumn.iterator();

			while (iterator.hasNext()) {
				e = (EnterpriseColumn) iterator.next();
				sb.append(" or enterpriseColumn.id = ").append(e.getId());
			}
			
			sb.append(")");
		}

		if (enterpriseNews.getTitle() != null && !"".equals(enterpriseNews.getTitle()))
			sb.append(" and title like '%").append(enterpriseNews.getTitle().trim().toString()).append("%'");
		
		if (enterpriseNews.getIsPrimPhoto() != null && !"".equals(enterpriseNews.getIsPrimPhoto()))
			sb.append(" and isPrimPhoto = '").append(enterpriseNews.getIsPrimPhoto().trim().toString()).append("'");

		result = this.epNewsDao.findNewsByWhere(sb.toString());

		int rowCount = result.size();

		if (rowCount < pageSize)
			pageNo = 1;
		int startIndex = pageSize * (pageNo - 1);

		result = result.subList(startIndex, (pageSize + startIndex) <= result.size() ? (pageSize + startIndex) : result.size());

		return new Pager(pageSize, pageNo, rowCount, result);
	}

	public Pager pager(Pager pager) {

		List newsList = pager.getResultList();

		Map<String, Users> userMap = new HashMap<String, Users>();
		Map<String, EnterpriseColumn> enterpriseColumnMap = new HashMap<String, EnterpriseColumn>();
		Long userId = null;
		Long newId = null;
		Users userTemp = null;
		EnterpriseColumn enterpriseColumnTemp = null;
		EnterpriseNews en = null;
		for (Iterator iterator = newsList.iterator(); iterator.hasNext();) {
			en = (EnterpriseNews) iterator.next();
			userId = null;

			if (en.getUsers() != null && en.getUsers().getId() != null) {
				userId = en.getUsers().getId();
				userTemp = null;
				if (userMap.get(userId.toString()) != null) {
					userTemp = userMap.get(userId.toString());
				} else {
					userTemp = usersDao.get(userId);
					userMap.put(userId.toString(), userTemp);
				}
				en.setUsers(userTemp);
			}

			if (en.getEnterpriseColumn() != null && en.getEnterpriseColumn().getId() != null) {
				newId = null;
				newId = en.getEnterpriseColumn().getId();

				if (enterpriseColumnMap.get(newId.toString()) != null) {
					enterpriseColumnTemp = enterpriseColumnMap.get(newId.toString());
				} else {
					enterpriseColumnTemp = epColumnDao.get(newId);
					enterpriseColumnMap.put(newId.toString(), enterpriseColumnTemp);
				}
				en.setEnterpriseColumn(enterpriseColumnTemp);
			}
		}
		userMap = null;
		enterpriseColumnMap = null;
		return pager;
	}

	/**
	 * 查看新闻 根据ID
	 */
	public EnterpriseNews getEpNews(Long id) {
		EnterpriseNews enterpriseNews = epNewsDao.get(id);
		if (enterpriseNews != null) {
			if (enterpriseNews.getUsers() != null && enterpriseNews.getUsers().getId() != null) {
				enterpriseNews.setUsers(usersDao.get(enterpriseNews.getUsers().getId()));
			}

			if (enterpriseNews.getEnterpriseColumn() != null && enterpriseNews.getEnterpriseColumn().getId() != null) {
				enterpriseNews.setEnterpriseColumn(epColumnDao.get(enterpriseNews.getEnterpriseColumn().getId()));
			}
		}

		return enterpriseNews;
	}

	/**
	 * 修改新闻 根据ID
	 */
	public void updateEpNews(EnterpriseNews enterpriseNews) {
		Long userId = (Long) ServletActionContext.getContext().getSession().get("userID");
		Long columnId = enterpriseNews.getEnterpriseColumn().getId();

		if (enterpriseNews.getInitTime() == null || "".equals(enterpriseNews.getInitTime().toString()))
			enterpriseNews.setInitTime(new Date());
		if (enterpriseNews.getEndTime() == null || "".equals(enterpriseNews.getEndTime().toString())) {
			Date d = new Date();
			d.setDate(d.getDate() + 3650);
			enterpriseNews.setEndTime(d);
		}

		enterpriseNews.setUsers(usersDao.get(userId));
		enterpriseNews.setEnterpriseColumn(epColumnDao.get(columnId));
		epNewsDao.update(enterpriseNews);
	}

	public List findFrontEpNews(Long size, Long clickTimes, String isprimPhoto, String isCommend, String b_or_s, Long columnId,
		String isIndexPhoto) {
		List enterpriseNewsList = epNewsDao.getInfoByClassAndSize("EnterpriseNews", size, clickTimes, isprimPhoto, isCommend,
			b_or_s, columnId, isIndexPhoto);
		return enterpriseNewsList;
	}

	public List findFrontLimitNewsByColumn(Long size, Long father) {
		return epNewsDao.findFrontLimitNewsByColumn(size, father);
	}

	public EnterpriseNews getNewsByClass(Long id) {
		return epNewsDao.getNews(id);
	}

	public List findNews(Long id) {
		return epNewsDao.findNews(id);
	}

	/**
	 * 根据title模糊查询出新闻列表
	 * 
	 * @param title
	 * @return
	 */
	public List findNews(String title) {
		return this.epNewsDao.findNewsByWhere(20L, " where 1 = 1 and title like '%" + title + "%'");
	}

	/**
	 * 根据collumn查询出图片新闻列表
	 * 
	 * @param collumn
	 * @return
	 */
	public List findPhotoNews(Long columnId) {
		return this.epNewsDao.findNewsByWhere(5L, " where enterpriseColumn.id = " + columnId + " and isPrimPhoto = 1 ");
	}

	public List findEpNewsByFrontNum(Long size, EnterpriseColumn ec) {
		List temp = epColumnDao.findByFatherAndState(ec.getId(), "1", "orderColumn");
		StringBuffer sql = new StringBuffer(" where 1 = 1 ");
		sql.append(" and enterpriseColumn.id = ").append(ec.getId());
		for (Iterator iterator = temp.iterator(); iterator.hasNext();) {
			EnterpriseColumn e = (EnterpriseColumn) iterator.next();
			sql.append(" or enterpriseColumn.id = ").append(e.getId());
		}

		List newsList = epNewsDao.findNewsByWhere(size, sql.toString());
		if (newsList != null) {
			EnterpriseNews news;
			Iterator iterator1 = newsList.iterator();
			while (iterator1.hasNext()) {
				news = (EnterpriseNews) iterator1.next();
				if (news.getUsers() != null && news.getUsers().getId() != null)
					news.setUsers(usersDao.get(news.getUsers().getId()));
				if (news.getEnterpriseColumn() != null && news.getEnterpriseColumn().getId() != null)
					news.setEnterpriseColumn(epColumnDao.get(news.getEnterpriseColumn().getId()));
			}
		}
		return newsList;
	}

	public EnterpriseNews getEpNewsByColumnId(Long columnId) {
		StringBuffer SQL = new StringBuffer(" where 1 = 1 ");
		SQL = SQL.append(" and enterpriseColumn.id = ").append(columnId);
		List temp = epNewsDao.findNewsByWhere(1L, SQL.toString());

		if (temp != null && temp.size() > 0)
			return (EnterpriseNews) temp.get(0);
		else
			return null;
	}

	public List findEpNews() {
		return epNewsDao.findAll();
	}
	
	public List findNewsByFaterColumn(Long faterColumn) {
		return epNewsDao.findNewsByFaterColumn(faterColumn);
	}

}