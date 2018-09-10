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
@Table(name="gjf_mechant_model_agent_trade_history")
public class GjfMerchantModelAgentTradeHistory implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4670184201150097656L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_",length=11)
	private Long id;
	
	@Column(name="MEMBER_ID_",length=11)
	private Long memberId;
	
	@Column(name="MEMBER_NAME_",length=100)
	private String memberName;
	
	@Column(name="MEMBER_MOBILE_",length=50)
	private String memberMobile;
	
	@Column(name="TRADE_MONEY_",precision=20,scale=2)
	private BigDecimal tradeMoney;
	
	@Column(name="ADD_TIME_")
	private Date addTime;
	
	@Column(name="TRADE_STATUS_",length=1)
	private String tradeStatus;
	
	@Column(name="AGENT_MEMBER_ID_",length=11)
	private Long agentMemberId;
	
	@Column(name="AGENT_DIRECT_MONEY_",precision=20,scale=2)
	private BigDecimal agentDirectMoney;
	
	@Column(name="TRADE_NO_",length=200)
	private String tradeNo;
	
	@Column(name="AGENT_MEMBER_NAME_",length=100)
	private String agentMemberName;
	
	@Column(name="AGENY_MEMBER_MOBILE_",length=50)
	private String agentMemberMobile;
	
	@Column(name="AGENT_TYPE_",length=1)
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
