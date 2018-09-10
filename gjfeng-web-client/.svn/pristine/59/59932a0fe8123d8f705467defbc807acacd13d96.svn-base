package com.wechat.popular.example;

import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wechat.popular.api.MessageAPI;
import com.wechat.popular.bean.pay.PayResult;
import com.wechat.popular.bean.templatemessage.TemplateMessage;
import com.wechat.popular.bean.templatemessage.TemplateMessageItem;
import com.wechat.popular.bean.templatemessage.TemplateMessageResult;
import com.wechat.popular.support.TokenManager;
import com.wechat.popular.util.XMLConverUtil;

import cc.messcat.gjfeng.config.WaixiConfig;

/**
 * 支付回调通知
 * @author LiYi
 *
 */
@Controller
@RequestMapping("PayNotify/")
public class PayNotifyAction{


	private static final long serialVersionUID = 1L;
	
	@Autowired
	private  HttpServletRequest request;
	@Autowired
	private HttpServletResponse response;

	public String execute() throws Exception {
		/*HttpServletRequest request = ServletActionContext.getRequest();
    	HttpServletResponse response = ServletActionContext.getResponse();*/
    	System.out.println("支付回调地址调用");
    	try {
    		//获取请求数据
    		PayResult payResult = XMLConverUtil.convertToObject(PayResult.class, request.getInputStream());
    		//签名验证
    		if(payResult != null && !"SUCCESS".equals(payResult.getResult_code()) && !"SUCCESS".equals(payResult.getReturn_code())){
    			response.getOutputStream().write("error".getBytes());
    		}else{
    			//执行支付成功后的逻辑代码
    			//支付成功后  若是第一次购买就变成分销商
    			String out_trade_no = payResult.getOut_trade_no();
    			//PayOrder payOrder = new PayOrder();
    			/*payOrder = this.payOrderManagerDao.queryByOrderNum(out_trade_no);
    			Member member  = payOrder.getMember();
    			if(member.getDistributor().equals("0")){
    				member.setDistributor("1");
        			this.memberManagerDao.modifyMember(member);	
    			}
    			
    			System.out.println("out_trade_no:" + out_trade_no);
    			if(ObjValid.isValid(out_trade_no)){
    				payOrderManagerDao.finishTrade(out_trade_no);
//    				changeOrderStatus(out_trade_no);
    				System.out.println("订单:" + out_trade_no + "已更新.");
    			}*/
    			
//    			Member tempmMember = member;
//    			tempmMember = tempmMember.getMemberFather();
//    			if (ObjValid.isValid(tempmMember)) {
//    				for (int i = 0; i < 3; i++) {
//    					if (ObjValid.isValid(tempmMember)) {
//	        				String userLevel = "";
//	        				if (i==0) {
//	        					userLevel = "皇妃";
//	    					}
//	        				if (i==1) {
//	        					userLevel = "贵妃";
//	        				}
//	        				if (i==2) {
//	        					userLevel = "贤妃";
//	        				}
//	        				//TODO：推送给上三级；
//	        				System.out.println("tempmMember:"+tempmMember);
//	        				templateMessageItem.clear();
//	            			TemplateMessageItem fatherTemplateMessageItem1 = new TemplateMessageItem("您的【"+userLevel+"】购买了新的商品", "#000000");
//	            			TemplateMessageItem fatherTemplateMessageItem2 = new TemplateMessageItem(member.getNickname(), "#000000");
//	            			TemplateMessageItem fatherTemplateMessageItem3 = new TemplateMessageItem(DateHelper.dataToString(payOrder.getCreateTime(), "yyyy-MM-dd HH:mm:ss"), "#000000");
//	            			TemplateMessageItem fatherTemplateMessageItem4 = new TemplateMessageItem("下单金额:"+payOrder.getOrderAmount().toString(), "#000000");
//	            			templateMessageItem.put("first", fatherTemplateMessageItem1);
//	            			templateMessageItem.put("keyword1", fatherTemplateMessageItem2);
//	            			templateMessageItem.put("keyword2", fatherTemplateMessageItem3);
//	            			templateMessageItem.put("remark", fatherTemplateMessageItem4);
//	            			
//	            			TemplateMessage templateMessage1 = new TemplateMessage();
//	            			templateMessage1.setTemplate_id(Constants.TEMPLATE_MESSAGE_SUBMIT);
//	            			templateMessage1.setTopcolor("#000000");
//	            			templateMessage1.setTouser(tempmMember.getLoginName());
//	        				System.out.println("tempmMember.getNickname()::"+tempmMember.getNickname());
//	
//	            			templateMessage1.setUrl("");
//	            			templateMessage1.setData(templateMessageItem);
//	            			
//	            			TemplateMessageResult templateMessageResult2 =  MessageAPI.messageTemplateSend(access_token, templateMessage1);
//	            			
//	            			TemplateMessageResult templateMessageResult = MessageManager.sendNewOrderTemplateMessage(userLevel, "网猫微购", "澜妃健康卫生巾", DateHelper.dataToString(payOrder.getCreateTime(), "yyyy-MM-dd HH:mm:ss"), payOrder.getProductAmount()+"", "1".equals(payOrder.getPayStatus())?"已付款":"未付款", "获得红包:"+, member.getLoginName());
//	            			
//	            			System.out.println("access_token2:"+access_token);
//	            			System.out.println("payNotifyAction2:"+templateMessageResult2.getErrcode()+" --" + templateMessageResult2.getErrmsg());
//	            			
//	            			
//	            			
//	            			
//	            			tempmMember = tempmMember.getMemberFather();
//    					}
//    				}
//    				
//				}
    		}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("支付回调异常");
		}
    	response.getOutputStream().write("success".getBytes());
		return null;
	}
	
	public static void main(String[] args) throws Exception {
		//消息通知1
		LinkedHashMap<String, TemplateMessageItem> templateMessageItem = new LinkedHashMap<String, TemplateMessageItem>();
		TemplateMessageItem templateMessageItem1 = new TemplateMessageItem("消费金额", "#000000");
		TemplateMessageItem templateMessageItem2 = new TemplateMessageItem("0.01", "#000000");
		TemplateMessageItem templateMessageItem3 = new TemplateMessageItem("会员", "#000000");
		TemplateMessageItem templateMessageItem4 = new TemplateMessageItem("小亮", "#000000");
		TemplateMessageItem templateMessageItem5 = new TemplateMessageItem("2015-08-04 05:25:21", "#000000");
		TemplateMessageItem templateMessageItem6 = new TemplateMessageItem("感谢您的购物，祝您生活愉快", "#000000");
		templateMessageItem.put("productType", templateMessageItem1);
		templateMessageItem.put("name", templateMessageItem2);
		templateMessageItem.put("accountType", templateMessageItem3);
		templateMessageItem.put("account", templateMessageItem4);
		templateMessageItem.put("time", templateMessageItem5);
		templateMessageItem.put("remark", templateMessageItem6);
		
		
		
		TemplateMessage templateMessage = new TemplateMessage();
		//templateMessage.setTemplate_id(Constants.TEMPLATE_MEMBER_EXPENSE_MESSAGE);
		templateMessage.setTopcolor("#000000");
		templateMessage.setTouser("orxiEtyvbryGCiIsfLcmNjXNWKsM"); //silver
		templateMessage.setUrl("");
		templateMessage.setData(templateMessageItem);
		TemplateMessageResult templateMessageResult =  MessageAPI.messageTemplateSend(TokenManager.getToken(WaixiConfig.GJFENG_APP_ID()), templateMessage);
		System.out.println("ReceiveServlet:"+templateMessageResult.getErrcode()+" --" + templateMessageResult.getErrmsg());
		
		//消息通知2
		LinkedHashMap<String, TemplateMessageItem> templateMessageItem32 = new LinkedHashMap<String, TemplateMessageItem>();
		TemplateMessageItem templateMessageItem21 = new TemplateMessageItem("消费金额", "#000000");
		TemplateMessageItem templateMessageItem22 = new TemplateMessageItem("0.01", "#000000");
		TemplateMessageItem templateMessageItem23 = new TemplateMessageItem("会员", "#000000");
		TemplateMessageItem templateMessageItem24 = new TemplateMessageItem("troy", "#000000");
		TemplateMessageItem templateMessageItem25 = new TemplateMessageItem("2015-08-04 05:25:21", "#000000");
		TemplateMessageItem templateMessageItem26 = new TemplateMessageItem("感谢您的购物，祝您生活愉快", "#000000");
		templateMessageItem32.put("productType", templateMessageItem21);
		templateMessageItem32.put("name", templateMessageItem22);
		templateMessageItem32.put("accountType", templateMessageItem23);
		templateMessageItem32.put("account", templateMessageItem24);
		templateMessageItem32.put("time", templateMessageItem25);
		templateMessageItem32.put("remark", templateMessageItem26);
		
		
		
		TemplateMessage templateMessage2 = new TemplateMessage();
		//templateMessage2.setTemplate_id(Constants.TEMPLATE_MEMBER_EXPENSE_MESSAGE);
		templateMessage2.setTopcolor("#000000");
		templateMessage2.setTouser("orxiEtyV5w-K2G7SIYu9cejrCMbU");//troy
		templateMessage2.setUrl("");
		templateMessage2.setData(templateMessageItem32);
		TemplateMessageResult templateMessageResult1 =  MessageAPI.messageTemplateSend(TokenManager.getToken(WaixiConfig.GJFENG_APP_ID()), templateMessage2);
		System.out.println("ReceiveServlet:"+templateMessageResult1.getErrcode()+" --" + templateMessageResult1.getErrmsg());

	}
	
}
