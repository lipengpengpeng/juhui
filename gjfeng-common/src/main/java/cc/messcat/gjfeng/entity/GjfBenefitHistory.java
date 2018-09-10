package cc.messcat.gjfeng.entity;

import java.math.BigDecimal;
import java.util.Date;

public class GjfBenefitHistory implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	private Long id;
	
	private BigDecimal benefitMoney;
	
	private BigDecimal benefitMoneyBf;
	
	private BigDecimal benefitMoneyAf;
	
	private BigDecimal unitPrice;
	
	private Date addTime;
	
	private String benefitType;
	
	private Date benefitTime;
	
	private Date tradeTime;
	
	private String tradeStatus;
	
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