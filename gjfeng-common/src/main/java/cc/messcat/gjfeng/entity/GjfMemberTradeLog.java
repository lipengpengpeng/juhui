package cc.messcat.gjfeng.entity;

import java.math.BigDecimal;
import java.util.Date;

public class GjfMemberTradeLog implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private GjfMemberInfo memberId;
	
	private GjfStoreInfo storeId;
	
	private String tradeNo;
	
	private String payTradeNo;
	
	private String linkTradeNo;
	
	private BigDecimal tradeMoney;
	
	private Date addTime;
	
	private Date tradeTime;
	
	private String tradeType;
	
	private String tradeStatus;
	
	private String tradeTrmo;
	
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