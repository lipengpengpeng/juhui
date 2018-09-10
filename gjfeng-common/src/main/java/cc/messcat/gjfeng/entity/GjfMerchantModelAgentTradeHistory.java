package cc.messcat.gjfeng.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


public class GjfMerchantModelAgentTradeHistory implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4670184201150097656L;
		
	private Long id;
	
	private Long memberId;
	
	private String memberName;
	
	private String memberMobile;
	
	private BigDecimal tradeMoney;
	
	private Date addTime;
	
	private String tradeStatus;
	
	private Long agentMemberId;
	
	private BigDecimal agentDirectMoney;
	
	private String tradeNo;
	
	private String agentMemberName;
	
	private String agentMemberMobile;
	
	private String agentType;

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

	public String getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public Long getAgentMemberId() {
		return agentMemberId;
	}

	public void setAgentMemberId(Long agentMemberId) {
		this.agentMemberId = agentMemberId;
	}

	public BigDecimal getAgentDirectMoney() {
		return agentDirectMoney;
	}

	public void setAgentDirectMoney(BigDecimal agentDirectMoney) {
		this.agentDirectMoney = agentDirectMoney;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
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

	public String getAgentType() {
		return agentType;
	}

	public void setAgentType(String agentType) {
		this.agentType = agentType;
	}
	
	
	

}
