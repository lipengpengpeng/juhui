package cc.messcat.service.dynamic;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import cc.messcat.bases.BaseManagerDaoImpl;
import cc.messcat.entity.EnterpriseAd;
import cc.messcat.gjfeng.common.bean.Pager;

/**
 * @author Messcat
 * @version 1.1
 *
 */
public class EpAdManagerDaoImpl extends BaseManagerDaoImpl implements EpAdManagerDao {

	public EpAdManagerDaoImpl() {
	}

	public Pager findEnterpriseAd(int pageSize, int pageNo, String statu) {
		Pager pager = epAdDao.getObjectListByClass(pageSize, pageNo, EnterpriseAd.class, statu);
		List adList = pager.getResultList();
		for (Iterator iterator = adList.iterator(); iterator.hasNext();) {
			EnterpriseAd enterpriseAd = (EnterpriseAd) iterator.next();
			if (enterpriseAd.getUsers() != null && enterpriseAd.getUsers().getId() != null)
				enterpriseAd.setUsers(usersDao.get(enterpriseAd.getUsers().getId()));
		}

		return pager;
	}

	public void addEnterpriseAd(EnterpriseAd enterpriseAd) {
		Long userId = (Long) ServletActionContext.getContext().getSession().get("userID");
		enterpriseAd.setUsers(usersDao.get(userId));
		if (enterpriseAd.getInitTime() == null || "".equals(enterpriseAd.getInitTime().toString()))
			enterpriseAd.setInitTime(new Date());
		if (enterpriseAd.getEndTime() == null || "".equals(enterpriseAd.getEndTime().toString())) {
			Date d = new Date();
			d.setDate(d.getDate() + 3650);
			enterpriseAd.setEndTime(d);
		}
		epAdDao.save(enterpriseAd);
	}

	public void deleteEnterpriseAd(Long id) {
		epAdDao.delete(id);
	}

	public EnterpriseAd getEnterpriseAd(Long id) {
		EnterpriseAd temp = epAdDao.get(id);
		temp.setUsers(usersDao.get(temp.getUsers().getId()));
		return temp;
	}

	public void updateEnterpriseAd(EnterpriseAd enterpriseAd) {
		Long userId = (Long) ServletActionContext.getContext().getSession().get("userID");
		enterpriseAd.setUsers(usersDao.get(userId));
		if (enterpriseAd.getInitTime() == null || "".equals(enterpriseAd.getInitTime().toString()))
			enterpriseAd.setInitTime(new Date());
		if (enterpriseAd.getEndTime() == null || "".equals(enterpriseAd.getEndTime().toString())) {
			Date d = new Date();
			d.setDate(d.getDate() + 3650);
			enterpriseAd.setEndTime(d);
		}
		epAdDao.update(enterpriseAd);
	}

	public List getEnterpriseAdByClassAndSize(Long size) {
		return epAdDao.getLinksAndAdByClassAndSize(EnterpriseAd.class, size);
	}

	// 获取所有的广告分类
	public List getDistinctFrontNum() {
		return this.epAdDao.getDistinctFrontNum();
	}

	// 获取所有的广告根据分类
	public List findAllByFrontNum(String frontNum) {
		return this.epAdDao.findAllByFrontNum(frontNum);
	}

}