package cc.messcat.web.subsystem;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import cc.messcat.gjfeng.common.bean.Pager;
import cc.messcat.gjfeng.entity.GjfBenefitPool;
import cc.messcat.gjfeng.entity.GjfMemberInfo;
import cc.messcat.gjfeng.entity.GjfOrderInfo;
import cc.messcat.gjfeng.entity.GjfStoreInfo;
import cc.modules.commons.PageAction;

public class CountInfoAction extends PageAction{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String token;
	private String op;
	private String name;
	private String nickName;
	private String type;
	private BigInteger memberAmount;//会员数量
	private BigInteger sellerMemberNum; //商家会员数量
	private BigInteger regularMemberNum; //普通会员数量
	private List<GjfOrderInfo> orderInfos;
	private List<GjfMemberInfo> memberInfos;
	private List<GjfStoreInfo> gjfStoreInfos;
	private String startTime;
	private String endTime;
	private Long pri;
	private Long city;
	private Long area;
	private GjfBenefitPool gjfBenefitPool; //资金池
	private BigInteger[] memberAdd; //会员增加总数（前端展示趋势图）
	private BigInteger[] regularMemberAdd; //普通会员增加总数
	private BigInteger[] sellerAdd; //商户增加总数
	private BigDecimal[] memberMonthsTurnover; //会员近三月的消费趋势
	private BigDecimal[] memberMoney; //会员分红趋势
	private BigDecimal[] merchantsMoney; //商家分红趋势
	private BigDecimal[] memberRealMoney; //会员实际分红趋势
	private BigDecimal[] merchantsRealMoney; //商家实际分红趋势
	
	private BigDecimal memberPoolIn; //会员池收入
	private BigDecimal memberPoolOut; //会员池支出
	private BigDecimal storePoolIn; //商户池收入
	private BigDecimal storePoolOut; //商户池支出
	private BigDecimal platFormIn; //平台池收入
	private BigDecimal platFormTex; //提现手续费
	private BigDecimal agentCityPoolIn; //市代池收入
	private BigDecimal agentCityPoolOut; //市代池支出
	private BigDecimal agentAreaPoolIn; //区代池收入
	private BigDecimal agentAreaPoolOut; //区代池支出
	private BigDecimal agentIndiPoolIn; //个代池收入
	private BigDecimal agentIndiPoolOut; //个代池支出
	
	private String storeName;
	private String storeNum;
	private String memberName;
	
	private String priValue;
	private String cityValue;
	private String areaValue;
	private String addTime;
	private String orderType;
	private String mobile;
	private String [] date;
	private BigDecimal performance;
	private Object object ;
	private String ste;
	
	/**
	 * 统计会员（商家）数量，消费额度
	 * @return
	 */
	public String countMemberInfoAmount(){
		try {
			resultVo = countInfoDubbo.findMemberAmount(pageNo,pageSize);
			pager = (Pager) resultVo.getResult();
			if (op.equals("countMembers")) {
				memberAmount = (BigInteger) countInfoDubbo.findMemberAmountByType("").getResult();
				//memberAdd = (BigInteger[]) countInfoDubbo.findAlmostOneMonthMemberAdd("").getResult();
				//regularMemberAdd = (BigInteger[]) countInfoDubbo.findAlmostOneMonthMemberAdd("0").getResult();
				//sellerAdd = (BigInteger[]) countInfoDubbo.findAlmostOneMonthMemberAdd("1").getResult();
				regularMemberNum =  (BigInteger) countInfoDubbo.findMemberAmountByType("0").getResult();
				sellerMemberNum =  (BigInteger) countInfoDubbo.findMemberAmountByType("1").getResult();
				return "toCountMember";
			}else if (op.equals("membersConsume")) {
				return "toMembersConsume";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return null;
	}
	
	/**
	 * 会员模糊查询
	 * @return
	 */
	public String retrieveMemberByCondition(){
		try {
			resultVo = memberInfoDubbo.findMembersByCondition(pageNo, pageSize, name, nickName,null, type);
			pager = (Pager) resultVo.getResult();
			if ("1".equals(op)) {
				return "balance_list";
			}else if ("2".equals(op)) {
				return "withdrawAmount_list";
			}else if ("3".equals(op)) {
				return "diviNum_list";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "toMembersConsume";
	}
	
	/**
	 * 会员消费记录（来源：已经成功收货的订单并且没有退货）
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findMemberConsumeHistory(){
		resultVo = orderInfoDubbo.findOrderByMemberId(pageNo,pageSize,id,startTime,endTime);
		memberMonthsTurnover = (BigDecimal[]) countInfoDubbo.findAlmostThreeMonthMemberTurnover(id).getResult();
		pager = (Pager) resultVo.getResult();
		orderInfos = pager.getResultList();
		return "toMemberConsumeRecord";
	}

	
	/**统计所有会员下级的总消费额
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String countMembersLowLevelConsume(){
		try {
			resultVo = countInfoDubbo.findMembersLowLevelConsume(pageNo, pageSize);
			pager = (Pager) resultVo.getResult();
			memberInfos = pager.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "toMembersLowLevelConsume";
	}
	
	/**
	 * 统计会员的下级总消费额
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findMemberLowLevelConsume(){
		try {
			resultVo = memberInfoDubbo.findLowLevelByMemberId(pageNo,pageSize,id);
			pager  =  (Pager) resultVo.getResult();
			memberInfos = pager.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "toMemberLowLevelConsume";
	}
	
	
	
	/**
	 * 统计商家让利金额
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String countStoresBenefit(){
		try {
			resultVo = storeInfoDubbo.findStoreByCondition(pageNo, pageSize, pri, city, area);
			pager  =  (Pager) resultVo.getResult();
			gjfStoreInfos = pager.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "toSellersBenefit";
	}
	
	/**
	 * 查找商家让利明细
	 * @return
	 */
	public String findBenefitDetail(){
		try {
			resultVo = tradeDubbo.findBenefitByTime(pageNo, pageSize, id, startTime, endTime);
			pager = (Pager) resultVo.getResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "toSellerBenefitDetail";
	}	
	
	/**
	 * 根据条件查询区域业绩
	 * @return
	 */
	public String countPerformanceByCondition(){
		try {
			resultVo = countInfoDubbo.findRegionalPerformance(pageNo, pageSize, pri, city, area,startTime,endTime);
			pager = (Pager) resultVo.getResult();
			this.performance = (BigDecimal) countInfoDubbo.findCountRegionalPerformance(pri, city, area, startTime, endTime).getResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "toRegionPerformamce";
	}
	
	
	/**
	 * 查询店铺O2O订单
	 * @return
	 */
	public String findOrderByCondition(){
		try {
			resultVo = orderInfoDubbo.findOrderByCondition(pageNo, pageSize, id,token, startTime, endTime);
			pager  =  (Pager) resultVo.getResult();
			List list = (List) orderInfoDubbo.findCountOrderByCondition(id, token, startTime, endTime).getResult();
			this.object = list.get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "store_orders";
	}
	
	
	/**
	 * 资金池
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String countBenefitPool(){
		try {
			gjfBenefitPool = (GjfBenefitPool) countInfoDubbo.findBenefitPool().getResult();
			resultVo = countInfoDubbo.findBenefitHistory(pageNo, pageSize);
			pager = (Pager) resultVo.getResult();
			Map<String, BigDecimal> map = (Map<String, BigDecimal>) countInfoDubbo.findCashPool().getResult();
			this.memberPoolIn = map.get("8");
			this.memberPoolOut = map.get("0");
			this.storePoolIn = map.get("9");
			this.storePoolOut = map.get("1");
			this.platFormIn = map.get("10");
			this.platFormTex = map.get("16");
			this.agentCityPoolIn = map.get("13");
			this.agentCityPoolOut = map.get("5");
			this.agentAreaPoolIn = map.get("14");
			this.agentAreaPoolOut = map.get("6");
			this.agentIndiPoolIn = map.get("15");
			this.agentIndiPoolOut = map.get("7");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "benefitPool";
	}
	
	/**
	 * 分红数据展示
	 * @return
	 */
	public String diviDataList(){
		try {
			resultVo = countInfoDubbo.findDiviData();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "divi_manage";
	}
	
	/**
	 * 查找短信发送记录
	 * @return
	 */
	public String findMessageHistory(){
		try {
			resultVo = countInfoDubbo.findMessageHistory(pageNo, pageSize);
			pager = (Pager) resultVo.getResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "message_history";
	}
	
	/**
	 * 总报表
	 * @return
	 */
	public String toSummaryReport(){
		try {
			resultVo = countInfoDubbo.findSummaryReport(pageNo, pageSize,addTime,endTime,ste);
			pager = (Pager) resultVo.getResult();
			if("1".equals(ste)){ //导出操作
				request=ServletActionContext.getRequest();//获取请求对象;
				return "summaryReport_export";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "summary_report";
	}
	
	/**
	 * 让利日报表
	 */
	public String toBenefitReport(){
		try {
			resultVo = countInfoDubbo.findBenefitReport(pageNo, pageSize,addTime,endTime,ste);
			pager = (Pager) resultVo.getResult();
			if("1".equals(ste)){ //导出操作
				request=ServletActionContext.getRequest();//获取请求对象;
				return "benefitReport_export";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "benefit_report";
	}
	
	/**
	 * 福利产出日报表
	 * @return
	 */
	public String toWealOutputReport(){
		try {
			resultVo = countInfoDubbo.findWealOutputReport(pageNo, pageSize,addTime,endTime,ste);
			pager = (Pager) resultVo.getResult();
			if("1".equals(ste)){ //导出操作
				request=ServletActionContext.getRequest();//获取请求对象;
				return "wealOutPutReport_export";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "wealOutput_report";
	}
	
	/**
	 * 福利派发报表
	 * @return
	 */
	public String toWealPayoutReport(){
		try {
			resultVo = countInfoDubbo.findWealPayoutReport(pageNo, pageSize,addTime,endTime,ste);
			pager = (Pager) resultVo.getResult();
			if("1".equals(ste)){ //导出操作
				request=ServletActionContext.getRequest();//获取请求对象;
				return "walPayOutReport_export";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "wealPayout_report";
	}
	
	/**
	 * 提现日报表
	 * @return
	 */
	public String toWithdrawReport(){
		try {
			resultVo = countInfoDubbo.findWithdrawReport(pageNo, pageSize,addTime,endTime,ste);
			pager = (Pager) resultVo.getResult();
			if("1".equals(ste)){ //导出操作
				request=ServletActionContext.getRequest();//获取请求对象;
				return "withdrawReport_export";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "withdraw_report";
	}
	
	/**
	 * 授信充值额
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String toCreditLineRecharge(){
		try {
			resultVo = countInfoDubbo.findCreditLineRecharge(pageNo,pageSize);
			Map<String, Object> result = (Map<String, Object>) resultVo.getResult();
			pager = (Pager) result.get("pager");
			this.date = getAlmostOneMonthDate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "toCreditLineRecharge";
	}
	
	/**
	 * 商户未使用授信
	 * @return
	 */
	public String storeCreditLine(){
		try {
			resultVo = countInfoDubbo.findStoreCreditLine(storeNum,storeName,memberName,pageNo,pageSize,orderType);
			pager = (Pager) resultVo.getResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "toStoreCreditLine";
	}
	
	/**
	 * 查询错误信息
	 * @return
	 */
	public String findErrorMsg(){
		try {
			resultVo = countInfoDubbo.findErrorMsg(pageNo, pageSize);
			pager = (Pager) resultVo.getResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "errorMsg_list";
	}
	
	/**
	 * 分红趋势
	 * @return
	 */
	public String benefitDayTrend(){
		try {
			memberMoney = (BigDecimal[]) countInfoDubbo.findAlmostOneMonthBenefitDay("1").getResult();
			merchantsMoney = (BigDecimal[]) countInfoDubbo.findAlmostOneMonthBenefitDay("2").getResult();
			memberRealMoney = (BigDecimal[]) countInfoDubbo.findAlmostOneMonthBenefitDay("3").getResult();
			merchantsRealMoney = (BigDecimal[]) countInfoDubbo.findAlmostOneMonthBenefitDay("4").getResult();
			this.date = getAlmostOneMonthDate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "benefitDayTrend";
	}
	
	/**
	 * 查询用户授信流水
	 * @return
	 */
	public String getAllShouXin(){
		try {
			resultVo = tradeDubbo.getAllShouXin(pageNo, pageSize, mobile);
			pager = (Pager) resultVo.getResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "AllShouXin";
	}
	
	/**
	 * 今日O2O订单
	 * @return
	 */
	public String findTodayO2OOrders(){
		try {
			resultVo = countInfoDubbo.findTodayO2OOrders(pageNo, pageSize);
			pager = (Pager) resultVo.getResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "todayO2OOrders";
	}
	
	private String[] getAlmostOneMonthDate(){
		int n = 30;
		String [] date = new String[30];
		Calendar now = Calendar.getInstance();
		for (int i = 1; i <= date.length; i++) {
			now.add(Calendar.DATE, -1);
			String endDate = new SimpleDateFormat("yyyy-MM-dd").format(now.getTime());
			date[n-i] = endDate;
		}
		return date;
	}
	
	/**
	 * 池收入
	 * @return
	 */
	public String findPoolIn(){
		try {
			resultVo = countInfoDubbo.findPoolIn(pageNo, pageSize, op, type);
			pager = (Pager) resultVo.getResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "poolIncome";
	}
	
	/**
	 * 当天池收入或支出明细
	 * @return
	 */
	public String findPoolInDetail(){
		try {
			resultVo = countInfoDubbo.findPoolDetail(pageNo, pageSize, addTime, type);
			pager = (Pager) resultVo.getResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "poolDetail";
	}
	
	/**
	 * 池支出
	 * @return
	 */
	public String findPoolOut(){
		try {
			resultVo = countInfoDubbo.findPoolOut(pageNo, pageSize, op, type);
			pager = (Pager) resultVo.getResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "poolOut";
	}
	
	/**
	 * 代理分红支出
	 * @return
	 */
	public String findTradeDiviByAgent(){
		try {
			resultVo = countInfoDubbo.findTradeDiviByAgent(pageNo, pageSize, addTime, type);
			pager = (Pager) resultVo.getResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "trade_divi_agent";
	}
	
	
	/**
	 * 统计报表
	 * @return
	 */
	public String countTotalDataByTime(){
		try{
			resultVo=countInfoDubbo.countTotalDataByTime(pageNo, pageSize, addTime, endTime);
			pager = (Pager) resultVo.getResult();
			
			resultVo=countInfoDubbo.countTotalDataInfo(addTime, endTime);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "count_total";
	}
	/**
	 * 更新统计信息
	 * @param id
	 * @return
	 */
	public String upateCountInfo(){
		try{
			countInfoDubbo.updateCouneInf(id);
		}catch(Exception e){
			e.printStackTrace();
		}
		return countTotalDataByTime();
	}
		
	public List<GjfMemberInfo> getMemberInfos() {
		return memberInfos;
	}

	public void setMemberInfos(List<GjfMemberInfo> memberInfos) {
		this.memberInfos = memberInfos;
	}


	public BigInteger getSellerMemberNum() {
		return sellerMemberNum;
	}

	public void setSellerMemberNum(BigInteger sellerMemberNum) {
		this.sellerMemberNum = sellerMemberNum;
	}

	public BigInteger getRegularMemberNum() {
		return regularMemberNum;
	}

	public void setRegularMemberNum(BigInteger regularMemberNum) {
		this.regularMemberNum = regularMemberNum;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<GjfOrderInfo> getOrderInfos() {
		return orderInfos;
	}

	public void setOrderInfos(List<GjfOrderInfo> orderInfos) {
		this.orderInfos = orderInfos;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Long getPri() {
		return pri;
	}

	public void setPri(Long pri) {
		this.pri = pri;
	}

	public Long getCity() {
		return city;
	}

	public void setCity(Long city) {
		this.city = city;
	}

	public Long getArea() {
		return area;
	}

	public void setArea(Long area) {
		this.area = area;
	}

	public List<GjfStoreInfo> getGjfStoreInfos() {
		return gjfStoreInfos;
	}

	public void setGjfStoreInfos(List<GjfStoreInfo> gjfStoreInfos) {
		this.gjfStoreInfos = gjfStoreInfos;
	}

	public GjfBenefitPool getGjfBenefitPool() {
		return gjfBenefitPool;
	}

	public void setGjfBenefitPool(GjfBenefitPool gjfBenefitPool) {
		this.gjfBenefitPool = gjfBenefitPool;
	}

	public BigInteger[] getMemberAdd() {
		return memberAdd;
	}

	public void setMemberAdd(BigInteger[] memberAdd) {
		this.memberAdd = memberAdd;
	}

	public BigInteger[] getRegularMemberAdd() {
		return regularMemberAdd;
	}

	public void setRegularMemberAdd(BigInteger[] regularMemberAdd) {
		this.regularMemberAdd = regularMemberAdd;
	}

	public BigInteger[] getSellerAdd() {
		return sellerAdd;
	}

	public void setSellerAdd(BigInteger[] sellerAdd) {
		this.sellerAdd = sellerAdd;
	}

	public BigDecimal[] getMemberMonthsTurnover() {
		return memberMonthsTurnover;
	}

	public void setMemberMonthsTurnover(BigDecimal[] memberMonthsTurnover) {
		this.memberMonthsTurnover = memberMonthsTurnover;
	}

	public BigDecimal getMemberPoolIn() {
		return memberPoolIn;
	}

	public void setMemberPoolIn(BigDecimal memberPoolIn) {
		this.memberPoolIn = memberPoolIn;
	}

	public BigDecimal getMemberPoolOut() {
		return memberPoolOut;
	}

	public void setMemberPoolOut(BigDecimal memberPoolOut) {
		this.memberPoolOut = memberPoolOut;
	}

	public BigDecimal getStorePoolIn() {
		return storePoolIn;
	}

	public void setStorePoolIn(BigDecimal storePoolIn) {
		this.storePoolIn = storePoolIn;
	}

	public BigDecimal getStorePoolOut() {
		return storePoolOut;
	}

	public void setStorePoolOut(BigDecimal storePoolOut) {
		this.storePoolOut = storePoolOut;
	}

	public BigDecimal getPlatFormIn() {
		return platFormIn;
	}

	public void setPlatFormIn(BigDecimal platFormIn) {
		this.platFormIn = platFormIn;
	}

	public BigDecimal getPlatFormTex() {
		return platFormTex;
	}

	public void setPlatFormTex(BigDecimal platFormTex) {
		this.platFormTex = platFormTex;
	}

	public BigDecimal getAgentCityPoolIn() {
		return agentCityPoolIn;
	}

	public void setAgentCityPoolIn(BigDecimal agentCityPoolIn) {
		this.agentCityPoolIn = agentCityPoolIn;
	}

	public BigDecimal getAgentCityPoolOut() {
		return agentCityPoolOut;
	}

	public void setAgentCityPoolOut(BigDecimal agentCityPoolOut) {
		this.agentCityPoolOut = agentCityPoolOut;
	}

	public BigDecimal getAgentAreaPoolIn() {
		return agentAreaPoolIn;
	}

	public void setAgentAreaPoolIn(BigDecimal agentAreaPoolIn) {
		this.agentAreaPoolIn = agentAreaPoolIn;
	}

	public BigDecimal getAgentAreaPoolOut() {
		return agentAreaPoolOut;
	}

	public void setAgentAreaPoolOut(BigDecimal agentAreaPoolOut) {
		this.agentAreaPoolOut = agentAreaPoolOut;
	}

	public BigDecimal getAgentIndiPoolIn() {
		return agentIndiPoolIn;
	}

	public void setAgentIndiPoolIn(BigDecimal agentIndiPoolIn) {
		this.agentIndiPoolIn = agentIndiPoolIn;
	}

	public BigDecimal getAgentIndiPoolOut() {
		return agentIndiPoolOut;
	}

	public void setAgentIndiPoolOut(BigDecimal agentIndiPoolOut) {
		this.agentIndiPoolOut = agentIndiPoolOut;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreNum() {
		return storeNum;
	}

	public void setStoreNum(String storeNum) {
		this.storeNum = storeNum;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getPriValue() {
		return priValue;
	}

	public void setPriValue(String priValue) {
		this.priValue = priValue;
	}

	public String getCityValue() {
		return cityValue;
	}

	public void setCityValue(String cityValue) {
		this.cityValue = cityValue;
	}

	public String getAreaValue() {
		return areaValue;
	}

	public void setAreaValue(String areaValue) {
		this.areaValue = areaValue;
	}

	public BigDecimal[] getMemberMoney() {
		return memberMoney;
	}

	public void setMemberMoney(BigDecimal[] memberMoney) {
		this.memberMoney = memberMoney;
	}

	public BigDecimal[] getMerchantsMoney() {
		return merchantsMoney;
	}

	public void setMerchantsMoney(BigDecimal[] merchantsMoney) {
		this.merchantsMoney = merchantsMoney;
	}

	public BigDecimal[] getMemberRealMoney() {
		return memberRealMoney;
	}

	public void setMemberRealMoney(BigDecimal[] memberRealMoney) {
		this.memberRealMoney = memberRealMoney;
	}

	public BigDecimal[] getMerchantsRealMoney() {
		return merchantsRealMoney;
	}

	public void setMerchantsRealMoney(BigDecimal[] merchantsRealMoney) {
		this.merchantsRealMoney = merchantsRealMoney;
	}

	public BigInteger getMemberAmount() {
		return memberAmount;
	}

	public void setMemberAmount(BigInteger memberAmount) {
		this.memberAmount = memberAmount;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String[] getDate() {
		return date;
	}

	public void setDate(String[] date) {
		this.date = date;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public BigDecimal getPerformance() {
		return performance;
	}

	public void setPerformance(BigDecimal performance) {
		this.performance = performance;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public String getSte() {
		return ste;
	}

	public void setSte(String ste) {
		this.ste = ste;
	}

	
	



	
	
	
}
