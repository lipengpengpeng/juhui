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

@Entity
@Table(name = "gjf_member_trade_detail")
public class GjfMemberTradeDetail implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_", length = 10)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "MEMBER_ID_")
	private GjfMemberInfo memberId;
	
	@Column(name = "TRADE_NO_", length = 50)
	private String tradeNo;

	@Column(name = "TRADE_MONEY_", precision = 20, scale = 2)
	private BigDecimal tradeMoney;

	@Column(name = "ADD_TIME_")
	private Date addTime;
	
	@Column(name = "TRADE_TIME_")
	private Date tradeTime;

	@Column(name = "TRADE_TYPE_", length = 1)
	private String tradeType;

	@Column(name = "TRADE_STATUS_", length = 1)
	private String tradeStatus;
	
	@Column(name = "TRADE_TRMO_", length = 1)
	private String tradeTrmo;

	public GjfMemberTradeDetail() {
		super();
	}


	public GjfMemberTradeDetail(Long id, GjfMemberInfo memberId, String tradeNo, BigDecimal tradeMoney, Date addTime,
		Date tradeTime, String tradeType, String tradeStatus, String tradeTrmo) {
		super();
		this.id = id;
		this.memberId = memberId;
		this.tradeNo = tradeNo;
		this.tradeMoney = tradeMoney;
		this.addTime = addTime;
		this.tradeTime = tradeTime;
		this.tradeType = tradeType;
		this.tradeStatus = tradeStatus;
		this.tradeTrmo = tradeTrmo;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public GjfMemberInfo getMemberId() {
		return memberId;
	}

	public void setMemberId(GjfMemberInfo memberId) {
		this.memberId = memberId;
	}

	public BigDecimal getTradeMoney() {
		return tradeMoney;
	}

	public void setTradeMoney(BigDecimal tradeMoney) {
		this.tradeMoney = tradeMoney;
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


	public String getTradeNo() {
		return tradeNo;
	}


	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}


	public Date getAddTime() {
		return addTime;
	}


	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}


	public String getTradeTrmo() {
		return tradeTrmo;
	}


	public void setTradeTrmo(String tradeTrmo) {
		this.tradeTrmo = tradeTrmo;
	}

}