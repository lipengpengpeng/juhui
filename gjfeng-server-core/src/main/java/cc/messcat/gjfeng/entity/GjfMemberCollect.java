package cc.messcat.gjfeng.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "gjf_member_collect")
public class GjfMemberCollect implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2443253723200175498L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_", length = 10)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="MEMBER_ID_")
	private GjfMemberInfo memberId;
	
	@ManyToOne
	@JoinColumn(name="GOODS_ID_")
	private GjfProductInfo goodsId;
	
	@ManyToOne
	@JoinColumn(name = "STORE_ID_")
	private GjfStoreInfo storeId;
	
	@Column(name = "ADD_TIME_")
	private Date addTime;
	
	@Column(name = "COLLECT_TYPE_", length = 1)
	private String collectType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public GjfMemberInfo getMemberId() {
		return memberId;
	}

	public void setMemberId(GjfMemberInfo memberId) {
		this.memberId = memberId;
	}

	public GjfProductInfo getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(GjfProductInfo goodsId) {
		this.goodsId = goodsId;
	}

	public GjfStoreInfo getStoreId() {
		return storeId;
	}

	public void setStoreId(GjfStoreInfo storeId) {
		this.storeId = storeId;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getCollectType() {
		return collectType;
	}

	public void setCollectType(String collectType) {
		this.collectType = collectType;
	}

}
