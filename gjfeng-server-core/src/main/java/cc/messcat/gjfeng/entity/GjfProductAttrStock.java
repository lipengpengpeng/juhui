package cc.messcat.gjfeng.entity;

import java.math.BigDecimal;
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
 * GjfProductAttr entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "gjf_product_attr_stock")
public class GjfProductAttrStock implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_", length = 10)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="PRODUCT_ID_")
	private GjfProductInfo productId;
	
	@Column(name="PRODUCT_ATTR_IDS_",length=500)
	private String productAttrIds;
	
	@Column(name="REPERTORY_",length=10)
	private Long repertory;
	
	@Column(name="PRICE_",precision=10,scale=2)
	private BigDecimal price;
	
	@Column(name="ADD_TIME_")
	private Date addTime;
	
	@Column(name="EDIT_TIME_")
	private Date editTime;
	
	@Column(name="STATUS_",length=1)
	private String status;
	
	@Column(name="STANDARD_PRICE_",precision=10,scale=2)
	private BigDecimal standardPrice;
	
	@Column(name="HONOUR_PRICE_",precision=10,scale=2)
	private BigDecimal honourPrice;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public GjfProductInfo getProductId() {
		return productId;
	}

	public void setProductId(GjfProductInfo productId) {
		this.productId = productId;
	}

	public String getProductAttrIds() {
		return productAttrIds;
	}

	public void setProductAttrIds(String productAttrIds) {
		this.productAttrIds = productAttrIds;
	}

	public Long getRepertory() {
		return repertory;
	}

	public void setRepertory(Long repertory) {
		this.repertory = repertory;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
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

	public BigDecimal getStandardPrice() {
		return standardPrice;
	}

	public void setStandardPrice(BigDecimal standardPrice) {
		this.standardPrice = standardPrice;
	}

	public BigDecimal getHonourPrice() {
		return honourPrice;
	}

	public void setHonourPrice(BigDecimal honourPrice) {
		this.honourPrice = honourPrice;
	}
	
	
}