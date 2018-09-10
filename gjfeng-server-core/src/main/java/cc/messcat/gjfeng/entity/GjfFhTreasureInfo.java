package cc.messcat.gjfeng.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="gjf_fh_treasure_member_info_")
public class GjfFhTreasureInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -124244988813170267L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_",length=11)
	private Long id;
	
	@Column(name="REAL_NAME_",length=300)
	private String realName;
	
	@Column(name="NICK_NAME_",length=300)
	private String nickName;
	
	@Column(name="MOBILE_",length=200)
	private String mobile;
	
	@Column(name="MEMBER_IDCARD_",length=300)
	private String memberIdCard;
	
	@Column(name="HEAD_IMG_",length=300)
	private String headImg;
	
	@Column(name="REAL_NAME_STATUS_",length=1)
	private String realNameStatus;
	
	@Column(name="FH_TREASURE_MONEY_",precision=20,scale=2)
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
