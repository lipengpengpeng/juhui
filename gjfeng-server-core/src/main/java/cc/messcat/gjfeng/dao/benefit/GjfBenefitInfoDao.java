package cc.messcat.gjfeng.dao.benefit;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import cc.messcat.gjfeng.common.bean.Pager;
import cc.messcat.gjfeng.common.vo.app.ResultVo;
import cc.messcat.gjfeng.dao.BaseHibernateDao;
import cc.messcat.gjfeng.entity.GjfBenefitDay;
import cc.messcat.gjfeng.entity.GjfMemberConsumptiomNum;
import cc.messcat.gjfeng.entity.GjfMemberTradeBenefit;
import cc.messcat.gjfeng.entity.GjfOrderInfo;
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
	public ResultVo updateMemberBenefit(GjfBenefitDay benefitDay);
	
	/**
	 * @描述 手动或自动发放商家分红
	 * @author Karhs
	 * @date 2017年2月16日
	 * @return
	 */
	public ResultVo updateMerchantsBenefit(GjfBenefitDay benefitDay);
	
	/**
	 * @描述 手动结算发放代理分红
	 * @author Karhs
	 * @date 2017年2月20日
	 * @return
	 */
	public ResultVo updateAgentBenefit(Long memberId,String token);
	
	/**
	 * @描述 查询所有的用户类型
	 * @author Karhs
	 * @date 2017年3月7日
	 * @return
	 */
	public List<String> findAllMemberType();
	
	
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
	
	/**
	 * 获取用户最近七天之内的让利数据
	 * @return
	 */
	public ResultVo findBenefitByTime(String mobile);
	
	/**
	 * 统计线上消费和线下消费笔数
	 * @param type 0 线上消费  1 线下消费
	 * @return
	 */
	public ResultVo findCountCousumtion(String type,Long memId);
	
	/**
	 * 获取用户订单消费最早记录
	 * @param memId
	 * @return
	 */
	public List<GjfOrderInfo> findAarliestConsumptionHistory(Long memId);
	
	/**
	 * 获取用户让利最早记录
	 * @param memId
	 * @return
	 */
	public List<GjfMemberTradeBenefit> findAarliestBenefitHistory(Long memId);
	
	/**
	 * 獲取用戶消費次數
	 * @param memId
	 * @return
	 */
	public List<GjfMemberConsumptiomNum> findMemberCousumptionNum(Long memId);
	
	/**
	 * 统计用户提现和让利金额
	 * @param type
	 * @return
	 */
	public BigDecimal sumMemberBenefitMoney(String type,Long memberId);
	
	/**
	 * 查询特殊发放人交易记录
	 * @param pageNo
	 * @param pageSize
	 * @param addTime
	 * @param memId
	 * @return
	 */
	public Pager findSpecialMemberTradeDiviHistory(Integer pageNo,Integer pageSize,String addTime,Long memId,String type);
	
	/**
	 * 获取分红权单价
	 * @param type
	 * @return
	 */
	public BigDecimal findUnitPriceByTime(String type,String time);
	
}
