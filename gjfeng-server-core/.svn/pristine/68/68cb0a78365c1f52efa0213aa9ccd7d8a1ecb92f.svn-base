package cc.messcat.gjfeng.entity;

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

/**
 * GjfMemberDiviHistory entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "gjf_member_trade_divi")
public class GjfMemberTradeDivi implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_", length = 10)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="MEMBER_ID_")
	private GjfMemberInfo memberId;
	
	@Column(name = "DIVI_NO_", length=50)
	private String diviNo;
	
	@Column(name = "DIVI_NUM_", precision=10, scale=2)
	private BigDecimal diviNum;
	
	@Column(name = "DIVI_MONEY_", precision=10, scale=2)
	private BigDecimal diviMoney;
	
	@Column(name = "DIVI_MONEY_BLA_", precision=10, scale=2)
	private BigDecimal diviMoneyBla;
	
	@Column(name = "CONSUMPTION_MONEY_", precision=10, scale=2)
	private BigDecimal consumptionMoney;
	
	@Column(name = "ADD_TIME_")
	private Date addTime;
	
	@Column(name = "PAY_TYPE_", length=1)
	private String payType;
	
	@Column(name = "PAY_STATUS_", length=1)
	private String payStatus;
	
	@Column(name = "DIVI_STATUS_", length=1)
	private String diviStatus;
	
	@Column(name = "DIVI_TYPE_", length=1)
	private String diviType;
	
	@Column(name = "DIVI_MEMBER_TYPE_", length=1)
	private String diviMemberType;
	
	@Column(name = "TOKEN_", length=200)
	private String token;

	// Property accessors
	
	public GjfMemberTradeDivi() {
		super();
	}


	public GjfMemberTradeDivi(Long id, GjfMemberInfo memberId, String diviNo, BigDecimal diviNum, BigDecimal diviMoney,
		BigDecimal diviMoneyBla, BigDecimal consumptionMoney, Date addTime, String payType, String payStatus, String diviStatus,
		String diviType, String diviMemberType, String token) {
		super();
		this.id = id;
		this.memberId = memberId;
		this.diviNo = diviNo;
		this.diviNum = diviNum;
		this.diviMoney = diviMoney;
		this.diviMoneyBla = diviMoneyBla;
		this.consumptionMoney = consumptionMoney;
		this.addTime = addTime;
		this.payType = payType;
		this.payStatus = payStatus;
		this.diviStatus = diviStatus;
		this.diviType = diviType;
		this.diviMemberType = diviMemberType;
		this.token = token;
	}


	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public GjfMemberInfo getMemberId() {
		return this.memberId;
	}

	public void setMemberId(GjfMemberInfo memberId) {
		this.memberId = memberId;
	}

	public BigDecimal getDiviNum() {
		return this.diviNum;
	}

	public void setDiviNum(BigDecimal diviNum) {
		this.diviNum = diviNum;
	}

	public BigDecimal getDiviMoney() {
		return this.diviMoney;
	}

	public void setDiviMoney(BigDecimal diviMoney) {
		this.diviMoney = diviMoney;
	}

	public BigDecimal getDiviMoneyBla() {
		return this.diviMoneyBla;
	}

	public void setDiviMoneyBla(BigDecimal diviMoneyBla) {
		this.diviMoneyBla = diviMoneyBla;
	}

	public BigDecimal getConsumptionMoney() {
		return this.consumptionMoney;
	}

	public void setConsumptionMoney(BigDecimal consumptionMoney) {
		this.consumptionMoney = consumptionMoney;
	}

	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getDiviStatus() {
		return diviStatus;
	}

	public void setDiviStatus(String diviStatus) {
		this.diviStatus = diviStatus;
	}


	public String getDiviNo() {
		return diviNo;
	}


	public void setDiviNo(String diviNo) {
		this.diviNo = diviNo;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public String getDiviType() {
		return diviType;
	}

	public void setDiviType(String diviType) {
		this.diviType = diviType;
	}

	public String getDiviMemberType() {
		return diviMemberType;
	}

	public void setDiviMemberType(String diviMemberType) {
		this.diviMemberType = diviMemberType;
	}

}