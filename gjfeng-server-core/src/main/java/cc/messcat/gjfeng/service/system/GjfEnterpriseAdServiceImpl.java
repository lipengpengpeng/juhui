package cc.messcat.gjfeng.service.system;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cc.messcat.gjfeng.common.util.BeanUtilsEx;
import cc.messcat.gjfeng.common.vo.app.EnterpriseAdVo;
import cc.messcat.gjfeng.common.vo.app.ResultVo;
import cc.messcat.gjfeng.dao.system.GjfEnterpriseAdDao;
import cc.messcat.gjfeng.entity.GjfEnterpriseAd;

@Service("gjfEnterpriseAdService")
public class GjfEnterpriseAdServiceImpl implements GjfEnterpriseAdService {

	@Autowired
	@Qualifier("gjfEnterpriseAdDao")
	private GjfEnterpriseAdDao gjfEnterpriseAdDao;

	/* 
	 * 查询首页广告
	 * @see cc.messcat.gjfeng.service.system.GjfEnterpriseAdService#findAd(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ResultVo findAd(String adKey) {
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("frontNum", adKey);
		attrs.put("state", "1");
		return new ResultVo(200, "查询成功", BeanUtilsEx.changeList(gjfEnterpriseAdDao.queryList(GjfEnterpriseAd.class, "id", "asc", attrs), EnterpriseAdVo.class));
	}



}