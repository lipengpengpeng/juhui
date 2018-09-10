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

/**
 * GjfCartInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "gjf_cart_info")
public class GjfCartInfo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields
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
	@JoinColumn(name="GOODS_ATTR_STOCK_ID_")
	private GjfProductAttrStock goodsAttrStockId;
	
	@Column(name="GOODS_ATTR_")
	private String goodsAttr;
	
	@Column(name = "GOODS_NUM_", length = 10)
	private Long goodsNum;
	
	@Column(name = "ADD_TIME_")
	private Date addTime;
	
	@Column(name = "LOGIST_", length = 1)
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