package cc.messcat.gjfeng.entity;

import java.io.Serializable;
import java.math.BigDecimal;


public class GjfSetDividends implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2605067262517372834L;

	private Long id;

	private BigDecimal consumptionMin;
	
	private BigDecimal consumptionMax;
	
	private BigDecimal consumption;
	
	private String status;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public BigDecimal getConsumptionMin() {
		return consumptionMin;
	}


	public void setConsumptionMin(BigDecimal consumptionMin) {
		this.consumptionMin = consumptionMin;
	}


	public BigDecimal getConsumptionMax() {
		return consumptionMax;
	}


	public void setConsumptionMax(BigDecimal consumptionMax) {
		this.consumptionMax = consumptionMax;
	}


	public BigDecimal getConsumption() {
		return consumption;
	}


	public void setConsumption(BigDecimal consumption) {
		this.consumption = consumption;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}
	
}
