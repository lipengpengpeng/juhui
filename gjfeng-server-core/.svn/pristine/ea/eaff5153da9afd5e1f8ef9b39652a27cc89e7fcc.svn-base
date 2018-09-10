package cc.messcat.gjfeng.service.count;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cc.messcat.gjfeng.common.bean.Pager;
import cc.messcat.gjfeng.common.exception.MyException;
import cc.messcat.gjfeng.common.util.BeanUtil;
import cc.messcat.gjfeng.common.util.ObjValid;
import cc.messcat.gjfeng.common.vo.app.ResultVo;
import cc.messcat.gjfeng.dao.count.CountInfoDao;
import cc.messcat.gjfeng.entity.GjfBenefitDay;
import cc.messcat.gjfeng.entity.GjfBenefitHistory;
import cc.messcat.gjfeng.entity.GjfBenefitInfo;
import cc.messcat.gjfeng.entity.GjfBenefitPool;
import cc.messcat.gjfeng.entity.GjfErrorInfo;
import cc.messcat.gjfeng.entity.GjfMemberInfo;
import cc.messcat.gjfeng.entity.GjfMemberTrade;
import cc.messcat.gjfeng.entity.GjfMessageHistory;
import cc.messcat.gjfeng.entity.GjfStoreInfo;
import cc.messcat.gjfeng.entity.GjfTotalReport;

@Service("countInfoService")
public class CountInfoServiceImpl implements CountInfoService {

	@Autowired
	@Qualifier("countInfoDao")
	public CountInfoDao countInfoDao;

	/*
	 * 统计会员数量
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.count.CountInfoService#countMemberInfoAmount()
	 */
	@Override
	public ResultVo findMemberAmount(int pageNo, int pageSize) {
		return new ResultVo(200, "查询成功", countInfoDao.findMemberAmount(pageNo, pageSize));
	}

	/*
	 * 根据会员类型统计会员数量
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.count.CountInfoService#findRegularMemberAmount(
	 * java.lang.String)
	 */
	@Override
	public ResultVo findMemberAmountByType(String type) {
		return new ResultVo(200, "查询成功", countInfoDao.findMemberAmountByType(type));
	}

	/*
	 * 统计会员下级消费额度
	 * 
	 * @see cc.messcat.gjfeng.service.count.CountInfoService#
	 * findMembersLowLevelConsume(int, int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ResultVo findMembersLowLevelConsume(int pageNo, int pageSize) {
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("status", "1");
		attrs.put("isDel", "1");
		// 会员集合
		List<GjfMemberInfo> gjfMemberInfos = countInfoDao.queryList(GjfMemberInfo.class, (pageNo - 1) * pageSize,
				pageSize, "addTime", "desc", attrs);
		// 统计每个会员的下级消费额度
		for (GjfMemberInfo gjfMemberInfo : gjfMemberInfos) {
			List<GjfMemberInfo> result = this.findLower(gjfMemberInfo.getId());
			BigDecimal lowLevelConsume = new BigDecimal("0.00");
			if (null != result) {
				for (GjfMemberInfo gjfMemberInfo2 : result) {
					lowLevelConsume = lowLevelConsume.add(gjfMemberInfo2.getCumulativeMoney());
				}
			}
			gjfMemberInfo.setLowLevelCumulativeMoney(lowLevelConsume);
		}
		Pager pager = new Pager(pageSize, pageNo,
				Integer.parseInt(String.valueOf(countInfoDao.queryTotalRecord(GjfMemberInfo.class, attrs))),
				gjfMemberInfos);
		return new ResultVo(200, "查询成功", pager);
	}

	@SuppressWarnings("unchecked")
	private List<GjfMemberInfo> findLower(Long id) {
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("superId", id);
		List<GjfMemberInfo> list = countInfoDao.queryList(GjfMemberInfo.class, "addTime", "desc", attrs);
		return list;
	}

	/*
	 * 查询资金池
	 * 
	 * @see cc.messcat.gjfeng.service.count.CountInfoService#findBenefitPool()
	 */
	@Override
	public ResultVo findBenefitPool() {
		Map<String, Object> attrs = new HashMap<String, Object>();
		return new ResultVo(200, "查询成功", countInfoDao.query(GjfBenefitPool.class, attrs));
	}

	/*
	 * 查询资金池分红记录(GjfBenefitHistory)
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.count.CountInfoService#findBenefitHistory(int,
	 * int)
	 */
	@Override
	public ResultVo findBenefitHistory(int pageNo, int pageSize) {
		Pager pager = new Pager(pageSize, pageNo,
				Integer.parseInt(String.valueOf(countInfoDao.queryTotalRecord(GjfBenefitHistory.class, null))),
				countInfoDao.queryList(GjfBenefitHistory.class, (pageNo - 1) * pageSize, pageSize, "addTime", "desc",
						null));
		return new ResultVo(200, "查询成功", pager);
	}

	/*
	 * 查询分红数据
	 * 
	 * @see cc.messcat.gjfeng.service.count.CountInfoService#findDidiData()
	 */
	@Override
	public ResultVo findDiviData() throws ParseException {
		Map<String, BigDecimal> data = new HashMap<String, BigDecimal>();
		data.put("todayO2OTurnovers", countInfoDao.findTodayO2OTurnover());// 今日O2O交易额
		data.put("todayStoreTurnovers", countInfoDao.findTodayStoreTurnover());// 今日网上商城交易额
		data.put("unusedCreditLine", countInfoDao.findSellerCreditLine());// 商户未使用授信额
		data.put("creditLine", countInfoDao.findCreditLineRecharge());// 今天授信充值额
		data.put("BalanceToal", countInfoDao.findMemberBalanceMoney(""));// 总剩余余额
		data.put("memberDiviTotal", countInfoDao.findMemberDiviTotal());// 会员分红权总额
		data.put("sellerDiviTotal", countInfoDao.findStoreDiviTotal());// 商户分红权总额
		data.put("memberDiviOnTime", countInfoDao.findMemberDiviUnitPrice()); // 会员实时分红
		data.put("sellerDiviOnTime", countInfoDao.findSellerDiviUnitPrice()); // 商户实时分红
		data.put("memberPoolOnTime", countInfoDao.findMemberPoolMoneyByTime());// 今日会员池入池金额
		data.put("merchantPoolOnTime", countInfoDao.fingMerchantPoolMoneyByTime());// 今日商户池入池金额
		data.put("memberDiviNum", countInfoDao.findMemberDiviNum());// 会员实时参与分红权数
		data.put("merchantDiviNum", countInfoDao.findMerchantDiviNum());// 商户实时参与分红权数
		return new ResultVo(200, "查询成功", data);
	}

	/*
	 * 查找短信记录
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.count.CountInfoService#findMessageHistory(int,
	 * int)
	 */
	@Override
	public ResultVo findMessageHistory(int pageNo, int pageSize) {
		Pager pager = new Pager(pageSize, pageNo,
				Integer.parseInt(String.valueOf(countInfoDao.queryTotalRecord(GjfMessageHistory.class, null))),
				countInfoDao.queryList(GjfMessageHistory.class, (pageNo - 1) * pageSize, pageSize, "sendTime", "desc",
						null));
		return new ResultVo(200, "查询成功", pager);
	}

	/*
	 * 查询近30天网上商城交易额
	 * 
	 * @see cc.messcat.gjfeng.service.count.CountInfoService#
	 * findAlmostOneMonthStoreTurnover()
	 */
	@Override
	public ResultVo findAlmostOneMonthStoreTurnover() {
		return new ResultVo(200, "查询成功", countInfoDao.findAlmostOneMonthStoreTurnover());
	}

	/*
	 * 查询近30天O2O交易额
	 * 
	 * @see cc.messcat.gjfeng.service.count.CountInfoService#
	 * findAlmostOneMonth020Turnover()
	 */
	@Override
	public ResultVo findAlmostOneMonth020Turnover() {
		return new ResultVo(200, "查询成功", countInfoDao.findAlmostOneMonth020Turnover());
	}

	/*
	 * 查询近30天会员增加数量和趋势
	 * 
	 * @see cc.messcat.gjfeng.service.count.CountInfoService#
	 * findAlmostOneMonthMemberAdd()
	 */
	@Override
	public ResultVo findAlmostOneMonthMemberAdd(String type) {
		return new ResultVo(200, "查询成功", countInfoDao.findAlmostOneMonthMemberAdd(type));
	}

	/*
	 * 查询近三个月会员的消费额（趋势）
	 * 
	 * @see cc.messcat.gjfeng.service.count.CountInfoService#
	 * findAlmostThreeMonthMemberTurnover(java.lang.Long)
	 */
	@Override
	public ResultVo findAlmostThreeMonthMemberTurnover(Long id) {
		if (ObjValid.isNotValid(id)) {
			return new ResultVo(400, "参数错误", null);
		}
		return new ResultVo(200, "查询成功", countInfoDao.findAlmostThreeMonthMemberTurnover(id));
	}

	/*
	 * 统计资金池收入支出
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.count.CountInfoService#findCashPool(java.lang.
	 * String)
	 */
	@Override
	public ResultVo findCashPool() {
		Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
		// 会员池
		map.put("8", countInfoDao.findCashPool("8"));
		map.put("0", countInfoDao.findCashPool("0"));
		// 商户池
		map.put("9", countInfoDao.findCashPool("9"));
		map.put("1", countInfoDao.findCashPool("1"));
		// 市代池
		map.put("13", countInfoDao.findCashPool("13"));
		map.put("5", countInfoDao.findCashPool("5"));
		// 区代池
		map.put("14", countInfoDao.findCashPool("14"));
		map.put("6", countInfoDao.findCashPool("6"));
		// 个代池
		map.put("15", countInfoDao.findCashPool("15"));
		map.put("7", countInfoDao.findCashPool("7"));
		// 平台池
		map.put("10", countInfoDao.findCashPool("10"));
		map.put("16", countInfoDao.findCashPool("16"));
		return new ResultVo(200, "查询成功", map);
	}

	/*
	 * 查询总报表
	 * 
	 * @see cc.messcat.gjfeng.service.count.CountInfoService#findSummaryReport()
	 */
	@Override
	public ResultVo findSummaryReport(int pageNo, int pageSize, String addTime, String endTime, String ste) {
		return new ResultVo(200, "查询成功", countInfoDao.findSummaryReport(pageNo, pageSize, addTime, endTime, ste));
	}

	/*
	 * 查询让利日报表
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.count.CountInfoService#findBenefitReport(int,
	 * int)
	 */
	@Override
	public ResultVo findBenefitReport(int pageNo, int pageSize, String addTime, String endTime, String ste) {
		return new ResultVo(200, "查询成功", countInfoDao.findBenefitReport(pageNo, pageSize, addTime, endTime, ste));
	}

	/*
	 * 福利产出日报表
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.count.CountInfoService#findWealOutputReport(
	 * int, int)
	 */
	@Override
	public ResultVo findWealOutputReport(int pageNo, int pageSize, String addTime, String endTime, String ste) {
		return new ResultVo(200, "查询成功", countInfoDao.findWealOutputReport(pageNo, pageSize, addTime, endTime, ste));
	}

	/*
	 * 福利派发报表
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.count.CountInfoService#findWealPayoutReport(
	 * int, int)
	 */
	@Override
	public ResultVo findWealPayoutReport(int pageNo, int pageSize, String addTime, String endTime, String ste) {
		return new ResultVo(200, "查询成功", countInfoDao.findWealPayoutReport(pageNo, pageSize, addTime, endTime, ste));
	}

	/*
	 * 提现日报表
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.count.CountInfoService#findWithdrawReport(int,
	 * int)
	 */
	@Override
	public ResultVo findWithdrawReport(int pageNo, int pageSize, String addTime, String endTime, String ste) {
		return new ResultVo(200, "查询成功", countInfoDao.findWithdrawReport(pageNo, pageSize, addTime, endTime, ste));
	}

	/*
	 * 查询订单累计成交额 * @see
	 * cc.messcat.gjfeng.service.count.CountInfoService#findOrderTotalAmount()
	 */
	@Override
	public ResultVo findOrderTotalAmount() {
		return new ResultVo(200, "查询成功", countInfoDao.findOrderTotalAmount());
	}

	/*
	 * 查询实时分红余额
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.count.CountInfoService#findOntimeDiviAmount(
	 * java.lang.String)
	 */
	@Override
	public ResultVo findOntimeDiviAmount(String type) {
		if (ObjValid.isNotValid(type)) {
			throw new MyException(400, "参数错误", null);
		}
		return new ResultVo(200, "查询成功", countInfoDao.findDiviMoneyBla(type));
	}

	/***** 每天定时统计 ********/
	/*
	 * 统计总报表
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.count.CountInfoService#updateSummaryReport()
	 */
	@Override
	public ResultVo updateSummaryReport() {
		return countInfoDao.updateSummaryReport();
	}

	/*
	 * 统计让利日报表
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.count.CountInfoService#updateBenefitReport()
	 */
	@Override
	public ResultVo updateBenefitReport() {
		return countInfoDao.updateBenefitReport();
	}

	/*
	 * 统计福利产出日报表
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.count.CountInfoService#updateWealOutputReport()
	 */
	@Override
	public ResultVo updateWealOutputReport() {
		return countInfoDao.updateWealOutputReport();
	}

	/*
	 * 统计福利派发报表
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.count.CountInfoService#updateWealPayoutReport()
	 */
	@Override
	public ResultVo updateWealPayoutReport() {
		return countInfoDao.updateWealPayoutReport();
	}

	/*
	 * 统计提现日报表
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.count.CountInfoService#updateWithdrawReport()
	 */
	@Override
	public ResultVo updateWithdrawReport() {
		return countInfoDao.updateWithdrawReport();
	}

	/*
	 * 统计资金池表
	 * 
	 * @see cc.messcat.gjfeng.service.count.CountInfoService#updatePoolReport()
	 */
	public ResultVo updatePoolReport() {
		return countInfoDao.updatePoolReport();
	}

	/*
	 * 修改总报表和福利派发报表的福利派发为实际派发额
	 * 
	 * @see cc.messcat.gjfeng.service.count.CountInfoService#
	 * updateSummaryAndPayoutReport()
	 */
	@Override
	public ResultVo updateSummaryAndPayoutReport() {
		return countInfoDao.updateSummaryAndPayoutReport();
	}

	/*
	 * 查询授信充值额
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.count.CountInfoService#findCreditLineRecharge()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ResultVo findCreditLineRecharge(int pageNo, int pageSize) throws ParseException {
		Map<String, Object> attrs = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("todayCreditLineRecharge", countInfoDao.findCreditLineRecharge());// 当日授信充值额
		result.put("totalCreditLineRecharge", countInfoDao.findCreditLineRechargeTotal()); // 查询总授信充值额度
		result.put("monthCreditLineRecharge", countInfoDao.findAlmostOneMonthCLRecharge()); // 近30天授信充值额度情况
		attrs.put("tradeType", "0");
		Pager pager = new Pager(pageSize, pageNo,
				Integer.parseInt(String.valueOf(countInfoDao.queryTotalRecord(GjfMemberTrade.class, attrs))),
				countInfoDao.queryList(GjfMemberTrade.class, (pageNo - 1) * pageSize, pageSize, "addTime", "desc",
						attrs));
		result.put("pager", pager);
		return new ResultVo(200, "查询成功", result);
	}

	/*
	 * 查询商户未使用授信充值额 分页 模糊查询
	 */
	@Override
	public ResultVo findStoreCreditLine(String storeNum, String storeName, String memeberName, int pageNo, int pageSize,
			String orderType) {
		return new ResultVo(200, "查询成功",
				countInfoDao.findStoreCreditLine(storeNum, storeName, memeberName, pageNo, pageSize, orderType));
	}

	/*
	 * 查询错误信息
	 * 
	 * @see cc.messcat.gjfeng.service.count.CountInfoService#findErrorMsg(int,
	 * int)
	 */
	@Override
	public ResultVo findErrorMsg(int pageNo, int pageSize) {
		Pager pager = new Pager(pageSize, pageNo,
				Integer.parseInt(String.valueOf(countInfoDao.queryTotalRecord(GjfErrorInfo.class, null))),
				countInfoDao.queryList(GjfErrorInfo.class, (pageNo - 1) * pageSize, pageSize, "errTime", "desc", null));
		return new ResultVo(200, "查询成功", pager);
	}

	/*
	 * 查询区域业绩
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.count.CountInfoService#findRegionalPerformance(
	 * int, int, java.lang.Long, java.lang.Long, java.lang.Long)
	 */
	@Override
	public ResultVo findRegionalPerformance(int pageNo, int pageSize, Long provinceId, Long cityId, Long areaId,
			String startTime, String endTime) {
		return new ResultVo(200, "查询成功",
				countInfoDao.findRegionalPerformance(pageNo, pageSize, provinceId, cityId, areaId, startTime, endTime));
	}

	/*
	 * 统计当前区域业绩
	 * 
	 * @see cc.messcat.gjfeng.service.count.CountInfoService#
	 * findCountRegionalPerformance(java.lang.Long, java.lang.Long,
	 * java.lang.Long, java.lang.String, java.lang.String)
	 */
	@Override
	public ResultVo findCountRegionalPerformance(Long provinceId, Long cityId, Long areaId, String startTime,
			String endTime) {
		return new ResultVo(200, "查询成功",
				countInfoDao.findCountRegionalPerformance(provinceId, cityId, areaId, startTime, endTime));
	}

	/*
	 * 分红趋势
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.count.CountInfoService#AlmostOneMonthBenefitDay
	 * (java.lang.String)
	 */
	@Override
	public ResultVo findAlmostOneMonthBenefitDay(String type) {
		return new ResultVo(200, "查询成功", countInfoDao.findAlmostOneMonthBenefitDay(type));
	}

	/*
	 * 今日O2O订单
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.count.CountInfoService#findTodayO2OOrders(int,
	 * int, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public ResultVo findTodayO2OOrders(int pageNo, int pageSize) {
		return new ResultVo(200, "查询成功", countInfoDao.findTodayO2OOrders(pageNo, pageSize));
	}

	/*
	 * 查询池收入
	 * 
	 * @see cc.messcat.gjfeng.service.count.CountInfoService#findPoolIn(int,
	 * int, java.lang.String, java.lang.String)
	 */
	@Override
	public ResultVo findPoolIn(int pageNo, int pageSize, String op, String type) {
		return new ResultVo(200, "查询成功", countInfoDao.findPoolIn(pageNo, pageSize, op, type));
	}

	/*
	 * 查询池支出
	 * 
	 * @see cc.messcat.gjfeng.service.count.CountInfoService#findPoolOut(int,
	 * int, java.lang.String, java.lang.String)
	 */
	@Override
	public ResultVo findPoolOut(int pageNo, int pageSize, String op, String type) {
		return new ResultVo(200, "查询成功", countInfoDao.findPoolOut(pageNo, pageSize, op, type));
	}

	/*
	 * 查询池收入支出明细
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.count.CountInfoService#findPoolInDetail(int,
	 * int, java.lang.String, java.lang.String)
	 */
	@Override
	public ResultVo findPoolDetail(int pageNo, int pageSize, String addTime, String type) {
		return new ResultVo(200, "查询成功", countInfoDao.findPoolDetail(pageNo, pageSize, addTime, type));
	}

	/*
	 * 查询代理分红支出明细
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.count.CountInfoService#findTradeDiviByAgent(
	 * int, int, java.lang.String, java.lang.String)
	 */
	@Override
	public ResultVo findTradeDiviByAgent(int pageNo, int pageSize, String addTime, String type) {
		return new ResultVo(200, "查询成功", countInfoDao.findTradeDiviByAgent(pageNo, pageSize, addTime, type));
	}

	/**
	 * 统计每天的交易数据
	 */
	@Override
	public ResultVo countDataByTime(int pageNo, int pageSize, String bigenTime, String endTime) {
		return new ResultVo(200, "查询成功", countInfoDao.findTotalReport(pageNo, pageSize, bigenTime, endTime));
	}

	/**
	 * 统计总额
	 */

	@SuppressWarnings("rawtypes")
	@Override
	public ResultVo totalDataInfo(String beginTime, String endTime) {
		Map map = countInfoDao.countTotalDataInfo(beginTime, endTime);

		return new ResultVo(200, "查询成功", map);
	}

	/**
	 * 添加交易记录
	 */
	@Override
	public ResultVo addAccountDataByTime(Integer pageNo, Integer pageSize, String beginTime, String endTime) {
		Map<String, Object> attrs = new HashMap<>();
		attrs.put("tradeStatus", "1");
		// 查询让利每天让利信息
		/*
		 * Pager pager = new Pager(pageSize, pageNo,
		 * Integer.parseInt(String.valueOf(countInfoDao.queryTotalRecord(
		 * GjfBenefitDay.class,attrs))),
		 * countInfoDao.queryList(GjfBenefitDay.class,(pageNo-1)*pageSize,
		 * pageSize, "addTime", "desc",attrs));
		 */
		Pager pager = countInfoDao.findBenefitDay(pageNo, pageSize, beginTime, endTime, "");
		List<GjfBenefitDay> benList = pager.getResultList();

		// 记录上一次剩余金额
		BigDecimal recoedRemMoney = new BigDecimal(0.00);
		/*
		 * //记录上次可参与分红权数 BigDecimal recoedMemberDiv=new BigDecimal(0.00);
		 * //记录上一次商户可以参与分红权 BigDecimal recoedMechDiv=new BigDecimal(0.00);
		 */
		// 记录上一次会员余额剩余
		BigDecimal shenYuMoney = new BigDecimal(0.00);
		// 迭代让利基本信息
		for (int i = 0; i < benList.size(); i++) {
			// 创建保存数据对象
			GjfTotalReport totalReport = new GjfTotalReport();

			// 保存基本信息集合
			Map<String, Object> map = new HashMap<>();

			// 获取让利信息
			GjfBenefitDay benefit = benList.get(i);
			// 保存每天让利信息
			map.put("benefitDay", benefit);
			// 统计每天的交易信息
			Map<String, BigDecimal> map0 = countInfoDao.countBenefitByTime(benefit.getTradeDay());
			map.put("info", map0);
			// 交易额
			totalReport.setBenerfitTradeMoney(map0.get("sumBeTradeMoney"));
			// 日营业额
			BigDecimal sumBeDateMoney = map0.get("sumBeWpay").add(map0.get("sumBeHpay").add(map0.get("sumBeSpay")))
					.add(map0.get("sumZhiFuBaoHpay"));
			totalReport.setBenerfitDateMoney(sumBeDateMoney);
			// 销售录入微信支付金额
			totalReport.setBenerfitWeipayMoney(map0.get("sumBeWpay"));
			// 销售录入银联支付金额
			totalReport.setBenerfitYinlMoney(map0.get("sumBeHpay"));
			// 销售录入授信支付金额
			totalReport.setBenerfitSxMoney(map0.get("sumBeSpay"));
			// 销售录入支付宝支付金额
			totalReport.setBenerfitZhibaoMoney(map0.get("sumZhiFuBaoHpay"));
			// 商城消费微信支付金额
			totalReport.setMallWeipayMoney(map0.get("sumOrderWpay"));
			// 商城消费银联支付金额
			totalReport.setMallYinlpayMoney(map0.get("sumOrderHpay"));
			// 商城消费责任消费金额
			totalReport.setMallZerenpayMoney(map0.get("sumOrderDpay"));
			// 商城消费余额
			totalReport.setMallBanlanMoney(map0.get("sumOrderYpay"));
			// 商城消费支付宝
			totalReport.setMallZhibaoMoney(map0.get("sumOrderZhiFuBaopay"));
			// 授信充值充值额度
			BigDecimal sumChEDMoney = map0.get("sumSXWpay").divide(new BigDecimal(0.12), 2, BigDecimal.ROUND_DOWN)
					.add(map0.get("sumSXHpay").divide(new BigDecimal(0.12), 2, BigDecimal.ROUND_UP))
					.add(map0.get("sumSXHTpay").divide(new BigDecimal(0.12), 2, BigDecimal.ROUND_UP));
			totalReport.setShouxinLinesMoney(sumChEDMoney);
			// 授信充值微信支付
			totalReport.setShouxinWeixinpayMoney(map0.get("sumSXWpay"));
			// 授信充值银联支付
			totalReport.setShouxinYinlPayMoney(map0.get("sumSXHpay"));
			// 授信充值后台充值
			totalReport.setShouxinHoutaipayMoney(map0.get("sumSXHTpay"));
			// 授信充值支付宝充值
			totalReport.setShouxinZhibaoMoney(map0.get("sumZhifuBaopay"));
			// 授信充值消耗额度
			BigDecimal sumConQuotaMoney = map0.get("sumBeSpay").divide(new BigDecimal(0.12), 2, BigDecimal.ROUND_UP);
			totalReport.setShouxinConsumptionMoney(sumConQuotaMoney);
			// 授信充值剩余
			if (i == 0) {
				recoedRemMoney = sumChEDMoney.subtract(sumConQuotaMoney);
				totalReport.setShouxinShenyuMoney(sumChEDMoney.subtract(sumConQuotaMoney));
			} else {
				totalReport.setShouxinShenyuMoney(recoedRemMoney.add(sumChEDMoney).subtract(sumConQuotaMoney));
				recoedRemMoney = recoedRemMoney.add(sumChEDMoney).subtract(sumConQuotaMoney);
			}
			// 微信支付总金额
			BigDecimal sumTotalWpay = map0.get("sumBeWpay").add(map0.get("sumSXWpay").add(map0.get("sumOrderWpay")));
			totalReport.setTotalWeipayMoney(sumTotalWpay);
			// 支付总额微信支付手续费
			totalReport.setTotalWeixShouxupayMoney(new BigDecimal(0));
			// 支付总额微信支付到账金额
			totalReport.setTotalWeiXinGetMoney(sumTotalWpay);
			// 网银支付总金额
			BigDecimal sumTotalHpay = map0.get("sumBeHpay").add(map0.get("sumOrderHpay")).add(map0.get("sumSXHpay"));
			totalReport.setTotalYinlpayMoney(sumTotalHpay);
			// 支付总额网银支付手续费
			totalReport.setTotalYinlShouxuMoney(new BigDecimal(0));
			// 支付总额网银支付到账金额
			totalReport.setTotalYinlGetMoney(sumTotalHpay);

			// 支付宝支付总金额
			BigDecimal sumTotalZhibaoPay = map0.get("sumZhiFuBaoHpay").add(map0.get("sumOrderZhiFuBaopay"))
					.add(map0.get("sumZhifuBaopay"));
			totalReport.setTotalZhifubaoMoney(sumTotalZhibaoPay);
			// 支付宝支付到账金额
			totalReport.setTotalZhifubaoGetMoney(sumTotalZhibaoPay);
			// 支付宝支付手续费
			totalReport.setTotalZhifubaoShouxupayMoney(new BigDecimal(0));
			// 会员新增分红权
			if (i == benList.size() - 1) {
				// recoedMemberDiv=benefit.getMemberDiviNum();
				totalReport.setMemberAddDivi(new BigDecimal(0));
			} else {
				// map.put("sumNewDiv",
				// recoedMemberDiv.subtract(benefit.getMemberDiviNum()));
				totalReport.setMemberAddDivi(
						benList.get(i).getMemberDiviNum().subtract(benList.get(i + 1).getMemberDiviNum()));
				// recoedMemberDiv=benefit.getMemberDiviNum();
			}
			// 会员可参与分红权数
			totalReport.setMemberCanCanYu_Divi(benefit.getMemberDiviNum());
			// 会员单价
			totalReport.setMemberDanJia(benefit.getMemberUnitMoney());
			// 会员实际发放金额
			totalReport.setMemberShiJiPayMoney(benefit.getMemberRealMoney());
			// 会员池收入
			totalReport.setMemberPoolMoney(benefit.getMemberIncomeMoney());
			// 会员差异
			BigDecimal sumMemDiff = benefit.getMemberIncomeMoney().subtract(benefit.getMemberRealMoney());
			totalReport.setMemberChaYiMoney(sumMemDiff);

			// 商户新增分红权
			if (i == benList.size() - 1) {
				// recoedMechDiv=benefit.getMerchantsDiviNum();
				totalReport.setMerchantsAddDivi(new BigDecimal(0));
			} else {

				// map.put("sumNewMerchDiv",
				// recoedMechDiv.subtract(benefit.getMerchantsDiviNum()));
				totalReport.setMerchantsAddDivi(
						benList.get(i).getMerchantsDiviNum().subtract(benList.get(i + 1).getMerchantsDiviNum()));
				// recoedMechDiv=benefit.getMerchantsDiviNum();
			}
			// 商户可参与分红权数
			totalReport.setMerchantsCanCanyuDivi(benefit.getMerchantsDiviNum());
			// 商户单价
			totalReport.setMerchantsDanjia(benefit.getMerchantsUnitMoney());
			// 商户实际发放金额
			totalReport.setMerchantsShijipayMoney(benefit.getMerchantsRealMoney());
			// 商户池收入
			totalReport.setMerchantsPoolMoney(benefit.getMerchantsIncomeMoney());
			// 商户差异
			BigDecimal sumMerchDiff = benefit.getMerchantsIncomeMoney().subtract(benefit.getMerchantsRealMoney());
			totalReport.setMerchantsChayiMoney(sumMerchDiff);

			// 直推奖励直推会员
			totalReport.setZhituiMemberMoney(map0.get("directMeMoney"));
			// 直推奖励直商家
			totalReport.setZhituiMerchantsMoney(map0.get("directChMoney"));

			// 总余额日发放额
			BigDecimal sumDateIssueMoney = benefit.getMemberRealMoney().add(benefit.getMerchantsRealMoney())
					.add(map0.get("directChMoney")).add(map0.get("directChMoney"));
			totalReport.setTotalBanlanDateMoney(sumDateIssueMoney);

			// 商户会员池
			BigDecimal sumMemMerPool = sumMemDiff.add(benefit.getMerchantsIncomeMoney());
			totalReport.setTotalBanlanPoolMoney(sumMemMerPool);
			// 直推奖励总额
			BigDecimal sumDirectMoney = map0.get("directMeMoney").add(map0.get("directChMoney"));
			totalReport.setTotalBanlanZhituiMoney(sumDirectMoney);
			// 总余额差异
			totalReport.setTotalBanlanChayiMoney(sumMemMerPool.add(sumMemMerPool).subtract(sumDateIssueMoney));
			// 提现金额
			totalReport.setWithdrawalMoney(map0.get("tradeMoney").divide(new BigDecimal(0.8), 2, BigDecimal.ROUND_UP));
			// 提现税收
			totalReport.setWithdrawalShuishouMoney(map0.get("taxMoney"));
			// 提现责任消费
			totalReport.setWithdrawalZenrenMoney(map0.get("insMoney"));
			// 提现到账金额
			totalReport.setWithdrawalDaozMoney(map0.get("tradeMoney"));
			// 提现手续费
			totalReport.setWithdrawalShouxuMoney(new BigDecimal(0));

			// 会员余额发放额
			totalReport.setMemberBanlanFafangMoney(sumDateIssueMoney);

			// 会员余额提现划出
			totalReport.setMemberBanlanHuaChuMoney(map0.get("tradeMoney"));
			// 会员余额消费
			totalReport.setMemberBanlanXianFeiMoney(map0.get("sumOrderYpay"));
			// 会员余额剩余
			if (i == 0) {
				shenYuMoney = sumDateIssueMoney.subtract(map0.get("tradeMoney").subtract(map0.get("sumOrderYpay")));
				totalReport.setMemberBanlanShenyuMoney(shenYuMoney);

			} else {
				totalReport.setMemberBanlanShenyuMoney(shenYuMoney.add(sumDateIssueMoney)
						.subtract(map0.get("tradeMoney").subtract(map0.get("sumOrderYpay"))));
				// map.put("sumShenYuMoney",
				// shenYuMoney.add(sumDateIssueMoney).subtract(map0.get("tradeMoney").subtract(map0.get("sumOrderYpay"))));
				shenYuMoney = shenYuMoney.add(sumDateIssueMoney)
						.subtract(map0.get("tradeMoney").subtract(map0.get("sumOrderYpay")));
			}

			// 责任消费日消费
			totalReport.setZenrenDateMoney(map0.get("insMoney"));
			// 责任消费消耗额
			totalReport.setZenrenXiaoHouMoney(map0.get("sumOrderDpay"));
			// 剩余额
			totalReport.setZenrenShenyuMoney(map0.get("insMoney").subtract(map0.get("sumOrderDpay")));
			// 会员人数
			totalReport.setMemberNumber(map0.get("newMember").intValue());
			// 商家数量
			totalReport.setMerchantsNumber(map0.get("newMc").intValue());
			// 代理收入个代
			totalReport.setGedaiMoney(map0.get("generationMoney"));
			// 代理收入区代
			totalReport.setEreadaiMoney(map0.get("areaGenerationMoney"));
			// 代理收入市代
			totalReport.setCitydaiMoney(map0.get("cityGenerationMoney"));
			// 会员商户池总营收
			BigDecimal sumTotalYSMoney = sumMemDiff.add(sumMerchDiff);
			totalReport.setMerchantsPoolTotalYiShou(sumTotalYSMoney);

			// 平台池总营收
			BigDecimal sumPTPoolMoney = sumBeDateMoney.multiply(new BigDecimal(0.85)).setScale(2, BigDecimal.ROUND_UP)
					.multiply(new BigDecimal(0.1)).setScale(2, BigDecimal.ROUND_UP)
					.add((sumBeDateMoney.multiply(new BigDecimal(0.15)).setScale(2, BigDecimal.ROUND_UP)
							.multiply(new BigDecimal(0.3).setScale(2, BigDecimal.ROUND_UP))));
			totalReport.setPingTaiTotalYiShou(sumPTPoolMoney);

			// 实际收款
			BigDecimal sumRealSMoney = sumTotalWpay.add(sumTotalHpay);
			totalReport.setShijiShoukuaiMoney(sumRealSMoney);
			// 实际支出
			totalReport.setZhichuDShijiPayMoney(map0.get("tradeMoney"));
			// 预日营收
			BigDecimal sumDateYSMoney = sumRealSMoney
					.subtract((sumDateIssueMoney.multiply(new BigDecimal(0.8)).setScale(2, BigDecimal.ROUND_UP)));
			totalReport.setYishouYuYinShouMoney(sumDateYSMoney);
			// 实际应收
			BigDecimal sumRealNeedMoney = sumDateYSMoney.subtract(map0.get("tradeMoney"));
			totalReport.setYiShouShiJiYiShouMoney(sumRealNeedMoney);

			totalReport.setAddTime(benefit.getActTime());
			countInfoDao.save(totalReport);
		}

		return new ResultVo(200, "查询成功", null);
	}

	/**
	 * 更新统计信息
	 */
	@Override
	public ResultVo updateCountInfo(Long id) {
		Map<String, Object> attr = new HashMap<>();
		attr.put("id", id);
		GjfTotalReport totalReport = countInfoDao.query(GjfTotalReport.class, attr);
		if (!BeanUtil.isValid(totalReport)) {
			return new ResultVo(400, "数据不存在", null);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String queryTime = sdf.format(totalReport.getAddTime()).replace("-", "").trim();
		Map<String, BigDecimal> map0 = countInfoDao.countBenefitByTime(queryTime);

		// 查询让利信息
		attr.remove("id");
		attr.put("tradeDay", queryTime);
		GjfBenefitDay benefit = countInfoDao.query(GjfBenefitDay.class, attr);
		// 交易额
		totalReport.setBenerfitTradeMoney(map0.get("sumBeTradeMoney"));
		// 日营业额
		BigDecimal sumBeDateMoney = map0.get("sumBeWpay").add(map0.get("sumBeHpay").add(map0.get("sumBeSpay")));
		totalReport.setBenerfitDateMoney(sumBeDateMoney);
		// 销售录入微信支付金额
		totalReport.setBenerfitWeipayMoney(map0.get("sumBeWpay"));
		// 销售录入银联支付金额
		totalReport.setBenerfitYinlMoney(map0.get("sumBeHpay"));
		// 销售录入授信支付金额
		totalReport.setBenerfitSxMoney(map0.get("sumBeSpay"));
		// 销售录入支付宝支付
		totalReport.setBenerfitZhibaoMoney(map0.get("sumZhiFuBaoHpay"));
		// 商城消费微信支付金额
		totalReport.setMallWeipayMoney(map0.get("sumOrderWpay"));
		// 商城消费银联支付金额
		totalReport.setMallYinlpayMoney(map0.get("sumOrderHpay"));
		// 商城消费责任消费金额
		totalReport.setMallZerenpayMoney(map0.get("sumOrderDpay"));
		// 商城消费余额
		totalReport.setMallBanlanMoney(map0.get("sumOrderYpay"));
		// 商城消费支付宝支付金额
		totalReport.setMallZhibaoMoney(map0.get("sumOrderZhiFuBaopay"));
		// 授信充值充值额度
		BigDecimal sumChEDMoney = map0.get("sumSXWpay").divide(new BigDecimal(0.12), 2, BigDecimal.ROUND_DOWN)
				.add(map0.get("sumSXHpay").divide(new BigDecimal(0.12), 2, BigDecimal.ROUND_UP))
				.add(map0.get("sumSXHTpay").divide(new BigDecimal(0.12), 2, BigDecimal.ROUND_UP));
		totalReport.setShouxinLinesMoney(sumChEDMoney);
		// 授信充值微信支付
		totalReport.setShouxinWeixinpayMoney(map0.get("sumSXWpay"));
		// 授信充值银联支付
		totalReport.setShouxinYinlPayMoney(map0.get("sumSXHpay"));
		// 授信充值后台充值
		totalReport.setShouxinHoutaipayMoney(map0.get("sumSXHTpay"));
		// 授信充值支付宝支付
		totalReport.setShouxinZhibaoMoney(map0.get("sumZhifuBaopay"));
		// 授信充值消耗额度
		BigDecimal sumConQuotaMoney = map0.get("sumBeSpay").divide(new BigDecimal(0.12), 2, BigDecimal.ROUND_UP);
		totalReport.setShouxinConsumptionMoney(sumConQuotaMoney);

		// 微信支付总金额
		BigDecimal sumTotalWpay = map0.get("sumBeWpay").add(map0.get("sumSXWpay").add(map0.get("sumOrderWpay")));
		totalReport.setTotalWeipayMoney(sumTotalWpay);
		// 支付总额微信支付手续费
		totalReport.setTotalWeixShouxupayMoney(new BigDecimal(0));
		// 支付总额微信支付到账金额
		totalReport.setTotalWeiXinGetMoney(sumTotalWpay);
		// 网银支付总金额
		BigDecimal sumTotalHpay = map0.get("sumBeHpay").add(map0.get("sumOrderHpay")).add(map0.get("sumSXHpay"));
		totalReport.setTotalYinlpayMoney(sumTotalHpay);
		// 支付总额网银支付手续费
		totalReport.setTotalYinlShouxuMoney(new BigDecimal(0));
		// 支付总额网银支付到账金额
		totalReport.setTotalYinlGetMoney(sumTotalHpay);

		// 支付宝支付总金额
		BigDecimal sumTotalZhibaoPay = map0.get("sumZhiFuBaoHpay").add(map0.get("sumOrderZhiFuBaopay"))
				.add(map0.get("sumZhifuBaopay"));
		totalReport.setTotalZhifubaoMoney(sumTotalZhibaoPay);
		// 支付宝支付到账金额
		totalReport.setTotalZhifubaoGetMoney(sumTotalZhibaoPay);
		// 支付宝支付手续费
		totalReport.setTotalZhifubaoShouxupayMoney(new BigDecimal(0));

		// 会员新增分红权
		/*
		 * if(i==benList.size()-1){
		 * //recoedMemberDiv=benefit.getMemberDiviNum();
		 * totalReport.setMemberAddDivi(new BigDecimal(0)); }else{
		 * //map.put("sumNewDiv",
		 * recoedMemberDiv.subtract(benefit.getMemberDiviNum()));
		 * totalReport.setMemberAddDivi(benList.get(i).getMemberDiviNum().
		 * subtract(benList.get(i+1).getMemberDiviNum()));
		 * //recoedMemberDiv=benefit.getMemberDiviNum(); }
		 */
		// 会员可参与分红权数
		totalReport.setMemberCanCanYu_Divi(benefit.getMemberDiviNum());
		// 会员单价
		totalReport.setMemberDanJia(benefit.getMemberUnitMoney());
		// 会员实际发放金额
		totalReport.setMemberShiJiPayMoney(benefit.getMemberRealMoney());
		// 会员池收入
		totalReport.setMemberPoolMoney(benefit.getMemberIncomeMoney());
		// 会员差异
		BigDecimal sumMemDiff = benefit.getMemberIncomeMoney().subtract(benefit.getMemberRealMoney());
		totalReport.setMemberChaYiMoney(sumMemDiff);

		// 商户新增分红权
		/*
		 * if(i==benList.size()-1){
		 * //recoedMechDiv=benefit.getMerchantsDiviNum();
		 * totalReport.setMerchantsAddDivi(new BigDecimal(0)); }else{
		 * 
		 * //map.put("sumNewMerchDiv",
		 * recoedMechDiv.subtract(benefit.getMerchantsDiviNum()));
		 * totalReport.setMerchantsAddDivi(benList.get(i).getMerchantsDiviNum().
		 * subtract(benList.get(i+1).getMerchantsDiviNum()));
		 * //recoedMechDiv=benefit.getMerchantsDiviNum(); }
		 */
		// 商户可参与分红权数
		totalReport.setMerchantsCanCanyuDivi(benefit.getMerchantsDiviNum());
		// 商户单价
		totalReport.setMerchantsDanjia(benefit.getMerchantsUnitMoney());
		// 商户实际发放金额
		totalReport.setMerchantsShijipayMoney(benefit.getMerchantsRealMoney());
		// 商户池收入
		totalReport.setMerchantsPoolMoney(benefit.getMerchantsIncomeMoney());
		// 商户差异
		BigDecimal sumMerchDiff = benefit.getMerchantsIncomeMoney().subtract(benefit.getMerchantsRealMoney());
		totalReport.setMerchantsChayiMoney(sumMerchDiff);

		// 直推奖励直推会员
		totalReport.setZhituiMemberMoney(map0.get("directMeMoney"));
		// 直推奖励直商家
		totalReport.setZhituiMerchantsMoney(map0.get("directChMoney"));

		// 总余额日发放额
		BigDecimal sumDateIssueMoney = benefit.getMemberRealMoney().add(benefit.getMerchantsRealMoney())
				.add(map0.get("directChMoney")).add(map0.get("directChMoney"));
		totalReport.setTotalBanlanDateMoney(sumDateIssueMoney);

		// 商户会员池
		BigDecimal sumMemMerPool = sumMemDiff.add(benefit.getMerchantsIncomeMoney());
		totalReport.setTotalBanlanPoolMoney(sumMemMerPool);
		// 直推奖励总额
		BigDecimal sumDirectMoney = map0.get("directMeMoney").add(map0.get("directChMoney"));
		totalReport.setTotalBanlanZhituiMoney(sumDirectMoney);
		// 总余额差异
		totalReport.setTotalBanlanChayiMoney(sumMemMerPool.add(sumMemMerPool).subtract(sumDateIssueMoney));
		// 提现金额
		totalReport.setWithdrawalMoney(map0.get("tradeMoney").divide(new BigDecimal(0.8), 2, BigDecimal.ROUND_UP));
		// 提现税收
		totalReport.setWithdrawalShuishouMoney(map0.get("taxMoney"));
		// 提现责任消费
		totalReport.setWithdrawalZenrenMoney(map0.get("insMoney"));
		// 提现到账金额
		totalReport.setWithdrawalDaozMoney(map0.get("tradeMoney"));
		// 提现手续费
		totalReport.setWithdrawalShouxuMoney(new BigDecimal(0));

		// 会员余额发放额
		totalReport.setMemberBanlanFafangMoney(sumDateIssueMoney);

		// 会员余额提现划出
		totalReport.setMemberBanlanHuaChuMoney(map0.get("tradeMoney"));
		// 会员余额消费
		totalReport.setMemberBanlanXianFeiMoney(map0.get("sumOrderYpay"));
		// 会员余额剩余
		/*
		 * if(i==0){
		 * shenYuMoney=sumDateIssueMoney.subtract(map0.get("tradeMoney").
		 * subtract(map0.get("sumOrderYpay")));
		 * totalReport.setMemberBanlanShenyuMoney(shenYuMoney);
		 * 
		 * }else{ totalReport.setMemberBanlanShenyuMoney(shenYuMoney.add(
		 * sumDateIssueMoney).subtract(map0.get("tradeMoney").subtract(map0.get(
		 * "sumOrderYpay")))); //map.put("sumShenYuMoney",
		 * shenYuMoney.add(sumDateIssueMoney).subtract(map0.get("tradeMoney").
		 * subtract(map0.get("sumOrderYpay"))));
		 * shenYuMoney=shenYuMoney.add(sumDateIssueMoney).subtract(map0.get(
		 * "tradeMoney").subtract(map0.get("sumOrderYpay"))); }
		 */

		// 责任消费日消费
		totalReport.setZenrenDateMoney(map0.get("insMoney"));
		// 责任消费消耗额
		totalReport.setZenrenXiaoHouMoney(map0.get("sumOrderDpay"));
		// 剩余额
		totalReport.setZenrenShenyuMoney(map0.get("insMoney").subtract(map0.get("sumOrderDpay")));
		// 会员人数
		totalReport.setMemberNumber(map0.get("newMember").intValue());
		// 商家数量
		totalReport.setMerchantsNumber(map0.get("newMc").intValue());
		// 代理收入个代
		totalReport.setGedaiMoney(map0.get("generationMoney"));
		// 代理收入区代
		totalReport.setEreadaiMoney(map0.get("areaGenerationMoney"));
		// 代理收入市代
		totalReport.setCitydaiMoney(map0.get("cityGenerationMoney"));
		// 会员商户池总营收
		// BigDecimal sumTotalYSMoney=sumMemDiff.add(sumMerchDiff);
		// totalReport.setMerchantsPoolTotalYiShou(sumTotalYSMoney);

		// 平台池总营收
		BigDecimal sumPTPoolMoney = sumBeDateMoney.multiply(new BigDecimal(0.85)).setScale(2, BigDecimal.ROUND_UP)
				.multiply(new BigDecimal(0.1)).setScale(2, BigDecimal.ROUND_UP)
				.add((sumBeDateMoney.multiply(new BigDecimal(0.15)).setScale(2, BigDecimal.ROUND_UP)
						.multiply(new BigDecimal(0.3).setScale(2, BigDecimal.ROUND_UP))));
		totalReport.setPingTaiTotalYiShou(sumPTPoolMoney);

		// 实际收款
		BigDecimal sumRealSMoney = sumTotalWpay.add(sumTotalHpay);
		totalReport.setShijiShoukuaiMoney(sumRealSMoney);
		// 实际支出
		totalReport.setZhichuDShijiPayMoney(map0.get("tradeMoney"));
		// 预日营收
		BigDecimal sumDateYSMoney = sumRealSMoney
				.subtract((sumDateIssueMoney.multiply(new BigDecimal(0.8)).setScale(2, BigDecimal.ROUND_UP)));
		totalReport.setYishouYuYinShouMoney(sumDateYSMoney);
		// 实际应收
		BigDecimal sumRealNeedMoney = sumDateYSMoney.subtract(map0.get("tradeMoney"));
		totalReport.setYiShouShiJiYiShouMoney(sumRealNeedMoney);

		countInfoDao.update(totalReport);
		return new ResultVo(200, "更新成功", null);
	}

	/**
	 * 每天统计报表
	 * 
	 * @param addTime
	 * @return
	 */
	@Override
	public ResultVo addTotalReportByTime(String addTime) {
		try {
			String time = addTime.replace("-", "").trim();
			GjfTotalReport totalReport = new GjfTotalReport();
			Map<String, BigDecimal> map0 = countInfoDao.countBenefitByTime(time);
			// 查询让利信息
			Map<String, Object> attr = new HashMap<>();
			SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
			attr.put("tradeDay", time);
			GjfBenefitDay benefit = countInfoDao.query(GjfBenefitDay.class, attr);

			String endDate = "";

			Date beginDate = dft.parse(addTime);
			Calendar date = Calendar.getInstance();
			date.setTime(beginDate);
			date.set(Calendar.DATE, date.get(Calendar.DATE) - 1);
			endDate = dft.format(date.getTime());

			attr.remove("tradeDay");
			attr.put("addTime", dft.parse(endDate));
			GjfTotalReport totalReport0 = countInfoDao.query(GjfTotalReport.class, attr);

			// 查询前一天让利信息
			attr.remove("addTime");
			String ben0Time = endDate.replace("-", "").trim();
			attr.put("tradeDay", ben0Time);
			GjfBenefitDay benefit0 = countInfoDao.query(GjfBenefitDay.class, attr);
			// 交易额
			totalReport.setBenerfitTradeMoney(map0.get("sumBeTradeMoney"));
			// 日营业额
			BigDecimal sumBeDateMoney = map0.get("sumBeWpay").add(map0.get("sumBeHpay").add(map0.get("sumBeSpay")));
			totalReport.setBenerfitDateMoney(sumBeDateMoney);
			// 销售录入微信支付金额
			totalReport.setBenerfitWeipayMoney(map0.get("sumBeWpay"));
			// 销售录入银联支付金额
			totalReport.setBenerfitYinlMoney(map0.get("sumBeHpay"));
			// 销售录入授信支付金额
			totalReport.setBenerfitSxMoney(map0.get("sumBeSpay"));
			// 销售录入支付宝支付金额
			totalReport.setBenerfitZhibaoMoney(map0.get("sumZhiFuBaoHpay"));
			// 商城消费微信支付金额
			totalReport.setMallWeipayMoney(map0.get("sumOrderWpay"));
			// 商城消费银联支付金额
			totalReport.setMallYinlpayMoney(map0.get("sumOrderHpay"));
			// 商城消费责任消费金额
			totalReport.setMallZerenpayMoney(map0.get("sumOrderDpay"));
			// 商城消费余额
			totalReport.setMallBanlanMoney(map0.get("sumOrderYpay"));
			// 商城消费支付宝支付
			totalReport.setMallZhibaoMoney(map0.get("sumOrderZhiFuBaopay"));
			// 授信充值充值额度
			BigDecimal sumChEDMoney = map0.get("sumSXWpay").divide(new BigDecimal(0.12), 2, BigDecimal.ROUND_DOWN)
					.add(map0.get("sumSXHpay").divide(new BigDecimal(0.12), 2, BigDecimal.ROUND_UP))
					.add(map0.get("sumSXHTpay").divide(new BigDecimal(0.12), 2, BigDecimal.ROUND_UP));
			totalReport.setShouxinLinesMoney(sumChEDMoney);
			// 授信充值微信支付
			totalReport.setShouxinWeixinpayMoney(map0.get("sumSXWpay"));
			// 授信充值银联支付
			totalReport.setShouxinYinlPayMoney(map0.get("sumSXHpay"));
			// 授信充值后台充值
			totalReport.setShouxinHoutaipayMoney(map0.get("sumSXHTpay"));
			// 授信充值支付宝支付
			totalReport.setShouxinZhibaoMoney(map0.get("sumZhifuBaopay"));
			// 授信充值消耗额度
			BigDecimal sumConQuotaMoney = map0.get("sumBeSpay").divide(new BigDecimal(0.12), 2, BigDecimal.ROUND_UP);
			totalReport.setShouxinConsumptionMoney(sumConQuotaMoney);

			// 授信充值剩余
			if (!BeanUtil.isValid(totalReport0)) {
				totalReport.setShouxinShenyuMoney(sumChEDMoney.subtract(sumConQuotaMoney));
			} else {
				totalReport.setShouxinShenyuMoney(
						totalReport0.getShouxinShenyuMoney().add(sumChEDMoney).subtract(sumConQuotaMoney));
			}

			// 微信支付总金额
			BigDecimal sumTotalWpay = map0.get("sumBeWpay").add(map0.get("sumSXWpay").add(map0.get("sumOrderWpay")));
			totalReport.setTotalWeipayMoney(sumTotalWpay);
			// 支付总额微信支付手续费
			totalReport.setTotalWeixShouxupayMoney(new BigDecimal(0));
			// 支付总额微信支付到账金额
			totalReport.setTotalWeiXinGetMoney(sumTotalWpay);
			// 网银支付总金额
			BigDecimal sumTotalHpay = map0.get("sumBeHpay").add(map0.get("sumOrderHpay")).add(map0.get("sumSXHpay"));
			totalReport.setTotalYinlpayMoney(sumTotalHpay);
			// 支付总额网银支付手续费
			totalReport.setTotalYinlShouxuMoney(new BigDecimal(0));
			// 支付总额网银支付到账金额
			totalReport.setTotalYinlGetMoney(sumTotalHpay);

			// 支付宝支付总金额
			BigDecimal sumTotalZhibaoPay = map0.get("sumZhiFuBaoHpay").add(map0.get("sumOrderZhiFuBaopay"))
					.add(map0.get("sumZhifuBaopay"));
			totalReport.setTotalZhifubaoMoney(sumTotalZhibaoPay);
			// 支付宝支付到账金额
			totalReport.setTotalZhifubaoGetMoney(sumTotalZhibaoPay);
			// 支付宝支付手续费
			totalReport.setTotalZhifubaoShouxupayMoney(new BigDecimal(0));

			// 会员新增分红权
			if (BeanUtil.isValid(benefit0)) {
				totalReport.setMemberAddDivi(benefit.getMemberDiviNum().subtract(benefit0.getMemberDiviNum()));
			} else {
				totalReport.setMemberAddDivi(new BigDecimal(0));
			}

			// 会员可参与分红权数
			totalReport.setMemberCanCanYu_Divi(benefit.getMemberDiviNum());
			// 会员单价
			totalReport.setMemberDanJia(benefit.getMemberUnitMoney());
			// 会员实际发放金额
			totalReport.setMemberShiJiPayMoney(benefit.getMemberRealMoney());
			// 会员池收入
			totalReport.setMemberPoolMoney(benefit.getMemberIncomeMoney());
			// 会员差异
			BigDecimal sumMemDiff = benefit.getMemberIncomeMoney().subtract(benefit.getMemberRealMoney());
			totalReport.setMemberChaYiMoney(sumMemDiff);

			// 商户新增分红权
			if (BeanUtil.isValid(benefit0)) {
				totalReport.setMerchantsAddDivi(benefit.getMerchantsDiviNum().subtract(benefit0.getMerchantsDiviNum()));
			} else {
				totalReport.setMerchantsAddDivi(new BigDecimal(0));
			}

			// 商户可参与分红权数
			totalReport.setMerchantsCanCanyuDivi(benefit.getMerchantsDiviNum());
			// 商户单价
			totalReport.setMerchantsDanjia(benefit.getMerchantsUnitMoney());
			// 商户实际发放金额
			totalReport.setMerchantsShijipayMoney(benefit.getMerchantsRealMoney());
			// 商户池收入
			totalReport.setMerchantsPoolMoney(benefit.getMerchantsIncomeMoney());
			// 商户差异
			BigDecimal sumMerchDiff = benefit.getMerchantsIncomeMoney().subtract(benefit.getMerchantsRealMoney());
			totalReport.setMerchantsChayiMoney(sumMerchDiff);

			// 直推奖励直推会员
			totalReport.setZhituiMemberMoney(map0.get("directMeMoney"));
			// 直推奖励直商家
			totalReport.setZhituiMerchantsMoney(map0.get("directChMoney"));

			// 总余额日发放额
			BigDecimal sumDateIssueMoney = benefit.getMemberRealMoney().add(benefit.getMerchantsRealMoney())
					.add(map0.get("directChMoney")).add(map0.get("directChMoney"));
			totalReport.setTotalBanlanDateMoney(sumDateIssueMoney);

			// 商户会员池
			BigDecimal sumMemMerPool = sumMemDiff.add(benefit.getMerchantsIncomeMoney());
			totalReport.setTotalBanlanPoolMoney(sumMemMerPool);
			// 直推奖励总额
			BigDecimal sumDirectMoney = map0.get("directMeMoney").add(map0.get("directChMoney"));
			totalReport.setTotalBanlanZhituiMoney(sumDirectMoney);
			// 总余额差异
			totalReport.setTotalBanlanChayiMoney(sumMemMerPool.add(sumMemMerPool).subtract(sumDateIssueMoney));
			// 提现金额
			totalReport.setWithdrawalMoney(map0.get("tradeMoney").divide(new BigDecimal(0.8), 2, BigDecimal.ROUND_UP));
			// 提现税收
			totalReport.setWithdrawalShuishouMoney(map0.get("taxMoney"));
			// 提现责任消费
			totalReport.setWithdrawalZenrenMoney(map0.get("insMoney"));
			// 提现到账金额
			totalReport.setWithdrawalDaozMoney(map0.get("tradeMoney"));
			// 提现手续费
			totalReport.setWithdrawalShouxuMoney(new BigDecimal(0));

			// 会员余额发放额
			totalReport.setMemberBanlanFafangMoney(sumDateIssueMoney);

			// 会员余额提现划出
			totalReport.setMemberBanlanHuaChuMoney(map0.get("tradeMoney"));
			// 会员余额消费
			totalReport.setMemberBanlanXianFeiMoney(map0.get("sumOrderYpay"));
			// 会员余额剩余
			if (BeanUtil.isValid(totalReport0)) {
				totalReport.setMemberBanlanShenyuMoney(totalReport0.getMemberBanlanShenyuMoney().add(sumDateIssueMoney)
						.subtract(map0.get("tradeMoney").subtract(map0.get("sumOrderYpay"))));
			} else {

				totalReport.setMemberBanlanShenyuMoney(
						sumDateIssueMoney.subtract(map0.get("tradeMoney").subtract(map0.get("sumOrderYpay"))));
			}

			// shenYuMoney=shenYuMoney.add(sumDateIssueMoney).subtract(map0.get("tradeMoney").subtract(map0.get("sumOrderYpay")));

			// 责任消费日消费
			totalReport.setZenrenDateMoney(map0.get("insMoney"));
			// 责任消费消耗额
			totalReport.setZenrenXiaoHouMoney(map0.get("sumOrderDpay"));
			// 剩余额
			totalReport.setZenrenShenyuMoney(map0.get("insMoney").subtract(map0.get("sumOrderDpay")));
			// 会员人数
			totalReport.setMemberNumber(map0.get("newMember").intValue());
			// 商家数量
			totalReport.setMerchantsNumber(map0.get("newMc").intValue());
			// 代理收入个代
			totalReport.setGedaiMoney(map0.get("generationMoney"));
			// 代理收入区代
			totalReport.setEreadaiMoney(map0.get("areaGenerationMoney"));
			// 代理收入市代
			totalReport.setCitydaiMoney(map0.get("cityGenerationMoney"));
			// 会员商户池总营收
			BigDecimal sumTotalYSMoney = sumMemDiff.add(sumMerchDiff);
			totalReport.setMerchantsPoolTotalYiShou(sumTotalYSMoney);

			// 平台池总营收
			BigDecimal sumPTPoolMoney = sumBeDateMoney.multiply(new BigDecimal(0.85)).setScale(2, BigDecimal.ROUND_UP)
					.multiply(new BigDecimal(0.1)).setScale(2, BigDecimal.ROUND_UP)
					.add((sumBeDateMoney.multiply(new BigDecimal(0.15)).setScale(2, BigDecimal.ROUND_UP)
							.multiply(new BigDecimal(0.3).setScale(2, BigDecimal.ROUND_UP))));
			totalReport.setPingTaiTotalYiShou(sumPTPoolMoney);

			// 实际收款
			BigDecimal sumRealSMoney = sumTotalWpay.add(sumTotalHpay);
			totalReport.setShijiShoukuaiMoney(sumRealSMoney);
			// 实际支出
			totalReport.setZhichuDShijiPayMoney(map0.get("tradeMoney"));
			// 预日营收
			BigDecimal sumDateYSMoney = sumRealSMoney
					.subtract((sumDateIssueMoney.multiply(new BigDecimal(0.8)).setScale(2, BigDecimal.ROUND_UP)));
			totalReport.setYishouYuYinShouMoney(sumDateYSMoney);
			// 实际应收
			BigDecimal sumRealNeedMoney = sumDateYSMoney.subtract(map0.get("tradeMoney"));
			totalReport.setYiShouShiJiYiShouMoney(sumRealNeedMoney);

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 1);
			totalReport.setAddTime(calendar.getTime());
			countInfoDao.save(totalReport);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new ResultVo(200, "添加成功", null);
	}

	/**
	 * 统计用户是否为活跃用户
	 * 
	 * @return
	 */
	@Override
	public ResultVo updateMemberIsActivity() {
		// 查询所有用户
		/*
		 * Map<String, Object> attrs=new HashMap<>(); attrs.put("isDivi", "1");
		 * attrs.put("isDel", "1"); attrs.put("status", "1");
		 * List<GjfMemberInfo> list=countInfoDao.queryList(GjfMemberInfo.class,
		 * "addTime", "desc", attrs);
		 */
		/*List list = countInfoDao.findAllDiviInfoByTime();
		if (BeanUtil.isValid(list)) {
			for (int i = 0; i < list.size(); i++) {
				Map<String, String> map = (Map<String, String>) list.get(i);
				Map<String, Object> attrs = new HashMap<>();
				attrs.put("id", map.get("memberId"));
				GjfMemberInfo member = countInfoDao.query(GjfMemberInfo.class, attrs);
				if (member.getDividendsTotalMoney().doubleValue() <= 200) {
					member.setIsActiveMember("1");
				} else {
					// 获取用户让利金额
					BigDecimal meBeMoey = countInfoDao.findCountCousumMoney("0", member.getId());
					// 获取用户本周分红金额
					// BigDecimal
					// diviMoney=countInfoDao.findDiviMoneyByTime("0",member.getId());
					if (meBeMoey.doubleValue() >= new BigDecimal(map.get("tradeMoney")).multiply(new BigDecimal(0.5))
							.setScale(2, BigDecimal.ROUND_DOWN).doubleValue()) {
						member.setIsActiveMember("1");
					} else {
						member.setIsActiveMember("0");
					}
				}
				countInfoDao.update(member);
				if ("1".equals(member.getType())) {
					// 查询店铺信息
					Map<String, Object> storeAttrs = new HashMap<>();
					storeAttrs.put("memberId.id", member.getId());
					GjfStoreInfo store = countInfoDao.query(GjfStoreInfo.class, storeAttrs);
					if (store.getStoreDividendsTotalMoney().doubleValue() <= 200) {
						store.setIsActivityStore("1");
					} else {
						// 查询商户本周获取的让利金额
						BigDecimal merBeMoney = countInfoDao.findCountCousumMoney("1", store.getId());
						// 查询商户本周分红金额
						BigDecimal mchDiviMoney = countInfoDao.findDiviMoneyByTime("1", member.getId());
						if (merBeMoney.doubleValue() >= mchDiviMoney.multiply(new BigDecimal(0.5))
								.setScale(2, BigDecimal.ROUND_DOWN).doubleValue()) {
							store.setIsActivityStore("1");
						} else {
							store.setIsActivityStore("0");
						}
					}
					countInfoDao.update(store);
				}

			}

		}*/
		return countInfoDao.updateIsActivity();
	}

}
