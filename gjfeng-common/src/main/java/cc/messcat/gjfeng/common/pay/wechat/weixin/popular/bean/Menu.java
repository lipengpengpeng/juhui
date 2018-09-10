package cc.messcat.gjfeng.common.pay.wechat.weixin.popular.bean;

import cc.messcat.gjfeng.common.pay.wechat.weixin.popular.bean.BaseResult;
import cc.messcat.gjfeng.common.pay.wechat.weixin.popular.bean.MenuButtons;

public class Menu extends BaseResult{
	
	private MenuButtons menu;

	public MenuButtons getMenu() {
		return menu;
	}

	public void setMenu(MenuButtons menu) {
		this.menu = menu;
	}
	
	
}
