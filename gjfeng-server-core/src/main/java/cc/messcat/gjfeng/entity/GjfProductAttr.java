package cc.messcat.gjfeng.entity;


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
@Table(name = "gjf_product_attr")
public class GjfProductAttr implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_", length = 10)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="PRODUCT_ID_")
	private GjfProductInfo productId;
	
	@ManyToOne
	@JoinColumn(name="ATTR_VALUE_ID_")
	private GjfAttrValue attrValueId;
	
	@Column(name = "ORDER_BY_", length = 10)
	private Long orderBy;

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

	public GjfAttrValue getAttrValueId() {
		return attrValueId;
	}

	public void setAttrValueId(GjfAttrValue attrValueId) {
		this.attrValueId = attrValueId;
	}

	public Long getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Long orderBy) {
		this.orderBy = orderBy;
	}
}