package cc.messcat.gjfeng.web.wechat;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alipay.direct.config.AlipayConfig;
import com.alipay.direct.util.OrderInfoUtil2_0;
import com.google.gson.JsonArray;
import com.h5pay.api.H5PayUtil;
import com.h5pay.api.HttpUtils;
import com.webpay.utils.MD5;
import com.webpay.utils.SignUtils;
import com.wechat.WeixinUtil;
import com.wechat.popular.api.TokenAPI;
import com.wechat.popular.api.UserAPI;
import com.wechat.popular.bean.Token;
import com.wechat.popular.bean.User;
import com.wechat.popular.bean.pay.PayJsRequest;
import com.wechat.popular.bean.pay.PayResult;
import com.wechat.popular.util.XMLConverUtil;

import cc.messcat.gjfeng.common.constant.CommonProperties;
import cc.messcat.gjfeng.common.exception.LoggerPrint;
import cc.messcat.gjfeng.common.fastpay.payForOther.FastPayDemoTest;
import cc.messcat.gjfeng.common.fastpay.payForOther.RechargeTest;
import cc.messcat.gjfeng.common.jd.JdNewUtil;
import cc.messcat.gjfeng.common.jd.JdUtil;
import cc.messcat.gjfeng.common.jd.bean.CatDetail;
import cc.messcat.gjfeng.common.jd.bean.CheckAccountResult;
import cc.messcat.gjfeng.common.jd.bean.GoodDetail;
import cc.messcat.gjfeng.common.jd.bean.GoodDetailList;
import cc.messcat.gjfeng.common.jd.bean.OrderResult;
import cc.messcat.gjfeng.common.jd.bean.ProductStock;
import cc.messcat.gjfeng.common.jd.bean.Province;
import cc.messcat.gjfeng.common.jd.bean.TrackResult;
import cc.messcat.gjfeng.common.netFriends.NetFriendUtil;
import cc.messcat.gjfeng.common.pay.alipay.util.AlipayRefundUtil;
import cc.messcat.gjfeng.common.pay.wechat.util.RefundUtil;
import cc.messcat.gjfeng.common.proprietary.util.ProUtil;
import cc.messcat.gjfeng.common.tianmao.TianMaoUtil;
import cc.messcat.gjfeng.common.util.BeanUtil;
import cc.messcat.gjfeng.common.util.DateHelper;
import cc.messcat.gjfeng.common.util.EncryptionUtil;
import cc.messcat.gjfeng.common.util.GenerateQrCodeUtil;
import cc.messcat.gjfeng.common.util.RandUtil;
import cc.messcat.gjfeng.common.vo.app.ResultVo;
import cc.messcat.gjfeng.common.web.BaseController;
import cc.messcat.gjfeng.dubbo.benefit.GjfBenefitInfoDubbo;
import cc.messcat.gjfeng.dubbo.core.CountInfoDubbo;
import cc.messcat.gjfeng.dubbo.core.GjfAddressDubbo;
import cc.messcat.gjfeng.dubbo.core.GjfLoginDubbo;
import cc.messcat.gjfeng.dubbo.core.GjfMemberInfoDubbo;
import cc.messcat.gjfeng.dubbo.core.GjfOrderInfoDubbo;
import cc.messcat.gjfeng.dubbo.core.GjfProductInfoDubbo;
import cc.messcat.gjfeng.dubbo.core.GjfTradeDubbo;
import cc.messcat.gjfeng.entity.GjfMemberInfo;
import cc.messcat.gjfeng.entity.GjfMerchantUpgradeHistory;
import cc.messcat.gjfeng.entity.GjfWeiXinPayInfo;
import cc.messcat.gjfeng.util.SessionUtil;
import cc.messcat.gjfeng.web.app.v1.LoginControllerV1;
import net.sf.json.JSONObject;
import prePay.PrePayUtil;

@Controller
@RequestMapping("/wx/test/")
public class TestController extends BaseController {

	@Autowired
	@Qualifier("memberInfoDubbo")
	private GjfMemberInfoDubbo memberInfoDubbo;

	@Autowired
	@Qualifier("request")
	private HttpServletRequest request;

	@Autowired
	@Qualifier("tradeDubbo")
	private GjfTradeDubbo tradeDubbo;
	
	@Autowired
	@Qualifier("orderInfoDubbo")
	private GjfOrderInfoDubbo orderInfoDubbo;

	@Autowired
	@Qualifier("productInfoDubbo")
	private GjfProductInfoDubbo productInfoDubbo;

	@Autowired
	@Qualifier("countInfoDubbo")
	private CountInfoDubbo countInfoDubbo;
	
	@Autowired
	@Qualifier("benefitInfoDubbo")
	private GjfBenefitInfoDubbo benefitInfoDubbo;
	
	@Autowired
	@Qualifier("addressDubbo")
	private GjfAddressDubbo addressDubbo;

	@Autowired
	@Qualifier("response")
	private HttpServletResponse response;

	@Value("${dubbo.application.web.client}")
	private String projectNames;

	@Value("${upload.member.code.path}")
	private String imageFolderNames;
	
	@Autowired
	@Qualifier("loginDubbo")
	private GjfLoginDubbo loginDubbo;

	/**
	 * 测试跳转到友集网
	 * 
	 * @param account
	 * @return
	 */
	@RequestMapping(value = "/testNetFriends", method = RequestMethod.GET)
	@ResponseBody
	public Object testNetFriends(String account) {
		try {
			GjfMemberInfo member = memberInfoDubbo.findMember(account);
			request.getSession().setAttribute("member", member);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 测试分红
	 * 
	 * @param account
	 * @return
	 */
	@RequestMapping(value = "/testbenefit", method = RequestMethod.GET)
	@ResponseBody
	public Object testbenefit(String account, String tradeNo, String payNo) {
		try {
			tradeDubbo.updateStoreBenefitPayStatus(tradeNo, payNo, "1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 测试从友集网获取商品数据
	@RequestMapping(value = "/testGetNetFriendsProductData", method = RequestMethod.GET)
	@ResponseBody
	public Object testGetNetFriendsProductData(String gid) throws IOException {
		Map<String, String> map = new HashMap<String, String>();
		map.put("appKey", "FnHbJsK6yFFuihUSef");
		map.put("page", "1");
		map.put("amount", "10");
		map.put("gid", gid);

		Map<String, String> params = SignUtils.paraFilter(map);
		StringBuilder buf = new StringBuilder((params.size() + 1) * 10);
		SignUtils.buildPayParams(buf, params, false);
		String preStr = buf.toString();
		// 生成签名
		// String
		// sign=DigestUtils.md5Hex(MD5.getContentBytes(preStr+"xk87UZUY1sdSD1SDSA3sOmKNSk2Kxa9s",
		// "utf-8"));
		String sign = MD5.sign("keyValue=".toUpperCase() + "xk87UZUY1sdSD1SDSA3sOmKNSk2Kxa9s".toUpperCase() + "&",
				preStr.toUpperCase(), "utf-8");
		// 请求域名
		String host = "http://m.upinkji.com";
		// 请求后缀
		String path = "/wap/Phoenix/product_list";
		// 请求类型
		String method = "GET";
		// 设置请求头
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "text/xml;charset=utf-8");
		// 请求参数
		Map<String, String> querys = new HashMap<String, String>();
		querys.put("appKey", "FnHbJsK6yFFuihUSef");
		querys.put("page", "1");
		querys.put("amount", "10");
		querys.put("gid", gid);

		querys.put("sign", sign);
		try {
			HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);

			System.out.println(response.toString());
			// 获取response的body
			String str = EntityUtils.toString(response.getEntity());
			JSONObject json = JSONObject.fromObject(str);
			// resultVo=productInfoDubbo.addNetFriendProductInfo(json);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 测试微信app支付
	 * 
	 * @param account
	 * @return
	 */
	@RequestMapping(value = "/testWeiAppPays", method = RequestMethod.GET)
	@ResponseBody
	public Object testWeiAppPays(String account, String money) {
		try {
			String tradeNo = DateHelper.dataToString(new Date(), "yyyyMMddHHmmss" + RandUtil.getRandomStr(6));
			Map map = WeixinUtil.unifiedorderResultInApp(request, tradeNo, "充值授信额度", money.toString(),
					CommonProperties.GJFENG_WEIXIN_JSPAY_NOTIFY_URL_SHOUXIN);
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 测试支付宝支付
	 * 
	 * @param account
	 * @return
	 */
	@RequestMapping(value = "/testAplyAppPay", method = RequestMethod.GET)
	@ResponseBody
	public Object testAplyAppPay(String money) {
		try {
			String tradeNo = DateHelper.dataToString(new Date(), "yyyyMMddHHmmss" + RandUtil.getRandomStr(6));
			Map<String, String> params = OrderInfoUtil2_0.getAppPlayParams(tradeNo, "下单成功", money, "购买商品",
					CommonProperties.GJFENG_APLY_PAY_NOTIFY_URL_BENEFIT);
			String orderParam = OrderInfoUtil2_0.buildOrderParam(params, false);
			String sign = OrderInfoUtil2_0.getSign(params, AlipayConfig.RSA_PRIVATE);
			final String orderInfo = orderParam + "&" + sign + "&sign_type=\"RSA\"";
			return orderInfo;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 微信扫码支付测试
	 * 
	 * @param account
	 * @return
	 */
	@RequestMapping(value = "WeiSweepPay", method = RequestMethod.GET)
	@ResponseBody
	public Object WeiSweepPay(String account) {
		String obj = "";
		Map<String, String> map = new HashMap<>();
		try {
			H5PayUtil.payChannel();
			String tradeNo = DateHelper.dataToString(new Date(), "yyyyMMddHHmmss" + RandUtil.getRandomStr(6));
			GjfWeiXinPayInfo payInfo = (GjfWeiXinPayInfo) tradeDubbo.findWeiXinBaseInfo("0").getResult();
			GjfMemberInfo gjfMemberInfo = memberInfoDubbo.findMember(account);
			obj = H5PayUtil.WeiSweepPay(payInfo, request, response, gjfMemberInfo, "0.01", tradeNo, "充值额度", "充值描述",
					CommonProperties.GJFENG_H5_NOTIFY_SHOUXIN);

			String[] str = obj.split("&");

			for (int j = 0; j < str.length; j++) {
				String str0 = str[j];
				String[] str1 = str0.split("=");
				if (str1.length == 1) {
					map.put(str1[0], "");
				} else {
					for (int k = 0; k < str1.length; k++) {
						map.put(str1[0], str1[1]);
					}
				}
			}
			String url = GenerateQrCodeUtil.generateQrcode(request, map.get("prepayId"), projectNames,
					imageFolderNames);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return obj;
	}

	/**
	 * 微信支付测试
	 * 
	 * @param account
	 * @return
	 */
	@RequestMapping(value = "getUnid", method = RequestMethod.GET)
	@ResponseBody
	public Object getUnid(String account) {
		try {
			// 获取微信配置信息
			GjfMemberInfo gjfMemberInfo = memberInfoDubbo.findMember(account);
			String tradeNo = DateHelper.dataToString(new Date(), "yyyyMMddHHmmss" + RandUtil.getRandomStr(6));
			GjfWeiXinPayInfo payInfo = (GjfWeiXinPayInfo) tradeDubbo.findWeiXinBaseInfo("0").getResult();
			String token_id = H5PayUtil.weiXinPay(payInfo, request, tradeNo, "0.01".toString(), "充值授信额度",
					CommonProperties.GJFENG_WEIXIN_NOTIFY_SHOUXIN, CommonProperties.GJFENG_WEIXIN_CALLBACK_URL_SHOUXIN,
					gjfMemberInfo.getOpenId());
			System.out.println("-----返回成功" + token_id);
			resultVo.setResult(token_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultVo;
	}

	/**
	 * 测试分红权
	 * 
	 * @param account
	 * @return
	 */
	@RequestMapping(value = "testDividends", method = RequestMethod.GET)
	@ResponseBody
	public Object testDividends() {
		User users = null;
		try {
			String out_trade_no = DateHelper.dataToString(new Date(), "yyyyMMddHHmmss" + RandUtil.getRandomStr(6))
					.toString();
			GjfWeiXinPayInfo payInfo = (GjfWeiXinPayInfo) tradeDubbo.findWeiXinBaseInfo("0").getResult();
			PrePayUtil.posPrePayRe(payInfo, request, out_trade_no, "0.01", "购买商品", "oAE3-wZnsd95lwSkHulGmiuTsDNs",
					CommonProperties.GJFENG_H5_NOTIFY_SHOUXIN);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return users;
	}

	/**
	 * 测试微信app支付
	 * 
	 * @param account
	 * @return
	 */
	@RequestMapping(value = "testWeiAppPay", method = RequestMethod.GET)
	@ResponseBody
	public Object testWeiAppPay(String account) {
		try {
			GjfWeiXinPayInfo payInfo = (GjfWeiXinPayInfo) tradeDubbo.findWeiXinBaseInfo("0").getResult();
			GjfMemberInfo gjfMemberInfo = memberInfoDubbo.findMember(account);
			String tradeNo = DateHelper.dataToString(new Date(), "yyyyMMddHHmmss" + RandUtil.getRandomStr(6));
			Map<String, String> map = H5PayUtil.weixinAppPay(payInfo, request, gjfMemberInfo, "0.01".toString(),
					tradeNo, CommonProperties.GJFENG_WEIXIN_NOTIFY_SHOUXIN);
			System.out.println("-----返回成功" + map);
			resultVo.setResult(map);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 测试支付宝app支付
	 * 
	 * @param account
	 * @return
	 */
	@RequestMapping(value = "testaplAppPay", method = RequestMethod.GET)
	public ModelAndView testaplAppPay(@RequestParam("account") String account) {
		Map<String, Object> model = new HashMap<>();
		try {
			GjfWeiXinPayInfo payInfo = (GjfWeiXinPayInfo) tradeDubbo.findWeiXinBaseInfo("0").getResult();
			GjfMemberInfo gjfMemberInfo = memberInfoDubbo.findMember(account);
			String tradeNo = DateHelper.dataToString(new Date(), "yyyyMMddHHmmss" + RandUtil.getRandomStr(6));
			Map<String, String> map = H5PayUtil.alipayAppPay(gjfMemberInfo, request, "0.01".toString(), tradeNo, "测试购买",
					"购买测试", CommonProperties.GJFENG_WEIXIN_NOTIFY_SHOUXIN);
			resultVo = new ResultVo(200, "返回成功", map);
			model.put("resultVo", resultVo);
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, MemberController.class);
			model.put("resultVo", resultVo);
		}
		return new ModelAndView("wx/online-shop/alipayCashier", model);
	}

	// 测试从card walkers
	@RequestMapping(value = "/testCardWalKersProductInfo", method = RequestMethod.GET)
	@ResponseBody
	public Object testCardWalKersProductInfo() throws IOException {
		String requestPath = "/api/goods/category";

		String url = "http://61.128.158.218:9001/mall-api" + requestPath;
		JdUtil.getData(url, requestPath, null);
		return null;
	}

	// 测试从card walkers
	@RequestMapping(value = "/testGetProInfo", method = RequestMethod.GET)
	@ResponseBody
	public Object testGetProInfo() throws IOException {
		Map<String, Object> data = new HashMap<>();
		String requestPath = "/api/goods/goodsPool.html";
		String url = "http://61.128.158.218:9001/mall-api" + requestPath;
		data.put("catId", "12259");
		JdUtil.getData(url, requestPath, data);
		return null;
	}

	// 测试从card walkers
	@RequestMapping(value = "/getProInfo", method = RequestMethod.GET)
	@ResponseBody
	public Object getProInfo() throws IOException {
		List<Province> list = JdUtil.getJdProvince();
		return new ResultVo(200, "获取成功", list);
	}

	// 测试从card walkers
	@RequestMapping(value = "/getProStock", method = RequestMethod.GET)
	@ResponseBody
	public Object getProStock(String goodsId, String area) throws IOException {
		List<ProductStock> list = JdUtil.getProductStore(goodsId, area);
		return new ResultVo(200, "获取成功", list);
	}
	
	
	// 测试从card walkers
	@RequestMapping(value = "/getOrderDetail", method = RequestMethod.GET)
	@ResponseBody
	public Object getOrderDetail(String orderSn) throws IOException {
		String str = JdUtil.getJdOrderDetail(orderSn);
		return new ResultVo(200, "获取成功", str);
	}

	// 测试从card walkers
	@RequestMapping(value = "/getProOrder", method = RequestMethod.GET)
	@ResponseBody
	public Object getProOrder() throws IOException {
		String requestPath = "/api/order/createOrder";
		String url = "http://61.128.158.218:9001/mall-api" + requestPath;

		// 传递业务参数
		Map<String, Object> data = new HashMap<String, Object>();

		/** 交易流水号（流水号相同，表示同一笔订单交易） */
		data.put("tradeNo", "131311114444");
		/** 收货人姓名 */
		data.put("name", "吖吖");
		/** 省 */
		data.put("provinceId", 27062);
		/** 市 */
		data.put("cityId", 27063);
		/** 区 */
		data.put("districtId", 27064);
		/** 镇 */
		data.put("townId", 43542);
		/** 收货人详细地址 */
		data.put("address", "1313");
		/** 座机号 */
		data.put("phone", "");
		/** 手机号 */
		data.put("mobile", "18888888888");
		/** 邮箱 */
		data.put("email", "31312@qq.com");
		/** 交易时间 格式（yyyyMMdd HHmmss） */
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HHmmss");
		data.put("tranTime", sdf.format(new Date()));
		List<Map<String, Object>> goodsNum = new ArrayList<>();
		Map<String, Object> goodsMap = new HashMap<String, Object>();
		goodsMap.put("goodsId", 397974);
		goodsMap.put("goodsNum", 1);
		goodsNum.add(goodsMap);
		/** 商品信息 */
		data.put("sku", goodsNum);
		JdUtil.getData(url, requestPath, data);
		return null;
	}

	// 测试从card walkers
	@RequestMapping(value = "/testConfim", method = RequestMethod.GET)
	@ResponseBody
	public Object testConfim(String orderSn, String jdOrderSn) throws IOException {
		OrderResult result = JdUtil.cofirmOrder(orderSn, jdOrderSn);
		return new ResultVo(200, "获取成功", result);
	}

	// 测试从card walkers
	@RequestMapping(value = "/testTrack", method = RequestMethod.GET)
	@ResponseBody
	public Object testTrack(String orderSn) throws IOException {
		TrackResult jdTrack = JdUtil.getTrack(orderSn);
		return new ResultVo(200, "获取成功", jdTrack);
	}

	// 测试从card walkers
	@RequestMapping(value = "/testGetProductDetail", method = RequestMethod.GET)
	@ResponseBody
	public Object testGetProductDetail(String goodsId) throws IOException {
		GoodDetail goodDetail = JdUtil.getJdGoodDetail(goodsId);
		return new ResultVo(200, "获取成功", goodDetail);
	}

	// 测试从card walkers
	@RequestMapping(value = "/testCatId", method = RequestMethod.GET)
	@ResponseBody
	public Object testCatId(String type) throws IOException {
		CatDetail goodDetail = JdUtil.addTestJdProductInfo();
		if (BeanUtil.isValid(goodDetail) && "1".equals(type)) {
			return new ResultVo(200, "获取成功", goodDetail.getFirstLevel());
		}
		if (BeanUtil.isValid(goodDetail) && "2".equals(type)) {
			return new ResultVo(200, "获取成功", goodDetail.getSecondLevel());
		}
		if (BeanUtil.isValid(goodDetail) && "3".equals(type)) {
			return new ResultVo(200, "获取成功", goodDetail.getThridLevel());
		}
		return new ResultVo(200, "获取成功", null);
	}

	// 分頁添加京東商品數據
	@RequestMapping(value = "/addJdPro", method = RequestMethod.GET)
	@ResponseBody
	public Object addJdPro(Integer pageNo, Integer pageSize) throws IOException {
		resultVo = productInfoDubbo.addJdProductByPager(pageNo, pageSize);
		return resultVo;
	}

	// 获取京东商品id
	@RequestMapping(value = "/testJDProductInfo", method = RequestMethod.GET)
	@ResponseBody
	public Object testJDProductInfo(String type,String page,String sup,String rateBegin) throws IOException {
		resultVo = productInfoDubbo.addJdProductInfoToPar(type,page,sup,rateBegin);
		return resultVo;
	}

	/**
	 * 添加每天统计记录
	 * 
	 */
	@RequestMapping(value = "/addTotalReport", method = RequestMethod.GET)
	@ResponseBody
	public Object addTotalReport(Integer pageNo, Integer pageSize, String beginTime, String endTime)
			throws IOException {
		resultVo = countInfoDubbo.addTotalReportData(pageNo, pageSize, beginTime, endTime);
		return resultVo;
	}

	@RequestMapping(value = "/addTotalReportByTime", method = RequestMethod.GET)
	@ResponseBody
	public Object addTotalReportByTime(String addTime) {
		try {
			SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
			String endDate = "";

			Calendar date = Calendar.getInstance();
			date.setTime(dft.parse(addTime));
			date.set(Calendar.DATE, date.get(Calendar.DATE) - 1);

			endDate = dft.format(date.getTime());
			resultVo = countInfoDubbo.addTotalReport(endDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultVo;
	}

	/**
	 * 测试快捷支付
	 * 
	 * @return
	 */
	@RequestMapping(value = "/testFastPay", method = RequestMethod.GET)
	@ResponseBody
	public Object testFastPay() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String orderId = sdf.format(new Date()) + System.currentTimeMillis();
			System.out.println("订单号：" + orderId);
			String dypass = FastPayDemoTest.getDyPass(orderId);
			System.out.println("动态秘钥：" + dypass);
			// 快捷支付申请
			// FastPayDemoTest.fastApply(orderId, dypass);
			FastPayDemoTest.fastApply(orderId, dypass, "1", "18300072217", "6217857000055154973", "李冠华",
					"450923199305022533", null);
			// 支付确认

			String verifyCode = "";// 短信验证码
			// FastPayDemoTest.fastConfirm(orderId, dypass, verifyCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultVo;
	}

	/**
	 * 测试快捷支付
	 * 
	 * @return
	 */
	@RequestMapping(value = "/testWeiUserInfo", method = RequestMethod.GET)
	@ResponseBody
	public Object testWeiUserInfo(String openId) {
		try {
			Token tokens = TokenAPI.token(CommonProperties.GJFENG_APP_ID, CommonProperties.GJFENG_APPSECRET);
			User user = UserAPI.userInfo(tokens.getAccess_token(), openId);
			return user;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 代付查询
	 * 
	 * @param orderId
	 * @return
	 */
	@RequestMapping(value = "/testPaidPayQuery", method = RequestMethod.GET)
	@ResponseBody
	public Object testPaidPayQuery(String orderId) {
		Map<String, String> map = new HashMap<>();
		try {
			String dypass = RechargeTest.getDyPass(orderId);
			map = RechargeTest.payForOtherQuery(orderId, dypass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 代付充值
	 * 
	 * @param orderId
	 * @return
	 */
	@RequestMapping(value = "/paidPaychage", method = RequestMethod.GET)
	@ResponseBody
	public Object paidPaychage(String accNo, String txtAmt, String name) {
		Map<String, String> map = new HashMap<>();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String orderId = sdf.format(new Date()) + System.currentTimeMillis();
			String dypass = RechargeTest.getDyPass(orderId);
			map = RechargeTest.rechargeApply(orderId, dypass, accNo, name, txtAmt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 测试获取友品集商品数据信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/testNetFriendGetAllProduct", method = RequestMethod.GET)
	@ResponseBody
	public Object testNetFriendGetAllProduct(String page, String pageSize) {
		try {
			JSONObject object = NetFriendUtil.getNetFriendProductInfo(page, pageSize, "", "", "", "", "", "", "", "",
					"", "", "", "", "");
			resultVo = productInfoDubbo.addNetFriendProductInfo(object);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultVo;
	}

	/**
	 * 测试获取友品集商品数据信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/testNetFriendGetAllProductOther", method = RequestMethod.GET)
	@ResponseBody
	public Object testNetFriendGetAllProductOther(String page, String pageSize) {
		try {
			JSONObject object = NetFriendUtil.getNetFriendProductInfo(page, pageSize, "", "", "", "", "", "", "", "",
					"", "", "", "", "");
			resultVo = new ResultVo(200, "查询成功", object);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultVo;
	}

	/**
	 * 测试获取友品集商品数据信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/testNetFriendGetAllProductDetail", method = RequestMethod.GET)
	@ResponseBody
	public Object testNetFriendGetAllProductDetail(String gid) {
		try {
			JSONObject object = NetFriendUtil.getNetFriendProductDetail(gid);
			resultVo = new ResultVo(200, "查询成功", object);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultVo;
	}
	
	/**
	 * 测试添加友品集订单
	 * 
	 * @return
	 */
	@RequestMapping(value = "/testAddNetFriendOrder", method = RequestMethod.GET)
	@ResponseBody
	public Object testAddNetFriendOrder(Float payMoney){
		try{
			JSONObject good=new JSONObject();
			good.put("gid", "3930");
			good.put("quantity", 1);
			good.put("oid", 0);
			good.put("shop",0);
			JSONArray array=new JSONArray();
			array.add(good);
			JSONObject netJson=NetFriendUtil.addNetFriendOrder("2",payMoney.toString() , "", array, "", "2", "测试","4198", "");
			return netJson;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 测试添加友品集订单
	 * 
	 * @return
	 */
	@RequestMapping(value = "/testWPayRefu", method = RequestMethod.GET)
	@ResponseBody
	public Object testWPayRefu(String orderSn,String payMoney){
		try{
			resultVo=RefundUtil.refundWeixinPay(orderSn, payMoney);
			return resultVo;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	

	/**
	 * 测试添加友品集更新订单
	 * 
	 * @return
	 */
	@RequestMapping(value = "/testNetFindOrder", method = RequestMethod.GET)
	@ResponseBody
	public Object testNetFindOrder(String oid,String osn) {
		try {
			JSONObject object = NetFriendUtil.findOrderDetail(oid, osn);
			System.out.println(object.get("data"));
			resultVo = new ResultVo(200, "查询成功", object);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultVo;
	}
	/**
	 * 测试支付宝退款
	 * @return
	 */
	@RequestMapping(value = "/testAplRefund", method = RequestMethod.GET)
	@ResponseBody
	public Object testAplRefund(String orderSn,String refundAmount){
		try{
			resultVo=AlipayRefundUtil.aliRefund(orderSn, refundAmount, "商品无库存");
		}catch(Exception e){
			e.printStackTrace();
		}
		return resultVo;					
	}
	
	/**
	 *测试对账接口
	 * @return
	 */
	@RequestMapping(value = "/checkAccount", method = RequestMethod.GET)
	@ResponseBody
	public Object checkAccount(String beginTime,String endTime){
		try{
			beginTime="20170801 110000";
			endTime="20170829 110000";
			CheckAccountResult checkAccountResult=JdUtil.checkAccount(beginTime, endTime);
			resultVo=new ResultVo(200,"查询成功",checkAccountResult);
		}catch(Exception e){
			e.printStackTrace();
		}
		return resultVo;					
	}
	
	
	/** 测试获取京东商品信息
	 * @return
	 */
	@RequestMapping(value = "/testGetJdProByCol", method = RequestMethod.GET)
	@ResponseBody
	public Object testGetJdProByCol(String conlumId){
		try{		
			resultVo=productInfoDubbo.findProByColumnId(Long.valueOf(conlumId),1,10,"",null, null);
		}catch(Exception e){
			e.printStackTrace();
		}
		return resultVo;	
	}
	
	
	/** 测试获取京东商品信息
	 * @return
	 */
	@RequestMapping(value = "/testJdRefund", method = RequestMethod.GET)
	@ResponseBody
	public Object testJdRefund(String kxzNo, String name, String provinceId, String cityId, String districtId,
			String townId, String address,String phone,String mobile,String remarks,String reason,String goodsId,String goNum){
		try{
			String result=JdUtil.putBackOrder(kxzNo, name, provinceId, cityId, districtId, townId, address, phone, mobile, remarks, reason, goodsId, goNum);
			resultVo=new ResultVo(200, "退款申请成功", result);
		}catch(Exception e){
			e.printStackTrace();
		}
		return resultVo;	
	}
	
	/**
	 * 测试设置用户是否参与分红
	 * @return
	 */
	@RequestMapping(value = "/testSetMemberIsDivi", method = RequestMethod.GET)
	@ResponseBody
	public Object testSetMemberIsDivi(){
		try{
			resultVo=countInfoDubbo.modifyAcountConsumption();
		}catch(Exception e){
			e.printStackTrace();
		}
		return resultVo;
	}
	
	/**
	 * 测试领回20%分红权减半50%再减半不变。
	 * @return
	 */
	@RequestMapping(value = "/testMemberDividecd", method = RequestMethod.GET)
	@ResponseBody
	public Object testMemberDividecd(){
		try{
			resultVo=countInfoDubbo.modifyMemberDeductDivi();
		}catch(Exception e){
			e.printStackTrace();
		}
		return resultVo;
	}
	
	/**
	 * 設置支付密碼
	 * @return
	 */
	@RequestMapping(value = "/testSetPayPassword", method = RequestMethod.GET)
	@ResponseBody
	public Object testSetPayPassword(String mobile,String payPassword){
		try{
			String str=EncryptionUtil.getMD5Code( mobile+ payPassword);
			return str;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 测试天猫获取分类
	 * @return
	 */
	@RequestMapping(value = "/testTianMaoCat", method = RequestMethod.GET)
	@ResponseBody
	public Object testTianMaoCat(){
		try{
			List<cc.messcat.gjfeng.common.tianmao.bean.CatDetail> list=TianMaoUtil.getTianMaoProCart();
			return list;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 测试天猫获取分类
	 * @return
	 */
	@RequestMapping(value = "/testTianMaoProductDetail", method = RequestMethod.GET)
	@ResponseBody
	public Object testTianMaoProductDetail(String type,String page){
		try{
			List list=TianMaoUtil.getProductList(type, page);
			return list;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	/**
	 * 测试天猫获取分类
	 * @return
	 */
	@RequestMapping(value = "/testTianMaoSerchProductDetail", method = RequestMethod.GET)
	@ResponseBody
	public Object testTianMaoSerchProductDetail(String keyWord,String page){
		try{
			List list=TianMaoUtil.getSerchProductList(keyWord, page);
			return list;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/*
	 * 测试订单积分
	 */
	@RequestMapping(value = "/testTmOrderPayNotify", method = RequestMethod.GET)
	@ResponseBody
	public Object testTmOrderPayNotify(){
		try{
			resultVo=orderInfoDubbo.updateOrderBenefit();
		}catch(Exception e){
			e.printStackTrace();
		}
		return resultVo;
	}
	
	/**
	 * 测试领回20%分红权减半50%再减半不变。
	 * @return
	 */
	@RequestMapping(value = "/testMemberIsActivity", method = RequestMethod.GET)
	@ResponseBody
	public Object testMemberIsActivity(){
		try{
			resultVo=countInfoDubbo.modifyMemberIsActivity();
		}catch(Exception e){
			e.printStackTrace();
		}
		return resultVo;
	}
	
	
	/**
	 * 测试让利回调
	 * @return
	 */
	@RequestMapping(value = "/testbenefitNotify", method = RequestMethod.GET)
	@ResponseBody
	public Object testbenefitNotify(String out_trade_no){
		try{
			tradeDubbo.updateStoreBenefitPayStatus(out_trade_no, "15456454", "1");
		}catch(Exception e){
			e.printStackTrace();
		}
		return resultVo;
	}
	
	/**
	 * 测试让利回调
	 * @return
	 */
	@RequestMapping(value = "/testMemberDedcut", method = RequestMethod.GET)
	@ResponseBody
	public Object testMemberDedcut(){
		try{
			benefitInfoDubbo.testMemberDedectDiviNum();
		}catch(Exception e){
			e.printStackTrace();
		}
		return resultVo;
	}
	
	
	/**
	 * 测试让利回调
	 * @return
	 */
	@RequestMapping(value = "/testMerchantUgrade", method = RequestMethod.GET)
	@ResponseBody
	public Object testMerchantUgrade(String out_trade_no){
		try{
			resultVo=tradeDubbo.updateRechargeHistoryStatus("1", out_trade_no);
		}catch(Exception e){
			e.printStackTrace();
		}
		return resultVo;
	}
	
	
	/**
	 * @描述 商家充值微信支付回调通知
	 * @author Karhs
	 * @date 2017年1月11日
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "testPayMerchantRechargeNotifyWeiXin", method = RequestMethod.POST)
	@ResponseBody
	public Object testPayMerchantRechargeNotifyWeiXin(String out_trade_no) {
		try {				
			resultVo=tradeDubbo.updateRechargeHistoryStatus("1", out_trade_no);		
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, PayNotifyController.class);
		}
		return resultVo;
	}
	
	
	/**
	 * @描述 商家赠送代金券微信支付回调通知
	 * @author Karhs
	 * @date 2017年1月11日
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "testPayVouchersNotifyWeiXin", method = RequestMethod.POST)
	@ResponseBody
	public Object testPayVouchersNotifyWeiXin(String out_trade_no) {
		try {				
		    resultVo=tradeDubbo.updateVoucherHistoryStatus("1", out_trade_no);

		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, PayNotifyController.class);
		}
		return resultVo;
	}
	
	
	/**
	 * @描述 商家赠送代金券微信支付回调通知
	 * @author Karhs
	 * @date 2017年1月11日
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "testPayMechantGivetifyWeiXin", method = RequestMethod.POST)
	@ResponseBody
	public Object testPayMechantGivetifyWeiXin(String out_trade_no) {
		try {
				
			resultVo=tradeDubbo.updateMerchantRechargeToMemberHistory("1", out_trade_no);
	
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, PayNotifyController.class);
		}
		return resultVo;
	}
	
	
	/**
	 *添加商户充值记录
	 */
	@RequestMapping(value = "testAddMerchantRechargeHistory", method = RequestMethod.POST)
	@ResponseBody
	public Object testAddMerchantRechargeHistory(String account,String merchantType,String payType,double tradeMoney) {
		try{			
			resultVo=tradeDubbo.addMerchantRechargeHistory(account, tradeMoney, merchantType, payType);				
		}catch(Exception e){
			resultVo = LoggerPrint.getResult(e, TradeContrller.class);			
		}				
		return resultVo; 
	}
	
	/**
	 * 京东商品获取大类
	 * @return
	 */
	@RequestMapping(value = "testGetJdCategory", method = RequestMethod.POST)
	@ResponseBody
	public Object testGetJdCategory(){
		CatDetail c=null;
		try{
			 c=JdNewUtil.addTestJdProductInfo();
		}catch(Exception e){
			e.printStackTrace();
		}
		return c;
	}
	
	
	/**
	 * 商品池任意查询接口（推荐使用！！）
	 * 
	 * @return
	 */
	@RequestMapping(value = "testGetJdGoodsPoolOmni", method = RequestMethod.POST)
	@ResponseBody
	public Object testGetJdGoodsPoolOmni(String catId,String page,String sup,String rateBegin){
		List list=null;
		try{
			list=JdNewUtil.goodsPoolOmni(catId,page,sup,rateBegin);
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	

	/**
	 * 商品池实时详情接口
	 * 
	 * @param catId
	 * @param page
	 * @return
	 */
	@RequestMapping(value = "goodsPoolDetail", method = RequestMethod.POST)
	@ResponseBody
	public Object goodsPoolDetail(String catId,String page,String sup,String rateBegin){
		GoodDetailList list=null;
		try{
			list=JdNewUtil.goodsPoolDetail(catId,page,sup,rateBegin);
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	

	/**
	 * 获取京东商品详细信息
	 * 
	 * @param goodsId
	 * @return
	 */
	
	@RequestMapping(value = "getJdGoodDetail", method = RequestMethod.POST)
	@ResponseBody
	public Object getJdGoodDetail(String goodsId){
		GoodDetail list=null;
		try{
			list=JdNewUtil.getJdGoodDetail(goodsId);
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 查询京东自营商品库存
	 * 
	 * @param goodsId
	 * @return
	 */
	
	@RequestMapping(value = "getProductStore", method = RequestMethod.GET)
	@ResponseBody
	public Object getProductStore(String goodsId, String area){
		List<ProductStock> list=null;
		try{
			list=JdNewUtil.getProductStore(goodsId,area);
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 京东自营产品确认订单
	 * @param goodsId
	 * @param area
	 * @return
	 */
	@RequestMapping(value = "cofirmOrder", method = RequestMethod.GET)
	@ResponseBody
	public Object cofirmOrder(String orderSn, String jdOrderSn){
		OrderResult list=null;
		try{
			list=JdNewUtil.cofirmOrder(orderSn,jdOrderSn);
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 获取京东自营商品订单详情
	 * @param jdOrderSn
	 * @return
	 */
	@RequestMapping(value = "getJdOrderDetail", method = RequestMethod.GET)
	@ResponseBody
	public Object getJdOrderDetail( String jdOrderSn){
		String list=null;
		try{
			list=JdNewUtil.getJdOrderDetail(jdOrderSn);
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 查询京东自营产品物流
	 * @param jdOrderSn
	 * @return
	 */
	@RequestMapping(value = "getTrack", method = RequestMethod.GET)
	@ResponseBody
	public Object getTrack( String jdOrderSn){
		TrackResult list=null;
		try{
			list=JdNewUtil.getTrack(jdOrderSn);
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 京东企业对账
	 * @param jdOrderSn
	 * @return
	 */
	@RequestMapping(value = "checkAccountJd", method = RequestMethod.GET)
	@ResponseBody
	public Object checkAccountJd( String beginTime,String endTime){
		CheckAccountResult list=null;
		try{
			list=JdNewUtil.checkAccount(beginTime,endTime);
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	
	/**
	 * 自营产品获取token
	 */
	@RequestMapping(value = "getAccessToken", method = RequestMethod.GET)
	@ResponseBody
	public Object getAccessToken(){
		String str="";
		try{
			ProUtil.getAccessToken();
		}catch(Exception e){
			e.printStackTrace();
		}
		return str;
	}
	
	/**
	 * 获取自营产品信息
	 * @return
	 */
	@RequestMapping(value = "getProProduct", method = RequestMethod.GET)
	@ResponseBody
	public Object getProProduct(String goods_id,String cat_id,String is_real,String is_on_sale,String rateBegin){
		List list=new ArrayList<>();
		try{
			list=ProUtil.getProductInfo(goods_id,cat_id,is_real,is_on_sale,rateBegin);
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 获取分类信息
	 * @return
	 */
	@RequestMapping(value = "getClassify", method = RequestMethod.GET)
	@ResponseBody
	public Object getClassify(){
		List list=new ArrayList<>();
		try{
			list=ProUtil.getCatId();
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 添加自营产品测试
	 *  * @return
	 */
	@RequestMapping(value = "addProProductInfo", method = RequestMethod.GET)
	@ResponseBody
	public Object addProProductInfo(String isOwn,String isCous,String isWho,String iSvou,String rateBegin){
		try{
			resultVo=productInfoDubbo.addProprietaryPro(isOwn, isCous, isWho, iSvou,rateBegin);
		}catch(Exception e){
			e.printStackTrace();
		}
		return resultVo;
	}
	
	/**
	 * 天天易购获取商城首页商品
	 * @param isOwn
	 * @param isCous
	 * @param isWho
	 * @param iSvou
	 * @return
	 */
	@RequestMapping(value = "findProIndex", method = RequestMethod.GET)
	@ResponseBody
	public Object findProIndex(){
		try{
			resultVo=productInfoDubbo.findProductInfoIndex();
		}catch(Exception e){
			e.printStackTrace();
		}
		return resultVo;
	}
	
	/**
	 * 获取产品池地址
	 * @return
	 */
	@RequestMapping(value = "testGetProAddress", method = RequestMethod.GET)
	@ResponseBody
	public Object testGetProAddress(){
		List list=new ArrayList<>();
		try{
			list=ProUtil.getAddress();
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 *  添加商品池地址
	 * @return
	 */
	@RequestMapping(value = "testAddAddress", method = RequestMethod.GET)
	@ResponseBody
	public Object testAddAddress(){
		try{
			resultVo=addressDubbo.addProAddress();
		}catch(Exception e){
			e.printStackTrace();
		}
		return resultVo;
	}
	
	/**
	 * @描述 注册
	 * @author Karhs
	 * @date 2017年3月14日
	 * @param mobile
	 * @return
	 */
	@RequestMapping(value = "register", method = RequestMethod.POST)
	@ResponseBody
	public Object register(@RequestParam("account")String account,@RequestParam("password")String password,@RequestParam("nickname")String nickname) {
		try {
			resultVo =loginDubbo.register(account,password, nickname, "0", "", "0",67682310L, "");
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, LoginControllerV1.class);
		}
		return resultVo;
	}
			
}
