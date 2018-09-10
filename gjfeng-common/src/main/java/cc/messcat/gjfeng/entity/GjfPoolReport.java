package cc.messcat.gjfeng.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class GjfPoolReport implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private BigDecimal memberIncomePool;
	
	private BigDecimal memberExpendPool;
	
	private BigDecimal merchantsIncomePool;
	
	private BigDecimal merchantsExpendPool;
	
	private BigDecimal platFormIncomePool;
	
	private BigDecimal cityAgentIncomePool;
	
	private BigDecimal cityAgentExpendPool;
	
	private BigDecimal areaAgentIncomePool;
	
	private BigDecimal areaAgentExpendPool;
	
	private BigDecimal indiviAgentIncomePool;
	
	private BigDecimal indiviAgentExpendPool;
	
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
