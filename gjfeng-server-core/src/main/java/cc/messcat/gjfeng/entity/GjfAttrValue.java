package cc.messcat.gjfeng.entity;

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
 * GjfAttrValue entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "gjf_attr_value")
public class GjfAttrValue implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_", length = 10)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="ATTR_ID_")
	private GjfAttrType attrId;
	
	@Column(name = "COLUMN_ID_", length = 10)
	private Long columnId;
	
	@Column(name = "ATTR_VALUE_", length = 100)
	private String attrValue;
	
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public GjfAttrType getAttrId() {
		return attrId;
	}

	public void setAttrId(GjfAttrType attrId) {
		this.attrId = attrId;
	}

	public String getAttrValue() {
		return attrValue;
	}

	public void setAttrValue(String attrValue) {
		this.attrValue = attrValue;
	}

	public Long getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Long sortOrder) {
		this.sortOrder = sortOrder;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Date getEditTime() {
		return editTime;
	}

	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getColumnId() {
		return columnId;
	}

	public void setColumnId(Long columnId) {
		this.columnId = columnId;
	}
}