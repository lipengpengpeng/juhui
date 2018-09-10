package cc.messcat.gjfeng.entity;

import java.util.Date;

/**
 * GjfOrderLog entity. @author MyEclipse Persistence Tools
 */
public class GjfOrderLog implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields
	private Long id;
	
	private GjfOrderInfo orderId;
	
	private String logMsg;
	
	private Date logTime;
	
	private String logRole;
	
	private String logUserName;
	
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