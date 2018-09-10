package cc.messcat.gjfeng.entity;

import java.io.Serializable;
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

@Entity
@Table(name="gjf_merchant_upgrade_trade_history")
public class GjfMerchantUpgradeHistory implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5871740908106141085L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_", length = 10)
	private Long id;
	
	@Column(name="MEMBER_ID_",length=10)
	private Long memberId;
	
	@Column(name="MEMBER_NAME_",length=200)
	private String memberName;
	
	@Column(name="MEMBER_MOBILE_",length=200)
	private String memberMobile;
	
	@Column(name="TRADE_TYPE_",length=1)
	private String tradeType;
	
	@Column(name="TRADE_MONEY_",precision=20,scale=2)
	private BigDecimal tradeMoney;
	
	@Column(name="TRADE_STATUS_",length=1)
	private String tradeStatus;
	
	@Column(name="PAY_TYPE_",length=1)
	private String payType;
	
	@Column(name="TRADE_TIME_")
	private Date tradeTime;
	
	@Column(name="DIRECT_MEMBER_MONEY_",precision=20,scale=2)
	private BigDecimal directMemberMoney;
	
	@Column(name="DIRECT_MEMBER_",length=11)
	private Long directMember;
	
	@Column(name="TRADE_NO_",length=200)
	private String tradeNo;
	
	@Column(name="GIVE_TYPE_",length=1)
	private String giveType;
	
	@Column(name="GIVE_MEMBER_ID_",length=11)
    private Long giveMemberId;
	
	@ManyToOne
	@JoinColumn(name="PROVICE_ID_")
	private GjfAddressProvince proviceId;
	
	@ManyToOne
	@JoinColumn(name="CITY_ID_")
	private GjfAddressCity cityId;
	
	@ManyToOne
	@JoinColumn(name="AREA_ID_")
	private GjfAddressArea areaId;
	
	@Column(name = "AGENT_START_DATE_")
	private Date agentStartDate;
	
	@Column(name = "AGENT_END_DATE_")
	private Date agentEndDate;
	
	@Column(name="AGENT_MONEY_",precision=20,scale=2)
	private BigDecimal agentMoney;
	
	@Column(name="OPCENTER_DIRECT_MONEY_",precision=20,scale=2)
	private BigDecimal opcenterDirectMoney;
	
	@Column(name = "OPCENTER_MEMBER_IDS_",length=100)
	private String opcenterMemberIds;
	
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

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public BigDecimal getTradeMoney() {
		return tradeMoney;
	}

	public void setTradeMoney(BigDecimal tradeMoney) {
		this.tradeMoney = tradeMoney;
	}

	public String getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public Date getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(Date tradeTime) {
		this.tradeTime = tradeTime;
	}

	public BigDecimal getDirectMemberMoney() {
		return directMemberMoney;
	}

	public void setDirectMemberMoney(BigDecimal directMemberMoney) {
		this.directMemberMoney = directMemberMoney;
	}

	
	public Long getDirectMember() {
		return directMember;
	}

	public void setDirectMember(Long directMember) {
		this.directMember = directMember;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getGiveType() {
		return giveType;
	}

	public void setGiveType(String giveType) {
		this.giveType = giveType;
	}

	public Long getGiveMemberId() {
		return giveMemberId;
	}

	public void setGiveMemberId(Long giveMemberId) {
		this.giveMemberId = giveMemberId;
	}

	public GjfAddressProvince getProviceId() {
		return proviceId;
	}

	public void setProviceId(GjfAddressProvince proviceId) {
		this.proviceId = proviceId;
	}

	public GjfAddressCity getCityId() {
		return cityId;
	}

	public void setCityId(GjfAddressCity cityId) {
		this.cityId = cityId;
	}

	public GjfAddressArea getAreaId() {
		return areaId;
	}

	public void setAreaId(GjfAddressArea areaId) {
		this.areaId = areaId;
	}

	public Date getAgentStartDate() {
		return agentStartDate;
	}

	public void setAgentStartDate(Date agentStartDate) {
		this.agentStartDate = agentStartDate;
	}

	public Date getAgentEndDate() {
		return agentEndDate;
	}

	public void setAgentEndDate(Date agentEndDate) {
		this.agentEndDate = agentEndDate;
	}

	public BigDecimal getAgentMoney() {
		return agentMoney;
	}

	public void setAgentMoney(BigDecimal agentMoney) {
		this.agentMoney = agentMoney;
	}

	public BigDecimal getOpcenterDirectMoney() {
		return opcenterDirectMoney;
	}

	public void setOpcenterDirectMoney(BigDecimal opcenterDirectMoney) {
		this.opcenterDirectMoney = opcenterDirectMoney;
	}

	public String getOpcenterMemberIds() {
		return opcenterMemberIds;
	}

	public void setOpcenterMemberIds(String opcenterMemberIds) {
		this.opcenterMemberIds = opcenterMemberIds;
	}
	
	

}
