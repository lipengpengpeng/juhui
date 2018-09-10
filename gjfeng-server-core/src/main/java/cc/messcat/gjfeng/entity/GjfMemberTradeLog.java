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
@Table(name = "gjf_member_trade_log")
public class GjfMemberTradeLog implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_", length = 10)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "MEMBER_ID_")
	private GjfMemberInfo memberId;

	@ManyToOne
	@JoinColumn(name = "STORE_ID_")
	private GjfStoreInfo storeId;

	@Column(name = "TRADE_NO_", length = 50)
	private String tradeNo;
	
	@Column(name = "LINK_TRADE_NO_", length = 50)
	private String linkTradeNo;

	@Column(name = "PAY_TRADE_NO_", length = 50)
	private String payTradeNo;

	@Column(name = "TRADE_MONEY_", precision = 10, scale = 2)
	private BigDecimal tradeMoney;

	@Column(name = "ADD_TIME_")
	private Date addTime;

	@Column(name = "TRADE_TIME_")
	private Date tradeTime;

	@Column(name = "TRADE_TYPE_", length = 1)
	private String tradeType;

	@Column(name = "TRADE_STATUS_", length = 1)
	private String tradeStatus;

	@Column(name = "TRADE_TRMO_", length = 100)
	private String tradeTrmo;

	@Column(name = "TOKEN_", length = 200)
	private String token;

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

	public GjfStoreInfo getStoreId() {
		return storeId;
	}

	public void setStoreId(GjfStoreInfo storeId) {
		this.storeId = storeId;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getPayTradeNo() {
		return payTradeNo;
	}

	public void setPayTradeNo(String payTradeNo) {
		this.payTradeNo = payTradeNo;
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

	public String getTradeTrmo() {
		return tradeTrmo;
	}

	public void setTradeTrmo(String tradeTrmo) {
		this.tradeTrmo = tradeTrmo;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getLinkTradeNo() {
		return linkTradeNo;
	}

	public void setLinkTradeNo(String linkTradeNo) {
		this.linkTradeNo = linkTradeNo;
	}

}