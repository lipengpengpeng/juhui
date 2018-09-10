package cc.messcat.gjfeng.common.vo.app;

import java.math.BigDecimal;

/**
 * GjfMemberTradeDivi entity. @author MyEclipse Persistence Tools
 */
public class MemberTradeDiviHistoryVo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields
	private String tradeNo;
	
	private BigDecimal tradeMoney;
	
	private Long addTime;
	
	private Long tradeTime;
	
	private String tradeType;
	
	private String tradeStatus;
	
	private String tradeTrmo;
	
	private BigDecimal tradeSecondMoney;
	
	private BigDecimal tradeThreeMoney;
	
	private BigDecimal tradeSecondUnit;
	
	private BigDecimal tradeThreeUnit;
	
	private BigDecimal firstConsumption;
	
	private BigDecimal secondConsumption;
	
	private BigDecimal threeConsumption;

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

	public Long getAddTime() {
		return addTime;
	}

	public void setAddTime(Long addTime) {
		this.addTime = addTime;
	}

	public Long getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(Long tradeTime) {
		this.tradeTime = tradeTime;
	}

	public BigDecimal getTradeSecondMoney() {
		return tradeSecondMoney;
	}

	public void setTradeSecondMoney(BigDecimal tradeSecondMoney) {
		this.tradeSecondMoney = tradeSecondMoney;
	}

	public BigDecimal getTradeThreeMoney() {
		return tradeThreeMoney;
	}

	public void setTradeThreeMoney(BigDecimal tradeThreeMoney) {
		this.tradeThreeMoney = tradeThreeMoney;
	}

	public BigDecimal getTradeSecondUnit() {
		return tradeSecondUnit;
	}

	public void setTradeSecondUnit(BigDecimal tradeSecondUnit) {
		this.tradeSecondUnit = tradeSecondUnit;
	}

	public BigDecimal getTradeThreeUnit() {
		return tradeThreeUnit;
	}

	public void setTradeThreeUnit(BigDecimal tradeThreeUnit) {
		this.tradeThreeUnit = tradeThreeUnit;
	}

	public BigDecimal getFirstConsumption() {
		return firstConsumption;
	}

	public void setFirstConsumption(BigDecimal firstConsumption) {
		this.firstConsumption = firstConsumption;
	}

	public BigDecimal getSecondConsumption() {
		return secondConsumption;
	}

	public void setSecondConsumption(BigDecimal secondConsumption) {
		this.secondConsumption = secondConsumption;
	}

	public BigDecimal getThreeConsumption() {
		return threeConsumption;
	}

	public void setThreeConsumption(BigDecimal threeConsumption) {
		this.threeConsumption = threeConsumption;
	}
	
	
}