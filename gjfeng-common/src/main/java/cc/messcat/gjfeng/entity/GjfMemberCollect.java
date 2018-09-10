package cc.messcat.gjfeng.entity;

import java.util.Date;


public class GjfMemberCollect implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2443253723200175498L;
		
	private Long id;

	private GjfMemberInfo memberId;

	private GjfProductInfo goodsId;

	private GjfStoreInfo storeId;
	
	private Date addTime;
	
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
