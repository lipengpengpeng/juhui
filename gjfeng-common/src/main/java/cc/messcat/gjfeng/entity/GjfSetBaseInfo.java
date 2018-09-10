package cc.messcat.gjfeng.entity;

import java.io.Serializable;


public class GjfSetBaseInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1724703916324986246L;

	private Long id;
	
	private String key;
	
	private String value;
	
	private String describe;
	
	private String status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
