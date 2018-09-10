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
@Table(name="gjf_special_member_trade_divi_history")
public class GjfSpecialMemberTradeDiviHistory implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -598091254463772114L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_")
	private Long id;
	
	@Column(name="MEMBER_ID_",length=11)
	private Long memberId;
	
	@Column(name="TRADE_NO_",length=300)
	private String tradeNo;
	
	@Column(name="TRADE_MONEY_",precision=20,scale=2)
	private BigDecimal tradeMoney;
	
	@Column(name="TRADE_DIVI_NUM_",precision=20,scale=6)
	private BigDecimal tradeDiviNum;
	
	@Column(name="TRADE_UNIT_",precision=20,scale=2)
	private BigDecimal tradeUnit;
	
	@Column(name="TRADE_TIME_")
	private Date tradeTime;
	
	@Column(name="TRADE_TYPE_",length=1)
	private String tradeType;
	
	@Column(name="TRADE_STATUS_",length=1)
	private String tradeStatus;
	
	@Column(name="SPECIAL_MEMBER_ID_",length=11)
	private Long specialMemberId;
	
	@Column(name="MEMBER_NAME_",length=200)
	private String memberName;
	
	@Column(name="MEMBER_MOBILE_",length=200)
	private String memberMobile;

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

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public BigDecimal getTradeMoney() {
		return tradeMoney;
	}

	public void setTradeMoney(BigDecimal tradeMoney) {
		this.tradeMoney = tradeMoney;
	}

	public BigDecimal getTradeDiviNum() {
		return tradeDiviNum;
	}

	public void setTradeDiviNum(BigDecimal tradeDiviNum) {
		this.tradeDiviNum = tradeDiviNum;
	}

	public BigDecimal getTradeUnit() {
		return tradeUnit;
	}

	public void setTradeUnit(BigDecimal tradeUnit) {
		this.tradeUnit = tradeUnit;
	}

	public Date getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(Date tradeTime) {
		this.tradeTime = tradeTime;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public Long getSpecialMemberId() {
		return specialMemberId;
	}

	public void setSpecialMemberId(Long specialMemberId) {
		this.specialMemberId = specialMemberId;
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
	
	

}
