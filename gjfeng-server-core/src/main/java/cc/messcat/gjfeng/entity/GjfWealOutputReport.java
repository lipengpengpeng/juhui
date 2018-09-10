package cc.messcat.gjfeng.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "gjf_weal_output_report")
public class GjfWealOutputReport implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_", length = 10)
	private Long id;
	
	@Column(name = "TURNOVER_", precision=10, scale=2)
	private BigDecimal turnOver;
	
	@Column(name = "STORE_DIVIDENDS_TOTAL_", precision=10, scale=2)
	private BigDecimal storeDividendsTotal;
	
	@Column(name = "MEMBER_DIVIDENDS_TOTAL_", precision=10, scale=2)
	private BigDecimal memberDividendsTotal;
	
	@Column(name = "GRADE_ONE_MEMBER_DIVIDENDS_TOTAL_", precision=10, scale=2)
	private BigDecimal gradeOneMemberDividendsTotal;
	
	@Column(name = "GRADE_ONE_MEMBER_DIVIDENDS_AMOUNT", precision=10, scale=2)
	private BigDecimal gradeOneMemberDividendsAmount;
	
	@Column(name = "GRADE_TWO_MEMBER_DIVIDENDS_TOTAL_", precision=10, scale=2)
	private BigDecimal gradeTwoMemberDividendsTotal;
	
	@Column(name = "GRADE_TWO_MEMBER_DIVIDENDS_AMOUNT", precision=10, scale=2)
	private BigDecimal gradeTwoMemberDividendsAmount;
	
	@Column(name = "GRADE_THREE_MEMBER_DIVIDENDS_TOTAL_", precision=10, scale=2)
	private BigDecimal gradeThreeMemberDividendsTotal;
	
	@Column(name = "GRADE_THREE_MEMBER_DIVIDENDS_AMOUNT", precision=10, scale=2)
	private BigDecimal gradeThreeMemberDividendsAmount;
	
	@Column(name = "GRADE_ONE_STORE_DIVIDENDS_TOTAL_", precision=10, scale=2)
	private BigDecimal gradeOneStoreDividendsTotal;
	
	@Column(name = "GRADE_ONE_STORE_DIVIDENDS_AMOUNT", precision=10, scale=2)
	private BigDecimal gradeOneStoreDividendsAmount;
	
	@Column(name = "GRADE_TWO_STORE_DIVIDENDS_TOTAL_", precision=10, scale=2)
	private BigDecimal gradeTwoStoreDividendsTotal;
	
	@Column(name = "GRADE_TWO_STORE_DIVIDENDS_AMOUNT", precision=10, scale=2)
	private BigDecimal gradeTwoStoreDividendsAmount;
	
	@Column(name = "GRADE_THREE_STORE_DIVIDENDS_TOTAL_", precision=10, scale=2)
	private BigDecimal gradeThreeStoreDividendsTotal;
	
	@Column(name = "GRADE_THREE_STORE_DIVIDENDS_AMOUNT", precision=10, scale=2)
	private BigDecimal gradeThreeStoreDividendsAmount;
	
	@Column(name = "ADD_TIME_")
	private Date addTime;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public BigDecimal getTurnOver() {
		return turnOver;
	}
	public void setTurnOver(BigDecimal turnOver) {
		this.turnOver = turnOver;
	}
	public BigDecimal getStoreDividendsTotal() {
		return storeDividendsTotal;
	}
	public void setStoreDividendsTotal(BigDecimal storeDividendsTotal) {
		this.storeDividendsTotal = storeDividendsTotal;
	}
	public BigDecimal getMemberDividendsTotal() {
		return memberDividendsTotal;
	}
	public void setMemberDividendsTotal(BigDecimal memberDividendsTotal) {
		this.memberDividendsTotal = memberDividendsTotal;
	}
	public BigDecimal getGradeOneMemberDividendsTotal() {
		return gradeOneMemberDividendsTotal;
	}
	public void setGradeOneMemberDividendsTotal(BigDecimal gradeOneMemberDividendsTotal) {
		this.gradeOneMemberDividendsTotal = gradeOneMemberDividendsTotal;
	}
	public BigDecimal getGradeOneMemberDividendsAmount() {
		return gradeOneMemberDividendsAmount;
	}
	public void setGradeOneMemberDividendsAmount(BigDecimal gradeOneMemberDividendsAmount) {
		this.gradeOneMemberDividendsAmount = gradeOneMemberDividendsAmount;
	}
	public BigDecimal getGradeTwoMemberDividendsTotal() {
		return gradeTwoMemberDividendsTotal;
	}
	public void setGradeTwoMemberDividendsTotal(BigDecimal gradeTwoMemberDividendsTotal) {
		this.gradeTwoMemberDividendsTotal = gradeTwoMemberDividendsTotal;
	}
	public BigDecimal getGradeTwoMemberDividendsAmount() {
		return gradeTwoMemberDividendsAmount;
	}
	public void setGradeTwoMemberDividendsAmount(BigDecimal gradeTwoMemberDividendsAmount) {
		this.gradeTwoMemberDividendsAmount = gradeTwoMemberDividendsAmount;
	}
	public BigDecimal getGradeThreeMemberDividendsTotal() {
		return gradeThreeMemberDividendsTotal;
	}
	public void setGradeThreeMemberDividendsTotal(BigDecimal gradeThreeMemberDividendsTotal) {
		this.gradeThreeMemberDividendsTotal = gradeThreeMemberDividendsTotal;
	}
	public BigDecimal getGradeThreeMemberDividendsAmount() {
		return gradeThreeMemberDividendsAmount;
	}
	public void setGradeThreeMemberDividendsAmount(BigDecimal gradeThreeMemberDividendsAmount) {
		this.gradeThreeMemberDividendsAmount = gradeThreeMemberDividendsAmount;
	}
	public BigDecimal getGradeOneStoreDividendsTotal() {
		return gradeOneStoreDividendsTotal;
	}
	public void setGradeOneStoreDividendsTotal(BigDecimal gradeOneStoreDividendsTotal) {
		this.gradeOneStoreDividendsTotal = gradeOneStoreDividendsTotal;
	}
	public BigDecimal getGradeOneStoreDividendsAmount() {
		return gradeOneStoreDividendsAmount;
	}
	public void setGradeOneStoreDividendsAmount(BigDecimal gradeOneStoreDividendsAmount) {
		this.gradeOneStoreDividendsAmount = gradeOneStoreDividendsAmount;
	}
	public BigDecimal getGradeTwoStoreDividendsTotal() {
		return gradeTwoStoreDividendsTotal;
	}
	public void setGradeTwoStoreDividendsTotal(BigDecimal gradeTwoStoreDividendsTotal) {
		this.gradeTwoStoreDividendsTotal = gradeTwoStoreDividendsTotal;
	}
	public BigDecimal getGradeTwoStoreDividendsAmount() {
		return gradeTwoStoreDividendsAmount;
	}
	public void setGradeTwoStoreDividendsAmount(BigDecimal gradeTwoStoreDividendsAmount) {
		this.gradeTwoStoreDividendsAmount = gradeTwoStoreDividendsAmount;
	}
	public BigDecimal getGradeThreeStoreDividendsTotal() {
		return gradeThreeStoreDividendsTotal;
	}
	public void setGradeThreeStoreDividendsTotal(BigDecimal gradeThreeStoreDividendsTotal) {
		this.gradeThreeStoreDividendsTotal = gradeThreeStoreDividendsTotal;
	}
	public BigDecimal getGradeThreeStoreDividendsAmount() {
		return gradeThreeStoreDividendsAmount;
	}
	public void setGradeThreeStoreDividendsAmount(BigDecimal gradeThreeStoreDividendsAmount) {
		this.gradeThreeStoreDividendsAmount = gradeThreeStoreDividendsAmount;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	
}
