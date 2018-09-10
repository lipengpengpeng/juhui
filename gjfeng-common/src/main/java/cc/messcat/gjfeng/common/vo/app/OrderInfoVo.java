package cc.messcat.gjfeng.common.vo.app;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import cc.messcat.gjfeng.entity.GjfMemberInfo;
import cc.messcat.gjfeng.entity.GjfOrderAddress;
import cc.messcat.gjfeng.entity.GjfStoreInfo;

public class OrderInfoVo implements Serializable {

	private static final long serialVersionUID = 1L;
	// Fields
	
	private String orderSn;
	
	private String pickupCode;
	
	private GjfMemberInfo memberId;
	
	private GjfOrderAddress gjfOrderAddress;
	
	private BigDecimal goodsTotalAmount;
	
	private BigDecimal orderTotalAmount;
	
	private BigDecimal realPayAmount;
	
	private BigDecimal onlinePayAmount;
	
	private BigDecimal offlinePayAmount;
	
    private BigDecimal storeRecAmount;
	
	private BigDecimal storeBenefitAmount;
	
	private BigDecimal refundAmount;
	
	private Long couponsId;
	
	private String payType;
	
	private Date addTime;
	
	private Date payTime;
	
	private Date deliveryTime;
	
	private Date finishedTime;
	
	private Date overdueTime;
	
	private Date refundTime;
	
	private Date cancelTime;
	
	private String remark;
	
	private String cancelReason;
	
	private String orderType;
	
	private String evaluationStatus;
	
	private String orderStatus;
	
	private String refundStatus;

	private List<OrderGoodsVo> goodsVos;
	
	private GjfStoreInfo storeId;

	public String getOrderSn() {
		return orderSn;
	}

	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}

	public String getPickupCode() {
		return pickupCode;
	}

	public void setPickupCode(String pickupCode) {
		this.pickupCode = pickupCode;
	}

	public BigDecimal getGoodsTotalAmount() {
		return goodsTotalAmount;
	}

	public void setGoodsTotalAmount(BigDecimal goodsTotalAmount) {
		this.goodsTotalAmount = goodsTotalAmount;
	}

	public BigDecimal getOrderTotalAmount() {
		return orderTotalAmount;
	}

	public void setOrderTotalAmount(BigDecimal orderTotalAmount) {
		this.orderTotalAmount = orderTotalAmount;
	}

	public BigDecimal getRealPayAmount() {
		return realPayAmount;
	}

	public void setRealPayAmount(BigDecimal realPayAmount) {
		this.realPayAmount = realPayAmount;
	}

	public BigDecimal getOnlinePayAmount() {
		return onlinePayAmount;
	}

	public void setOnlinePayAmount(BigDecimal onlinePayAmount) {
		this.onlinePayAmount = onlinePayAmount;
	}

	public BigDecimal getOfflinePayAmount() {
		return offlinePayAmount;
	}

	public void setOfflinePayAmount(BigDecimal offlinePayAmount) {
		this.offlinePayAmount = offlinePayAmount;
	}

	public BigDecimal getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(BigDecimal refundAmount) {
		this.refundAmount = refundAmount;
	}

	public Long getCouponsId() {
		return couponsId;
	}

	public void setCouponsId(Long couponsId) {
		this.couponsId = couponsId;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public Date getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public Date getFinishedTime() {
		return finishedTime;
	}

	public void setFinishedTime(Date finishedTime) {
		this.finishedTime = finishedTime;
	}

	public Date getOverdueTime() {
		return overdueTime;
	}

	public void setOverdueTime(Date overdueTime) {
		this.overdueTime = overdueTime;
	}

	public Date getRefundTime() {
		return refundTime;
	}

	public void setRefundTime(Date refundTime) {
		this.refundTime = refundTime;
	}

	public Date getCancelTime() {
		return cancelTime;
	}

	public void setCancelTime(Date cancelTime) {
		this.cancelTime = cancelTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getEvaluationStatus() {
		return evaluationStatus;
	}

	public void setEvaluationStatus(String evaluationStatus) {
		this.evaluationStatus = evaluationStatus;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getRefundStatus() {
		return refundStatus;
	}

	public void setRefundStatus(String refundStatus) {
		this.refundStatus = refundStatus;
	}

	public List<OrderGoodsVo> getGoodsVos() {
		return goodsVos;
	}

	public void setGoodsVos(List<OrderGoodsVo> goodsVos) {
		this.goodsVos = goodsVos;
	}

	public BigDecimal getStoreRecAmount() {
		return storeRecAmount;
	}

	public void setStoreRecAmount(BigDecimal storeRecAmount) {
		this.storeRecAmount = storeRecAmount;
	}

	public BigDecimal getStoreBenefitAmount() {
		return storeBenefitAmount;
	}

	public void setStoreBenefitAmount(BigDecimal storeBenefitAmount) {
		this.storeBenefitAmount = storeBenefitAmount;
	}

	public GjfMemberInfo getMemberId() {
		return memberId;
	}

	public void setMemberId(GjfMemberInfo memberId) {
		this.memberId = memberId;
	}

	public GjfOrderAddress getGjfOrderAddress() {
		return gjfOrderAddress;
	}

	public void setGjfOrderAddress(GjfOrderAddress gjfOrderAddress) {
		this.gjfOrderAddress = gjfOrderAddress;
	}

	public GjfStoreInfo getStoreId() {
		return storeId;
	}

	public void setStoreId(GjfStoreInfo storeId) {
		this.storeId = storeId;
	}

}