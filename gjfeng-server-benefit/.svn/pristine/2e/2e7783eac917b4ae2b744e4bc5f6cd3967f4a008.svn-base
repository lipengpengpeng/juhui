package cc.messcat.gjfeng.service;

import java.util.List;

import cc.messcat.gjfeng.common.vo.app.ResultVo;
import cc.messcat.gjfeng.entity.GjfBenefitInfo;
import cc.messcat.gjfeng.entity.GjfBenefitMqLog;

public interface GjfBenefitInfoService {

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
	
	public ResultVo findBenefitDayByPage(int pageNo, int pageSize);
	
	public ResultVo findBenefitHistoryByTime(String addTime) throws Exception;
	
	/**
	 * @描述 保存mq调用记录
	 * @author Karhs
	 * @date 2017年2月27日
	 * @param benefitMqLog
	 * @return
	 */
	public ResultVo saveBenefitMqLog(GjfBenefitMqLog benefitMqLog);
	
	/**
	 * @描述 修改mq调用记录
	 * @author Karhs
	 * @date 2017年2月27日
	 * @return
	 */
	public ResultVo updateBenefitMqLog(String tradeNo,String finshStatus);
	
	/**
	 * 设置——分红金额设置 ： 今日实时池
	 * @param type  0：会员池   1：商户池
	 * @return
	 */
	public ResultVo findTodayRealTimePool(String type);
}
