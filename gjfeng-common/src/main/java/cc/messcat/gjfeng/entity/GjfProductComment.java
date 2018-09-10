package cc.messcat.gjfeng.entity;

import java.io.Serializable;
import java.util.Date;

public class GjfProductComment implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 483802981345796034L;

	private Long id;
	
	private GjfProductInfo product;//商品
	
	private GjfMemberInfo member;//商品
	
	private String content;//评论内容
	
	private String comImg;//评论图片
	
	private Integer comScore;//评分
		
	private Long comFatherId;//父级评论
	
	private Date comTime;//评论时间

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public GjfProductInfo getProduct() {
		return product;
	}

	public void setProduct(GjfProductInfo product) {
		this.product = product;
	}

	public GjfMemberInfo getMember() {
		return member;
	}

	public void setMember(GjfMemberInfo member) {
		this.member = member;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getComImg() {
		return comImg;
	}

	public void setComImg(String comImg) {
		this.comImg = comImg;
	}

	public Integer getComScore() {
		return comScore;
	}

	public void setComScore(Integer comScore) {
		this.comScore = comScore;
	}

	public Long getComFatherId() {
		return comFatherId;
	}

	public void setComFatherId(Long comFatherId) {
		this.comFatherId = comFatherId;
	}

	public Date getComTime() {
		return comTime;
	}

	public void setComTime(Date comTime) {
		this.comTime = comTime;
	}
	
	
}
