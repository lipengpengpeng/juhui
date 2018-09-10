package cc.messcat.gjfeng.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "gjf_benefit_mq_log")
public class GjfBenefitMqLog implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_", length = 10)
	private Long id;
	
	@Column(name = "TRADE_NO_", length=50)
	private String tradeNo;
	
	@Column(name = "MQ_NAME_", length=50)
	private String mqName;
	
	@Column(name = "ADD_TIME_")
	private Date addTime;
	
	@Column(name = "FINSH_TIME_")
	private Date finshTime;
	
	@Column(name = "FINSH_NUM_")
	private Long finshNum;
	
	@Column(name = "FINSH_STATUS_", length=1)
	private String finshStatus;

	public GjfBenefitMqLog() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GjfBenefitMqLog(Long id, String tradeNo, String mqName, Date addTime, Date finshTime, Long finshNum,
		String finshStatus) {
		super();
		this.id = id;
		this.tradeNo = tradeNo;
		this.mqName = mqName;
		this.addTime = addTime;
		this.finshTime = finshTime;
		this.finshNum = finshNum;
		this.finshStatus = finshStatus;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getMqName() {
		return mqName;
	}

	public void setMqName(String mqName) {
		this.mqName = mqName;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Date getFinshTime() {
		return finshTime;
	}

	public void setFinshTime(Date finshTime) {
		this.finshTime = finshTime;
	}

	public Long getFinshNum() {
		return finshNum;
	}

	public void setFinshNum(Long finshNum) {
		this.finshNum = finshNum;
	}

	public String getFinshStatus() {
		return finshStatus;
	}

	public void setFinshStatus(String finshStatus) {
		this.finshStatus = finshStatus;
	}


}