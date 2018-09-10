package cc.messcat.gjfeng.web.wechat;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cc.messcat.gjfeng.config.WaixiConfig;

@Controller
@RequestMapping("wx/WechatConfig/")
public class WechatConfigController {

	@RequestMapping(value = "reload")
	public void toDrawCash() {
		WaixiConfig.reload();
	}

}
