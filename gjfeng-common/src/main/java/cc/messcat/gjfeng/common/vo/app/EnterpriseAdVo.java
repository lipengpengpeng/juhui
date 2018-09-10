package cc.messcat.gjfeng.common.vo.app;

import java.io.Serializable;
import java.math.BigDecimal;

public class EnterpriseAdVo implements Serializable {

	private static final long serialVersionUID = 1L;

	// Fields
	private Long id;
	
	private String frontNum;
	
	private BigDecimal orderColumn;

	private String names;
	
	private String photo;
	
	private String address;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFrontNum() {
		return frontNum;
	}

	public void setFrontNum(String frontNum) {
		this.frontNum = frontNum;
	}

	public BigDecimal getOrderColumn() {
		return orderColumn;
	}

	public void setOrderColumn(BigDecimal orderColumn) {
		this.orderColumn = orderColumn;
	}

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	

}