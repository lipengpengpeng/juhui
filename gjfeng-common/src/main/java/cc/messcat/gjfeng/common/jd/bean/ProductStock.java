package cc.messcat.gjfeng.common.jd.bean;

import java.math.BigDecimal;

public class ProductStock {
	
	private Long goodsId;
	
	private BigDecimal shopPrice;
	
	private BigDecimal price;
	
	private Integer goodsNumber;
	
	private Integer isOnSale;

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public BigDecimal getShopPrice() {
		return shopPrice;
	}

	public void setShopPrice(BigDecimal shopPrice) {
		this.shopPrice = shopPrice;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getGoodsNumber() {
		return goodsNumber;
	}

	public void setGoodsNumber(Integer goodsNumber) {
		this.goodsNumber = goodsNumber;
	}

	public Integer getIsOnSale() {
		return isOnSale;
	}

	public void setIsOnSale(Integer isOnSale) {
		this.isOnSale = isOnSale;
	}

		
}
