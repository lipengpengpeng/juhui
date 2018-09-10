package cc.messcat.gjfeng.jms;

import java.util.HashMap;
import java.util.Map;

import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import cc.messcat.gjfeng.common.exception.LoggerPrint;
import cc.messcat.gjfeng.service.sms.SmsService;
import net.sf.json.JSONObject;

@Component
public class JmsSmsNotify implements MessageListener {

	@Autowired
	@Qualifier("smsService")
	private SmsService smsService;

	/*
	 * 接收mq消息
	 * 
	 * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void onMessage(Message message) {
		try {
			ActiveMQTextMessage msg = (ActiveMQTextMessage) message;
			final String ms = msg.getText();
			if ("queue://SmsSendNotify".equals(message.getJMSDestination().toString())) {
				Map<String, Object> dataMap = new HashMap<String, Object>();
				dataMap = JSONObject.fromObject(ms);
//				String mobile=(String) dataMap.get("mobile");
//				String content=(String) dataMap.get("content");
				smsService.sendContentSms(dataMap);
			}
		} catch (Exception e) {
			LoggerPrint.getResult(e, JmsSmsNotify.class);
			throw new ServiceException("mq消息调用异常");
		}
	}
}
