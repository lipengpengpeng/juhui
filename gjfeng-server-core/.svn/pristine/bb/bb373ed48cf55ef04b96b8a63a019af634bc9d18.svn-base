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
@Table(name="gjf_special_member_trade_divi")
public class GjfspecialMemberTradeDivi implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -662915774891849101L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_")
	private Long id;
	
	@Column(name="ACTUAL_ISSUE_MONEY_",precision=20,scale=2)
	private BigDecimal actualIssueMoney;
	
	@Column(name="ISSUE_MONEY_",precision=20,scale=2)
	private BigDecimal issueMoney;
	
	@Column(name="ACTUAL_ISSUE_DIVI_",precision=20,scale=6)
	private BigDecimal actualIssueDivi;
	
	@Column(name="SPECIAL_MEMBER_ID_",length=11)
	private Long specialMemberId;
	
	@Column(name="SPECIAL_MEMBER_NAME_",length=200)
	private String specialMemberName;
	
	@Column(name="SPECIAL_MEMBER_MOBILE_",length=200)
	private String specialMemberMobile;
	
	@Column(name="ADD_TIME_")
	private Date addTime;
	
	@Column(name="TRADE_STATUS_",length=1)
	private String tradeStatus;
	
	@Column(name="TRADE_TYPE_",length=1)
	private String tradeType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getActualIssueMoney() {
		return actualIssueMoney;
	}

	public void setActualIssueMoney(BigDecimal actualIssueMoney) {
		this.actualIssueMoney = actualIssueMoney;
	}

	public BigDecimal getIssueMoney() {
		return issueMoney;
	}

	public void setIssueMoney(BigDecimal issueMoney) {
		this.issueMoney = issueMoney;
	}

	public BigDecimal getActualIssueDivi() {
		return actualIssueDivi;
	}

	public void setActualIssueDivi(BigDecimal actualIssueDivi) {
		this.actualIssueDivi = actualIssueDivi;
	}

	public Long getSpecialMemberId() {
		return specialMemberId;
	}

	public void setSpecialMemberId(Long specialMemberId) {
		this.specialMemberId = specialMemberId;
	}

	public String getSpecialMemberName() {
		return specialMemberName;
	}

	public void setSpecialMemberName(String specialMemberName) {
		this.specialMemberName = specialMemberName;
	}

	public String getSpecialMemberMobile() {
		return specialMemberMobile;
	}

	public void setSpecialMemberMobile(String specialMemberMobile) {
		this.specialMemberMobile = specialMemberMobile;
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

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	
	

}
