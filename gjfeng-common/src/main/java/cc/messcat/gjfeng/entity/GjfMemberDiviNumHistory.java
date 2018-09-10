package cc.messcat.gjfeng.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


public class GjfMemberDiviNumHistory implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 7641256206313509359L;

	private Long id;
	
	private Long memberId;
	
	private BigDecimal dedcutAmount;
	
	private BigDecimal dedcutDiviTotalMoneyBf;
	
	private BigDecimal dedectDiviTotalMoneyAf;
	
	private BigDecimal memberDiviNumBf;
	
	private BigDecimal memberDiviNumAf;
	
	private BigDecimal deductDiviNum;
	
	private String tradeStatus;
	
	private String tradeType;
	
	private String memberName;
	
	private String memberMobile;
	
	private Date addTime;

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

	public BigDecimal getDedcutAmount() {
		return dedcutAmount;
	}

	public void setDedcutAmount(BigDecimal dedcutAmount) {
		this.dedcutAmount = dedcutAmount;
	}

	public BigDecimal getDedcutDiviTotalMoneyBf() {
		return dedcutDiviTotalMoneyBf;
	}

	public void setDedcutDiviTotalMoneyBf(BigDecimal dedcutDiviTotalMoneyBf) {
		this.dedcutDiviTotalMoneyBf = dedcutDiviTotalMoneyBf;
	}

	public BigDecimal getDedectDiviTotalMoneyAf() {
		return dedectDiviTotalMoneyAf;
	}

	public void setDedectDiviTotalMoneyAf(BigDecimal dedectDiviTotalMoneyAf) {
		this.dedectDiviTotalMoneyAf = dedectDiviTotalMoneyAf;
	}

	public BigDecimal getMemberDiviNumBf() {
		return memberDiviNumBf;
	}

	public void setMemberDiviNumBf(BigDecimal memberDiviNumBf) {
		this.memberDiviNumBf = memberDiviNumBf;
	}

	public BigDecimal getMemberDiviNumAf() {
		return memberDiviNumAf;
	}

	public void setMemberDiviNumAf(BigDecimal memberDiviNumAf) {
		this.memberDiviNumAf = memberDiviNumAf;
	}

	public BigDecimal getDeductDiviNum() {
		return deductDiviNum;
	}

	public void setDeductDiviNum(BigDecimal deductDiviNum) {
		this.deductDiviNum = deductDiviNum;
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

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberMobile() {
		return memberMobile;
	}

	public void setMemberMobile(String memberMobile) {
		this.memberMobile = memberMobile;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	
	

}
