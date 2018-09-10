package prePay;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cc.messcat.gjfeng.common.util.DateHelper;
import cc.messcat.gjfeng.common.util.RandUtil;
import cc.messcat.gjfeng.entity.GjfWeiXinPayInfo;
import cc.messcat.gjfeng.common.httpclient.Requests;

public class PrePayUtil {
	public static PosPrepayRe posPrePayRe(GjfWeiXinPayInfo payInfo,HttpServletRequest request,String out_trade_no,String total_fee,String body,String openId,String notofyUrl) {
		String access_token = payInfo.getPartner();
		String prePay_url = "http://pay.lcsw.cn/lcsw/pay/100/jspay";
		PosPrepayRe posPrePayRe = new PosPrepayRe();
		try {
			JSONObject jsonParam = new JSONObject();
			//Map<String, Object> jsonParam=new HashMap<>();
			jsonParam.put("pay_ver", "100");
			jsonParam.put("pay_type", "010");
			jsonParam.put("service_id", "012");
			jsonParam.put("merchant_no", payInfo.getMchId());
			jsonParam.put("terminal_id", payInfo.getPayKey());
			jsonParam.put("terminal_trace",out_trade_no);
			SimpleDateFormat adf = new SimpleDateFormat("yyyyMMddHHmmss");
			jsonParam.put("terminal_time", adf.format(new Date()));
			float sessionmoney = Float.parseFloat(total_fee) * 100;
			jsonParam.put("total_fee", String.format("%.0f", sessionmoney));
			jsonParam.put("open_id",openId);
			jsonParam.put("order_body", body);
			jsonParam.put("notify_url", notofyUrl);
			jsonParam.put("attach", "购买商品");			
			String parm = "pay_ver=" + jsonParam.get("pay_ver") + "&pay_type=" + jsonParam.get("pay_type") + "&service_id="
					+ jsonParam.get("service_id") + "&merchant_no=" + jsonParam.get("merchant_no") + "&terminal_id="
					+ jsonParam.get("terminal_id") + "&terminal_trace=" + jsonParam.get("terminal_trace")
					+ "&terminal_time=" + jsonParam.get("terminal_time") + "&total_fee=" + jsonParam.get("total_fee")					
					+ "&access_token=" + access_token;
			String sign = MD5.sign(parm, "utf-8");
			jsonParam.put("key_sign", sign);
			System.out.println(prePay_url + "");
			
			//String s=Requests.doPost(prePay_url, jsonParam);
			String xmlText = tojson(prePay_url, jsonParam.toJSONString());
			posPrePayRe = (PosPrepayRe) JSON.parseObject(xmlText, PosPrepayRe.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return posPrePayRe;
	}
	
	//
	public static void getAccessToken(HttpServletRequest request){
		String prePay_url = "http://pay.lcsw.cn/lcsw/lcsw/pay/100/sign";
		JSONObject jsonParam = new JSONObject();
		PosSignRe posSignRe = new PosSignRe();
		try {
			jsonParam.put("pay_ver", "100");
			jsonParam.put("service_id", "090");
			jsonParam.put("merchant_no", "858100042000003");
			jsonParam.put("terminal_id", "10130461");
			jsonParam.put("terminal_trace", DateHelper.dataToString(new Date(), "yyyyMMddHHmmss" + RandUtil.getRandomStr(6)).toString());
			SimpleDateFormat adf = new SimpleDateFormat("yyyyMMddHHmmss");
			jsonParam.put("terminal_time", adf.format(new Date()).toString());
			String parm = "pay_ver=" + jsonParam.get("pay_ver") + "&service_id=" +jsonParam.get("service_id")+ "&merchant_no="
					+ jsonParam.get("merchant_no") + "&terminal_id=" + jsonParam.get("terminal_id") + "&terminal_trace="
					+ jsonParam.get("terminal_trace") + "&terminal_time=" + jsonParam.get("terminal_time");
					
			String sign = MD5.sign(parm, "utf-8");
			jsonParam.put("key_sign", sign);
			String xmlText = tojson(prePay_url, jsonParam.toJSONString());
			posSignRe = (PosSignRe) JSON.parseObject(xmlText, PosSignRe.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String tojson(String gateway, String jsonParam) throws Exception {

		String xmlText = "";

		CloseableHttpClient httpclient = HttpClients.custom().build();
		try {

			HttpPost httpPost = new HttpPost(gateway);
			httpPost.addHeader("charset", "UTF-8");
			System.out.println(jsonParam.toString());
			StringEntity stentity = new StringEntity(jsonParam.toString(), "utf-8");// 解决中文乱码问题
			stentity.setContentEncoding("UTF-8");
			stentity.setContentType("application/json");
			httpPost.setEntity(stentity);
			CloseableHttpResponse response = httpclient.execute(httpPost);
			try {
			
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					BufferedReader bufferedReader = new BufferedReader(
							new InputStreamReader(entity.getContent(), "UTF-8"));
					String text;
					while ((text = bufferedReader.readLine()) != null) {
						xmlText = xmlText + text;
					}
				}

				EntityUtils.consume(entity);
				System.out.println(xmlText);
			} finally {

				response.close();
			}
		} finally {

			httpclient.close();
		}

		return xmlText;
	}

}
