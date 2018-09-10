package cc.messcat.vo;

import cc.modules.util.StringUtil;

/**
 * 购物车商品信息
 * @author Administrator
 *
 */
public class CartProVo {
	
	private Long proId;//商品id
	private Long columnId;//栏目id
	private String imgUrl;//商品图片
	private String name;//商品名称
	private String price;//商品价格
	private int num;//商品数量
	private Long cartId;//购物车id
	private String subtotal;//总价格=price*num
	private Double giftPoint;//赠与积分
	private String storeName;//商家名称
	private Long storeId;//商家ID
	private Long memberId;//会员Id
	private Double giftRatio;//赠与比例
	private Integer state;//状态

	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public Long getProId() {
		return proId;
	}
	public void setProId(Long proId) {
		this.proId = proId;
	}
	public Long getColumnId() {
		return columnId;
	}
	public void setColumnId(Long columnId) {
		this.columnId = columnId;
	}
	public Long getCartId() {
		return cartId;
	}
	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}
	public String getSubtotal() {
		if(StringUtil.isNotBlank(price) && num >0)
			subtotal = String.format("%1$.2f", Double.valueOf(price) * num);
		return subtotal;
	}
	public void setSubtotal(String subtotal) {
		this.subtotal = subtotal;
	}
	public Double getGiftPoint() {
		return giftPoint;
	}
	public void setGiftPoint(Double giftPoint) {
		this.giftPoint = giftPoint;
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
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	public Double getGiftRatio() {
		return giftRatio;
	}
	public void setGiftRatio(Double giftRatio) {
		this.giftRatio = giftRatio;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}

}
