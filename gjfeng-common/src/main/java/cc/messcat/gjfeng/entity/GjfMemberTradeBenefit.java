package cc.messcat.gjfeng.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * GjfMemberBenefitRecord entity. @author MyEclipse Persistence Tools
 */
public class GjfMemberTradeBenefit implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields
	private Long id;
	
	private String tradeNo;
	
	private GjfMemberInfo memberId;
	
	private GjfStoreInfo storeId;
	
	private BigDecimal tradeMoney;
	
	private BigDecimal benefitMoney;
	
	private BigDecimal memberDividendsNum;
	
	private BigDecimal merchantsDividendsNum;
	
	private BigDecimal directMemberMoney;
	
	private BigDecimal directMerchantsMoney;
	
	private GjfMemberInfo directMember;
	
	private GjfMemberInfo directMerchants;
	
	private Date addTime;
	
	private String payType;
	
	private String tradeStatus;
	
	private String token;
	
	private String confirmStatus;
	
	private String changeAcivityStatus;
	
	private BigDecimal consumptionMoney;
	
	private String merchantGrade;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public GjfMemberInfo getMemberId() {
		return memberId;
	}

	public void setMemberId(GjfMemberInfo memberId) {
		this.memberId = memberId;
	}

	public GjfStoreInfo getStoreId() {
		return storeId;
	}

	public void setStoreId(GjfStoreInfo storeId) {
		this.storeId = storeId;
	}

	public BigDecimal getBenefitMoney() {
		return benefitMoney;
	}

	public void setBenefitMoney(BigDecimal benefitMoney) {
		this.benefitMoney = benefitMoney;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public BigDecimal getTradeMoney() {
		return tradeMoney;
	}

	public void setTradeMoney(BigDecimal tradeMoney) {
		this.tradeMoney = tradeMoney;
	}

	public BigDecimal getMemberDividendsNum() {
		return memberDividendsNum;
	}

	public void setMemberDividendsNum(BigDecimal memberDividendsNum) {
		this.memberDividendsNum = memberDividendsNum;
	}

	public BigDecimal getMerchantsDividendsNum() {
		return merchantsDividendsNum;
	}

	public void setMerchantsDividendsNum(BigDecimal merchantsDividendsNum) {
		this.merchantsDividendsNum = merchantsDividendsNum;
	}

	public BigDecimal getDirectMemberMoney() {
		return directMemberMoney;
	}

	public void setDirectMemberMoney(BigDecimal directMemberMoney) {
		this.directMemberMoney = directMemberMoney;
	}

	public BigDecimal getDirectMerchantsMoney() {
		return directMerchantsMoney;
	}

	public void setDirectMerchantsMoney(BigDecimal directMerchantsMoney) {
		this.directMerchantsMoney = directMerchantsMoney;
	}

	public GjfMemberInfo getDirectMember() {
		return directMember;
	}

	public void setDirectMember(GjfMemberInfo directMember) {
		this.directMember = directMember;
	}

	public GjfMemberInfo getDirectMerchants() {
		return directMerchants;
	}

	public void setDirectMerchants(GjfMemberInfo directMerchants) {
		this.directMerchants = directMerchants;
	}

	public String getConfirmStatus() {
		return confirmStatus;
	}

	public void setConfirmStatus(String confirmStatus) {
		this.confirmStatus = confirmStatus;
	}

	public String getChangeAcivityStatus() {
		return changeAcivityStatus;
	}

	public void setChangeAcivityStatus(String changeAcivityStatus) {
		this.changeAcivityStatus = changeAcivityStatus;
	}

	public BigDecimal getConsumptionMoney() {
		return consumptionMoney;
	}

	public void setConsumptionMoney(BigDecimal consumptionMoney) {
		this.consumptionMoney = consumptionMoney;
	}

	public String getMerchantGrade() {
		return merchantGrade;
	}

	public void setMerchantGrade(String merchantGrade) {
		this.merchantGrade = merchantGrade;
	}
	
	

}