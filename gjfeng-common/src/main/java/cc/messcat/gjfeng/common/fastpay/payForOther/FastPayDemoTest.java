package cc.messcat.gjfeng.common.fastpay.payForOther;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;

import cc.messcat.gjfeng.common.fastpay.util.Base;
import cc.messcat.gjfeng.common.fastpay.util.DesCrypt;
import cc.messcat.gjfeng.common.fastpay.util.NetXmlUtils;




/***
 * 快捷支付测试demo
 * 注意项：deskey秘钥只在获取动态秘钥时针对报文加密；后续所有交易都是基于动态秘钥加密的
 * @author Administrator
 *
 */
public class FastPayDemoTest {
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	private static final String password = "am835u8mpi1lm4ua4d1chp8j";
	private static final String keyStorePath = "E:\\keystore_http\\client.keystore";
	private static final String dyPass_url = "https://gateway.yjf-pay.com/WebRoot/SwitchDynamicPassword";
	private static final String netPay_url = "https://gateway.yjf-pay.com/WebRoot/UserAuth";
	private static final String merid = "6600000000000569";
	private static final String deskey = "9m31cwlyak9i8cn5a8oj28sn";
	private static final String md5key = "ifvxgg3a7yfdtlm730d3vjtx1paot9ek";

	
	public static void main(String[] args) {
		String orderId = sdf.format(new Date()) + System.currentTimeMillis();
		System.out.println("订单号：" + orderId);
		String dypass = getDyPass(orderId);
		System.out.println("动态秘钥：" + dypass);
		// 快捷支付申请
//		fastApply(orderId, dypass);
		//FastPayDemoTest.fastApply(orderId,dypass,"1","18300072217","6217857000055154973","李冠华","450923199305022533", "www.baidu.com");
		// 支付确认
		
		//代付交易
		//payForOther1(orderId,dypass,"100","张三","6226090000000048","www.paidu.com");
		
		String verifyCode = "";//短信验证码
		//fastConfirm(orderId, dypass, verifyCode);
	}
	
	
	/***
	 * 快捷支付确认，凭借短信验证码支付
	 * @param orderId
	 * @param dypass
	 * @param verifyCode
	 */
	public static Map<String, String> fastConfirm(String orderId, String dypass, String verifyCode,String payMoney,String mobile,String accNo,String name,String idCard) {
		try {
			String xmlNode = "charCode,Version,TradeType,ChannelID,bmMerId,timeStamp,orderId,txnAmt,orderDesc,extData,accNo,nbr,name,certificateCode,verifyCode,fileId1,fileId2,fileId3";
			Map<String, String> reqMap = new HashMap<String, String>();
			reqMap.put("charCode", "gbk");
			reqMap.put("Version", "2.0.1");
			reqMap.put("TradeType", "0604");
			reqMap.put("ChannelID", merid);
			reqMap.put("bmMerId", merid);
			reqMap.put("orderId", orderId);
			reqMap.put("timeStamp", sdf.format(new Date()));
			
			// 业务信息
			float sessionmoney = Float.parseFloat(payMoney) * 100;					
			reqMap.put("txnAmt", String.format("%.0f", sessionmoney));//分为单位			
			reqMap.put("nbr", mobile);
			reqMap.put("accNo", accNo);
			reqMap.put("name", name);
			reqMap.put("certificateCode", idCard);
			reqMap.put("orderDesc", "购买商品");
			reqMap.put("verifyCode", verifyCode); // 短信验证码
			reqMap.put("extData", "extData"); // 商户保留信息
			
			reqMap.put("keyMd5", md5key);
			reqMap.put("key3Des", dypass);
			String xmlStr = NetXmlUtils.genXmlFromMapAndSign2(xmlNode, reqMap, reqMap.get("keyMd5"), "requestData");
			System.out.println("【请求】报文（明文）：" + xmlStr);
			byte[] xmlByte = DesCrypt.encrypt(xmlStr.getBytes("GBK"), reqMap.get("key3Des"));
			String xmlSend = Base.addZeroForNum(String.valueOf(xmlByte.length + 16), 4) + reqMap.get("ChannelID")
					+ Base64.encodeBase64String(xmlByte);
			System.out.println("【请求】报文（密文）：" + xmlSend);
			// https发起请求
			HttpClient hc = new HttpClient(netPay_url, 60000, keyStorePath, password, keyStorePath, password);
			String res = hc.submitUrl(xmlSend);
			System.out.println("【返回】报文（密文）：" + res);
			// 解密报文
			Map<String, String> decryptMap = RechargeTest.decryptData(res, dypass, md5key);
			System.out.println("【返回】报文（明文）：" + decryptMap);
			return decryptMap;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	/***
	 * 快捷支付申请
	 * @param orderId
	 * @param dypass
	 */
	public static Map<String, String> fastApply(String orderId, String dypass,String tradeMoney,String mobile,String accNo,String name,String idCardNo,String retUrl) {
		try {
			String xmlNode = "charCode,Version,TradeType,ChannelID,bmMerId,timeStamp,transType,orderId,txnAmt,accNo,nbr,name,certificateCode,retUrl,fileId1,fileId2,fileId3";
			Map<String, String> reqMap = new HashMap<String, String>();
			reqMap.put("charCode", "gbk");
			reqMap.put("Version", "2.0.1");
			reqMap.put("TradeType", "0602");
			reqMap.put("ChannelID", merid);
			reqMap.put("bmMerId", merid);
			reqMap.put("orderId", orderId);
			reqMap.put("timeStamp", sdf.format(new Date()));
			// 业务信息
			reqMap.put("transType", "1"); // 交易类型： 1：发送短信 2：重发短信
			
			float sessionmoney = Float.parseFloat(tradeMoney) * 100;					
			reqMap.put("txnAmt", String.format("%.0f", sessionmoney));//分为单位
			reqMap.put("nbr", mobile);
			reqMap.put("accNo", accNo);
			reqMap.put("name", name);
			reqMap.put("certificateCode",idCardNo);
			reqMap.put("retUrl", retUrl);// 异步通知地址，用于接收后台通知
			
			reqMap.put("keyMd5", md5key);
			reqMap.put("key3Des", dypass);
			String xmlStr = NetXmlUtils.genXmlFromMapAndSign2(xmlNode, reqMap, reqMap.get("keyMd5"), "requestData");
			System.out.println("【请求】报文（明文）：" + xmlStr);
			byte[] xmlByte = DesCrypt.encrypt(xmlStr.getBytes("GBK"), reqMap.get("key3Des"));
			String xmlSend = Base.addZeroForNum(String.valueOf(xmlByte.length + 16), 4) + reqMap.get("ChannelID")
					+ Base64.encodeBase64String(xmlByte);
			System.out.println("【请求】报文（密文）：" + xmlSend);
			// https发起请求
			HttpClient hc = new HttpClient(netPay_url, 60000, keyStorePath, password, keyStorePath, password);
			String res = hc.submitUrl(xmlSend);
			System.out.println("【返回】报文（密文）：" + res);
			// 解密报文
			Map<String, String> decryptMap = RechargeTest.decryptData(res, dypass, md5key);
			System.out.println("【返回】报文（明文）：" + decryptMap);
			return decryptMap;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}



	/***
	 * 获取动态秘钥
	 * TODO 建议通过单线程不大于2小时定时请求
	 * 取md5值的中间24位
	 * @return
	 */
	public static String getDyPass(String orderId) {
		String xmlNode = "charCode,Version,TradeType,ChannelID,orderId,bmMerId,timeStamp";
		Map<String, String> reqMap = new HashMap<String, String>();
		reqMap.put("charCode", "gbk");
		reqMap.put("Version", "2.0.1");
		reqMap.put("TradeType", "0413");
		reqMap.put("ChannelID", merid);
		reqMap.put("bmMerId", merid);
		reqMap.put("orderId", orderId);
		reqMap.put("timeStamp", sdf.format(new Date()));
		reqMap.put("keyMd5", md5key);
		reqMap.put("key3Des", deskey);
		try {
			String xmlStr = NetXmlUtils.genXmlFromMapAndSign2(xmlNode, reqMap, reqMap.get("keyMd5"), "requestData");
			System.out.println("动态秘钥【请求】报文（明文）：" + xmlStr);
			byte[] xmlByte = DesCrypt.encrypt(xmlStr.getBytes("GBK"), reqMap.get("key3Des"));
			String xmlSend = Base.addZeroForNum(String.valueOf(xmlByte.length + 16), 4) + reqMap.get("ChannelID") 
					+ Base64.encodeBase64String(xmlByte);
			System.out.println("动态秘钥【请求】报文（密文）：" + xmlSend);
			// https发起请求
			HttpClient hc = new HttpClient(dyPass_url, 60000, keyStorePath, password, keyStorePath, password);
			String res = hc.submitUrl(xmlSend);
			System.out.println("动态秘钥【返回】报文（密文）：" + res);
			// 解密报文
			Map<String, String> decryptMap = RechargeTest.decryptData(res, deskey, md5key);
			System.out.println("动态秘钥【返回】报文（明文）：" + decryptMap);
			if (!decryptMap.isEmpty()) {
				if ("00".equals(decryptMap.get("resultCode"))) {
					String random = decryptMap.get("random");
					if(!StringUtils.isEmpty(random)){
						String md5Value = Base.md5s(deskey + random);
						// TODO 取md5值的中间24位
						return md5Value.substring(4, 28);
					}else{
						return "";
					}
				}else{
					return "";
				}
			}else{
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 查询快捷支付订单
	 * @param queryOrderId
	 */
	public static Map<String, String> queryFastPayTrade(String queryOrderId,String dypass){
		String orderId = sdf.format(new Date()) + System.currentTimeMillis();
		try{
			String xmlNode = "charCode,Version,TradeType,ChannelID,bmMerId,timeStamp,orderId,queryOrderId";
			Map<String, String> reqMap = new HashMap<String, String>();
			reqMap.put("charCode", "gbk");
			reqMap.put("Version", "2.0.1");
			reqMap.put("TradeType", "0610");
			reqMap.put("ChannelID", merid);
			reqMap.put("bmMerId", merid);			
			reqMap.put("timeStamp", sdf.format(new Date()));
			reqMap.put("orderId", orderId);
			reqMap.put("queryOrderId", queryOrderId);
			reqMap.put("keyMd5", md5key);
			reqMap.put("key3Des", deskey);
			String xmlStr = NetXmlUtils.genXmlFromMapAndSign2(xmlNode, reqMap, reqMap.get("keyMd5"), "requestData");
			System.out.println("【请求】报文（明文）：" + xmlStr);
			byte[] xmlByte = DesCrypt.encrypt(xmlStr.getBytes("GBK"), reqMap.get("key3Des"));
			String xmlSend = Base.addZeroForNum(String.valueOf(xmlByte.length + 16), 4) + reqMap.get("ChannelID")
					+ Base64.encodeBase64String(xmlByte);
			System.out.println("【请求】报文（密文）：" + xmlSend);
			// https发起请求
			HttpClient hc = new HttpClient(netPay_url, 60000, keyStorePath, password, keyStorePath, password);
			String res = hc.submitUrl(xmlSend);
			System.out.println("【返回】报文（密文）：" + res);
			// 解密报文
			Map<String, String> decryptMap = RechargeTest.decryptData(res, dypass, md5key);
			System.out.println("【返回】报文（明文）：" + decryptMap);
			return decryptMap;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 退款接口
	 * @param refundOrderId
	 * @param dypass
	 * @param amount
	 * @return
	 */
	public static Map<String,String> refundApply(String refundOrderId,String amount,String dypass){
		String orderId = sdf.format(new Date()) + System.currentTimeMillis();
		try {
			String xmlNode = "charCode,Version,TradeType,ChannelID,bmMerId,timeStamp,orderId,refundOrderId,refundAmount,fileId1,fileId2,fileId3";
			Map<String,String> reqMap = new HashMap<String,String>();
			reqMap.put("charCode", "gbk");
			reqMap.put("Version", "2.0.1");
			reqMap.put("TradeType", "0606");
			reqMap.put("ChannelID", merid);
			reqMap.put("bmMerId", merid);
			reqMap.put("timeStamp", sdf.format(new Date()));
			reqMap.put("orderId", orderId);
			reqMap.put("refundOrderId", refundOrderId);
			float sessionmoney = Float.parseFloat(amount) * 100;
			reqMap.put("refundAmount", String.format("%.0f", sessionmoney));
			reqMap.put("keyMd5", md5key);
			reqMap.put("key3Des", deskey);
			
			String xmlStr = NetXmlUtils.genXmlFromMapAndSign2(xmlNode, reqMap, reqMap.get("keyMd5"), "requestData");
			System.out.println("【请求】报文（明文）：" + xmlStr);
			byte[] xmlByte = DesCrypt.encrypt(xmlStr.getBytes("GBK"), reqMap.get("key3Des"));
			String xmlSend = Base.addZeroForNum(String.valueOf(xmlByte.length + 16), 4) + reqMap.get("ChannelID")
					+ Base64.encodeBase64String(xmlByte);
			System.out.println("【请求】报文（密文）：" + xmlSend);
			
			// https发起请求
			HttpClient hc = new HttpClient(netPay_url, 60000, keyStorePath, password, keyStorePath, password);
			String res = hc.submitUrl(xmlSend);
			System.out.println("【返回】报文（密文）：" + new String(res.getBytes("gbk"),"utf-8"));
			
			// 解密报文
			Map<String, String> decryptMap = RechargeTest.decryptData(res,dypass, md5key);
			System.out.println("【返回】报文（明文）：" + decryptMap);
			return decryptMap;
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return null;
	}
	
	/***
	 * 获取动态秘钥
	 * TODO 建议通过单线程不大于2小时定时请求
	 * 取md5值的中间24位
	 * @return
	 */
	public static String getDyPass(String orderId,String tradeType) {
		String xmlNode = "charCode,Version,TradeType,ChannelID,orderId,bmMerId,timeStamp";
		Map<String, String> reqMap = new HashMap<String, String>();
		reqMap.put("charCode", "gbk");
		reqMap.put("Version", "2.0.1");
		reqMap.put("TradeType", tradeType);
		reqMap.put("ChannelID", merid);
		reqMap.put("bmMerId", merid);
		reqMap.put("orderId", orderId);
		reqMap.put("timeStamp", sdf.format(new Date()));
		reqMap.put("keyMd5", md5key);
		reqMap.put("key3Des", deskey);
		try {
			String xmlStr = NetXmlUtils.genXmlFromMapAndSign2(xmlNode, reqMap, reqMap.get("keyMd5"), "requestData");
			System.out.println("动态秘钥【请求】报文（明文）：" + xmlStr);
			byte[] xmlByte = DesCrypt.encrypt(xmlStr.getBytes("GBK"), reqMap.get("key3Des"));
			String xmlSend = Base.addZeroForNum(String.valueOf(xmlByte.length + 16), 4) + reqMap.get("ChannelID") 
					+ Base64.encodeBase64String(xmlByte);
			System.out.println("动态秘钥【请求】报文（密文）：" + xmlSend);
			// https发起请求
			HttpClient hc = new HttpClient(dyPass_url, 60000, keyStorePath, password, keyStorePath, password);
			String res = hc.submitUrl(xmlSend);
			System.out.println("动态秘钥【返回】报文（密文）：" + res);
			// 解密报文
			Map<String, String> decryptMap = RechargeTest.decryptData(res, deskey, md5key);
			System.out.println("动态秘钥【返回】报文（明文）：" + decryptMap);
			if (!decryptMap.isEmpty()) {
				if ("00".equals(decryptMap.get("resultCode"))) {
					String random = decryptMap.get("random");
					if(!StringUtils.isEmpty(random)){
						String md5Value = Base.md5s(deskey + random);
						// TODO 取md5值的中间24位
						return md5Value.substring(4, 28);
					}else{
						return "";
					}
				}else{
					return "";
				}
			}else{
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
