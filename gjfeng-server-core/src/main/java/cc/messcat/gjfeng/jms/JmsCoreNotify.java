package cc.messcat.gjfeng.jms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jms.Message;
import javax.jms.MessageListener;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import cc.messcat.gjfeng.common.dto.Arrts;
import cc.messcat.gjfeng.entity.GjfProductInfo;
import cc.messcat.gjfeng.service.product.GjfProductInfoService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Component
public class JmsCoreNotify implements MessageListener {

	private static final Log log = LogFactory.getLog(JmsCoreNotify.class);

	@Autowired
	@Qualifier("gjfProductInfoService")
	private GjfProductInfoService gjfProductInfoService;
	
	/*
	 * 接收mq消息
	 * 
	 * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public void onMessage(Message message) {
		try {
			ActiveMQTextMessage msg = (ActiveMQTextMessage) message;
			final String ms = msg.getText();
			if ("queue://ProductInfoAddNotify".equals(message.getJMSDestination().toString())) {
				Map<String, Object> dataMap = new HashMap<String, Object>();
				dataMap = JSONObject.fromObject(ms);
				GjfProductInfo gjfProductInfo = (GjfProductInfo) JSONObject.toBean(JSONObject.fromObject(dataMap.get("gjfProductInfo")), GjfProductInfo.class);
				List<Arrts> arrts=JSONArray.toList(JSONArray.fromObject(dataMap.get("arrts")), Arrts.class);
				Long columnId=Long.parseLong(String.valueOf(dataMap.get("columnId")));
				gjfProductInfoService.addShopProduct(gjfProductInfo, arrts, columnId);
			}else if ("queue://ProductInfoUpdateNotify".equals(message.getJMSDestination().toString())) {
				System.out.println("进入mq");
				Map<String, Object> dataMap = new HashMap<String, Object>();
				dataMap = JSONObject.fromObject(ms);
				GjfProductInfo gjfProductInfo = (GjfProductInfo) JSONObject.toBean(JSONObject.fromObject(dataMap.get("gjfProductInfo")), GjfProductInfo.class);
				List<Arrts> arrts=JSONArray.toList(JSONArray.fromObject(dataMap.get("arrts")), Arrts.class);
				Long id=Long.parseLong(String.valueOf(dataMap.get("id")));
				gjfProductInfoService.updateShopProduct(id, gjfProductInfo, arrts);
			}	
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			throw new ServiceException("mq消息调用异常");
		}
	}
}
