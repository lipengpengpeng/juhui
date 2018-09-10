package cc.messcat.gjfeng.entity;

import java.math.BigDecimal;

/**
 * GjfAttrType entity. @author MyEclipse Persistence Tools
 */
public class GjfBenefitPool implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private BigDecimal memberSysPoolCur;
	
	private BigDecimal merchantSysPoolCur;
	
	private BigDecimal platformSysPoolCur;
	
	private BigDecimal directMemberSysPoolCur;
	
	private BigDecimal directMerchantsSysPoolCur;
	
	private BigDecimal agentSysPoolCur;
	
	private BigDecimal agentSysCityPoolCur;
	
	private BigDecimal agentSysAreaPoolCur;
	
	private BigDecimal agentSysIndiPoolCur;
	
	private BigDecimal memberSysPoolTotal;
	
	private BigDecimal merchantSysPoolTotal;
	
	private BigDecimal platformSysPoolTotal;
	
	private BigDecimal directMemberSysPoolTotal;
	
	private BigDecimal directMerchantsSysPoolTotal;
	
	private BigDecimal agentSysPoolTotal;
	
	private BigDecimal agentSysCityPoolTotal;
	
	private BigDecimal agentSysAreaPoolTotal;
	
	private BigDecimal agentSysIndiPoolTotal;
	
	private String token;
	
	private BigDecimal opcenterPoolInCom;
	
	private BigDecimal opcenterPoolSpend;
	
	private BigDecimal merchartFirstGrade;
	
	private BigDecimal merchartSecondGrade;
	
	private BigDecimal merchartThreeGrade;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getMemberSysPoolCur() {
		return memberSysPoolCur;
	}

	public void setMemberSysPoolCur(BigDecimal memberSysPoolCur) {
		this.memberSysPoolCur = memberSysPoolCur;
	}

	public BigDecimal getMerchantSysPoolCur() {
		return merchantSysPoolCur;
	}

	public void setMerchantSysPoolCur(BigDecimal merchantSysPoolCur) {
		this.merchantSysPoolCur = merchantSysPoolCur;
	}

	public BigDecimal getPlatformSysPoolCur() {
		return platformSysPoolCur;
	}

	public void setPlatformSysPoolCur(BigDecimal platformSysPoolCur) {
		this.platformSysPoolCur = platformSysPoolCur;
	}

	public BigDecimal getMemberSysPoolTotal() {
		return memberSysPoolTotal;
	}

	public void setMemberSysPoolTotal(BigDecimal memberSysPoolTotal) {
		this.memberSysPoolTotal = memberSysPoolTotal;
	}

	public BigDecimal getMerchantSysPoolTotal() {
		return merchantSysPoolTotal;
	}

	public void setMerchantSysPoolTotal(BigDecimal merchantSysPoolTotal) {
		this.merchantSysPoolTotal = merchantSysPoolTotal;
	}

	public BigDecimal getPlatformSysPoolTotal() {
		return platformSysPoolTotal;
	}

	public void setPlatformSysPoolTotal(BigDecimal platformSysPoolTotal) {
		this.platformSysPoolTotal = platformSysPoolTotal;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public BigDecimal getDirectMemberSysPoolCur() {
		return directMemberSysPoolCur;
	}

	public void setDirectMemberSysPoolCur(BigDecimal directMemberSysPoolCur) {
		this.directMemberSysPoolCur = directMemberSysPoolCur;
	}

	public BigDecimal getAgentSysPoolCur() {
		return agentSysPoolCur;
	}

	public void setAgentSysPoolCur(BigDecimal agentSysPoolCur) {
		this.agentSysPoolCur = agentSysPoolCur;
	}

	public BigDecimal getDirectMemberSysPoolTotal() {
		return directMemberSysPoolTotal;
	}

	public void setDirectMemberSysPoolTotal(BigDecimal directMemberSysPoolTotal) {
		this.directMemberSysPoolTotal = directMemberSysPoolTotal;
	}

	public BigDecimal getAgentSysPoolTotal() {
		return agentSysPoolTotal;
	}

	public void setAgentSysPoolTotal(BigDecimal agentSysPoolTotal) {
		this.agentSysPoolTotal = agentSysPoolTotal;
	}

	public BigDecimal getDirectMerchantsSysPoolCur() {
		return directMerchantsSysPoolCur;
	}

	public void setDirectMerchantsSysPoolCur(BigDecimal directMerchantsSysPoolCur) {
		this.directMerchantsSysPoolCur = directMerchantsSysPoolCur;
	}

	public BigDecimal getDirectMerchantsSysPoolTotal() {
		return directMerchantsSysPoolTotal;
	}

	public void setDirectMerchantsSysPoolTotal(BigDecimal directMerchantsSysPoolTotal) {
		this.directMerchantsSysPoolTotal = directMerchantsSysPoolTotal;
	}

	public BigDecimal getAgentSysCityPoolCur() {
		return agentSysCityPoolCur;
	}

	public void setAgentSysCityPoolCur(BigDecimal agentSysCityPoolCur) {
		this.agentSysCityPoolCur = agentSysCityPoolCur;
	}

	public BigDecimal getAgentSysAreaPoolCur() {
		return agentSysAreaPoolCur;
	}

	public void setAgentSysAreaPoolCur(BigDecimal agentSysAreaPoolCur) {
		this.agentSysAreaPoolCur = agentSysAreaPoolCur;
	}

	public BigDecimal getAgentSysIndiPoolCur() {
		return agentSysIndiPoolCur;
	}

	public void setAgentSysIndiPoolCur(BigDecimal agentSysIndiPoolCur) {
		this.agentSysIndiPoolCur = agentSysIndiPoolCur;
	}

	public BigDecimal getAgentSysCityPoolTotal() {
		return agentSysCityPoolTotal;
	}

	public void setAgentSysCityPoolTotal(BigDecimal agentSysCityPoolTotal) {
		this.agentSysCityPoolTotal = agentSysCityPoolTotal;
	}

	public BigDecimal getAgentSysAreaPoolTotal() {
		return agentSysAreaPoolTotal;
	}

	public void setAgentSysAreaPoolTotal(BigDecimal agentSysAreaPoolTotal) {
		this.agentSysAreaPoolTotal = agentSysAreaPoolTotal;
	}

	public BigDecimal getAgentSysIndiPoolTotal() {
		return agentSysIndiPoolTotal;
	}

	public void setAgentSysIndiPoolTotal(BigDecimal agentSysIndiPoolTotal) {
		this.agentSysIndiPoolTotal = agentSysIndiPoolTotal;
	}

	public BigDecimal getOpcenterPoolInCom() {
		return opcenterPoolInCom;
	}

	public void setOpcenterPoolInCom(BigDecimal opcenterPoolInCom) {
		this.opcenterPoolInCom = opcenterPoolInCom;
	}

	public BigDecimal getOpcenterPoolSpend() {
		return opcenterPoolSpend;
	}

	public void setOpcenterPoolSpend(BigDecimal opcenterPoolSpend) {
		this.opcenterPoolSpend = opcenterPoolSpend;
	}

	public BigDecimal getMerchartFirstGrade() {
		return merchartFirstGrade;
	}

	public void setMerchartFirstGrade(BigDecimal merchartFirstGrade) {
		this.merchartFirstGrade = merchartFirstGrade;
	}

	public BigDecimal getMerchartSecondGrade() {
		return merchartSecondGrade;
	}

	public void setMerchartSecondGrade(BigDecimal merchartSecondGrade) {
		this.merchartSecondGrade = merchartSecondGrade;
	}

	public BigDecimal getMerchartThreeGrade() {
		return merchartThreeGrade;
	}

	public void setMerchartThreeGrade(BigDecimal merchartThreeGrade) {
		this.merchartThreeGrade = merchartThreeGrade;
	}

	
}