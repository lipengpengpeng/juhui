package cc.messcat.gjfeng.entity;

import java.math.BigDecimal;
import java.util.Date;

public class GjfSummaryReport implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private BigDecimal turnOver;
	
	private BigDecimal benefitMoney;
	
	private BigDecimal memberIncomeMoney;
	
	private BigDecimal merchantsIncomeMoney;
	
	private BigDecimal memberWealPayout;
	
	private BigDecimal memberWealWaitPayout;
	
	private BigDecimal storeWealPayout;
	
	private BigDecimal storeWealWaitPayout;
	
	private BigDecimal directDriveMemberAward;
	
	private BigDecimal directDriveStoreAward;
	
	private BigDecimal agentSysCityAward;
	
	private BigDecimal agentSysAreaAwara;
	
	private BigDecimal agentSysIndiAwara;
	
	private BigDecimal platFormEarnings;
	
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
