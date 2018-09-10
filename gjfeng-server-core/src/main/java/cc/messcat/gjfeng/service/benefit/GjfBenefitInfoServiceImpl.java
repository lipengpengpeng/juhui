package cc.messcat.gjfeng.service.benefit;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.BagUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cc.messcat.gjfeng.common.bean.Pager;
import cc.messcat.gjfeng.common.constant.CommonStatus;
import cc.messcat.gjfeng.common.exception.MyException;
import cc.messcat.gjfeng.common.util.BeanUtil;
import cc.messcat.gjfeng.common.util.DateHelper;
import cc.messcat.gjfeng.common.util.ObjValid;
import cc.messcat.gjfeng.common.util.Sha256;
import cc.messcat.gjfeng.common.util.StringUtil;
import cc.messcat.gjfeng.common.vo.app.MemberTradeBenefitVo;
import cc.messcat.gjfeng.common.vo.app.ResultVo;
import cc.messcat.gjfeng.dao.benefit.GjfBenefitInfoDao;
import cc.messcat.gjfeng.dao.count.CountInfoDao;
import cc.messcat.gjfeng.entity.GjfBenefitHistory;
import cc.messcat.gjfeng.entity.GjfBenefitInfo;
import cc.messcat.gjfeng.entity.GjfBenefitPool;
import cc.messcat.gjfeng.entity.GjfMemberConsumptiomNum;
import cc.messcat.gjfeng.entity.GjfMemberInfo;
import cc.messcat.gjfeng.entity.GjfMemberTradeBenefit;
import cc.messcat.gjfeng.entity.GjfMemberTradeDetail;
import cc.messcat.gjfeng.entity.GjfMemberTradeDivi;
import cc.messcat.gjfeng.entity.GjfMemberTradeDiviHistory;
import cc.messcat.gjfeng.entity.GjfMemberTradeIndi;
import cc.messcat.gjfeng.entity.GjfMemberTradeOpcenter;
import cc.messcat.gjfeng.entity.GjfOrderInfo;
import cc.messcat.gjfeng.entity.GjfSetBaseInfo;
import cc.messcat.gjfeng.entity.GjfSetDividends;
import cc.messcat.gjfeng.entity.GjfStoreInfo;
import cc.messcat.gjfeng.entity.GjfTransferHistory;
import cc.messcat.gjfeng.entity.GjfspecialMemberTradeDivi;

@Service("gjfBenefitInfoService")
public class GjfBenefitInfoServiceImpl implements GjfBenefitInfoService {

	@Autowired
	@Qualifier("gjfBenefitInfoDao")
	private GjfBenefitInfoDao gjfBenefitInfoDao;

	@Autowired
	@Qualifier("countInfoDao")
	private CountInfoDao countInfoDao;

	/*
	 * 用户每消费一笔，就会计算一次其所获得的分红权
	 * 
	 * @see cc.messcat.gjfeng.service.GjfBenefitNotifyService#
	 * updateMemberBenefitNotify(java.lang.String, java.lang.String,
	 * java.lang.Double, java.lang.Double)
	 */
	@Override
	public ResultVo updateMemberDividendsNumNotify(String membersMobile, String merchantsMobile,
			Double consumptionMoney, String tradeNo, String merchartType) {
		// 如果关键参数为空返回提示
		if (StringUtil.isBlank(membersMobile) || StringUtil.isBlank(merchantsMobile)
				|| ObjValid.isNotValid(consumptionMoney)) {
			throw new MyException(400, "数据有误", null);
		}
		// 计算用户让利金额
		double totalBenefit=0.00;
		//如果是一类
		if("1".equals(merchartType)){
			totalBenefit = new BigDecimal(consumptionMoney * 0.1).setScale(2, BigDecimal.ROUND_UP).doubleValue();
		}
		//如果是二类
		if("2".equals(merchartType)){
			totalBenefit = new BigDecimal(consumptionMoney * 0.15).setScale(2, BigDecimal.ROUND_UP).doubleValue();
		}
		
		//如果是二类
		if("3".equals(merchartType)){
			totalBenefit = new BigDecimal(consumptionMoney * 0.2).setScale(2, BigDecimal.ROUND_UP).doubleValue();
		}
		
		// 商家计算分红比例
		BigDecimal subPlaSys = new BigDecimal(0.00);

		// 1.更新用户的分红权(固定)
		// BigDecimal[] directBenefit1
		// =updateMemberBenefitNum(membersMobile,"0", consumptionMoney,
		// totalBenefit, tradeNo, subPlaSys);

		// 更新用户分红权（可后台调整）
		BigDecimal[] directBenefit1 = updateMemberBenefitNumCanSetInBack(membersMobile, "0", consumptionMoney,
				totalBenefit, tradeNo, subPlaSys, merchartType);

		// 2.更新商家的分红权（固定）
		subPlaSys = directBenefit1[2];
		// updateMemberBenefitNum(merchantsMobile, "1",
		// consumptionMoney,totalBenefit, tradeNo, subPlaSys);
		// 更新商家的分红权（可后台设置）
		updateMemberBenefitNumCanSetInBack(merchantsMobile, "1", consumptionMoney, totalBenefit, tradeNo, subPlaSys,
				merchartType);
		// 3.直推会员分红
		updateBenefit(directBenefit1[0], membersMobile, "0", "0", tradeNo);
		// 4.直推商家分红
		updateBenefit(directBenefit1[1], merchantsMobile, "1", "0", tradeNo);
		// 5.会员线个代分红
		addIndiBenefit(merchantsMobile, tradeNo, consumptionMoney, totalBenefit,"0");
		//商家线
		addIndiBenefit(membersMobile, tradeNo, consumptionMoney, totalBenefit,"1");
		// 6.计算运营中心分红
		addOperationCenterBenefit(merchantsMobile, tradeNo, consumptionMoney, totalBenefit);
        //用户每月消费笔数
		updateMemberCouNum(tradeNo);
		return null;
	}

	/**
	 * 记录会员消费笔数
	 * 
	 * @param tradeNo
	 */
	public void updateMemberCouNum(String tradeNo) {
		// 创建查询条件map
		Map<String, Object> attrs = new HashMap<>();
		// 订单号
		attrs.put("tradeNo", tradeNo);
		// 获取用户让利细心
		GjfMemberTradeBenefit benefit = gjfBenefitInfoDao.query(GjfMemberTradeBenefit.class, attrs);
		// 如果让利信息不为空
		if (BeanUtil.isValid(benefit)) {
			// 查詢用戶消費記錄
			List<GjfMemberConsumptiomNum> list = gjfBenefitInfoDao
					.findMemberCousumptionNum(benefit.getMemberId().getId());
			// 如果不為空則直接加一
			if (BeanUtil.isValid(list)) {
				//获取记录信息
				GjfMemberConsumptiomNum consumptiomNum = list.get(0);
				//让利笔数加一
				consumptiomNum.setBenefitNum(consumptiomNum.getBenefitNum() + 1);
				//更新信息
				gjfBenefitInfoDao.update(consumptiomNum);
			} else {
				//创建记录消费笔数对象
				GjfMemberConsumptiomNum consumptiomNum = new GjfMemberConsumptiomNum();
				//让利消费加一
				consumptiomNum.setBenefitNum(1);
				//商城消费
				consumptiomNum.setShopConsumptionNum(0);
				//用户id
				consumptiomNum.setMemberId(benefit.getMemberId().getId());
				//创建时间格式化对象
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				//格式化当前时间
				String date = sdf.format(new Date());
				ParsePosition ps = new ParsePosition(0);
				//记录添加时间
				consumptiomNum.setAddTime(sdf.parse(date, ps));
				//保存消费记录
				gjfBenefitInfoDao.save(consumptiomNum);
			}
			// 判断用户最早消费时间是否为空
			if (!BeanUtil.isValid(benefit.getMemberId().getFirstConsumptionTime())) {
				GjfMemberInfo member = benefit.getMemberId();
				member.setFirstConsumptionTime(new Date());
			}
		}
	}

	/*
	 * 用户每消费一笔，就会计算一次其所获得的分红权
	 * 
	 * @see cc.messcat.gjfeng.service.GjfBenefitNotifyService#
	 * updateMemberBenefitNum(java.lang.String, java.lang.Double)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public BigDecimal[] updateMemberBenefitNum(String membersMobile, String memberType, Double consumptionMoney,
			Double totalBenefit, String tradeNo, BigDecimal subSysPla) {

		BigDecimal diviNum = new BigDecimal(0.000000);

		if (memberType.equals("0")) {
			double cumulativeMoney = 0.00;
			// 1.求目前剩余的用户(商家)计算分红权的额度，并加上当次消费额度
			Map<String, Object> attrs = new HashMap<String, Object>();
			attrs.put("mobile", membersMobile);
			GjfMemberInfo gjfMemberInfo = gjfBenefitInfoDao.query(GjfMemberInfo.class, attrs);

			// 2.求用户累计消费额度
			double bfCumulativeMoney = gjfMemberInfo.getCumulativeMoney().doubleValue();
			BigDecimal benefitMoney = new BigDecimal(totalBenefit).setScale(2, BigDecimal.ROUND_UP);

			gjfMemberInfo.setCumulativeMoney(gjfMemberInfo.getCumulativeMoney().add(new BigDecimal(consumptionMoney)));
			gjfMemberInfo
					.setConsumptionMoney(gjfMemberInfo.getConsumptionMoney().add(new BigDecimal(consumptionMoney)));
			cumulativeMoney = gjfMemberInfo.getCumulativeMoney().doubleValue();

			// 3.根据目前的分红权额度以及相对应的累积消费额度计算分红权个数
			if (bfCumulativeMoney <= 10000) {
				// 3.1 小于1万 500一个分红权
				if (cumulativeMoney <= 10000) {
					BigDecimal a = new BigDecimal(consumptionMoney).divide(new BigDecimal(500), 6,
							BigDecimal.ROUND_DOWN);
					diviNum = a;

					GjfMemberTradeDivi diviHistory = new GjfMemberTradeDivi(null, gjfMemberInfo, tradeNo + "-1",
							diviNum, benefitMoney, new BigDecimal(0.00), new BigDecimal(consumptionMoney), new Date(),
							null, null, "0", "1", "0", "");
					diviHistory.setToken(Sha256.getSha256Hash(diviHistory.getDiviNo(), diviHistory.getDiviStatus(),
							CommonStatus.SIGN_KEY_NUM));
					gjfBenefitInfoDao.save(diviHistory);
				} else if (cumulativeMoney > 10000 && cumulativeMoney <= 10000 * 100) {
					BigDecimal a = new BigDecimal(10000 - bfCumulativeMoney).divide(new BigDecimal(500), 6,
							BigDecimal.ROUND_DOWN);
					BigDecimal b = new BigDecimal((cumulativeMoney - 10000)).divide(new BigDecimal(1000), 6,
							BigDecimal.ROUND_DOWN);
					diviNum = a.add(b);

					GjfMemberTradeDivi diviHistory = new GjfMemberTradeDivi(null, gjfMemberInfo, tradeNo + "-1",
							diviNum, benefitMoney, new BigDecimal(0.00), new BigDecimal(consumptionMoney), new Date(),
							null, null, "0", "2", "0", "");
					diviHistory.setToken(Sha256.getSha256Hash(diviHistory.getDiviNo(), diviHistory.getDiviStatus(),
							CommonStatus.SIGN_KEY_NUM));
					gjfBenefitInfoDao.save(diviHistory);
				} else if (cumulativeMoney > 10000 * 100) {
					BigDecimal a = new BigDecimal((10000 - bfCumulativeMoney)).divide(new BigDecimal(500), 6,
							BigDecimal.ROUND_DOWN);
					BigDecimal b = new BigDecimal((10000 * 100 - 10000)).divide(new BigDecimal(1000), 6,
							BigDecimal.ROUND_DOWN);
					BigDecimal c = new BigDecimal((cumulativeMoney - 10000 * 100)).divide(new BigDecimal(5000), 6,
							BigDecimal.ROUND_DOWN);
					diviNum = a.add(b).add(c);

					GjfMemberTradeDivi diviHistory = new GjfMemberTradeDivi(null, gjfMemberInfo, tradeNo + "-1",
							diviNum, benefitMoney, new BigDecimal(0.00), new BigDecimal(consumptionMoney), new Date(),
							null, null, "0", "3", "0", "");
					diviHistory.setToken(Sha256.getSha256Hash(diviHistory.getDiviNo(), diviHistory.getDiviStatus(),
							CommonStatus.SIGN_KEY_NUM));
					gjfBenefitInfoDao.save(diviHistory);
				}
			} else if (bfCumulativeMoney > 10000 && bfCumulativeMoney <= 10000 * 100) {
				// 3.2 1万-100万 1000一个分红权
				if (cumulativeMoney > 10000 && cumulativeMoney <= 10000 * 100) {
					BigDecimal a = new BigDecimal(consumptionMoney).divide(new BigDecimal(1000), 6,
							BigDecimal.ROUND_DOWN);
					diviNum = a;

					GjfMemberTradeDivi diviHistory = new GjfMemberTradeDivi(null, gjfMemberInfo, tradeNo + "-1",
							diviNum, benefitMoney, new BigDecimal(0.00), new BigDecimal(consumptionMoney), new Date(),
							null, null, "0", "2", "0", "");
					diviHistory.setToken(Sha256.getSha256Hash(diviHistory.getDiviNo(), diviHistory.getDiviStatus(),
							CommonStatus.SIGN_KEY_NUM));
					gjfBenefitInfoDao.save(diviHistory);
				} else if (cumulativeMoney > 10000 * 100) {
					BigDecimal a = new BigDecimal((10000 * 100 - bfCumulativeMoney)).divide(new BigDecimal(1000), 6,
							BigDecimal.ROUND_DOWN);
					BigDecimal b = new BigDecimal((cumulativeMoney - 10000 * 100)).divide(new BigDecimal(5000), 6,
							BigDecimal.ROUND_DOWN);
					diviNum = a.add(b);

					GjfMemberTradeDivi diviHistory = new GjfMemberTradeDivi(null, gjfMemberInfo, tradeNo + "-1",
							diviNum, benefitMoney, new BigDecimal(0.00), new BigDecimal(consumptionMoney), new Date(),
							null, null, "0", "3", "0", "");
					diviHistory.setToken(Sha256.getSha256Hash(diviHistory.getDiviNo(), diviHistory.getDiviStatus(),
							CommonStatus.SIGN_KEY_NUM));
					gjfBenefitInfoDao.save(diviHistory);
				}
			} else if (bfCumulativeMoney > 10000 * 100) {
				// 3.3 100万以上 5000一个分红权
				BigDecimal a = new BigDecimal(consumptionMoney).divide(new BigDecimal(5000), 6, BigDecimal.ROUND_DOWN);
				diviNum = a;

				GjfMemberTradeDivi diviHistory = new GjfMemberTradeDivi(null, gjfMemberInfo, tradeNo + "-1", diviNum,
						benefitMoney, new BigDecimal(0.00), new BigDecimal(consumptionMoney), new Date(), null, null,
						"0", "3", "0", "");
				diviHistory.setToken(Sha256.getSha256Hash(diviHistory.getDiviNo(), diviHistory.getDiviStatus(),
						CommonStatus.SIGN_KEY_NUM));
				gjfBenefitInfoDao.save(diviHistory);
			}

			// 升级活跃区
			// 获取订单信息
			Map<String, Object> neMap = new HashMap<>();
			neMap.put("tradeNo", tradeNo);
			GjfMemberTradeBenefit benefit = countInfoDao.query(GjfMemberTradeBenefit.class, neMap);
			if (BeanUtil.isValid(benefit)) {
				benefit.setChangeAcivityStatus("0");
				benefit.setConsumptionMoney(new BigDecimal(0.00));

				if ("0".equals(gjfMemberInfo.getIsActiveMember())) {

					// 获取用户本周让利金额
					BigDecimal meBeMoey = countInfoDao.findCountCousumMoney("0", gjfMemberInfo.getId());
					if ("0".equals(benefit.getPayType()) || "1".equals(benefit.getPayType())) {
						meBeMoey = meBeMoey.add(new BigDecimal(totalBenefit));
					}
					// 取整
					Double meBeMoney = meBeMoey.doubleValue();
					// BigDecimal totalUnit=getUnitAveragePrice("0");
					BigDecimal argUnit = new BigDecimal("1");
					Map<String, Object> setAttrs = new HashMap<String, Object>();
					setAttrs.put("key", "MEMBER_UNIT_PRICE_");
					setAttrs.put("status", "1");
					GjfSetBaseInfo baseInfo = gjfBenefitInfoDao.query(GjfSetBaseInfo.class, setAttrs);
					if (BeanUtil.isValid(baseInfo)) {
						argUnit = new BigDecimal(baseInfo.getValue());
					}
					if ("1".equals(gjfMemberInfo.getType())) {
						Map<String, Object> storeAttrs = new HashMap<String, Object>();
						storeAttrs.put("memberId.id", gjfMemberInfo.getId());
						GjfStoreInfo storeInfo = gjfBenefitInfoDao.query(GjfStoreInfo.class, storeAttrs);
						// 如果用户存在并且店铺为不活跃
						if (BeanUtil.isValid(storeInfo) && "0".equals(storeInfo.getIsActivityStore())) {
							BigDecimal totalDivi = gjfMemberInfo.getDividendsNum()
									.add(storeInfo.getStoreDividendsNum());
							// 分红权取整
							Double intTotalDivi = Math.floor(totalDivi.doubleValue());
							BigDecimal niceBeMoney = argUnit.multiply(new BigDecimal(7 * 0.5))
									.multiply(new BigDecimal(intTotalDivi)).setScale(2, BigDecimal.ROUND_DOWN);
							if (meBeMoney >= niceBeMoney.doubleValue()) {
								gjfMemberInfo.setIsActiveMember("1");
								storeInfo.setIsActivityStore("1");
								// 查询用户升级金额
								BigDecimal upgradeMoney = countInfoDao
										.findMemberBenefitUpgradeMoney(gjfMemberInfo.getId());
								benefit.setConsumptionMoney(niceBeMoney.subtract(upgradeMoney));
								benefit.setChangeAcivityStatus("1");
								gjfBenefitInfoDao.update(storeInfo);
							} else {
								if (gjfMemberInfo.getDividendsNum().doubleValue() >= storeInfo.getStoreDividendsNum()
										.doubleValue()) {
									// 分红权取整
									if ("0".equals(storeInfo.getIsActivityStore())) {
										Double intStoredivi = Math
												.floor(storeInfo.getStoreDividendsNum().doubleValue());
										BigDecimal sniceBeMoney = argUnit.multiply(new BigDecimal(7 * 0.5))
												.multiply(new BigDecimal(intStoredivi))
												.setScale(2, BigDecimal.ROUND_DOWN);
										if (meBeMoney >= sniceBeMoney.doubleValue()) {
											storeInfo.setIsActivityStore("1");
											benefit.setConsumptionMoney(sniceBeMoney);
											benefit.setChangeAcivityStatus("1");
											gjfBenefitInfoDao.update(storeInfo);
										}
									}

								} else {
									Double intMemdivi = Math.floor(gjfMemberInfo.getDividendsNum().doubleValue());
									BigDecimal miceBeMoney = argUnit.multiply(new BigDecimal(7 * 0.5))
											.multiply(new BigDecimal(intMemdivi)).setScale(2, BigDecimal.ROUND_DOWN);
									if (meBeMoney >= miceBeMoney.doubleValue()) {
										benefit.setConsumptionMoney(miceBeMoney);
										benefit.setChangeAcivityStatus("1");
										gjfMemberInfo.setIsActiveMember("1");
									}
								}
							}
						} else {// 如果已经是活跃商家
							Double intMemdivi = Math.floor(gjfMemberInfo.getDividendsNum().doubleValue());
							BigDecimal niceBeMoney = argUnit.multiply(new BigDecimal(7 * 0.5))
									.multiply(new BigDecimal(intMemdivi)).setScale(2, BigDecimal.ROUND_DOWN);
							if (meBeMoney >= niceBeMoney.doubleValue()) {
								benefit.setConsumptionMoney(niceBeMoney);
								benefit.setChangeAcivityStatus("1");
								gjfMemberInfo.setIsActiveMember("1");
							}
						}

					} else {// 如果为普通用户
						Double intMemdivi = Math.floor(gjfMemberInfo.getDividendsNum().doubleValue());
						BigDecimal niceBeMoney = argUnit.multiply(new BigDecimal(7 * 0.5))
								.multiply(new BigDecimal(intMemdivi)).setScale(2, BigDecimal.ROUND_DOWN);
						if (meBeMoney >= niceBeMoney.doubleValue()) {
							benefit.setConsumptionMoney(niceBeMoney);
							benefit.setChangeAcivityStatus("1");
							gjfMemberInfo.setIsActiveMember("1");
						}
					}

					if (!BeanUtil.isValid(gjfMemberInfo.getFirstConsumptionTime())) {
						// 查询用户是否第一次消费
						Map<String, Object> comAttrs = new HashMap<>();
						comAttrs.put("memberId.id", gjfMemberInfo.getId());
						List<GjfMemberTradeBenefit> bList = gjfBenefitInfoDao.queryList(GjfMemberTradeBenefit.class, 0,
								2, "addTime", "desc", comAttrs);
						List<GjfOrderInfo> oList = gjfBenefitInfoDao.queryList(GjfOrderInfo.class, 0, 2, "addTime",
								"desc", comAttrs);
						if (BeanUtil.isValid(bList) && !BeanUtil.isValid(oList) && bList.size() == 1) {
							gjfMemberInfo.setIsActiveMember("1");
						}
						if (!BeanUtil.isValid(bList) && BeanUtil.isValid(oList) && oList.size() == 1) {
							gjfMemberInfo.setIsActiveMember("1");
						}
					}

				} else {

					if ("1".equals(gjfMemberInfo.getType())) {
						// 获取用户本周让利金额
						BigDecimal meBeMoey = countInfoDao.findCountCousumMoney("0", gjfMemberInfo.getId());
						// BigDecimal totalUnit=getUnitAveragePrice("0");
						BigDecimal argUnit = new BigDecimal("1");
						Map<String, Object> setAttrs = new HashMap<String, Object>();
						setAttrs.put("key", "MEMBER_UNIT_PRICE_");
						setAttrs.put("status", "1");
						GjfSetBaseInfo baseInfo = gjfBenefitInfoDao.query(GjfSetBaseInfo.class, setAttrs);
						if (BeanUtil.isValid(baseInfo)) {
							argUnit = new BigDecimal(baseInfo.getValue());
						}

						Map<String, Object> storeAttrs = new HashMap<String, Object>();
						storeAttrs.put("memberId.id", gjfMemberInfo.getId());
						GjfStoreInfo storeInfo = gjfBenefitInfoDao.query(GjfStoreInfo.class, storeAttrs);
						if ("0".equals(storeInfo.getIsActivityStore())) {
							Double intMemdivi = Math.floor(storeInfo.getStoreDividendsNum().doubleValue());
							BigDecimal niceBeMoney = argUnit.multiply(new BigDecimal(7 * 0.5))
									.multiply(new BigDecimal(intMemdivi)).setScale(2, BigDecimal.ROUND_DOWN);
							if (meBeMoey.doubleValue() >= niceBeMoney.doubleValue()) {
								benefit.setChangeAcivityStatus("1");
								benefit.setConsumptionMoney(niceBeMoney);
								storeInfo.setIsActivityStore("1");
								gjfBenefitInfoDao.update(storeInfo);
							}
						}

					}
				}
				gjfBenefitInfoDao.update(benefit);
			}

			gjfMemberInfo
					.setDividendsNum(gjfMemberInfo.getDividendsNum().add(diviNum).setScale(6, BigDecimal.ROUND_DOWN));
			// 4.增加分红权获取记录
			gjfBenefitInfoDao.update(gjfMemberInfo);

		} else {
			Map<String, Object> storeAttrs = new HashMap<String, Object>();
			storeAttrs.put("memberId.mobile", membersMobile);
			GjfStoreInfo gjfStoreInfo = gjfBenefitInfoDao.query(GjfStoreInfo.class, storeAttrs);

			double bfTotalBenefitMoney = gjfStoreInfo.getStoreBenefitTotalMoney().doubleValue();
			BigDecimal benefitMoney = new BigDecimal(totalBenefit).setScale(2, BigDecimal.ROUND_UP);

			gjfStoreInfo.setStoreSaleTotalMoney(
					gjfStoreInfo.getStoreSaleTotalMoney().add(new BigDecimal(consumptionMoney)));
			gjfStoreInfo.setStoreBenefitTotalMoney(gjfStoreInfo.getStoreBenefitTotalMoney().add(benefitMoney));
			gjfStoreInfo
					.setStoreDividendsTotalMoneyBla(gjfStoreInfo.getStoreDividendsTotalMoneyBla().add(benefitMoney));
			double totalBenefitMoney = gjfStoreInfo.getStoreBenefitTotalMoney().doubleValue();

			// 3.根据目前的分红权额度以及相对应的累积消费额度计算分红权个数
			if (bfTotalBenefitMoney <= 10000) {
				// 3.1 小于1万 500一个分红权
				if (totalBenefitMoney <= 10000) {
					BigDecimal a = new BigDecimal(benefitMoney.doubleValue()).divide(new BigDecimal(500), 6,
							BigDecimal.ROUND_DOWN);
					diviNum = a;

					GjfMemberTradeDivi diviHistory = new GjfMemberTradeDivi(null, gjfStoreInfo.getMemberId(),
							tradeNo + "-2", diviNum, benefitMoney, new BigDecimal(0.00),
							new BigDecimal(consumptionMoney), new Date(), null, null, "0", "1", "1", "");
					diviHistory.setToken(Sha256.getSha256Hash(diviHistory.getDiviNo(), diviHistory.getDiviStatus(),
							CommonStatus.SIGN_KEY_NUM));
					gjfBenefitInfoDao.save(diviHistory);
				} else if (totalBenefitMoney > 10000 && totalBenefitMoney <= 10000 * 100) {
					BigDecimal a = new BigDecimal((10000 - bfTotalBenefitMoney)).divide(new BigDecimal(500), 6,
							BigDecimal.ROUND_DOWN);
					BigDecimal b = new BigDecimal((totalBenefitMoney - 10000)).divide(new BigDecimal(1000), 6,
							BigDecimal.ROUND_DOWN);
					diviNum = a.add(b);

					GjfMemberTradeDivi diviHistory = new GjfMemberTradeDivi(null, gjfStoreInfo.getMemberId(),
							tradeNo + "-2", diviNum, benefitMoney, new BigDecimal(0.00),
							new BigDecimal(consumptionMoney), new Date(), null, null, "0", "2", "1", "");
					diviHistory.setToken(Sha256.getSha256Hash(diviHistory.getDiviNo(), diviHistory.getDiviStatus(),
							CommonStatus.SIGN_KEY_NUM));
					gjfBenefitInfoDao.save(diviHistory);
				} else if (totalBenefitMoney > 10000 * 100) {
					BigDecimal a = new BigDecimal((10000 - bfTotalBenefitMoney)).divide(new BigDecimal(500), 6,
							BigDecimal.ROUND_DOWN);
					BigDecimal b = new BigDecimal((10000 * 100 - 10000)).divide(new BigDecimal(1000), 6,
							BigDecimal.ROUND_DOWN);
					BigDecimal c = new BigDecimal((totalBenefitMoney - 10000 * 100)).divide(new BigDecimal(5000), 6,
							BigDecimal.ROUND_DOWN);
					diviNum = a.add(b).add(c);

					GjfMemberTradeDivi diviHistory = new GjfMemberTradeDivi(null, gjfStoreInfo.getMemberId(),
							tradeNo + "-2", diviNum, benefitMoney, new BigDecimal(0.00),
							new BigDecimal(consumptionMoney), new Date(), null, null, "0", "3", "1", "");
					diviHistory.setToken(Sha256.getSha256Hash(diviHistory.getDiviNo(), diviHistory.getDiviStatus(),
							CommonStatus.SIGN_KEY_NUM));
					gjfBenefitInfoDao.save(diviHistory);
				}
			} else if (bfTotalBenefitMoney > 10000 && bfTotalBenefitMoney <= 10000 * 100) {
				// 3.2 1万-100万 1000一个分红权
				if (totalBenefitMoney > 10000 && totalBenefitMoney <= 10000 * 100) {
					BigDecimal a = new BigDecimal(benefitMoney.doubleValue()).divide(new BigDecimal(1000), 6,
							BigDecimal.ROUND_DOWN);
					diviNum = a;

					GjfMemberTradeDivi diviHistory = new GjfMemberTradeDivi(null, gjfStoreInfo.getMemberId(),
							tradeNo + "-2", diviNum, benefitMoney, new BigDecimal(0.00),
							new BigDecimal(consumptionMoney), new Date(), null, null, "0", "2", "1", "");
					diviHistory.setToken(Sha256.getSha256Hash(diviHistory.getDiviNo(), diviHistory.getDiviStatus(),
							CommonStatus.SIGN_KEY_NUM));
					gjfBenefitInfoDao.save(diviHistory);
				} else if (totalBenefitMoney > 10000 * 100) {
					BigDecimal a = new BigDecimal((10000 * 100 - bfTotalBenefitMoney)).divide(new BigDecimal(1000), 6,
							BigDecimal.ROUND_DOWN);
					BigDecimal b = new BigDecimal((totalBenefitMoney - 10000 * 100)).divide(new BigDecimal(5000), 6,
							BigDecimal.ROUND_DOWN);
					;
					diviNum = a.add(b);

					GjfMemberTradeDivi diviHistory = new GjfMemberTradeDivi(null, gjfStoreInfo.getMemberId(),
							tradeNo + "-2", diviNum, benefitMoney, new BigDecimal(0.00),
							new BigDecimal(consumptionMoney), new Date(), null, null, "0", "3", "1", "");
					diviHistory.setToken(Sha256.getSha256Hash(diviHistory.getDiviNo(), diviHistory.getDiviStatus(),
							CommonStatus.SIGN_KEY_NUM));
					gjfBenefitInfoDao.save(diviHistory);
				}
			} else if (bfTotalBenefitMoney > 10000 * 100) {
				// 3.3 100万以上 5000一个分红权
				BigDecimal a = new BigDecimal(benefitMoney.doubleValue()).divide(new BigDecimal(5000), 6,
						BigDecimal.ROUND_DOWN);
				diviNum = a;

				GjfMemberTradeDivi diviHistory = new GjfMemberTradeDivi(null, gjfStoreInfo.getMemberId(),
						tradeNo + "-2", diviNum, benefitMoney, new BigDecimal(0.00), new BigDecimal(consumptionMoney),
						new Date(), null, null, "0", "3", "1", "");
				diviHistory.setToken(Sha256.getSha256Hash(diviHistory.getDiviNo(), diviHistory.getDiviStatus(),
						CommonStatus.SIGN_KEY_NUM));
				gjfBenefitInfoDao.save(diviHistory);
			}

			gjfStoreInfo.setStoreDividendsNum(
					gjfStoreInfo.getStoreDividendsNum().add(diviNum).setScale(6, BigDecimal.ROUND_DOWN));

			gjfBenefitInfoDao.update(gjfStoreInfo);
		}

		// 修改商家让利表记录的分红权额
		Map<String, Object> attrsBenefit = new HashMap<String, Object>();
		Map<String, Object> propsBenefit = new HashMap<String, Object>();
		attrsBenefit.put("tradeNo", tradeNo);
		if (memberType.equals("0")) {
			propsBenefit.put("memberDividendsNum", diviNum);
		} else {
			propsBenefit.put("merchantsDividendsNum", diviNum);
		}
		gjfBenefitInfoDao.update(GjfMemberTradeBenefit.class, propsBenefit, attrsBenefit);

		// 修改资金池
		BigDecimal[] directBenefit = updateBenefitPool(memberType, totalBenefit, subSysPla, "0","0","0",consumptionMoney);
		return directBenefit;
	}

	/*
	 * 计算用户每笔消费所产生的分红权额度，并返还给客户和商家
	 * 
	 * @see cc.messcat.gjfeng.service.GjfBenefitNotifyService#updateBenefit(
	 * java.lang.Double, java.lang.String)
	 */
	@Override
	public BigDecimal updateBenefit(BigDecimal totalBenefit, String membersMobile, String memberType, String actType,
			String tradeNo) {
		// 1.直推会员or商家分红，求出会员or商家的上一级
		//获取当前时间
		Date curDate = new Date();
		//创建查询条件map
		Map<String, Object> attrs = new HashMap<String, Object>();
		//用户电话号码
		attrs.put("mobile", membersMobile);
		//获取用户信息
		GjfMemberInfo gjfMemberInfo = gjfBenefitInfoDao.query(GjfMemberInfo.class, attrs);
		//获取让利占比信息
		GjfBenefitInfo gjfBenefitInfo = findByType(actType);
		//如果用户信息和用户推荐人不为空，并且是会员
		if (ObjValid.isValid(gjfMemberInfo) && ObjValid.isValid(gjfMemberInfo.getSuperId()) && actType.equals("0")) {
			//创建查询推荐map
			Map<String, Object> supMemberAttrs = new HashMap<String, Object>();
			//推荐人id
			supMemberAttrs.put("id", gjfMemberInfo.getSuperId());
			//推荐人状态
			supMemberAttrs.put("status", "1");
			//推荐人删除状态
			supMemberAttrs.put("isDel", "1");
			// supMemberAttrs.put("isDivi", "1");
			//获取推荐人信息
			GjfMemberInfo superMember = gjfBenefitInfoDao.query(GjfMemberInfo.class, supMemberAttrs);
			//如果推荐人为空结束方法
			if (ObjValid.isNotValid(superMember)) {
				return null;
			}
			//获取推荐人占比
			double ratio = memberType.equals("0") ? gjfBenefitInfo.getDirectMembersRatio()
					: gjfBenefitInfo.getDirectMerchantsRatio();
			// 如果是会员
			if (memberType.equals("0")) {
				//添加推荐人直推会员奖励
				superMember.setDirectMemberTotalMoney(
			          superMember.getDirectMemberTotalMoney().add(totalBenefit).setScale(2, BigDecimal.ROUND_DOWN));
			//如果是商户
			} else {
				//给商家添加直推奖励金额
				superMember.setDirectMerchantsTotalMoney(superMember.getDirectMerchantsTotalMoney().add(totalBenefit));
			}
			//给推荐人添加余额
			superMember.setBalanceMoney(superMember.getBalanceMoney().add(totalBenefit));
			//给推荐人添加提现金额
			superMember.setWithdrawalMoney(superMember.getWithdrawalMoney().add(totalBenefit));
			// 记录推荐分红金额
			// superMember.setRecommendRewardMoney(superMember.getRecommendRewardMoney().add(totalBenefit));
			gjfBenefitInfoDao.update(superMember);

			// 添加交易流水
			Date date = new Date();
			GjfMemberTradeDiviHistory gjfMemberTradeDiviHistory = new GjfMemberTradeDiviHistory(null, superMember,
					DateHelper.dataToString(curDate, "yyyyMMddHHmmss")
							+ String.valueOf(System.currentTimeMillis()).substring(0, 5),
					totalBenefit, new BigDecimal(0.00), new BigDecimal(0.00), new BigDecimal(0.00),
					new BigDecimal(ratio), date, date, memberType.equals("0") ? "0" : "1", "1",
					memberType.equals("0") ? "直推会员分红" : "直推商家分红",
					Sha256.getSha256Hash(superMember.getId().toString(), "2016", CommonStatus.SIGN_KEY_NUM));
			gjfBenefitInfoDao.save(gjfMemberTradeDiviHistory);

			// 添加余额和提现额度变更流水
			GjfMemberTradeDetail detail1 = new GjfMemberTradeDetail(null, superMember, tradeNo + "-1", totalBenefit,
					new Date(), new Date(), "0", "1", gjfMemberTradeDiviHistory.getTradeTrmo());
			GjfMemberTradeDetail detail2 = new GjfMemberTradeDetail(null, superMember, tradeNo + "-2", totalBenefit,
					new Date(), new Date(), "1", "1", gjfMemberTradeDiviHistory.getTradeTrmo());
			gjfBenefitInfoDao.save(detail1);
			gjfBenefitInfoDao.save(detail2);

			// 修改资金池流水和资金池金额
			Map<String, Object> attrsPool = new HashMap<String, Object>();
			GjfBenefitPool benefitPool = gjfBenefitInfoDao.query(GjfBenefitPool.class, attrsPool);
			if (ObjValid.isNotValid(benefitPool)) {
				throw new MyException(400, "资金池数据异常", null);
			}

			// 修改商家让利表记录的分红权额
			Map<String, Object> attrsBenefit = new HashMap<String, Object>();
			Map<String, Object> propsBenefit = new HashMap<String, Object>();
			attrsBenefit.put("tradeNo", tradeNo);
			if (memberType.equals("0")) {
				propsBenefit.put("directMemberMoney", totalBenefit);
				propsBenefit.put("directMember.id", superMember.getId());

				benefitPool.setDirectMemberSysPoolCur(benefitPool.getDirectMemberSysPoolCur().subtract(totalBenefit));
				// 5.直推会员池
				GjfBenefitHistory benefitHistory4 = new GjfBenefitHistory(null, totalBenefit,
						benefitPool.getDirectMemberSysPoolCur(),
						benefitPool.getDirectMemberSysPoolCur().subtract(totalBenefit), new BigDecimal(0.00), curDate,
						"3", null, curDate, "1");

				gjfBenefitInfoDao.update(benefitPool);
				gjfBenefitInfoDao.save(benefitHistory4);
			} else {
				propsBenefit.put("directMerchantsMoney", totalBenefit);
				propsBenefit.put("directMerchants.id", superMember.getId());

				benefitPool.setDirectMerchantsSysPoolCur(
						benefitPool.getDirectMerchantsSysPoolCur().subtract(totalBenefit));
				// 5.直推商家池
				GjfBenefitHistory benefitHistory5 = new GjfBenefitHistory(null, totalBenefit,
						benefitPool.getDirectMerchantsSysPoolCur(),
						benefitPool.getDirectMerchantsSysPoolCur().subtract(totalBenefit), new BigDecimal(0.00),
						curDate, "4", null, curDate, "1");

				gjfBenefitInfoDao.update(benefitPool);
				gjfBenefitInfoDao.save(benefitHistory5);
			}
			gjfBenefitInfoDao.update(GjfMemberTradeBenefit.class, propsBenefit, attrsBenefit);

		}
		return null;
	}

	/**
	 * @描述 修改资金池金额
	 * @author Karhs
	 * @date 2017年2月15日
	 */
	public BigDecimal[] updateBenefitPool(String memberType, double totalBenefit, BigDecimal subPlaSys,
			String merchartGrade,String memActivity,String storeActivity,double comsuptionMoney) {
		// 创建查询条件map
		Map<String, Object> attrs = new HashMap<String, Object>();
		// 获取资金池信息
		GjfBenefitPool benefitPool = gjfBenefitInfoDao.query(GjfBenefitPool.class, attrs);
		// 如果资金池信息为空
		if (ObjValid.isNotValid(benefitPool)) {
			throw new MyException(400, "资金池数据异常", null);
		}
		// 获取让利占比信息
		GjfBenefitInfo gjfBenefitInfo = findByType(memberType);
		// 如果让利占比信息为空
		if (ObjValid.isNotValid(gjfBenefitInfo)) {
			throw new MyException(400, "配置数据异常", null);
		}
		// 获取当前时间
		Date curDate = new Date();
		// 让利金额
		BigDecimal benefit = new BigDecimal(totalBenefit);
		// 系统占比
		BigDecimal sysBenefit = new BigDecimal(benefit.doubleValue() * gjfBenefitInfo.getSysRatio())
				.divide(new BigDecimal(100), 2, BigDecimal.ROUND_DOWN);
		// 分红池占比
		BigDecimal poolBenefit = new BigDecimal(sysBenefit.doubleValue() * gjfBenefitInfo.getDiviPoolsRatio())
				.divide(new BigDecimal(100), 2, BigDecimal.ROUND_DOWN);

		// 直推会员或商家的让利金额
		BigDecimal[] directBenefit = new BigDecimal[] { new BigDecimal(0.00), new BigDecimal(0.00),
				new BigDecimal(0.00) };
		// 如果是会员
		if (memberType.equals("0")) {
			//记录会员奖励
			BigDecimal directMembersBenefit=new BigDecimal(0);
			//如果为一类
			if("1".equals(merchartGrade)){
				// 直推用户占比
				 directMembersBenefit = new BigDecimal(
						 comsuptionMoney* gjfBenefitInfo.getDirectMembersRatio()).divide(new BigDecimal(100), 2,
								BigDecimal.ROUND_DOWN);
			}
			//如果为二类
			if("2".equals(merchartGrade)){
				// 直推用户占比
				 directMembersBenefit = new BigDecimal(
						 comsuptionMoney * gjfBenefitInfo.getDirectMembersSecondRatio()).divide(new BigDecimal(100), 2,
								BigDecimal.ROUND_DOWN);
			}
			//如果为三类
			if("3".equals(merchartGrade)){
				// 直推用户占比
				 directMembersBenefit = new BigDecimal(
						 comsuptionMoney * gjfBenefitInfo.getDirectMembersThreeRatio()).divide(new BigDecimal(100), 2,
								BigDecimal.ROUND_DOWN);
			}
			
			BigDecimal directMerchantsBenefit=new BigDecimal(0); 
			//如果为一类
			if("1".equals(merchartGrade)){
				// 直推商家占比
				directMerchantsBenefit = new BigDecimal(
						comsuptionMoney* gjfBenefitInfo.getDirectMerchantsRatio()).divide(new BigDecimal(1000), 2,
								BigDecimal.ROUND_DOWN);
			}
			//如果为二类
			if("2".equals(merchartGrade)){
				// 直推商家占比
				directMerchantsBenefit = new BigDecimal(
						comsuptionMoney * gjfBenefitInfo.getDirectMerchantsSecondRatio()).divide(new BigDecimal(1000), 2,
								BigDecimal.ROUND_DOWN);
			}
			//如果为三类
			if("3".equals(merchartGrade)){
				// 直推商家占比
				directMerchantsBenefit = new BigDecimal(
						comsuptionMoney * gjfBenefitInfo.getDirectMerchantsThreeRatio()).divide(new BigDecimal(1000), 2,
								BigDecimal.ROUND_DOWN);
			}
			
						
			
			// 代理商占比
			BigDecimal agentBenefit = new BigDecimal(sysBenefit.doubleValue() * gjfBenefitInfo.getAgentRatio())
					.divide(new BigDecimal(100), 2, BigDecimal.ROUND_DOWN);

			// 记录直推会员占比
			directBenefit[0] = directMembersBenefit;
			// 记录直推商家占比
			directBenefit[1] = directMerchantsBenefit;

			// 添加会员池进账记录
			GjfBenefitHistory benefitHistory1 = new GjfBenefitHistory(null, poolBenefit,
					benefitPool.getMemberSysPoolCur(), benefitPool.getMemberSysPoolCur().add(poolBenefit),
					new BigDecimal(0.00), curDate, "8", null, curDate, "1");
			//是否活跃
			benefitHistory1.setIsActivity(memActivity);
			// 当前会员系统池金额
			benefitPool.setMemberSysPoolCur(benefitPool.getMemberSysPoolCur().add(poolBenefit));
			// 会员系统资金池累计金额
			benefitPool.setMemberSysPoolTotal(benefitPool.getMemberSysPoolTotal().add(poolBenefit));
			// 判断是那类型商户，如果为一类商户
			if ("1".equals(merchartGrade)) {
				benefitPool.setMerchartFirstGrade(benefitPool.getMerchartFirstGrade().add(poolBenefit));
				//一类记录
				GjfBenefitHistory benefitHistory20 = new GjfBenefitHistory(null, poolBenefit,
						benefitPool.getMerchartFirstGrade(), benefitPool.getMerchartFirstGrade().add(poolBenefit),
						new BigDecimal(0.00), curDate, "20", null, curDate, "1");
				//是否活跃
				benefitHistory20.setIsActivity(memActivity);
				//保存数据
				gjfBenefitInfoDao.save(benefitHistory20);
			}
			// 如果为二类商户
			if ("2".equals(merchartGrade)) {
				benefitPool.setMerchartSecondGrade(benefitPool.getMerchartSecondGrade().add(poolBenefit));
				//一类记录
				GjfBenefitHistory benefitHistory21 = new GjfBenefitHistory(null, poolBenefit,
						benefitPool.getMerchartSecondGrade(), benefitPool.getMerchartSecondGrade().add(poolBenefit),
						new BigDecimal(0.00), curDate, "21", null, curDate, "1");
				//是否活跃
				benefitHistory21.setIsActivity(memActivity);
				//保存数据
				gjfBenefitInfoDao.save(benefitHistory21);
			}
			// 如果为三类商户
			if ("3".equals(merchartGrade)) {
				benefitPool.setMerchartThreeGrade(benefitPool.getMerchartThreeGrade().add(poolBenefit));
				//一类记录
				GjfBenefitHistory benefitHistory22 = new GjfBenefitHistory(null, poolBenefit,
						benefitPool.getMerchartThreeGrade(), benefitPool.getMerchartThreeGrade().add(poolBenefit),
						new BigDecimal(0.00), curDate, "22", null, curDate, "1");
				//是否活跃
				benefitHistory22.setIsActivity(memActivity);
				//保存数据
				gjfBenefitInfoDao.save(benefitHistory22);
			}

			// 添加直推会员池进账记录
			GjfBenefitHistory benefitHistory4 = new GjfBenefitHistory(null, directMembersBenefit,
					benefitPool.getDirectMemberSysPoolCur(),
					benefitPool.getDirectMemberSysPoolCur().add(directMembersBenefit), new BigDecimal(0.00), curDate,
					"11", null, curDate, "1");

			// 添加直推商家池进账记录
			GjfBenefitHistory benefitHistory5 = new GjfBenefitHistory(null, directMerchantsBenefit,
					benefitPool.getDirectMerchantsSysPoolCur(),
					benefitPool.getDirectMerchantsSysPoolCur().add(directMerchantsBenefit), new BigDecimal(0.00),
					curDate, "12", null, curDate, "1");

			// 当前直推会员系统资金池
			benefitPool.setDirectMemberSysPoolCur(benefitPool.getDirectMemberSysPoolCur().add(directMembersBenefit));
			// 平台直推会员资金池累计金额
			benefitPool
					.setDirectMemberSysPoolTotal(benefitPool.getDirectMemberSysPoolTotal().add(directMembersBenefit));
			// 当前直推商家系统资金池
			benefitPool.setDirectMerchantsSysPoolCur(
					benefitPool.getDirectMerchantsSysPoolCur().add(directMerchantsBenefit));
			// 平台直推商家资金池累计金额
			benefitPool.setDirectMerchantsSysPoolTotal(
					benefitPool.getDirectMerchantsSysPoolTotal().add(directMerchantsBenefit));
			// 当前代理系统资金池
			benefitPool.setAgentSysPoolCur(benefitPool.getAgentSysPoolCur().add(agentBenefit));
			// 平台代理资金池累计金额
			benefitPool.setAgentSysPoolTotal(benefitPool.getAgentSysPoolTotal().add(agentBenefit));
			// 计算市代奖励金额
			BigDecimal agenCity = new BigDecimal(sysBenefit.doubleValue() * gjfBenefitInfo.getAgentCityRatio())
					.divide(new BigDecimal(100), 2, BigDecimal.ROUND_DOWN);
			// 计算区代奖励金额
			BigDecimal agenArea = new BigDecimal(sysBenefit.doubleValue() * gjfBenefitInfo.getAgentAreaRatio())
					.divide(new BigDecimal(100), 2, BigDecimal.ROUND_DOWN);
			// 计算个代奖励金额
			BigDecimal agenIndi = new BigDecimal(sysBenefit.doubleValue() * gjfBenefitInfo.getAgentIndiRatio())
					.divide(new BigDecimal(100), 2, BigDecimal.ROUND_DOWN);

			// 添加市代分红池进账记录
			GjfBenefitHistory benefitHistory6 = new GjfBenefitHistory(null, agenCity,
					benefitPool.getAgentSysCityPoolCur(), benefitPool.getAgentSysCityPoolCur().add(agenCity),
					new BigDecimal(0.00), curDate, "13", null, curDate, "1");

			// 添加区代分红池进账记录
			GjfBenefitHistory benefitHistory7 = new GjfBenefitHistory(null, agenArea,
					benefitPool.getAgentSysAreaPoolCur(), benefitPool.getAgentSysAreaPoolCur().add(agenArea),
					new BigDecimal(0.00), curDate, "14", null, curDate, "1");

			// 添加个代分红池进账记录
			GjfBenefitHistory benefitHistory8 = new GjfBenefitHistory(null, agenIndi,
					benefitPool.getAgentSysIndiPoolCur(), benefitPool.getAgentSysIndiPoolCur().add(agenIndi),
					new BigDecimal(0.00), curDate, "15", null, curDate, "1");

			// 1.2判断代理+平台+直推会员+直推商家+会员分红池=会员系统让利差
			subPlaSys = benefit.subtract(poolBenefit).subtract(directMembersBenefit).subtract(directMerchantsBenefit)
					.subtract(agenCity).subtract(agenArea).subtract(agenIndi);
			// 当前城市代理系统资金池
			benefitPool.setAgentSysCityPoolCur(benefitPool.getAgentSysCityPoolCur().add(agenCity));
			// 平台城市代理资金池累计金额
			benefitPool.setAgentSysCityPoolTotal(benefitPool.getAgentSysCityPoolTotal().add(agenCity));
			// 当前区县代理系统资金池
			benefitPool.setAgentSysAreaPoolCur(benefitPool.getAgentSysAreaPoolCur().add(agenArea));
			// 平台区县代理资金池累计金额
			benefitPool.setAgentSysAreaPoolTotal(benefitPool.getAgentSysAreaPoolTotal().add(agenArea));
			// 当前个人代理系统资金池
			benefitPool.setAgentSysIndiPoolCur(benefitPool.getAgentSysIndiPoolCur().add(agenIndi));
			// 平台个人代理资金池累计金额
			benefitPool.setAgentSysIndiPoolTotal(benefitPool.getAgentSysIndiPoolTotal().add(agenIndi));
			// 保存记录
			gjfBenefitInfoDao.save(benefitHistory1);
			gjfBenefitInfoDao.save(benefitHistory4);
			gjfBenefitInfoDao.save(benefitHistory5);
			gjfBenefitInfoDao.save(benefitHistory6);
			gjfBenefitInfoDao.save(benefitHistory7);
			gjfBenefitInfoDao.save(benefitHistory8);

		} else {
			// 添加商家池记录
			GjfBenefitHistory benefitHistory2 = new GjfBenefitHistory(null, poolBenefit,
					benefitPool.getMerchantSysPoolCur(), benefitPool.getMerchantSysPoolCur().add(poolBenefit),
					new BigDecimal(0.00), curDate, "9", null, curDate, "1");
			//记录是否活跃
			benefitHistory2.setIsActivity(storeActivity);
			// 1.3判断客户平台+客户分红池=客户系统让利差
			subPlaSys = subPlaSys.subtract(poolBenefit);
			// 添加平台池记录
			GjfBenefitHistory benefitHistory3 = new GjfBenefitHistory(null, subPlaSys,
					benefitPool.getPlatformSysPoolCur(), benefitPool.getPlatformSysPoolCur().add(subPlaSys),
					new BigDecimal(0.00), curDate, "10", null, curDate, "1");

			// 当前商家系统资金池
			benefitPool.setMerchantSysPoolCur(benefitPool.getMerchantSysPoolCur().add(poolBenefit));
			// 商家系统资金池累计金额
			benefitPool.setMerchantSysPoolTotal(benefitPool.getMerchantSysPoolTotal().add(poolBenefit));
			// 当前平台系统资金池
			benefitPool.setPlatformSysPoolCur(benefitPool.getPlatformSysPoolCur().add(subPlaSys));
			// 平台系统资金池累计金额
			benefitPool.setPlatformSysPoolTotal(benefitPool.getPlatformSysPoolTotal().add(subPlaSys));
			// 保存记录
			gjfBenefitInfoDao.save(benefitHistory2);
			gjfBenefitInfoDao.save(benefitHistory3);
		}
		directBenefit[2] = subPlaSys;
		// 添加资金池流水
		gjfBenefitInfoDao.update(benefitPool);
		// 返回信息
		return directBenefit;
	}

	/**
	 * @描述 根据类型查询让利配置信息
	 * @author Karhs
	 * @date 2017年3月7日
	 * @param findType
	 * @return
	 */
	public GjfBenefitInfo findByType(String findType) {
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("ratioType", findType);
		return gjfBenefitInfoDao.query(GjfBenefitInfo.class, attrs);
	}

	/**
	 * @描述 添加让利给个代记录
	 * @author Karhs
	 * @throws ParseException
	 * @date 2017年3月7日
	 */
	public void addIndiBenefit(String merchantsMobile, String tradeNo, double buyMoney, double benefitMoney,String type) {
		//创建查询条件map
		Map<String, Object> attrs = new HashMap<String, Object>();
		//商户电话号码
		attrs.put("mobile", merchantsMobile);
		//查询用户信息
		GjfMemberInfo gjfMemberInfo = gjfBenefitInfoDao.query(GjfMemberInfo.class, attrs);
	    //获取全部用户信息
		List<String> listArr = gjfBenefitInfoDao.findAllMemberType();
		//如果用户信息不为空遍历
		if (null != listArr && listArr.size() > 0) {
			Map<String, String[]> dataMap = new HashMap<String, String[]>();
			for (String str : listArr) {
				if (StringUtil.isNotBlank(str)) {
					String[] strArr = str.split(",");
					String memberId = strArr[0];
					dataMap.put(memberId, strArr);
				}
			}
			//获取个代信息
			List<String> personal = findPersonalIds(gjfMemberInfo.getId().toString(), dataMap);
			//如果个代信息不为空
			if (null != personal && personal.size() > 0) {
				//遍历集合
				for (String personalId : personal) {
					//获取用户信息
					GjfMemberInfo memberInfo = (GjfMemberInfo) gjfBenefitInfoDao.get(Long.parseLong(personalId),
							GjfMemberInfo.class.getName());
					//创建记录奖励记录
					GjfMemberTradeDiviHistory trade=new GjfMemberTradeDiviHistory();
					//计算奖励金额
					BigDecimal directMoney=new BigDecimal(0);
					//记录比例
					double tradeRatio=0.00;
					//如果是会员
					if("0".equals(type)){
						//获取配置信息
						GjfBenefitInfo benefitInfo=findByType("0");
						//如果配置信息为空
						if(!BeanUtil.isValid(benefitInfo)){
							continue;
						}
						//计算奖励金额
						directMoney=new BigDecimal(buyMoney).multiply(new BigDecimal(benefitInfo.getAgentIndiRatio()).divide(new BigDecimal(100))).setScale(2, BigDecimal.ROUND_HALF_DOWN);
						//交易比例
						tradeRatio=benefitInfo.getAgentIndiRatio();
						//附言
						trade.setTradeTrmo("个代会员奖励");
					}else{
						//获取配置信息
						GjfBenefitInfo benefitInfo=findByType("1");
						//如果配置信息为空
						if(!BeanUtil.isValid(benefitInfo)){
							continue;
						}
						//计算奖励金额
						directMoney=new BigDecimal(benefitMoney).multiply(new BigDecimal(benefitInfo.getAgentIndiRatio()).divide(new BigDecimal(100))).setScale(2, BigDecimal.ROUND_HALF_DOWN);
						//交易比例
						tradeRatio=benefitInfo.getAgentIndiRatio();
						//附言
						trade.setTradeTrmo("个代商家奖励");
						
					}
					
					//用户id
					trade.setMemberId(memberInfo);
					//交易单号
					trade.setTradeNo(DateHelper.dataToString(new Date(), "yyyyMMddHHmmss")+ String.valueOf(System.currentTimeMillis()).substring(0, 5));
					//交易金额
					trade.setTradeMoney(directMoney);
					//交易比例
					trade.setTradeRatio(new BigDecimal(tradeRatio));
					//添加时间
					trade.setAddTime(new Date());
					//交易类型
					trade.setTradeType("6");
					//交易状态
					trade.setTradeStatus("1");
					//保存记录
					gjfBenefitInfoDao.save(trade);
					//给用户添加
					memberInfo.setBalanceMoney(memberInfo.getBalanceMoney().add(directMoney));
					memberInfo.setWithdrawalMoney(memberInfo.getWithdrawalMoney().add(directMoney));
					//添加代理奖励金额
					memberInfo.setAgentTotalMoney(memberInfo.getAgentTotalMoney().add(directMoney));
					//添加个代记录
					GjfMemberTradeIndi memberTradeIndi = new GjfMemberTradeIndi(null, tradeNo, memberInfo,
							gjfMemberInfo, new BigDecimal(buyMoney).setScale(2, BigDecimal.ROUND_DOWN),
							new BigDecimal(benefitMoney).setScale(2, BigDecimal.ROUND_DOWN), new Date());
					//保存记录
					gjfBenefitInfoDao.save(memberTradeIndi);
				}
			}
		}
	}

	/**
	 * 返回个代们的id list，如果为空则找不到个代们
	 * 
	 * @param memberId
	 * @param map
	 * @return
	 * @throws ParseException
	 */
	public static List<String> findPersonalIds(String memberId, Map<String, String[]> map) {

		// 会员不存在
		if (memberId == null || memberId.length() == 0) {
			return null;
		}

		// 列表为空，没得聊了
		if (map == null || map.isEmpty()) {
			return null;
		}

		// 该会员找不到则返回
		String[] theMember = map.get(memberId);
		if (theMember == null) {
			return null;
		}

		// 默认加入已加载队列
		Map<String, String[]> foundMap = new HashMap<String, String[]>();
		foundMap.put(memberId, theMember);

		// 该会员上级id
		String fatherId = theMember[1];
		if (fatherId.equals("0")) {
			return null;
		}
		List<String> personal = findPersonalIds(fatherId, map, foundMap, null);
		return personal;
	}

	/**
	 * 
	 * @param memberId
	 * @param map
	 * @param foundMap
	 * @param personal
	 * @return
	 * @throws ParseException
	 */
	private static List<String> findPersonalIds(String memberId, Map<String, String[]> map,
			Map<String, String[]> foundMap, List<String> personal) {

		// 该会员找不到则返回
		String[] theMember = map.get(memberId);
		if (theMember == null) {
			return personal;
		}

		// 已经找到个代们了
		if (personal != null && personal.size() == 2) {
			return personal;
		}

		// 如果已加载队列里已经有这个会员了，说明已经循环了
		if (foundMap.containsKey(memberId)) {
			return personal;
		}

		// 默认加入已加载队列
		foundMap.put(memberId, theMember);

		// 如果是个贷，1代表个贷
		String isPersonal = theMember[2];

		if ("1".equals(isPersonal) && !theMember[3].equals("0") && !theMember[4].equals("0")) {
			// 判断代理是否过期
			int day1 = -1;
			int day2 = 1;
			String startDate = theMember[3];
			String endDate = theMember[4];
			String status = theMember[5];
			String isDel = theMember[6];
			if (status.equals("1") && isDel.equals("1")) {
				try {
					String curDate = DateHelper.dataToString(new Date(), "yyyy-MM-dd");
					day1 = DateHelper.daysBetween(startDate, curDate);
					day2 = DateHelper.daysBetween(endDate, curDate);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				if (day1 >= 0 && day2 <= 0) {
					if (personal == null) {
						personal = new ArrayList<String>();
					}
					personal.add(memberId);
				}
			}
		}
		// 该会员上级id
		String fatherId = theMember[1];
		if (fatherId != null && fatherId.length() > 0 && !fatherId.equals("0")) {
			personal = findPersonalIds(fatherId, map, foundMap, personal);
		}
		return personal;
	}

	/*
	 * 用户每消费一笔，就会计算一次其所获得的分红权（可在后台设置）
	 * 
	 * @see cc.messcat.gjfeng.service.GjfBenefitNotifyService#
	 * updateMemberBenefitNotify(java.lang.String, java.lang.String,
	 * java.lang.Double, java.lang.Double)
	 */
	@Override
	public BigDecimal[] updateMemberBenefitNumCanSetInBack(String membersMobile, String memberType,
			Double consumptionMoney, Double totalBenefit, String tradeNo, BigDecimal subSysPla, String merchantGrade) {
		//记录分红权
		BigDecimal diviNum = new BigDecimal(0.000000);
		//记录会员是否活跃
		String memActivity="0";
		//记录商家是否活跃
		String storeActivity="0";
		// 如果是计算用户分红权
		if (memberType.equals("0")) {
			// 总消费金额
			double cumulativeMoney = 0.00;
			// 创建查询条件map
			Map<String, Object> attrs = new HashMap<String, Object>();
			// 用户电话号码
			attrs.put("mobile", membersMobile);
			// 获取用户信息
			GjfMemberInfo gjfMemberInfo = gjfBenefitInfoDao.query(GjfMemberInfo.class, attrs);
            //活跃是否活跃
			memActivity=gjfMemberInfo.getIsActiveMember();
			// 获取用户当前累计消费金额
			Double bfCumulativeMoney =0.00;
			//如果是一类
			if("1".equals(merchantGrade)){
				//获取一类消费金额
				bfCumulativeMoney=gjfMemberInfo.getMerchantFirstCumulativeMoney().doubleValue();
				//一类待领消费金额
				gjfMemberInfo.setMerchantFirstCousumptionMoney(gjfMemberInfo.getMerchantFirstCousumptionMoney().add(new BigDecimal(consumptionMoney)));
				//一类总消费金额
				gjfMemberInfo.setMerchantFirstCumulativeMoney(gjfMemberInfo.getMerchantFirstCumulativeMoney().add(new BigDecimal(consumptionMoney)));
				// 记录用户的总消费金额
				cumulativeMoney = gjfMemberInfo.getMerchantFirstCumulativeMoney().doubleValue();
			}
			
			//如果是二类
			if("2".equals(merchantGrade)){
				//获取二类类消费金额
				bfCumulativeMoney=gjfMemberInfo.getMerchantSecondCumulativeMoney().doubleValue();
				//二类待领消费金额
				gjfMemberInfo.setMerchantSecondCousumptionMoney(gjfMemberInfo.getMerchantSecondCousumptionMoney().add(new BigDecimal(consumptionMoney)));
				//二类总消费金额
				gjfMemberInfo.setMerchantSecondCumulativeMoney(gjfMemberInfo.getMerchantSecondCumulativeMoney().add(new BigDecimal(consumptionMoney)));
				// 记录用户的总消费金额
				cumulativeMoney = gjfMemberInfo.getMerchantSecondCumulativeMoney().doubleValue();
			}
			//如果是三类
			if("3".equals(merchantGrade)){
				//获取三类消费金额
				bfCumulativeMoney=gjfMemberInfo.getMerchantThreeCumulativeMoney().doubleValue();
				//三类待领消费金额
				gjfMemberInfo.setMerchantThreeCousumptionMoney(gjfMemberInfo.getMerchantThreeCousumptionMoney().add(new BigDecimal(consumptionMoney)));
				//三类总消费金额
				gjfMemberInfo.setMerchantThreeCumulativeMoney(gjfMemberInfo.getMerchantThreeCumulativeMoney().add(new BigDecimal(consumptionMoney)));
				// 记录用户的总消费金额
				cumulativeMoney = gjfMemberInfo.getMerchantThreeCumulativeMoney().doubleValue();
			}
			
			// 记录用户的让利金额
			BigDecimal benefitMoney = new BigDecimal(totalBenefit).setScale(2, BigDecimal.ROUND_UP);
			// 给用户添加总消费金额
			gjfMemberInfo.setCumulativeMoney(gjfMemberInfo.getCumulativeMoney().add(new BigDecimal(consumptionMoney)));
			// 给用户添加积分
			gjfMemberInfo
					.setConsumptionMoney(gjfMemberInfo.getConsumptionMoney().add(new BigDecimal(consumptionMoney)));

			// 根据用户消费前累计消费金额获取分分红权设置信息
			List<GjfSetDividends> diviList = gjfBenefitInfoDao.findDividendsDate(bfCumulativeMoney);
			// 判断是否存在数据
			if (diviList.size() > 0) {
				// 获取设置信息
				GjfSetDividends dividends = diviList.get(0);
				// 当用户消费总额小于等于设置的最大消费总额时
				if (cumulativeMoney <= dividends.getConsumptionMax().doubleValue()) {
					// 计算产生的分红权
					BigDecimal a = new BigDecimal(consumptionMoney).divide(dividends.getConsumption(), 6,
							BigDecimal.ROUND_DOWN);
					// 记录分红权
					diviNum = a;
					// 添加分红权记录
					GjfMemberTradeDivi diviHistory = new GjfMemberTradeDivi(null, gjfMemberInfo, tradeNo + "-1",
							diviNum, benefitMoney, new BigDecimal(0.00), new BigDecimal(consumptionMoney), new Date(),
							null, null, "0", "1", "0", "");
					// 设置token
					diviHistory.setToken(Sha256.getSha256Hash(diviHistory.getDiviNo(), diviHistory.getDiviStatus(),
							CommonStatus.SIGN_KEY_NUM));
					// 保存记录
					gjfBenefitInfoDao.save(diviHistory);

				} else {// 当用户消费总额大于设置的最大消费总额时
					// 查看设置的区间的最大消费金额小于消费金额的数据个数
					List<GjfSetDividends> divDateSize = gjfBenefitInfoDao
							.findDividendiByCumulativeMoney(cumulativeMoney, "1");
					// 如果数据不为空
					if (divDateSize.size() > 0) {
						// 遍历获取的数据
						for (int i = 0; i < divDateSize.size(); i++) {
							// 如果是第一个数据
							if (i == 0) {
								// 消费前金额小于设置数据最大金额
								if (bfCumulativeMoney < divDateSize.get(i).getConsumptionMax().doubleValue()) {
									// 计算分红权
									BigDecimal b = new BigDecimal(
											divDateSize.get(i).getConsumptionMax().doubleValue() - bfCumulativeMoney)
													.divide(divDateSize.get(i).getConsumption(), 6,
															BigDecimal.ROUND_DOWN);
									// 添加分红权数
									diviNum = diviNum.add(b);
								}
								// 如果不是第一条数据也不是最后一条数据
							} else if (i != 0 && i != divDateSize.size() - 1) {
								// 消费前金额小于上一条设置数据的最大金额
								if (bfCumulativeMoney < divDateSize.get(i - 1).getConsumptionMax().doubleValue()) {
									// 计算分红权
									BigDecimal b = new BigDecimal(divDateSize.get(i).getConsumptionMax().doubleValue()
											- divDateSize.get(i - 1).getConsumptionMax().doubleValue()).divide(
													divDateSize.get(i).getConsumption(), 6, BigDecimal.ROUND_DOWN);
									// 添加分红权数
									diviNum = diviNum.add(b);
								}
								// 如果消费前金额大于上一条数据的最大金额
								if (bfCumulativeMoney > divDateSize.get(i - 1).getConsumptionMax().doubleValue()) {
									// 计算分红权
									BigDecimal b = new BigDecimal(
											divDateSize.get(i).getConsumptionMax().doubleValue() - bfCumulativeMoney)
													.divide(divDateSize.get(i).getConsumption(), 6,
															BigDecimal.ROUND_DOWN);
									// 添加分红权数
									diviNum = diviNum.add(b);
								}
								// 如果为最后一条数据
							} else {
								// 计算分红权
								BigDecimal b = new BigDecimal(
										cumulativeMoney - divDateSize.get(i).getConsumptionMin().doubleValue())
												.divide(divDateSize.get(i).getConsumption(), 6, BigDecimal.ROUND_DOWN);
								// 添加分红权数
								diviNum = diviNum.add(b);
							}
						}

						// 添加记录
						GjfMemberTradeDivi diviHistory = new GjfMemberTradeDivi(null, gjfMemberInfo, tradeNo + "-1",
								diviNum, benefitMoney, new BigDecimal(0.00), new BigDecimal(consumptionMoney),
								new Date(), null, null, "0", "1", "0", "");
						// 设置token
						diviHistory.setToken(Sha256.getSha256Hash(diviHistory.getDiviNo(), diviHistory.getDiviStatus(),
								CommonStatus.SIGN_KEY_NUM));
						// 保存数据
						gjfBenefitInfoDao.save(diviHistory);
					}
				}
			}
			
			//如果是一类
			if("1".equals(merchantGrade)){
				gjfMemberInfo.setMerchantFirstDiviNum(gjfMemberInfo.getMerchantFirstDiviNum().add(diviNum));
			}
			//如果是二类
			if("2".equals(merchantGrade)){
				gjfMemberInfo.setMerchantSecondDiviNum(gjfMemberInfo.getMerchantSecondDiviNum().add(diviNum));
			}
			//如果是三类
			if("3".equals(merchantGrade)){
				gjfMemberInfo.setMerchantThreeDiviNum(gjfMemberInfo.getMerchantThreeDiviNum().add(diviNum));
			}
			
			// 给用户添加分红权
			gjfMemberInfo
					.setDividendsNum(gjfMemberInfo.getDividendsNum().add(diviNum).setScale(6, BigDecimal.ROUND_DOWN));
			// 更新用户分红权
			gjfBenefitInfoDao.update(gjfMemberInfo);

		} else {
			// 创建查询条件map
			Map<String, Object> storeAttrs = new HashMap<String, Object>();
			// 获取商户电话号码
			storeAttrs.put("memberId.mobile", membersMobile);
			// 获取商户店铺信息
			GjfStoreInfo gjfStoreInfo = gjfBenefitInfoDao.query(GjfStoreInfo.class, storeAttrs);
			//记录商家是否活跃
			storeActivity=gjfStoreInfo.getIsActivityStore();
			// 记录商户消费前分红权
			double bfTotalBenefitMoney = gjfStoreInfo.getStoreBenefitTotalMoney().doubleValue();
			// 让利金额
			BigDecimal benefitMoney = new BigDecimal(totalBenefit).setScale(2, BigDecimal.ROUND_UP);
			// 给商户添加销售金额
			gjfStoreInfo.setStoreSaleTotalMoney(
					gjfStoreInfo.getStoreSaleTotalMoney().add(new BigDecimal(consumptionMoney)));
			// 给商户添加让利金额
			gjfStoreInfo.setStoreBenefitTotalMoney(gjfStoreInfo.getStoreBenefitTotalMoney().add(benefitMoney));
			// 给商户添加待分红金额
			gjfStoreInfo
					.setStoreDividendsTotalMoneyBla(gjfStoreInfo.getStoreDividendsTotalMoneyBla().add(benefitMoney));
			// 商户总让利金额
			double totalBenefitMoney = gjfStoreInfo.getStoreBenefitTotalMoney().doubleValue();

			// 根据用户累计消费金额获取分分红权设置信息
			List<GjfSetDividends> diviList = gjfBenefitInfoDao.findDividendsDate(bfTotalBenefitMoney);
			// 判断是否存在数据
			if (diviList.size() > 0) {
				// 获取设置数据
				GjfSetDividends dividends = diviList.get(0);
				// 当用户消费总额小于等于设置的最大消费总额时
				if (totalBenefitMoney <= dividends.getConsumptionMax().doubleValue()) {
					// 计算商户分红权
					BigDecimal a = new BigDecimal(benefitMoney.doubleValue()).divide(dividends.getConsumption(), 6,
							BigDecimal.ROUND_DOWN);
					// 记录分红权
					diviNum = a;
					
					// 当用户消费总额大于设置的最大消费总额时
				} else {
					// 查看设置的区间的最大消费金额小于消费金额的数据个数
					List<GjfSetDividends> divDateSize = gjfBenefitInfoDao
							.findDividendiByCumulativeMoney(totalBenefitMoney, "1");
					// 获取数据大于零
					if (divDateSize.size() > 0) {
						// 遍历集合
						for (int i = 0; i < divDateSize.size(); i++) {
							// 如果为第一个数据
							if (i == 0) {
								// 消费前的让利总金额大于设置数据最大金额
								if (bfTotalBenefitMoney < divDateSize.get(i).getConsumptionMax().doubleValue()) {
									// 计算分红权
									BigDecimal b = new BigDecimal(
											divDateSize.get(i).getConsumptionMax().doubleValue() - bfTotalBenefitMoney)
													.divide(divDateSize.get(i).getConsumption(), 6,
															BigDecimal.ROUND_DOWN);
									// 记录分红权
									diviNum = diviNum.add(b);
								}
								// 如果不是第一条数据
							} else if (i != 0 && i != divDateSize.size() - 1) {
								// 消费前的让利总额小于上一条记录的最大金额
								if (bfTotalBenefitMoney < divDateSize.get(i - 1).getConsumptionMax().doubleValue()) {
									// 计算分红权
									BigDecimal b = new BigDecimal(divDateSize.get(i).getConsumptionMax().doubleValue()
											- divDateSize.get(i - 1).getConsumptionMax().doubleValue()).divide(
													divDateSize.get(i).getConsumption(), 6, BigDecimal.ROUND_DOWN);
									// 记录分红权
									diviNum = diviNum.add(b);
								}
								// 如果消费前的让利总额数据大于上一条记录的最大金额
								if (bfTotalBenefitMoney > divDateSize.get(i - 1).getConsumptionMax().doubleValue()) {
									// 计算分红权
									BigDecimal b = new BigDecimal(
											divDateSize.get(i).getConsumptionMax().doubleValue() - bfTotalBenefitMoney)
													.divide(divDateSize.get(i).getConsumption(), 6,
															BigDecimal.ROUND_DOWN);
									// 添加分红权
									diviNum = diviNum.add(b);
								}
								// 如果为最后一条数据
							} else {
								// 计算分红权
								BigDecimal b = new BigDecimal(
										totalBenefitMoney - divDateSize.get(i).getConsumptionMin().doubleValue())
												.divide(divDateSize.get(i).getConsumption(), 6, BigDecimal.ROUND_DOWN);
								// 添加分红权
								diviNum = diviNum.add(b);
							}
						}

					}
				}
			}

			// 添加记录
			GjfMemberTradeDivi diviHistory = new GjfMemberTradeDivi(null, gjfStoreInfo.getMemberId(), tradeNo + "-2",
					diviNum, benefitMoney, new BigDecimal(0.00), new BigDecimal(consumptionMoney), new Date(), null,
					null, "0", "3", "1", "");
			// 设置token
			diviHistory.setToken(Sha256.getSha256Hash(diviHistory.getDiviNo(), diviHistory.getDiviStatus(),
					CommonStatus.SIGN_KEY_NUM));
			// 保存记录
			gjfBenefitInfoDao.save(diviHistory);
			// 更商家添加分红权
			gjfStoreInfo.setStoreDividendsNum(
					gjfStoreInfo.getStoreDividendsNum().add(diviNum).setScale(6, BigDecimal.ROUND_DOWN));

			// 更新商家数据
			gjfBenefitInfoDao.update(gjfStoreInfo);
		}

		// 修改商家让利表记录的分红权额
		Map<String, Object> attrsBenefit = new HashMap<String, Object>();
		Map<String, Object> propsBenefit = new HashMap<String, Object>();
		attrsBenefit.put("tradeNo", tradeNo);
		if (memberType.equals("0")) {
			propsBenefit.put("memberDividendsNum", diviNum);
		} else {
			propsBenefit.put("merchantsDividendsNum", diviNum);
		}
		gjfBenefitInfoDao.update(GjfMemberTradeBenefit.class, propsBenefit, attrsBenefit);

		// 修改资金池
		BigDecimal[] directBenefit = updateBenefitPool(memberType, totalBenefit, subSysPla, merchantGrade,memActivity,storeActivity,consumptionMoney);
		return directBenefit;
	}

	/**
	 * 获取上周分红单价平均值
	 */
	public BigDecimal getUnitAveragePrice(String type) {
		BigDecimal totalUnitPrice = new BigDecimal(0.00);
		for (int i = 1; i <= 7; i++) {
			Calendar calendar1 = Calendar.getInstance();
			int dayOfWeek = calendar1.get(Calendar.DAY_OF_WEEK) - 1;
			int offset1 = i - dayOfWeek;
			calendar1.add(Calendar.DATE, offset1 - 7);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String lastTime = sdf.format(calendar1.getTime());
			BigDecimal unitPrice = gjfBenefitInfoDao.findUnitPriceByTime(type, lastTime);
			totalUnitPrice = totalUnitPrice.add(unitPrice);
		}
		return totalUnitPrice;
	}

	/**
	 * 获取全部分红权设置信息
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ResultVo findAllDividends(Integer pageNo, Integer pageSize) {
		Map<String, Object> attr = new HashMap<>();
		attr.put("status", "1");
		return new ResultVo(200, "查询成功", gjfBenefitInfoDao.queryList(GjfSetDividends.class, (pageNo - 1) * pageSize,
				pageSize, "id", "asc", attr));
	}

	/**
	 * 根据id获取分红权设置信息
	 */
	@Override
	public ResultVo findDividendsById(Long divId) {
		Map<String, Object> attr = new HashMap<>();
		attr.put("status", "1");
		attr.put("id", divId);
		return new ResultVo(200, "查询成功", gjfBenefitInfoDao.query(GjfSetDividends.class, attr));
	}

	/**
	 * 添加分红权设置信息
	 * 
	 * @param setDiv
	 * @return
	 */
	@Override
	public ResultVo addDividensData(GjfSetDividends setDiv) {

		gjfBenefitInfoDao.save(setDiv);
		return new ResultVo(200, "添加成功", null);
	}

	/**
	 * 删除分红权设置信息
	 * 
	 * @param divId
	 * @return
	 */
	@Override
	public ResultVo removeDividensData(Long divId) {
		Map<String, Object> attr = new HashMap<>();
		attr.put("status", "1");
		attr.put("id", divId);
		GjfSetDividends gjfSetDividends = gjfBenefitInfoDao.query(GjfSetDividends.class, attr);
		gjfBenefitInfoDao.delete(gjfSetDividends);
		return new ResultVo(200, "添加成功", null);
	}

	/**
	 * 修改分红权设置信息
	 * 
	 * @param setDiv
	 * @return
	 */
	@Override
	public ResultVo modifyDividensData(GjfSetDividends setDiv) {
		gjfBenefitInfoDao.update(setDiv);
		return new ResultVo(200, "修改成功", null);
	}

	/**
	 * 查询最近七天之内用户的让利数据
	 * 
	 * @param mobile
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public ResultVo findBenefitByTime(String mobile) {
		List list = (List) gjfBenefitInfoDao.findBenefitByTime(mobile).getResult();
		MemberTradeBenefitVo benefit = null;
		if (list.size() > 0) {
			benefit = (MemberTradeBenefitVo) list.get(0);
		}
		return new ResultVo(200, "查询成功", benefit);
	}

	/**
	 * 用户确认让利提示信息
	 * 
	 * @param tradeNo
	 * @return
	 */
	@Override
	public ResultVo modifyBenefitConfirmStatus(String tradeNo) {
		Map<String, Object> attr = new HashMap<>();
		attr.put("tradeNo", tradeNo);
		GjfMemberTradeBenefit benefit = gjfBenefitInfoDao.query(GjfMemberTradeBenefit.class, attr);
		benefit.setConfirmStatus("1");
		gjfBenefitInfoDao.update(benefit);
		GjfMemberInfo info = benefit.getMemberId();
		info.setIsConfirm("1");
		gjfBenefitInfoDao.update(info);
		return new ResultVo(200, "修改成功", null);
	}

	/**
	 * 转移分红金额
	 */
	@Override
	public ResultVo modifyTransferMoney(String account) {
		// 查询用户信息
		Map<String, Object> attrs = new HashMap<>();
		attrs.put("mobile", account);
		GjfMemberInfo memberInfo = gjfBenefitInfoDao.query(GjfMemberInfo.class, attrs);
		if (!BeanUtil.isValid(memberInfo)) {
			return new ResultVo(400, "用户不存在", null);
		}
		// 计算用户可转移金额
		BigDecimal totalTransferMoney = memberInfo.getDividendsRewardMoney().add(memberInfo.getIndiRewardMoney())
				.add(memberInfo.getRecommendRewardMoney());
		if (totalTransferMoney.doubleValue() == 0) {
			return new ResultVo(400, "可转移金额为零", null);
		}
		// 创建历史记录
		GjfTransferHistory tansferHistory = new GjfTransferHistory();
		tansferHistory.setBalanceBf(memberInfo.getBalanceMoney());
		tansferHistory.setIsDel("1");
		tansferHistory.setMemberId(memberInfo);
		tansferHistory.setAddTime(new Date());
		tansferHistory.setTransferDividendsMoney(memberInfo.getDividendsRewardMoney());
		tansferHistory.setTransferRecomMoney(memberInfo.getRecommendRewardMoney());
		tansferHistory.setTransferIndiMoney(memberInfo.getIndiRewardMoney());
		// 计算税费
		BigDecimal pundageMoney = totalTransferMoney.multiply(new BigDecimal(0.1)).setScale(2, BigDecimal.ROUND_DOWN);

		// 转移到余额的金额
		BigDecimal fransferToBalanceMoney = totalTransferMoney.subtract(pundageMoney);
		// 把钱转移到余额
		memberInfo.setBalanceMoney(memberInfo.getBalanceMoney().add(fransferToBalanceMoney));
		memberInfo.setWithdrawalMoney(memberInfo.getWithdrawalMoney().add(fransferToBalanceMoney));
		memberInfo.setDividendsRewardMoney(new BigDecimal(0.00));
		memberInfo.setRecommendRewardMoney(new BigDecimal(0.00));
		memberInfo.setIndiRewardMoney(new BigDecimal(0.00));

		// 记录转移后余额
		tansferHistory.setPoundage(pundageMoney);
		tansferHistory.setBalanceAf(memberInfo.getBalanceMoney());
		tansferHistory.setTransferMoney(totalTransferMoney);
		tansferHistory.setTransferAfterTax(fransferToBalanceMoney);
		// 更新用户信息
		gjfBenefitInfoDao.update(memberInfo);
		gjfBenefitInfoDao.save(tansferHistory);
		return new ResultVo(200, "转移成功", null);
	}

	/**
	 * 调整用户每月是否参与分红
	 * 
	 * @return
	 */
	@SuppressWarnings({ "unchecked" })
	@Override
	public ResultVo modifyMemberDiviByMonth() {
		Map<String, Object> attrs = new HashMap<>();
		attrs.put("status", "1");
		List<GjfMemberInfo> memList = gjfBenefitInfoDao.queryList(GjfMemberInfo.class, "id", "asc", attrs);
		if (memList.size() > 0) {
			for (GjfMemberInfo member : memList) {
				// 获取用户最早消费时间
				Date firstConsumptionTime = member.getFirstConsumptionTime();
				// 获取当前时间
				Date dataTime = new Date();
				if (BeanUtil.isValid(firstConsumptionTime)) {
					// 计算两个时间差
					long days = (dataTime.getTime() - firstConsumptionTime.getTime()) / (1000 * 60 * 60 * 24);
					if (days > 60) {
						// 获取用户消费次数
						List<GjfMemberConsumptiomNum> list = gjfBenefitInfoDao.findMemberCousumptionNum(member.getId());
						// 如果消费记录不为空
						if (BeanUtil.isValid(list) && list.size() == 1) {
							GjfMemberConsumptiomNum consumptionNum = list.get(0);
							member.setIsDivi("0");
							// 如果线上消费和线下消费都消费三笔
							if (consumptionNum.getShopConsumptionNum() >= 3) {
								member.setIsDivi("1");
							}
							// 如果线上消费小于3笔线下消费大于6笔
							/*
							 * if (consumptionNum.getShopConsumptionNum() < 3 &&
							 * consumptionNum.getBenefitNum() >= 6) { //
							 * 统计用户当月的提现金额 BigDecimal wMoney =
							 * gjfBenefitInfoDao.sumMemberBenefitMoney("0",
							 * member.getId()); // 统计用户当月让利金额 BigDecimal bMoney
							 * = gjfBenefitInfoDao.sumMemberBenefitMoney("1",
							 * member.getId()); // 如果当月提现金额为零 if
							 * (wMoney.doubleValue() == 0) {
							 * member.setIsDivi("1"); } else { //
							 * 如果提现金额不为零并且当月让利金额大于提现金额的40%，则可参与分红 if
							 * (bMoney.doubleValue() > (wMoney.multiply(new
							 * BigDecimal(0.4))
							 * .setScale(2,BigDecimal.ROUND_DOWN)).doubleValue()
							 * ) { member.setIsDivi("1"); } } }
							 */
						} else {
							// 获取线上消费记录
							BigInteger orCoount = (BigInteger) gjfBenefitInfoDao
									.findCountCousumtion("0", member.getId()).getResult();
							// 获取线下消费笔数
							// BigInteger beCount = (BigInteger)
							// gjfBenefitInfoDao.findCountCousumtion("1",
							// member.getId())
							// .getResult();

							member.setIsDivi("0");
							// 如果线上消费和线下消费都消费三笔
							if (orCoount.intValue() >= 3) {
								member.setIsDivi("1");
							}
							// 如果线上消费小于3笔线下消费大于6笔
							/*
							 * if (orCoount.intValue() < 3 && beCount.intValue()
							 * >= 6) { // 统计用户当月的提现金额 BigDecimal wMoney =
							 * gjfBenefitInfoDao.sumMemberBenefitMoney("0",
							 * member.getId()); // 统计用户当月让利金额 BigDecimal bMoney
							 * = gjfBenefitInfoDao.sumMemberBenefitMoney("1",
							 * member.getId()); // 如果当月提现金额为零 if
							 * (wMoney.doubleValue() == 0) {
							 * member.setIsDivi("1"); } else { //
							 * 如果提现金额不为零并且当月让利金额大于提现金额的40%，则可参与分红 if
							 * (bMoney.doubleValue() > (wMoney.multiply(new
							 * BigDecimal(0.4))
							 * .setScale(2,BigDecimal.ROUND_DOWN)).doubleValue()
							 * ) { member.setIsDivi("1"); } } }
							 */
						}
						gjfBenefitInfoDao.update(member);
					}
				} else {
					member.setIsDivi("0");
					gjfBenefitInfoDao.update(member);
				}
			}
		}
		return new ResultVo(200, "统计成功", null);
	}

	/**
	 * 领回20%分红权减半50%再减半不变
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ResultVo modifyMemberDeductDivi() {
		Map<String, Object> attrs = new HashMap<>();
		attrs.put("isDel", "1");
		attrs.put("status", "1");
		List<GjfMemberInfo> memList = gjfBenefitInfoDao.queryList(GjfMemberInfo.class, "id", "asc", attrs);
		if (BeanUtil.isValid(memList)) {
			for (GjfMemberInfo member : memList) {

				BigDecimal syDivi = new BigDecimal(0.000000);
				// 計算消費的20%金額
				BigDecimal cousum = member.getCumulativeMoney().multiply(new BigDecimal(0.2)).setScale(2,
						BigDecimal.ROUND_DOWN);
				BigDecimal lastDiviMoney = member.getDividendsTotalMoney().subtract(member.getLastGetBackDiviMoney());
				if (lastDiviMoney.doubleValue() != 0 || cousum.doubleValue() != 0) {
					if (lastDiviMoney.doubleValue() >= cousum.doubleValue()) {
						// 計算扣減后的分紅權
						syDivi = (member.getDividendsNum().subtract(member.getLastDeductDiviNum()))
								.divide(new BigDecimal(2), 6, BigDecimal.ROUND_DOWN);
					}
					// 計算消費的50%金額
					BigDecimal cousumMax = member.getCumulativeMoney().multiply(new BigDecimal(0.5)).setScale(2,
							BigDecimal.ROUND_DOWN);
					if (lastDiviMoney.doubleValue() >= cousumMax.doubleValue()) {
						// 計算扣減后的分紅權
						syDivi = syDivi.divide(new BigDecimal(2), 6, BigDecimal.ROUND_DOWN);
					}
				}

				if (syDivi.doubleValue() != 0) {
					BigDecimal diviMoney = member.getLastDeductDiviNum();
					member.setLastDeductDiviNum(syDivi.add(diviMoney));
					member.setReserveDiviNum(member.getReserveDiviNum().add(member.getDividendsNum().subtract(syDivi))
							.subtract(diviMoney));
					member.setLastGetBackDiviMoney(member.getDividendsTotalMoney());
					member.setDividendsNum(syDivi.add(diviMoney));
					gjfBenefitInfoDao.update(member);
				}

			}
		}
		return new ResultVo(200, "統計成功");
	}

	/**
	 * 添加让利给运营中心
	 * 
	 * @return
	 */
	@Override
	public ResultVo addOperationCenterBenefit(String merchantsMobile, String tradeNo, double buyMoney,
			double benefitMoney) {
		//创建查询条件map
		Map<String, Object> attrs = new HashMap<String, Object>();
		//商户电话号码
		attrs.put("mobile", merchantsMobile);
		//获取用户信息
		GjfMemberInfo gjfMemberInfo = gjfBenefitInfoDao.query(GjfMemberInfo.class, attrs);
		//获取全部用户信息
		List<String> listArr = gjfBenefitInfoDao.findAllMemberType();
		//如果用户信息不为空
		if (null != listArr && listArr.size() > 0) {
		    //创建接收用户map集合
			Map<String, String[]> dataMap = new HashMap<String, String[]>();
			for (String str : listArr) {
				if (StringUtil.isNotBlank(str)) {
					String[] strArr = str.split(",");
					String memberId = strArr[0];
					dataMap.put(memberId, strArr);
				}
			}
			//获取运营中心数据
			List<String> personal = findPersonalIdsOperationsCenter(gjfMemberInfo.getId().toString(), dataMap);
			//如果运营中心数据不为空
			if (null != personal && personal.size() > 0) {
				//遍历集合
				for (String personalId : personal) {
					//获取运营中心用户信息
					GjfMemberInfo memberInfo = (GjfMemberInfo) gjfBenefitInfoDao.get(Long.parseLong(personalId),
							GjfMemberInfo.class.getName());
					//获取配置信息
					GjfBenefitInfo benefitInfo=findByType("0");
					//如果为空
					if(!BeanUtil.isValid(benefitInfo)){
						continue;
					}
					//计算奖励金额
					BigDecimal tradeMoney = new BigDecimal(buyMoney).multiply(new BigDecimal(benefitInfo.getOperationCenterRatio()).divide(new BigDecimal(1000))).setScale(2,
							BigDecimal.ROUND_DOWN);
					//如果用户数据为空设置为零
					if (!BeanUtil.isValid(memberInfo.getOpcenterMoney())) {
						memberInfo.setOpcenterMoney(new BigDecimal(0));
					}
					//如果用户数据为空设置为零
					if (!BeanUtil.isValid(memberInfo.getOpcenterTotalMoney())) {
						memberInfo.setOpcenterTotalMoney(new BigDecimal(0));
					}
					//给用户添加运营中心奖励金额
					memberInfo.setOpcenterMoney(memberInfo.getOpcenterMoney().add(tradeMoney));
					//给用户添加运营中心奖励总金额
					memberInfo.setOpcenterTotalMoney(memberInfo.getOpcenterTotalMoney().add(tradeMoney));
					//更新用户信息
					gjfBenefitInfoDao.update(memberInfo);
					//添加交易记录细心
					GjfMemberTradeOpcenter opcenter = new GjfMemberTradeOpcenter(memberInfo.getId(),
							gjfMemberInfo.getId(), new BigDecimal(buyMoney), new BigDecimal(benefitMoney), tradeMoney,
							tradeNo, "1", "1", new Date());
					//保存记录
					gjfBenefitInfoDao.save(opcenter);
				}
			}
		}
		return null;
	}

	/**
	 * 返回运营们的id list，如果为空则找不到运营中心们
	 * 
	 * @param memberId
	 * @param map
	 * @return
	 * @throws ParseException
	 */
	public static List<String> findPersonalIdsOperationsCenter(String memberId, Map<String, String[]> map) {

		// 会员不存在
		if (memberId == null || memberId.length() == 0) {
			return null;
		}

		// 列表为空，没得聊了
		if (map == null || map.isEmpty()) {
			return null;
		}

		// 该会员找不到则返回
		String[] theMember = map.get(memberId);
		if (theMember == null) {
			return null;
		}

		// 默认加入已加载队列
		Map<String, String[]> foundMap = new HashMap<String, String[]>();
		foundMap.put(memberId, theMember);

		// 该会员上级id
		String fatherId = theMember[1];
		if (fatherId.equals("0")) {
			return null;
		}
		List<String> personal = findPersonalOperationsCenter(fatherId, map, foundMap, null);
		return personal;
	}

	/**
	 * 获取用户运营中心数据（只取一层）
	 * 
	 * @param memberId
	 * @param map
	 * @param foundMap
	 * @param personal
	 * @return
	 * @throws ParseException
	 */
	private static List<String> findPersonalOperationsCenter(String memberId, Map<String, String[]> map,
			Map<String, String[]> foundMap, List<String> personal) {

		// 该会员找不到则返回
		String[] theMember = map.get(memberId);
		if (theMember == null) {
			return personal;
		}

		// 已经找到个代们了
		if (personal != null && personal.size() == 1) {
			return personal;
		}

		// 如果已加载队列里已经有这个会员了，说明已经循环了
		if (foundMap.containsKey(memberId)) {
			return personal;
		}

		// 默认加入已加载队列
		foundMap.put(memberId, theMember);

		// 如果是个贷，1代表个贷
		String isPersonal = theMember[7];

		if ("1".equals(isPersonal)) {
			String status = theMember[5];
			String isDel = theMember[6];
			if (status.equals("1") && isDel.equals("1")) {
				if (personal == null) {
					personal = new ArrayList<String>();
				}
				personal.add(memberId);
			}
		}
		// 该会员上级id
		String fatherId = theMember[1];
		if (fatherId != null && fatherId.length() > 0 && !fatherId.equals("0")) {
			personal = findPersonalOperationsCenter(fatherId, map, foundMap, personal);
		}
		return personal;
	}

	/**
	 * 查看特殊发放人统计记录
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ResultVo findSpecialTotalHistory(Integer pageNo, Integer pageSize) {
		Map<String, Object> attrs = new HashMap<>();
		List<GjfspecialMemberTradeDivi> list = gjfBenefitInfoDao.queryList(GjfspecialMemberTradeDivi.class, "addTime",
				"desc", attrs);
		Pager pager = new Pager(pageSize, pageNo,
				(int) gjfBenefitInfoDao.queryTotalRecord(GjfspecialMemberTradeDivi.class, attrs), list);
		return new ResultVo(200, "查询成", pager);
	}

	/**
	 * 获取特殊发放人记录
	 */
	@Override
	public ResultVo findSpecialTradeDiviHistory(Integer pageNo, Integer pageSize, String addTime, Long memId,
			String type) {
		Pager pager = gjfBenefitInfoDao.findSpecialMemberTradeDiviHistory(pageNo, pageSize, addTime, memId, type);
		return new ResultVo(200, "查询成功", pager);
	}

}
