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
@Table(name = "gjf_benefit_report")
public class GjfBenefitReport implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_", length = 10)
	private Long id;
	
	@Column(name = "TRDDE_MONEY_", precision=20, scale=2)
	private BigDecimal trddeMoney;
	
	@Column(name = "BENEFIT_MONEY_", precision=20, scale=2)
	private BigDecimal benefitMoney;
	
	@Column(name = "BALANCE_", precision=20, scale=2)
	private BigDecimal balance;
	
	@Column(name = "WECHAT_PAYMENT_", precision=20, scale=2)
	private BigDecimal weChatPayment;
	
	@Column(name = "H5_PAYMENT_", precision=20, scale=2)
	private BigDecimal h5Payment;
	
	@Column(name = "OFFLINE_PAYMENT_", precision=20, scale=2)
	private BigDecimal offLinePayment;
	
	@Column(name = "SHOU_XIN_PAYMENT_", precision=10, scale=2)
	private BigDecimal shouXinPayment;
	
	@Column(name = "ADD_TIME_")
	private Date addTime;
	
	@Column(name = "TYPE_",length=1)
	private String type;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public BigDecimal getWeChatPayment() {
		return weChatPayment;
	}
	public void setWeChatPayment(BigDecimal weChatPayment) {
		this.weChatPayment = weChatPayment;
	}
	public BigDecimal getH5Payment() {
		return h5Payment;
	}
	public void setH5Payment(BigDecimal h5Payment) {
		this.h5Payment = h5Payment;
	}
	public BigDecimal getOffLinePayment() {
		return offLinePayment;
	}
	public void setOffLinePayment(BigDecimal offLinePayment) {
		this.offLinePayment = offLinePayment;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	public BigDecimal getTrddeMoney() {
		return trddeMoney;
	}
	public void setTrddeMoney(BigDecimal trddeMoney) {
		this.trddeMoney = trddeMoney;
	}
	public BigDecimal getBenefitMoney() {
		return benefitMoney;
	}
	public void setBenefitMoney(BigDecimal benefitMoney) {
		this.benefitMoney = benefitMoney;
	}
	public BigDecimal getShouXinPayment() {
		return shouXinPayment;
	}
	public void setShouXinPayment(BigDecimal shouXinPayment) {
		this.shouXinPayment = shouXinPayment;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
