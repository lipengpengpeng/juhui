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
@Table(name = "gjf_recharge_paid")
public class GjfRechargePaid implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -988393262416187013L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_", length = 10)
	private Long id;
	
	@Column(name = "ACC_NO_", length = 100)
	private String accNo;
	
	@Column(name = "HOLER_NAME_", length = 100)
	private String holerName;
	
	@Column(name = "OPERATION_NAME_", length = 100)
	private String operationName;
	
	@Column(name = "TANAMT_")
	private BigDecimal tanAmt;
	
	@Column(name = "ADD_TIME_")
	private Date addTime;
	
	@Column(name = "ORDER_SN_",length = 100)
	private String orderSn;
	
	@Column(name = "ORDER_STATUS_",length = 1)
	private String orderStatus;
	
	@Column(name = "RMARK_",length = 500)
	private  String rmark;

	public String getRmark() {
		return rmark;
	}

	public void setRmark(String rmark) {
		this.rmark = rmark;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccNo() {
		return accNo;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	public String getHolerName() {
		return holerName;
	}

	public void setHolerName(String holerName) {
		this.holerName = holerName;
	}

	public String getOperationName() {
		return operationName;
	}

	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}

	

	public BigDecimal getTanAmt() {
		return tanAmt;
	}

	public void setTanAmt(BigDecimal tanAmt) {
		this.tanAmt = tanAmt;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getOrderSn() {
		return orderSn;
	}

	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	
	
}
