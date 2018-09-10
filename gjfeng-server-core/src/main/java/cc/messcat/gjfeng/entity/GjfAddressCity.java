package cc.messcat.gjfeng.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 城市地址实体
 * @author Karhs
 * @date 2017-01-03 12:01
 * @version 1.0
 */
@Entity
@Table(name = "gjf_address_city")
public class GjfAddressCity implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_", length = 10)
	private Long id;
	
	@Column(name = "CITY_ID_", length = 10)
	private Long cityId;
	
	@Column(name = "CITY_", length = 20)
	private String city;
	
	@Column(name = "FATHER_ID_", length = 10)
	private Long fatherId;
	
	@Column(name = "INITAL_", length = 1)
	private String letter;
	
	@Column(name = "CITY_SOUCE_", length = 1)
	private String citySouce;

	// Property accessors

	public String getCitySouce() {
		return citySouce;
	}

	public void setCitySouce(String citySouce) {
		this.citySouce = citySouce;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCityId() {
		return this.cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
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