package cc.messcat.gjfeng.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "gjf_jd_goods")
public class GjfJdGoods implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3835879706612456386L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_", length = 10)
	private Long id;
	
	@Column(name = "JD_GOOD_ID_", length = 50)
	private String jdGoodsId;
	
	@Column(name = "CAT_ID_", length = 50)
	private String catId;
	
	@Column(name = "ADD_TIME_")
	private Date addTime;

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

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	
	

}
