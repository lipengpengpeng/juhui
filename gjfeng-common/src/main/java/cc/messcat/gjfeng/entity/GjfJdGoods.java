package cc.messcat.gjfeng.entity;

import java.io.Serializable;
import java.util.Date;


public class GjfJdGoods implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3835879706612456386L;

	
	private Long id;
	
	
	private String jdGoodsId;
	

	private String catId;
	
	private Date addTime;

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getJdGoodsId() {
		return jdGoodsId;
	}

	public void setJdGoodsId(String jdGoodsId) {
		this.jdGoodsId = jdGoodsId;
	}

	public String getCatId() {
		return catId;
	}

	public void setCatId(String catId) {
		this.catId = catId;
	}
	
	

}
