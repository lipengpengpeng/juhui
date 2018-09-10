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
@Table(name = "gjf_withdraw_report")
public class GjfWithdrawReport implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_", length = 10)
	private Long id;
	
	@Column(name = "MONEY_", precision=10, scale=2)
	private BigDecimal money;
	
	@Column(name = "SUCCESS_MONEY_", precision=10, scale=2)
	private BigDecimal successMoney;
	
	@Column(name = "FAIL_MONEY_", precision=10, scale=2)
	private BigDecimal failMoney;
	
	@Column(name = "ADD_TIME_")
	private Date addTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public BigDecimal getSuccessMoney() {
		return successMoney;
	}

	public void setSuccessMoney(BigDecimal successMoney) {
		this.successMoney = successMoney;
	}

	public BigDecimal getFailMoney() {
		return failMoney;
	}

	public void setFailMoney(BigDecimal failMoney) {
		this.failMoney = failMoney;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	
	
 
}
