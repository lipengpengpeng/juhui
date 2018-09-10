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
 * GjfMemberBenefitRecord entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "gjf_member_trade_benefit")
public class GjfMemberTradeBenefit implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_", length = 10)
	private Long id;
	
	@Column(name = "TRADE_NO_", length=50)
	private String tradeNo;
	
	@ManyToOne
	@JoinColumn(name="MEMBER_ID_")
	private GjfMemberInfo memberId;
	
	@ManyToOne
	@JoinColumn(name="STORE_ID_")
	private GjfStoreInfo storeId;
	
	@Column(name = "TRADE_MONEY_", precision=10, scale=2)
	private BigDecimal tradeMoney;
	
	@Column(name = "BENEFIT_MONEY_", precision=10, scale=2)
	private BigDecimal benefitMoney;
	
	@Column(name = "MEMBER_DIVIDENDS_NUM_", precision=10, scale=2)
	private BigDecimal memberDividendsNum;
	
	@Column(name = "MERCHANTS_DIVIDENDS_NUM_", precision=10, scale=2)
	private BigDecimal merchantsDividendsNum;
	
	@Column(name = "DIRECT_MEMBER_MONEY_", precision=10, scale=2)
	private BigDecimal directMemberMoney;
	
	@Column(name = "DIRECT_MERCHANTS_MONEY_", precision=10, scale=2)
	private BigDecimal directMerchantsMoney;
	
	@ManyToOne
	@JoinColumn(name="DIRECT_MEMBER_")
	private GjfMemberInfo directMember;
	
	@ManyToOne
	@JoinColumn(name="DIRECT_MERCHANTS_")
	private GjfMemberInfo directMerchants;
	
	@Column(name = "ADD_TIME_")
	private Date addTime;
	
	@Column(name = "PAY_TYPE_", length=1)
	private String payType;
	
	@Column(name = "TRADE_STATUS_", length=1)
	private String tradeStatus;
	
	@Column(name = "TOKEN_", length=200)
	private String token;
	
	@Column(name = "CHANGE_ACIVITY_STATUS_", length=1)
	private String changeAcivityStatus;
	
	@Column(name = "CONSUMPTION_MONEY_",precision=10, scale=2)
	private BigDecimal consumptionMoney;

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

	
}