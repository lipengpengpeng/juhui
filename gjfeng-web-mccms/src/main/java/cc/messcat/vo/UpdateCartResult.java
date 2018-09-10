package cc.messcat.vo;

public class UpdateCartResult {
	private String state;
	private double subtotal;
	private String goods_price;
	private int goods_num;
	private String msg;
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	public String getGoods_price() {
		return goods_price;
	}
	public void setGoods_price(String goodsPrice) {
		goods_price = goodsPrice;
	}
	public int getGoods_num() {
		return goods_num;
	}
	public void setGoods_num(int goodsNum) {
		goods_num = goodsNum;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

}
