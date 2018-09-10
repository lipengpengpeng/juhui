package cc.messcat.gjfeng.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "gjf_error_info")
public class GjfErrorInfo implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_", length = 10)
	private Long id;
	
	@Column(name = "ERR_MSG_", length=300)
	private String errMsg;
	
	@Column(name = "ERR_TIME_")
	private Date errTime;
	
	@Column(name = "ERR_STATUS_", length=1)
	private String errStatus;

	public GjfErrorInfo() {
		super();
	}
	

	public GjfErrorInfo(Long id, String errMsg, Date errTime, String errStatus) {
		super();
		this.id = id;
		this.errMsg = errMsg;
		this.errTime = errTime;
		this.errStatus = errStatus;
	}

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