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
 * GjfMemberTrade entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "gjf_member_trade")
public class GjfMemberTrade implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_", length = 10)
	private Long id;
	
	@Column(name = "TRADE_NO_", length = 20)
	private String tradeNo;
	
	@ManyToOne
	@JoinColumn(name="MEMBER_ID_")
	private GjfMemberInfo memberId;
	
	@ManyToOne
	@JoinColumn(name="BANK_ID_")
	private GjfMemberBank bankId;
	
	@Column(name = "APPLY_MONEY_", precision=10, scale=2)
	private BigDecimal applyMoney;
	
	@Column(name = "TRADE_MONEY_", precision=10, scale=2)
	private BigDecimal tradeMoney;
	
	@Column(name = "TAX_MONEY_", precision=10, scale=2)
	private BigDecimal taxMoney;
	
	@Column(name = "INSURANCE_MONEY_", precision=10, scale=2)
	private BigDecimal insuranceMoney;
	
	@Column(name = "ADD_TIME_")
	private Date addTime;
	
	@Column(name = "DEAL_TIME_")
	private Date dealTime;
	
	@Column(name = "TRADE_TIME_")
	private Date tradeTime;
	
	@Column(name = "TRADE_TYPE_", length = 1)
	private String tradeType;
	
	@Column(name = "TRADE_STATUS_", length = 1)
	private String tradeStatus;
	
	@Column(name = "PAY_TYPE_", length = 1)
	private String payType;
	
	@Column(name = "CHECK_USER_", length = 20)
	private String checkUser;
	
	
	@Column(name = "TOKEN_", length = 200)
	private String token;
	
	@ManyToOne
	@JoinColumn(name="TO_MEMBER_ID")
	private GjfMemberInfo toMemberId;
	
	@Column(name = "CON_GROWTH_", precision=10, scale=2)
	private BigDecimal conGrowth;
	
	@Column(name = "SALES_GROWTH_", precision=10, scale=2)
	private BigDecimal salesGrowth;
	
	@Column(name = "SHOUXIN_TYPE_",length=1)
	private String shouxinType;
	
	@Column(name = "SHOUXIN_CREDENTIALS_IMG_",length=300)
    private String shouxinCredntialsImg; 

	public GjfMemberTrade() {
		super();
	}

	public GjfMemberTrade(Long id, String tradeNo, GjfMemberInfo memberId, GjfMemberBank bankId, BigDecimal applyMoney,
		BigDecimal tradeMoney, BigDecimal taxMoney, BigDecimal insuranceMoney, Date addTime, Date dealTime, Date tradeTime,
		String tradeType, String tradeStatus, String token, GjfMemberInfo toMemberId) {
		super();
		this.id = id;
		this.tradeNo = tradeNo;
		this.memberId = memberId;
		this.bankId = bankId;
		this.applyMoney = applyMoney;
		this.tradeMoney = tradeMoney;
		this.taxMoney = taxMoney;
		this.insuranceMoney = insuranceMoney;
		this.addTime = addTime;
		this.dealTime = dealTime;
		this.tradeTime = tradeTime;
		this.tradeType = tradeType;
		this.tradeStatus = tradeStatus;
		this.token = token;
		this.toMemberId = toMemberId;
	}




	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public GjfMemberInfo getMemberId() {
		return memberId;
	}

	public void setMemberId(GjfMemberInfo memberId) {
		this.memberId = memberId;
	}

	public GjfMemberBank getBankId() {
		return bankId;
	}

	public void setBankId(GjfMemberBank bankId) {
		this.bankId = bankId;
	}

	public BigDecimal getTradeMoney() {
		return tradeMoney;
	}

	public void setTradeMoney(BigDecimal tradeMoney) {
		this.tradeMoney = tradeMoney;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Date getDealTime() {
		return dealTime;
	}

	public void setDealTime(Date dealTime) {
		this.dealTime = dealTime;
	}

	public Date getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(Date tradeTime) {
		this.tradeTime = tradeTime;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
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


	public GjfMemberInfo getToMemberId() {
		return toMemberId;
	}


	public void setToMemberId(GjfMemberInfo toMemberId) {
		this.toMemberId = toMemberId;
	}


	public BigDecimal getTaxMoney() {
		return taxMoney;
	}


	public void setTaxMoney(BigDecimal taxMoney) {
		this.taxMoney = taxMoney;
	}


	public BigDecimal getInsuranceMoney() {
		return insuranceMoney;
	}


	public void setInsuranceMoney(BigDecimal insuranceMoney) {
		this.insuranceMoney = insuranceMoney;
	}

	public BigDecimal getApplyMoney() {
		return applyMoney;
	}

	public void setApplyMoney(BigDecimal applyMoney) {
		this.applyMoney = applyMoney;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getCheckUser() {
		return checkUser;
	}

	public void setCheckUser(String checkUser) {
		this.checkUser = checkUser;
	}

	public BigDecimal getConGrowth() {
		return conGrowth;
	}

	public void setConGrowth(BigDecimal conGrowth) {
		this.conGrowth = conGrowth;
	}

	public BigDecimal getSalesGrowth() {
		return salesGrowth;
	}

	public void setSalesGrowth(BigDecimal salesGrowth) {
		this.salesGrowth = salesGrowth;
	}

	public String getShouxinType() {
		return shouxinType;
	}

	public void setShouxinType(String shouxinType) {
		this.shouxinType = shouxinType;
	}

	public String getShouxinCredntialsImg() {
		return shouxinCredntialsImg;
	}

	public void setShouxinCredntialsImg(String shouxinCredntialsImg) {
		this.shouxinCredntialsImg = shouxinCredntialsImg;
	}
	
	
}