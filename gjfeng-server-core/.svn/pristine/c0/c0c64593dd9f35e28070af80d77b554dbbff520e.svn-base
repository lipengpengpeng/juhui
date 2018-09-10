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
 * GjfProductColumn entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "gjf_product_column")
public class GjfProductColumn implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_", length = 10)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="PRODUCT_ID_")
	private GjfProductInfo productId;
	
	@ManyToOne
	@JoinColumn(name="COLUMN_ID_")
	private GjfEnterpriseColumn columnId;
	
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

	public GjfEnterpriseColumn getColumnId() {
		return columnId;
	}

	public void setColumnId(GjfEnterpriseColumn columnId) {
		this.columnId = columnId;
	}
}