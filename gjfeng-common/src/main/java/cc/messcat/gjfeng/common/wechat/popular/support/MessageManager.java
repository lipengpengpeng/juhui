package cc.messcat.gjfeng.common.wechat.popular.support;

import java.util.LinkedHashMap;

import cc.messcat.gjfeng.common.pay.wechat.weixin.popular.api.MessageAPI;
import cc.messcat.gjfeng.common.pay.wechat.weixin.popular.bean.templatemessage.TemplateMessage;
import cc.messcat.gjfeng.common.pay.wechat.weixin.popular.bean.templatemessage.TemplateMessageItem;
import cc.messcat.gjfeng.common.pay.wechat.weixin.popular.bean.templatemessage.TemplateMessageResult;
import cc.messcat.gjfeng.common.util.ObjValid;


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
		
		TemplateMessageResult templateMessageResult =  MessageAPI.messageTemplateSend("", templateMessage);
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
		
		TemplateMessageResult templateMessageResult =  MessageAPI.messageTemplateSend("", templateMessage);
		return templateMessageResult;
	}
	
	/**
	 * 商家联盟奖励推送
	 * @param memNikeName
	 * @param directMoney
	 * @param toUserName
	 * @return
	 */
	public static TemplateMessageResult sendMedolMerchantDirectMoney(String memNikeName,String merchantType,String proName,String orderSn,String orderMoney,String directMoney,String toUserName,String token){
		LinkedHashMap<String, TemplateMessageItem> templateMessageItem = new LinkedHashMap<String, TemplateMessageItem>();
		String payMoney="商家版";
		if("2".equals(merchantType)){
			payMoney="企业版";
		}
		if("3".equals(merchantType)){
			payMoney="商家代理";
		}
		
		TemplateMessageItem templateMessageItem1=new TemplateMessageItem("您的会员升级成为"+payMoney+"联盟商家","#000000");
		TemplateMessageItem templateMessageItem2=new TemplateMessageItem(memNikeName,"#000000");
		TemplateMessageItem templateMessageItem3=new TemplateMessageItem(proName,"#000000");
		TemplateMessageItem templateMessageItem4=new TemplateMessageItem(orderSn,"#000000");
		TemplateMessageItem templateMessageItem5=new TemplateMessageItem(orderMoney,"#000000");
		TemplateMessageItem templateMessageItem6=new TemplateMessageItem(directMoney,"#000000");
		
		templateMessageItem.put("first", templateMessageItem1);
		templateMessageItem.put("keyword1", templateMessageItem2);		
		templateMessageItem.put("keyword2", templateMessageItem3);		
		templateMessageItem.put("keyword3", templateMessageItem4);		
		templateMessageItem.put("keyword4", templateMessageItem5);
		templateMessageItem.put("keyword5", templateMessageItem6);

		TemplateMessage templateMessage = new TemplateMessage();
		//templateMessage.setTemplate_id("NXQ2ZdiA_SoK3sZgOM43MdRiWTB7FUAJWz-JiFpYjZc");
		templateMessage.setTemplate_id("c7RfsujlefhxSdeSBWC9wgBfv1quR1x2iFQdK91uoEo");
		templateMessage.setTopcolor("#000000");
		templateMessage.setTouser(toUserName);
		templateMessage.setUrl("");
		templateMessage.setData(templateMessageItem);
		TemplateMessageResult templateMessageResult =  MessageAPI.messageTemplateSend(token, templateMessage);
		return templateMessageResult;
	}
	
	/**
	 * 订单奖励推送
	 * @param memberNikeName
	 * @param directMoney
	 * @param toUserName
	 * @return
	 */
	public static TemplateMessageResult sendOrderDirectMoney(String memberNikeName,String proName,String orderSn,String orderMoney,String directMoney,String toUserName,String token){
		LinkedHashMap<String, TemplateMessageItem> templateMessageItem=new LinkedHashMap<String, TemplateMessageItem>();
		TemplateMessageItem templateMessageItem1=new TemplateMessageItem("您的会员购买了商品","#000000");
		TemplateMessageItem templateMessageItem2=new TemplateMessageItem(memberNikeName,"#000000");
		TemplateMessageItem templateMessageItem3=new TemplateMessageItem(proName,"#000000");
		TemplateMessageItem templateMessageItem4=new TemplateMessageItem(orderSn,"#000000");
		TemplateMessageItem templateMessageItem5=new TemplateMessageItem(orderMoney,"#000000");
		TemplateMessageItem templateMessageItem6=new TemplateMessageItem(directMoney,"#000000");
		
		templateMessageItem.put("first", templateMessageItem1);
		templateMessageItem.put("keyword1", templateMessageItem2);		
		templateMessageItem.put("keyword2", templateMessageItem3);		
		templateMessageItem.put("keyword3", templateMessageItem4);		
		templateMessageItem.put("keyword4", templateMessageItem5);
		templateMessageItem.put("keyword5", templateMessageItem6);
	
		TemplateMessage templateMessage = new TemplateMessage();
		//templateMessage.setTemplate_id("NXQ2ZdiA_SoK3sZgOM43MdRiWTB7FUAJWz-JiFpYjZc");
		templateMessage.setTemplate_id("c7RfsujlefhxSdeSBWC9wgBfv1quR1x2iFQdK91uoEo");
		templateMessage.setTopcolor("#000000");
		templateMessage.setTouser(toUserName);
		templateMessage.setUrl("");
		templateMessage.setData(templateMessageItem);
		TemplateMessageResult templateMessageResult =  MessageAPI.messageTemplateSend(token, templateMessage);
		return templateMessageResult;
	}
	
	/**
	 * vip升级推荐奖励推送
	 * @param memberNikeName
	 * @param dirctMoney
	 * @param toUserName
	 * @param token
	 * @return
	 */
	public static TemplateMessageResult sendUpgradeVipDirectMoney(String memberNikeName,String orderSn,String proName,String orderMoney,String directMoney,String toUserName,String token){
		LinkedHashMap<String, TemplateMessageItem> templateMessageItem=new LinkedHashMap<>();
		TemplateMessageItem templateMessageItem1=new TemplateMessageItem("您的会员升级成为vip会员","#000000");
		TemplateMessageItem templateMessageItem2=new TemplateMessageItem(memberNikeName,"#000000");
		TemplateMessageItem templateMessageItem3=new TemplateMessageItem(proName,"#000000");
		TemplateMessageItem templateMessageItem4=new TemplateMessageItem(orderSn,"#000000");
		TemplateMessageItem templateMessageItem5=new TemplateMessageItem(orderMoney,"#000000");
		TemplateMessageItem templateMessageItem6=new TemplateMessageItem(directMoney,"#000000");
		
		templateMessageItem.put("first", templateMessageItem1);
		templateMessageItem.put("keyword1", templateMessageItem2);		
		templateMessageItem.put("keyword2", templateMessageItem3);		
		templateMessageItem.put("keyword3", templateMessageItem4);		
		templateMessageItem.put("keyword4", templateMessageItem5);
		templateMessageItem.put("keyword5", templateMessageItem6);
		TemplateMessage templateMessage = new TemplateMessage();
		//templateMessage.setTemplate_id("NXQ2ZdiA_SoK3sZgOM43MdRiWTB7FUAJWz-JiFpYjZc");
		templateMessage.setTemplate_id("c7RfsujlefhxSdeSBWC9wgBfv1quR1x2iFQdK91uoEo");
		templateMessage.setTopcolor("#000000");
		templateMessage.setTouser(toUserName);
		templateMessage.setUrl("");
		templateMessage.setData(templateMessageItem);
		TemplateMessageResult templateMessageResult =  MessageAPI.messageTemplateSend(token, templateMessage);
		return templateMessageResult;
	}
	

}
