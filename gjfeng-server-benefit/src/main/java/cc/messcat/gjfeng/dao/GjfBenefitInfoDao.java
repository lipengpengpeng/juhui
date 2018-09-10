package cc.messcat.gjfeng.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import cc.messcat.gjfeng.common.vo.app.ResultVo;
import cc.messcat.gjfeng.entity.GjfBenefitDay;
import cc.messcat.gjfeng.entity.GjfBenefitHistory;
import cc.messcat.gjfeng.entity.GjfSetDividends;

public interface GjfBenefitInfoDao extends BaseHibernateDao<Serializable> {

	/**
	 * @描述 求所有用户or商家的分红总数
	 * @author Karhs
	 * @date 2016年12月29日
	 * @param memberType
	 * @return
	 */
	public BigDecimal queryTotalDiviNumByMemberType(String memberType);
	
	/**
	 * @描述 每隔几分钟定时计算会员和商家所获得的分红
	 * @author Karhs
	 * @date 2017年2月8日
	 * @return
	 */
	public void updateBenefit() throws NumberFormatException, SQLException;
	
	/**
	 * @描述 计算代理每天的分红
	 * @author Karhs
	 * @date 2016年12月30日
	 * @param id
	 * @throws NumberFormatException
	 * @throws SQLException
	 */
	public void updateAgentBenefitNotify() throws NumberFormatException, SQLException;
	
	/**
	 * @描述 手动或自动发放会员分红
	 * @author Karhs
	 * @date 2017年2月16日
	 * @return
	 */
	public ResultVo updateMemberBenefit(GjfBenefitDay benefitDay,List<Integer> memList,String account,String activityType);
	
	/**
	 * @描述 手动或自动发放商家分红
	 * @author Karhs
	 * @date 2017年2月16日
	 * @return
	 */
	public ResultVo updateMerchantsBenefit(GjfBenefitDay benefitDay,List<Integer> memList,String account,String activityType);
	
	/**
	 * @描述 手动结算发放代理分红
	 * @author Karhs
	 * @date 2017年2月20日
	 * @return
	 */
	public ResultVo updateAgentBenefit(Long memberId,String token);
	
	/**
	 * 查询分红
	 * @param addTime
	 * @return
	 */
	public List<GjfBenefitHistory> findBenefitHistoryByTime(String addTime);
	
	/**
	 * 设置——分红金额设置 ： 今日实时池
	 * @param type  0：会员池   1：商户池
	 * @return
	 */
	public BigDecimal findTodayRealTimePool(String type);
	
	/**
	 * 查找所有用户类型
	 * @return
	 */
	public List<String> findAllMemberType();
	
	/**
	 * 查询用户的下级
	 * @param memberId
	 * @return
	 */
	public List<Integer> findAllMemberSuperId(Integer memberId);
	
	/**
	 * 获取今天分红用户id
	 * @return
	 */
	public List<Integer> findAllMemberId();
	
	/**
	 * 获取发放用户或商户信息
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List findMemberMeetIssue(String type,String activityType);
	
	/**
	 * 获取分红权设置数据
	 * @param bfCumulativeMoney
	 * @return
	 */
	public List<GjfSetDividends> findDividendsDate(Double bfCumulativeMoney);
	
	/**
	 * 根据类型获取设置信息，0 小于消费金额的数据   1 大于消费金额的数据
	 * @param cumulativeMoney
	 * @return
	 */
	public List<GjfSetDividends> findDividendiByCumulativeMoney(Double cumulativeMoney,String type);

	
	
}
