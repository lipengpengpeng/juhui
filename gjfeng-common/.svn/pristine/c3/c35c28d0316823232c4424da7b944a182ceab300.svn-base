package cc.messcat.gjfeng.common.netFriends;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONArray;

import cc.messcat.gjfeng.common.util.HttpXmlClient;
import net.sf.json.JSONObject;

public class NetFriendUtil {

	// 获取友集网商品信息
	public static JSONObject getNetFriendProductInfo(String page, String pageSize, String keyword, String brand,
			String brandname, String min, String max, String ratio, String pcate, String ccate, String scate,
			String sale, String part, String recommend, String sort) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("appkey", "FnHbJsK6yFFuihUSef");
		map.put("page", page);
		map.put("amount", pageSize);
		String nonceStr = UUID.randomUUID().toString().replace("-", "");
		map.put("nonce_str", nonceStr);
		map.put("keyword", keyword);
		map.put("brand", brand);
		map.put("brandname", brandname);
		map.put("min", min);
		map.put("max", max);
		map.put("ratio", ratio);
		map.put("pcate", pcate);
		map.put("ccate", ccate);
		map.put("scate", scate);
		map.put("sale", sale);
		map.put("sort", sort);

		Map<String, String> params = SignUtils.paraFilter(map);
		StringBuilder buf = new StringBuilder((params.size() + 1) * 10);
		SignUtils.buildPayParams(buf, params, false);
		String preStr = buf.toString();
		// 生成签名

		String sign = MD5.sign("keyValue=".toUpperCase() + "xk87UZUY1sdSD1SDSA3sOmKNSk2Kxa9s".toUpperCase() + "&",
				preStr.toUpperCase(), "utf-8");
		// 请求域名
		String host = "http://m.upinkji.com";
		// 请求后缀
		String path = "/club/goods/find.html";
		// 请求类型
		String method = "GET";
		// 设置请求头
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "text/xml;charset=utf-8");
		// 请求参数
		map.put("sign", sign);
		try {
			HttpResponse response = HttpUtils.doGet(host, path, method, headers, map);

			// 获取response的body
			String str = EntityUtils.toString(response.getEntity());
			JSONObject json = JSONObject.fromObject(str);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 获取友集网商品詳情
	public static JSONObject getNetFriendProductDetail(String gid) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("appkey", "FnHbJsK6yFFuihUSef");
		String nonceStr = UUID.randomUUID().toString().replace("-", "");
		map.put("nonce_str", nonceStr);
		map.put("gid", gid);

		Map<String, String> params = SignUtils.paraFilter(map);
		StringBuilder buf = new StringBuilder((params.size() + 1) * 10);
		SignUtils.buildPayParams(buf, params, false);
		String preStr = buf.toString();

		String sign = MD5.sign("keyValue=".toUpperCase() + "xk87UZUY1sdSD1SDSA3sOmKNSk2Kxa9s".toUpperCase() + "&",
				preStr.toUpperCase(), "utf-8");
		// 请求域名
		String host = "http://m.upinkji.com";
		// 请求后缀
		String path = "/club/goods/get.html";
		// 请求类型
		String method = "GET";
		// 设置请求头
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "text/xml;charset=utf-8");
		// 请求参数
		map.put("sign", sign);
		try {
			HttpResponse response = HttpUtils.doGet(host, path, method, headers, map);

			// 获取response的body
			String str = EntityUtils.toString(response.getEntity());
			JSONObject json = JSONObject.fromObject(str);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 添加收货地址
	 * 
	 * @param oid
	 * @param realname
	 * @param idcard
	 * @param mobile
	 * @param areaId
	 * @param province
	 * @param city
	 * @param area
	 * @param address
	 * @param isDefault
	 * @param aid
	 * @return
	 */
	public static JSONObject addNetFriendAddress(String oid, String realname, String idcard, String mobile,
			String areaId, String province, String city, String area, String address, String isDefault, String aid) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("appkey", "FnHbJsK6yFFuihUSef");
		String nonceStr = UUID.randomUUID().toString().replace("-", "");
		map.put("nonce_str", nonceStr);
		map.put("token", "");
		map.put("oid", oid);
		map.put("realname", realname);
		map.put("idcard", idcard);
		map.put("mobile", mobile);
		map.put("areaid", areaId);
		map.put("province", province);
		map.put("city", city);
		map.put("area", area);
		map.put("address", address);
		map.put("default", isDefault);
		map.put("aid", aid);

		Map<String, String> params = SignUtils.paraFilter(map);
		StringBuilder buf = new StringBuilder((params.size() + 1) * 10);
		SignUtils.buildPayParams(buf, params, false);
		String preStr = buf.toString();

		String sign = MD5.sign("keyValue=".toUpperCase() + "xk87UZUY1sdSD1SDSA3sOmKNSk2Kxa9s".toUpperCase() + "&",
				preStr.toUpperCase(), "utf-8");
		// 请求域名
		String host = "http://m.upinkji.com/club/address/save.html";
		
		// 设置请求头
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "text/xml;charset=utf-8");
		// 请求参数
		map.put("sign", sign);
		
		try {
			String str=HttpXmlClient.post(host, map);
			//HttpResponse response = HttpUtils.doPost(host, path, method, headers, body,map);
			// 获取response的body
			//String str = EntityUtils.toString(response.getEntity());
			JSONObject json = JSONObject.fromObject(str);
			System.out.println(json);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 添加订单信息
	 * 	 
	 */
	public static JSONObject addNetFriendOrder(String oid, String order_total, String part_total, JSONArray goods,
			String type, String pay_type, String remark,String aid, String unoin) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("appkey", "FnHbJsK6yFFuihUSef");
		String nonceStr = UUID.randomUUID().toString().replace("-", "");
		map.put("nonce_str", nonceStr);
		map.put("token", "");
		map.put("oid", oid);
		map.put("order_total", order_total);
		map.put("part_total", part_total);
		map.put("goods", goods.toJSONString());
		map.put("type", type);
		map.put("pay_type", pay_type);
		map.put("remark", remark);
		map.put("aid", aid);
		map.put("unoin", unoin);
		
		Map<String, String> params = SignUtils.paraFilter(map);
		StringBuilder buf = new StringBuilder((params.size() + 1) * 10);
		SignUtils.buildPayParams(buf, params, false);
		String preStr = buf.toString();

		String sign = MD5.sign("keyValue=".toUpperCase() + "xk87UZUY1sdSD1SDSA3sOmKNSk2Kxa9s".toUpperCase() + "&",
				preStr.toUpperCase(), "utf-8");
		// 请求域名
		String host = "http://m.upinkji.com/club/order/submit.html";
	
		
		map.put("sign", sign);
		String data="appkey=FnHbJsK6yFFuihUSef"+"&nonce_str="+nonceStr+"&token="+"&oid="+oid+"&order_total="+Float.valueOf(order_total)
		     +"&part_total="+part_total+"&goods="+goods+"&type="+type+"&pay_type="+pay_type+"&remark="+remark+"&aid="+aid+"&unoin="+"&sign="+sign;
		try {
			//HttpResponse response = HttpUtils.doGet(host, path, method, headers, map);
			String str=HttpXmlClient.post(host, map);
			
			//String requestPath = "/club/order/submit.html";
			//String url = "http://m.upinkji.com/club/order/submit.html";
			//String str=HttpUtils.getData(url, requestPath, paMap,data);
			
			//String str=HttpConnetUtil.sendPost(url, data);

			// 获取response的body
			//String str = EntityUtils.toString(response.getEntity());
			JSONObject json = JSONObject.fromObject(str);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 确认订单信息
	 * 	 
	 */
	public static JSONObject confirmNetFriendOrder(String oid, String goods,
			String type,String aid) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("appkey", "FnHbJsK6yFFuihUSef");
		String nonceStr = UUID.randomUUID().toString().replace("-", "");
		map.put("nonce_str", nonceStr);
		map.put("token", "");
		map.put("oid", oid);		
		map.put("goods", goods);
		map.put("type", type);		
		map.put("aid", aid);
		
		Map<String, String> params = SignUtils.paraFilter(map);
		StringBuilder buf = new StringBuilder((params.size() + 1) * 10);
		SignUtils.buildPayParams(buf, params, false);
		String preStr = buf.toString();

		String sign = MD5.sign("keyValue=".toUpperCase() + "xk87UZUY1sdSD1SDSA3sOmKNSk2Kxa9s".toUpperCase() + "&",
				preStr.toUpperCase(), "utf-8");
		// 请求域名
		String host = "http://m.upinkji.com/club/order/confirm.html";				
		// 请求参数
		map.put("sign", sign);
		try {
			String str=HttpXmlClient.post(host, map);

			// 获取response的body			
			JSONObject json = JSONObject.fromObject(str);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	/**
	 * 获取订单详情
	 * 	 
	 */
	public static JSONObject findOrderDetail(String oid,String osn) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("appkey", "FnHbJsK6yFFuihUSef");
		String nonceStr = UUID.randomUUID().toString().replace("-", "");
		map.put("nonce_str", nonceStr);
		map.put("token", "");
		map.put("oid", oid);				
		map.put("osn", osn);
		
		Map<String, String> params = SignUtils.paraFilter(map);
		StringBuilder buf = new StringBuilder((params.size() + 1) * 10);
		SignUtils.buildPayParams(buf, params, false);
		String preStr = buf.toString();

		String sign = MD5.sign("keyValue=".toUpperCase() + "xk87UZUY1sdSD1SDSA3sOmKNSk2Kxa9s".toUpperCase() + "&",
				preStr.toUpperCase(), "utf-8");
		// 请求域名
		String host = "http://m.upinkji.com/club/order/get.html";				
		// 请求参数
		map.put("sign", sign);
		try {
			String str=HttpXmlClient.post(host, map);

			// 获取response的body			
			JSONObject json = JSONObject.fromObject(str);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 更新订单信息
	 * 	 
	 */
	public static JSONObject updateNetFriendOrder(String oid, String osn,
			String status,String cause) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("appkey", "FnHbJsK6yFFuihUSef");
		String nonceStr = UUID.randomUUID().toString().replace("-", "");
		map.put("nonce_str", nonceStr);
		map.put("token", "");
		map.put("oid", oid);		
		map.put("osn", osn);
		map.put("status", status);		
		map.put("cause", cause);
		
		Map<String, String> params = SignUtils.paraFilter(map);
		StringBuilder buf = new StringBuilder((params.size() + 1) * 10);
		SignUtils.buildPayParams(buf, params, false);
		String preStr = buf.toString();

		String sign = MD5.sign("keyValue=".toUpperCase() + "xk87UZUY1sdSD1SDSA3sOmKNSk2Kxa9s".toUpperCase() + "&",
				preStr.toUpperCase(), "utf-8");
		// 请求域名
		String host = "http://m.upinkji.com/club/order/update.html";		
		// 请求参数
		map.put("sign", sign);
		try {
			String str=HttpXmlClient.post(host, map);
			JSONObject json = JSONObject.fromObject(str);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 订单支付
	 * 	 
	 */
	public static String payNetFriendOrder(String oid, String osn,String pay_type) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("appkey", "FnHbJsK6yFFuihUSef");
		String nonceStr = UUID.randomUUID().toString().replace("-", "");
		map.put("nonce_str", nonceStr);
		map.put("token", "");
		map.put("oid", oid);		
		map.put("osn", osn);
		map.put("pay_type", pay_type);
		
		Map<String, String> params = SignUtils.paraFilter(map);
		StringBuilder buf = new StringBuilder((params.size() + 1) * 10);
		SignUtils.buildPayParams(buf, params, false);
		String preStr = buf.toString();

		String sign = MD5.sign("keyValue=".toUpperCase() + "xk87UZUY1sdSD1SDSA3sOmKNSk2Kxa9s".toUpperCase() + "&",
				preStr.toUpperCase(), "utf-8");
		// 请求域名
		String host = "http://m.upinkji.com/club/pay/online.html";
			
		// 请求参数
		map.put("sign", sign);
		try {
			//HttpResponse response = HttpUtils.doGet(host, path, method, headers, map);
			String str=HttpXmlClient.post(host, map);

			// 获取response的body
			//String str = EntityUtils.toString(response.getEntity());
			
			return str;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 订单支付
	 * 	 
	 */
	public static Map<String, String> payNetFriendOrderSign(String oid, String osn,String pay_type) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("appkey", "FnHbJsK6yFFuihUSef");
		String nonceStr = UUID.randomUUID().toString().replace("-", "");
		map.put("nonce_str", nonceStr);
		map.put("token", "");
		map.put("oid", oid);		
		map.put("osn", osn);
		map.put("pay_type", pay_type);
		
		Map<String, String> params = SignUtils.paraFilter(map);
		StringBuilder buf = new StringBuilder((params.size() + 1) * 10);
		SignUtils.buildPayParams(buf, params, false);
		String preStr = buf.toString();

		String sign = MD5.sign("keyValue=".toUpperCase() + "xk87UZUY1sdSD1SDSA3sOmKNSk2Kxa9s".toUpperCase() + "&",
				preStr.toUpperCase(), "utf-8");
		map.put("sign", sign);
		return map;
	}
	
	
	/**
	 * 注册新用户
	 * 	 
	 */
	public static JSONObject registerNetFriend(String phoenix_id,String phoenix_name,String phoenix_mobile,String phoenix_avatar) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("weid", "2");		
		map.put("domain", "baf46d31fff31a7587df12dea5bfe");
		map.put("share_id", "b1d41MSSuxRYJH_YfA05z5JZbpya2q4XZn5d5-6VUiGaF7zVdjw");		
		map.put("phoenix_id", phoenix_id);
		map.put("phoenix_name", phoenix_name);
		map.put("phoenix_mobile", phoenix_mobile);
		map.put("phoenix_avatar", phoenix_avatar);
		
		Map<String, String> params = SignUtils.paraFilter(map);
		StringBuilder buf = new StringBuilder((params.size() + 1) * 10);
		SignUtils.buildPayParams(buf, params, false);
		String preStr = buf.toString();

		String sign = MD5.sign("keyValue=".toUpperCase() + "xk87UZUY1sdSD1SDSA3sOmKNSk2Kxa9s".toUpperCase() + "&",
				preStr.toUpperCase(), "utf-8");
		// 请求域名
		String host = "http://m.upinkji.com";
		// 请求后缀
		String path = "/wap/phoenix/shop_bind.html";
		// 请求类型
		String method = "GET";
		// 设置请求头
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "text/xml;charset=utf-8");
		// 请求参数
		map.put("sign", sign);
		try {
			HttpResponse response = HttpUtils.doGet(host, path, method, headers, map);

			// 获取response的body
			String str = EntityUtils.toString(response.getEntity());
			JSONObject json = JSONObject.fromObject(str);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
