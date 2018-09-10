package cc.messcat.gjfeng.dubbo.core;

import java.text.ParseException;

import cc.messcat.gjfeng.common.vo.app.ResultVo;

public interface CountInfoDubbo {

	/**
	 * 统计会员（商家）总数
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public ResultVo findMemberAmount(int pageNo,int pageSize);
	
	/**
	 * 根据会员类型统计会员数量
	 * @param type
	 * @return
	 */
	public ResultVo findMemberAmountByType(String type);

	/**
	 * 统计会员下级消费总额度
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public ResultVo findMembersLowLevelConsume(int pageNo,int pageSize);
	
	/**
	 * 查询资金池
	 * @return
	 */
	public ResultVo findBenefitPool();
	
	/**
	 * 查询资金池分红记录(GjfBenefitHistory)
	 * @return
	 */
	public ResultVo findBenefitHistory(int pageNo,int pageSize);
	
	/**
	 * 查询分红数据
	 * @return
	 */
	public ResultVo findDiviData() throws ParseException;
	
	/**
	 * 查找短信发送记录
	 */
	public ResultVo findMessageHistory(int pageNo,int pageSize);
	
	/**
	 * 查询近30天网上商城交易额
	 * @return
	 */
	public ResultVo findAlmostOneMonthStoreTurnover();
	
	/**
	 * 查询近30天O2O交易额
	 * @return
	 */
	public ResultVo findAlmostOneMonth020Turnover();
	
	/**
	 * 查询近30天会员增加数量和趋势
	 * @return
	 */
	public ResultVo findAlmostOneMonthMemberAdd(String type);
	
	/**
	 * 查询近三个月会员消费额（趋势）
	 * @param id
	 * @return
	 */
	public ResultVo findAlmostThreeMonthMemberTurnover(Long id);
	
	/**
	 * 查询资金池(收入支出)
	 * @return
	 */
	public ResultVo findCashPool();
	
	/**
	 * 查询总报表
	 * @return
	 */
	public ResultVo findSummaryReport(int pageNo,int pageSize,String addTime,String endTime,String ste);
	
	/**
	 * 查询让利日报表
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public ResultVo findBenefitReport(int pageNo,int pageSize,String addTime,String endTime,String ste);
	
	/**
	 * 福利产出日报表
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public ResultVo findWealOutputReport(int pageNo,int pageSize,String addTime,String endTime,String ste);
	
	/**
	 * 福利派发报表
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public ResultVo findWealPayoutReport(int pageNo,int pageSize,String addTime,String endTime,String ste);
	
	/**
	 * 提现日报表
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public ResultVo findWithdrawReport(int pageNo,int pageSize,String addTime,String endTime,String ste);
	
	/**
	 * 查询订单累计成交额
	 * @return
	 */
	public ResultVo findOrderTotalAmount();
	
	/**
	 * 实时分红余额
	 * @param type 0:普通用户  1:商户
	 * @return
	 */
	public ResultVo findOntimeDiviAmount(String type);
	
	
	/*****每天定时统计********/
	/**
	 * @描述 统计总报表
	 * @author Karhs
	 * @date 2017年2月17日
	 * @return
	 */
	public ResultVo updateSummaryReport();
	
	/**
	 * @描述 统计让利日报表
	 * @author Karhs
	 * @date 2017年2月17日
	 * @return
	 */
	public ResultVo updateBenefitReport();
	
	/**
	 * @描述 统计福利产出日报表
	 * @author Karhs
	 * @date 2017年2月17日
	 * @return
	 */
	public ResultVo updateWealOutputReport();
	
	/**
	 * @描述 统计福利派发报表
	 * @author Karhs
	 * @date 2017年2月17日
	 * @return
	 */
	public ResultVo updateWealPayoutReport();
	
	/**
	 * @描述 统计提现日报表
	 * @author Karhs
	 * @date 2017年2月17日
	 * @return
	 */
	public ResultVo updateWithdrawReport();
	
	/**
	 * 统计资金池表
	 * @return
	 */
	public ResultVo updatePoolReport();
	
	/**
	 * @描述 修改总报表和福利派发报表的福利派发为实际派发额
	 * @author Karhs
	 * @date 2017年3月10日
	 * @return
	 */
	public ResultVo updateSummaryAndPayoutReport();
	
	/*****每天定时统计 end********/
	
	
	/**
	 * 查询授信充值额
	 * @return
	 */
	public ResultVo findCreditLineRecharge(int pageNo,int pageSize) throws ParseException;
	
	/**
	 * 查询商户未使用授信充值额  分页  模糊查询
	 * @param storeNum  店铺编码
	 * @param storeName 店铺名称
	 * @param memeberName 会员名称
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public ResultVo findStoreCreditLine(String storeNum,String storeName,String memeberName,int pageNo,int pageSize,String orderType);
	
	/**
	 * 查询错误信息
	 * @return
	 */
	public ResultVo findErrorMsg(int pageNo,int pageSize);
	
	/**
	 * 查找区域业绩
	 * @param pageNo
	 * @param pageSize
	 * @param provinceId
	 * @param cityId
	 * @param areaId
	 * @return
	 */
	public ResultVo findRegionalPerformance(int pageNo, int pageSize,Long provinceId,Long cityId,Long areaId,String startTime,String endTime);
	
	/**
	 * 统计当前条件区域业绩
	 * @param pageNo
	 * @param pageSize
	 * @param provinceId
	 * @param cityId
	 * @param areaId
	 * @return
	 */
	public ResultVo findCountRegionalPerformance(Long provinceId,Long cityId,Long areaId,String startTime,String endTime);
	
	/**
	 * 分红趋势
	 * @param type
	 * @return
	 */
	public ResultVo findAlmostOneMonthBenefitDay(String type);
	
	/**
	 * 今日O2O订单
	 * @param pageNo
	 * @param pageSize
	 * @param startTime
	 * @param endTime
	 * @param tradeStatus
	 * @return
	 */
	public ResultVo findTodayO2OOrders(int pageNo, int pageSize);
	
	/**
	 * 查询池收入
	 * @param pageNo
	 * @param pageSize
	 * @param op
	 * @param type
	 * @return
	 */
	public ResultVo findPoolIn(int pageNo, int pageSize,String op,String type);
	
	/**
	 * 查询池收入支出明细
	 * @param pageNo
	 * @param pageSize
	 * @param op
	 * @param type
	 * @return
	 */
	public ResultVo findPoolDetail(int pageNo, int pageSize,String addTime,String type);
	
	/**
	 * 查询代理分红支出明细
	 * @param pageNo
	 * @param pageSize
	 * @param op
	 * @param type
	 * @return
	 */
	public ResultVo findTradeDiviByAgent(int pageNo, int pageSize,String addTime,String type);
	
	/**
	 * 查询池支出
	 * @param pageNo
	 * @param pageSize
	 * @param op
	 * @param type
	 * @return
	 */
	public ResultVo findPoolOut(int pageNo, int pageSize,String op,String type);
	
	/**
	 * 统计总报表
	 * @param pageNo
	 * @param pageSize
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public ResultVo countTotalDataByTime(int pageNo,int pageSize,String beginTime,String endTime);
	
	/**
	 * 统计总额
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public ResultVo countTotalDataInfo(String beginTime,String endTime);
	
	/**
	 * 添加每天统计记录
	 * @param pageNo
	 * @param pageSize
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public ResultVo addTotalReportData(Integer pageNo,Integer pageSize,String beginTime,String endTime);
	
	/**
	 * 更新统计信息
	 * @param id
	 * @return
	 */
	public ResultVo updateCouneInf(Long id);
	
	/**
	 * 添加
	 * @param addTime
	 * @return
	 */
	public ResultVo addTotalReport(String addTime);
	
	/**
	 * 调整用户每月是否参与分红
	 * @return
	 */
	public ResultVo modifyAcountConsumption();
	
	/**
	 * 领回20%分红权减半50%再减半不变
	 * @return
	 */
	public ResultVo modifyMemberDeductDivi();
	
	/**
	 * 统计用户是否为活跃区用户
	 * @return
	 */
	public ResultVo modifyMemberIsActivity();
	
}
