package cc.messcat.gjfeng.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


public class GjfMemberVouchersTradeHistory implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4762031444149869929L;

	private Long id;
	
	private Long memberId;
	
	private Long storeId;
	
	private String memberName;
	
	private String memberMobile;
	
	private Date addTime;
	
	private BigDecimal tradeMoney;
	
	private String tradeStatus;
	
	private String payType;
	
	private String tradeNo;
	
	private BigDecimal realTreadeMoney;
	
	private String merchantName;
	
	private String merchantMobile;
	
	private BigDecimal discountMoney;
	
	private Long merchantDirectMemberId;
	
	private String merchantDirectMemberName;
	
	private String merchantDirectMemberMobile;
	
	private BigDecimal merchantDirectMemberMoney;
	
	private Long agentMemberId;
	
	private String agentMemberName;
	
	private String agentMemberMobile;
	
	private BigDecimal agentMemberDirectMoney;
	
	private String mechantType;
	
	private String merchantDirectType;
	
	private String agentMemberType;
	
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
