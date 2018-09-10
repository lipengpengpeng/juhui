package cc.messcat.gjfeng.dubbo.core;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

import cc.messcat.gjfeng.common.vo.app.ResultVo;
import cc.messcat.gjfeng.entity.GjfRechargePaid;
import cc.messcat.gjfeng.entity.GjfSetDividends;
import cc.messcat.gjfeng.entity.GjfWeiXinPayInfo;

public interface GjfTradeDubbo {

	/**
	 * @描述 跳转到提现
	 * @author Karhs
	 * @date 2017年1月4日
	 * @param account
	 * @return
	 */
	public ResultVo toDrawCash(String account);
	
	/**
	 * @描述 添加提现
	 * @author Karhs
	 * @date 2017年1月4日
	 * @param account
	 * @param remark
	 * @param myBankId
	 * @param money
	 * @return
	 */
	public ResultVo addDrawCash(String account,String remark,Long myBankId,Double money);
	
	/**
	 * @描述 审核提现记录
	 * @author Karhs
	 * @date 2017年1月5日
	 * @param id
	 * @param token
	 * @param tradeStatus
	 * @return
	 */
	public ResultVo updateDrawCashStatus(Long id,String token,String tradeStatus,String userName);
	
	/**
	 * 退回审核状态
	 * @param id
	 * @param token
	 * @param userName
	 * @return
	 */
	public ResultVo preDrawCashStatus(Long id,String token,String userName);
	
	/**
	 * @描述 查询提现记录信息
	 * @author Karhs
	 * @date 2017年1月5日
	 * @param id
	 * @param token
	 * @return
	 */
	public ResultVo findDrawCashHistory(Long id,String token);
	
	/**
	 * 根据用户Id查询提现记录
	 * @param id
	 * @return
	 */
	public ResultVo findDrawCashHistoryById(Long id);
	
	/**
	 * @描述 查询我的提现历史记录
	 * @author Karhs
	 * @date 2017年1月4日
	 * @param pageNo
	 * @param pageSize
	 * @param account
	 * @return
	 */
	public ResultVo findDrawCashHistory(int pageNo,int pageSize,String account);
	
	/**
	 * @描述 查询提现流水详情
	 * @author Karhs
	 * @date 2017年3月8日
	 * @param id
	 * @param account
	 * @return
	 */
	public ResultVo findDrawCashHistoryDetail(String tradeNo, String account);
	
	/**
	 * @描述 绑定银行卡
	 * @author Karhs
	 * @date 2017年1月4日
	 * @param account
	 * @param bankId
	 * @param bankSub
	 * @param bankCard
	 * @param holder
	 * @return
	 */
	public ResultVo bindMyBankCard(String account,Long bankId,String bankSub,String bankCard,String holder, String cityValue);
	
	/**
	 * @描述 删除我的银行卡
	 * @author Karhs
	 * @date 2017年2月23日
	 * @param account
	 * @param bankId
	 * @return
	 */
	public ResultVo delMyBank(String account, Long bankId);
	
	/**
	 * @描述 查找我的银行卡
	 * @author Karhs
	 * @date 2017年1月4日
	 * @param account
	 * @return
	 */
	public ResultVo findMyBankCard(String account);
	
	/**
	 * @描述 查询所有的银行
	 * @author Karhs
	 * @date 2017年1月10日
	 * @return
	 */
	public ResultVo findAllBank();
	
	/**
	 * @描述 购买分红权
	 * @author Karhs
	 * @date 2017年1月5日
	 * @param account
	 * @param diviNum
	 * @param payType
	 * @param reqStatus 0:查询当前用户购买分红权所需要多少金额 1:提交购买
	 * @return
	 */
	public ResultVo addDivi(String account,Double diviNum,String payType,String reqStatus);
	
	/**
	 * @描述 修改支付状态
	 * @author Karhs
	 * @date 2017年1月5日
	 * @param diviNo
	 * @param payStatus
	 * @return
	 */
	public ResultVo updateDiviPayStatus(String diviNo,String payStatus);
	
	/**
	 * @描述 根据流水号查询分红权信息
	 * @author Karhs
	 * @date 2017年1月5日
	 * @param diviNo
	 * @return
	 */
	public ResultVo findDivi(String diviNo);
	
	/**
	 * @描述 根据流水号和token查询分红权信息
	 * @author Karhs
	 * @date 2017年1月5日
	 * @param diviNo
	 * @param token
	 * @return
	 */
	public ResultVo findDivi(String diviNo,String token);
	
	/**
	 * @描述 查询我的分红权购买历史记录
	 * @author Karhs
	 * @date 2017年1月4日
	 * @param pageNo
	 * @param pageSize
	 * @param account
	 * @return
	 */
	public ResultVo findTradeDivi(int pageNo,int pageSize,String account,String status);
	
	/**
	 * 查找所有分红权购买记录
	 * @return
	 */
	public ResultVo findTradeDiviByPage( int pageNo,int pageSize,String name, String payStatus, String diviStatus,String startDate,String endDate);
	
	/**
	 * 统计分红权购买记录
	 * @return
	 */
	public ResultVo findCountTradeDivi(String name, String payStatus, String diviStatus);
	
	/**
	 * @描述 查询我的分红历史记录
	 * @author Karhs
	 * @date 2017年1月4日
	 * @param pageNo
	 * @param pageSize
	 * @param account
	 * @return
	 */
	public ResultVo findTradeDiviHistory(int pageNo,int pageSize,String account,String tradeStatus);
	
	/**
	 * 查询分红记录
	 * @return
	 */
	public ResultVo findMemberTradeDiviHistoryByPage(int pageNo,int pageSize,String name,String addTime,String endTime,String tradeNo,String tradeType,String tradeStatus);
	
	/**
	 * 当前条件统计分红记录
	 * @return
	 */
	public ResultVo findCountTradeDiviHistory(String name,String addTime,String endTime,String tradeNo,String tradeType,String tradeStatus);
	
	
	/**
	 * 查询所有发放成功分红记录
	 * @return
	 */
	public ResultVo findALLTradeDiviHistoryByPage(int pageNo,int pageSize);
	
	/**
	 * 查询全部的提现记录
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public ResultVo findAllDrawCashHistory(int pageNo,int pageSize);

	
	/**
	 * @描述 添加商家让利
	 * @author Karhs
	 * @date 2017年1月11日
	 * @param storeId
	 * @param mobile
	 * @param amount
	 * @return
	 */
	public ResultVo addStoreBenefit(String account,Long storeId,String mobile,Double amount,String payType,String merchantGrade);
	
	/**
	 * @描述  修改商家让利支付状态
	 * @author Karhs
	 * @date 2017年1月11日
	 * @param tradeNo
	 * @param payStatus
	 * @return
	 */
	public ResultVo updateStoreBenefitPayStatus(String tradeNo,String payTradeNo, String payStatus);
	
	/**
	 * @描述 查找前端商家查询自己的让利流水记录
	 * @author Karhs
	 * @date 2017年1月11日
	 * @param pageNo
	 * @param pageSize
	 * @param storeId
	 * @return
	 */
	public ResultVo findStoreBenefit(int pageNo,int pageSize,Long storeId);
	
	/**
	 * @描述 查询用户的商家给他的让利记录
	 * @author Karhs
	 * @date 2017年3月1日
	 * @param pageNo
	 * @param pageSize
	 * @param account
	 * @return
	 */
	public ResultVo findMemberBenefit(int pageNo,int pageSize,String account);
	
	/**
	 * @描述 查询后台查询所有的商家让利记录
	 * @author Karhs
	 * @date 2017年1月11日
	 * @param pageNo
	 * @param pageSize
	 * @param storeId
	 * @return
	 */
	public ResultVo findStoreBenefitPager(int pageNo,int pageSize,Long storeId);

	
	/**
	 * 后台获取提现记录
	 * @param pageNo
	 * @param pageSize
	 * @param memberId
	 * @return
	 */
	public ResultVo findAllTradeInBack(Integer pageNo,Integer pageSize,Long memberId,String holder,String bankCard,
			String mobile,String addTime,String endTime,String tradeStatus,String ste,Long id)throws Exception;
	
	/**
	 * 后台统计当前条件下的提现记录
	 * @param pageNo
	 * @param pageSize
	 * @param memberId
	 * @return
	 */
	public ResultVo findTradeAmountInBack(String holder,String bankCard,
			String mobile,String addTime,String endTime,String tradeStatus,Long id) throws Exception;

	/**
	 *  查询商家让利记录
	 * @param pageNo
	 * @param pageSize
	 * @param gjfMemberTradeBenefit
	 * @param nameType 搜索名称类型
	 * @return
	 */
	public ResultVo findBenefitByPage(int pageNo,int pageSize,String name,String storeName,String payType,String tradeStatus,String addTime,String endTime,String ste,String directMemberName,String directMerchantsName) throws Exception;
	
	/**
	 * 统计当前条件的商家让利汇总
	 * @param name
	 * @param storeName
	 * @param addTime
	 * @param directMemberName
	 * @param directMerchantsName
	 * @return
	 */
	public ResultVo findBenefitAmountInBack(String name,String storeName,String addTime,String endTime,String directMemberName,String directMerchantsName,String tradeStatus,String payType);
	
	/**
	 * 根据时间段查询商家让利记录
	 * @return
	 */
	public ResultVo findBenefitByTime(int pageNo,int pageSize,Long id,String startTime,String endTime);
	
	/**
	 * 获取用户银行卡号
	 * @param bankId
	 * @param mobile
	 * @return
	 */
	public ResultVo findMemberBankById(Long bankId,String mobile);

	/**
	 * 查询支付明细 分页
	 * @param pageNo
	 * @param pageSize
	 * @param ste
	 * @return
	 */
	public ResultVo findTradeLogByPage(int pageNo,int pageSize,String ste,String name,
			String storeName,String addTime,String endTime,String tradeNo,String payTradeNo,String tradeType,String tradeStatus)throws ParseException;
	
	/**
	 * 统计当前条件支付明细 
	 * @param pageNo
	 * @param pageSize
	 * @param ste
	 * @return
	 */
	public ResultVo findCountTradeLog(String name,String storeName,String addTime,String endTime,String tradeNo,String payTradeNo,String tradeType,String tradeStatus);
	
	/**
	 * 充值授授信额度
	 * @param account
	 * @param tradeMoney
	 * @return
	 */
	public ResultVo addShouXin(String type,String account,Double tradeMoney,String shouType,String fileImage);
	
	/**
	 * @描述 修改充值授信额度的支付状态
	 * @author Karhs
	 * @date 2017年2月18日
	 * @param tradeNo
	 * @param payTradeNo
	 * @param payStatus
	 * @return
	 */
	public ResultVo updateShouXinPayStatus(String tradeNo,String payTradeNo,String payStatus);
	
	/**
	 * 获取所有授信记录
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public ResultVo getAllShouXin(Integer pageNo,Integer pageSize,String account);
	
	/**
	 * @描述 查询用户代理信息
	 * @author Karhs
	 * @date 2017年2月20日
	 * @param account
	 * @return
	 */
	public ResultVo findAgentInfo(String account);
	
	/**
	 * @描述 查询用户代理分红历史记录
	 * @author Karhs
	 * @date 2017年2月20日
	 * @param pageNo
	 * @param pageSize
	 * @param account
	 * @return
	 */
	public ResultVo findAgentHistory(Integer pageNo,Integer pageSize,String account);
	
	/**
	 * @描述 查询我的下级代理
	 * @author Karhs
	 * @date 2017年2月27日
	 * @param account
	 * @param agentType
	 * @return
	 */
	public ResultVo findNextAgent(String account,Integer pageNo, Integer pageSize);
	
	/**
	 * 获取提现详细信息
	 * @param id
	 * @param token
	 * @return
	 */
	public ResultVo findMemberTradeDetail(Long id,String token);
	
	/**
	 * 我的钱包--销售福利
	 * @param account
	 * @return
	 */
	public ResultVo findMemberSalesWelfare(String account,String tradeType);
	
	/**
	 * 我的钱包---福利权益
	 * @param account
	 * @return
	 */
	public ResultVo findMemberParticipate(String account,String type);
	
	/**
	 * 我的钱包---累积消费
	 * @param account
	 * @return
	 */
	public ResultVo findMemberInterests(String account,String type); 
	
	/**
	 * 根据Id查询让利明细
	 * @return
	 */
	public ResultVo findMemberTradeBenefitById(Long id,String token);
	
	/**
	 * 查询用户O2O订单历史
	 * @param id
	 * @param token
	 * @return
	 */
	public ResultVo findMemberO2OHistory(int pageNo, int pageSize,Long id, String token);
	
	/**
	 * 查询用户历史提现总额
	 * @param id
	 * @param token
	 * @return
	 */
	public ResultVo findMemberWithdrawHistoryMoney(Long id,String token);
	
	/**
	 * 查询用户流水记录
	 * @param pageNo
	 * @param pageSize
	 * @param id
	 * @param token
	 * @return
	 */
	public ResultVo findBalanceMoneyHistoryByMemberId(int pageNo,int pageSize,Long id,String token,String tradeType);
	
	/**
	 * 查询用户分红历史
	 * @param pageNo
	 * @param pageSize
	 * @param id
	 * @return
	 */
	public ResultVo findDiviHistoryByMemberId(int pageNo,int pageSize,Long id);
	
	/**
	 * 查询代理未结算分红
	 * @param pageNo
	 * @param pageSize
	 * @param id
	 * @return
	 */
	public ResultVo findAgentDiviByMemberId(int pageNo,int pageSize,Long id,String identity);
	
	/**
	 * 获取微信信息
	 * @param type
	 * @return
	 */
	public ResultVo findWeiXinBaseInfo(String type);
	
	/**
	 * 获取全部微信配置信息
	 * @return
	 */
	public ResultVo findAllWeiXinInfo(Integer pageNo,Integer pageSize);
	
	/**
	 * 添加微信配置信息
	 * @param weiXinInfo
	 * @return
	 */
	public ResultVo addWeiXinInfo(GjfWeiXinPayInfo weiXinInfo);
	
	/**
	 * 更新微信配置信息
	 * @param weiXinInfo
	 * @return
	 */
	public ResultVo updateWeiXinInfo(GjfWeiXinPayInfo weiXinInfo);
	
	/**
	 * 启用微信信息
	 * @param id
	 * @return
	 */
	public ResultVo updateWeiInfos(Long id);
	
	/**
	 * 根据id获取微信信息
	 * @param id
	 * @return
	 */
	public ResultVo findWeiXinInfoById(Long id);
	
	/**
	 * 获取全部分红权设置信息
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public ResultVo findAllDividends(Integer pageNo, Integer pageSize);
	
	/**
	 * 根据id获取分红权设置信息
	 * @param divId
	 * @return
	 */
	public ResultVo findDividendsById(Long divId);
	
	/**
	 * 添加分红权设置信息
	 * @param setDiv
	 * @return
	 */
	public ResultVo addDividensData(GjfSetDividends setDiv);
	
	/**
	 *  删除分红权设置信息
	 * @param divId
	 * @return
	 */
	public ResultVo removeDividensData(Long divId);
	
	/**
	 *  编辑分红权设置信息
	 * @param divId
	 * @return
	 */
	public ResultVo modifyDividensData(GjfSetDividends setDiv);
	
	/**
	 * 查询用户最近七天之内的让利数据
	 * @param mobile
	 * @return
	 */
	public ResultVo findBenefitByTime(String mobile);
	
	/**
	 * 用户确认让利提示信息
	 * @param tradeNo
	 * @return
	 */
	public ResultVo modifyBenefitConfirmStatus(String tradeNo);
	
	/**
	 * 根据订单号获取提现信息
	 * @param orderSn
	 * @return
	 */
	public ResultVo findMemberTradeByOrderSn(String orderSn);
	
	/**
	 * 查询代付记录
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public ResultVo findRechargePaid(int pageNo,int pageSize);
	
	/**
	 * 添加代付记录
	 * @param rechargePaid
	 * @return
	 */
	public ResultVo addRechargeRecord(GjfRechargePaid rechargePaid);
	
	/**
	 * 分红转移
	 * @param account
	 * @return
	 */
	public ResultVo modifyTransferDividendsMoney(String account);
	
	/**
	 * 转移积分
	 * @param type
	 * @param memberMobile
	 * @param transferMemberMobile
	 * @param transferMoney
	 * @return
	 */
	public ResultVo updateMemberPointTransfer(String type, String memberMobile, String transferMemberMobile,
			BigDecimal transferMoney);
	
	/**
	 * 后台获取积分转移记录
	 * @param pageNo
	 * @param pageSize
	 * @param memberName
	 * @param transferMemberName
	 * @param memberMobile
	 * @param transferMemberMobile
	 * @return
	 */
	public ResultVo findMemberPointByPager(Integer pageNo,Integer pageSize,String memberName,String transferMemberName,String memberMobile,String transferMemberMobile,String type);
	
	/**
	 * 获取转移历史记录
	 * @param pageNo
	 * @param pageSize
	 * @param account
	 * @param type 0 积分转移 1合并用户信息
	 * @return
	 */
	public ResultVo findTransferHistoryByPager(Integer pageNo,Integer pageSize,String account,String type);
	
	/**
	 * 后台获取用户合并详情信息
	 * @param id
	 * @return
	 */
	public ResultVo findMemberMergeDetailById(Long id);
	
	/**
	 * 获取特殊发放人统计记录
	 * @return
	 */
	public ResultVo findSpecialTotalHistory(Integer pageNo,Integer pageSize);
	
	/**
	 * 查看特殊发放人交易记录
	 * @param pageNo
	 * @param pageSize
	 * @param addTime
	 * @param memId
	 * @return
	 */
	public ResultVo findSpecialMemTradeDiviHistory(Integer pageNo,Integer pageSize,String addTime,Long memId,String type);
	
	
	/**
	 * 获取授信交易记录
	 * @param pageNo
	 * @param pageSize
	 * @param shouxinType
	 * @return
	 */
	public ResultVo findShouxinHistory(Integer pageNo,Integer pageSize,String shouxinType);
	
	/**
	 *获取授信充值详细信息
	 * @param id
	 * @return
	 */
	public ResultVo findShouxinById(Long id);
	
	/**
	 * 审核线下充值记录
	 * @param id
	 * @return
	 */
	public ResultVo updateShouxinStatus(Long id,String acduitStatus);
	
	/**
	 * 查询用户凤凰宝信息
	 * @param account
	 * @return
	 */
	public ResultVo findFhTreasureInfo(String account);
	
	/**
	 * 把余额转到凤凰宝	 
	 * @param account
	 * @param money
	 * @return
	 */
	public ResultVo addBalanceToFhTreasure(String account, Double money);
	
	/**
	 * 添加用户凤凰宝转移记录
	 * @param account
	 * @param TransferMobile
	 * @param tradeMoney
	 * @return
	 */
	public ResultVo addTransferFhTreasureHistory(String account, String TransferMobile, double tradeMoney);
	
	/**
	 * 凤凰宝提现
	 * @param account
	 * @param remark
	 * @param myBankId
	 * @param money
	 * @return
	 */
	public ResultVo addFhTreasureDrawCash(String account, String remark, Long myBankId, Double money);
	
	/**
	 * 凤凰宝交易记录
	 * @param pageNo
	 * @param pageSize
	 * @param memberId
	 * @return
	 */
	public ResultVo findFhTreaureTradeHistory(Integer pageNo, Integer pageSize, String account);
	
	/**
	 * 后台获取凤凰宝交易记录
	 * @param pageNo
	 * @param pageSize
	 * @param tradeType
	 * @param memName
	 * @param memMobile
	 * @return
	 */
	public ResultVo findFhTreasureTradeHistoryByCondition(Integer pageNo, Integer pageSize, String tradeType,String memName,String memMobile);
	
	
	/**
	 * 添加商家充值商家联盟记录
	 * @param account
	 * @param tradeMoney
	 * @param merchantType
	 * @param payType
	 * @return
	 */
	public ResultVo addMerchantRechargeHistory(String account,double tradeMoney,String merchantType,String payType);
	
	/**
	 * 商家充值支付回调
	 * @param status
	 * @param tradeNo
	 * @return
	 */
	public ResultVo updateRechargeHistoryStatus(String status, String tradeNo);
	
	
	/**
	 * 添加商家代金券交易记录
	 * @param account
	 * @param storeId
	 * @param mobile
	 * @param amount
	 * @param payType
	 * @return
	 */
	public ResultVo addMemberVouchersHistory(String account, String mobile, Double amount,String payType);
	
	/**
	 * 商家赠送代金券支付回调
	 * @param status
	 * @param tradeNo
	 * @return
	 */
	public ResultVo updateVoucherHistoryStatus(String status, String tradeNo);
	
	/***
	 * 后台设置商家联盟
	 * @param type
	 * @param account
	 * @param tradeMoney
	 * @return
	 */
	public ResultVo addMerchantModelInBack(String type, String account, double tradeMoney,Long area,Long pri,Long city,Date startTime,Date endTime);
	
	/**
	 * 获取赠送商家列表
	 */
	public ResultVo findGiveMerchantModelByType(String type, Long memberId);
	
	/**
	 * 添加商家赠送记录
	 * @param account
	 * @param giveMemberId
	 * @param type
	 * @return
	 */
	public ResultVo addMerchantGiveHistory(String account, Long giveMemberId, String type);
	
	
	/**
	 * 添加会员升级赠送记录
	 * @param account
	 * @param tradeMoney
	 * @param merchantType
	 * @param payType
	 * @return
	 */
	public ResultVo addMerchantRechargeToMemberHistory(String account, String mobile, double tradeMoney,
			String merchantType, String payType);
	
	/**
	 * 会员升级赠送支付回调
	 * @param status
	 * @param tradeNo
	 * @return
	 */
	public ResultVo updateMerchantRechargeToMemberHistory(String status,String tradeNo);
	
	/**
	 * 获取会员升级赠送记录
	 * @param memberId
	 * @return
	 */
	public ResultVo findMerchantRechargeToMemberHistory(Long memberId);
	
	/**
	 * 获取用户赠送代金券记录
	 * @param memberId
	 * @return
	 */
	public ResultVo findMemberVoucherHistory(Long memberId);
	
	/**
	 * 获取用户推荐奖励历史记录
	 * @param memberId
	 * @return
	 */
	public ResultVo findMemberDirectMemberMoney(Long memberId);
	
	/**
	 * 联盟商家奖励转移到凤凰宝
	 * @param account
	 * @param money
	 * @return
	 */
	public ResultVo addMerchantDirectMoneyToFhTreasure(String account, double money);
	
	/**
	 * 后台获取代金券交易记录
	 * @param memberName
	 * @param memberMobile
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public ResultVo findMemberVouchers(String memberName, String memberMobile, Integer pageNo, Integer pageSize);
	
	/**
	 * 获取联盟商家升级记录
	 * @param pageNo
	 * @param pageSize
	 * @param type
	 * @return
	 */
	public ResultVo findMemberUpgradeHistory(Integer pageNo, Integer pageSize, String type,String account,String memberName);
	
	/**
	 * 获取用户升级vip推荐奖励
	 * @param pageNo
	 * @param pageSize
	 * @param account
	 * @return
	 */
	public ResultVo findMemberUpgradeVipDirectHistory(Integer pageNo, Integer pageSize, String account);
	
	/**
	 * 后台充值积分
	 * @param useName
	 * @param integral
	 * @return
	 */
	public ResultVo addRechargeIntegralInBack(String useName,String account,double rechargeMoney);
	
	/**
	 * 后台获取积分充值记录
	 * @param pageNo
	 * @param pageSize
	 * @param mobile
	 * @param memName
	 * @param type
	 * @return
	 */
	public ResultVo findAllRechargeIntergral(Integer pageNo,Integer pageSize,String mobile,String memName,String type);
	

}
