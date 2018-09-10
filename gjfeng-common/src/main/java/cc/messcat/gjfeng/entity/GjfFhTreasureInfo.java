package cc.messcat.gjfeng.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class GjfFhTreasureInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -124244988813170267L;

	private Long id;
	
	private String realName;
	
	private String nickName;
	
	private String mobile;
	
	private String memberIdCard;
	
	private String headImg;
	
	private String realNameStatus;
	
	private BigDecimal fhTreasureMoney;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMemberIdCard() {
		return memberIdCard;
	}

	public void setMemberIdCard(String memberIdCard) {
		this.memberIdCard = memberIdCard;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public String getRealNameStatus() {
		return realNameStatus;
	}

	public void setRealNameStatus(String realNameStatus) {
		this.realNameStatus = realNameStatus;
	}

	public BigDecimal getFhTreasureMoney() {
		return fhTreasureMoney;
	}

	public void setFhTreasureMoney(BigDecimal fhTreasureMoney) {
		this.fhTreasureMoney = fhTreasureMoney;
	}
	
	

}
