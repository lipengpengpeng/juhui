package cc.messcat.gjfeng.entity;

import java.io.Serializable;
import java.util.Date;



public class GjfSupplierInfo implements Serializable{
 
	/**
	 * 
	 */
	private static final long serialVersionUID = -2683745110542375114L;

	private Long id;
	
	private String supplierMobile;

	private String supplierName;
	
	private String supplierStoreName;
	
	private String status;
	
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
