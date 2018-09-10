package cc.messcat.gjfeng.entity;

import java.util.Date;

/**
 * GjfCartInfo entity. @author MyEclipse Persistence Tools
 */
public class GjfCartInfo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields
	private Long id;
	private GjfMemberInfo memberId;
	private GjfProductInfo goodsId;
	private GjfProductAttrStock goodsAttrStockId;
	private String goodsAttr;
	private Long goodsNum;
	private Date addTime;
	private String logist;

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

	public Long getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(Long goodsNum) {
		this.goodsNum = goodsNum;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getGoodsAttr() {
		return goodsAttr;
	}

	public void setGoodsAttr(String goodsAttr) {
		this.goodsAttr = goodsAttr;
	}

	public GjfProductAttrStock getGoodsAttrStockId() {
		return goodsAttrStockId;
	}

	public void setGoodsAttrStockId(GjfProductAttrStock goodsAttrStockId) {
		this.goodsAttrStockId = goodsAttrStockId;
	}

	public String getLogist() {
		return logist;
	}

	public void setLogist(String logist) {
		this.logist = logist;
	}
	

}