package cc.messcat.gjfeng.common.vo.app;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrderGoodsVo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long goodsId;
	
	private String goodsName;
	
	private String goodsImg;
	
	private BigDecimal goodsAmount;
	
	private String goodsAttr;
	
	private Long goodsNum;
	
	private BigDecimal standardPrice;
	
	private BigDecimal honourPrice;

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsImg() {
		return goodsImg;
	}

	public void setGoodsImg(String goodsImg) {
		this.goodsImg = goodsImg;
	}

	public BigDecimal getGoodsAmount() {
		return goodsAmount;
	}

	public void setGoodsAmount(BigDecimal goodsAmount) {
		this.goodsAmount = goodsAmount;
	}

	public String getGoodsAttr() {
		return goodsAttr;
	}

	public void setGoodsAttr(String goodsAttr) {
		this.goodsAttr = goodsAttr;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Long getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(Long goodsNum) {
		this.goodsNum = goodsNum;
	}

	public BigDecimal getStandardPrice() {
		return standardPrice;
	}

	public void setStandardPrice(BigDecimal standardPrice) {
		this.standardPrice = standardPrice;
	}

	public BigDecimal getHonourPrice() {
		return honourPrice;
	}

	public void setHonourPrice(BigDecimal honourPrice) {
		this.honourPrice = honourPrice;
	}
	
	
}
