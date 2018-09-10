package cc.messcat.web.subsystem;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import cc.messcat.gjfeng.common.bean.Pager;
import cc.messcat.gjfeng.common.exception.LoggerPrint;
import cc.messcat.gjfeng.common.fastpay.payForOther.FastPayDemoTest;
import cc.messcat.gjfeng.common.jd.JdUtil;
import cc.messcat.gjfeng.common.jd.bean.CheckAccountResult;
import cc.messcat.gjfeng.common.pay.alipay.util.AlipayRefundUtil;
import cc.messcat.gjfeng.common.pay.wechat.util.RefundUtil;
import cc.messcat.gjfeng.common.util.BeanUtil;
import cc.messcat.gjfeng.common.util.ObjValid;
import cc.messcat.gjfeng.common.vo.app.ResultVo;
import cc.messcat.gjfeng.entity.GjfOrderAddress;
import cc.messcat.gjfeng.entity.GjfOrderGoods;
import cc.messcat.gjfeng.entity.GjfOrderInfo;
import cc.modules.commons.PageAction;
import net.sf.json.JSONObject;

public class OrderInfoAction extends PageAction  {

	private static final long serialVersionUID = 1L;
	
	private Long addreId;
	
	private String likeValue;
	
	private String orderStatus;
	
	private String payType;
	
	private String startDate;
	
	private String endDate;
	
	private String orderSn;
	
	private String mobile;	
	
	private String token;
	
	private Long fatherId;
	
	private String addressType;
	
    private Long orderId;
	
	private String reciverName;
	
	private String reciverMobile;
	
	private Long reciverProvinceId;
	
	private Long reciverCityId;
	
	private Long reciverAreaId;
	
	private BigDecimal shippingFeeAmount;
	
	private String courierName;
	
	private String shippingCode;
	
	private String ste;
	
	private List<GjfOrderGoods> gjfOrderGoods;
	
	private String orderType;
	
	private String name;
	
	private String storeName;
	
	private String nickName;
	
	private String goodsName;
	
	private String tradeNo;
	
	private String jdOrderSn;
	
	private String goodSource;
	
	/**
	 * 获取全部订单信息
	 * @return
	 */
	public String findAllOrderInfo(){
		try{
			resultVo=orderInfoDubbo.findAllOrder(pageNo, pageSize, orderSn,storeName,goodsName,name,nickName, orderStatus, payType,orderType, startDate, endDate,ste,jdOrderSn,goodSource);
			pager = (Pager) resultVo.getResult();
			if("1".equals(ste)){ //导出操作
				request=ServletActionContext.getRequest();//获取请求对象;
				return "export";
			}
		}catch(Exception e){
			e.printStackTrace();;
		}
		return SUCCESS;
	}
	
	/**
	 * 查看订单明细
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findOrderInfo(){
		try{
			resultVo=orderInfoDubbo.findOrderDetailInBack(orderSn, token);
			if (null != orderInfoDubbo.findOrderGoodsByOrderId(orderId).getResult()) {
				gjfOrderGoods = (List<GjfOrderGoods>) orderInfoDubbo.findOrderGoodsByOrderId(orderId).getResult();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "views";
	}
	
	/**
	 * 跳转到添加订单发货信息页面
	 * @return
	 */
	public String goAddAddress(){
		try{
			resultVo=orderInfoDubbo.findOrderAddressDetail(orderSn, token);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "goAddAddress";
	}
	
	
	/**
	 * 获取所有省份
	 * @return
	 */
	public String findAllProvice(){
		
		try {
			resultVo=addressDubbo.findAddress(fatherId, addressType,"0");
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, StoreInfoAction.class);
		}
		return "goAddAddressProvince";	
	}
	/**
	 * 获取所有省份
	 * @return
	 */
	public String findAllCity(){
		
		try {
			resultVo=addressDubbo.findAddress(fatherId, addressType,"0");
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, StoreInfoAction.class);
		}
		return "goAddAddressCity";	
	}
	/**
	 * 获取所有省份
	 * @return
	 */
	public String findAllArea(){
		
		try {
			resultVo=addressDubbo.findAddress(fatherId, addressType,"0");
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, OrderInfoAction.class);
		}
		return "goAddAddressArea";	
	}
	
	/**
	 * 添加发货信息
	 * @return
	 */
	
	public String addAddress(){
		try{
			GjfOrderAddress address=new GjfOrderAddress();
			address.setId(addreId);
			address.setCourierName(courierName);
			address.setShippingCode(shippingCode);
			address.setShippingFeeAmount(shippingFeeAmount);
			orderInfoDubbo.addOrderAddress(address,orderId);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "new_orderAddrees";
		
	}
	
	/**
	 * 关闭订单
	 * @return
	 */
	public String closeOrder(){
		try{
			orderInfoDubbo.delOrder(tradeNo, "", token, 0);
		}catch(Exception e){
			e.printStackTrace();
		}
		return findAllOrderInfo();
	}
	
	/**
	 * 退单
	 * @return
	 */
	public String orderBack(){
		try {
			resultVo = new ResultVo(404,"系统错误");
			GjfOrderInfo orderInfo = (GjfOrderInfo)orderInfoDubbo.findOrderBySn(orderSn,token).getResult();
			if(ObjValid.isNotValid(orderInfo)){
				resultVo.setMsg("订单不存在！");
				return renderText(JSONObject.fromObject(resultVo).toString());
			}
			String orderStatus = orderInfo.getOrderStatus();
			if(ObjValid.isNotValid(orderStatus) || !"1".equals(orderStatus)){
				resultVo.setMsg("订单不存在！");
				return renderText(JSONObject.fromObject(resultVo).toString());
			}
			//支付方式  
			//支付方式（0:线上余额 1:微信支付 2:支付宝 3:银联支付 
			//4:余额+微信 5:余额+支付宝 6:余额+银联 7:待领消费金额支付 8:责任消费金额支付）
			String payType = orderInfo.getPayType();
			
			//线下支付金额
			BigDecimal offlinePayAmount = orderInfo.getOfflinePayAmount();
			
			//订单编号
			String orderSn = orderInfo.getOrderSn();
			//线上支付
			if("0".equals(payType) || "7".equals(payType)
					|| "8".equals(payType)|| "10".equals(payType)|| "9".equals(payType)){
				
				//如果积分或责任消费有用钱支付则退钱
				if(payType!="0"&&orderInfo.getOfflinePayAmount().doubleValue()!=0){
					//如果为微信支付
					resultVo = RefundUtil.refundWeixinPay(orderSn, String.valueOf(offlinePayAmount.doubleValue()));
					if(resultVo.getCode()!=200){
						//支付宝支付
						resultVo = AlipayRefundUtil.aliRefund(orderSn,offlinePayAmount.toString(), "退单");
					}
					if(resultVo.getCode()!=200){
						return renderText(JSONObject.fromObject(resultVo).toString());
					}
					if(payType!="0"&&orderInfo.getOfflinePayAmount().doubleValue()!=0&&resultVo.getCode()==200){
						resultVo =  orderInfoDubbo.refundOnlineMoney(orderInfo);
					}
				}
								
				if(orderInfo.getOfflinePayAmount().doubleValue()==0){
					resultVo =  orderInfoDubbo.refundOnlineMoney(orderInfo);
				}
				
			}else if ("1".equals(payType)) {
				//微信支付
				resultVo = RefundUtil.refundWeixinPay(orderSn, String.valueOf(offlinePayAmount.doubleValue()));
			}else if ("2".equals(payType)) {
				//支付宝支付
				resultVo = AlipayRefundUtil.aliRefund(orderSn,offlinePayAmount.toString(), "退单");
			}else if ("3".equals(payType)){
				//银联支付
				resultVo.setMsg("银联退款接口暂时无法使用！");
//				String dyPass = FastPayDemoTest.getDyPass(orderSn);
//				Map<String,String> reqMap = FastPayDemoTest.refundApply(orderSn,offlinePayAmount.toString(),dyPass);
//				resultVo.setMsg(reqMap.get("resultDesc"));
//				int code = "00".equals(reqMap.get("resultCode").toString()) 
//						? 200 
//						: 404;
//				resultVo.setCode(code);
			}
			//更新订单状态
			if(resultVo.getCode() == 200 && !("0".equals(payType) || "7".equals(payType)
					|| "8".equals(payType))){
				orderInfoDubbo.updateRefundStatus(orderInfo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		return renderText(JSONObject.fromObject(resultVo).toString());
	}
	
	/**
	 * 京东企业对账
	 * @return
	 */
	public String checkAccount(){
		try{
			if(!BeanUtil.isValid(startDate)){
				startDate="20170601 120000";
			}
			if(!BeanUtil.isValid(endDate)){
				endDate="20900601 120000";
			}
			
			CheckAccountResult checkAccountResult=JdUtil.checkAccount(startDate, endDate);
			resultVo=new ResultVo(200,"查询成功",checkAccountResult);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "chectAccount"; 
	}
	
	public String getLikeValue() {
		return likeValue;
	}

	public void setLikeValue(String likeValue) {
		this.likeValue = likeValue;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getOrderSn() {
		return orderSn;
	}

	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}	

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getFatherId() {
		return fatherId;
	}

	public void setFatherId(Long fatherId) {
		this.fatherId = fatherId;
	}

	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getReciverName() {
		return reciverName;
	}

	public void setReciverName(String reciverName) {
		this.reciverName = reciverName;
	}

	public String getReciverMobile() {
		return reciverMobile;
	}

	public void setReciverMobile(String reciverMobile) {
		this.reciverMobile = reciverMobile;
	}

	public Long getReciverProvinceId() {
		return reciverProvinceId;
	}

	public void setReciverProvinceId(Long reciverProvinceId) {
		this.reciverProvinceId = reciverProvinceId;
	}

	public Long getReciverCityId() {
		return reciverCityId;
	}

	public void setReciverCityId(Long reciverCityId) {
		this.reciverCityId = reciverCityId;
	}

	public Long getReciverAreaId() {
		return reciverAreaId;
	}

	public void setReciverAreaId(Long reciverAreaId) {
		this.reciverAreaId = reciverAreaId;
	}

	public BigDecimal getShippingFeeAmount() {
		return shippingFeeAmount;
	}

	public void setShippingFeeAmount(BigDecimal shippingFeeAmount) {
		this.shippingFeeAmount = shippingFeeAmount;
	}

	public String getCourierName() {
		return courierName;
	}

	public void setCourierName(String courierName) {
		this.courierName = courierName;
	}

	public String getShippingCode() {
		return shippingCode;
	}

	public void setShippingCode(String shippingCode) {
		this.shippingCode = shippingCode;
	}

	public Long getAddreId() {
		return addreId;
	}

	public void setAddreId(Long addreId) {
		this.addreId = addreId;
	}

	public String getSte() {
		return ste;
	}

	public void setSte(String ste) {
		this.ste = ste;
	}


	public List<GjfOrderGoods> getGjfOrderGoods() {
		return gjfOrderGoods;
	}


	public void setGjfOrderGoods(List<GjfOrderGoods> gjfOrderGoods) {
		this.gjfOrderGoods = gjfOrderGoods;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getJdOrderSn() {
		return jdOrderSn;
	}

	public void setJdOrderSn(String jdOrderSn) {
		this.jdOrderSn = jdOrderSn;
	}

	public String getGoodSource() {
		return goodSource;
	}

	public void setGoodSource(String goodSource) {
		this.goodSource = goodSource;
	}
	
	

}
