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
@Table(name="gjf_transfer_integral")
public class GjfTransferIntegral implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2927251436974037190L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_",length=11)
	private Long id;
	
	@Column(name="MEMBER_ID_",length=11)
	private Long memberId;
	
	@Column(name="TRANSFER_MEMBER_ID_",length=11)
	private Long transferMemberId;
	
	@Column(name="MEMBER_NAME_",length=300)
	private String memberName;
	
	@Column(name="MEMBER_MOBILE_",length=50)
	private String memberMobile;
	
	@Column(name="TRANSFER_MEMBER_NAME_",length=300)
	private String transferMemberName;
	
	@Column(name="TRANSFER_MEMBER_MOBILE_",length=50)
	private String transferMemberMobile;
	
	@Column(name="MEMBER_DATA_BF_",precision=20,scale=2)
	private BigDecimal memberDataBf;
	
	@Column(name="MEMBER_DATA_AF_",precision=20,scale=2)
	private BigDecimal memberDataAf;
	
	@Column(name="TRANSFER_MEMBER_DATA_BF_",precision=20,scale=2)
	private BigDecimal transferMemberDataBf;
	
	@Column(name="TRANSFER_MEMBER_DATA_AF_",precision=20,scale=2)
	private BigDecimal transferMemberDataAf;
	
	@Column(name="TRANSFER_DATA_",precision=20,scale=2)
	private BigDecimal transferData;
	
	@Column(name="TRANSFER_POUNDAGE_",precision=20,scale=2)
	private BigDecimal transferPoundage;
	
	@Column(name="TRANSFER_TYPE_",length=1)
	private String transferType;
	
	@Column(name="STATE_",length=1)
	private String state;
	
	@Column(name="ADD_TIME_",length=1)
	private Date addTime;
	
	@Column(name="ACTUAL_TRANSFER_MONEY_",precision=20,scale=2)
	private BigDecimal actualTransferMoney;

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

	public Long getTransferMemberId() {
		return transferMemberId;
	}

	public void setTransferMemberId(Long transferMemberId) {
		this.transferMemberId = transferMemberId;
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

	public String getTransferMemberName() {
		return transferMemberName;
	}

	public void setTransferMemberName(String transferMemberName) {
		this.transferMemberName = transferMemberName;
	}

	public String getTransferMemberMobile() {
		return transferMemberMobile;
	}

	public void setTransferMemberMobile(String transferMemberMobile) {
		this.transferMemberMobile = transferMemberMobile;
	}

	public BigDecimal getMemberDataBf() {
		return memberDataBf;
	}

	public void setMemberDataBf(BigDecimal memberDataBf) {
		this.memberDataBf = memberDataBf;
	}

	public BigDecimal getMemberDataAf() {
		return memberDataAf;
	}

	public void setMemberDataAf(BigDecimal memberDataAf) {
		this.memberDataAf = memberDataAf;
	}

	public BigDecimal getTransferMemberDataBf() {
		return transferMemberDataBf;
	}

	public void setTransferMemberDataBf(BigDecimal transferMemberDataBf) {
		this.transferMemberDataBf = transferMemberDataBf;
	}

	public BigDecimal getTransferMemberDataAf() {
		return transferMemberDataAf;
	}

	public void setTransferMemberDataAf(BigDecimal transferMemberDataAf) {
		this.transferMemberDataAf = transferMemberDataAf;
	}

	public BigDecimal getTransferData() {
		return transferData;
	}

	public void setTransferData(BigDecimal transferData) {
		this.transferData = transferData;
	}

	public BigDecimal getTransferPoundage() {
		return transferPoundage;
	}

	public void setTransferPoundage(BigDecimal transferPoundage) {
		this.transferPoundage = transferPoundage;
	}

	public String getTransferType() {
		return transferType;
	}

	public void setTransferType(String transferType) {
		this.transferType = transferType;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public BigDecimal getActualTransferMoney() {
		return actualTransferMoney;
	}

	public void setActualTransferMoney(BigDecimal actualTransferMoney) {
		this.actualTransferMoney = actualTransferMoney;
	}
	
	

}
