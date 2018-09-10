package cc.messcat.vo;

import java.util.List;

public class CartResultVo {
	private String num;//商品种类
	private String amount;//总金额
	private List<CartVo> cartVoList;//购物车商品列表
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
	public List<CartVo> getCartVoList() {
		return cartVoList;
	}
	public void setCartVoList(List<CartVo> cartVoList) {
		this.cartVoList = cartVoList;
	}
	
	
	
	
}
