package cc.messcat.gjfeng.common.jd.bean;

import java.io.Serializable;

public class GalleryImg implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6674323982276260386L;

	private String goodsId;
	
	private String imgUrl;
	
	private String isPrimary;

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getIsPrimary() {
		return isPrimary;
	}

	public void setIsPrimary(String isPrimary) {
		this.isPrimary = isPrimary;
	}
	
	

}
