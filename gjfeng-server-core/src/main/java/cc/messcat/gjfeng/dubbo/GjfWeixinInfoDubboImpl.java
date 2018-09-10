package cc.messcat.gjfeng.dubbo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import cc.messcat.gjfeng.common.vo.app.ResultVo;
import cc.messcat.gjfeng.dubbo.webclient.GjfWeixinInfoDubbo;
import cc.messcat.gjfeng.service.weixin.GjfWeixinInfoService;

public class GjfWeixinInfoDubboImpl implements GjfWeixinInfoDubbo {
	@Autowired
	@Qualifier("gjfWeixinInfoService")
	private GjfWeixinInfoService gjfWeixinInfoService;

	@Override
	public ResultVo getUsingWeixinPublicNumberInfo() {
		return gjfWeixinInfoService.getUsingWeixinPublicNumberInfo();
	}

	@Override
	public ResultVo getUsingH5Info() {
		return gjfWeixinInfoService.getUsingH5Info();
	}

}
