package cc.messcat.gjfeng.dao.benefit;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.transform.Transformers;
import org.hibernate.type.BigDecimalType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import cc.messcat.gjfeng.common.bean.Pager;
import cc.messcat.gjfeng.common.exception.MyException;
import cc.messcat.gjfeng.common.util.BeanUtil;
import cc.messcat.gjfeng.common.util.ObjValid;
import cc.messcat.gjfeng.common.vo.app.MemberBankVo;
import cc.messcat.gjfeng.common.vo.app.MemberTradeBenefitVo;
import cc.messcat.gjfeng.common.vo.app.ResultVo;
import cc.messcat.gjfeng.dao.BaseHibernateDaoImpl;
import cc.messcat.gjfeng.entity.GjfBenefitDay;
import cc.messcat.gjfeng.entity.GjfBenefitHistory;
import cc.messcat.gjfeng.entity.GjfBenefitPool;
import cc.messcat.gjfeng.entity.GjfMemberConsumptiomNum;
import cc.messcat.gjfeng.entity.GjfMemberInfo;
import cc.messcat.gjfeng.entity.GjfMemberTradeBenefit;
import cc.messcat.gjfeng.entity.GjfMemberTradeDiviHistory;
import cc.messcat.gjfeng.entity.GjfOrderInfo;
import cc.messcat.gjfeng.entity.GjfSetDividends;
import cc.messcat.gjfeng.entity.GjfSpecialMemberTradeDiviHistory;

@Repository("gjfBenefitInfoDao")
public class GjfBenefitInfoDaoImpl extends BaseHibernateDaoImpl<Serializable>implements GjfBenefitInfoDao {

	/*
	 * 求所有用户or商家的分红总数
	 * 
	 * @see
	 * cc.messcat.gjfeng.dao.GjfBenefitInfoDao#queryTotalDiviNumByMemberType(
	 * java.lang.String)
	 */
	@Override
	public BigDecimal queryTotalDiviNumByMemberType(String memberType) {
		String sql = "";
		if (memberType.equals("0")) {
			sql = "SELECT IFNULL(SUM(m.DIVIDENDS_NUM_),0) FROM gjf_member_info m WHERE m.STATUS_='1' AND m.IS_DEL_='1'";
		} else {
			sql = "SELECT IFNULL(SUM(s.STORE_DIVIDENDS_NUM_),0) FROM gjf_store_info s WHERE s.STORE_STATUS_='1' AND s.IS_DEL_='1'";
		}
		return (BigDecimal) getCurrentSession().createSQLQuery(sql).uniqueResult();
	}

	/*
	 * 每隔几分钟定时计算会员和商家所获得的分红
	 * 
	 * @see cc.messcat.gjfeng.dao.GjfBenefitInfoDao#updateBenefit()
	 */
	@Override
	public void updateBenefit() throws NumberFormatException, SQLException {
		String procdureBenefit = "{Call gjf_calculate_benefit_pro()}";
		getCurrentSession().createSQLQuery(procdureBenefit).executeUpdate();
	}

	/*
	 * 计算代理每天的分红
	 * 
	 * @see cc.messcat.gjfeng.dao.GjfBenefitInfoDao#updateAgentBenefit()
	 */
	@Override
	public void updateAgentBenefitNotify() throws NumberFormatException, SQLException {
		String procdureAgent = "{Call gjf_calculate_agent_pro()}";
		getCurrentSession().createSQLQuery(procdureAgent).executeUpdate();
	}

	/*
	 * 手动或自动发放会员分红
	 * 
	 * @see cc.messcat.gjfeng.dao.GjfBenefitInfoDao#updateMemberBenefit()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ResultVo updateMemberBenefit(GjfBenefitDay benefitDay) {
		// 更新所有分红记录里面用户的资金情况和记录的交易时间、状态、实际分红单价
		Map<String, Object> paraMap = new HashMap<String, Object>();
		StringBuffer sql = new StringBuffer(
				"UPDATE gjf_member_trade_divi_history d INNER JOIN gjf_member_info m ON m.ID_=d.MEMBER_ID_ SET d.TRADE_REAL_UNIT_=:realUnit,");
		sql.append(
				"d.TRADE_MONEY_=(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_)),");
		sql.append(
				"d.TRADE_TIME_=NOW(),d.TRADE_STATUS_='1',m.BALANCE_MONEY_=m.BALANCE_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_)),");
		sql.append(
				"m.CONSUMPTION_MONEY_=m.CONSUMPTION_MONEY_-(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_)),");
		sql.append(
				"m.WITHDRAWAL_MONEY_=m.WITHDRAWAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_)),");
		sql.append(
				"m.DIVIDENDS_TOTAL_MONEY_=m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_)) ");
		//start每领取1000后扣除1分红权
		sql.append(",m.DIVIDENDS_NUM_=m.DIVIDENDS_NUM_-(IF(m.DIVIDENDS_TOTAL_MONEY_-m.LAST_TIME_DIVI_MONEY_>=1000,floor((m.DIVIDENDS_TOTAL_MONEY_-m.LAST_TIME_DIVI_MONEY_)/1000),0))");
		sql.append(", m.DEDUCT_DIVI_NUM_=m.DEDUCT_DIVI_NUM_+(IF(m.DIVIDENDS_TOTAL_MONEY_-m.LAST_TIME_DIVI_MONEY_>=1000,floor((m.DIVIDENDS_TOTAL_MONEY_-m.LAST_TIME_DIVI_MONEY_)/1000),0))");
		sql.append(", m.LAST_TIME_DIVI_MONEY_=m.LAST_TIME_DIVI_MONEY_+(IF(m.DIVIDENDS_TOTAL_MONEY_-m.LAST_TIME_DIVI_MONEY_>=1000,floor((m.DIVIDENDS_TOTAL_MONEY_-m.LAST_TIME_DIVI_MONEY_)/1000)*1000,0))");
		//end每领取1000后扣除1分红权
		sql.append(
				"WHERE TO_DAYS(NOW())-TO_DAYS(d.ADD_TIME_)=0 AND d.TRADE_TYPE_='2' AND d.TRADE_STATUS_='0' AND m.CONSUMPTION_MONEY_ > 0 AND m.STATUS_='1' AND m.IS_DEL_='1'");

		// 查询最新的单价
		double unitPrice = 0d;
		Object unitPrice1 = getCurrentSession()
				.createQuery("select b.unitPrice from GjfBenefitInfo b where b.ratioType='0'").uniqueResult();
		if (ObjValid.isNotValid(unitPrice1)) {
			// 没有手动设置单价，直接不给予发放
			throw new MyException(400, "没有设置会员分红单价，请先设置分红单价再方法", null);
		}
		unitPrice = Double.parseDouble(String.valueOf(unitPrice1));
		paraMap.put("realUnit", unitPrice);
		getCurrentSession().createSQLQuery(sql.toString()).setProperties(paraMap).executeUpdate();

		// 查询总的会员分红金额
		String sql1 = "SELECT IFNULL(SUM(TRADE_MONEY_),0),IFNULL(SUM(TRADE_DIVI_NUM_),0) FROM gjf_member_trade_divi_history WHERE TO_DAYS(NOW())-TO_DAYS(ADD_TIME_)=0 AND TRADE_TYPE_='2' AND TRADE_STATUS_='1'";
		List<Object[]> toatlTrade = getCurrentSession().createSQLQuery(sql1).list();

		double totalMoney = Double.parseDouble(String.valueOf(toatlTrade.get(0)[0]));
		double totalDiviNum = Double.parseDouble(String.valueOf(toatlTrade.get(0)[1]));
		/*
		 * if (totalMoney==0) { return new ResultVo(400, "会员发放交易总金额为0，分红结算失败",
		 * null); } if (totalDiviNum==0) { throw new MyException(400,
		 * "会员发放交易总分红权为0，分红结算失败", null); }
		 */

		paraMap.put("benefitMoney", totalMoney);
		// 更新资金池历史的交易状态和实际分红单价、交易总额、交易后资金池的额度
		String sql2 = "UPDATE gjf_benefit_history SET BENEFIT_MONEY_=:benefitMoney,BENEFIT_MONEY_BF_=(SELECT MEMBER_SYS_POOL_CUR_ FROM gjf_benefit_pool),BENEFIT_MONEY_AF_=BENEFIT_MONEY_BF_-:benefitMoney,"
				+ "UNIT_PRICE_=:realUnit,TRADE_TIME_=NOW(),TRADE_STATUS_='1' WHERE BENEFIT_TYPE_='0' AND TO_DAYS(NOW())-TO_DAYS(BENEFIT_TIME_)=1 AND TRADE_STATUS_='0'";
		getCurrentSession().createSQLQuery(sql2).setProperties(paraMap).executeUpdate();

		// 更新资金池的金额
		String sql3 = "UPDATE gjf_benefit_pool SET MEMBER_SYS_POOL_CUR_=MEMBER_SYS_POOL_CUR_- ?";
		getCurrentSession().createSQLQuery(sql3).setParameter(0, totalMoney).executeUpdate();

		// 更新每天统计记录值
		// String sql4="UPDATE gjf_benefit_day SET
		// MEMBER_REAL_MONEY_=?,MEMBER_REAL_UNIT_MONEY_=?,MEMBER_REAL_DIVI_NUM_=?
		// WHERE TRADE_DAY_=DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 1
		// DAY),'%Y%m%d')";
		// getCurrentSession().createSQLQuery(sql4).setParameter(0,
		// totalMoney).setParameter(1, unitPrice).setParameter(2,
		// totalDiviNum).executeUpdate();
		benefitDay.setMemberRealMoney(new BigDecimal(totalMoney));
		benefitDay.setMemberRealUnitMoney(new BigDecimal(unitPrice));
		benefitDay.setMemberRealDiviNum(new BigDecimal(totalDiviNum));
		benefitDay.setMemberUnitMoney(
				(benefitDay.getMemberIncomeMoney().divide(new BigDecimal(totalDiviNum), 2, BigDecimal.ROUND_DOWN))
						.multiply(new BigDecimal(totalDiviNum)));
		return new ResultVo(200, "会员分红发放成功", null);
	}

	/*
	 * 手动或自动发放商家分红
	 * 
	 * @see cc.messcat.gjfeng.dao.GjfBenefitInfoDao#updateMerchantsBenefit()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ResultVo updateMerchantsBenefit(GjfBenefitDay benefitDay) {
		// 更新所有分红记录里面用户的资金情况和记录的交易时间、状态、实际分红单价
		Map<String, Object> paraMap = new HashMap<String, Object>();
		// 新分红单价计算
		StringBuffer sql = new StringBuffer(
				"UPDATE gjf_member_trade_divi_history d INNER JOIN gjf_member_info m ON m.ID_=d.MEMBER_ID_ INNER JOIN gjf_store_info s ON s.MEMBER_ID_=m.ID_");
		sql.append(
				" SET d.TRADE_REAL_UNIT_=:realUnit,d.TRADE_MONEY_=(IF(s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_)),");
		sql.append(
				"m.WITHDRAWAL_MONEY_=m.WITHDRAWAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_)),m.BALANCE_MONEY_=m.BALANCE_MONEY_+"
						+ "(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_)),s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_=s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_-(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_)),");
		sql.append(
				"s.STORE_DIVIDENDS_TOTAL_MONEY_=s.STORE_DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_)),d.TRADE_TIME_=NOW(),d.TRADE_STATUS_='1'");
		//start每领取1000扣除1个分红权
		sql.append(", s.STORE_DIVIDENDS_NUM_=s.STORE_DIVIDENDS_NUM_-(IF(s.STORE_DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-s.LAST_TIME_DIVI_MONEY_>=1000,floor((s.STORE_DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-s.LAST_TIME_DIVI_MONEY_)/1000),0))");
		sql.append(", s.DEDUCT_DIVI_NUM_=s.DEDUCT_DIVI_NUM_+(IF(s.STORE_DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-s.LAST_TIME_DIVI_MONEY_>=1000,floor((s.STORE_DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-s.LAST_TIME_DIVI_MONEY_)/1000),0))");
		sql.append(",s.LAST_TIME_DIVI_MONEY_=s.LAST_TIME_DIVI_MONEY_+(IF(s.STORE_DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-s.LAST_TIME_DIVI_MONEY_>=1000,floor((s.STORE_DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-s.LAST_TIME_DIVI_MONEY_)/1000)*1000,0))");
		//start每领取1000扣除1个分红权
		sql.append(
				" WHERE TO_DAYS(NOW())-TO_DAYS(d.ADD_TIME_)=0 AND d.TRADE_TYPE_='3' AND d.TRADE_STATUS_='0' AND s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_ > 0 AND s.STORE_STATUS_='1' AND s.IS_DEL_='1'");

		// 查询最新的单价
		double unitPrice = 0d;
		Object unitPrice1 = getCurrentSession()
				.createQuery("select b.unitPrice from GjfBenefitInfo b where b.ratioType='1'").uniqueResult();
		if (ObjValid.isNotValid(unitPrice1)) {
			// 没有手动设置单价，直接不给予发放
			throw new MyException(400, "没有设置商家分红单价，请先设置分红单价再方法", null);
		}
		unitPrice = Double.parseDouble(String.valueOf(unitPrice1));
		paraMap.put("realUnit", unitPrice);
		getCurrentSession().createSQLQuery(sql.toString()).setProperties(paraMap).executeUpdate();

		// 查询总的会员分红金额
		String sql1 = "SELECT IFNULL(SUM(TRADE_MONEY_),0),IFNULL(SUM(TRADE_DIVI_NUM_),0) FROM gjf_member_trade_divi_history WHERE TO_DAYS(NOW())-TO_DAYS(ADD_TIME_)=0 AND TRADE_TYPE_='3' AND TRADE_STATUS_='1'";
		List<Object[]> toatlTrade = getCurrentSession().createSQLQuery(sql1).list();

		double totalMoney = Double.parseDouble(String.valueOf(toatlTrade.get(0)[0]));
		double totalDiviNum = Double.parseDouble(String.valueOf(toatlTrade.get(0)[1]));
		/*
		 * if (totalMoney==0) { throw new MyException(400, "商家发放交易总金额为0，分红结算失败",
		 * null); } if (totalDiviNum==0) { throw new MyException(400,
		 * "商家发放交易总分红权为0，分红结算失败", null); }
		 */
		paraMap.put("benefitMoney", totalMoney);
		// 更新资金池历史的交易状态和实际分红单价、交易总额、交易后资金池的额度
		String sql2 = "UPDATE gjf_benefit_history SET BENEFIT_MONEY_=:benefitMoney,BENEFIT_MONEY_BF_=(SELECT MERCHANT_SYS_POOL_CUR_ FROM gjf_benefit_pool),BENEFIT_MONEY_AF_=BENEFIT_MONEY_BF_-:benefitMoney,"
				+ "UNIT_PRICE_=:realUnit,TRADE_TIME_=NOW(),TRADE_STATUS_='1' WHERE BENEFIT_TYPE_='1' AND TO_DAYS(NOW())-TO_DAYS(BENEFIT_TIME_)=1 AND TRADE_STATUS_='0'";
		getCurrentSession().createSQLQuery(sql2).setProperties(paraMap).executeUpdate();

		// 更新资金池的金额
		String sql3 = "UPDATE gjf_benefit_pool SET MERCHANT_SYS_POOL_CUR_=MERCHANT_SYS_POOL_CUR_- ?";
		getCurrentSession().createSQLQuery(sql3).setParameter(0, totalMoney).executeUpdate();

		// String sql4="UPDATE gjf_benefit_day SET
		// MERCHANTS_REAL_MONEY_=?,MERCHANTS_REAL_UNIT_MONEY_=?,MERCHANTS_REAL_DIVI_NUM_=?
		// WHERE TRADE_DAY_=DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 1
		// DAY),'%Y%m%d')";
		// getCurrentSession().createSQLQuery(sql4).setParameter(0,
		// totalMoney).setParameter(1, unitPrice).setParameter(2,
		// totalDiviNum).executeUpdate();
		benefitDay.setMerchantsRealMoney(new BigDecimal(totalMoney));
		benefitDay.setMerchantsRealUnitMoney(new BigDecimal(unitPrice));
		benefitDay.setMerchantsRealDiviNum(new BigDecimal(totalDiviNum));
		benefitDay.setMerchantsUnitMoney(benefitDay.getMerchantsIncomeMoney()
				.divide(new BigDecimal(totalDiviNum), 2, BigDecimal.ROUND_DOWN).multiply(new BigDecimal(totalDiviNum)));
		return new ResultVo(200, "商家分红发放成功", null);
	}

	/*
	 * 手动结算发放代理分红
	 * 
	 * @see cc.messcat.gjfeng.dao.GjfBenefitInfoDao#updateAgentBenefit()
	 */
	@Override
	public ResultVo updateAgentBenefit(Long memberId, String token) {

		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("id", memberId);
		attrs.put("token", token);
		GjfMemberInfo memberInfo = query(GjfMemberInfo.class, attrs);
		if (ObjValid.isNotValid(memberInfo)) {
			return new ResultVo(400, "用户不存在", null);
		}

		// 1.获取用户的代理状态
		String identity = memberInfo.getIdentity();
		if (!identity.equals("1") && !identity.equals("2") && !identity.equals("3")) {
			return new ResultVo(400, "用户不是代理，分红结算失败", null);
		}
		String agentType = "0";
		if (identity.equals("1")) {
			agentType = "6";
		} else if (identity.equals("2")) {
			agentType = "5";
		} else if (identity.equals("3")) {
			agentType = "4";
		}

		// 2.求出当前所有未结算的当个用户的代理分红，并修改状态
		String sql1 = "SELECT IFNULL(SUM(TRADE_MONEY_),0) FROM gjf_member_trade_divi_history WHERE MEMBER_ID_=? AND TRADE_TYPE_=? AND TRADE_STATUS_='0'";
		BigDecimal tradeTotalMoney = new BigDecimal(String.valueOf(getCurrentSession().createSQLQuery(sql1)
				.setParameter(0, memberId).setParameter(1, agentType).uniqueResult()));
		if (tradeTotalMoney.doubleValue() == 0) {
			return new ResultVo(400, "用户结算总金额为0，分红结算失败", null);
		}
		String sql2 = "UPDATE gjf_member_trade_divi_history d SET d.TRADE_TIME_=NOW(),d.TRADE_STATUS_='1' WHERE MEMBER_ID_=? AND TRADE_TYPE_=? AND TRADE_STATUS_='0'";
		getCurrentSession().createSQLQuery(sql2).setParameter(0, memberId).setParameter(1, agentType).executeUpdate();

		// 3.修改用户的余额、提现金额、分红金额
		memberInfo.setBalanceMoney(memberInfo.getBalanceMoney().add(tradeTotalMoney));
		memberInfo.setWithdrawalMoney(memberInfo.getWithdrawalMoney().add(tradeTotalMoney));
		memberInfo.setAgentTotalMoney(memberInfo.getAgentTotalMoney().add(tradeTotalMoney));
		memberInfo.setAgentMoney(memberInfo.getAgentMoney().subtract(tradeTotalMoney));
		update(memberInfo);

		Map<String, Object> attrsPool = new HashMap<String, Object>();
		GjfBenefitPool benefitPool = query(GjfBenefitPool.class, attrsPool);
		if (ObjValid.isNotValid(benefitPool)) {
			throw new MyException(400, "资金池数据异常", null);
		}

		GjfBenefitHistory benefitHistory = null;
		// 4.扣减代理资金池的金额
		if (identity.equals("1")) {
			benefitHistory = new GjfBenefitHistory(null, tradeTotalMoney, benefitPool.getAgentSysIndiPoolCur(),
					benefitPool.getAgentSysIndiPoolCur().add(tradeTotalMoney), null, new Date(), "7", null, new Date(),
					"1");
			benefitPool.setAgentSysIndiPoolCur(benefitPool.getAgentSysIndiPoolCur().add(tradeTotalMoney));
		} else if (identity.equals("2")) {
			benefitHistory = new GjfBenefitHistory(null, tradeTotalMoney, benefitPool.getAgentSysAreaPoolCur(),
					benefitPool.getAgentSysAreaPoolCur().add(tradeTotalMoney), null, new Date(), "6", null, new Date(),
					"1");
			benefitPool.setAgentSysAreaPoolCur(benefitPool.getAgentSysAreaPoolCur().add(tradeTotalMoney));
		} else if (identity.equals("3")) {
			benefitHistory = new GjfBenefitHistory(null, tradeTotalMoney, benefitPool.getAgentSysCityPoolCur(),
					benefitPool.getAgentSysCityPoolCur().add(tradeTotalMoney), null, new Date(), "5", null, new Date(),
					"1");
			benefitPool.setAgentSysCityPoolCur(benefitPool.getAgentSysCityPoolCur().add(tradeTotalMoney));
		}
		update(benefitPool);
		// 5.添加一条资金池流水记录
		save(benefitHistory);
		return new ResultVo(200, "分红结算成功", tradeTotalMoney.doubleValue());
	}

	/*
	 * 查询所有的用户类型
	 * 
	 * @see cc.messcat.gjfeng.dao.benefit.GjfBenefitInfoDao#findAllMemberType()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<String> findAllMemberType() {
		String sql = "select concat(ID_,',',SUPER_ID_,',',IDENTITY_,',',IFNULL(AGENT_START_DATE_,0),',',IFNULL(AGENT_END_DATE_,0),',',STATUS_,',',IS_DEL_,',',IS_OPCENTER_,',',MERCHANT_TYPE_,',',IS_ACTIVE_MEMBER_) from gjf_member_info";
		return getCurrentSession().createSQLQuery(sql).list();
	}

	/**
	 * 获取分红权设置数据
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<GjfSetDividends> findDividendsDate(Double bfCumulativeMoney) {

		return getCurrentSession().createQuery(
				"from GjfSetDividends where " + bfCumulativeMoney + " between consumptionMin and consumptionMax")
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GjfSetDividends> findDividendiByCumulativeMoney(Double cumulativeMoney, String type) {
		
		if (Integer.parseInt(type) == 0) {
			return getCurrentSession()
					.createQuery("from GjfSetDividends where " + cumulativeMoney + " >= consumptionMax").list();
		} else {
			return getCurrentSession()
					.createQuery("from GjfSetDividends where " + cumulativeMoney + " > consumptionMin").list();
		}
	}

	/**
	 * 查询用户最近七天的让利数据
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public ResultVo findBenefitByTime(String mobile) {		
		String sql = "select b.TRADE_NO_ tradeNo,m.MOBILE_ mobile,b.BENEFIT_MONEY_ benefitMoney,b.ADD_TIME_ addTime,b.PAY_TYPE_ payType,b.TRADE_STATUS_ tradeStatus,b.CONFIRM_STATUS_ confirmStatus from gjf_member_trade_benefit as b left join gjf_member_info as m on m.ID_=b.MEMBER_ID_ where m.MOBILE_=:mobile and  DATE_SUB(CURDATE(),INTERVAL 7 DAY) <= DATE(b.ADD_TIME_) and b.TRADE_STATUS_=1 order by b.ADD_TIME_ desc";
		List list = getCurrentSession().createSQLQuery(sql.toString()).addScalar("tradeNo", StringType.INSTANCE)
				.addScalar("mobile", StringType.INSTANCE).addScalar("benefitMoney", BigDecimalType.INSTANCE)
				.addScalar("addTime", LongType.INSTANCE).addScalar("payType", StringType.INSTANCE)
				.addScalar("tradeStatus",StringType.INSTANCE).addScalar("confirmStatus",StringType.INSTANCE)
				.setResultTransformer(Transformers.aliasToBean(MemberTradeBenefitVo.class)).setParameter("mobile", mobile).list();
		return new ResultVo(200, "查询成功", list);
	}

	/**
	 * 统计线上消费和线下消费笔数
	 * @param type 0 线上消费  1 线下消费
	 * @return
	 */
	@Override
	public ResultVo findCountCousumtion(String type,Long memId) {	
		BigInteger count=new BigInteger("0");
		if("0".equals(type)){
			StringBuilder sql=new StringBuilder();
			sql.append(" SELECT COUNT(*)  from gjf_order_info  where MEMBER_ID_=:memId and date_format(ADD_TIME_,'%Y-%m')=date_format(now(),'%Y-%m')  AND ORDER_STATUS_ IN(1,2,3) and PAY_TYPE_!=8 ");
			count=(BigInteger) getCurrentSession().createSQLQuery(sql.toString()).setParameter("memId", memId).uniqueResult();
		}else{
			StringBuilder sql=new StringBuilder();
			sql.append(" SELECT COUNT(*)  FROM gjf_member_trade_benefit  WHERE MEMBER_ID_=:memId AND date_format(ADD_TIME_,'%Y-%m')=date_format(now(),'%Y-%m')  AND  TRADE_STATUS_=1 ");
			count=(BigInteger) getCurrentSession().createSQLQuery(sql.toString()).setParameter("memId", memId).uniqueResult();
		}
		return new ResultVo(200,"查询成功",count);
	}

	/**
	 * 获取用户订单消费最早记录
	 * @param memId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<GjfOrderInfo> findAarliestConsumptionHistory(Long memId) {
		StringBuilder sql=new StringBuilder();
		sql.append("from GjfOrderInfo where memberId.id=:memId and payType!=8 and orderStatus in(1,2,3)");
		List<GjfOrderInfo> orList=getCurrentSession().createQuery(sql.toString()).setParameter("memId", memId).setFirstResult(0).setMaxResults(1).list();		
		return orList;
	}

	/**
	 * 获取用户让利最早记录
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<GjfMemberTradeBenefit> findAarliestBenefitHistory(Long memId) {
		StringBuilder sql=new StringBuilder();
		sql.append("from gjfMemberTradeBenefit where memberId.id=:memId and tradeStatus=1");
		List<GjfMemberTradeBenefit> beList=getCurrentSession().createQuery(sql.toString()).setParameter("memId", memId).setFirstResult(0).setMaxResults(1).list();
		return beList;
	}

	/**
	 * 獲取用戶的消費次數
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<GjfMemberConsumptiomNum> findMemberCousumptionNum(Long memId) {
		String sql="from GjfMemberConsumptiomNum where memberId=:memId and date_format(addTime,'%Y%m')=date_format(now(),'%Y%m')";
		return  getCurrentSession().createQuery(sql.toString()).setParameter("memId", memId).list();
	}

	/**
	 * 统计用户提现和让利金额
	 * @param type
	 * @return
	 */
	@Override
	public BigDecimal sumMemberBenefitMoney(String type,Long memberId) {
		String sql="";
		if("0".equals(type)){
			 sql="SELECT IFNULL(SUM(APPLY_MONEY_),0) FROM gjf_member_trade WHERE TRADE_TYPE_=1 AND TRADE_STATUS_=1 AND  DATE_FORMAT(ADD_TIME_,'%y%m')=DATE_FORMAT(NOW(),'%y%m') AND MEMBER_ID_=:memId";
		}else{
			sql="SELECT IFNULL(SUM(BENEFIT_MONEY_),0) FROM gjf_member_trade_benefit WHERE TRADE_STATUS_=1 AND DATE_FORMAT(ADD_TIME_,'%y%m')=DATE_FORMAT(NOW(),'%y%m') AND MEMBER_ID_=:memId";
		}	
		return new BigDecimal(String
			.valueOf(getCurrentSession().createSQLQuery(sql).setParameter("memId", memberId).uniqueResult()));
	}

	/**
	 * 查询特殊发放人交易历史记录
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Pager findSpecialMemberTradeDiviHistory(Integer pageNo, Integer pageSize, String addTime, Long memId,String type) {
		StringBuilder hql=new StringBuilder();
		hql.append("from GjfSpecialMemberTradeDiviHistory where specialMemberId=:memId and date_format(:time,'%y-%m-%d')=date_format(tradeTime,'%y-%m-%d') and tradeType=:type");
		List<GjfSpecialMemberTradeDiviHistory> hisList=getCurrentSession().createQuery(hql.toString()).setParameter("memId", memId).setParameter("time", addTime).setParameter("type", type).setFirstResult((pageNo-1)*pageSize).setMaxResults(pageSize).list();
		String countSql="select count(0) from GjfSpecialMemberTradeDiviHistory where specialMemberId=:memId and date_format(:time,'%y-%m-%d')=date_format(tradeTime,'%y-%m-%d')";
		Long count=(Long) getCurrentSession().createQuery(countSql).setParameter("memId", memId).setParameter("time", addTime).uniqueResult();
		return new Pager(pageSize, pageNo, count.intValue(), hisList);
	}

	/**
	 * 获取分红权单价
	 */
	@Override
	public BigDecimal findUnitPriceByTime(String type,String time) {
		if("0".equals(type)){
			String hql="from GjfMemberTradeDiviHistory where tradeStatus=1 and tradeType=2 and date_format(addTime,'%y%m%d')=date_format('"+time+"','%y%m%d') and isActivity=1";
			List<GjfMemberTradeDiviHistory> list=getCurrentSession().createQuery(hql).setFirstResult(0).setMaxResults(1).list();
			if(BeanUtil.isValid(list)){
				GjfMemberTradeDiviHistory divi=list.get(0);
				return divi.getTradeRealUnit();
			}else{
				String hql0="from GjfMemberTradeDiviHistory where tradeStatus=1 and tradeType=2 and date_format(addTime,'%y%m%d')=date_format('"+time+"','%y%m%d') and isActivity=0";
				List<GjfMemberTradeDiviHistory> list0=getCurrentSession().createQuery(hql0).setFirstResult(0).setMaxResults(1).list();
				if(BeanUtil.isValid(list0)){
					GjfMemberTradeDiviHistory divi=list0.get(0);
					return divi.getTradeRealUnit();
				}else{
					return new BigDecimal(0.00);
				}
			}
		}else{
			String hql=" from GjfMemberTradeDiviHistory where tradeStatus=1 and tradeType=3 and date_format(addTime,'%y%m%d')=date_format('"+time+"','%y%m%d') and isActivity=1";
			List<GjfMemberTradeDiviHistory> list=getCurrentSession().createQuery(hql).setFirstResult(0).setMaxResults(1).list();
			if(BeanUtil.isValid(list)){
				GjfMemberTradeDiviHistory divi=list.get(0);
				return divi.getTradeRealUnit();
			}else{
				String hql0="from GjfMemberTradeDiviHistory where tradeStatus=1 and tradeType=3 and date_format(addTime,'%y%m%d')=date_format('"+time+"','%y%m%d') and isActivity=0";
				List<GjfMemberTradeDiviHistory> list0=getCurrentSession().createQuery(hql0).list();
				if(BeanUtil.isValid(list0)){
					GjfMemberTradeDiviHistory divi=list0.get(0);
					return divi.getTradeRealUnit();
				}else{
					return new BigDecimal(0.00);
				}
			}
		}
				
	}

	
}
