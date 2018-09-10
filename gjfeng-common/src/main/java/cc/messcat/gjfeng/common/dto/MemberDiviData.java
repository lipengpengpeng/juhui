package cc.messcat.gjfeng.common.dto;

import java.math.BigDecimal;
/**
 * 用于计算会员实时分红数
 * @author Stephen
 *
 */
public class MemberDiviData {
	private BigDecimal cumulativeMoney; //用户累计消费金额
	private BigDecimal diviTotalMoney; //用户累计分红金额
	private BigDecimal diviNum; //用户分红权个数
	
	public MemberDiviData() {
		
	}
	
	public MemberDiviData(BigDecimal cumulativeMoney, BigDecimal diviTotalMoney, BigDecimal diviNum) {
		super();
		this.cumulativeMoney = cumulativeMoney;
		this.diviTotalMoney = diviTotalMoney;
		this.diviNum = diviNum;
	}

	public BigDecimal getCumulativeMoney() {
		return cumulativeMoney;
	}
	public void setCumulativeMoney(BigDecimal cumulativeMoney) {
		this.cumulativeMoney = cumulativeMoney;
	}
	public BigDecimal getDiviTotalMoney() {
		return diviTotalMoney;
	}
	public void setDiviTotalMoney(BigDecimal diviTotalMoney) {
		this.diviTotalMoney = diviTotalMoney;
	}
	public BigDecimal getDiviNum() {
		return diviNum;
	}
	public void setDiviNum(BigDecimal diviNum) {
		this.diviNum = diviNum;
	}
	
	
}
