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
@Table(name = "gjf_pool_report")
public class GjfPoolReport implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_", length = 10)
	private Long id;
	
	@Column(name = "MEMBER_INCOME_POOL_", precision=10, scale=2)
	private BigDecimal memberIncomePool;
	
	@Column(name = "MEMBER_EXPEND_POOL_", precision=10, scale=2)
	private BigDecimal memberExpendPool;
	
	@Column(name = "MERCHANTS_INCOME_POOL_", precision=10, scale=2)
	private BigDecimal merchantsIncomePool;
	
	@Column(name = "MERCHANTS_EXPEND_POOL_", precision=10, scale=2)
	private BigDecimal merchantsExpendPool;
	
	@Column(name = "PLATFORM_INCOME_POOL_", precision=10, scale=2)
	private BigDecimal platFormIncomePool;
	
	@Column(name = "CITYAGENT_INCOME_POOL_", precision=10, scale=2)
	private BigDecimal cityAgentIncomePool;
	
	@Column(name = "CITYAGENT_EXPEND_POOL_", precision=10, scale=2)
	private BigDecimal cityAgentExpendPool;
	
	@Column(name = "AREAAGENT_INCOME_POOL_", precision=10, scale=2)
	private BigDecimal areaAgentIncomePool;
	
	@Column(name = "AREAAGENT_EXPEND_POOL_", precision=10, scale=2)
	private BigDecimal areaAgentExpendPool;
	
	@Column(name = "INDIVIAGENT_INCOME_POOL_", precision=10, scale=2)
	private BigDecimal indiviAgentIncomePool;
	
	@Column(name = "INDIVIAGENT_EXPEND_POOL_", precision=10, scale=2)
	private BigDecimal indiviAgentExpendPool;
	
	@Column(name = "ADD_TIME_")
	private Date addTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getMemberIncomePool() {
		return memberIncomePool;
	}

	public void setMemberIncomePool(BigDecimal memberIncomePool) {
		this.memberIncomePool = memberIncomePool;
	}

	public BigDecimal getMemberExpendPool() {
		return memberExpendPool;
	}

	public void setMemberExpendPool(BigDecimal memberExpendPool) {
		this.memberExpendPool = memberExpendPool;
	}

	public BigDecimal getMerchantsIncomePool() {
		return merchantsIncomePool;
	}

	public void setMerchantsIncomePool(BigDecimal merchantsIncomePool) {
		this.merchantsIncomePool = merchantsIncomePool;
	}

	public BigDecimal getMerchantsExpendPool() {
		return merchantsExpendPool;
	}

	public void setMerchantsExpendPool(BigDecimal merchantsExpendPool) {
		this.merchantsExpendPool = merchantsExpendPool;
	}

	public BigDecimal getPlatFormIncomePool() {
		return platFormIncomePool;
	}

	public void setPlatFormIncomePool(BigDecimal platFormIncomePool) {
		this.platFormIncomePool = platFormIncomePool;
	}

	public BigDecimal getCityAgentIncomePool() {
		return cityAgentIncomePool;
	}

	public void setCityAgentIncomePool(BigDecimal cityAgentIncomePool) {
		this.cityAgentIncomePool = cityAgentIncomePool;
	}

	public BigDecimal getCityAgentExpendPool() {
		return cityAgentExpendPool;
	}

	public void setCityAgentExpendPool(BigDecimal cityAgentExpendPool) {
		this.cityAgentExpendPool = cityAgentExpendPool;
	}

	public BigDecimal getAreaAgentIncomePool() {
		return areaAgentIncomePool;
	}

	public void setAreaAgentIncomePool(BigDecimal areaAgentIncomePool) {
		this.areaAgentIncomePool = areaAgentIncomePool;
	}

	public BigDecimal getAreaAgentExpendPool() {
		return areaAgentExpendPool;
	}

	public void setAreaAgentExpendPool(BigDecimal areaAgentExpendPool) {
		this.areaAgentExpendPool = areaAgentExpendPool;
	}

	public BigDecimal getIndiviAgentIncomePool() {
		return indiviAgentIncomePool;
	}

	public void setIndiviAgentIncomePool(BigDecimal indiviAgentIncomePool) {
		this.indiviAgentIncomePool = indiviAgentIncomePool;
	}

	public BigDecimal getIndiviAgentExpendPool() {
		return indiviAgentExpendPool;
	}

	public void setIndiviAgentExpendPool(BigDecimal indiviAgentExpendPool) {
		this.indiviAgentExpendPool = indiviAgentExpendPool;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	
	
	

}
