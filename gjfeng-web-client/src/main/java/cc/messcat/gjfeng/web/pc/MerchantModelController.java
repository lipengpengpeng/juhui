package cc.messcat.gjfeng.web.pc;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alipay.direct.config.AlipayConfig;
import com.alipay.direct.util.AlipaySubmit;
import com.alipay.direct.util.OrderInfoUtil2_0;
import com.newAlipay.util.NewsOrderUtil;
import com.wechat.WeixinUtil;
import com.wechat.popular.bean.pay.PayJsRequest;

import cc.messcat.gjfeng.common.constant.CommonProperties;
import cc.messcat.gjfeng.common.exception.LoggerPrint;
import cc.messcat.gjfeng.common.util.BeanUtil;
import cc.messcat.gjfeng.common.util.EncryptionUtil;
import cc.messcat.gjfeng.common.util.GenerateQrCodeUtil;
import cc.messcat.gjfeng.common.util.ObjValid;
import cc.messcat.gjfeng.common.util.StringUtil;
import cc.messcat.gjfeng.common.vo.app.OrderAddModel;
import cc.messcat.gjfeng.common.vo.app.OrderAddVo;
import cc.messcat.gjfeng.common.vo.app.ResultVo;
import cc.messcat.gjfeng.common.web.BaseController;
import cc.messcat.gjfeng.dubbo.core.GjfAddressDubbo;
import cc.messcat.gjfeng.dubbo.core.GjfCartInfoDubbo;
import cc.messcat.gjfeng.dubbo.core.GjfEnterpriseColumnDubbo;
import cc.messcat.gjfeng.dubbo.core.GjfLoginDubbo;
import cc.messcat.gjfeng.dubbo.core.GjfMemberInfoDubbo;
import cc.messcat.gjfeng.dubbo.core.GjfOrderInfoDubbo;
import cc.messcat.gjfeng.dubbo.core.GjfProductAttrDubbo;
import cc.messcat.gjfeng.dubbo.core.GjfProductInfoDubbo;
import cc.messcat.gjfeng.dubbo.core.GjfTradeDubbo;
import cc.messcat.gjfeng.entity.GjfFhTreasureInfo;
import cc.messcat.gjfeng.entity.GjfMemberInfo;
import cc.messcat.gjfeng.entity.GjfOrderInfo;
import cc.messcat.gjfeng.entity.GjfProductInfo;
import cc.messcat.gjfeng.entity.GjfWeiXinPayInfo;
import cc.messcat.gjfeng.util.SessionUtil;
import cc.messcat.gjfeng.web.app.v1.CartControllerV1;
import cc.messcat.gjfeng.web.app.v1.LoginControllerV1;
import cc.messcat.gjfeng.web.wechat.AddressController;
import cc.messcat.gjfeng.web.wechat.IndexController;
import cc.messcat.gjfeng.web.wechat.OrderController;
import cc.messcat.gjfeng.web.wechat.ProductController;

@Controller
@RequestMapping(value="pc/merchant")
public class MerchantModelController extends BaseController{

	@Autowired
	@Qualifier("loginDubbo")
	private GjfLoginDubbo loginDubbo;
	
	@Autowired
	@Qualifier("request")
	private HttpServletRequest request;
	
	@Autowired
	@Qualifier("memberInfoDubbo")
	private GjfMemberInfoDubbo memberInfoDubbo;
	
	@Autowired
	@Qualifier("productInfoDubbo")
	private GjfProductInfoDubbo productInfoDubbo;
	
	@Autowired
	@Qualifier("enterpriseColumnDubbo")
	private GjfEnterpriseColumnDubbo enterpriseColumnDubbo;
	
	@Autowired
	@Qualifier("orderInfoDubbo")
	private GjfOrderInfoDubbo orderInfoDubbo;
	
	@Autowired
	@Qualifier("addressDubbo")
	private GjfAddressDubbo addressDubbo;
	
	@Autowired
	@Qualifier("cartInfoDubbo")
	private GjfCartInfoDubbo cartInfoDubbo;
	
	@Autowired
	@Qualifier("productAttrDubbo")
	private GjfProductAttrDubbo productAttrDubbo;
	
	@Autowired
	@Qualifier("tradeDubbo")
	private GjfTradeDubbo tradeDubbo;
	
	@Value("${dubbo.application.web.client}")
	private String projectNames;
	
	@Value("${upload.member.idcard.path}")
	private String imageFolderName;

	//跳转到登录页面
	@RequestMapping(value = "/goLoginPage", method = RequestMethod.GET)
	public ModelAndView goLoginPage(){
		Map<String, Object> model=new HashMap<>();		
		return new ModelAndView("pc/index", model);
	}
	
	/**
	 * @描述 联盟商家pc端登录
	 * @date 2017年3月14日
	 * @param account
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Object login(@RequestParam("account") String account) {
		try {
			if(!BeanUtil.isValid(account)){
				resultVo=new ResultVo(400, "用户不存在");
			}
			GjfMemberInfo member=memberInfoDubbo.findMember(account);
			if(!BeanUtil.isValid(member)){
				resultVo=new ResultVo(400, "用户不存在");
			}			
			request.getSession().setAttribute("account", account);
			
			if("0".equals(member.getMerchantType())){
				resultVo=new ResultVo(400, "你不是联盟商家请升级后再进行登录");
			}
			
			if("1".equals(member.getMerchantType())){
				request.getSession().setAttribute("merchantType", "1");
			}else{
				request.getSession().setAttribute("merchantType", "2");
			}
			resultVo=new ResultVo(200, "登录成功",member);
			request.getSession().setAttribute("gjfMemberInfo", member);			
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, LoginControllerV1.class);
		}
		return resultVo;
	}
	
	/**
	 * 获取联盟商家首页商品信息
	 * @return
	 */
	@RequestMapping(value = "goSpecifiedMerchantOnilne", method = RequestMethod.GET)
	public ModelAndView goSpecifiedMerchantOnilne(){
		Map<String, Object> model = new HashMap<String, Object>();		
		try {
			//获取商品信息
			model.put("products", productInfoDubbo.findMerchantProcurementProductInPc(null,null, 1, 20,null));
			//获取栏目信息
			model.put("column", enterpriseColumnDubbo.findProductModelColumn("MODELPRODUCT","0"));	
			
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, IndexController.class);			
			model.put("resultVo",null);
		}
		return new ModelAndView("pc/list", model);
	}
	
	/**
	 * @描述 查询网上商城商品详情
	 * @author Karhs
	 * @date 2017年1月9日
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "online/product/{id}", method = RequestMethod.GET)
	public ModelAndView onlineProductsDetailInfo(@PathVariable("id") Long id,String type) {
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			// 查询商品信息
			// model.put("resultVo", productInfoDubbo.findProductById(id, 0L));
			resultVo = productAttrDubbo.findProAttrByProId(id);
			Object o = resultVo.getResult();
			if (ObjValid.isValid(o)) {
				LinkedHashMap<String, Object> data = new LinkedHashMap<String, Object>();
				data = (LinkedHashMap<String, Object>) o;				
				model.put("productInfo", data.get("productInfo"));
				GjfProductInfo productInfo=(GjfProductInfo) data.get("productInfo");
				if("1".equals(productInfo.getSuorceGoods())){
					String account = SessionUtil.getAccountSession(request);
					model.put("memberInfo",memberInfoDubbo.findMember(account));
				}
				model.put("productAttrStock", data.get("productAttrStock"));
				data.remove("productInfo");
				data.remove("productAttrStock");
				model.put("productAttrs", data);
			}

			// 2.查询商品评论次数
			model.put("commentsNum", 0);
			model.put("type", type);
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, ProductController.class);
			model.put("resultVo", resultVo);
		}
		return new ModelAndView("pc/detail", model);
	}
	
	/**
	 * 根据条件获取采购商品
	 * @param columnId
	 * @param pageNo
	 * @param pageSize
	 * @param likeName
	 * @param type
	 * @param discount
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="getMechartProList",method=RequestMethod.GET)
	public Object getMechartProList(Long columnId,Integer pageNo,Integer pageSize,String likeName,String discount ){
		try{
			resultVo=productInfoDubbo.findMerchantProcurementProductInPc(columnId,likeName, pageNo, pageSize,discount);
		}catch(Exception e){
			e.printStackTrace();
		}
		return resultVo;
	}

		
	/**
	 * 获取用户订单信息
	 * @param aounct
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="findMemberOrderInfo",method=RequestMethod.GET)
	public ModelAndView findMemberOrderInfo(Integer pageNo,Integer pageSize,String status){
		Map<String, Object> model=new HashMap<>();
		try {
			String account = SessionUtil.getAccountSession(request);
			if(!BeanUtil.isValid(account)){
				return new ModelAndView("pc/index", model);
			}
			
			resultVo = orderInfoDubbo.findMyOrder(ObjValid.isNotValid(pageNo) ? 1 : pageNo,
					ObjValid.isNotValid(pageSize) ? 10 : pageSize, account, status);
			model.put("orderInfo", resultVo.getResult());
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, OrderController.class);
		}
		return  new ModelAndView("pc/orderList", model);
	}
	
	/**
	 * @描述 跳转到我的收货地址
	 * @author Karhs
	 * @date 2017年1月9日
	 * @return
	 */
	@RequestMapping(value = "toFind", method = RequestMethod.GET)
	public ModelAndView toMyAddress(String type,String goodSource){
		Map<String, Object> model = new HashMap<String, Object>();
		try {
            String account=SessionUtil.getAccountSession(request);
			if(!BeanUtil.isValid(account)){
				return new ModelAndView("pc/index", model);
			}
			resultVo=addressDubbo.findMyDeliveryAddress(account,goodSource);
			model.put("resultVo", resultVo);
			model.put("type", type);
			model.put("goodSource", goodSource);
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, AddressController.class);
			model.put("resultVo", resultVo);
		}
		return new ModelAndView("pc/address-choice", model);
	}
	
	/**
	 * @描述 跳转到添加收货地址页面
	 * @return
	 */
	@RequestMapping(value = "goNewsAddress", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView goNewsAddress(String type,String goodSource){
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("type", type);
		model.put("goodSource", goodSource);
		return new ModelAndView("pc/address-add",model);
	}
	
	/**
	 * @描述 跳转到修改我的收货地址
	 * @author Karhs
	 * @date 2017年1月9日
	 * @return
	 */
	@RequestMapping(value = "toUpdate", method = RequestMethod.GET)
	@ResponseBody
	public Object toUpdateMyAddress(@RequestParam("id") Long id,String goodSource){
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			
			String account=SessionUtil.getAccountSession(request);
			if(!BeanUtil.isValid(account)){
				return new ModelAndView("pc/index", model);
			}
			resultVo=addressDubbo.findAddressById(id,account,goodSource);
			model.put("resultVo", resultVo);
			model.put("goodSource", goodSource);
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, AddressController.class);
		}
		return new ModelAndView("pc/address-update", model);
	}
	
	/**
	 * @描述 新增地址
	 * @return
	 */
	@RequestMapping(value = "newsAddress", method = RequestMethod.POST)
	@ResponseBody
	public Object newsAddress(String consigneeName,String consigneeSex,String mobile,Long proviceId,Long cityId,Long areaId,String addressDetail,String goodSource,Long townId){	
		try {
			String account=SessionUtil.getAccountSession(request);
			Object[] object={account,consigneeName,consigneeSex,mobile,proviceId,cityId,areaId,addressDetail,goodSource,townId};
			resultVo=addressDubbo.addDeliveryAddress(object);
			
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, AddressController.class);
		}
		return resultVo;		
	}
	
	/**
	 * @描述获取省市区
	 * @return
	 */
	@RequestMapping(value = "getAllProvince", method = RequestMethod.GET)
	@ResponseBody
	public Object getAllProvince(Long fatherId,String addressType,String goodSource){
		try {
			if("2".equals(goodSource)){
				goodSource="0";
			}
			resultVo=addressDubbo.findAddress(fatherId, addressType,goodSource);
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, AddressController.class);
		}
		return resultVo;
	}
	
	/**
	 * @描述 删除收货地址
	 * @author Karhs
	 * @date 2017年1月9日
	 * @return
	 */
	@RequestMapping(value = "del", method = RequestMethod.POST)
	@ResponseBody
	public Object delAddress(@RequestParam("id") Long id){
		try {
			String account=SessionUtil.getAccountSession(request);
			resultVo=addressDubbo.delAddress(id, account);
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, AddressController.class);
		}
		return resultVo;
	}
	
	/**
	 * @描述 修改收货地址
	 * @author Karhs
	 * @date 2017年1月9日
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public Object updateAddress(@RequestParam("id") Long id,String consigneeName,String consigneeSex,String mobile,Long proviceId,Long cityId,Long areaId,String addressDetail,Long townId,String goodSource){
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			String account=SessionUtil.getAccountSession(request);
			if(!BeanUtil.isValid(account)){
				return new ModelAndView("pc/index", model);
			}
			Object[] object={account,consigneeName,consigneeSex,mobile,proviceId,cityId,areaId,addressDetail,id,townId};
			addressDubbo.updateAddress(object);
			resultVo=addressDubbo.findMyDeliveryAddress(account,goodSource);
			model.put("resultVo", resultVo);
			if("2".equals(goodSource)){
				goodSource="0";
			}
			model.put("goodSource", goodSource);
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, AddressController.class);
		}
		return new ModelAndView("wx/online-shop/address-choice", model);
	}
	
	/**
	 * @描述 我的购物车
	 * @date 2017年1月9日
	 * @return
	 */
	@RequestMapping(value = "myCart", method = RequestMethod.GET)
	public ModelAndView toMyCart() {
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			String account=SessionUtil.getAccountSession(request);
			if(!BeanUtil.isValid(account)){
				return new ModelAndView("pc/index", model);
			}
			 resultVo = cartInfoDubbo.findMyCart(account);
			 model.put("cart", resultVo.getResult());
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, CartControllerV1.class);	
		}
		return new ModelAndView("pc/cart", model);
	}

	/**
	 * @描述 添加商品到购物车
	 * @date 2017年1月9日
	 * @param proId
	 * @param proAttrId
	 * @param num
	 * @return
	 */
	@RequestMapping(value = "addCart", method = RequestMethod.POST)
	@ResponseBody
	public Object addCart(OrderAddVo orderAddVo) {
		try {		
			String account=SessionUtil.getAccountSession(request);
			resultVo = cartInfoDubbo.addCart(orderAddVo, account);
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, CartControllerV1.class);
		}
		return resultVo;
	}
	
	/**
	 * @描述 修改购物车商品数量
	 * @date 2017年1月17日
	 * @param id
	 * @param goodsNum
	 * @return
	 */
	@RequestMapping(value = "updateCartNum", method = RequestMethod.POST)
	@ResponseBody
	public Object updateCart(@RequestParam("id") Long id, @RequestParam("goodsNum") Long goodsNum) {
		try {
			String account=SessionUtil.getAccountSession(request);
			resultVo = cartInfoDubbo.updateCartNum(id, goodsNum, account);
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, CartControllerV1.class);
		}
		return resultVo;
	}
	
	/**
	 * @描述 删除购物车商品
	 * @date 2017年1月9日
	 * @param proId
	 * @param proAttrId
	 * @param num
	 * @return
	 */
	@RequestMapping(value = "delCart/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Object delCart(@PathVariable("id") Long id) {
		try {
			String account=SessionUtil.getAccountSession(request);
			resultVo = cartInfoDubbo.delCart(id, account);
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, CartControllerV1.class);
		}
		return resultVo;
	}
	
	/**
	 * @描述 忘记密码
	 * @param account
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "forgetPassWord", method = RequestMethod.POST)
	@ResponseBody
	public Object forget(@RequestParam("mobile") String mobile, @RequestParam("newPassword") String newPassword,
		@RequestParam("reNewPassword") String reNewPassword) {
		try {
			resultVo=loginDubbo.forgetPwd(mobile, newPassword, reNewPassword);
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, LoginControllerV1.class);
		}
		return resultVo;
	}
	
	
	/**
	 * @描述 结算购物车
	 * @author Karhs
	 * @date 2017年1月9日
	 * @param orderStatus
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "actCart", method = RequestMethod.POST)
	public ModelAndView actCart(@RequestParam("cartIds") String cartIds) {
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			String account = SessionUtil.getAccountSession(request);
			if(!BeanUtil.isValid(account)){
				return new ModelAndView("pc/index", model);
			}
			// 查询当前用户的最新信息
			GjfMemberInfo member = memberInfoDubbo.findMember(account);
			model.put("gjfMemberInfo", member);
			
			// 查询用户凤凰宝信息
			GjfFhTreasureInfo fh = (GjfFhTreasureInfo) tradeDubbo.findFhTreasureInfo(account).getResult();
			if (!BeanUtil.isValid(fh)) {
				model.put("fhTreasureInfo", 0);
			} else {
				model.put("fhTreasureInfo", fh.getFhTreasureMoney());
			}					
			// 查询当前用户的最新信息
			if ("1".equals(member.getMerchantType())) {
				model.put("merchantType", "1");
			} else if ("0".equals(member.getMerchantType())) {
				model.put("merchantType", "0");
			} else {
				model.put("merchantType", "2");
			}			
			// 查询订单信息
			resultVo = cartInfoDubbo.caculateCart(account, cartIds);
			// 查询用户默认的收货地址			
			Object o = resultVo.getResult();
			if (ObjValid.isValid(o)) {
				Map<String, Object> data = (Map<String, Object>) o;
				model.put("memberDefAddress", addressDubbo.findMyDefDeliveryAddress(account,  data.get("goodSource").toString()));				
				model.put("orderSn", data.get("orderSn"));
				model.put("customerSn", data.get("customerSn"));
				model.put("totalAmount", data.get("totalAmount"));
				model.put("goodsVos", data.get("goodsVos"));
				model.put("orderAddVos", data.get("orderAddVos"));
				model.put("isCanUseCou", data.get("isCanUseCou"));
				model.put("pos", data.get("pos"));
				model.put("isWholesale", data.get("isWohsalse"));
				model.put("goodSource", data.get("goodSource"));
				model.put("pointNiceAmount", data.get("pointNiceAmount"));
				model.put("standardTotalAmount", data.get("standardTotalAmount"));
				model.put("honourTotalAmount", data.get("honourTotalAmount"));
				model.put("honourPreferentialMoney", data.get("honourPreferentialMoney"));
				model.put("standardPreferentialMoney", data.get("standardPreferentialMoney"));
				model.put("logist", data.get("logist"));				
			}
			// 移除购物车
			String[] carIds = cartIds.split(",");
			/*for (String str : carIds) {
				cartInfoDubbo.delCart(Long.valueOf(str), account);
			}*/
		
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, OrderController.class);
			model.put("memberDefAddress", null);
			model.put("orderGoods", null);
		}

		return new ModelAndView("pc/payOrder", model);

	}
	
	
	/**
	 * @描述 下单
	 * @date 2017年1月9日
	 * @param orderStatus
	 * @return
	 */
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public ModelAndView addOrder(OrderAddModel orderAddModel, @RequestParam("payType") String payType, String remark,
			Long couponsId, @RequestParam("orderAddressId") Long orderAddressId, String payPassword,double pointNiceMoney,String logist,String commissionType,String orderSn,String customerSn,BigDecimal postage) {
		Map<String, Object> model=new HashMap<>();
		try {
			
			String account = SessionUtil.getAccountSession(request);
			if(!BeanUtil.isValid(account)){
				return new ModelAndView("pc/index", model);
			}

			/*if (payType.equals("0") || (payType.equals("8")&&pointNiceMoney==0) || payType.equals("9")|| (payType.equals("10")&&pointNiceMoney==0)) {
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
			}*/
			List<OrderAddVo> orderAddVos = orderAddModel.getOrderAddVos();
			resultVo = orderInfoDubbo.addOrder(account, orderAddVos, "1", payType, remark, couponsId, orderAddressId,logist,commissionType,orderSn,customerSn,postage);

			if (resultVo.getCode() == 200) {
				GjfOrderInfo gjfOrderInfo = (GjfOrderInfo) resultVo.getResult();
				if (payType.equals("1")
						|| ("7".equals(payType) && gjfOrderInfo.getOfflinePayAmount().doubleValue() != 0)
						|| ("8".equals(payType) && gjfOrderInfo.getOfflinePayAmount().doubleValue() != 0)|| ("10".equals(payType) && gjfOrderInfo.getOfflinePayAmount().doubleValue() != 0)) {
									
					// 微信原生
					PayJsRequest json = WeixinUtil.unifiedorderResult(request, gjfOrderInfo.getOrderSn(), "购买商品",
							"NATIVE", gjfOrderInfo.getOfflinePayAmount().toString(),
							CommonProperties.GJFENG_WEIXIN_JSPAY_NOTIFY_URL_ORDER,
							gjfOrderInfo.getMemberId().getOpenId());
					
					model.put("orderInfo", resultVo.getResult());
					String url = GenerateQrCodeUtil.generateQrcode(request, json.getNonceStr(), projectNames, imageFolderName);
					String path = "/"+projectNames + imageFolderName + "/" + url;
					model.put("payInfo", path);
					return new ModelAndView("pc/pay", model);

				} else if (payType.equals("2") && gjfOrderInfo.getSuoceGood() != "1") {
					/*Map<String, String> params = OrderInfoUtil2_0.getAppPlayParams(gjfOrderInfo.getOrderSn(), "下单成功",
							gjfOrderInfo.getOfflinePayAmount().toString(), "购买商品",CommonProperties.GJFENG_APLY_PAY_NOTIFY_URL_ORDER);
					String orderParam = OrderInfoUtil2_0.buildOrderParam(params, false);
					String sign = OrderInfoUtil2_0.getSign(params, AlipayConfig.RSA_PRIVATE);
					final String orderInfo = orderParam + "&" + sign + "&sign_type=\"RSA\"";*/
					//Map<String, String> params=AlipaySubmit.getParams(gjfOrderInfo.getOrderSn(),gjfOrderInfo.getOfflinePayAmount().toString(),CommonProperties.GJFENG_APLY_PAY_NOTIFY_URL_ORDER,AlipayConfig.return_url_inweb);
					//String requestText = AlipaySubmit.buildRequest(params, "get", "确认");	
					String requestText=NewsOrderUtil.getWebPayBody(gjfOrderInfo.getOrderSn(),"用户购买商品",gjfOrderInfo.getOfflinePayAmount().toString(),"购买商品",CommonProperties.GJFENG_APLY_PAY_NOTIFY_URL_ORDER,AlipayConfig.return_url_inweb);
					model.put("orderInfo", requestText);
					request.setAttribute("requestText", requestText);					
					return new ModelAndView("pc/alipayapi", model);
				} 
				
			}

		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, OrderController.class);
		}
		return new ModelAndView("pc/pay", model);
	}
	
	
	
}
