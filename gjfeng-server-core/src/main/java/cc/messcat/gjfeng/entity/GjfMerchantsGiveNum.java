package cc.messcat.gjfeng.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="gjf_merchants_give_num_")
public class GjfMerchantsGiveNum implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1142709250883474618L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_",length=11)
	private Long id;

	@Column(name="MEMBER_ID_",length=11)
	private Long memberId;
	
	@Column(name="MEMBER_MOBILE_",length=200)
	private String memberMobile;
	
	@Column(name="MEMBER_NAME_",length=200)
	private String memberName;
	
	@Column(name="MERCHANTS1_NUM_",length=11)
	private Integer merchants1Num;
	
	@Column(name="MERCHANTS2_NUM_",length=11)
	private Integer merchants2Num;
	
	@Column(name="MERCHANTS3_NUM_",length=11)
	private Integer merchants3Num;
	
	@Column(name="MERCHANTS4_NUM_",length=11)
	private Integer merchants4Num;
	
	@Column(name="MERCHANTS5_NUM_",length=11)
	private Integer merchants5Num;
	
	@Column(name="MERCHANTS6_NUM_",length=11)
	private Integer merchants6Num;
	
	@Column(name="MERCHANTS7_NUM_",length=11)
	private Integer merchants7Num;
	
	@Column(name="STATUS_",length=1)
	private String status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getMemberMobile() {
		return memberMobile;
	}

	public void setMemberMobile(String memberMobile) {
		this.memberMobile = memberMobile;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public Integer getMerchants1Num() {
		return merchants1Num;
	}

	public void setMerchants1Num(Integer merchants1Num) {
		this.merchants1Num = merchants1Num;
	}

	public Integer getMerchants2Num() {
		return merchants2Num;
	}

	public void setMerchants2Num(Integer merchants2Num) {
		this.merchants2Num = merchants2Num;
	}

	public Integer getMerchants3Num() {
		return merchants3Num;
	}

	public void setMerchants3Num(Integer merchants3Num) {
		this.merchants3Num = merchants3Num;
	}

	public Integer getMerchants4Num() {
		return merchants4Num;
	}

	public void setMerchants4Num(Integer merchants4Num) {
		this.merchants4Num = merchants4Num;
	}

	public Integer getMerchants5Num() {
		return merchants5Num;
	}

	public void setMerchants5Num(Integer merchants5Num) {
		this.merchants5Num = merchants5Num;
	}

	public Integer getMerchants6Num() {
		return merchants6Num;
	}

	public void setMerchants6Num(Integer merchants6Num) {
		this.merchants6Num = merchants6Num;
	}

	public Integer getMerchants7Num() {
		return merchants7Num;
	}

	public void setMerchants7Num(Integer merchants7Num) {
		this.merchants7Num = merchants7Num;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
