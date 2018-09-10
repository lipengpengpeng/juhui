package cc.messcat.gjfeng.dao.trade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;
import org.hibernate.transform.Transformers;
import org.hibernate.type.BigDecimalType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.springframework.stereotype.Repository;

import cc.messcat.gjfeng.common.bean.Pager;
import cc.messcat.gjfeng.common.util.BeanUtil;
import cc.messcat.gjfeng.common.util.ObjValid;
import cc.messcat.gjfeng.common.util.StringUtil;
import cc.messcat.gjfeng.common.vo.app.MemberTradeDiviHistoryVo;
import cc.messcat.gjfeng.common.vo.app.MemberTradeVo;
import cc.messcat.gjfeng.common.vo.app.ResultVo;
import cc.messcat.gjfeng.common.vo.app.StoreInfoVo;
import cc.messcat.gjfeng.dao.BaseHibernateDaoImpl;
import cc.messcat.gjfeng.entity.GjfMemberTradeDiviHistory;
import cc.messcat.gjfeng.entity.GjfMemberTreasureTrade;
import cc.messcat.gjfeng.entity.GjfOrderInfo;

@Repository("gjfTradeDao")
public class GjfTradeDaoImpl extends BaseHibernateDaoImpl<Serializable> implements GjfTradeDao {

	@SuppressWarnings({ "rawtypes"})
	@Override
	public Pager getGjfTrade(Integer pageNo, Integer pageSize, Long memberId, String holder, String bankCard, String mobile,
		String addTime, String endTime, String tradeStatus, String ste, Long id) throws Exception{
		StringBuffer sql = new StringBuffer();
		Map<String, Object> arrMap = new HashMap<String, Object>();
		sql.append("SELECT t,(SELECT MAX(b.addTime) FROM GjfMemberTradeBenefit b WHERE b.memberId = t.memberId AND b.tradeStatus='1') AS lastDate "
				+ " FROM GjfMemberTrade t WHERE t.tradeType='1' ");
		if (ObjValid.isValid(memberId)) {
			arrMap.put("memberId", memberId);
			sql.append(" AND t.memberId.is = :memberId ");
		}
		if (ObjValid.isValid(id)) {
			arrMap.put("id", id);
			sql.append(" AND t.id = :id ");
		}
		if (StringUtil.isNotBlank(holder)) {
			arrMap.put("holder", "%"+holder+"%");
			sql.append(" and t.bankId.holder like :holder ");
		}
		if (StringUtil.isNotBlank(bankCard)) {
			arrMap.put("bankCard", "%"+bankCard+"%");
			sql.append(" and t.bankId.bankCard like :bankCard ");
		}
		if (StringUtil.isNotBlank(mobile)) {
			arrMap.put("mobile", "%"+mobile+"%");
			sql.append(" and t.memberId.mobile like :mobile ");
		}
		DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd");
		if (StringUtil.isNotBlank(addTime)) {
			arrMap.put("addTime", fmt.parse(addTime));
			sql.append(" and t.addTime >= :addTime ");
		}
		if (StringUtil.isNotBlank(endTime)) {
			arrMap.put("endTime", fmt.parse(endTime));
			sql.append(" and t.addTime <= :endTime ");
		}
		if (StringUtil.isNotBlank(tradeStatus)) {
			arrMap.put("tradeStatus", tradeStatus);
			sql.append(" and t.tradeStatus = :tradeStatus ");
		}
		sql.append(" order by t.addTime desc");
		List list = new ArrayList<>();
		if ("1".equals(ste)) {
			list = getCurrentSession().createQuery(sql.toString()).setProperties(arrMap).list();
		} else {
			list = getCurrentSession().createQuery(sql.toString()).setProperties(arrMap).setMaxResults(pageSize)
				.setFirstResult((pageNo - 1) * pageSize).list();
		}
		String sql0 = sql.toString().replace("SELECT t,(SELECT MAX(b.addTime) FROM GjfMemberTradeBenefit b WHERE b.memberId = t.memberId AND b.tradeStatus='1') AS lastDate", "select count(1)");
		Long count = (Long)getCurrentSession().createQuery(sql0).setProperties(arrMap).uniqueResult();
		return new Pager(pageSize, pageNo, count.intValue(), list);
	}

	/*
	 * 查询我的提现历史记录
	 * 
	 * @see cc.messcat.gjfeng.dao.trade.GjfTradeDao#findDrawCashHistory(int,
	 * int, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<MemberTradeVo> findDrawCashHistory(int pageNo, int pageSize, String account) {
		String sql = "SELECT t.TRADE_NO_ tradeNo,t.APPLY_MONEY_ applyMoney,t.TRADE_MONEY_ tradeMoney,UNIX_TIMESTAMP(t.ADD_TIME_)*1000 addTime,t.TRADE_STATUS_ tradeStatus FROM gjf_member_trade t "
			+ "LEFT JOIN gjf_member_info m ON t.MEMBER_ID_=m.ID_ WHERE m.MOBILE_=? AND t.TRADE_TYPE_='1' ORDER BY t.ADD_TIME_ DESC";
		return getCurrentSession().createSQLQuery(sql).addScalar("tradeNo", StringType.INSTANCE)
			.addScalar("applyMoney", BigDecimalType.INSTANCE).addScalar("tradeMoney", BigDecimalType.INSTANCE)
			.addScalar("addTime", LongType.INSTANCE).addScalar("tradeStatus", StringType.INSTANCE)
			.setResultTransformer(Transformers.aliasToBean(MemberTradeVo.class)).setParameter(0, account)
			.setFirstResult((pageNo - 1) * pageSize).setMaxResults(pageSize).list();
	}

	/*
	 * 查询提现流水详情
	 * 
	 * @see
	 * cc.messcat.gjfeng.dao.trade.GjfTradeDao#findDrawCashHistoryDetail(java.
	 * lang.Long, java.lang.String)
	 */
	public MemberTradeVo findDrawCashHistoryDetail(Long id,String tradeNo, String account,String clientType) {
		String sql = "SELECT t.TRADE_NO_ tradeNo,m.NAME_ memberName,t.APPLY_MONEY_ applyMoney,t.TRADE_MONEY_ tradeMoney,t.TAX_MONEY_ taxMoney,t.INSURANCE_MONEY_ insuranceMoney,b.BANK_CARD_ bankCard,b.HOLDER_ holder,"
			+ "b.BANK_SUB_ bankSub,i.BANK_NAME_ bankName,UNIX_TIMESTAMP(t.ADD_TIME_)*1000 addTime,UNIX_TIMESTAMP(t.DEAL_TIME_)*1000 dealTime,UNIX_TIMESTAMP(t.TRADE_TIME_)*1000 tradeTime,t.TRADE_STATUS_ tradeStatus,t.CHECK_USER_ checkUser FROM gjf_member_trade t LEFT JOIN gjf_member_info m "
			+ "ON t.MEMBER_ID_=m.ID_ LEFT JOIN gjf_member_bank b ON t.BANK_ID_=b.ID_ LEFT JOIN gjf_bank_info i ON i.ID_=b.BANK_ID_";
		Map<String, Object> arrMap=new HashMap<String, Object>();
		if (clientType.equals("1")) {
			arrMap.put("tradeNo", tradeNo);
			arrMap.put("mobile", account);
			sql+=" WHERE t.TRADE_NO_=:tradeNo AND m.MOBILE_=:mobile";
		}else if (clientType.equals("0")){
			arrMap.put("tradeId", id);
			arrMap.put("token", account);
			sql+=" WHERE t.ID_=:tradeId AND t.TOKEN_=:token";
		}
		return (MemberTradeVo) getCurrentSession().createSQLQuery(sql).addScalar("tradeNo", StringType.INSTANCE)
			.addScalar("memberName", StringType.INSTANCE).addScalar("applyMoney", BigDecimalType.INSTANCE)
			.addScalar("tradeMoney", BigDecimalType.INSTANCE).addScalar("taxMoney", BigDecimalType.INSTANCE)
			.addScalar("insuranceMoney", BigDecimalType.INSTANCE).addScalar("bankCard", StringType.INSTANCE)
			.addScalar("holder", StringType.INSTANCE).addScalar("bankSub", StringType.INSTANCE)
			.addScalar("bankName", StringType.INSTANCE).addScalar("addTime", LongType.INSTANCE)
			.addScalar("dealTime", LongType.INSTANCE).addScalar("tradeTime", LongType.INSTANCE).addScalar("checkUser", StringType.INSTANCE)
			.addScalar("tradeStatus", StringType.INSTANCE).setResultTransformer(Transformers.aliasToBean(MemberTradeVo.class))
			.setProperties(arrMap).uniqueResult();
	}

	/*
	 * 根据用户身份证获取当天是否已经有提现了
	 * 
	 * @see cc.messcat.gjfeng.dao.trade.GjfTradeDao#findDrawByIdCard(java.lang.
	 * String)
	 */
	@Override
	public int findDrawByIdCard(String idCard) {
		String sql = "SELECT COUNT(*) FROM gjf_member_trade t LEFT JOIN gjf_member_info m ON m.ID_=t.MEMBER_ID_ WHERE m.ID_CARD_=? "
			+ "AND t.TRADE_TYPE_='1' and t.TRADE_STATUS_='1' AND DATE_FORMAT(t.ADD_TIME_,'%Y%m%d')=DATE_FORMAT(DATE_SUB(CURDATE(), INTERVAL 0 DAY),'%Y%m%d')";
		return Integer.parseInt(String.valueOf(getCurrentSession().createSQLQuery(sql).setParameter(0, idCard).uniqueResult()));
	}
	
	/* 
	 *  查询是否有处于提现中的记录
	 * @see cc.messcat.gjfeng.dao.trade.GjfTradeDao#findDrawByStatus(java.lang.Long)
	 */
	public int findDrawByStatus(Long memberId){
		String hql = "select count(*) from GjfMemberTrade  where tradeType='1' and tradeStatus='0' and memberId.id=?";
		return Integer.parseInt(String.valueOf(getCurrentSession().createQuery(hql).setParameter(0, memberId).uniqueResult()));
	}

	/*
	 * 查询我的分红历史记录
	 * 
	 * @see cc.messcat.gjfeng.dao.trade.GjfTradeDao#findTradeDiviHistory(int,
	 * int, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<MemberTradeDiviHistoryVo> findTradeDiviHistory(int pageNo, int pageSize, String account, String tradeStatus) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		String sql = "SELECT d.TRADE_NO_ tradeNo,d.TRADE_MONEY_ tradeMoney,UNIX_TIMESTAMP(d.ADD_TIME_)*1000 addTime,d.TRADE_STATUS_ tradeStatus,TRADE_TYPE_ tradeType,"
				+ "d.TRADE_SECOND_MONEY_ as tradeSecondMoney,d.TRADE_THREE_MONEY_ as tradeThreeMoney "
			+ "FROM gjf_member_trade_divi_history d LEFT JOIN gjf_member_info m ON m.ID_=d.MEMBER_ID_ WHERE m.MOBILE_=:mobile "
			+ "AND (TRADE_TYPE_='0' OR TRADE_TYPE_='1' OR TRADE_TYPE_='2' OR TRADE_TYPE_='3')";
		if (StringUtil.isNotBlank(tradeStatus)) {
			sql += " AND TRADE_STATUS_=:tradeStatus";
			paraMap.put("tradeStatus", tradeStatus);
		}
		sql += " ORDER BY d.ADD_TIME_ DESC";
		paraMap.put("mobile", account);
		return getCurrentSession().createSQLQuery(sql).addScalar("tradeNo", StringType.INSTANCE)
			.addScalar("tradeMoney", BigDecimalType.INSTANCE).addScalar("addTime", LongType.INSTANCE)
			.addScalar("tradeStatus", StringType.INSTANCE).addScalar("tradeType", StringType.INSTANCE)
			.addScalar("tradeSecondMoney", BigDecimalType.INSTANCE).addScalar("tradeThreeMoney", BigDecimalType.INSTANCE)
			.setResultTransformer(Transformers.aliasToBean(MemberTradeDiviHistoryVo.class)).setProperties(paraMap)
			.setFirstResult((pageNo - 1)*pageSize).setMaxResults(pageSize).list();
	}

	/*
	 * 根据时间段查询商家让利记录
	 * 
	 * @see cc.messcat.gjfeng.dao.trade.GjfTradeDao#findBenefitByTime(int, int,
	 * java.lang.Long, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Pager findBenefitByTime(int pageNo, int pageSize, Long id, String startTime, String endTime) {
		StringBuffer sql = new StringBuffer();
		Map<String, Object> arrMap = new HashMap<String, Object>();
		sql.append(
			"select t.TRADE_NO_,m.NAME_,t.MEMBER_ID_,t.TRADE_MONEY_,t.BENEFIT_MONEY_,t.ADD_TIME_,t.PAY_TYPE_,t.TRADE_STATUS_ from gjf_member_trade_benefit t,gjf_member_info m where t.MEMBER_ID_ = m.ID_ and  t.STORE_ID_ =")
			.append(id);
		if (StringUtil.isNotBlank(startTime)) {
			arrMap.put("startTime", startTime);
			sql.append(" and t.ADD_TIME_ >= :startTime ");
		}
		if (StringUtil.isNotBlank(endTime)) {
			arrMap.put("endTime", endTime);
			sql.append(" and t.ADD_TIME_ <=:endTime ");
		}
		sql.append(" order by t.ADD_TIME_ desc");
		String sql0 = sql.toString().replace(
			"select t.TRADE_NO_,m.NAME_,t.MEMBER_ID_,t.TRADE_MONEY_,t.BENEFIT_MONEY_,t.ADD_TIME_,t.PAY_TYPE_,t.TRADE_STATUS_",
			"select count(1)");
		BigInteger count = (BigInteger) getCurrentSession().createSQLQuery(sql0).setProperties(arrMap).uniqueResult();
		List list = getCurrentSession().createSQLQuery(sql.toString()).setProperties(arrMap).setMaxResults(pageSize)
			.setFirstResult((pageNo - 1) * pageSize).list();
		String[] param = { "tradeNo", "memberName", "memberId", "tradeMoney", "benefitMoney", "addTime", "payType", "tradeStatus" };
		list = BeanUtil.changeObjectArrayToMaps(list, param);
		return new Pager(pageSize, pageNo, count.intValue(), list);
	}

	/*
	 * 查询当前用户昨天的收益
	 * 
	 * @see
	 * cc.messcat.gjfeng.dao.trade.GjfTradeDao#findBenefitYesterdayByMember(java
	 * .lang.Long)
	 */
	@Override
	public BigDecimal findBenefitYesterdayByMember(Long memberId, String tradeType) {
		String sql = "SELECT IFNULL(SUM(h.TRADE_MONEY_),0)+IFNULL(SUM(h.TRADE_SECOND_MONEY_),0)+IFNULL(SUM(h.TRADE_THREE_MONEY_),0) FROM gjf_member_trade_divi_history h LEFT JOIN gjf_member_info m ON h.MEMBER_ID_=m.ID_ WHERE m.ID_=? AND h.TRADE_TYPE_=? AND h.TRADE_STATUS_='1' AND TO_DAYS(NOW())-TO_DAYS(h.TRADE_TIME_)=0";
		return new BigDecimal(String
			.valueOf(getCurrentSession().createSQLQuery(sql).setParameter(0, memberId).setParameter(1, tradeType).uniqueResult()));
	}

	/*
	 * 查询用户代理信息
	 * 
	 * @see
	 * cc.messcat.gjfeng.dao.trade.GjfTradeDao#findAgentInfo(java.lang.Long,
	 * java.lang.String)
	 */
	@Override
	public Double findAgentInfo(Long memberId, Long addressId, String agentType, String findType) {
		// 如果最后一次时间为空，则查找的是全部的让利
		if (agentType.equals("4")) {
			String sql = "SELECT IFNULL(SUM(r.BENEFIT_MONEY_),0) FROM gjf_member_trade_benefit r "
				+ "LEFT JOIN gjf_store_info s ON s.ID_=r.STORE_ID_ WHERE s.STORE_PRO_='0' AND s.CITY_ID_=?";
			if (findType.equals("0")) {
				sql += " AND TO_DAYS(NOW())-TO_DAYS(r.ADD_TIME_)=0";
			}
			Double totalMoney = Double
				.parseDouble(String.valueOf(getCurrentSession().createSQLQuery(sql).setParameter(0, addressId).uniqueResult()));
			return totalMoney;
		} else if (agentType.equals("5")) {
			String sql = "SELECT IFNULL(SUM(r.BENEFIT_MONEY_),0) FROM gjf_member_trade_benefit r "
				+ "LEFT JOIN gjf_store_info s ON s.ID_=r.STORE_ID_ WHERE s.STORE_PRO_='0' AND s.AREA_ID_=?";
			if (findType.equals("0")) {
				sql += " AND TO_DAYS(NOW())-TO_DAYS(r.ADD_TIME_)=0";
			}
			Double totalMoney = Double
				.parseDouble(String.valueOf(getCurrentSession().createSQLQuery(sql).setParameter(0, addressId).uniqueResult()));
			return totalMoney;
		} else if (agentType.equals("6")) {
			// 下级的让利额
			String sql = "SELECT TRUNCATE(IFNULL(SUM(BENEFIT_MONEY_),0),2) FROM gjf_member_trade_indi WHERE MEMBER_ID_=?";
			if (findType.equals("0")) {
				sql += " AND TO_DAYS(NOW())-TO_DAYS(ADD_TIME_)=0";
			}
			Double totalMoney = Double
				.parseDouble(String.valueOf(getCurrentSession().createSQLQuery(sql).setParameter(0, memberId).uniqueResult()));
			return totalMoney;
		}
		return 0.00;
	}

	/*
	 * 代理分红记录
	 * 
	 * @see cc.messcat.gjfeng.dao.trade.GjfTradeDao#findAgentHistory(java.lang.
	 * Integer, java.lang.Integer, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ResultVo findAgentHistory(Integer pageNo, Integer pageSize, String account, String agentType) {
		String sql = "select h.TRADE_NO_ tradeNo,h.TRADE_MONEY_ tradeMoney,UNIX_TIMESTAMP(h.ADD_TIME_)*1000 addTime,UNIX_TIMESTAMP(h.TRADE_TIME_)*1000 tradeTime,h.TRADE_TYPE_ tradeType,"
			+ "h.TRADE_STATUS_ tradeStatus from gjf_member_trade_divi_history h left join gjf_member_info m on h.MEMBER_ID_=m.ID_ where h.TRADE_TYPE_=? and m.MOBILE_=? and h.TRADE_STATUS_='1' order by h.ADD_TIME_ desc";

		List<MemberTradeDiviHistoryVo> diviHistoryVos = getCurrentSession().createSQLQuery(sql)
			.addScalar("tradeNo", StringType.INSTANCE).addScalar("tradeMoney", BigDecimalType.INSTANCE)
			.addScalar("addTime", LongType.INSTANCE).addScalar("tradeTime", LongType.INSTANCE)
			.addScalar("tradeType", StringType.INSTANCE).addScalar("tradeStatus", StringType.INSTANCE)
			.setResultTransformer(Transformers.aliasToBean(MemberTradeDiviHistoryVo.class)).setParameter(0, agentType)
			.setParameter(1, account).setFirstResult((pageNo - 1) * pageSize).setMaxResults(pageSize).list();
		String sqlCount = "select count(*) from gjf_member_trade_divi_history h left join gjf_member_info m on h.MEMBER_ID_=m.ID_ where h.TRADE_TYPE_=? and m.MOBILE_=?";
		return new ResultVo(200, "查询成功",
			new Pager(pageSize, pageNo, Integer.parseInt(String.valueOf(
				getCurrentSession().createSQLQuery(sqlCount).setParameter(0, agentType).setParameter(1, account).uniqueResult())),
				diviHistoryVos));
	}

	/*
	 * 查询我的下级代理
	 * 
	 * @see
	 * cc.messcat.gjfeng.dao.trade.GjfTradeDao#findNextAgent(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public Object findNextAgent(String account, String agentType, Integer pageNo, Integer pageSize, String reqType) {
		Object o = null;
		if (agentType.equals("4")) {
			String sql = "SELECT s.STORE_NAME_ storeName,s.STORE_NUM_ storeNum,s.SELLER_MOBILE_ sellerMobile,s.STORE_SALE_TOTAL_MONEY_ storeSaleTotalMoney,"
				+ "(SELECT IFNULL(SUM(TRADE_MONEY_),0) FROM gjf_member_trade_benefit WHERE STORE_ID_=s.ID_ AND DATE_FORMAT(ADD_TIME_,'%Y-%m')=DATE_FORMAT(NOW(),'%Y-%m') and TRADE_STATUS_=1 ) AS storeMonthSaleTotalMoney "
				+ "FROM gjf_store_info s LEFT JOIN gjf_member_info m ON m.CITY_ID_=s.CITY_ID_ WHERE s.STORE_STATUS_='1' AND s.IS_DEL_='1' AND m.MOBILE_=?";
			if (reqType.equals("1")) {
				String sqlCount = "SELECT COUNT(s.ID_) FROM gjf_store_info s LEFT JOIN gjf_member_info m ON m.CITY_ID_=s.CITY_ID_ WHERE s.STORE_STATUS_='1' AND s.IS_DEL_='1' AND m.MOBILE_=?";
				o = getCurrentSession().createSQLQuery(sqlCount).setParameter(0, account).uniqueResult();
			} else {
				o = getCurrentSession().createSQLQuery(sql).addScalar("storeName", StringType.INSTANCE)
					.addScalar("storeNum", StringType.INSTANCE).addScalar("sellerMobile", StringType.INSTANCE)
					.addScalar("storeSaleTotalMoney", BigDecimalType.INSTANCE)
					.addScalar("storeMonthSaleTotalMoney", BigDecimalType.INSTANCE)
					.setResultTransformer(Transformers.aliasToBean(StoreInfoVo.class)).setParameter(0, account)
					.setFirstResult((pageNo - 1) * pageSize).setMaxResults(pageSize).list();
			}
			return o;
		} else if (agentType.equals("5")) {
			String sql = "SELECT s.STORE_NAME_ storeName,s.STORE_NUM_ storeNum,s.SELLER_MOBILE_ sellerMobile,s.STORE_SALE_TOTAL_MONEY_ storeSaleTotalMoney,"
				+ "(SELECT IFNULL(SUM(TRADE_MONEY_),0) FROM gjf_member_trade_benefit WHERE STORE_ID_=s.ID_ AND DATE_FORMAT(ADD_TIME_,'%Y-%m')=DATE_FORMAT(NOW(),'%Y-%m') ) AS storeMonthSaleTotalMoney "
				+ "FROM gjf_store_info s LEFT JOIN gjf_member_info m ON m.AREA_ID_=s.AREA_ID_ WHERE s.STORE_STATUS_='1' AND s.IS_DEL_='1' AND m.MOBILE_=?";

			if (reqType.equals("1")) {
				String sqlCount = "SELECT COUNT(s.ID_) FROM gjf_store_info s LEFT JOIN gjf_member_info m ON m.AREA_ID_=s.AREA_ID_ WHERE s.STORE_STATUS_='1' AND s.IS_DEL_='1' AND m.MOBILE_=?";
				o = getCurrentSession().createSQLQuery(sqlCount).setParameter(0, account).uniqueResult();
			} else {
				o = getCurrentSession().createSQLQuery(sql).addScalar("storeName", StringType.INSTANCE)
					.addScalar("storeNum", StringType.INSTANCE).addScalar("sellerMobile", StringType.INSTANCE)
					.addScalar("storeSaleTotalMoney", BigDecimalType.INSTANCE)
					.addScalar("storeMonthSaleTotalMoney", BigDecimalType.INSTANCE)
					.setResultTransformer(Transformers.aliasToBean(StoreInfoVo.class)).setParameter(0, account)
					.setFirstResult((pageNo - 1) * pageSize).setMaxResults(pageSize).list();
			}
			return o;
		} else if (agentType.equals("6")) {
			String sql = "SELECT s.STORE_NAME_ storeName,s.SELLER_MOBILE_ sellerMobile,s.STORE_SALE_TOTAL_MONEY_ storeSaleTotalMoney,(SELECT IFNULL(SUM(TRADE_MONEY_),0) FROM gjf_member_trade_benefit WHERE STORE_ID_=s.ID_ AND DATE_FORMAT(ADD_TIME_,'%Y-%m')=DATE_FORMAT(NOW(),'%Y-%m') ) AS storeMonthSaleTotalMoney  "
				+ "FROM gjf_store_info s LEFT JOIN gjf_member_info m ON s.MEMBER_ID_=m.ID_ WHERE m.SUPER_ID_=(SELECT ID_ FROM gjf_member_info WHERE MOBILE_=?) AND m.TYPE_='1' AND m.STATUS_='1' AND m.IS_DEL_='1'";
			if (reqType.equals("1")) {
				String sqlCount = "SELECT COUNT(s.ID_) FROM gjf_store_info s LEFT JOIN gjf_member_info m ON s.MEMBER_ID_=m.ID_ WHERE m.SUPER_ID_=(SELECT ID_ FROM gjf_member_info WHERE MOBILE_=?) AND m.TYPE_='1' AND m.STATUS_='1' AND m.IS_DEL_='1'";
				o = getCurrentSession().createSQLQuery(sqlCount).setParameter(0, account).uniqueResult();
			} else {
				o = getCurrentSession().createSQLQuery(sql).addScalar("storeName", StringType.INSTANCE)
					.addScalar("sellerMobile", StringType.INSTANCE).addScalar("storeSaleTotalMoney", BigDecimalType.INSTANCE)
					.addScalar("storeMonthSaleTotalMoney", BigDecimalType.INSTANCE)
					.setResultTransformer(Transformers.aliasToBean(StoreInfoVo.class)).setParameter(0, account)
					.setFirstResult((pageNo - 1) * pageSize).setMaxResults(pageSize).list();
			}
			return o;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GjfOrderInfo> findMemberInterests(String account, String type) {
		List<GjfOrderInfo> list = new ArrayList<>();
		if (Integer.parseInt(type) == 0) {
			list = getCurrentSession().createQuery("from GjfOrderInfo where memberId.mobile=:account and orderStatus=3 and isDel=1")
				.setParameter("account", account).list();
		} else {
			list = getCurrentSession()
				.createQuery("from GjfOrderInfo where storeId.memberId.mobile=:account and orderStatus=3 and isDel=1")
				.setParameter("account", account).list();
		}

		return list;
	}

	@SuppressWarnings({ "rawtypes" })
	@Override
	public List findTradeAmountInBack(String holder,String bankCard,
			String mobile,String addTime,String endTime,String tradeStatus,Long id) throws Exception{
		StringBuffer sql = new StringBuffer();
		Map<String, Object> arrMap = new HashMap<String, Object>();
		sql.append(
			"select COALESCE(sum(t.applyMoney),0),COALESCE(SUM(t.tradeMoney),0),COALESCE(SUM(t.taxMoney),0),COALESCE(SUM(t.insuranceMoney),0) from GjfMemberTrade t where t.tradeType = '1' ");
		if (ObjValid.isValid(id)) {
			arrMap.put("id", id);
			sql.append(" AND t.id = :id ");
		}
		if (StringUtil.isNotBlank(holder)) {
			arrMap.put("holder", "%"+holder+"%");
			sql.append(" and t.bankId.holder like :holder ");
		}
		if (StringUtil.isNotBlank(bankCard)) {
			arrMap.put("bankCard", "%"+bankCard+"%");
			sql.append(" and t.bankId.bankCard like :bankCard ");
		}
		if (StringUtil.isNotBlank(mobile)) {
			arrMap.put("mobile", "%"+mobile+"%");
			sql.append(" and t.memberId.mobile like :mobile ");
		}
		DateFormat fmt =new SimpleDateFormat("yyyy-MM-dd");
		if (StringUtil.isNotBlank(addTime)) {
			arrMap.put("addTime", fmt.parse(addTime));
			sql.append(" and t.addTime >= :addTime ");
		}
		if (StringUtil.isNotBlank(endTime)) {
			arrMap.put("endTime", fmt.parse(endTime));
			sql.append(" and t.addTime <= :endTime ");
		}
		if (StringUtil.isNotBlank(tradeStatus)) {
			arrMap.put("tradeStatus", tradeStatus);
			sql.append(" and t.tradeStatus = :tradeStatus ");
		}
		List list = getCurrentSession().createQuery(sql.toString()).setProperties(arrMap).list();
		return list;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List findBenefitAmountInBack(String name, String storeName, String addTime,String endTime, String directMemberName,
		String directMerchantsName,String tradeStatus,String payType) {
		StringBuffer sql = new StringBuffer();
		Map<String, Object> arrMap = new HashMap<String, Object>();
		sql.append(
			"select IFNULL(sum(b.TRADE_MONEY_),0),IFNULL(sum(b.BENEFIT_MONEY_),0),IFNULL(sum(b.MEMBER_DIVIDENDS_NUM_),0),IFNULL(sum(b.MERCHANTS_DIVIDENDS_NUM_),0) from gjf_member_trade_benefit b left join gjf_member_info c on b.DIRECT_MEMBER_ = c.ID_ left join gjf_member_info d on b.DIRECT_MERCHANTS_ = d.ID_ left join gjf_member_info e on b.MEMBER_ID_ = e.ID_ left join gjf_store_info s on b.STORE_ID_ = s.ID_ where 1 = 1 ");
		if (StringUtil.isNotBlank(name)) {
			arrMap.put("name", "%"+name+"%");
			sql.append(" and e.NAME_ like :name ");
		}
		if (StringUtil.isNotBlank(storeName)) {
			arrMap.put("storeName", "%"+storeName+"%");
			sql.append(" and s.STORE_NAME_ like :storeName ");
		}
		if (StringUtil.isNotBlank(addTime)) {
			arrMap.put("addTime", addTime);
			sql.append(" and b.ADD_TIME_ >= :addTime ");
		}
		if (StringUtil.isNotBlank(endTime)) {
			arrMap.put("endTime", endTime);
			sql.append(" and b.ADD_TIME_ <= :endTime ");
		}
		if (StringUtil.isNotBlank(directMemberName)) {
			arrMap.put("directMemberName", "%"+directMemberName+"%");
			sql.append(" and c.NAME_ like :directMemberName ");
		}
		if (StringUtil.isNotBlank(directMerchantsName)) {
			arrMap.put("directMerchantsName", "%"+directMerchantsName+"%");
			sql.append(" and d.NAME_ like :directMerchantsName ");
		}
		if (StringUtil.isNotBlank(payType)) {
			arrMap.put("payType", payType);
			sql.append(" and  b.PAY_TYPE_ = :payType ");
		}
		if (StringUtil.isNotBlank(tradeStatus)) {
			arrMap.put("tradeStatus", tradeStatus);
			sql.append(" and  b.TRADE_STATUS_ = :tradeStatus ");
		}
		List list = getCurrentSession().createSQLQuery(sql.toString()).setProperties(arrMap).list();
		String[] param = { "tradeMoneys", "benefitMoneys", "memberDividendsNums", "merchantsDividendsNums" };
		list = BeanUtil.changeObjectArrayToMaps(list, param);
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GjfMemberTradeDiviHistory> findMemberTradeDiviHis(String account, String tradeType) {
		return getCurrentSession()
			.createQuery("from GjfMemberTradeDiviHistory where tradeType in(0,1) and memberId.mobile=:acount order by addTime desc")
			.setParameter("acount", account).list();
	}

	/**
	 * 查询支付明细
	 * 
	 * @throws ParseException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Pager findTradeLogByPage(int pageNo, int pageSize, String ste, String name, String storeName, String addTime,String endTime,
		String tradeNo, String payTradeNo, String tradeType, String tradeStatus) throws ParseException {
		StringBuffer sql = new StringBuffer();
		Map<String, Object> arrMap = new HashMap<String, Object>();
		sql.append(
			"select memberId.name,storeId.storeName,tradeNo,payTradeNo,tradeMoney,addTime,tradeTime,tradeType,tradeStatus,tradeTrmo from GjfMemberTradeLog where 1=1 ");
		if (StringUtil.isNotBlank(name)) {
			arrMap.put("name", "%"+name+"%");
			sql.append(" and memberId.name like :name ");
		}
		if (StringUtil.isNotBlank(storeName)) {
			arrMap.put("storeName", "%"+storeName+"%");
			sql.append(" and storeId.storeName like :storeName ");
		}
		if (StringUtil.isNotBlank(tradeNo)) {
			arrMap.put("tradeNo", "%"+tradeNo+"%");
			sql.append(" and tradeNo like :tradeNo ");
		}
		if (StringUtil.isNotBlank(addTime)) {
			arrMap.put("addTime", addTime);
			sql.append(" and date_format(addTime,'%Y-%m-%d') >= :addTime ");
		}
		if (StringUtil.isNotBlank(endTime)) {
			arrMap.put("endTime", endTime);
			sql.append(" and date_format(addTime,'%Y-%m-%d') < :endTime");
		}
		if (StringUtil.isNotBlank(payTradeNo)) {
			arrMap.put("payTradeNo", "%"+payTradeNo+"%");
			sql.append(" payTradeNo like :payTradeNo ");
		}
		if (StringUtil.isNotBlank(tradeType)) {
			arrMap.put("tradeType", tradeType);
			sql.append(" and tradeType = :tradeType ");
		}
		if (StringUtil.isNotBlank(tradeStatus)) {
			arrMap.put("tradeStatus", tradeStatus);
			sql.append(" and tradeStatus = :tradeStatus ");
		}
		sql.append(" order by addTime desc,tradeTime desc ");
		List list = new ArrayList<>();
		if ("1".equals(ste)) {
			list = getCurrentSession().createQuery(sql.toString()).setProperties(arrMap).list();
		} else {
			list = getCurrentSession().createQuery(sql.toString()).setProperties(arrMap).setMaxResults(pageSize).setFirstResult((pageNo - 1) * pageSize)
				.list();
		}
		String[] param = { "name", "storeName", "tradeNo", "payTradeNo", "tradeMoney", "addTime", "tradeTime", "tradeType",
			"tradeStatus", "tradeTrmo" };
		list = BeanUtil.changeObjectArrayToMaps(list, param);
		String sql0 = sql.toString().replace(
			"select memberId.name,storeId.storeName,tradeNo,payTradeNo,tradeMoney,addTime,tradeTime,tradeType,tradeStatus,tradeTrmo",
			"select count(1)");
		return new Pager(pageSize, pageNo, ((Long) getCurrentSession().createQuery(sql0).setProperties(arrMap).uniqueResult()).intValue(), list);
	}

	/**
	 * 统计当前条件支付明细
	 * @param name
	 * @param storeName
	 * @param addTime
	 * @param tradeNo
	 * @param payTradeNo
	 * @param tradeType
	 * @param tradeStatus
	 * @return
	 */
	@Override
	public BigDecimal findCountTradeLog(String name, String storeName, String addTime,String endTime, String tradeNo, String payTradeNo,
			String tradeType, String tradeStatus){
		StringBuffer sql = new StringBuffer();
		Map<String, Object> arrMap = new HashMap<String, Object>();
		sql.append(
			"select IFNULL(SUM(t.TRADE_MONEY_),0) from gjf_member_trade_log t left join gjf_member_info m on t.MEMBER_ID_ = m.ID_ left join gjf_store_info s on t.STORE_ID_ = s.ID_  where 1=1 ");
		if (StringUtil.isNotBlank(name)) {
			arrMap.put("name", "%"+name+"%");
			sql.append(" and m.NAME_ like :name ");
		}
		if (StringUtil.isNotBlank(storeName)) {
			arrMap.put("storeName", "%"+storeName+"%");
			sql.append(" and s.STORE_NAME_ like :storeName ");
		}
		if (StringUtil.isNotBlank(tradeNo)) {
			arrMap.put("tradeNo", "%"+tradeNo+"%");
			sql.append(" and t.TRADE_NO_ like :tradeNo ");
		}
		if (StringUtil.isNotBlank(addTime)) {
			arrMap.put("addTime", addTime);
			sql.append(" and t.ADD_TIME_ >= :addTime ");
		}
		if (StringUtil.isNotBlank(endTime)) {
			arrMap.put("endTime", endTime);
			sql.append(" and t.ADD_TIME_ < :endTime");
		}
		if (StringUtil.isNotBlank(payTradeNo)) {
			arrMap.put("payTradeNo", "%"+payTradeNo+"%");
			sql.append(" and t.PAY_TRADE_NO_ like :payTradeNo ");
		}
		if (StringUtil.isNotBlank(tradeType)) {
			arrMap.put("tradeType", tradeType);
			sql.append(" and t.TRADE_TYPE_ = :tradeType ");
		}
		if (StringUtil.isNotBlank(tradeStatus)) {
			arrMap.put("tradeStatus", tradeStatus);
			sql.append(" and t.TRADE_STATUS_ = :tradeStatus ");
		}
		BigDecimal result = (BigDecimal) getCurrentSession().createSQLQuery(sql.toString()).setProperties(arrMap).uniqueResult();
		return result;
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Pager findAllBenefit(int pageNo, int pageSize, String name, String storeName, String payType, String tradeStatus,
		String addTime,String endTime, String ste, String directMemberName, String directMerchantsName) throws ParseException {
		StringBuffer sql = new StringBuffer();
		Map<String, Object> arrMap = new HashMap<String, Object>();
		sql.append("select t.ID_,t.TRADE_NO_,t.TRADE_MONEY_,t.BENEFIT_MONEY_,t.MEMBER_DIVIDENDS_NUM_,t.MERCHANTS_DIVIDENDS_NUM_,t.DIRECT_MEMBER_MONEY_,t.DIRECT_MERCHANTS_MONEY_,t.ADD_TIME_,t.PAY_TYPE_,t.TRADE_STATUS_,t.TOKEN_,m.NAME_ as memberName,s.STORE_NAME_ as storeName,a.NAME_ as directMemberName,b.NAME_ as directSellerName from gjf_member_trade_benefit t left join gjf_member_info m on m.ID_ = t.MEMBER_ID_ left join gjf_store_info s on s.ID_ = t.STORE_ID_ "
				+ " left join gjf_member_info a on t.DIRECT_MEMBER_ = a.ID_ left join gjf_member_info b on t.DIRECT_MERCHANTS_ = b.ID_ where 1 = 1 ");
		if (StringUtil.isNotBlank(name)) {
			arrMap.put("name", "%"+name+"%");
			sql.append(" and m.NAME_ like :name ");
		}
		if (StringUtil.isNotBlank(storeName)) {
			arrMap.put("storeName", "%"+storeName+"%");
			sql.append(" and s.STORE_NAME_ like :storeName ");
		}
		if (StringUtil.isNotBlank(payType)) {
			arrMap.put("payType", payType);
			sql.append(" and  t.PAY_TYPE_ = :payType ");
		}
		if (StringUtil.isNotBlank(tradeStatus)) {
			arrMap.put("tradeStatus", tradeStatus);
			sql.append(" and  t.TRADE_STATUS_ = :tradeStatus ");
		}
		if (StringUtil.isNotBlank(addTime)) {
			arrMap.put("addTime", addTime);
			sql.append(" and t.ADD_TIME_ >= :addTime ");
		}
		if (StringUtil.isNotBlank(endTime)) {
			arrMap.put("endTime", endTime);
			sql.append(" and t.ADD_TIME_ <= :endTime ");
		}
		if (StringUtil.isNotBlank(directMemberName)) {
			arrMap.put("directMemberName", "%"+directMemberName+"%");
			sql.append(" and a.NAME_ like :directMemberName ");
		}
		if (StringUtil.isNotBlank(directMerchantsName)) {
			arrMap.put("directMerchantsName", "%"+directMerchantsName+"%");
			sql.append(" and b.NAME_ like :directMerchantsName ");
		}
		sql.append(" order by t.ADD_TIME_ desc");
		List list = new ArrayList<>();
		if ("1".equals(ste)) {
			list = getCurrentSession().createSQLQuery(sql.toString()).setProperties(arrMap).list();
		} else {
			list = getCurrentSession().createSQLQuery(sql.toString()).setProperties(arrMap).setMaxResults(pageSize).setFirstResult((pageNo - 1) * pageSize)
				.list();
		}
		String [] param = {"id","tradeNo","tradeMoney","benefitMoney","memberDividendsNum","merchantsDividendsNum","directMemberMoney","directMerchantsMoney","addTime","payType","tradeStatus","token","name","storeName","directMemberName","directSellerName"};
		String sql0 = sql.toString().replace("select t.ID_,t.TRADE_NO_,t.TRADE_MONEY_,t.BENEFIT_MONEY_,t.MEMBER_DIVIDENDS_NUM_,t.MERCHANTS_DIVIDENDS_NUM_,t.DIRECT_MEMBER_MONEY_,t.DIRECT_MERCHANTS_MONEY_,t.ADD_TIME_,t.PAY_TYPE_,t.TRADE_STATUS_,t.TOKEN_,m.NAME_ as memberName,s.STORE_NAME_ as storeName,a.NAME_ as directMemberName,b.NAME_ as directSellerName", "select count(1)");
		list = BeanUtil.changeObjectArrayToMaps(list, param);
		return new Pager(pageSize, pageNo, ((BigInteger) getCurrentSession().createSQLQuery(sql0).setProperties(arrMap).uniqueResult()).intValue(), list);
	}

	@Override
	public BigDecimal findMemberWithdrawHistoryMoney(Long id) {
		String sql = "SELECT IFNULL(SUM(TRADE_MONEY_),0) FROM gjf_member_trade WHERE TRADE_TYPE_ = '1' AND TRADE_STATUS_ = '1' AND  MEMBER_ID_ = "
			+ id;
		BigDecimal reslt = (BigDecimal) getCurrentSession().createSQLQuery(sql).uniqueResult();
		return reslt;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Pager findAllTradeDivi(int pageNo, int pageSize, String name, String payStatus, String diviStatus,String startDate,String endDate) {
		StringBuffer sql = new StringBuffer();
		Map<String, Object> arrMap = new HashMap<String, Object>();
		sql.append("SELECT d.DIVI_NO_,d.DIVI_NUM_,d.DIVI_MONEY_,d.DIVI_MONEY_BLA_,d.CONSUMPTION_MONEY_,d.ADD_TIME_,"
				+ "d.PAY_TYPE_,d.PAY_STATUS_,d.DIVI_STATUS_,d.DIVI_TYPE_,d.DIVI_MEMBER_TYPE_,m.ID_,m.NAME_,s.STORE_NAME_ "
				+ "FROM gjf_member_trade_divi d LEFT JOIN gjf_member_info m ON m.ID_ = d.MEMBER_ID_ LEFT JOIN gjf_store_info s ON s.MEMBER_ID_ = m.ID_ where 1=1 ");
		if (StringUtil.isNotBlank(name)) {
			arrMap.put("name", "%"+name+"%");
			sql.append(" and m.NAME_ like :name ");
		}
		if (StringUtil.isNotBlank(payStatus)) {
			arrMap.put("payStatus", payStatus);
			sql.append(" and d.PAY_STATUS_ = :payStatus ");
		}
		if (StringUtil.isNotBlank(diviStatus)) {
			arrMap.put("diviStatus", diviStatus);
			sql.append(" and d.DIVI_STATUS_ = :diviStatus ");
		}
		if (StringUtil.isNotBlank(startDate)) {
			arrMap.put("startDate", parseDate(startDate));
			sql.append(" and d.ADD_TIME_ >= :startDate ");
		}
		if (StringUtil.isNotBlank(endDate)) {
			arrMap.put("endDate", DateUtils.addDays(parseDate(endDate), 1));
			sql.append(" and d.ADD_TIME_ < :endDate ");
		}
		sql.append(" order by d.ADD_TIME_ desc ");
		String sql0 = sql.toString().replace("SELECT d.DIVI_NO_,d.DIVI_NUM_,d.DIVI_MONEY_,d.DIVI_MONEY_BLA_,d.CONSUMPTION_MONEY_,d.ADD_TIME_,d.PAY_TYPE_,d.PAY_STATUS_,d.DIVI_STATUS_,d.DIVI_TYPE_,d.DIVI_MEMBER_TYPE_,m.ID_,m.NAME_,s.STORE_NAME_", "select count(1)");
		List list = getCurrentSession().createSQLQuery(sql.toString()).setProperties(arrMap).setMaxResults(pageSize).setFirstResult((pageNo - 1) * pageSize).list();
		String[] param = {"diviNo","diviNum","diviMoney","diviMoneyBla","consumptionMoney","addTime","payType","payStatus","diviStatus","diviType","diviMemberType","memberId","name","storeName"};
		list = BeanUtil.changeObjectArrayToMaps(list, param);
		return new Pager(pageSize,pageNo,((BigInteger)getCurrentSession().createSQLQuery(sql0).setProperties(arrMap).uniqueResult()).intValue(),list);
	}
	
	/**
	 * 统计分红权购买记录
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List findCountTradeDivi(String name, String payStatus, String diviStatus){
		StringBuffer sql = new StringBuffer();
		Map<String, Object> arrMap = new HashMap<String, Object>();
		sql.append("SELECT IFNULL(SUM(d.DIVI_NUM_),0),IFNULL(SUM(d.DIVI_MONEY_),0),IFNULL(SUM(d.DIVI_MONEY_BLA_),0),IFNULL(SUM(d.CONSUMPTION_MONEY_),0) "
				+ " FROM gjf_member_trade_divi d LEFT JOIN gjf_member_info m ON m.ID_ = d.MEMBER_ID_ LEFT JOIN gjf_store_info s ON s.MEMBER_ID_ = m.ID_ where 1=1 ");
		if (StringUtil.isNotBlank(name)) {
			arrMap.put("name", "%"+name+"%");
			sql.append(" and m.NAME_ like :name ");
		}
		if (StringUtil.isNotBlank(payStatus)) {
			arrMap.put("payStatus", payStatus);
			sql.append(" and d.PAY_STATUS_ = :payStatus ");
		}
		if (StringUtil.isNotBlank(diviStatus)) {
			arrMap.put("diviStatus", diviStatus);
			sql.append(" and d.DIVI_STATUS_ = :diviStatus ");
		}
		List list = getCurrentSession().createSQLQuery(sql.toString()).setProperties(arrMap).list();
		String[] param = {"totalDiviNum","totalDiviMoney","totalDiviMoneyBla","totalConsumption"};
		list = BeanUtil.changeObjectArrayToMaps(list, param);
		return list;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Pager findMemberTradeDiviHistoryByPage(int pageNo, int pageSize, String name, String addTime,String endTime, String tradeNo,
			String tradeType, String tradeStatus) {
		StringBuffer sql = new StringBuffer();
		Map<String, Object> arrMap = new HashMap<String, Object>();
		sql.append("SELECT m.NAME_,h.TRADE_NO_,h.TRADE_MONEY_,h.TRADE_DIVI_NUM_,h.TRADE_UNIT_,h.TRADE_REAL_UNIT_,h.TRADE_RATIO_,h.ADD_TIME_,h.TRADE_TIME_,h.TRADE_TYPE_,h.TRADE_STATUS_,h.TRADE_TRMO_,h.TOKEN_"
				   + ",h.TRADE_SECOND_MONEY_,h.TRADE_THREE_MONEY_,h.TRADE_SECOND_UNIT_,h.TRADE_THREE_UNIT_,h.FIRST_CONSUMPTION_,h.SECOND_CONSUMPTION_,h.THREE_CONSUMPTION_"
				+ " from gjf_member_trade_divi_history h LEFT JOIN gjf_member_info m on h.MEMBER_ID_ = m.ID_ where 1=1 ");
		if (StringUtil.isNotBlank(name)) {
			arrMap.put("name", "%"+name+"%");
			sql.append(" and m.NAME_ like :name ");
		}
		if (StringUtil.isNotBlank(addTime)) {
			arrMap.put("addTime", addTime);
			sql.append(" and h.ADD_TIME_ >= :addTime ");
		}
		if (StringUtil.isNotBlank(endTime)) {
			arrMap.put("endTime", endTime);
			sql.append(" and h.ADD_TIME_ < :endTime ");
		}
		if (StringUtil.isNotBlank(tradeNo)) {
			arrMap.put("tradeNo", "%"+tradeNo+"%");
			sql.append(" and h.TRADE_NO_ like :tradeNo ");
		}
		if (StringUtil.isNotBlank(tradeType)) {
			arrMap.put("tradeType", tradeType);
			sql.append(" and h.TRADE_TYPE_ = :tradeType ");
		}
		if (StringUtil.isNotBlank(tradeStatus)) {
			arrMap.put("tradeStatus", tradeStatus);
			sql.append(" and h.TRADE_STATUS_ = :tradeStatus ");
		}
		sql.append(" order by h.ADD_TIME_ desc ");
		String sql0 = sql.toString().replace("SELECT m.NAME_,h.TRADE_NO_,h.TRADE_MONEY_,h.TRADE_DIVI_NUM_,h.TRADE_UNIT_,h.TRADE_REAL_UNIT_,h.TRADE_RATIO_,h.ADD_TIME_,h.TRADE_TIME_,h.TRADE_TYPE_,h.TRADE_STATUS_,h.TRADE_TRMO_,h.TOKEN_,h.TRADE_SECOND_MONEY_,h.TRADE_THREE_MONEY_,h.TRADE_SECOND_UNIT_,h.TRADE_THREE_UNIT_,h.FIRST_CONSUMPTION_,h.SECOND_CONSUMPTION_,h.THREE_CONSUMPTION_", "select count(1)");
		List list = getCurrentSession().createSQLQuery(sql.toString()).setProperties(arrMap).setMaxResults(pageSize).setFirstResult((pageNo - 1) * pageSize).list();
		String[] param = {"name","tradeNo","tradeMoney","tradeDiviNum","tradeUnit","tradeRealUnit","tradeRatio","addTime","tradeTime","tradeType","tradeStatus","tradeTrmo","token","tradeSecondMoney","tradeThreeMoney","tradeSecondUnit","tradeThreeUnit","firstConsumption","secondConsumption","threeConsumption"};
		list = BeanUtil.changeObjectArrayToMaps(list, param);
		return new Pager(pageSize,pageNo,((BigInteger)getCurrentSession().createSQLQuery(sql0).setProperties(arrMap).uniqueResult()).intValue(),list);
	}
	
	
	/**
	 * 当前条件统计分红记录
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List findCountTradeDiviHistory(String name,String addTime,String endTime,String tradeNo,String tradeType,String tradeStatus) {	
		StringBuffer sql = new StringBuffer();
		Map<String, Object> arrMap = new HashMap<String, Object>();
		sql.append("SELECT IFNULL(SUM(TRADE_MONEY_),0)+IFNULL(SUM(TRADE_SECOND_MONEY_),0)+IFNULL(SUM(TRADE_THREE_MONEY_),0),IFNULL(SUM(TRADE_DIVI_NUM_),0) "
				+ " from gjf_member_trade_divi_history h LEFT JOIN gjf_member_info m on h.MEMBER_ID_ = m.ID_ where 1=1 ");
		if (StringUtil.isNotBlank(name)) {
			arrMap.put("name", "%"+name+"%");
			sql.append(" and m.NAME_ like :name ");
		}
		if (StringUtil.isNotBlank(addTime)) {
			arrMap.put("addTime", addTime);
			sql.append(" and h.ADD_TIME_ >= :addTime ");
		}
		if (StringUtil.isNotBlank(endTime)) {
			arrMap.put("endTime", endTime);
			sql.append(" and h.ADD_TIME_ < :endTime ");
		}
		if (StringUtil.isNotBlank(tradeNo)) {
			arrMap.put("tradeNo", "%"+tradeNo+"%");
			sql.append(" and h.TRADE_NO_ like :tradeNo ");
		}
		if (StringUtil.isNotBlank(tradeType)) {
			arrMap.put("tradeType", tradeType);
			sql.append(" and h.TRADE_TYPE_ = :tradeType ");
		}
		if (StringUtil.isNotBlank(tradeStatus)) {
			arrMap.put("tradeStatus", tradeStatus);
			sql.append(" and h.TRADE_STATUS_ = :tradeStatus ");
		}
		List list = getCurrentSession().createSQLQuery(sql.toString()).setProperties(arrMap).list();
		String[] param = {"totalTradeMoney","totalTradeDiviNum"};
		list = BeanUtil.changeObjectArrayToMaps(list, param);
		return list;
	}
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private Date parseDate(String str){
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			throw new RuntimeException(String.format("字符串:%s转换成日期类型出错", str));
		}
	}

	/**
	 *  凤凰宝交易记录
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Pager findFhTreaureTradeHistory(Integer pageNo, Integer pageSize, String account) {
		String hql="from GjfMemberTreasureTrade where memebrMobile=:account or (transferMemberMobile=:account and tradeType='1') order by addTime desc";
		List<GjfMemberTreasureTrade> list=getCurrentSession().createQuery(hql.toString()).setParameter("account", account).setFirstResult((pageNo-1)*pageSize).setMaxResults(pageSize).list();
		String countHql="select count(*) from GjfMemberTreasureTrade where memebrMobile=:account or transferMemberMobile=:account";
		Long count=(Long) getCurrentSession().createQuery(countHql).setParameter("account", account).uniqueResult();
		return new Pager(pageSize, pageNo, count.intValue(), list);
	}

}
