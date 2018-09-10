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
@Table(name="gjf_member_merge")
public class GjfMemberMerge implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8025634340101792974L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_",length=11)
	private Long id;
	
	@Column(name="MEMBER_ID_",length=11)
	private Long memberId;
	
	@Column(name="MERGE_MEMBER_ID_",length=11)
	private Long mergeMemberId;
	
	@Column(name="MEMBER_NAME_",length=200)
	private String memberName;
	
	@Column(name="MEMBER_MOBILE_",length=200)
	private String memberMobile;
	
	@Column(name="MERGE_MEMBER_NAME_",length=200)
	private String mergeMemberName;
	
	@Column(name="MERGE_MEMBER_MOBILE_",length=200)
	private String mergeMemberMobile;
	
	@Column(name="MEMBER_BALANCE_BF_",precision=20,scale=2)
	private BigDecimal memberBalanceBF;
	
	@Column(name="MEMBER_DIVI_BF_",precision=20,scale=6)
	private BigDecimal memberDiviBf;
	
	@Column(name="MEMBER_INTEGRAL_BF_",precision=20,scale=2)
	private BigDecimal memberIntegralBf;
	
	@Column(name="MEMBER_COUSUMPTION_BF_",precision=20,scale=2)
	private BigDecimal memberCousumptionBf;
	
	@Column(name="MERCHANT_SALE_BF_",precision=20,scale=2)
	private BigDecimal merchantSaleBf;
	
	@Column(name="MERCHANT_DIVI_BF",precision=20,scale=2)
	private BigDecimal merchantDiviBf;
	
	@Column(name="MEMBER_BALANCE_AF_",precision=20,scale=2)
	private BigDecimal memberBalanceAf;
	
	@Column(name="MEMBER_DIVI_AF_",precision=20,scale=6)
	private BigDecimal memberDiviAf;
	
	@Column(name="MEMBER_INTEGRAL_AF_",precision=20,scale=2)
	private BigDecimal memberIntegralAf;
	
	@Column(name="MEMBER_COUSUMPTION_AF_",precision=20,scale=2)
	private BigDecimal memberCousumptionAf;
	
	@Column(name="MERCHANT_SALE_AF_",precision=20,scale=2)
	private BigDecimal merchantSaleAf;
	
	@Column(name="MERCHANT_DIVI_AF_",precision=20,scale=6)
	private BigDecimal merchantDiviAf;
	
	@Column(name="MERGE_MEMBER_BALANCE_BF_",precision=20,scale=2)
	private BigDecimal mergeMemberBalanceBf;
	
	@Column(name="MERGE_MEMBER_DIVI_BF_",precision=20,scale=6)
	private BigDecimal mergeMemberDiviBf;
	
	@Column(name="MERGE_MEMBER_INTEGRAL_BF_",precision=20,scale=2)
	private BigDecimal mergeMemberIntegralBf;
	
	@Column(name="MERGE_MEMBER_COUSUMPTION_BF_",precision=20,scale=2)
	private BigDecimal mergeMemberCousumptionBf;
	
	@Column(name="MERGE_MERCHANT_SALA_BF_",precision=20,scale=2)
	private BigDecimal mergeMerchantSaleBf;
	
	@Column(name="MERGE_MERCHANT_DIVI_BF_",precision=20,scale=2)
	private BigDecimal mergeMerchantDiviBf;
	
	@Column(name="MERGE_MEMBER_BALANCE_AF_",precision=20,scale=2)
	private BigDecimal mergeMemberBalanceAf;
	
	@Column(name="MERGE_MEMBER_DIVI_AF_",precision=20,scale=6)
	private BigDecimal mergeMemberDiviAf;
	
	@Column(name="MERGE_MEMBER_INTEGRAL_AF_",precision=20,scale=2)
	private BigDecimal mergeMemberIntegralAf;
	
	@Column(name="MERGE_MEMBER_COUSUMPTION_AF_",precision=20,scale=2)
	private BigDecimal mergeMemberCousumptionAf;
	
	@Column(name="MERGE_MERCHANT_SALA_AF_",precision=20,scale=2)
	private BigDecimal mergeMerchantSaleAf;
	
	@Column(name="MERGE_MERCHANT_DIVI_AF_",precision=20,scale=6)
	private BigDecimal mergeMerchantDiviAf;
	
	@Column(name="BALANCE_POUNDAGE_",precision=20,scale=2)
	private BigDecimal balancePoundage;
	
	@Column(name="INTEGRAL_POUNDAGE_",precision=20,scale=2)
	private BigDecimal integralPoundage;
	
	@Column(name="COUNSUMPTION_POUNDAGE_",precision=20,scale=2)
	private BigDecimal cousumptiomPoundage;
	
	@Column(name="SALA_POUNDAGE_",precision=20,scale=2)
	private BigDecimal salePoundage;
	
	@Column(name="ACTUAL_MERGE_BALANCE_",precision=20,scale=2)
	private BigDecimal actualMergeBalance;
	
	@Column(name="ACTUAL_MERGE_INTEGRAL_",precision=20,scale=2)
	private BigDecimal actualMergeIntegral;
	
	@Column(name="ACTUAL_MERGE_COUSUMPTION_",precision=20,scale=2)
	private BigDecimal actualMergeCousumption;
	
	@Column(name="ACTUAL_MERGE_SALA_",precision=20,scale=2)
	private BigDecimal actualMergeSale;
	
	@Column(name="ACTUAL_MERGE_MEMBER_DIVI_",precision=20,scale=6)
	private BigDecimal actualMergeMemberDivi;
	
	@Column(name="ACTUAL_MERGE_MERCHANT_DIVI_",precision=20,scale=6)
	private BigDecimal actualMergeMerchantDivi;
	
	@Column(name="MEMBER_RESPON_MONEY_BF_",precision=20,scale=2)
	private BigDecimal memberResponMoneyBf;
	
	@Column(name="MEMBER_RESPON_MONEY_AF_",precision=20,scale=2)
	private BigDecimal memberResponMoneyAf;
	
	@Column(name="MERGE_MEMBER_RESPON_MONEY_BF_",precision=20,scale=2)
	private BigDecimal mergeMemberResponMoneyBf;
	
	@Column(name="MERGE_MEMBER_RESPON_MONEY_AF_",precision=20,scale=2)
	private BigDecimal mergeMemberResponMoneyAf;
	
	@Column(name="RESPON_POUNDAGE_",precision=20,scale=2)
	private BigDecimal responPoundage;
	
	@Column(name="ACTUAL_MERGE_RESPON_",precision=20,scale=2)
	private BigDecimal actualMergeRespon;
	
	@Column(name="DIVI_POUNFAGE_",precision=20,scale=6)
	private BigDecimal diviPoundage;
	
	@Column(name="DIVI_MERCHANT_POUNDAGE_",precision=20,scale=6)
	private BigDecimal diviMerchantPoundage;
	
	@Column(name="MEMBER_RESERVE_DIVI_BF_",precision=20,scale=6)
	private BigDecimal memberReserveDiviBf;
	
	@Column(name="MEMBER_RESERVE_DIVI_AF_",precision=20,scale=6)
	private BigDecimal memberReserveDiviAf;
	
	@Column(name="MERGE_MEMBER_RESERVE_DIVI_BF_",precision=20,scale=6)
	private BigDecimal mergeMemberReserveDiviBf;
	
	@Column(name="MERGE_MEMBER_RESERVE_DIVI_AF_",precision=20,scale=6)
	private BigDecimal mergeMemberReserveDiviAf;
	
	@Column(name="ACTUAL_RESERVE_",precision=20,scale=6)
	private BigDecimal actualReserve;
	
	@Column(name="RESERVE_POUNDAGE_",precision=20,scale=6)
	private BigDecimal reservePoundage;
	
	@Column(name="MERCHANT_RESERVE_DIVI_BF_",precision=20,scale=6)
	private BigDecimal merchantReserveDiviBf;
	
	@Column(name="MERCHANT_RESERVE_DIVI_AF_",precision=20,scale=6)
	private BigDecimal merchantReserveDiviAf;
	
	@Column(name="MERGE_MERCHANT_RESERVE_DIVI_BF_",precision=20,scale=6)
	private BigDecimal mergeMerchantReserveBf;
	
	@Column(name="MERGE_MERCHANT_RESERVE_DIVI_AF_",precision=20,scale=6)
	private BigDecimal mergeMerchantReserveAf;
	
	@Column(name="ACTUAL_MERCHANT_RESERVE_",precision=20,scale=6)
	private BigDecimal actualMerchantReserve;
	
	@Column(name="MERCHANT_RESERVE_POUNDAGE_",precision=20,scale=6)
	private BigDecimal merchantReservePoundage;
	
	@Column(name="ADD_TIME_")
	private Date addTime;
	
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

	public Long getMergeMemberId() {
		return mergeMemberId;
	}

	public void setMergeMemberId(Long mergeMemberId) {
		this.mergeMemberId = mergeMemberId;
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

	public String getMergeMemberName() {
		return mergeMemberName;
	}

	public void setMergeMemberName(String mergeMemberName) {
		this.mergeMemberName = mergeMemberName;
	}

	public String getMergeMemberMobile() {
		return mergeMemberMobile;
	}

	public void setMergeMemberMobile(String mergeMemberMobile) {
		this.mergeMemberMobile = mergeMemberMobile;
	}

	public BigDecimal getMemberBalanceBF() {
		return memberBalanceBF;
	}

	public void setMemberBalanceBF(BigDecimal memberBalanceBF) {
		this.memberBalanceBF = memberBalanceBF;
	}

	public BigDecimal getMemberDiviBf() {
		return memberDiviBf;
	}

	public void setMemberDiviBf(BigDecimal memberDiviBf) {
		this.memberDiviBf = memberDiviBf;
	}

	public BigDecimal getMemberIntegralBf() {
		return memberIntegralBf;
	}

	public void setMemberIntegralBf(BigDecimal memberIntegralBf) {
		this.memberIntegralBf = memberIntegralBf;
	}

	public BigDecimal getMemberCousumptionBf() {
		return memberCousumptionBf;
	}

	public void setMemberCousumptionBf(BigDecimal memberCousumptionBf) {
		this.memberCousumptionBf = memberCousumptionBf;
	}

	public BigDecimal getMerchantSaleBf() {
		return merchantSaleBf;
	}

	public void setMerchantSaleBf(BigDecimal merchantSaleBf) {
		this.merchantSaleBf = merchantSaleBf;
	}

	public BigDecimal getMerchantDiviBf() {
		return merchantDiviBf;
	}

	public void setMerchantDiviBf(BigDecimal merchantDiviBf) {
		this.merchantDiviBf = merchantDiviBf;
	}

	public BigDecimal getMemberBalanceAf() {
		return memberBalanceAf;
	}

	public void setMemberBalanceAf(BigDecimal memberBalanceAf) {
		this.memberBalanceAf = memberBalanceAf;
	}

	public BigDecimal getMemberDiviAf() {
		return memberDiviAf;
	}

	public void setMemberDiviAf(BigDecimal memberDiviAf) {
		this.memberDiviAf = memberDiviAf;
	}

	public BigDecimal getMemberIntegralAf() {
		return memberIntegralAf;
	}

	public void setMemberIntegralAf(BigDecimal memberIntegralAf) {
		this.memberIntegralAf = memberIntegralAf;
	}

	public BigDecimal getMemberCousumptionAf() {
		return memberCousumptionAf;
	}

	public void setMemberCousumptionAf(BigDecimal memberCousumptionAf) {
		this.memberCousumptionAf = memberCousumptionAf;
	}

	public BigDecimal getMerchantSaleAf() {
		return merchantSaleAf;
	}

	public void setMerchantSaleAf(BigDecimal merchantSaleAf) {
		this.merchantSaleAf = merchantSaleAf;
	}

	public BigDecimal getMerchantDiviAf() {
		return merchantDiviAf;
	}

	public void setMerchantDiviAf(BigDecimal merchantDiviAf) {
		this.merchantDiviAf = merchantDiviAf;
	}

	public BigDecimal getMergeMemberBalanceBf() {
		return mergeMemberBalanceBf;
	}

	public void setMergeMemberBalanceBf(BigDecimal mergeMemberBalanceBf) {
		this.mergeMemberBalanceBf = mergeMemberBalanceBf;
	}

	public BigDecimal getMergeMemberDiviBf() {
		return mergeMemberDiviBf;
	}

	public void setMergeMemberDiviBf(BigDecimal mergeMemberDiviBf) {
		this.mergeMemberDiviBf = mergeMemberDiviBf;
	}

	public BigDecimal getMergeMemberIntegralBf() {
		return mergeMemberIntegralBf;
	}

	public void setMergeMemberIntegralBf(BigDecimal mergeMemberIntegralBf) {
		this.mergeMemberIntegralBf = mergeMemberIntegralBf;
	}

	public BigDecimal getMergeMemberCousumptionBf() {
		return mergeMemberCousumptionBf;
	}

	public void setMergeMemberCousumptionBf(BigDecimal mergeMemberCousumptionBf) {
		this.mergeMemberCousumptionBf = mergeMemberCousumptionBf;
	}

	public BigDecimal getMergeMerchantSaleBf() {
		return mergeMerchantSaleBf;
	}

	public void setMergeMerchantSaleBf(BigDecimal mergeMerchantSaleBf) {
		this.mergeMerchantSaleBf = mergeMerchantSaleBf;
	}

	public BigDecimal getMergeMerchantDiviBf() {
		return mergeMerchantDiviBf;
	}

	public void setMergeMerchantDiviBf(BigDecimal mergeMerchantDiviBf) {
		this.mergeMerchantDiviBf = mergeMerchantDiviBf;
	}

	public BigDecimal getMergeMemberBalanceAf() {
		return mergeMemberBalanceAf;
	}

	public void setMergeMemberBalanceAf(BigDecimal mergeMemberBalanceAf) {
		this.mergeMemberBalanceAf = mergeMemberBalanceAf;
	}

	public BigDecimal getMergeMemberDiviAf() {
		return mergeMemberDiviAf;
	}

	public void setMergeMemberDiviAf(BigDecimal mergeMemberDiviAf) {
		this.mergeMemberDiviAf = mergeMemberDiviAf;
	}

	public BigDecimal getMergeMemberIntegralAf() {
		return mergeMemberIntegralAf;
	}

	public void setMergeMemberIntegralAf(BigDecimal mergeMemberIntegralAf) {
		this.mergeMemberIntegralAf = mergeMemberIntegralAf;
	}

	public BigDecimal getMergeMemberCousumptionAf() {
		return mergeMemberCousumptionAf;
	}

	public void setMergeMemberCousumptionAf(BigDecimal mergeMemberCousumptionAf) {
		this.mergeMemberCousumptionAf = mergeMemberCousumptionAf;
	}

	public BigDecimal getMergeMerchantSaleAf() {
		return mergeMerchantSaleAf;
	}

	public void setMergeMerchantSaleAf(BigDecimal mergeMerchantSaleAf) {
		this.mergeMerchantSaleAf = mergeMerchantSaleAf;
	}

	public BigDecimal getMergeMerchantDiviAf() {
		return mergeMerchantDiviAf;
	}

	public void setMergeMerchantDiviAf(BigDecimal mergeMerchantDiviAf) {
		this.mergeMerchantDiviAf = mergeMerchantDiviAf;
	}

	public BigDecimal getBalancePoundage() {
		return balancePoundage;
	}

	public void setBalancePoundage(BigDecimal balancePoundage) {
		this.balancePoundage = balancePoundage;
	}

	public BigDecimal getIntegralPoundage() {
		return integralPoundage;
	}

	public void setIntegralPoundage(BigDecimal integralPoundage) {
		this.integralPoundage = integralPoundage;
	}

	public BigDecimal getCousumptiomPoundage() {
		return cousumptiomPoundage;
	}

	public void setCousumptiomPoundage(BigDecimal cousumptiomPoundage) {
		this.cousumptiomPoundage = cousumptiomPoundage;
	}

	public BigDecimal getSalePoundage() {
		return salePoundage;
	}

	public void setSalePoundage(BigDecimal salePoundage) {
		this.salePoundage = salePoundage;
	}

	public BigDecimal getActualMergeBalance() {
		return actualMergeBalance;
	}

	public void setActualMergeBalance(BigDecimal actualMergeBalance) {
		this.actualMergeBalance = actualMergeBalance;
	}

	public BigDecimal getActualMergeIntegral() {
		return actualMergeIntegral;
	}

	public void setActualMergeIntegral(BigDecimal actualMergeIntegral) {
		this.actualMergeIntegral = actualMergeIntegral;
	}

	public BigDecimal getActualMergeCousumption() {
		return actualMergeCousumption;
	}

	public void setActualMergeCousumption(BigDecimal actualMergeCousumption) {
		this.actualMergeCousumption = actualMergeCousumption;
	}

	public BigDecimal getActualMergeSale() {
		return actualMergeSale;
	}

	public void setActualMergeSale(BigDecimal actualMergeSale) {
		this.actualMergeSale = actualMergeSale;
	}

	public BigDecimal getActualMergeMemberDivi() {
		return actualMergeMemberDivi;
	}

	public void setActualMergeMemberDivi(BigDecimal actualMergeMemberDivi) {
		this.actualMergeMemberDivi = actualMergeMemberDivi;
	}

	public BigDecimal getActualMergeMerchantDivi() {
		return actualMergeMerchantDivi;
	}

	public void setActualMergeMerchantDivi(BigDecimal actualMergeMerchantDivi) {
		this.actualMergeMerchantDivi = actualMergeMerchantDivi;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getMemberResponMoneyBf() {
		return memberResponMoneyBf;
	}

	public void setMemberResponMoneyBf(BigDecimal memberResponMoneyBf) {
		this.memberResponMoneyBf = memberResponMoneyBf;
	}

	public BigDecimal getMemberResponMoneyAf() {
		return memberResponMoneyAf;
	}

	public void setMemberResponMoneyAf(BigDecimal memberResponMoneyAf) {
		this.memberResponMoneyAf = memberResponMoneyAf;
	}

	public BigDecimal getMergeMemberResponMoneyBf() {
		return mergeMemberResponMoneyBf;
	}

	public void setMergeMemberResponMoneyBf(BigDecimal mergeMemberResponMoneyBf) {
		this.mergeMemberResponMoneyBf = mergeMemberResponMoneyBf;
	}

	public BigDecimal getMergeMemberResponMoneyAf() {
		return mergeMemberResponMoneyAf;
	}

	public void setMergeMemberResponMoneyAf(BigDecimal mergeMemberResponMoneyAf) {
		this.mergeMemberResponMoneyAf = mergeMemberResponMoneyAf;
	}

	public BigDecimal getResponPoundage() {
		return responPoundage;
	}

	public void setResponPoundage(BigDecimal responPoundage) {
		this.responPoundage = responPoundage;
	}

	public BigDecimal getActualMergeRespon() {
		return actualMergeRespon;
	}

	public void setActualMergeRespon(BigDecimal actualMergeRespon) {
		this.actualMergeRespon = actualMergeRespon;
	}

	public BigDecimal getDiviPoundage() {
		return diviPoundage;
	}

	public void setDiviPoundage(BigDecimal diviPoundage) {
		this.diviPoundage = diviPoundage;
	}

	public BigDecimal getDiviMerchantPoundage() {
		return diviMerchantPoundage;
	}

	public void setDiviMerchantPoundage(BigDecimal diviMerchantPoundage) {
		this.diviMerchantPoundage = diviMerchantPoundage;
	}

	public BigDecimal getMemberReserveDiviBf() {
		return memberReserveDiviBf;
	}

	public void setMemberReserveDiviBf(BigDecimal memberReserveDiviBf) {
		this.memberReserveDiviBf = memberReserveDiviBf;
	}

	public BigDecimal getMemberReserveDiviAf() {
		return memberReserveDiviAf;
	}

	public void setMemberReserveDiviAf(BigDecimal memberReserveDiviAf) {
		this.memberReserveDiviAf = memberReserveDiviAf;
	}

	public BigDecimal getMergeMemberReserveDiviBf() {
		return mergeMemberReserveDiviBf;
	}

	public void setMergeMemberReserveDiviBf(BigDecimal mergeMemberReserveDiviBf) {
		this.mergeMemberReserveDiviBf = mergeMemberReserveDiviBf;
	}

	public BigDecimal getMergeMemberReserveDiviAf() {
		return mergeMemberReserveDiviAf;
	}

	public void setMergeMemberReserveDiviAf(BigDecimal mergeMemberReserveDiviAf) {
		this.mergeMemberReserveDiviAf = mergeMemberReserveDiviAf;
	}

	public BigDecimal getActualReserve() {
		return actualReserve;
	}

	public void setActualReserve(BigDecimal actualReserve) {
		this.actualReserve = actualReserve;
	}

	public BigDecimal getReservePoundage() {
		return reservePoundage;
	}

	public void setReservePoundage(BigDecimal reservePoundage) {
		this.reservePoundage = reservePoundage;
	}

	public BigDecimal getMerchantReserveDiviBf() {
		return merchantReserveDiviBf;
	}

	public void setMerchantReserveDiviBf(BigDecimal merchantReserveDiviBf) {
		this.merchantReserveDiviBf = merchantReserveDiviBf;
	}

	public BigDecimal getMerchantReserveDiviAf() {
		return merchantReserveDiviAf;
	}

	public void setMerchantReserveDiviAf(BigDecimal merchantReserveDiviAf) {
		this.merchantReserveDiviAf = merchantReserveDiviAf;
	}

	public BigDecimal getMergeMerchantReserveBf() {
		return mergeMerchantReserveBf;
	}

	public void setMergeMerchantReserveBf(BigDecimal mergeMerchantReserveBf) {
		this.mergeMerchantReserveBf = mergeMerchantReserveBf;
	}

	public BigDecimal getMergeMerchantReserveAf() {
		return mergeMerchantReserveAf;
	}

	public void setMergeMerchantReserveAf(BigDecimal mergeMerchantReserveAf) {
		this.mergeMerchantReserveAf = mergeMerchantReserveAf;
	}

	public BigDecimal getActualMerchantReserve() {
		return actualMerchantReserve;
	}

	public void setActualMerchantReserve(BigDecimal actualMerchantReserve) {
		this.actualMerchantReserve = actualMerchantReserve;
	}

	public BigDecimal getMerchantReservePoundage() {
		return merchantReservePoundage;
	}

	public void setMerchantReservePoundage(BigDecimal merchantReservePoundage) {
		this.merchantReservePoundage = merchantReservePoundage;
	}
	
	

}
