package cc.messcat.gjfeng.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "gjf_member_trade_indi")
public class GjfMemberTradeIndi implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_", length = 10)
	private Long id;
	
	@Column(name = "TRADE_NO_",length=50)
	private String tradeNo;
	
	@ManyToOne
	@JoinColumn(name = "MEMBER_ID_")
	private GjfMemberInfo memberId;
	
	@ManyToOne
	@JoinColumn(name = "BUY_MEMBER_ID_")
	private GjfMemberInfo buyMemberId;

	@Column(name = "BUY_MONEY_", precision = 20, scale = 2)
	private BigDecimal buyMoney;
	
	@Column(name = "BENEFIT_MONEY_", precision = 20, scale = 2)
	private BigDecimal benefitMoney;
	
	@Column(name = "ADD_TIME_")
	private Date addTime;
	

	public GjfMemberTradeIndi() {
		super();
	}

	public GjfMemberTradeIndi(Long id, String tradeNo, GjfMemberInfo memberId, GjfMemberInfo buyMemberId, BigDecimal buyMoney,
		BigDecimal benefitMoney, Date addTime) {
		super();
		this.id = id;
		this.tradeNo = tradeNo;
		this.memberId = memberId;
		this.buyMemberId = buyMemberId;
		this.buyMoney = buyMoney;
		this.benefitMoney = benefitMoney;
		this.addTime = addTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public GjfMemberInfo getMemberId() {
		return memberId;
	}

	public void setMemberId(GjfMemberInfo memberId) {
		this.memberId = memberId;
	}

	public GjfMemberInfo getBuyMemberId() {
		return buyMemberId;
	}

	public void setBuyMemberId(GjfMemberInfo buyMemberId) {
		this.buyMemberId = buyMemberId;
	}

	public BigDecimal getBuyMoney() {
		return buyMoney;
	}

	public void setBuyMoney(BigDecimal buyMoney) {
		this.buyMoney = buyMoney;
	}

	public BigDecimal getBenefitMoney() {
		return benefitMoney;
	}

	public void setBenefitMoney(BigDecimal benefitMoney) {
		this.benefitMoney = benefitMoney;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

}