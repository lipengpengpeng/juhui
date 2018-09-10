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
@Table(name="gjf_member_trade_opcenter")
public class GjfMemberTradeOpcenter implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_")
	private Long id;
	
	@Column(name="MEMBER_ID_")
	private Long memberId;
	
	@Column(name="BUY_MEMBER_ID_")
	private Long buyMemberId;
	
	@Column(name="CONSUMPTION_MONEY_",precision=20,scale=2)
	private BigDecimal consumptionMoney;
	
	@Column(name="BENEFIT_MONEY_",precision=20,scale=2)
	private BigDecimal benefitMoney;
	
	@Column(name="TRADE_MONEY_",precision=20,scale=2)
	private BigDecimal tradeMoney;
	
	@Column(name="TRADE_NO_",length=300)
	private String tradeNo;
	
	@Column(name="TRADE_STATUS_",length=1)
	private String tradeStatus;
	
	@Column(name="TRADE_TYPE_",length=1)
	private String tradeType;
	
	@Column(name="ADD_TIME_")
	private Date addTime;
	
	public GjfMemberTradeOpcenter(){
		super();
	}
	
	public GjfMemberTradeOpcenter(Long memberId,Long buyMemberId,BigDecimal consumptionMoney,BigDecimal benefitMoney
			                      ,BigDecimal tradeMoney,String tradeNo,String tradeStatus,String tradeType,Date addTime){
		this.memberId=memberId;
		this.buyMemberId=buyMemberId;
		this.consumptionMoney=consumptionMoney;
		this.benefitMoney=benefitMoney;
		this.tradeMoney=tradeMoney;
		this.tradeNo=tradeNo;
		this.tradeStatus=tradeStatus;
		this.tradeType=tradeType;
		this.addTime=addTime;
	}

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

	public Long getBuyMemberId() {
		return buyMemberId;
	}

	public void setBuyMemberId(Long buyMemberId) {
		this.buyMemberId = buyMemberId;
	}

	public BigDecimal getConsumptionMoney() {
		return consumptionMoney;
	}

	public void setConsumptionMoney(BigDecimal consumptionMoney) {
		this.consumptionMoney = consumptionMoney;
	}

	public BigDecimal getBenefitMoney() {
		return benefitMoney;
	}

	public void setBenefitMoney(BigDecimal benefitMoney) {
		this.benefitMoney = benefitMoney;
	}

	public BigDecimal getTradeMoney() {
		return tradeMoney;
	}

	public void setTradeMoney(BigDecimal tradeMoney) {
		this.tradeMoney = tradeMoney;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	
}
