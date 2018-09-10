package cc.messcat.gjfeng.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class GjfSupplierInfo implements Serializable{
 
	/**
	 * 
	 */
	private static final long serialVersionUID = -2683745110542375114L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_", length = 10)
	private Long id;
	
	@Column(name = "SUPPLIER_MOBILE_", length = 50)
	private String supplierMobile;
	
	@Column(name = "SUPPLIER_NAME_", length = 200)
	private String supplierName;
	
	@Column(name = "SUPPLIER_STORE_NAME_", length = 200)
	private String supplierStoreName;
	
	@Column(name = "STATUS_", length = 1)
	private String status;
	
	@Column(name = "ADD_TIME_")
	private Date addTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSupplierMobile() {
		return supplierMobile;
	}

	public void setSupplierMobile(String supplierMobile) {
		this.supplierMobile = supplierMobile;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSupplierStoreName() {
		return supplierStoreName;
	}

	public void setSupplierStoreName(String supplierStoreName) {
		this.supplierStoreName = supplierStoreName;
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
	
	
}
