package cc.messcat.gjfeng.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * GjfOrdersInfo entity. @author MyEclipse Persistence Tools
 */
public class GjfOrderInfo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields
	private Long id;
	
	private String orderSn;
	
	private String paySn;
	
	private String pickupCode;
	
	private GjfMemberInfo memberId;
	
	private GjfStoreInfo storeId;
	
	/*private GjfMemberAddress memberAddressId;*/
	
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
	
	private String isDel;

	private String token;
	
	private BigDecimal orderPostage;
	
	private String suoceGood;
	
	private String jdOrderSn;
	
	private BigDecimal benerfitMoney;	
	
	private BigDecimal taxMoney;
	
	private BigDecimal directMemberMoney;
	
	private Long directMemberId;
	
	private BigDecimal salseAmount;

	private String isWholesalse;
	
	private String logist;
	
	private String commisionType;
	
	private BigDecimal jdCostPrice;
	
	private String isUpgradePro;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderSn() {
		return orderSn;
	}

	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}

	public String getPaySn() {
		return paySn;
	}

	public void setPaySn(String paySn) {
		this.paySn = paySn;
	}

	public String getPickupCode() {
		return pickupCode;
	}

	public void setPickupCode(String pickupCode) {
		this.pickupCode = pickupCode;
	}

	public GjfMemberInfo getMemberId() {
		return memberId;
	}

	public void setMemberId(GjfMemberInfo memberId) {
		this.memberId = memberId;
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

	public String getIsDel() {
		return isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getPayType() {
		return payType;
	}

	public GjfStoreInfo getStoreId() {
		return storeId;
	}

	public void setStoreId(GjfStoreInfo storeId) {
		this.storeId = storeId;
	}

	public BigDecimal getOrderPostage() {
		return orderPostage;
	}

	public void setOrderPostage(BigDecimal orderPostage) {
		this.orderPostage = orderPostage;
	}

	public String getSuoceGood() {
		return suoceGood;
	}

	public void setSuoceGood(String suoceGood) {
		this.suoceGood = suoceGood;
	}

	public String getJdOrderSn() {
		return jdOrderSn;
	}

	public void setJdOrderSn(String jdOrderSn) {
		this.jdOrderSn = jdOrderSn;
	}

	public BigDecimal getBenerfitMoney() {
		return benerfitMoney;
	}

	public void setBenerfitMoney(BigDecimal benerfitMoney) {
		this.benerfitMoney = benerfitMoney;
	}

	public BigDecimal getTaxMoney() {
		return taxMoney;
	}

	public void setTaxMoney(BigDecimal taxMoney) {
		this.taxMoney = taxMoney;
	}

	public BigDecimal getDirectMemberMoney() {
		return directMemberMoney;
	}

	public void setDirectMemberMoney(BigDecimal directMemberMoney) {
		this.directMemberMoney = directMemberMoney;
	}

	public Long getDirectMemberId() {
		return directMemberId;
	}

	public void setDirectMemberId(Long directMemberId) {
		this.directMemberId = directMemberId;
	}

	public BigDecimal getSalseAmount() {
		return salseAmount;
	}

	public void setSalseAmount(BigDecimal salseAmount) {
		this.salseAmount = salseAmount;
	}

	public String getIsWholesalse() {
		return isWholesalse;
	}

	public void setIsWholesalse(String isWholesalse) {
		this.isWholesalse = isWholesalse;
	}

	public String getLogist() {
		return logist;
	}

	public void setLogist(String logist) {
		this.logist = logist;
	}

	public String getCommisionType() {
		return commisionType;
	}

	public void setCommisionType(String commisionType) {
		this.commisionType = commisionType;
	}

	public BigDecimal getJdCostPrice() {
		return jdCostPrice;
	}

	public void setJdCostPrice(BigDecimal jdCostPrice) {
		this.jdCostPrice = jdCostPrice;
	}

	public String getIsUpgradePro() {
		return isUpgradePro;
	}

	public void setIsUpgradePro(String isUpgradePro) {
		this.isUpgradePro = isUpgradePro;
	}

	

}