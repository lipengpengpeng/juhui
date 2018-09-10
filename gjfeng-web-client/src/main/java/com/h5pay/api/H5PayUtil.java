package com.h5pay.api;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.webpay.utils.MD5;
import com.webpay.utils.SignUtils;
import com.webpay.utils.XmlUtils;
import com.wechat.popular.api.JsonUtil;

import cc.messcat.gjfeng.common.constant.CommonProperties;
import cc.messcat.gjfeng.common.util.DateHelper;
import cc.messcat.gjfeng.common.util.HttpXmlClient;
import cc.messcat.gjfeng.common.util.JsonMd5Utils;
import cc.messcat.gjfeng.common.util.RandUtil;
import cc.messcat.gjfeng.entity.GjfMemberInfo;
import cc.messcat.gjfeng.entity.GjfWeiXinPayInfo;
import net.sf.json.JSONObject;

public class H5PayUtil {

	/**
	 * H5支付
	 * 
	 * @param request
	 * @param resp
	 * @param memberInfo
	 * @param orderAddress
	 * @param amt
	 * @param out_trade_no
	 * @param pName
	 * @param pDesc
	 * @return
	 */
	public static Map<String, String> H5PayResult(HttpServletRequest request, HttpServletResponse resp,
			GjfMemberInfo memberInfo, String amt, String out_trade_no, String pName, String pDesc, String retUrl) {
		JSONObject reqJson = new JSONObject();
		// Map<String, String> reqJson=new HashMap<>();
		reqJson.put("svcName", "paygate.cashier");
		//reqJson.put("svcName", "paygate.thirdpay");
		reqJson.put("merId",  CommonProperties.GJFENG_H5_MERID);
		reqJson.put("merchOrderId", out_trade_no);

		float sessionmoney = Float.parseFloat(amt) * 100;
		reqJson.put("amt", String.format("%.0f", sessionmoney));
		reqJson.put("ccy", "CNY");
		SimpleDateFormat adf = new SimpleDateFormat("YYYYMMDD HH:MM:SS");
		reqJson.put("tranTime", adf.format(new Date()));
		reqJson.put("merUrl", CommonProperties.GJFENG_H5_MERURL);
		reqJson.put("bankName", memberInfo.getName());
		reqJson.put("bankId", memberInfo.getIdCard());
		reqJson.put("retUrl", retUrl);
		reqJson.put("pName", pName);
		reqJson.put("pCat", "7");
		reqJson.put("pDesc", pDesc);
		reqJson.put("mer_cust_id", memberInfo.getId().toString());
		reqJson.put("terminalType", "3");
		reqJson.put("productType", "3");
		reqJson.put("userIp", request.getRemoteHost());
		reqJson.put("terminalId", request.getHeader("User-Agent"));
		reqJson.put("rcvName", "凤凰云商");
		reqJson.put("rcvMobile", memberInfo.getMobile());
		reqJson.put("merData", "用户购买商品");

		reqJson.put("rcvAdress", "广州省广州市越秀区丽丰中心");
		reqJson.put("regMail", "3089459827@qq.com");
		SimpleDateFormat ads = new SimpleDateFormat("YYYYMMDDHHMMSS");
		reqJson.put("regTime", ads.format(new Date()));
		reqJson.put("key",  CommonProperties.GJFENG_H5_KEY);
		String md5value = JsonMd5Utils.json2Md5(reqJson,
				"svcName,merId,merchOrderId,amt,ccy,tranTime,merUrl,mer_cust_id,terminalType,terminalId,productType,userIp,rcvMobile,regMail,regTime,key");
		reqJson.put("md5value", md5value);
		reqJson.remove("key");
		return JsonUtil.jsonObjectToMap(reqJson);
	}

	/**
	 * 微信支付
	 * 
	 * @param out_trade_no
	 * @param total_fee
	 */
	public static String weiXinPay(GjfWeiXinPayInfo payInfo, HttpServletRequest request, String out_trade_no,
			String total_fee, String body, String notifyUrl, String callBackUrl, String openId)
					throws ServletException, IOException {
		Map<String, String> map = new HashMap<String, String>();
		// 获取微信基本配置信息
		System.out.println("----" + payInfo.getMchId() + "*******" + payInfo.getPayKey());
		map.put("service", "pay.weixin.jspay");
		map.put("mch_id", payInfo.getMchId());
		map.put("out_trade_no", out_trade_no);
		map.put("device_info", request.getRemoteHost());
		map.put("body", body);
		// String openid = (String) session.getAttribute("openid");
		System.out.println(openId);
		map.put("sub_openid", openId);
		float sessionmoney = Float.parseFloat(total_fee) * 100;
		map.put("total_fee", String.format("%.0f", sessionmoney));
		map.put("mch_create_ip", request.getRemoteHost());
		map.put("notify_url", notifyUrl);
		map.put("callback_url", callBackUrl);
		map.put("nonce_str", UUID.randomUUID().toString().replace("-", ""));
		Map<String, String> params = SignUtils.paraFilter(map);
		StringBuilder buf = new StringBuilder((params.size() + 1) * 10);
		SignUtils.buildPayParams(buf, params, false);
		String preStr = buf.toString();
		String sign = MD5.sign(preStr, "&key=" + payInfo.getPayKey(), "utf-8");
		System.out.println(sign);
		map.put("sign", sign);

		CloseableHttpResponse response = null;
		CloseableHttpClient client = null;
		String res = null;
		Map<String, String> resultMap = null;
		try {
			HttpPost httpPost = new HttpPost("https://pay.swiftpass.cn/pay/gateway");
			StringEntity entityParams = new StringEntity(XmlUtils.parseXML(map), "utf-8");
			System.out.println(entityParams);
			httpPost.setEntity(entityParams);
			httpPost.setHeader("Content-Type", "text/xml;charset=ISO-8859-1");
			client = HttpClients.createDefault();
			response = client.execute(httpPost);
			System.out.println(response.getEntity());
			if (response != null && response.getEntity() != null) {
				resultMap = XmlUtils.toMap(EntityUtils.toByteArray(response.getEntity()), "utf-8");
				System.out.println(resultMap);
				res = XmlUtils.toXml(resultMap);

				if (!SignUtils.checkParam(resultMap, payInfo.getPayKey())) {
					res = "验证签名不通过";
				} else {
					if ("0".equals(resultMap.get("status")) && "0".equals(resultMap.get("result_code"))) {
						res = "ok";
						String token_id = resultMap.get("token_id");
						System.out.println("-----调用成功" + token_id);
						return token_id;
					}
				}
			} else {
				res = "操作失败";
			}
		} catch (Exception e) {
			res = "网络异常,请重试";
		} finally {
			if (response != null) {
				response.close();
			}
			if (client != null) {
				client.close();
			}
		}
		return null;
	}

	/**
	 * 查询订单
	 * 
	 * @return
	 */
	public static String queryH5Order(HttpServletRequest req, String tradeNo) throws IOException {

		JSONObject reqJson = new JSONObject();
		// Map<String, String> reqJson = new HashMap<String, String>();
		reqJson.put("svcName", "paygate.resultqry");
		reqJson.put("merId", CommonProperties.GJFENG_H5_MERID);
		reqJson.put("merchOrderId", tradeNo);
		SimpleDateFormat ads = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
		reqJson.put("tranTime", ads.format(new Date()));
		reqJson.put("key", CommonProperties.GJFENG_H5_KEY);
		// 计算MD5的值
		String keys = "svcName,merId,merchOrderId,tranTime,key";
		String md5value = JsonMd5Utils.json2Md5(reqJson, keys);
		reqJson.put("md5value", md5value);
		reqJson.remove("key");
		System.out.println("请求参数------>" + reqJson.toString());
		// 发送请求参数
		// String retStr=readContentFromPost(reqJson);
		String xml = HttpXmlClient.post("http://pay.ronghexx.com/fm/", JsonUtil.jsonObjectToMap(reqJson));
		return xml;
	}

	// 实名认证
	public static String realName(String payAcc, String payName, String identityCode) {
		JSONObject reqJson = new JSONObject();
		reqJson.put("svcName", "paygate.realnameauth");
		reqJson.put("merId", CommonProperties.GJFENG_H5_MERID);
		SimpleDateFormat ads = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
		reqJson.put("merchOrderId", DateHelper.dataToString(new Date(), "yyyyMMddHHmmss" + RandUtil.getRandomStr(6)));
		reqJson.put("tranTime", ads.format(new Date()));

		reqJson.put("payAcc", payAcc);
		reqJson.put("payName", payName);
		reqJson.put("cardType", "0");
		reqJson.put("payBankInsCode", "");
		reqJson.put("identityType", "0");
		reqJson.put("identityCode", identityCode);
		reqJson.put("provNo", "");
		reqJson.put("key", CommonProperties.GJFENG_H5_KEY);
		// 计算MD5的值
		String keys = "svcName,merId,merchOrderId,tranTime,payAcc,payName,cardType,payBankInsCode,identityType,identityCode,provNo,key";
		String md5value = JsonMd5Utils.json2Md5(reqJson, keys);
		reqJson.put("md5value", md5value);
		reqJson.remove("key");
		String xml = HttpXmlClient.post("http://pay.ronghexx.com/fm/", JsonUtil.jsonObjectToMap(reqJson));
		return xml;
	}

	/**
	 * 微信扫码支付
	 */
	public static String WeiSweepPay(GjfWeiXinPayInfo payInfo, HttpServletRequest request, HttpServletResponse resp,
			GjfMemberInfo memberInfo, String amt, String out_trade_no, String pName, String pDesc, String retUrl) {
		JSONObject reqJson = new JSONObject();
		reqJson.put("svcName", "paygate.thirdpay");
		reqJson.put("merId", CommonProperties.GJFENG_H5_MERID);
		reqJson.put("merchOrderId", out_trade_no);
		float sessionmoney = Float.parseFloat(amt) * 100;
		reqJson.put("amt", String.format("%.0f", sessionmoney));
		reqJson.put("ccy", "CNY");
		SimpleDateFormat adf = new SimpleDateFormat("YYYYMMDD HH:MM:SS");
		reqJson.put("tranTime", adf.format(new Date()));
		reqJson.put("tranChannel", "4000221");
		reqJson.put("retUrl", retUrl);
		reqJson.put("merUserId", memberInfo.getId().toString());
		reqJson.put("tranType", "WEIXIN_NATIVE");
		reqJson.put("terminalType", "1");
		reqJson.put("terminalId", request.getHeader("User-Agent"));
		reqJson.put("productType", "3");
		reqJson.put("userIp", request.getRemoteHost());
		reqJson.put("key", CommonProperties.GJFENG_H5_KEY);
		reqJson.put("pDesc", pDesc);
		String md5value = JsonMd5Utils.json2Md5(reqJson,
				"svcName,merId,merchOrderId,amt,ccy,tranTime,tranChannel,retUrl,merUserId,tranType,terminalType,terminalId,productType,userIp,key");
		reqJson.put("md5value", md5value);
		reqJson.remove("key");
		String xml = HttpXmlClient.post("http://pay.ronghexx.com/fm/", JsonUtil.jsonObjectToMap(reqJson));
		return xml;
	}

	// 实名认证
	public static String olyrealName(String payName, String identityCode) {
		String host = "http://aliyun.id98.cn";
		String path = "/idcard";
		String method = "GET";
		String appcode = "d9f0db5d0a914801b7f979d989e35304";
		Map<String, String> headers = new HashMap<String, String>();
		// 最后在header中的格式(中间是英文空格)为Authorization:APPCODE
		// 83359fd73fe94948385f570e3c139105
		headers.put("Authorization", "APPCODE " + appcode);
		Map<String, String> querys = new HashMap<String, String>();
		querys.put("cardno", identityCode);
		querys.put("name", payName);

		try {
			/**
			 * 重要提示如下: HttpUtils请从
			 * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/
			 * src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java 下载
			 *
			 * 相应的依赖请参照
			 * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/
			 * pom.xml
			 */
			HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
			System.out.println(response.toString());
			// 获取response的body
			//System.out.println(EntityUtils.toString(response.getEntity()));
			return EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 支付渠道查询
	 */
	public static String payChannel() {
		JSONObject reqJson = new JSONObject();
		reqJson.put("svcName", "paygate.tranChannelQry");
		reqJson.put("merId", CommonProperties.GJFENG_H5_MERID);
		reqJson.put("payType", "2");		
		SimpleDateFormat adf = new SimpleDateFormat("YYYYMMDD HH:MM:SS");
		reqJson.put("tranTime", adf.format(new Date()));		
		reqJson.put("key", CommonProperties.GJFENG_H5_KEY);
		String md5value = JsonMd5Utils.json2Md5(reqJson,
				"svcName,merId,payType,tranTime,key");
		reqJson.put("md5value", md5value);
		reqJson.remove("key");
		String xml = HttpXmlClient.post("http://pay.juhefu.cn/fm/", JsonUtil.jsonObjectToMap(reqJson));
		return xml;
	}
	
	/**
	 * 微信app支付
	 * @param request
	 * @param resp
	 * @param memberInfo
	 * @param amt
	 * @param out_trade_no
	 * @param pName
	 * @param pDesc
	 * @param retUrl
	 * @return
	 * @throws IOException 
	 */
	public static Map<String, String> weixinAppPay(GjfWeiXinPayInfo payInfo,HttpServletRequest request,GjfMemberInfo memberInfo, String amt, String out_trade_no, String retUrl) throws IOException{
		Map<String, String> reqJson = new HashMap<String, String>();
		reqJson.put("service", "unified.trade.pay");
		reqJson.put("mch_id", payInfo.getMchId());
		reqJson.put("charset", "UTF-8");
		reqJson.put("sign_type", "MD5");
		reqJson.put("out_trade_no", out_trade_no);
		reqJson.put("body", "购买商品");
		float sessionmoney = Float.parseFloat(amt) * 100;
		reqJson.put("total_fee", String.format("%.0f", sessionmoney));
		reqJson.put("mch_create_ip", request.getRemoteHost());
		reqJson.put("notify_url", retUrl);
		reqJson.put("nonce_str", UUID.randomUUID().toString().replace("-", ""));
		
		 Map<String,String> params = SignUtils.paraFilter(reqJson);
         StringBuilder buf = new StringBuilder((params.size() +1) * 10);
         SignUtils.buildPayParams(buf,params,false);
         String preStr = buf.toString();
         String sign = MD5.sign(preStr, "&key=" + payInfo.getPayKey(), "utf-8");
         reqJson.put("sign", sign);
		/*String sign0 = SignatureUtil.generateSign(reqJson, "7daa4babae15ae17eee90c9e");
		reqJson.put("sign", sign0);*/
		//String xml = HttpXmlClient.post("https://pay.swiftpass.cn/pay/gateway", JsonUtil.jsonObjectToMap(reqJson));
		CloseableHttpResponse response = null;
		CloseableHttpClient client = null;
		String res = null;
		Map<String, String> resultMap = null;
		try {
			HttpPost httpPost = new HttpPost("https://pay.swiftpass.cn/pay/gateway");
			StringEntity entityParams = new StringEntity(XmlUtils.parseXML(reqJson), "utf-8");
			System.out.println(entityParams);
			httpPost.setEntity(entityParams);
			httpPost.setHeader("Content-Type", "text/xml;charset=ISO-8859-1");
			client = HttpClients.createDefault();
			response = client.execute(httpPost);
			System.out.println(response.getEntity());
			if (response != null && response.getEntity() != null) {
				resultMap = XmlUtils.toMap(EntityUtils.toByteArray(response.getEntity()), "utf-8");
				System.out.println(resultMap);
				res = XmlUtils.toXml(resultMap);
				if (!SignUtils.checkParam(resultMap,  payInfo.getPayKey())) {
					res = "验证签名不通过";
				} else {
					 if("0".equals(resultMap.get("status")) && "0".equals(resultMap.get("result_code"))){
                        //Map<String, String> reMap=new HashMap<String, String>();
                         String token_id = resultMap.get("token_id");
                         String services = resultMap.get("services");
//                         reMap.put("token_id", token_id);
//                         reMap.put("services", services);
                         return resultMap;
                     }else{
                    	 request.setAttribute("result", res);
                    	 return resultMap;
                     }
				}
			} else {
				res = "操作失败";
			}
		} catch (Exception e) {
			res = "网络异常,请重试";
		} finally {
			if (response != null) {
				response.close();
			}
			if (client != null) {
				client.close();
			}
		}
		return null;
	}
	
	/**
	 * 微信扫码支付
	 */
	public static String weixinSweepPay(String payMchId,String peyKey,HttpServletRequest request, String amt, String out_trade_no,String body,String retUrl) throws IOException{
		Map<String, String> reqJson = new HashMap<String, String>();
		reqJson.put("service", "pay.weixin.native");
		reqJson.put("mch_id", payMchId);
		reqJson.put("charset", "UTF-8");
		reqJson.put("sign_type", "MD5");
		reqJson.put("version", "1.0");
		reqJson.put("out_trade_no", out_trade_no);
		reqJson.put("body", body);
		float sessionmoney = Float.parseFloat(amt) * 100;
		reqJson.put("total_fee", String.format("%.0f", sessionmoney));
		reqJson.put("mch_create_ip", request.getRemoteHost());
		reqJson.put("notify_url", retUrl);
		reqJson.put("nonce_str", UUID.randomUUID().toString().replace("-", ""));
		
		 Map<String,String> params = SignUtils.paraFilter(reqJson);
         StringBuilder buf = new StringBuilder((params.size() +1) * 10);
         SignUtils.buildPayParams(buf,params,false);
         String preStr = buf.toString();
         String sign = MD5.sign(preStr, "&key=" + peyKey, "utf-8");
         reqJson.put("sign", sign);
		/*String sign0 = SignatureUtil.generateSign(reqJson, "7daa4babae15ae17eee90c9e");
		reqJson.put("sign", sign0);*/
		//String xml = HttpXmlClient.post("https://pay.swiftpass.cn/pay/gateway", JsonUtil.jsonObjectToMap(reqJson));
		CloseableHttpResponse response = null;
		CloseableHttpClient client = null;
		String res = null;
		Map<String, String> resultMap = null;
		try {
			HttpPost httpPost = new HttpPost("https://pay.swiftpass.cn/pay/gateway");
			StringEntity entityParams = new StringEntity(XmlUtils.parseXML(reqJson), "utf-8");
			System.out.println(entityParams);
			httpPost.setEntity(entityParams);
			httpPost.setHeader("Content-Type", "text/xml;charset=ISO-8859-1");
			client = HttpClients.createDefault();
			response = client.execute(httpPost);
			System.out.println(response.getEntity());
			if (response != null && response.getEntity() != null) {
				resultMap = XmlUtils.toMap(EntityUtils.toByteArray(response.getEntity()), "utf-8");
				System.out.println(resultMap);
				res = XmlUtils.toXml(resultMap);
				if (!SignUtils.checkParam(resultMap, peyKey)) {
					res = "验证签名不通过";
				} else {
					String code_img_url="";
					 if("0".equals(resultMap.get("status")) && "0".equals(resultMap.get("result_code"))){                         
                         code_img_url = resultMap.get("code_img_url");
                         return code_img_url;
                     }else{
                    	 return null; 
                     }
				}
			} else {
				res = "操作失败";
			}
		} catch (Exception e) {
			res = "网络异常,请重试";
		} finally {
			if (response != null) {
				response.close();
			}
			if (client != null) {
				client.close();
			}
		}
		return null;
	}
	
	
	/**
	 * 支付宝扫码支付
	 */
	public static String alipaySweepPay(String payMchId,String peyKey,HttpServletRequest request, String amt, String out_trade_no,String body,String retUrl) throws IOException{
		Map<String, String> reqJson = new HashMap<String, String>();
		reqJson.put("service", "pay.alipay.native");
		reqJson.put("mch_id", payMchId);
		reqJson.put("charset", "UTF-8");
		reqJson.put("sign_type", "MD5");
		reqJson.put("version", "1.0");
		reqJson.put("out_trade_no", out_trade_no);
		reqJson.put("body", body);
		float sessionmoney = Float.parseFloat(amt) * 100;
		reqJson.put("total_fee", String.format("%.0f", sessionmoney));
		reqJson.put("mch_create_ip", request.getRemoteHost());
		reqJson.put("notify_url", retUrl);
		reqJson.put("nonce_str", UUID.randomUUID().toString().replace("-", ""));
		
		 Map<String,String> params = SignUtils.paraFilter(reqJson);
         StringBuilder buf = new StringBuilder((params.size() +1) * 10);
         SignUtils.buildPayParams(buf,params,false);
         String preStr = buf.toString();
         String sign = MD5.sign(preStr, "&key=" + peyKey, "utf-8");
         reqJson.put("sign", sign);
		CloseableHttpResponse response = null;
		CloseableHttpClient client = null;
		String res = null;
		Map<String, String> resultMap = null;
		try {
			HttpPost httpPost = new HttpPost("https://pay.swiftpass.cn/pay/gateway");
			StringEntity entityParams = new StringEntity(XmlUtils.parseXML(reqJson), "utf-8");
			System.out.println(entityParams);
			httpPost.setEntity(entityParams);
			httpPost.setHeader("Content-Type", "text/xml;charset=ISO-8859-1");
			client = HttpClients.createDefault();
			response = client.execute(httpPost);
			System.out.println(response.getEntity());
			if (response != null && response.getEntity() != null) {
				resultMap = XmlUtils.toMap(EntityUtils.toByteArray(response.getEntity()), "utf-8");
				System.out.println(resultMap);
				res = XmlUtils.toXml(resultMap);
				if (!SignUtils.checkParam(resultMap, peyKey)) {
					res = "验证签名不通过";
				} else {
					String code_img_url="";
					 if("0".equals(resultMap.get("status")) && "0".equals(resultMap.get("result_code"))){                         
                         code_img_url = resultMap.get("code_img_url");
                         return code_img_url;
                     }else{
                    	 return null; 
                     }
				}
			} else {
				res = "操作失败";
			}
		} catch (Exception e) {
			res = "网络异常,请重试";
		} finally {
			if (response != null) {
				response.close();
			}
			if (client != null) {
				client.close();
			}
		}
		return null;
	}
	
	
	/**
	 * 扫码支付查询
	 */
	public static Map<String, Object> alipaySweepPay(String payMchId,String peyKey,String out_trade_no) throws IOException{
		Map<String, String> reqJson = new HashMap<String, String>();
		reqJson.put("service", "unified.trade.query");
		reqJson.put("mch_id", payMchId);
		reqJson.put("charset", "UTF-8");
		reqJson.put("sign_type", "MD5");
		reqJson.put("version", "1.0");
		reqJson.put("out_trade_no", out_trade_no);
		reqJson.put("nonce_str", UUID.randomUUID().toString().replace("-", ""));
		
		 Map<String,String> params = SignUtils.paraFilter(reqJson);
         StringBuilder buf = new StringBuilder((params.size() +1) * 10);
         SignUtils.buildPayParams(buf,params,false);
         String preStr = buf.toString();
         String sign = MD5.sign(preStr, "&key=" + peyKey, "utf-8");
         reqJson.put("sign", sign);
		CloseableHttpResponse response = null;
		CloseableHttpClient client = null;
		String res = null;
		Map<String, String> resultMap = null;
		try {
			HttpPost httpPost = new HttpPost("https://pay.swiftpass.cn/pay/gateway");
			StringEntity entityParams = new StringEntity(XmlUtils.parseXML(reqJson), "utf-8");
			System.out.println(entityParams);
			httpPost.setEntity(entityParams);
			httpPost.setHeader("Content-Type", "text/xml;charset=ISO-8859-1");
			client = HttpClients.createDefault();
			response = client.execute(httpPost);
			System.out.println(response.getEntity());
			if (response != null && response.getEntity() != null) {
				resultMap = XmlUtils.toMap(EntityUtils.toByteArray(response.getEntity()), "utf-8");
				System.out.println(resultMap);
				res = XmlUtils.toXml(resultMap);
				if (!SignUtils.checkParam(resultMap,  peyKey)) {
					res = "验证签名不通过";
				} else {			
					 if("0".equals(resultMap.get("status")) && "0".equals(resultMap.get("result_code"))){                         
                         Map<String, Object> reqMap=new HashMap<>();
                         reqMap.put("out_trade_no", resultMap.get("out_trade_no"));
                         reqMap.put("transaction_id", resultMap.get("transaction_id"));
                         return reqMap;
                     }else{
                    	 return null; 
                     }
				}
			} else {
				res = "操作失败";
			}
		} catch (Exception e) {
			res = "网络异常,请重试";
		} finally {
			if (response != null) {
				response.close();
			}
			if (client != null) {
				client.close();
			}
		}
		return null;
	}
	
	
	/**
	 * 支付宝app支付
	 */
	public static Map<String, String> alipayAppPay(GjfMemberInfo memberInfo,HttpServletRequest request, String amt, String out_trade_no,String goodName,String body,String retUrl) throws IOException{
		JSONObject reqJson = new JSONObject();
		reqJson.put("svcName", "paygate.thirdpay");
		reqJson.put("merId", CommonProperties.GJFENG_H5_MERID);
		reqJson.put("merchOrderId", out_trade_no);
		float sessionmoney = Float.parseFloat(amt) * 100;
		reqJson.put("amt",String.format("%.0f", sessionmoney));
		reqJson.put("ccy", "CNY");
		SimpleDateFormat adf = new SimpleDateFormat("YYYYMMDD HH:MM:SS");
		reqJson.put("tranTime", adf.format(new Date()));		
		reqJson.put("tranChannel", "4000117");
		reqJson.put("merUrl", CommonProperties.GJFENG_H5_MERURL);
		reqJson.put("retUrl", retUrl);		
		reqJson.put("pName", goodName);
		reqJson.put("pCat", "7");
		reqJson.put("pDesc", body);
		reqJson.put("merData", "用户购买商品");
		reqJson.put("tranType", "ALIPAYSCAN");
		reqJson.put("merUserId", "1");
		reqJson.put("terminalType", "3");
		reqJson.put("terminalId",  request.getHeader("User-Agent"));
		reqJson.put("productType", "4");
		reqJson.put("userIp", request.getRemoteHost());
		reqJson.put("key", CommonProperties.GJFENG_H5_KEY);
		reqJson.put("mer_cust_id", memberInfo.getId().toString());
		reqJson.put("rcvName", "凤凰云商");
		reqJson.put("rcvMobile",  memberInfo.getMobile());
		reqJson.put("rcvAdress", "广州省广州市越秀区丽丰中心");
		reqJson.put("regMail", "3089459827@qq.com");
		SimpleDateFormat ads = new SimpleDateFormat("YYYYMMDDHHMMSS");
		reqJson.put("regTime", ads.format(new Date()));
		String md5value = JsonMd5Utils.json2Md5(reqJson,
				"svcName,merId,merchOrderId,amt,ccy,tranTime,tranChannel,"
				+ "retUrl,merUserId,tranType,terminalType,terminalId,productType,userIp,key");
		reqJson.put("md5value", md5value);
		reqJson.remove("key");	
		
		
		// 发送请求参数
		// String retStr=readContentFromPost(reqJson);
		/*String xml = HttpXmlClient.post("https://cashier.ronghexx.com/h5pay/", JsonUtil.jsonObjectToMap(reqJson));
		return xml;*/
		return JsonUtil.jsonObjectToMap(reqJson);
	}
			
}
