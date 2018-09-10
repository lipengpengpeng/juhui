package cc.messcat.gjfeng.entity;

/**
 * 省份地址实体
 * @author Karhs
 * @date 2017-01-03 12:01
 * @version 1.0
 */
public class GjfAddressProvince implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields
	private Long id;
	
	private Long provinceId;
	
	private String province;
	
	private String letter;
		
	public String getProvinceSource() {
		return provinceSource;
	}

	public void setProvinceSource(String provinceSource) {
		this.provinceSource = provinceSource;
	}

	private String provinceSource;

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProvinceId() {
		return this.provinceId;
	}

	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getLetter() {
		return letter;
	}

	public void setLetter(String letter) {
		this.letter = letter;
	}

}