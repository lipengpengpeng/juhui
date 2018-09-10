/*
 * Copyright (c) 2015 Messcat. All rights reserved.
 */
package cc.messcat.gjfeng.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * 用户绑定银行卡记录实体
 * @author Karhs
 * @date 2017-01-03 12:01
 * @version 1.0
 */
@Entity
@Table(name = "gjf_member_bank")
public class GjfMemberBank implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_", length = 10)
	private Long id;

	@ManyToOne
	@JoinColumn(name="MEMBER_ID_", nullable=false)
	private GjfMemberInfo memberId;

	@ManyToOne
	@JoinColumn(name="BANK_ID_", nullable=false)
	private GjfBankInfo bankId;
	
	@Column(name = "BANK_SUB_", length = 100)
	private String bankSub;
	
	@Column(name = "BANK_CARD_", length = 50)
	private String bankCard;
	
	@Column(name = "HOLDER_", length = 50)
	private String holder;
	
	@ManyToOne
	@JoinColumn(name="BANK_PROVINCE_ID_", nullable=false)
	private GjfAddressProvince bankProvinceId;
	
	@ManyToOne
	@JoinColumn(name="BANK_CITY_ID_")
	private GjfAddressCity bankCityId;
	
	@Column(name = "ADD_TIME_")
	private Date addTime;
	
	@Column(name = "STATUS_", length = 1)
	private String status;

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

	public GjfBankInfo getBankId() {
		return bankId;
	}

	public void setBankId(GjfBankInfo bankId) {
		this.bankId = bankId;
	}

	public String getBankSub() {
		return bankSub;
	}

	public void setBankSub(String bankSub) {
		this.bankSub = bankSub;
	}

	public String getBankCard() {
		return bankCard;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

	public String getHolder() {
		return holder;
	}

	public void setHolder(String holder) {
		this.holder = holder;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public GjfAddressProvince getBankProvinceId() {
		return bankProvinceId;
	}

	public void setBankProvinceId(GjfAddressProvince bankProvinceId) {
		this.bankProvinceId = bankProvinceId;
	}

	public GjfAddressCity getBankCityId() {
		return bankCityId;
	}

	public void setBankCityId(GjfAddressCity bankCityId) {
		this.bankCityId = bankCityId;
	}

}
