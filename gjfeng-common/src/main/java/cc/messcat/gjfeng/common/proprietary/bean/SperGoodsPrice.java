package cc.messcat.gjfeng.common.proprietary.bean;

import java.math.BigDecimal;

public class SperGoodsPrice {
	
	private String key_name;
	
	private BigDecimal price;
	
	private BigDecimal vip_price;
	
	private String store_count;
	
	private String key;
	
	public String getKey_name() {
		return key_name;
	}

	public void setKey_name(String key_name) {
		this.key_name = key_name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getVip_price() {
		return vip_price;
	}

	public void setVip_price(BigDecimal vip_price) {
		this.vip_price = vip_price;
	}

	public String getStore_count() {
		return store_count;
	}

	public void setStore_count(String store_count) {
		this.store_count = store_count;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	

}
