package cc.messcat.gjfeng.dao.weixin;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import cc.messcat.gjfeng.dao.BaseHibernateDaoImpl;
import cc.messcat.gjfeng.entity.GjfWeiXinPayInfo;

@Repository("gjfWeixinInfoDao")
public class GjfWeixinInfoDaoImpl extends BaseHibernateDaoImpl<Serializable> implements GjfWeixinInfoDao {

	@Override
	public GjfWeiXinPayInfo findUsingWeixinPublicNumberInfo() {
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("status", "1");//正在使用
		attrs.put("type", "1");//公众号
		GjfWeiXinPayInfo gjfWeiXinPayInfo = this.query(GjfWeiXinPayInfo.class, attrs);
		return gjfWeiXinPayInfo;
	}

	@Override
	public GjfWeiXinPayInfo findUsingH5Info() {
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("status", "1");//正在使用
		attrs.put("type", "2");//h5
		GjfWeiXinPayInfo gjfH5Info = this.query(GjfWeiXinPayInfo.class, attrs);
		return gjfH5Info;
	}

}
