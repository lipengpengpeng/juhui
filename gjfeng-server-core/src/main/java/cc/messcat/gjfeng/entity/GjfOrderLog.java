package cc.messcat.gjfeng.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * GjfOrderLog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "gjf_order_log")
public class GjfOrderLog implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_", length = 10)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="ORDER_ID_")
	private GjfOrderInfo orderId;
	
	@Column(name = "LOG_MSG", length = 150)
	private String logMsg;
	
	@Column(name = "LOG_TIME_")
	private Date logTime;
	
	@Column(name = "LOG_ROLE", length = 20)
	private String logRole;
	
	@Column(name = "LOG_USER_NAME", length = 30)
	private String logUserName;
	
	@Column(name = "LOG_ORDER_STATUS", length = 1)
	private String logOrderStatus;

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public GjfOrderInfo getOrderId() {
		return this.orderId;
	}

	public void setOrderId(GjfOrderInfo orderId) {
		this.orderId = orderId;
	}

	public String getLogMsg() {
		return this.logMsg;
	}

	public void setLogMsg(String logMsg) {
		this.logMsg = logMsg;
	}

	public Date getLogTime() {
		return this.logTime;
	}

	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}

	public String getLogRole() {
		return this.logRole;
	}

	public void setLogRole(String logRole) {
		this.logRole = logRole;
	}

	public String getLogUserName() {
		return this.logUserName;
	}

	public void setLogUserName(String logUserName) {
		this.logUserName = logUserName;
	}

	public String getLogOrderStatus() {
		return this.logOrderStatus;
	}

	public void setLogOrderStatus(String logOrderStatus) {
		this.logOrderStatus = logOrderStatus;
	}

}