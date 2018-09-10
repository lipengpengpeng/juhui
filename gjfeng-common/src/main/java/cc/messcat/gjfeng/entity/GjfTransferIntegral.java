package cc.messcat.gjfeng.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class GjfTransferIntegral implements Serializable{

	private static final long serialVersionUID = 2927251436974037190L;

	private Long id;
	
	private Long memberId;
	
	private Long transferMemberId;
	
	private String memberName;
	
	private String memberMobile;
	
	private String transferMemberName;
	
	private String transferMemberMobile;
	
	private BigDecimal memberDataBf;
	
	private BigDecimal memberDataAf;
	
	private BigDecimal transferMemberDataBf;
	
	private BigDecimal transferMemberDataAf;
	
	private BigDecimal transferData;
	
	private BigDecimal transferPoundage;
	
	private String transferType;
	
	private String state;
	
	private Date addTime;

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
