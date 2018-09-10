package cc.messcat.gjfeng.entity;

import java.math.BigDecimal;
import java.util.Date;


public class GjfMemberTradeDetail implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	private Long id;

	private GjfMemberInfo memberId;
	
	private String tradeNo;

	private BigDecimal tradeMoney;
	
	private Date addTime;

	private Date tradeTime;

	private String tradeType;

	private String tradeStatus;
	
	private String tradeTrmo;

	public GjfMemberTradeDetail() {
		super();
	}

	public GjfMemberTradeDetail(Long id, GjfMemberInfo memberId, BigDecimal tradeMoney, Date tradeTime, String tradeType,
		String tradeStatus) {
		super();
		this.id = id;
		this.memberId = memberId;
		this.tradeMoney = tradeMoney;
		this.tradeTime = tradeTime;
		this.tradeType = tradeType;
		this.tradeStatus = tradeStatus;
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