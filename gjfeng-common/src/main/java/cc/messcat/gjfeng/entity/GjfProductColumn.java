package cc.messcat.gjfeng.entity;

/**
 * GjfProductColumn entity. @author MyEclipse Persistence Tools
 */
public class GjfProductColumn implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields
	private Long id;
	
	private GjfProductInfo productId;
	
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