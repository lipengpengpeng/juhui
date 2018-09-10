package cc.messcat.gjfeng.entity;

import java.io.Serializable;
import java.util.Date;


public class GjfAppUpdateInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6203740937972403290L;

	private Long id;
	
	private String version;
	
	private String jumpUrl;
	
	private String describe;
	
	private String type;
	
	private Date addTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getJumpUrl() {
		return jumpUrl;
	}

	public void setJumpUrl(String jumpUrl) {
		this.jumpUrl = jumpUrl;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	
	
}
