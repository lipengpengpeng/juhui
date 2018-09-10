package cc.messcat.gjfeng.entity;

import java.util.Date;

/**
 * GjfBankInfo entity. @author MyEclipse Persistence Tools
 */

public class GjfBankInfo implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	private Long id;
	
	private String bankCode;
	
	private String bankName;
	
	private String bankPic;
	
	private String bankUrl;
	
	private String bankColor;
	
	private Long orderBy;
	
	private Date addTime;
	
	private String status;

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBankCode() {
		return this.bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankName() {
		return this.bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankPic() {
		return this.bankPic;
	}

	public void setBankPic(String bankPic) {
		this.bankPic = bankPic;
	}

	public String getBankUrl() {
		return this.bankUrl;
	}

	public void setBankUrl(String bankUrl) {
		this.bankUrl = bankUrl;
	}

	public Long getOrderBy() {
		return this.orderBy;
	}

	public void setOrderBy(Long orderBy) {
		this.orderBy = orderBy;
	}

	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBankColor() {
		return bankColor;
	}

	public void setBankColor(String bankColor) {
		this.bankColor = bankColor;
	}

}