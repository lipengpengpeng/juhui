package cc.messcat.gjfeng.common.proprietary.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;

import cc.messcat.gjfeng.common.netFriends.HttpUtils;
import cc.messcat.gjfeng.common.netFriends.MD5;
import cc.messcat.gjfeng.common.netFriends.SignUtils;
import cc.messcat.gjfeng.common.proprietary.bean.AddressInfo;
import cc.messcat.gjfeng.common.proprietary.bean.Classify;
import cc.messcat.gjfeng.common.proprietary.bean.ProductInfo;
import cc.messcat.gjfeng.common.util.BeanUtil;
import cc.messcat.gjfeng.common.util.HttpXmlClient;
import cc.messcat.gjfeng.entity.GjfMemberAddress;
import cc.messcat.gjfeng.entity.GjfOrderGoods;
import cc.messcat.gjfeng.entity.GjfOrderInfo;
import net.sf.json.JSONObject;

public class ProUtil {

	// 请求url
	//测试
	//private static String serverURL = "http://test.gjfeng.com";
	private static String serverURL = "http://api.ttyg168.cn";
	// 请求key
	//private static String appKey = "EE56F91277834243";
	//private static String scre = "A0A3916AEE56F91277834243FE75D56D";
	//private static String mobilephone = "15013307578";
	//private static String userId = "1";
	//供应链
	//private static String appKey = "jyHRxCALYANViQBM";
	//private static String scre = "3W6bdeemz0XKAYmyxRJgW2kiAUayHRFG";
	//private static String mobilephone = "13800138001";
	//private static String userId = "1";
	//天天易购
	//private static String appKey = "WlDRyqDNbCEnycNd";
	//private static String scre = "Gpfdu9FW378CPPIK6br31NL8k9Y4lCtO";
	//private static String mobilephone = "13800138002";
	//private static String userId = "2";
	//比特
	//private static String appKey = "gIcy2N2k7vPoXeNvr";
	//private static String scre = "T5RQux4Fv8BNoRMSsRSNKK7VO07RoDGe";
	//private static String mobilephone = "13800138003";
	//private static String userId = "3";
	
	//湛江
	//private static String appKey = "PQWb8VtvClwPJCKl";
	//private static String scre = "lnCaWxcBzpohOtnbQgricAuB1mc6Lg4m";
	//private static String mobilephone = "13800138005";
	//private static String userId = "5";
	
	//云南
	//private static String appKey = "za3Db8rbJryUCMKo";
	//private static String scre = "Xm7lBujwh3izTxTx6hEpE6YwjVV51XSv";
	//private static String mobilephone = "13800138004";
	//private static String userId = "4";
	
	//客户分红
	private static String appKey = "RSd2c6MH3BMDAEq8";
	private static String scre = "TlCJRUZ7KtEJJNgUgAgcFpozV2yQXImW";
	private static String mobilephone = "13800138007";
	private static String userId = "7";
		
	// token
	private static String accessToken = "";

	

	/**
	 * 获取请求数据
	 * 
	 * @param host
	 * @param path
	 * @param method
	 * @param map
	 * @return
	 */
	public static String getDate(String host, String path, String method, Map<String, String> map) {
		try {
			// 设置请求头
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "multipart/form-data;charset=utf-8");
			//host=host+path;
			//String str=HttpXmlClient.post(host, map);
			//System.out.println(str+"地址");
			String body="";
			HttpResponse response = HttpUtils.doPost(host, path, method, headers, map,body);

			// 获取response的body
			String str = EntityUtils.toString(response.getEntity());
			System.out.println(str);
			JSONObject json=null;
			if(BeanUtil.isValid(str)){
				json= JSONObject.fromObject(str);
				return json.toString();
			}			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

	/**
	 * 获取accessToken
	 * 
	 * @return
	 */
	public static void getAccessToken() {
		Map<String, String> map = new HashMap<>();
		map.put("app_key", appKey);
		map.put("mobilephone", mobilephone);
		String nonceStr = UUID.randomUUID().toString().replace("-", "");
		map.put("nonce", nonceStr);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = sdf.format(new Date());
		ParsePosition s = new ParsePosition(0);
		String timestamp = String.valueOf(sdf.parse(dateStr, s).getTime()).substring(0, 10);
		map.put("timestamp", timestamp);
		map.put("app_secret_key", scre);
		map.put("captcha", "");
		// 把map转换成字符串
		// Map<String, String> params = SignUtils.paraFilter(map);
		StringBuilder buf = new StringBuilder((map.size() + 1) * 10);
		SignUtils.buildPayParams(buf, map, false);
		String preStr = URLEncoder.encode(buf.toString());
		String sing = MD5.sign("".trim(), preStr, "utf-8");
		map.put("signature", sing);
		// 请求后缀
		String path = "/index.php/v1/token/mobile";
		// 请求类型
		String method = "POST";
		// 设置请求头
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "multipart/form-data;charset=utf-8");
		try {
			HttpResponse response = HttpUtils.doGet(serverURL, path, method, headers, map);
			// 获取response的body
			String str = EntityUtils.toString(response.getEntity());
			if (BeanUtil.isValid(str)) {
				com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(str);
				if (BeanUtil.isValid(jsonObject) && jsonObject.getInteger("code") == 200) {
					String tokenStr = jsonObject.getString("data");
					com.alibaba.fastjson.JSONObject object = com.alibaba.fastjson.JSONObject.parseObject(tokenStr);
					accessToken = object.getString("access_token");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取分类
	 */
	public static List<Classify> getCatId() {
		// 获取token
		getAccessToken();
		Map<String, String> map = new HashMap<>();
		map.put("user_id", userId);
		map.put("app_key", appKey);
		map.put("access_token", accessToken);
		String path = "/index.php/v1/goods/classify";
		String method = "POST";
		String str = getDate(serverURL, path,method, map);		
		List<Classify> list = new ArrayList<>();
		if (BeanUtil.isValid(str)) {
			com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(str);
			if (BeanUtil.isValid(jsonObject) && jsonObject.getInteger("code") == 200) {
				String proStr = jsonObject.getString("data");
				list = com.alibaba.fastjson.JSONObject.parseArray(proStr, Classify.class);
			}
		}
		return list;
	}

	/**
	 * 获取商品信息
	 * 
	 * @return
	 */
	public static List<ProductInfo> getProductInfo(String goods_id,String cat_id,String is_real,String is_on_sale,String rateBegin) {
		// 获取token
		getAccessToken();

		Map<String, String> map = new HashMap<>();
		map.put("user_id", userId);
		map.put("app_key", appKey);
		map.put("access_token", accessToken);
		map.put("goods_id", goods_id);
		map.put("cat_id", cat_id);
		map.put("is_real", is_real);
		map.put("is_on_sale", is_on_sale);
		map.put("rateBegin",rateBegin);
		String path = "/index.php/v1/goods";
		String method = "POST";
		
		String str = getDate(serverURL, path,method, map);
		List<ProductInfo> list = new ArrayList<>();
		if (BeanUtil.isValid(str)) {
			com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(str);
			if (BeanUtil.isValid(jsonObject) && jsonObject.getInteger("code") == 200) {
				String proStr = jsonObject.getString("data");
				list = com.alibaba.fastjson.JSONObject.parseArray(proStr, ProductInfo.class);
			}
		}
		return list;
	}

	/**
	 * 预下单
	 */
	public static String createOrder(GjfMemberAddress address, GjfOrderInfo order, List<GjfOrderGoods> gjfOrderGoods) {
		// 获取token
		getAccessToken();
		try{
			Map<String, String> map = new HashMap<>();
			map.put("user_id", userId);
			map.put("app_key", appKey);
			map.put("access_token", accessToken);
			//map.put("consignee", URLEncoder.encode(cnToUnicode(address.getConsigneeName()),"utf-8"));
			map.put("consignee", address.getConsigneeName());
			map.put("province", address.getProviceId().getProvinceId().toString());
			map.put("city", address.getCityId().getCityId().toString());
			if(BeanUtil.isValid(address.getAreaId())){
				map.put("district", address.getAreaId().getAreaId().toString());
			}else{
				map.put("district", "");
			}
			if(BeanUtil.isValid( address.getTownId())){
				map.put("twon", address.getTownId().getTownId().toString());
			}else{
				map.put("twon", "");
			}
			
			//map.put("address", URLEncoder.encode(cnToUnicode(address.getAddressDetail()),"utf-8"));
			map.put("address", address.getAddressDetail());
			map.put("zipcode", "");
			map.put("mobile", address.getMobile());
			map.put("email", "");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateStr = sdf.format(new Date());
			ParsePosition s = new ParsePosition(0);
			String timestamp = String.valueOf(sdf.parse(dateStr, s).getTime()).substring(0, 10);
			map.put("timestamp", timestamp);
			map.put("country", "0");
			if(BeanUtil.isValid(order.getRemark())){
				map.put("user_note", order.getRemark());
			}else{
				map.put("user_note", "");
			}
			
			map.put("customer_sn", order.getOrderSn());
			
			List<Map<String, Object>> goodsNum = new ArrayList<>();
			for (GjfOrderGoods goods : gjfOrderGoods) {
				Map<String, Object> goodsMap = new HashMap<String, Object>();
				goodsMap.put("goods_id", goods.getGoodsId().getNetProId().toString());
				goodsMap.put("goods_num", goods.getGoodsNum());	
				if(BeanUtil.isValid(goods.getGoodsId().getPara8())){
					goodsMap.put("spec_key", goods.getGoodsId().getPara8());
				}else{
					goodsMap.put("spec_key", "");
				}
				
				goodsNum.add(goodsMap);
			}
			String JsonString = "";
			if (goodsNum != null) {
				JsonString = JSON.toJSONString(JSON.toJSON(goodsNum));
			}
			map.put("app_secret_key", scre);
			//map.put("sku", URLEncoder.encode(JsonString,"UTF-8"));
			map.put("sku", JsonString);
			JSONObject mapJson=JSONObject.fromObject(map);
			StringBuilder buf = new StringBuilder((map.size() + 1) * 10);
			SignUtils.buildPayParams(buf, map, true);
			String preStr="";
			try {
				preStr = URLEncoder.encode(buf.toString(),"UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String sing = MD5.sign("".trim(), preStr, "utf-8");
			map.put("signature", sing);
			map.put("sku", JsonString);
			//map.put("consignee", cnToUnicode(address.getConsigneeName()));
			//map.put("address", cnToUnicode(address.getAddressDetail()));
			map.put("consignee", address.getConsigneeName());
			map.put("address", address.getAddressDetail());
			String path = "/index.php/v1/Createorder";
			String method = "POST";
			String str = getDate(serverURL, path,method, map);
			if (BeanUtil.isValid(str)) {
				com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(str);
				if (BeanUtil.isValid(jsonObject) && jsonObject.getInteger("code") == 200) {
					String proStr = jsonObject.getString("data");
					return proStr;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}		
		return null;
	}
	
	private static String cnToUnicode(String cn) {
	    char[] chars = cn.toCharArray();
	    String returnStr = "";
	    for (int i = 0; i < chars.length; i++) {
	      returnStr += "\\u" + Integer.toString(chars[i], 16);
	    }
	    return returnStr;
	}
			
	/**
	 * 确认下单
	 * @param order_sn
	 */
	public static String confirmorder(String order_sn,String customer_sn){
		// 获取token
		getAccessToken();
		Map<String, String> map = new HashMap<>();
		map.put("user_id", userId);
		map.put("app_key", appKey);
		map.put("access_token", accessToken);	
		map.put("order_sn", order_sn);
		map.put("customer_sn", customer_sn);
		map.put("app_secret_key", scre);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = sdf.format(new Date());
		ParsePosition s = new ParsePosition(0);
		String timestamp = String.valueOf(sdf.parse(dateStr, s).getTime()).substring(0, 10);
		map.put("timestamp", timestamp);
		StringBuilder buf = new StringBuilder((map.size() + 1) * 10);
		SignUtils.buildPayParams(buf, map, false);
		String preStr="";
		try {
			preStr = URLEncoder.encode(buf.toString(),"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(preStr);
		String sing = MD5.sign("".trim(), preStr, "utf-8");
		map.put("signature", sing);
		String path = "/v1/createorder/confirmorder";
		String method = "POST";
		String str = getDate(serverURL, path,method, map);
		if (BeanUtil.isValid(str)) {
			com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(str);
			if (BeanUtil.isValid(jsonObject) && jsonObject.getInteger("code") == 200) {
				String proStr = jsonObject.getString("data");
				return proStr;
			}
		}
		return null;
	}
	
	/**
	 * 获取收货地址
	 */
	public static List<AddressInfo> getAddress(){
		// 获取token
		getAccessToken();
		Map<String, String> map = new HashMap<>();
		map.put("user_id", userId);
		map.put("app_key", appKey);
		System.out.println(accessToken);
		map.put("access_token", accessToken);
		String path = "/index.php/v1/address/getAddress";
		String method = "POST";
		String str = getDate(serverURL, path,method, map);
		List<AddressInfo> list = new ArrayList<>();
		if (BeanUtil.isValid(str)) {
			com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(str);
			if (BeanUtil.isValid(jsonObject) && jsonObject.getInteger("code") == 200) {
				String proStr = jsonObject.getString("data");
				list=com.alibaba.fastjson.JSONObject.parseArray(proStr, AddressInfo.class);
				return list;
			}
		}
		return null;
		
	}
	
	public static void main(String[] args) {
		//String str=getAddress();
		//System.out.println(str);
		//getCatId();
	}

}
