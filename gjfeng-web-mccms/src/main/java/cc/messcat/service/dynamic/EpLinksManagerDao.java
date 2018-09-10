package cc.messcat.service.dynamic;

import java.util.List;

import cc.messcat.entity.EnterpriseLinks;
import cc.messcat.gjfeng.common.bean.Pager;

/**
 * @author Messcat
 * @version 1.1
 *
 */
public interface EpLinksManagerDao {

	public abstract Pager findEpLinks(int i, int j, String s);

	public abstract EnterpriseLinks getEpLinks(Long long1);

	public abstract void addEpLinks(EnterpriseLinks enterpriselinks);

	public abstract void updateEnterpriseLinks(EnterpriseLinks enterpriselinks);

	public abstract void deleteEnterpriseLinks(Long long1);

	public abstract List getEpLinksByClassAndSize(Long long1);

	public abstract List getDistinctFrontNum();

	public abstract List findAllByFrontNum(String frontNum);
}