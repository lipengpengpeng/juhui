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
@Table(name="gjf_benefit_integral_day")
public class GjfBenefitIntegralDay implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1661121075744524967L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_", length = 10)
	private Long id;
	
	@Column(name = "TRADE_DAY_", length=20)
	private String tradeDay;
	
	@Column(name = "MEMBER_INCOME_TOTAL_MONEY_", precision=20,scale=2)
	private BigDecimal memberIncomeTotalMoney;
	
	@Column(name = "MERCHANT_INCOME_TOTAL_MONEY_", precision=20,scale=2)
	private BigDecimal merchantIncomeTotalMoney;
	
	@Column(name = "ACTIVITY_MERCHANTS_INCOME_MONEY_", precision=20,scale=2)
	private BigDecimal activityMerchantsIncomeMoney;
	
	@Column(name = "NO_ACTIVITY_MERCHANTS_INCOME_MONEY_", precision=20,scale=2)
	private BigDecimal noActivityMerchantsIncomeMoney;

	@Column(name = "ACTIVITY_MEMBER_FIRST_INCOME_MONEY_", precision=20,scale=2)
	private BigDecimal activityMemberFirstIncomeMoney;

	@Column(name = "ACTIVITY_MEMBER_SECOND_INCOME_MONEY_", precision=20,scale=2)
	private BigDecimal activityMemberSecondIncomeMoney;

	@Column(name = "ACTIVITY_MEMBER_THREE_INCOME_MONEY_", precision=20,scale=2)
	private BigDecimal activityMemberThreeIncomeMoney;

	@Column(name = "NO_ACTIVITY_MEMBER_FIRST_INCOME_MONEY_", precision=20,scale=2)
	private BigDecimal noActivityMemberFirstIncomeMoney;

	@Column(name = "NO_ACTIVITY_MEMBER_SECOND_INCOME_MONEY_", precision=20,scale=2)
	private BigDecimal noActivityMemberSecondIncomeMoney;

	@Column(name = "NO_ACTIVITY_MEMBER_THREE_INCOME_MONEY_", precision=20,scale=2)
	private BigDecimal noActivityMemberThreeIncomeMoney;

	@Column(name = "ACTIVITY_MERCHANT_REAL_ISSUE_MONEY_", precision=20,scale=2)
	private BigDecimal activityMerchantRealIssueMoney;
	
	@Column(name = "NO_ACTIVITY_MERCHANT_REAL_ISSUE_MONEY_", precision=20,scale=2)
	private BigDecimal noActivityMerchantRealIssueMoney;
	
	@Column(name = "ACTIVITY_MEMBER_FIRST_REAL_ISSUE_MONEY_", precision=20,scale=2)
	private BigDecimal activityMemberFirstRealIssueMoney;
	
	@Column(name = "ACTIVITY_MEMBER_SECOND_REAL_ISSUE_MONEY_", precision=20,scale=2)
	private BigDecimal activityMemberSecondRealIssueMoney;
	
	@Column(name = "ACTIVITY_MEMBER_THREE_REAL_ISSUE_MONEY_", precision=20,scale=2)
	private BigDecimal activityMemberThreeRealIssueMoney;
	
	@Column(name = "NO_ACTIVITY_MEMBER_FIRST_ISSUE_MONEY_", precision=20,scale=2)
	private BigDecimal noActivityMemberFirstIssueMoney;
	
	@Column(name = "NO_ACTIVITY_MEMBER_SECOND_ISSUE_MONEY_", precision=20,scale=2)
	private BigDecimal noActivityMemberSecondIssueMoney;
	
	@Column(name = "NO_ACTIVITY_MEMBER_THREE_ISSUE_MONEY_", precision=20,scale=2)
	private BigDecimal noActivityMemberThreeIssueMoney;
	
	@Column(name = "ACTIVITY_MERCHANT_UNIT_MONEY_", precision=20,scale=6)
	private BigDecimal activityMerchantUnitMoney;
	
	@Column(name = "NO_ACTIVITY_MERCHANT_UNIT_MONEY_", precision=20,scale=6)
	private BigDecimal noActivityMerchantUnitMoney;
	
	@Column(name = "ACTIVITY_MEMBER_FIRST_UNIT_MONEY_", precision=20,scale=6)
	private BigDecimal activityMemberFirstUnitMoney;
	
	@Column(name = "ACTIVITY_MEMBER_SECOND_UNIT_MONEY_", precision=20,scale=6)
	private BigDecimal activtiyMemberSecondUnitMoney;
	
	@Column(name = "ACTIVITY_MEMBER_THREE_UNIT_MONEY_", precision=20,scale=6)
	private BigDecimal activityMemberThreeUnitMoney;
	
	@Column(name = "NO_ACTIVITY_MEMBER_FIRST_UNIT_MONEY_", precision=20,scale=6)
	private BigDecimal noActivityMemberFirstUnitMoney;
		
	@Column(name = "NO_ACTIVITY_MEMBER_SECOND_UNIT_MONEY_", precision=20,scale=6)
	private BigDecimal noActivityMemberSecondUnitMoney;
		
    @Column(name = "NO_ACTIVITY_MEMBER_THREE_UNIT_MONEY_", precision=20,scale=6)
	private BigDecimal noActivityMemberThreeUnitMoney;
	
	@Column(name = "ADD_TIME_")
	private Date addTime;
	
	@Column(name = "ACT_TIME_")
	private Date actTime;
	
	@Column(name = "TRADE_TIME_")
	private Date tradeTime;
	
	@Column(name = "TRADE_STATUS_", length=1)
	private String tradeStatus;

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

	public BigDecimal getMemberIncomeTotalMoney() {
		return memberIncomeTotalMoney;
	}

	public void setMemberIncomeTotalMoney(BigDecimal memberIncomeTotalMoney) {
		this.memberIncomeTotalMoney = memberIncomeTotalMoney;
	}

	public BigDecimal getMerchantIncomeTotalMoney() {
		return merchantIncomeTotalMoney;
	}

	public void setMerchantIncomeTotalMoney(BigDecimal merchantIncomeTotalMoney) {
		this.merchantIncomeTotalMoney = merchantIncomeTotalMoney;
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

	public BigDecimal getActivityMemberFirstIncomeMoney() {
		return activityMemberFirstIncomeMoney;
	}

	public void setActivityMemberFirstIncomeMoney(BigDecimal activityMemberFirstIncomeMoney) {
		this.activityMemberFirstIncomeMoney = activityMemberFirstIncomeMoney;
	}

	public BigDecimal getActivityMemberSecondIncomeMoney() {
		return activityMemberSecondIncomeMoney;
	}

	public void setActivityMemberSecondIncomeMoney(BigDecimal activityMemberSecondIncomeMoney) {
		this.activityMemberSecondIncomeMoney = activityMemberSecondIncomeMoney;
	}

	public BigDecimal getActivityMemberThreeIncomeMoney() {
		return activityMemberThreeIncomeMoney;
	}

	public void setActivityMemberThreeIncomeMoney(BigDecimal activityMemberThreeIncomeMoney) {
		this.activityMemberThreeIncomeMoney = activityMemberThreeIncomeMoney;
	}

	public BigDecimal getNoActivityMemberFirstIncomeMoney() {
		return noActivityMemberFirstIncomeMoney;
	}

	public void setNoActivityMemberFirstIncomeMoney(BigDecimal noActivityMemberFirstIncomeMoney) {
		this.noActivityMemberFirstIncomeMoney = noActivityMemberFirstIncomeMoney;
	}

	public BigDecimal getNoActivityMemberSecondIncomeMoney() {
		return noActivityMemberSecondIncomeMoney;
	}

	public void setNoActivityMemberSecondIncomeMoney(BigDecimal noActivityMemberSecondIncomeMoney) {
		this.noActivityMemberSecondIncomeMoney = noActivityMemberSecondIncomeMoney;
	}

	public BigDecimal getNoActivityMemberThreeIncomeMoney() {
		return noActivityMemberThreeIncomeMoney;
	}

	public void setNoActivityMemberThreeIncomeMoney(BigDecimal noActivityMemberThreeIncomeMoney) {
		this.noActivityMemberThreeIncomeMoney = noActivityMemberThreeIncomeMoney;
	}

	public BigDecimal getActivityMerchantRealIssueMoney() {
		return activityMerchantRealIssueMoney;
	}

	public void setActivityMerchantRealIssueMoney(BigDecimal activityMerchantRealIssueMoney) {
		this.activityMerchantRealIssueMoney = activityMerchantRealIssueMoney;
	}

	public BigDecimal getNoActivityMerchantRealIssueMoney() {
		return noActivityMerchantRealIssueMoney;
	}

	public void setNoActivityMerchantRealIssueMoney(BigDecimal noActivityMerchantRealIssueMoney) {
		this.noActivityMerchantRealIssueMoney = noActivityMerchantRealIssueMoney;
	}

	public BigDecimal getActivityMemberFirstRealIssueMoney() {
		return activityMemberFirstRealIssueMoney;
	}

	public void setActivityMemberFirstRealIssueMoney(BigDecimal activityMemberFirstRealIssueMoney) {
		this.activityMemberFirstRealIssueMoney = activityMemberFirstRealIssueMoney;
	}

	public BigDecimal getActivityMemberSecondRealIssueMoney() {
		return activityMemberSecondRealIssueMoney;
	}

	public void setActivityMemberSecondRealIssueMoney(BigDecimal activityMemberSecondRealIssueMoney) {
		this.activityMemberSecondRealIssueMoney = activityMemberSecondRealIssueMoney;
	}

	public BigDecimal getActivityMemberThreeRealIssueMoney() {
		return activityMemberThreeRealIssueMoney;
	}

	public void setActivityMemberThreeRealIssueMoney(BigDecimal activityMemberThreeRealIssueMoney) {
		this.activityMemberThreeRealIssueMoney = activityMemberThreeRealIssueMoney;
	}

	public BigDecimal getNoActivityMemberFirstIssueMoney() {
		return noActivityMemberFirstIssueMoney;
	}

	public void setNoActivityMemberFirstIssueMoney(BigDecimal noActivityMemberFirstIssueMoney) {
		this.noActivityMemberFirstIssueMoney = noActivityMemberFirstIssueMoney;
	}

	public BigDecimal getNoActivityMemberSecondIssueMoney() {
		return noActivityMemberSecondIssueMoney;
	}

	public void setNoActivityMemberSecondIssueMoney(BigDecimal noActivityMemberSecondIssueMoney) {
		this.noActivityMemberSecondIssueMoney = noActivityMemberSecondIssueMoney;
	}

	public BigDecimal getNoActivityMemberThreeIssueMoney() {
		return noActivityMemberThreeIssueMoney;
	}

	public void setNoActivityMemberThreeIssueMoney(BigDecimal noActivityMemberThreeIssueMoney) {
		this.noActivityMemberThreeIssueMoney = noActivityMemberThreeIssueMoney;
	}

	public BigDecimal getActivityMerchantUnitMoney() {
		return activityMerchantUnitMoney;
	}

	public void setActivityMerchantUnitMoney(BigDecimal activityMerchantUnitMoney) {
		this.activityMerchantUnitMoney = activityMerchantUnitMoney;
	}

	public BigDecimal getNoActivityMerchantUnitMoney() {
		return noActivityMerchantUnitMoney;
	}

	public void setNoActivityMerchantUnitMoney(BigDecimal noActivityMerchantUnitMoney) {
		this.noActivityMerchantUnitMoney = noActivityMerchantUnitMoney;
	}

	public BigDecimal getActivityMemberFirstUnitMoney() {
		return activityMemberFirstUnitMoney;
	}

	public void setActivityMemberFirstUnitMoney(BigDecimal activityMemberFirstUnitMoney) {
		this.activityMemberFirstUnitMoney = activityMemberFirstUnitMoney;
	}

	public BigDecimal getActivtiyMemberSecondUnitMoney() {
		return activtiyMemberSecondUnitMoney;
	}

	public void setActivtiyMemberSecondUnitMoney(BigDecimal activtiyMemberSecondUnitMoney) {
		this.activtiyMemberSecondUnitMoney = activtiyMemberSecondUnitMoney;
	}

	public BigDecimal getActivityMemberThreeUnitMoney() {
		return activityMemberThreeUnitMoney;
	}

	public void setActivityMemberThreeUnitMoney(BigDecimal activityMemberThreeUnitMoney) {
		this.activityMemberThreeUnitMoney = activityMemberThreeUnitMoney;
	}

	public BigDecimal getNoActivityMemberFirstUnitMoney() {
		return noActivityMemberFirstUnitMoney;
	}

	public void setNoActivityMemberFirstUnitMoney(BigDecimal noActivityMemberFirstUnitMoney) {
		this.noActivityMemberFirstUnitMoney = noActivityMemberFirstUnitMoney;
	}

	public BigDecimal getNoActivityMemberSecondUnitMoney() {
		return noActivityMemberSecondUnitMoney;
	}

	public void setNoActivityMemberSecondUnitMoney(BigDecimal noActivityMemberSecondUnitMoney) {
		this.noActivityMemberSecondUnitMoney = noActivityMemberSecondUnitMoney;
	}

	public BigDecimal getNoActivityMemberThreeUnitMoney() {
		return noActivityMemberThreeUnitMoney;
	}

	public void setNoActivityMemberThreeUnitMoney(BigDecimal noActivityMemberThreeUnitMoney) {
		this.noActivityMemberThreeUnitMoney = noActivityMemberThreeUnitMoney;
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
	
	
	
}
