package cc.messcat.gjfeng.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "gjf_benefit_day")
public class GjfBenefitDay implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_", length = 10)
	private Long id;
	
	@Column(name = "TRADE_DAY_", length=20)
	private String tradeDay;
	
	@Column(name = "MEMBER_INCOME_MONEY_", precision=20, scale=2)
	private BigDecimal memberIncomeMoney;
	
	@Column(name = "MERCHANTS_INCOME_MONEY_", precision=20, scale=2)
	private BigDecimal merchantsIncomeMoney;
	
	@Column(name = "MEMBER_MONEY_", precision=20, scale=2)
	private BigDecimal memberMoney;
	
	@Column(name = "MERCHANTS_MONEY_", precision=20, scale=2)
	private BigDecimal merchantsMoney;
	
	@Column(name = "MEMBER_REAL_MONEY_", precision=20, scale=2)
	private BigDecimal memberRealMoney;
	
	@Column(name = "MERCHANTS_REAL_MONEY_", precision=20, scale=2)
	private BigDecimal merchantsRealMoney;
	
	@Column(name = "MEMBER_UNIT_MONEY_", precision=20, scale=2)
	private BigDecimal memberUnitMoney;
	
	@Column(name = "MERCHANTS_UNIT_MONEY_", precision=20, scale=2)
	private BigDecimal merchantsUnitMoney;
	
	@Column(name = "MEMBER_REAL_UNIT_MONEY_", precision=20, scale=2)
	private BigDecimal memberRealUnitMoney;
	
	@Column(name = "MERCHANTS_REAL_UNIT_MONEY_", precision=20, scale=2)
	private BigDecimal merchantsRealUnitMoney;
	
	@Column(name = "MEMBER_DIVI_NUM_", precision=20, scale=2)
	private BigDecimal memberDiviNum;
	
	@Column(name = "MERCHANTS_DIVI_NUM_", precision=20, scale=2)
	private BigDecimal merchantsDiviNum;
	
	@Column(name = "MEMBER_REAL_DIVI_NUM_", precision=20, scale=2)
	private BigDecimal memberRealDiviNum;
	
	@Column(name = "merchants_REAL_DIVI_NUM_", precision=10, scale=2)
	private BigDecimal merchantsRealDiviNum;
	
	@Column(name = "ADD_TIME_")
	private Date addTime;
	
	@Column(name = "ACT_TIME_")
	private Date actTime;
	
	@Column(name = "TRADE_TIME_")
	private Date tradeTime;
	
	@Column(name = "TRADE_STATUS_", length=1)
	private String tradeStatus;
	
	@Column(name = "ACTIVITY_MEMBER_INCOME_MONEY_", precision=20, scale=2)
	private BigDecimal activityMemberIncomeMoney;
	
	@Column(name = "NO_ACTIVITY_MEMBER_INCOME_MONEY_", precision=20, scale=2)
	private BigDecimal noActivityMemberIncomeMoney;
	
	@Column(name = "ACTIVITY_MERCHANTS_INCOME_MONEY_", precision=20, scale=2)
	private BigDecimal activityMerchantsIncomeMoney;
	
	@Column(name = "NO_ACTIVITY_NERCHANTS_INCOME_MONEY_", precision=20, scale=2)
	private BigDecimal noActivityMerchantsIncomeMoney;
	
	@Column(name = "ACTIVITY_MEMBER_DIVI_NUM_", precision=20, scale=6)
	private BigDecimal activityMemberDiviNum;
	
	@Column(name = "NO_ACTIVITY_MEMBER_DIVI_NUM_", precision=20, scale=6)
	private BigDecimal noActivityMemberDiviNum;
	
	@Column(name = "ACTIVITY_MERCHANTS_DIVI_NUM_", precision=20, scale=6)
	private BigDecimal activityMerchantsDiviNum;
	
	@Column(name = "NO_ACTIVITY_MERCHANTS_DIVI_NUM_", precision=20, scale=6)
	private BigDecimal noActivityMerchantsDiviNum;
	
	@Column(name = "ACTIVITY_MEMBER_REAL_MONEY_", precision=20, scale=2)
	private BigDecimal activityMemberRealMoney;
	
	@Column(name = "NO_ACTIVITY_MEMBER_REAL_MONEY_", precision=20, scale=2)
	private BigDecimal noActivityMemberRealMoney;
	
	@Column(name = "ACTIVITY_MERCHANTS_REAL_MONEY_", precision=20, scale=2)
	private BigDecimal activtiyMerchantsRealMoney;
	
	@Column(name = "NO_ACTIVITY_MERCHANTS_REAL_MONEY_", precision=20, scale=2)
	private BigDecimal noActivityMerchantsRealMoney;
	
	@Column(name = "NO_ACTIVITY_MEMBER_UNIT_MONEY_", precision=20, scale=2)
	private BigDecimal noActivityMemberUnityMoney;
	
	@Column(name = "NO_ACTIVITY_MERCHANTS_UNIT_MONEY_", precision=20, scale=2)
	private BigDecimal noActivityMerchantsUnitMoney;
	
	@Column(name = "NO_ACTIVITY_MEMBER_REAL_UNIT_MONEY", precision=20, scale=2)
	private BigDecimal noActivityMemberRealUnitMoney;
	
	@Column(name = "NO_ACTIVITY_MERCHANTS_REAL_UNIT_MONEY_", precision=20, scale=2)
	private BigDecimal noActivityMerchantsRealUnitMoney;

	public GjfBenefitDay() {
		super();
	}

	public GjfBenefitDay(Long id, String tradeDay, BigDecimal memberIncomeMoney, BigDecimal merchantsIncomeMoney,
		BigDecimal memberMoney, BigDecimal merchantsMoney, BigDecimal memberRealMoney, BigDecimal merchantsRealMoney,
		BigDecimal memberUnitMoney, BigDecimal merchantsUnitMoney, BigDecimal memberRealUnitMoney,
		BigDecimal merchantsRealUnitMoney, BigDecimal memberDiviNum, BigDecimal merchantsDiviNum, BigDecimal memberRealDiviNum,
		BigDecimal merchantsRealDiviNum, Date addTime, Date actTime, Date tradeTime, String tradeStatus) {
		super();
		this.id = id;
		this.tradeDay = tradeDay;
		this.memberIncomeMoney = memberIncomeMoney;
		this.merchantsIncomeMoney = merchantsIncomeMoney;
		this.memberMoney = memberMoney;
		this.merchantsMoney = merchantsMoney;
		this.memberRealMoney = memberRealMoney;
		this.merchantsRealMoney = merchantsRealMoney;
		this.memberUnitMoney = memberUnitMoney;
		this.merchantsUnitMoney = merchantsUnitMoney;
		this.memberRealUnitMoney = memberRealUnitMoney;
		this.merchantsRealUnitMoney = merchantsRealUnitMoney;
		this.memberDiviNum = memberDiviNum;
		this.merchantsDiviNum = merchantsDiviNum;
		this.memberRealDiviNum = memberRealDiviNum;
		this.merchantsRealDiviNum = merchantsRealDiviNum;
		this.addTime = addTime;
		this.actTime = actTime;
		this.tradeTime = tradeTime;
		this.tradeStatus = tradeStatus;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTradeDay() {
		return tradeDay;
	}

	public void setTradeDay(String tradeDay) {
		this.tradeDay = tradeDay;
	}

	public BigDecimal getMemberMoney() {
		return memberMoney;
	}

	public void setMemberMoney(BigDecimal memberMoney) {
		this.memberMoney = memberMoney;
	}

	public BigDecimal getMerchantsMoney() {
		return merchantsMoney;
	}

	public void setMerchantsMoney(BigDecimal merchantsMoney) {
		this.merchantsMoney = merchantsMoney;
	}

	public BigDecimal getMemberRealMoney() {
		return memberRealMoney;
	}

	public void setMemberRealMoney(BigDecimal memberRealMoney) {
		this.memberRealMoney = memberRealMoney;
	}

	public BigDecimal getMerchantsRealMoney() {
		return merchantsRealMoney;
	}

	public void setMerchantsRealMoney(BigDecimal merchantsRealMoney) {
		this.merchantsRealMoney = merchantsRealMoney;
	}

	public BigDecimal getMemberUnitMoney() {
		return memberUnitMoney;
	}

	public void setMemberUnitMoney(BigDecimal memberUnitMoney) {
		this.memberUnitMoney = memberUnitMoney;
	}

	public BigDecimal getMerchantsUnitMoney() {
		return merchantsUnitMoney;
	}

	public void setMerchantsUnitMoney(BigDecimal merchantsUnitMoney) {
		this.merchantsUnitMoney = merchantsUnitMoney;
	}

	public BigDecimal getMemberRealUnitMoney() {
		return memberRealUnitMoney;
	}

	public void setMemberRealUnitMoney(BigDecimal memberRealUnitMoney) {
		this.memberRealUnitMoney = memberRealUnitMoney;
	}

	public BigDecimal getMerchantsRealUnitMoney() {
		return merchantsRealUnitMoney;
	}

	public void setMerchantsRealUnitMoney(BigDecimal merchantsRealUnitMoney) {
		this.merchantsRealUnitMoney = merchantsRealUnitMoney;
	}

	public BigDecimal getMemberDiviNum() {
		return memberDiviNum;
	}

	public void setMemberDiviNum(BigDecimal memberDiviNum) {
		this.memberDiviNum = memberDiviNum;
	}

	public BigDecimal getMerchantsDiviNum() {
		return merchantsDiviNum;
	}

	public void setMerchantsDiviNum(BigDecimal merchantsDiviNum) {
		this.merchantsDiviNum = merchantsDiviNum;
	}

	public BigDecimal getMemberRealDiviNum() {
		return memberRealDiviNum;
	}

	public void setMemberRealDiviNum(BigDecimal memberRealDiviNum) {
		this.memberRealDiviNum = memberRealDiviNum;
	}

	public BigDecimal getMerchantsRealDiviNum() {
		return merchantsRealDiviNum;
	}

	public void setMerchantsRealDiviNum(BigDecimal merchantsRealDiviNum) {
		this.merchantsRealDiviNum = merchantsRealDiviNum;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Date getActTime() {
		return actTime;
	}

	public void setActTime(Date actTime) {
		this.actTime = actTime;
	}

	public Date getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(Date tradeTime) {
		this.tradeTime = tradeTime;
	}

	public String getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public BigDecimal getMemberIncomeMoney() {
		return memberIncomeMoney;
	}

	public void setMemberIncomeMoney(BigDecimal memberIncomeMoney) {
		this.memberIncomeMoney = memberIncomeMoney;
	}

	public BigDecimal getMerchantsIncomeMoney() {
		return merchantsIncomeMoney;
	}

	public void setMerchantsIncomeMoney(BigDecimal merchantsIncomeMoney) {
		this.merchantsIncomeMoney = merchantsIncomeMoney;
	}

	public BigDecimal getActivityMemberIncomeMoney() {
		return activityMemberIncomeMoney;
	}

	public void setActivityMemberIncomeMoney(BigDecimal activityMemberIncomeMoney) {
		this.activityMemberIncomeMoney = activityMemberIncomeMoney;
	}

	public BigDecimal getNoActivityMemberIncomeMoney() {
		return noActivityMemberIncomeMoney;
	}

	public void setNoActivityMemberIncomeMoney(BigDecimal noActivityMemberIncomeMoney) {
		this.noActivityMemberIncomeMoney = noActivityMemberIncomeMoney;
	}

	public BigDecimal getActivityMerchantsIncomeMoney() {
		return activityMerchantsIncomeMoney;
	}

	public void setActivityMerchantsIncomeMoney(BigDecimal activityMerchantsIncomeMoney) {
		this.activityMerchantsIncomeMoney = activityMerchantsIncomeMoney;
	}

	public BigDecimal getNoActivityMerchantsIncomeMoney() {
		return noActivityMerchantsIncomeMoney;
	}

	public void setNoActivityMerchantsIncomeMoney(BigDecimal noActivityMerchantsIncomeMoney) {
		this.noActivityMerchantsIncomeMoney = noActivityMerchantsIncomeMoney;
	}

	public BigDecimal getActivityMemberDiviNum() {
		return activityMemberDiviNum;
	}

	public void setActivityMemberDiviNum(BigDecimal activityMemberDiviNum) {
		this.activityMemberDiviNum = activityMemberDiviNum;
	}

	public BigDecimal getNoActivityMemberDiviNum() {
		return noActivityMemberDiviNum;
	}

	public void setNoActivityMemberDiviNum(BigDecimal noActivityMemberDiviNum) {
		this.noActivityMemberDiviNum = noActivityMemberDiviNum;
	}

	public BigDecimal getActivityMerchantsDiviNum() {
		return activityMerchantsDiviNum;
	}

	public void setActivityMerchantsDiviNum(BigDecimal activityMerchantsDiviNum) {
		this.activityMerchantsDiviNum = activityMerchantsDiviNum;
	}

	public BigDecimal getNoActivityMerchantsDiviNum() {
		return noActivityMerchantsDiviNum;
	}

	public void setNoActivityMerchantsDiviNum(BigDecimal noActivityMerchantsDiviNum) {
		this.noActivityMerchantsDiviNum = noActivityMerchantsDiviNum;
	}

	public BigDecimal getActivityMemberRealMoney() {
		return activityMemberRealMoney;
	}

	public void setActivityMemberRealMoney(BigDecimal activityMemberRealMoney) {
		this.activityMemberRealMoney = activityMemberRealMoney;
	}

	public BigDecimal getNoActivityMemberRealMoney() {
		return noActivityMemberRealMoney;
	}

	public void setNoActivityMemberRealMoney(BigDecimal noActivityMemberRealMoney) {
		this.noActivityMemberRealMoney = noActivityMemberRealMoney;
	}

	public BigDecimal getActivtiyMerchantsRealMoney() {
		return activtiyMerchantsRealMoney;
	}

	public void setActivtiyMerchantsRealMoney(BigDecimal activtiyMerchantsRealMoney) {
		this.activtiyMerchantsRealMoney = activtiyMerchantsRealMoney;
	}

	public BigDecimal getNoActivityMerchantsRealMoney() {
		return noActivityMerchantsRealMoney;
	}

	public void setNoActivityMerchantsRealMoney(BigDecimal noActivityMerchantsRealMoney) {
		this.noActivityMerchantsRealMoney = noActivityMerchantsRealMoney;
	}

	public BigDecimal getNoActivityMemberUnityMoney() {
		return noActivityMemberUnityMoney;
	}

	public void setNoActivityMemberUnityMoney(BigDecimal noActivityMemberUnityMoney) {
		this.noActivityMemberUnityMoney = noActivityMemberUnityMoney;
	}

	public BigDecimal getNoActivityMerchantsUnitMoney() {
		return noActivityMerchantsUnitMoney;
	}

	public void setNoActivityMerchantsUnitMoney(BigDecimal noActivityMerchantsUnitMoney) {
		this.noActivityMerchantsUnitMoney = noActivityMerchantsUnitMoney;
	}

	public BigDecimal getNoActivityMemberRealUnitMoney() {
		return noActivityMemberRealUnitMoney;
	}

	public void setNoActivityMemberRealUnitMoney(BigDecimal noActivityMemberRealUnitMoney) {
		this.noActivityMemberRealUnitMoney = noActivityMemberRealUnitMoney;
	}

	public BigDecimal getNoActivityMerchantsRealUnitMoney() {
		return noActivityMerchantsRealUnitMoney;
	}

	public void setNoActivityMerchantsRealUnitMoney(BigDecimal noActivityMerchantsRealUnitMoney) {
		this.noActivityMerchantsRealUnitMoney = noActivityMerchantsRealUnitMoney;
	}
	
	

}