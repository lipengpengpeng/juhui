package com.wechat.popular.support;

import java.util.LinkedHashMap;

import com.wechat.popular.api.MessageAPI;
import com.wechat.popular.bean.templatemessage.TemplateMessage;
import com.wechat.popular.bean.templatemessage.TemplateMessageItem;
import com.wechat.popular.bean.templatemessage.TemplateMessageResult;

import cc.messcat.gjfeng.common.util.ObjValid;
import cc.messcat.gjfeng.config.WaixiConfig;

/**
 * MessageManager	消息通知
 * @author Silver
 *
 */
public class MessageManager {

	/**
	 * 发送新增会员通知
	  * @param memberLevel
	 * 				会员等级：分为皇妃、贵妃、宾妃
	 * @param memberNumber
	 * 				会员编号
	 * @param memberNickName
	 * 				会员昵称
	 * @param addTime
	 * 				加入时间
	 * @param toUserName
	 * 				给谁发通知
	 * @param focusWay
	 * 				关注方式
	 * @return
	 * 				返回通知信息结果；
	 */
	public static TemplateMessageResult sendNewMemberTemplateMessage(String memberLevel, String memberNumber, String memberNickName, String addTime, String toUserName, String focusWay ){
		LinkedHashMap<String, TemplateMessageItem> templateMessageItem = new LinkedHashMap<String, TemplateMessageItem>();
		TemplateMessageItem templateMessageItem1 = new TemplateMessageItem("恭喜您，有新会员关注", "#000000");
		TemplateMessageItem templateMessageItem2 = new TemplateMessageItem(memberNumber, "#000000");
		TemplateMessageItem templateMessageItem3 = new TemplateMessageItem(addTime, "#000000");
		
		String focusWayString = "";
		/*if (Constants.FOCUS_WAY_QRCODE.equals(focusWay)) {
			focusWayString = "二维码";
		}else {
			focusWayString = "链接";
		}*/
		
		TemplateMessageItem templateMessageItem4 = new TemplateMessageItem(memberNickName+"【"+memberLevel+"】通过"+focusWayString+"关注了【网猫微购】成为您的会员", "#000000");
		templateMessageItem.put("first", templateMessageItem1);
		templateMessageItem.put("keyword1", templateMessageItem2);
		templateMessageItem.put("keyword2", templateMessageItem3);
		templateMessageItem.put("remark", templateMessageItem4);
		
		TemplateMessage templateMessage = new TemplateMessage();
		//templateMessage.setTemplate_id(Constants.TEMPLATE_MEMBER_ADD);
		templateMessage.setTopcolor("#000000");
		templateMessage.setTouser(toUserName);
		templateMessage.setUrl("");
		templateMessage.setData(templateMessageItem);
		
		TemplateMessageResult templateMessageResult =  MessageAPI.messageTemplateSend(TokenManager.getToken(WaixiConfig.GJFENG_APP_ID()), templateMessage);
		return templateMessageResult;
	}
	
	/**
	 * 新增订单信息
	 * @param memberLevel
	 * 			会员等级：分为皇妃、贵妃、宾妃
	 * @param memberNickName
	 * 				会员昵称
	 * @param shopName
	 * 			店铺名称
	 * @param goodsName
	 * 			商品名称
	 * @param addTime
	 * 			下单时间
	 * @param money
	 * 			下单金额
	 * @param paymentType
	 * 			付款状态
	 * @param remark
	 * 			备注
	 * @return
	 * 			返回通知信息结果；
	 */
	public static TemplateMessageResult sendNewOrderTemplateMessage(String memberLevel,String memberNickName, String shopName, String goodsName, String addTime, String money, String paymentType, String remark,String toUserName ){
		LinkedHashMap<String, TemplateMessageItem> templateMessageItem = new LinkedHashMap<String, TemplateMessageItem>();
		
		String tempLevelString = "";
		if (ObjValid.isValid(memberLevel)) {
			tempLevelString = "的【"+memberLevel+"】"+memberNickName;
		}
		TemplateMessageItem templateMessageItem1 = new TemplateMessageItem("您"+tempLevelString+"已成功下单", "#000000");
		TemplateMessageItem templateMessageItem2 = new TemplateMessageItem(shopName, "#000000");
		TemplateMessageItem templateMessageItem3 = new TemplateMessageItem(goodsName, "#000000");
		TemplateMessageItem templateMessageItem4 = new TemplateMessageItem(addTime, "#000000");
		TemplateMessageItem templateMessageItem5 = new TemplateMessageItem(money, "#000000");
		TemplateMessageItem templateMessageItem6 = new TemplateMessageItem(paymentType, "#000000");
		TemplateMessageItem templateMessageItem7 = new TemplateMessageItem(remark, "#000000");
		templateMessageItem.put("first", templateMessageItem1);
		templateMessageItem.put("keyword1", templateMessageItem2);
		templateMessageItem.put("keyword2", templateMessageItem3);
		templateMessageItem.put("keyword3", templateMessageItem4);
		templateMessageItem.put("keyword4", templateMessageItem5);
		templateMessageItem.put("keyword5", templateMessageItem6);
		templateMessageItem.put("remark", templateMessageItem7);
		
		TemplateMessage templateMessage = new TemplateMessage();
		//templateMessage.setTemplate_id(Constants.TEMPLATE_ORDER_ADD);
		templateMessage.setTopcolor("#000000");
		templateMessage.setTouser(toUserName);
		templateMessage.setUrl("");
		templateMessage.setData(templateMessageItem);
		
		TemplateMessageResult templateMessageResult =  MessageAPI.messageTemplateSend(TokenManager.getToken(WaixiConfig.GJFENG_APP_ID()), templateMessage);
		return templateMessageResult;
	}
	

}
