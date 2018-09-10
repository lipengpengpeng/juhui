package cc.messcat.gjfeng.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class GjfMemberTreasureTrade implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9149777815207902181L;

	private Long id;
	
	private Long memberId;
	
	private String memberName;
	
	private String memebrMobile;
	
	private Long transferMemberId;
	
	private String transferMemberMobile;
	
	private String transferMemberName;
	
	private BigDecimal memberTreasureMoneyBf;
	
	private BigDecimal memberTreasureMoneyAf;
	
	private BigDecimal memberTreasureTradeMoney;
	
	private BigDecimal transferMemberMoneyBf;
	
	private BigDecimal transferMemberMoneyAf;
	
	private String tradeNo;
	
	private Date addTime;
	
	private String tradeStatus;
	
	private String tradeType;
	
	private BigDecimal realMoney;
	
	private BigDecimal taxMoney;

	private BigDecimal insuranceMoney;
	
	private BigDecimal conGrowth;
	
	private BigDecimal voucherMoney;

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

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemebrMobile() {
		return memebrMobile;
	}

	public void setMemebrMobile(String memebrMobile) {
		this.memebrMobile = memebrMobile;
	}

	public Long getTransferMemberId() {
		return transferMemberId;
	}

	public void setTransferMemberId(Long transferMemberId) {
		this.transferMemberId = transferMemberId;
	}

	public String getTransferMemberMobile() {
		return transferMemberMobile;
	}

	public void setTransferMemberMobile(String transferMemberMobile) {
		this.transferMemberMobile = transferMemberMobile;
	}

	public String getTransferMemberName() {
		return transferMemberName;
	}

	public void setTransferMemberName(String transferMemberName) {
		this.transferMemberName = transferMemberName;
	}

	public BigDecimal getMemberTreasureMoneyBf() {
		return memberTreasureMoneyBf;
	}

	public void setMemberTreasureMoneyBf(BigDecimal memberTreasureMoneyBf) {
		this.memberTreasureMoneyBf = memberTreasureMoneyBf;
	}

	public BigDecimal getMemberTreasureMoneyAf() {
		return memberTreasureMoneyAf;
	}

	public void setMemberTreasureMoneyAf(BigDecimal memberTreasureMoneyAf) {
		this.memberTreasureMoneyAf = memberTreasureMoneyAf;
	}

	public BigDecimal getMemberTreasureTradeMoney() {
		return memberTreasureTradeMoney;
	}

	public void setMemberTreasureTradeMoney(BigDecimal memberTreasureTradeMoney) {
		this.memberTreasureTradeMoney = memberTreasureTradeMoney;
	}

	public BigDecimal getTransferMemberMoneyBf() {
		return transferMemberMoneyBf;
	}

	public void setTransferMemberMoneyBf(BigDecimal transferMemberMoneyBf) {
		this.transferMemberMoneyBf = transferMemberMoneyBf;
	}

	public BigDecimal getTransferMemberMoneyAf() {
		return transferMemberMoneyAf;
	}

	public void setTransferMemberMoneyAf(BigDecimal transferMemberMoneyAf) {
		this.transferMemberMoneyAf = transferMemberMoneyAf;
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

	public BigDecimal getRealMoney() {
		return realMoney;
	}

	public void setRealMoney(BigDecimal realMoney) {
		this.realMoney = realMoney;
	}

	public BigDecimal getTaxMoney() {
		return taxMoney;
	}

	public void setTaxMoney(BigDecimal taxMoney) {
		this.taxMoney = taxMoney;
	}

	public BigDecimal getInsuranceMoney() {
		return insuranceMoney;
	}

	public void setInsuranceMoney(BigDecimal insuranceMoney) {
		this.insuranceMoney = insuranceMoney;
	}

	public BigDecimal getConGrowth() {
		return conGrowth;
	}

	public void setConGrowth(BigDecimal conGrowth) {
		this.conGrowth = conGrowth;
	}

	public BigDecimal getVoucherMoney() {
		return voucherMoney;
	}

	public void setVoucherMoney(BigDecimal voucherMoney) {
		this.voucherMoney = voucherMoney;
	}		
	
}
