package cc.messcat.vo;

import java.util.List;

public class CartVo {
	
	private String num;//商品种类
	private String amount;//总金额
	private List<CartProVo> cartProList;//购物车商品列表
	private String storeName;//商家名称
	private Long storeId;//商家ID
	
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public List<CartProVo> getCartProList() {
		return cartProList;
	}
	public void setCartProList(List<CartProVo> cartProList) {
		this.cartProList = cartProList;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public Long getStoreId() {
		return storeId;
	}
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}
	
}
