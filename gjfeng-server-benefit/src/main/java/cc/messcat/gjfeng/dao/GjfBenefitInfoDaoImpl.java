package cc.messcat.gjfeng.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.stereotype.Repository;

import cc.messcat.gjfeng.common.exception.MyException;
import cc.messcat.gjfeng.common.util.BeanUtil;
import cc.messcat.gjfeng.common.util.ObjValid;
import cc.messcat.gjfeng.common.vo.app.ResultVo;
import cc.messcat.gjfeng.entity.GjfBenefitDay;
import cc.messcat.gjfeng.entity.GjfBenefitHistory;
import cc.messcat.gjfeng.entity.GjfBenefitPool;
import cc.messcat.gjfeng.entity.GjfMemberInfo;
import cc.messcat.gjfeng.entity.GjfMemberTradeDiviHistory;
import cc.messcat.gjfeng.entity.GjfSetDividends;
import cc.messcat.gjfeng.entity.GjfStoreInfo;

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
	public ResultVo updateMemberBenefit(GjfBenefitDay benefitDay, List<Integer> memList, String account,
			String activityType) {

		if (BeanUtil.isValid(memList) && BeanUtil.isValid(account)) {// 特殊发放人

			// 查询最新的单价
			double unitPrice = 0d;
			Object unitPrice1 = getCurrentSession()
					.createQuery("select b.unitPrice from GjfBenefitInfo b where b.ratioType='0'").uniqueResult();
			if (ObjValid.isNotValid(unitPrice1)) {
				// 没有手动设置单价，直接不给予发放
				throw new MyException(400, "没有设置会员分红单价，请先设置分红单价再发放", null);
			}

			// 获取特殊人信息
			GjfMemberInfo specialMember = (GjfMemberInfo) getCurrentSession()
					.createQuery("from GjfMemberInfo where mobile=" + account).uniqueResult();
			// 记录特殊人实际发放金额
			BigDecimal actaulIssueMoney = new BigDecimal(0.00);
			// 记录特殊人实际发放分红权
			BigDecimal actaulIssueDivi = new BigDecimal(0.000000);
			for (Integer str : memList) {

				String tradeSql = "from GjfMemberTradeDiviHistory where memberId.id=:memberId and date_format(addTime,'%y%m%d')=date_format(now(),'%y%m%d') and tradeStatus=0 and tradeType=2";
				GjfMemberTradeDiviHistory tDivi = (GjfMemberTradeDiviHistory) getCurrentSession().createQuery(tradeSql)
						.setParameter("memberId", str.longValue()).uniqueResult();
				if (!BeanUtil.isValid(tDivi)) {
					continue;
				}

				// 记录用户交易金额
				BigDecimal diviMoney = new BigDecimal(0);
				// 记录交易分红权
				BigDecimal memTradeDivi = new BigDecimal(0);
				// 查询用户信息
				GjfMemberInfo member = (GjfMemberInfo) getCurrentSession()
						.createQuery("from GjfMemberInfo where id=" + str).uniqueResult();
				// 如果积分大方发放金额
				if (member.getConsumptionMoney()
						.doubleValue() > (new BigDecimal(unitPrice1.toString()).multiply(tDivi.getTradeDiviNum()))
								.doubleValue()) {
					// 计算分红金额
					diviMoney = new BigDecimal(unitPrice1.toString()).multiply(tDivi.getTradeDiviNum()).setScale(2,
							BigDecimal.ROUND_DOWN);
					actaulIssueMoney = actaulIssueMoney.add(diviMoney).setScale(2, BigDecimal.ROUND_DOWN);
				} else {
					diviMoney = member.getConsumptionMoney();
					actaulIssueMoney = actaulIssueMoney.add(member.getConsumptionMoney()).setScale(2,
							BigDecimal.ROUND_DOWN);
				}
				// 记录用户分红权
				memTradeDivi = tDivi.getTradeDiviNum();
				actaulIssueDivi = actaulIssueDivi.add(tDivi.getTradeDiviNum());
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
				// start每领取1000后扣除1分红权
				// sql.append(",m.DIVIDENDS_NUM_=m.DIVIDENDS_NUM_-(IF(m.CONSUMPTION_MONEY_-(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))>1000000,
				// IF(m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_>=1000,floor((m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_)/5000),0),IF(m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_>=1000,floor((m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_)/1000),0)))");
				// sql.append(",
				// m.DEDUCT_DIVI_NUM_=m.DEDUCT_DIVI_NUM_+((IF(m.CONSUMPTION_MONEY_-(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))>1000000,
				// IF(m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_>=1000,floor((m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_)/5000),0),IF(m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_>=1000,floor((m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_)/1000),0)))");
				// sql.append(",
				// m.LAST_TIME_DIVI_MONEY_=m.LAST_TIME_DIVI_MONEY_+(IF(m.CONSUMPTION_MONEY_-(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))>1000000,IF(m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_>=1000,floor((m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_)/1000)*5000,0),IF(m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_>=1000,floor((m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_)/1000)*1000,0)))
				// ");
				// end每领取1000后扣除1分红权
				// 湛江start每领取1000后扣除1分红权
				//sql.append(
				//		",m.DIVIDENDS_NUM_=m.DIVIDENDS_NUM_-(IF(m.CONSUMPTION_MONEY_-(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))>1000000,IF(m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_>=1000,floor((m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_)/5000),0),IF(m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_>=1000,floor((m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_)/1000),0)))");
				//sql.append(
				//		",m.DEDUCT_DIVI_NUM_=m.DEDUCT_DIVI_NUM_+(IF(m.CONSUMPTION_MONEY_-(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))>1000000,IF(m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_>=1000,floor((m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_)/5000),0),IF(m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_>=1000,floor((m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_)/1000),0)))");
				//sql.append(
				//		", m.LAST_TIME_DIVI_MONEY_=m.LAST_TIME_DIVI_MONEY_+(IF(m.CONSUMPTION_MONEY_-(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))>1000000,IF(m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_>=1000,floor((m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_)/5000)*5000,0),IF(m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_>=1000,floor((m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_)/1000)*1000,0))) ");
				// end每领取1000后扣除1分红权
				
				
				
				sql.append(
						"WHERE TO_DAYS(NOW())-TO_DAYS(d.ADD_TIME_)=0 AND d.TRADE_TYPE_='2' AND d.TRADE_STATUS_='0' AND m.CONSUMPTION_MONEY_ > 0 AND m.STATUS_='1' AND m.IS_DEL_='1' and m.ID_=:mebId");

				unitPrice = Double.parseDouble(String.valueOf(unitPrice1));
				paraMap.put("realUnit", unitPrice);
				paraMap.put("mebId", str);
				getCurrentSession().createSQLQuery(sql.toString()).setProperties(paraMap).executeUpdate();

				// 插入交易记录
				String hisSql = "insert into gjf_special_member_trade_divi_history(MEMBER_ID_,TRADE_NO_,TRADE_MONEY_,TRADE_DIVI_NUM_,TRADE_UNIT_,TRADE_TIME_,TRADE_TYPE_,TRADE_STATUS_,SPECIAL_MEMBER_ID_,MEMBER_NAME_,MEMBER_MOBILE_)"
						+ "value(?,DATE_FORMAT(NOW(),'%Y%m%d%h%m%s'),?,?,?,now(),'0','1',?,?,?)";
				getCurrentSession().createSQLQuery(hisSql.toString()).setParameter(0, member.getId())
						.setParameter(1, diviMoney).setParameter(2, memTradeDivi).setParameter(3, unitPrice)
						.setParameter(4, specialMember.getId()).setParameter(5, member.getName())
						.setParameter(6, member.getMobile()).executeUpdate();

			}

			String sptradeSql = "insert into gjf_special_member_trade_divi(ACTUAL_ISSUE_MONEY_,ISSUE_MONEY_,ACTUAL_ISSUE_DIVI_,SPECIAL_MEMBER_ID_,SPECIAL_MEMBER_NAME_,SPECIAL_MEMBER_MOBILE_,ADD_TIME_,TRADE_STATUS_,TRADE_TYPE_)"
					+ "value(?,0,?,?,?,?,now(),'1','0')";
			getCurrentSession().createSQLQuery(sptradeSql.toString()).setParameter(0, actaulIssueMoney)
					.setParameter(1, actaulIssueDivi).setParameter(2, specialMember.getId())
					.setParameter(3, specialMember.getMobile()).setParameter(4, specialMember.getName())
					.executeUpdate();
			updateMerchantsBenefit(benefitDay, memList, account,activityType);
		} else {// 公众
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
			// start每领取1000后扣除1分红权
			// sql.append(",m.DIVIDENDS_NUM_=m.DIVIDENDS_NUM_-(IF(m.CONSUMPTION_MONEY_-(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))>1000000,
			// IF(m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_>=1000,floor((m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_)/5000),0),IF(m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_>=1000,floor((m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_)/1000),0)))");
			// sql.append(",
			// m.DEDUCT_DIVI_NUM_=m.DEDUCT_DIVI_NUM_+((IF(m.CONSUMPTION_MONEY_-(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))>1000000,
			// IF(m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_>=1000,floor((m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_)/5000),0),IF(m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_>=1000,floor((m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_)/1000),0)))");
			// sql.append(",
			// m.LAST_TIME_DIVI_MONEY_=m.LAST_TIME_DIVI_MONEY_+(IF(m.CONSUMPTION_MONEY_-(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))>1000000,IF(m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_>=1000,floor((m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_)/1000)*5000,0),IF(m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_>=1000,floor((m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_)/1000)*1000,0)))
			// ");
			// end每领取1000后扣除1分红权
			// 湛江start每领取1000后扣除1分红权
			//sql.append(
			//		",m.DIVIDENDS_NUM_=m.DIVIDENDS_NUM_-(IF(m.CONSUMPTION_MONEY_-(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))>1000000,IF(m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_>=1000,floor((m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_)/5000),0),IF(m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_>=1000,floor((m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_)/1000),0)))");
			//sql.append(
			//		",m.DEDUCT_DIVI_NUM_=m.DEDUCT_DIVI_NUM_+(IF(m.CONSUMPTION_MONEY_-(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))>1000000,IF(m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_>=1000,floor((m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_)/5000),0),IF(m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_>=1000,floor((m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_)/1000),0)))");
			//sql.append(
			//		", m.LAST_TIME_DIVI_MONEY_=m.LAST_TIME_DIVI_MONEY_+(IF(m.CONSUMPTION_MONEY_-(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))>1000000,IF(m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_>=1000,floor((m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_)/5000)*5000,0),IF(m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_>=1000,floor((m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_)/1000)*1000,0))) ");
			// end每领取1000后扣除1分红权
			
			// 湛江start每领取1000后扣除1分红权
			sql.append(
					",m.DIVIDENDS_NUM_=m.DIVIDENDS_NUM_-(IF (m.CUMULATIVE_MONEY_>1000000,IF(m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_>=1000,floor((m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_)/5000),0),IF(m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_>=1000,floor((m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_)/1000),0)))");
			sql.append(
					",m.DEDUCT_DIVI_NUM_=m.DEDUCT_DIVI_NUM_+(IF (m.CUMULATIVE_MONEY_>1000000,IF(m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_>=1000,floor((m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_)/5000),0),IF(m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_>=1000,floor((m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_)/1000),0)))");
			sql.append(
					", m.LAST_TIME_DIVI_MONEY_=m.LAST_TIME_DIVI_MONEY_+(IF (m.CUMULATIVE_MONEY_>1000000,IF(m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_>=1000,floor((m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_)/5000)*5000,0),IF(m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_>=1000,floor((m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_)/1000)*1000,0))) ");
			// end每领取1000后扣除1分红权
			
			// 云南start每领取1000后扣除1分红权	
			/*sql.append(",m.DIVIDENDS_NUM_=m.DIVIDENDS_NUM_-(CASE WHEN m.CUMULATIVE_MONEY_<=10000 THEN IF(m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_>=500,floor((m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_)/500),0)"
					+ "WHEN m.CUMULATIVE_MONEY_>10000 AND m.CUMULATIVE_MONEY_<=1000000 THEN IF(m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_>=1000,floor((m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_)/1000),0)"
					+ "ELSE IF(m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_>=5000,floor((m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_)/5000),0) END)  ");
			
			sql.append(", m.DEDUCT_DIVI_NUM_=m.DEDUCT_DIVI_NUM_+(CASE WHEN m.CUMULATIVE_MONEY_<=10000 THEN IF(m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_>=500,floor((m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_)/500),0)"
					+ "WHEN m.CUMULATIVE_MONEY_>10000 AND m.CUMULATIVE_MONEY_<=1000000 THEN IF(m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_>=1000,floor((m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_)/1000),0)"
					+ "ELSE IF(m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_>=5000,floor((m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_)/5000),0) END) ");
			
			sql.append(", m.LAST_TIME_DIVI_MONEY_=m.LAST_TIME_DIVI_MONEY_+(CASE WHEN m.CUMULATIVE_MONEY_<=10000 THEN IF(m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_>=500,floor((m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_)/500)*500,0)"
					+ "WHEN m.CUMULATIVE_MONEY_>10000 AND m.CUMULATIVE_MONEY_<=1000000 THEN IF(m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_>=1000,floor((m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_)/1000)*1000,0)"
					+ "ELSE IF(m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_>=5000,floor((m.DIVIDENDS_TOTAL_MONEY_+(IF(m.CONSUMPTION_MONEY_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),m.CONSUMPTION_MONEY_))-m.LAST_TIME_DIVI_MONEY_)/5000)*5000,0) END)");*/			
			// end每领取1000后扣除1分红权
			
			
			sql.append(
					"WHERE TO_DAYS(NOW())-TO_DAYS(d.ADD_TIME_)=0 AND d.TRADE_TYPE_='2' AND d.TRADE_STATUS_='0' AND m.CONSUMPTION_MONEY_ > 0 AND m.STATUS_='1' AND m.IS_DEL_='1' and d.IS_ACTIVITY_=:activityType");
			// 查询最新的单价
			double unitPrice = 0d;
			// 如果为活跃区
			if ("1".equals(activityType)) {
				Object unitPrice1 = getCurrentSession()
						.createQuery("select b.unitPrice from GjfBenefitInfo b where b.ratioType='0'").uniqueResult();
				if (ObjValid.isNotValid(unitPrice1)) {
					// 没有手动设置单价，直接不给予发放
					throw new MyException(400, "没有设置活跃会员分红单价，请先设置分红单价再发放", null);
				}

				unitPrice = Double.parseDouble(String.valueOf(unitPrice1));
			} else {
				Object unitPrice1 = getCurrentSession()
						.createQuery("select b.activieRegionUnitPrice from GjfBenefitInfo b where b.ratioType='0'")
						.uniqueResult();
				if (ObjValid.isNotValid(unitPrice1)) {
					// 没有手动设置单价，直接不给予发放
					throw new MyException(400, "没有设置非活跃会员分红单价，请先设置分红单价再发放", null);
				}

				unitPrice = Double.parseDouble(String.valueOf(unitPrice1));
			}

			paraMap.put("realUnit", unitPrice);
			paraMap.put("activityType", activityType);
			getCurrentSession().createSQLQuery(sql.toString()).setProperties(paraMap).executeUpdate();

			// 查询总的会员分红金额
			String sql1 = "SELECT IFNULL(SUM(TRADE_MONEY_),0),IFNULL(SUM(TRADE_DIVI_NUM_),0) FROM gjf_member_trade_divi_history WHERE TO_DAYS(NOW())-TO_DAYS(ADD_TIME_)=0 AND TRADE_TYPE_='2' AND TRADE_STATUS_='1'";
			List<Object[]> toatlTrade = getCurrentSession().createSQLQuery(sql1).list();

			double totalMoney = Double.parseDouble(String.valueOf(toatlTrade.get(0)[0]));
			double totalDiviNum = Double.parseDouble(String.valueOf(toatlTrade.get(0)[1]));
			/*
			 * if (totalMoney==0) { return new ResultVo(400,
			 * "会员发放交易总金额为0，分红结算失败", null); } if (totalDiviNum==0) { throw new
			 * MyException(400, "会员发放交易总分红权为0，分红结算失败", null); }
			 */

				
			// 更新每天统计记录值
			benefitDay.setMemberRealMoney(new BigDecimal(totalMoney));
			benefitDay.setMemberRealUnitMoney(new BigDecimal(unitPrice));
			benefitDay.setMemberRealDiviNum(new BigDecimal(totalDiviNum));

			if ("0".endsWith(activityType)) {
				// 查询非活跃用户的金额
				String noActivitysql1 = "SELECT IFNULL(SUM(TRADE_MONEY_),0),IFNULL(SUM(TRADE_DIVI_NUM_),0) FROM gjf_member_trade_divi_history WHERE TO_DAYS(NOW())-TO_DAYS(ADD_TIME_)=0 AND TRADE_TYPE_='2' AND TRADE_STATUS_='1' and IS_ACTIVITY_=0";
				List<Object[]> noActivityToatlTrade = getCurrentSession().createSQLQuery(noActivitysql1).list();

				double noActivityTMoney = Double.parseDouble(String.valueOf(noActivityToatlTrade.get(0)[0]));
				double noActivityDiviNum = Double.parseDouble(String.valueOf(noActivityToatlTrade.get(0)[1]));
				
				paraMap.remove("activityType");
				paraMap.put("benefitMoney", totalMoney);
				paraMap.put("noacBenefitMoney", noActivityTMoney);
				// 更新资金池历史的交易状态和实际分红单价、交易总额、交易后资金池的额度
				String sql2 = "UPDATE gjf_benefit_history SET BENEFIT_MONEY_=:benefitMoney,BENEFIT_MONEY_BF_=(SELECT MEMBER_SYS_POOL_CUR_ FROM gjf_benefit_pool),BENEFIT_MONEY_AF_=BENEFIT_MONEY_BF_-:noacBenefitMoney,"
						+ "UNIT_PRICE_=:realUnit,TRADE_TIME_=NOW(),TRADE_STATUS_='1' WHERE BENEFIT_TYPE_='0' AND TO_DAYS(NOW())-TO_DAYS(BENEFIT_TIME_)=1 AND TRADE_STATUS_='0'";
				getCurrentSession().createSQLQuery(sql2).setProperties(paraMap).executeUpdate();

				// 更新资金池的金额
				String sql3 = "UPDATE gjf_benefit_pool SET MEMBER_SYS_POOL_CUR_=MEMBER_SYS_POOL_CUR_- ?";
				getCurrentSession().createSQLQuery(sql3).setParameter(0, noActivityTMoney).executeUpdate();
				
				benefitDay.setNoActivityMemberDiviNum(new BigDecimal(noActivityDiviNum));
				benefitDay.setNoActivityMemberRealMoney(new BigDecimal(noActivityTMoney));
				benefitDay.setNoActivityMemberRealUnitMoney(new BigDecimal(unitPrice));
			} else {
				// 查询非活跃用户的金额
				String activitysql1 = "SELECT IFNULL(SUM(TRADE_MONEY_),0),IFNULL(SUM(TRADE_DIVI_NUM_),0) FROM gjf_member_trade_divi_history WHERE TO_DAYS(NOW())-TO_DAYS(ADD_TIME_)=0 AND TRADE_TYPE_='2' AND TRADE_STATUS_='1' and IS_ACTIVITY_=1";
				List<Object[]> activityToatlTrade = getCurrentSession().createSQLQuery(activitysql1).list();

				double activityTMoney = Double.parseDouble(String.valueOf(activityToatlTrade.get(0)[0]));
				double activityDiviNum = Double.parseDouble(String.valueOf(activityToatlTrade.get(0)[1]));
				
				paraMap.remove("activityType");
				paraMap.put("benefitMoney", totalMoney);
				paraMap.put("acBenefitMoney", activityTMoney);
				// 更新资金池历史的交易状态和实际分红单价、交易总额、交易后资金池的额度
				String sql2 = "UPDATE gjf_benefit_history SET BENEFIT_MONEY_=:benefitMoney,BENEFIT_MONEY_BF_=(SELECT MEMBER_SYS_POOL_CUR_ FROM gjf_benefit_pool),BENEFIT_MONEY_AF_=BENEFIT_MONEY_BF_-:acBenefitMoney,"
						+ "UNIT_PRICE_=:realUnit,TRADE_TIME_=NOW(),TRADE_STATUS_='1' WHERE BENEFIT_TYPE_='0' AND TO_DAYS(NOW())-TO_DAYS(BENEFIT_TIME_)=1 AND TRADE_STATUS_='0'";
				getCurrentSession().createSQLQuery(sql2).setProperties(paraMap).executeUpdate();

				// 更新资金池的金额
				String sql3 = "UPDATE gjf_benefit_pool SET MEMBER_SYS_POOL_CUR_=MEMBER_SYS_POOL_CUR_- ?";
				getCurrentSession().createSQLQuery(sql3).setParameter(0, activityTMoney).executeUpdate();
				
				benefitDay.setActivityMemberDiviNum(new BigDecimal(activityDiviNum));
				benefitDay.setActivityMemberRealMoney(new BigDecimal(activityTMoney));
			}

			// 添加用户余额、提现增加流水
			String sql4 = "INSERT INTO gjf_member_trade_detail(MEMBER_ID_,TRADE_NO_,TRADE_MONEY_,ADD_TIME_,TRADE_TIME_,TRADE_TYPE_,TRADE_STATUS_,TRADE_TRMO_) "
					+ "SELECT MEMBER_ID_,TRADE_NO_,TRADE_MONEY_,ADD_TIME_,TRADE_TIME_,?,'1',TRADE_TRMO_ FROM gjf_member_trade_divi_history WHERE DATE_FORMAT(TRADE_TIME_,'%Y%m%d')="
					+ "DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 1 DAY),'%Y%m%d') AND TRADE_STATUS_='1' AND TRADE_TYPE_='2'";
			getCurrentSession().createSQLQuery(sql4).setParameter(0, "0").executeUpdate();
			getCurrentSession().createSQLQuery(sql4).setParameter(0, "1").executeUpdate();

			updateMerchantsBenefit(benefitDay, null, null, activityType);
		}
		return new ResultVo(200, "分红发放成功", null);
	}

	/*
	 * 手动或自动发放商家分红
	 * 
	 * @see cc.messcat.gjfeng.dao.GjfBenefitInfoDao#updateMerchantsBenefit()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ResultVo updateMerchantsBenefit(GjfBenefitDay benefitDay, List<Integer> memList, String account,
			String activityType) {
		// 更新所有分红记录里面用户的资金情况和记录的交易时间、状态、实际分红单价
		Map<String, Object> paraMap = new HashMap<String, Object>();

		if (BeanUtil.isValid(memList) && BeanUtil.isValid(account)) {

			// 查询最新的单价
			double unitPrice = 0d;
			Object unitPrice1 = getCurrentSession()
					.createQuery("select b.unitPrice from GjfBenefitInfo b where b.ratioType='1'").uniqueResult();
			if (ObjValid.isNotValid(unitPrice1)) {
				// 没有手动设置单价，直接不给予发放
				throw new MyException(400, "没有设置商家分红单价，请先设置分红单价再发放", null);
			}

			// 获取特殊人信息
			GjfMemberInfo specialMember = (GjfMemberInfo) getCurrentSession()
					.createQuery("from GjfMemberInfo where mobile=" + account).uniqueResult();
			// 记录特殊人实际发放金额
			BigDecimal actaulIssueMoney = new BigDecimal(0.00);
			// 记录特殊人实际发放分红权
			BigDecimal actaulIssueDivi = new BigDecimal(0.000000);
			for (Integer memId : memList) {

				// 获取用户信息
				GjfStoreInfo storeInfo = (GjfStoreInfo) getCurrentSession()
						.createQuery("from GjfStoreInfo where memberId.id=" + memId).uniqueResult();
				if (!BeanUtil.isValid(storeInfo)) {
					continue;
				}
				// 查询是否有交易记录
				String tradeSql = "from GjfMemberTradeDiviHistory where memberId.id=:memberId and date_format(addTime,'%y%m%d')=date_format(now(),'%y%m%d') and tradeStatus=0 and tradeType=3";
				GjfMemberTradeDiviHistory tDivi = (GjfMemberTradeDiviHistory) getCurrentSession().createQuery(tradeSql)
						.setParameter("memberId", memId.longValue()).uniqueResult();
				if (!BeanUtil.isValid(tDivi)) {
					continue;
				}

				// 记录用户交易金额
				BigDecimal diviMoney = new BigDecimal(0);
				// 记录交易分红权
				BigDecimal memTradeDivi = new BigDecimal(0);
				if (storeInfo.getStoreDividendsTotalMoneyBla()
						.doubleValue() > (new BigDecimal(unitPrice1.toString()).multiply(tDivi.getTradeDiviNum()))
								.doubleValue()) {
					diviMoney = (new BigDecimal(unitPrice1.toString())).multiply(tDivi.getTradeDiviNum()).setScale(2,
							BigDecimal.ROUND_DOWN);
					actaulIssueMoney = actaulIssueMoney.add((new BigDecimal(unitPrice1.toString()))
							.multiply(tDivi.getTradeDiviNum()).setScale(2, BigDecimal.ROUND_DOWN));
				} else {
					diviMoney = storeInfo.getStoreDividendsMoneyBla();
					actaulIssueMoney = actaulIssueMoney.add(storeInfo.getStoreDividendsMoneyBla());
				}

				// 记录用户分红权
				memTradeDivi = tDivi.getTradeDiviNum();
				actaulIssueDivi = actaulIssueDivi.add(tDivi.getTradeDiviNum());

				// 新分红单价计算
				StringBuffer sql = new StringBuffer(
						"UPDATE gjf_member_trade_divi_history d INNER JOIN gjf_member_info m ON m.ID_=d.MEMBER_ID_ INNER JOIN gjf_store_info s ON s.MEMBER_ID_=m.ID_");
				sql.append(
						" SET d.TRADE_REAL_UNIT_=:realUnit,d.TRADE_MONEY_=(IF(s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_)),");
				sql.append(
						"m.WITHDRAWAL_MONEY_=m.WITHDRAWAL_MONEY_+(IF(s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_)),m.BALANCE_MONEY_=m.BALANCE_MONEY_+"
								+ "(IF(s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_)),s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_=s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_-"
								+ "(IF(s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_)),");
				sql.append(
						"s.STORE_DIVIDENDS_TOTAL_MONEY_=s.STORE_DIVIDENDS_TOTAL_MONEY_+(IF(s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_)),d.TRADE_TIME_=NOW(),d.TRADE_STATUS_='1'");
				// 湛江start每领取1000扣除1个分红权
				/*sql.append(
						", s.STORE_DIVIDENDS_NUM_=s.STORE_DIVIDENDS_NUM_-(IF(s.STORE_DIVIDENDS_TOTAL_MONEY_+(IF(s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_))-s.LAST_TIME_DIVI_MONEY_>=1000,floor((s.STORE_DIVIDENDS_TOTAL_MONEY_+(IF(s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_))-s.LAST_TIME_DIVI_MONEY_)/1000),0))");
				sql.append(
						", s.DEDUCT_DIVI_NUM_=s.DEDUCT_DIVI_NUM_+(IF(s.STORE_DIVIDENDS_TOTAL_MONEY_+(IF(s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_))-s.LAST_TIME_DIVI_MONEY_>=1000,floor((s.STORE_DIVIDENDS_TOTAL_MONEY_+(IF(s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_))-s.LAST_TIME_DIVI_MONEY_)/1000),0))");
				sql.append(
						",s.LAST_TIME_DIVI_MONEY_=s.LAST_TIME_DIVI_MONEY_+(IF(s.STORE_DIVIDENDS_TOTAL_MONEY_+(IF(s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_))-s.LAST_TIME_DIVI_MONEY_>=1000,floor((s.STORE_DIVIDENDS_TOTAL_MONEY_+(IF(s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_))-s.LAST_TIME_DIVI_MONEY_)/1000)*1000,0))");*/
				// start每领取1000扣除1个分红权
				
				
				
				
				sql.append(
						" WHERE TO_DAYS(NOW())-TO_DAYS(d.ADD_TIME_)=0 AND d.TRADE_TYPE_='3' AND d.TRADE_STATUS_='0' AND s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_ > 0 AND s.STORE_STATUS_='1' AND s.IS_DEL_='1' and s.MEMBER_ID_=:memId");

				unitPrice = Double.parseDouble(String.valueOf(unitPrice1));
				paraMap.put("realUnit", unitPrice);
				paraMap.put("memId", memId);
				getCurrentSession().createSQLQuery(sql.toString()).setProperties(paraMap).executeUpdate();

				// 插入交易记录
				String hisSql = "insert into gjf_special_member_trade_divi_history(MEMBER_ID_,TRADE_NO_,TRADE_MONEY_,TRADE_DIVI_NUM_,TRADE_UNIT_,TRADE_TIME_,TRADE_TYPE_,TRADE_STATUS_,SPECIAL_MEMBER_ID_,MEMBER_NAME_,MEMBER_MOBILE_)"
						+ "value(?,DATE_FORMAT(NOW(),'%Y%m%d%h%m%s'),?,?,?,now(),'1','1',?,?,?)";
				getCurrentSession().createSQLQuery(hisSql.toString()).setParameter(0, storeInfo.getMemberId().getId())
						.setParameter(1, diviMoney).setParameter(2, memTradeDivi).setParameter(3, unitPrice)
						.setParameter(4, specialMember.getId()).setParameter(5, storeInfo.getMemberId().getName())
						.setParameter(6, storeInfo.getMemberId().getName()).executeUpdate();
			}

			String sptradeSql = "insert into gjf_special_member_trade_divi(ACTUAL_ISSUE_MONEY_,ISSUE_MONEY_,ACTUAL_ISSUE_DIVI_,SPECIAL_MEMBER_ID_,SPECIAL_MEMBER_NAME_,SPECIAL_MEMBER_MOBILE_,ADD_TIME_,TRADE_STATUS_,TRADE_TYPE_)"
					+ "value(?,0,?,?,?,?,now(),'1','1')";
			getCurrentSession().createSQLQuery(sptradeSql.toString()).setParameter(0, actaulIssueMoney)
					.setParameter(1, actaulIssueDivi).setParameter(2, specialMember.getId())
					.setParameter(3, specialMember.getMobile()).setParameter(4, specialMember.getName())
					.executeUpdate();

		} else {
			// 新分红单价计算
			StringBuffer sql = new StringBuffer(
					"UPDATE gjf_member_trade_divi_history d INNER JOIN gjf_member_info m ON m.ID_=d.MEMBER_ID_ INNER JOIN gjf_store_info s ON s.MEMBER_ID_=m.ID_");
			sql.append(
					" SET d.TRADE_REAL_UNIT_=:realUnit,d.TRADE_MONEY_=(IF(s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_)),");
			sql.append(
					"m.WITHDRAWAL_MONEY_=m.WITHDRAWAL_MONEY_+(IF(s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_)),m.BALANCE_MONEY_=m.BALANCE_MONEY_+"
							+ "(IF(s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_)),s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_=s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_-"
							+ "(IF(s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_))");
			
			// 湛江start每领取1000扣除1个分红权
			sql.append(
					", s.STORE_DIVIDENDS_NUM_=s.STORE_DIVIDENDS_NUM_-(IF(s.STORE_DIVIDENDS_TOTAL_MONEY_+(IF(s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_))-s.LAST_TIME_DIVI_MONEY_>=1000,floor((s.STORE_DIVIDENDS_TOTAL_MONEY_+(IF(s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_))-s.LAST_TIME_DIVI_MONEY_)/1000),0))");
			sql.append(
					", s.DEDUCT_DIVI_NUM_=s.DEDUCT_DIVI_NUM_+(IF(s.STORE_DIVIDENDS_TOTAL_MONEY_+(IF(s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_))-s.LAST_TIME_DIVI_MONEY_>=1000,floor((s.STORE_DIVIDENDS_TOTAL_MONEY_+(IF(s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_))-s.LAST_TIME_DIVI_MONEY_)/1000),0))");
			sql.append(
					",s.LAST_TIME_DIVI_MONEY_=s.LAST_TIME_DIVI_MONEY_+(IF(s.STORE_DIVIDENDS_TOTAL_MONEY_+(IF(s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_))-s.LAST_TIME_DIVI_MONEY_>=1000,floor((s.STORE_DIVIDENDS_TOTAL_MONEY_+(IF(s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_))-s.LAST_TIME_DIVI_MONEY_)/1000)*1000,0))");
			// start每领取1000扣除1个分红权
			
			// 云南start每领取1000扣除1个分红权
			/*sql.append(
					", s.STORE_DIVIDENDS_NUM_=s.STORE_DIVIDENDS_NUM_-(CASE WHEN s.STORE_BENEFIT_TOTAL_MONEY_<=10000 THEN IF(s.STORE_DIVIDENDS_TOTAL_MONEY_+(IF(s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_))-s.LAST_TIME_DIVI_MONEY_>=500,floor((s.STORE_DIVIDENDS_TOTAL_MONEY_+(IF(s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_))-s.LAST_TIME_DIVI_MONEY_)/500),0)"
					+ "WHEN s.STORE_BENEFIT_TOTAL_MONEY_>10000 AND s.STORE_BENEFIT_TOTAL_MONEY_<=1000000  THEN IF(s.STORE_DIVIDENDS_TOTAL_MONEY_+(IF(s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_))-s.LAST_TIME_DIVI_MONEY_>=1000,floor((s.STORE_DIVIDENDS_TOTAL_MONEY_+(IF(s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_))-s.LAST_TIME_DIVI_MONEY_)/1000),0)"
					+ "ELSE IF(s.STORE_DIVIDENDS_TOTAL_MONEY_+(IF(s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_))-s.LAST_TIME_DIVI_MONEY_>=5000,floor((s.STORE_DIVIDENDS_TOTAL_MONEY_+(IF(s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_))-s.LAST_TIME_DIVI_MONEY_)/5000),0) END )");
			sql.append(
					", s.DEDUCT_DIVI_NUM_=s.DEDUCT_DIVI_NUM_+(CASE WHEN s.STORE_BENEFIT_TOTAL_MONEY_<=10000 THEN IF(s.STORE_DIVIDENDS_TOTAL_MONEY_+(IF(s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_))-s.LAST_TIME_DIVI_MONEY_>=500,floor((s.STORE_DIVIDENDS_TOTAL_MONEY_+(IF(s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_))-s.LAST_TIME_DIVI_MONEY_)/500),0)"
					+ "WHEN s.STORE_BENEFIT_TOTAL_MONEY_>10000 AND s.STORE_BENEFIT_TOTAL_MONEY_<=1000000  THEN IF(s.STORE_DIVIDENDS_TOTAL_MONEY_+(IF(s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_))-s.LAST_TIME_DIVI_MONEY_>=1000,floor((s.STORE_DIVIDENDS_TOTAL_MONEY_+(IF(s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_))-s.LAST_TIME_DIVI_MONEY_)/1000),0)"
					+ "ELSE IF(s.STORE_DIVIDENDS_TOTAL_MONEY_+(IF(s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_))-s.LAST_TIME_DIVI_MONEY_>=5000,floor((s.STORE_DIVIDENDS_TOTAL_MONEY_+(IF(s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_))-s.LAST_TIME_DIVI_MONEY_)/5000),0) END )");
			sql.append(
					",s.LAST_TIME_DIVI_MONEY_=s.LAST_TIME_DIVI_MONEY_+(CASE WHEN s.STORE_BENEFIT_TOTAL_MONEY_<=10000 THEN IF(s.STORE_DIVIDENDS_TOTAL_MONEY_+(IF(s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_))-s.LAST_TIME_DIVI_MONEY_>=500,floor((s.STORE_DIVIDENDS_TOTAL_MONEY_+(IF(s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_))-s.LAST_TIME_DIVI_MONEY_)/500)*500,0)"
					+ "WHEN s.STORE_BENEFIT_TOTAL_MONEY_>10000 AND s.STORE_BENEFIT_TOTAL_MONEY_<=1000000  THEN IF(s.STORE_DIVIDENDS_TOTAL_MONEY_+(IF(s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_))-s.LAST_TIME_DIVI_MONEY_>=1000,floor((s.STORE_DIVIDENDS_TOTAL_MONEY_+(IF(s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_))-s.LAST_TIME_DIVI_MONEY_)/1000)*1000,0)"
					+ "ELSE IF(s.STORE_DIVIDENDS_TOTAL_MONEY_+(IF(s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_))-s.LAST_TIME_DIVI_MONEY_>=5000,floor((s.STORE_DIVIDENDS_TOTAL_MONEY_+(IF(s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_))-s.LAST_TIME_DIVI_MONEY_)/5000)*5000,0) END )");*/
			// start每领取1000扣除1个分红权
			
			sql.append(
					",s.STORE_DIVIDENDS_TOTAL_MONEY_=s.STORE_DIVIDENDS_TOTAL_MONEY_+(IF(s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_>:realUnit*d.TRADE_DIVI_NUM_,TRUNCATE(:realUnit*d.TRADE_DIVI_NUM_,2),s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_)),d.TRADE_TIME_=NOW(),d.TRADE_STATUS_='1'");
			
			sql.append(
					" WHERE TO_DAYS(NOW())-TO_DAYS(d.ADD_TIME_)=0 AND d.TRADE_TYPE_='3' AND d.TRADE_STATUS_='0' AND s.STORE_DIVIDENDS_TOTAL_MONEY_BLA_ > 0 AND s.STORE_STATUS_='1' AND s.IS_DEL_='1'and d.IS_ACTIVITY_=:activiyType");

			// 查询最新的单价
			double unitPrice = 0d;
			// 如果为活跃区
			if ("1".equals(activityType)) {
				Object unitPrice1 = getCurrentSession()
						.createQuery("select b.unitPrice from GjfBenefitInfo b where b.ratioType='1'").uniqueResult();
				if (ObjValid.isNotValid(unitPrice1)) {
					// 没有手动设置单价，直接不给予发放
					throw new MyException(400, "没有设置活跃会员分红单价，请先设置分红单价再发放", null);
				}

				unitPrice = Double.parseDouble(String.valueOf(unitPrice1));
			} else {
				Object unitPrice1 = getCurrentSession()
						.createQuery("select b.activieRegionUnitPrice from GjfBenefitInfo b where b.ratioType='1'")
						.uniqueResult();
				if (ObjValid.isNotValid(unitPrice1)) {
					// 没有手动设置单价，直接不给予发放
					throw new MyException(400, "没有设置非活跃会员分红单价，请先设置分红单价再发放", null);
				}

				unitPrice = Double.parseDouble(String.valueOf(unitPrice1));
			}

			paraMap.put("realUnit", unitPrice);
			paraMap.put("activiyType", activityType);
			getCurrentSession().createSQLQuery(sql.toString()).setProperties(paraMap).executeUpdate();

			// 查询总的商家分红金额
			String sql1 = "SELECT IFNULL(SUM(TRADE_MONEY_),0),IFNULL(SUM(TRADE_DIVI_NUM_),0) FROM gjf_member_trade_divi_history WHERE TO_DAYS(NOW())-TO_DAYS(ADD_TIME_)=0 AND TRADE_TYPE_='3' AND TRADE_STATUS_='1'";
			List<Object[]> toatlTrade = getCurrentSession().createSQLQuery(sql1).list();

			double totalMoney = Double.parseDouble(String.valueOf(toatlTrade.get(0)[0]));
			double totalDiviNum = Double.parseDouble(String.valueOf(toatlTrade.get(0)[1]));
			/*
			 * if (totalMoney==0) { throw new MyException(400,
			 * "商家发放交易总金额为0，分红结算失败", null); } if (totalDiviNum==0) { throw new
			 * MyException(400, "商家发放交易总分红权为0，分红结算失败", null); }
			 */
			

			// 更新余额流水记录
			benefitDay.setMerchantsRealMoney(new BigDecimal(totalMoney));
			benefitDay.setMerchantsRealUnitMoney(new BigDecimal(unitPrice));
			benefitDay.setMerchantsRealDiviNum(new BigDecimal(totalDiviNum));
			
			if("0".equals(activityType)){
				String noActivitysql1 = "SELECT IFNULL(SUM(TRADE_MONEY_),0),IFNULL(SUM(TRADE_DIVI_NUM_),0) FROM gjf_member_trade_divi_history WHERE TO_DAYS(NOW())-TO_DAYS(ADD_TIME_)=0 AND TRADE_TYPE_='3' AND TRADE_STATUS_='1' and IS_ACTIVITY_=0";
				List<Object[]> noActivitytoatlTrade = getCurrentSession().createSQLQuery(noActivitysql1).list();

				double noActivityTotalMoney = Double.parseDouble(String.valueOf(noActivitytoatlTrade.get(0)[0]));
				double noActivityTotalDiviNum = Double.parseDouble(String.valueOf(noActivitytoatlTrade.get(0)[1]));
				
				paraMap.remove("activiyType");
				paraMap.put("benefitMoney", totalMoney);
				paraMap.put("noacBenefitMoney", noActivityTotalDiviNum);
				// 更新资金池历史的交易状态和实际分红单价、交易总额、交易后资金池的额度
				String sql2 = "UPDATE gjf_benefit_history SET BENEFIT_MONEY_=:benefitMoney,BENEFIT_MONEY_BF_=(SELECT MERCHANT_SYS_POOL_CUR_ FROM gjf_benefit_pool),BENEFIT_MONEY_AF_=BENEFIT_MONEY_BF_-:noacBenefitMoney,"
						+ "UNIT_PRICE_=:realUnit,TRADE_TIME_=NOW(),TRADE_STATUS_='1' WHERE BENEFIT_TYPE_='1' AND TO_DAYS(NOW())-TO_DAYS(BENEFIT_TIME_)=1 AND TRADE_STATUS_='0'";
				getCurrentSession().createSQLQuery(sql2).setProperties(paraMap).executeUpdate();


				// 更新资金池的金额
				String sql3 = "UPDATE gjf_benefit_pool SET MERCHANT_SYS_POOL_CUR_=MERCHANT_SYS_POOL_CUR_- ?";
				getCurrentSession().createSQLQuery(sql3).setParameter(0, noActivityTotalMoney).executeUpdate();
				
				benefitDay.setNoActivityMerchantsRealMoney(new BigDecimal(noActivityTotalMoney));
				benefitDay.setNoActivityMerchantsRealUnitMoney(new BigDecimal(unitPrice));
				benefitDay.setNoActivityMerchantsDiviNum(new BigDecimal(noActivityTotalDiviNum));
			}else{
				String activitysql1 = "SELECT IFNULL(SUM(TRADE_MONEY_),0),IFNULL(SUM(TRADE_DIVI_NUM_),0) FROM gjf_member_trade_divi_history WHERE TO_DAYS(NOW())-TO_DAYS(ADD_TIME_)=0 AND TRADE_TYPE_='3' AND TRADE_STATUS_='1' and IS_ACTIVITY_=1";
				List<Object[]> activitytoatlTrade = getCurrentSession().createSQLQuery(activitysql1).list();
							
				double activityTotalMoney = Double.parseDouble(String.valueOf(activitytoatlTrade.get(0)[0]));
				double activityTotalDiviNum = Double.parseDouble(String.valueOf(activitytoatlTrade.get(0)[1]));
				
				paraMap.remove("activiyType");
				paraMap.put("benefitMoney", totalMoney);
				paraMap.put("acBenefitMoney", activityTotalMoney);
				// 更新资金池历史的交易状态和实际分红单价、交易总额、交易后资金池的额度
				String sql2 = "UPDATE gjf_benefit_history SET BENEFIT_MONEY_=:benefitMoney,BENEFIT_MONEY_BF_=(SELECT MERCHANT_SYS_POOL_CUR_ FROM gjf_benefit_pool),BENEFIT_MONEY_AF_=BENEFIT_MONEY_BF_-:acBenefitMoney,"
						+ "UNIT_PRICE_=:realUnit,TRADE_TIME_=NOW(),TRADE_STATUS_='1' WHERE BENEFIT_TYPE_='1' AND TO_DAYS(NOW())-TO_DAYS(BENEFIT_TIME_)=1 AND TRADE_STATUS_='0'";
				getCurrentSession().createSQLQuery(sql2).setProperties(paraMap).executeUpdate();

				
				String sql3 = "UPDATE gjf_benefit_pool SET MERCHANT_SYS_POOL_CUR_=MERCHANT_SYS_POOL_CUR_- ?";
				getCurrentSession().createSQLQuery(sql3).setParameter(0, activityTotalMoney).executeUpdate();
				
				benefitDay.setActivtiyMerchantsRealMoney(new BigDecimal(activityTotalMoney));;
				benefitDay.setActivityMerchantsDiviNum(new BigDecimal(activityTotalDiviNum));;
			}

			// 添加用户余额、提现增加流水
			String sql4 = "INSERT INTO gjf_member_trade_detail(MEMBER_ID_,TRADE_NO_,TRADE_MONEY_,ADD_TIME_,TRADE_TIME_,TRADE_TYPE_,TRADE_STATUS_,TRADE_TRMO_) "
					+ "SELECT MEMBER_ID_,TRADE_NO_,TRADE_MONEY_,ADD_TIME_,TRADE_TIME_,?,'1',TRADE_TRMO_ FROM gjf_member_trade_divi_history WHERE DATE_FORMAT(TRADE_TIME_,'%Y%m%d')="
					+ "DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 1 DAY),'%Y%m%d') AND TRADE_STATUS_='1' AND TRADE_TYPE_='3'";
			getCurrentSession().createSQLQuery(sql4).setParameter(0, "0").executeUpdate();
			getCurrentSession().createSQLQuery(sql4).setParameter(0, "1").executeUpdate();

			benefitDay.setTradeTime(new Date());
			benefitDay.setTradeStatus("1");
			update(benefitDay);
		}

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

		// 添加余额和提现额度变更记录
		String sql4 = "INSERT INTO gjf_member_trade_detail(MEMBER_ID_,TRADE_NO_,TRADE_MONEY_,ADD_TIME_,TRADE_TIME_,TRADE_TYPE_,TRADE_STATUS_,TRADE_TRMO_) "
				+ "SELECT MEMBER_ID_,TRADE_NO_,TRADE_MONEY_,ADD_TIME_,TRADE_TIME_,?,'1',TRADE_TRMO_ FROM gjf_member_trade_divi_history WHERE MEMBER_ID_=? AND TRADE_STATUS_='0' AND TRADE_TYPE_=?";
		getCurrentSession().createSQLQuery(sql4).setParameter(0, "0").setParameter(1, memberId)
				.setParameter(2, agentType).executeUpdate();
		getCurrentSession().createSQLQuery(sql4).setParameter(0, "1").setParameter(1, memberId)
				.setParameter(2, agentType).executeUpdate();
		getCurrentSession().createSQLQuery(sql2).setParameter(0, memberId).setParameter(1, agentType).executeUpdate();

		// 3.修改用户的余额、提现金额、分红金额
		memberInfo.setBalanceMoney(memberInfo.getBalanceMoney().add(tradeTotalMoney));
		memberInfo.setWithdrawalMoney(memberInfo.getWithdrawalMoney().add(tradeTotalMoney));
		// 记录个贷分红金额
		// memberInfo.setIndiRewardMoney(tradeTotalMoney);
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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List findBenefitHistoryByTime(String addTime) {
		List list = new ArrayList<GjfBenefitHistory>();
		String sql = "select ID_,BENEFIT_MONEY_,BENEFIT_MONEY_BF_,BENEFIT_MONEY_AF_,UNIT_PRICE_,ADD_TIME_,BENEFIT_TYPE_,BENEFIT_TIME_,TRADE_TIME_,TRADE_STATUS_ from gjf_benefit_history as g where g.BENEFIT_TYPE_ = '1' and g.ADD_TIME_ like '%"
				+ addTime + "%'";
		List obj1 = (List<GjfBenefitHistory>) getCurrentSession().createSQLQuery(sql).list();
		for (int i = 0; i < obj1.size(); i++) {
			if (ObjValid.isValid(obj1.get(i))) {
				list.add(obj1.get(i));
			}
		}
		String sql0 = "select ID_,BENEFIT_MONEY_,BENEFIT_MONEY_BF_,BENEFIT_MONEY_AF_,UNIT_PRICE_,ADD_TIME_,BENEFIT_TYPE_,BENEFIT_TIME_,TRADE_TIME_,TRADE_STATUS_ from gjf_benefit_history as g where g.BENEFIT_TYPE_ = '0' and g.ADD_TIME_ like '%"
				+ addTime + "%'";
		List obj2 = (List<GjfBenefitHistory>) getCurrentSession().createSQLQuery(sql0).list();
		for (int i = 0; i < obj2.size(); i++) {
			if (ObjValid.isValid(obj2.get(i))) {
				list.add(obj2.get(i));
			}
		}
		String[] param = { "id", "benefitMoney", "benefitMoneyBf", "benefitMoneyAf", "unitPrice", "addTime",
				"benefitType", "benefitTime", "tradeTime", "tradeStatus" };
		list = BeanUtil.changeObjectArrayToMaps(list, param);
		return list;
	}

	/**
	 * 设置——分红金额设置 ： 今日实时池
	 * 
	 * @param type
	 *            0：会员池 1：商户池
	 * @return
	 */
	@Override
	public BigDecimal findTodayRealTimePool(String type) {
		StringBuffer sql = new StringBuffer();
		// 利用Apache lang包快速获取凌晨0点0分0秒，23点59分59秒字符串
		String startTime = DateFormatUtils.format(new Date(), "yyyy-MM-dd 00:00:00");
		String endTime = DateFormatUtils.format(new Date(), "yyyy-MM-dd 23:59:59");
		if ("0".equals(type)) {// 今日充进会员池
			sql.append(
					"select IFNULL(SUM(BENEFIT_MONEY_),0) from gjf_benefit_history where BENEFIT_TYPE_ = '8' and TRADE_STATUS_ = '1' and TRADE_TIME_ between '")
					.append(startTime).append("' and '").append(endTime).append("'");
		} else if ("1".equals(type)) {// 今日充进商户池
			sql.append(
					"select IFNULL(SUM(BENEFIT_MONEY_),0) from gjf_benefit_history where BENEFIT_TYPE_ = '9' and TRADE_STATUS_ = '1' and TRADE_TIME_ between '")
					.append(startTime).append("' and '").append(endTime).append("'");
		}
		return (BigDecimal) getCurrentSession().createSQLQuery(sql.toString()).uniqueResult();
	}

	/*
	 * 查询所有的用户类型
	 * 
	 * @see cc.messcat.gjfeng.dao.benefit.GjfBenefitInfoDao#findAllMemberType()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<String> findAllMemberType() {
		String sql = "select concat(ID_,',',SUPER_ID_,',',IDENTITY_,',',IFNULL(AGENT_START_DATE_,0),',',IFNULL(AGENT_END_DATE_,0),',',STATUS_,',',IS_DEL_,',',IS_OPCENTER_,',',IS_DIVI_) from gjf_member_info";
		return getCurrentSession().createSQLQuery(sql).list();
	}

	/**
	 * 查询用户下级
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> findAllMemberSuperId(Integer memberId) {
		String sql = "select ID_ from gjf_member_info where SUPER_ID_=:superId";
		return getCurrentSession().createSQLQuery(sql.toString()).setParameter("superId", memberId).list();
	}

	/**
	 * 获取今天分红用户id
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> findAllMemberId() {
		String sql="select MEMBER_ID_ FROM gjf_member_trade_divi_history WHERE TRADE_STATUS_=1 AND (TRADE_TYPE_='2' OR TRADE_TYPE_='3') AND date_format(ADD_TIME_,'%y%m%d')=date_format(now(),'%y%m%d') group by MEMBER_ID_";
		return getCurrentSession().createSQLQuery(sql.toString()).list();
	}

	/**
	 * 获取发放用户或商户信息
	 * @return
	 */

	@SuppressWarnings({ "rawtypes" })
	@Override
	public List findMemberMeetIssue(String type,String activityType) {
		//如果是用户
		if("0".equals(type)){
			String hql="from GjfMemberInfo where (merchantFirstCousumptionMoney>0 or merchantSecondCousumptionMoney>0 or merchantThreeCousumptionMoney>0) and (merchantFirstDiviNum>=1 or merchantSecondDiviNum>=1 or merchantThreeDiviNum>=1) and status=1 and isDel=1 and isDivi=1 and isActiveMember=:isActiveMember";
			return  getCurrentSession().createQuery(hql).setParameter("isActiveMember", activityType).list();
		}else {
			String hql="from GjfStoreInfo where storeDividendsTotalMoneyBla>0 and  isDel=1 and isActivityStore=:isActivityStore and storeDividendsNum>=1";
			return getCurrentSession().createQuery(hql).setParameter("isActivityStore", activityType).list();
		}
		
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

	/**
	 * 根据类型获取设置信息，0 小于消费金额的数据   1 大于消费金额的数据
	 * @param cumulativeMoney
	 * @return
	 */
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
	
}
