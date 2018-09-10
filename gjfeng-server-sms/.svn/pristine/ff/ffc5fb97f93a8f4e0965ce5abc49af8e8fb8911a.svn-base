package cc.messcat.gjfeng.dubbo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import cc.messcat.gjfeng.common.vo.app.SmsVo;
import cc.messcat.gjfeng.dubbo.sms.SmsDubbo;
import cc.messcat.gjfeng.entity.GjfMessageHistory;
import cc.messcat.gjfeng.service.mail.MailService;
import cc.messcat.gjfeng.service.sms.SmsService;

@Component
public class SmsDubboImpl implements SmsDubbo {
	
	@Autowired
	@Qualifier("smsService")
	private SmsService smsService;
	
	@Autowired
	@Qualifier("mailService")
	private MailService mailService;

	@Override
	public SmsVo sendMsg(String mobile, String randomCode) {
		return smsService.sendMobileSms(mobile, randomCode);
	}

	@Override
	public SmsVo sendMail(String title, String content, String emails) {
		return mailService.sendMail(title, content, emails);
	}

	@Override
	public void addMessageHistory(GjfMessageHistory gjfMessageHistory) {
		 smsService.addMessageHistory(gjfMessageHistory);
	}
	
	@Override
	public void updateMnsSmsStatus(){
		smsService.updateMnsSmsStatus();
	}

}
