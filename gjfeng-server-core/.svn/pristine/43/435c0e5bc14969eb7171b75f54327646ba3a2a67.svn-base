package cc.messcat.gjfeng.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 省份地址实体
 * @author Karhs
 * @date 2017-01-03 12:01
 * @version 1.0
 */
@Entity
@Table(name = "gjf_address_province")
public class GjfAddressProvince implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_", length = 10)
	private Long id;
	
	@Column(name = "PROVINCE_ID_", length = 10)
	private Long provinceId;
	
	@Column(name = "PROVINCE_", length = 20)
	private String province;
	
	@Column(name = "INITAL_", length = 1)
	private String letter;
	
	@Column(name = "PROVINCE_SOURCE_", length = 1)
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

	public String getProvinceSource() {
		return provinceSource;
	}

	public void setProvinceSource(String provinceSource) {
		this.provinceSource = provinceSource;
	}

	
}