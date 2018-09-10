package cc.messcat.gjfeng.entity;

import java.util.Date;

public class GjfErrorInfo {
	
	private Long id;
	private String errMsg;
	private Date errTime;
	private String errStatus;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	public Date getErrTime() {
		return errTime;
	}
	public void setErrTime(Date errTime) {
		this.errTime = errTime;
	}
	public String getErrStatus() {
		return errStatus;
	}
	public void setErrStatus(String errStatus) {
		this.errStatus = errStatus;
	}
	
}
