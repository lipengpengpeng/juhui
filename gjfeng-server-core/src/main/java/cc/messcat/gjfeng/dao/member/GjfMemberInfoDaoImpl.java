package cc.messcat.gjfeng.dao.member;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
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
import cc.messcat.gjfeng.common.util.BeanUtil;
import cc.messcat.gjfeng.common.util.StringUtil;
import cc.messcat.gjfeng.common.vo.app.MemberBankVo;
import cc.messcat.gjfeng.common.vo.app.MemberTradeBenefitVo;
import cc.messcat.gjfeng.common.vo.app.StoreInfoVo;
import cc.messcat.gjfeng.dao.BaseHibernateDaoImpl;
import cc.messcat.gjfeng.entity.GjfMemberInfo;
import cc.messcat.gjfeng.entity.GjfMemberTrade;

@Repository("gjfMemberInfoDao")
public class GjfMemberInfoDaoImpl extends BaseHibernateDaoImpl<Serializable>implements GjfMemberInfoDao {

	/*
	 * 查询当日获得的让利金额
	 * 
	 * @see
	 * cc.messcat.gjfeng.dao.member.GjfMemberInfoDao#findTotalBenefitByToday(
	 * java.lang.Long)
	 */
	@Override
	public BigDecimal findTotalBenefitByToday(Long memebrId) {
		String sql = "SELECT IFNULL(SUM(d.DIVI_MONEY_),0.00) FROM gjf_member_trade_divi d WHERE d.MEMBER_ID_=? AND d.DIVI_STATUS_='1' AND TO_DAYS(d.ADD_TIME_)=TO_DAYS(NOW())";
		return new BigDecimal(
				String.valueOf(getCurrentSession().createSQLQuery(sql).setParameter(0, memebrId).uniqueResult()));
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Pager findMembersByPager(int pageNo, int pageSize, String name, String userName, String mobile, String type,
			String identity, String realNameState,String isActivityMember) {
		StringBuffer sql = new StringBuffer();
		Map<String, Object> arrMap = new HashMap<String, Object>();
		sql.append(
				"SELECT m.ID_,m.UNION_ID_,m.NAME_,m.MOBILE_,m.NICK_NAME_,m.SEX_,m.EMAIL_,m.ID_CARD_,m.IMG_ID_CARD_BEFORE_URL_,"
						+ "m.IMG_ID_CARD_BEHIND_URL_,m.IMG_HEAD_URL_,m.IMG_QR_URL_,m.SUPER_ID_,m.PROVICE_ID_,m.CITY_ID_,m.AREA_ID_,m.ADRRESS_,"
						+ "m.REMARK_,m.BALANCE_MONEY_,m.CONSUMPTION_MONEY_,m.CUMULATIVE_MONEY_,m.LOW_LEVEL_CUMULATIVE_MONEY_,m.WITHDRAWAL_MONEY_,"
						+ "m.DIVIDENDS_MONEY_BLA_,m.DIVIDENDS_TOTAL_MONEY_,m.DIVIDENDS_NUM_,m.DIRECT_MEMBER_TOTAL_MONEY_,m.DIRECT_MERCHANTS_TOTAL_MONEY_,"
						+ "m.AGENT_MONEY_,m.AGENT_TOTAL_MONEY_,m.INSURANCE_MONEY_,m.ADD_TIME_,m.EDIT_TIME_,m.AGENT_START_DATE_,m.AGENT_END_DATE_,"
						+ "m.LAST_LOGIN_TIME_,m.IS_READ_NAME_,m.TYPE_,m.IDENTITY_,m.IS_REPORT_,m.IS_BUY_,m.IS_MESSAGE_,m.IS_COMMENTS_,"
						+ "m.STATUS_,m.IS_DEL_,m.TOKEN_,m.REAL_NAME_STATE_,m.LINE_OF_CREDIT_,m.OPEN_ID,a.NAME_ as SUPER_Name_,a.TOKEN_ AS SUPER_TOKEN_,m.IS_OPCENTER_,m.IS_ACTIVE_MEMBER_,m.MERCHANT_TYPE_,m.MERCHANT_MODEL_,"
						+ "m.MERCHANT_FIRST_CONSUMPTION_MONEY_,m.MERCHANT_FIRST_CUMULATIVE_MONEY_,m.MERCHANT_SECOND_CONSUMPTION_MONEY_,m.MERCHANT_SECOND_CUMULATIVE_MONEY_,"
						+ "m.MERCHANT_THREE_CONSUMPTION_MONEY_,m.MERCHANT_THREE_CUMULATIVE_MONEY_,m.MERCHANT_FIRST_DIVI_NUM_,m.MERCHANT_SECOND_DIVI_NUM_,m.MERCHANT_THREE_DIVI_NUM_"
						+ " from  gjf_member_info m left join gjf_member_info a on m.SUPER_ID_ = a.ID_ where m.IS_DEL_ = 1 ");
		if (StringUtil.isNotBlank(name)) {
			arrMap.put("name", "%" + name + "%");
			sql.append(" and m.NAME_ like :name ");
		}
		if (StringUtil.isNotBlank(userName)) {
			arrMap.put("userName", "%" + userName + "%");
			sql.append(" and m.NICK_NAME_ like :userName ");
		}
		if (StringUtil.isNotBlank(mobile)) {
			arrMap.put("mobile", "%" + mobile + "%");
			sql.append(" and m.MOBILE_ like :mobile ");
		}
		if (StringUtil.isNotBlank(type) && Integer.parseInt(type) != 2) {
			arrMap.put("type", type);
			sql.append(" and m.TYPE_ = :type ");
		}
		if (StringUtil.isNotBlank(isActivityMember) && Integer.parseInt(isActivityMember) != 2) {
			arrMap.put("isActivityMember", isActivityMember);
			sql.append(" and m.IS_ACTIVE_MEMBER_ = :isActivityMember ");
		}
		if (StringUtil.isNotBlank(identity) && Integer.parseInt(identity) != 4) {
			arrMap.put("identity", identity);
			sql.append(" and m.IDENTITY_ = :identity ");
		}
		if (StringUtil.isNotBlank(realNameState) && Integer.parseInt(realNameState) != 4) {
			arrMap.put("realNameState", realNameState);
			sql.append(" and m.REAL_NAME_STATE_ = :realNameState ");
		}
		sql.append(" order by m.ADD_TIME_ DESC");
		List list = getCurrentSession().createSQLQuery(sql.toString()).setProperties(arrMap).setMaxResults(pageSize)
				.setFirstResult((pageNo - 1) * pageSize).list();
		String[] param = { "id", "unionId", "name", "mobile", "nickName", "sex", "email", "idCard",
				"imgIdCardBeforeUrl", "imgIdCardBehindUrl", "imgHeadUrl", "imgQrUrl", "superId", "proviceId", "cityId",
				"areaId", "adrress", "remark", "balanceMoney", "consumptionMoney", "cumulativeMoney",
				"lowLevelCumulativeMoney", "withdrawalMoney", "dividendsMoneyBla", "dividendsTotalMoney",
				"dividendsNum", "directMemberTotalMoney", "directMerchantsTotalMoney", "agentMoney", "agentTotalMoney",
				"insuranceMoney", "addTime", "editTime", "agentStartDate", "agentEndDate", "lastLoginTime",
				"isReadName", "type", "identity", "isReport", "isBuy", "isMessage", "isComments", "status", "isDel",
				"token", "realNameState", "lineOfCrade", "openId", "superName", "superToken", "isOpcenter" ,"isactiveMember","merchantType","merchantModel",
				"merchantFirstCousumptionMoney","merchantFirstCumulativeMoney","merchantSecondCousumptionMoney","merchantSecondCumulativeMoney",
				"merchantThreeCousumptionMoney","merchantThreeCumulativeMoney","merchantFirstDiviNum","merchantSecondDiviNum","merchantThreeDiviNum"};
		list = BeanUtil.changeObjectArrayToMaps(list, param);
		String sqlCount = sql.toString().replace(
				"SELECT m.ID_,m.UNION_ID_,m.NAME_,m.MOBILE_,m.NICK_NAME_,m.SEX_,m.EMAIL_,m.ID_CARD_,m.IMG_ID_CARD_BEFORE_URL_,m.IMG_ID_CARD_BEHIND_URL_,m.IMG_HEAD_URL_,m.IMG_QR_URL_,m.SUPER_ID_,m.PROVICE_ID_,m.CITY_ID_,m.AREA_ID_,m.ADRRESS_,m.REMARK_,m.BALANCE_MONEY_,m.CONSUMPTION_MONEY_,m.CUMULATIVE_MONEY_,m.LOW_LEVEL_CUMULATIVE_MONEY_,m.WITHDRAWAL_MONEY_,m.DIVIDENDS_MONEY_BLA_,m.DIVIDENDS_TOTAL_MONEY_,m.DIVIDENDS_NUM_,m.DIRECT_MEMBER_TOTAL_MONEY_,m.DIRECT_MERCHANTS_TOTAL_MONEY_,m.AGENT_MONEY_,m.AGENT_TOTAL_MONEY_,m.INSURANCE_MONEY_,m.ADD_TIME_,m.EDIT_TIME_,m.AGENT_START_DATE_,m.AGENT_END_DATE_,m.LAST_LOGIN_TIME_,m.IS_READ_NAME_,m.TYPE_,m.IDENTITY_,m.IS_REPORT_,m.IS_BUY_,m.IS_MESSAGE_,m.IS_COMMENTS_,m.STATUS_,m.IS_DEL_,m.TOKEN_,m.REAL_NAME_STATE_,m.LINE_OF_CREDIT_,m.OPEN_ID,a.NAME_ as SUPER_Name_,a.TOKEN_ AS SUPER_TOKEN_,m.IS_OPCENTER_,m.IS_ACTIVE_MEMBER_,m.MERCHANT_TYPE_,m.MERCHANT_MODEL_,"
				+ "m.MERCHANT_FIRST_CONSUMPTION_MONEY_,m.MERCHANT_FIRST_CUMULATIVE_MONEY_,m.MERCHANT_SECOND_CONSUMPTION_MONEY_,m.MERCHANT_SECOND_CUMULATIVE_MONEY_,m.MERCHANT_THREE_CONSUMPTION_MONEY_,m.MERCHANT_THREE_CUMULATIVE_MONEY_,m.MERCHANT_FIRST_DIVI_NUM_,m.MERCHANT_SECOND_DIVI_NUM_,m.MERCHANT_THREE_DIVI_NUM_",
				"select count(1)");
		Pager pager = new Pager(pageSize, pageNo,
				((BigInteger) getCurrentSession().createSQLQuery(sqlCount).setProperties(arrMap).uniqueResult())
						.intValue(),
				list);
		return pager;

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<GjfMemberInfo> findAllMember() {
		String hql = "select m from GjfMemberInfo m where status = '1' and isDel = '1'";
		List list = getCurrentSession().createQuery(hql).list();
		return list;
	}

	/*
	 * 查找我的银行卡
	 * 
	 * @see
	 * cc.messcat.gjfeng.dao.member.GjfMemberInfoDao#findMyBankCard(java.lang.
	 * String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<MemberBankVo> findMyBankCard(Long memebrId, int pageSize) {
		String sql = "SELECT mb.ID_ AS id,b.BANK_CODE_ AS bankCode,b.BANK_COLOR_ AS bankColor,b.BANK_NAME_ AS bankName,"
				+ "b.BANK_PIC_ AS bankPic,mb.BANK_SUB_ AS bankSub,mb.BANK_CARD_ AS bankCard,mb.HOLDER_ AS holder "
				+ "FROM gjf_member_bank mb LEFT JOIN gjf_bank_info b ON b.ID_=mb.BANK_ID_ WHERE mb.MEMBER_ID_=? "
				+ "AND mb.STATUS_='1'";
		if (pageSize == 1) {
			return getCurrentSession().createSQLQuery(sql.toString()).addScalar("id", LongType.INSTANCE)
					.addScalar("bankCode", StringType.INSTANCE).addScalar("bankColor", StringType.INSTANCE)
					.addScalar("bankName", StringType.INSTANCE).addScalar("bankPic", StringType.INSTANCE)
					.addScalar("bankSub", StringType.INSTANCE).addScalar("bankCard", StringType.INSTANCE)
					.addScalar("holder", StringType.INSTANCE)
					.setResultTransformer(Transformers.aliasToBean(MemberBankVo.class)).setParameter(0, memebrId)
					.setMaxResults(1).setFirstResult(0).list();
		}
		return getCurrentSession().createSQLQuery(sql.toString()).addScalar("id", LongType.INSTANCE)
				.addScalar("bankName", StringType.INSTANCE).addScalar("bankPic", StringType.INSTANCE)
				.addScalar("bankColor", StringType.INSTANCE).addScalar("bankSub", StringType.INSTANCE)
				.addScalar("bankCard", StringType.INSTANCE).addScalar("holder", StringType.INSTANCE)
				.setResultTransformer(Transformers.aliasToBean(MemberBankVo.class)).setParameter(0, memebrId).list();
	}

	/*
	 * 查找前端商家查询自己的让利流水记录
	 * 
	 * @see cc.messcat.gjfeng.dao.member.GjfMemberInfoDao#findStoreBenefit(int,
	 * int, java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<MemberTradeBenefitVo> findStoreBenefit(int pageNo, int pageSize, Long storeId) {
		String sql = "select b.TRADE_NO_ tradeNo,m.MOBILE_ mobile,b.BENEFIT_MONEY_ benefitMoney,UNIX_TIMESTAMP(b.ADD_TIME_)*1000 addTime,"
				+ "b.PAY_TYPE_ payType,b.TRADE_STATUS_ tradeStatus from gjf_member_trade_benefit b left join gjf_member_info m on m.ID_=b.MEMBER_ID_ "
				+ "where b.STORE_ID_=? and b.TRADE_STATUS_='1' ORDER BY b.ADD_TIME_ DESC";
		return getCurrentSession().createSQLQuery(sql.toString()).addScalar("tradeNo", StringType.INSTANCE)
				.addScalar("mobile", StringType.INSTANCE).addScalar("benefitMoney", BigDecimalType.INSTANCE)
				.addScalar("addTime", LongType.INSTANCE).addScalar("payType", StringType.INSTANCE)
				.addScalar("tradeStatus", StringType.INSTANCE)
				.setResultTransformer(Transformers.aliasToBean(MemberTradeBenefitVo.class)).setParameter(0, storeId)
				.setMaxResults(pageSize).setFirstResult((pageNo - 1) * pageSize).list();
	}

	/*
	 * 查询用户的商家给他的让利记录
	 * 
	 * @see cc.messcat.gjfeng.dao.member.GjfMemberInfoDao#findMemberBenefit(int,
	 * int, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<MemberTradeBenefitVo> findMemberBenefit(int pageNo, int pageSize, String account) {
		String sql = "select b.TRADE_NO_ tradeNo,s.STORE_NAME_ mobile,b.TRADE_MONEY_ benefitMoney,UNIX_TIMESTAMP(b.ADD_TIME_)*1000 addTime,"
				+ "b.PAY_TYPE_ payType,b.TRADE_STATUS_ tradeStatus from gjf_member_trade_benefit b left join gjf_member_info m on m.ID_=b.MEMBER_ID_ left join gjf_store_info s ON b.STORE_ID_=s.ID_ "
				+ "where m.MOBILE_=? and b.TRADE_STATUS_='1' ORDER BY b.ADD_TIME_ DESC";
		return getCurrentSession().createSQLQuery(sql.toString()).addScalar("tradeNo", StringType.INSTANCE)
				.addScalar("mobile", StringType.INSTANCE).addScalar("benefitMoney", BigDecimalType.INSTANCE)
				.addScalar("addTime", LongType.INSTANCE).addScalar("payType", StringType.INSTANCE)
				.addScalar("tradeStatus", StringType.INSTANCE)
				.setResultTransformer(Transformers.aliasToBean(MemberTradeBenefitVo.class)).setParameter(0, account)
				.setMaxResults(pageSize).setFirstResult((pageNo - 1) * pageSize).list();
	}

	@Override
	public void delMemAdder(Long memId, Long id) {
		String sql = "delete from gjf_member_address where MEMBER_ID_=:membId and ID_=:id";
		getCurrentSession().createSQLQuery(sql.toString()).setParameter("membId", memId).setParameter("id", id)
				.executeUpdate();
	}

	/**
	 * 查询所有代理商
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Pager findAllAgents(int pageNo, int pageSize, String name, String startDate, String identity,
			String status) {
		StringBuffer sql = new StringBuffer();
		Map<String, Object> arrMap = new HashMap<String, Object>();
		sql.append(
				"select g.ID_,g.MOBILE_,g.NAME_,g.AGENT_MONEY_,g.AGENT_TOTAL_MONEY_,g.AGENT_START_DATE_,g.AGENT_END_DATE_,g.IDENTITY_,g.STATUS_,g.TOKEN_ from gjf_member_info as g where g.IDENTITY_ <> 0 ");
		if (StringUtil.isNotBlank(name)) {
			arrMap.put("name", "%" + name + "%");
			sql.append(" and g.NAME_ like :name ");
		}
		if (StringUtil.isNotBlank(startDate)) {
			arrMap.put("startDate", "%" + startDate + "%");
			sql.append(" and g.AGENT_START_DATE_ like :startDate ");
		}
		if (StringUtil.isLongValue(identity)) {
			arrMap.put("identity", identity);
			sql.append(" and g.IDENTITY_ = :identity ");
		}
		if (StringUtil.isNotBlank(status)) {
			arrMap.put("status", status);
			sql.append(" and g.STATUS_ = :status ");
		}
		sql.append("order by g.ADD_TIME_ desc");
		List list = getCurrentSession().createSQLQuery(sql.toString()).setProperties(arrMap).setMaxResults(pageSize)
				.setFirstResult((pageNo - 1) * pageSize).list();
		String[] param = { "id", "mobile", "name", "agentMoney", "agentTotalMoney", "startDate", "endDate", "identity",
				"status", "token" };
		list = BeanUtil.changeObjectArrayToMaps(list, param);
		String sql0 = sql.toString().replace(
				"select g.ID_,g.MOBILE_,g.NAME_,g.AGENT_MONEY_,g.AGENT_TOTAL_MONEY_,g.AGENT_START_DATE_,g.AGENT_END_DATE_,g.IDENTITY_,g.STATUS_,g.TOKEN_",
				"select count(1)");
		BigInteger count = (BigInteger) getCurrentSession().createSQLQuery(sql0).setProperties(arrMap).uniqueResult();
		Pager pager = new Pager(pageSize, pageNo, count.intValue(), list);
		return pager;
	}

	@Override
	public Double findMemberBenefitMoney(String type, Long id) {
		if (Integer.parseInt(type) == 1) {
			BigDecimal obj = (BigDecimal) getCurrentSession()
					.createSQLQuery(
							"select ifnull(sum(BENEFIT_MONEY_),0) from gjf_member_trade_benefit where date(ADD_TIME_)=CURDATE() and TRADE_STATUS_=1 ")
					.uniqueResult();
			if (BeanUtil.isValid(obj)) {
				return obj.doubleValue();
			} else {
				return 0.00;
			}
		} else {
			BigDecimal obj = (BigDecimal) getCurrentSession()
					.createSQLQuery(
							"select ifnull(sum(BENEFIT_MONEY_),0) from gjf_member_trade_benefit where date(ADD_TIME_)=CURDATE() and TRADE_STATUS_=1 ")
					.uniqueResult();
			if (BeanUtil.isValid(obj)) {
				return obj.doubleValue();
			} else {
				return 0.00;
			}
		}

	}

	/*
	 * 统计当前用户直推会员的交易总额
	 * 
	 * @see
	 * cc.messcat.gjfeng.dao.member.GjfMemberInfoDao#findDiviTotalMoeny(java.
	 * lang.Long)
	 */
	@Override
	public Double findDiviTotalMoeny(Long id) {
		BigDecimal obj = (BigDecimal) getCurrentSession()
				.createSQLQuery(
						"SELECT IFNULL(SUM(TRADE_MONEY_),0) FROM gjf_member_trade_divi_history WHERE MEMBER_ID_=:memId AND (TRADE_TYPE_='0' OR TRADE_TYPE_='1') AND TRADE_STATUS_='1'")
				.setParameter("memId", id).uniqueResult();
		if (BeanUtil.isValid(obj)) {
			return obj.doubleValue();
		} else {
			return 0.00;
		}

	}

	/*
	 * 获取所有开启用户的总分红权数
	 * 
	 * @see
	 * cc.messcat.gjfeng.dao.member.GjfMemberInfoDao#findMemberDivNum(java.lang.
	 * String)
	 */
	@Override
	public Double findMemberDivNum(String type) {
		BigDecimal obj = new BigDecimal(0.00);
		if (Integer.parseInt(type) == 0) {
			obj = (BigDecimal) getCurrentSession()
					.createSQLQuery(
							"SELECT IFNULL(SUM(num),0) FROM (SELECT IF(DIVIDENDS_TOTAL_MONEY_/CUMULATIVE_MONEY_ >= 0.2,"
									+ "IF(DIVIDENDS_TOTAL_MONEY_/CUMULATIVE_MONEY_ >= 0.5,FLOOR(TRUNCATE(DIVIDENDS_NUM_/4,6)),FLOOR(TRUNCATE(DIVIDENDS_NUM_/2,6))),FLOOR(TRUNCATE(DIVIDENDS_NUM_,6))) AS num FROM gjf_member_info WHERE STATUS_=1 AND IS_DEL_=1) AS totalSum")
					.uniqueResult();
			if (BeanUtil.isValid(obj)) {
				return obj.doubleValue();
			} else {
				return 0.00;
			}

		} else {
			obj = (BigDecimal) getCurrentSession()
					.createSQLQuery(
							"SELECT IFNULL(SUM(STORE_DIVIDENDS_NUM_),0) FROM gjf_store_info WHERE STORE_STATUS_=1 AND IS_DEL_=1")
					.uniqueResult();
			if (BeanUtil.isValid(obj)) {
				return obj.doubleValue();
			} else {
				return 0.00;
			}
		}

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Pager findMemberByCondition(int pageNo, int pageSize, String name, String nickName, String mobile,
			String type) {
		StringBuffer sql = new StringBuffer();
		Map<String, Object> arrMap = new HashMap<String, Object>();
		sql.append(
				"select ID_,NAME_,MOBILE_,NICK_NAME_,SEX_,BALANCE_MONEY_,WITHDRAWAL_MONEY_,DIVIDENDS_NUM_,CUMULATIVE_MONEY_,LAST_LOGIN_TIME_,TYPE_,TOKEN_ from gjf_member_info where STATUS_ ='1' and IS_DEL_ = '1' ");
		if (StringUtil.isNotBlank(name)) {
			arrMap.put("name", "%" + name + "%");
			sql.append(" and NAME_ like :name ");
		}
		if (StringUtil.isNotBlank(nickName)) {
			arrMap.put("nickName", "%" + nickName + "%");
			sql.append(" and NICK_NAME_ like :nickName ");
		}
		if (StringUtil.isNotBlank(mobile)) {
			arrMap.put("mobile", "%" + mobile + "%");
			sql.append(" and MOBILE_ like :mobile ");
		}
		if (StringUtil.isNotBlank(type)) {
			arrMap.put("type", type);
			sql.append(" and TYPE_ = :type ");
		}
		sql.append(" order by ADD_TIME_ DESC");
		List list = getCurrentSession().createSQLQuery(sql.toString()).setProperties(arrMap).setMaxResults(pageSize)
				.setFirstResult((pageNo - 1) * pageSize).list();
		String[] param = { "id", "name", "mobile", "nickName", "sex", "balanceMoney", "withdrawalMoney", "diviNum",
				"cumulativeMoney", "lastLoginTime", "type", "token" };
		list = BeanUtil.changeObjectArrayToMaps(list, param);
		String sqlCount = sql.toString().replace(
				"select ID_,NAME_,MOBILE_,NICK_NAME_,SEX_,BALANCE_MONEY_,WITHDRAWAL_MONEY_,DIVIDENDS_NUM_,CUMULATIVE_MONEY_,LAST_LOGIN_TIME_,TYPE_,TOKEN_",
				"select count(1)");
		Pager pager = new Pager(pageSize, pageNo,
				((BigInteger) getCurrentSession().createSQLQuery(sqlCount).setProperties(arrMap).uniqueResult())
						.intValue(),
				list);
		return pager;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<GjfMemberTrade> findAllMemberTradeByH5() {
		String hql = "from GjfMeberTrade where tradeType=0 and tradeStatus=0 and PAY_TYPE_ payType=3 and to_char(addTime,'yyyy-MM-dd') =  to_char(?,yyyy-MM-dd)";
		return getCurrentSession().createQuery(hql).setParameter("0", new Date()).list();
	}

	/**
	 * 获取运营中心列表数据
	 */
	@Override
	public Pager findMemberOpcentionByCondition(int pageNo, int pageSize, String name) {
		StringBuffer sql = new StringBuffer();
		Map<String, Object> arrMap = new HashMap<String, Object>();
		sql.append(
				"select g.ID_,g.MOBILE_,g.NAME_,g.OPCENTER_MONEY_,g.STATUS_,g.TOKEN_,g.OPCENTER_TOTAL_MONEY_ from gjf_member_info as g where g.IS_OPCENTER_ =1 ");
		if (StringUtil.isNotBlank(name)) {
			arrMap.put("name", "%" + name + "%");
			sql.append(" and g.NAME_ like :name ");
		}

		sql.append("order by g.ADD_TIME_ desc");
		List list = getCurrentSession().createSQLQuery(sql.toString()).setProperties(arrMap).setMaxResults(pageSize)
				.setFirstResult((pageNo - 1) * pageSize).list();
		String[] param = { "id", "mobile", "name", "opcenterMoney", "status", "token","opcenterTotalMoney" };
		list = BeanUtil.changeObjectArrayToMaps(list, param);
		String sql0 = sql.toString().replace("select g.ID_,g.MOBILE_,g.NAME_,g.OPCENTER_MONEY_,g.STATUS_,g.TOKEN_,g.OPCENTER_TOTAL_MONEY_",
				"select count(1)");
		BigInteger count = (BigInteger) getCurrentSession().createSQLQuery(sql0).setProperties(arrMap).uniqueResult();
		Pager pager = new Pager(pageSize, pageNo, count.intValue(), list);
		return pager;
	}

	/**
	 * 统计运营中心商家总让利金额或当天天让利金额
	 * 
	 * @param type
	 *            0 总让利金额 1 当天让利金额
	 * @return
	 */
	@Override
	public BigDecimal countOpentionMchBenefitMoney(String type, Long memId) {
		StringBuilder sql = new StringBuilder();
		if ("0".equals(type)) {
			sql.append(
					"select ifnull(sum(BENEFIT_MONEY_),0) from gjf_member_trade_opcenter where date_format(ADD_TIME_,'%Y%M%d')=date_format(now(),'%Y%M%d') and MEMBER_ID_=:memId and TRADE_TYPE_=1 ");
		} else {
			sql.append(
					"select ifnull(sum(BENEFIT_MONEY_),0) from gjf_member_trade_opcenter where  MEMBER_ID_=:memId and TRADE_TYPE_=1 ");
		}
		return (BigDecimal) getCurrentSession().createSQLQuery(sql.toString()).setParameter("memId", memId)
				.uniqueResult();
	}

	/**
	 * 获取运营中心商家列表信息
	 */
	@Override
	public Object findOpcenterMchInfo(Integer pageNo, Integer pageSize, Long memId, String type) {
		Object o = null;
		String sql = "SELECT s.STORE_NAME_ storeName,s.SELLER_MOBILE_ sellerMobile,s.STORE_SALE_TOTAL_MONEY_ storeSaleTotalMoney,(SELECT IFNULL(SUM(TRADE_MONEY_),0) FROM gjf_member_trade_benefit WHERE STORE_ID_=s.ID_ AND DATE_FORMAT(ADD_TIME_,'%Y-%m')=DATE_FORMAT(NOW(),'%Y-%m') ) AS storeMonthSaleTotalMoney  "
				+ "FROM gjf_store_info s LEFT JOIN gjf_member_info m ON s.MEMBER_ID_=m.ID_ WHERE m.SUPER_ID_=:memId AND m.TYPE_='1' AND m.STATUS_='1' AND m.IS_DEL_='1'";
		if (type.equals("1")) {
			String sqlCount = "SELECT COUNT(s.ID_) FROM gjf_store_info s LEFT JOIN gjf_member_info m ON s.MEMBER_ID_=m.ID_ WHERE m.SUPER_ID_=:memId AND m.TYPE_='1' AND m.STATUS_='1' AND m.IS_DEL_='1'";
			o = getCurrentSession().createSQLQuery(sqlCount).setParameter("memId", memId).uniqueResult();
		} else {
			o = getCurrentSession().createSQLQuery(sql).addScalar("storeName", StringType.INSTANCE)
					.addScalar("sellerMobile", StringType.INSTANCE)
					.addScalar("storeSaleTotalMoney", BigDecimalType.INSTANCE)
					.addScalar("storeMonthSaleTotalMoney", BigDecimalType.INSTANCE)
					.setResultTransformer(Transformers.aliasToBean(StoreInfoVo.class)).setParameter("memId", memId)
					.setFirstResult((pageNo - 1) * pageSize).setMaxResults(pageSize).list();
		}
		return o;

	}

	/**
	 * 查询用户下级
	 */
	@SuppressWarnings({ "rawtypes"})
	@Override
	public List findUnderMember(Long supId) {
		String sql="select ID_ from gjf_member_info where SUPER_ID_=:supId";
		List list=getCurrentSession().createSQLQuery(sql.toString()).setParameter("supId", supId).list();
		
		return list;
	}

}
