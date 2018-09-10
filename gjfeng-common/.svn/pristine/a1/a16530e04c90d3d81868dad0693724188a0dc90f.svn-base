package cc.messcat.gjfeng.dubbo.benefit;

import java.sql.SQLException;
import java.util.List;

import cc.messcat.gjfeng.common.vo.app.ResultVo;
import cc.messcat.gjfeng.entity.GjfBenefitInfo;
import cc.messcat.gjfeng.entity.GjfBenefitMqLog;

public interface GjfBenefitInfoDubbo {

	/**
	 * @描述 修改让利系统的比例配置
	 * @author Karhs
	 * @date 2016年12月28日
	 * @param benefitInfos
	 * @return
	 */
	public ResultVo updateBenefitInfo(List<GjfBenefitInfo> benefitInfos);
	
	/**
	 * @描述 根据类型查找占比配置
	 * @author Karhs
	 * @date 2016年12月28日
	 * @param type
	 * @return
	 */
	public GjfBenefitInfo findByType(String type);
	
	/**
	 * @描述 查询所有的占比配置
	 * @author Karhs
	 * @date 2016年12月28日
	 * @return
	 */
	public List<GjfBenefitInfo> findAlls();
	
	/**
	 * @描述 每天定时计算返回所有用户所获得的分红
	 * @author Karhs
	 * @date 2017年2月7日
	 * @param membersMobile
	 * @param merchantsMobile
	 * @param totalBenefit
	 * @param consumptionMoney
	 * @return
	 */
	public ResultVo updateMemberBenefitNotify() throws NumberFormatException, SQLException;
	
	/**
	 * @描述 每天定时计算当天代理所获得的分红
	 * @author Karhs
	 * @date 2016年12月28日
	 * @return
	 */
	public ResultVo updateAgentBenefitByDayNotify() throws NumberFormatException, SQLException;
	
	/**
	 * @描述 手动或自动发放会员或商家分红
	 * @author Karhs
	 * @date 2017年2月16日
	 * @return
	 */
	public ResultVo updateBenefit(String account,String activityType);
	
	/**
	 * @描述 手动结算发放代理分红
	 * @author Karhs
	 * @date 2017年2月20日
	 * @return
	 */
	public ResultVo updateAgentBenefit(Long memberId,String token);
	
	public ResultVo findBenefitDayByPage(int pageNo, int pageSize);
	
	public ResultVo findBenefitHistoryByTime(String addTime)throws Exception;
	
	/**
	 * @描述 保存mq调用记录
	 * @author Karhs
	 * @date 2017年2月27日
	 * @param benefitMqLog
	 * @return
	 */
	public ResultVo saveBenefitMqLog(GjfBenefitMqLog benefitMqLog);
	
	/**
	 * 设置——分红金额设置 ： 今日实时池
	 * @param type  0：会员池   1：商户池
	 * @return
	 */
	public ResultVo findTodayRealTimePool(String type);
	
	/**
	 * 测试云南扣减分红权
	 * @return
	 */
	public ResultVo testMemberDedectDiviNum();
	
}
