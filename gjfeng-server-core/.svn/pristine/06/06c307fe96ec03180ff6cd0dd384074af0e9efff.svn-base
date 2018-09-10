package cc.messcat.gjfeng.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * GjfAttrType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "gjf_attr_type")
public class GjfAttrType implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_", length = 10)
	private Long id;
	
	@Column(name = "ATTR_NAME_", length = 100)
	private String attrName;
	
	@Column(name = "SORT_ORDER_", length = 10)
	private Long sortOrder;
	
	@Column(name = "ADD_TIME_")
	private Date addTime;
	
	@Column(name = "EDIT_TIME_")
	private Date editTime;
	
	@Column(name = "STATUS_", length = 1)
	private String status;
	
	@Column(name = "TOKEN_", length = 200)
	private String token;

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAttrName() {
		return this.attrName;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	public Long getSortOrder() {
		return this.sortOrder;
	}

	public void setSortOrder(Long sortOrder) {
		this.sortOrder = sortOrder;
	}

	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Date getEditTime() {
		return this.editTime;
	}

	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}