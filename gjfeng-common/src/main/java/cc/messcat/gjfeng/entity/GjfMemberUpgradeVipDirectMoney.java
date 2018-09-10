package cc.messcat.gjfeng.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class GjfMemberUpgradeVipDirectMoney implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -4365343855833994089L;

	private Long id;
		
	private Long memberId;

	private String memberName;
	
	private String memberMobile;
	
	private BigDecimal memberCou;
	
	private BigDecimal directMoney;
	
	private Long directMemberId;
	
	private BigDecimal firstDirectMoney;
	
	private Long firstDirectMember;
	
	private String status;
	
	private Date addTime;
	
	private String tradeType;
	
	private BigDecimal merchantMoney;
	
	private BigDecimal firstMerchantMoney;
	
	private BigDecimal directPoint;
	
	private String tradeNo;
	
	private String upgradeType;

	private String directMemberName;

	private String directMemberMobile;
	
	private String directMemberUpgradeType;
	
	private String blockNumber;

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

	public String getMemberMobile() {
		return memberMobile;
	}

	public void setMemberMobile(String memberMobile) {
		this.memberMobile = memberMobile;
	}

	public BigDecimal getMemberCou() {
		return memberCou;
	}

	public void setMemberCou(BigDecimal memberCou) {
		this.memberCou = memberCou;
	}

	public BigDecimal getDirectMoney() {
		return directMoney;
	}

	public void setDirectMoney(BigDecimal directMoney) {
		this.directMoney = directMoney;
	}

	public Long getDirectMemberId() {
		return directMemberId;
	}

	public void setDirectMemberId(Long directMemberId) {
		this.directMemberId = directMemberId;
	}

	public BigDecimal getFirstDirectMoney() {
		return firstDirectMoney;
	}

	public void setFirstDirectMoney(BigDecimal firstDirectMoney) {
		this.firstDirectMoney = firstDirectMoney;
	}

	
	public Long getFirstDirectMember() {
		return firstDirectMember;
	}

	public void setFirstDirectMember(Long firstDirectMember) {
		this.firstDirectMember = firstDirectMember;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public BigDecimal getMerchantMoney() {
		return merchantMoney;
	}

	public void setMerchantMoney(BigDecimal merchantMoney) {
		this.merchantMoney = merchantMoney;
	}

	public BigDecimal getFirstMerchantMoney() {
		return firstMerchantMoney;
	}

	public void setFirstMerchantMoney(BigDecimal firstMerchantMoney) {
		this.firstMerchantMoney = firstMerchantMoney;
	}

	public BigDecimal getDirectPoint() {
		return directPoint;
	}

	public void setDirectPoint(BigDecimal directPoint) {
		this.directPoint = directPoint;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getUpgradeType() {
		return upgradeType;
	}

	public void setUpgradeType(String upgradeType) {
		this.upgradeType = upgradeType;
	}

	public String getDirectMemberName() {
		return directMemberName;
	}

	public void setDirectMemberName(String directMemberName) {
		this.directMemberName = directMemberName;
	}

	public String getDirectMemberMobile() {
		return directMemberMobile;
	}

	public void setDirectMemberMobile(String directMemberMobile) {
		this.directMemberMobile = directMemberMobile;
	}

	public String getDirectMemberUpgradeType() {
		return directMemberUpgradeType;
	}

	public void setDirectMemberUpgradeType(String directMemberUpgradeType) {
		this.directMemberUpgradeType = directMemberUpgradeType;
	}

	public String getBlockNumber() {
		return blockNumber;
	}

	public void setBlockNumber(String blockNumber) {
		this.blockNumber = blockNumber;
	}
	
	

}
