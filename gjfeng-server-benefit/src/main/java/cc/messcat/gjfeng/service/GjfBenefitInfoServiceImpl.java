package cc.messcat.gjfeng.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cc.messcat.gjfeng.common.bean.Pager;
import cc.messcat.gjfeng.common.exception.MyException;
import cc.messcat.gjfeng.common.util.ObjValid;
import cc.messcat.gjfeng.common.util.StringUtil;
import cc.messcat.gjfeng.common.vo.app.ResultVo;
import cc.messcat.gjfeng.dao.GjfBenefitInfoDao;
import cc.messcat.gjfeng.entity.GjfBenefitDay;
import cc.messcat.gjfeng.entity.GjfBenefitInfo;
import cc.messcat.gjfeng.entity.GjfBenefitIntegralDay;
import cc.messcat.gjfeng.entity.GjfBenefitMqLog;

@Service("gjfBenefitInfoService")
public class GjfBenefitInfoServiceImpl implements GjfBenefitInfoService {
	
	@Autowired
	@Qualifier("gjfBenefitInfoDao")
	private GjfBenefitInfoDao gjfBenefitInfoDao;

	/* 
	 * 修改让利系统的比例配置
	 * @see cc.messcat.gjfeng.service.GjfBenefitInfoService#updateBenefitInfo(java.util.List)
	 */
	@Override
	public ResultVo updateBenefitInfo(List<GjfBenefitInfo> benefitInfos) {
		if (null == benefitInfos || benefitInfos.isEmpty()) {
			throw new MyException(400, "对象不存在", null);
		}
		double totalSysRatio=0.00;
		for (GjfBenefitInfo bi : benefitInfos) {
			// 1.判断参数是否为空,判断参数类型是会员子系统还是商家子系统(会员子系统+商家子系统占比=100%)
			if (!ObjValid.isValid(bi) || !ObjValid.isValid(bi.getId())) {
				throw new MyException(400, "对象不存在", null);
			}
			Map<String, Object> attrs = new HashMap<String, Object>();
			attrs.put("id", bi.getId());
			GjfBenefitInfo benefitInfo = gjfBenefitInfoDao.query(GjfBenefitInfo.class, attrs);
			if (!ObjValid.isValid(benefitInfo)) {
				throw new MyException(400, "对象不存在", null);
			}
			if (StringUtil.isBlank(bi.getRatioType()) || (!"0".equals(bi.getRatioType()) && !"1".equals(bi.getRatioType()))) {
				throw new MyException(400, "数据类型错误", null);
			}
			if (null == bi.getSysRatio() || bi.getSysRatio().doubleValue() < 0 || bi.getSysRatio().doubleValue() > 100) {
				throw new MyException(400, "子系统占比错误", null);
			}
			if (null == bi.getDiviPoolsRatio() || bi.getDiviPoolsRatio().doubleValue() < 0 || bi.getDiviPoolsRatio().doubleValue() > 100) {
				throw new MyException(400, "分红池占比错误", null);
			}
			if (null == bi.getPlatformRatio() || bi.getPlatformRatio().doubleValue() < 0 || bi.getPlatformRatio().doubleValue() > 100) {
				throw new MyException(400, "平台占比错误", null);	
			}
			if ("0".equals(bi.getRatioType())) {
				if (null == bi.getAgentRatio() || bi.getAgentRatio().doubleValue() < 0 || bi.getAgentRatio().doubleValue() > 100) {
					throw new MyException(400, "代理占比错误", null);
				}
				if (null == bi.getAgentCityRatio() || bi.getAgentCityRatio().doubleValue() < 0 || bi.getAgentCityRatio().doubleValue() > 100) {
					throw new MyException(400, "市代占比错误", null);
				}
				if (null == bi.getAgentAreaRatio() || bi.getAgentAreaRatio().doubleValue() < 0 || bi.getAgentAreaRatio().doubleValue() > 100) {
					throw new MyException(400, "区代占比错误", null);
				}
				if (null == bi.getAgentIndiRatio() || bi.getAgentIndiRatio().doubleValue() < 0 || bi.getAgentIndiRatio().doubleValue() > 100) {
					throw new MyException(400, "个代占比错误", null);
				}
				if (null == bi.getDirectMembersRatio() || bi.getDirectMembersRatio().doubleValue() < 0 || bi.getDirectMembersRatio().doubleValue() > 100) {
					throw new MyException(400, "直推会员占比错误", null);
				}
				if (null == bi.getDirectMerchantsRatio() || bi.getDirectMerchantsRatio().doubleValue() < 0 || bi.getDirectMerchantsRatio().doubleValue() > 100) {
					throw new MyException(400, "直推商家占比错误", null);
				}
			}
			if (null == bi.getWithdrawalRatio() || bi.getWithdrawalRatio().doubleValue() < 0 || bi.getWithdrawalRatio().doubleValue() > 100) {
				throw new MyException(400, "提现占比错误", null);
			}
			if (null == bi.getInsuranceRatio() || bi.getInsuranceRatio().doubleValue() < 0 || bi.getInsuranceRatio().doubleValue() > 100) {
				throw new MyException(400, "责任险占比错误", null);
			}
			if (null == bi.getTaxRatio() || bi.getTaxRatio().doubleValue() < 0 || bi.getTaxRatio().doubleValue() > 100) {
				throw new MyException(400, "税收占比错误", null);	
			}
			// 额度发放占比
			if (null == bi.getIssueRatio()) {
				throw new MyException(400, "发放占比错误", null);
			}
			
			// 3.若是会员子系统
			if ("0".equals(bi.getRatioType())) {
				// 3.1 直推会员+直推商家+分红池=70%
				// 3.2 平台+代理=30%
				// 3.3 市代+区代+个代 <= 代理
				if(bi.getAgentRatio() < bi.getAgentCityRatio()+bi.getAgentAreaRatio()+bi.getAgentIndiRatio()){
					throw new MyException(400, " 会员子系统：市代+区代+个代 > 总代理占比", null);
				}
				/*// 3.4 直推会员+直推商家+分红池+平台+代理=100%
				if (bi.getDirectMembersRatio()+bi.getDirectMerchantsRatio()+bi.getDiviPoolsRatio()+bi.getPlatformRatio()+bi.getAgentRatio() != 100) {
					throw new MyException(400, "会员子系统：直推会员+直推商家+分红池+平台+代理 != 100%", null);
				}*/
			}
			// 4.若是商家子系统
			if ("1".equals(bi.getRatioType())) {
				// 4.1 平台+分红池=100%
				/*if (bi.getDiviPoolsRatio()+bi.getPlatformRatio() != 100) {
					throw new MyException(400, "商家子系统：平台+分红池 != 100%", null);
				}*/
			}
			// 提现+税收+责任险=100%
			/*if (bi.getWithdrawalRatio()+bi.getTaxRatio()+bi.getInsuranceRatio() != 100) {
				throw new MyException(400, "提现+税收+责任险 != 100%", null);
			}*/
			totalSysRatio+=bi.getSysRatio();
			
			//set
			benefitInfo.setSysRatio(bi.getSysRatio());
			benefitInfo.setPlatformRatio(bi.getPlatformRatio());
			benefitInfo.setDiviPoolsRatio(bi.getDiviPoolsRatio());
			benefitInfo.setAgentIndiRatio(bi.getAgentIndiRatio());
			if ("0".equals(bi.getRatioType())) {
				benefitInfo.setAgentRatio(bi.getAgentRatio());
				benefitInfo.setAgentCityRatio(bi.getAgentCityRatio());
				benefitInfo.setAgentAreaRatio(bi.getAgentAreaRatio());
				
				benefitInfo.setDirectMembersRatio(bi.getDirectMembersRatio());
				benefitInfo.setDirectMerchantsRatio(bi.getDirectMerchantsRatio());
				benefitInfo.setDirectMembersSecondRatio(bi.getDirectMembersSecondRatio());
				benefitInfo.setDirectMembersThreeRatio(bi.getDirectMembersThreeRatio());
				benefitInfo.setDirectMerchantsSecondRatio(bi.getDirectMerchantsSecondRatio());
				benefitInfo.setDirectMerchantsThreeRatio(bi.getDirectMerchantsThreeRatio());
				benefitInfo.setOperationCenterRatio(bi.getOperationCenterRatio());
			}
			benefitInfo.setWithdrawalRatio(bi.getWithdrawalRatio());
			benefitInfo.setInsuranceRatio(bi.getInsuranceRatio());
			benefitInfo.setTaxRatio(bi.getTaxRatio());
			benefitInfo.setIssueRatio(bi.getIssueRatio());
			//活跃一类比例
			if (ObjValid.isNotValid(bi.getUnitPrice())) {
				benefitInfo.setUnitPrice(0.00);
			}else {
				benefitInfo.setUnitPrice(bi.getUnitPrice());
			}
			//非活跃一类比例
			if (ObjValid.isNotValid(bi.getActivieRegionUnitPrice())) {
				benefitInfo.setActivieRegionUnitPrice(0.00);
			}else {
				benefitInfo.setActivieRegionUnitPrice(bi.getActivieRegionUnitPrice());
			}
			//活跃二类比例
			if (ObjValid.isNotValid(bi.getActivitySecondUnitPrice())) {
				benefitInfo.setActivitySecondUnitPrice(0.00);
			}else {
				benefitInfo.setActivitySecondUnitPrice(bi.getActivitySecondUnitPrice());
			}
			//非活跃二类比例
			if (ObjValid.isNotValid(bi.getNoActivitySecondUnitPrice())) {
				benefitInfo.setNoActivitySecondUnitPrice(0.00);
			}else {
				benefitInfo.setNoActivitySecondUnitPrice(bi.getNoActivitySecondUnitPrice());
			}
			//活跃三类比例
			if (ObjValid.isNotValid(bi.getActivityThreeUnitPrice())) {
				benefitInfo.setActivityThreeUnitPrice(0.00);
			}else {
				benefitInfo.setActivityThreeUnitPrice(bi.getActivityThreeUnitPrice());
			}
			//费活跃三类比例
			if (ObjValid.isNotValid(bi.getNoActivityThreeUnitPrice())) {
				benefitInfo.setNoActivityThreeUnitPrice(0.00);
			}else {
				benefitInfo.setNoActivityThreeUnitPrice(bi.getNoActivityThreeUnitPrice());
			}
			gjfBenefitInfoDao.update(benefitInfo);
		}
		if(totalSysRatio != 100){
			throw new MyException(400, "系统占比必须为100%", null);
		}
		return new ResultVo(200, "修改成功", null);
	}

	/* 
	 * 根据类型查找占比配置
	 * @see cc.messcat.gjfeng.service.GjfBenefitInfoService#findByType(java.lang.String)
	 */
	@Override
	public GjfBenefitInfo findByType(String type) {
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("ratioType", type);
		return gjfBenefitInfoDao.query(GjfBenefitInfo.class, attrs);
	}
	
	/* 
	 * 查询所有的占比配置
	 * @see cc.messcat.gjfeng.service.GjfBenefitInfoService#findAlls()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<GjfBenefitInfo> findAlls() {
		return gjfBenefitInfoDao.getAll(GjfBenefitInfo.class.getName());
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public ResultVo findBenefitDayByPage(int pageNo, int pageSize) {
		//创建查询条件
		Map<String, Object> attrs=new HashMap<>();
		//获取每天统计信息
		List<GjfBenefitIntegralDay> list=gjfBenefitInfoDao.queryList(GjfBenefitIntegralDay.class, (pageNo - 1) * pageSize, pageSize, "id", "desc", attrs);
		//统计总数
		Long count=gjfBenefitInfoDao.queryTotalRecord(GjfBenefitIntegralDay.class, attrs);
		//创建分页对象
		Pager pager = new Pager(pageSize, pageNo,count.intValue(),list);
		//返回结果
		return new ResultVo(200,"查询成功",pager);
	}

	@Override
	public ResultVo findBenefitHistoryByTime(String addTime) throws Exception {
		return new ResultVo(200,"查询成功",gjfBenefitInfoDao.findBenefitHistoryByTime(addTime));
	}
	
	/* 
	 * 保存mq调用记录
	 * @see cc.messcat.gjfeng.service.GjfBenefitInfoService#saveBenefitMqLog(cc.messcat.gjfeng.entity.GjfBenefitMqLog)
	 */
	@Override
	public ResultVo saveBenefitMqLog(GjfBenefitMqLog benefitMqLog) {
		gjfBenefitInfoDao.save(benefitMqLog);
		return new ResultVo(200,"操作成功",null);
	}

	/* 
	 * 修改mq调用记录
	 * @see cc.messcat.gjfeng.service.GjfBenefitInfoService#updateBenefitMqLog()
	 */
	@Override
	public ResultVo updateBenefitMqLog(String tradeNo,String finshStatus) {
		Map<String,Object> attrs = new HashMap<String,Object>();
		attrs.put("tradeNo", tradeNo);
		GjfBenefitMqLog benefitMqLog = gjfBenefitInfoDao.query(GjfBenefitMqLog.class, attrs);
		if (ObjValid.isValid(benefitMqLog)) {
			benefitMqLog.setFinshStatus(finshStatus);
			if ("3".equals(finshStatus)) {
				benefitMqLog.setFinshTime(new Date());
				benefitMqLog.setFinshNum(benefitMqLog.getFinshNum()+1);
			}
			gjfBenefitInfoDao.update(benefitMqLog);
		}
		return null;
	}

	/**
	 * 设置——分红金额设置 ： 今日实时池
	 * @param type  0：会员池   1：商户池
	 * @return
	 */
	@Override
	public ResultVo findTodayRealTimePool(String type) {
		return new ResultVo(200,"查询成功",gjfBenefitInfoDao.findTodayRealTimePool(type));
	}
}
