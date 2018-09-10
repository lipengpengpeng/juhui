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
@Table(name="gjf_member_treasure_trade")
public class GjfMemberTreasureTrade implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9149777815207902181L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_", length = 10)
	private Long id;
	
	@Column(name="MEMBER_ID_",length=10)
	private Long memberId;
	
	@Column(name="MEMBER_NAME_",length=200)
	private String memberName;
	
	@Column(name="MEMBER_MOBILE_",length=200)
	private String memebrMobile;
	
	@Column(name="TRANSFER_MEMBER_ID_",length=11)
	private Long transferMemberId;
	
	@Column(name="TRANSFER_MEMBER_MOBILE_",length=200)
	private String transferMemberMobile;
	
	@Column(name="TRANSFER_MEMBER_NAME_",length=200)
	private String transferMemberName;
	
	@Column(name="MEMBER_TREASURE_MONEY_BF_",precision=20,scale=2)
	private BigDecimal memberTreasureMoneyBf;
	
	@Column(name="MEMBER_TREASURE_MONEY_AF_",precision=20,scale=2)
	private BigDecimal memberTreasureMoneyAf;
	
	@Column(name="MEMBER_TREASURE_TRADE_MONEY_",precision=20,scale=2)
	private BigDecimal memberTreasureTradeMoney;
	
	@Column(name="TRANSFER_MEMBER_MONEY_BF_",precision=20,scale=2)
	private BigDecimal transferMemberMoneyBf;
	
	@Column(name="TRANSFER_MEMBER_MONEY_AF_",precision=20,scale=2)
	private BigDecimal transferMemberMoneyAf;
	
	@Column(name="TRADE_NO_",length=300)
	private String tradeNo;
	
	@Column(name="ADD_TIME_")
	private Date addTime;
	
	@Column(name="TRADE_STATUS_")
	private String tradeStatus;
	
	@Column(name="TRADE_TYPE_")
	private String tradeType;
	
	@Column(name="REAL_MONEY_",precision=20,scale=2)
	private BigDecimal realMoney;
	
	@Column(name="TAX_MONEY_",precision=20,scale=2)
	private BigDecimal taxMoney;
	
	@Column(name="INSURANCE_MONEY_",precision=20,scale=2)
	private BigDecimal insuranceMoney;
	
	@Column(name="CON_GROWTH_",precision=20,scale=2)
	private BigDecimal conGrowth;
	
	@Column(name="VOUCHERS_MONEY_",precision=20,scale=2)
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
