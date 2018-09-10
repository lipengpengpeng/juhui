package cc.messcat.gjfeng.entity;

/**
 * 城市地址实体
 * @author Karhs
 * @date 2017-01-03 12:01
 * @version 1.0
 */
public class GjfAddressCity implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private Long id;
	
	private Long cityId;
	
	private String city;
	
	private Long fatherId;
	
	private String letter;
	
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