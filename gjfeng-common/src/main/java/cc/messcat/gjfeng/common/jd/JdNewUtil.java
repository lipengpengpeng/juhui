package cc.messcat.gjfeng.common.jd;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.text.Position;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpHeaders;

import com.alibaba.fastjson.JSON;

import cc.messcat.gjfeng.common.jd.bean.AccessResult;
import cc.messcat.gjfeng.common.jd.bean.CatDetail;
import cc.messcat.gjfeng.common.jd.bean.CheckAccountResult;
import cc.messcat.gjfeng.common.jd.bean.GoodDetail;
import cc.messcat.gjfeng.common.jd.bean.GoodDetailList;
import cc.messcat.gjfeng.common.jd.bean.OrderResult;
import cc.messcat.gjfeng.common.jd.bean.ProductStock;
import cc.messcat.gjfeng.common.jd.bean.Province;
import cc.messcat.gjfeng.common.jd.bean.Result;
import cc.messcat.gjfeng.common.jd.bean.TrackResult;
import cc.messcat.gjfeng.common.util.BeanUtil;
import cc.messcat.gjfeng.entity.GjfMemberAddress;
import cc.messcat.gjfeng.entity.GjfOrderGoods;
import cc.messcat.gjfeng.entity.GjfOrderInfo;

public class JdNewUtil {

	// 链接地址
	private static String serverURL = "http://api.shanghai.com.cn";
	// 请求accessKey
	private static String agentUser = "FengHuang";
	// 请求secretKey
	private static String agentKey = "KXZ1011101";

	public static String getData(String url, String requestPath, Map<String, Object> data) {

		String method = "POST";
		Date date = new Date();
		HttpURLConnection conn = null;
		InputStream in = null;
		PrintWriter out = null;
		try {
			URL realURL = new URL(url);
			HttpURLConnection.setFollowRedirects(true);
			conn = (HttpURLConnection) realURL.openConnection();
			conn.setRequestMethod(method);
			conn.setDoOutput(true);
			/* 请求认证头参数 */
			conn.setRequestProperty(HttpHeaders.DATE, date.toString());
			conn.setRequestProperty("Accept-Charset", "UTF-8");
			conn.setRequestProperty("Content-Type", "text/json;charset=UTF-8");

			// 传递业务参
			String JsonString = "";
			if (data != null) {
				JsonString = JSON.toJSONString(JSON.toJSON(data));
			}
			out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(), "utf-8"));
			IOUtils.copy(new ByteArrayInputStream(JsonString.getBytes("utf-8")), out, "utf-8");
			out.flush();

			int status = conn.getResponseCode();
			if (status >= 200 && status < 400) {
				in = conn.getInputStream();
				StringWriter w = new StringWriter();
				IOUtils.copy(new InputStreamReader(in, "UTF-8"), w);
				String jsonData = w.toString();
				//System.out.println("成功返回" + jsonData);
				return jsonData;
			} else {
				in = conn.getErrorStream();
				StringWriter w = new StringWriter();
				IOUtils.copy(new InputStreamReader(in, "UTF-8"), w);
				String jsonData = w.toString();
				System.out.println("失败返回" + jsonData);
				return jsonData;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放资源和连接
			conn.disconnect();
			IOUtils.closeQuietly(in);
			IOUtils.closeQuietly(out);
		}
		return null;
	}

	/**
	 * 獲取簽名
	 * 
	 * @param data
	 */
	public static String getSign(Map<String, Object> data) {
		// 簽名
		Map<String, Object> params = SignUtils.paraFilter(data);
		StringBuilder buf = new StringBuilder((params.size() + 1) * 10);
		SignUtils.buildPayParams(buf, params, false);
		String preStr = buf.toString() + "&agentKey=" + agentKey;
		String sign = MD5.sign(preStr, "utf-8");
		return sign;
	}

	/**
	 * 接入京东商品数据
	 */

	public static CatDetail addTestJdProductInfo() {
		// 获取大类数据
		String requestPath = "/api/v1.0/goods/category";
		String url = serverURL + requestPath;
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("agentUser", agentUser);
		String sign = getSign(data);
		data.put("sign", sign);
		String categoryJson = JdNewUtil.getData(url, requestPath, data);
		// 把json字符串转为json对象
		com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(categoryJson);

		Result result = jsonObject.getObject("result", Result.class);
		if ("0000".equals(result.getResultCode()) && "1".equals(result.getSuccess())) {
			// 获取全部分类
			CatDetail catDetail = jsonObject.getObject("detail", CatDetail.class);
			return catDetail;
		}
		return null;
	}

	/**
	 * 商品池任意查询接口（推荐使用！！）
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "static-access" })
	public static List goodsPoolOmni(String catId, String page, String sup, String rateBegin) {
		List goodIds = new ArrayList<>();
		String requestPath = "/api/v1.0/goods/goods_poolOmni";
		String url = serverURL + requestPath;
		// 传递业务参数
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("catId", catId);// 商品大类id
		condition.put("page", page);
		condition.put("isOnSale", "1");
		condition.put("sup", sup);
		condition.put("rateBegin", rateBegin);
		String signStr = catId + sup + "1" + page + rateBegin;
		Map<String, Object> c = new HashMap<String, Object>();
		c.put("agentUser", agentUser);
		c.put("condition", signStr);
		String sign = getSign(c);
		c.put("sign", sign);
		c.put("condition", condition);
		// 请求接口
		String goodIdJson = JdNewUtil.getData(url, requestPath, c);
		// 处理返回的json字符创
		com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(goodIdJson);
		// 获取请求返回的状态信息
		Result result = jsonObject.getObject("result", Result.class);
		if ("0000".equals(result.getResultCode()) && "1".equals(result.getSuccess())) {
			// 获取goodid数据
			String jsonStr = jsonObject.getString("detail");
			// goodIds = jsonObject.getJSONA("detail");
			goodIds = jsonObject.parseArray(jsonStr);
			return goodIds;
		}
		return goodIds;
	}

	/**
	 * 商品池实时详情接口
	 * 
	 * @param catId
	 * @param page
	 * @return
	 */
	public static GoodDetailList goodsPoolDetail(String catId, String page, String priceBegin, String priceEnd) {

		String requestPath = "/api/v1.0/goods/goods_poolDetail";
		String url = serverURL + requestPath;
		// 传递业务参数
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("catId", catId);// 商品大类id
		condition.put("page", page);
		condition.put("isOnSale", "1");
		condition.put("priceBegin", priceBegin);
		condition.put("priceEnd", priceEnd);
		String signStr = catId + priceBegin + "1" + page + priceEnd;
		Map<String, Object> c = new HashMap<String, Object>();
		c.put("agentUser", agentUser);
		c.put("condition", signStr);
		String sign = getSign(c);
		c.put("sign", sign);
		c.put("condition", condition);
		// 请求接口
		String goodsDetailJson = JdNewUtil.getData(url, requestPath, c);
		// 处理返回的json字符创
		com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(goodsDetailJson);
		// 获取请求返回的状态信息
		Result result = jsonObject.getObject("result", Result.class);
		if ("0000".equals(result.getResultCode()) && "1".equals(result.getSuccess())) {
			// 获取goodid数据
			String jsonStr = jsonObject.getString("detail");
			if(!BeanUtil.isValid(jsonStr)){
				return null;
			}
			// 获取goodid数据
			GoodDetailList goodDetails = com.alibaba.fastjson.JSONObject.parseObject(jsonStr, GoodDetailList.class);

			return goodDetails;
		}
		return null;
	}

	/**
	 * 获取京东商品池列表
	 * 
	 * @param firstLevel
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	public static List getJdGoodPool(String catId) {
		List goodIds = new ArrayList<>();
		Map<String, Object> data = new HashMap<>();
		// 设置请求信息
		String requestPath = "/api/goods/goodsPool.html";
		String url = serverURL + requestPath;
		data.put("catId", catId);
		// 请求接口
		String goodIdJson = JdNewUtil.getData(url, requestPath, data);
		// 处理返回的json字符创
		com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(goodIdJson);
		// 获取请求返回的状态信息
		Result result = jsonObject.getObject("result", Result.class);
		if ("0000".equals(result.getResultCode()) && "1".equals(result.getSuccess())) {
			// 获取goodid数据
			String jsonStr = jsonObject.getString("detail");
			// goodIds = jsonObject.getJSONA("detail");
			goodIds = jsonObject.parseArray(jsonStr);
			return goodIds;
		}
		return goodIds;
	}

	/**
	 * 获取京东商品详细信息
	 * 
	 * @param goodsId
	 * @return
	 */
	public static GoodDetail getJdGoodDetail(String goodsId) {
		GoodDetail goodDetail = new GoodDetail();
		// 设置请求信息
		Map<String, Object> data = new HashMap<>();
		// 设置请求信息
		String requestPath = "/api/v1.0/goods/goods_detail";
		String url = serverURL + requestPath;
		data.put("agentUser", agentUser);		
		data.put("goodsId", goodsId);
		String sign = getSign(data);
		data.put("sign", sign);
		// 请求接口
		String goodsDetailJson = JdNewUtil.getData(url, requestPath, data);
		// 处理返回的json字符创
		com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(goodsDetailJson);
		// 获取请求返回的状态信息
		Result result = jsonObject.getObject("result", Result.class);
		if ("0000".equals(result.getResultCode()) && "1".equals(result.getSuccess())) {
			// 获取goodid数据
			goodDetail = jsonObject.getObject("detail", GoodDetail.class);
			return goodDetail;
		}
		return goodDetail;
	}

	/**
	 * 获取省
	 */
	@SuppressWarnings("static-access")
	public static List<Province> getJdProvince() {
		List<Province> list = new ArrayList<>();

		// 设置请求信息
		String requestPath = "/api/province.html";
		String url = serverURL + requestPath;
		// 请求接口
		String goodsDetailJson = JdNewUtil.getData(url, requestPath, null);
		// 处理返回的json字符创
		com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(goodsDetailJson);
		// 获取请求返回的状态信息
		Result result = jsonObject.getObject("result", Result.class);
		if ("0000".equals(result.getResultCode()) && "1".equals(result.getSuccess())) {
			// 获取goodid数据
			String jsonStr = jsonObject.getString("detail");
			list = jsonObject.parseArray(jsonStr, Province.class);

		}
		return list;
	}

	/**
	 * 获取二三四级地址接口
	 * 
	 * @param proviceId
	 * @return
	 */
	public static String getJdProviceLowerLevel(Long proviceId) {
		// 设置请求信息
		Map<String, Object> data = new HashMap<>();
		String requestPath = "/api/address.html";
		String url = serverURL + requestPath;
		data.put("provinceId", proviceId.toString());

		// 请求接口
		String goodsDetailJson = JdNewUtil.getData(url, requestPath, data);
		// 处理返回的json字符创
		com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(goodsDetailJson);
		// 获取请求返回的状态信息
		String jsonStr = "";
		Result result = jsonObject.getObject("result", Result.class);
		if ("0000".equals(result.getResultCode()) && "1".equals(result.getSuccess())) {
			// 获取goodid数据
			jsonStr = jsonObject.getString("detail");

		}
		return jsonStr;
	}

	/**
	 * 查询商品库存
	 */
	public static List<ProductStock> getProductStore(String goodsId, String area) {
		// 设置请求信息
		Map<String, Object> data = new HashMap<>();
		String requestPath = "/api/v1.0/goods/goods_status";
		String url = serverURL + requestPath;
		data.put("goodsId", goodsId);
		data.put("agentUser", agentUser);
		data.put("area", area.replaceAll(",", ""));
		String signStr="agentUser="+agentUser+"&goodsId="+goodsId.replaceAll(",", "")+"&area="+area.replaceAll(",", "").trim()+"&agentKey="+agentKey;
		String sign = MD5.sign(signStr, "utf-8");
		data.put("area", area);
		data.put("sign", sign);
		// 请求接口
		String goodsDetailJson = JdNewUtil.getData(url, requestPath, data);
		// 处理返回的json字符创
		com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(goodsDetailJson);
		// 获取请求返回的状态信息
		List<ProductStock> list = new ArrayList<>();
		Result result = jsonObject.getObject("result", Result.class);
		if ("0000".equals(result.getResultCode()) && "1".equals(result.getSuccess())) {
			// 获取goodid数据
			String jsonStr = jsonObject.getString("detail");
			list = com.alibaba.fastjson.JSONObject.parseArray(jsonStr, ProductStock.class);
		}
		return list;
	}

	/**
	 * 用户预下单
	 * 
	 * @throws UnsupportedEncodingException
	 */
	public static OrderResult addJdOrder(GjfMemberAddress address, GjfOrderInfo order,
			List<GjfOrderGoods> gjfOrderGoods,double tradePrice) {
		String requestPath = "/api/v1.0/order/createOrder";
		String url = serverURL + requestPath;

		// 传递业务参数
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("agentUser", agentUser);
		/** 交易流水号（流水号相同，表示同一笔订单交易） */
		data.put("tradeNo", order.getOrderSn().toString());
		/** 收货人姓名 */
		data.put("name", address.getConsigneeName());
		/** 省 */
		data.put("provinceId", address.getProviceId().getProvinceId().toString());
		/** 市 */
		data.put("cityId", address.getCityId().getCityId().toString());
		/** 区 */
		if (BeanUtil.isValid(address.getAreaId())) {
			data.put("districtId", address.getAreaId().getAreaId().toString());
		} else {
			data.put("districtId", "0");
		}
		/** 镇 */
		if (BeanUtil.isValid(address.getTownId())) {
			data.put("townId", address.getTownId().getTownId().toString());
		} else {
			data.put("townId", "0");
		}
		/** 收货人详细地址 */
		data.put("address", address.getAddressDetail());
		/** 座机号 */
		data.put("phone", "");
		/** 手机号 */
		data.put("mobile", address.getMobile().toString());
		/** 邮箱 */
		data.put("email", "");
		/** 交易时间 格式（yyyyMMdd HHmmss） */
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr=sdf.format(new Date());
		ParsePosition s=new ParsePosition(0);
	    String timestamp=String.valueOf(sdf.parse(dateStr,s).getTime()).substring(0, 10);  
		data.put("timestamp", timestamp);
		List<Map<String, Object>> goodsNum = new ArrayList<>();
		for (GjfOrderGoods goods : gjfOrderGoods) {
			Map<String, Object> goodsMap = new HashMap<String, Object>();
			goodsMap.put("goodsId", goods.getGoodsId().getNetProId().toString());
			goodsMap.put("goodsNum", goods.getGoodsNum());
			goodsMap.put("tradePrice",tradePrice);
			goodsNum.add(goodsMap);
		}

		/** 商品信息 */
		data.put("sku", goodsNum);
		String signStr="agentUser="+agentUser+"&agentKey="+agentKey+"&tradeNo="+order.getOrderSn().toString()+"&provinceId="+address.getProviceId().getProvinceId().toString()
				       +"&cityId="+address.getCityId().getCityId().toString()+"&districtId="+data.get("districtId")
				       +"&townId="+data.get("townId")+"&mobile="+address.getMobile().toString()+"&timestamp="+timestamp;
		String sign = MD5.sign(signStr, "utf-8");
		data.put("sign", sign);

		String OrderJson = getData(url, requestPath, data);
		System.out.println(OrderJson);
		com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(OrderJson);
		Result result = jsonObject.getObject("result", Result.class);
		if ("0000".equals(result.getResultCode()) && Integer.valueOf(result.getSuccess()) == 1) {
			// 获取goodid数据
			String jsonStr = jsonObject.getString("detail");
			OrderResult orderResult = com.alibaba.fastjson.JSONObject.parseObject(jsonStr, OrderResult.class);
			return orderResult;
		}
		return null;
	}

	/**
	 * 用户正式下单
	 * 
	 * @param orderSn
	 * @param jdOrderSn
	 */
	public static OrderResult cofirmOrder(String orderSn, String jdOrderSn) {
		String requestPath = "/api/v1.0/order/confirmOrder";
		String url = serverURL + requestPath;
		// 传递业务参数
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("agentUser", agentUser);
		/** 交易流水号，唯一（相同代表重复） */
		data.put("kxzNo", jdOrderSn);
		/** 预下单返回的订单号 */
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr=sdf.format(new Date());
		ParsePosition s=new ParsePosition(0);
	    String timestamp=String.valueOf(sdf.parse(dateStr,s).getTime()).substring(0, 10);  
		data.put("timestamp", timestamp);
		String signStr="agentUser="+agentUser+"&agentKey="+agentKey+"&kxzNo="+jdOrderSn+"&timestamp="+timestamp;
		String sign = MD5.sign(signStr, "utf-8");
		data.put("sign", sign);
		String OrderJson = getData(url, requestPath, data);
		System.out.println(OrderJson);
		com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(OrderJson);
		Result result = jsonObject.getObject("result", Result.class);
		if ("0000".equals(result.getResultCode()) && Integer.valueOf(result.getSuccess()) == 1) {
			// 获取goodid数据
			String jsonStr = jsonObject.getString("detail");
			OrderResult orderResult = com.alibaba.fastjson.JSONObject.parseObject(jsonStr, OrderResult.class);
			return orderResult;

		}
		return null;
	}

	/**
	 * 获取京东订单详情信息
	 * 
	 * @param jdOrderSn
	 */
	public static String getJdOrderDetail(String jdOrderSn) {
		String requestPath = "/api/v1.0/order/getOrderDetail";
		String url = serverURL + requestPath;
		// 传递业务参数
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("agentUser", agentUser);
		
		/** 预下单返回订单号 */
		data.put("kxzNo", jdOrderSn);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr=sdf.format(new Date());
		ParsePosition s=new ParsePosition(0);
	    String timestamp=String.valueOf(sdf.parse(dateStr,s).getTime()).substring(0, 10); 
		data.put("timestamp", timestamp);
		String signStr="agentUser="+agentUser+"&agentKey="+agentKey+"&kxzNo="+jdOrderSn+"&timestamp="+timestamp;
		String sign = MD5.sign(signStr, "utf-8");
        data.put("sign", sign);
		String OrderJson = getData(url, requestPath, data);
		com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(OrderJson);
		Result result = jsonObject.getObject("result", Result.class);
		if ("0000".equals(result.getResultCode()) && "1".equals(result.getSuccess())) {
			// 获取goodid数据
			String jsonStr = jsonObject.getString("detail");
			return jsonStr;

		}
		return null;

	}

	/**
	 * 查看物流
	 * 
	 * @param jdOrderSn
	 */
	public static TrackResult getTrack(String jdOrderSn) {
		String requestPath = "/api/v1.0/order/getTrack";
		String url = serverURL + requestPath;
		// 传递业务参数
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("agentUser", agentUser);
		/** 预下单返回订单号 */
		data.put("kxzNo", jdOrderSn);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr=sdf.format(new Date());
		ParsePosition s=new ParsePosition(0);
	    String timestamp=String.valueOf(sdf.parse(dateStr,s).getTime()).substring(0, 10); 
		data.put("timestamp", timestamp);
		String signStr="agentUser="+agentUser+"&agentKey="+agentKey+"&kxzNo="+jdOrderSn+"&timestamp="+timestamp;
		String sign = MD5.sign(signStr, "utf-8");
		data.put("sign", sign);

		String OrderJson = getData(url, requestPath, data);
		com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(OrderJson);
		Result result = jsonObject.getObject("result", Result.class);
		if ("0000".equals(result.getResultCode()) && Integer.valueOf(result.getSuccess()) == 1) {
			// 获取goodid数据
			String jsonStr = jsonObject.getString("detail");
			if(!"[]".equals(jsonStr)){
				TrackResult jdTrackResult = com.alibaba.fastjson.JSONObject.parseObject(jsonStr, TrackResult.class);
				return jdTrackResult;
			}
			
		}
		return null;
	}

	/**
	 * 企业对账
	 * 
	 * @param beginTime
	 * @param endTime
	 */
	public static CheckAccountResult checkAccount(String beginTime, String endTime) {
		try {
			String requestPath = "/api/v1.0/order/checkAccount";
			String url = serverURL + requestPath;
			Map<String, Object> data = new HashMap<String, Object>();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HHmmss");
			data.put("agentUser", agentUser);
			data.put("startTime", sdf.parse(beginTime).getTime());
			data.put("endTime", sdf.parse(endTime).getTime());
			String signStr="agentUser="+agentUser+"&agentKey="+agentKey+"&startTime="+sdf.parse(beginTime).getTime()+"&endTime="+sdf.parse(endTime).getTime();
			String sign = MD5.sign(signStr, "utf-8");
			data.put("sign", sign);
			String OrderJson = getData(url, requestPath, data);
			com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(OrderJson);
			Result result = jsonObject.getObject("result", Result.class);
			if ("0000".equals(result.getResultCode()) && "1".equals(result.getSuccess())) {
				// 获取goodid数据
				String jsonStr = jsonObject.getString("detail");
				CheckAccountResult checkAccountResult = com.alibaba.fastjson.JSONObject.parseObject(jsonStr,
						CheckAccountResult.class);
				return checkAccountResult;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 申请退货
	 */
	public static String putBackOrder(String kxzNo, String name, String provinceId, String cityId, String districtId,
			String townId, String address, String phone, String mobile, String remarks, String reason, String goodsId,
			String goNum) {
		String requestPath = "/api/v1.0/order/putBackOrder";
		String url = serverURL + requestPath;
		// 传递业务参数
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("agentUser", agentUser);
		/** 预下单返回订单号 */
		data.put("kxzNo", kxzNo);
		/** 退货人姓名 */
		data.put("name", name);
		/** 省 */
		data.put("provinceId", provinceId);
		/** 市 */
		data.put("cityId", cityId);
		/** 区 */
		data.put("districtId", districtId);
		/** 镇 */
		data.put("townId", townId);
		/** 收货人详细地址 */
		data.put("address", address);
		/** 座机号 */
		data.put("phone", phone);
		/** 手机号 */
		data.put("mobile", mobile);
		/** 备注信息 */
		data.put("remarks", remarks);
		/** 退货原因 */
		data.put("reason", reason);
		/** 商品情况列表 */
		List<Map<String, Object>> goodsNum = new ArrayList();
		Map<String, Object> goodsMap = new HashMap<String, Object>();
		goodsMap.put("goodsId", goodsId);// 商品编码
		goodsMap.put("goodsNum", goNum);// 商品数量
		goodsNum.add(goodsMap);
		/** 商品信息 */
		data.put("sku", goodsNum.toString());
		data.put("timestamp", "");
		String sign=getSign(data);
		data.put("sign", sign);
		String OrderJson = getData(url, requestPath, data);
		com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(OrderJson);
		Result result = jsonObject.getObject("result", Result.class);
		if ("0000".equals(result.getResultCode()) && "1".equals(result.getSuccess())) {
			// 获取goodid数据
			String jsonStr = jsonObject.getString("detail");
			return jsonStr;
		}
		return OrderJson;
	}

	/**
	 * 退货详情
	 */
	public static void getBackOrder(String jdOrderSn) {
		String requestPath = "/api/v1.0/order/getBackOrder";
		String url = serverURL + requestPath;
		// 传递业务参数
		Map<String, Object> data = new HashMap<String, Object>();
		/* 订单号 */
		data.put("kxzNo", jdOrderSn);
	}

}
