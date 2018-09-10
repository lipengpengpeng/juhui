package cc.messcat.gjfeng.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * GjfMemberTradeDivi entity. @author MyEclipse Persistence Tools
 */
public class GjfMemberTradeDiviHistory implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields
	private Long id;
	
	private GjfMemberInfo memberId;
	
	private String tradeNo;
	
	private BigDecimal tradeMoney;
	
	private BigDecimal tradeDiviNum;
	
	private BigDecimal tradeUnit;
	
	private BigDecimal tradeRealUnit;
	
	private BigDecimal tradeRatio;
	
	private Date addTime;
	
	private Date tradeTime;
	
	private String tradeType;
	
	private String tradeStatus;
	
	private String tradeTrmo;
	
	private String token;
	
	private String isActivity;
	
	private BigDecimal tradeSecondMoney;
	
	private BigDecimal tradeThreeMoney;
	
	private BigDecimal tradeSecondUnit;
	
	private BigDecimal tradeThreeUnit;
	
	private BigDecimal firstConsumption;
	
	private BigDecimal secondConsumption;
	
	private BigDecimal threeConsumption;
	// Property accessors
	
	public GjfMemberTradeDiviHistory() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public GjfMemberTradeDiviHistory(Long id, GjfMemberInfo memberId, String tradeNo, BigDecimal tradeMoney,
		BigDecimal tradeDiviNum, BigDecimal tradeUnit, BigDecimal tradeRealUnit, BigDecimal tradeRatio, Date addTime,
		Date tradeTime, String tradeType, String tradeStatus, String tradeTrmo, String token) {
		super();
		this.id = id;
		this.memberId = memberId;
		this.tradeNo = tradeNo;
		this.tradeMoney = tradeMoney;
		this.tradeDiviNum = tradeDiviNum;
		this.tradeUnit = tradeUnit;
		this.tradeRealUnit = tradeRealUnit;
		this.tradeRatio = tradeRatio;
		this.addTime = addTime;
		this.tradeTime = tradeTime;
		this.tradeType = tradeType;
		this.tradeStatus = tradeStatus;
		this.tradeTrmo = tradeTrmo;
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

	public String getTradeNo() {
		return this.tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public BigDecimal getTradeMoney() {
		return this.tradeMoney;
	}

	public void setTradeMoney(BigDecimal tradeMoney) {
		this.tradeMoney = tradeMoney;
	}

	public BigDecimal getTradeRatio() {
		return this.tradeRatio;
	}

	public void setTradeRatio(BigDecimal tradeRatio) {
		this.tradeRatio = tradeRatio;
	}

	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Date getTradeTime() {
		return this.tradeTime;
	}

	public void setTradeTime(Date tradeTime) {
		this.tradeTime = tradeTime;
	}

	public String getTradeType() {
		return this.tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getTradeStatus() {
		return this.tradeStatus;
	}

	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public String getTradeTrmo() {
		return this.tradeTrmo;
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

	public BigDecimal getTradeRealUnit() {
		return tradeRealUnit;
	}

	public void setTradeRealUnit(BigDecimal tradeRealUnit) {
		this.tradeRealUnit = tradeRealUnit;
	}

	public String getIsActivity() {
		return isActivity;
	}

	public void setIsActivity(String isActivity) {
		this.isActivity = isActivity;
	}

	
}