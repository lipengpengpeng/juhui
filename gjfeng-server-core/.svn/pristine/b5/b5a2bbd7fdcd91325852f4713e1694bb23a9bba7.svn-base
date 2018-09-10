package cc.messcat.gjfeng.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * GjfOrderGoods entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "gjf_order_goods")
public class GjfOrderGoods implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_", length = 10)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="ORDER_ID_")
	private GjfOrderInfo orderId;
	
	@ManyToOne
	@JoinColumn(name="GOODS_ID_")
	private GjfProductInfo goodsId;
	
	@Column(name="GOODS_ATTR_")
	private String goodsAttr;
	
	@Column(name="GOODS_ATTR_STOCK_ID_")
	private Long goodsAttrStockId;
	
	@Column(name = "GOODS_NAME_", length = 50)
	private String goodsName;
	
	@Column(name = "GOODS_AMOUNT_", precision=10, scale=2)
	private BigDecimal goodsAmount;
	
	@Column(name = "GOODS_PAY_AMOUNT_", precision=10, scale=2)
	private BigDecimal goodsPayAmount;
	
	@Column(name = "GOODS_NUM_", length = 10)
	private Long goodsNum;
	
	@Column(name = "GOODS_IMAGE_URL_", length = 100)
	private String goodsImageUrl;
	
	@Column(name = "GOODS_TYPE_", length = 1)
	private String goodsType;
	
	@Column(name = "PROMOTIONS_ID_", length = 10)
	private Long promotionsId;
	
	@Column(name = "STORE_REC_AMOUNT_", precision=10, scale=2)
	private BigDecimal storeRecAmount;
	
	@Column(name = "STORE_BENEFIT_AMOUNT_", precision=10, scale=2)
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