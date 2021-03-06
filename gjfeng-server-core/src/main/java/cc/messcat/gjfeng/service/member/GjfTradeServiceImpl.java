package cc.messcat.gjfeng.service.member;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.shiro.crypto.hash.Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import cc.messcat.gjfeng.common.bean.Pager;
import cc.messcat.gjfeng.common.constant.CommonProperties;
import cc.messcat.gjfeng.common.constant.CommonStatus;
import cc.messcat.gjfeng.common.exception.MyException;
import cc.messcat.gjfeng.common.pay.wechat.weixin.popular.api.TokenAPI;
import cc.messcat.gjfeng.common.pay.wechat.weixin.popular.bean.Token;
import cc.messcat.gjfeng.common.util.BeanUtil;
import cc.messcat.gjfeng.common.util.BeanUtilsEx;
import cc.messcat.gjfeng.common.util.DateHelper;
import cc.messcat.gjfeng.common.util.ObjValid;
import cc.messcat.gjfeng.common.util.RandUtil;
import cc.messcat.gjfeng.common.util.Sha256;
import cc.messcat.gjfeng.common.util.StringUtil;
import cc.messcat.gjfeng.common.vo.app.MemberBankVo;
import cc.messcat.gjfeng.common.vo.app.MemberTradeBenefitVo;
import cc.messcat.gjfeng.common.vo.app.MemberTradeDiviVo;
import cc.messcat.gjfeng.common.vo.app.MemberTradeVo;
import cc.messcat.gjfeng.common.vo.app.ResultVo;
import cc.messcat.gjfeng.common.wechat.popular.support.MessageManager;
import cc.messcat.gjfeng.config.WaixiConfig;
import cc.messcat.gjfeng.dao.benefit.GjfBenefitInfoDao;
import cc.messcat.gjfeng.dao.member.GjfMemberInfoDao;
import cc.messcat.gjfeng.dao.trade.GjfTradeDao;
import cc.messcat.gjfeng.entity.GjfAccessToken;
import cc.messcat.gjfeng.entity.GjfAddressArea;
import cc.messcat.gjfeng.entity.GjfAddressCity;
import cc.messcat.gjfeng.entity.GjfAddressProvince;
import cc.messcat.gjfeng.entity.GjfBankInfo;
import cc.messcat.gjfeng.entity.GjfBenefitHistory;
import cc.messcat.gjfeng.entity.GjfBenefitInfo;
import cc.messcat.gjfeng.entity.GjfBenefitPool;
import cc.messcat.gjfeng.entity.GjfFhTreasureInfo;
import cc.messcat.gjfeng.entity.GjfMemberBank;
import cc.messcat.gjfeng.entity.GjfMemberInfo;
import cc.messcat.gjfeng.entity.GjfMemberMerge;
import cc.messcat.gjfeng.entity.GjfMemberTrade;
import cc.messcat.gjfeng.entity.GjfMemberTradeBenefit;
import cc.messcat.gjfeng.entity.GjfMemberTradeDetail;
import cc.messcat.gjfeng.entity.GjfMemberTradeDivi;
import cc.messcat.gjfeng.entity.GjfMemberTradeDiviHistory;
import cc.messcat.gjfeng.entity.GjfMemberTradeLog;
import cc.messcat.gjfeng.entity.GjfMemberTradeOpcenter;
import cc.messcat.gjfeng.entity.GjfMemberTreasureTrade;
import cc.messcat.gjfeng.entity.GjfMemberUpgradeVipDirectMoney;
import cc.messcat.gjfeng.entity.GjfMemberVouchersTradeHistory;
import cc.messcat.gjfeng.entity.GjfMerchantModelAgentTradeHistory;
import cc.messcat.gjfeng.entity.GjfMerchantUpgradeHistory;
import cc.messcat.gjfeng.entity.GjfMerchantsGiveNum;
import cc.messcat.gjfeng.entity.GjfOrderInfo;
import cc.messcat.gjfeng.entity.GjfRechargePaid;
import cc.messcat.gjfeng.entity.GjfSetBaseInfo;
import cc.messcat.gjfeng.entity.GjfStoreInfo;
import cc.messcat.gjfeng.entity.GjfTransferIntegral;
import cc.messcat.gjfeng.entity.GjfWeiXinPayInfo;
import cc.messcat.gjfeng.service.benefit.GjfBenefitInfoService;
import net.sf.json.JSONObject;

@Service("gjfTradeService")
public class GjfTradeServiceImpl implements GjfTradeService {

	@Autowired
	@Qualifier("gjfMemberInfoDao")
	private GjfMemberInfoDao gjfMemberInfoDao;

	@Autowired
	@Qualifier("gjfBenefitInfoDao")
	private GjfBenefitInfoDao gjfBenefitInfoDao;

	@Autowired
	@Qualifier("gjfTradeDao")
	private GjfTradeDao gjfTradeDao;

	@Autowired
	@Qualifier("gjfMemberInfoService")
	private GjfMemberInfoService gjfMemberInfoService;

	@Autowired
	@Qualifier("gjfBenefitInfoService")
	private GjfBenefitInfoService gjfBenefitInfoService;

	@Autowired
	@Qualifier("notifyJmsTemplate")
	private JmsTemplate notifyJmsTemplate;

	/*
	 * 跳转到提现
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.member.GjfTradeService#toDrawCash(java.lang.
	 * String)
	 */
	@Override
	public ResultVo toDrawCash(String account) {
		Map<String, Object> data = new HashMap<String, Object>();
		GjfMemberInfo gjfMemberInfo = gjfMemberInfoService.findMember(account);
		if (StringUtil.isBlank(account) || ObjValid.isNotValid(gjfMemberInfo)) {
			data.put("withdrawalMoney", 0.00);
			data.put("bank", null);
			return new ResultVo(400, "用户不存在", data);
		}
		// 1.查询用户的可提现余额
		data.put("withdrawalMoney", gjfMemberInfo.getWithdrawalMoney().doubleValue());
		// 2.查询第一张绑定的银行卡
		List<MemberBankVo> banks = gjfMemberInfoDao.findMyBankCard(gjfMemberInfo.getId(), 1);
		data.put("bank", banks.size() == 0 ? null : banks.get(0));
		return new ResultVo(200, "查询成功", data);
	}

	/*
	 * 添加提现
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.member.GjfTradeService#addDrawCash(java.lang.
	 * String, java.lang.String, java.lang.Long, java.lang.Double)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public synchronized ResultVo addDrawCash(String account, String remark, Long myBankId, Double money) {
		if (StringUtil.isBlank(account)) {
			throw new MyException(400, "用户不存在", null);
		}
		if (ObjValid.isNotValid(myBankId)) {
			throw new MyException(400, "请选择银行卡", null);
		}
		if (ObjValid.isNotValid(money)) {
			throw new MyException(400, "输入金额有误", null);
		}
		if (money.doubleValue() < 200) {
			throw new MyException(400, "提现金额不能小于200元", null);
		}
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("id", myBankId);
		attrs.put("memberId.mobile", account);
		GjfMemberBank gjfMemberBank = gjfMemberInfoDao.query(GjfMemberBank.class, attrs);
		if (ObjValid.isNotValid(gjfMemberBank)) {
			throw new MyException(400, "请选择正确的银行卡", null);
		}

		// 获取用户信息
		Map<String, Object> memMap = new HashMap<String, Object>();
		memMap.put("mobile", account);
		GjfMemberInfo mem = gjfMemberInfoDao.query(GjfMemberInfo.class, memMap);
		if (ObjValid.isNotValid(mem)) {
			throw new MyException(400, "用户不存在", null);
		}

		// 查询用户是否已经有一笔再提现中
		int num = gjfTradeDao.findDrawByStatus(gjfMemberBank.getMemberId().getId());
		if (num > 0) {
			throw new MyException(400, "已经有一笔提现正在审核中，请等待审核通过", null);
		}

		// 判断同一个身份证同一天只能提现一次
		if (StringUtil.isBlank(gjfMemberBank.getMemberId().getIdCard())) {
			throw new MyException(400, "实名认证身份证为空，不能提现", null);
		}
		int count = gjfTradeDao.findDrawByIdCard(gjfMemberBank.getMemberId().getIdCard());
		if (count > 0) {
			throw new MyException(400, "同一身份证用户一天只能提现一次", null);
		}

		// 1.查询当前用户的可提现金额，判断提现的金额是否大于可提现金额
		if (gjfMemberBank.getMemberId().getWithdrawalMoney().doubleValue() < money.doubleValue()) {
			throw new MyException(400, "提现金额不能大于可提现金额", null);
		}
		// 2.进行提现申请
		Map<String, Object> attrs1 = new HashMap<String, Object>();
		attrs.put("id", myBankId);
		GjfBenefitInfo benefitInfo = gjfMemberInfoDao.query(GjfBenefitInfo.class, attrs1);
		BigDecimal tradeMoney = new BigDecimal(money * (benefitInfo.getWithdrawalRatio() / 100)).setScale(1,
				BigDecimal.ROUND_DOWN); // 交易金额
		BigDecimal insuranceMoney = new BigDecimal(money * (benefitInfo.getInsuranceRatio() / 100)).setScale(2,
				BigDecimal.ROUND_DOWN);
		BigDecimal taxMoney = new BigDecimal(money).subtract(tradeMoney).subtract(insuranceMoney);

		GjfMemberTrade gjfMemberTrade = new GjfMemberTrade(null,
				DateHelper.dataToString(new Date(), "yyyyMMddHHmmss" + RandUtil.getRandomStr(6)),
				gjfMemberBank.getMemberId(), gjfMemberBank, new BigDecimal(money), tradeMoney, taxMoney, insuranceMoney,
				new Date(), null, null, "1", "0", null, null);

		// 获取用户信息
		GjfMemberInfo gjfMemberInfo = gjfMemberBank.getMemberId();
		if ("0".equals(gjfMemberInfo.getIsActiveMember())) {
			// 查询用户最新提现记录
			Map<String, Object> tradeMap = new HashMap<String, Object>();
			tradeMap.put("memberId.mobile", account);
			tradeMap.put("tradeType", "1");
			tradeMap.put("tradeStatus", "1");
			List<GjfMemberTrade> list = gjfMemberInfoDao.queryList(GjfMemberTrade.class, 0, 5, "addTime", "desc",
					tradeMap);
			if (list != null && list.size() > 0) {
				// 消费增长值
				BigDecimal conGrowth = mem.getCumulativeMoney().subtract(list.get(0).getConGrowth());
				BigDecimal tradeMon = list.get(0).getApplyMoney().multiply(new BigDecimal(2.5)).setScale(2,
						BigDecimal.ROUND_DOWN);
				// 领回20%帐户增值贡献多增加10%（40%）
				BigDecimal fullTwentyCon = mem.getCumulativeMoney().multiply(new BigDecimal(0.2)).setScale(2,
						BigDecimal.ROUND_DOWN);
				if (mem.getDividendsTotalMoney().doubleValue() >= fullTwentyCon.doubleValue()) {
					tradeMon = list.get(0).getApplyMoney().multiply(new BigDecimal(3.34)).setScale(2,
							BigDecimal.ROUND_DOWN);
				}
				// 领回50%增值消费多增加10%（50%）
				BigDecimal fullFiftyCon = mem.getCumulativeMoney().multiply(new BigDecimal(0.5)).setScale(2,
						BigDecimal.ROUND_DOWN);
				if (mem.getDividendsTotalMoney().doubleValue() >= fullFiftyCon.doubleValue()) {
					tradeMon = list.get(0).getApplyMoney().multiply(new BigDecimal(4.17)).setScale(2,
							BigDecimal.ROUND_DOWN);
				}

				if (conGrowth.doubleValue() < tradeMon.doubleValue()) {
					BigDecimal memChae = tradeMon.subtract(conGrowth).setScale(2, BigDecimal.ROUND_DOWN);
					return new ResultVo(400, "亲！您在平台商户再消费" + memChae + "，即可立即提取福利。", null);
				} else {
					gjfMemberTrade.setConGrowth(mem.getCumulativeMoney());
					gjfMemberTrade.setSalesGrowth(new BigDecimal(0.00));
				}

			} else {
				gjfMemberTrade.setConGrowth(mem.getCumulativeMoney());
				gjfMemberTrade.setSalesGrowth(new BigDecimal(0.00));
			}
		} else {
			// 查询用户最新提现记录
			Map<String, Object> tradeMap = new HashMap<String, Object>();
			tradeMap.put("memberId.mobile", account);
			tradeMap.put("tradeType", "1");
			tradeMap.put("tradeStatus", "1");
			List<GjfMemberTrade> list = gjfMemberInfoDao.queryList(GjfMemberTrade.class, 0, 5, "addTime", "desc",
					tradeMap);
			if (list != null && list.size() > 0) {
				gjfMemberTrade.setConGrowth(list.get(0).getConGrowth());
				gjfMemberTrade.setSalesGrowth(new BigDecimal(0.00));
			} else {
				gjfMemberTrade.setConGrowth(mem.getCumulativeMoney());
				gjfMemberTrade.setSalesGrowth(new BigDecimal(0.00));
			}

		}

		gjfMemberTrade.setToken(Sha256.getSha256Hash(gjfMemberTrade.getTradeNo(), gjfMemberTrade.getTradeStatus(),
				CommonStatus.SIGN_KEY_NUM));
		gjfMemberInfoDao.save(gjfMemberTrade);

		// 用户资金修改

		gjfMemberInfo.setWithdrawalMoney(gjfMemberInfo.getWithdrawalMoney().subtract(new BigDecimal(money)));
		gjfMemberInfo.setBalanceMoney(gjfMemberInfo.getBalanceMoney().subtract(new BigDecimal(money)));

		gjfMemberInfoDao.update(gjfMemberInfo);

		return new ResultVo(200, "提交成功,请等待审核", gjfMemberTrade);
	}

	/*
	 * 审核提现记录
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.member.GjfTradeService#updateDrawCashStatus(
	 * java.lang.Long, java.lang.String, java.lang.String)
	 */
	@Override
	public ResultVo updateDrawCashStatus(Long id, String token, String tradeStatus, String userName) {
		if (ObjValid.isNotValid(id) || StringUtil.isBlank(tradeStatus)
				|| (!"1".equals(tradeStatus) && !"2".equals(tradeStatus))) {
			throw new MyException(400, "参数错误", null);
		}
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("id", id);
		attrs.put("token", token);
		GjfMemberTrade gjfMemberTrade = gjfMemberInfoDao.query(GjfMemberTrade.class, attrs);
		if (ObjValid.isNotValid(gjfMemberTrade)) {
			throw new MyException(400, "对象不存在", null);
		}
		// 增加审核的操作人员

		gjfMemberTrade.setCheckUser(userName);

		/* GjfMemberTrade gjfMemberTrade = (GjfMemberTrade) o; */
		gjfMemberTrade.setTradeStatus(tradeStatus);
		gjfMemberTrade.setDealTime(new Date());

		gjfMemberTrade.setToken(Sha256.getSha256Hash(gjfMemberTrade.getTradeNo(), gjfMemberTrade.getTradeStatus(),
				CommonStatus.SIGN_KEY_NUM));

		if (ObjValid.isNotValid(gjfMemberTrade.getMemberId())) {
			throw new MyException(400, "用户不存在", null);
		}
		if (gjfMemberTrade.getTradeType().equals("1") && "1".equals(tradeStatus)) {
			gjfMemberTrade.setTradeTime(new Date());
			/*
			 * GjfMemberInfo info = gjfMemberTrade.getMemberId();
			 * info.setInsuranceMoney(info.getInsuranceMoney().add(
			 * gjfMemberTrade.getInsuranceMoney()));
			 * gjfMemberInfoDao.update(info);
			 * 
			 * // 修改资金池金额 Map<String, Object> attrsPool = new HashMap<>();
			 * GjfBenefitPool gjfBenefitPool =
			 * gjfMemberInfoDao.query(GjfBenefitPool.class, attrsPool);
			 * 
			 * // 添加资金流水 GjfBenefitHistory benefitHistory = new
			 * GjfBenefitHistory(null, gjfMemberTrade.getTaxMoney(),
			 * gjfBenefitPool.getPlatformSysPoolCur(),
			 * gjfBenefitPool.getPlatformSysPoolCur().add(gjfMemberTrade.
			 * getTaxMoney()), new BigDecimal(0.00), new Date(), "16", null, new
			 * Date(), "1");
			 * 
			 * gjfBenefitPool
			 * .setPlatformSysPoolCur(gjfBenefitPool.getPlatformSysPoolCur().add
			 * (gjfMemberTrade.getTaxMoney()));
			 * gjfBenefitPool.setPlatformSysPoolTotal(
			 * gjfBenefitPool.getPlatformSysPoolTotal().add(gjfMemberTrade.
			 * getTaxMoney()));
			 * 
			 * gjfMemberInfoDao.update(gjfBenefitPool);
			 * gjfMemberInfoDao.save(benefitHistory);
			 * 
			 * // 添加余额、提现金额、责任险余额变更记录 GjfMemberTradeDetail detail1 = new
			 * GjfMemberTradeDetail(null, gjfMemberTrade.getMemberId(),
			 * gjfMemberTrade.getTradeNo() + "-1",
			 * gjfMemberTrade.getApplyMoney(), new Date(), new Date(), "0", "0",
			 * "提现-余额"); GjfMemberTradeDetail detail2 = new
			 * GjfMemberTradeDetail(null, gjfMemberTrade.getMemberId(),
			 * gjfMemberTrade.getTradeNo() + "-2",
			 * gjfMemberTrade.getApplyMoney(), new Date(), new Date(), "1", "0",
			 * "提现-提现金额"); gjfMemberInfoDao.save(detail1);
			 * gjfMemberInfoDao.save(detail2); if
			 * (gjfMemberTrade.getInsuranceMoney().doubleValue() > 0) {
			 * GjfMemberTradeDetail detail3 = new GjfMemberTradeDetail(null,
			 * gjfMemberTrade.getMemberId(), gjfMemberTrade.getTradeNo() + "-3",
			 * gjfMemberTrade.getInsuranceMoney(), new Date(), new Date(), "2",
			 * "1", "提现-责任消费金额"); gjfMemberInfoDao.save(detail3); }
			 */
		} else if (gjfMemberTrade.getTradeType().equals("1") && "2".equals(tradeStatus)) {
			// 审核不通过，用户资金还原
			GjfMemberInfo gjfMemberInfo = gjfMemberTrade.getMemberId();
			// gjfMemberInfo.setWithdrawalMoney(gjfMemberInfo.getWithdrawalMoney().add(gjfMemberTrade.getApplyMoney()));
			// gjfMemberInfo.setBalanceMoney(gjfMemberInfo.getBalanceMoney().add(gjfMemberTrade.getApplyMoney()));
			// 查询天天宝信息
			Map<String, Object> fhAttrs = new HashMap<>();
			fhAttrs.put("mobile", gjfMemberInfo.getMobile());
			GjfFhTreasureInfo fh = gjfMemberInfoDao.query(GjfFhTreasureInfo.class, fhAttrs);
			fh.setFhTreasureMoney(fh.getFhTreasureMoney().add(gjfMemberTrade.getApplyMoney()));
			gjfMemberInfoDao.update(fh);

			// 添加提现退回天天宝记录
			GjfMemberTreasureTrade fhTrede = new GjfMemberTreasureTrade();
			fhTrede.setMemberId(gjfMemberInfo.getId());
			fhTrede.setMemberName(gjfMemberInfo.getName());
			fhTrede.setMemebrMobile(gjfMemberInfo.getMobile());
			fhTrede.setMemberTreasureTradeMoney(gjfMemberTrade.getApplyMoney());
			fhTrede.setTradeStatus("1");
			fhTrede.setTradeType("6");
			fhTrede.setTradeNo(gjfMemberTrade.getTradeNo());
			fhTrede.setAddTime(new Date());
			gjfMemberInfoDao.save(fhTrede);
		}
		gjfMemberInfoDao.update(gjfMemberTrade);

		// 查询天天宝提现记录
		Map<String, Object> tradeAttrs = new HashMap<>();
		tradeAttrs.put("tradeNo", gjfMemberTrade.getTradeNo());
		GjfMemberTreasureTrade trade = gjfMemberInfoDao.query(GjfMemberTreasureTrade.class, tradeAttrs);
		trade.setTradeStatus(gjfMemberTrade.getTradeStatus());
		gjfMemberInfoDao.update(trade);
		if (gjfMemberTrade.getTradeType().equals("1") && "1".equals(tradeStatus)) {
			sendMessage(gjfMemberTrade);
		}
		return new ResultVo(200, "操作成功", null);
	}

	/**
	 * 退回待审核状态
	 * 
	 * @param id
	 * @param token
	 * @param userName
	 * @return
	 */
	@Override
	public ResultVo preDrawCashStatus(Long id, String token, String userName) {
		if (ObjValid.isNotValid(id) || StringUtil.isBlank(token) || StringUtil.isBlank(userName)) {
			throw new MyException(400, "参数错误", null);
		}
		GjfMemberTrade gjfMemberTrade = queryById(id, token);
		if (ObjValid.isNotValid(gjfMemberTrade)) {
			throw new MyException(400, "用户不存在", null);
		}
		if ("1".equals(gjfMemberTrade.getTradeStatus()) && "1".equals(gjfMemberTrade.getTradeType())) {
			GjfMemberInfo info = gjfMemberTrade.getMemberId();
			// 责任险可为负数
			info.setInsuranceMoney(info.getInsuranceMoney().subtract(gjfMemberTrade.getInsuranceMoney()));
			gjfMemberInfoDao.update(info);

			// 修改资金池金额
			Map<String, Object> attrsPool = new HashMap<>();
			GjfBenefitPool gjfBenefitPool = gjfMemberInfoDao.query(GjfBenefitPool.class, attrsPool);

			gjfBenefitPool.setPlatformSysPoolCur(
					gjfBenefitPool.getPlatformSysPoolCur().subtract(gjfMemberTrade.getTaxMoney()));
			gjfBenefitPool.setPlatformSysPoolTotal(
					gjfBenefitPool.getPlatformSysPoolTotal().subtract(gjfMemberTrade.getTaxMoney()));

			gjfMemberInfoDao.update(gjfBenefitPool);

			// 添加余额、提现金额、责任险余额变更记录
			Map<String, Object> attrs = new HashMap<String, Object>();
			attrs.put("memberId", gjfMemberTrade.getMemberId());
			attrs.put("tradeNo", gjfMemberTrade.getTradeNo() + "-1");
			gjfMemberInfoDao.delete(GjfMemberTradeDetail.class, attrs);
			attrs.put("tradeNo", gjfMemberTrade.getTradeNo() + "-2");
			gjfMemberInfoDao.delete(GjfMemberTradeDetail.class, attrs);
		} else if (gjfMemberTrade.getTradeType().equals("1") && "2".equals(gjfMemberTrade.getTradeStatus())) {
			// 审核不通过，退回审核状态 金额可为负数
			GjfMemberInfo gjfMemberInfo = gjfMemberTrade.getMemberId();
			gjfMemberInfo
					.setWithdrawalMoney(gjfMemberInfo.getWithdrawalMoney().subtract(gjfMemberTrade.getApplyMoney()));
			gjfMemberInfo.setBalanceMoney(gjfMemberInfo.getBalanceMoney().subtract(gjfMemberTrade.getApplyMoney()));

			gjfMemberInfoDao.update(gjfMemberInfo);
		}
		gjfMemberTrade.setTradeStatus("0");
		gjfMemberTrade.setCheckUser(userName);
		gjfMemberTrade.setToken(Sha256.getSha256Hash(gjfMemberTrade.getTradeNo(), gjfMemberTrade.getTradeStatus(),
				CommonStatus.SIGN_KEY_NUM));
		gjfMemberTrade.setDealTime(new Date());

		// gjfTradeDao.update(gjfMemberTrade);
		return new ResultVo(200, "操作成功", null);
	}

	/*
	 * 查询提现记录信息
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.member.GjfTradeService#findDrawCashHistory(java
	 * .lang.Long, java.lang.String)
	 */
	public ResultVo findDrawCashHistory(Long id, String token) {
		if (ObjValid.isNotValid(id)) {
			throw new MyException(400, "参数错误", null);
		}
		return new ResultVo(200, "查询成功", gjfTradeDao.findDrawCashHistoryDetail(id, null, token, "0"));
	}

	/*
	 * 根据用户Id查询提现记录
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ResultVo findDrawCashHistoryById(Long id) {
		if (ObjValid.isNotValid(id)) {
			throw new MyException(400, "参数错误", null);
		}
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("memberId.id", id);
		attrs.put("tradeType", "1");
		return new ResultVo(200, "查询成功", gjfMemberInfoDao.queryList(GjfMemberTrade.class, "addTime", "desc", attrs));
	}

	/*
	 * 查询我的提现历史记录
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.member.GjfTradeService#findDrawCashHistory(int,
	 * int, java.lang.String)
	 */
	@Override
	public ResultVo findDrawCashHistory(int pageNo, int pageSize, String account) {
		List<MemberTradeVo> gjfMemberTrades = gjfTradeDao.findDrawCashHistory(pageNo, pageSize, account);
		return new ResultVo(200, "查询成功", gjfMemberTrades);
	}

	/*
	 * 查询提现流水详情
	 * 
	 * @see cc.messcat.gjfeng.service.member.GjfTradeService#
	 * findDrawCashHistoryDetail(java.lang.Long, java.lang.String)
	 */
	@Override
	public ResultVo findDrawCashHistoryDetail(String tradeNo, String account) {
		return new ResultVo(200, "查询成功", gjfTradeDao.findDrawCashHistoryDetail(null, tradeNo, account, "1"));
	}

	/*
	 * 绑定银行卡
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.member.GjfTradeService#bindMyBankCard(java.lang
	 * .String, java.lang.Long, java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public ResultVo addMyBankCard(String account, Long bankId, String bankSub, String bankCard, String holder,
			String cityValue) {
		if (StringUtil.isBlank(account)) {
			throw new MyException(400, "用户账号不存在", null);
		}
		if (StringUtil.isBlank(bankSub)) {
			throw new MyException(400, "请输入支行名称", null);
		}
		if (StringUtil.isBlank(bankCard)) {
			throw new MyException(400, "参请输入卡号", null);
		}
		if (StringUtil.isBlank(holder)) {
			throw new MyException(400, "请输入开户人", null);
		}
		if (ObjValid.isNotValid(bankId)) {
			throw new MyException(400, "请选择需要绑定的银行卡", null);
		}
		if (StringUtil.isBlank(cityValue)) {
			throw new MyException(400, "请选择银行卡开户省市", null);
		}
		Object o = gjfMemberInfoDao.get(bankId, GjfBankInfo.class.getName());
		if (ObjValid.isNotValid(o)) {
			throw new MyException(400, "银行信息不存在", null);
		}
		GjfMemberInfo gjfMemberInfo = gjfMemberInfoService.findMember(account);
		if (ObjValid.isNotValid(gjfMemberInfo)) {
			throw new MyException(400, "用户不存在", null);
		}
		String[] addressId = cityValue.split(",");
		Map<String, Object> proAttrs = new HashMap<String, Object>();
		Map<String, Object> cityAttrs = new HashMap<String, Object>();
		Object proObj = null;
		Object cityObj = null;
		if (addressId.length > 0) {
			proAttrs.put("provinceId", Long.valueOf(addressId[0]));
			proObj = gjfMemberInfoDao.query(GjfAddressProvince.class, proAttrs);
			if (ObjValid.isNotValid(proObj)) {
				throw new MyException(400, "省份不能为空", null);
			}
		}
		if (addressId.length > 1) {
			cityAttrs.put("cityId", Long.valueOf(addressId[1]));
			cityObj = gjfMemberInfoDao.query(GjfAddressCity.class, cityAttrs);
			if (ObjValid.isNotValid(cityObj)) {
				throw new MyException(400, "城市不能为空", null);
			}
		}
		GjfMemberBank gjfMemberBank = new GjfMemberBank();
		gjfMemberBank.setAddTime(new Date());
		gjfMemberBank.setBankCard(bankCard);
		gjfMemberBank.setBankId((GjfBankInfo) o);
		gjfMemberBank.setBankSub(bankSub);
		gjfMemberBank.setHolder(holder);
		gjfMemberBank.setBankProvinceId((GjfAddressProvince) proObj);
		gjfMemberBank.setBankCityId((GjfAddressCity) cityObj);
		gjfMemberBank.setMemberId(gjfMemberInfo);
		gjfMemberBank.setStatus("1");
		gjfMemberInfoDao.save(gjfMemberBank);
		return new ResultVo(200, "绑定成功", null);
	}

	/*
	 * 删除我的银行卡
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.member.GjfTradeService#delMyBank(java.lang.
	 * String, java.lang.Long)
	 */
	public ResultVo delMyBank(String account, Long bankId) {
		if (StringUtil.isBlank(account)) {
			throw new MyException(400, "用户账号不存在", null);
		}
		if (ObjValid.isNotValid(bankId)) {
			throw new MyException(400, "请选择需要删除的银行卡", null);
		}
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("memberId.mobile", account);
		attrs.put("id", bankId);
		GjfMemberBank bank = gjfMemberInfoDao.query(GjfMemberBank.class, attrs);
		if (ObjValid.isNotValid(bank)) {
			throw new MyException(400, "删除的银行卡不存在", null);
		}
		bank.setStatus("0");
		gjfMemberInfoDao.update(bank);
		return new ResultVo(200, "删除成功", null);
	}

	/*
	 * 查找我的银行卡
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.member.GjfTradeService#findMyBankCard(java.lang
	 * .String)
	 */
	@Override
	public ResultVo findMyBankCard(String account) {
		GjfMemberInfo gjfMemberInfo = gjfMemberInfoService.findMember(account);
		if (StringUtil.isBlank(account) || ObjValid.isNotValid(gjfMemberInfo)) {
			return new ResultVo(400, "用户不存在", null);
		}
		List<MemberBankVo> banks = gjfMemberInfoDao.findMyBankCard(gjfMemberInfo.getId(), 0);
		return new ResultVo(200, "查询成功", banks);
	}

	/*
	 * 查询所有的银行
	 * 
	 * @see cc.messcat.gjfeng.service.member.GjfTradeService#findAllBank()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ResultVo findAllBank() {
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("status", "1");
		List<GjfBankInfo> banks = gjfMemberInfoDao.queryList(GjfBankInfo.class, "id", "asc", attrs);
		return new ResultVo(200, "查询成功", banks);
	}

	/*
	 * 购买分红权
	 * 
	 * @see cc.messcat.gjfeng.service.member.GjfTradeService#addDivi(java.lang.
	 * String, java.lang.Double)
	 */
	@Override
	public ResultVo addDivi(String account, Double diviNum, String payType, String reqStatus) {
		if (StringUtil.isBlank(account)) {
			throw new MyException(400, "账号不存在", 0.00);
		}
		if (null == diviNum || 0 == diviNum.doubleValue()) {
			throw new MyException(400, "购买数量有误", 0.00);
		}
		GjfMemberInfo gjfMemberInfo = gjfMemberInfoService.findMember(account);
		if (ObjValid.isNotValid(gjfMemberInfo)) {
			throw new MyException(400, "用户不存在", 0.00);
		}
		if (!gjfMemberInfo.getType().equals("1")) {
			throw new MyException(400, "只有商家才能购买分红权", 0.00);
		}
		// 3.根据目前的分红权额度以及相对应的累积消费额度计算分红权个数
		double diviMoney = 0;
		if (gjfMemberInfo.getCumulativeMoney().longValue() < 10000) {
			// 3.1 小于1万 500一个分红权
			diviMoney = diviNum * 500;
		} else if (10000 < gjfMemberInfo.getCumulativeMoney().longValue()
				&& gjfMemberInfo.getCumulativeMoney().longValue() < 10000 * 100) {
			// 3.2 1万-100万 1000一个分红权
			diviMoney = diviNum * 1000;
		} else if (gjfMemberInfo.getCumulativeMoney().longValue() > 1000) {
			// 3.3 100万以上 3000一个分红权
			diviMoney = diviNum * 3000;
		}
		if (StringUtil.isNotBlank(reqStatus) && "1".equals(reqStatus)) {
			GjfMemberTradeDivi memberTradeDivi = new GjfMemberTradeDivi(null, gjfMemberInfo,
					String.valueOf(System.currentTimeMillis()), new BigDecimal(diviNum),
					new BigDecimal(diviMoney).setScale(2, BigDecimal.ROUND_UP), null, null, new Date(), payType, "0",
					"1", "1", "1", "");
			memberTradeDivi.setToken(Sha256.getSha256Hash(memberTradeDivi.getDiviNo(), memberTradeDivi.getDiviStatus(),
					CommonStatus.SIGN_KEY_NUM));
			gjfMemberInfoDao.save(memberTradeDivi);
		}
		return new ResultVo(200, "提交成功", new BigDecimal(diviMoney).setScale(2, BigDecimal.ROUND_UP));
	}

	/*
	 * 修改支付状态
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.member.GjfTradeService#updateDiviPayStatus(java
	 * .lang.String, java.lang.String)
	 */
	public ResultVo updateDiviPayStatus(String diviNo, String payStatus) {
		GjfMemberTradeDivi gjfMemberTradeDivi = (GjfMemberTradeDivi) findDivi(diviNo).getResult();
		gjfMemberTradeDivi.setPayStatus(payStatus);
		gjfMemberTradeDivi.setToken(Sha256.getSha256Hash(gjfMemberTradeDivi.getDiviNo(),
				gjfMemberTradeDivi.getDiviStatus(), CommonStatus.SIGN_KEY_NUM));
		gjfMemberInfoDao.update(gjfMemberTradeDivi);
		return new ResultVo(200, "修改成功", null);
	}

	/*
	 * 根据流水号查询分红权信息
	 * 
	 * @see cc.messcat.gjfeng.service.member.GjfTradeService#findDivi(java.lang.
	 * String)
	 */
	public ResultVo findDivi(String diviNo) {
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("diviNo", diviNo);
		return new ResultVo(200, "查询成功", gjfMemberInfoDao.query(GjfMemberTradeDivi.class, attrs));
	}

	/*
	 * 根据流水号和token查询分红权信息
	 * 
	 * @see cc.messcat.gjfeng.service.member.GjfTradeService#findDivi(java.lang.
	 * String, java.lang.String)
	 */
	public ResultVo findDivi(String diviNo, String token) {
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("diviNo", diviNo);
		attrs.put("token", token);
		return new ResultVo(200, "查询成功", gjfMemberInfoDao.query(GjfMemberTradeDivi.class, attrs));
	}

	/*
	 * 查询我的分红权购买历史记录--前端
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.member.GjfTradeService#findDiviHistory(int,
	 * int, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ResultVo findTradeDivi(int pageNo, int pageSize, String account, String status) {
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("memberId.mobile", account);
		attrs.put("diviStatus", status);
		List<GjfMemberTradeDivi> diviHistories = gjfMemberInfoDao.queryList(GjfMemberTradeDivi.class,
				(pageNo - 1) * pageSize, pageSize, "addTime", "desc", attrs);
		return new ResultVo(200, "查询成功", BeanUtilsEx.copyBean(MemberTradeDiviVo.class, diviHistories));
	}

	/*
	 * 查找所有分红权购买记录
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.member.GjfTradeService#findAllTradeDivi(int,
	 * int)
	 */
	@Override
	public ResultVo findAllTradeDivi(int pageNo, int pageSize, String name, String payStatus, String diviStatus,
			String startDate, String endDate) {
		return new ResultVo(200, "查询成功",
				gjfTradeDao.findAllTradeDivi(pageNo, pageSize, name, payStatus, diviStatus, startDate, endDate));
	}

	/*
	 * 统计分红权购买记录
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.member.GjfTradeService#findCountTradeDivi(java.
	 * lang.String, java.lang.String, java.lang.String)
	 */
	public ResultVo findCountTradeDivi(String name, String payStatus, String diviStatus) {
		return new ResultVo(200, "查询成功", gjfTradeDao.findCountTradeDivi(name, payStatus, diviStatus));
	}

	/*
	 * 查询我的分红历史记录--前端
	 * 
	 * @see cc.messcat.gjfeng.service.member.GjfTradeService#findTradeDivi(int,
	 * int, java.lang.String, java.lang.String)
	 */
	@Override
	public ResultVo findTradeDiviHistory(int pageNo, int pageSize, String account, String tradeStatus) {
		return new ResultVo(200, "查询成功", gjfTradeDao.findTradeDiviHistory(pageNo, pageSize, account, tradeStatus));
	}

	/*
	 * 查询会员所有分红记录
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.member.GjfTradeService#findAllTradeDiviHistory(
	 * int, int)
	 */
	@Override
	public ResultVo findMemberTradeDiviHistoryByPage(int pageNo, int pageSize, String name, String addTime,
			String endTime, String tradeNo, String tradeType, String tradeStatus) {
		return new ResultVo(200, "查询成功", gjfTradeDao.findMemberTradeDiviHistoryByPage(pageNo, pageSize, name, addTime,
				endTime, tradeNo, tradeType, tradeStatus));
	}

	/*
	 * 当前条件统计分红记录
	 * 
	 * @see cc.messcat.gjfeng.service.member.GjfTradeService#
	 * findCountTradeDiviHistory(java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public ResultVo findCountTradeDiviHistory(String name, String addTime, String endTime, String tradeNo,
			String tradeType, String tradeStatus) {
		return new ResultVo(200, "查询成功",
				gjfTradeDao.findCountTradeDiviHistory(name, addTime, endTime, tradeNo, tradeType, tradeStatus));
	}

	/*
	 * 查询所有发放成功分红记录
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.member.GjfTradeService#findAllTradeDiviHistory(
	 * int, int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ResultVo findAllTradeDiviHistory(int pageNo, int pageSize) {
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("tradeStatus", "1");
		Pager pager = new Pager(pageSize, pageNo,
				Integer.parseInt(
						String.valueOf(gjfMemberInfoDao.queryTotalRecord(GjfMemberTradeDiviHistory.class, attrs))),
				gjfMemberInfoDao.queryList(GjfMemberTradeDiviHistory.class, (pageNo - 1) * pageSize, pageSize,
						"addTime", "desc", attrs));
		return new ResultVo(200, "查询成功", pager);
	}

	/*
	 * 添加商家让利
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.member.GjfTradeService#addStoreBenefit(java.
	 * lang.Long, java.lang.String, java.lang.Double)
	 */
	@Override
	public ResultVo addStoreBenefit(String account, Long storeId, String mobile, Double amount, String payType,String merchantGrade) {
		//如果商家电话和录入用户的的电话一样返回提示
		if (account.equals(mobile)) {
			return new ResultVo(400, "消费会员不能是自己", null);
		}
        //如果商家电话为空返回提示
		if (ObjValid.isNotValid(storeId)) {
			return new ResultVo(400, "商家不存在", null);
		}
		//如果录入用户电话号码为空返回提示
		if (StringUtil.isBlank(mobile)) {
			return new ResultVo(400, "用户不存在", null);
		}
		//如果金额为空返回提示
		if (ObjValid.isNotValid(amount)) {
			return new ResultVo(400, "金额不能为空", null);
		}
		//如果录入金额小于等于0返回提示
		if(amount<=0){
			return new ResultVo(400, "录入金额不能要大于零", null);
		}
		//获取录入用户信息
		GjfMemberInfo gjfMemberInfo = gjfMemberInfoService.findMember(mobile);
		//如果用户信息为空返回提示
		if (ObjValid.isNotValid(gjfMemberInfo)) {
			return new ResultVo(400, "用户不存在", null);
		}
		//如果录入用户的状态为零返回他会死
		if ("0".equals(gjfMemberInfo.getStatus())) {
			return new ResultVo(400, "消费会员已停用", null);
		}
        //获取商家信息
		Object o = gjfMemberInfoDao.get(storeId, GjfStoreInfo.class.getName());
		//如果商户信息为空返回提示
		if (ObjValid.isNotValid(o)) {
			return new ResultVo(400, "商家不存在", null);
		}
		//获取商家对应的用户信息
		GjfMemberInfo gjfMemberInfo0 = gjfMemberInfoService.findMember(account);
		//如果商户对应的用户信息为空返回提示
		if ("0".equals(gjfMemberInfo0.getStatus())) {
			return new ResultVo(400, "用户已停用", null);
		}
        //如果支付方式为授信支付
		if (payType.equals("4")) {
			//如果用户的的授信额度不为零
			if (gjfMemberInfo0.getLineOfCrade().doubleValue() < amount) {
				return new ResultVo(400, "授信金额不足请选择其他支付方式或充值授信金额", null);
			}
		}
        //如果支付方式为余额支付
		if (payType.equals("5")) {
			// 判断用户授信金额是否足够
			if (gjfMemberInfo0.getBalanceMoney().doubleValue() < (new BigDecimal(amount).multiply(new BigDecimal(0.12)))
					.doubleValue()) {
				return new ResultVo(400, "余额不足请选择其他支付方式", null);
			}
		}
        //把对象转为Object
		GjfStoreInfo gjfStoreInfo = (GjfStoreInfo) o;
		//创建让利交易记录对象
		GjfMemberTradeBenefit benefit = new GjfMemberTradeBenefit();
		//添加时间
		benefit.setAddTime(new Date());
		//记录让利金额
		BigDecimal benefitMoney=new BigDecimal(0);
		//如果是一类
		if("1".equals(merchantGrade)){
			//计算让利金额
			 benefitMoney=new BigDecimal(amount * 0.1);
		}
		//如果是二类
		if("2".equals(merchantGrade)){
			//计算让利金额
			benefitMoney=new BigDecimal(amount * 0.15);
		}
		//如果是三类
		if("3".equals(merchantGrade)){
			//计算让利金额
		    benefitMoney=new BigDecimal(amount * 0.2);
		}
		
		//让利金额
		benefit.setBenefitMoney(benefitMoney);
		//交易金额
		benefit.setTradeMoney(new BigDecimal(amount));
		//录入用户id
		benefit.setMemberId(gjfMemberInfo);
		//商家店铺信息
		benefit.setStoreId(gjfStoreInfo);
		//确认状态
		benefit.setConfirmStatus("0");
		//生成订单号
		benefit.setTradeNo(DateHelper.dataToString(new Date(), "yyyyMMddHHmmss" + RandUtil.getRandomStr(6)));
		//支付方式
		benefit.setPayType(payType);
		//交易类型
		benefit.setTradeStatus("0");
		//商家类型
		benefit.setMerchantGrade(merchantGrade);
		//生成token
		benefit.setToken(
				Sha256.getSha256Hash(benefit.getTradeNo(), benefit.getTradeStatus(), CommonStatus.SIGN_KEY_NUM));
		//保存让利记录
		gjfMemberInfoDao.save(benefit);

		//如果支付方式为授信额度支付付
		if (payType.equals("4")) {
			//扣减商家对应于用户的授信金额
			gjfMemberInfo0.setLineOfCrade(gjfMemberInfo0.getLineOfCrade().subtract(new BigDecimal(amount)));
			//交易状态改为1
			benefit.setTradeStatus("1");
			//更新商家对应用户
			gjfMemberInfoDao.update(gjfMemberInfo0);
		}

		// 如果支付方式为余额支付
		if (payType.equals("5")) {
			//修改商家对应用户的余额
			gjfMemberInfo0.setBalanceMoney(
					gjfMemberInfo0.getBalanceMoney().subtract(benefitMoney));
			//修改商家对应用户可提现金额
			gjfMemberInfo0.setWithdrawalMoney(gjfMemberInfo0.getWithdrawalMoney()
					.subtract(benefitMoney));
			//修改记录状态
			benefit.setTradeStatus("1");
			//更新商家对应用户的信息
			gjfMemberInfoDao.update(gjfMemberInfo0);
		}

		//如果支付方式为凤凰宝支付
		if (payType.equals("6")) {
			//创建查询凤凰宝记录信息
			Map<String, Object> fhAttrs = new HashMap<>();
			//商户对应用户的电话号码
			fhAttrs.put("mobile", gjfMemberInfo0.getMobile());
			//查询商家对应用户的凤凰宝信息
			GjfFhTreasureInfo fhInfo = gjfMemberInfoDao.query(GjfFhTreasureInfo.class, fhAttrs);
			//如果凤凰宝为空返回提示
			if (!BeanUtil.isValid(fhInfo)) {
				return new ResultVo(400, "凤凰宝余额不足");
			}
			//如果凤凰宝金额小于对应需要支付的金额返回提示
			if (fhInfo.getFhTreasureMoney()
					.doubleValue() < benefitMoney.doubleValue()) {
				return new ResultVo(400, "凤凰宝余额不足");
			}
			//创建凤凰宝交易记录对象
			GjfMemberTreasureTrade trade = new GjfMemberTreasureTrade();
			//用户id
			trade.setMemberId(gjfMemberInfo0.getId());
			//用户姓名
			trade.setMemberName(gjfMemberInfo0.getName());
			//用户电话号码
			trade.setMemebrMobile(gjfMemberInfo0.getMobile());
			//用户交易钱凤凰宝金额
			trade.setMemberTreasureMoneyBf(fhInfo.getFhTreasureMoney());
            //扣将凤凰宝金额
			fhInfo.setFhTreasureMoney(
					fhInfo.getFhTreasureMoney().subtract(benefitMoney));
			//更新凤凰宝记录
			gjfMemberInfoDao.update(fhInfo);
			//消费的用户id
			trade.setTransferMemberId(gjfMemberInfo.getId());
			//消费用户的电话号码
			trade.setTransferMemberMobile(gjfMemberInfo.getMobile());
			//消费用户的姓名
			trade.setTransferMemberName(gjfMemberInfo.getName());
			//交易金额
			trade.setMemberTreasureTradeMoney(benefitMoney);
			//交易后用户凤凰金额
			trade.setMemberTreasureMoneyAf(fhInfo.getFhTreasureMoney());
			//交易状态
			trade.setTradeStatus("1");
			//交易类型
			trade.setTradeType("2");
			//添加时间
			trade.setAddTime(new Date());
			//订单号
			trade.setTradeNo(DateHelper.dataToString(new Date(), "yyyyMMddHHmmss" + RandUtil.getRandomStr(6)));
			//保存凤凰宝交易记录
			gjfMemberInfoDao.save(trade);
            //修改让利记录状态
			benefit.setTradeStatus("1");
		}

		// 创建交易明细对象
		GjfMemberTradeLog gjfMemberTradeLog = new GjfMemberTradeLog();
		//交易单号
		gjfMemberTradeLog.setTradeNo(DateHelper.dataToString(new Date(), "yyyyMMddHHmmss" + RandUtil.getRandomStr(6)));
		//记录对应交易的订单号
		gjfMemberTradeLog.setLinkTradeNo(benefit.getTradeNo());
		//交易金额
		gjfMemberTradeLog.setTradeMoney(benefit.getBenefitMoney());
		//用户id
		gjfMemberTradeLog.setMemberId(gjfMemberInfo);
		//店铺id
		gjfMemberTradeLog.setStoreId(gjfStoreInfo);
		
		//如果支付方式为微信支付
		if ("0".equals(payType)) {
			//记录交易类型
			gjfMemberTradeLog.setTradeType("3");
			//交易状态
			gjfMemberTradeLog.setTradeStatus("0");
			//交易附言
			gjfMemberTradeLog.setTradeTrmo("微信支付--销售录入");
		} else if ("1".equals(payType)) {//如果支付方式为支付宝
			//记录交易类型
			gjfMemberTradeLog.setTradeType("4");
			//交易状态
			gjfMemberTradeLog.setTradeStatus("0");
			//交易附言
			gjfMemberTradeLog.setTradeTrmo("支付宝支付--销售录入");
		} else if ("2".equals(payType)) {//如果支付方式为银联支付
			//记录交易类型
			gjfMemberTradeLog.setTradeType("5");
			//交易状态
			gjfMemberTradeLog.setTradeStatus("0");
			//交易附言
			gjfMemberTradeLog.setTradeTrmo("银联支付--销售录入");
		} else if ("4".equals(payType)) {//如果支付方式为授信支付
			//交易金额
			gjfMemberTradeLog.setTradeMoney(benefit.getTradeMoney());
			//交易类型
			gjfMemberTradeLog.setTradeType("6");
			//交易状态
			gjfMemberTradeLog.setTradeStatus("1");			
			//交易附言
			gjfMemberTradeLog.setTradeTrmo("授信额度支付--销售录入");
		} else if ("5".equals(payType)) {//如果支付方式为余额支付
			//交易金额
			gjfMemberTradeLog.setTradeMoney(benefit.getTradeMoney());
			//交易类型
			gjfMemberTradeLog.setTradeType("0");
			//交易状态
			gjfMemberTradeLog.setTradeStatus("1");
			//交易附言
			gjfMemberTradeLog.setTradeTrmo("余额支付--销售录入");
		} else if ("6".equals(payType)) {//如果支付方式为凤凰宝
			//交易金额
			gjfMemberTradeLog.setTradeMoney(benefit.getTradeMoney());
			//交易类型
			gjfMemberTradeLog.setTradeType("8");
			//交易状态
			gjfMemberTradeLog.setTradeStatus("1");
			//交易附言
			gjfMemberTradeLog.setTradeTrmo("凤凰宝--销售录入");
		}
		//添加时间
		gjfMemberTradeLog.setTradeTime(new Date());
		gjfMemberTradeLog.setAddTime(new Date());
		//生成token
		gjfMemberTradeLog.setToken(Sha256.getSha256Hash(gjfMemberTradeLog.getTradeNo(),
				gjfMemberTradeLog.getTradeType(), CommonStatus.SIGN_KEY_NUM));
        //保存记录
		gjfMemberInfoDao.save(gjfMemberTradeLog);

		// 如果支付状态为1，计算分红权和资金池
		if (benefit.getTradeStatus().equals("1")) {
			gjfBenefitInfoService.updateMemberDividendsNumNotify(benefit.getMemberId().getMobile(),
					benefit.getStoreId().getMemberId().getMobile(), benefit.getTradeMoney().doubleValue(),
					benefit.getTradeNo(),merchantGrade);
		}

		//返回信息
		return new ResultVo(200, "录入成功", BeanUtilsEx.copyBean(MemberTradeBenefitVo.class, benefit));
	}

	/*
	 * 修改商家让利支付状态
	 * 
	 * @see cc.messcat.gjfeng.service.member.GjfTradeService#
	 * updateStoreBenefitPayStatus(java.lang.String, java.lang.String)
	 */
	@Override
	public ResultVo updateStoreBenefitPayStatus(String tradeNo, String payTradeNo, String payStatus) {
		//创建查条件map
		Map<String, Object> attrs = new HashMap<String, Object>();
		//交易单号
		attrs.put("tradeNo", tradeNo);
		//获取交易记录
		GjfMemberTradeBenefit benefit = gjfMemberInfoDao.query(GjfMemberTradeBenefit.class, attrs);
		//如果交易记录为空返回提示信息
		if (ObjValid.isNotValid(benefit)) {
			throw new MyException(400, "订单不存在", null);
		}
		//如果交易状态为0
		if ("0".equals(benefit.getTradeStatus())) {
			//更新记录交易状态
			benefit.setTradeStatus(payStatus);
			//更新token
			benefit.setToken(
					Sha256.getSha256Hash(benefit.getTradeNo(), benefit.getTradeStatus(), CommonStatus.SIGN_KEY_NUM));
			//保存记录信息
			gjfMemberInfoDao.update(benefit);
            
			Map<String, Object> attrsTradeLog = new HashMap<String, Object>();
			Map<String, Object> propsTradeLog = new HashMap<String, Object>();
			attrsTradeLog.put("linkTradeNo", tradeNo);
			propsTradeLog.put("payTradeNo", payTradeNo);
			propsTradeLog.put("tradeTime", new Date());
			propsTradeLog.put("tradeStatus", "1".equals(payStatus) ? "1" : "0");
			gjfMemberInfoDao.update(GjfMemberTradeLog.class, propsTradeLog, attrsTradeLog);

			//如果交易状态为1，计算分红权和资金池
			if (payStatus.equals("1")) {
				gjfBenefitInfoService.updateMemberDividendsNumNotify(benefit.getMemberId().getMobile(),
						benefit.getStoreId().getMemberId().getMobile(), benefit.getTradeMoney().doubleValue(),
						benefit.getTradeNo(),benefit.getMerchantGrade());
			}
		}
		//返回信息
		return new ResultVo(200, payStatus.equals("1") ? "支付成功" : "支付失败",
				BeanUtilsEx.copyBean(MemberTradeBenefitVo.class, benefit));
	}

	/*
	 * 查找前端商家查询自己的让利流水记录
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.member.GjfTradeService#findStoreBenefit(int,
	 * int, java.lang.Long)
	 */
	@Override
	public ResultVo findStoreBenefit(int pageNo, int pageSize, Long storeId) {
		return new ResultVo(200, "查询成功", gjfMemberInfoDao.findStoreBenefit(pageNo, pageSize, storeId));
	}

	/*
	 * 查询用户的商家给他的让利记录
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.member.GjfTradeService#findMemberBenefit(int,
	 * int, java.lang.String)
	 */
	public ResultVo findMemberBenefit(int pageNo, int pageSize, String account) {
		return new ResultVo(200, "查询成功", gjfMemberInfoDao.findMemberBenefit(pageNo, pageSize, account));
	}

	/*
	 * 查询后台商家让利记录
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.member.GjfTradeService#findStoreBenefitPager(
	 * int, int, java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ResultVo findStoreBenefitPager(int pageNo, int pageSize, Long storeId) {
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("storeId", storeId);
		List<GjfMemberTradeBenefit> gjfMemberTradeBenefits = gjfMemberInfoDao.queryList(GjfMemberTradeBenefit.class,
				(pageNo - 1) * pageSize, pageSize, "addTime", "desc", attrs);
		return new ResultVo(200, "查询成功",
				new Pager(pageSize, pageNo,
						Integer.parseInt(
								String.valueOf(gjfMemberInfoDao.queryTotalRecord(GjfMemberTradeBenefit.class, attrs))),
				gjfMemberTradeBenefits));
	}

	/*
	 * 查询所有提现记录
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.member.GjfTradeService#findAllTradeInBack(java.
	 * lang.Integer, java.lang.Integer, java.lang.Long, java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.Long)
	 */
	@Override
	public ResultVo findAllTradeInBack(Integer pageNo, Integer pageSize, Long memberId, String holder, String bankCard,
			String mobile, String addTime, String endTime, String tradeStatus, String ste, Long id) throws Exception {
		return new ResultVo(200, "查询成功", gjfTradeDao.getGjfTrade(pageNo, pageSize, memberId, holder, bankCard, mobile,
				addTime, endTime, tradeStatus, ste, id));
	}

	/*
	 * 查询让利，导出记录，分页，模糊查询
	 * 
	 * @see cc.messcat.gjfeng.service.member.GjfTradeService#findAllBenefit(int,
	 * int, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public ResultVo findAllBenefit(int pageNo, int pageSize, String name, String storeName, String payType,
			String tradeStatus, String addTime, String endTime, String ste, String directMemberName,
			String directMerchantsName) throws Exception {
		return new ResultVo(200, "查询成功", gjfTradeDao.findAllBenefit(pageNo, pageSize, name, storeName, payType,
				tradeStatus, addTime, endTime, ste, directMemberName, directMerchantsName));

	}

	/*
	 * 查询银行卡
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.member.GjfTradeService#finMemberBankById(java.
	 * lang.Long, java.lang.String)
	 */
	@Override
	public ResultVo finMemberBankById(Long bankId, String moblie) {
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("id", bankId);
		attrs.put("memberId.mobile", moblie);
		return new ResultVo(200, "查询成功", gjfMemberInfoDao.query(GjfMemberBank.class, attrs));
	}

	/*
	 * 根据时间段查询商家让利记录
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.member.GjfTradeService#findBenefitByTime(int,
	 * int, java.lang.String, java.lang.String)
	 */
	@Override
	public ResultVo findBenefitByTime(int pageNo, int pageSize, Long id, String startTime, String endTime) {
		return new ResultVo(200, "查询成功", gjfTradeDao.findBenefitByTime(pageNo, pageSize, id, startTime, endTime));
	}

	/*
	 * 查询支付明细
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.member.GjfTradeService#findTradeLogByPage(int,
	 * int, java.lang.String)
	 */
	@Override

	public ResultVo findTradeLogByPage(int pageNo, int pageSize, String ste, String name, String storeName,
			String addTime, String endTime, String tradeNo, String payTradeNo, String tradeType, String tradeStatus)
					throws ParseException {
		return new ResultVo(200, "查询成功", gjfTradeDao.findTradeLogByPage(pageNo, pageSize, ste, name, storeName, addTime,
				endTime, tradeNo, payTradeNo, tradeType, tradeStatus));

	}

	/*
	 * 统计当前条件支付明细
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.member.GjfTradeService#findCountTradeLog(java.
	 * lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public ResultVo findCountTradeLog(String name, String storeName, String addTime, String endTime, String tradeNo,
			String payTradeNo, String tradeType, String tradeStatus) {
		return new ResultVo(200, "查询成功", gjfTradeDao.findCountTradeLog(name, storeName, addTime, endTime, tradeNo,
				payTradeNo, tradeType, tradeStatus));
	}

	/*
	 * 充值授信额度
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.member.GjfTradeService#addShouXin(java.lang.
	 * String, java.lang.Double)
	 */
	@Override
	public ResultVo addShouXin(String type, String account, Double tradeMoney, String shouType, String fileImage) {
		Map<String, Object> attrs = new HashMap<>();
		attrs.put("mobile", account);
		GjfMemberInfo gjfMemberInfo = gjfMemberInfoDao.query(GjfMemberInfo.class, attrs);
		if (!BeanUtil.isValid(gjfMemberInfo)) {
			return new ResultVo(400, "用户不存在", null);
		}
		if (tradeMoney == 0.00) {
			return new ResultVo(400, "充值金额不能为零", null);
		}
		// 根据用户查询店铺
		Map<String, Object> attrsStore = new HashMap<String, Object>();
		attrsStore.put("memberId.id", gjfMemberInfo.getId());
		attrsStore.put("storeStatus", "1");
		attrsStore.put("isDel", "1");
		GjfStoreInfo gjfStoreInfo = gjfMemberInfoDao.query(GjfStoreInfo.class, attrsStore);
		if (ObjValid.isNotValid(gjfStoreInfo)) {
			return new ResultVo(400, "你没有充值授信额度权限", null);
		}

		GjfMemberTrade trade = new GjfMemberTrade();
		trade.setAddTime(new Date());
		trade.setTradeNo(DateHelper.dataToString(new Date(), "yyyyMMddHHmmss" + RandUtil.getRandomStr(6)));
		trade.setMemberId(gjfMemberInfo);
		trade.setApplyMoney(new BigDecimal(tradeMoney));
		trade.setTradeMoney(new BigDecimal(tradeMoney).multiply(new BigDecimal(0.12)));
		trade.setTradeStatus("0");
		trade.setTradeType("0");
		trade.setShouxinType(shouType);
		// 余额支付
		if (Integer.parseInt(type) == 0) {
			trade.setPayType("0");
			trade.setTradeStatus("1");
			// 更新用户授信金额
			GjfMemberInfo info = trade.getMemberId();
			if (info.getBalanceMoney().doubleValue() < (new BigDecimal(tradeMoney).multiply(new BigDecimal(0.12)))
					.doubleValue()) {
				return new ResultVo(400, "余额不足，请选择其他支付方式", null);
			}
			info.setLineOfCrade(info.getLineOfCrade().add(trade.getApplyMoney()));
			info.setBalanceMoney(
					info.getBalanceMoney().subtract(new BigDecimal(tradeMoney).multiply(new BigDecimal(0.12))));
			info.setWithdrawalMoney(
					info.getWithdrawalMoney().subtract(new BigDecimal(tradeMoney).multiply(new BigDecimal(0.12))));
			gjfMemberInfoDao.update(info);
		}
		// 微信支付
		if (Integer.parseInt(type) == 1) {
			trade.setPayType("1");
		}

		// 支付宝支付
		if (Integer.parseInt(type) == 2) {
			trade.setPayType("2");
		}

		// 银联支付
		if (Integer.parseInt(type) == 3) {
			trade.setPayType("3");
		}
		// 后台充值
		if (Integer.parseInt(type) == 5) {
			trade.setPayType("5");
			trade.setTradeStatus("1");
		}
		// 线下充值
		if (Integer.parseInt(type) == 6 && Integer.valueOf(shouType) == 1) {
			trade.setPayType("6");
			trade.setShouxinCredntialsImg(fileImage);
		}
		trade.setToken(Sha256.getSha256Hash(trade.getTradeNo(), trade.getTradeStatus(), CommonStatus.SIGN_KEY_NUM));
		gjfMemberInfoDao.save(trade);

		// 添加支付明细日志
		GjfMemberTradeLog gjfMemberTradeLog = new GjfMemberTradeLog();
		gjfMemberTradeLog.setTradeNo(DateHelper.dataToString(new Date(), "yyyyMMddHHmmss" + RandUtil.getRandomStr(6)));
		gjfMemberTradeLog.setLinkTradeNo(trade.getTradeNo());
		gjfMemberTradeLog.setTradeMoney(trade.getTradeMoney());
		gjfMemberTradeLog.setMemberId(gjfMemberInfo);
		gjfMemberTradeLog.setStoreId(gjfStoreInfo);
		gjfMemberTradeLog.setTradeStatus("0");
		if (Integer.parseInt(type) == 1) {
			gjfMemberTradeLog.setTradeType("3");
			gjfMemberTradeLog.setTradeTrmo("微信支付--充值授信额度");
		} else if (Integer.parseInt(type) == 2) {
			gjfMemberTradeLog.setTradeType("4");
			gjfMemberTradeLog.setTradeTrmo("支付宝支付--充值授信额度");
		} else if (Integer.parseInt(type) == 3) {
			gjfMemberTradeLog.setTradeType("5");
			gjfMemberTradeLog.setTradeTrmo("银联支付--充值授信额度");
		} else if (Integer.parseInt(type) == 5) {
			gjfMemberTradeLog.setTradeStatus("1");
			gjfMemberTradeLog.setTradeType("7");
			gjfMemberTradeLog.setTradeTrmo("后台授信充值消费--充值授信额度");
		} else if (Integer.parseInt(type) == 0) {
			gjfMemberTradeLog.setTradeStatus("1");
			gjfMemberTradeLog.setTradeType("0");
			gjfMemberTradeLog.setTradeTrmo("余额--充值授信额度");
			gjfMemberTradeLog.setTradeTime(new Date());
		} else if (Integer.parseInt(type) == 6) {
			gjfMemberTradeLog.setTradeStatus("0");
			gjfMemberTradeLog.setTradeType("8");
			gjfMemberTradeLog.setTradeTrmo("线下--充值授信额度");
			gjfMemberTradeLog.setTradeTime(new Date());
		}
		gjfMemberTradeLog.setAddTime(new Date());
		gjfMemberTradeLog.setToken(Sha256.getSha256Hash(gjfMemberTradeLog.getTradeNo(),
				gjfMemberTradeLog.getTradeType(), CommonStatus.SIGN_KEY_NUM));
		gjfMemberInfoDao.save(gjfMemberTradeLog);

		return new ResultVo(200, "充值成功", trade);
	}

	/*
	 * 修改充值授信额度的支付状态
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.member.GjfTradeService#updateShouXinPayStatus(
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public ResultVo updateShouXinPayStatus(String tradeNo, String payTradeNo, String payStatus) {
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("tradeNo", tradeNo);
		GjfMemberTrade trade = gjfMemberInfoDao.query(GjfMemberTrade.class, attrs);
		if (ObjValid.isNotValid(trade)) {
			return new ResultVo(400, "记录不存在", null);
		}
		if ("0".equals(trade.getTradeStatus())) {
			trade.setTradeStatus(payStatus);
			trade.setToken(Sha256.getSha256Hash(trade.getTradeNo(), trade.getTradeStatus(), CommonStatus.SIGN_KEY_NUM));
			gjfMemberInfoDao.update(trade);

			GjfMemberInfo info = trade.getMemberId();
			info.setLineOfCrade(info.getLineOfCrade().add(trade.getApplyMoney()));
			gjfMemberInfoDao.update(info);

			Map<String, Object> attrsTradeLog = new HashMap<String, Object>();
			Map<String, Object> propsTradeLog = new HashMap<String, Object>();
			attrsTradeLog.put("linkTradeNo", tradeNo);
			propsTradeLog.put("payTradeNo", payTradeNo);
			propsTradeLog.put("tradeTime", new Date());
			propsTradeLog.put("tradeStatus", "1".equals(payStatus) ? "1" : "0");
			gjfMemberInfoDao.update(GjfMemberTradeLog.class, propsTradeLog, attrsTradeLog);
		}

		return new ResultVo(200, "支付成功", null);
	}

	/*
	 * 获取授信额度记录
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.member.GjfTradeService#getShouXinRc(java.lang.
	 * Integer, java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ResultVo getShouXinRc(Integer pageNo, Integer pageSize, String account) {
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("memberId.mobile", account);
		attrs.put("tradeType", "0");
		return new ResultVo(200, "查询成功",
				new Pager(pageSize, pageNo,
						Integer.parseInt(
								String.valueOf(gjfMemberInfoDao.queryTotalRecord(GjfMemberTrade.class, attrs))),
				gjfMemberInfoDao.queryList(GjfMemberTrade.class, (pageNo - 1) * pageSize, pageSize, "addTime", "desc",
						attrs)));
	}

	/*
	 * 查询用户代理信息
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.member.GjfTradeService#findAgentInfo(java.lang.
	 * String)
	 */
	@Override
	public ResultVo findAgentInfo(String account) {
		// 1.查询用户信息，获取代理累计领取金额和待结算金额
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("mobile", account);
		GjfMemberInfo memberInfo = gjfMemberInfoDao.query(GjfMemberInfo.class, attrs);

		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("agentMoney", 0.00);
		dataMap.put("agentTotalMoney", 0.00);
		dataMap.put("noStoreTotalBenefit", 0.00);
		dataMap.put("storeTotalBenefit", 0.00);

		if (ObjValid.isNotValid(memberInfo)) {
			return new ResultVo(400, "用户不存在", dataMap);
		}
		String identity = memberInfo.getIdentity();
		if (!identity.equals("1") && !identity.equals("2") && !identity.equals("3")) {
			return new ResultVo(400, "用户不是代理", dataMap);
		}

		dataMap.put("agentMoney", memberInfo.getAgentMoney());
		dataMap.put("agentTotalMoney", memberInfo.getAgentTotalMoney());

		// 2.统计历史以来代理所代的商家的总让利金额和还未结算让利金额

		/*
		 * (1)查询出当前用户结算的最后一条流水记录的时间 (2)根据时间查询大于当前时间的所有商家让利金额
		 * 
		 */
		Long addressId = null;
		String agentType = null;
		if (identity.equals("1")) {
			agentType = "6";
		} else if (identity.equals("2")) {
			addressId = memberInfo.getAreaId().getId();
			agentType = "5";
		} else if (identity.equals("3")) {
			addressId = memberInfo.getCityId().getId();
			agentType = "4";
		}
		// 求未统计
		Double noStoreTotalBenefit = gjfTradeDao.findAgentInfo(memberInfo.getId(), addressId, agentType, "0");
		// 求全部
		Double storeTotalBenefit = gjfTradeDao.findAgentInfo(memberInfo.getId(), addressId, agentType, "1");

		dataMap.put("noStoreTotalBenefit", noStoreTotalBenefit);
		dataMap.put("storeTotalBenefit", storeTotalBenefit);

		return new ResultVo(200, "查询成功", dataMap);
	}

	/*
	 * 查询用户代理分红历史记录
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.member.GjfTradeService#findAgentHistory(java.
	 * lang.Integer, java.lang.Integer, java.lang.String)
	 */
	@Override
	public ResultVo findAgentHistory(Integer pageNo, Integer pageSize, String account) {
		GjfMemberInfo gjfMemberInfo = gjfMemberInfoService.findMember(account);
		if (ObjValid.isNotValid(gjfMemberInfo)) {
			return new ResultVo(400, "用户不存在", null);
		}
		String agentType = "0";
		if (gjfMemberInfo.getIdentity().equals("1")) {
			agentType = "6";
		} else if (gjfMemberInfo.getIdentity().equals("2")) {
			agentType = "5";
		} else if (gjfMemberInfo.getIdentity().equals("3")) {
			agentType = "4";
		} else {
			return new ResultVo(400, "用户不是代理", null);
		}
		return gjfTradeDao.findAgentHistory(pageNo, pageSize, account, agentType);
	}

	/*
	 * 获取提现详细信息
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.member.GjfTradeService#findMemberTradeDetail(
	 * java.lang.Long, java.lang.String)
	 */
	@Override
	public ResultVo findMemberTradeDetail(Long id, String token) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("token", token);
		return new ResultVo(200, "查询成功", gjfMemberInfoDao.query(GjfMemberTrade.class, map));
	}

	// 发送mq消息
	public void sendMessage(GjfMemberTrade gjfMemberTrade) {
		// 发送短信消息通知
		try {
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("mobile", gjfMemberTrade.getMemberId().getMobile());
			dataMap.put("variable0", gjfMemberTrade.getMemberId().getName());
			if (gjfMemberTrade.getTradeType().equals("1") && gjfMemberTrade.getTradeStatus().equals("1")) {
				// 成功
				dataMap.put("variable1", gjfMemberTrade.getTradeMoney().setScale(2, BigDecimal.ROUND_DOWN));
				dataMap.put("templateCode", CommonProperties.MNS_TEMPLATE_DRAWCASH_SUCC);
			} else if (gjfMemberTrade.getTradeType().equals("1") && gjfMemberTrade.getTradeStatus().equals("2")) {
				// 失败
				dataMap.put("variable1", CommonProperties.MNS_TEMPLATE_CUSTOMER_SERVICE);
				dataMap.put("templateCode", CommonProperties.MNS_TEMPLATE_DRAWCASH_FAIL);
			}
			Object toJSON = JSONObject.fromObject(dataMap);
			final String str = toJSON.toString();
			notifyJmsTemplate.send("SmsSendNotify", new MessageCreator() {
				public Message createMessage(Session session) throws JMSException {
					Message obj = session.createTextMessage(str);
					return obj;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * 查询我的下级代理
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.member.GjfTradeService#findNextAgent(java.lang.
	 * String, java.lang.String)
	 */
	@Override
	public ResultVo findNextAgent(String account, Integer pageNo, Integer pageSize) {
		String agentType = "";
		if (StringUtil.isBlank(account)) {
			return new ResultVo(400, "查询失败，用户不存在", null);
		}
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("mobile", account);
		GjfMemberInfo gjfMemberInfo = gjfMemberInfoDao.query(GjfMemberInfo.class, attrs);
		if (ObjValid.isNotValid(gjfMemberInfo)) {
			return new ResultVo(400, "查询失败，用户不存在", null);
		}
		if (gjfMemberInfo.getIdentity().equals("1")) {
			agentType = "6";
		} else if (gjfMemberInfo.getIdentity().equals("2")) {
			agentType = "5";
		} else if (gjfMemberInfo.getIdentity().equals("3")) {
			agentType = "4";
		} else {
			return new ResultVo(400, "用户不是代理", null);
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();
		Object o = gjfTradeDao.findNextAgent(account, agentType, pageNo, pageSize, "0");
		Object o1 = gjfTradeDao.findNextAgent(account, agentType, pageNo, pageSize, "1");
		dataMap.put("agentList", o);
		dataMap.put("totalCount", o1);
		dataMap.put("agentType", agentType);
		return new ResultVo(200, "查询成功", dataMap);
	}

	/**
	 * 我的钱包--销售福利
	 * 
	 * @param account
	 * @return
	 */
	@Override
	public ResultVo findMemberSalesWelfare(String account, String tradeType) {
		List<GjfMemberTradeDiviHistory> list = gjfTradeDao.findMemberTradeDiviHis(account, tradeType);
		return new ResultVo(200, "查询成功", list);
	}

	/**
	 * 我的钱包---福利权益
	 * 
	 * @param account
	 * @return
	 */
	@Override
	public ResultVo findMemberParticipate(String account, String type) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("diviStatus", "0");
		map.put("diviMemberType", type);
		map.put("memberId.mobile", account);
		List<GjfMemberTradeDivi> list = gjfMemberInfoDao.queryList(GjfMemberTradeDivi.class, "addTime", "desc", map);
		return new ResultVo(200, "查询成功", list);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ResultVo findMemberInterests(String account, String type) {
		List list = new ArrayList<>();
		Map<String, Object> attr = new HashMap<String, Object>();
		attr.put("tradeStatus", "1");
		if (Integer.parseInt(type) == 0) {
			attr.put("memberId.mobile", account);
		} else {
			attr.put("storeId.memberId.mobile", account);
		}

		List<GjfMemberTradeBenefit> list0 = gjfMemberInfoDao.queryList(GjfMemberTradeBenefit.class, "addTime", "desc",
				attr);
		if (list0.size() > 0) {
			for (int i = 0; i < list0.size(); i++) {
				Map map0 = new HashMap<>();
				GjfMemberTradeBenefit bene = list0.get(i);
				map0.put("nickName", bene.getMemberId().getNickName());
				if (Integer.parseInt(type) == 0) {
					map0.put("tradeMoney", bene.getTradeMoney());
				} else {
					map0.put("tradeMoney", bene.getBenefitMoney());
				}
				map0.put("payType", bene.getPayType());
				map0.put("addTime", bene.getAddTime());
				list.add(map0);
			}

		}

		List<GjfOrderInfo> list1 = gjfTradeDao.findMemberInterests(account, type);
		if (list1.size() > 0) {
			for (int i = 0; i < list1.size(); i++) {
				Map map0 = new HashMap<>();
				GjfOrderInfo bene = list1.get(i);
				map0.put("nickName", bene.getMemberId().getNickName());
				map0.put("tradeMoney", bene.getOrderTotalAmount());
				map0.put("addTime", bene.getAddTime());
				map0.put("payType", bene.getPayTime());
				list.add(map0);
			}

		}

		// 对list进行排序
		Collections.sort(list, new Comparator<Map>() {

			@Override
			public int compare(Map map1, Map map2) {
				// TODO Auto-generated method stub
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				ParsePosition p = new ParsePosition(0);
				Date time1 = sdf.parse(map1.get("addTime").toString(), p);
				ParsePosition p1 = new ParsePosition(0);
				Date time2 = sdf.parse(map2.get("addTime").toString(), p1);
				int i = time1.compareTo(time2);
				if (i > 0) {
					return -1;
				}
				if (i == 0) {
					return 0;
				}
				return 1;
			}

		});
		return new ResultVo(200, "查询成功", list);
	}

	@Override
	public ResultVo findTradeAmountInBack(String holder, String bankCard, String mobile, String addTime, String endTime,
			String tradeStatus, Long id) throws Exception {
		return new ResultVo(200, "查询成功",
				gjfTradeDao.findTradeAmountInBack(holder, bankCard, mobile, addTime, endTime, tradeStatus, id));
	}

	/*
	 * 根据Id查询商家让利明细
	 */
	@Override
	public ResultVo findMemberTradeBenefitById(Long id, String token) {
		if (ObjValid.isNotValid(id)) {
			throw new MyException(400, "参数错误", null);
		}
		if (ObjValid.isNotValid(token)) {
			throw new MyException(400, "参数错误", null);
		}
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("id", id);
		attrs.put("token", token);
		return new ResultVo(200, "查询成功", gjfTradeDao.query(GjfMemberTradeBenefit.class, attrs));
	}

	/*
	 * 查询用户O2O订单
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ResultVo findMemberO2OHistory(int pageNo, int pageSize, Long id, String token) {
		if (ObjValid.isNotValid(id)) {
			throw new MyException(400, "参数错误", null);
		}
		if (ObjValid.isNotValid(token)) {
			throw new MyException(400, "参数错误", null);
		}
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("memberId.id", id);
		attrs.put("memberId.token", token);
		Pager pager = new Pager(pageSize, pageNo,
				Integer.parseInt(String.valueOf(gjfTradeDao.queryTotalRecord(GjfMemberTradeBenefit.class, attrs))),
				gjfTradeDao.queryList(GjfMemberTradeBenefit.class, (pageNo - 1) * pageSize, pageSize, "addTime", "desc",
						attrs));
		return new ResultVo(200, "查询成功", pager);
	}

	/*
	 * 查询用户历史提现总额
	 * 
	 * @see cc.messcat.gjfeng.service.member.GjfTradeService#
	 * findMemberWithdrawHistoryMoney(java.lang.Long, java.lang.String)
	 */
	@Override
	public ResultVo findMemberWithdrawHistoryMoney(Long id, String token) {
		if (ObjValid.isNotValid(id)) {
			throw new MyException(400, "参数错误", null);
		}
		if (ObjValid.isNotValid(token)) {
			throw new MyException(400, "参数错误", null);
		}
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("id", id);
		attrs.put("token", token);
		GjfMemberInfo memberInfo = gjfTradeDao.query(GjfMemberInfo.class, attrs);
		if (ObjValid.isNotValid(memberInfo)) {
			throw new MyException(400, "用户不存在", null);
		}
		return new ResultVo(200, "查询成功", gjfTradeDao.findMemberWithdrawHistoryMoney(id));
	}

	/*
	 * 查询用户流水记录
	 * 
	 * @see cc.messcat.gjfeng.service.member.GjfTradeService#
	 * findBalanceMoneyHistoryByMemberId(java.lang.Integer, java.lang.Integer,
	 * java.lang.Long, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ResultVo findBalanceMoneyHistoryByMemberId(int pageNo, int pageSize, Long id, String token,
			String tradeType) {
		if (ObjValid.isNotValid(id)) {
			throw new MyException(400, "参数错误", null);
		}
		if (ObjValid.isNotValid(token)) {
			throw new MyException(400, "参数错误", null);
		}
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("memberId.id", id);
		attrs.put("memberId.token", token);
		attrs.put("tradeType", tradeType);
		Pager pager = new Pager(pageSize, pageNo,
				Integer.parseInt(String.valueOf(gjfTradeDao.queryTotalRecord(GjfMemberTradeDetail.class, attrs))),
				gjfTradeDao.queryList(GjfMemberTradeDetail.class, (pageNo - 1) * pageSize, pageSize, "tradeTime",
						"desc", attrs));
		return new ResultVo(200, "查询成功", pager);
	}

	/*
	 * 统计当前条件的商家让利汇总
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.member.GjfTradeService#findBenefitAmountInBack(
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public ResultVo findBenefitAmountInBack(String name, String storeName, String addTime, String endTime,
			String directMemberName, String directMerchantsName, String tradeStatus, String payType) {
		return new ResultVo(200, "查询成功", gjfTradeDao.findBenefitAmountInBack(name, storeName, addTime, endTime,
				directMemberName, directMerchantsName, tradeStatus, payType));
	}

	/*
	 * 查询用户分红权、分红金额流水
	 * 
	 * @see cc.messcat.gjfeng.service.member.GjfTradeService#
	 * findDiviHistoryByMemberId(int, int, java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ResultVo findDiviHistoryByMemberId(int pageNo, int pageSize, Long id) {
		if (ObjValid.isNotValid(id)) {
			throw new MyException(400, "参数错误", null);
		}
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("memberId.id", id);
		Pager pager = new Pager(pageSize, pageNo,
				Integer.parseInt(String.valueOf(gjfTradeDao.queryTotalRecord(GjfMemberTradeDivi.class, attrs))),
				gjfTradeDao.queryList(GjfMemberTradeDivi.class, (pageNo - 1) * pageSize, pageSize, "addTime", "desc",
						attrs));
		return new ResultVo(200, "查询成功", pager);
	}

	/*
	 * 查询代理未结算分红
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.member.GjfTradeService#findAgentDiviByMemberId(
	 * int, int, java.lang.Long, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ResultVo findAgentDiviByMemberId(int pageNo, int pageSize, Long id, String identity) {
		if (ObjValid.isNotValid(id)) {
			throw new MyException(400, "参数错误", null);
		}
		if (ObjValid.isNotValid(identity)) {
			throw new MyException(400, "参数错误", null);
		}
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("memberId.id", id);
		if ("1".equals(identity)) {
			attrs.put("tradeType", "6");
		} else if ("2".equals(identity)) {
			attrs.put("tradeType", "5");
		} else if ("3".equals(identity)) {
			attrs.put("tradeType", "4");
		}
		attrs.put("tradeStatus", "0");
		Pager pager = new Pager(pageSize, pageNo,
				Integer.parseInt(String.valueOf(gjfTradeDao.queryTotalRecord(GjfMemberTradeDiviHistory.class, attrs))),
				gjfTradeDao.queryList(GjfMemberTradeDiviHistory.class, (pageNo - 1) * pageSize, pageSize, "addTime",
						"desc", attrs));
		return new ResultVo(200, "查询成功", pager);
	}

	/**
	 * 获取微信信息
	 * 
	 * @return
	 */
	@Override
	public ResultVo findWeiXinPayBaseInfo(String type) {

		Map<String, Object> attr = new HashMap<>();
		attr.put("status", "1");
		attr.put("type", type);
		return new ResultVo(200, "查询成功", gjfTradeDao.query(GjfWeiXinPayInfo.class, attr));
	}

	/**
	 * 获取全部微信配置信息
	 */
	@Override
	public ResultVo findAllWeiXinInfo(Integer pageNo, Integer pageSize) {

		Map<String, Object> attrs = new HashMap<>();
		return new ResultVo(200, "查询成功",
				gjfTradeDao.queryList(GjfWeiXinPayInfo.class, (pageNo - 1) * pageSize, pageSize, "id", "asc", attrs));
	}

	/**
	 * 添加微信信息
	 */
	@Override
	public ResultVo addWeiXinInfo(GjfWeiXinPayInfo weiXinInfo) {

		gjfTradeDao.save(weiXinInfo);
		return new ResultVo(200, "添加成功", null);

	}

	/**
	 * 修改微信配置信息
	 */
	@Override
	public ResultVo updateWeiXinInfo(GjfWeiXinPayInfo weiXinInfo) {
		Map<String, Object> attrs = new HashMap<>();
		attrs.put("id", weiXinInfo.getId());
		GjfWeiXinPayInfo weixin = gjfTradeDao.query(GjfWeiXinPayInfo.class, attrs);
		if (!BeanUtil.isValid(weixin)) {
			throw new MyException(400, "微信配置信息不存在", null);
		}
		String originType = weixin.getType();
		try {
			weixin = BeanUtil.setBeanByOtherBeanWithoutNull(weixin, weiXinInfo);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		if (!originType.equals(weiXinInfo.getType())) {
			// 如果类型改变了，需要判断一下将要变成的那种类型有没有启用状态的，如果有的话，这条记录变成禁用，防止同一种类型有多个启用
			weixin.setStatus("0");
		}
		gjfTradeDao.update(weixin);
		if (weiXinInfo.getType().equals("1") || weiXinInfo.getType().equals("2")) {// type=1
																					// 微信公众号
																					// type=2
																					// h5
			WaixiConfig.reload();// 重读配置信息
		}
		return new ResultVo(200, "修改成功", null);
	}

	/**
	 * 启用微信信息
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public ResultVo updateWeiInfos(Long id) {
		Map<String, Object> attrs = new HashMap<>();
		attrs.put("id", id);
		GjfWeiXinPayInfo weixin = gjfTradeDao.query(GjfWeiXinPayInfo.class, attrs);

		Map<String, Object> attr = new HashMap<>();
		attr.put("status", "1");
		attr.put("type", weixin.getType());
		List<GjfWeiXinPayInfo> list = gjfTradeDao.queryList(GjfWeiXinPayInfo.class, "id", "asc", attr);
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				GjfWeiXinPayInfo payInfo = list.get(i);
				payInfo.setStatus("0");
				gjfTradeDao.update(payInfo);
			}
		}
		weixin.setStatus("1");
		gjfTradeDao.update(weixin);
		if (weixin.getType().equals("1") || weixin.getType().equals("2")) {// type=1
																			// 微信公众号
																			// type=2
																			// h5
			WaixiConfig.reload();// 重读配置信息
		}
		return new ResultVo(200, "启用成功", null);
	}

	/**
	 * 根据id获取微信信息
	 */
	@Override
	public ResultVo findWeiXinInfoById(Long id) {
		Map<String, Object> attrs = new HashMap<>();
		attrs.put("id", id);
		GjfWeiXinPayInfo weixin = gjfTradeDao.query(GjfWeiXinPayInfo.class, attrs);
		return new ResultVo(200, "启用成功", weixin);
	}

	/**
	 * 更新审核人员
	 * 
	 * @param name
	 */
	public void updateCheckUser(Long id, String token, String name) {
		Map<String, Object> attrs = new HashMap<>();
		attrs.put("id", id);
		attrs.put("token", token);
		GjfMemberTrade gjfMemberTrade = gjfMemberInfoDao.query(GjfMemberTrade.class, attrs);
	}

	/**
	 * 根据订单号获取提现信息
	 */
	@Override
	public ResultVo findMemberTradeByOrderSn(String orderSn) {
		Map<String, Object> attrs = new HashMap<>();
		attrs.put("tradeNo", orderSn);
		attrs.put("tradeType", "1");
		GjfMemberTrade gjfMemberTrade = gjfMemberInfoDao.query(GjfMemberTrade.class, attrs);
		return new ResultVo(200, "查询成功", gjfMemberTrade);
	}

	public GjfMemberTrade queryById(Long id, String token) {
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("id", id);
		attrs.put("token", token);
		return gjfTradeDao.query(GjfMemberTrade.class, attrs);
	}

	/**
	 * 获取代付列表
	 */
	@Override
	public ResultVo findRechargePaid(int pageNo, int pageSize) {
		Map<String, Object> attrs = new HashMap<>();
		Pager pager = new Pager(pageSize, pageNo,
				Integer.parseInt(String.valueOf(gjfTradeDao.queryTotalRecord(GjfRechargePaid.class, attrs))),
				gjfMemberInfoDao.queryList(GjfRechargePaid.class, (pageNo - 1) * pageSize, pageSize, "addTime", "desc",
						attrs));
		return new ResultVo(200, "查询成功", pager);
	}

	/**
	 * 添加代付充值记录
	 */
	@Override
	public ResultVo addRechargeRecod(GjfRechargePaid rechargePaid) {
		rechargePaid.setAddTime(new Date());
		gjfMemberInfoDao.save(rechargePaid);
		return new ResultVo(200, "添加成功", null);
	}

	/**
	 * 用户转移积分
	 */
	@Override
	public ResultVo updateMemberPointTransfer(String type, String memberMobile, String transferMemberMobile,
			BigDecimal transferMoney) {
		// 如果转移类型为空返回提示
		if (!BeanUtil.isValid(type)) {
			return new ResultVo(400, "转移类型不能为空", null);
		}
		// 如果转移金额返回提示
		if (!BeanUtil.isValid(transferMoney) && !"3".equals(type)) {
			return new ResultVo(400, "转移金额不能为空", null);
		}
		// 如果转移金额为零返回提示
		if (BeanUtil.isValid(transferMoney)) {
			if (transferMoney.doubleValue() == 0) {
				return new ResultVo(400, "转移金额不能为零", null);
			}
		}

		// 如果用户电话号码为空返回提示
		if (!BeanUtil.isValid(memberMobile)) {
			return new ResultVo(400, "用户电话号码不能为空", null);
		}
		// 如果转移用户电话号码为空返回提示
		if (!BeanUtil.isValid(memberMobile)) {
			return new ResultVo(400, "转移用户电话号码不能为空", null);
		}

		Map<String, Object> attrs = new HashMap<>();
		attrs.put("mobile", memberMobile);
		// 获取用户信息
		GjfMemberInfo member = gjfTradeDao.query(GjfMemberInfo.class, attrs);
		if (!BeanUtil.isValid(member)) {// 用户不存在
			return new ResultVo(400, "用户不存在", null);
		}
		// 获取转移用户信息
		attrs.put("mobile", transferMemberMobile);
		GjfMemberInfo transferMember = gjfTradeDao.query(GjfMemberInfo.class, attrs);
		if (!BeanUtil.isValid(transferMember)) {// 转移用户不存在
			return new ResultVo(400, "转移用户不存在", null);
		}

		// 获取基本配置信息
		attrs.remove("mobile");
		attrs.put("key", "POUNDAGE");
		// 给配置值设置默认值
		BigDecimal baseInfoValue = new BigDecimal("0");
		GjfSetBaseInfo baseInfo = gjfTradeDao.query(GjfSetBaseInfo.class, attrs);
		if (BeanUtil.isValid(baseInfo)) {
			baseInfoValue = new BigDecimal(baseInfo.getValue());
		}

		if (!"3".equals(type)) {// 不是用户合并
			// 创建交易流水对象
			GjfTransferIntegral transfer = new GjfTransferIntegral();
			// 记录用户信息
			transfer.setMemberId(member.getId());
			transfer.setMemberName(member.getName());
			transfer.setMemberMobile(member.getMobile());
			// 记录转移用户的信息
			transfer.setTransferMemberId(transferMember.getId());
			transfer.setTransferMemberName(transferMember.getName());
			transfer.setTransferMemberMobile(transferMember.getMobile());
			// 转移分红权
			if ("0".equals(type)) {
				// 判断转移的分红权是否大于可以转移的分红权
				if (transferMoney.doubleValue() > member.getDividendsNum().doubleValue()) {
					return new ResultVo(400, "分红权不足", null);
				}
				// 记录用户和转移用户转移前分红权信息
				transfer.setMemberDataBf(member.getDividendsNum());
				transfer.setTransferMemberDataBf(transferMember.getDividendsNum());
				// 计算扣除的手续费
				BigDecimal poundage = transferMoney.multiply(baseInfoValue).setScale(6, BigDecimal.ROUND_DOWN);
				// 计算给到转移用户的分红权
				BigDecimal transferDiv = transferMoney.subtract(poundage);
				// 扣除用户分红权
				member.setDividendsNum(member.getDividendsNum().subtract(transferMoney));
				// 给转移用户添加分红权
				transferMember.setDividendsNum(transferMember.getDividendsNum().add(transferDiv));
				// 记录转移信息
				transfer.setMemberDataAf(member.getDividendsNum());
				transfer.setTransferMemberDataAf(transferMember.getDividendsNum());
				transfer.setTransferData(transferMoney);
				transfer.setTransferPoundage(poundage);
				transfer.setTransferType("0");
				transfer.setActualTransferMoney(transferDiv);
				// 更新用户信息
				gjfTradeDao.update(member);
				gjfTradeDao.update(transferMember);

			} else if ("1".equals(type)) {// 转移余额
				// 判断转移余额是否大于可转移的余额
				if (transferMoney.doubleValue() > member.getBalanceMoney().doubleValue()) {
					return new ResultVo(400, "余额不足", null);
				}
				// 如果用户余额小于0返回提示
				if (member.getBalanceMoney().doubleValue() <= 0) {
					return new ResultVo(400, "余额不足", null);
				}
				// 记录用户和转移用户转移前的余额
				transfer.setMemberDataBf(member.getBalanceMoney());
				transfer.setTransferMemberDataBf(transferMember.getBalanceMoney());
				// 计算扣除的手续费
				BigDecimal poundage = transferMoney.multiply(baseInfoValue).setScale(2, BigDecimal.ROUND_DOWN);
				// 计算转移的余额
				BigDecimal transferBanMoney = transferMoney.subtract(poundage);
				// 扣除用户余额
				member.setBalanceMoney(member.getBalanceMoney().subtract(transferMoney));
				member.setWithdrawalMoney(member.getWithdrawalMoney().subtract(transferMoney));
				// 给转移用户添加余额
				transferMember.setBalanceMoney(transferMember.getBalanceMoney().add(transferBanMoney));
				transferMember.setWithdrawalMoney(transferMember.getWithdrawalMoney().add(transferBanMoney));
				// 记录转移信息
				transfer.setMemberDataAf(member.getBalanceMoney());
				transfer.setTransferMemberDataAf(transferMember.getBalanceMoney());
				transfer.setTransferData(transferMoney);
				transfer.setTransferPoundage(poundage);
				transfer.setTransferType("1");
				transfer.setActualTransferMoney(transferBanMoney);
				// 更新用户信息
				gjfTradeDao.update(member);
				gjfTradeDao.update(transferMember);

			} else if ("2".equals(type)) {// 转移积分
				// 判断转移积分是否大于可转移积分，如果大于返回提示
				if (transferMoney.doubleValue() > member.getConsumptionMoney().doubleValue()) {
					return new ResultVo(400, "积分不足", null);
				}
				// 如果用户积分小于0返回提示
				if (member.getConsumptionMoney().doubleValue() <= 0) {
					return new ResultVo(400, "余额不足", null);
				}
				// 记录用户和转移用转移前的积分
				transfer.setMemberDataBf(member.getConsumptionMoney());
				transfer.setTransferMemberDataBf(transferMember.getConsumptionMoney());
				// 计算需要扣除的手续费
				BigDecimal poundage = transferMoney.multiply(baseInfoValue).setScale(2, BigDecimal.ROUND_DOWN);
				// 计算实际转移的金额
				BigDecimal transferCons = transferMoney.subtract(poundage);
				// 扣除用户积分
				member.setConsumptionMoney(member.getConsumptionMoney().subtract(transferMoney));
				// 添加转移用户的积分
				transferMember.setConsumptionMoney(transferMember.getConsumptionMoney().add(transferCons));
				// 记录转移信息
				transfer.setMemberDataAf(member.getConsumptionMoney());
				transfer.setTransferMemberDataAf(transferMember.getConsumptionMoney());
				transfer.setTransferData(transferMoney);
				transfer.setTransferPoundage(poundage);
				transfer.setTransferType("2");
				transfer.setActualTransferMoney(transferCons);
				// 更新用户信息
				gjfTradeDao.update(member);
				gjfTradeDao.update(transferMember);
			}
			transfer.setAddTime(new Date());
			transfer.setState("1");
			gjfTradeDao.save(transfer);
			return new ResultVo(200, "转移成功", null);

		} else {// 用户合并
			BigDecimal zore = new BigDecimal(0);
			// 创建合并流水记录
			GjfMemberMerge merge = new GjfMemberMerge();
			// 记录用户合并前的数据
			merge.setMemberId(member.getId());
			merge.setMemberName(member.getName());
			merge.setMemberMobile(member.getMobile());
			merge.setMemberBalanceBF(member.getBalanceMoney());
			merge.setMemberDiviBf(member.getDividendsNum());
			merge.setMemberIntegralBf(member.getConsumptionMoney());
			merge.setMemberCousumptionBf(member.getCumulativeMoney());
			merge.setMemberResponMoneyBf(member.getInsuranceMoney());
			merge.setMemberReserveDiviBf(zore);
			// 记录合并用户合并前数据
			merge.setMergeMemberId(transferMember.getId());
			merge.setMergeMemberName(transferMember.getName());
			merge.setMergeMemberMobile(transferMember.getMobile());
			merge.setMergeMemberBalanceBf(transferMember.getBalanceMoney());
			merge.setMergeMemberDiviBf(transferMember.getDividendsNum());
			merge.setMergeMemberIntegralBf(transferMember.getConsumptionMoney());
			merge.setMergeMemberCousumptionBf(transferMember.getCumulativeMoney());
			merge.setMergeMemberResponMoneyBf(transferMember.getInsuranceMoney());
			merge.setMergeMemberReserveDiviBf(zore);

			if (member.getBalanceMoney().doubleValue() < 0 || member.getConsumptionMoney().doubleValue() < 0
					|| member.getCumulativeMoney().doubleValue() < 0) {
				return new ResultVo(400, "用户数据出现负数，不能进行数据合并", null);
			}

			// 处理余额
			// 计算余额手续费
			BigDecimal balancePoundage = member.getBalanceMoney().multiply(baseInfoValue).setScale(2,
					BigDecimal.ROUND_DOWN);
			// 实际到账余额
			BigDecimal actuclBalance = member.getBalanceMoney().subtract(balancePoundage);
			// 处理积分
			// 计算积分手续费
			BigDecimal integralPoundage = member.getConsumptionMoney().multiply(baseInfoValue).setScale(2,
					BigDecimal.ROUND_DOWN);
			// 实际到账积分
			BigDecimal actuclIntegral = member.getConsumptionMoney().subtract(integralPoundage);
			// 处理消费金额
			// 计算消费金额手续费
			BigDecimal cousumptionPoundage = member.getCumulativeMoney().multiply(baseInfoValue).setScale(2,
					BigDecimal.ROUND_DOWN);
			// 实际到账消费金额
			BigDecimal actuclCousumption = member.getCumulativeMoney().subtract(cousumptionPoundage);

			// 處理責任消費
			// 計算責任消費手續費
			BigDecimal responPoundage = member.getInsuranceMoney().multiply(baseInfoValue).setScale(2,
					BigDecimal.ROUND_DOWN);
			// 實際到賬責任消費
			BigDecimal actualRespon = member.getInsuranceMoney().subtract(responPoundage);

			// 分紅權處理
			// 分紅權手續費
			BigDecimal diviPoundage = member.getDividendsNum().multiply(baseInfoValue).setScale(6,
					BigDecimal.ROUND_DOWN);
			// 實際到賬分紅權
			BigDecimal actualDivi = member.getDividendsNum().subtract(diviPoundage);

			// 處理用戶儲備分紅權
			// 儲備池手續費
			BigDecimal reservePoundage = member.getReserveDiviNum().add(member.getDeductDiviNum())
					.multiply(baseInfoValue).setScale(6, BigDecimal.ROUND_DOWN);
			// 實際到賬儲備池分紅權
			BigDecimal actualReserve = member.getReserveDiviNum().add(member.getDeductDiviNum())
					.subtract(reservePoundage);

			// 记录转移信息
			merge.setBalancePoundage(balancePoundage);
			merge.setActualMergeBalance(actuclBalance);
			merge.setIntegralPoundage(integralPoundage);
			merge.setActualMergeIntegral(actuclIntegral);
			merge.setCousumptiomPoundage(cousumptionPoundage);
			merge.setActualMergeCousumption(actuclCousumption);
			merge.setResponPoundage(responPoundage);
			merge.setActualMergeRespon(actualRespon);
			merge.setActualMergeMemberDivi(actualDivi);
			merge.setDiviPoundage(diviPoundage);
			merge.setActualReserve(zore);
			merge.setReservePoundage(zore);
			// 处理用户数据
			member.setBalanceMoney(zore);
			member.setWithdrawalMoney(zore);
			member.setConsumptionMoney(zore);
			member.setCumulativeMoney(zore);
			member.setDividendsNum(zore);
			member.setInsuranceMoney(zore);
			// member.setReserveDiviNum(zore);
			// member.setDeductDiviNum(zore);
			// 记录用户合并后的数据
			merge.setMemberBalanceAf(member.getBalanceMoney());
			merge.setMemberIntegralAf(member.getConsumptionMoney());
			merge.setMemberCousumptionAf(member.getCumulativeMoney());
			merge.setMemberDiviAf(member.getDividendsNum());
			merge.setMemberResponMoneyAf(member.getInsuranceMoney());
			merge.setMemberReserveDiviAf(zore);
			// 处理合并用户数据
			transferMember.setBalanceMoney(transferMember.getBalanceMoney().add(actuclBalance));
			transferMember.setWithdrawalMoney(transferMember.getWithdrawalMoney().add(actuclBalance));
			transferMember.setConsumptionMoney(transferMember.getConsumptionMoney().add(actuclIntegral));
			transferMember.setCumulativeMoney(transferMember.getCumulativeMoney().add(actuclCousumption));
			transferMember.setInsuranceMoney(transferMember.getInsuranceMoney().add(actualRespon));
			transferMember.setDividendsNum(transferMember.getDividendsNum().add(actualDivi));
			// transferMember.setReserveDiviNum(transferMember.getReserveDiviNum().add(actualReserve));
			// 记录合并用户合并后的数据
			merge.setMergeMemberBalanceAf(transferMember.getBalanceMoney());
			merge.setMergeMemberIntegralAf(transferMember.getConsumptionMoney());
			merge.setMergeMemberCousumptionAf(transferMember.getCumulativeMoney());
			merge.setMergeMemberResponMoneyBf(transferMember.getInsuranceMoney());
			merge.setMergeMemberDiviAf(transferMember.getDeductDiviNum());
			merge.setMergeMemberReserveDiviAf(zore);
			// 更新用户信息
			gjfTradeDao.update(member);
			gjfTradeDao.update(transferMember);

			// 如果用户和合并用都是商户则进行商户信息的整合
			if ("1".equals(member.getType()) && "1".equals(transferMember.getType())) {
				// 查询用户的商户信息
				Map<String, Object> merAttrs = new HashMap<>();
				merAttrs.put("memberId.id", member.getId());
				GjfStoreInfo store = gjfTradeDao.query(GjfStoreInfo.class, merAttrs);
				// 查询合并用户的商户信息
				merAttrs.put("memberId.id", transferMember.getId());
				GjfStoreInfo mergeStore = gjfTradeDao.query(GjfStoreInfo.class, merAttrs);
				// 记录商户转移前的分红权和销售额
				merge.setMerchantDiviBf(store.getStoreDividendsNum());
				merge.setMerchantSaleBf(store.getStoreSaleTotalMoney());
				merge.setMerchantReserveDiviBf(zore);
				// 记录合并商户合并前的分红钱和销售额
				merge.setMergeMerchantDiviBf(mergeStore.getStoreDividendsNum());
				merge.setMergeMerchantSaleBf(mergeStore.getStoreSaleTotalMoney());
				merge.setMergeMemberReserveDiviBf(zore);
				// 处理商户销售额
				// 计算销售额手续费
				BigDecimal salePoundage = store.getStoreSaleTotalMoney().multiply(baseInfoValue)
						.setScale(BigDecimal.ROUND_DOWN, 2);
				// 计算实际到账金额
				BigDecimal actualSale = store.getStoreSaleTotalMoney().subtract(salePoundage);
				// 处理商户和合并商户的数据
				store.setStoreSaleTotalMoney(zore);
				mergeStore.setStoreSaleTotalMoney(mergeStore.getStoreSaleTotalMoney().add(actualSale));
				// 记录销售转移记录
				merge.setMerchantSaleAf(zore);
				merge.setMergeMerchantSaleAf(mergeStore.getStoreSaleTotalMoney());
				merge.setSalePoundage(salePoundage);
				merge.setActualMergeSale(actualSale);
				// 处理商户的分红权
				BigDecimal storeDiviPoundage = store.getStoreDividendsNum().multiply(baseInfoValue).setScale(6,
						BigDecimal.ROUND_DOWN);
				// 實際到賬分紅權
				BigDecimal actualStoreDivi = store.getStoreDividendsNum().subtract(storeDiviPoundage);

				// 调整商户分红权
				store.setStoreDividendsNum(zore);
				mergeStore.setStoreDividendsNum(mergeStore.getStoreDividendsNum().add(actualStoreDivi));
				// 记录合并商户分红权记录
				merge.setMerchantDiviAf(zore);
				merge.setMergeMerchantDiviAf(mergeStore.getStoreDividendsNum());
				merge.setActualMergeMerchantDivi(actualStoreDivi);
				merge.setDiviMerchantPoundage(storeDiviPoundage);

				// 處理儲備池
				// 計算手續費
				BigDecimal storeReservePoundage = store.getDeductDiviNum().multiply(baseInfoValue).setScale(6,
						BigDecimal.ROUND_DOWN);
				// 實際到賬遲不遲
				BigDecimal actualStoreReserve = store.getDeductDiviNum().subtract(storeReservePoundage);
				// store.setDeductDiviNum(zore);
				// mergeStore.setDeductDiviNum(mergeStore.getDeductDiviNum().add(actualStoreReserve));
				// 記錄合併信息
				merge.setMemberReserveDiviAf(zore);
				merge.setMergeMerchantReserveAf(zore);
				merge.setActualMerchantReserve(zore);
				merge.setMerchantReservePoundage(zore);
				// 更新商户信息
				gjfTradeDao.update(store);
				gjfTradeDao.update(mergeStore);
			} else {
				merge.setMerchantDiviBf(zore);
				merge.setMerchantDiviAf(zore);
				merge.setMerchantSaleBf(zore);
				merge.setMerchantSaleAf(zore);
				merge.setMergeMerchantDiviBf(zore);
				merge.setMergeMerchantDiviAf(zore);
				merge.setMergeMerchantSaleBf(zore);
				merge.setMergeMerchantSaleAf(zore);
				merge.setSalePoundage(zore);
				merge.setActualMergeSale(zore);
				merge.setActualMergeMerchantDivi(zore);
			}

			merge.setAddTime(new Date());
			merge.setStatus("1");
			gjfTradeDao.save(merge);
			return new ResultVo(200, "合并成功", null);
		}

	}

	/***
	 * 获取积分转移流水
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ResultVo findAllMemberPointByPage(Integer pageNo, Integer pageSize, String memberName,
			String transferMemberName, String memberMobile, String transferMemberMobile, String type) {
		Map<String, Object> attrs = new HashMap<>();
		if ("0".equals(type)) {

			if (BeanUtil.isValid(memberName)) {
				attrs.put("memberName", memberName);
			}
			if (BeanUtil.isValid(memberMobile)) {
				attrs.put("memberMobile", memberMobile);
			}
			if (BeanUtil.isValid(transferMemberName)) {
				attrs.put("transferMemberName", transferMemberName);
			}
			if (BeanUtil.isValid(transferMemberMobile)) {
				attrs.put("transferMemberMobile", transferMemberMobile);
			}
			List<GjfTransferIntegral> list = gjfTradeDao.queryList(GjfTransferIntegral.class, (pageNo - 1) * pageSize,
					pageSize, "addTime", "desc", attrs);
			Pager pager = new Pager(pageSize, pageNo,
					(int) gjfTradeDao.queryTotalRecord(GjfTransferIntegral.class, attrs), list);
			return new ResultVo(200, "获取成功", pager);
		} else {
			if (BeanUtil.isValid(memberName)) {
				attrs.put("memberName", memberName);
			}
			if (BeanUtil.isValid(memberMobile)) {
				attrs.put("memberMobile", memberMobile);
			}
			if (BeanUtil.isValid(transferMemberName)) {
				attrs.put("mergeMemberName", transferMemberName);
			}
			if (BeanUtil.isValid(transferMemberMobile)) {
				attrs.put("mergeMemberMobile", transferMemberMobile);
			}

			List<GjfMemberMerge> list = gjfTradeDao.queryList(GjfMemberMerge.class, (pageNo - 1) * pageSize, pageSize,
					"addTime", "desc", attrs);
			Pager pager = new Pager(pageSize, pageNo, (int) gjfTradeDao.queryTotalRecord(GjfMemberMerge.class, attrs),
					list);
			return new ResultVo(200, "获取成功", pager);
		}

	}

	/**
	 * 获取转移历史信息
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param account
	 * @param type
	 *            0 积分转移 1 合并用户信息
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ResultVo findTransferHistoryByPager(Integer pageNo, Integer pageSize, String account, String type) {
		Map<String, Object> attrs = new HashMap<>();
		attrs.put("memberMobile", account);
		if ("0".equals(type)) {
			List<GjfTransferIntegral> integral = gjfTradeDao.queryList(GjfTransferIntegral.class,
					(pageNo - 1) * pageSize, pageSize, "addTime", "desc", attrs);
			return new ResultVo(200, "查询成功", integral);
		} else {
			List<GjfMemberMerge> merge = gjfTradeDao.queryList(GjfMemberMerge.class, (pageNo - 1) * pageSize, pageSize,
					"addTime", "desc", attrs);
			return new ResultVo(200, "查询成功", merge);
		}

	}

	/**
	 * 后台获取合并用户信息详情
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public ResultVo findMemberMergeDetailById(Long id) {
		Map<String, Object> attrs = new HashMap<>();
		attrs.put("id", id);
		GjfMemberMerge merge = gjfTradeDao.query(GjfMemberMerge.class, attrs);
		return new ResultVo(200, "查询成功", merge);
	}

	/**
	 * 获取授信交易记录
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ResultVo findShouxinHistory(Integer pageNo, Integer pageSize, String shouxinType) {
		Map<String, Object> attrs = new HashMap<>();
		attrs.put("tradeType", "0");
		if (BeanUtil.isValid(shouxinType)) {
			attrs.put("shouxinType", shouxinType);
		}
		List<GjfMemberTrade> list = gjfTradeDao.queryList(GjfMemberTrade.class, (pageNo - 1) * pageSize, pageSize,
				"addTime", "desc", attrs);
		Pager pager = new Pager(pageSize, pageNo, (int) gjfTradeDao.queryTotalRecord(GjfMemberTrade.class, attrs),
				list);
		return new ResultVo(200, "查询成功", pager);
	}

	/**
	 * 获取授信充值详细记录
	 */
	@Override
	public ResultVo findShouxinById(Long id) {
		Map<String, Object> attrs = new HashMap<>();
		attrs.put("id", id);
		GjfMemberTrade gjfMemberTrade = gjfTradeDao.query(GjfMemberTrade.class, attrs);
		return new ResultVo(200, "查询成功", gjfMemberTrade);
	}

	/**
	 * 审核授信充值记录
	 */
	@Override
	public ResultVo updateShouxinStatus(Long id, String acduitStatus) {
		Map<String, Object> attrs = new HashMap<>();
		attrs.put("id", id);
		GjfMemberTrade trade = gjfTradeDao.query(GjfMemberTrade.class, attrs);
		if (!BeanUtil.isValid(trade)) {
			return new ResultVo(400, "交易不存在", trade);
		}
		trade.setTradeStatus(acduitStatus);
		gjfTradeDao.update(trade);
		if ("1".equals(acduitStatus)) {
			GjfMemberInfo member = trade.getMemberId();
			member.setLineOfCrade(member.getLineOfCrade().add(trade.getApplyMoney()));
			gjfTradeDao.update(member);
		}
		attrs.remove("id");
		attrs.put("linkTradeNo", trade.getTradeNo());
		GjfMemberTradeLog log = gjfTradeDao.query(GjfMemberTradeLog.class, attrs);
		log.setTradeStatus(acduitStatus);
		gjfTradeDao.update(log);
		return new ResultVo(200, "审核成功", null);
	}

	/**
	 * 查询用户天天宝记录
	 */
	@Override
	public ResultVo addMemberFhTreasureInfo(String account) {
		Map<String, Object> attrs = new HashMap<>();
		attrs.put("mobile", account);
		// 获取用户天天宝信息
		GjfFhTreasureInfo treasure = gjfTradeDao.query(GjfFhTreasureInfo.class, attrs);
		if (!BeanUtil.isValid(treasure)) {// 如果用户天天宝信息为空
			// 查询用户信息
			GjfMemberInfo memberInfo = gjfTradeDao.query(GjfMemberInfo.class, attrs);
			// 创建用户天天宝信息
			GjfFhTreasureInfo fh = new GjfFhTreasureInfo();
			fh.setMobile(memberInfo.getMobile());
			fh.setRealName(memberInfo.getName());
			fh.setNickName(memberInfo.getNickName());
			fh.setRealNameStatus(memberInfo.getRealNameState());
			fh.setMemberIdCard(memberInfo.getIdCard());
			fh.setHeadImg(memberInfo.getImgHeadUrl());
			fh.setFhTreasureMoney(new BigDecimal(0.00));
			gjfTradeDao.save(fh);
			return new ResultVo(200, "查询成功", fh);
		}
		return new ResultVo(200, "查询成功", treasure);
	}

	/**
	 * 把余额转到天天宝
	 */
	@Override
	public ResultVo addBalanceToFhTreasure(String account, Double money) {
		//如果用户电话号码为空返回提示信息
		if (StringUtil.isBlank(account)) {
			return new ResultVo(400, "用户不存在", null);
		}
        //如果转移金额为空返回提示信息
		if (ObjValid.isNotValid(money)) {
			return new ResultVo(400, "输入金额有误", null);
		}
		
		/*if (money.doubleValue() < 200) {
			return new ResultVo(400, "转入金额不能小于200元", null);
		}*/

		//创建查询条件map
		Map<String, Object> memMap = new HashMap<String, Object>();
		//用户电话号码
		memMap.put("mobile", account);
		//获取用户信息
		GjfMemberInfo mem = gjfMemberInfoDao.query(GjfMemberInfo.class, memMap);
		//如果用户细信息为空返回提示信息
		if (ObjValid.isNotValid(mem)) {
			return new ResultVo(400, "用户不存在", null);
		}

		//判断用户可以转移金额是否大于转移的金额，如果小于返回提示
		if (mem.getBalanceMoney().doubleValue() < money.doubleValue()) {
			return new ResultVo(400, "转入金额不能大于可转入金额", null);
		}
		//创建查询设置信息map
		Map<String, Object> attrs1 = new HashMap<String, Object>();
		//获取设置信息
		GjfBenefitInfo benefitInfo = gjfMemberInfoDao.query(GjfBenefitInfo.class, attrs1);
		//扣除税费后金额
		BigDecimal tradeMoney = new BigDecimal(money * (benefitInfo.getWithdrawalRatio() / 100)).setScale(1,
				BigDecimal.ROUND_DOWN); 
		//计算到账金额
		BigDecimal treasureTradeMoney=tradeMoney.multiply(new BigDecimal(benefitInfo.getInsuranceRatio()/100)).setScale(2, BigDecimal.ROUND_DOWN);
		//计算代金券金额
		BigDecimal voucherMoney=tradeMoney.subtract(treasureTradeMoney);
		//责任金额0
		BigDecimal insuranceMoney = new BigDecimal(0);
		//税费
		BigDecimal taxMoney = new BigDecimal(money).subtract(tradeMoney).subtract(insuranceMoney);
        //创建转移历史记录对象
		GjfMemberTreasureTrade trede = new GjfMemberTreasureTrade();
		//用户id
		trede.setMemberId(mem.getId());
		//用户姓名
		trede.setMemberName(mem.getName());
		//用户电话号码
		trede.setMemebrMobile(mem.getMobile());
		//交易金额
		trede.setRealMoney(new BigDecimal(money));
		//责任金额
		trede.setInsuranceMoney(insuranceMoney);
		//税费
		trede.setTaxMoney(taxMoney);
		//交易单号
		trede.setTradeNo(DateHelper.dataToString(new Date(), "yyyyMMddHHmmss" + RandUtil.getRandomStr(6)));
		//交易类型
		trede.setTradeType("3");
		//交易状态
		trede.setTradeStatus("1");
		//交易时间
		trede.setAddTime(new Date());
		//实际到账金额
		trede.setMemberTreasureTradeMoney(treasureTradeMoney);
	    //消费增长
		trede.setConGrowth(new BigDecimal(0));
		//代金券金额
		trede.setVoucherMoney(voucherMoney);
				
		//获取凤凰宝信息
		GjfFhTreasureInfo fhInfo = gjfMemberInfoDao.query(GjfFhTreasureInfo.class, memMap);
		//凤凰宝交易后金额
		trede.setMemberTreasureMoneyAf(fhInfo.getFhTreasureMoney().add(trede.getMemberTreasureTradeMoney()).setScale(2,
				BigDecimal.ROUND_DOWN));
		//凤凰宝交易前金额
		trede.setMemberTreasureMoneyBf(fhInfo.getFhTreasureMoney());
		//保存记录
		gjfMemberInfoDao.save(trede);
		
		//创建代金券奖励历史记录
		GjfMemberVouchersTradeHistory voucher=new GjfMemberVouchersTradeHistory();
		//用户id
		voucher.setMemberId(mem.getId());
		//用户名称
		voucher.setMemberName(mem.getName());
		//用户电话号码
		voucher.setMemberMobile(mem.getMobile());
		//交易金额
		voucher.setTradeMoney(voucherMoney);
		//添加时间
		voucher.setAddTime(new Date());
		//交易状态
		voucher.setTradeStatus("1");
		//交易类型
		voucher.setPayType("6");
		//交易单号
		voucher.setTradeNo(DateHelper.dataToString(new Date(), "yyyyMMddHHmmss" + RandUtil.getRandomStr(6)));
		//保存记录
		gjfMemberInfoDao.save(voucher);

		// 用户资金修改
		mem.setWithdrawalMoney(mem.getWithdrawalMoney().subtract(new BigDecimal(money)));
		mem.setBalanceMoney(mem.getBalanceMoney().subtract(new BigDecimal(money)));
		gjfMemberInfoDao.update(mem);

		// 修改资金池金额
		Map<String, Object> attrsPool = new HashMap<>();
		GjfBenefitPool gjfBenefitPool = gjfMemberInfoDao.query(GjfBenefitPool.class, attrsPool);

		// 添加资金流水
		GjfBenefitHistory benefitHistory = new GjfBenefitHistory(null, trede.getTaxMoney(),
				gjfBenefitPool.getPlatformSysPoolCur(), gjfBenefitPool.getPlatformSysPoolCur().add(trede.getTaxMoney()),
				new BigDecimal(0.00), new Date(), "16", null, new Date(), "1");

		gjfBenefitPool.setPlatformSysPoolCur(gjfBenefitPool.getPlatformSysPoolCur().add(trede.getTaxMoney()));
		gjfBenefitPool.setPlatformSysPoolTotal(gjfBenefitPool.getPlatformSysPoolTotal().add(trede.getTaxMoney()));

		gjfMemberInfoDao.update(gjfBenefitPool);
		gjfMemberInfoDao.save(benefitHistory);

		// 添加余额、提现金额、责任险余额变更记录
		GjfMemberTradeDetail detail1 = new GjfMemberTradeDetail(null, mem, trede.getTradeNo() + "-1",
				trede.getRealMoney(), new Date(), new Date(), "0", "0", "提现-余额");
		GjfMemberTradeDetail detail2 = new GjfMemberTradeDetail(null, mem, trede.getTradeNo() + "-2",
				trede.getRealMoney(), new Date(), new Date(), "1", "0", "提现-提现金额");
		gjfMemberInfoDao.save(detail1);
		gjfMemberInfoDao.save(detail2);
		if (trede.getInsuranceMoney().doubleValue() > 0) {
			GjfMemberTradeDetail detail3 = new GjfMemberTradeDetail(null, mem, trede.getTradeNo() + "-3",
					trede.getInsuranceMoney(), new Date(), new Date(), "2", "1", "提现-责任消费金额");
			gjfMemberInfoDao.save(detail3);
		}
        //修改凤凰宝信息
		fhInfo.setFhTreasureMoney(fhInfo.getFhTreasureMoney().add(trede.getMemberTreasureTradeMoney()).setScale(2,
				BigDecimal.ROUND_DOWN));
		gjfMemberInfoDao.update(fhInfo);

		mem.setInsuranceMoney(mem.getInsuranceMoney().add(insuranceMoney));
		//给用户添加代金券
		mem.setMemberVoucherMoney(mem.getMemberVoucherMoney().add(voucherMoney));
		gjfMemberInfoDao.update(mem);
		return new ResultVo(200, "转入成功", trede);
	}

	/**
	 * 添加分红宝积分转移记录
	 * 
	 * @param account
	 * @param TransferMobile
	 * @param tradeMoney
	 * @return
	 */
	@Override
	public ResultVo addTransferFhTreasureHistory(String account, String TransferMobile, double tradeMoney) {
		if (!BeanUtil.isValid(account)) {
			return new ResultVo(400, "用户不存在");
		}
		if (!BeanUtil.isValid(TransferMobile)) {
			return new ResultVo(400, "转移到的用户不存在");
		}
		if (!BeanUtil.isValid(tradeMoney)) {
			return new ResultVo(400, "转移金额不能为空");
		}
		if (tradeMoney <= 0) {
			return new ResultVo(400, "转移金额不能小于或等于零");
		}
		// 查询用户信息
		Map<String, Object> attrs = new HashMap<>();
		attrs.put("mobile", account);
		GjfMemberInfo memberInfo = gjfMemberInfoDao.query(GjfMemberInfo.class, attrs);
		if (!BeanUtil.isValid(memberInfo)) {
			return new ResultVo(400, "用户不存在");
		}
		// 查询用户天天宝信息
		GjfFhTreasureInfo treaInfo = gjfMemberInfoDao.query(GjfFhTreasureInfo.class, attrs);
		if (!BeanUtil.isValid(treaInfo)) {
			return new ResultVo(400, "用户天天宝信息不存在");
		}
		// 判断转移金额是否大于可转移金额
		if (treaInfo.getFhTreasureMoney().doubleValue() < tradeMoney) {
			return new ResultVo(400, "转移金额不足");
		}
		// 查询转移到的用户
		attrs.put("mobile", TransferMobile);
		GjfMemberInfo transferMemberInfo = gjfMemberInfoDao.query(GjfMemberInfo.class, attrs);
		if (!BeanUtil.isValid(transferMemberInfo)) {
			return new ResultVo(400, "转移到的用户不存在");
		}
		// 创建记录信息
		GjfMemberTreasureTrade trade = new GjfMemberTreasureTrade();
		trade.setMemberId(memberInfo.getId());
		trade.setMemberName(memberInfo.getName());
		trade.setMemebrMobile(memberInfo.getMobile());
		trade.setMemberTreasureMoneyBf(treaInfo.getFhTreasureMoney());
		trade.setMemberTreasureMoneyAf(treaInfo.getFhTreasureMoney().subtract(new BigDecimal(tradeMoney)));
		trade.setTransferMemberId(transferMemberInfo.getId());
		trade.setTransferMemberMobile(transferMemberInfo.getMobile());
		trade.setTransferMemberName(transferMemberInfo.getName());
		trade.setMemberTreasureTradeMoney(new BigDecimal(tradeMoney));
		treaInfo.setFhTreasureMoney(treaInfo.getFhTreasureMoney().subtract(new BigDecimal(tradeMoney)));
		// 查询转移到的用户是否创建有天天宝信息
		GjfFhTreasureInfo treasureInfo = gjfMemberInfoDao.query(GjfFhTreasureInfo.class, attrs);
		if (BeanUtil.isValid(treasureInfo)) {
			trade.setTransferMemberMoneyBf(treasureInfo.getFhTreasureMoney());

			treasureInfo.setFhTreasureMoney(treasureInfo.getFhTreasureMoney().add(new BigDecimal(tradeMoney)));
			trade.setTransferMemberMoneyAf(treasureInfo.getFhTreasureMoney());

			gjfMemberInfoDao.update(treasureInfo);
		} else {
			treasureInfo = new GjfFhTreasureInfo();
			treasureInfo.setHeadImg(transferMemberInfo.getImgHeadUrl());
			treasureInfo.setMemberIdCard(transferMemberInfo.getIdCard());
			treasureInfo.setMobile(transferMemberInfo.getMobile());
			treasureInfo.setNickName(transferMemberInfo.getNickName());
			treasureInfo.setRealName(transferMemberInfo.getName());
			treasureInfo.setRealNameStatus(transferMemberInfo.getRealNameState());
			treasureInfo.setFhTreasureMoney(new BigDecimal(tradeMoney));
			trade.setTransferMemberMoneyBf(new BigDecimal(0.00));
			trade.setTransferMemberMoneyAf(new BigDecimal(tradeMoney));
			gjfMemberInfoDao.save(treasureInfo);
		}
		trade.setTradeStatus("1");
		trade.setTradeType("1");
		trade.setAddTime(new Date());
		trade.setTradeNo(DateHelper.dataToString(new Date(), "yyyyMMddHHmmss" + RandUtil.getRandomStr(6)));
		gjfMemberInfoDao.save(trade);
		gjfMemberInfoDao.update(treaInfo);
		return new ResultVo(200, "转移成功");
	}

	/**
	 * 天天宝提现
	 * 
	 * @param account
	 * @param remark
	 * @param myBankId
	 * @param money
	 * @return
	 */
	@Override
	public ResultVo addFhTreasureDrawCash(String account, String remark, Long myBankId, Double money) {

		if (StringUtil.isBlank(account)) {
			return new ResultVo(400, "用户不存在", null);
		}
		if (ObjValid.isNotValid(myBankId)) {
			return new ResultVo(400, "请选择银行卡", null);
		}
		if (ObjValid.isNotValid(money)) {
			return new ResultVo(400, "输入金额有误", null);
		}
		if (money.doubleValue() < 160) {
			return new ResultVo(400, "提现金额不能小于160元", null);
		}
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("id", myBankId);
		attrs.put("memberId.mobile", account);
		GjfMemberBank gjfMemberBank = gjfMemberInfoDao.query(GjfMemberBank.class, attrs);
		if (ObjValid.isNotValid(gjfMemberBank)) {
			return new ResultVo(400, "请选择正确的银行卡", null);
		}

		// 获取用户信息
		Map<String, Object> memMap = new HashMap<String, Object>();
		memMap.put("mobile", account);
		GjfMemberInfo mem = gjfMemberInfoDao.query(GjfMemberInfo.class, memMap);
		if (ObjValid.isNotValid(mem)) {
			return new ResultVo(400, "用户不存在", null);
		}

		// 查询用户是否已经有一笔再提现中
		int num = gjfTradeDao.findDrawByStatus(gjfMemberBank.getMemberId().getId());
		if (num > 0) {
			return new ResultVo(400, "已经有一笔提现正在审核中，请等待审核通过", null);
		}

		// 判断同一个身份证同一天只能提现一次
		if (StringUtil.isBlank(gjfMemberBank.getMemberId().getIdCard())) {
			return new ResultVo(400, "实名认证身份证为空，不能提现", null);
		}
		int count = gjfTradeDao.findDrawByIdCard(gjfMemberBank.getMemberId().getIdCard());
		if (count > 0) {
			return new ResultVo(400, "同一身份证用户一天只能提现一次", null);
		}

		// 查询用户天天宝信息

		GjfFhTreasureInfo fhInfo = gjfTradeDao.query(GjfFhTreasureInfo.class, memMap);
		if (!BeanUtil.isValid(fhInfo)) {
			return new ResultVo(400, "用户天天宝余额不足", null);
		}
		// 1.查询当前用户的可提现金额，判断提现的金额是否大于可提现金额
		if (fhInfo.getFhTreasureMoney().doubleValue() < money.doubleValue()) {
			throw new MyException(400, "提现金额不能大于可提现金额", null);
		}
		// 2.进行提现申请

		GjfMemberTrade gjfMemberTrade = new GjfMemberTrade(null,
				DateHelper.dataToString(new Date(), "yyyyMMddHHmmss" + RandUtil.getRandomStr(6)),
				gjfMemberBank.getMemberId(), gjfMemberBank, new BigDecimal(money), new BigDecimal(money),
				new BigDecimal(0), new BigDecimal(0), new Date(), null, null, "1", "0", null, null);

		gjfMemberTrade.setToken(Sha256.getSha256Hash(gjfMemberTrade.getTradeNo(), gjfMemberTrade.getTradeStatus(),
				CommonStatus.SIGN_KEY_NUM));
		gjfMemberInfoDao.save(gjfMemberTrade);

		// 添加记录
		GjfMemberTreasureTrade trade = new GjfMemberTreasureTrade();
		trade.setMemberId(mem.getId());
		trade.setMemberName(mem.getName());
		trade.setMemebrMobile(mem.getMobile());
		trade.setMemberTreasureMoneyBf(fhInfo.getFhTreasureMoney());
		trade.setMemberTreasureMoneyAf(fhInfo.getFhTreasureMoney().subtract(new BigDecimal(money)));
		trade.setAddTime(new Date());
		trade.setTradeStatus("0");
		trade.setMemberTreasureTradeMoney(new BigDecimal(money));
		trade.setTradeNo(gjfMemberTrade.getTradeNo());
		trade.setTradeType("4");
		gjfMemberInfoDao.save(trade);
		// 用户资金修改
		fhInfo.setFhTreasureMoney(fhInfo.getFhTreasureMoney().subtract(new BigDecimal(money)));
		gjfMemberInfoDao.update(fhInfo);

		return new ResultVo(200, "提交成功,请等待审核", gjfMemberTrade);
	}

	/**
	 * 获取天天宝交易记录
	 */
	@Override
	public ResultVo findFhTreaureTradeHistory(Integer pageNo, Integer pageSize, String account) {
		/*
		 * Map<String, Object> attrs=new HashMap<>();
		 * attrs.put("memebrMobile",account); List
		 * list=gjfMemberInfoDao.queryList(GjfMemberTreasureTrade.class,
		 * (pageNo-1)*pageSize, pageSize, "addTime", "desc", attrs); Pager
		 * pager=new Pager(pageSize, pageNo,
		 * (int)gjfMemberInfoDao.queryTotalRecord(GjfMemberTreasureTrade.class,
		 * attrs), list);
		 */
		Pager pager = gjfTradeDao.findFhTreaureTradeHistory(pageNo, pageSize, account);
		return new ResultVo(200, "查询成功", pager);
	}

	/**
	 * 后台获取分红宝交易记录
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ResultVo findFhTreasureTradeHistoryByCondition(Integer pageNo, Integer pageSize, String tradeType,
			String memName, String memMobile) {
		Map<String, Object> attrs = new HashMap<>();
		if (BeanUtil.isValid(tradeType)) {
			attrs.put("tradeType", tradeType);
		}
		if (BeanUtil.isValid(memName)) {
			attrs.put("memberName", memName);
		}
		if (BeanUtil.isValid(memMobile)) {
			attrs.put("memebrMobile", memMobile);
		}
		List<GjfMemberTreasureTrade> list = gjfMemberInfoDao.queryList(GjfMemberTreasureTrade.class,
				(pageNo - 1) * pageSize, pageSize, "addTime", "desc", attrs);
		Pager pager = new Pager(pageSize, pageNo,
				(int) gjfMemberInfoDao.queryTotalRecord(GjfMemberTreasureTrade.class, attrs), list);
		return new ResultVo(200, "查询成功", pager);
	}

	/**
	 * 添加商家充值商家联盟记录
	 * 
	 * @param account
	 * @param tradeMoney
	 * @param merchantType
	 * @param payType
	 * @return
	 */
	@Override
	public ResultVo addMerchantRechargeHistory(String account, double tradeMoney, String merchantType, String payType) {
		if (!BeanUtil.isValid(account)) {
			return new ResultVo(400, "用户不存在");
		}
		// 获取用户信息
		Map<String, Object> attrs = new HashMap<>();
		attrs.put("mobile", account);
		GjfMemberInfo member = gjfMemberInfoDao.query(GjfMemberInfo.class, attrs);
		if (!BeanUtil.isValid(member)) {
			return new ResultVo(400, "用户不存在");
		}
		// 判断用户是否为商户
		/*if ("0".equals(member.getType())&&("2".equals(merchantType)||"3".equals(merchantType)||"1".equals(merchantType))) {
			return new ResultVo(400, "你不是商家，请申请成为商家后再进行相应的操作");
		}*/
		// 判断金额是否为空
		if (!BeanUtil.isValid(tradeMoney)) {
			return new ResultVo(400, "支付金额不能为空");
		}
		// 判断金额是否为零或负数
		if (tradeMoney <= 0) {
			return new ResultVo(400, "支付金额不能小于或等于零");
		}
		// 判断商户升级类型是否为空
		if (!BeanUtil.isValid(merchantType)) {
			return new ResultVo(400, "商户升级类型不能为空");
		}
		// 判断支付类型是否为空
		if (!BeanUtil.isValid(payType)) {
			return new ResultVo(400, "支付类型不能为空");
		}
		// 创建记录对象
		GjfMerchantUpgradeHistory upgradeHistory = new GjfMerchantUpgradeHistory();
		upgradeHistory.setMemberId(member.getId());
		upgradeHistory.setMemberMobile(member.getMobile());
		upgradeHistory.setMemberName(member.getName());
		upgradeHistory.setTradeMoney(new BigDecimal(tradeMoney));
		upgradeHistory.setPayType(payType);
		upgradeHistory.setGiveType("0");
		upgradeHistory.setTradeTime(new Date());
		upgradeHistory.setTradeNo(DateHelper.dataToString(new Date(), "yyyyMMddHHmmss" + RandUtil.getRandomStr(6)));
		upgradeHistory.setTradeType(merchantType);
		upgradeHistory.setAgentMoney(new BigDecimal(0.00));
		
		//运营中心id
		String opcenterIds="";
		//微信支付
		if("0".equals(payType)){
			upgradeHistory.setTradeStatus("0");
			upgradeHistory.setDirectMemberMoney(new BigDecimal(0));
		}else if("1".equals(payType)){
			upgradeHistory.setTradeStatus("0");
			upgradeHistory.setDirectMemberMoney(new BigDecimal(0));
		}else if("3".equals(payType)){//天天宝
			//查询用户天天宝信息
			Map<String, Object> fhAttrs=new HashMap<>();
			fhAttrs.put("mobile", member.getMobile());
			GjfFhTreasureInfo fh=gjfMemberInfoDao.query(GjfFhTreasureInfo.class, fhAttrs);
			if(!BeanUtil.isValid(fh)){
				return new ResultVo(400,"金额不足");
			}
			if(fh.getFhTreasureMoney().doubleValue()<tradeMoney){
				return new ResultVo(400,"金额不足");
			}
			
			
			GjfMemberTreasureTrade trade = new GjfMemberTreasureTrade();
			trade.setMemberId(member.getId());
			trade.setMemberName(member.getName());
			trade.setMemebrMobile(member.getMobile());
			trade.setMemberTreasureMoneyBf(fh.getFhTreasureMoney());
			//扣减天天宝余额
			fh.setFhTreasureMoney(fh.getFhTreasureMoney().subtract(new BigDecimal(tradeMoney)));
			upgradeHistory.setTradeStatus("1");
						
			trade.setMemberTreasureTradeMoney(new BigDecimal(tradeMoney));
			trade.setMemberTreasureMoneyAf(fh.getFhTreasureMoney());
			trade.setTradeStatus("1");
			trade.setTradeType("8");
			trade.setAddTime(new Date());
			trade.setTradeNo(DateHelper.dataToString(new Date(), "yyyyMMddHHmmss" + RandUtil.getRandomStr(6)));
			gjfMemberInfoDao.save(trade);
			
			//查询平台信息
			Map<String, Object> pafInfo=new HashMap<>();
			pafInfo.put("key", "PLATFORM");
			pafInfo.put("status", "1");
			GjfSetBaseInfo plaInfo=gjfMemberInfoDao.query(GjfSetBaseInfo.class, pafInfo);
			String platform="0";
			if(BeanUtil.isValid(plaInfo)){
				platform=plaInfo.getValue();
			}
			//赠送购买用户代金券
			if("1".equals(platform)||"2".equals(platform)){
			    //添加赠送代金券记录
				if("1".equals(merchantType)){
					GjfMemberVouchersTradeHistory vouchers=new GjfMemberVouchersTradeHistory();
					vouchers.setAddTime(new Date());
					vouchers.setMemberId(member.getId());
					vouchers.setMemberMobile(member.getMobile());
					vouchers.setMemberName(member.getName());
					vouchers.setPayType("5");
					vouchers.setRealTreadeMoney(new BigDecimal(tradeMoney));
					vouchers.setTradeMoney(new BigDecimal(tradeMoney));
					vouchers.setTradeNo(DateHelper.dataToString(new Date(), "yyyyMMddHHmmss"));
					vouchers.setTradeStatus("1");
					member.setMemberVoucherMoney(member.getMemberVoucherMoney().add(new BigDecimal(tradeMoney)));
					gjfMemberInfoDao.save(vouchers);
				}
				
			}
			
			//添加用户赠送数量记录
			//if("2".equals(anObject)){}
			
			
			// 查询推荐人信息
			attrs.remove("mobile");
			attrs.put("id", member.getSuperId());
			GjfMemberInfo superMember = gjfMemberInfoDao.query(GjfMemberInfo.class, attrs);
			if (BeanUtil.isValid(superMember)) {// 如果推荐人存在
				
				if("0".equals(superMember.getMerchantType())){//推荐会员为普通会员
					// 给推荐人20%奖励
					superMember.setDirectMemberMoney(superMember.getDirectMemberMoney().add(upgradeHistory.getTradeMoney().multiply(new  BigDecimal(0.1))));
					// 记录奖励记录信息
					upgradeHistory.setDirectMember(superMember.getId());
					upgradeHistory.setDirectMemberMoney(upgradeHistory.getTradeMoney().multiply(new  BigDecimal(0.1)));
				}else if("1".equals(superMember.getMerchantType())){//商家版
					superMember.setDirectMemberMoney(superMember.getDirectMemberMoney().add(upgradeHistory.getTradeMoney().multiply(new  BigDecimal(0.2))));
					// 记录奖励记录信息
					upgradeHistory.setDirectMember(superMember.getId());
					upgradeHistory.setDirectMemberMoney(upgradeHistory.getTradeMoney().multiply(new  BigDecimal(0.2)));
				}else  if("2".equals(superMember.getMerchantType())){//企业版
					superMember.setDirectMemberMoney(superMember.getDirectMemberMoney().add(upgradeHistory.getTradeMoney().multiply(new  BigDecimal(0.3))));
					// 记录奖励记录信息
					upgradeHistory.setDirectMember(superMember.getId());
					upgradeHistory.setDirectMemberMoney(upgradeHistory.getTradeMoney().multiply(new  BigDecimal(0.3)));
				}else  if("3".equals(superMember.getMerchantType())){//商家代理
					superMember.setDirectMemberMoney(superMember.getDirectMemberMoney().add(upgradeHistory.getTradeMoney().multiply(new  BigDecimal(0.5))));
					// 记录奖励记录信息
					upgradeHistory.setDirectMember(superMember.getId());
					upgradeHistory.setDirectMemberMoney(upgradeHistory.getTradeMoney().multiply(new  BigDecimal(0.5)));
				}else  if("4".equals(superMember.getMerchantType())||"5".equals(superMember.getMerchantType())||"6".equals(superMember.getMerchantType())||"7".equals(superMember.getMerchantType())){//企业代理
					superMember.setDirectMemberMoney(superMember.getDirectMemberMoney().add(upgradeHistory.getTradeMoney().multiply(new  BigDecimal(0.6))));
					// 记录奖励记录信息
					upgradeHistory.setDirectMember(superMember.getId());
					upgradeHistory.setDirectMemberMoney(upgradeHistory.getTradeMoney().multiply(new  BigDecimal(0.6)));
				}
				
				//微信消息推送
				
			 try{
				if(BeanUtil.isValid(superMember.getOpenId())){
					Token tokens = new Token();
					//获取token信息
					Map<String, Object> tokenAttr = new HashMap<>();
					tokenAttr.put("type", "1");
					List<GjfAccessToken> list = gjfMemberInfoDao.queryList(GjfAccessToken.class, "expirationTime", "desc", tokenAttr);
					GjfAccessToken accessTokens = null;
					if (list.size() > 0) {
						accessTokens = list.get(0);
					}
					if (BeanUtil.isValid(accessTokens)) {// 如果tokentoken信息不为空
						Date time = new Date();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						// 判断token是否失效
						if (sdf.parse(sdf.format(accessTokens.getExpirationTime()))
								.compareTo(sdf.parse(sdf.format(time))) == 1) {
							
							tokens.setAccess_token(accessTokens.getToken());
						} else {// 如果token失效
							tokens = TokenAPI.token(CommonProperties.GJFENG_APP_ID, // 吐过token信息
									CommonProperties.GJFENG_APPSECRET);
							int i = 0;
							while (!ObjValid.isValid(tokens.getAccess_token()) && i < 5) { // 如果请求失败重新获取token
								tokens = TokenAPI.token(CommonProperties.GJFENG_APP_ID, CommonProperties.GJFENG_APPSECRET);
								if (!ObjValid.isValid(tokens.getAccess_token())) { // 若取不到snsToken、则不获取openid;
									Thread.sleep(500); // 睡眠0.5 再取
								}
								i++;
							}
							
							// 保存token信息
							GjfAccessToken token = new GjfAccessToken();
							token.setAppId(CommonProperties.GJFENG_APP_ID);
							token.setAppsecret(CommonProperties.GJFENG_APPSECRET);
							token.setType("1");
							token.setExpirationTime(new Date());
							token.setToken(tokens.getAccess_token());
							gjfMemberInfoDao.save(token);
						}
					} else {
						tokens = TokenAPI.token(CommonProperties.GJFENG_APP_ID, CommonProperties.GJFENG_APPSECRET);
						// 如果请求失败重新获取token
						int i = 0;
						while (!ObjValid.isValid(tokens.getAccess_token()) && i < 5) { //
							tokens = TokenAPI.token(CommonProperties.GJFENG_APP_ID, CommonProperties.GJFENG_APPSECRET);
							if (!ObjValid.isValid(tokens.getAccess_token())) { // 若取不到snsToken、则不获取openid;
								Thread.sleep(500); // 睡眠0.5 再取
							}
							i++;
						}						
						// 保存token信息
						GjfAccessToken token = new GjfAccessToken();
						token.setAppId(CommonProperties.GJFENG_APP_ID);
						token.setAppsecret(CommonProperties.GJFENG_APPSECRET);
						token.setType("1");
						token.setExpirationTime(new Date());
						token.setToken(tokens.getAccess_token());
						gjfMemberInfoDao.save(token);
					}
					
					//进行消息推送
					MessageManager.sendMedolMerchantDirectMoney(member.getNickName(), merchantType, "联盟商家升级",upgradeHistory.getTradeNo(),upgradeHistory.getTradeMoney().toString(), upgradeHistory.getDirectMemberMoney().toString(), superMember.getOpenId(),tokens.getAccess_token());
				}
			 }catch(Exception e){
				 e.printStackTrace();
			 }					
				// 更新推荐人信息
				gjfMemberInfoDao.update(superMember);
			}
			
			//企业版代理
			if("1".equals(merchantType)||"2".equals(merchantType)||"3".equals(merchantType)){
				// 如果推荐人不为空并且推荐人为个代
				if(BeanUtil.isValid(superMember)&&"4".equals(superMember.getMerchantType())){
					//奖励金额
					BigDecimal directMoney=upgradeHistory.getTradeMoney().multiply(new BigDecimal(0.01)).setScale(2, BigDecimal.ROUND_DOWN);
					//给推荐人添加奖励金额
					superMember.setDirectMemberMoney(superMember.getDirectMemberMoney().add(directMoney));
					//创建奖励记录
					GjfMerchantModelAgentTradeHistory agentHistory=new GjfMerchantModelAgentTradeHistory();
					agentHistory.setMemberId(member.getId());
					agentHistory.setAddTime(new Date());
					agentHistory.setMemberMobile(member.getMobile());
					agentHistory.setMemberName(member.getName());
					agentHistory.setTradeMoney(upgradeHistory.getTradeMoney());
					agentHistory.setTradeStatus("1");
					agentHistory.setTradeNo(upgradeHistory.getTradeNo());
					agentHistory.setAgentDirectMoney(directMoney);
					agentHistory.setAgentMemberId(superMember.getId());
					
					gjfBenefitInfoDao.update(superMember);
					gjfBenefitInfoDao.save(agentHistory);
					
					//查询推荐人上级
					Map<String, Object> firstMap=new HashMap<>();
					firstMap.put("id", superMember.getSuperId());
					GjfMemberInfo firstSuperMem = gjfMemberInfoDao.query(GjfMemberInfo.class, firstMap);
					//如果推荐人上级不为空并且是个代
					if(BeanUtil.isValid(firstSuperMem)&&"4".equals(firstSuperMem.getMerchantType())){
						//推荐人上级奖励金额
						BigDecimal firstDirectMoney=upgradeHistory.getTradeMoney().multiply(new BigDecimal(0.01)).setScale(2, BigDecimal.ROUND_DOWN);
						//给推荐人上级添加奖励金额
						firstSuperMem.setDirectMemberMoney(firstSuperMem.getDirectMemberMoney().add(firstDirectMoney));
						//添加历史记录
						GjfMerchantModelAgentTradeHistory agentHistory1=new GjfMerchantModelAgentTradeHistory();
						agentHistory1.setMemberId(member.getId());
						agentHistory1.setAddTime(new Date());
						agentHistory1.setMemberMobile(member.getMobile());
						agentHistory1.setMemberName(member.getName());
						agentHistory1.setTradeMoney(upgradeHistory.getTradeMoney());
						agentHistory1.setTradeStatus("1");
						agentHistory1.setTradeNo(upgradeHistory.getTradeNo());
						agentHistory1.setAgentDirectMoney(firstDirectMoney);
						agentHistory1.setAgentMemberId(firstSuperMem.getId());
						gjfBenefitInfoDao.update(firstSuperMem);
						gjfBenefitInfoDao.save(agentHistory1);
					}
					
				}
								
			}
			//运营中心奖励
			if("1".equals(merchantType)||"2".equals(merchantType)||"3".equals(merchantType)){
				List<String> listArr = gjfBenefitInfoDao.findAllMemberType();
				if (null != listArr && listArr.size() > 0) {
					Map<String, String[]> dataMap = new HashMap<String, String[]>();
					for (String str : listArr) {
						if (StringUtil.isNotBlank(str)) {
							String[] strArr = str.split(",");
							String memberId = strArr[0];
							dataMap.put(memberId, strArr);
						}
					}
					List<String> personal = findPersonalIdsOperationsCenter(member.getId().toString(), dataMap);
					if (null != personal && personal.size() > 0) {
						for (String personalId : personal) {
							GjfMemberInfo memberInfo = (GjfMemberInfo) gjfBenefitInfoDao.get(Long.parseLong(personalId),
									GjfMemberInfo.class.getName());
							BigDecimal operationMoney=new BigDecimal(tradeMoney).multiply(new BigDecimal(0.005)).setScale(2,BigDecimal.ROUND_DOWN);
							if(!BeanUtil.isValid(memberInfo.getMerchantOperationMoney())){
								memberInfo.setMerchantOperationMoney(new BigDecimal(0));
							}							
							memberInfo.setMerchantOperationMoney(memberInfo.getMerchantOperationMoney().add(operationMoney));
							upgradeHistory.setOpcenterDirectMoney(operationMoney);
							gjfBenefitInfoDao.update(memberInfo);						
							opcenterIds=opcenterIds+memberInfo.getId()+",";
						}
					}
				}
			}
			
			upgradeHistory.setOpcenterMemberIds(opcenterIds);		
			member.setMerchantType(upgradeHistory.getTradeType());
			
			gjfMemberInfoDao.update(fh);
			// 更新用户信息
			gjfMemberInfoDao.update(member);
		}
		
		gjfMemberInfoDao.save(upgradeHistory);
		return new ResultVo(200, "充值成功", upgradeHistory);
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
	 * 商家充值支付回调
	 */
	@Override
	public ResultVo updateRechargeHistoryStatus(String status, String tradeNo) {
		if (!BeanUtil.isValid(tradeNo)) {
			return new ResultVo(400, "交易不存在");
		}
		// 查询交易记录
		Map<String, Object> attr = new HashMap<>();
		attr.put("tradeNo", tradeNo);
		GjfMerchantUpgradeHistory upgradeHistory = gjfMemberInfoDao.query(GjfMerchantUpgradeHistory.class, attr);
		if (!BeanUtil.isValid(upgradeHistory)) {
			return new ResultVo(400, "交易不存在");
		}
		if ("1".equals(upgradeHistory.getTradeStatus())) {
			return new ResultVo(400, "交易已成功");
		}
		// 获取用户信息
		attr.remove("tradeNo");
		attr.put("id", upgradeHistory.getMemberId());
		GjfMemberInfo member = gjfMemberInfoDao.query(GjfMemberInfo.class, attr);
		if (BeanUtil.isValid(member)) {
			
			//查询平台信息
			Map<String, Object> pafInfo=new HashMap<>();
			pafInfo.put("key", "PLATFORM");
			pafInfo.put("status", "1");
			GjfSetBaseInfo plaInfo=gjfMemberInfoDao.query(GjfSetBaseInfo.class, pafInfo);
			String platform="0";
			if(BeanUtil.isValid(plaInfo)){
				platform=plaInfo.getValue();
			}
			//赠送购买用户代金券
			if("1".equals(platform)||"2".equals(platform)){
			    //添加赠送代金券记录
				if("1".equals(upgradeHistory.getTradeType())){
					GjfMemberVouchersTradeHistory vouchers=new GjfMemberVouchersTradeHistory();
					vouchers.setAddTime(new Date());
					vouchers.setMemberId(member.getId());
					vouchers.setMemberMobile(member.getMobile());
					vouchers.setMemberName(member.getName());
					vouchers.setPayType("5");
					vouchers.setRealTreadeMoney(upgradeHistory.getTradeMoney());
					vouchers.setTradeMoney(upgradeHistory.getTradeMoney());
					vouchers.setTradeNo(DateHelper.dataToString(new Date(), "yyyyMMddHHmmss"));
					vouchers.setTradeStatus("1");
					member.setMemberVoucherMoney(member.getMemberVoucherMoney().add(upgradeHistory.getTradeMoney()));
					gjfMemberInfoDao.save(vouchers);
				}
				
			}
			
			//添加用户赠送数量记录
			if("3".equals(upgradeHistory.getTradeType())){
				GjfMerchantsGiveNum giveNum=new GjfMerchantsGiveNum();
				giveNum.setMemberId(member.getId());
				giveNum.setMemberMobile(member.getMobile());
				giveNum.setMemberName(giveNum.getMemberName());
				giveNum.setMerchants1Num(10000);
				giveNum.setMerchants2Num(10);
				giveNum.setMerchants3Num(0);
				giveNum.setMerchants4Num(0);
				giveNum.setMerchants5Num(0);
				giveNum.setMerchants6Num(0);
				giveNum.setMerchants7Num(0);
				giveNum.setStatus("1");
			}
			
			
			// 查询推荐人信息
			attr.put("id", member.getSuperId());
			GjfMemberInfo superMember = gjfMemberInfoDao.query(GjfMemberInfo.class, attr);
			if (BeanUtil.isValid(superMember)) {// 如果推荐人存在

				if("0".equals(superMember.getMerchantType())){//推荐会员为普通会员
					// 给推荐人20%奖励
					superMember.setDirectMemberMoney(superMember.getDirectMemberMoney().add(upgradeHistory.getTradeMoney().multiply(new  BigDecimal(0.1))).setScale(2, BigDecimal.ROUND_DOWN));
					// 记录奖励记录信息
					upgradeHistory.setDirectMember(superMember.getId());
					upgradeHistory.setDirectMemberMoney(upgradeHistory.getTradeMoney().multiply(new  BigDecimal(0.1)).setScale(2, BigDecimal.ROUND_DOWN));
				}else if("1".equals(superMember.getMerchantType())){//商家版
					superMember.setDirectMemberMoney(superMember.getDirectMemberMoney().add(upgradeHistory.getTradeMoney().multiply(new  BigDecimal(0.2))).setScale(2, BigDecimal.ROUND_DOWN));
					// 记录奖励记录信息
					upgradeHistory.setDirectMember(superMember.getId());
					upgradeHistory.setDirectMemberMoney(upgradeHistory.getTradeMoney().multiply(new  BigDecimal(0.2)).setScale(2, BigDecimal.ROUND_DOWN));
				}else  if("2".equals(superMember.getMerchantType())){//企业版
					superMember.setDirectMemberMoney(superMember.getDirectMemberMoney().add(upgradeHistory.getTradeMoney().multiply(new  BigDecimal(0.3))).setScale(2, BigDecimal.ROUND_DOWN));
					// 记录奖励记录信息
					upgradeHistory.setDirectMember(superMember.getId());
					upgradeHistory.setDirectMemberMoney(upgradeHistory.getTradeMoney().multiply(new  BigDecimal(0.3)).setScale(2, BigDecimal.ROUND_DOWN));
				}else  if("3".equals(superMember.getMerchantType())){//商家代理
					superMember.setDirectMemberMoney(superMember.getDirectMemberMoney().add(upgradeHistory.getTradeMoney().multiply(new  BigDecimal(0.5))).setScale(2, BigDecimal.ROUND_DOWN));
					// 记录奖励记录信息
					upgradeHistory.setDirectMember(superMember.getId());
					upgradeHistory.setDirectMemberMoney(upgradeHistory.getTradeMoney().multiply(new  BigDecimal(0.5)).setScale(2, BigDecimal.ROUND_DOWN));
				}else  if("4".equals(superMember.getMerchantType())||"5".equals(superMember.getMerchantType())||"6".equals(superMember.getMerchantType())||"7".equals(superMember.getMerchantType())){//企业代理
					superMember.setDirectMemberMoney(superMember.getDirectMemberMoney().add(upgradeHistory.getTradeMoney().multiply(new  BigDecimal(0.6))).setScale(2, BigDecimal.ROUND_DOWN));
					// 记录奖励记录信息
					upgradeHistory.setDirectMember(superMember.getId());
					upgradeHistory.setDirectMemberMoney(upgradeHistory.getTradeMoney().multiply(new  BigDecimal(0.6)).setScale(2, BigDecimal.ROUND_DOWN));
				}
				
				
				//微信消息推送		
				 try{
					if(BeanUtil.isValid(superMember.getOpenId())){
						Token tokens = new Token();
						//获取token信息
						Map<String, Object> tokenAttr = new HashMap<>();
						tokenAttr.put("type", "1");
						List<GjfAccessToken> list = gjfMemberInfoDao.queryList(GjfAccessToken.class, "expirationTime", "desc", tokenAttr);
						GjfAccessToken accessTokens = null;
						if (list.size() > 0) {
							accessTokens = list.get(0);
						}
						if (BeanUtil.isValid(accessTokens)) {// 如果tokentoken信息不为空
							Date time = new Date();
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							// 判断token是否失效
							if (sdf.parse(sdf.format(accessTokens.getExpirationTime()))
									.compareTo(sdf.parse(sdf.format(time))) == 1) {
								
								tokens.setAccess_token(accessTokens.getToken());
							} else {// 如果token失效
								tokens = TokenAPI.token(CommonProperties.GJFENG_APP_ID, // 吐过token信息
										CommonProperties.GJFENG_APPSECRET);
								int i = 0;
								while (!ObjValid.isValid(tokens.getAccess_token()) && i < 5) { // 如果请求失败重新获取token
									tokens = TokenAPI.token(CommonProperties.GJFENG_APP_ID, CommonProperties.GJFENG_APPSECRET);
									if (!ObjValid.isValid(tokens.getAccess_token())) { // 若取不到snsToken、则不获取openid;
										Thread.sleep(500); // 睡眠0.5 再取
									}
									i++;
								}
								
								// 保存token信息
								GjfAccessToken token = new GjfAccessToken();
								token.setAppId(CommonProperties.GJFENG_APP_ID);
								token.setAppsecret(CommonProperties.GJFENG_APPSECRET);
								token.setType("1");
								token.setToken(tokens.getAccess_token());
								gjfMemberInfoDao.save(token);
							}
						} else {
							tokens = TokenAPI.token(CommonProperties.GJFENG_APP_ID, CommonProperties.GJFENG_APPSECRET);
							// 如果请求失败重新获取token
							int i = 0;
							while (!ObjValid.isValid(tokens.getAccess_token()) && i < 5) { //
								tokens = TokenAPI.token(CommonProperties.GJFENG_APP_ID, CommonProperties.GJFENG_APPSECRET);
								if (!ObjValid.isValid(tokens.getAccess_token())) { // 若取不到snsToken、则不获取openid;
									Thread.sleep(500); // 睡眠0.5 再取
								}
								i++;
							}						
							// 保存token信息
							GjfAccessToken token = new GjfAccessToken();
							token.setAppId(CommonProperties.GJFENG_APP_ID);
							token.setAppsecret(CommonProperties.GJFENG_APPSECRET);
							token.setType("1");
							token.setToken(tokens.getAccess_token());
							gjfMemberInfoDao.save(token);
						}
						
						//进行消息推送
						MessageManager.sendMedolMerchantDirectMoney(member.getNickName(), upgradeHistory.getTradeType(),"联盟商家升级" ,upgradeHistory.getTradeNo(),upgradeHistory.getTradeMoney().toString(), upgradeHistory.getDirectMemberMoney().toString(), superMember.getOpenId(),tokens.getAccess_token());
					}
				 }catch(Exception e){
					 e.printStackTrace();
				 }					
				
				
				
				//企业版代理
				if("1".equals(upgradeHistory.getTradeType())||"2".equals(upgradeHistory.getTradeType())||"3".equals(upgradeHistory.getTradeType())){
					// 如果推荐人不为空并且推荐人为个代
					if(BeanUtil.isValid(superMember)&&"4".equals(superMember.getMerchantType())){
						//奖励金额
						BigDecimal directMoney=upgradeHistory.getTradeMoney().multiply(new BigDecimal(0.01)).setScale(2, BigDecimal.ROUND_DOWN);
						//给推荐人添加奖励金额
						superMember.setDirectMemberMoney(superMember.getDirectMemberMoney().add(directMoney));
						//创建奖励记录
						GjfMerchantModelAgentTradeHistory agentHistory=new GjfMerchantModelAgentTradeHistory();
						agentHistory.setMemberId(member.getId());
						agentHistory.setAddTime(new Date());
						agentHistory.setMemberMobile(member.getMobile());
						agentHistory.setMemberName(member.getName());
						agentHistory.setTradeMoney(upgradeHistory.getTradeMoney());
						agentHistory.setTradeStatus("1");
						agentHistory.setTradeNo(upgradeHistory.getTradeNo());
						agentHistory.setAgentDirectMoney(directMoney);
						agentHistory.setAgentMemberId(superMember.getId());
						
						gjfBenefitInfoDao.update(superMember);
						gjfBenefitInfoDao.save(agentHistory);
						
						//查询推荐人上级
						Map<String, Object> firstMap=new HashMap<>();
						firstMap.put("id", superMember.getSuperId());
						GjfMemberInfo firstSuperMem = gjfMemberInfoDao.query(GjfMemberInfo.class, firstMap);
						//如果推荐人上级不为空并且是个代
						if(BeanUtil.isValid(firstSuperMem)&&"4".equals(firstSuperMem.getMerchantType())){
							//推荐人上级奖励金额
							BigDecimal firstDirectMoney=upgradeHistory.getTradeMoney().multiply(new BigDecimal(0.01)).setScale(2, BigDecimal.ROUND_DOWN);
							//给推荐人上级添加奖励金额
							firstSuperMem.setDirectMemberMoney(firstSuperMem.getDirectMemberMoney().add(firstDirectMoney));
							//添加历史记录
							GjfMerchantModelAgentTradeHistory agentHistory1=new GjfMerchantModelAgentTradeHistory();
							agentHistory1.setMemberId(member.getId());
							agentHistory1.setAddTime(new Date());
							agentHistory1.setMemberMobile(member.getMobile());
							agentHistory1.setMemberName(member.getName());
							agentHistory1.setTradeMoney(upgradeHistory.getTradeMoney());
							agentHistory1.setTradeStatus("1");
							agentHistory1.setTradeNo(upgradeHistory.getTradeNo());
							agentHistory1.setAgentDirectMoney(firstDirectMoney);
							agentHistory1.setAgentMemberId(firstSuperMem.getId());
							gjfBenefitInfoDao.update(firstSuperMem);
							gjfBenefitInfoDao.save(agentHistory1);
						}
						
					}
									
				}
				
			    String opcenterIds="";
			    String isOpenOpcenter="0";
			    Map<String, Object> baseInfo=new HashMap<>();
			    baseInfo.put("key", "ISOPENOPCENTER");
			    baseInfo.put("status", "1");
			    GjfSetBaseInfo openInfo=gjfBenefitInfoDao.query(GjfSetBaseInfo.class, baseInfo);
			    if(BeanUtil.isValid(openInfo)){
			    	isOpenOpcenter=openInfo.getValue();
			    }
			    if("1".equals(isOpenOpcenter)){
			    	//运营中心奖励
					if("1".equals(upgradeHistory.getTradeType())||"2".equals(upgradeHistory.getTradeType())||"3".equals(upgradeHistory.getTradeType())){
						List<String> listArr = gjfBenefitInfoDao.findAllMemberType();
						if (null != listArr && listArr.size() > 0) {
							Map<String, String[]> dataMap = new HashMap<String, String[]>();
							for (String str : listArr) {
								if (StringUtil.isNotBlank(str)) {
									String[] strArr = str.split(",");
									String memberId = strArr[0];
									dataMap.put(memberId, strArr);
								}
							}
							List<String> personal = findPersonalIdsOperationsCenter(member.getId().toString(), dataMap);
							if (null != personal && personal.size() > 0) {
								for (String personalId : personal) {
									GjfMemberInfo memberInfo = (GjfMemberInfo) gjfBenefitInfoDao.get(Long.parseLong(personalId),
											GjfMemberInfo.class.getName());
									BigDecimal operationMoney=upgradeHistory.getTradeMoney().multiply(new BigDecimal(0.005)).setScale(2,BigDecimal.ROUND_DOWN);
									if(!BeanUtil.isValid(memberInfo.getMerchantOperationMoney())){
										memberInfo.setMerchantOperationMoney(new BigDecimal(0));
									}
									
									memberInfo.setMerchantOperationMoney(memberInfo.getMerchantOperationMoney().add(operationMoney));
									upgradeHistory.setOpcenterDirectMoney(operationMoney);
									gjfBenefitInfoDao.update(memberInfo);
									
									opcenterIds=opcenterIds+memberInfo.getId()+",";
								}
							}
						}
					}
			    }
				
				upgradeHistory.setOpcenterMemberIds(opcenterIds);
				// 更新推荐人信息
				gjfMemberInfoDao.update(superMember);
			}
			// 改变商户的升级状态

			member.setMerchantType(upgradeHistory.getTradeType());// 标准版

			// 更新用户信息
			gjfMemberInfoDao.update(member);

		}
		// 改变记录状态
		upgradeHistory.setTradeStatus(status);
		return new ResultVo(200, "更新成功", upgradeHistory);
	}

	/**
	 * 添加商家代金券交易记录
	 * 
	 * @param account
	 * @param storeId
	 * @param mobile
	 * @param amount
	 * @param payType
	 * @return
	 */
	@Override
	public ResultVo addMemberVouchersHistory(String account, String mobile, Double amount, String payType) {
		if (account.equals(mobile)) {
			return new ResultVo(400, "消费会员不能是自己", null);
		}
		if (StringUtil.isBlank(mobile)) {
			return new ResultVo(400, "消费会员不存在", null);
		}
		if (ObjValid.isNotValid(amount)) {
			return new ResultVo(400, "金额不能为空并要大于0", null);
		}
		GjfMemberInfo gjfMemberInfo = gjfMemberInfoService.findMember(mobile);
		if (ObjValid.isNotValid(gjfMemberInfo)) {
			return new ResultVo(400, "消费会员不存在", null);
		}
		if ("0".equals(gjfMemberInfo.getStatus())) {
			return new ResultVo(400, "消费会员已停用", null);
		}

		if (!BeanUtil.isValid(account)) {
			return new ResultVo(400, "用户不存在", null);
		}

		GjfMemberInfo gjfMemberInfo0 = gjfMemberInfoService.findMember(account);
		if ("0".equals(gjfMemberInfo0.getStatus())) {
			return new ResultVo(400, "用户已停用", null);
		}
		// 查询店铺信息
		Map<String, Object> attrs = new HashMap<>();
		attrs.put("memberId.id", gjfMemberInfo0.getId());
		GjfStoreInfo storeInfo = gjfMemberInfoDao.query(GjfStoreInfo.class, attrs);
		if (!BeanUtil.isValid(storeInfo)) {
			return new ResultVo(400, "商家不存在", null);
		}
		
		//获取平台信息
		String platform="0";
		//创建查询map
		Map<String, Object> platMap=new HashMap<>();
		//关键字
		platMap.put("key", "PLATFORM");
		//状态
		platMap.put("status", "1");
		//获取设置信息
		GjfSetBaseInfo platformBase=gjfMemberInfoDao.query(GjfSetBaseInfo.class, platMap);
		//如果设置信息不为空
		if(BeanUtil.isValid(platformBase)){
			//获取平台值
			platform=platformBase.getValue();
		}
		
		// 创建交易历史记录
	    GjfMemberVouchersTradeHistory vouchensTradeHistory = new GjfMemberVouchersTradeHistory();		
	    //记录商户姓名
	    vouchensTradeHistory.setMerchantName(gjfMemberInfo0.getName());
	    //记录商户电话
	    vouchensTradeHistory.setMerchantMobile(gjfMemberInfo0.getMobile());
	    //记录折扣
	    vouchensTradeHistory.setDiscountMoney(new BigDecimal(0));
	    
	    //推荐人
	    GjfMemberInfo superMemInfo=null;
	    //代理
	    GjfMemberInfo agentSupInfo=null;
	    //实际支付金额
	    BigDecimal realPayMoney=new BigDecimal(0);
		//如果平台的为2表示是凤凰供应链
		if("2".equals(platform)){				
		    //记录联盟商家类型
		    vouchensTradeHistory.setMechantType(gjfMemberInfo0.getMerchantType());
			//计算需要支付金额		
			if("1".equals(gjfMemberInfo0.getMerchantType())){//如果商户是商家版
				//计算打折金额
				BigDecimal discountMoney=new BigDecimal(amount).multiply(new BigDecimal(0.1)).multiply(new BigDecimal(0.03)).setScale(2, BigDecimal.ROUND_DOWN);
	            //折扣后的金额
				realPayMoney=new BigDecimal(amount).subtract(discountMoney);
				//记录折扣
				vouchensTradeHistory.setDiscountMoney(discountMoney);
			}
			
			//如果商户是企业版
			if("2".equals(gjfMemberInfo0.getMerchantType())){
				//计算打折金额
				BigDecimal discountMoney=new BigDecimal(amount).multiply(new BigDecimal(0.1)).multiply(new BigDecimal(0.05)).setScale(2, BigDecimal.ROUND_DOWN);
	            //折扣后的金额
				realPayMoney=new BigDecimal(amount).subtract(discountMoney);
				//记录折扣
				vouchensTradeHistory.setDiscountMoney(discountMoney);
			}
			
			//如果商户是商家代理
			if("3".equals(gjfMemberInfo0.getMerchantType())||"4".equals(gjfMemberInfo0.getMerchantType())
					||"5".equals(gjfMemberInfo0.getMerchantType())||"6".equals(gjfMemberInfo0.getMerchantType())
					||"7".equals(gjfMemberInfo0.getMerchantType())){
				//计算打折金额
				BigDecimal discountMoney=new BigDecimal(amount).multiply(new BigDecimal(0.1)).multiply(new BigDecimal(0.08)).setScale(2, BigDecimal.ROUND_DOWN);
			    //折扣后的金额
				realPayMoney=new BigDecimal(amount).subtract(discountMoney);
				//记录折扣
				vouchensTradeHistory.setDiscountMoney(discountMoney);
			}
			
			//查询推荐人
			Map<String, Object> supMap=new HashMap<>();
			//用户id
			supMap.put("id",gjfMemberInfo0.getSuperId());
			//用户状态
			supMap.put("status", "1");
		    //获取推荐人信息
			 superMemInfo=gjfMemberInfoDao.query(GjfMemberInfo.class, supMap);
			//如果推荐人不为空
			if(BeanUtil.isValid(superMemInfo)&&!"0".equals(gjfMemberInfo0.getMerchantType())){
				//获取用户联盟商家类型
				String memType=gjfMemberInfo0.getMerchantType();
				//获取推荐人联盟商家类型
				String supType=superMemInfo.getMerchantType();
				//如果推荐人等级大于用户等级
				if(Integer.valueOf(memType)<Integer.valueOf(supType)){
					//奖励金额
					BigDecimal supDirectMoney=new BigDecimal(0.00);
					//如果用户为商家版，推荐人为企业版（5%-3%）
					if("1".equals(memType)&&"2".equals(supType)){
						//计算奖励金额
						 supDirectMoney=new BigDecimal(amount).multiply(new BigDecimal(0.1)).multiply(new BigDecimal(0.02)).setScale(2, BigDecimal.ROUND_DOWN);
					}
					//如果用户为企业版，推荐人为商家代理（8%-5%）
					if("2".equals(memType)&&"3".equals(supType)){
						//计算奖励金额
						 supDirectMoney=new BigDecimal(amount).multiply(new BigDecimal(0.1)).multiply(new BigDecimal(0.03)).setScale(2, BigDecimal.ROUND_DOWN);
					}
					//如果用户为商家版，推荐为商家代理（8%-3%）
					if("1".equals(memType)&&"3".equals(supType)){
						//计算奖励金额
						 supDirectMoney=new BigDecimal(amount).multiply(new BigDecimal(0.1)).multiply(new BigDecimal(0.05)).setScale(2, BigDecimal.ROUND_DOWN);
					}
					//添加记录
					vouchensTradeHistory.setMerchantDirectMemberId(superMemInfo.getId());
					//记录推荐人电话
					vouchensTradeHistory.setMerchantDirectMemberMobile(superMemInfo.getMobile());
					//记录推荐人姓名
					vouchensTradeHistory.setMerchantDirectMemberName(superMemInfo.getName());
					//记录推荐奖励
					vouchensTradeHistory.setMerchantDirectMemberMoney(supDirectMoney);
					//记录推荐人联盟商家类型
					vouchensTradeHistory.setMerchantDirectType(superMemInfo.getMerchantType());
					
				}
				
			}
			
			
			//1.8代理奖励（1.8万直推1.8万的团队收益1%）
			List<String> listArr = gjfBenefitInfoDao.findAllMemberType();
			if (null != listArr && listArr.size() > 0) {
				Map<String, String[]> dataMap = new HashMap<String, String[]>();
				for (String str : listArr) {
					if (StringUtil.isNotBlank(str)) {
						String[] strArr = str.split(",");
						String memberId = strArr[0];
						dataMap.put(memberId, strArr);
					}
				}
				//获取代理id
				List<String> personal = findVochensDirectMember(gjfMemberInfo0.getId().toString(), dataMap);
				if (null != personal && personal.size() > 0) {
					for (String personalId : personal) {
						//获取代理信息
						GjfMemberInfo memInfo = (GjfMemberInfo) gjfBenefitInfoDao.get(Long.parseLong(personalId),
								GjfMemberInfo.class.getName());
						//查询代理的上级用户
						Map<String, Object> agentMap=new HashMap<>();
						agentMap.put("id", memInfo.getSuperId());
						agentMap.put("status", "1");
						agentSupInfo=gjfBenefitInfoDao.query(GjfMemberInfo.class, agentMap);
						if(BeanUtil.isValid(agentSupInfo)){
							if("3".equals(agentSupInfo.getMerchantType())){
								//计算奖励金额
								BigDecimal agentDirectMoney=new BigDecimal(amount).multiply(new BigDecimal(0.1)).multiply(new BigDecimal(0.01)).setScale(2, BigDecimal.ROUND_DOWN);
								//记录代理信息
								vouchensTradeHistory.setAgentMemberId(agentSupInfo.getId());
								//记录代理电话号码
								vouchensTradeHistory.setAgentMemberMobile(agentSupInfo.getMobile());
								//记录代理用户名
								vouchensTradeHistory.setAgentMemberName(agentSupInfo.getName());
								//记录奖励代理金额
								vouchensTradeHistory.setAgentMemberDirectMoney(agentDirectMoney);
								//记录代理联盟商家类型
								vouchensTradeHistory.setAgentMemberType(agentSupInfo.getMerchantType());

							}
						}
					}
				}
			}
		}

		
		
		vouchensTradeHistory.setAddTime(new Date());
		vouchensTradeHistory.setMemberId(gjfMemberInfo.getId());
		vouchensTradeHistory.setMemberMobile(gjfMemberInfo.getMobile());
		vouchensTradeHistory.setMemberName(gjfMemberInfo.getName());
		vouchensTradeHistory.setPayType(payType);
		vouchensTradeHistory.setStoreId(storeInfo.getId());
		vouchensTradeHistory.setTradeMoney(new BigDecimal(amount));
		vouchensTradeHistory.setTradeStatus("0");
		vouchensTradeHistory.setRealTreadeMoney(new BigDecimal(amount * 0.1));
		vouchensTradeHistory.setRealPayMoney(realPayMoney.multiply(new BigDecimal(0.1)).setScale(2, BigDecimal.ROUND_DOWN));

		vouchensTradeHistory
				.setTradeNo(DateHelper.dataToString(new Date(), "yyyyMMddHHmmss" + RandUtil.getRandomStr(6)));

		// 如果支付方式为授信额度就修改用户的授信额度
		if (payType.equals("4")) {
			if(gjfMemberInfo0.getLineOfCrade().doubleValue()<amount.doubleValue()){
				return new ResultVo(400,"授信金额不足");
			}
			gjfMemberInfo0.setLineOfCrade(gjfMemberInfo0.getLineOfCrade().subtract(new BigDecimal(amount)));
			vouchensTradeHistory.setTradeStatus("1");
			gjfMemberInfo.setMemberVoucherMoney(gjfMemberInfo.getMemberVoucherMoney().add(new BigDecimal(amount)));
			gjfMemberInfoDao.update(gjfMemberInfo0);
			gjfMemberInfoDao.update(gjfMemberInfo);
		}
		
		if("0".equals(payType)||"1".equals(payType)){
			vouchensTradeHistory.setTradeStatus("0");			
		}

		// 天天宝支付
		if (payType.equals("6")) {
			// 查询天天宝记录
			Map<String, Object> fhAttrs = new HashMap<>();
			fhAttrs.put("mobile", gjfMemberInfo0.getMobile());
			GjfFhTreasureInfo fhInfo = gjfMemberInfoDao.query(GjfFhTreasureInfo.class, fhAttrs);
			if (!BeanUtil.isValid(fhInfo)) {
				return new ResultVo(400, "金额不足");
			}
			if (fhInfo.getFhTreasureMoney()
					.doubleValue() < (realPayMoney.multiply(new BigDecimal(0.1)).doubleValue())) {
				return new ResultVo(400, "金额不足");
			}

			// 添加相应记录
			GjfMemberTreasureTrade trade = new GjfMemberTreasureTrade();
			trade.setMemberId(gjfMemberInfo0.getId());
			trade.setMemberName(gjfMemberInfo0.getName());
			trade.setMemebrMobile(gjfMemberInfo0.getMobile());
			trade.setMemberTreasureMoneyBf(fhInfo.getFhTreasureMoney());

			fhInfo.setFhTreasureMoney(
					fhInfo.getFhTreasureMoney().subtract(realPayMoney.multiply(new BigDecimal(0.1))));
			gjfMemberInfoDao.update(fhInfo);
			trade.setTransferMemberId(gjfMemberInfo.getId());
			trade.setTransferMemberMobile(gjfMemberInfo.getMobile());
			trade.setTransferMemberName(gjfMemberInfo.getName());
			trade.setMemberTreasureTradeMoney(realPayMoney.multiply(new BigDecimal(0.1)));
			trade.setMemberTreasureMoneyAf(fhInfo.getFhTreasureMoney());
			trade.setTradeStatus("1");
			trade.setTradeType("7");
			trade.setAddTime(new Date());
			trade.setTradeNo(DateHelper.dataToString(new Date(), "yyyyMMddHHmmss" + RandUtil.getRandomStr(6)));
			gjfMemberInfoDao.save(trade);
			gjfMemberInfo.setMemberVoucherMoney(gjfMemberInfo.getMemberVoucherMoney().add(new BigDecimal(amount)));
			gjfMemberInfoDao.update(gjfMemberInfo);
			vouchensTradeHistory.setTradeStatus("1");
			
			//如果推荐人不为空并且平台为凤凰供应链更新推荐人信息
			if(BeanUtil.isValid(superMemInfo)&&"2".equals(platform)){
				//给推荐人添加奖励金额
				superMemInfo.setDirectMemberMoney(superMemInfo.getDirectMemberMoney().add(vouchensTradeHistory.getMerchantDirectMemberMoney()));
				//更新推荐人的信息
				gjfMemberInfoDao.update(superMemInfo);
			}
			//如果代理不为空并且平台为凤凰供应链则更新代理信息
			if(BeanUtil.isValid(agentSupInfo)&&"2".equals(platform)){
				if("3".equals(agentSupInfo.getMerchantType())){
					//给代理添加奖励金额
					agentSupInfo.setDirectMemberMoney(agentSupInfo.getDirectMemberMoney().add(vouchensTradeHistory.getAgentMemberDirectMoney()));
					//更新代理信息
					gjfBenefitInfoDao.update(agentSupInfo);
				}
				
			}
		}

		gjfMemberInfoDao.save(vouchensTradeHistory);
		return new ResultVo(200, "交易成功", vouchensTradeHistory);
	}
	
	
	/**
	 * 返回运营们的id list，如果为空则找不到运营中心们
	 * 
	 * @param memberId
	 * @param map
	 * @return
	 * @throws ParseException
	 */
	public static List<String> findVochensDirectMember(String memberId, Map<String, String[]> map) {

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
		List<String> personal = findVochensDirectMembers(fatherId, map, foundMap, null);
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
	private static List<String> findVochensDirectMembers(String memberId, Map<String, String[]> map,
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
		String isPersonal = theMember[8];

		if ("3".equals(isPersonal)) {
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
			personal = findVochensDirectMembers(fatherId, map, foundMap, personal);
		}
		return personal;
	}


	/**
	 * 商家赠送代金券支付回调
	 * 
	 * @param status
	 * @param tradeNo
	 * @return
	 */
	@Override
	public ResultVo updateVoucherHistoryStatus(String status, String tradeNo) {
		if (!BeanUtil.isValid(tradeNo)) {
			return new ResultVo(400, "交易不存在");
		}

		// 查询交易记录
		Map<String, Object> attrs = new HashMap<>();
		attrs.put("tradeNo", tradeNo);
		GjfMemberVouchersTradeHistory vouchensTradeHistory = gjfMemberInfoDao.query(GjfMemberVouchersTradeHistory.class,
				attrs);
		if ("1".equals(vouchensTradeHistory.getTradeStatus())) {
			return new ResultVo(400, "交易已成功");
		}
		// 获取用户信息
		attrs.remove("tradeNo");
		attrs.put("id", vouchensTradeHistory.getMemberId());
		GjfMemberInfo memberInfo = gjfMemberInfoDao.query(GjfMemberInfo.class, attrs);
		if (!BeanUtil.isValid(memberInfo)) {
			return new ResultVo(400, "用户不存在");
		}
		
		//获取平台信息
		String platform="0";
		//创建查询map
		Map<String, Object> platMap=new HashMap<>();
		//关键字
		platMap.put("key", "PLATFORM");
		//状态
		platMap.put("status", "1");
		//获取设置信息
		GjfSetBaseInfo platformBase=gjfMemberInfoDao.query(GjfSetBaseInfo.class, platMap);
		//如果设置信息不为空
		if(BeanUtil.isValid(platformBase)){
		      //获取平台值
		      platform=platformBase.getValue();
		}
		//如果推荐人不为空
		if(BeanUtil.isValid(vouchensTradeHistory.getMerchantDirectMemberId())){
			attrs.put("id", vouchensTradeHistory.getMerchantDirectMemberId());
			attrs.put("status", "1");
			//获取推荐人
			GjfMemberInfo superInfo=gjfMemberInfoDao.query(GjfMemberInfo.class, attrs);
			//如果推荐人不为空，平台为凤凰供应链
			if(BeanUtil.isValid(superInfo)&&"2".equals(platform)){
				//给推荐人添加奖励金额
				superInfo.setDirectMemberMoney(superInfo.getDirectMemberMoney().add(vouchensTradeHistory.getMerchantDirectMemberMoney()));
				//更新推荐人的信息
				gjfMemberInfoDao.update(superInfo);
			}			
		}
		
		//如果代理id不为空
		if(BeanUtil.isValid(vouchensTradeHistory.getAgentMemberId())&&"2".equals(platform)){
			attrs.put("id", vouchensTradeHistory.getAgentMemberId());
			attrs.put("status", "1");
			//获取代理信息
			GjfMemberInfo agentMemInfo=gjfMemberInfoDao.query(GjfMemberInfo.class, attrs);
			//如果代理信息不为空,凤凰供应链为2
			if(BeanUtil.isValid(agentMemInfo)&&"2".equals(platform)){
				//给代理添加奖励金额
				agentMemInfo.setDirectMemberMoney(agentMemInfo.getDirectMemberMoney().add(vouchensTradeHistory.getAgentMemberDirectMoney()));
				//更新代理信息
				gjfBenefitInfoDao.update(agentMemInfo);
			}
		}

		memberInfo.setMemberVoucherMoney(memberInfo.getMemberVoucherMoney().add(vouchensTradeHistory.getTradeMoney()));
		vouchensTradeHistory.setTradeStatus("1");
		gjfMemberInfoDao.update(vouchensTradeHistory);
		gjfMemberInfoDao.update(memberInfo);
		return new ResultVo(200, "更新成功");
	}

	/***
	 * 后台设置商家联盟
	 * 
	 * @param type
	 * @param account
	 * @param tradeMoney
	 * @return
	 */
	@Override
	public ResultVo addMerchantModelInBack(String type, String account, double tradeMoney, Long area, Long pri,
			Long city, Date startTime, Date endTime) {
		if (!BeanUtil.isValid(account)) {
			return new ResultVo(400, "用户不存在");
		}
		// 查询用户信息
		Map<String, Object> attrs = new HashMap<>();
		attrs.put("mobile", account);
		GjfMemberInfo memberInfo = gjfMemberInfoDao.query(GjfMemberInfo.class, attrs);
		if (!BeanUtil.isValid(memberInfo)) {
			return new ResultVo(400, "用户不存在");
		}
		/*if ("0".equals(memberInfo.getType())) {
			return new ResultVo(400, "你不是商家请先升级成为商家");
		}*/
		if (!BeanUtil.isValid(type)) {
			return new ResultVo(400, "请选择充值类型");
		}
		if (!BeanUtil.isValid(tradeMoney)) {
			return new ResultVo(400, "金额不能为空");
		}

		// 创建交易历史对象
		GjfMerchantUpgradeHistory upgradeHistory = new GjfMerchantUpgradeHistory();
		upgradeHistory.setMemberId(memberInfo.getId());
		upgradeHistory.setMemberMobile(memberInfo.getMobile());
		upgradeHistory.setMemberName(memberInfo.getName());
		upgradeHistory.setPayType("2");
		upgradeHistory.setTradeTime(new Date());
		upgradeHistory.setTradeMoney(new BigDecimal(tradeMoney));
		upgradeHistory.setTradeStatus("1");
		upgradeHistory.setTradeType(type);
		upgradeHistory.setGiveType("0");
		upgradeHistory.setTradeNo(DateHelper.dataToString(new Date(), "yyyyMMddHHmmss" + RandUtil.getRandomStr(6)));
		// 查询用户的推荐人
		if (BeanUtil.isValid(memberInfo.getSuperId())) {
			attrs.remove("mobile");
			attrs.put("id", memberInfo.getSuperId());
			GjfMemberInfo superMember = gjfMemberInfoDao.query(GjfMemberInfo.class, attrs);
			// 如果推荐人不能为空
			if (BeanUtil.isValid(superMember)) {
				
				superMember.setDirectMemberMoney(superMember.getDirectMemberMoney().add(upgradeHistory.getTradeMoney().multiply(new  BigDecimal(0.2))).setScale(2, BigDecimal.ROUND_DOWN));
				// 记录奖励记录信息
				upgradeHistory.setDirectMember(superMember.getId());
				upgradeHistory.setDirectMemberMoney(upgradeHistory.getTradeMoney().multiply(new  BigDecimal(0.2)).setScale(2, BigDecimal.ROUND_DOWN));

				if("0".equals(superMember.getMerchantType())){//推荐会员为普通会员
					// 给推荐人20%奖励
					superMember.setDirectMemberMoney(superMember.getDirectMemberMoney().add(upgradeHistory.getTradeMoney().multiply(new  BigDecimal(0.1))).setScale(2, BigDecimal.ROUND_DOWN));
					// 记录奖励记录信息
					upgradeHistory.setDirectMember(superMember.getId());
					upgradeHistory.setDirectMemberMoney(upgradeHistory.getTradeMoney().multiply(new  BigDecimal(0.1)).setScale(2, BigDecimal.ROUND_DOWN));
				}else if("1".equals(superMember.getMerchantType())){//商家版
					superMember.setDirectMemberMoney(superMember.getDirectMemberMoney().add(upgradeHistory.getTradeMoney().multiply(new  BigDecimal(0.2))).setScale(2, BigDecimal.ROUND_DOWN));
					// 记录奖励记录信息
					upgradeHistory.setDirectMember(superMember.getId());
					upgradeHistory.setDirectMemberMoney(upgradeHistory.getTradeMoney().multiply(new  BigDecimal(0.2)).setScale(2, BigDecimal.ROUND_DOWN));
				}else  if("2".equals(superMember.getMerchantType())){//企业版
					superMember.setDirectMemberMoney(superMember.getDirectMemberMoney().add(upgradeHistory.getTradeMoney().multiply(new  BigDecimal(0.3))).setScale(2, BigDecimal.ROUND_DOWN));
					// 记录奖励记录信息
					upgradeHistory.setDirectMember(superMember.getId());
					upgradeHistory.setDirectMemberMoney(upgradeHistory.getTradeMoney().multiply(new  BigDecimal(0.3)).setScale(2, BigDecimal.ROUND_DOWN));
				}else  if("3".equals(superMember.getMerchantType())){//商家代理
					superMember.setDirectMemberMoney(superMember.getDirectMemberMoney().add(upgradeHistory.getTradeMoney().multiply(new  BigDecimal(0.5))).setScale(2, BigDecimal.ROUND_DOWN));
					// 记录奖励记录信息
					upgradeHistory.setDirectMember(superMember.getId());
					upgradeHistory.setDirectMemberMoney(upgradeHistory.getTradeMoney().multiply(new  BigDecimal(0.5)).setScale(2, BigDecimal.ROUND_DOWN));
				}else  if("4".equals(superMember.getMerchantType())||"5".equals(superMember.getMerchantType())||"6".equals(superMember.getMerchantType())||"7".equals(superMember.getMerchantType())){//企业代理
					superMember.setDirectMemberMoney(superMember.getDirectMemberMoney().add(upgradeHistory.getTradeMoney().multiply(new  BigDecimal(0.6))).setScale(2, BigDecimal.ROUND_DOWN));
					// 记录奖励记录信息
					upgradeHistory.setDirectMember(superMember.getId());
					upgradeHistory.setDirectMemberMoney(upgradeHistory.getTradeMoney().multiply(new  BigDecimal(0.6)).setScale(2, BigDecimal.ROUND_DOWN));
				}
				
				gjfMemberInfoDao.update(superMember);
			}
		}
		attrs.remove("id");
		// 判断是那个类型
		if (ObjValid.isNotValid(area) && ObjValid.isNotValid(pri) && ObjValid.isNotValid(city) && ("4".equals(type)||"4".equals(type))) {
			upgradeHistory.setProviceId(null);
			upgradeHistory.setCityId(null);
			upgradeHistory.setAreaId(null);
			memberInfo.setMerchantType(type);
		} else if (ObjValid.isValid(area) && ObjValid.isValid(pri) && ObjValid.isValid(city) && "5".equals(type)) {
			attrs.put("provinceId", pri);
			GjfAddressProvince gjfAddressProvince = gjfMemberInfoDao.query(GjfAddressProvince.class, attrs);
			if (ObjValid.isNotValid(gjfAddressProvince)) {
				throw new MyException(400, "省份不存在", null);
			}

			attrs.clear();
			attrs.put("cityId", city);
			GjfAddressCity gjfAddressCity = gjfMemberInfoDao.query(GjfAddressCity.class, attrs);
			if (ObjValid.isNotValid(gjfAddressCity)) {
				throw new MyException(400, "市不存在", null);
			}

			attrs.clear();
			attrs.put("areaId", area);
			GjfAddressArea gjfAddressArea = gjfMemberInfoDao.query(GjfAddressArea.class, attrs);
			if (ObjValid.isNotValid(gjfAddressArea)) {
				throw new MyException(400, "地区不存在", null);
			}
			upgradeHistory.setProviceId(gjfAddressProvince);
			upgradeHistory.setCityId(gjfAddressCity);
			upgradeHistory.setAreaId(gjfAddressArea);
			memberInfo.setMerchantType(type);
		} else if (ObjValid.isNotValid(area) && ObjValid.isValid(pri) && ObjValid.isValid(city) && "6".equals(type)) {
			attrs.put("provinceId", pri);
			GjfAddressProvince gjfAddressProvince = gjfMemberInfoDao.query(GjfAddressProvince.class, attrs);
			if (ObjValid.isNotValid(gjfAddressProvince)) {
				throw new MyException(400, "省份不存在", null);
			}

			attrs.clear();
			attrs.put("cityId", city);
			GjfAddressCity gjfAddressCity = gjfMemberInfoDao.query(GjfAddressCity.class, attrs);
			if (ObjValid.isNotValid(gjfAddressCity)) {
				throw new MyException(400, "市不存在", null);
			}

			upgradeHistory.setProviceId(gjfAddressProvince);
			upgradeHistory.setCityId(gjfAddressCity);
			upgradeHistory.setAreaId(null);
			memberInfo.setMerchantType(type);
		} else if (ObjValid.isNotValid(area) && ObjValid.isValid(pri) && ObjValid.isValid(city) && "7".equals(type)) {
			attrs.put("provinceId", pri);
			GjfAddressProvince gjfAddressProvince = gjfMemberInfoDao.query(GjfAddressProvince.class, attrs);
			if (ObjValid.isNotValid(gjfAddressProvince)) {
				throw new MyException(400, "省份不存在", null);
			}

			attrs.clear();
			attrs.put("cityId", city);
			GjfAddressCity gjfAddressCity = gjfMemberInfoDao.query(GjfAddressCity.class, attrs);
			if (ObjValid.isNotValid(gjfAddressCity)) {
				throw new MyException(400, "市不存在", null);
			}

			upgradeHistory.setProviceId(gjfAddressProvince);
			upgradeHistory.setCityId(gjfAddressCity);
			upgradeHistory.setAreaId(null);
			memberInfo.setMerchantType(type);
		}
		upgradeHistory.setAgentStartDate(startTime);
		upgradeHistory.setAgentEndDate(endTime);
		gjfMemberInfoDao.save(upgradeHistory);
		gjfMemberInfoDao.update(memberInfo);
		return new ResultVo(200, "设置成功", upgradeHistory);
	}

	/**
	 * 获取赠送商家列表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ResultVo findGiveMerchantModelByType(String type, Long memberId) {
		Map<String, Object> attrs = new HashMap<>();
		List<GjfMerchantUpgradeHistory> list = null;
		attrs.put("giveType", "1");
		attrs.put("giveMemberId", memberId);
		list = gjfMemberInfoDao.queryList(GjfMerchantUpgradeHistory.class, "tradeTime", "desc", attrs);
		return new ResultVo(200, "查询成功", list);
	}

	/**
	 * 添加商家赠送记录
	 * 
	 * @param account
	 * @param giveMemberId
	 * @param type
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ResultVo addMerchantGiveHistory(String account, Long giveMemberId, String type) {

		// 查询赠送用户信息
		Map<String, Object> attrs = new HashMap<>();
		attrs.put("mobile", account);
		GjfMemberInfo member = gjfMemberInfoDao.query(GjfMemberInfo.class, attrs);
		if (!BeanUtil.isValid(member)) {
			return new ResultVo(400, "赠送用户不存在");
		}
		/*if ("0".equals(member.getType())
				&& ("2".equals(member.getMerchantType()) || "3".equals(member.getMerchantType())
						|| "4".equals(member.getMerchantType()) || "5".equals(member.getMerchantType()))) {
			return new ResultVo(400, "赠送用户不是商家不能进行相应的操作");
		}*/
		GjfMerchantUpgradeHistory upgradeHistory = new GjfMerchantUpgradeHistory();
		upgradeHistory.setGiveMemberId(giveMemberId);
		upgradeHistory.setMemberId(member.getId());
		upgradeHistory.setMemberMobile(member.getMobile());
		upgradeHistory.setMemberName(member.getName());
		upgradeHistory.setGiveType("1");
		upgradeHistory.setPayType("2");
		upgradeHistory.setTradeStatus("1");

		// 查询用户信息
		attrs.remove("mobile");
		attrs.put("id", giveMemberId);
		GjfMemberInfo givemem = gjfMemberInfoDao.query(GjfMemberInfo.class, attrs);
		Map<String, Object> fhAttrs = new HashMap<>();
		fhAttrs.put("mobile", givemem.getMobile());
		GjfFhTreasureInfo fhInfo = gjfMemberInfoDao.query(GjfFhTreasureInfo.class, fhAttrs);
		//如果天天宝金额为负数
		if(BeanUtil.isValid(fhInfo)&&fhInfo.getFhTreasureMoney().doubleValue()<0){
			return new ResultVo(400, "赠送失败");
		}
		if (!BeanUtil.isValid(givemem)) {
			return new ResultVo(400, "用户不存在");
		}
		if(givemem.getMobile().equals(account)){
			return new ResultVo(400, "赠送会员不能是自己");
		}

		if ("1".equals(type)) {
			if ("1".equals(member.getMerchantType())) {
				return new ResultVo(400, "赠送用户已经是商家版会员");
			}

			// 查询已经赠送的用户数据
			if("1".equals(givemem.getMerchantType())||"2".equals(givemem.getMerchantType())||"3".equals(givemem.getMerchantType())){
				attrs.remove("id");
				attrs.put("giveType", "1");
				attrs.put("giveMemberId", giveMemberId);
				attrs.put("tradeType", "1");
				Long count = gjfMemberInfoDao.queryTotalRecord(GjfMerchantUpgradeHistory.class, attrs);
				if (count >= 100) {
					return new ResultVo(400, "赠送商家版数量已满");
				}
			}			
			upgradeHistory.setTradeType("1");
			member.setMerchantModel("0");
			member.setMerchantType("1");

		}

		if ("2".equals(type)) {
			if ("2".equals(member.getMerchantType())) {
				return new ResultVo(400, "赠送用户已经是企业版会员");
			}
			// 查询已经赠送的用户数据
			attrs.remove("id");
			attrs.put("giveType", "1");
			attrs.put("giveMemberId", giveMemberId);
			attrs.put("tradeType", "2");
			Long count = gjfMemberInfoDao.queryTotalRecord(GjfMerchantUpgradeHistory.class, attrs);
			if (count >= 3) {
				return new ResultVo(400, "赠送企业版数量已满");
			}
			upgradeHistory.setTradeType("2");
			member.setMerchantModel("0");
			member.setMerchantType("2");
		}

		if ("3".equals(type)) {
			if ("3".equals(member.getMerchantType())) {
				return new ResultVo(400, "赠送用户已经是商家代理版会员");
			}

			if ("4".equals(givemem.getMerchantType()) || "5".equals(givemem.getMerchantType())
					|| "6".equals(givemem.getMerchantType())) {
				attrs.remove("id");
				attrs.put("giveType", "1");
				attrs.put("giveMemberId", giveMemberId);
				attrs.put("tradeType", "3");
				Long count = gjfMemberInfoDao.queryTotalRecord(GjfMerchantUpgradeHistory.class, attrs);
				if (count >= 10) {
					return new ResultVo(400, "赠送商家代理版数量已满");
				}
			} else if ("7".equals(givemem.getMerchantType())) {
				attrs.remove("id");
				attrs.put("giveType", "1");
				attrs.put("giveMemberId", giveMemberId);
				attrs.put("tradeType", "3");
				Long count = gjfMemberInfoDao.queryTotalRecord(GjfMerchantUpgradeHistory.class, attrs);
				if (count >= 30) {
					return new ResultVo(400, "赠送商家代理版数量已满");
				}
			}

			upgradeHistory.setTradeType("3");
			member.setMerchantModel("0");
			member.setMerchantType("3");

		}

		if ("4".equals(type)) {
			if ("4".equals(member.getMerchantType())) {
				return new ResultVo(400, "赠送用户已经是企业代理版会员");
			}
			attrs.remove("id");
			attrs.put("giveType", "1");
			attrs.put("giveMemberId", giveMemberId);
			attrs.put("tradeType", "4");
			if ("5".equals(givemem.getMerchantType())) {
				Long count = gjfMemberInfoDao.queryTotalRecord(GjfMerchantUpgradeHistory.class, attrs);
				if (count >= 2) {
					return new ResultVo(400, "赠送企业代理版数量已满");
				}
			}

			if ("6".equals(givemem.getMerchantType())) {
				Long count = gjfMemberInfoDao.queryTotalRecord(GjfMerchantUpgradeHistory.class, attrs);
				if (count >= 5) {
					return new ResultVo(400, "赠送企业代理版数量已满");
				}
			}

			if ("7".equals(givemem.getMerchantType())) {
				Long count = gjfMemberInfoDao.queryTotalRecord(GjfMerchantUpgradeHistory.class, attrs);
				if (count >= 10) {
					return new ResultVo(400, "赠送企业代理版数量已满");
				}
			}

			upgradeHistory.setTradeType("4");
			member.setMerchantModel("0");
			member.setMerchantType("4");
		}
		upgradeHistory.setTradeNo(DateHelper.dataToString(new Date(), "yyyyMMddHHmmss" + RandUtil.getRandomStr(6)));
		upgradeHistory.setTradeTime(new Date());
		gjfMemberInfoDao.save(upgradeHistory);
		gjfMemberInfoDao.update(member);
		return new ResultVo(200, "操作成功", upgradeHistory);
	}

	/**
	 * 添加会员升级赠送记录
	 * 
	 * @param account
	 * @param tradeMoney
	 * @param merchantType
	 * @param payType
	 * @return
	 */
	@Override
	public ResultVo addMerchantRechargeToMemberHistory(String account, String mobile, double tradeMoney,
			String merchantType, String payType) {
		if (!BeanUtil.isValid(account)) {
			return new ResultVo(400, "用户不存在");
		}
		// 查询用户信
		Map<String, Object> attrs = new HashMap<>();
		attrs.put("mobile", account);
		GjfMemberInfo member = gjfMemberInfoDao.query(GjfMemberInfo.class, attrs);
		if (!BeanUtil.isValid(member)) {
			return new ResultVo(400, "用户不存在");
		}
		if (!BeanUtil.isValid(mobile)) {
			return new ResultVo(400, "赠送用户不存在");
		}
		// 查询赠送用户信息
		attrs.put("mobile", mobile);
		GjfMemberInfo giveMem = gjfMemberInfoDao.query(GjfMemberInfo.class, attrs);
		if (!BeanUtil.isValid(giveMem)) {
			return new ResultVo(400, "赠送用户不存在");
		}
		if (!BeanUtil.isValid(merchantType)) {
			return new ResultVo(400, "请选择赠送类型");
		}
		if (!BeanUtil.isValid(payType)) {
			return new ResultVo(400, "请选择支付类型");
		}
		if (!BeanUtil.isValid(tradeMoney)) {
			return new ResultVo(400, "支付金额不能为空");
		}
		if (tradeMoney <= 0) {
			return new ResultVo(400, "支付金额不能小于或等于零");
		}

		GjfMerchantUpgradeHistory upgradeHistory = new GjfMerchantUpgradeHistory();
		upgradeHistory.setMemberName(giveMem.getName());
		upgradeHistory.setMemberId(giveMem.getId());
		upgradeHistory.setGiveType("2");
		upgradeHistory.setMemberMobile(giveMem.getMobile());
		upgradeHistory.setPayType(payType);
		upgradeHistory.setTradeMoney(new BigDecimal(tradeMoney));
		upgradeHistory.setTradeTime(new Date());
		
		upgradeHistory.setTradeNo(DateHelper.dataToString(new Date(), "yyyyMMddHHmmss" + RandUtil.getRandomStr(6)));
		upgradeHistory.setTradeType(merchantType);
		upgradeHistory.setGiveMemberId(member.getId());
		if("0".equals(payType)||"1".equals(payType)){//微信支付
			upgradeHistory.setTradeStatus("0");
		}else if("3".equals(payType)){//天天宝支付
			upgradeHistory.setTradeStatus("1");
			giveMem.setMerchantType(merchantType);			
			//查询用户天天宝信息
			Map<String, Object> fhAttrs=new HashMap<>();
			fhAttrs.put("mobile", member.getMobile());
			GjfFhTreasureInfo fh=gjfMemberInfoDao.query(GjfFhTreasureInfo.class, fhAttrs);
			if(!BeanUtil.isValid(fh)){
				return new ResultVo(400,"金额不足");
			}
			if(fh.getFhTreasureMoney().doubleValue()<tradeMoney){
				return new ResultVo(400,"金额不足");
			}
			
			GjfMemberTreasureTrade trade = new GjfMemberTreasureTrade();
			trade.setMemberId(member.getId());
			trade.setMemberName(member.getName());
			trade.setMemebrMobile(member.getMobile());
			trade.setMemberTreasureMoneyBf(fh.getFhTreasureMoney());
			//扣减天天宝余额
			fh.setFhTreasureMoney(fh.getFhTreasureMoney().subtract(new BigDecimal(tradeMoney)));
									
			trade.setMemberTreasureTradeMoney(new BigDecimal(tradeMoney));
			trade.setMemberTreasureMoneyAf(fh.getFhTreasureMoney());
			trade.setTradeStatus("1");
			trade.setTradeType("9");
			trade.setAddTime(new Date());
			trade.setTradeNo(DateHelper.dataToString(new Date(), "yyyyMMddHHmmss" + RandUtil.getRandomStr(6)));
			gjfMemberInfoDao.save(trade);
			
			//给购买人奖励
			if("0".equals(member.getMerchantType())){//普通会员
				BigDecimal directMoney=upgradeHistory.getTradeMoney().multiply(new BigDecimal(0.1)).setScale(2, BigDecimal.ROUND_DOWN);
				upgradeHistory.setDirectMemberMoney(directMoney);
				member.setDirectMemberMoney(member.getDirectMemberMoney().add(directMoney));
			}else if("1".equals(member.getMerchantType())){
				BigDecimal directMoney=upgradeHistory.getTradeMoney().multiply(new BigDecimal(0.2)).setScale(2, BigDecimal.ROUND_DOWN);
				upgradeHistory.setDirectMemberMoney(directMoney);
				member.setDirectMemberMoney(member.getDirectMemberMoney().add(directMoney));
			}else if("2".equals(member.getMerchantType())){
				BigDecimal directMoney=upgradeHistory.getTradeMoney().multiply(new BigDecimal(0.3)).setScale(2, BigDecimal.ROUND_DOWN);
				upgradeHistory.setDirectMemberMoney(directMoney);
				member.setDirectMemberMoney(member.getDirectMemberMoney().add(directMoney));
			}else if("3".equals(member.getMerchantType())){
				BigDecimal directMoney=upgradeHistory.getTradeMoney().multiply(new BigDecimal(0.5)).setScale(2, BigDecimal.ROUND_DOWN);
				upgradeHistory.setDirectMemberMoney(directMoney);
				member.setDirectMemberMoney(member.getDirectMemberMoney().add(directMoney));
			}else{
				BigDecimal directMoney=upgradeHistory.getTradeMoney().multiply(new BigDecimal(0.6)).setScale(2, BigDecimal.ROUND_DOWN);
				upgradeHistory.setDirectMemberMoney(directMoney);
				member.setDirectMemberMoney(member.getDirectMemberMoney().add(directMoney));
			}
			gjfMemberInfoDao.update(member);
			gjfMemberInfoDao.update(fh);
			gjfMemberInfoDao.update(giveMem);
		}
		gjfMemberInfoDao.save(upgradeHistory);
		return new ResultVo(200, "赠送成功", upgradeHistory);
	}

	/**
	 * 会员升级赠送支付回调
	 * 
	 * @param status
	 * @param tradeNo
	 * @return
	 */
	@Override
	public ResultVo updateMerchantRechargeToMemberHistory(String status, String tradeNo) {
		if (!BeanUtil.isValid(tradeNo)) {
			return new ResultVo(400, "订单不存在");
		}
		// 查询订单信息
		Map<String, Object> attrs = new HashMap<>();
		attrs.put("tradeNo", tradeNo);
		GjfMerchantUpgradeHistory upgradeHistory = gjfMemberInfoDao.query(GjfMerchantUpgradeHistory.class, attrs);
		if ("1".equals(upgradeHistory.getTradeStatus())) {
			return new ResultVo(400, "订单交易成功");
		}
		upgradeHistory.setTradeStatus("1");
		// 获取用户信息
		attrs.remove("tradeNo");
		attrs.put("id", upgradeHistory.getMemberId());
		GjfMemberInfo member = gjfMemberInfoDao.query(GjfMemberInfo.class, attrs);		
		member.setMerchantType(upgradeHistory.getTradeType());
		//获取购买人信息
		attrs.put("id", upgradeHistory.getGiveMemberId());
		GjfMemberInfo buyMember = gjfMemberInfoDao.query(GjfMemberInfo.class, attrs);	
		//给购买人奖励
		if("0".equals(buyMember.getMerchantType())){//普通会员
			BigDecimal directMoney=upgradeHistory.getTradeMoney().multiply(new BigDecimal(0.1)).setScale(2, BigDecimal.ROUND_DOWN);
			upgradeHistory.setDirectMemberMoney(directMoney);
			buyMember.setDirectMemberMoney(buyMember.getDirectMemberMoney().add(directMoney));
		}else if("1".equals(buyMember.getMerchantType())){
			BigDecimal directMoney=upgradeHistory.getTradeMoney().multiply(new BigDecimal(0.2)).setScale(2, BigDecimal.ROUND_DOWN);
			upgradeHistory.setDirectMemberMoney(directMoney);
			buyMember.setDirectMemberMoney(buyMember.getDirectMemberMoney().add(directMoney));
		}else if("2".equals(buyMember.getMerchantType())){
			BigDecimal directMoney=upgradeHistory.getTradeMoney().multiply(new BigDecimal(0.3)).setScale(2, BigDecimal.ROUND_DOWN);
			upgradeHistory.setDirectMemberMoney(directMoney);
			buyMember.setDirectMemberMoney(buyMember.getDirectMemberMoney().add(directMoney));
		}else if("3".equals(buyMember.getMerchantType())){
			BigDecimal directMoney=upgradeHistory.getTradeMoney().multiply(new BigDecimal(0.5)).setScale(2, BigDecimal.ROUND_DOWN);
			upgradeHistory.setDirectMemberMoney(directMoney);
			buyMember.setDirectMemberMoney(buyMember.getDirectMemberMoney().add(directMoney));
		}else{
			BigDecimal directMoney=upgradeHistory.getTradeMoney().multiply(new BigDecimal(0.6)).setScale(2, BigDecimal.ROUND_DOWN);
			upgradeHistory.setDirectMemberMoney(directMoney);
			buyMember.setDirectMemberMoney(buyMember.getDirectMemberMoney().add(directMoney));
		}
		gjfMemberInfoDao.update(buyMember);
		gjfMemberInfoDao.update(upgradeHistory);
		gjfMemberInfoDao.update(member);
		return new ResultVo(200, "更新成功", upgradeHistory);
	}

	/**
	 * 获取会员升级赠送记录
	 * 
	 * @param memberId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ResultVo findMerchantRechargeToMemberHistory(Long memberId) {
		Map<String, Object> attrs = new HashMap<>();
		attrs.put("giveMemberId", memberId);
		attrs.put("giveType", "2");
		List<GjfMerchantUpgradeHistory> list = gjfMemberInfoDao.queryList(GjfMerchantUpgradeHistory.class, "tradeTime",
				"desc", attrs);
		return new ResultVo(200, "查询成功", list);
	}

	/**
	 * 获取用户代金券赠送历史记录
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ResultVo findMemberVoucherHistory(Long memberId) {
		Map<String, Object> attrs=new HashMap<>();
		attrs.put("storeId", memberId);
		attrs.put("tradeStatus", "1");
		List<GjfMemberVouchersTradeHistory> list=gjfMemberInfoDao.queryList(GjfMemberVouchersTradeHistory.class, "addTime", "desc", attrs);
		return new ResultVo(200,"查询成功",list);
	}

	/**
	 * 获取用户推荐奖励历史记录
	 * @param memberId
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public ResultVo findMemberDirectMemberMoney(Long memberId) {
		//查询结果集合
		List resultList=new ArrayList<>();
		
		//查询条件集合
		Map<String, Object> attrs=new HashMap<>();
		//查询推荐奖励信息
		attrs.put("directMember", memberId);
		attrs.put("tradeStatus", "1");
		List<GjfMerchantUpgradeHistory> upgrade=gjfMemberInfoDao.queryList(GjfMerchantUpgradeHistory.class, 0, 20, "tradeTime", "desc", attrs);
		if(BeanUtil.isValid(upgrade)){
			for(GjfMerchantUpgradeHistory up:upgrade){
				Map<String, Object> resultMap=new HashMap<>();
				resultMap.put("comMemberName",up.getMemberName());
				resultMap.put("comMemberMobile", up.getMemberMobile());
				resultMap.put("directMemberMoney", up.getDirectMemberMoney());
				resultMap.put("tradeMoney", up.getTradeMoney());
				resultMap.put("tradeType", up.getTradeType());
				resultMap.put("payType", up.getPayType());
				resultMap.put("addTime", up.getTradeTime());
				resultMap.put("agentMoney", up.getAgentMoney());
				resultMap.put("isWholesalse", "0");
				resultList.add(resultMap);
			}
			
		}
		//查询采购订单奖励
		attrs.clear();
		attrs.put("directMemberId", memberId);
		attrs.put("isWholesalse", "1");
		attrs.put("orderStatus", "1");
		List<GjfOrderInfo> orderList=gjfMemberInfoDao.queryList(GjfOrderInfo.class,0,20, "addTime", "desc", attrs);
		if(BeanUtil.isValid(orderList)){
			for(GjfOrderInfo order:orderList){
				if(order.getDirectMemberMoney()!=null&&order.getDirectMemberMoney().doubleValue()!=0){
					//查询用户信息				
					Map<String, Object> resultMap=new HashMap<>();
					resultMap.put("comMemberName",order.getMemberId().getName());
					resultMap.put("comMemberMobile", order.getMemberId().getMobile());
					resultMap.put("directMemberMoney", order.getDirectMemberMoney());
					resultMap.put("agentMoney", order.getAgentDeirectMoney());
					resultMap.put("tradeMoney", order.getGoodsTotalAmount());
					resultMap.put("tradeType","8");
					resultMap.put("isWholesalse", "1");
					resultMap.put("addTime", order.getAddTime());
					resultMap.put("payType", order.getPayType());
					resultList.add(resultMap);
				}
				
			}
		}
		return new ResultVo(200, "查询成功", resultList);
	}

	/**
	 * 把联盟商家金额转移到天天宝上
	 */
	@Override
	public ResultVo addMerchantDirectMoneyToFhTreasure(String account, double money) {
		if (StringUtil.isBlank(account)) {
			return new ResultVo(400, "用户不存在", null);
		}

		if (ObjValid.isNotValid(money)) {
			return new ResultVo(400, "输入金额有误", null);
		}
		if (money < 200) {
			return new ResultVo(400, "转入金额不能小于200元", null);
		}
		// 获取用户信息
		Map<String, Object> memMap = new HashMap<String, Object>();
		memMap.put("mobile", account);
		GjfMemberInfo mem = gjfMemberInfoDao.query(GjfMemberInfo.class, memMap);
		if (ObjValid.isNotValid(mem)) {
			return new ResultVo(400, "用户不存在", null);
		}
		// 1.查询当前用户的可提现金额，判断提现的金额是否大于可提现金额
		if (mem.getDirectMemberMoney().doubleValue() < money) {
			return new ResultVo(400, "转入金额不能大于可转入金额", null);
		}
		//计算金额
		//获取付费比例
		String taxProportion="0.2";
		Map<String, Object> baseInfo=new HashMap<>();
		baseInfo.put("key", "TAXMONEY");
		baseInfo.put("status", "1");
		GjfSetBaseInfo base=gjfMemberInfoDao.query(GjfSetBaseInfo.class, baseInfo);
		if(BeanUtil.isValid(base)){
			taxProportion=base.getValue();
		}
		//计算税费
		BigDecimal taxMoney = new BigDecimal(money).multiply(new BigDecimal(taxProportion)).setScale(2, BigDecimal.ROUND_DOWN);
		//实际到账金额
		BigDecimal tradeMoney = new BigDecimal(money).subtract(taxMoney);
		
		GjfMemberTreasureTrade trede = new GjfMemberTreasureTrade();
		trede.setMemberId(mem.getId());
		trede.setMemberName(mem.getName());
		trede.setMemebrMobile(mem.getMobile());
		trede.setRealMoney(new BigDecimal(money));
		trede.setInsuranceMoney(new BigDecimal(0.00));
		trede.setTaxMoney(taxMoney);
		trede.setTradeNo(DateHelper.dataToString(new Date(), "yyyyMMddHHmmss" + RandUtil.getRandomStr(6)));
		trede.setTradeType("10");
		trede.setTradeStatus("1");
		trede.setAddTime(new Date());
		trede.setMemberTreasureTradeMoney(tradeMoney);
		
		// 修改用户天天宝信息
		GjfFhTreasureInfo fhInfo = gjfMemberInfoDao.query(GjfFhTreasureInfo.class, memMap);
		trede.setMemberTreasureMoneyAf(fhInfo.getFhTreasureMoney().add(trede.getMemberTreasureTradeMoney()).setScale(2,
				BigDecimal.ROUND_DOWN));
		trede.setMemberTreasureMoneyBf(fhInfo.getFhTreasureMoney());
		gjfMemberInfoDao.save(trede);

		// 用户资金修改
		mem.setDirectMemberMoney(mem.getDirectMemberMoney().subtract(new BigDecimal(money)));
			
		fhInfo.setFhTreasureMoney(fhInfo.getFhTreasureMoney().add(trede.getMemberTreasureTradeMoney()).setScale(2,
				BigDecimal.ROUND_DOWN));
		gjfMemberInfoDao.update(fhInfo);
	
		gjfMemberInfoDao.update(mem);
		return new ResultVo(200, "转入成功", trede);
	}

	/**
	 * 后台获取代金券交易记录
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ResultVo findMemberVouchers(String memberName, String memberMobile, Integer pageNo, Integer pageSize) {
		Map<String, Object> attrs = new HashMap<>();
		if (BeanUtil.isValid(memberName)) {
			attrs.put("memberName", memberName);
		}
		if (BeanUtil.isValid(memberMobile)) {
			attrs.put("memberMobile", memberMobile);
		}	
		List<GjfMemberVouchersTradeHistory> list = gjfMemberInfoDao.queryList(GjfMemberVouchersTradeHistory.class,
				(pageNo - 1) * pageSize, pageSize, "addTime", "desc", attrs);
		Pager pager = new Pager(pageSize, pageNo,
				(int) gjfMemberInfoDao.queryTotalRecord(GjfMemberVouchersTradeHistory.class, attrs), list);
		return new ResultVo(200, "查询成功", pager);
	}

	/**
	 * 获取联盟商家升级记录
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ResultVo findMemberUpgradeHistory(Integer pageNo, Integer pageSize, String type,String account,String memberName) {
		Map<String, Object> attrs=new HashMap<>();
		if(BeanUtil.isValid(type)){
			attrs.put("tradeType", type);
		}
		if(BeanUtil.isValid(account)){
			attrs.put("memberMobile", account);
		}
		if(BeanUtil.isValid(memberName)){
			attrs.put("memberName", memberName);
		}
		List<GjfMerchantUpgradeHistory> upgradeHistory=gjfMemberInfoDao.queryList(GjfMerchantUpgradeHistory.class, (pageNo-1)*pageSize, pageSize, "tradeTime", "desc", attrs);
		List resultList=new ArrayList<>();
		for(GjfMerchantUpgradeHistory upgrade:upgradeHistory){
			Map<String, Object> map=new HashMap<>();
			map.put("id", upgrade.getId());
			map.put("memberId", upgrade.getMemberId());
			map.put("memberName", upgrade.getMemberName());
			map.put("memberMobile", upgrade.getMemberMobile());
			map.put("tradeMoney", upgrade.getTradeMoney());
			map.put("payType", upgrade.getPayType());
			map.put("tradeType", upgrade.getTradeType());
			map.put("tradeStatus", upgrade.getTradeStatus());
			map.put("tradeNo", upgrade.getTradeNo());
			map.put("giveType", upgrade.getGiveType());
			map.put("directMemberMoney", upgrade.getDirectMemberMoney());
			map.put("addTime", upgrade.getTradeTime());
			map.put("directMemberName", "");
			map.put("directMemberMobile", "");
			if(BeanUtil.isValid(upgrade.getDirectMember())){
				Map<String, Object> memAttrs=new HashMap<>();
				memAttrs.put("id", upgrade.getDirectMember());
				memAttrs.put("status", "1");
				GjfMemberInfo memberInfo=gjfMemberInfoDao.query(GjfMemberInfo.class, memAttrs);
				if(BeanUtil.isValid(memberInfo)){
					map.put("directMemberName", memberInfo.getName());
					map.put("directMemberMobile", memberInfo.getMobile());
				}
			}
			resultList.add(map);
		}
		Pager pager=new Pager(pageSize, pageNo,(int)gjfMemberInfoDao.queryTotalRecord(GjfMerchantUpgradeHistory.class, attrs) , resultList);
		return new ResultVo(200, "查询成功", pager);
	}

	/**
	 * 获取用户升级vip推荐奖励
	 * @param pageNo
	 * @param pageSize
	 * @param account
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ResultVo findMemberUpgradeVipDirectHistory(Integer pageNo, Integer pageSize, String account) {
		if(!BeanUtil.isValid(account)){
			return new ResultVo(400,"用户不存在");
		}
		//查询用户信息
		Map<String, Object> attrs=new HashMap<>();
		attrs.put("mobile", account);
		GjfMemberInfo memberInfo=gjfMemberInfoDao.query(GjfMemberInfo.class, attrs);
		if(!BeanUtil.isValid(memberInfo)){
			return new ResultVo(400,"用户不存在");		
		}
		//查询交易记录
	    attrs.clear();
	    attrs.put("directMemberId", memberInfo.getId());
	    List<GjfMemberUpgradeVipDirectMoney> list=gjfMemberInfoDao.queryList(GjfMemberUpgradeVipDirectMoney.class, (pageNo-1)*pageSize, pageSize, "addTime", "desc", attrs);
	   
		return new ResultVo(200,"查询成功",list);
	}

	/**
	 * 后台充值积分
	 * @param useName
	 * @param integral
	 * @return
	 */
	@Override
	public ResultVo addRechargeIntegralInBack(String useName, String account,double rechargeMoney) {
		//判断后台登录用户是否存在
		if(!BeanUtil.isValid(useName)){
			return new ResultVo(400,"后台登录用户不存在");
		}
		//判断用户是否存在
		if(!BeanUtil.isValid(account)){
			return new ResultVo(400,"充值用户不存在");
		}
		//判断金额是否为零或小于零
		if(rechargeMoney<=0){
			return new ResultVo(400,"充值金额不能小于或等于零");
		}
		//创建查询用户map
		Map<String, Object> attrs=new HashMap<>();
		//用户电话号码
		attrs.put("mobile", account);
		//用户状态
		attrs.put("status", "1");
		//获取用户信息
		GjfMemberInfo memberInfo=gjfMemberInfoDao.query(GjfMemberInfo.class, attrs);
		//如果用户为空返回提示信息
		if(!BeanUtil.isValid(memberInfo)){
			return new ResultVo(400,"充值用户不存在");
		}
		//创建记录充值记录信息对象
		GjfTransferIntegral integral=new GjfTransferIntegral();
		//操作用户名
		integral.setMemberName(useName);
		//充值用户id
		integral.setTransferMemberId(memberInfo.getId());
		//充值用户姓名
		integral.setTransferMemberMobile(memberInfo.getMobile());
		//充值用户电话
		integral.setTransferMemberName(memberInfo.getName());
		//用户充值前的积分
		integral.setTransferMemberDataBf(memberInfo.getConsumptionMoney());
		//充值时间
		integral.setAddTime(new Date());
		//充值状态
		integral.setState("1");
		//充值积分
		integral.setTransferData(new BigDecimal(rechargeMoney));
		integral.setActualTransferMoney(new BigDecimal(rechargeMoney));
		//手续费
		integral.setTransferPoundage(new BigDecimal(0));
		//充值类型
		integral.setTransferType("0");
		//充值后的积分
		integral.setTransferMemberDataAf(memberInfo.getConsumptionMoney().add(new BigDecimal(rechargeMoney)));
		//给用户添加积分
		memberInfo.setConsumptionMoney(memberInfo.getConsumptionMoney().add(new BigDecimal(rechargeMoney)));
		//保存记录
		gjfMemberInfoDao.save(integral);
		//更新用户信息
		gjfMemberInfoDao.update(memberInfo);
		
		return new ResultVo(200, "充值成功");
	}

	/**
	 * 后台获取积分充值记录
	 * @param pageNo
	 * @param pageSize
	 * @param mobile
	 * @param memName
	 * @param type
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ResultVo findAllRechargeIntergral(Integer pageNo, Integer pageSize, String mobile, String memName,
			String type) {
		//创建查询条件map
		Map<String, Object> attrs=new HashMap<>();
		//交易类型
		attrs.put("transferType", type);
		//如果用户名不为空
		if(BeanUtil.isValid(memName)){
			//用户名
			attrs.put("transferMemberName", memName);
		}
		//如果用户电话号码不为空
		if(BeanUtil.isValid(mobile)){
			//电话号码
			attrs.put("transferMemberMobile", mobile);
		}
		//获取数据
		List<GjfTransferIntegral> inList=gjfMemberInfoDao.queryList(GjfTransferIntegral.class, (pageNo-1)*pageSize, pageSize, "addTime", "desc", attrs);
		//统计总记录数
		Long count=gjfMemberInfoDao.queryTotalRecord(GjfTransferIntegral.class, attrs);
		//创建分页对象
		Pager page=new Pager(pageSize, pageNo, count.intValue(), inList);
				
		return new ResultVo(200,"查询成功",page);
	}
	
}
