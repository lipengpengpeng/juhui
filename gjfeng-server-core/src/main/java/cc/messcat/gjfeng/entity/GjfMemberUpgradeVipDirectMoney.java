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
@Table(name = "gjf_member_upgrade_vip_direct_money")
public class GjfMemberUpgradeVipDirectMoney implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 833663057225226200L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_", length = 10)
	private Long id;
	
	@Column(name = "MEMBER_ID_", length = 10)
	private Long memberId;
	
	@Column(name = "MEMBER_NAME_", length = 100)
	private String memberName;
	
	@Column(name = "MEMBER_MOBILE_", length = 50)
	private String memberMobile;
	
	@Column(name = "MEMBER_COU_", precision=20, scale=2)
	private BigDecimal memberCou;
	
	@Column(name = "DIRECT_MONEY_", precision=20, scale=2)
	private BigDecimal directMoney;
	
	@Column(name = "DIRECT_MEMBER_ID_", length=10)
	private Long directMemberId;
	
	@Column(name = "FIRST_DIRECT_MONEY_", precision=20, scale=2)
	private BigDecimal firstDirectMoney;
	
	@Column(name = "FIRST_DIRECT_MEMBER_", length=10)
	private Long firstDirectMember;
	
	@Column(name = "STATUS_", length=1)
	private String status;
	
	@Column(name = "ADD_TIME_")
	private Date addTime;
	
	@Column(name = "TRADE_TYPE_", length=1)
	private String tradeType;
	
	@Column(name = "MERCHANT_MONEY_", precision=20, scale=2)
	private BigDecimal merchantMoney;
	
	@Column(name = "FIRST_MERCHANT_MONEY_", precision=20, scale=2)
	private BigDecimal firstMerchantMoney;
	
	@Column(name = "DIRECT_POINT_", precision=20, scale=2)
	private BigDecimal directPoint;
	
	@Column(name = "TRADE_NO_", length=200)
	private String tradeNo;
	
	@Column(name = "UPGRADE_TYPE_", length=1)
	private String upgradeType;
	
	@Column(name = "DIRECT_MEMBER_NAME_", length=200)
	private String directMemberName;

	@Column(name = "DIRECT_MEMBER_MOBILE_", length=200)
	private String directMemberMobile;
	
	@Column(name = "DIRECT_MEMBER_UPGRADE_TYPE_", length=1)
	private String directMemberUpgradeType;
	
	@Column(name = "BLOCK_NUMBER_", length=1)
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
