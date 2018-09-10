package cc.messcat.web.subsystem;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import cc.messcat.gjfeng.common.bean.Pager;
import cc.messcat.gjfeng.common.exception.LoggerPrint;
import cc.messcat.gjfeng.common.util.BeanUtil;
import cc.messcat.gjfeng.common.vo.app.ResultVo;
import cc.messcat.gjfeng.entity.GjfBenefitHistory;
import cc.messcat.gjfeng.entity.GjfBenefitInfo;
import cc.modules.commons.PageAction;
import net.sf.json.JSONObject;

public class BenefitInfoAction extends PageAction  {

	private static final long serialVersionUID = 1L;
	
	private List<GjfBenefitInfo> gjfBenefitInfos;
	private String type;
	private String msg;
	private BigDecimal memberSysPoolCur; //会员实时池
	private BigDecimal merchantSysPoolCur; //商户实时池
	private BigDecimal merchantDiviOnTime; //会员分红实时余额
	private BigDecimal memberDiviOntime; //商户分红实时余额
	private Double memberSysRatio; //会员占比
	private Double merchantSysRatio; //商户占比
	private BigDecimal memberDiviPrice; //用户活跃区分红单价

	private BigDecimal merchantDiviPrice;  //商户分红单价
	private Double  memberUnitPrice;
	private Double merchantUnitPrice;
	private Double memberActivityRegionPrice;//用户非活跃区分红单价
	private Double merchantActivityRegionPrice;//商户非活跃区分红单价
	private String addTime;
	
	private String mobile;
	private String activityType;
	
	private GjfBenefitInfo memBenInfo;//会员占比信息
	private GjfBenefitInfo merchentBenInfo;//商户占比信息
	
	
	private Long id;
	
	private List<GjfBenefitHistory> gjfBenefitHistories;
	
	/**
	 * 查询所有让利
	 */
	public String execute(){
		try {
			gjfBenefitInfos=benefitInfoDubbo.findAlls();
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, BenefitInfoAction.class);
		}
		return SUCCESS;
	}
	
	/**
	 * 修改让利比例
	 */
	public String update(){
		try{
			resultVo=benefitInfoDubbo.updateBenefitInfo(gjfBenefitInfos);
		}catch(Exception e){
			resultVo = LoggerPrint.getResult(e, BenefitInfoAction.class);
		}finally {
			msg=resultVo.getMsg();
		}
		return SUCCESS;
	}
	
	/**
	 * 手动发放分红
	 */
	public String updateBenefit(){
		JSONObject json = new JSONObject();
		try{
			resultVo=benefitInfoDubbo.updateBenefit(type,activityType);
		}catch(Exception e){
			resultVo = LoggerPrint.getResult(e, BenefitInfoAction.class);
		}
		json = JSONObject.fromObject(resultVo);
		return renderText(json == null ? null : json.toString());
	}
	
	/**
	 * 分红金额设置
	 * @return
	 */
	public String toDividentAmountSetting(){
		try {
			//所有占比配置
			gjfBenefitInfos=benefitInfoDubbo.findAlls();
			//今日会员池收入
			this.memberSysPoolCur = (BigDecimal) benefitInfoDubbo.findTodayRealTimePool("0").getResult();
			//今日商户池收入
			this.merchantSysPoolCur = (BigDecimal) benefitInfoDubbo.findTodayRealTimePool("1").getResult();
			//获取会员占比信息
			memBenInfo= benefitInfoDubbo.findByType("0");
			//获取商家占比信息
			merchentBenInfo = benefitInfoDubbo.findByType("1");
			
			//this.merchantDiviOnTime = (BigDecimal) countInfoDubbo.findOntimeDiviAmount("1").getResult();
			//this.memberDiviOntime = (BigDecimal) countInfoDubbo.findOntimeDiviAmount("0").getResult();
			//this.memberDiviPrice = memberSysPoolCur.divide(memberDiviOntime,2, BigDecimal.ROUND_DOWN);
			//this.merchantDiviPrice = merchantSysPoolCur.divide(merchantDiviOnTime,2, BigDecimal.ROUND_DOWN);
			//获取每天统计信息
			resultVo = benefitInfoDubbo.findBenefitDayByPage(pageNo, pageSize);
			pager = (Pager) resultVo.getResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "divi_amount_setting";
	}
	
	/**
	 * 每日分红信息
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findBenefitHistoryByTime(){
		try {
			resultVo = benefitInfoDubbo.findBenefitHistoryByTime(addTime);
			gjfBenefitHistories = (List<GjfBenefitHistory>) resultVo.getResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "benefit_day";
	}
	
	
	/**
	 * 跳转测试控制面板
	 * @return
	 */
	public String toTestControl(){
		return "testControl";
	}
	
	/**
	 * 分红池统计
	 * @return
	 */
	public String diviCount(){		
		JSONObject json = new JSONObject();
		try {
			resultVo = benefitInfoDubbo.updateMemberBenefitNotify();
			json = JSONObject.fromObject(resultVo);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			json = JSONObject.fromObject(new ResultVo(400, "统计出错", null));
		} catch (SQLException e) {
			e.printStackTrace();
			json = JSONObject.fromObject(new ResultVo(400, "统计出错", null));
		}
	    return renderText(json == null ? null : json.toString());
	}
	
	/**
	 * 分红池发放
	 * @return
	 */
	public String diviGrant(){
		JSONObject json = new JSONObject();
	    try {
			resultVo = benefitInfoDubbo.updateBenefit(type,activityType);
	    	json = JSONObject.fromObject(resultVo);
		} catch (Exception e) {
			e.printStackTrace();
			json = JSONObject.fromObject(new ResultVo(400, "发放出错", null));
		}
	    return renderText(json == null ? null : json.toString());
	}
	
	/**
	 * 代理统计
	 * @return
	 */
	public String agentCount(){
		JSONObject json = new JSONObject();
	    
		try {
			resultVo = benefitInfoDubbo.updateAgentBenefitByDayNotify();
			json = JSONObject.fromObject(resultVo);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			json = JSONObject.fromObject(new ResultVo(400, "统计出错", null));
		} catch (SQLException e) {
			e.printStackTrace();
			json = JSONObject.fromObject(new ResultVo(400, "统计出错", null));
		}
	    return renderText(json == null ? null : json.toString());
	}
	
	
	/**
	 * @描述 添加统计测试
	 * @author Karhs
	 * @date 2017年2月25日
	 * @return
	 */
	public String updateTestAct(){
		if (type.equals("1")) {
			resultVo = countInfoDubbo.updateSummaryReport();		//统计总报表
		}else if (type.equals("2")) {
			resultVo = countInfoDubbo.updateBenefitReport();		//统计让利日报表
		}else if (type.equals("3")) {
			resultVo = countInfoDubbo.updateWealOutputReport();		//统计福利产出日报表
		}else if (type.equals("4")) {
			resultVo = countInfoDubbo.updateWealPayoutReport();		//统计福利派发报表
		}else if (type.equals("5")) {
			resultVo = countInfoDubbo.updateWithdrawReport();		//统计提现日报表
		}else if (type.equals("6")) {
			resultVo = countInfoDubbo.updatePoolReport();		
		}
		JSONObject json = new JSONObject();
		json = JSONObject.fromObject(resultVo);
		return renderText(json == null ? null : json.toString());
	}
	
	
	/**
	 * 分红金额设置
	 * @return
	 */
	public String toSpecialDividentAmountSetting(){
		try {
			gjfBenefitInfos=benefitInfoDubbo.findAlls();
			this.memberSysPoolCur = (BigDecimal) benefitInfoDubbo.findTodayRealTimePool("0").getResult();
			this.merchantSysPoolCur = (BigDecimal) benefitInfoDubbo.findTodayRealTimePool("1").getResult();
			GjfBenefitInfo gjfBenefitInfo = benefitInfoDubbo.findByType("0");
			memberSysRatio = gjfBenefitInfo.getSysRatio();
			memberUnitPrice = gjfBenefitInfo.getUnitPrice();
			GjfBenefitInfo gjfBenefitInfo2 = benefitInfoDubbo.findByType("1");
			merchantSysRatio = gjfBenefitInfo2.getSysRatio();
			merchantUnitPrice = gjfBenefitInfo2.getUnitPrice();
			this.merchantDiviOnTime = (BigDecimal) countInfoDubbo.findOntimeDiviAmount("1").getResult();
			this.memberDiviOntime = (BigDecimal) countInfoDubbo.findOntimeDiviAmount("0").getResult();
			this.memberDiviPrice = memberSysPoolCur.divide(memberDiviOntime,2, BigDecimal.ROUND_DOWN);
			this.merchantDiviPrice = merchantSysPoolCur.divide(merchantDiviOnTime,2, BigDecimal.ROUND_DOWN);
			resultVo = tradeDubbo.findSpecialTotalHistory(pageNo, pageSize);
			pager = (Pager) resultVo.getResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "special_divi_amount_setting";
	}
	
	/**
	 * 获取用户信息
	 * @return
	 */
	public String getMemberInfo(){
		JSONObject json = new JSONObject();
	    
		try {
			resultVo = memberInfoDubbo.findMemberByMobile(mobile);
			if(!BeanUtil.isValid(resultVo.getResult())){
				resultVo=new ResultVo(400, "用户不存在", resultVo);
			}
			json = JSONObject.fromObject(resultVo);
		}catch(Exception e){
			e.printStackTrace();
		}
	    return renderText(json == null ? null : json.toString());
	}
	
	/**
	 * 获取特殊发放人交易记录
	 * @return
	 */
	public String findSpecialMemTradeHistory(){
		try{
			pager=(Pager) tradeDubbo.findSpecialMemTradeDiviHistory(pageNo, pageSize, addTime, id,type).getResult();
		}catch(Exception e){
			e.printStackTrace();
		}
		return "spcail_trade_history";
	}
	
	
	public List<GjfBenefitInfo> getGjfBenefitInfos() {
		return gjfBenefitInfos;
	}

	public void setGjfBenefitInfos(List<GjfBenefitInfo> gjfBenefitInfos) {
		this.gjfBenefitInfos = gjfBenefitInfos;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public BigDecimal getMemberSysPoolCur() {
		return memberSysPoolCur;
	}

	public void setMemberSysPoolCur(BigDecimal memberSysPoolCur) {
		this.memberSysPoolCur = memberSysPoolCur;
	}

	public BigDecimal getMerchantSysPoolCur() {
		return merchantSysPoolCur;
	}

	public void setMerchantSysPoolCur(BigDecimal merchantSysPoolCur) {
		this.merchantSysPoolCur = merchantSysPoolCur;
	}

	public BigDecimal getMemberDiviOntime() {
		return memberDiviOntime;
	}

	public void setMemberDiviOntime(BigDecimal memberDiviOntime) {
		this.memberDiviOntime = memberDiviOntime;
	}

	public Double getMemberSysRatio() {
		return memberSysRatio;
	}

	public void setMemberSysRatio(Double memberSysRatio) {
		this.memberSysRatio = memberSysRatio;
	}

	public Double getMerchantSysRatio() {
		return merchantSysRatio;
	}

	public void setMerchantSysRatio(Double merchantSysRatio) {
		this.merchantSysRatio = merchantSysRatio;
	}

	public BigDecimal getMemberDiviPrice() {
		return memberDiviPrice;
	}

	public void setMemberDiviPrice(BigDecimal memberDiviPrice) {
		this.memberDiviPrice = memberDiviPrice;
	}

	public BigDecimal getMerchantDiviOnTime() {
		return merchantDiviOnTime;
	}

	public void setMerchantDiviOnTime(BigDecimal merchantDiviOnTime) {
		this.merchantDiviOnTime = merchantDiviOnTime;
	}

	public BigDecimal getMerchantDiviPrice() {
		return merchantDiviPrice;
	}

	public void setMerchantDiviPrice(BigDecimal merchantDiviPrice) {
		this.merchantDiviPrice = merchantDiviPrice;
	}

	public Double getMemberUnitPrice() {
		return memberUnitPrice;
	}

	public void setMemberUnitPrice(Double memberUnitPrice) {
		this.memberUnitPrice = memberUnitPrice;
	}

	public Double getMerchantUnitPrice() {
		return merchantUnitPrice;
	}

	public void setMerchantUnitPrice(Double merchantUnitPrice) {
		this.merchantUnitPrice = merchantUnitPrice;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public List<GjfBenefitHistory> getGjfBenefitHistories() {
		return gjfBenefitHistories;
	}

	public void setGjfBenefitHistories(List<GjfBenefitHistory> gjfBenefitHistories) {
		this.gjfBenefitHistories = gjfBenefitHistories;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getMemberActivityRegionPrice() {
		return memberActivityRegionPrice;
	}

	public void setMemberActivityRegionPrice(Double memberActivityRegionPrice) {
		this.memberActivityRegionPrice = memberActivityRegionPrice;
	}

	public Double getMerchantActivityRegionPrice() {
		return merchantActivityRegionPrice;
	}

	public void setMerchantActivityRegionPrice(Double merchantActivityRegionPrice) {
		this.merchantActivityRegionPrice = merchantActivityRegionPrice;
	}

	public String getActivityType() {
		return activityType;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	public GjfBenefitInfo getMemBenInfo() {
		return memBenInfo;
	}

	public void setMemBenInfo(GjfBenefitInfo memBenInfo) {
		this.memBenInfo = memBenInfo;
	}

	public GjfBenefitInfo getMerchentBenInfo() {
		return merchentBenInfo;
	}

	public void setMerchentBenInfo(GjfBenefitInfo merchentBenInfo) {
		this.merchentBenInfo = merchentBenInfo;
	}
    

}
