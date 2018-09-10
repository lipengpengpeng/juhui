package com.alipay.direct.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import com.alipay.direct.config.AlipayConfig;
import com.alipay.direct.sign.MD5;

/* *
 *类名：AlipayNotify
 *功能：支付宝通知处理类
 *详细：处理支付宝各接口通知返回
 *版本：3.3
 *日期：2012-08-17
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考

 *************************注意*************************
 *调试通知返回时，可查看或改写log日志的写入TXT里的数据，来检查通知返回是否正常
 */
public class AlipayNotify {

	/**
	 * 支付宝消息验证地址
	 */
	private static final String HTTPS_VERIFY_URL = "https://mapi.alipay.com/gateway.do?service=notify_verify&";

	/**
	 * 验证消息是否是支付宝发出的合法消息
	 * 
	 * @param params
	 *            通知返回来的参数数组
	 * @return 验证结果
	 */
	public static boolean verify(Map<String, String> params) {

		// 判断responsetTxt是否为true，isSign是否为true
		// responsetTxt的结果不是true，与服务器设置问题、合作身份者ID、notify_id一分钟失效有关
		// isSign不是true，与安全校验码、请求时的参数格式（如：带自定义参数等）、编码格式有关
		String responseTxt = "true";
		if (params.get("notify_id") != null) {
			String notify_id = params.get("notify_id");
			responseTxt = verifyResponse(notify_id);
		}
		String sign = "";
		if (params.get("sign") != null) {
			sign = params.get("sign");
		}
		boolean isSign = getSignVeryfy(params, sign);

		// 写日志记录（若要调试，请取消下面两行注释）
		String sWord = "responseTxt=" + responseTxt + "\n isSign=" + isSign
				+ "\n 返回回来的参数：" + AlipayCore.createLinkString(params);
		AlipayCore.logResult(sWord);

		if (isSign && responseTxt.equals("true")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 根据反馈回来的信息，生成签名结果
	 * 
	 * @param Params
	 *            通知返回来的参数数组
	 * @param sign
	 *            比对的签名结果
	 * @return 生成的签名结果
	 */
	private static boolean getSignVeryfy(Map<String, String> Params, String sign) {
		// 过滤空值、sign与sign_type参数
		Map<String, String> sParaNew = AlipayCore.paraFilter(Params);
		// 获取待签名字符串
		String preSignStr = AlipayCore.createLinkString(sParaNew);
		// 获得签名验证结果
		boolean isSign = false;
		
		if (AlipayConfig.sign_type.equals("MD5")) {
			isSign = MD5.verify(preSignStr, sign, AlipayConfig.key,
					AlipayConfig.input_charset);
		}
		
		return isSign;
	}

	/**
	 * 获取远程服务器ATN结果,验证返回URL
	 * 
	 * @param notify_id
	 *            通知校验ID
	 * @return 服务器ATN结果 验证结果集： invalid命令参数不对 出现这个错误，请检测返回处理中partner和key是否为空 true
	 *         返回正确信息 false 请检查防火墙或者是服务器阻止端口问题以及验证时间是否超过一分钟
	 */
	public static String verifyResponse(String notify_id) {
		// 获取远程服务器ATN结果，验证是否是支付宝服务器发来的请求

		String partner = AlipayConfig.partner;
		String veryfy_url = HTTPS_VERIFY_URL + "partner=" + partner
				+ "&notify_id=" + notify_id;

		return checkUrl(veryfy_url);
	}

	/**
	 * 获取远程服务器ATN结果
	 * 
	 * @param urlvalue
	 *            指定URL路径地址
	 * @return 服务器ATN结果 验证结果集： invalid命令参数不对 出现这个错误，请检测返回处理中partner和key是否为空 true
	 *         返回正确信息 false 请检查防火墙或者是服务器阻止端口问题以及验证时间是否超过一分钟
	 */
	private static String checkUrl(String urlvalue) {
		String inputLine = "";

		try {
			URL url = new URL(urlvalue);
			HttpURLConnection urlConnection = (HttpURLConnection) url
					.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					urlConnection.getInputStream()));
			inputLine = in.readLine().toString();
		} catch (Exception e) {
			e.printStackTrace();
			inputLine = "";
		}
		return inputLine;
	}
	public static boolean verifyAppAlipay(Map<String,String> params,String sign){
		String content=OrderInfoUtil2_0.buildOrderParam(params, false);
//		AlipayCore.logResult("content：" + content);
//		AlipayCore.logResult("sign：" + sign);
		return SignUtils.verify(content, sign, AlipayConfig.ALIPAY_RSA_PUBLIC, AlipayConfig.input_charset);
		
	}
	
	public static void main(String[] args) {
		String content="body=订单编号2016110310150200000&buyer_email=1475002072@qq.com&buyer_id=2088112991056945&discount=0.00&gmt_create=2016-11-03 10:15:13&is_total_fee_adjust=Y&notify_id=9e3bf784873c48cedef644172d40e27n96&notify_time=2016-11-03 10:15:13&notify_type=trade_status_sync&out_trade_no=2016110310150200000&payment_type=1&price=0.01&quantity=1&seller_email=yangletang69@163.com&seller_id=2088911183985360&sign_type=RSA&subject=下单成功&total_fee=0.01&trade_no=2016110321001004940281703683&trade_status=WAIT_BUYER_PAY&use_coupon=N";
		String sign="YrmuOFwvN6+CDBHJFiEUjHBFpfK6GLGMI1XbRBjc5Hq7aNhlZlFg1ACcC2DP2gn/JWM/QBfhRMSrxg/B5Tmaxx8U2oQqSQTKJi90c1DHjsQF7HYl+q+F84LmFCSZKJrqXKVUEvEP2574ZAdTYbW7mhvYR7n5Dgg7mrY47qdhmi0=";
//		boolean a= SignUtils.verify(content, sign, AlipayConfig.ALIPAY_RSA_PUBLIC, AlipayConfig.input_charset);
//		System.out.println(a);
//		String content1="body=body&buyer_email=13416237125&buyer_id=2088902653882496&discount=0.00&gmt_create=2016-09-27 16:34:01&gmt_payment=2016-09-27 16:34:02&is_total_fee_adjust=N&notify_id=ff3f75a7db18028d38cd7b4d7e3dd82js6&notify_time=2016-09-27 16:34:02&notify_type=trade_status_sync&out_trade_no=58adc531ff784b55901bfd7810187867&payment_type=1&price=0.01&quantity=1&seller_email=yangletang69@163.com&seller_id=2088911183985360&subject=subject&total_fee=0.01&trade_no=2016092721001004490212663197&trade_status=TRADE_SUCCESS&use_coupon=N";
//		String content="body=订单编号705aae73d6e64a89ac989228b9d61ad1&buyer_email=13416237125&buyer_id=2088902653882496&discount=0.00&gmt_create=2016-09-27 09:54:47&gmt_payment=2016-09-27 09:54:48&is_total_fee_adjust=N&notify_id=06bb679ecd91de57e407ebea992eedejs6&notify_time=2016-09-27 09:58:35&notify_type=trade_status_sync&out_trade_no=705aae73d6e64a89ac989228b9d61ad1&payment_type=1&price=0.01&quantity=1&seller_email=yangletang69@163.com&seller_id=2088911183985360&subject=下订单成功&total_fee=0.01&trade_no=2016092721001004490204271817&trade_status=TRADE_SUCCESS&use_coupon=N";
//	    String sign="M069a+ZhopBVZPHHGt1Aqu+L/2iWG20h9dWryzdhGKCHY3EFcyqKDHV3rV4h52P7OI8Hx2TkGRntdyGTWca3dtM5bKzbJnE6pM829f6/EsxwLUZcZvRaF4fX28Cc/QRI5U/8BKraoCCW1IXbOgHHGBc42bH/aKMZmQdD9gMWL6w=";
	    
//	    String sign1="D42z9vi5G2OJuVuEK1fsOLU0sAg+XK6pV6a9s8ZLo/MNvYYdu3V72kSuETx5wX0mQ1VDTKx5+CPwVT7AcMUAeZEDScpnzdjYiByhMv0Lo0gGzKiYxUWnYV/shxNv3dEM14v6pBTtkse0TG89wE1if2/xfDCEk2suoBGTFdcKHGc=";
	    System.out.println(SignUtils.verify(content, sign, AlipayConfig.ALIPAY_RSA_PUBLIC, "utf-8"));
	   	   
	}
}
