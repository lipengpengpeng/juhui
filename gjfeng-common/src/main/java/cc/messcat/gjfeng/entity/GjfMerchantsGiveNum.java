package cc.messcat.gjfeng.entity;

import java.io.Serializable;

public class GjfMerchantsGiveNum implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1142709250883474618L;

	private Long id;

	private Long memberId;

	private String memberMobile;

	private String memberName;

	private Integer merchants1Num;

	private Integer merchants2Num;

	private Integer merchants3Num;

	private Integer merchants4Num;

	private Integer merchants5Num;

	private Integer merchants6Num;

	private Integer merchants7Num;

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
