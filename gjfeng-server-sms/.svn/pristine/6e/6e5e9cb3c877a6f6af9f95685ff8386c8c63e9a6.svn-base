package cc.messcat.gjfeng.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aliyun.mns.client.CloudAccount;
import com.aliyun.mns.client.CloudQueue;
import com.aliyun.mns.client.CloudTopic;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.common.ClientException;
import com.aliyun.mns.common.ServiceException;
import com.aliyun.mns.model.BatchSmsAttributes;
import com.aliyun.mns.model.Message;
import com.aliyun.mns.model.MessageAttributes;
import com.aliyun.mns.model.RawTopicMessage;
import com.aliyun.mns.model.TopicMessage;

import cc.messcat.gjfeng.common.constant.CommonProperties;
import cc.messcat.gjfeng.common.vo.app.SmsVo;

public class SendMNSUtil {

	private static volatile CloudAccount account = null;

	private static volatile MNSClient client = null;

	/**
	 * 发送短信模版
	 * 
	 * @param mobile
	 * @param templateCode
	 * @param templateParam
	 * @return
	 */
	public static SmsVo sendMSG(Map<String, Object> templateParam) {
		CloudAccount cloudAccount = new CloudAccount(CommonProperties.MNS_ACCESSKEYID, CommonProperties.MNS_ACCESSKEYSECRET,
				CommonProperties.MNS_ENDPOINT);
		MNSClient client = cloudAccount.getMNSClient();
		/**
		 * Step 1. 获取主题引用
		 */
//		MNSClient client = getMNSClient();
		CloudTopic topic = client.getTopicRef(CommonProperties.MNS_SMSTOPIC);
		/**
		 * Step 2. 设置SMS消息体（必须）
		 *
		 * 注：目前暂时不支持消息内容为空，需要指定消息内容，不为空即可。
		 */
		RawTopicMessage msg = new RawTopicMessage();
		try {
			msg.setMessageBody(new String("sms-message".getBytes("UTF-8")));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		/**
		 * Step 3. 生成SMS消息属性
		 */
		MessageAttributes messageAttributes = new MessageAttributes();
		BatchSmsAttributes batchSmsAttributes = new BatchSmsAttributes();

		// 3.1 设置发送短信的签名（SMSSignName）
		batchSmsAttributes.setFreeSignName("凤凰云商");

		// 3.2 设置发送短信使用的模板（SMSTempateCode）
		String templateCode = templateParam.get("templateCode").toString();
		batchSmsAttributes.setTemplateCode(templateCode);

		// 3.3 设置发送短信所使用的模板中参数对应的值（在短信模板中定义的，没有可以不用设置）
		String code = templateParam.get("variable0").toString();
		BatchSmsAttributes.SmsReceiverParams smsReceiverParams = new BatchSmsAttributes.SmsReceiverParams();
		for (Map.Entry<String, Object> entry : templateParam.entrySet()) {
			if (entry.getKey().startsWith("variable")) {
				smsReceiverParams.setParam(entry.getKey().toString(), entry.getValue().toString());
			}
		}

		// 3.4 增加接收短信的号码
		String mobile = (String) templateParam.get("mobile");
		batchSmsAttributes.addSmsReceiver(mobile, smsReceiverParams);
		messageAttributes.setBatchSmsAttributes(batchSmsAttributes);

		TopicMessage ret = null;
		try {
			/**
			 * Step 4. 发布SMS消息
			 */
			ret = topic.publishMessage(msg, messageAttributes);
//			System.out.println("MessageId: " + ret.getMessageId());
//			System.out.println("MessageMD5: " + ret.getMessageBodyMD5());
		} catch (ServiceException se) {
			System.out.println(se.getErrorCode() + se.getRequestId());
			System.out.println(se.getMessage());
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			client.close();
		}
		return new SmsVo(mobile, new Date(), CommonProperties.MNS_TEMPLATE_CODE.equals(templateCode) ? code : templateCode, "2", "");
	}

	/**
	 * 
	 */
	public static List<Map<String, String>> getQueueMessage() {
		CloudAccount cloudAccount = new CloudAccount(CommonProperties.MNS_ACCESSKEYID, CommonProperties.MNS_ACCESSKEYSECRET,
				CommonProperties.MNS_ENDPOINT);
		MNSClient client = cloudAccount.getMNSClient();
		List<Map<String,String>> resultList = new ArrayList<Map<String,String>>();

		try {
			CloudQueue queue = client.getQueueRef(CommonProperties.MNS_QUEUE_SMS);
			if (0 == queue.getAttributes().getActiveMessages())
				return resultList;
			// 批量查看消息
//			System.out.println("getActiveMessages:" + queue.getAttributes().getActiveMessages());
			List<Message> batchPeekMessage = queue
					.batchPopMessage(queue.getAttributes().getActiveMessages().intValue());
//			System.out.println("batchPeekMessage.size:" + batchPeekMessage.size());
			for (Message peekMsg : batchPeekMessage) {
//				System.out.println("PeekMessage has MsgId:" + peekMsg.getMessageId());
//				System.out.println("PeekMessage has MsgBodyMD5:" + peekMsg.getMessageBodyMD5());
//				System.out.println("PeekMessage getErrorMessage:" + peekMsg.getErrorMessage());
				String body = peekMsg.getMessageBodyAsBase64();
				String[] strs = body.split("&");
				Map<String, String> map = new HashMap<String, String>();
				try {
					for (String str : strs) {
						if (str.split("=").length > 1)
							map.put(str.split("=")[0], str.split("=")[1]);
					}
				} catch (ArrayIndexOutOfBoundsException ex) {
					ex.printStackTrace();
				}
				queue.deleteMessage(peekMsg.getReceiptHandle());
				resultList.add(map);
//				System.out.println("body:" + body);
				// 删除已经取出消费的消息
//				System.out.println("delete message successfully.\n");
			}
		} catch (ClientException ce) {
			System.out.println("Something wrong with the network connection between client and MNS service."
					+ "Please check your network and DNS availablity.");
			ce.printStackTrace();
		} catch (ServiceException se) {
			se.printStackTrace();
			if (se.getErrorCode() != null) {
				if (se.getErrorCode().equals("QueueNotExist")) {
					System.out.println("Queue is not exist.Please create before use");
				} else if (se.getErrorCode().equals("TimeExpired")) {
					System.out.println("The request is time expired. Please check your local machine timeclock");
				}
				/*
				 * you can get more MNS service error code from following link:
				 * https://help.aliyun.com/document_detail/mns/api_reference/
				 * error_code/error_code.html
				 */
			}
		} catch (Exception e) {
			System.out.println("Unknown exception happened!");
			e.printStackTrace();
		} finally {
			client.close();
		}
		return resultList;
	}

	/**
	 * 获取CloundAccount单例
	 * 
	 * @return CloudAccount
	 */
	public static CloudAccount getCloundAccount() {
		if (account == null) {
			synchronized (CloudAccount.class) {
				if (account == null) {
					account = new CloudAccount(CommonProperties.MNS_ACCESSKEYID, CommonProperties.MNS_ACCESSKEYSECRET,
							CommonProperties.MNS_ENDPOINT);
				}
			}
		}
		return account;
	}

	/**
	 * 获取MNSClient单例
	 * 
	 * @return MNSClient
	 */
	public static MNSClient getMNSClient() {
		if (client == null) {
			synchronized (MNSClient.class) {
				if (client == null) {
					if (account == null) {
						getCloundAccount();
					}
					client = account.getMNSClient();
				}
			}
		}
		return client;
	}
}