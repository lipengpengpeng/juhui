package cc.messcat.gjfeng.dao.count;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.DateFormatUtils;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DoubleType;
import org.springframework.stereotype.Repository;

import cc.messcat.gjfeng.common.bean.Pager;
import cc.messcat.gjfeng.common.util.BeanUtil;
import cc.messcat.gjfeng.common.util.ObjValid;
import cc.messcat.gjfeng.common.util.StringUtil;
import cc.messcat.gjfeng.common.vo.app.ResultVo;
import cc.messcat.gjfeng.dao.BaseHibernateDaoImpl;
import cc.messcat.gjfeng.entity.GjfBenefitDay;
import cc.messcat.gjfeng.entity.GjfBenefitReport;
import cc.messcat.gjfeng.entity.GjfSummaryReport;
import cc.messcat.gjfeng.entity.GjfTotalReport;
import cc.messcat.gjfeng.entity.GjfWealOutputReport;
import cc.messcat.gjfeng.entity.GjfWealPayoutReport;
import cc.messcat.gjfeng.entity.GjfWithdrawReport;

@Repository("countInfoDao")
public class CountInfoDaoImpl extends BaseHibernateDaoImpl<Serializable>implements CountInfoDao {

	/**
	 * 余额
	 */
	@Override
	public BigDecimal findMemberBalanceMoney(String type) {
		StringBuffer sql = new StringBuffer();
		if (StringUtil.isNotBlank(type)) {
			sql.append("select COALESCE(SUM(balanceMoney),0) from GjfMemberInfo where status = '1' and type =:type ");
			return (BigDecimal) getCurrentSession().createQuery(sql.toString()).setParameter("type", type)
					.uniqueResult();
		} else {
			sql.append("select IFNULL(sum(BALANCE_MONEY_),0) from gjf_member_info where STATUS_ = '1'");
			return (BigDecimal) getCurrentSession().createSQLQuery(sql.toString()).uniqueResult();
		}
	}

	/**
	 * 会员分红权总额
	 */
	@Override
	public BigDecimal findMemberDiviTotal() {
		String sql = "select IFNULL(sum(DIVIDENDS_NUM_),0) from gjf_member_info where STATUS_ = '1' and  IS_DEL_ = '1'";
		return (BigDecimal) getCurrentSession().createSQLQuery(sql.toString()).uniqueResult();
	}

	/**
	 * 商户分红权总额
	 */
	@Override
	public BigDecimal findStoreDiviTotal() {
		String sql = "select IFNULL(sum(STORE_DIVIDENDS_NUM_),0) from gjf_store_info where STORE_STATUS_ = '1' and  IS_DEL_= '1'";
		return (BigDecimal) getCurrentSession().createSQLQuery(sql.toString()).uniqueResult();
	}

	/**
	 * 分红发放总额
	 */
	@Override
	public BigDecimal findAllDiviAmount() {
		String sql = "select IFNULL(sum(TRADE_DIVI_NUM_),0) from gjf_member_trade_divi_history where TRADE_STATUS_ = '1'";
		return (BigDecimal) getCurrentSession().createSQLQuery(sql).uniqueResult();
	}

	/**
	 * 分红权实时余额
	 */
	@Override
	public BigDecimal findDiviMoneyBla(String type) {
		StringBuffer sql = new StringBuffer();
		if ("0".equals(type)) {// 会员分红实时余额
			return (BigDecimal) getCurrentSession()
					.createSQLQuery(
							"SELECT IFNULL(SUM(num),0) FROM (SELECT IF(DIVIDENDS_TOTAL_MONEY_/CUMULATIVE_MONEY_ >= 0.2,"
									+ "IF(DIVIDENDS_TOTAL_MONEY_/CUMULATIVE_MONEY_ >= 0.5,FLOOR(TRUNCATE(DIVIDENDS_NUM_/4,6)),FLOOR(TRUNCATE(DIVIDENDS_NUM_/2,6))),FLOOR(TRUNCATE(DIVIDENDS_NUM_,6))) AS num FROM gjf_member_info WHERE IS_DIVI_ = '1' AND STATUS_=1 AND IS_DEL_=1) AS totalSum")
					.uniqueResult();
		}
		if ("1".equals(type)) {// 商户分红实时余额
			sql.append(
					"SELECT FLOOR(IFNULL(SUM(STORE_DIVIDENDS_NUM_),0)) FROM gjf_store_info WHERE IS_DIVI_ = '1' AND STORE_STATUS_ ='1' AND IS_DEL_ = '1'");
			return (BigDecimal) getCurrentSession().createSQLQuery(sql.toString()).uniqueResult();
		}
		return null;
	}

	/**
	 * 会员实时分红单价
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public BigDecimal findMemberDiviUnitPrice() {
		String sql = "SELECT SYS_RATIO_ sysRatio,DIVI_POOLS_RATIO_ diviPoolsRatio,ISSUE_RATIO_ issueRatio FROM gjf_benefit_info where RATIO_TYPE_ = '0'";
		List list = getCurrentSession().createSQLQuery(sql).addScalar("sysRatio", DoubleType.INSTANCE)
				.addScalar("diviPoolsRatio", DoubleType.INSTANCE).addScalar("issueRatio", DoubleType.INSTANCE).list();
		Object[] objects = (Object[]) list.get(0);
		Double sysRatio = (Double) objects[0];
		Double diviPoolsRatio = (Double) objects[1];
		Double issueRatio = (Double) objects[2];
		// 商店让利额
		BigDecimal storeBenefitMoney = (BigDecimal) getCurrentSession()
				.createSQLQuery(
						"SELECT IFNULL(SUM(r.BENEFIT_MONEY_),0) FROM gjf_member_trade_benefit r WHERE DATE_FORMAT(r.ADD_TIME_,'%Y%m%d')=DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 0 DAY),'%Y%m%d') and r.TRADE_STATUS_='1'")
				.uniqueResult();
		// 订单的让利金额
		BigDecimal orderBenefitMoney = (BigDecimal) getCurrentSession()
				.createSQLQuery(
						"SELECT IFNULL(SUM(r.STORE_BENEFIT_AMOUNT_),0) FROM gjf_order_info r WHERE DATE_FORMAT(r.FINISHED_TIME_,'%Y%m%d')=DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 0 DAY),'%Y%m%d') AND r.ORDER_STATUS_='3' AND (r.PAY_TYPE_='0' OR r.PAY_TYPE_='1' OR r.PAY_TYPE_='2' OR r.PAY_TYPE_='3' OR r.PAY_TYPE_='4')")
				.uniqueResult();
		// 总让利额
		BigDecimal totalBenefitMoney = (storeBenefitMoney.add(orderBenefitMoney)).setScale(2, BigDecimal.ROUND_DOWN);// 保留两位，截断多余的
		// 计算实际参与分红的分红权,取整
		BigDecimal memberTotalBenefitNum = (BigDecimal) getCurrentSession()
				.createSQLQuery(
						"SELECT FLOOR(IFNULL(SUM(NUM),0)) FROM (SELECT IF(DIVIDENDS_TOTAL_MONEY_/CUMULATIVE_MONEY_ >= 0.2,IF(DIVIDENDS_TOTAL_MONEY_/CUMULATIVE_MONEY_ >= 0.5,FLOOR(TRUNCATE(DIVIDENDS_NUM_/4,6)),FLOOR(TRUNCATE(DIVIDENDS_NUM_/2,6))),FLOOR(TRUNCATE(DIVIDENDS_NUM_,6))) AS NUM FROM gjf_member_info WHERE STATUS_='1' AND IS_DEL_='1') AS TOTAL_SUM")
				.uniqueResult();
		BigDecimal result = totalBenefitMoney
				.multiply(new BigDecimal(sysRatio.toString()).divide(new BigDecimal("100")))
				.multiply(new BigDecimal(diviPoolsRatio.toString()).divide(new BigDecimal("100")))
				.divide(new BigDecimal(issueRatio.toString()), 2, BigDecimal.ROUND_DOWN)
				.divide(memberTotalBenefitNum, 2, BigDecimal.ROUND_DOWN);
		return result;
	}

	/**
	 * 商户实时分红单价
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public BigDecimal findSellerDiviUnitPrice() {
		String sql = "SELECT SYS_RATIO_ sysRatio,DIVI_POOLS_RATIO_ diviPoolsRatio,ISSUE_RATIO_ issueRatio FROM gjf_benefit_info where RATIO_TYPE_ = '1'";
		List list = getCurrentSession().createSQLQuery(sql).addScalar("sysRatio", DoubleType.INSTANCE)
				.addScalar("diviPoolsRatio", DoubleType.INSTANCE).addScalar("issueRatio", DoubleType.INSTANCE).list();
		Object[] objects = (Object[]) list.get(0);
		Double sysRatio = (Double) objects[0];
		Double diviPoolsRatio = (Double) objects[1];
		Double issueRatio = (Double) objects[2];
		// 商店让利额
		BigDecimal storeBenefitMoney = (BigDecimal) getCurrentSession()
				.createSQLQuery(
						"SELECT IFNULL(SUM(r.BENEFIT_MONEY_),0) FROM gjf_member_trade_benefit r WHERE DATE_FORMAT(r.ADD_TIME_,'%Y%m%d')=DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 0 DAY),'%Y%m%d') and r.TRADE_STATUS_='1'")
				.uniqueResult();
		// 订单的让利金额
		BigDecimal orderBenefitMoney = (BigDecimal) getCurrentSession()
				.createSQLQuery(
						"SELECT IFNULL(SUM(r.STORE_BENEFIT_AMOUNT_),0) FROM gjf_order_info r WHERE DATE_FORMAT(r.FINISHED_TIME_,'%Y%m%d')=DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 0 DAY),'%Y%m%d') AND r.ORDER_STATUS_='3' AND (r.PAY_TYPE_='0' OR r.PAY_TYPE_='1' OR r.PAY_TYPE_='2' OR r.PAY_TYPE_='3' OR r.PAY_TYPE_='4')")
				.uniqueResult();
		// 总让利额
		BigDecimal totalBenefitMoney = (storeBenefitMoney.add(orderBenefitMoney)).setScale(2, BigDecimal.ROUND_DOWN);// 保留两位
		// 查询店铺总的分红权数量,取整
		BigDecimal storeBenefitNum = (BigDecimal) getCurrentSession()
				.createSQLQuery(
						"SELECT FLOOR(IFNULL(SUM(s.STORE_DIVIDENDS_NUM_),0)) FROM gjf_store_info s WHERE s.STORE_STATUS_='1' AND s.IS_DEL_='1'")
				.uniqueResult();
		BigDecimal result = totalBenefitMoney
				.multiply(new BigDecimal(sysRatio.toString()).divide(new BigDecimal("100")))
				.multiply(new BigDecimal(diviPoolsRatio.toString()).divide(new BigDecimal("100")))
				.divide(new BigDecimal(issueRatio.toString()), 2, BigDecimal.ROUND_DOWN)
				.divide(storeBenefitNum, 2, BigDecimal.ROUND_DOWN);
		return result;
	}

	/**
	 * 商户剩余授信额度
	 * 
	 * @return
	 */
	@Override
	public BigDecimal findSellerCreditLine() {
		String sql = "select IFNULL(sum(LINE_OF_CREDIT_),0) from gjf_member_info where TYPE_ = '1' and STATUS_ ='1'";
		return (BigDecimal) getCurrentSession().createSQLQuery(sql).uniqueResult();
	}

	/**
	 * 查询用户当日的订单总额
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public BigDecimal findMemberOrderByday(Long id) {
		String sql = "select COALESCE(SUM(orderTotalAmount),0) from GjfOrderInfo where orderStatus IN ('1','2','3') and memberId.id = :id  AND finishedTime between :startTime and :endTime";
		return (BigDecimal) getCurrentSession().createQuery(sql.toString()).setParameter("id", id)
				.setParameter("startTime", getDayBegin()).setParameter("endTime", getDayEnd()).uniqueResult();
	}

	/**
	 * 查询总授信充值额度
	 * 
	 * @return
	 */
	@Override
	public BigDecimal findCreditLineRechargeTotal() {
		String sql = "select IFNULL(sum(TRADE_MONEY_),0) from gjf_member_trade where TRADE_TYPE_ = '0' and TRADE_STATUS_ = '1'";
		return (BigDecimal) getCurrentSession().createSQLQuery(sql).uniqueResult();
	}

	/**
	 * 查询当日授信充值额
	 * 
	 * @return
	 * @throws ParseException
	 */
	@Override
	public BigDecimal findCreditLineRecharge() throws ParseException {
		String sql = "select COALESCE(sum(applyMoney),0) from GjfMemberTrade where tradeType = '0' and  tradeStatus = '1' and addTime between :startTime and :endTime ";
		return (BigDecimal) getCurrentSession().createQuery(sql).setParameter("startTime", getDayBegin())
				.setParameter("endTime", getDayEnd()).uniqueResult();
	}

	// 获取当天的开始时间
	private Date getDayBegin() {
		Calendar cal = new GregorianCalendar();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	// 获取当天的结束时间
	private Date getDayEnd() {
		Calendar cal = new GregorianCalendar();
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		return cal.getTime();
	}

	/**
	 * 查询近30天授信充值趋势
	 * 
	 * @return
	 */
	@Override
	public BigDecimal[] findAlmostOneMonthCLRecharge() {
		BigDecimal[] result = new BigDecimal[30];
		String[] date = getAlmostOneMonthDate();
		for (int i = 0; i < date.length; i++) {
			StringBuffer sql = new StringBuffer();
			if (i == 29) {
				Calendar yesterday = Calendar.getInstance();
				yesterday.add(Calendar.DATE, -1);
				String startTime = new SimpleDateFormat("yyyy-MM-dd 00:00:00").format(yesterday.getTime());
				String endTime = new SimpleDateFormat("yyyy-MM-dd 23:59:59").format(yesterday.getTime());
				sql.append(
						"select IFNULL(sum(TRADE_MONEY_),0) from gjf_member_trade where TRADE_TYPE_ = '0' and TRADE_STATUS_ = '1' and ADD_TIME_ between '")
						.append(startTime).append("' and '").append(endTime).append("'");
			} else {
				sql.append(
						"select IFNULL(sum(TRADE_MONEY_),0) from gjf_member_trade where TRADE_TYPE_ = '0' and TRADE_STATUS_ = '1' and ADD_TIME_ between '")
						.append(date[i]).append("' and '").append(date[i + 1]).append("'");
			}
			result[i] = (BigDecimal) getCurrentSession().createSQLQuery(sql.toString()).uniqueResult();
		}
		return result;
	}

	/**
	 * 查询当日网上商城交易额
	 * 
	 * @return
	 */
	@Override
	public BigDecimal findTodayStoreTurnover() {
		String sql = "select COALESCE(sum(orderTotalAmount),0) from GjfOrderInfo where payType NOT IN ('7','8') AND orderStatus IN ('1','2','3') and isDel = '1' and addTime between :startTime and :endTime";
		return (BigDecimal) getCurrentSession().createQuery(sql).setParameter("startTime", getDayBegin())
				.setParameter("endTime", getDayEnd()).uniqueResult();
	}

	/**
	 * 查询当日O2O交易额
	 * 
	 * @return
	 */
	@Override
	public BigDecimal findTodayO2OTurnover() {
		String sql = "select COALESCE(sum(tradeMoney),0) from GjfMemberTradeBenefit where tradeStatus = '1' and addTime between :startTime and :endTime";
		return (BigDecimal) getCurrentSession().createQuery(sql).setParameter("startTime", getDayBegin())
				.setParameter("endTime", getDayEnd()).uniqueResult();
	}

	/**
	 * 查询近30天网上商城交易额
	 * 
	 * @return
	 */
	@Override
	public BigDecimal[] findAlmostOneMonthStoreTurnover() {
		BigDecimal[] result = new BigDecimal[30];
		String[] date = getAlmostOneMonthDate();
		for (int i = 0; i < date.length; i++) {
			StringBuffer sql = new StringBuffer();
			if (i == 29) {
				Calendar yesterday = Calendar.getInstance();
				yesterday.add(Calendar.DATE, -1);
				String startTime = new SimpleDateFormat("yyyy-MM-dd 00:00:00").format(yesterday.getTime());
				String endTime = new SimpleDateFormat("yyyy-MM-dd 23:59:59").format(yesterday.getTime());
				sql.append(
						"select IFNULL(sum(ORDER_TOTAL_AMOUNT_),0) from gjf_order_info where PAY_TYPE_ NOT IN ('7','8') AND ORDER_STATUS_ = '1' and PAY_TIME_ between '")
						.append(startTime).append("' and '").append(endTime).append("'");
			} else {
				sql.append(
						"select IFNULL(sum(ORDER_TOTAL_AMOUNT_),0) from gjf_order_info where PAY_TYPE_ NOT IN ('7','8') AND ORDER_STATUS_ = '1' and PAY_TIME_ between '")
						.append(date[i]).append("' and '").append(date[i + 1]).append("'");
			}
			result[i] = (BigDecimal) getCurrentSession().createSQLQuery(sql.toString()).uniqueResult();
		}
		return result;
	}

	/**
	 * 查询近30天O2O交易额
	 * 
	 * @return
	 */
	@Override
	public BigDecimal[] findAlmostOneMonth020Turnover() {
		BigDecimal[] result = new BigDecimal[30];
		String[] date = getAlmostOneMonthDate();
		for (int i = 0; i < date.length; i++) {
			StringBuffer sql = new StringBuffer();
			if (i == 29) {
				Calendar yesterday = Calendar.getInstance();
				yesterday.add(Calendar.DATE, -1);
				String startTime = new SimpleDateFormat("yyyy-MM-dd 00:00:00").format(yesterday.getTime());
				String endTime = new SimpleDateFormat("yyyy-MM-dd 23:59:59").format(yesterday.getTime());
				sql.append(
						"select IFNULL(sum(TRADE_MONEY_),0) from gjf_member_trade_benefit where TRADE_STATUS_ = '1' and ADD_TIME_ between '")
						.append(startTime).append("' and '").append(endTime).append("'");
			} else {
				sql.append(
						"select IFNULL(sum(TRADE_MONEY_),0) from gjf_member_trade_benefit where TRADE_STATUS_ = '1' and ADD_TIME_ between '")
						.append(date[i]).append("' and '").append(date[i + 1]).append("'");
			}
			result[i] = (BigDecimal) getCurrentSession().createSQLQuery(sql.toString()).uniqueResult();
		}
		return result;
	}

	/**
	 * 查询近30天会员增加数量和趋势
	 * 
	 * @return
	 */
	@Override
	public BigInteger[] findAlmostOneMonthMemberAdd(String type) {
		BigInteger[] result = new BigInteger[30];
		String[] date = getAlmostOneMonthDate();
		for (int i = 0; i < date.length; i++) {
			StringBuffer sql = new StringBuffer();
			if (i == 29) {
				Calendar yesterday = Calendar.getInstance();
				yesterday.add(Calendar.DATE, -1);
				String startTime = new SimpleDateFormat("yyyy-MM-dd 00:00:00").format(yesterday.getTime());
				String endTime = new SimpleDateFormat("yyyy-MM-dd 23:59:59").format(yesterday.getTime());
				if (StringUtil.isNotBlank(type)) {
					sql.append("select count(1) from gjf_member_info where TYPE_ = '").append(type)
							.append("' and ADD_TIME_ between '").append(startTime).append("' and '").append(endTime)
							.append("'");
				} else {
					sql.append("select count(1) from gjf_member_info where ADD_TIME_ between '").append(startTime)
							.append("' and '").append(endTime).append("'");
				}
			} else {
				if (StringUtil.isNotBlank(type)) {
					sql.append("select count(1) from gjf_member_info where TYPE_ = '").append(type)
							.append("' and ADD_TIME_ between '").append(date[i]).append("' and '").append(date[i + 1])
							.append("'");
				} else {
					sql.append("select count(1) from gjf_member_info where ADD_TIME_ between '").append(date[i])
							.append("' and '").append(date[i + 1]).append("'");
				}
			}
			result[i] = (BigInteger) getCurrentSession().createSQLQuery(sql.toString()).uniqueResult();

		}
		return result;
	}

	/**
	 * 查询分红趋势
	 */
	@Override
	public BigDecimal[] findAlmostOneMonthBenefitDay(String type) {
		BigDecimal[] result = new BigDecimal[30];
		String[] date = getAlmostOneMonthDate();
		String value = null;
		if ("1".equals(type)) {
			value = "MEMBER_MONEY_";
		} else if ("2".equals(type)) {
			value = "MERCHANTS_MONEY_";
		} else if ("3".equals(type)) {
			value = "MEMBER_REAL_MONEY_";
		} else if ("4".equals(type)) {
			value = "MERCHANTS_REAL_MONEY_";
		}
		for (int i = 0; i < date.length; i++) {
			StringBuffer sql = new StringBuffer();
			if (i == 29) {
				Calendar yesterday = Calendar.getInstance();
				yesterday.add(Calendar.DATE, -1);
				String startTime = new SimpleDateFormat("yyyy-MM-dd 00:00:00").format(yesterday.getTime());
				String endTime = new SimpleDateFormat("yyyy-MM-dd 23:59:59").format(yesterday.getTime());
				sql.append("select IFNULL(").append(value).append(",0) from gjf_benefit_day where ACT_TIME_ >= '")
						.append(startTime).append("' and ACT_TIME_ <= '").append(endTime).append("'");
			} else {
				sql.append("select IFNULL(").append(value).append(",0) from gjf_benefit_day where ACT_TIME_ >= '")
						.append(date[i]).append("' and ACT_TIME_ < '").append(date[i + 1]).append("'");
			}
			BigDecimal temp = (BigDecimal) getCurrentSession().createSQLQuery(sql.toString()).uniqueResult();
			if (ObjValid.isValid(temp)) {
				result[i] = temp;
			} else {
				result[i] = new BigDecimal("0.00");
			}
		}
		return result;
	}

	/**
	 * 查询近三个月会员的消费额,趋势
	 */
	@Override
	public BigDecimal[] findAlmostThreeMonthMemberTurnover(Long id) {
		// 包括本月的最近三个月月初和月末日期
		String[] startDate = getAlmostThreeMonth("0");
		String[] endDate = getAlmostThreeMonth("1");
		BigDecimal[] result = new BigDecimal[3];
		for (int i = 0; i < 3; i++) {
			result[i] = new BigDecimal("0.00");
			StringBuffer sql = new StringBuffer();
			sql.append(
					"select IFNULL(sum(ORDER_TOTAL_AMOUNT_),0) from gjf_order_info where ORDER_STATUS_ IN ('1','2','3') and IS_DEL_ = '1' and MEMBER_ID_ = ")
					.append(id).append(" and PAY_TIME_ between '").append(startDate[i]).append("' and '")
					.append(endDate[i]).append("'");
			result[i] = (BigDecimal) getCurrentSession().createSQLQuery(sql.toString()).uniqueResult();
		}
		return result;
	}

	/**
	 * 获得近30天的日期
	 * 
	 * @return
	 */
	private String[] getAlmostOneMonthDate() {
		int n = 30;
		String[] date = new String[30];
		Calendar now = Calendar.getInstance();
		for (int i = 1; i <= date.length; i++) {
			now.add(Calendar.DATE, -1);
			String endDate = new SimpleDateFormat("yyyy-MM-dd").format(now.getTime());
			date[n - i] = endDate;
		}
		return date;
	}

	/**
	 * 获得本月以及前两月的开始日期和结束日期
	 * 
	 * @param type
	 *            0：月起始日期 1：月结束日期
	 * @return
	 */
	private String[] getAlmostThreeMonth(String type) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String[] date = new String[3];
		int n = 0;
		for (int i = 2; i >= 0; i--) {
			Calendar calendar = Calendar.getInstance();
			int month = calendar.get(Calendar.MONTH);
			if ("0".equals(type)) {
				calendar.add(Calendar.MONTH, -i);
				calendar.set(Calendar.DAY_OF_MONTH, 1);
				date[n] = sf.format(calendar.getTime());
			} else if ("1".equals(type)) {
				calendar.set(Calendar.MONTH, month - i);
				calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
				date[n] = sf.format(calendar.getTime()).toString();
			}
			n++;
		}
		return date;
	}

	/**
	 * 统计资金池
	 */
	@Override
	public BigDecimal findCashPool(String type) {
		StringBuffer sql = new StringBuffer();
		if ("5".equals(type)) {
			sql.append(
					"select IFNULL(sum(TRADE_MONEY_),0) from gjf_member_trade_divi_history where TRADE_STATUS_ IN ('0','1') and TRADE_TYPE_ = '4'");
		} else if ("6".equals(type)) {
			sql.append(
					"select IFNULL(sum(TRADE_MONEY_),0) from gjf_member_trade_divi_history where TRADE_STATUS_ IN ('0','1') and TRADE_TYPE_ = '5'");
		} else if ("7".equals(type)) {
			sql.append(
					"select IFNULL(sum(TRADE_MONEY_),0) from gjf_member_trade_divi_history where TRADE_STATUS_ IN ('0','1') and TRADE_TYPE_ = '6'");
		} else {
			sql.append(
					"select COALESCE(sum(benefitMoney),0) from GjfBenefitHistory where tradeStatus = '1' and benefitType = :benefitType");
			return (BigDecimal) getCurrentSession().createQuery(sql.toString()).setParameter("benefitType", type)
					.uniqueResult();
		}
		return (BigDecimal) getCurrentSession().createSQLQuery(sql.toString()).uniqueResult();
	}

	/**
	 * 查询提现手续费,责任险
	 * 
	 * @param type
	 *            0:手续费 1:责任险
	 * @return
	 */
	@Override
	public BigDecimal findTaxMoney(String type) {
		StringBuffer sql = new StringBuffer();
		if ("0".equals(type)) {
			sql.append(
					"select IFNULL(sum(TAX_MONEY_),0) from gjf_member_trade where TRADE_TYPE_ = '1' and TRADE_STATUS_ = '1'");
		} else if ("1".equals(type)) {
			sql.append(
					"select IFNULL(sum(INSURANCE_MONEY_),0) from gjf_member_trade where TRADE_TYPE_ = '1' and TRADE_STATUS_ = '1'");
		}
		BigDecimal result = (BigDecimal) getCurrentSession().createSQLQuery(sql.toString()).uniqueResult();
		if (ObjValid.isNotValid(result)) {
			result = new BigDecimal("0.00");
		}
		return result;
	}

	/**
	 * 查询订单累计成交额
	 * 
	 * @return
	 */
	@Override
	public BigDecimal findOrderTotalAmount() {
		String sql = "select IFNULL(sum(ORDER_TOTAL_AMOUNT_),0) from gjf_order_info where ORDER_STATUS_ = '3' and IS_DEL_ = '1' and PAY_TYPE_ NOT IN ('7','8')";
		BigDecimal temp1 = (BigDecimal) getCurrentSession().createSQLQuery(sql.toString()).uniqueResult();
		String sql1 = "select IFNULL(sum(TRADE_MONEY_),0) from gjf_member_trade_benefit where TRADE_STATUS_ = '1'";
		BigDecimal temp2 = (BigDecimal) getCurrentSession().createSQLQuery(sql1.toString()).uniqueResult();
		BigDecimal result = temp1.add(temp2);
		return result;
	}

	/***** 每天定时统计 ********/
	/*
	 * 统计总报表
	 * 
	 * @see cc.messcat.gjfeng.dao.count.CountInfoDao#updateSummaryReport()
	 */
	@Override
	public ResultVo updateSummaryReport() {
		String procdureSummaryReport = "{Call gjf_calculate_summary_report_pro(?)}";
		getCurrentSession().createSQLQuery(procdureSummaryReport).setParameter(0, 1).executeUpdate();
		return new ResultVo(200, "统计成功", null);
	}

	/*
	 * 统计让利日报表
	 * 
	 * @see cc.messcat.gjfeng.dao.count.CountInfoDao#updateBenefitReport()
	 */
	@Override
	public ResultVo updateBenefitReport() {
		String procdureBenefitReport = "{Call gjf_calculate_benefit_report_pro(?)}";
		getCurrentSession().createSQLQuery(procdureBenefitReport).setParameter(0, 1).executeUpdate();
		return new ResultVo(200, "统计成功", null);
	}

	/*
	 * 统计福利产出日报表
	 * 
	 * @see cc.messcat.gjfeng.dao.count.CountInfoDao#updateWealOutputReport()
	 */
	@Override
	public ResultVo updateWealOutputReport() {
		String procdureWealOutputReport = "{Call gjf_calculate_output_report_pro(?)}";
		getCurrentSession().createSQLQuery(procdureWealOutputReport).setParameter(0, 1).executeUpdate();
		return new ResultVo(200, "统计成功", null);
	}

	/*
	 * 统计福利派发报表
	 * 
	 * @see cc.messcat.gjfeng.dao.count.CountInfoDao#updateWealPayoutReport()
	 */
	@Override
	public ResultVo updateWealPayoutReport() {
		String procdureWealPayoutReport = "{Call gjf_calculate_payout_report_pro(?)}";
		getCurrentSession().createSQLQuery(procdureWealPayoutReport).setParameter(0, 1).executeUpdate();
		return new ResultVo(200, "统计成功", null);
	}

	/*
	 * 统计提现日报表
	 * 
	 * @see cc.messcat.gjfeng.dao.count.CountInfoDao#updateWithdrawReport()
	 */
	@Override
	public ResultVo updateWithdrawReport() {
		// 1.提现成功金额
		String sql = "INSERT INTO gjf_withdraw_report(MONEY_,SUCCESS_MONEY_,FAIL_MONEY_,ADD_TIME_) VALUES("
				+ "(SELECT IFNULL(SUM(TRADE_MONEY_),0) FROM gjf_member_trade WHERE TRADE_TYPE_='1' AND (TRADE_STATUS_='1' OR TRADE_STATUS_='2') AND DATE_FORMAT(DEAL_TIME_,'%Y-%m-%d')=DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 1 DAY),'%Y-%m-%d')),"
				+ "(SELECT IFNULL(SUM(TRADE_MONEY_),0) FROM gjf_member_trade WHERE TRADE_TYPE_='1' AND TRADE_STATUS_='1' AND DATE_FORMAT(DEAL_TIME_,'%Y-%m-%d')=DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 1 DAY),'%Y-%m-%d')),"
				+ "(SELECT IFNULL(SUM(TRADE_MONEY_),0) FROM gjf_member_trade WHERE TRADE_TYPE_='1' AND TRADE_STATUS_='2' AND DATE_FORMAT(DEAL_TIME_,'%Y-%m-%d')=DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 1 DAY),'%Y-%m-%d')),"
				+ "DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 1 DAY),'%Y-%m-%d'))";
		getCurrentSession().createSQLQuery(sql).executeUpdate();
		return new ResultVo(200, "统计成功", null);
	}

	/*
	 * 修改总报表和福利派发报表的福利派发为实际派发额
	 * 
	 * @see
	 * cc.messcat.gjfeng.dao.count.CountInfoDao#updateSummaryAndPayoutReport()
	 */
	public ResultVo updateSummaryAndPayoutReport() {
		String procdureSummaryAndPayoutReport = "{Call gjf_calculate_update_summary_payout_report_pro(?)}";
		getCurrentSession().createSQLQuery(procdureSummaryAndPayoutReport).setParameter(0, 1).executeUpdate();
		return new ResultVo(200, "统计成功", null);
	}

	/*
	 * 统计资金池表
	 */
	@Override
	public ResultVo updatePoolReport() {
		String poolReport = "{Call gjf_calculate_pool_report_pro(?)}";
		getCurrentSession().createSQLQuery(poolReport).setParameter(0, 1).executeUpdate();
		return new ResultVo(200, "统计成功", null);
	}

	/***** 每天定时统计 end ********/

	/*
	 * 查询历史交易总额
	 * 
	 * @see cc.messcat.gjfeng.dao.count.CountInfoDao#findHistoryTurnover()
	 */
	@Override
	public BigDecimal findHistoryTurnover() {
		String sql = "select IFNULL(sum(TRADE_MONEY_),0) from gjf_member_trade_benefit where TRADE_STATUS_ = '1'";
		BigDecimal result = (BigDecimal) getCurrentSession().createSQLQuery(sql).uniqueResult();
		result = result.add(findOrderTurnOverByPayType("0"));
		result = result.add(findOrderTurnOverByPayType("1"));
		result = result.add(findOrderTurnOverByPayType("3"));
		result = result.add(findOrderTurnOverByPayType("4"));
		return result;
	}

	/*
	 * 根据支付方式查询订单总交易额
	 */
	public BigDecimal findOrderTurnOverByPayType(String type) {
		Map<String, Object> arrMap = new HashMap<String, Object>();
		String sql = "select COALESCE(sum(orderTotalAmount),0) from GjfOrderInfo where orderStatus IN ('1','2','3')";
		if (ObjValid.isValid(type)) {
			arrMap.put("payType", type);
			sql += " and payType = :payType";
		}
		return (BigDecimal) getCurrentSession().createQuery(sql).setProperties(arrMap).uniqueResult();
	}

	/**
	 * 统计各档分红权人数和总数
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map<String, Object> countDivi(String type, String min, String max) {
		StringBuffer sql = new StringBuffer();
		Map<String, Object> arrMap = new HashMap<String, Object>();
		if ("0".equals(type)) {
			sql.append(
					"select count(DIVIDENDS_NUM_),IFNULL(sum(DIVIDENDS_NUM_),0) from gjf_member_info where STATUS_ = '1' AND IS_DEL_='1' ");
			if (StringUtil.isNotBlank(min)) {
				arrMap.put("min", min);
				sql.append(" and DIVIDENDS_NUM_ >= :min ");
			}
			if (StringUtil.isNotBlank(max)) {
				arrMap.put("max", max);
				sql.append(" and DIVIDENDS_NUM_ < :max ");
			}
		}
		if ("1".equals(type)) {
			sql.append(
					"select count(STORE_DIVIDENDS_NUM_),IFNULL(sum(STORE_DIVIDENDS_NUM_),0) from gjf_store_info where STORE_STATUS_ = '1' ");
			if (StringUtil.isNotBlank(min)) {
				arrMap.put("min", min);
				sql.append(" and STORE_DIVIDENDS_NUM_ >= :min ");
			}
			if (StringUtil.isNotBlank(max)) {
				arrMap.put("max", max);
				sql.append(" and STORE_DIVIDENDS_NUM_ < :max ");
			}
		}
		List list = getCurrentSession().createSQLQuery(sql.toString()).setProperties(arrMap).list();
		String[] param = { "amount", "totalNum" };
		list = BeanUtil.changeObjectArrayToMaps(list, param);
		return (Map<String, Object>) list.get(0);
	}

	/**
	 * 查询用户未使用授信额 分页 模糊查询
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Pager findStoreCreditLine(String storeNum, String storeName, String memeberName, int pageNo, int pageSize,
			String orderType) {
		StringBuffer sql = new StringBuffer();
		Map<String, Object> arrMap = new HashMap<String, Object>();
		sql.append(
				"SELECT s.STORE_NUM_,s.STORE_NAME_,m.NAME_,m.LINE_OF_CREDIT_,m.ID_,m.MOBILE_ FROM gjf_store_info AS s LEFT JOIN gjf_member_info AS m ON s.MEMBER_ID_ = m.ID_ WHERE  m.TYPE_ = '1'  ");
		if (StringUtil.isNotBlank(storeNum)) {
			arrMap.put("storeNum", "%" + storeNum + "%");
			sql.append(" and s.STORE_NUM_ like :storeNum ");
		}
		if (StringUtil.isNotBlank(storeName)) {
			arrMap.put("storeName", "%" + storeName + "%");
			sql.append(" and s.STORE_NAME_ like :storeName ");
		}
		if (StringUtil.isNotBlank(memeberName)) {
			arrMap.put("memeberName", "%" + memeberName + "%");
			sql.append(" and m.NAME_ like :memeberName ");
		}
		if (ObjValid.isNotValid(orderType)) {
			sql.append(" order by m.ADD_TIME_ DESC");
		} else if ("1".equals(orderType)) {
			sql.append(" order by m.LINE_OF_CREDIT_ ASC");
		} else if ("2".equals(orderType)) {
			sql.append(" order by m.LINE_OF_CREDIT_ DESC");
		}
		List list = getCurrentSession().createSQLQuery(sql.toString()).setProperties(arrMap).setMaxResults(pageSize)
				.setFirstResult((pageNo - 1) * pageSize).list();
		String[] param = { "num", "sName", "name", "creditLine", "memberId", "mobile" };
		list = BeanUtil.changeObjectArrayToMaps(list, param);
		String sqlCount = sql.toString().replace(
				"SELECT s.STORE_NUM_,s.STORE_NAME_,m.NAME_,m.LINE_OF_CREDIT_,m.ID_,m.MOBILE_", "select count(1)");
		BigInteger count = (BigInteger) getCurrentSession().createSQLQuery(sqlCount).uniqueResult();
		Pager pager = new Pager(pageSize, pageNo, count.intValue(), list);
		return pager;
	}

	/**
	 * 区域业绩
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param provinceId
	 * @param cityId
	 * @param areaId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Pager findRegionalPerformance(int pageNo, int pageSize, Long provinceId, Long cityId, Long areaId,
			String startTime, String endTime) {
		StringBuffer sql = new StringBuffer();
		Map<String, Object> arrMap = new HashMap<String, Object>();
		sql.append(" SELECT m.NAME_,s.STORE_NAME_,b.TRADE_MONEY_,b.ADD_TIME_,p.PROVINCE_,c.CITY_,a.AREA_ "
				+ " FROM gjf_member_trade_benefit b LEFT JOIN gjf_member_info m ON b.MEMBER_ID_ = m.ID_ "
				+ " LEFT JOIN gjf_store_info s ON b.STORE_ID_ = s.ID_ LEFT JOIN gjf_address_province p "
				+ " ON s.PROVINCE_ID_ = p.ID_ LEFT JOIN gjf_address_city c ON s.CITY_ID_ = c.ID_ LEFT JOIN gjf_address_area a "
				+ "ON s.AREA_ID_ = a.ID_ where b.TRADE_STATUS_ = '1' ");
		if (ObjValid.isValid(provinceId)) {
			arrMap.put("provinceId", provinceId);
			sql.append(" and p.PROVINCE_ID_ = :provinceId ");
		}
		if (ObjValid.isValid(cityId)) {
			arrMap.put("cityId", cityId);
			sql.append(" and c.CITY_ID_ = :cityId ");
		}
		if (ObjValid.isValid(areaId)) {
			arrMap.put("areaId", areaId);
			sql.append(" and a.AREA_ID_ = :areaId ");
		}
		if (ObjValid.isValid(startTime)) {
			arrMap.put("startTime", startTime);
			sql.append(" and b.ADD_TIME_ >= :startTime ");
		}
		if (ObjValid.isValid(endTime)) {
			arrMap.put("endTime", endTime);
			sql.append(" and b.ADD_TIME_ < :endTime ");
		}
		sql.append(" order by b.ADD_TIME_ DESC");
		String sql0 = sql.toString().replace(
				"SELECT m.NAME_,s.STORE_NAME_,b.TRADE_MONEY_,b.ADD_TIME_,p.PROVINCE_,c.CITY_,a.AREA_",
				"select count(1)");
		BigInteger count = (BigInteger) getCurrentSession().createSQLQuery(sql0).setProperties(arrMap).uniqueResult();
		List list = getCurrentSession().createSQLQuery(sql.toString()).setProperties(arrMap).setMaxResults(pageSize)
				.setFirstResult((pageNo - 1) * pageSize).list();
		String[] param = { "name", "storeName", "tradeMoney", "addTime", "province", "city", "area" };
		list = BeanUtil.changeObjectArrayToMaps(list, param);
		Pager pager = new Pager(pageSize, pageNo, count.intValue(), list);
		return pager;
	}

	/**
	 * 统计当前条件区域业绩
	 */
	@Override
	public BigDecimal findCountRegionalPerformance(Long provinceId, Long cityId, Long areaId, String startTime,
			String endTime) {
		StringBuffer sql = new StringBuffer();
		Map<String, Object> arrMap = new HashMap<String, Object>();
		sql.append(
				" SELECT IFNULL(sum(b.TRADE_MONEY_),0) FROM gjf_member_trade_benefit b LEFT JOIN gjf_member_info m ON b.MEMBER_ID_ = m.ID_ "
						+ " LEFT JOIN gjf_store_info s ON b.STORE_ID_ = s.ID_ LEFT JOIN gjf_address_province p "
						+ " ON s.PROVINCE_ID_ = p.ID_ LEFT JOIN gjf_address_city c ON s.CITY_ID_ = c.ID_ LEFT JOIN gjf_address_area a "
						+ "ON s.AREA_ID_ = a.ID_ where b.TRADE_STATUS_ = '1' ");
		if (ObjValid.isValid(provinceId)) {
			arrMap.put("provinceId", provinceId);
			sql.append(" and p.PROVINCE_ID_ = :provinceId ");
		}
		if (ObjValid.isValid(cityId)) {
			arrMap.put("cityId", cityId);
			sql.append(" and c.CITY_ID_ = :cityId ");
		}
		if (ObjValid.isValid(areaId)) {
			arrMap.put("areaId", areaId);
			sql.append(" and a.AREA_ID_ = :areaId ");
		}
		if (ObjValid.isValid(startTime)) {
			arrMap.put("startTime", startTime);
			sql.append(" and b.ADD_TIME_ >= :startTime ");
		}
		if (ObjValid.isValid(endTime)) {
			arrMap.put("endTime", endTime);
			sql.append(" and b.ADD_TIME_ < :endTime ");
		}
		return (BigDecimal) getCurrentSession().createSQLQuery(sql.toString()).setProperties(arrMap).uniqueResult();
	}

	/**
	 * 根据会员类型统计会员数量
	 */
	@Override
	public BigInteger findMemberAmountByType(String type) {
		StringBuffer sql = new StringBuffer();
		Map<String, Object> arrMap = new HashMap<String, Object>();
		sql.append("select count(1) from gjf_member_info where STATUS_ = '1' and IS_DEL_ = '1' ");
		if (StringUtil.isNotBlank(type)) {
			arrMap.put("type", type);
			sql.append(" and TYPE_ = :type ");
		}
		BigInteger result = (BigInteger) getCurrentSession().createSQLQuery(sql.toString()).setProperties(arrMap)
				.uniqueResult();
		return result;
	}

	/**
	 * 会员分页
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Pager findMemberAmount(int pageNo, int pageSize) {
		String sql = "select ID_,NAME_,MOBILE_,NICK_NAME_,SEX_,CUMULATIVE_MONEY_,TYPE_ from gjf_member_info where STATUS_ = '1' and IS_DEL_ = '1' order by ADD_TIME_ DESC";
		List list = getCurrentSession().createSQLQuery(sql).setMaxResults(pageSize)
				.setFirstResult((pageNo - 1) * pageSize).list();
		String[] param = { "id", "name", "mobile", "nickName", "sex", "cumulativeMoney", "type" };
		list = BeanUtil.changeObjectArrayToMaps(list, param);
		String sqlCount = sql.replace("select ID_,NAME_,MOBILE_,NICK_NAME_,SEX_,CUMULATIVE_MONEY_,TYPE_",
				"select count(1)");
		Pager pager = new Pager(pageSize, pageNo,
				((BigInteger) getCurrentSession().createSQLQuery(sqlCount).uniqueResult()).intValue(), list);
		return pager;
	}

	/**
	 * 查询总报表
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Pager findSummaryReport(int pageNo, int pageSize, String addTime, String endTime, String ste) {
		StringBuffer sql = new StringBuffer();
		Map<String, Object> arrMap = new HashMap<String, Object>();
		sql.append("select * from gjf_summary_report where 1=1");
		if (ObjValid.isValid(addTime)) {
			arrMap.put("addTime", addTime);
			sql.append(" and ADD_TIME_  >= :addTime ");
		}
		if (ObjValid.isValid(endTime)) {
			arrMap.put("endTime", endTime);
			sql.append(" and ADD_TIME_  <= :endTime ");
		}
		sql.append(" order by ADD_TIME_ DESC ");
		List list = null;
		if ("1".equals(ste)) {
			list = getCurrentSession().createSQLQuery(sql.toString()).addEntity(GjfSummaryReport.class)
					.setProperties(arrMap).list();
		} else {
			list = getCurrentSession().createSQLQuery(sql.toString()).addEntity(GjfSummaryReport.class)
					.setProperties(arrMap).setMaxResults(pageSize).setFirstResult((pageNo - 1) * pageSize).list();
		}
		String sqlCount = sql.toString().replace("select *", "select count(1)");
		Pager pager = new Pager(pageSize, pageNo,
				((BigInteger) getCurrentSession().createSQLQuery(sqlCount).setProperties(arrMap).uniqueResult())
						.intValue(),
				list);
		return pager;
	}

	/**
	 * 查询福利产出表
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Pager findWealOutputReport(int pageNo, int pageSize, String addTime, String endTime, String ste) {
		StringBuffer sql = new StringBuffer();
		Map<String, Object> arrMap = new HashMap<String, Object>();
		sql.append("select * from gjf_weal_output_report where 1=1");
		if (ObjValid.isValid(addTime)) {
			arrMap.put("addTime", addTime);
			sql.append(" and ADD_TIME_  >= :addTime ");
		}
		if (ObjValid.isValid(endTime)) {
			arrMap.put("endTime", endTime);
			sql.append(" and ADD_TIME_  <= :endTime ");
		}
		sql.append(" order by ADD_TIME_ DESC ");
		List list = null;
		if ("1".equals(ste)) {
			list = getCurrentSession().createSQLQuery(sql.toString()).addEntity(GjfWealOutputReport.class)
					.setProperties(arrMap).list();
		} else {
			list = getCurrentSession().createSQLQuery(sql.toString()).addEntity(GjfWealOutputReport.class)
					.setProperties(arrMap).setMaxResults(pageSize).setFirstResult((pageNo - 1) * pageSize).list();
		}
		String sqlCount = sql.toString().replace("select *", "select count(1)");
		Pager pager = new Pager(pageSize, pageNo,
				((BigInteger) getCurrentSession().createSQLQuery(sqlCount).setProperties(arrMap).uniqueResult())
						.intValue(),
				list);
		return pager;
	}

	/**
	 * 查询福利派发表
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param addTime
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Pager findWealPayoutReport(int pageNo, int pageSize, String addTime, String endTime, String ste) {
		StringBuffer sql = new StringBuffer();
		Map<String, Object> arrMap = new HashMap<String, Object>();
		sql.append("select * from gjf_weal_payout_report where 1=1");
		if (ObjValid.isValid(addTime)) {
			arrMap.put("addTime", addTime);
			sql.append(" and ADD_TIME_  >= :addTime ");
		}
		if (ObjValid.isValid(endTime)) {
			arrMap.put("endTime", endTime);
			sql.append(" and ADD_TIME_  <= :endTime ");
		}
		sql.append(" order by ADD_TIME_ DESC ");
		List list = null;
		if ("1".equals(ste)) {
			list = getCurrentSession().createSQLQuery(sql.toString()).addEntity(GjfWealPayoutReport.class)
					.setProperties(arrMap).list();
		} else {
			list = getCurrentSession().createSQLQuery(sql.toString()).addEntity(GjfWealPayoutReport.class)
					.setProperties(arrMap).setMaxResults(pageSize).setFirstResult((pageNo - 1) * pageSize).list();
		}
		String sqlCount = sql.toString().replace("select *", "select count(1)");
		Pager pager = new Pager(pageSize, pageNo,
				((BigInteger) getCurrentSession().createSQLQuery(sqlCount).setProperties(arrMap).uniqueResult())
						.intValue(),
				list);
		return pager;
	}

	/**
	 * 提现日报表
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param addTime
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Pager findWithdrawReport(int pageNo, int pageSize, String addTime, String endTime, String ste) {
		StringBuffer sql = new StringBuffer();
		Map<String, Object> arrMap = new HashMap<String, Object>();
		sql.append("select * from gjf_withdraw_report where 1=1");
		if (ObjValid.isValid(addTime)) {
			arrMap.put("addTime", addTime);
			sql.append(" and ADD_TIME_  >= :addTime ");
		}
		if (ObjValid.isValid(endTime)) {
			arrMap.put("endTime", endTime);
			sql.append(" and ADD_TIME_  <= :endTime ");
		}
		sql.append(" order by ADD_TIME_ DESC ");
		List list = null;
		if ("1".equals(ste)) {
			list = getCurrentSession().createSQLQuery(sql.toString()).addEntity(GjfWithdrawReport.class)
					.setProperties(arrMap).list();
		} else {
			list = getCurrentSession().createSQLQuery(sql.toString()).addEntity(GjfWithdrawReport.class)
					.setProperties(arrMap).setMaxResults(pageSize).setFirstResult((pageNo - 1) * pageSize).list();
		}
		String sqlCount = sql.toString().replace("select *", "select count(1)");
		Pager pager = new Pager(pageSize, pageNo,
				((BigInteger) getCurrentSession().createSQLQuery(sqlCount).setProperties(arrMap).uniqueResult())
						.intValue(),
				list);
		return pager;
	}

	/**
	 * 让利日报表
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param addTime
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Pager findBenefitReport(int pageNo, int pageSize, String addTime, String endTime, String ste) {
		StringBuffer sql = new StringBuffer();
		Map<String, Object> arrMap = new HashMap<String, Object>();
		sql.append("select * from gjf_benefit_report where 1=1");
		if (ObjValid.isValid(addTime)) {
			arrMap.put("addTime", addTime);
			sql.append(" and ADD_TIME_  >= :addTime ");
		}
		if (ObjValid.isValid(endTime)) {
			arrMap.put("endTime", endTime);
			sql.append(" and ADD_TIME_  <= :endTime ");
		}
		sql.append(" order by ADD_TIME_ DESC ");
		List list = null;
		if ("1".equals(ste)) {
			list = getCurrentSession().createSQLQuery(sql.toString()).addEntity(GjfBenefitReport.class)
					.setProperties(arrMap).list();
		} else {
			list = getCurrentSession().createSQLQuery(sql.toString()).addEntity(GjfBenefitReport.class)
					.setProperties(arrMap).setMaxResults(pageSize).setFirstResult((pageNo - 1) * pageSize).list();
		}
		String sqlCount = sql.toString().replace("select *", "select count(1)");
		Pager pager = new Pager(pageSize, pageNo,
				((BigInteger) getCurrentSession().createSQLQuery(sqlCount).setProperties(arrMap).uniqueResult())
						.intValue(),
				list);
		return pager;
	}

	/**
	 * 今日O2O订单
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Pager findTodayO2OOrders(int pageNo, int pageSize) {
		StringBuffer sql = new StringBuffer();
		// 利用Apache lang包快速获取凌晨0点0分0秒，23点59分59秒字符串
		String startTime = DateFormatUtils.format(new Date(), "yyyy-MM-dd 00:00:00");
		String endTime = DateFormatUtils.format(new Date(), "yyyy-MM-dd 23:59:59");
		sql.append(
				"select b.TRADE_NO_,m.NAME_,s.STORE_NAME_,b.TRADE_MONEY_,b.ADD_TIME_,b.PAY_TYPE_,b.TRADE_STATUS_ from gjf_member_trade_benefit b left join gjf_member_info m on b.MEMBER_ID_ = m.ID_ left join gjf_store_info s on b.STORE_ID_ = s.ID_ where b.ADD_TIME_ between '")
				.append(startTime).append("' and '").append(endTime).append("' ");
		List list = getCurrentSession().createSQLQuery(sql.toString()).setMaxResults(pageSize)
				.setFirstResult((pageNo - 1) * pageSize).list();
		String[] param = { "tradeNo", "name", "storeName", "tradeMoney", "addTime", "payType", "tradeStatus" };
		list = BeanUtil.changeObjectArrayToMaps(list, param);
		String sql0 = sql.toString().replace(
				"select b.TRADE_NO_,m.NAME_,s.STORE_NAME_,b.TRADE_MONEY_,b.ADD_TIME_,b.PAY_TYPE_,b.TRADE_STATUS_",
				"select count(1)");
		BigInteger count = (BigInteger) getCurrentSession().createSQLQuery(sql0).uniqueResult();
		return new Pager(pageSize, pageNo, count.intValue(), list);
	}

	/**
	 * 查询池收入
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Pager findPoolIn(int pageNo, int pageSize, String op, String type) {
		String sql = null;
		if ("1".equals(op) && "8".equals(type)) {
			sql = "select MEMBER_INCOME_POOL_,ADD_TIME_ FROM gjf_pool_report order by ADD_TIME_ DESC";
		}
		if ("2".equals(op) && "9".equals(type)) {
			sql = "select MERCHANTS_INCOME_POOL_,ADD_TIME_ FROM gjf_pool_report order by ADD_TIME_ DESC";
		}
		if ("3".equals(op) && "10".equals(type)) {
			sql = "select PLATFORM_INCOME_POOL_,ADD_TIME_ FROM gjf_pool_report order by ADD_TIME_ DESC";
		}
		if ("4".equals(op) && "13".equals(type)) {
			sql = "select CITYAGENT_INCOME_POOL_,ADD_TIME_ FROM gjf_pool_report order by ADD_TIME_ DESC";
		}
		if ("5".equals(op) && "14".equals(type)) {
			sql = "select AREAAGENT_INCOME_POOL_,ADD_TIME_ FROM gjf_pool_report order by ADD_TIME_ DESC";
		}
		if ("6".equals(op) && "15".equals(type)) {
			sql = "select INDIVIAGENT_INCOME_POOL_,ADD_TIME_ FROM gjf_pool_report order by ADD_TIME_ DESC";
		}
		List list = getCurrentSession().createSQLQuery(sql).setMaxResults(pageSize)
				.setFirstResult((pageNo - 1) * pageSize).list();
		String[] param = { "income", "addTime" };
		list = BeanUtil.changeObjectArrayToMaps(list, param);
		String sqlCount = "select count(ID_) from gjf_pool_report";
		Pager pager = new Pager(pageSize, pageNo,
				((BigInteger) getCurrentSession().createSQLQuery(sqlCount).uniqueResult()).intValue(), list);
		return pager;
	}

	/**
	 * 查询池支出
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Pager findPoolOut(int pageNo, int pageSize, String op, String type) {
		String sql = null;
		if ("1".equals(op) && "0".equals(type)) {
			sql = "select MEMBER_EXPEND_POOL_,ADD_TIME_ FROM gjf_pool_report order by ADD_TIME_ DESC";
		}
		if ("2".equals(op) && "1".equals(type)) {
			sql = "select MERCHANTS_EXPEND_POOL_,ADD_TIME_ FROM gjf_pool_report order by ADD_TIME_ DESC";
		}
		if ("3".equals(op) && "5".equals(type)) {
			sql = "select CITYAGENT_EXPEND_POOL_,ADD_TIME_ FROM gjf_pool_report order by ADD_TIME_ DESC";
		}
		if ("4".equals(op) && "6".equals(type)) {
			sql = "select AREAAGENT_EXPEND_POOL_,ADD_TIME_ FROM gjf_pool_report order by ADD_TIME_ DESC";
		}
		if ("5".equals(op) && "7".equals(type)) {
			sql = "select INDIVIAGENT_EXPEND_POOL_,ADD_TIME_ FROM gjf_pool_report order by ADD_TIME_ DESC";
		}
		List list = getCurrentSession().createSQLQuery(sql).setMaxResults(pageSize)
				.setFirstResult((pageNo - 1) * pageSize).list();
		String[] param = { "expend", "addTime" };
		list = BeanUtil.changeObjectArrayToMaps(list, param);
		String sqlCount = "select count(ID_) from gjf_pool_report";
		Pager pager = new Pager(pageSize, pageNo,
				((BigInteger) getCurrentSession().createSQLQuery(sqlCount).uniqueResult()).intValue(), list);
		return pager;
	}

	/**
	 * 查询池收入支出明细
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Pager findPoolDetail(int pageNo, int pageSize, String addTime, String type) {
		// 利用Apache lang包快速获取凌晨0点0分0秒，23点59分59秒字符串
		String startTime = addTime + " 00:00:00";
		String endTime = addTime + " 23:59:59";
		StringBuffer sql = new StringBuffer();
		sql.append(
				"select BENEFIT_MONEY_,BENEFIT_MONEY_BF_,BENEFIT_MONEY_AF_,UNIT_PRICE_,ADD_TIME_,BENEFIT_TIME_,TRADE_TIME_,TRADE_STATUS_ from gjf_benefit_history"
						+ " where TRADE_STATUS_ = '1' and  BENEFIT_TYPE_ = :type and ADD_TIME_ >= :startTime and ADD_TIME_ < :endTime ");
		sql.append(" order by ADD_TIME_ desc");
		List list = getCurrentSession().createSQLQuery(sql.toString()).setParameter("tradeType", type)
				.setParameter("startTime", startTime).setParameter("endTime", endTime).setMaxResults(pageSize)
				.setFirstResult((pageNo - 1) * pageSize).list();
		String[] param = { "benefitMoney", "benefitMoneyBF", "benefitMoneyAF", "unitPrice", "addTime", "benefitTime",
				"tradeTime", "tradeStatus" };
		list = BeanUtil.changeObjectArrayToMaps(list, param);
		String sqlCount = sql.toString().replace(
				"select BENEFIT_MONEY_,BENEFIT_MONEY_BF_,BENEFIT_MONEY_AF_,UNIT_PRICE_,ADD_TIME_,BENEFIT_TIME_,TRADE_TIME_,TRADE_STATUS_",
				"select count(ID_) ");
		Pager pager = new Pager(pageSize, pageNo,
				((BigInteger) getCurrentSession().createSQLQuery(sqlCount).setParameter("tradeType", type)
						.setParameter("startTime", startTime).setParameter("endTime", endTime).uniqueResult())
								.intValue(),
				list);
		return pager;
	}

	@Override
	public Pager findTradeDiviByAgent(int pageNo, int pageSize, String addTime, String type) {
		// 利用Apache lang包快速获取凌晨0点0分0秒，23点59分59秒字符串
		String startTime = addTime + " 00:00:00";
		String endTime = addTime + " 23:59:59";
		String sql = "select TRADE_NO_,TRADE_MONEY_,TRADE_DIVI_NUM_,TRADE_UNIT_,TRADE_REAL_UNIT_,ADD_TIME_,TRADE_STATUS_ from gjf_member_trade_divi_history where TRADE_STATUS_ IN ('0','1') and TRADE_TYPE_ = :tradeType AND ADD_TIME_ >= :startTime and ADD_TIME_ < :endTime";
		List list = getCurrentSession().createSQLQuery(sql.toString()).setParameter("tradeType", type)
				.setParameter("startTime", startTime).setParameter("endTime", endTime).setMaxResults(pageSize)
				.setFirstResult((pageNo - 1) * pageSize).list();
		String[] param = { "tradeNo", "tradeMoney", "diviNum", "unit", "realUnit", "addTime", "tradeStatus" };
		list = BeanUtil.changeObjectArrayToMaps(list, param);
		String sqlCount = sql.toString().replace(
				"select TRADE_NO_,TRADE_MONEY_,TRADE_DIVI_NUM_,TRADE_UNIT_,TRADE_REAL_UNIT_,ADD_TIME_,TRADE_STATUS_",
				"select count(ID_) ");
		BigInteger count = (BigInteger) getCurrentSession().createSQLQuery(sqlCount).setParameter("tradeType", type)
				.setParameter("startTime", startTime).setParameter("endTime", endTime).uniqueResult();
		Pager pager = new Pager(pageSize, pageNo, count.intValue(), list);
		return pager;
	}

	/**
	 * 获取每天销售录入的统计数据
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Map countBenefitByTime(String addTime) {
		Map<String, BigDecimal> map = new HashMap<>();

		// 统计每天的让利记录 微信支付
		BigDecimal sumBeTradeMoney = (BigDecimal) getCurrentSession()
				.createSQLQuery(
						"select IFNULL(sum(TRADE_MONEY_),0) from gjf_member_trade_benefit where TRADE_STATUS_=1  and DATE_FORMAT(ADD_TIME_,'%Y%m%d')=:addTime")
				.setParameter("addTime", addTime).uniqueResult();
		map.put("sumBeTradeMoney", sumBeTradeMoney);

		// 统计每天的让利记录 微信支付
		BigDecimal sumBeWpay = (BigDecimal) getCurrentSession()
				.createSQLQuery(
						"select IFNULL(sum(BENEFIT_MONEY_),0) from gjf_member_trade_benefit where TRADE_STATUS_=1 and PAY_TYPE_=0 and DATE_FORMAT(ADD_TIME_,'%Y%m%d')=:addTime")
				.setParameter("addTime", addTime).uniqueResult();
		map.put("sumBeWpay", sumBeWpay);
		// 统计每天的让利记录 银联支付
		BigDecimal sumBeHpay = (BigDecimal) getCurrentSession()
				.createSQLQuery(
						"select IFNULL(sum(BENEFIT_MONEY_),0) from gjf_member_trade_benefit where TRADE_STATUS_=1 and PAY_TYPE_=2 and DATE_FORMAT(ADD_TIME_,'%Y%m%d')=:addTime")
				.setParameter("addTime", addTime).uniqueResult();
		map.put("sumBeHpay", sumBeHpay);

		// 统计每天的让利记录 支付宝支付
		BigDecimal sumZhiFuBaoHpay = (BigDecimal) getCurrentSession()
				.createSQLQuery(
						"select IFNULL(sum(BENEFIT_MONEY_),0) from gjf_member_trade_benefit where TRADE_STATUS_=1 and PAY_TYPE_=1 and DATE_FORMAT(ADD_TIME_,'%Y%m%d')=:addTime")
				.setParameter("addTime", addTime).uniqueResult();
		map.put("sumZhiFuBaoHpay", sumZhiFuBaoHpay);

		// 统计每天的让利记录 授信支付
		BigDecimal sumBeSpay = (BigDecimal) getCurrentSession()
				.createSQLQuery(
						"select IFNULL(sum(BENEFIT_MONEY_),0) from gjf_member_trade_benefit where TRADE_STATUS_=1 and PAY_TYPE_=4 and DATE_FORMAT(ADD_TIME_,'%Y%m%d')=:addTime")
				.setParameter("addTime", addTime).uniqueResult();
		map.put("sumBeSpay", sumBeSpay);
		// 统计每天的商城订单记录 余额支付
		BigDecimal sumOrderYpay = (BigDecimal) getCurrentSession()
				.createSQLQuery(
						"select IFNULL(sum(REAL_PAY_AMOUNT_),0) from gjf_order_info where ORDER_STATUS_=1 and PAY_TYPE_=0 and DATE_FORMAT(ADD_TIME_,'%Y%m%d')=:addTime")
				.setParameter("addTime", addTime).uniqueResult();
		map.put("sumOrderYpay", sumOrderYpay);
		// 统计每天的商城订单记录 微信支付
		BigDecimal sumOrderWpay = (BigDecimal) getCurrentSession()
				.createSQLQuery(
						"select IFNULL(sum(REAL_PAY_AMOUNT_),0) from gjf_order_info where ORDER_STATUS_=1 and PAY_TYPE_=1 and DATE_FORMAT(ADD_TIME_,'%Y%m%d')=:addTime")
				.setParameter("addTime", addTime).uniqueResult();
		map.put("sumOrderWpay", sumOrderWpay);
		// 统计每天的商城订单记录 银联支付
		BigDecimal sumOrderHpay = (BigDecimal) getCurrentSession()
				.createSQLQuery(
						"select IFNULL(sum(REAL_PAY_AMOUNT_),0) from gjf_order_info where ORDER_STATUS_=1 and PAY_TYPE_=3 and DATE_FORMAT(ADD_TIME_,'%Y%m%d')=:addTime")
				.setParameter("addTime", addTime).uniqueResult();
		map.put("sumOrderHpay", sumOrderHpay);
		// 统计每天的商城订单记录 责任消费支付
		BigDecimal sumOrderDpay = (BigDecimal) getCurrentSession()
				.createSQLQuery(
						"select IFNULL(sum(REAL_PAY_AMOUNT_),0) from gjf_order_info where ORDER_STATUS_=1 and PAY_TYPE_=8 and DATE_FORMAT(ADD_TIME_,'%Y%m%d')=:addTime")
				.setParameter("addTime", addTime).uniqueResult();
		map.put("sumOrderDpay", sumOrderDpay);
		// 统计每天的商城订单记录 支付宝支付
		BigDecimal sumOrderZhiFuBaopay = (BigDecimal) getCurrentSession()
				.createSQLQuery(
						"select IFNULL(sum(REAL_PAY_AMOUNT_),0) from gjf_order_info where ORDER_STATUS_=1 and PAY_TYPE_=2 and DATE_FORMAT(ADD_TIME_,'%Y%m%d')=:addTime")
				.setParameter("addTime", addTime).uniqueResult();
		map.put("sumOrderZhiFuBaopay", sumOrderZhiFuBaopay);

		// 统计每天授信记录 微信支付
		BigDecimal sumSXWpay = (BigDecimal) getCurrentSession()
				.createSQLQuery(
						"select IFNULL(sum(TRADE_MONEY_),0) from gjf_member_trade where TRADE_TYPE_=0 and TRADE_STATUS_=1 and PAY_TYPE_=1 and DATE_FORMAT(ADD_TIME_,'%Y%m%d')=:addTime")
				.setParameter("addTime", addTime).uniqueResult();
		map.put("sumSXWpay", sumSXWpay);
		// 统计每天授信记录 银联支付
		BigDecimal sumSXHpay = (BigDecimal) getCurrentSession()
				.createSQLQuery(
						"select IFNULL(sum(TRADE_MONEY_),0) from gjf_member_trade where TRADE_TYPE_=0 and TRADE_STATUS_=1 and PAY_TYPE_=3 and DATE_FORMAT(ADD_TIME_,'%Y%m%d')=:addTime")
				.setParameter("addTime", addTime).uniqueResult();
		map.put("sumSXHpay", sumSXHpay);
		// 统计每天授信记录 后台充值支付
		BigDecimal sumSXHTpay = (BigDecimal) getCurrentSession()
				.createSQLQuery(
						"select IFNULL(sum(TRADE_MONEY_),0) from gjf_member_trade where TRADE_TYPE_=0 and TRADE_STATUS_=1 and PAY_TYPE_=5 and DATE_FORMAT(ADD_TIME_,'%Y%m%d')=:addTime")
				.setParameter("addTime", addTime).uniqueResult();
		map.put("sumSXHTpay", sumSXHTpay);

		// 统计每天授信记录 支付宝支付
		BigDecimal sumZhifuBaopay = (BigDecimal) getCurrentSession()
				.createSQLQuery(
						"select IFNULL(sum(TRADE_MONEY_),0) from gjf_member_trade where TRADE_TYPE_=0 and TRADE_STATUS_=1 and PAY_TYPE_=2 and DATE_FORMAT(ADD_TIME_,'%Y%m%d')=:addTime")
				.setParameter("addTime", addTime).uniqueResult();
		map.put("sumZhifuBaopay", sumZhifuBaopay);

		// 直推会员奖励
		BigDecimal directMeMoney = (BigDecimal) getCurrentSession()
				.createSQLQuery(
						"select IFNULL(sum(DIRECT_MEMBER_MONEY_),0) from gjf_member_trade_benefit where TRADE_STATUS_=1  and DATE_FORMAT(ADD_TIME_,'%Y%m%d')=:addTime")
				.setParameter("addTime", addTime).uniqueResult();
		map.put("directMeMoney", directMeMoney);

		// 直推商家奖励
		BigDecimal directChMoney = (BigDecimal) getCurrentSession()
				.createSQLQuery(
						"select IFNULL(sum(DIRECT_MERCHANTS_MONEY_),0) from gjf_member_trade_benefit where TRADE_STATUS_=1 and DATE_FORMAT(ADD_TIME_,'%Y%m%d')=:addTime")
				.setParameter("addTime", addTime).uniqueResult();
		map.put("directChMoney", directChMoney);

		// 提现交易总额
		BigDecimal tradeMoney = (BigDecimal) getCurrentSession()
				.createSQLQuery(
						"select IFNULL(sum(TRADE_MONEY_),0) from gjf_member_trade where TRADE_STATUS_=1 and TRADE_TYPE_=1 and DATE_FORMAT(DEAL_TIME_,'%Y%m%d')=:addTime")
				.setParameter("addTime", addTime).uniqueResult();
		map.put("tradeMoney", tradeMoney);

		// 提现税收总额
		BigDecimal taxMoney = (BigDecimal) getCurrentSession()
				.createSQLQuery(
						"select IFNULL(sum(TAX_MONEY_),0) from gjf_member_trade where TRADE_STATUS_=1 and TRADE_TYPE_=1 and DATE_FORMAT(DEAL_TIME_,'%Y%m%d')=:addTime")
				.setParameter("addTime", addTime).uniqueResult();
		map.put("taxMoney", taxMoney);

		// 提现税收总额
		BigDecimal insMoney = (BigDecimal) getCurrentSession()
				.createSQLQuery(
						"select IFNULL(sum(INSURANCE_MONEY_),0) from gjf_member_trade where TRADE_STATUS_=1 and TRADE_TYPE_=1 and DATE_FORMAT(DEAL_TIME_,'%Y%m%d')=:addTime")
				.setParameter("addTime", addTime).uniqueResult();
		map.put("insMoney", insMoney);

		// 每天新增会员数量
		BigInteger newMember = (BigInteger) getCurrentSession()
				.createSQLQuery("select count(*) from gjf_member_info where DATE_FORMAT(ADD_TIME_,'%Y%m%d')=:addTime")
				.setParameter("addTime", addTime).uniqueResult();
		BigDecimal newMem = new BigDecimal(newMember);
		map.put("newMember", newMem);

		// 每天新增商户数量
		BigInteger newMc = (BigInteger) getCurrentSession()
				.createSQLQuery(
						"select count(*) from gjf_store_info where DATE_FORMAT(STORE_ADUIT_TIME_,'%Y%m%d')=:addTime")
				.setParameter("addTime", addTime).uniqueResult();
		BigDecimal newM = new BigDecimal(newMc);
		map.put("newMc", newM);

		// 获取个代分红金额
		BigDecimal generationMoney = (BigDecimal) getCurrentSession()
				.createSQLQuery(
						"select IFNULL(sum(TRADE_MONEY_),0) from gjf_member_trade_divi_history where TRADE_TYPE_=6 and DATE_FORMAT(ADD_TIME_,'%Y%m%d')=:addTime")
				.setParameter("addTime", addTime).uniqueResult();
		map.put("generationMoney", generationMoney);

		// 获取区代分红金额
		BigDecimal areaGenerationMoney = (BigDecimal) getCurrentSession()
				.createSQLQuery(
						"select IFNULL(sum(TRADE_MONEY_),0) from gjf_member_trade_divi_history where  TRADE_TYPE_=5 and DATE_FORMAT(ADD_TIME_,'%Y%m%d')=:addTime")
				.setParameter("addTime", addTime).uniqueResult();
		map.put("areaGenerationMoney", areaGenerationMoney);
		// 获取市代分红金额
		BigDecimal cityGenerationMoney = (BigDecimal) getCurrentSession()
				.createSQLQuery(
						"select IFNULL(sum(TRADE_MONEY_),0) from gjf_member_trade_divi_history where  TRADE_TYPE_=4 and DATE_FORMAT(ADD_TIME_,'%Y%m%d')=:addTime")
				.setParameter("addTime", addTime).uniqueResult();
		map.put("cityGenerationMoney", cityGenerationMoney);

		return map;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Map countTotalDataInfo(String beginTime, String endTime) {
		Map map = new HashMap<>();
		StringBuffer sql = new StringBuffer();
		sql.append(
				"select IFNULL(sum(BENERFIT_TRADE_MONEY_),0) benerfigTradeMoney,IFNULL(sum(BENERFIT_DATE_MONEY_),0) benerfitDateMoney,IFNULL(sum(BENERFIT_WEIPAY_MONEY_),0) benerfitWeipayMoney,IFNULL(sum(BENERFIT_YINL_MONEY_),0) benerfitYinlMoney,"
						+ "IFNULL(sum(BENERFIT_SX_MONEY_),0) benerfitSxMoney,IFNULL(sum(MALL_WEIPAY_MONEY_),0) mallWeiPayMoney,IFNULL(sum(MALL_YINLPAY_MONEY_),0) mallYinlPay,IFNULL(sum(MALL_ZERENPAY_MONEY_),0) mallZenrnPayMoney,IFNULL(sum(MALL_BANLAN_MONEY_),0) mallBanlanMoney,"
						+ "IFNULL(sum(SHOUXIN_LINES_MONEY_),0) shouxinLinesMoney,IFNULL(sum(SHOUXIN_WEIXINPAY_MONEY_),0) shouxinWeixinPayMoney,IFNULL(sum(SHOUXIN_YINLPAY_MONEY_),0) shouxinYinlPayMoney,IFNULL(sum(SHOUXIN_HOUTAIPAY_MONEY_),0) shouxinHouTaiPayMoney,"
						+ "IFNULL(sum(SHOUXIN_CONSUMPTION_MONEY_),0) shouxinCousumptionMoney,IFNULL(sum(SHOUXIN_SHENYU_MONEY_),0) shouxinShenyuMoney,IFNULL(sum(TOTAL_WEIPAY_MONEY_),0) totalWeiPayMoney,IFNULL(sum(TOTAL_WEIX_SHOUXUPAY_MONEY_),0) totalWeixShouxupayMoney,IFNULL(sum(TOTAL_YINLPAY_MONEY_),0) totalYinlpayMoney,IFNULL(sum(TOTAL_YINL_SHOUXU_MONEY_),0) totalYinlShouyuMoney,"
						+ "IFNULL(sum(TOTAL_WEIXIN_GET_MONEY_),0) totalWeixinGetMoney,IFNULL(sum(TOTAL_YINL_GET_MONEY_),0) totalYinlGetmoney,IFNULL(sum(MEMBER_ADD_DIVI_),0) memberAddDiv,IFNULL(sum(MEMBER_CAN_CANYU_DIVI_),0) memberCanUserDivi,IFNULL(sum(MEMBER_DANJIA_),0) memberDanjia,"
						+ "IFNULL(sum(MEMBER_SHIJIPAY_MONEY_),0) memberShiJipayMoney,IFNULL(sum(MEMBER_POOL_MONEY_),0) memberPoolMoney,IFNULL(sum(MEMBER_CHAYI_MONEY_),0) memberChayiMoney,IFNULL(sum(MERCHANTS_ADD_DIVI_),0) merchantsAddDivs,IFNULL(sum(MERCHANTS_CAN_CANYU_DIVI_),0) mercahntsCanUserDivi,"
						+ "IFNULL(sum(MERCHANTS_DANJIA_),0) merchantsDanJia,IFNULL(sum(MERCHANTS_SHIJIPAY_MONEY_),0) merchantsShiJiPay,IFNULL(sum(MERCHANTS_POOL_MONEY_),0) merchantsPoolMoney,IFNULL(sum(MERCHANTS_CHAYI_MONEY_),0) merchantsChaYiMoney,IFNULL(sum(ZHITUI_MEMBER_MONEY_),0) zhituiMemberMoney,"
						+ "IFNULL(sum(ZHITUI_MERCHANTS_MONEY_),0) zhituiMerchantsMoney,IFNULL(sum(TOTAL_BANLAN_DATE_MONEY_),0) totalBanlanDateMoney,IFNULL(sum(TOTAL_BANLAN_POOL_MONEY_),0) totalBanlanPoolMoney,IFNULL(sum(TOTAL_BANLAN_ZHITUI_MONEY_),0) totalBanlanZhituiMoney,IFNULL(sum(TOTAL_BANLAN_CHAYI_MONEY_),0) totalBanlanChayiMoney,"
						+ "IFNULL(sum(WITHDRAWAL_MONEY_),0) withdrawalMoney,IFNULL(sum(WITHDRAWAL_SHUISHOU_MONEY_),0) withdrawalShuishouMoney,IFNULL(sum(WITHDRAWAL_ZENREN_MONEY_),0) withdrawalZenrenMoney,IFNULL(sum(WITHDRAWAL_DAOZ_MONEY_),0) withdrawalDaozMoney,IFNULL(sum(WITHDRAWAL_SHOUXU_MONEY_),0) withdrawalShouxuMoney,"
						+ "IFNULL(sum(MEMBER_BANLAN_FAFANG_MONEY_),0) memberBanlanFafangMoney,IFNULL(sum(MEMBER_BANLAN_HUACHU_MONEY_),0) memberBanlanHuaChuMoney,IFNULL(sum(MEMBER_BANLAN_XIAOFEI_MONEY_),0) memberBanlanXiaofeiMoney,IFNULL(sum(MEMBER_BANLAN_SHENYU_MONEY_),0) memberBanlanShenYuMoney,IFNULL(sum(ZENREN_DATE_MONEY_),0) zenrenDateMoney,"
						+ "IFNULL(sum(ZENREN_XIAOHAO_MONEY_),0) zenrenXiaoHaoMoney,IFNULL(sum(ZENREN_SHENYU_MONEY_),0) zenrenshenyumoney,IFNULL(sum(MEMBER_NUMBER_),0) memberNumber,IFNULL(sum(MERCHANTS_NUMBER_),0) merchantsNumber,IFNULL(sum(GEDAI_MONEY_),0) geDaiMoney,IFNULL(sum(EREADAI_MONEY_),0) ereaDaiMoney,IFNULL(sum(CITYDAI_MONEY_),0) cityDaiMoney,IFNULL(sum(MERCHANTS_POOL_TOTAL_YISHOU_),0) merchantsPoolTotalYiShou,"
						+ "IFNULL(sum(PINGTAI_TOTAL_YISHOU_),0) pingtaiTotalYiShou,IFNULL(sum(SHIJI_SHOUKUAI_MONEY_),0) shijiShouKuaiMoney,IFNULL(sum(ZHICHU_SHIJIPAY_NONEY_),0) zhichuShiJipayMoney,IFNULL(sum(YISHOU_YUYINSHOU_MONEY_),0) yishouYUYInshouMoney,IFNULL(sum(YISHOU_SHIJIYISHOU_MONEY_),0) yishouShiJiYiShou,"
						+ "IFNULL(sum(BENERFIT_ZHIBAO_MONEY_),0) benerfitZhibaoMoney,IFNULL(sum(MALL_ZHIBAO_MONEY_),0) mallZhibaoMoney,IFNULL(sum(SHOUXIN_ZHIBAO_MONEY_),0) shouxinZhibaoMoney,IFNULL(sum(TOTAL_ZHIFUBAO_MONEY_),0) totalZhifubaoMoney,IFNULL(sum(TOTAL_ZHIFUBAO_SHOUXUPAY_MONEY_),0) totalZhifubaoShouxupayMoney,IFNULL(sum(TOTAL_ZHIFUBAO_GET_MONEY_),0) totalZhifubaoGetMoney"
						+ " from gjf_total_report");
		map = (Map) getCurrentSession().createSQLQuery(sql.toString())
				.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).uniqueResult();
		// List list= getCurrentSession().createSQLQuery(sql.toString()).list();
		// map.put("sumTotalReport", gjfTotalReportVo);
		return map;
	}

	/**
	 * 查询福利产出表
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Pager findBenefitDay(int pageNo, int pageSize, String addTime, String endTime, String ste) {
		StringBuffer sql = new StringBuffer();
		Map<String, Object> arrMap = new HashMap<String, Object>();
		sql.append("select * from gjf_benefit_day where  1=1");
		if (ObjValid.isValid(addTime)) {
			arrMap.put("addTime", addTime);
			sql.append(" and ACT_TIME_  >= :addTime ");
		}
		if (ObjValid.isValid(endTime)) {
			arrMap.put("endTime", endTime);
			sql.append(" and ACT_TIME_  <= :endTime ");
		}
		sql.append(" order by ACT_TIME_ DESC ");
		List list = null;
		if ("1".equals(ste)) {
			list = getCurrentSession().createSQLQuery(sql.toString()).addEntity(GjfBenefitDay.class)
					.setProperties(arrMap).list();
		} else {
			list = getCurrentSession().createSQLQuery(sql.toString()).addEntity(GjfBenefitDay.class)
					.setProperties(arrMap).setMaxResults(pageSize).setFirstResult((pageNo - 1) * pageSize).list();
		}
		String sqlCount = sql.toString().replace("select *", "select count(1)");
		Pager pager = new Pager(pageSize, pageNo,
				((BigInteger) getCurrentSession().createSQLQuery(sqlCount).setProperties(arrMap).uniqueResult())
						.intValue(),
				list);
		return pager;
	}

	/**
	 * 获取每天统计交易记录
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Pager findTotalReport(Integer pageNo, Integer pageSize, String beginTime, String endTime) {
		StringBuffer hql = new StringBuffer();
		Map<String, Object> arrMap = new HashMap<String, Object>();
		hql.append("select * from gjf_total_report where 1=1 ");
		if (ObjValid.isValid(beginTime)) {
			arrMap.put("addTime", beginTime);
			hql.append(" and ADD_TIME_  >= :addTime ");
		}
		if (ObjValid.isValid(endTime)) {
			arrMap.put("endTime", endTime);
			hql.append(" and ADD_TIME_  <= :endTime ");
		}
		hql.append("  order by ADD_TIME_ desc");
		List list = getCurrentSession().createSQLQuery(hql.toString()).addEntity(GjfTotalReport.class)
				.setProperties(arrMap).setMaxResults(pageSize).setFirstResult((pageNo - 1) * pageSize).list();
		String sqlCount = hql.toString().replace("select *", "select count(1)");
		Pager pager = new Pager(pageSize, pageNo,
				((BigInteger) getCurrentSession().createSQLQuery(sqlCount).setProperties(arrMap).uniqueResult())
						.intValue(),
				list);
		return pager;
	}

	/**
	 * 今日会员池入池金额
	 */
	@Override
	public BigDecimal findMemberPoolMoneyByTime() {
		String hql = "select COALESCE(sum(benefitMoney),0) from GjfBenefitHistory where benefitType=8 and addTime between :startTime and :endTime";
		return (BigDecimal) getCurrentSession().createQuery(hql.toString()).setParameter("startTime", getDayBegin())
				.setParameter("endTime", getDayEnd()).uniqueResult();
	}

	/**
	 * 今日商户池入池金额
	 */
	@Override
	public BigDecimal fingMerchantPoolMoneyByTime() {
		String hql = "select COALESCE(sum(benefitMoney),0) from GjfBenefitHistory where benefitType=9 and addTime between :startTime and :endTime";
		return (BigDecimal) getCurrentSession().createQuery(hql.toString()).setParameter("startTime", getDayBegin())
				.setParameter("endTime", getDayEnd()).uniqueResult();
	}

	/**
	 * 会员实时参与分红权数
	 */
	@Override
	public BigDecimal findMemberDiviNum() {
		BigDecimal memberTotalBenefitNum = (BigDecimal) getCurrentSession()
				.createSQLQuery(
						"SELECT FLOOR(IFNULL(SUM(NUM),0)) FROM (SELECT IF(DIVIDENDS_TOTAL_MONEY_/CUMULATIVE_MONEY_ >= 0.2,IF(DIVIDENDS_TOTAL_MONEY_/CUMULATIVE_MONEY_ >= 0.5,FLOOR(TRUNCATE(DIVIDENDS_NUM_/4,6)),FLOOR(TRUNCATE(DIVIDENDS_NUM_/2,6))),FLOOR(TRUNCATE(DIVIDENDS_NUM_,6))) AS NUM FROM gjf_member_info WHERE STATUS_='1' AND IS_DEL_='1' and IS_DIVI_='1' ) AS TOTAL_SUM")
				.uniqueResult();
		return memberTotalBenefitNum;
	}

	/**
	 * 商户实时参与分红权数
	 */
	@Override
	public BigDecimal findMerchantDiviNum() {
		String sql = "SELECT FLOOR(IFNULL(SUM(NUM),0)) FROM (SELECT IF(STORE_DIVIDENDS_TOTAL_MONEY_/STORE_BENEFIT_TOTAL_MONEY_ >= 0.2,IF(STORE_DIVIDENDS_TOTAL_MONEY_/STORE_BENEFIT_TOTAL_MONEY_ >= 0.5,FLOOR(TRUNCATE(STORE_DIVIDENDS_NUM_/4,6)),FLOOR(TRUNCATE(STORE_DIVIDENDS_NUM_/2,6))),FLOOR(TRUNCATE(STORE_DIVIDENDS_NUM_,6))) AS NUM FROM gjf_store_info WHERE STORE_STATUS_='1' AND IS_DEL_='1' and IS_DIVI_='1') AS TOTAL_SUM";
		return (BigDecimal) getCurrentSession().createSQLQuery(sql.toString()).uniqueResult();
	}

	/**
	 * 获取本周会有和商家的线下让利金额
	 */
	@Override
	public BigDecimal findCountCousumMoney(String type, Long id) {
		if ("0".equals(type)) {
			// 让利金额
			String sql = "select IFNULL(sum(BENEFIT_MONEY_),0) from gjf_member_trade_benefit where MEMBER_ID_=:memId and TRADE_STATUS_='1' and YEARWEEK(date_format(ADD_TIME_,'%Y-%m-%d'),1) = YEARWEEK(now(),1)";
			BigDecimal benMoney = (BigDecimal) getCurrentSession().createSQLQuery(sql).setParameter("memId", id)
					.uniqueResult();
			// 订单让利金额
			String oSql = "select IFNULL(sum(STORE_BENEFIT_AMOUNT_),0) from  gjf_order_info where (PAY_TYPE_='0' or PAY_TYPE_='1' or PAY_TYPE_='2' or PAY_TYPE_='3' OR PAY_TYPE_='4' OR PAY_TYPE_='9' ) and (ORDER_STATUS_=3) and MEMBER_ID_=:memId and YEARWEEK(date_format(ADD_TIME_,'%Y-%m-%d'),1) = YEARWEEK(now(),1)";
			BigDecimal orMoney = (BigDecimal) getCurrentSession().createSQLQuery(oSql).setParameter("memId", id)
					.uniqueResult();
			return benMoney.add(orMoney);
		} else {
			// 让利金额
			String sql = "select IFNULL(sum(BENEFIT_MONEY_),0) from gjf_member_trade_benefit where STORE_ID_=:storeId and TRADE_STATUS_='1' and YEARWEEK(date_format(ADD_TIME_,'%Y-%m-%d'),1) = YEARWEEK(now(),1)";
			BigDecimal benMoney = (BigDecimal) getCurrentSession().createSQLQuery(sql).setParameter("storeId", id)
					.uniqueResult();
			return benMoney;
		}
	}

	/**
	 * 获取会员或商家本周分红金额
	 * 
	 * @param type
	 * @param id
	 * @return
	 */
	public BigDecimal findDiviMoneyByTime(String type, Long id) {
		if ("0".equals(type)) {
			String sql = "select IFNULL(sum(TRADE_MONEY_),0) from gjf_member_trade_divi_history where TRADE_TYPE_='2' and TRADE_STATUS_='1' and MEMBER_ID_=:memId and YEARWEEK(date_format(ADD_TIME_,'%Y-%m-%d'),1) = YEARWEEK(now(),1)";
			return (BigDecimal) getCurrentSession().createSQLQuery(sql.toString()).setParameter("memId", id)
					.uniqueResult();
		} else {
			String sql = "select IFNULL(sum(TRADE_MONEY_),0) from gjf_member_trade_divi_history where TRADE_TYPE_='3' and TRADE_STATUS_='1' and MEMBER_ID_=:memId and YEARWEEK(date_format(ADD_TIME_,'%Y-%m-%d'),1) = YEARWEEK(now(),1)";
			return (BigDecimal) getCurrentSession().createSQLQuery(sql.toString()).setParameter("memId", id)
					.uniqueResult();
		}
	}

	/**
	 * 获取用户本周分红信息
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List findAllDiviInfoByTime() {
		String sql = "select IFNULL(sum(TRADE_MONEY_),0) ,MEMBER_ID_  from gjf_member_trade_divi_history where TRADE_TYPE_='2' and TRADE_STATUS_='1'  and YEARWEEK(date_format(ADD_TIME_,'%Y-%m-%d'),1) = YEARWEEK(now(),1) group by MEMBER_ID_";
		List list = getCurrentSession().createSQLQuery(sql.toString()).list();
		String[] param = { "tradeMoney", "memberId" };
		list = BeanUtil.changeObjectArrayToMaps(list, param);
		return list;
	}

	/**
	 * 更新是否为活跃会员
	 */
	@Override
	public ResultVo updateIsActivity() {
		String procdureBenefitReport = "{Call gjf_calculate_update_member_isactivity()}";
		getCurrentSession().createSQLQuery(procdureBenefitReport).executeUpdate();
		System.out.println("执行成功:"+new Date());
		return new ResultVo(200, "统计成功", null);
	}

	/**
	 * 查询用户升级消费金额
	 */
	@Override
	public BigDecimal findMemberBenefitUpgradeMoney(Long memberId) {

		// 让利金额
		String sql = "select IFNULL(sum(CONSUMPTION_MONEY_),0) from gjf_member_trade_benefit where MEMBER_ID_=:memId and TRADE_STATUS_='1' and CHANGE_ACIVITY_STATUS_='1' and YEARWEEK(date_format(ADD_TIME_,'%Y-%m-%d'),1) = YEARWEEK(now(),1)";
		BigDecimal benMoney = (BigDecimal) getCurrentSession().createSQLQuery(sql).setParameter("memId", memberId)
				.uniqueResult();
		
		return benMoney;

	}

	

}
