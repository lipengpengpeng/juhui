package cc.messcat.gjfeng.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="gjf_member_dedcut_divi_num_history")
public class GjfMemberDiviNumHistory  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7641256206313509359L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_", length = 11)
	private Long id;
	
	@Column(name="MEMBER_ID_",length=11)
	private Long memberId;
	
	@Column(name="DEDUCT_AMOUNT_",precision=20,scale=2)
	private BigDecimal dedcutAmount;
	
	@Column(name="DEDCUT_DIVI_TOTAL_MONEY_BF_",precision=20,scale=2)
	private BigDecimal dedcutDiviTotalMoneyBf;
	
	@Column(name="DEDCUT_DIVI_TOTAL_MONEY_AF_",precision=20,scale=2)
	private BigDecimal dedectDiviTotalMoneyAf;
	
	@Column(name="MEMBER_DIVI_NUM_BF_",precision=20,scale=6)
	private BigDecimal memberDiviNumBf;
	
	@Column(name="MEMBER_DIVI_NUM_AF_",precision=20,scale=6)
	private BigDecimal memberDiviNumAf;
	
	@Column(name="DEDCUT_DIVI_NUM_",precision=20,scale=6)
	private BigDecimal deductDiviNum;
	
	@Column(name="TRADE_STATUE_",length=1)
	private String tradeStatus;
	
	@Column(name="TRADE_TYPE_",length=1)
	private String tradeType;
	
	@Column(name="MEMBER_NAME_",length=150)
	private String memberName;
	
	@Column(name="MEMBER_MOBILE_",length=150)
	private String memberMobile;
	
	@Column(name="ADD_TIME_")
	private Date addTime;

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

	public BigDecimal getDedcutAmount() {
		return dedcutAmount;
	}

	public void setDedcutAmount(BigDecimal dedcutAmount) {
		this.dedcutAmount = dedcutAmount;
	}

	public BigDecimal getDedcutDiviTotalMoneyBf() {
		return dedcutDiviTotalMoneyBf;
	}

	public void setDedcutDiviTotalMoneyBf(BigDecimal dedcutDiviTotalMoneyBf) {
		this.dedcutDiviTotalMoneyBf = dedcutDiviTotalMoneyBf;
	}

	public BigDecimal getDedectDiviTotalMoneyAf() {
		return dedectDiviTotalMoneyAf;
	}

	public void setDedectDiviTotalMoneyAf(BigDecimal dedectDiviTotalMoneyAf) {
		this.dedectDiviTotalMoneyAf = dedectDiviTotalMoneyAf;
	}

	public BigDecimal getMemberDiviNumBf() {
		return memberDiviNumBf;
	}

	public void setMemberDiviNumBf(BigDecimal memberDiviNumBf) {
		this.memberDiviNumBf = memberDiviNumBf;
	}

	public BigDecimal getMemberDiviNumAf() {
		return memberDiviNumAf;
	}

	public void setMemberDiviNumAf(BigDecimal memberDiviNumAf) {
		this.memberDiviNumAf = memberDiviNumAf;
	}

	public BigDecimal getDeductDiviNum() {
		return deductDiviNum;
	}

	public void setDeductDiviNum(BigDecimal deductDiviNum) {
		this.deductDiviNum = deductDiviNum;
	}

	public String getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberMobile() {
		return memberMobile;
	}

	public void setMemberMobile(String memberMobile) {
		this.memberMobile = memberMobile;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	
	

}
