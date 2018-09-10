package cc.messcat.gjfeng.entity;

import java.math.BigDecimal;

/**
 * GjfOrderGoods entity. @author MyEclipse Persistence Tools
 */
public class GjfOrderGoods implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields
	private Long id;
	
	private GjfOrderInfo orderId;
	
	private GjfProductInfo goodsId;
	
	private String goodsAttr;
	
	private Long goodsAttrStockId;
	
	private String goodsName;
	
	private BigDecimal goodsAmount;
	
	private BigDecimal goodsPayAmount;
	
	private Long goodsNum;
	
	private String goodsImageUrl;
	
	private String goodsType;
	
	private Long promotionsId;
	
	private BigDecimal storeRecAmount;
	
	private BigDecimal storeBenefitAmount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public GjfOrderInfo getOrderId() {
		return orderId;
	}

	public void setOrderId(GjfOrderInfo orderId) {
		this.orderId = orderId;
	}

	public GjfProductInfo getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(GjfProductInfo goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public BigDecimal getGoodsAmount() {
		return goodsAmount;
	}

	public void setGoodsAmount(BigDecimal goodsAmount) {
		this.goodsAmount = goodsAmount;
	}

	public BigDecimal getGoodsPayAmount() {
		return goodsPayAmount;
	}

	public void setGoodsPayAmount(BigDecimal goodsPayAmount) {
		this.goodsPayAmount = goodsPayAmount;
	}

	public Long getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(Long goodsNum) {
		this.goodsNum = goodsNum;
	}

	public String getGoodsImageUrl() {
		return goodsImageUrl;
	}

	public void setGoodsImageUrl(String goodsImageUrl) {
		this.goodsImageUrl = goodsImageUrl;
	}

	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	public Long getPromotionsId() {
		return promotionsId;
	}

	public void setPromotionsId(Long promotionsId) {
		this.promotionsId = promotionsId;
	}

	public BigDecimal getStoreRecAmount() {
		return storeRecAmount;
	}

	public void setStoreRecAmount(BigDecimal storeRecAmount) {
		this.storeRecAmount = storeRecAmount;
	}

	public BigDecimal getStoreBenefitAmount() {
		return storeBenefitAmount;
	}

	public void setStoreBenefitAmount(BigDecimal storeBenefitAmount) {
		this.storeBenefitAmount = storeBenefitAmount;
	}

	public String getGoodsAttr() {
		return goodsAttr;
	}

	public void setGoodsAttr(String goodsAttr) {
		this.goodsAttr = goodsAttr;
	}

	public Long getGoodsAttrStockId() {
		return goodsAttrStockId;
	}

	public void setGoodsAttrStockId(Long goodsAttrStockId) {
		this.goodsAttrStockId = goodsAttrStockId;
	}
}