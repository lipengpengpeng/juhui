package cc.messcat.gjfeng.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * GjfOrdersInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "gjf_order_info")
public class GjfOrderInfo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_", length = 10)
	private Long id;
	
	@Column(name = "ORDER_SN_", length = 33)
	private String orderSn;
	
	@Column(name = "PAY_SN_", length = 33)
	private String paySn;
	
	@Column(name = "PICKUP_CODE_", length = 10)
	private String pickupCode;
	
	@ManyToOne
	@JoinColumn(name="MEMBER_ID_")
	private GjfMemberInfo memberId;
	
	@ManyToOne
	@JoinColumn(name="STORE_ID_")
	private GjfStoreInfo storeId;
	
	/*@ManyToOne
	@JoinColumn(name="MEMBER_ADDRESS_ID")
	private GjfMemberAddress memberAddressId;*/
	
	@Column(name = "GOODS_TOTAL_AMOUNT_",precision=10,scale=2)
	private BigDecimal goodsTotalAmount;
	
	@Column(name = "ORDER_TOTAL_AMOUNT_",precision=10,scale=2)
	private BigDecimal orderTotalAmount;
	
	@Column(name = "REAL_PAY_AMOUNT_",precision=10,scale=2)
	private BigDecimal realPayAmount;
	
	@Column(name = "ONLINE_PAY_AMOUNT_",precision=10,scale=2)
	private BigDecimal onlinePayAmount;
	
	@Column(name = "OFFLINE_PAY_AMOUNT_",precision=10,scale=2)
	private BigDecimal offlinePayAmount;
	
	@Column(name = "STORE_REC_AMOUNT_",precision=10,scale=2)
	private BigDecimal storeRecAmount;
	
	@Column(name = "STORE_BENEFIT_AMOUNT_",precision=10,scale=2)
	private BigDecimal storeBenefitAmount;
	
	@Column(name = "REFUND_AMOUNT_",precision=10,scale=2)
	private BigDecimal refundAmount;
	
	@Column(name = "COUPONS_ID_", length = 10)
	private Long couponsId;
	
	@Column(name = "PAY_TYPE_")
	private String payType;
	
	@Column(name = "ADD_TIME_")
	private Date addTime;
	
	@Column(name = "PAY_TIME_")
	private Date payTime;
	
	@Column(name = "DELIVERY_TIME_")
	private Date deliveryTime;
	
	@Column(name = "FINISHED_TIME_")
	private Date finishedTime;
	
	@Column(name = "OVERDUE_TIME_")
	private Date overdueTime;
	
	@Column(name = "REFUND_TIME_")
	private Date refundTime;
	
	@Column(name = "CANCEL_TIME_")
	private Date cancelTime;
	
	@Column(name = "REMARK_", length = 300)
	private String remark;
	
	@Column(name = "CANCEL_REASON_", length = 300)
	private String cancelReason;
	
	@Column(name = "ORDER_TYPE_", length = 1)
	private String orderType;
	
	@Column(name = "EVALUATION_STATUS_", length = 1)
	private String evaluationStatus;
	
	@Column(name = "ORDER_STATUS_", length = 1)
	private String orderStatus;
	
	@Column(name = "REFUND_STATUS_", length = 1)
	private String refundStatus;
	
	@Column(name = "IS_DEL_", length = 1)
	private String isDel;

	@Column(name = "TOKEN_", length = 200)
	private String token;
	
	@Column(name = "ORDER_POSTAGE_", precision=20,scale=2)
	private BigDecimal orderPostage;
	
	@Column(name = "SUORCE_ORDER_", length = 1)
	private String suoceGood;
	
	@Column(name = "JD_ORDERSN_", length = 50)
	private String jdOrderSn;
	
	@Column(name = "BENERFIT_MONEY_", precision=20, scale=2)
	private BigDecimal benerfitMoney;
	
	@Column(name = "TAX_MONEY_", precision=20, scale=2)
	private BigDecimal taxMoney;
	
	@Column(name = "DIRECT_MEMBER_MONEY_", precision=20, scale=2)
	private BigDecimal directMemberMoney;
	
	@Column(name = "DIRECT_MEMBER_ID_", length = 11)
	private Long directMemberId;
	
	@Column(name = "SALSE_AMOUNT_", precision=20, scale=2)
	private BigDecimal salseAmount;

	@Column(name = "IS_WHOLESALE_", length = 1)
	private String isWholesalse;
	
	@Column(name = "AGENT_DEIECT_MONEY_", precision=20, scale=2)
	private BigDecimal agentDeirectMoney;
	
	@Column(name = "LOGIST_", length = 1)
	private String logist;
	
	@Column(name = "COMMISSION_TYPE_", length = 1)
	private String commisionType;
	
	@Column(name = "JD_COST_PRICE_", precision=20, scale=2)
	private BigDecimal jdCostPrice;
	
	@Column(name="IS_UPGRADE_PRO_",length = 1)
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

	public BigDecimal getAgentDeirectMoney() {
		return agentDeirectMoney;
	}

	public void setAgentDeirectMoney(BigDecimal agentDeirectMoney) {
		this.agentDeirectMoney = agentDeirectMoney;
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

	
	
	/*public GjfMemberAddress getMemberAddressId() {
		return memberAddressId;
	}

	public void setMemberAddressId(GjfMemberAddress memberAddressId) {
		this.memberAddressId = memberAddressId;
	}*/


}