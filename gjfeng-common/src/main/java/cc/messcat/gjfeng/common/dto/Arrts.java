package cc.messcat.gjfeng.common.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class Arrts implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private List<Long> id;
	private BigDecimal price;
	private Long repertory;
	private List<String> value;
	private Long proStockId;
	private BigDecimal standardPrice;
	private BigDecimal honourPrice;
	
	public Arrts() {
	
	}	

	
	public Arrts(List<Long> id, BigDecimal price, Long repertory) {
		super();
		this.id = id;
		this.price = price;
		this.repertory = repertory;
	}


	public Arrts(BigDecimal price, Long repertory, List<String> value) {
		super();
		this.price = price;
		this.repertory = repertory;
		this.value = value;
	}


	public Arrts(List<Long> id, BigDecimal price, Long repertory, List<String> value) {
		super();
		this.id = id;
		this.price = price;
		this.repertory = repertory;
		this.value = value;
	}

	

	public Arrts(BigDecimal price, Long repertory, List<String> value, Long proStockId) {
		super();
		this.price = price;
		this.repertory = repertory;
		this.value = value;
		this.proStockId = proStockId;
	}
	
	public Arrts(BigDecimal price, Long repertory, List<String> value, Long proStockId,BigDecimal standardPrice,BigDecimal honourPrice) {
		super();
		this.price = price;
		this.repertory = repertory;
		this.value = value;
		this.proStockId = proStockId;
		this.standardPrice=standardPrice;
		this.honourPrice=honourPrice;
	}


	public List<Long> getId() {
		return id;
	}
	
	public void setId(List<Long> id) {
		this.id = id;
	}


	public BigDecimal getPrice() {
		return price;
	}


	public void setPrice(BigDecimal price) {
		this.price = price;
	}


	public Long getRepertory() {
		return repertory;
	}


	public void setRepertory(Long repertory) {
		this.repertory = repertory;
	}


	public List<String> getValue() {
		return value;
	}


	public void setValue(List<String> value) {
		this.value = value;
	}


	public Long getProStockId() {
		return proStockId;
	}


	public void setProStockId(Long proStockId) {
		this.proStockId = proStockId;
	}


	public BigDecimal getStandardPrice() {
		return standardPrice;
	}


	public void setStandardPrice(BigDecimal standardPrice) {
		this.standardPrice = standardPrice;
	}


	public BigDecimal getHonourPrice() {
		return honourPrice;
	}


	public void setHonourPrice(BigDecimal honourPrice) {
		this.honourPrice = honourPrice;
	}

	
	
}
