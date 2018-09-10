package cc.messcat.gjfeng.dubbo;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import cc.messcat.gjfeng.common.vo.app.OrderAddVo;
import cc.messcat.gjfeng.common.vo.app.ResultVo;
import cc.messcat.gjfeng.dubbo.core.GjfOrderInfoDubbo;
import cc.messcat.gjfeng.entity.GjfOrderAddress;
import cc.messcat.gjfeng.entity.GjfOrderInfo;
import cc.messcat.gjfeng.service.order.GjfOrderInfoService;

public class GjfOrderInfoDubboImpl implements GjfOrderInfoDubbo {

	@Autowired
	@Qualifier("gjfOrderInfoService")
	private GjfOrderInfoService gjfOrderInfoService;
	
	/*
	 * 跳到到下单
	 * @see cc.messcat.gjfeng.dubbo.core.GjfOrderInfoDubbo#toAddOrder(java.util.List)
	 */
	public ResultVo toAddOrder(List<OrderAddVo> orderAddVos,String account){
		return gjfOrderInfoService.toAddOrder(orderAddVos,account);
	}

	/*
	 * 用户下单
	 * 
	 * @see
	 * cc.messcat.gjfeng.dubbo.core.GjfOrderInfoDubbo#addOrder(java.lang.String,
	 * java.util.List, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.Long, java.lang.Long)
	 */
	@Override
	public ResultVo addOrder(String account, List<OrderAddVo> orderAddVos, String orderType, String payType, String remark,
		Long couponsId, Long orderAddressId,String  logist,String commissionType,String orderSn,String customerSn,BigDecimal postage) {
		return gjfOrderInfoService.addOrder(account, orderAddVos, orderType, payType, remark, couponsId, orderAddressId,logist,commissionType,orderSn,customerSn,postage);
	}

	/*
	 * 修改订单的状态
	 * 
	 * @see
	 * cc.messcat.gjfeng.dubbo.core.GjfOrderInfoDubbo#updateOrderStatus(java.
	 * lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public ResultVo updateOrderStatus(String orderSn, String payOrderSn, String orderStatus, String account,String token, String client) {
		return gjfOrderInfoService.updateOrderStatus(orderSn,payOrderSn, orderStatus,account, token, client);
	}

	/*
	 * 删除订单
	 * 
	 * @see
	 * cc.messcat.gjfeng.dubbo.core.GjfOrderInfoDubbo#delOrder(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public ResultVo delOrder(String orderSn,String account, String token,int isNeedMember) {
		return gjfOrderInfoService.delOrder(orderSn, account, token, isNeedMember);
	}

	/*
	 * 查询用户订单详情
	 * 
	 * @see
	 * cc.messcat.gjfeng.dubbo.core.GjfOrderInfoDubbo#findOrderDetail(java.lang.
	 * String, java.lang.String)
	 */
	@Override
	public ResultVo findOrderDetail(String orderSn, String account) {
		return gjfOrderInfoService.findOrderDetail(orderSn, account);
	}

	/*
	 * 根据订单号和token查询订单
	 * 
	 * @see
	 * cc.messcat.gjfeng.dubbo.core.GjfOrderInfoDubbo#findOrderBySn(java.lang.
	 * String, java.lang.String)
	 */
	@Override
	public ResultVo findOrderBySn(String orderSn, String token) {
		return gjfOrderInfoService.findOrderBySn(orderSn, token);
	}

	/*
	 * 根据账户和订单状态查询订单
	 * 
	 * @see cc.messcat.gjfeng.dubbo.core.GjfOrderInfoDubbo#findMyOrder(int, int,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public ResultVo findMyOrder(int pageNo, int pageSize, String account, String status) {
		return gjfOrderInfoService.findMyOrder(pageNo, pageSize, account, status);
	}

	/*
	 * 分页查询用户订单
	 * 
	 * @see cc.messcat.gjfeng.dubbo.core.GjfOrderInfoDubbo#findAllOrder(int,
	 * int, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public ResultVo findAllOrder(int pageNo,int pageSize,String orderSn,String storeName,String goodsName,
			String name,String nickName,String orderStatus,String payType,String orderType ,String startDate,String endDate,String ste,String jdOrderSn,String goodSource) {
		return gjfOrderInfoService.findAllOrder(pageNo, pageSize, orderSn, storeName, goodsName, name, nickName, orderStatus, payType, orderType, startDate, endDate, ste,jdOrderSn,goodSource);
	}

	/*
	 * 根据用户Id查询用户订单
	 * @see cc.messcat.gjfeng.dubbo.core.GjfOrderInfoDubbo#findOrderByMemberId(int, int, java.lang.Long)
	 */
	@Override
	public ResultVo findOrderByMemberId(int pageNo, int pageSize, Long memberId,String startTime,String endTime) {
		return gjfOrderInfoService.findOrderByMemberId(pageNo,pageSize,memberId,startTime,endTime);
	}


	@Override
	public ResultVo addOrderAddress(GjfOrderAddress address,Long orderId) {
		return gjfOrderInfoService.addOrderAddress(address, orderId);
	}

	@Override
	public ResultVo findOrderDetailInBack(String orderSn, String token) {
		return gjfOrderInfoService.findOrderDetailInBack(orderSn, token);
	}
	
	
	/*
	 * 根据用户Id查找分销商品
	 * @see cc.messcat.gjfeng.dubbo.core.GjfOrderInfoDubbo#findLowersOrderGoodsById(int, int, java.lang.Long)
	 */
	@Override
	public ResultVo findLowersOrderGoodsById(int pageNo, int pageSize, Long id) {
		return gjfOrderInfoService.findLowersOrderGoodsById(pageNo,pageSize,id);
	}

	@Override
	public ResultVo findOrderAddressDetail(String orderSn, String token) {
		// TODO Auto-generated method stub
		return gjfOrderInfoService.findOrderAddressDetail(orderSn, token);
	}

	/*
	 * 根据订单Id查找订单的商品
	 * @see cc.messcat.gjfeng.dubbo.core.GjfOrderInfoDubbo#findOrderGoodsByOrderId(java.lang.Long)
	 */
	@Override
	public ResultVo findOrderGoodsByOrderId(Long id) {
		return null;
	}

	@Override
	public ResultVo findOrderByCondition(int pageNo, int pageSize, Long id,String token, String startTime, String endTime) {
		return gjfOrderInfoService.findOrderByCondition(pageNo, pageSize, id, token,startTime, endTime);
	}
	
	
	@Override
	public ResultVo updateOrderPayMoney(String orderSn, Double onlinePay, Double offlinePay,String newOrdersn) {
		return gjfOrderInfoService.updateOrderPayMoney( orderSn, onlinePay, offlinePay,newOrdersn);
	}
	
	@Override
	public ResultVo findCountOrderByCondition(Long id, String token, String startTime, String endTime) {
		return gjfOrderInfoService.findCountOrderByCondition(id, token, startTime, endTime);
	}

	@Override
	public ResultVo addO2oOrderInfo(String account, Double payMoney, Long storeId, String orderType, String payType,
			String remark, Long couponsId) {
		return gjfOrderInfoService.addO2oOrderInfo(account,payMoney,storeId,orderType,payType,remark,couponsId);
	}

	@Override
	public ResultVo findO2oOrderInfo(String status,Long storeId, String beginTime, String endTime,Integer pageNo,Integer pageSize) {
		
		return gjfOrderInfoService.findO2oOrderInfo(status,storeId,beginTime,endTime,pageNo,pageSize);
	}
	
	@Override
	public ResultVo refundOnlineMoney(GjfOrderInfo orderInfo){
		return gjfOrderInfoService.refundOnlineMoney(orderInfo);
	}
	
	@Override
	public ResultVo updateRefundStatus(GjfOrderInfo orderInfo){
		return gjfOrderInfoService.updateRefundStatus(orderInfo);
	}

	/**
	 * 天猫订单处理
	 */
	@Override
	public ResultVo addTianmaoOrder(String item_title, String pay_price, String commission, String trade_id, String uid,
			String status,String apitype) {

		return gjfOrderInfoService.addTianmaoOrder(item_title, pay_price, commission, trade_id, uid, status,apitype);
	}

	/**
	 * 天猫订单结算积分
	 */
	@Override
	public ResultVo updateOrderBenefit() {
		
		return gjfOrderInfoService.updateOrderBenefit();
	}

	/**
	 * 根据地址查询商品邮费
	 * @param goodIds
	 * @param goodNums
	 * @param addessId
	 * @return
	 */
	@Override
	public ResultVo findOrderPos(String goodIds, String goodNums, String addessId) {
		
		return gjfOrderInfoService.findOrderPos(goodIds, goodNums, addessId);
	}
}
