package cc.messcat.gjfeng.common.jd.bean;

import java.math.BigDecimal;

public class OrderResult {

	private String kxzNo;
	
	private String tradeNo;
	
	private BigDecimal productAmount;
	
	private BigDecimal orderAmount;
	
	private String totalDeliveryFee;
		
	
	private GoodList goodsList;
	
	public String getKxzNo() {
		return kxzNo;
	}

	public void setKxzNo(String kxzNo) {
		this.kxzNo = kxzNo;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public BigDecimal getProductAmount() {
		return productAmount;
	}

	public void setProductAmount(BigDecimal productAmount) {
		this.productAmount = productAmount;
	}

	public BigDecimal getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getTotalDeliveryFee() {
		return totalDeliveryFee;
	}

	public void setTotalDeliveryFee(String totalDeliveryFee) {
		this.totalDeliveryFee = totalDeliveryFee;
	}

	public GoodList getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(GoodList goodsList) {
		this.goodsList = goodsList;
	}

	
}
