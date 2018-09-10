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
@Table(name = "gjf_benefit_history")
public class GjfBenefitHistory implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_", length = 10)
	private Long id;
	
	@Column(name = "BENEFIT_MONEY_", precision=10, scale=2)
	private BigDecimal benefitMoney;
	
	@Column(name = "BENEFIT_MONEY_BF_", precision=10, scale=2)
	private BigDecimal benefitMoneyBf;
	
	@Column(name = "BENEFIT_MONEY_AF_", precision=10, scale=2)
	private BigDecimal benefitMoneyAf;
	
	@Column(name = "UNIT_PRICE_", precision=10, scale=2)
	private BigDecimal unitPrice;
	
	@Column(name = "ADD_TIME_")
	private Date addTime;
	
	@Column(name = "BENEFIT_TYPE_", length=2)
	private String benefitType;
	
	@Column(name = "BENEFIT_TIME_")
	private Date benefitTime;
	
	@Column(name = "TRADE_TIME_")
	private Date tradeTime;
	
	@Column(name = "TRADE_STATUS_", length=1)
	private String tradeStatus;
	
	@Column(name = "IS_ACTIVITY_", length=1)
	private String isActivity;
	
	public GjfBenefitHistory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GjfBenefitHistory(Long id, BigDecimal benefitMoney, BigDecimal benefitMoneyBf, BigDecimal benefitMoneyAf,
		BigDecimal unitPrice, Date addTime, String benefitType, Date benefitTime, Date tradeTime, String tradeStatus) {
		super();
		this.id = id;
		this.benefitMoney = benefitMoney;
		this.benefitMoneyBf = benefitMoneyBf;
		this.benefitMoneyAf = benefitMoneyAf;
		this.unitPrice = unitPrice;
		this.addTime = addTime;
		this.benefitType = benefitType;
		this.benefitTime = benefitTime;
		this.tradeTime = tradeTime;
		this.tradeStatus = tradeStatus;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getBenefitMoney() {
		return benefitMoney;
	}

	public void setBenefitMoney(BigDecimal benefitMoney) {
		this.benefitMoney = benefitMoney;
	}

	public BigDecimal getBenefitMoneyBf() {
		return benefitMoneyBf;
	}

	public void setBenefitMoneyBf(BigDecimal benefitMoneyBf) {
		this.benefitMoneyBf = benefitMoneyBf;
	}

	public BigDecimal getBenefitMoneyAf() {
		return benefitMoneyAf;
	}

	public void setBenefitMoneyAf(BigDecimal benefitMoneyAf) {
		this.benefitMoneyAf = benefitMoneyAf;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getBenefitType() {
		return benefitType;
	}

	public void setBenefitType(String benefitType) {
		this.benefitType = benefitType;
	}

	public Date getBenefitTime() {
		return benefitTime;
	}

	public void setBenefitTime(Date benefitTime) {
		this.benefitTime = benefitTime;
	}

	public Date getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(Date tradeTime) {
		this.tradeTime = tradeTime;
	}

	public String getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public String getIsActivity() {
		return isActivity;
	}

	public void setIsActivity(String isActivity) {
		this.isActivity = isActivity;
	}

	

}