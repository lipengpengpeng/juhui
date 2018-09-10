package cc.messcat.gjfeng.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="gjf_transfer_history")
public class GjfTransferHistory implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -949402635224327921L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_", length = 10)
	private Long id;
	
	@Column(name = "TRANSFER_MONEY_", precision=20, scale=2)
	private BigDecimal transferMoney;
	
	@Column(name = "BALANCE_BF_", precision=20, scale=2)
	private BigDecimal balanceBf;
	
	@Column(name = "BALANCE_AF_", precision=20, scale=2)
	private BigDecimal balanceAf;
	
	@ManyToOne
	@JoinColumn(name="MEMBER_ID_")
	private GjfMemberInfo memberId;
	
	@Column(name = "ADD_TIME_")
	private Date addTime;
	
	@Column(name = "IS_DEL_",length=1)
	private String isDel;
	
	@Column(name = "TRANSFER_DIVIDENDS_MONEY_", precision=20, scale=2)
	private BigDecimal transferDividendsMoney;
	
	@Column(name = "TRANSFER_RECOM_MONEY_", precision=20, scale=2)
	private BigDecimal transferRecomMoney;
	
	@Column(name = "TRANSFER_INDI_MONEY_", precision=20, scale=2)
	private BigDecimal transferIndiMoney;
	
	@Column(name = "POUNDAGE_", precision=20, scale=2)
	private BigDecimal poundage;
	
	@Column(name = "TRANSFER_AFTER_TAX_", precision=20, scale=2)
	private BigDecimal transferAfterTax;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getTransferMoney() {
		return transferMoney;
	}

	public void setTransferMoney(BigDecimal transferMoney) {
		this.transferMoney = transferMoney;
	}

	public BigDecimal getBalanceBf() {
		return balanceBf;
	}

	public void setBalanceBf(BigDecimal balanceBf) {
		this.balanceBf = balanceBf;
	}

	public BigDecimal getBalanceAf() {
		return balanceAf;
	}

	public void setBalanceAf(BigDecimal balanceAf) {
		this.balanceAf = balanceAf;
	}

	public GjfMemberInfo getMemberId() {
		return memberId;
	}

	public void setMemberId(GjfMemberInfo memberId) {
		this.memberId = memberId;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getIsDel() {
		return isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

	public BigDecimal getTransferDividendsMoney() {
		return transferDividendsMoney;
	}

	public void setTransferDividendsMoney(BigDecimal transferDividendsMoney) {
		this.transferDividendsMoney = transferDividendsMoney;
	}

	public BigDecimal getTransferRecomMoney() {
		return transferRecomMoney;
	}

	public void setTransferRecomMoney(BigDecimal transferRecomMoney) {
		this.transferRecomMoney = transferRecomMoney;
	}

	public BigDecimal getTransferIndiMoney() {
		return transferIndiMoney;
	}

	public void setTransferIndiMoney(BigDecimal transferIndiMoney) {
		this.transferIndiMoney = transferIndiMoney;
	}

	public BigDecimal getPoundage() {
		return poundage;
	}

	public void setPoundage(BigDecimal poundage) {
		this.poundage = poundage;
	}

	public BigDecimal getTransferAfterTax() {
		return transferAfterTax;
	}

	public void setTransferAfterTax(BigDecimal transferAfterTax) {
		this.transferAfterTax = transferAfterTax;
	}
	
	
}
