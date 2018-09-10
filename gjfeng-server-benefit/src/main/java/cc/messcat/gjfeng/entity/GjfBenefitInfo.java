package cc.messcat.gjfeng.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * GjfBenefitInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "gjf_benefit_info")
public class GjfBenefitInfo implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;

	// Fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_", precision=10, scale=2)
	private Long id;
	
	@Column(name = "SYS_RATIO_", precision=10, scale=2)
	private Double sysRatio;
	
	@Column(name = "DIVI_POOLS_RATIO_", precision=10, scale=2)
	private Double diviPoolsRatio;
	
	@Column(name = "PLATFORM_RATIO_", precision=10, scale=2)
	private Double platformRatio;
	
	@Column(name = "AGENT_RATIO_", precision=10, scale=2)
	private Double agentRatio;
	
	@Column(name = "AGENT_CITY_RATIO_", precision=10, scale=2)
	private Double agentCityRatio;
	
	@Column(name = "AGENT_AREA_RATIO_", precision=10, scale=2)
	private Double agentAreaRatio;
	
	@Column(name = "AGENT_INDI_RATIO_", precision=10, scale=2)
	private Double agentIndiRatio;
	
	@Column(name = "DIRECT_MEMBERS_RATIO_", precision=10, scale=2)
	private Double directMembersRatio;
	
	@Column(name = "DIRECT_MERCHANTS_RATIO_", precision=10, scale=2)
	private Double directMerchantsRatio;
	
	@Column(name = "WITHDRAWAL_RATIO_", precision=10, scale=2)
	private Double withdrawalRatio;
	
	@Column(name = "INSURANCE_RATIO_", precision=10, scale=2)
	private Double insuranceRatio;
	
	@Column(name = "TAX_RATIO_", precision=10, scale=2)
	private Double taxRatio;
	
	@Column(name = "ISSUE_RATIO_", precision=10, scale=2)
	private Double issueRatio;
	
	@Column(name = "RATIO_TYPE_", length = 1)
	private String ratioType;
	
	@Column(name = "UNIT_PRICE_", precision=10, scale=2)
	private Double unitPrice;
	
	@Column(name = "ACTIVIE_REGION_UNTI_PRICE_", precision=10, scale=2)
	private Double activieRegionUnitPrice;
	
	@Column(name = "ACTIVITY_SECOND_UNIT_PRICE_", precision=10, scale=2)
	private Double activitySecondUnitPrice;
	
	@Column(name = "NO_ACTIVITY_SECOND_UNIT_PRICE_", precision=10, scale=2)
	private Double noActivitySecondUnitPrice;
	
	@Column(name = "ACTIVITY_THREE_UNIT_PRICE_", precision=10, scale=2)
	private Double activityThreeUnitPrice;
	
	@Column(name = "NO_ACTIVITY_THREE_UNIT_PRICE_", precision=10, scale=2)
	private Double noActivityThreeUnitPrice;
	
	@Column(name = "DIRECT_MEMBERS_SECOND_RATIO_", precision=10, scale=2)
	private Double directMembersSecondRatio;
	
	@Column(name = "DIRECT_MEMBER_THREE_RATIO_", precision=10, scale=2)
	private Double directMembersThreeRatio;
	
	@Column(name = "DIRECT_MERCHANTS_SECOND_RATIO_", precision=10, scale=2)
	private Double directMerchantsSecondRatio;
	
	@Column(name = "DIRECT_MERCAHNTS_THREE_RATIO_", precision=10, scale=2)
	private Double directMerchantsThreeRatio;
	
	@Column(name = "OPERATION_CENTER_RATIO_", precision=10, scale=2)
	private Double operationCenterRatio;
	

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getSysRatio() {
		return this.sysRatio;
	}

	public void setSysRatio(Double sysRatio) {
		this.sysRatio = sysRatio;
	}

	public Double getDiviPoolsRatio() {
		return this.diviPoolsRatio;
	}

	public void setDiviPoolsRatio(Double diviPoolsRatio) {
		this.diviPoolsRatio = diviPoolsRatio;
	}

	public Double getPlatformRatio() {
		return this.platformRatio;
	}

	public void setPlatformRatio(Double platformRatio) {
		this.platformRatio = platformRatio;
	}

	public Double getAgentRatio() {
		return this.agentRatio;
	}

	public void setAgentRatio(Double agentRatio) {
		this.agentRatio = agentRatio;
	}

	public Double getAgentCityRatio() {
		return this.agentCityRatio;
	}

	public void setAgentCityRatio(Double agentCityRatio) {
		this.agentCityRatio = agentCityRatio;
	}

	public Double getAgentAreaRatio() {
		return this.agentAreaRatio;
	}

	public void setAgentAreaRatio(Double agentAreaRatio) {
		this.agentAreaRatio = agentAreaRatio;
	}

	public Double getAgentIndiRatio() {
		return this.agentIndiRatio;
	}

	public void setAgentIndiRatio(Double agentIndiRatio) {
		this.agentIndiRatio = agentIndiRatio;
	}

	public Double getDirectMembersRatio() {
		return this.directMembersRatio;
	}

	public void setDirectMembersRatio(Double directMembersRatio) {
		this.directMembersRatio = directMembersRatio;
	}

	public Double getDirectMerchantsRatio() {
		return this.directMerchantsRatio;
	}

	public void setDirectMerchantsRatio(Double directMerchantsRatio) {
		this.directMerchantsRatio = directMerchantsRatio;
	}

	public Double getWithdrawalRatio() {
		return this.withdrawalRatio;
	}

	public void setWithdrawalRatio(Double withdrawalRatio) {
		this.withdrawalRatio = withdrawalRatio;
	}

	public Double getInsuranceRatio() {
		return this.insuranceRatio;
	}

	public void setInsuranceRatio(Double insuranceRatio) {
		this.insuranceRatio = insuranceRatio;
	}

	public Double getTaxRatio() {
		return this.taxRatio;
	}

	public void setTaxRatio(Double taxRatio) {
		this.taxRatio = taxRatio;
	}

	public Double getIssueRatio() {
		return this.issueRatio;
	}

	public void setIssueRatio(Double issueRatio) {
		this.issueRatio = issueRatio;
	}

	public String getRatioType() {
		return this.ratioType;
	}

	public void setRatioType(String ratioType) {
		this.ratioType = ratioType;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Double getActivieRegionUnitPrice() {
		return activieRegionUnitPrice;
	}

	public void setActivieRegionUnitPrice(Double activieRegionUnitPrice) {
		this.activieRegionUnitPrice = activieRegionUnitPrice;
	}

	public Double getActivitySecondUnitPrice() {
		return activitySecondUnitPrice;
	}

	public void setActivitySecondUnitPrice(Double activitySecondUnitPrice) {
		this.activitySecondUnitPrice = activitySecondUnitPrice;
	}

	public Double getNoActivitySecondUnitPrice() {
		return noActivitySecondUnitPrice;
	}

	public void setNoActivitySecondUnitPrice(Double noActivitySecondUnitPrice) {
		this.noActivitySecondUnitPrice = noActivitySecondUnitPrice;
	}

	public Double getActivityThreeUnitPrice() {
		return activityThreeUnitPrice;
	}

	public void setActivityThreeUnitPrice(Double activityThreeUnitPrice) {
		this.activityThreeUnitPrice = activityThreeUnitPrice;
	}

	public Double getNoActivityThreeUnitPrice() {
		return noActivityThreeUnitPrice;
	}

	public void setNoActivityThreeUnitPrice(Double noActivityThreeUnitPrice) {
		this.noActivityThreeUnitPrice = noActivityThreeUnitPrice;
	}

	public Double getDirectMembersSecondRatio() {
		return directMembersSecondRatio;
	}

	public void setDirectMembersSecondRatio(Double directMembersSecondRatio) {
		this.directMembersSecondRatio = directMembersSecondRatio;
	}

	public Double getDirectMembersThreeRatio() {
		return directMembersThreeRatio;
	}

	public void setDirectMembersThreeRatio(Double directMembersThreeRatio) {
		this.directMembersThreeRatio = directMembersThreeRatio;
	}

	public Double getDirectMerchantsSecondRatio() {
		return directMerchantsSecondRatio;
	}

	public void setDirectMerchantsSecondRatio(Double directMerchantsSecondRatio) {
		this.directMerchantsSecondRatio = directMerchantsSecondRatio;
	}

	public Double getDirectMerchantsThreeRatio() {
		return directMerchantsThreeRatio;
	}

	public void setDirectMerchantsThreeRatio(Double directMerchantsThreeRatio) {
		this.directMerchantsThreeRatio = directMerchantsThreeRatio;
	}

	public Double getOperationCenterRatio() {
		return operationCenterRatio;
	}

	public void setOperationCenterRatio(Double operationCenterRatio) {
		this.operationCenterRatio = operationCenterRatio;
	}


	
}