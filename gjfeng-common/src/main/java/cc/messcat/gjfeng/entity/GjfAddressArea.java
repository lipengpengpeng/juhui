package cc.messcat.gjfeng.entity;

/**
 * 区县地址实体
 * @author Karhs
 * @date 2017-01-03 12:01
 * @version 1.0
 */
public class GjfAddressArea implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	private Long id;
	
	private Long areaId;
	
	private String area;
	
	private Long fatherId;
	
	private String letter;

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAreaId() {
		return this.areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Long getFatherId() {
		return this.fatherId;
	}

	public void setFatherId(Long fatherId) {
		this.fatherId = fatherId;
	}

	public String getLetter() {
		return letter;
	}

	public void setLetter(String letter) {
		this.letter = letter;
	}

}