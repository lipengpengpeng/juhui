package cc.messcat.gjfeng.dao.trade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;

import cc.messcat.gjfeng.common.bean.Pager;
import cc.messcat.gjfeng.common.vo.app.MemberTradeDiviHistoryVo;
import cc.messcat.gjfeng.common.vo.app.MemberTradeVo;
import cc.messcat.gjfeng.common.vo.app.ResultVo;
import cc.messcat.gjfeng.dao.BaseHibernateDao;
import cc.messcat.gjfeng.entity.GjfMemberTradeDiviHistory;
import cc.messcat.gjfeng.entity.GjfOrderInfo;

public interface GjfTradeDao extends BaseHibernateDao<Serializable>{
	
	/**
	 * 获取提现记录
	 * @param pageNo
	 * @param pageSize
	 * @param memberId
	 * @return
	 */
	 public Pager getGjfTrade(Integer pageNo,Integer pageSize,Long memberId,String holder,String bankCard,
				String mobile,String addTime,String endTime,String tradeStatus,String ste,Long id)throws Exception;
	 
	 /**
	 * @描述 查询我的提现历史记录
	 * @author Karhs
	 * @date 2017年1月4日
	 * @param pageNo
	 * @param pageSize
	 * @param account
	 * @return
	 */
	public List<MemberTradeVo> findDrawCashHistory(int pageNo, int pageSize, String account);
	
	/**
	 * @描述  查询提现流水详情
	 * @author Karhs
	 * @date 2017年3月8日
	 * @param tradeNo
	 * @param account
	 * @param clientType 0:后台管理  1:微信端
	 * @return
	 */
	public MemberTradeVo findDrawCashHistoryDetail(Long id,String tradeNo, String account,String clientType);
	 
	 /**
	 * @描述 根据用户身份证获取当天是否已经有提现了
	 * @author Karhs
	 * @date 2017年3月7日
	 * @param idCard
	 * @return
	 */
	public int findDrawByIdCard(String idCard);
	
	/**
	 * @描述 查询是否有处于提现中的记录
	 * @author Karhs
	 * @date 2017年3月9日
	 * @param memberId
	 * @return
	 */
	public int findDrawByStatus(Long memberId);
	 
	 /**
	 * @描述 查询我的分红历史记录
	 * @author Karhs
	 * @date 2017年1月4日
	 * @param pageNo
	 * @param pageSize
	 * @param account
	 * @return
	 */
	public List<MemberTradeDiviHistoryVo> findTradeDiviHistory(int pageNo, int pageSize, String account, String tradeStatus);

	 /**
	  * 根据时间段查询商家让利记录
	  * @param pageNo
	  * @param pageSize
	  * @param startTime
	  * @param endTime
	  * @return
	  */
	public Pager findBenefitByTime(int pageNo, int pageSize,Long id, String startTime, String endTime);
	
	/**
	 * @描述 查询当前用户昨天的收益
	 * @author Karhs
	 * @date 2017年2月23日
	 * @param memberId
	 * @return
	 */
	public BigDecimal findBenefitYesterdayByMember(Long memberId,String tradeType);
	
	/**
	 * @描述 查询用户代理信息
	 * @author Karhs
	 * @date 2017年2月21日
	 * @param memberId
	 * @param addressId
	 * @param agentType
	 * @param findType 0:c查询未统计让利金额的， 1：查询所有的让利金额
	 * @return
	 */
	public Double findAgentInfo(Long memberId,Long addressId,String agentType,String findType);
	
	/**
	 * @描述 查询用户代理分红历史记录
	 * @author Karhs
	 * @date 2017年2月20日
	 * @param pageNo
	 * @param pageSize
	 * @param account
	 * @return
	 */
	public ResultVo findAgentHistory(Integer pageNo,Integer pageSize,String account,String agentType);
	
	/**
	 * @描述 查询我的下级代理
	 * @author Karhs
	 * @date 2017年2月27日
	 * @param account
	 * @param agentType
	 * @return
	 */
	public Object findNextAgent(String account,String agentType,Integer pageNo, Integer pageSize,String reqType);
	
	/**
	 * 获取订单信息
	 * @param account
	 * @return
	 */
	public List<GjfOrderInfo> findMemberInterests(String account,String type);
	
	@SuppressWarnings("rawtypes")
	public List findTradeAmountInBack(String holder,String bankCard,
			String mobile,String addTime,String endTime,String tradeStatus,Long id)throws Exception;
	
	/**
	 * 统计当前条件的商家让利汇总
	 * @param name
	 * @param storeName
	 * @param addTime
	 * @param directMemberName
	 * @param directMerchantsName
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List findBenefitAmountInBack(String name, String storeName, String addTime,String endTime, String directMemberName,
			String directMerchantsName,String tradeStatus,String payType);
	
	/**
	 *查询直推会员直推商家的历史记录
	 * @param account
	 * @param tradeType
	 * @return
	 */
	public List<GjfMemberTradeDiviHistory> findMemberTradeDiviHis(String account,String tradeType);
	
	/**
	 * 查询支付明细
	 * @param pageNo
	 * @param pageSize
	 * @param ste
	 * @param name
	 * @param storeName
	 * @param addTime
	 * @param tradeNo
	 * @param payTradeNo
	 * @param tradeType
	 * @param tradeStatus
	 * @return
	 */
	public Pager findTradeLogByPage(int pageNo,int pageSize,String ste,String name,
			String storeName,String addTime,String endTime,String tradeNo,String payTradeNo,String tradeType,String tradeStatus)throws ParseException;
	
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
	public BigDecimal findCountTradeLog(String name, String storeName, String addTime,String endTime, String tradeNo, String payTradeNo,
			String tradeType, String tradeStatus);
	
	/**
	 * 查询让利，导出记录，分页，模糊查询
	 * @throws Exception 
	 */
	public Pager findAllBenefit(int pageNo,int pageSize,String name,String storeName,String payType,String tradeStatus,String addTime,String endTime,String ste,String directMemberName,String directMerchantsName)throws ParseException;

	/**
	 * 查询用户历史提现额
	 * @param id
	 * @param token
	 * @return
	 */
	public BigDecimal findMemberWithdrawHistoryMoney(Long id);
	
	/**
	 * 分红权记录
	 * @param pageNo
	 * @param pageSize
	 * @param name
	 * @param payStatus
	 * @param diviStatus
	 * @return
	 */
	public Pager findAllTradeDivi(int pageNo, int pageSize,String name,String payStatus,String diviStatus,String startDate,String endDate);
	
	/**
	 * 统计分红权购买记录
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List findCountTradeDivi(String name, String payStatus, String diviStatus);
	
	
	/**
	 * 分红交易记录 分页
	 * @param pageNo
	 * @param pageSize
	 * @param name
	 * @param addTime
	 * @param tradeNo
	 * @param tradeType
	 * @param tradeStatus
	 * @return
	 */
	public Pager findMemberTradeDiviHistoryByPage(int pageNo,int pageSize,String name,String addTime,String endTime,String tradeNo,String tradeType,String tradeStatus);

	/**
	 * 当前条件统计分红记录
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List findCountTradeDiviHistory(String name,String addTime,String endTime,String tradeNo,String tradeType,String tradeStatus);
	
	/**
	 * 凤凰宝交易记录
	 * @param pageNo
	 * @param pageSize
	 * @param account
	 * @return
	 */
	public Pager findFhTreaureTradeHistory(Integer pageNo, Integer pageSize, String account);

}
