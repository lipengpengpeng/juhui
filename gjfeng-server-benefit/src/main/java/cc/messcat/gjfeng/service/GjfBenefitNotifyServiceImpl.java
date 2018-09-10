package cc.messcat.gjfeng.service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cc.messcat.gjfeng.common.constant.CommonStatus;
import cc.messcat.gjfeng.common.exception.MyException;
import cc.messcat.gjfeng.common.util.BeanUtil;
import cc.messcat.gjfeng.common.util.DateHelper;
import cc.messcat.gjfeng.common.util.ObjValid;
import cc.messcat.gjfeng.common.util.RandUtil;
import cc.messcat.gjfeng.common.util.Sha256;
import cc.messcat.gjfeng.common.vo.app.ResultVo;
import cc.messcat.gjfeng.dao.GjfBenefitInfoDao;
import cc.messcat.gjfeng.entity.GjfBenefitDay;
import cc.messcat.gjfeng.entity.GjfBenefitInfo;
import cc.messcat.gjfeng.entity.GjfBenefitIntegralDay;
import cc.messcat.gjfeng.entity.GjfBenefitPool;
import cc.messcat.gjfeng.entity.GjfErrorInfo;
import cc.messcat.gjfeng.entity.GjfMemberDiviNumHistory;
import cc.messcat.gjfeng.entity.GjfMemberInfo;
import cc.messcat.gjfeng.entity.GjfMemberTradeDiviHistory;
import cc.messcat.gjfeng.entity.GjfSetDividends;
import cc.messcat.gjfeng.entity.GjfStoreInfo;

@Service("gjfBenefitNotifyService")
public class GjfBenefitNotifyServiceImpl implements GjfBenefitNotifyService {

	@Autowired
	@Qualifier("gjfBenefitInfoDao")
	private GjfBenefitInfoDao gjfBenefitInfoDao;

	@Autowired
	@Qualifier("gjfBenefitInfoService")
	private GjfBenefitInfoService gjfBenefitInfoService;


	/*
	 * 每天24点计算返回所有用户所获得的分红，已供于手动和自动发放
	 * 
	 * @see cc.messcat.gjfeng.service.GjfBenefitNotifyService#
	 * updateMemberBenefitNotify(java.lang.String, java.lang.String,
	 * java.lang.Double, java.lang.Double)
	 */
	@Override
	public ResultVo updateMemberBenefitNotify() throws NumberFormatException, SQLException {
		// 调用存储过程
		System.out.println("调用“每天24点计算返回所有用户所获得的分红”存储过程");
		gjfBenefitInfoDao.updateBenefit();
		return new ResultVo(200, "统计成功", null);
	}

	/*
	 * 每天定时24点计算当天代理所获得的分红
	 * 
	 * @see cc.messcat.gjfeng.service.GjfBenefitInfoService#
	 * updateAgentBenefitByDayNotify()
	 */
	@Override
	public ResultVo updateAgentBenefitByDayNotify() throws NumberFormatException, SQLException {
		// 调用存储过程
		gjfBenefitInfoDao.updateAgentBenefitNotify();
		return new ResultVo(200, "统计成功", null);
	}

	/*
	 * 手动或自动发放会员或商家分红
	 * 
	 * @see cc.messcat.gjfeng.service.GjfBenefitNotifyService#updateBenefit()
	 */
	@Override
	public ResultVo updateBenefit(String account,String activityType) {
		// 1.判断是否今天已经发放了昨天的，如果没有则进行发放
		synchronized ("updateBenefit") {
			Map<String, Object> attrs = new HashMap<>();
			attrs.put("tradeDay", DateHelper.dataToString(DateHelper.getDateByCalculateDays(new Date(), -1), "yyyyMMdd"));
			GjfBenefitDay benefitDay = gjfBenefitInfoDao.query(GjfBenefitDay.class, attrs);
			if (ObjValid.isNotValid(benefitDay)) {
				return new ResultVo(400, "发放失败,没有统计数据", null);
			}
			/*if (benefitDay.getTradeStatus().equals("1")) {
				return new ResultVo(400, "发放失败,昨天分红已经发放成功了", null);
			}*/
			//查询特殊分红人底下会员
			if(BeanUtil.isValid(account)){
				
				attrs.remove("tradeDay");
				attrs.put("mobile", account);
				attrs.put("isDel", "1");
				attrs.put("status", "1");
				attrs.put("isDivi", "1");
				GjfMemberInfo member=gjfBenefitInfoDao.query(GjfMemberInfo.class, attrs);
				if(!BeanUtil.isValid(member)){
					return new ResultVo(400, "用户不存在或用户不可参与分红", null);
				}
				
				Set<Integer> personal=new HashSet<>();
				personal.add(member.getId().intValue());
				personal=findSepcialPersonals(personal,new ArrayList<>(personal));
				gjfBenefitInfoDao.updateMemberBenefit(benefitDay,new ArrayList<>(personal),account,activityType);
				
			}else{

				//查询总报表和福利派发报表统计记录，以便于修改
				gjfBenefitInfoDao.updateMemberBenefit(benefitDay,null,null,activityType);
			}
			//云南扣除分红权
			//addMemberDeductHistory();
			
		}
		return new ResultVo(200, "发放成功", null);
	}
	
	/**获取用户下级
	 * @return
	 */
	public Set<Integer> findSepcialPersonals(Set<Integer> personal,List<Integer> superId){
		int size=personal.size();
		List<Integer> subSuperId=new ArrayList<>();
	
		for(Integer memId:superId){	
			List<Integer> spId=gjfBenefitInfoDao.findAllMemberSuperId(memId);
			personal.addAll(spId);
			subSuperId.addAll(spId);		
		}	
		
		if(personal.size()>size){
			findSepcialPersonals(personal,subSuperId);
		}	
		return personal;
	}	
	
	/*
	 * 手动结算发放代理分红
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.GjfBenefitNotifyService#updateAgentBenefit()
	 */
	@Override
	public ResultVo updateAgentBenefit(Long memberId, String token) {
		synchronized (token) {
			// 每天的统计时间禁止发放操作
			try {
				/*DateHelper.getMinBetween(DateHelper.stringToDate("2017-3-3 00:01:10", "yyyy-MM-dd HH:mm:ss"),
					DateHelper.stringToDate("2017-3-2 23:59:10", "yyyy-MM-dd HH:mm:ss"));*/
				return gjfBenefitInfoDao.updateAgentBenefit(memberId, token);
			} catch (Exception e) {
				e.printStackTrace();
				throw new MyException(400, "方法失败", null);
			}

		}
	}

	/*
	 * 保存错误信息
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.GjfBenefitNotifyService#saveErrMsg(cc.messcat.
	 * gjfeng.entity.GjfErrorInfo)
	 */
	@Override
	public ResultVo saveErrMsg(GjfErrorInfo errorInfo) {
		gjfBenefitInfoDao.save(errorInfo);
		return new ResultVo(200, "保存成功", null);
	}

	/**
	 * 添加用户扣减历史
	 */
	@Override
	public ResultVo addMemberDeductHistory() {
		//或今天分红用户id
		List<Integer> list=gjfBenefitInfoDao.findAllMemberId();
		if(BeanUtil.isValid(list)){
			for(Integer memId:list){
				//获取用户信息
				Map<String, Object> attrs=new HashMap<>();
				attrs.put("id", Long.valueOf(memId));
				GjfMemberInfo memberInfo=gjfBenefitInfoDao.query(GjfMemberInfo.class, attrs);
				if(BeanUtil.isValid(memberInfo)){
					//获取已经使用的积分
					BigDecimal userPoint=memberInfo.getCumulativeMoney().subtract(memberInfo.getConsumptionMoney());
					//获取使用积分和上次扣减的差额
					BigDecimal dedcutMoney=userPoint.subtract(memberInfo.getLastTimeDiviMoney());
					//上次扣减时的积分
					BigDecimal dedcutPoint=new BigDecimal(0.00);
					//扣减分红权
					double dedcutDiviNum=0;
					//如果使用的积分大于500小于10000
					if(userPoint.doubleValue()>=500&&userPoint.doubleValue()<=10000){
						if(dedcutMoney.doubleValue()>=500){
							dedcutDiviNum=Math.floor(dedcutMoney.divide(new BigDecimal(500), 6, BigDecimal.ROUND_DOWN).doubleValue());
							dedcutPoint=new BigDecimal(dedcutDiviNum*500);
						}
					}else if(userPoint.doubleValue()>10000&&userPoint.doubleValue()<=1000000){
						if(dedcutMoney.doubleValue()>10000&&dedcutMoney.doubleValue()<=1000000){
							dedcutMoney=dedcutMoney.subtract(new BigDecimal(10000));
							dedcutDiviNum=20+Math.floor(dedcutMoney.divide(new BigDecimal(1000), 6, BigDecimal.ROUND_DOWN).doubleValue());
							dedcutPoint=new BigDecimal(20*500+Math.floor(dedcutMoney.divide(new BigDecimal(1000), 6, BigDecimal.ROUND_DOWN).doubleValue())*1000);
						}else if(dedcutMoney.doubleValue()>1000000){
							dedcutMoney=dedcutMoney.subtract(new BigDecimal(1000000));
							dedcutDiviNum=1010+Math.floor(dedcutMoney.divide(new BigDecimal(5000), 6, BigDecimal.ROUND_DOWN).doubleValue());
							dedcutPoint=new BigDecimal(20*500+990*1000+Math.floor(dedcutMoney.divide(new BigDecimal(5000), 6, BigDecimal.ROUND_DOWN).doubleValue())*5000);
						}else if(dedcutMoney.doubleValue()>=1000&&dedcutMoney.doubleValue()<=10000){
							dedcutDiviNum=Math.floor(dedcutMoney.divide(new BigDecimal(1000), 6, BigDecimal.ROUND_DOWN).doubleValue());
							dedcutPoint=new BigDecimal(Math.floor(dedcutMoney.divide(new BigDecimal(1000), 6, BigDecimal.ROUND_DOWN).doubleValue())*1000);
						}
					}else{
						if(dedcutMoney.doubleValue()>1000000){
							dedcutMoney=dedcutMoney.subtract(new BigDecimal(1000000));
							dedcutDiviNum=1010+Math.floor(dedcutMoney.divide(new BigDecimal(5000), 6, BigDecimal.ROUND_DOWN).doubleValue());
							dedcutPoint=new BigDecimal(20*500+990*1000+Math.floor(dedcutMoney.divide(new BigDecimal(5000), 6, BigDecimal.ROUND_DOWN).doubleValue())*5000);
						}else if(dedcutMoney.doubleValue()<1000000&&dedcutMoney.doubleValue()>5000){
							dedcutDiviNum=Math.floor(dedcutMoney.divide(new BigDecimal(5000), 6, BigDecimal.ROUND_DOWN).doubleValue());
							dedcutPoint=new BigDecimal(Math.floor(dedcutMoney.divide(new BigDecimal(5000), 6, BigDecimal.ROUND_DOWN).doubleValue())*5000);
						}
					}
					//添加用户扣减记录
					if(dedcutDiviNum!=0){
						GjfMemberDiviNumHistory memHistory=new GjfMemberDiviNumHistory();
						memHistory.setDedcutAmount(dedcutPoint);
						memHistory.setDeductDiviNum(new BigDecimal(dedcutDiviNum));
						memHistory.setMemberDiviNumBf(memberInfo.getDividendsNum());
						memHistory.setMemberId(memberInfo.getId());
						memHistory.setMemberMobile(memberInfo.getMobile());
						memHistory.setMemberName(memberInfo.getName());
						memHistory.setTradeStatus("1");
						memHistory.setTradeType("0");
						memHistory.setAddTime(new Date());
						memHistory.setMemberDiviNumBf(memberInfo.getLastTimeDiviMoney());
					
						//更改用户信息
						memberInfo.setLastTimeDiviMoney(memberInfo.getLastTimeDiviMoney().add(dedcutPoint));
						memberInfo.setDeductDiviNum(memberInfo.getDeductDiviNum().add(new BigDecimal(dedcutDiviNum)));
						//减去要扣减的分红权
						BigDecimal diviNum=memberInfo.getDividendsNum().subtract(new BigDecimal(dedcutDiviNum));
						if(diviNum.doubleValue()>0){
							memberInfo.setDividendsNum(memberInfo.getDividendsNum().subtract(new BigDecimal(dedcutDiviNum)));
						}else{
							memberInfo.setDividendsNum(new BigDecimal(0.000000));
						}
						
						
						memHistory.setMemberDiviNumAf(memberInfo.getLastTimeDiviMoney());
						memHistory.setMemberDiviNumAf(memberInfo.getDividendsNum());
						gjfBenefitInfoDao.save(memHistory);
						gjfBenefitInfoDao.update(memberInfo);
					}
					
				}
				
				//如果是商家
				if("1".equals(memberInfo.getType())){
					attrs.remove("id");
					attrs.put("memberId.id", Long.valueOf(memId));
					//获取店铺信息
					GjfStoreInfo storeInfo=gjfBenefitInfoDao.query(GjfStoreInfo.class, attrs);
					if(BeanUtil.isValid(storeInfo)){
						//获取已经使用的积分
						BigDecimal storeUserPoint=storeInfo.getStoreDividendsTotalMoney();
						//获取使用积分和上次扣减的差额
						BigDecimal storeDedcutMoney=storeUserPoint.subtract(storeInfo.getLastTimeDiviMoney());
						//上次扣减时的积分
						BigDecimal storeDedcutPoint=new BigDecimal(0.00);
						//扣减分红权
						double storeDedcutDiviNum=0;
						//如果使用的积分大于500小于10000
						if(storeUserPoint.doubleValue()>=500&&storeUserPoint.doubleValue()<=10000){
							if(storeDedcutMoney.doubleValue()>=500){
								storeDedcutDiviNum=Math.floor(storeDedcutMoney.divide(new BigDecimal(500), 6, BigDecimal.ROUND_DOWN).doubleValue());
								storeDedcutPoint=new BigDecimal(storeDedcutDiviNum*500);
							}
						}else if(storeUserPoint.doubleValue()>10000&&storeUserPoint.doubleValue()<=1000000){
							if(storeDedcutMoney.doubleValue()>10000&&storeDedcutMoney.doubleValue()<=1000000){
								storeDedcutMoney=storeDedcutMoney.subtract(new BigDecimal(10000));
								storeDedcutDiviNum=20+Math.floor(storeDedcutMoney.divide(new BigDecimal(1000), 6, BigDecimal.ROUND_DOWN).doubleValue());
								storeDedcutPoint=new BigDecimal(20*500+Math.floor(storeDedcutMoney.divide(new BigDecimal(1000), 6, BigDecimal.ROUND_DOWN).doubleValue())*1000);
							}else if(storeDedcutMoney.doubleValue()>1000000){
								storeDedcutMoney=storeDedcutMoney.subtract(new BigDecimal(1000000));
								storeDedcutDiviNum=1010+Math.floor(storeDedcutMoney.divide(new BigDecimal(5000), 6, BigDecimal.ROUND_DOWN).doubleValue());
								storeDedcutPoint=new BigDecimal(20*500+990*1000+Math.floor(storeDedcutMoney.divide(new BigDecimal(5000), 6, BigDecimal.ROUND_DOWN).doubleValue())*5000);
							}else if(storeDedcutMoney.doubleValue()>=1000&&storeDedcutMoney.doubleValue()<=10000){
								storeDedcutDiviNum=Math.floor(storeDedcutMoney.divide(new BigDecimal(5000), 6, BigDecimal.ROUND_DOWN).doubleValue());
								storeDedcutPoint=new BigDecimal(storeDedcutDiviNum*1000);
							}
						}else{
							if(storeDedcutMoney.doubleValue()>1000000){
								storeDedcutMoney=storeDedcutMoney.subtract(new BigDecimal(1000000));
								storeDedcutDiviNum=1010+Math.floor(storeDedcutMoney.divide(new BigDecimal(5000), 6, BigDecimal.ROUND_DOWN).doubleValue());
								storeDedcutPoint=new BigDecimal(20*500+990*1000+Math.floor(storeDedcutMoney.divide(new BigDecimal(5000), 6, BigDecimal.ROUND_DOWN).doubleValue())*5000);
							}else if(storeDedcutMoney.doubleValue()<1000000&&storeDedcutMoney.doubleValue()>5000){
								storeDedcutDiviNum=Math.floor(storeDedcutMoney.divide(new BigDecimal(5000), 6, BigDecimal.ROUND_DOWN).doubleValue());
								storeDedcutPoint=new BigDecimal(storeDedcutDiviNum*5000);
							}
						}
						
						if(storeDedcutDiviNum!=0){
							//添加用户扣减记录
							GjfMemberDiviNumHistory storeMemHistory=new GjfMemberDiviNumHistory();
							storeMemHistory.setDedcutAmount(storeDedcutPoint);
							storeMemHistory.setDeductDiviNum(new BigDecimal(storeDedcutDiviNum));
							storeMemHistory.setMemberDiviNumBf(storeInfo.getStoreDividendsNum());
							storeMemHistory.setMemberId(memberInfo.getId());
							storeMemHistory.setMemberMobile(memberInfo.getMobile());
							storeMemHistory.setMemberName(memberInfo.getName());
							storeMemHistory.setTradeStatus("1");
							storeMemHistory.setTradeType("1");
							storeMemHistory.setAddTime(new Date());
							storeMemHistory.setDedcutDiviTotalMoneyBf(storeInfo.getLastTimeDiviMoney());
							//更新店铺信息
							storeInfo.setLastTimeDiviMoney(storeInfo.getLastTimeDiviMoney().add(storeDedcutPoint));
							storeInfo.setDeductDiviNum(storeInfo.getDeductDiviNum().add(new BigDecimal(storeDedcutDiviNum)));
							//扣减的分红权
							BigDecimal diviNum=storeInfo.getStoreDividendsNum().subtract(new BigDecimal(storeDedcutDiviNum));
							if(diviNum.doubleValue()>0){
								storeInfo.setStoreDividendsNum(storeInfo.getStoreDividendsNum().subtract(new BigDecimal(storeDedcutDiviNum)));
							}else{
								storeInfo.setStoreDividendsNum(new BigDecimal(0.000000));
							}
							
							storeMemHistory.setMemberDiviNumAf(storeInfo.getStoreDividendsNum());
							storeMemHistory.setDedectDiviTotalMoneyAf(storeInfo.getLastTimeDiviMoney());
							gjfBenefitInfoDao.save(storeMemHistory);
							gjfBenefitInfoDao.update(storeInfo);
						}
					}
					
					
				}
			}
		}
		return new ResultVo(200, "更新成功", null);
	}

	/**
	 * 根据积分发放金额
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public ResultVo updateIntegralDirectMoney(String ratioType,String activityType) {
		//加锁
		synchronized ("updateBenefit") {
			//创建查询条件map
			Map<String, Object> attrs = new HashMap<>();
			//统计时间
			attrs.put("tradeDay", DateHelper.dataToString(DateHelper.getDateByCalculateDays(new Date(), -1), "yyyyMMdd"));
			//获取统计信息
			GjfBenefitIntegralDay benefitDay = gjfBenefitInfoDao.query(GjfBenefitIntegralDay.class, attrs);
			//如果没有统计数据返回提示
			if (ObjValid.isNotValid(benefitDay)) {
				return new ResultVo(400, "发放失败,没有统计数据", null);
			}
			//如果统计数据状态为成功返回提示
			/*if (benefitDay.getTradeStatus().equals("1")) {
				return new ResultVo(400, "发放失败,昨天分红已经发放成功了", null);
			}*/
			//清除attrs
			//如果为会员
			if("0".equals(ratioType)){
				//如果活跃类实际发放金额大于零返回提示
				if("1".equals(activityType)){
					if(benefitDay.getActivityMemberFirstRealIssueMoney().doubleValue()>0){
						return new ResultVo(400,"活跃会员今天分红已经发放请不要重复操作");
					}
					if(benefitDay.getActivityMemberSecondRealIssueMoney().doubleValue()>0){
						return new ResultVo(400,"活跃会员今天分红已经发放请不要重复操作");
					}
					if(benefitDay.getActivityMemberThreeRealIssueMoney().doubleValue()>0){
						return new ResultVo(400,"活跃会员今天分红已经发放请不要重复操作");
					}
				}else {
					//如果非活跃会员实际发放金额
					if(benefitDay.getNoActivityMemberFirstIssueMoney().doubleValue()>0){
						return new ResultVo(400,"非活跃会员今天分红已经发放请不要重复操作");
					}
					//如果非活跃会员实际发放金额
					if(benefitDay.getNoActivityMemberSecondIssueMoney().doubleValue()>0){
						return new ResultVo(400,"非活跃会员今天分红已经发放请不要重复操作");
					}
					//如果非活跃会员实际发放金额
					if(benefitDay.getNoActivityMemberThreeIssueMoney().doubleValue()>0){
						return new ResultVo(400,"非活跃会员今天分红已经发放请不要重复操作");
					}
				}
				
				
				
			}else{
				if("1".equals(activityType)){
					//如果商家实际发放金额大于零
					if(benefitDay.getActivityMerchantRealIssueMoney().doubleValue()>0){
						return new ResultVo(400,"活跃商家今天分红已经发放请不要重复操作");
					}
				}else{
					//如果非活跃商户实际发放金额大于零
					if(benefitDay.getNoActivityMerchantRealIssueMoney().doubleValue()>0){
						return new ResultVo(400,"非活跃商家今天分红已经发放请不要重复操作");
					}
				}				
				
			}
			
			attrs.clear();
			//设置单价类型
			attrs.put("ratioType", ratioType);
			//获取设置单价信息
			GjfBenefitInfo benefitInfo=gjfBenefitInfoDao.query(GjfBenefitInfo.class, attrs);
			//如果设置发放比例信息为空返回提示
			if(!BeanUtil.isValid(benefitInfo)){
				return new ResultVo(400, "发放比例不能为空", null);
			}
			//清除attrs
			attrs.clear();
			//查询池信息
			GjfBenefitPool benefitPool=gjfBenefitInfoDao.query(GjfBenefitPool.class, attrs);
			//如果池信息为空返回提示
			if(!BeanUtil.isValid(benefitPool)){
				return new ResultVo(400,"池信息不存在");
			}
			//如果是会员
			if("0".equals(ratioType)){
				//一类比例
				double firstUnitPrice=0.00;
				//二类比例
				double secondUnitPrice=0.00;
				//三类比例
				double threeUnitPrice=0.00;
				//记录一类总发放金额
				BigDecimal firstTotalIssueMoney=new BigDecimal(0);
				//记录二类总发放金额
				BigDecimal secondTotalIssueMoney=new BigDecimal(0);
				//记录三类总发放金额
				BigDecimal threeTotalIssueMoney=new BigDecimal(0);
				//如果是活跃会员
				if("1".equals(activityType)){
					//如果一类发放比例为空,返回提示
					if(!BeanUtil.isValid(benefitInfo.getUnitPrice())){
						return new ResultVo(400,"活跃会员一类发放比例不能为空");
					}
					//记录一类比例
					firstUnitPrice=benefitInfo.getUnitPrice();
					//如果二类发放比例为空，返回提示
					if(!BeanUtil.isValid(benefitInfo.getActivitySecondUnitPrice())){
						return new ResultVo(400,"活跃会员二类发放比例不能为空");
					}
					//记录二类比例
					secondUnitPrice=benefitInfo.getActivitySecondUnitPrice();
					//如果三类发放比例为空，返回提示
					if(!BeanUtil.isValid(benefitInfo.getActivityThreeUnitPrice())){
						return new ResultVo(400,"活跃会员三类发放比例不能为空");
					}
					//记录三类比例
					threeUnitPrice=benefitInfo.getActivityThreeUnitPrice();
			    //如果为非活跃会员
				}else{
					//如果一类发放比例为空,返回提示
					if(!BeanUtil.isValid(benefitInfo.getActivieRegionUnitPrice())){
						return new ResultVo(400,"非活跃会员一类发放比例不能为空");
					}
					//记录一类比例
					firstUnitPrice=benefitInfo.getActivieRegionUnitPrice();
					//如果二类发放比例为空，返回提示
					if(!BeanUtil.isValid(benefitInfo.getNoActivitySecondUnitPrice())){
						return new ResultVo(400,"非活跃会员二类发放比例不能为空");
					}
					//记录二类比例
					secondUnitPrice=benefitInfo.getNoActivitySecondUnitPrice();
					//如果三类发放比例为空，返回提示
					if(!BeanUtil.isValid(benefitInfo.getNoActivityThreeUnitPrice())){
						return new ResultVo(400,"非活跃会员三类发放比例不能为空");
					}
					//记录三类比例
					threeUnitPrice=benefitInfo.getNoActivityThreeUnitPrice();
				}
				//获取会员信息
				List list=gjfBenefitInfoDao.findMemberMeetIssue("0",activityType);
				
				//记录全部用户分红金额
				BigDecimal allMemberTotalDirectMoney=new BigDecimal(0);
				//如果集合不为空
				if(BeanUtil.isValid(list)){
					//遍历集合
					for(int i=0;i<list.size();i++){
						//获取用户信息
						GjfMemberInfo member=(GjfMemberInfo) list.get(i);
						//如果用户信息为空继续
						if(!BeanUtil.isValid(member)){
							continue;
						}
						//记录总奖励金额
						BigDecimal directTotalMoney=new BigDecimal(0);
						//创建记录分放历史记录对象
						GjfMemberTradeDiviHistory divi=new GjfMemberTradeDiviHistory();
						//用户id
						divi.setMemberId(member);
						//交易单号
						divi.setTradeNo(DateHelper.dataToString(new Date(), "yyyyMMddHHmmss" + RandUtil.getRandomStr(6)));
						//分红权
						divi.setTradeDiviNum(new BigDecimal(0));
						//实际单价
						divi.setTradeRealUnit(new BigDecimal(0));
						//比例
						divi.setTradeRatio(new BigDecimal(0));
						//添加时间
						divi.setAddTime(new Date());
						//交易时间
						divi.setTradeTime(new Date());
						//交易类型
						divi.setTradeType("2");
						//交易状态
						divi.setTradeStatus("1");
						//交易token
						divi.setToken(Sha256.getSha256Hash(divi.getTradeNo(), divi.getTradeStatus(),
				                 CommonStatus.SIGN_KEY_NUM));
						//交易附言
						divi.setTradeTrmo("普通会员分红");
						//是否为活跃
						divi.setIsActivity(activityType);
						//一类积分
						divi.setFirstConsumption(member.getMerchantFirstCumulativeMoney());
						//二类积分
						divi.setSecondConsumption(member.getMerchantSecondCumulativeMoney());
						//三类积分
						divi.setThreeConsumption(member.getMerchantThreeCumulativeMoney());
						//一类交易单价
						divi.setTradeUnit(new BigDecimal(firstUnitPrice));
						//二类交易比例
						divi.setTradeSecondUnit(new BigDecimal(secondUnitPrice));
						//三类交易单价
						divi.setTradeThreeUnit(new BigDecimal(threeUnitPrice));
						//如果一类的总消费金额大于零
			            if(member.getMerchantFirstDiviNum().doubleValue()>=1){
			                //计算发放奖励
			            	BigDecimal firstTradeMoney=member.getMerchantFirstCumulativeMoney().multiply(new BigDecimal(firstUnitPrice)).setScale(2, BigDecimal.ROUND_DOWN);
			            	//如果奖励金额大于待领金额，直接奖励待领金额
			            	if(firstTradeMoney.doubleValue()>member.getMerchantFirstCousumptionMoney().doubleValue()){
			            		firstTradeMoney=member.getMerchantFirstCousumptionMoney();
			            	}
			            	//给会员添加余额
			            	member.setBalanceMoney(member.getBalanceMoney().add(firstTradeMoney));
			            	//可提现金额
			            	member.setWithdrawalMoney(member.getWithdrawalMoney().add(firstTradeMoney));
			            	//减去待领金额
			            	member.setMerchantFirstCousumptionMoney(member.getMerchantFirstCousumptionMoney().subtract(firstTradeMoney));
			            	//记录奖励金额
			            	divi.setTradeMoney(firstTradeMoney);
			            	//总奖励
			            	directTotalMoney=directTotalMoney.add(firstTradeMoney);
			            	//记录一类奖励总金额
			            	firstTotalIssueMoney=firstTotalIssueMoney.add(firstTradeMoney);
			            	
			            	//扣减分红权
			            	updateDeductionDivi(member.getMobile(),"0",firstTradeMoney.doubleValue(),"1");
			            //如果小于零
			            }else{
			            	//记录交易金额
			            	divi.setTradeMoney(new BigDecimal(0));
			            }
			            //如果二类的总消费金额大于500
			            if(member.getMerchantSecondDiviNum().doubleValue()>=1){
			            	//计算发放奖励
			            	BigDecimal secondTradeMoney=member.getMerchantSecondCumulativeMoney().multiply(new BigDecimal(secondUnitPrice)).setScale(2, BigDecimal.ROUND_DOWN);
			            	//给会员添加余额
			            	member.setBalanceMoney(member.getBalanceMoney().add(secondTradeMoney));
			            	//可提现金额
			            	member.setWithdrawalMoney(member.getWithdrawalMoney().add(secondTradeMoney));
			            	//减去待领金额
			            	member.setMerchantSecondCousumptionMoney(member.getMerchantSecondCousumptionMoney().subtract(secondTradeMoney));
			            	//记录奖励金额
			            	divi.setTradeSecondMoney(secondTradeMoney);
			            	//总奖励
			            	directTotalMoney=directTotalMoney.add(secondTradeMoney);
			            	//记录二类奖励总金额
			            	secondTotalIssueMoney=secondTotalIssueMoney.add(secondTradeMoney);
			            	//扣减分红权
			            	updateDeductionDivi(member.getMobile(),"0",secondTradeMoney.doubleValue(),"2");
			            }else{
			            	//记录奖励金额
			            	divi.setTradeSecondMoney(new BigDecimal(0));
			            }
			            //如果三类的总消费金额大于500
			            if(member.getMerchantThreeDiviNum().doubleValue()>=1){
			            	//计算发放奖励金额
			            	BigDecimal threeTradeMoney=member.getMerchantThreeCumulativeMoney().multiply(new BigDecimal(threeUnitPrice)).setScale(2, BigDecimal.ROUND_DOWN);
			            	//给会员添加余额
			            	member.setBalanceMoney(member.getBalanceMoney().add(threeTradeMoney));
			            	//可提现金额
			            	member.setWithdrawalMoney(member.getWithdrawalMoney().add(threeTradeMoney));
			            	//减去待领金额
			            	member.setMerchantThreeCousumptionMoney(member.getMerchantThreeCousumptionMoney().subtract(threeTradeMoney));
			            	//记录奖励金额
			            	divi.setTradeThreeMoney(threeTradeMoney);
			            	//总奖励
			            	directTotalMoney=directTotalMoney.add(threeTradeMoney);
			            	//记录三类总奖励金额
			            	threeTotalIssueMoney=threeTotalIssueMoney.add(threeTradeMoney);
			            	//扣减分红权
			            	updateDeductionDivi(member.getMobile(),"0",threeTradeMoney.doubleValue(),"3");
			            	
			            }else{
			            	//记录奖励金额
			            	divi.setTradeThreeMoney(new BigDecimal(0));
			            }
			            //记录总金额
			            allMemberTotalDirectMoney=allMemberTotalDirectMoney.add(directTotalMoney);
			            //扣减用户总待领金额
			            member.setConsumptionMoney(member.getConsumptionMoney().subtract(directTotalMoney));
			            //总分红金额
			            member.setDividendsTotalMoney(member.getDividendsTotalMoney().add(directTotalMoney));
			            
			            //更新用户信息
			            gjfBenefitInfoDao.update(member);
			            //保存记录
			            gjfBenefitInfoDao.save(divi);
					}
				}
				
				//如果我活跃会员
	            if("1".equals(activityType)){
	                //活跃一类实际发放金额
	            	benefitDay.setActivityMemberFirstRealIssueMoney(firstTotalIssueMoney);
	            	//活跃二类实际发放金额
	            	benefitDay.setActivityMemberSecondRealIssueMoney(secondTotalIssueMoney);
	            	//活跃三类实际发放金额
	            	benefitDay.setActivityMemberThreeRealIssueMoney(threeTotalIssueMoney);
	            	//活跃一类实际发放比例
	            	benefitDay.setActivityMemberFirstUnitMoney(new BigDecimal(benefitInfo.getUnitPrice()));
	            	//活跃二类实际发放比例
	            	benefitDay.setActivtiyMemberSecondUnitMoney(new BigDecimal(benefitInfo.getActivitySecondUnitPrice()));
	            	//活跃三类实际发放比例
	            	benefitDay.setActivityMemberThreeUnitMoney(new BigDecimal(benefitInfo.getActivityThreeUnitPrice()));
	            	
	            //非活跃会员
	            }else{
	               //非活跃一类实际发放金额
	               benefitDay.setNoActivityMemberFirstIssueMoney(firstTotalIssueMoney);
	               //非活跃二类实际发放金额
	               benefitDay.setNoActivityMemberSecondIssueMoney(secondTotalIssueMoney);
	               //非活跃三类实际发放金额
	               benefitDay.setNoActivityMemberThreeIssueMoney(threeTotalIssueMoney);
	               //非活跃一类实际发放比例
	               benefitDay.setNoActivityMemberFirstUnitMoney(new BigDecimal(benefitInfo.getActivieRegionUnitPrice()));
	               //非活跃二类实际发放比例
	               benefitDay.setNoActivityMemberSecondUnitMoney(new BigDecimal(benefitInfo.getNoActivitySecondUnitPrice()));
	               //非活跃三类实际发放比例
	               benefitDay.setNoActivityMemberThreeUnitMoney(new BigDecimal(benefitInfo.getNoActivityThreeUnitPrice()));
	            }
	            //交易状态
	            benefitDay.setTradeStatus("1");
	            //交易时间
	            benefitDay.setTradeTime(new Date());
	            //更新统计数据
	            gjfBenefitInfoDao.update(benefitDay);
	            
	          //扣减池金额
	          benefitPool.setMemberSysPoolCur(benefitPool.getMemberSysPoolCur().subtract(allMemberTotalDirectMoney));
	          //更新池信息
	          gjfBenefitInfoDao.update(benefitPool);
				
			//商家分红
			}else{				
				double firstUnitPrice=0.00;	
				//记录总发放金额
				BigDecimal totalIssueMoney=new BigDecimal(0);
				//如果是活跃商家
				if("1".equals(activityType)){
					//如果一类发放比例为空,返回提示
					if(!BeanUtil.isValid(benefitInfo.getUnitPrice())){
						return new ResultVo(400,"活跃商家发放比例不能为空");
					}
					//记录比例
					firstUnitPrice=benefitInfo.getUnitPrice();
			    //如果为非活跃商家
				}else{
					//如果发放比例为空,返回提示
					if(!BeanUtil.isValid(benefitInfo.getActivieRegionUnitPrice())){
						return new ResultVo(400,"非活跃商户发放比例不能为空");
					}
					//记录比例
					firstUnitPrice=benefitInfo.getActivieRegionUnitPrice();					
				}
				//获取会员商家
				List list=gjfBenefitInfoDao.findMemberMeetIssue("1",activityType);
				
				//如果集合不为空
				if(BeanUtil.isValid(list)){
					//遍历集合
					for(int i=0;i<list.size();i++){
						//获取商户信息
						GjfStoreInfo storeInfo=(GjfStoreInfo) list.get(i);
						//如果商户信息为空，结束循环
						if(!BeanUtil.isValid(storeInfo)){
							continue;
						}
						//创建记录分放历史记录对象
						GjfMemberTradeDiviHistory divi=new GjfMemberTradeDiviHistory();
						//用户id
						divi.setMemberId(storeInfo.getMemberId());
						//交易单号
						divi.setTradeNo(DateHelper.dataToString(new Date(), "yyyyMMddHHmmss" + RandUtil.getRandomStr(6)));
						//分红权
						divi.setTradeDiviNum(new BigDecimal(0));
						//实际单价
						divi.setTradeRealUnit(new BigDecimal(0));
						//比例
						divi.setTradeRatio(new BigDecimal(0));
						//添加时间
						divi.setAddTime(new Date());
						//交易时间
						divi.setTradeTime(new Date());
						//交易类型
						divi.setTradeType("3");
						//交易状态
						divi.setTradeStatus("1");
						//交易token
						divi.setToken(Sha256.getSha256Hash(divi.getTradeNo(), divi.getTradeStatus(),
				                 CommonStatus.SIGN_KEY_NUM));
						//交易附言
						divi.setTradeTrmo("普通商户分红");
						//是否为活跃
						divi.setIsActivity(activityType);
						//一类积分
						divi.setFirstConsumption(storeInfo.getStoreBenefitTotalMoney());
						//二类积分
						divi.setSecondConsumption(new BigDecimal(0));
						//三类积分
						divi.setThreeConsumption(new BigDecimal(0));
						//一类交易单价
						divi.setTradeUnit(new BigDecimal(firstUnitPrice));
						//二类交易比例
						divi.setTradeSecondUnit(new BigDecimal(0));
						//三类交易单价
						divi.setTradeThreeUnit(new BigDecimal(0));
						//获取用户信息
						GjfMemberInfo memberInfo=storeInfo.getMemberId();
						//如果商户的分红权大于1
						if(storeInfo.getStoreDividendsNum().doubleValue()>=1){
							//计算奖励金额
							BigDecimal directMoney=storeInfo.getStoreBenefitTotalMoney().multiply(new BigDecimal(firstUnitPrice)).setScale(2, BigDecimal.ROUND_DOWN);
							//如果待领金额小于奖励金额
							if(directMoney.doubleValue()>storeInfo.getStoreDividendsTotalMoneyBla().doubleValue()){
								//奖励金额为待领金额
								directMoney=storeInfo.getStoreDividendsTotalMoneyBla();
							}
							//记录奖励金额
							divi.setTradeMoney(directMoney);
							
							//给用户添加金额
							memberInfo.setBalanceMoney(memberInfo.getBalanceMoney().add(directMoney));
							//添加提现金额
							memberInfo.setWithdrawalMoney(memberInfo.getWithdrawalMoney().add(directMoney));
							//扣减店铺待领金额
							storeInfo.setStoreDividendsTotalMoneyBla(storeInfo.getStoreDividendsTotalMoneyBla().subtract(directMoney));
							//计算已领金
							storeInfo.setStoreDividendsTotalMoney(storeInfo.getStoreDividendsTotalMoney().add(directMoney));
							//记录总金额
							totalIssueMoney=totalIssueMoney.add(directMoney);
							//扣减分红权
			            	updateDeductionDivi(memberInfo.getMobile(),"1",directMoney.doubleValue(),"0");
						}else {
							//记录奖励金额
							divi.setTradeMoney(new BigDecimal(0));
						}
						
						
						//保存记录
						gjfBenefitInfoDao.save(divi);
						//更新用户信息
						gjfBenefitInfoDao.update(memberInfo);
						//更新店铺信息
						gjfBenefitInfoDao.update(storeInfo);
					}
				}
				
				//如果为活跃用户
				if("1".equals(activityType)){
					//活跃商家实际发放金额
					benefitDay.setActivityMerchantRealIssueMoney(totalIssueMoney);							
					//活跃商家发放比例 
					benefitDay.setActivityMerchantUnitMoney(new BigDecimal(benefitInfo.getUnitPrice()));
					
				}else{
					//非活跃商家发放金额
					benefitDay.setNoActivityMerchantRealIssueMoney(totalIssueMoney);
					//非活跃商家发放比例
					benefitDay.setNoActivityMerchantUnitMoney(new BigDecimal(benefitInfo.getActivieRegionUnitPrice()));
				}
				//更新统计数据
				gjfBenefitInfoDao.update(benefitDay);
				//扣减池金额
				benefitPool.setMerchantSysPoolCur(benefitPool.getMerchantSysPoolCur().subtract(totalIssueMoney));
				//更新池信息
				gjfBenefitInfoDao.save(benefitPool);
			}
			
		}
		return new ResultVo(200, "发放成功", null);
	
	}
	
	/**
	 * 扣减分红权
	 * @param mobile
	 * @param type
	 * @param consumption
	 */
	public void updateDeductionDivi(String mobile,String type,double consumption,String merchantGrade){
		//创建查询条件map
		Map<String, Object> attrs=new HashMap<>();
		//如果是会员
		if("0".equals(type)){
			//用户电话号码
			attrs.put("mobile", mobile);
			//获取用户信息
			GjfMemberInfo member=gjfBenefitInfoDao.query(GjfMemberInfo.class, attrs);
			//如果用户为空返回
			if(!BeanUtil.isValid(member)){
				return;
			}
			//用户消费总积分
			BigDecimal totalCumulative=new BigDecimal(0);
			//如果为一类
			if("1".equals(merchantGrade)){
				//记录一类消费总积分
				totalCumulative=member.getMerchantFirstCumulativeMoney();
			}
			//如果是二类
			if("2".equals(merchantGrade)){
				//记录二类消费积分
				totalCumulative=member.getMerchantSecondCumulativeMoney();
			}
			//如果是三类
			if("3".equals(merchantGrade)){
				//记录三类消费积分
				totalCumulative=member.getMerchantThreeCumulativeMoney();
			}
			
			// 根据用户消费前累计消费金额获取分分红权设置信息
			List<GjfSetDividends> diviList = gjfBenefitInfoDao.findDividendsDate(totalCumulative.doubleValue());
			//如果数据大于零
			if(diviList.size()>0){
				//记录剩余数
				BigDecimal rewardConmuption=new BigDecimal(consumption);
				//记录扣减分红权
				BigDecimal diviNum=new BigDecimal(0);
				// 获取设置信息
				GjfSetDividends dividends = diviList.get(0);
				//如果领回的金额小于设置的最大金额
				if(rewardConmuption.doubleValue()<=dividends.getConsumptionMax().doubleValue()){
					//计算分红权
					BigDecimal num=rewardConmuption.divide(dividends.getConsumption()).setScale(6, BigDecimal.ROUND_DOWN);
					//记录分红权
					diviNum=diviNum.add(num);
				}else{
					// 查看设置的区间的最大消费金额小于消费金额的数据个数
					List<GjfSetDividends> divDateSize = gjfBenefitInfoDao
							.findDividendiByCumulativeMoney(totalCumulative.doubleValue(), "1");
					//如果数据大于零
					if(divDateSize.size()>0){
						//遍历数据
						for(int i=0;i<divDateSize.size();i++){
							//如果领回的金额大于记录的最大金额
							if(rewardConmuption.doubleValue()>divDateSize.get(i).getConsumptionMax().doubleValue()){
								//计算分红权
								BigDecimal num=divDateSize.get(i).getConsumptionMax().divide(divDateSize.get(i).getConsumption()).setScale(6, BigDecimal.ROUND_DOWN);
								//记录扣减分红权
								diviNum=diviNum.add(num);
								//减去扣减积分
								rewardConmuption=rewardConmuption.subtract(divDateSize.get(i).getConsumptionMax());
							}else{					
								//计算分红权
								BigDecimal num=rewardConmuption.divide(dividends.getConsumption()).setScale(6, BigDecimal.ROUND_DOWN);
								//记录分红权
								diviNum=diviNum.add(num);
							}
						}
					}
				}
				
				//创建扣减记录
				GjfMemberDiviNumHistory deDivi=new GjfMemberDiviNumHistory();
				//用户id
				deDivi.setMemberId(member.getId());
				//扣减积分
				deDivi.setDedcutAmount(new BigDecimal(consumption));
				//总积分
				deDivi.setDedcutDiviTotalMoneyBf(totalCumulative);
				//扣减分红权
				deDivi.setDeductDiviNum(diviNum);
				//添加时间
				deDivi.setAddTime(new Date());
				//用户电话
				deDivi.setMemberName(member.getName());
				//用户电话号码
				deDivi.setMemberMobile(member.getMobile());
				//交易状态
				deDivi.setTradeStatus("1");
				//交易类型
				deDivi.setTradeType(merchantGrade);
				//如果为一类
				if("1".equals(merchantGrade)){
					//用户扣减前分红权				
					deDivi.setMemberDiviNumBf(member.getMerchantFirstDiviNum());
					//用户扣减分红权
					member.setMerchantFirstDiviNum(member.getMerchantFirstDiviNum().subtract(diviNum));
					//用户扣减前的分红权
					deDivi.setMemberDiviNumAf(member.getMerchantFirstDiviNum());
					
				}
				//如果为一类
				if("2".equals(merchantGrade)){
					//用户扣减前分红权				
					deDivi.setMemberDiviNumBf(member.getMerchantSecondDiviNum());
					//用户扣减分红权
					member.setMerchantSecondDiviNum(member.getMerchantSecondDiviNum().subtract(diviNum));
					//用户扣减前的分红权
					deDivi.setMemberDiviNumAf(member.getMerchantSecondDiviNum());
					
				}
				//如果为一类
				if("3".equals(merchantGrade)){
					//用户扣减前分红权				
					deDivi.setMemberDiviNumBf(member.getMerchantThreeDiviNum());
					//用户扣减分红权
					member.setMerchantThreeDiviNum(member.getMerchantThreeDiviNum().subtract(diviNum));
					//用户扣减前的分红权
					deDivi.setMemberDiviNumAf(member.getMerchantThreeDiviNum());
					
				}
				//保存记录
				gjfBenefitInfoDao.save(deDivi);
				//更新用户信息
				gjfBenefitInfoDao.update(member);
				
				
			}
			
		//如果是商户	
		}else{
			//用户电话号码
			attrs.put("mobile", mobile);
			//获取用户信息
			GjfMemberInfo member=gjfBenefitInfoDao.query(GjfMemberInfo.class, attrs);
			//如果用户为空返回
			if(!BeanUtil.isValid(member)){
				return;
			}
			//清除attrs
			attrs.clear();
			//用户id
			attrs.put("memberId.id", member.getId());
			//获取店铺信息
			GjfStoreInfo store=gjfBenefitInfoDao.query(GjfStoreInfo.class, attrs);
			//如果店铺不存在返回
			if(!BeanUtil.isValid(store)){
				return;
			}
			//用户消费总积分
			BigDecimal totalCumulative=store.getStoreDividendsTotalMoney();
			
			
			// 根据用户消费前累计消费金额获取分分红权设置信息
			List<GjfSetDividends> diviList = gjfBenefitInfoDao.findDividendsDate(totalCumulative.doubleValue());
			//如果数据大于零
			if(diviList.size()>0){
				//记录剩余数
				BigDecimal rewardConmuption=new BigDecimal(consumption);
				//记录扣减分红权
				BigDecimal diviNum=new BigDecimal(0);
				// 获取设置信息
				GjfSetDividends dividends = diviList.get(0);
				//如果领回的金额小于设置的最大金额
				if(rewardConmuption.doubleValue()<=dividends.getConsumptionMax().doubleValue()){
					//计算分红权
					BigDecimal num=rewardConmuption.divide(dividends.getConsumption()).setScale(6, BigDecimal.ROUND_DOWN);
					//记录分红权
					diviNum=diviNum.add(num);
				}else{
					// 查看设置的区间的最大消费金额小于消费金额的数据个数
					List<GjfSetDividends> divDateSize = gjfBenefitInfoDao
							.findDividendiByCumulativeMoney(totalCumulative.doubleValue(), "1");
					//如果数据大于零
					if(divDateSize.size()>0){
						//遍历数据
						for(int i=0;i<divDateSize.size();i++){
							//如果领回的金额大于记录的最大金额
							if(rewardConmuption.doubleValue()>divDateSize.get(i).getConsumptionMax().doubleValue()){
								//计算分红权
								BigDecimal num=divDateSize.get(i).getConsumptionMax().divide(divDateSize.get(i).getConsumption()).setScale(6, BigDecimal.ROUND_DOWN);
								//记录扣减分红权
								diviNum=diviNum.add(num);
								//减去扣减积分
								rewardConmuption=rewardConmuption.subtract(divDateSize.get(i).getConsumptionMax());
							}else{					
								//计算分红权
								BigDecimal num=rewardConmuption.divide(dividends.getConsumption()).setScale(6, BigDecimal.ROUND_DOWN);
								//记录分红权
								diviNum=diviNum.add(num);
							}
						}
					}
				}
				
				//创建扣减记录
				GjfMemberDiviNumHistory deDivi=new GjfMemberDiviNumHistory();
				//用户id
				deDivi.setMemberId(member.getId());
				//扣减积分
				deDivi.setDedcutAmount(new BigDecimal(consumption));
				//总积分
				deDivi.setDedcutDiviTotalMoneyBf(totalCumulative);
				//扣减分红权
				deDivi.setDeductDiviNum(diviNum);
				//添加时间
				deDivi.setAddTime(new Date());
				//用户电话
				deDivi.setMemberName(member.getName());
				//用户电话号码
				deDivi.setMemberMobile(member.getMobile());
				//交易状态
				deDivi.setTradeStatus("1");
				//交易类型
				deDivi.setTradeType(merchantGrade);
				
				//用户扣减前分红权				
				deDivi.setMemberDiviNumBf(store.getStoreDividendsNum());
				//用户扣减分红权
				store.setStoreDividendsNum(store.getStoreDividendsNum().subtract(diviNum));
				//用户扣减前的分红权
				deDivi.setMemberDiviNumAf(store.getStoreDividendsNum());
				//保存记录
				gjfBenefitInfoDao.save(deDivi);
				//更新店铺信息
				gjfBenefitInfoDao.update(store);
			}
		}
	}

}
