package cc.messcat.gjfeng.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "gjf_address_town")
public class GjfAddressTown implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9133431457517581209L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_", length = 10)
	private Long id;
	
	@Column(name = "TOWN_ID_", length = 10)
	private Long townId;
	
	@Column(name = "TOWN_NAME_", length = 20)
	private String townName;
	
	@Column(name = "FATHER_ID_", length = 10)
	private Long fatherId;
	
	@Column(name = "TOWN_SOUCE_", length = 1)
	private String townSouce;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTownId() {
		return townId;
	}

	public void setTownId(Long townId) {
		this.townId = townId;
	}

	public String getTownName() {
		return townName;
	}

	public void setTownName(String townName) {
		this.townName = townName;
	}

	public Long getFatherId() {
		return fatherId;
	}

	public void setFatherId(Long fatherId) {
		this.fatherId = fatherId;
	}

	public String getTownSouce() {
		return townSouce;
	}

	public void setTownSouce(String townSouce) {
		this.townSouce = townSouce;
	}
	
	

}
