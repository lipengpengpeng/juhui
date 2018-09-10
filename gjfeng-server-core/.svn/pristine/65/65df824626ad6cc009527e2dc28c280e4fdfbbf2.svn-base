package cc.messcat.gjfeng.service.weixin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cc.messcat.gjfeng.common.vo.app.ResultVo;
import cc.messcat.gjfeng.dao.weixin.GjfWeixinInfoDao;
import cc.messcat.gjfeng.entity.GjfWeiXinPayInfo;

@Service("gjfWeixinInfoService")
public class GjfWeixinInfoServiceImpl implements GjfWeixinInfoService {
	@Autowired
	@Qualifier("gjfWeixinInfoDao")
	private GjfWeixinInfoDao gjfWeixinInfoDao;

	@Override
	public ResultVo getUsingWeixinPublicNumberInfo() {
		GjfWeiXinPayInfo publicNumberInfo = gjfWeixinInfoDao.findUsingWeixinPublicNumberInfo();
		return new ResultVo(200, "查询成功", publicNumberInfo);
	}

	@Override
	public ResultVo getUsingH5Info() {
		GjfWeiXinPayInfo h5Info = gjfWeixinInfoDao.findUsingH5Info();
		return new ResultVo(200, "查询成功", h5Info);
	}

}
