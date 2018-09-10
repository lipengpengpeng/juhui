package cc.messcat.gjfeng.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="gjf_member_consumption_num")
public class GjfMemberConsumptiomNum implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7119435446731723282L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_", length = 11)
	private Long id;
	
	@Column(name="SHOP_CONSUMPTION_NUM_",length=11)
	private Integer shopConsumptionNum;
	
	@Column(name="BENEFIT_NUM_",length=11)
	private Integer benefitNum;
	
	@Column(name="MEMBER_ID_",length=11)
	private Long memberId;
	
	@Column(name="ADD_TIME_")
	private Date addTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getShopConsumptionNum() {
		return shopConsumptionNum;
	}

	public void setShopConsumptionNum(Integer shopConsumptionNum) {
		this.shopConsumptionNum = shopConsumptionNum;
	}

	public Integer getBenefitNum() {
		return benefitNum;
	}

	public void setBenefitNum(Integer benefitNum) {
		this.benefitNum = benefitNum;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	
	
	
	
}
