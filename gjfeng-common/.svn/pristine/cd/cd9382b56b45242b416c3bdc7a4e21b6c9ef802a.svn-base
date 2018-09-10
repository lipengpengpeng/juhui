package cc.messcat.gjfeng.dubbo.sms;

import cc.messcat.gjfeng.common.vo.app.SmsVo;
import cc.messcat.gjfeng.entity.GjfMessageHistory;

public interface SmsDubbo {

	public SmsVo sendMsg(String mobile, String randomCode);

	public SmsVo sendMail(String title, String content, String emails);
	
	public void addMessageHistory(GjfMessageHistory gjfMessageHistory);
	
	public void updateMnsSmsStatus();
}
