package cc.messcat.gjfeng.common.vo.app;

import java.math.BigDecimal;
import java.util.Date;

/**
 * GjfMemberDiviHistory entity. @author MyEclipse Persistence Tools
 */
public class MemberTradeDiviVo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields
	private Long id;
	
	private String diviNo;
	
	private BigDecimal diviNum;
	
	private BigDecimal diviMoney;
	
	private Date addTime;
	
	private String payType;
	
	private String payStatus;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDiviNo() {
		return diviNo;
	}

	public void setDiviNo(String diviNo) {
		this.diviNo = diviNo;
	}

	public BigDecimal getDiviNum() {
		return diviNum;
	}

	public void setDiviNum(BigDecimal diviNum) {
		this.diviNum = diviNum;
	}

	public BigDecimal getDiviMoney() {
		return diviMoney;
	}

	public void setDiviMoney(BigDecimal diviMoney) {
		this.diviMoney = diviMoney;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
}