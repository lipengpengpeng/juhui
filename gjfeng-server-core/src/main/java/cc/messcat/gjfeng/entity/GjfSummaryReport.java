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
@Table(name = "gjf_summary_report")
public class GjfSummaryReport implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_", length = 10)
	private Long id;
	
	@Column(name = "TURNOVER_", precision=20, scale=2)
	private BigDecimal turnOver;
	
	@Column(name = "MEMBER_INCOME_MONEY_", precision=20, scale=2)
	private BigDecimal memberIncomeMoney;
	
	@Column(name = "MERCHANTS_INCOME_MONEY_", precision=20, scale=2)
	private BigDecimal merchantsIncomeMoney;
	
	@Column(name = "BENEFIT_MONEY_", precision=20, scale=2)
	private BigDecimal benefitMoney;
	
	@Column(name = "MEMBER_WEAL_PAYOUT_", precision=20, scale=2)
	private BigDecimal memberWealPayout;
	
	@Column(name = "MEMBER_WEAL_WAIT_PAYOUT_", precision=20, scale=2)
	private BigDecimal memberWealWaitPayout;
	
	@Column(name = "STORE_WEAL_PAYOUT_", precision=20, scale=2)
	private BigDecimal storeWealPayout;
	
	@Column(name = "STORE_WEAL_WAIT_PAYOUT_", precision=20, scale=2)
	private BigDecimal storeWealWaitPayout;
	
	@Column(name = "DIRECT_DRIVE_MEMBER_AWARD_", precision=20, scale=2)
	private BigDecimal directDriveMemberAward;
	
	@Column(name = "DIRECT_DRIVE_STORE_AWARD_", precision=20, scale=2)
	private BigDecimal directDriveStoreAward;
	
	@Column(name = "AGENT_SYS_CITY_AWARD_", precision=20, scale=2)
	private BigDecimal agentSysCityAward;
	
	@Column(name = "AGENT_SYS_AREA_AWARD_", precision=20, scale=2)
	private BigDecimal agentSysAreaAwara;
	
	@Column(name = "AGENT_SYS_INDI_AWARD_", precision=20, scale=2)
	private BigDecimal agentSysIndiAwara;
	
	@Column(name = "PLATFORM_EARNINGS_", precision=20, scale=2)
	private BigDecimal platFormEarnings;
	
	@Column(name = "ADD_TIME_")
	private Date addTime;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public BigDecimal getTurnOver() {
		return turnOver;
	}
	public void setTurnOver(BigDecimal turnOver) {
		this.turnOver = turnOver;
	}
	public BigDecimal getBenefitMoney() {
		return benefitMoney;
	}
	public void setBenefitMoney(BigDecimal benefitMoney) {
		this.benefitMoney = benefitMoney;
	}
	public BigDecimal getMemberWealPayout() {
		return memberWealPayout;
	}
	public void setMemberWealPayout(BigDecimal memberWealPayout) {
		this.memberWealPayout = memberWealPayout;
	}
	public BigDecimal getMemberWealWaitPayout() {
		return memberWealWaitPayout;
	}
	public void setMemberWealWaitPayout(BigDecimal memberWealWaitPayout) {
		this.memberWealWaitPayout = memberWealWaitPayout;
	}
	public BigDecimal getStoreWealPayout() {
		return storeWealPayout;
	}
	public void setStoreWealPayout(BigDecimal storeWealPayout) {
		this.storeWealPayout = storeWealPayout;
	}
	public BigDecimal getStoreWealWaitPayout() {
		return storeWealWaitPayout;
	}
	public void setStoreWealWaitPayout(BigDecimal storeWealWaitPayout) {
		this.storeWealWaitPayout = storeWealWaitPayout;
	}
	public BigDecimal getDirectDriveMemberAward() {
		return directDriveMemberAward;
	}
	public void setDirectDriveMemberAward(BigDecimal directDriveMemberAward) {
		this.directDriveMemberAward = directDriveMemberAward;
	}
	public BigDecimal getDirectDriveStoreAward() {
		return directDriveStoreAward;
	}
	public void setDirectDriveStoreAward(BigDecimal directDriveStoreAward) {
		this.directDriveStoreAward = directDriveStoreAward;
	}
	public BigDecimal getAgentSysCityAward() {
		return agentSysCityAward;
	}
	public void setAgentSysCityAward(BigDecimal agentSysCityAward) {
		this.agentSysCityAward = agentSysCityAward;
	}
	public BigDecimal getAgentSysAreaAwara() {
		return agentSysAreaAwara;
	}
	public void setAgentSysAreaAwara(BigDecimal agentSysAreaAwara) {
		this.agentSysAreaAwara = agentSysAreaAwara;
	}
	public BigDecimal getAgentSysIndiAwara() {
		return agentSysIndiAwara;
	}
	public void setAgentSysIndiAwara(BigDecimal agentSysIndiAwara) {
		this.agentSysIndiAwara = agentSysIndiAwara;
	}
	public BigDecimal getPlatFormEarnings() {
		return platFormEarnings;
	}
	public void setPlatFormEarnings(BigDecimal platFormEarnings) {
		this.platFormEarnings = platFormEarnings;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
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
	
	
}
