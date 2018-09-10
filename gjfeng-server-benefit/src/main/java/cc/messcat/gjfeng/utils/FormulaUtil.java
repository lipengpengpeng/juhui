package cc.messcat.gjfeng.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FormulaUtil {

	
	/**
	 * @描述 计算直推商家和直推会员分红权额度
	 * @author Karhs
	 * @date 2016年12月28日
	 * @param totalBenefit	直推让利金额
	 * @param sysRatio		子系统的占比
	 * @param directRatio   直推占比
	 * @return
	 */
	public static BigDecimal directMerOrMemFormula(Double totalBenefit,Double sysRatio,Double directRatio){
		return new BigDecimal(totalBenefit).multiply(new BigDecimal(sysRatio/100)).multiply(new BigDecimal(directRatio/100)).setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
	/**
	 * @描述 计算分红池会员或商家的分红单价额度
	 * @author Karhs
	 * @date 2016年12月28日
	 * @param sysTotalBenefit 系统总额
	 * @param issueRatio	    发放比例
	 * @param benefitTotalNum 分红权总数
	 * @return
	 */
	public static BigDecimal merOrMemFormula(Double sysTotalBenefit,Double issueRatio,Double benefitTotalNum){
		return new BigDecimal(sysTotalBenefit).divide(new BigDecimal(issueRatio)).divide(new BigDecimal(benefitTotalNum), 2, RoundingMode.HALF_UP);
	}
	
	/**
	 * @描述 计算代理获取到的分红权额度
	 * @author Karhs
	 * @date 2016年12月28日
	 * @param totalBenefit 直推让利金额
	 * @param sysRatio     子系统的占比
	 * @param agentRatio   代理占比
	 * @return
	 */
	public static BigDecimal agentFormula(Double totalBenefit,Double sysRatio,Double agentRatio){
		return new BigDecimal(totalBenefit).multiply(new BigDecimal(sysRatio/100)).multiply(new BigDecimal(agentRatio/100)).setScale(2, BigDecimal.ROUND_HALF_UP);
	}
}
