package cc.messcat.gjfeng.jms;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import cc.messcat.gjfeng.common.util.ObjValid;
import cc.messcat.gjfeng.entity.GjfErrorInfo;
import cc.messcat.gjfeng.service.GjfBenefitInfoService;
import cc.messcat.gjfeng.service.GjfBenefitNotifyService;
import net.sf.json.JSONObject;

@Component
public class JmsBenefitNotify implements MessageListener {

	@Autowired
	@Qualifier("gjfBenefitInfoService")
	private GjfBenefitInfoService gjfBenefitInfoService;
	
	@Autowired
	@Qualifier("gjfBenefitNotifyService")
	private GjfBenefitNotifyService gjfBenefitNotifyService;

	/*
	 * 接收mq消息
	 * 
	 * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void onMessage(Message message) {
		String queueName="";
		Map<String, Object> dataMap = new HashMap<String, Object>();
		try {
			ActiveMQTextMessage msg = (ActiveMQTextMessage) message;
			final String ms = msg.getText();
			queueName=message.getJMSDestination().toString();
			dataMap = JSONObject.fromObject(ms);
			if ("queue://MemberDividendsNumNotify".equals(queueName)) {
				gjfBenefitInfoService.updateBenefitMqLog(String.valueOf(dataMap.get("tradeNo")), "2");
//				gjfBenefitNotifyService.updateMemberDividendsNumNotify(String.valueOf(dataMap.get("membersMobile")),
//					String.valueOf(dataMap.get("merchantsMobile")),Double.parseDouble(String.valueOf(dataMap.get("consumptionMoney"))),String.valueOf(dataMap.get("tradeNo")));
				gjfBenefitInfoService.updateBenefitMqLog(String.valueOf(dataMap.get("tradeNo")), "3");
			}else if ("queue://MemberBenefitNotity".equals(queueName)) {
				if (ObjValid.isValid(dataMap.get("tradeNo"))) {
					gjfBenefitInfoService.updateBenefitMqLog(String.valueOf(dataMap.get("tradeNo")), "2");
				}
				gjfBenefitNotifyService.updateMemberBenefitNotify();
				
				if (ObjValid.isValid(dataMap.get("tradeNo"))) {
					gjfBenefitInfoService.updateBenefitMqLog(String.valueOf(dataMap.get("tradeNo")), "3");
				}

			} else if ("queue://AgentBenefitNotity".equals(queueName)) {
				if (ObjValid.isValid(dataMap.get("tradeNo"))) {
					gjfBenefitInfoService.updateBenefitMqLog(String.valueOf(dataMap.get("tradeNo")), "2");
				}
				gjfBenefitNotifyService.updateAgentBenefitByDayNotify();
				
				if (ObjValid.isValid(dataMap.get("tradeNo"))) {
					gjfBenefitInfoService.updateBenefitMqLog(String.valueOf(dataMap.get("tradeNo")), "3");
				}
			}
		} catch (Exception e) {
			String msg="";
			if ("queue://MemberDividendsNumNotify".equals(queueName)) {
				msg="用户分红权计算出错，请及时处理";
			}else if ("queue://MemberBenefitNotity".equals(queueName)) {
				msg="用户商家分红统计出错，请及时处理";
			} else if ("queue://AgentBenefitNotity".equals(queueName)) {
				msg="用户代理分红统计出错，请及时处理";
			}
			if (ObjValid.isValid(dataMap.get("tradeNo"))) {
				gjfBenefitInfoService.updateBenefitMqLog(String.valueOf(dataMap.get("tradeNo")), "4");
			}
			gjfBenefitNotifyService.saveErrMsg(new GjfErrorInfo(null,"mq调用异常:"+msg, new Date(), "0"));
			e.printStackTrace();
		}
	}
}
