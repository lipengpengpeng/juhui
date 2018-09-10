package cc.messcat.service.dynamic;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cc.messcat.bases.BaseManagerDaoImpl;
import cc.messcat.entity.EnterpriseLinks;
import cc.messcat.gjfeng.common.bean.Pager;

/**
 * @author Messcat
 * @version 1.1
 *
 */
public class EpLinksManagerDaoImpl extends BaseManagerDaoImpl implements EpLinksManagerDao {

	public EpLinksManagerDaoImpl() {
	}

	public void addEpLinks(EnterpriseLinks enterpriseLinks) {
		if (enterpriseLinks.getInitTime() == null || "".equals(enterpriseLinks.getInitTime().toString()))
			enterpriseLinks.setInitTime(new Date());
		if (enterpriseLinks.getEndTime() == null || "".equals(enterpriseLinks.getEndTime().toString())) {
			Date d = new Date();
			d.setDate(d.getDate() + 3650);
			enterpriseLinks.setEndTime(d);
		}
		epLinksDao.save(enterpriseLinks);
	}

	public void deleteEnterpriseLinks(Long id) {
		epLinksDao.delete(id);
	}

	@SuppressWarnings("unchecked")
	public Pager findEpLinks(int pageSize, int pageNo, String statu) {
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("state", statu);
		Pager pager = new Pager(pageSize, pageNo,
			Integer.parseInt(String.valueOf(epLinksDao.queryTotalRecord(EnterpriseLinks.class, attrs))),
			epLinksDao.queryList(EnterpriseLinks.class, (pageNo - 1) * pageSize, pageSize, "id", "asc", attrs));
		return pager;
	}

	public EnterpriseLinks getEpLinks(Long id) {
		return epLinksDao.get(id);
	}

	public void updateEnterpriseLinks(EnterpriseLinks enterpriseLinks) {
		if (enterpriseLinks.getInitTime() == null || "".equals(enterpriseLinks.getInitTime().toString()))
			enterpriseLinks.setInitTime(new Date());
		if (enterpriseLinks.getEndTime() == null || "".equals(enterpriseLinks.getEndTime().toString())) {
			Date d = new Date();
			d.setDate(d.getDate() + 3650);
			enterpriseLinks.setEndTime(d);
		}
		epLinksDao.update(enterpriseLinks);
	}

	public List getEpLinksByClassAndSize(Long size) {
		return epLinksDao.getLinksAndAdByClassAndSize(EnterpriseLinks.class, size);
	}

	/**
	 * 获取所有的友情链接分类
	 */
	public List getDistinctFrontNum() {
		return this.epLinksDao.getDistinctFrontNum();
	}

	/**
	 * 根据分类获取所有的友情链接
	 */
	public List findAllByFrontNum(String frontNum) {
		return this.epLinksDao.findAllByFrontNum(frontNum);
	}
}