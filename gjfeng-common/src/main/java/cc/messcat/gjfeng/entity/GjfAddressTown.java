package cc.messcat.gjfeng.entity;

import java.io.Serializable;


public class GjfAddressTown implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9133431457517581209L;
	
	
	private Long id;
	
	
	private Long townId;
	

	private String townName;
	
	
	private Long fatherId;
	

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
