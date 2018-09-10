package cc.messcat.gjfeng.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "gjf_set_dividends")
public class GjfSetDividends implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2605067262517372834L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_", length = 10)
	private Long id;
	
	@Column(name = "CONSUMPTION_MIN_",  precision=20, scale=2)
	private BigDecimal consumptionMin;
	
	@Column(name = "CONSUMPTION_MAX_",  precision=20, scale=2)
	private BigDecimal consumptionMax;
	
	@Column(name = "CONSUMPTION_",  precision=20, scale=2)
	private BigDecimal consumption;
	
	
	@Column(name = "STAUS_",  precision=20, scale=2)
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
