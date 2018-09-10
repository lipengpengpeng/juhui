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
@Table(name = "gjf_weal_payout_report")
public class GjfWealPayoutReport implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_", length = 10)
	private Long id;
	
	@Column(name = "POOL_MONEY_", precision=10, scale=2)
	private BigDecimal poolMoney;
	
	@Column(name = "PAYOUT_MONEY_", precision=10, scale=2)
	private BigDecimal payoutMoney;
	
	@Column(name = "DIVIDENDS_PRICE_", precision=10, scale=2)
	private BigDecimal dividendsPrice;
	
	@Column(name = "PRECIPITATION_AMOUNT_", precision=10, scale=2)
	private BigDecimal precipitationAmount;
	
	@Column(name = "TYPE_", length = 1)
	private String type;
	
	@Column(name = "ADD_TIME_")
	private Date addTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getPoolMoney() {
		return poolMoney;
	}

	public void setPoolMoney(BigDecimal poolMoney) {
		this.poolMoney = poolMoney;
	}

	public BigDecimal getPayoutMoney() {
		return payoutMoney;
	}

	public void setPayoutMoney(BigDecimal payoutMoney) {
		this.payoutMoney = payoutMoney;
	}

	public BigDecimal getDividendsPrice() {
		return dividendsPrice;
	}

	public void setDividendsPrice(BigDecimal dividendsPrice) {
		this.dividendsPrice = dividendsPrice;
	}

	public BigDecimal getPrecipitationAmount() {
		return precipitationAmount;
	}

	public void setPrecipitationAmount(BigDecimal precipitationAmount) {
		this.precipitationAmount = precipitationAmount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	
	
	
	
}
