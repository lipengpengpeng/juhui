package cc.messcat.gjfeng.web.wechat;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alipay.direct.config.AlipayConfig;
import com.alipay.direct.util.OrderInfoUtil2_0;
import com.h5pay.api.H5PayUtil;
import com.wechat.WeixinUtil;
import com.wechat.popular.api.QrcodeAPI;
import com.wechat.popular.api.TokenAPI;
import com.wechat.popular.bean.QrcodeTicket;
import com.wechat.popular.bean.Token;

import cc.messcat.gjfeng.common.constant.CommonProperties;
import cc.messcat.gjfeng.common.exception.LoggerPrint;
import cc.messcat.gjfeng.common.util.BeanUtil;
import cc.messcat.gjfeng.common.util.DateHelper;
import cc.messcat.gjfeng.common.util.EncryptionUtil;
import cc.messcat.gjfeng.common.util.GenerateQrCodeUtil;
import cc.messcat.gjfeng.common.util.ObjValid;
import cc.messcat.gjfeng.common.util.RandUtil;
import cc.messcat.gjfeng.common.util.StringUtil;
import cc.messcat.gjfeng.common.vo.app.MemberTradeBenefitVo;
import cc.messcat.gjfeng.common.vo.app.OrderAddModel;
import cc.messcat.gjfeng.common.vo.app.OrderAddVo;
import cc.messcat.gjfeng.common.vo.app.OrderInfoVo;
import cc.messcat.gjfeng.common.vo.app.ResultVo;
import cc.messcat.gjfeng.common.vo.app.StoreInfoVo;
import cc.messcat.gjfeng.common.web.BaseController;
import cc.messcat.gjfeng.dubbo.core.GjfAddressDubbo;
import cc.messcat.gjfeng.dubbo.core.GjfCartInfoDubbo;
import cc.messcat.gjfeng.dubbo.core.GjfMemberInfoDubbo;
import cc.messcat.gjfeng.dubbo.core.GjfOrderInfoDubbo;
import cc.messcat.gjfeng.dubbo.core.GjfStoreInfoDubbo;
import cc.messcat.gjfeng.dubbo.core.GjfTradeDubbo;
import cc.messcat.gjfeng.entity.GjfAccessToken;
import cc.messcat.gjfeng.entity.GjfMemberInfo;
import cc.messcat.gjfeng.entity.GjfMemberTrade;
import cc.messcat.gjfeng.entity.GjfOrderInfo;
import cc.messcat.gjfeng.entity.GjfWeiXinPayInfo;
import cc.messcat.gjfeng.util.SessionUtil;
	
@Controller
@RequestMapping("/appNeed")
public class AppNeedController extends BaseController {
	
	@Autowired
	@Qualifier("request")
	private HttpServletRequest request;

	@Autowired
	@Qualifier("response")
	private HttpServletResponse response;

	@Autowired
	@Qualifier("tradeDubbo")
	private GjfTradeDubbo tradeDubbo;

	@Autowired
	@Qualifier("memberInfoDubbo")
	private GjfMemberInfoDubbo memberInfoDubbo;

	@Autowired
	@Qualifier("storeInfoDubbo")
	private GjfStoreInfoDubbo storeInfoDubbo;
	
	@Autowired
	@Qualifier("orderInfoDubbo")
	private GjfOrderInfoDubbo orderInfoDubbo;
	
	
	@Autowired
	@Qualifier("addressDubbo")
	private GjfAddressDubbo addressDubbo;

	@Autowired
	@Qualifier("cartInfoDubbo")
	private GjfCartInfoDubbo cartInfoDubbo;
		
	@Value("${gjfeng.client.project.url}")
	private String projectName;

	@Value("${upload.member.head.path}")
	private String uploadFilePath;

	@Value("${upload.member.idcard.path}")
	private String imageFolderName;

	@Value("${dubbo.application.web.client}")
	private String projectNames;

	@Value("${upload.member.code.path}")
	private String imageFolderNames;

	
	/**
	 * @描述 商家让利
	 * @author Karhs
	 * @date 2017年1月9日
	 * @return
	 */
	PrimeUnionThread primeUnionThread=null;
	@RequestMapping(value = "addBenefitByH5", method = RequestMethod.GET)
	public ModelAndView addBenefitByH5(@RequestParam("amount") double amount, @RequestParam("mobile") String mobile,
			@RequestParam("payType") String payType,@RequestParam("account") String account,String merchantGrade) {
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			// 添加让利记录并调支付接口
			StoreInfoVo store = (StoreInfoVo) storeInfoDubbo.findStoreByAccount(account).getResult();
			resultVo = tradeDubbo.addStoreBenefit(account, store.getId(), mobile, amount, payType,merchantGrade);

			if ((payType.equals("2")||payType.equals("1"))&& resultVo.getCode() == 200) {
				MemberTradeBenefitVo benefitVo = (MemberTradeBenefitVo) resultVo.getResult();

				GjfMemberInfo info = memberInfoDubbo.findMember(account);
				Map<String, String> map = H5PayUtil.H5PayResult(request, response, info,
						benefitVo.getBenefitMoney().toString(), benefitVo.getTradeNo(), "商家让利", "商家让利描述",
						CommonProperties.GJFENG_H5_NOTIFY_BENETI);
				resultVo.setResult(map);
				model.put("obj", map);
				
				primeUnionThread=new PrimeUnionThread(benefitVo.getTradeNo());
				primeUnionThread.start();
			}
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, TradeContrller.class);
		}
		return new ModelAndView("wx/online-shop/H5cashier", model);
	}
	
	
	@RequestMapping(value = "addBenefitByAlipay", method = RequestMethod.GET)
	public ModelAndView addBenefitByAlipay(@RequestParam("amount") double amount, @RequestParam("mobile") String mobile,
			@RequestParam("payType") String payType,@RequestParam("account") String account,String merchantGrade) {
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			// 添加让利记录并调支付接口
			StoreInfoVo store = (StoreInfoVo) storeInfoDubbo.findStoreByAccount(account).getResult();
			resultVo = tradeDubbo.addStoreBenefit(account, store.getId(), mobile, amount, payType,merchantGrade);

			if (payType.equals("1") && resultVo.getCode() == 200) {
				MemberTradeBenefitVo benefitVo = (MemberTradeBenefitVo) resultVo.getResult();

				GjfMemberInfo info = memberInfoDubbo.findMember(account);
				Map<String, String> map = H5PayUtil.alipayAppPay(info,request, benefitVo.getBenefitMoney().toString(), benefitVo.getTradeNo(), "商家让利", "商家让利描述",
						CommonProperties.GJFENG_H5_NOTIFY_BENETI);
				resultVo=new ResultVo(200,"返回成功",map);
				model.put("resultVo", resultVo);
				
				primeUnionThread=new PrimeUnionThread(benefitVo.getTradeNo());
				primeUnionThread.start();
			}
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, TradeContrller.class);
		}
		return new ModelAndView("wx/online-shop/alipayCashier", model);
	}

	// 线程
	class PrimeUnionThread extends Thread {
		private String out_trade_on;

		PrimeUnionThread(String out_trade_on) {
			this.out_trade_on = out_trade_on;
		}

		public void run() {
			try {
				for (int i = 0; i < 3; i++) {
					if (i == 0) {
						Thread.sleep(180000);
					}
					if (i == 1) {
						Thread.sleep(480000);
					}					

					String xml = H5PayUtil.queryH5Order(request, out_trade_on);
					String[] str=xml.split("&");
					Map<String, String> map=new HashMap<String, String>();
					for(int j=0;j<str.length;j++){
						String str0=str[j];
						String[] str1=str0.split("=");
						if(str1.length==1){
							map.put(str1[0], "");
						}else{
							for(int k=0;k<str1.length;k++){
								map.put(str1[0], str1[1]);
							}	
						}					
					}	
					/*"000000".equals(map.get("retCode"))&&*/
					if ("0".equals(map.get("status"))) {
						String out_trade_no = map.get("merchOrderId");
						String trade_no = map.get("orderId");
						resultVo=tradeDubbo.updateStoreBenefitPayStatus(out_trade_no, trade_no, "1");
						if(resultVo.getCode()==200){
							primeUnionThread.interrupt();
						}
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * 充值授信额度
	 * 
	 * @return
	 */
	PrimeUnionThread0 primeUnionThread0 = null;
	@RequestMapping(value = "addShouXinByH5", method = RequestMethod.GET)
	public ModelAndView addShouXinByH5(String type, Double tradeMoney,String account,String shouType,String fileImage) {
		Map<String, Object> model = new HashMap<String, Object>();
		try {			
			resultVo = tradeDubbo.addShouXin(type, account, tradeMoney,shouType,fileImage);

			if (("3".equals(type)||"2".equals(type)) && resultVo.getCode() == 200) {
				GjfMemberTrade trade = (GjfMemberTrade) resultVo.getResult();
				Map<String, String> obj = H5PayUtil.H5PayResult(request, response, trade.getMemberId(),
					trade.getTradeMoney().toString(), trade.getTradeNo(), "充值授信额度", "充值授信额度描述",
					CommonProperties.GJFENG_H5_NOTIFY_SHOUXIN);
				// resultVo.setResult(xml);
				model.put("obj", obj);

				// 定义线程
				primeUnionThread0 = new PrimeUnionThread0(trade.getTradeNo());
				primeUnionThread0.start();
			}
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, MemberController.class);
		}
		return new ModelAndView("wx/online-shop/H5cashier", model);
	}
	
	@RequestMapping(value = "addShouXinByAlipay", method = RequestMethod.GET)
	public ModelAndView addShouXinByAlipay(String type, Double tradeMoney,String account,String shouType,String fileImage) {
		Map<String, Object> model = new HashMap<String, Object>();
		try {			
			resultVo = tradeDubbo.addShouXin(type, account, tradeMoney,shouType,fileImage);

			if ("2".equals(type) && resultVo.getCode() == 200) {
				GjfMemberTrade trade = (GjfMemberTrade) resultVo.getResult();
				Map<String, String> obj = H5PayUtil.alipayAppPay( trade.getMemberId(),request,
					trade.getTradeMoney().toString(), trade.getTradeNo(), "充值授信额度", "充值授信额度描述",
					CommonProperties.GJFENG_H5_NOTIFY_SHOUXIN);
				resultVo=new ResultVo(200,"返回成功",obj);
				model.put("resultVo", resultVo);

				// 定义线程
				primeUnionThread0 = new PrimeUnionThread0(trade.getTradeNo());
				primeUnionThread0.start();
			}
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, MemberController.class);
		}
		return new ModelAndView("wx/online-shop/alipayCashier", model);
	}

	// 线程
	class PrimeUnionThread0 extends Thread {
		private String out_trade_on;

		PrimeUnionThread0(String out_trade_on) {
			this.out_trade_on = out_trade_on;
		}

		public void run() {
			try {
				for (int i = 0; i < 3; i++) {
					if (i == 0) {
						Thread.sleep(180000);
					}
					if (i == 1) {
						Thread.sleep(480000);
					}
					System.out.println("线程开启");
					String xml = H5PayUtil.queryH5Order(request, out_trade_on);
					System.out.println(xml);
					String[] str = xml.split("&");
					Map<String, String> map = new HashMap<String, String>();
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
					System.out.println(map);
					/*"000000".equals(map.get("retCode"))&&*/
					if ("0".equals(map.get("status"))) {
						String out_trade_no1 = map.get("merchOrderId");
						String trade_no = map.get("orderId");
						resultVo = tradeDubbo.updateShouXinPayStatus(out_trade_no1, trade_no, "1");
						if (resultVo.getCode() == 200) {
							System.out.println("----线程结束-----");
							primeUnionThread0.interrupt();
						}
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	PrimeUnionThread1 primeUnionThread1=null;
	@RequestMapping(value = "payH5", method = RequestMethod.POST)
	public ModelAndView addOrderByH5(OrderAddModel orderAddModel, @RequestParam("payType") String payType,
			String remark, Long couponsId, @RequestParam("orderAddressId") Long orderAddressId, String payPassword,String account,String logist,String commissionType,String orderSn,String customerSn) {
		Map<String, Object> model = new HashMap<>();
		try {
			List<OrderAddVo> orderAddVos = orderAddModel.getOrderAddVos();
			resultVo = orderInfoDubbo.addOrder(account, orderAddVos, "1", payType, remark, couponsId, orderAddressId,logist,commissionType,orderSn,customerSn,new BigDecimal(0));

			if (resultVo.getCode() == 200) {
				GjfOrderInfo gjfOrderInfo = (GjfOrderInfo) resultVo.getResult();
				// 调用银联支付
				Map<String, String> map = H5PayUtil.H5PayResult(request, response, gjfOrderInfo.getMemberId(),
						gjfOrderInfo.getOfflinePayAmount().toString(), gjfOrderInfo.getOrderSn(), "测试商品", "测试商品描述",
						CommonProperties.GJFENG_H5_NOTIFY_ORDER);
				resultVo.setResult(map);
				model.put("obj", map);
				
				primeUnionThread1=new PrimeUnionThread1(gjfOrderInfo.getOrderSn());
				primeUnionThread1.start();
			}
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, OrderController.class);
		}
		return new ModelAndView("wx/online-shop/H5cashier", model);
	}

	// 线程
	class PrimeUnionThread1 extends Thread {
		private String out_trade_on;

		PrimeUnionThread1(String out_trade_on) {
			this.out_trade_on = out_trade_on;
		}

		public void run() {
			try {
				for (int i = 0; i < 3; i++) {
					if (i == 0) {
						Thread.sleep(180000);
					}
					if (i == 1) {
						Thread.sleep(480000);
					}
					String xml = H5PayUtil.queryH5Order(request, out_trade_on);
					String[] str=xml.split("&");
					Map<String, String> map=new HashMap<String, String>();
					for(int j=0;j<str.length;j++){
						String str0=str[j];
						String[] str1=str0.split("=");
						if(str1.length==1){
							map.put(str1[0], "");
						}else{
							for(int k=0;k<str1.length;k++){
								map.put(str1[0], str1[1]);
							}	
						}					
					}
					/*"000000".equals(map.get("retCode"))&&*/
					if ("0".equals(map.get("status"))) {
						String out_trade_no=map.get("merchOrderId");
						String trade_no=map.get("orderId");
					    resultVo=orderInfoDubbo.updateOrderStatus(out_trade_no, trade_no, "1", null, null, "1");
					    if(resultVo.getCode()==200){
					    	primeUnionThread1.interrupt();
					    }
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * @描述 下单Search

	 * @author Karhs
	 * @date 2017年1月9日
	 * @param orderStatus
	 * @return
	 */
	PrimeUnionThread3 primeUnionThread3 = null;

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "addOrder", method = RequestMethod.POST)
	@ResponseBody
	public Object addOrder(OrderAddModel orderAddModel, @RequestParam("payType") String payType, String remark,
			Long couponsId, @RequestParam("orderAddressId") Long orderAddressId, String payPassword,String logist,String commissionType,String orderSn,String customerSn) {
		try {
			String account = SessionUtil.getAccountSession(request);
			if (payType.equals("0")  || payType.equals("8")) {
				// 如果是全余额支付，则主要校检其支付密码
				GjfMemberInfo info = memberInfoDubbo.findMember(account);
				if (StringUtil.isBlank(info.getPayPassword())) {
					resultVo = new ResultVo(402, "请先设置支付密码", null);
					return resultVo;
				}
				if (!info.getPayPassword().equals(EncryptionUtil.getMD5Code(info.getMobile() + payPassword))) {
					resultVo = new ResultVo(401, "支付密码错误", null);
					return resultVo;
				}
			}
			List<OrderAddVo> orderAddVos = orderAddModel.getOrderAddVos();
			resultVo = orderInfoDubbo.addOrder(account, orderAddVos, "1", payType, remark, couponsId, orderAddressId,logist,commissionType,orderSn,customerSn,new BigDecimal(0));

			if (resultVo.getCode() == 200) {
				GjfOrderInfo gjfOrderInfo = (GjfOrderInfo) resultVo.getResult();
				if (payType.equals("1")) {
					// 调用微信支付
					GjfWeiXinPayInfo payInfo = (GjfWeiXinPayInfo) tradeDubbo.findWeiXinBaseInfo("0").getResult();
					/*String token_id = H5PayUtil.weiXinPay(payInfo, request, gjfOrderInfo.getOrderSn(),
							gjfOrderInfo.getOfflinePayAmount().toString(), "购买商品",
							CommonProperties.GJFENG_WEIXIN_NOTIFY_ORDER,
							CommonProperties.GJFENG_WEIXIN_CALLBACK_URL_ORDER, gjfOrderInfo.getMemberId().getOpenId());*/
					Map map=WeixinUtil.unifiedorderResultInApp(request,gjfOrderInfo.getOrderSn(),"充值授信额度",gjfOrderInfo.getOfflinePayAmount().toString(),CommonProperties.GJFENG_WEIXIN_JSPAY_NOTIFY_URL_ORDER);
					resultVo.setResult(map);
				} else if (payType.equals("2")&&!"1".equals(gjfOrderInfo.getSuoceGood())) {
					// 调用支付宝支付
					Map<String, String> map=new HashMap<>();
					Map<String, String> params = OrderInfoUtil2_0.getAppPlayParams(gjfOrderInfo.getOrderSn(), "下单成功",
							String.valueOf(gjfOrderInfo.getOfflinePayAmount().doubleValue()), "购买商品", CommonProperties.GJFENG_APLY_PAY_NOTIFY_URL_ORDER);
					String orderParam = OrderInfoUtil2_0.buildOrderParam(params, false);
					String sign = OrderInfoUtil2_0.getSign(params, AlipayConfig.RSA_PRIVATE);
					final String orderInfo = orderParam + "&" + sign + "&sign_type=\"RSA\"";
					map.put("payString", orderInfo);
					resultVo.setResult(map);
					
				} else if (payType.equals("3")) {
					// 调用银联支付
					Map<String, String> map = H5PayUtil.H5PayResult(request, response, gjfOrderInfo.getMemberId(),
							gjfOrderInfo.getOfflinePayAmount().toString(), gjfOrderInfo.getOrderSn(), "测试商品", "测试商品描述",
							CommonProperties.GJFENG_H5_NOTIFY_ORDER);
					resultVo.setResult(map);
					primeUnionThread3 = new PrimeUnionThread3(gjfOrderInfo.getOrderSn());
					primeUnionThread3.start();
				}
			}

		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, OrderController.class);
		}
		return resultVo;
	}

	// 线程
	class PrimeUnionThread3 extends Thread {
		private String out_trade_on;

		PrimeUnionThread3(String out_trade_on) {
			this.out_trade_on = out_trade_on;
		}

		public void run() {
			try {
				for (int i = 0; i < 3; i++) {
					if (i == 0) {
						Thread.sleep(180000);
					}
					if (i == 1) {
						Thread.sleep(480000);
					}
					String xml = H5PayUtil.queryH5Order(request, out_trade_on);
					String[] str = xml.split("&");
					Map<String, String> map = new HashMap<String, String>();
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
					if (map.get("retCode").equals("000000")) {
						String out_trade_no = map.get("merchOrderId");
						String trade_no = map.get("orderId");
						resultVo = orderInfoDubbo.updateOrderStatus(out_trade_no, trade_no, "1", null, null, "1");
						if (resultVo.getCode() == 200) {
							primeUnionThread3.interrupt();
						}
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * @描述 结算购物车
	 * @author Karhs
	 * @date 2017年1月9日
	 * @param orderStatus
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "appActCart", method = RequestMethod.GET)
	public ModelAndView appActCart(@RequestParam("cartIds") String cartIds,String account) {
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			
			/*if(!BeanUtil.isValid(account)){
				return new ModelAndView("wx/app-register", model);
			}*/
			// 查询当前用户的最新信息
			model.put("gjfMemberInfo", memberInfoDubbo.findMember(account));
			// 查询用户默认的收货地址
			model.put("memberDefAddress", addressDubbo.findMyDefDeliveryAddress(account,"1"));
			// 查询订单信息
			resultVo = cartInfoDubbo.caculateCart(account, cartIds);
			Object o = resultVo.getResult();
			if (ObjValid.isValid(o)) {
				Map<String, Object> data = (Map<String, Object>) o;
				model.put("totalAmount", data.get("totalAmount"));
				model.put("goodsVos", data.get("goodsVos"));
				model.put("orderAddVos", data.get("orderAddVos"));
				model.put("isCanUseCou", data.get("isCanUseCou"));
				model.put("pos", data.get("pos"));
				model.put("goodSource", data.get("goodSource"));
				model.put("pointNiceAmount", data.get("pointNiceAmount"));
			}

			// 移除购物车
			String[] carIds = cartIds.split(",");
			for (String str : carIds) {
				cartInfoDubbo.delCart(Long.valueOf(str), account);
			}
			// model.put("orderAddVos", orderAddVos);
			// 查询优惠券

			// 查询物流费用
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, OrderController.class);
			model.put("memberDefAddress", null);
			model.put("orderGoods", null);
		}

		return new ModelAndView("wx/online-shop/pay-affirm", model);

	}
	
	
	/**
	 * @描述 未支付订单调用支付
	 * @param orderSn
	 * @return
	 */
	@RequestMapping(value = "payOrderSignByH5", method = RequestMethod.GET)
	public ModelAndView payOrderSignByH5(@RequestParam("orderSn") String orderSn,String account) {
		Map<String, Object> model = new HashMap<>();
		try {			
			resultVo = orderInfoDubbo.findOrderDetail(orderSn, account);
			if (resultVo.getCode() == 200) {
				OrderInfoVo infoVo = (OrderInfoVo) resultVo.getResult();
				
				//有余额的先判断用户余额是否充足
				if("4".equals(infoVo.getPayType())||"6".equals(infoVo.getPayType())){
					GjfMemberInfo info = memberInfoDubbo.findMember(account);
					if(infoVo.getOnlinePayAmount().compareTo(info.getBalanceMoney())>1){
						//如果余额不足就进行资金的调整
						String newOrderSn=DateHelper.dataToString(new Date(), "yyyyMMddHHmmss" + RandUtil.getRandomStr(6));
						infoVo.setOnlinePayAmount(info.getBalanceMoney());
						infoVo.setOfflinePayAmount(infoVo.getOrderTotalAmount().subtract(info.getBalanceMoney()));
						orderInfoDubbo.updateOrderPayMoney(infoVo.getOrderSn(),infoVo.getOnlinePayAmount().doubleValue(),infoVo.getOfflinePayAmount().doubleValue(),newOrderSn);
						infoVo.setOrderSn(newOrderSn);
					}
				}
				
				if (infoVo.getPayType().equals("3")) {
					// 调用微信支付
					Map<String, String> map = H5PayUtil.H5PayResult(request, response, infoVo.getMemberId(),
							infoVo.getOfflinePayAmount().toString(), infoVo.getOrderSn(), "测试商品", "测试商品描述",
							CommonProperties.GJFENG_H5_NOTIFY_ORDER);
					resultVo.setResult(map);
					model.put("obj", map);
				}

			}
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, OrderController.class);
		}
		return new ModelAndView("wx/online-shop/H5cashier", model);
	}
	
	
	/**
	 * app注册后跳转到下载引导页面
	 * 
	 */
	@RequestMapping(value = "appDownLoadGuide", method = RequestMethod.GET)
	public ModelAndView appDownLoadGuide(Long superId){
		Map<String, Object> model=new HashMap<>();
		try{
			GjfMemberInfo member=(GjfMemberInfo) memberInfoDubbo.findMemberById(superId).getResult();
			if(BeanUtil.isValid(member.getUnionId())&&!BeanUtil.isValid(member.getImgQrUrl())){
				GjfAccessToken accessToken = (GjfAccessToken) memberInfoDubbo.getAccessToken("1").getResult();
				Token tokens = new Token();
				if (BeanUtil.isValid(accessToken)) {
					Date time = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

					if (sdf.parse(sdf.format(accessToken.getExpirationTime())).compareTo(sdf.parse(sdf.format(time))) == 1) {
						tokens.setAccess_token(accessToken.getToken());
					} else {
						tokens = TokenAPI.token(CommonProperties.GJFENG_APP_ID, CommonProperties.GJFENG_APPSECRET);
						// 如果请求失败重新获取token
						int i = 0;
						while (!ObjValid.isValid(tokens.getAccess_token()) && i < 5) { //
							tokens = TokenAPI.token(CommonProperties.GJFENG_APP_ID, CommonProperties.GJFENG_APPSECRET);
							if (!ObjValid.isValid(tokens.getAccess_token())) { // 若取不到snsToken、则不获取openid;
								Thread.sleep(500); // 睡眠0.5 再取
							}
							i++;
						}
						GjfAccessToken token = new GjfAccessToken(CommonProperties.GJFENG_APP_ID, CommonProperties.GJFENG_APPSECRET,
							"1");

						token.setToken(tokens.getAccess_token());
						memberInfoDubbo.addAccessToken(token);
					}
				} else {
					tokens = TokenAPI.token(CommonProperties.GJFENG_APP_ID, CommonProperties.GJFENG_APPSECRET);
					// 如果请求失败重新获取token
					int i = 0;
					while (!ObjValid.isValid(tokens.getAccess_token()) && i < 5) { //
						tokens = TokenAPI.token(CommonProperties.GJFENG_APP_ID, CommonProperties.GJFENG_APPSECRET);
						if (!ObjValid.isValid(tokens.getAccess_token())) { // 若取不到snsToken、则不获取openid;
							Thread.sleep(500); // 睡眠0.5 再取
						}
						i++;
					}
					GjfAccessToken token = new GjfAccessToken(CommonProperties.GJFENG_APP_ID, CommonProperties.GJFENG_APPSECRET,
						"1");

					token.setToken(tokens.getAccess_token());
					memberInfoDubbo.addAccessToken(token);
				}

				String access_token = tokens.getAccess_token();

				// 生成二维码
				QrcodeTicket qrcodeTicket = QrcodeAPI.qrcodeCreateFinalStr(access_token, member.getId().toString());
				if ("40001".equals(qrcodeTicket.getErrcode())) {
					tokens = TokenAPI.token(CommonProperties.GJFENG_APP_ID, CommonProperties.GJFENG_APPSECRET);
					GjfAccessToken token = new GjfAccessToken(CommonProperties.GJFENG_APP_ID, CommonProperties.GJFENG_APPSECRET,
						"1");
					token.setToken(tokens.getAccess_token());
					memberInfoDubbo.addAccessToken(token);
					qrcodeTicket = QrcodeAPI.qrcodeCreateFinalStr(access_token, member.getId().toString());
				}
				String url = "";
				String path = "";
				try {
					url = GenerateQrCodeUtil.generateQrcode(request, qrcodeTicket.getUrl(), projectNames, imageFolderNames);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (BeanUtil.isValid(url)) {
					path = projectName + imageFolderNames + "/" + url;
				}
				member.setImgQrUrl(path);
				
				memberInfoDubbo.updateMemberCode(member);
			}
			model.put("member", member);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new ModelAndView("wx/o2o-shop/new_file",model);
	}
	
	/**
	 * 积分+现金支付
	 * @param orderAddModel
	 * @param payType
	 * @param remark
	 * @param couponsId
	 * @param orderAddressId
	 * @param payPassword
	 * @return
	 */
	@RequestMapping(value = "/pointNeedPay", method = RequestMethod.GET)
	public ModelAndView pointNeedPay(String payMoney,String orderSn) {
		Map<String, Object> model = new HashMap<>();
		try {
			String account = SessionUtil.getAccountSession(request);
			GjfMemberInfo memberInfo=memberInfoDubbo.findMember(account);
			model.put("orderId", orderSn);
			model.put("payMoney", payMoney);
			model.put("isReadName", memberInfo.getIsReadName());
			model.put("idCard",memberInfo.getIdCard());
			model.put("mobile", memberInfo.getMobile());
			model.put("retUrl", CommonProperties.GJFENG_WANGYIN_PAY_NOTIFY_URL_ORDER);
			// 查询用户最新的资金情况和所有的银行卡列表
			resultVo = tradeDubbo.toDrawCash(account);
			model.put("resultVo", resultVo);				
			
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, OrderController.class);
		}
		//return new ModelAndView("wx/online-shop/H5cashier", model);
		return new ModelAndView("wx/o2o-shop/H5payConfirm", model);
	}
	
	/**
	 * appw网银支付
	 * @param payMoney
	 * @param orderSn
	 * @return
	 */
	@RequestMapping(value = "/appWangYinPay", method = RequestMethod.GET)
	public ModelAndView appWangYinPay(String payMoney,String orderSn,String mobile,String type) {
		Map<String, Object> model = new HashMap<>();
		try {
			GjfMemberInfo memberInfo=memberInfoDubbo.findMember(mobile);
			model.put("orderId", orderSn);
			model.put("payMoney", payMoney);
			model.put("isReadName", memberInfo.getIsReadName());
			model.put("idCard",memberInfo.getIdCard());
			model.put("mobile", memberInfo.getMobile());
			if("0".equals(type)){
				model.put("retUrl", CommonProperties.GJFENG_WANGYIN_PAY_NOTIFY_URL_SHOUXIN);
			}
			if("1".equals(type)){
				model.put("retUrl", CommonProperties.GJFENG_WANGYIN_PAY_NOTIFY_URL_BENERFIT);
			}
			if("2".equals(type)){
				model.put("retUrl", CommonProperties.GJFENG_WANGYIN_PAY_NOTIFY_URL_ORDER);
			}
			
			// 查询用户最新的资金情况和所有的银行卡列表
			resultVo = tradeDubbo.toDrawCash(memberInfo.getMobile());
			model.put("resultVo", resultVo);				
			
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, OrderController.class);
		}
		//return new ModelAndView("wx/online-shop/H5cashier", model);
		return new ModelAndView("wx/o2o-shop/H5payConfirm", model);
	}
	
	
	/**
	 * 跳转app下载页面
	 * @return
	 */
	@RequestMapping(value="/goUpgradeAppPage",method=RequestMethod.GET)
	public Object goUpgradeAppPage(){
		return new ModelAndView("wx/o2o-shop/app-download");
	}


	

}
