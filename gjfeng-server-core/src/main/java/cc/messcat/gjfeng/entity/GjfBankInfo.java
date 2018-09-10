package cc.messcat.gjfeng.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * GjfBankInfo entity. @author MyEclipse Persistence Tools
 */

@Entity
@Table(name = "gjf_bank_info")
public class GjfBankInfo implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_", length = 10)
	private Long id;
	
	@Column(name = "BANK_CODE_", length = 10)
	private String bankCode;
	
	@Column(name = "BANK_NAME_", length = 20)
	private String bankName;
	
	@Column(name = "BANK_PIC_", length = 100)
	private String bankPic;
	
	@Column(name = "BANK_URL_", length = 100)
	private String bankUrl;
	
	@Column(name = "BANK_COLOR_", length = 10)
	private String bankColor;
	
	@Column(name = "ORDER_BY_", length = 10)
	private Long orderBy;
	
	@Column(name = "ADD_TIME_")
	private Date addTime;
	
	@Column(name = "STATUS_", length = 1)
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