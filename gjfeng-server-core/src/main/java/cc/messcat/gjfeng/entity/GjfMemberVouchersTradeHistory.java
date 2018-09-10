package cc.messcat.gjfeng.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="gjf_member_vouchers_trade_history")
public class GjfMemberVouchersTradeHistory implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4762031444149869929L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_",length=11)
	private Long id;
	
	@Column(name="MEMBER_ID_",length=11)
	private Long memberId;
	
	@Column(name="STORE_ID_",length=11)
	private Long storeId;
	
	@Column(name="MEMBER_NAME_",length=200)
	private String memberName;
	
	@Column(name="MEMBER_MOBILE_",length=50)
	private String memberMobile;
	
	@Column(name="ADD_TIME_",length=50)
	private Date addTime;
	
	@Column(name="TRADE_MONEY_",precision=20,scale=2)
	private BigDecimal tradeMoney;
	
	@Column(name="TRADE_STATUS_",length=1)
	private String tradeStatus;
	
	@Column(name="PAY_TYPE_",length=1)
	private String payType;
	
	@Column(name="TRADE_NO_",length=200)
	private String tradeNo;
	
	@Column(name="REAL_TRADE_MONEY_",precision=20,scale=2)
	private BigDecimal realTreadeMoney;
	
	@Column(name="MERCAHNT_NAME_",length=200)
	private String merchantName;
	
	@Column(name="MERCAHNT_MOBILE_",length=50)
	private String merchantMobile;
	
	@Column(name="DISCOUNT_MONEY_",precision=20,scale=2)
	private BigDecimal discountMoney;
	
	@Column(name="MERCHANT_DIRECT_MEMBER_ID_")
	private Long merchantDirectMemberId;
	
	@Column(name="MERCHANT_DIRECT_MEMBER_NAME_",length=200)
	private String merchantDirectMemberName;
	
	@Column(name="MERCHANT_DIRECT_MEMBER_MOBILE_",length=50)
	private String merchantDirectMemberMobile;
	
	@Column(name="MERCHANT_DIRECT_MEMBER_MONEY_",precision=20,scale=2)
	private BigDecimal merchantDirectMemberMoney;
	
	@Column(name="AGENT_MEMBER_ID_")
	private Long agentMemberId;
	
	@Column(name="AGENT_MEMBER_NAME_",length=200)
	private String agentMemberName;
	
	@Column(name="AGENT_MEMBER_MOBILE_",length=50)
	private String agentMemberMobile;
	
	@Column(name="AGENT_MEMBER_DIRECT_MONEY_",precision=20,scale=2)
	private BigDecimal agentMemberDirectMoney;
	
	@Column(name="MERCHANT_TYPE_",length=1)
	private String mechantType;
	
	@Column(name="MERCAHNT_DIRECT_TYPE_",length=1)
	private String merchantDirectType;
	
	@Column(name="AGENT_MEMBER_TYPE_",length=1)
	private String agentMemberType;
	
	@Column(name="REAL_PAY_MONEY_",precision=20,scale=2)
	private BigDecimal realPayMoney;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberMobile() {
		return memberMobile;
	}

	public void setMemberMobile(String memberMobile) {
		this.memberMobile = memberMobile;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public BigDecimal getTradeMoney() {
		return tradeMoney;
	}

	public void setTradeMoney(BigDecimal tradeMoney) {
		this.tradeMoney = tradeMoney;
	}

	public String getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public BigDecimal getRealTreadeMoney() {
		return realTreadeMoney;
	}

	public void setRealTreadeMoney(BigDecimal realTreadeMoney) {
		this.realTreadeMoney = realTreadeMoney;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getMerchantMobile() {
		return merchantMobile;
	}

	public void setMerchantMobile(String merchantMobile) {
		this.merchantMobile = merchantMobile;
	}

	public BigDecimal getDiscountMoney() {
		return discountMoney;
	}

	public void setDiscountMoney(BigDecimal discountMoney) {
		this.discountMoney = discountMoney;
	}

	public Long getMerchantDirectMemberId() {
		return merchantDirectMemberId;
	}

	public void setMerchantDirectMemberId(Long merchantDirectMemberId) {
		this.merchantDirectMemberId = merchantDirectMemberId;
	}

	public String getMerchantDirectMemberName() {
		return merchantDirectMemberName;
	}

	public void setMerchantDirectMemberName(String merchantDirectMemberName) {
		this.merchantDirectMemberName = merchantDirectMemberName;
	}

	public String getMerchantDirectMemberMobile() {
		return merchantDirectMemberMobile;
	}

	public void setMerchantDirectMemberMobile(String merchantDirectMemberMobile) {
		this.merchantDirectMemberMobile = merchantDirectMemberMobile;
	}

	public BigDecimal getMerchantDirectMemberMoney() {
		return merchantDirectMemberMoney;
	}

	public void setMerchantDirectMemberMoney(BigDecimal merchantDirectMemberMoney) {
		this.merchantDirectMemberMoney = merchantDirectMemberMoney;
	}

	public Long getAgentMemberId() {
		return agentMemberId;
	}

	public void setAgentMemberId(Long agentMemberId) {
		this.agentMemberId = agentMemberId;
	}

	public String getAgentMemberName() {
		return agentMemberName;
	}

	public void setAgentMemberName(String agentMemberName) {
		this.agentMemberName = agentMemberName;
	}

	public String getAgentMemberMobile() {
		return agentMemberMobile;
	}

	public void setAgentMemberMobile(String agentMemberMobile) {
		this.agentMemberMobile = agentMemberMobile;
	}

	public BigDecimal getAgentMemberDirectMoney() {
		return agentMemberDirectMoney;
	}

	public void setAgentMemberDirectMoney(BigDecimal agentMemberDirectMoney) {
		this.agentMemberDirectMoney = agentMemberDirectMoney;
	}

	public String getMechantType() {
		return mechantType;
	}

	public void setMechantType(String mechantType) {
		this.mechantType = mechantType;
	}

	public String getMerchantDirectType() {
		return merchantDirectType;
	}

	public void setMerchantDirectType(String merchantDirectType) {
		this.merchantDirectType = merchantDirectType;
	}

	public String getAgentMemberType() {
		return agentMemberType;
	}

	public void setAgentMemberType(String agentMemberType) {
		this.agentMemberType = agentMemberType;
	}

	public BigDecimal getRealPayMoney() {
		return realPayMoney;
	}

	public void setRealPayMoney(BigDecimal realPayMoney) {
		this.realPayMoney = realPayMoney;
	}

	
	

}
