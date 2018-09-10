package cc.messcat.gjfeng.common.jd;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpHeaders;

import com.alibaba.fastjson.JSON;

import cc.messcat.gjfeng.common.jd.bean.AccessResult;
import cc.messcat.gjfeng.common.jd.bean.CatDetail;
import cc.messcat.gjfeng.common.jd.bean.CheckAccountResult;
import cc.messcat.gjfeng.common.jd.bean.FirstLevel;
import cc.messcat.gjfeng.common.jd.bean.GoodDetail;
import cc.messcat.gjfeng.common.jd.bean.GoodDetailList;
import cc.messcat.gjfeng.common.jd.bean.JdTrackResult;
import cc.messcat.gjfeng.common.jd.bean.OrderResult;
import cc.messcat.gjfeng.common.jd.bean.ProductStock;
import cc.messcat.gjfeng.common.jd.bean.Province;
import cc.messcat.gjfeng.common.jd.bean.Result;
import cc.messcat.gjfeng.common.jd.bean.TrackResult;
import cc.messcat.gjfeng.common.util.BeanUtil;
import cc.messcat.gjfeng.common.vo.app.ResultVo;
import cc.messcat.gjfeng.entity.GjfMemberAddress;
import cc.messcat.gjfeng.entity.GjfOrderGoods;
import cc.messcat.gjfeng.entity.GjfOrderInfo;

public class JdUtil {

	// 链接地址
	// private static String serverURL = "http://61.128.158.218:9001/mall-api";
	private static String serverURL = "https://api.gjfengcq.com";

	// 请求accessKey
	// private static String accessKey = "53d5e3a86bc64c7fb09a5cee2c1f1cf5";
	private static String accessKey = "52935701c32c45768c0ddb0b2b149d25";
	// 请求secretKey
	private static String secretKey = "";

	private static String mac = "34-97-F6-28-A2-2F";

	private static String ip = "127.0.0.1";

	private static void updateSessionKey(String mac, String ip) {
		String requestPath = "/api/sessionkey";
		String url = serverURL + requestPath;
		String method = "GET";
		Date date = new Date();
		// String signature="";
		String signature = Signatures.sign(method, requestPath, date.toString(), accessKey);
		String authorization = accessKey + ":" + signature;

		HttpURLConnection conn = null;
		InputStream in = null;
		try {
			URL realURL = new URL(url);
			HttpURLConnection.setFollowRedirects(true);
			conn = (HttpURLConnection) realURL.openConnection();
			conn.setRequestMethod(method);

			conn.setRequestProperty("x-qianlk-client-mac", mac);
			conn.setRequestProperty("x-qianlk-client-ip", ip);
			/* 请求认证头参数 */
			conn.setRequestProperty(HttpHeaders.DATE, date.toString());
			conn.setRequestProperty(HttpHeaders.AUTHORIZATION, authorization);

			// printRequest(url, method, authorization, conn);
			int status = conn.getResponseCode();

			// printHeaders(conn);
			if (status >= 200 && status < 400) {
				in = conn.getInputStream();
				StringWriter w = new StringWriter();
				IOUtils.copy(new InputStreamReader(in, "UTF-8"), w);
				String jsonData = w.toString();

				AccessResult dto = JSON.parseObject(jsonData, AccessResult.class);

				String code = dto.getRetCode();
				String msg = dto.getRetMsg();
				if ("000000".equals(dto.getRetCode())) {
					accessKey = dto.getAccessKey();
					secretKey = dto.getSecretKey();
				} else {
					System.out.println("GET sessionKey FAILED: errorCode=" + code + " errorMsg=" + msg);
				}
			} else {
				in = conn.getErrorStream();
				IOUtils.copy(in, System.out);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			// 释放资源和连接
			// IOUtils.close(conn);

			IOUtils.closeQuietly(in);
		}
	}

	public static String getData(String url, String requestPath, Map<String, Object> data) {
		updateSessionKey(mac, ip);

		String method = "POST";
		Date date = new Date();
		String signature = Signatures.sign(method, requestPath, date.toString(), secretKey);
		String authorization = accessKey + ":" + signature;

		HttpURLConnection conn = null;
		InputStream in = null;
		// OutputStream out = null;
		PrintWriter out = null;
		try {
			URL realURL = new URL(url);
			HttpURLConnection.setFollowRedirects(true);
			conn = (HttpURLConnection) realURL.openConnection();
			conn.setRequestMethod(method);
			conn.setDoOutput(true);
			/* 请求认证头参数 */
			conn.setRequestProperty(HttpHeaders.DATE, date.toString());
			conn.setRequestProperty(HttpHeaders.AUTHORIZATION, authorization);
			// conn.setRequestProperty(HttpHeaders.CONTENT_TYPE,
			// "text/html;charset=UTF-8");
			// conn.setRequestProperty("Charsert", "UTF-8");
			conn.setRequestProperty("Accept-Charset", "UTF-8");
			conn.setRequestProperty("Content-Type", "text/json;charset=UTF-8");
			// printRequest(url, method, authorization, conn);

			// 传递业务参
			String JsonString = "";
			if (data != null) {
				// JsonString = Gson.toJson2(data);
				JsonString = JSON.toJSONString(JSON.toJSON(data));
			}

			// logger.info(JsonString);
			out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(), "utf-8"));
			// out = conn.getOutputStream();
			IOUtils.copy(new ByteArrayInputStream(JsonString.getBytes("utf-8")), out, "utf-8");
			out.flush();

			int status = conn.getResponseCode();
			if (status >= 200 && status < 400) {
				in = conn.getInputStream();
				StringWriter w = new StringWriter();
				IOUtils.copy(new InputStreamReader(in, "UTF-8"), w);
				String jsonData = w.toString();
				System.out.println("成功返回" + jsonData);
				return jsonData;
			} else {
				in = conn.getErrorStream();
				StringWriter w = new StringWriter();
				IOUtils.copy(new InputStreamReader(in, "UTF-8"), w);
				String jsonData = w.toString();
				System.out.println("失败返回" + jsonData);
				return jsonData;
			}

			// logger.info("[Response]");
			// printHeaders(conn);
			// IOUtils.copy(in, System.out);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放资源和连接
			// IOUtils.close(conn);
			IOUtils.closeQuietly(in);
			IOUtils.closeQuietly(out);
		}
		return null;
	}

	/**
	 * 接入京东商品数据
	 */

	public static CatDetail addTestJdProductInfo() {
		// 获取大类数据
		String requestPath = "/api/goods/category";
		String url = serverURL + requestPath;

		String categoryJson = JdUtil.getData(url, requestPath, null);
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
	 * 获取京东商品池列表
	 * 
	 * @param firstLevel
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unused" })
	public static List getJdGoodPool(String catId) {
		List goodIds = new ArrayList<>();
		Map<String, Object> data = new HashMap<>();
		// 设置请求信息
		String requestPath = "/api/goods/goodsPool.html";
		String url = serverURL + requestPath;
		data.put("catId", catId);
		// 请求接口
		String goodIdJson = JdUtil.getData(url, requestPath, data);
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
		String requestPath = "/api/goods/goodsDetail.html";
		String url = serverURL + requestPath;
		data.put("goodsId", goodsId);
		// 请求接口
		String goodsDetailJson = JdUtil.getData(url, requestPath, data);
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
		String goodsDetailJson = JdUtil.getData(url, requestPath, null);
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
		data.put("provinceId", proviceId);

		// 请求接口
		String goodsDetailJson = JdUtil.getData(url, requestPath, data);
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
		String requestPath = "/api/goods/goodStatus.html";
		String url = serverURL + requestPath;
		data.put("goodsId", goodsId);
		data.put("area", area);
		// 请求接口
		String goodsDetailJson = JdUtil.getData(url, requestPath, data);
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
	 * 用户下单
	 * 
	 * @throws UnsupportedEncodingException
	 */
	public static OrderResult addJdOrder(GjfMemberAddress address, GjfOrderInfo order,
			List<GjfOrderGoods> gjfOrderGoods) {
		String requestPath = "/api/order/createOrder.html";
		String url = serverURL + requestPath;

		// 传递业务参数
		Map<String, Object> data = new HashMap<String, Object>();
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
			data.put("districtId", 0);
		}
		/** 镇 */
		if (BeanUtil.isValid(address.getTownId())) {
			data.put("townId", address.getTownId().getTownId().toString());
		} else {
			data.put("townId", 0);
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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HHmmss");
		data.put("tranTime", sdf.format(new Date()));
		List<Map<String, Object>> goodsNum = new ArrayList<>();
		for (GjfOrderGoods goods : gjfOrderGoods) {
			Map<String, Object> goodsMap = new HashMap<String, Object>();
			goodsMap.put("goodsId", goods.getGoodsId().getNetProId().toString());
			goodsMap.put("goodsNum", goods.getGoodsNum().intValue());
			goodsNum.add(goodsMap);
		}

		/** 商品信息 */
		data.put("sku", goodsNum);

		String OrderJson = getData(url, requestPath, data);
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
	 * 支付回调
	 * 
	 * @param orderSn
	 * @param jdOrderSn
	 */
	public static OrderResult cofirmOrder(String orderSn, String jdOrderSn) {
		String requestPath = "/api/order/confirmOrder.html";
		String url = serverURL + requestPath;
		// 传递业务参数
		Map<String, Object> data = new HashMap<String, Object>();
		/** 交易流水号，唯一（相同代表重复） */
		data.put("tradeNo", orderSn);
		/** 预下单返回的订单号 */
		data.put("orderNo", jdOrderSn);
		String OrderJson = getData(url, requestPath, data);
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
		String requestPath = "/api/order/getOrderDetail";
		String url = serverURL + requestPath;
		// 传递业务参数
		Map<String, Object> data = new HashMap<String, Object>();
		/** 预下单返回订单号 */
		data.put("kxzNo", jdOrderSn);

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
		String requestPath = "/api/order/getTrack";
		String url = serverURL + requestPath;
		// 传递业务参数
		Map<String, Object> data = new HashMap<String, Object>();
		/** 预下单返回订单号 */
		data.put("kxzNo", jdOrderSn);

		String OrderJson = getData(url, requestPath, data);
		com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(OrderJson);
		Result result = jsonObject.getObject("result", Result.class);
		if ("0000".equals(result.getResultCode()) && Integer.valueOf(result.getSuccess()) == 1) {
			// 获取goodid数据
			String jsonStr = jsonObject.getString("detail");
			TrackResult jdTrackResult = com.alibaba.fastjson.JSONObject.parseObject(jsonStr, TrackResult.class);
			return jdTrackResult;
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
			String requestPath = "/api/order/checkAccount.html";
			String url = serverURL + requestPath;
			Map<String, Object> data = new HashMap<String, Object>();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HHmmss");

			data.put("startTime", beginTime.toString());
			data.put("endTime", endTime.toString());
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
			String townId, String address,String phone,String mobile,String remarks,String reason,String goodsId,String goNum) {
		String requestPath = "/api/order/putBackOrder.html";
		String url = serverURL + requestPath;
		// 传递业务参数
		Map<String, Object> data = new HashMap<String, Object>();
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
		data.put("sku", goodsNum);
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
		String requestPath = "/api/order/getBackOrder.html";
		String url = serverURL + requestPath;
		// 传递业务参数
		Map<String, Object> data = new HashMap<String, Object>();
		/* 订单号 */
		data.put("kxzNo", jdOrderSn);
	}

	/**
	 * 商品池任意查询接口（推荐使用！！）
	 * 
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "static-access" })
	public static List goodsPoolOmni(String catId, String page,String sup,String rateBegin) {
		List goodIds = new ArrayList<>();
		String requestPath = "/api/goods/goodsPoolOmni";
		String url = serverURL + requestPath;
		// 传递业务参数
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("catId", catId);// 商品大类id
		condition.put("page", page);
		condition.put("isOnSale", "1");
		condition.put("sup", sup);
		condition.put("rateBegin", rateBegin);
		Map<String, Object> c = new HashMap<String, Object>();
		c.put("condition", condition);
		// 请求接口
		String goodIdJson = JdUtil.getData(url, requestPath, c);
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
	public static GoodDetailList goodsPoolDetail(String catId, String page) {

		String requestPath = "/api/goods/goodsPoolDetail";
		String url = serverURL + requestPath;
		// 传递业务参数
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("catId", catId);// 商品大类id
		condition.put("page", page);

		Map<String, Object> c = new HashMap<String, Object>();
		c.put("condition", condition);
		// 请求接口
		String goodsDetailJson = JdUtil.getData(url, requestPath, c);
		// 处理返回的json字符创
		com.alibaba.fastjson.JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(goodsDetailJson);
		// 获取请求返回的状态信息
		Result result = jsonObject.getObject("result", Result.class);
		if ("0000".equals(result.getResultCode()) && "1".equals(result.getSuccess())) {
			// 获取goodid数据
			String jsonStr = jsonObject.getString("detail");
			// 获取goodid数据
			GoodDetailList goodDetails = com.alibaba.fastjson.JSONObject.parseObject(jsonStr, GoodDetailList.class);

			return goodDetails;
		}
		return null;
	}

}
