/*
 * Copyright (c) 2015 Messcat. All rights reserved.
 * 
 */
package cc.messcat.gjfeng.service.mail;

import java.util.Date;

import org.springframework.stereotype.Service;

import cc.messcat.gjfeng.common.exception.MyException;
import cc.messcat.gjfeng.common.util.StringUtil;
import cc.messcat.gjfeng.common.vo.app.SmsVo;
import cc.messcat.gjfeng.util.MailSenderInfo;
import cc.messcat.gjfeng.util.SimpleMailSender;


@Service("mailService")
public class MailServiceImpl implements MailService {

	/* 
	 * 发送邮件
	 * @see cc.messcat.jfeimao.service.mail.MailService#sendMail(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public SmsVo sendMail(String title,String content,String emails) {
		// 发送邮箱消息
		if (StringUtil.isBlank(title) || StringUtil.isBlank(content) || StringUtil.isBlank(emails)) {
			throw new MyException(400, "发送失败", null);
		}
		MailSenderInfo mailInfo = new MailSenderInfo();  
		mailInfo.setFromAddress("jfeimaocom@126.com");  // 设置发送人邮箱地址  
		mailInfo.setUserName("jfeimaocom@126.com"); 	// 实际发送者  
	    mailInfo.setPassword("ahdtoyracslnxjyl");			//您的邮箱密码  
	    mailInfo.setValidate(true);  
	    mailInfo.setMailServerPort("25");  
	    mailInfo.setMailServerHost("smtp.126.com");  
	    mailInfo.setSubject(title);  
	    mailInfo.setContent(content);  
	    String[] strArr=emails.split(";");
	    for (String email : strArr) {
	    	SimpleMailSender sms = new SimpleMailSender();  
	    	mailInfo.setToAddress(email); // 设置接受者邮箱地址 
            sms.sendTextMail(mailInfo);
            
            //保存邮件消息到数据库--根据发送状态进行保存
		}
	    return new SmsVo(mailInfo.getUserName(), new Date(), content, "2", "发送成功");
	}
}
