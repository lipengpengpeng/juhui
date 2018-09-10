package cc.messcat.gjfeng.service.member;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import cc.messcat.gjfeng.common.bean.Pager;
import cc.messcat.gjfeng.common.constant.CommonProperties;
import cc.messcat.gjfeng.common.constant.CommonStatus;
import cc.messcat.gjfeng.common.exception.MyException;
import cc.messcat.gjfeng.common.util.BeanUtil;
import cc.messcat.gjfeng.common.util.BeanUtilsEx;
import cc.messcat.gjfeng.common.util.DateHelper;
import cc.messcat.gjfeng.common.util.HttpXmlClient;
import cc.messcat.gjfeng.common.util.ObjValid;
import cc.messcat.gjfeng.common.util.RandUtil;
import cc.messcat.gjfeng.common.util.Sha256;
import cc.messcat.gjfeng.common.util.StringUtil;
import cc.messcat.gjfeng.common.vo.app.MemberInfoVo;
import cc.messcat.gjfeng.common.vo.app.ResultVo;
import cc.messcat.gjfeng.dao.benefit.GjfBenefitInfoDao;
import cc.messcat.gjfeng.dao.count.CountInfoDao;
import cc.messcat.gjfeng.dao.member.GjfMemberInfoDao;
import cc.messcat.gjfeng.dao.trade.GjfTradeDao;
import cc.messcat.gjfeng.entity.GjfAccessToken;
import cc.messcat.gjfeng.entity.GjfAddressArea;
import cc.messcat.gjfeng.entity.GjfAddressCity;
import cc.messcat.gjfeng.entity.GjfAddressProvince;
import cc.messcat.gjfeng.entity.GjfBenefitPool;
import cc.messcat.gjfeng.entity.GjfMemberInfo;
import cc.messcat.gjfeng.entity.GjfMemberTrade;
import cc.messcat.gjfeng.entity.GjfMemberTradeBenefit;
import cc.messcat.gjfeng.entity.GjfMemberTradeDiviHistory;
import cc.messcat.gjfeng.entity.GjfMemberTradeOpcenter;
import cc.messcat.gjfeng.entity.GjfProductInfo;
import cc.messcat.gjfeng.entity.GjfStoreInfo;
import cc.messcat.gjfeng.entity.GjfStoreJoinin;
import cc.messcat.gjfeng.entity.GjfMemberCollect;
import cc.messcat.gjfeng.entity.GjfMemberConsumptiomNum;
import cc.messcat.gjfeng.entity.GjfSetBaseInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service("gjfMemberInfoService")
public class GjfMemberInfoServiceImpl implements GjfMemberInfoService {

	@Value("${dubbo.application.web.client}")
	private String projectName;

	@Value("${upload.member.idcard.path}")
	private String imageFolderName;

	@Value("${gjfeng.client.project.url}")
	private String domainName;

	@Autowired
	@Qualifier("gjfMemberInfoDao")
	private GjfMemberInfoDao gjfMemberInfoDao;

	@Autowired
	@Qualifier("gjfTradeDao")
	private GjfTradeDao gjfTradeDao;
	
	@Autowired
	@Qualifier("gjfBenefitInfoDao")
	private GjfBenefitInfoDao gjfBenefitInfoDao;

	@Autowired
	@Qualifier("notifyJmsTemplate")
	private JmsTemplate notifyJmsTemplate;
	
	@Autowired
	@Qualifier("countInfoDao")
	public CountInfoDao countInfoDao;

	/*
	 * 我的个人信息
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.member.GjfMemberInfoService#findMemberByMobile(
	 * java.lang.String)
	 */
	@Override
	public ResultVo findMemberByMobile(String account) {
		if (StringUtil.isBlank(account)) {
			return new ResultVo(400, "用户不存在", null);
		}
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("mobile", account);
		System.out.println("session中用户保存电话的号码---->"+account);
		GjfMemberInfo gjfMemberInfo = gjfMemberInfoDao.query(GjfMemberInfo.class, attrs);
		return new ResultVo(200, "查询成功",
				ObjValid.isNotValid(gjfMemberInfo) ? null : BeanUtilsEx.copyBean(MemberInfoVo.class, gjfMemberInfo));
	}

	/*
	 * 我的钱包
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.member.GjfMemberInfoService#findMyWallet(java.
	 * lang.String)
	 */
	@Override
	public ResultVo findMyWallet(String account) {
		//创建记录结果集的map
		Map<String, Object> data = new HashMap<String, Object>();
		//创建查询条件的map
		Map<String, Object> attrs = new HashMap<String, Object>();
		//用户电话号码
		attrs.put("mobile", account);
		//获取用户信息
		GjfMemberInfo gjfMemberInfo = gjfMemberInfoDao.query(GjfMemberInfo.class, attrs);
		//如果用户不存在返回提示信息
		if (ObjValid.isNotValid(gjfMemberInfo)) {
			return new ResultVo(400, "用户不存在", data);
		}
		//用户信息
		data.put("memberInfo", gjfMemberInfo);
		//责任消费
		data.put("insuranceMoney", gjfMemberInfo.getInsuranceMoney());
		//推荐业务奖励
		data.put("directMemberMoney", gjfMemberInfo.getDirectMemberMoney());
		//用户代金券
		data.put("memberVoucherMoney", gjfMemberInfo.getMemberVoucherMoney());
		//用户类型
		data.put("memberType", gjfMemberInfo.getType());
		//让利是否确认
		data.put("isConfirm", gjfMemberInfo.getIsConfirm());
		//分红奖励
		data.put("dividendsRewardMoney", gjfMemberInfo.getDividendsRewardMoney());
		//直推分红奖励
		data.put("recomRewardMoney", gjfMemberInfo.getRecommendRewardMoney());
		//代理奖励
		data.put("indiRewardMoney", gjfMemberInfo.getIndiRewardMoney());
		//扣减分红权
		data.put("dedecutDiviNum", gjfMemberInfo.getDeductDiviNum().add(gjfMemberInfo.getReserveDiviNum()));
		if(gjfMemberInfo.getCumulativeMoney().doubleValue()>0){
			data.put("diviTotalMoney", gjfMemberInfo.getDividendsTotalMoney().divide(gjfMemberInfo.getCumulativeMoney(),2,BigDecimal.ROUND_DOWN));	
		}else{
			data.put("diviTotalMoney", 0);
		}
		
		//查询当月消费笔数
		List<GjfMemberConsumptiomNum> list=gjfBenefitInfoDao.findMemberCousumptionNum(gjfMemberInfo.getId());
		if(BeanUtil.isValid(list)&&list.size()==1){
			GjfMemberConsumptiomNum conNum=list.get(0);
			data.put("orCount", conNum.getShopConsumptionNum());
			data.put("beCount", conNum.getBenefitNum());
		}else{
			data.put("orCount", gjfBenefitInfoDao.findCountCousumtion("0", gjfMemberInfo.getId()).getResult());
			data.put("beCount", gjfBenefitInfoDao.findCountCousumtion("1", gjfMemberInfo.getId()).getResult());
		}
		if("1".equals(gjfMemberInfo.getType())){
			attrs.remove("mobile");
			attrs.put("memberId.id", gjfMemberInfo.getId());
			GjfStoreInfo  store=gjfMemberInfoDao.query(GjfStoreInfo.class, attrs);
			data.put("storeDedecut", store.getDeductDiviNum());
		}else{
			data.put("storeDedecut", 0);
		}
		return new ResultVo(200, "查询成功", data);
	}

	/*
	 * 查询我的收藏
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.member.GjfMemberInfoService#findMyCollect(java.
	 * lang.String, java.lang.String, int, int)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ResultVo findMyCollect(String account, String collectType, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		Map<String, Object> attr = new HashMap<>();
		attr.put("collectType", collectType);
		attr.put("memberId.mobile", account);
		List<GjfMemberCollect> list = gjfMemberInfoDao.queryList(GjfMemberCollect.class, (pageNo - 1) * pageSize,
				pageSize, "addTime", "desc", attr);
		List allInfo = new ArrayList<>();
		for (GjfMemberCollect collect : list) {
			Map<String, Object> map = new HashMap<>();
			map.put("colId", collect.getId());
			if (Integer.parseInt(collectType) == 2) {
				map.put("id", collect.getGoodsId().getId());
				map.put("goodName", collect.getGoodsId().getName());
				map.put("storeName", collect.getGoodsId().getStoreId().getStoreName());
				map.put("img", collect.getGoodsId().getImgUrl());
			} else {
				map.put("id", collect.getStoreId().getId());
				map.put("goodName", "");
				map.put("storeName", collect.getStoreId().getStoreName());
				map.put("img", collect.getStoreId().getStoreBanner());
			}
			map.put("addTime", collect.getAddTime());
			allInfo.add(map);
		}
		return new ResultVo(200, "查询成功", allInfo);
	}

	/*
	 * 添加我的收藏
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.member.GjfMemberInfoService#addMyCollect(java.
	 * lang.String, java.lang.String, java.lang.Long)
	 */
	@Override
	public ResultVo addMyCollect(String account, String collectType, Long id) {
		//创建收藏对象
		GjfMemberCollect collect=new GjfMemberCollect();
		//创建查询条件集合
		Map<String, Object> attrs=new HashMap<>();
		//查询用户信息
		attrs.put("mobile", account);
		GjfMemberInfo memInfo=gjfMemberInfoDao.query(GjfMemberInfo.class, attrs);
		if(!BeanUtil.isValid(memInfo)){
			return new ResultVo(400,"用户不存在",null);
		}
		attrs.remove("mobile");
		attrs.put("id", id);
		//根据类型判断是收藏商品还是收藏店铺
		if("2".equals(collectType)){
		   	GjfProductInfo product=gjfMemberInfoDao.query(GjfProductInfo.class, attrs);
		   	collect.setGoodsId(product);
		   	collect.setCollectType("2");
		}else{
			GjfStoreInfo store=gjfMemberInfoDao.query(GjfStoreInfo.class, attrs);
			collect.setStoreId(store);
			collect.setCollectType("1");
		}
		collect.setAddTime(new Date());
		collect.setMemberId(memInfo);
		gjfMemberInfoDao.save(collect);
		Map<String, Object> info=new HashMap<>();
		info.put("collectType", collectType);
		info.put("collectStatus", "1");
		info.put("colId", collect.getId());
		return new ResultVo(200, "收藏成功",info);
	}

	/*
	 * 修改用户资料--微信端
	 * 
	 * @see cc.messcat.gjfeng.service.member.GjfMemberInfoService#
	 * updateMemberByWxOrApp(cc. messcat.gjfeng.entity.GjfMemberInfo)
	 */
	@Override
	public ResultVo updateMemberByWxOrApp(GjfMemberInfo gjfMemberInfo) {
		if (ObjValid.isNotValid(gjfMemberInfo) || StringUtil.isBlank(gjfMemberInfo.getMobile())) {
			throw new MyException(400, "用户不存在", null);
		}
		if (StringUtil.isBlank(gjfMemberInfo.getNickName())) {
			throw new MyException(400, "昵称不能为空", null);
		}
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("mobile", gjfMemberInfo.getMobile());
		GjfMemberInfo info = gjfMemberInfoDao.query(GjfMemberInfo.class, attrs);
		if (ObjValid.isNotValid(info)) {
			throw new MyException(400, "用户不存在", null);
		}
		if (StringUtil.isNotBlank(gjfMemberInfo.getImgHeadUrl())) {
			info.setImgHeadUrl(gjfMemberInfo.getImgHeadUrl());
		}
		if (StringUtil.isNotBlank(gjfMemberInfo.getRemark())) {
			info.setRemark(gjfMemberInfo.getRemark());
		}
		info.setNickName(gjfMemberInfo.getNickName());
		try {
			info = BeanUtil.setBeanByOtherBeanWithoutNull(info, gjfMemberInfo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyException(400, "修改失败", null);
		}
		gjfMemberInfoDao.update(info);
		return new ResultVo(200, "修改成功", BeanUtilsEx.copyBean(MemberInfoVo.class, info));
	}

	/*
	 * 修改用户资料--后台
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.member.GjfMemberInfoService#updateMember(cc.
	 * messcat.gjfeng.entity.GjfMemberInfo)
	 */
	@Override
	public ResultVo updateMember(GjfMemberInfo gjfMemberInfo) {
		if (ObjValid.isNotValid(gjfMemberInfo)/*
												 * || StringUtil.isBlank(
												 * gjfMemberInfo.getMobile())
												 */) {
			throw new MyException(400, "用户不存在", null);
		}
		/*if (StringUtil.isBlank(gjfMemberInfo.getNickName())) {
			throw new MyException(400, "昵称不能为空", null);
		}*/
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("id", gjfMemberInfo.getId());
		/* attrs.put("token", gjfMemberInfo.getToken()); */
		GjfMemberInfo info = gjfMemberInfoDao.query(GjfMemberInfo.class, attrs);
		if (ObjValid.isNotValid(info)) {
			throw new MyException(400, "用户不存在", null);
		}
		if (StringUtil.isNotBlank(gjfMemberInfo.getImgHeadUrl())) {
			info.setImgHeadUrl(gjfMemberInfo.getImgHeadUrl());
		}
		if (StringUtil.isNotBlank(gjfMemberInfo.getRemark())) {
			info.setRemark(gjfMemberInfo.getRemark());
		}
		info.setNickName(gjfMemberInfo.getNickName());
		try {
			info = BeanUtil.setBeanByOtherBeanWithoutNull(info, gjfMemberInfo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyException(400, "修改失败", null);
		}
		gjfMemberInfoDao.update(info);
		if ("0".equals(gjfMemberInfo.getStatus())) {
			attrs.clear();
			attrs.put("memberId.id", gjfMemberInfo.getId());
			GjfStoreInfo store = gjfMemberInfoDao.query(GjfStoreInfo.class, attrs);
			if (ObjValid.isValid(store)) {
				store.setStoreStatus("0");
				gjfMemberInfoDao.update(store);
			}
		}
		return new ResultVo(200, "修改成功", BeanUtilsEx.copyBean(MemberInfoVo.class, info));
	}

	/*
	 * 身份证实名认证
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.member.GjfMemberInfoService#updateMemberIdCard(
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public ResultVo updateMemberIdCard(String account, String idCardName, String idCardNo, String idCardBefImg,
			String idCardBehImg, String idCardHeadhild, String fileName) {
		if (StringUtil.isBlank(account)) {
			throw new MyException(400, "账号不能为空", null);
		}
		if (StringUtil.isBlank(idCardName)) {
			throw new MyException(400, "真实姓名不能为空", null);
		}
		if (StringUtil.isBlank(idCardNo)) {
			throw new MyException(400, "身份证号不能为空", null);
		}
		if (StringUtil.isBlank(idCardBefImg)) {
			throw new MyException(400, "身份证正面图片不能为空", null);
		}
		if (StringUtil.isBlank(idCardBehImg)) {
			throw new MyException(400, "身份证背面图片不能为空", null);
		}
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("mobile", account);
		GjfMemberInfo info = gjfMemberInfoDao.query(GjfMemberInfo.class, attrs);
		if (ObjValid.isNotValid(info)) {
			throw new MyException(400, "用户不存在", null);
		}

		if("1".equals(idCardBefImg)&&"2".equals(idCardBehImg)){
			info.setName(idCardName);
			info.setIdCard(idCardNo);		
			info.setIsReadName(idCardBefImg);
			info.setRealNameState(idCardBehImg);
			gjfMemberInfoDao.update(info);
		}	
		return new ResultVo(200, "提交成功", null);
	}

	/*
	 * 身份证审核
	 * 
	 * @see cc.messcat.gjfeng.service.member.GjfMemberInfoService#
	 * updateMemberIdCardStatus(java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public ResultVo updateMemberIdCardStatus(String account, String status, String token) {
		if (StringUtil.isBlank(status)
				|| (!"0".equals(status) && !"1".equals(status) && !"2".equals(status) && !"3".equals(status))) {
			return new ResultVo(400, "状态异常", null);
		}
		GjfMemberInfo info = findMember(account);
		if (ObjValid.isNotValid(info)) {
			return new ResultVo(400, "用户不存在", null);
		}
		info.setRealNameState(status);
		if (Integer.parseInt(status) == 2) {
			info.setIsReadName("1");
		}
		gjfMemberInfoDao.update(info);

		sendMessage(info);
		return new ResultVo(200, "审核成功", null);
	}

	/*
	 * 后台根据手机号查询个人资料
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.member.GjfMemberInfoService#findMember(java.
	 * lang.String)
	 */
	public GjfMemberInfo findMember(String account) {
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("mobile", account);
		return gjfMemberInfoDao.query(GjfMemberInfo.class, attrs);
	}

	/*
	 * 查询用户根据unionId
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.member.GjfMemberInfoService#findMemberByUnionId
	 * (java.lang.String)
	 */
	public GjfMemberInfo findMemberByUnionId(String unionId) {
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("unionId", unionId);
		attrs.put("isDel", "1");
		return gjfMemberInfoDao.query(GjfMemberInfo.class, attrs);
	}

	/*
	 * 后台根据手机号和token查询个人资料
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.member.GjfMemberInfoService#findMemberByMobile(
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public GjfMemberInfo findMemberByMobile(String account, String token) {
		Map<String, Object> attrs = new HashMap<String, Object>();
		if (StringUtil.isBlank(account)) {
			throw new MyException(400, "参数缺失", null);
		}
		if (StringUtil.isBlank(token)) {
			throw new MyException(400, "参数缺失", null);
		}
		attrs.put("mobile", account);
		attrs.put("token", token);
		return gjfMemberInfoDao.query(GjfMemberInfo.class, attrs);
	}

	/*
	 * 后台分页查询个人资料
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.member.GjfMemberInfoService#findMembersByPager(
	 * int, int, java.lang.String)
	 */
	@Override
	public Pager findMembersByPager(int pageNo, int pageSize, String name, String userName, String mobile, String type,
			String identity, String realNameState,String isActivityMember) {
		return gjfMemberInfoDao.findMembersByPager(pageNo, pageSize, name, userName, mobile, type, identity,
				realNameState,isActivityMember);
	}

	/*
	 * 获取所有会员
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@Override
	public ResultVo findAllMembers(int pageNo, int pageSize) {
		return new ResultVo(200, "查询成功", gjfMemberInfoDao.findAllMember());
	}

	/*
	 * 后台删除用户数据
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.member.GjfMemberInfoService#removeMember(java.
	 * lang.Long)
	 */
	@Override
	public ResultVo modifyMember(Long id) {
		// TODO Auto-generated method stub
		if (ObjValid.isNotValid(id)) {
			throw new MyException(400, "参数错误", null);
		}
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		GjfMemberInfo member = gjfMemberInfoDao.query(GjfMemberInfo.class, map);
		if (ObjValid.isNotValid(member)) {
			return new ResultVo(400, "用户不存在！", null);
		}
		member.setIsDel("0");
		gjfMemberInfoDao.update(member);
		map.clear();
		map.put("memberId.id", id);
		GjfStoreInfo store = gjfMemberInfoDao.query(GjfStoreInfo.class, map);
		if (ObjValid.isValid(store)) {
			store.setIsDel("0");
			gjfMemberInfoDao.update(store);
		}
		return new ResultVo(200, "删除成功", null);
	}

	/*
	 * 根据id查询用户信息
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.member.GjfMemberInfoService#findMemberById(java
	 * .lang.Long)
	 */
	@Override
	public ResultVo findMemberById(Long memId) {
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("id", memId);
		return new ResultVo(200, "查询成功", gjfMemberInfoDao.query(GjfMemberInfo.class, attrs));
	}

	/*
	 * 根据条件查找用户
	 * 
	 * @see cc.messcat.gjfeng.service.member.GjfMemberInfoService#
	 * findMemberByCondition(int, int, java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public ResultVo findMemberByCondition(int pageNo, int pageSize, String name, String nickName, String mobile,
			String type) {
		return new ResultVo(200, "查询成功",
				gjfMemberInfoDao.findMemberByCondition(pageNo, pageSize, name, nickName, mobile, type));
	}

	/*
	 * 查找用户下级
	 * 
	 * @see cc.messcat.gjfeng.service.member.GjfMemberInfoService#
	 * findLowLevelByMemberId(int, int, java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ResultVo findLowLevelByMemberId(int pageNo, int pageSize, Long memberId) {
		if (ObjValid.isNotValid(memberId)) {
			return new ResultVo(400, "查询失败", null);
		}
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("superId", memberId);
		Pager pager = new Pager(pageSize, pageNo,
				Integer.parseInt(String.valueOf(gjfMemberInfoDao.queryTotalRecord(GjfMemberInfo.class, attrs))),
				gjfMemberInfoDao.queryList(GjfMemberInfo.class, (pageNo - 1) * pageSize, pageSize, "addTime", "desc",
						attrs));
		return new ResultVo(200, "查询成功", pager);
	}

	/*
	 * 查找所有代理商
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.member.GjfMemberInfoService#findAllAgents(int,
	 * int)
	 */
	@Override
	public ResultVo findAllAgents(int pageNo, int pageSize, String name, String startDate, String identity,
			String status) {
		return new ResultVo(200, "查询成功",
				gjfMemberInfoDao.findAllAgents(pageNo, pageSize, name, startDate, identity, status));
	}

	/*
	 * 我的钱包首页获取统计数据
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.member.GjfMemberInfoService#findMemberCountInfo
	 * (java.lang.String, java.lang.String)
	 */
	@Override
	public ResultVo findMemberCountInfo(String type, String account) {
		Map<String, Object> map = new HashMap<>();
		map.put("benefitYesterdayMoney", 0.00); // 今日收益
		map.put("cumulativeMoney", 0.00);
		map.put("balanceMoney", 0.00);
		map.put("dividendsTotalMoney", 0.00);
		map.put("consumptionMoney", 0.00);
		map.put("canMoney", 0.00);
		map.put("canParticipate", 0.00);
		map.put("saleTotalMoney", 0.00);
		if (ObjValid.isValid(type) && Integer.parseInt(type) == 0) {
			// 获取用户信息
			Map<String, Object> attr0 = new HashMap<String, Object>();
			attr0.put("mobile", account);
			GjfMemberInfo gjfMemberInfo = gjfMemberInfoDao.query(GjfMemberInfo.class, attr0);
			if (!BeanUtil.isValid(gjfMemberInfo)) {
				return new ResultVo(200, "用户不存在", map);
			}

			/*
			 * Double memBenefit = gjfMemberInfoDao.findMemberBenefitMoney("1",
			 * gjfMemberInfo.getId()); // 查询用户当天让利金额 Double memDivNum =
			 * gjfMemberInfoDao.findMemberDivNum("0"); // 获取所有开启用户的总分红权数
			 * 
			 * // 计算单个权益 BigDecimal individual = new BigDecimal(0.00); if
			 * (memDivNum == 0) { map.put("individual", 0.00); } else {
			 * individual = new BigDecimal(memBenefit).divide(new
			 * BigDecimal(memDivNum), 2, BigDecimal.ROUND_DOWN);
			 * map.put("individual", individual); }
			 */

			BigDecimal benefitYesterdayMoney = gjfTradeDao.findBenefitYesterdayByMember(gjfMemberInfo.getId(), "2");
			map.put("benefitYesterdayMoney", benefitYesterdayMoney); // 昨日收益
			map.put("cumulativeMoney", gjfMemberInfo.getCumulativeMoney()); // 累计消费金额
			map.put("balanceMoney", gjfMemberInfo.getBalanceMoney()); // 福利帐户
			// 统计当前用户直推会员和直推商家的交易总额
			Double memDiviMoney = gjfMemberInfoDao.findDiviTotalMoeny(gjfMemberInfo.getId());
			map.put("dividendsTotalMoney", memDiviMoney); // 销售福利
			map.put("consumptionMoney", gjfMemberInfo.getConsumptionMoney()); // 待领取
			map.put("saleTotalMoney", gjfMemberInfo.getInsuranceMoney());
			if (gjfMemberInfo.getCumulativeMoney().doubleValue() == 0) { // 已领取
				map.put("canMoney", 0.00);
			} else {
				map.put("canMoney", gjfMemberInfo.getDividendsTotalMoney().multiply(new BigDecimal(100))
						.divide(gjfMemberInfo.getCumulativeMoney(), 2, BigDecimal.ROUND_DOWN));
			}
			double diviNum = gjfMemberInfo.getDividendsNum().doubleValue();
			// 可参与福利权益
			/*if (gjfMemberInfo.getDividendsTotalMoney().doubleValue()
					/ gjfMemberInfo.getCumulativeMoney().doubleValue() >= 0.2) {
				diviNum = diviNum / 2;
			}
			if (gjfMemberInfo.getDividendsTotalMoney().doubleValue()
					/ gjfMemberInfo.getCumulativeMoney().doubleValue() >= 0.52) {
				diviNum = diviNum / 2;
			}*/
			map.put("canParticipate", new BigDecimal(diviNum).setScale(0, BigDecimal.ROUND_DOWN));

		} else {
			// 获取店铺信息
			Map<String, Object> attr1 = new HashMap<String, Object>();
			attr1.put("memberId.mobile", account);
			GjfStoreInfo gjfStoreInfo = gjfMemberInfoDao.query(GjfStoreInfo.class, attr1);
			if (!BeanUtil.isValid(gjfStoreInfo)) {
				return new ResultVo(200, "店铺不存在", map);
			}

			/*
			 * Double memBenefit = gjfMemberInfoDao.findMemberBenefitMoney("2",
			 * gjfStoreInfo.getId()); // 查询用户当天让利金额 Double storeDivNum =
			 * gjfMemberInfoDao.findMemberDivNum("0"); // 获取商家分红权总数 // 计算单个权益
			 * BigDecimal individual = new BigDecimal(0.00); if (storeDivNum ==
			 * 0) { map.put("individual", 0.00); } else { individual = (new
			 * BigDecimal(memBenefit)).divide(new BigDecimal(storeDivNum), 2,
			 * BigDecimal.ROUND_DOWN); map.put("individual", individual); }
			 */

			BigDecimal benefitYesterdayMoney = gjfTradeDao
					.findBenefitYesterdayByMember(gjfStoreInfo.getMemberId().getId(), "3");
			map.put("benefitYesterdayMoney", benefitYesterdayMoney); // 昨日收益
			// 累计贡献金额
			map.put("cumulativeMoney", gjfStoreInfo.getStoreBenefitTotalMoney());
			// 总销售额
			map.put("saleTotalMoney", gjfStoreInfo.getStoreSaleTotalMoney());
			// 已经领取
			if (gjfStoreInfo.getStoreSaleTotalMoney().doubleValue() == 0) {
				map.put("canMoney", 0.00);
			} else {
				map.put("canMoney", gjfStoreInfo.getStoreDividendsTotalMoney().multiply(new BigDecimal(100))
						.divide(gjfStoreInfo.getStoreBenefitTotalMoney(), 2, BigDecimal.ROUND_DOWN));
			}

			// 待领取
			map.put("consumptionMoney", gjfStoreInfo.getStoreDividendsTotalMoneyBla());

			// 可参与福利权益
			BigDecimal storeDividensNum=gjfStoreInfo.getStoreDividendsNum();
			/*if(gjfStoreInfo.getStoreDividendsTotalMoney().doubleValue()>=gjfStoreInfo.getStoreBenefitTotalMoney().multiply(new BigDecimal(0.5)).doubleValue()){
				storeDividensNum=storeDividensNum.divide(new BigDecimal(2)).setScale(2,BigDecimal.ROUND_DOWN);
			}
			if(gjfStoreInfo.getStoreDividendsTotalMoney().doubleValue()>=gjfStoreInfo.getStoreBenefitTotalMoney().multiply(new BigDecimal(0.2)).doubleValue()){
				storeDividensNum=storeDividensNum.divide(new BigDecimal(2)).setScale(2,BigDecimal.ROUND_DOWN);
			}*/
			
			map.put("canParticipate", storeDividensNum.setScale(0, BigDecimal.ROUND_DOWN));
		}

		return new ResultVo(200, "查询成功", map);
	}

	@Override
	public ResultVo addMemberData(String url, String type, String status) {
		String str = HttpXmlClient.get(url);
		JSONObject json = JSONObject.fromObject(str);
		if (Integer.parseInt(type) == 1) {
			return addMemberInfo(json);
		}
		if (Integer.parseInt(type) == 2) {
			return addStoreInfo(json);
		}
		if (Integer.parseInt(type) == 3) {
			return addStoreJoin(json);
		}
		if (Integer.parseInt(type) == 4) {
			return addWithdraw(json);
		}
		if (Integer.parseInt(type) == 5) {
			return addBenefit(json);
		}
		if (Integer.parseInt(type) == 6) {
			return addBenefitHistory(json, status);
		}

		return new ResultVo(200, "成功", null);
	}

	// 添加用户信息
	public ResultVo addMemberInfo(JSONObject json) {
		if (Integer.parseInt(json.getString("status")) == 200) {
			JSONArray list = json.getJSONArray("result");
			if (BeanUtil.isValid(list)) {
				for (int i = 0; i < list.size(); i++) {
					JSONObject map = list.getJSONObject(i);
					GjfMemberInfo info = new GjfMemberInfo();
					info.setId(map.getLong("id"));
					// info.setUnionId(map.getString("openid"));
					info.setOpenId(map.getString("openid"));
					info.setMobile(map.getString("mobile"));
					info.setNickName(map.getString("nickName"));

					String sex = map.getString("sex");
					if (Integer.parseInt(sex) == -1) {
						info.setSex("0");
					}
					if (Integer.parseInt(sex) == 0) {
						info.setSex("1");
					}
					if (Integer.parseInt(sex) == 1) {
						info.setSex("2");
					}
					info.setImgHeadUrl(map.getString("imgHeadUrl"));
					info.setSuperId(map.getLong("superId"));
					info.setBalanceMoney(new BigDecimal(map.getDouble("balanceMoney")));
					info.setConsumptionMoney(new BigDecimal(map.getDouble("consumptionMoney")));
					info.setCumulativeMoney(new BigDecimal(map.getDouble("cumulativeMoney")));
					info.setDividendsMoneyBla(new BigDecimal("0.00"));
					info.setWithdrawalMoney(new BigDecimal(map.getDouble("withdrawalMoney")));
					info.setDividendsTotalMoney(new BigDecimal(map.getDouble("dividendsTotalMoney")));
					info.setDividendsNum(new BigDecimal(map.getDouble("dividendsNum")));
					info.setInsuranceMoney(new BigDecimal(0.00));
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					try {
						if (BeanUtil.isValid(map.getLong("addTime"))) {
							Long lt = map.getLong("addTime");
							String time = sdf.format(lt);
							info.setAddTime(sdf.parse(time));
						} else {
							info.setAddTime(new Date());
						}

						if (BeanUtil.isValid(map.getLong("editTime"))) {
							Long lt = map.getLong("editTime");
							String time = sdf.format(lt);
							info.setEditTime(sdf.parse(time));
						}

						if (BeanUtil.isValid(map.getLong("lastLoginTime"))) {
							Long lt = map.getLong("lastLoginTime");
							String time = sdf.format(lt);
							info.setLastLoginTime(sdf.parse(time));
						}

					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					info.setType(map.getString("type"));
					info.setIdentity("0");
					info.setIsBuy("1");
					info.setIsComments("1");
					info.setIsDel("1");
					info.setIsMessage("1");
					info.setIsReport("1");
					info.setIsReadName("0");
					info.setStatus("1");
					info.setToken(Sha256.getSha256Hash(info.getMobile(), info.getStatus(), CommonStatus.SIGN_KEY_NUM));
					info.setRealNameState("0");

					gjfMemberInfoDao.save(info);
				}
			}

		}
		return new ResultVo(200, "添加成功", null);
	}

	// 接入店铺信息
	public ResultVo addStoreInfo(JSONObject json) {
		if (Integer.parseInt(json.getString("status")) == 200) {
			JSONArray list = json.getJSONArray("result");
			if (BeanUtil.isValid(list)) {
				for (int i = 0; i < list.size(); i++) {
					JSONObject map = list.getJSONObject(i);
					if (BeanUtil.isValid(map.getLong("memberId"))) {
						GjfStoreInfo store = new GjfStoreInfo();
						store.setId(map.getLong("id"));
						store.setStoreName(map.getString("storeName"));
						GjfMemberInfo info = new GjfMemberInfo();
						info.setId(map.getLong("id"));
						GjfMemberInfo memberId = new GjfMemberInfo();
						memberId.setId(map.getLong("memberId"));
						store.setMemberId(memberId);
						store.setSellerName(map.getString("sellerName"));
						store.setSellerMobile(map.getString("sellerMobile"));
						store.setAddressDetail(map.getString("addressDetail"));
						store.setLatitude(map.getDouble("latitude"));
						store.setLongitude(map.getDouble("longitude"));
						store.setStoreLogoUrl(map.getString("storeLogoUrl"));
						store.setStoreDescription(map.getString("storeDescription"));
						store.setStoreDividendsTotalMoney(new BigDecimal(map.getDouble("storeDividendsTotalMoney")));
						store.setStoreDividendsTotalMoneyBla(
								new BigDecimal(map.getDouble("storeDividendsTotalMoneyBla")));
						store.setStoreDividendsNum(new BigDecimal(map.getDouble("storeDividendsNum")));
						store.setStoreBenefitTotalMoney(new BigDecimal(map.getDouble("storeBenefitTotalMoney")));
						store.setStoreWorkingTime(map.getString("storeWorkingTime"));

						store.setStoreAddTime(new Date());
						store.setStoreRecommend("0");
						store.setStoreSaleTotalMoney(new BigDecimal(0.00));
						store.setStoreBenefitTotalMoney(new BigDecimal(0.00));
						store.setStoreCreditScore(0L);
						store.setStoreDesccreditScore(new BigDecimal(0.00));
						store.setStoreServiceCreditScore(new BigDecimal(0.00));
						store.setStoreDeliveryCreditScore(new BigDecimal(0.00));
						store.setStoreCollectNum(0L);
						store.setStoreFreePrice(new BigDecimal(0.00));

						Map<String, Object> memMap = new HashMap<>();
						memMap.put("id", map.getLong("memberId"));
						GjfMemberInfo memInfo = gjfMemberInfoDao.query(GjfMemberInfo.class, memMap);
						if (BeanUtil.isValid(memInfo)) {
							memInfo.setLineOfCrade(new BigDecimal(map.getDouble("ed")));
							gjfMemberInfoDao.update(memInfo);
						}

						store.setStoreIsOwnShop("0");
						store.setStorePro("0");
						store.setStoreType("1");
						store.setStoreStatus("1");
						store.setIsDel("1");
						store.setStoreDividendsMoneyBla(new BigDecimal(0.00));
						store.setStoreNum(
								DateHelper.dataToString(new Date(), "yyyyMMddHHmmss") + RandUtil.getRandomStr(2));
						store.setToken(Sha256.getSha256Hash(store.getSellerMobile(), store.getStoreName(),
								CommonStatus.SIGN_KEY_NUM));
						gjfMemberInfoDao.save(store);
					}

				}
			}

		}
		return new ResultVo(200, "添加成功", null);
	}

	/**
	 * 添加店铺的其他信息
	 * 
	 * @param json
	 * @return
	 */
	public ResultVo addStoreJoin(JSONObject json) {
		if (Integer.parseInt(json.getString("status")) == 200) {
			JSONArray list = json.getJSONArray("result");
			if (BeanUtil.isValid(list)) {
				for (int i = 0; i < list.size(); i++) {
					JSONObject map = list.getJSONObject(i);
					GjfStoreJoinin join = new GjfStoreJoinin();
					// join.setId(map.getLong("id"));
					GjfStoreInfo store = new GjfStoreInfo();
					store.setId(map.getLong("storeId"));
					join.setStoreId(store);
					join.setCompanyName(map.getString("companyName"));
					join.setBusinessLicenceNumberElectronic(map.getString("businessLicenceNumberElectronic"));
					gjfMemberInfoDao.save(join);
				}
			}

		}
		return new ResultVo(200, "添加成功", null);
	}

	/**
	 * 接入提现记录
	 * 
	 * @param json
	 * @return
	 */
	public ResultVo addWithdraw(JSONObject json) {
		if (Integer.parseInt(json.getString("status")) == 200) {
			JSONArray list = json.getJSONArray("result");
			if (BeanUtil.isValid(list)) {
				for (int i = 0; i < list.size(); i++) {
					JSONObject map = list.getJSONObject(i);
					if (BeanUtil.isValid(map.getLong("memberId"))) {
						GjfMemberTrade trade = new GjfMemberTrade();
						// join.setId(map.getLong("id"));
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						Long lt = map.getLong("addTime");
						String time = sdf.format(lt);
						try {
							trade.setAddTime(sdf.parse(time));
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						GjfMemberInfo member = new GjfMemberInfo();
						member.setId(map.getLong("memberId"));
						System.out.println(map.getLong("memberId"));
						trade.setMemberId(member);
						trade.setTradeMoney(new BigDecimal(map.getDouble("tradeMoney")));
						trade.setTradeType(map.getString("tradeType"));
						trade.setTradeStatus("1");
						trade.setTaxMoney(new BigDecimal(0.00));
						trade.setInsuranceMoney(new BigDecimal(0.00));
						trade.setTradeNo(
								DateHelper.dataToString(new Date(), "yyyyMMddHHmmss" + RandUtil.getRandomStr(6)));
						trade.setToken(Sha256.getSha256Hash(
								DateHelper.dataToString(new Date(), "yyyyMMddHHmmss" + RandUtil.getRandomStr(6)),
								trade.getTradeType(), CommonStatus.SIGN_KEY_NUM));
						gjfMemberInfoDao.save(trade);
					}

				}
			}

		}
		return new ResultVo(200, "添加成功", null);
	}

	/**
	 * 接入商家让利信息
	 * 
	 * @param json
	 * @return
	 */
	public ResultVo addBenefit(JSONObject json) {
		if (Integer.parseInt(json.getString("status")) == 200) {
			JSONArray list = json.getJSONArray("result");
			if (BeanUtil.isValid(list)) {
				for (int i = 0; i < list.size(); i++) {
					JSONObject map = list.getJSONObject(i);
					GjfMemberTradeBenefit trade = new GjfMemberTradeBenefit();
					GjfMemberInfo member = new GjfMemberInfo();
					member.setId(map.getLong("memberId"));
					trade.setMemberId(member);
					GjfStoreInfo store = new GjfStoreInfo();
					store.setId(map.getLong("storeId"));
					trade.setStoreId(store);
					trade.setTradeNo(DateHelper.dataToString(new Date(), "yyyyMMddHHmmss" + RandUtil.getRandomStr(6)));
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

					try {
						trade.setAddTime(sdf.parse(map.getString("addTime")));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					trade.setTradeMoney(new BigDecimal(map.getDouble("tradeMoney")));
					trade.setBenefitMoney(new BigDecimal(map.getDouble("benefitMoney")));
					trade.setMemberDividendsNum(new BigDecimal(0.00));
					trade.setMerchantsDividendsNum(new BigDecimal(0.00));
					trade.setPayType("0");
					trade.setTradeStatus("1");
					trade.setToken(Sha256.getSha256Hash(
							DateHelper.dataToString(new Date(), "yyyyMMddHHmmss" + RandUtil.getRandomStr(6)), "1",
							CommonStatus.SIGN_KEY_NUM));
					gjfMemberInfoDao.save(trade);
				}
			}

		}
		return new ResultVo(200, "添加成功", null);
	}

	/**
	 * 接入商家让利信息
	 * 
	 * @param json
	 * @return
	 */
	public ResultVo addBenefitHistory(JSONObject json, String status) {
		if (Integer.parseInt(json.getString("status")) == 200) {
			JSONArray list = json.getJSONArray("result");
			if (BeanUtil.isValid(list)) {
				for (int i = 0; i < list.size(); i++) {
					JSONObject map = list.getJSONObject(i);
					GjfMemberTradeDiviHistory trade = new GjfMemberTradeDiviHistory();
					if (Integer.parseInt(status) == 3) {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						try {
							trade.setAddTime(sdf.parse(map.getString("addTime")));
							trade.setTradeTime(sdf.parse(map.getString("tradeTime")));
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						try {
							Long lt = map.getLong("addTime");
							String time = sdf.format(lt);
							trade.setAddTime(sdf.parse(time));

							Long tradelt = map.getLong("tradeTime");
							String tradeTime = sdf.format(tradelt);
							trade.setTradeTime(sdf.parse(tradeTime));
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					trade.setTradeMoney(new BigDecimal(map.getDouble("tradeMoney")));
					if (Integer.parseInt(status) == 3) {
						Long storeId = map.getLong("supplier_id");
						Map<String, Object> attr = new HashMap<>();
						attr.put("id", storeId);
						GjfStoreInfo store = gjfMemberInfoDao.query(GjfStoreInfo.class, attr);
						if (BeanUtil.isValid(store)) {
							trade.setMemberId(store.getMemberId());
						} else {
							GjfMemberInfo member = new GjfMemberInfo();
							member.setId(1L);
							trade.setMemberId(member);
						}
					} else {
						Map<String, Object> attr = new HashMap<>();
						attr.put("id", map.getLong("memberId"));
						GjfMemberInfo member = gjfMemberInfoDao.query(GjfMemberInfo.class, attr);
						if (BeanUtil.isValid(member)) {
							trade.setMemberId(member);
						} else {
							GjfMemberInfo member0 = new GjfMemberInfo();
							member0.setId(1L);
							trade.setMemberId(member0);
						}
					}

					trade.setTradeType(status);
					trade.setTradeStatus("1");
					trade.setTradeTrmo(map.getString("tradeTrmo"));
					trade.setTradeNo(DateHelper.dataToString(new Date(), "yyyyMMddHHmmss" + RandUtil.getRandomStr(6)));
					trade.setToken(Sha256.getSha256Hash(
							DateHelper.dataToString(new Date(), "yyyyMMddHHmmss" + RandUtil.getRandomStr(6)), "1",
							CommonStatus.SIGN_KEY_NUM));
					gjfMemberInfoDao.save(trade);

				}
			}

		}
		return new ResultVo(200, "添加成功", null);
	}

	@Override
	public ResultVo addMemberDataGetUnId(GjfMemberInfo info) {
		gjfMemberInfoDao.save(info);
		return new ResultVo(200, "添加成功", null);

	}

	/*
	 * 修改用户代理情况
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.member.GjfMemberInfoService#updateMemberAgent(
	 * java.lang.Long, java.lang.Long, java.lang.Long,
	 * cc.messcat.gjfeng.entity.GjfMemberInfo)
	 */
	@Override
	public ResultVo updateMemberAgent(Long area, Long pri, Long city, GjfMemberInfo gjfMemberInfo, String type,
			Date startTime, Date endTime) throws ParseException {
		Map<String, Object> attrs = new HashMap<String, Object>();
		if (ObjValid.isNotValid(area) && ObjValid.isNotValid(pri) && ObjValid.isNotValid(city) && "1".equals(type)) {
			gjfMemberInfo.setProviceId(null);
			gjfMemberInfo.setCityId(null);
			gjfMemberInfo.setAreaId(null);
			gjfMemberInfo.setIdentity(type);
		} else if (ObjValid.isValid(area) && ObjValid.isValid(pri) && ObjValid.isValid(city) && "2".equals(type)) {
			attrs.put("provinceId", pri);
			GjfAddressProvince gjfAddressProvince = gjfMemberInfoDao.query(GjfAddressProvince.class, attrs);
			if (ObjValid.isNotValid(gjfAddressProvince)) {
				throw new MyException(400, "省份不存在", null);
			}

			attrs.clear();
			attrs.put("cityId", city);
			GjfAddressCity gjfAddressCity = gjfMemberInfoDao.query(GjfAddressCity.class, attrs);
			if (ObjValid.isNotValid(gjfAddressCity)) {
				throw new MyException(400, "市不存在", null);
			}

			attrs.clear();
			attrs.put("areaId", area);
			GjfAddressArea gjfAddressArea = gjfMemberInfoDao.query(GjfAddressArea.class, attrs);
			if (ObjValid.isNotValid(gjfAddressArea)) {
				throw new MyException(400, "地区不存在", null);
			}
			gjfMemberInfo.setProviceId(gjfAddressProvince);
			gjfMemberInfo.setCityId(gjfAddressCity);
			gjfMemberInfo.setAreaId(gjfAddressArea);
			gjfMemberInfo.setIdentity(type);
		} else if (ObjValid.isNotValid(area) && ObjValid.isValid(pri) && ObjValid.isValid(city) && "3".equals(type)) {
			attrs.put("provinceId", pri);
			GjfAddressProvince gjfAddressProvince = gjfMemberInfoDao.query(GjfAddressProvince.class, attrs);
			if (ObjValid.isNotValid(gjfAddressProvince)) {
				throw new MyException(400, "省份不存在", null);
			}

			attrs.clear();
			attrs.put("cityId", city);
			GjfAddressCity gjfAddressCity = gjfMemberInfoDao.query(GjfAddressCity.class, attrs);
			if (ObjValid.isNotValid(gjfAddressCity)) {
				throw new MyException(400, "市不存在", null);
			}

			gjfMemberInfo.setProviceId(gjfAddressProvince);
			gjfMemberInfo.setCityId(gjfAddressCity);
			gjfMemberInfo.setAreaId(null);
			gjfMemberInfo.setIdentity(type);
		}else if("4".equals(type)){
			gjfMemberInfo.setProviceId(null);
			gjfMemberInfo.setCityId(null);
			gjfMemberInfo.setAreaId(null);
			gjfMemberInfo.setIsOpcenter("1");
		}else if("5".equals(type)){
			gjfMemberInfo.setProviceId(null);
			gjfMemberInfo.setCityId(null);
			gjfMemberInfo.setAreaId(null);
			gjfMemberInfo.setIsOpcenter("0");
		}
		if(Integer.valueOf(type)==1||Integer.valueOf(type)==2||Integer.valueOf(type)==3){
			gjfMemberInfo.setAgentStartDate(startTime);
			gjfMemberInfo.setAgentEndDate(endTime);
		}		
		gjfMemberInfoDao.update(gjfMemberInfo);
		return new ResultVo(200, "操作成功", null);
	}
	
	/*
	 * 修改用户代理情况
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.member.GjfMemberInfoService#updateMemberAgent(
	 * java.lang.Long, java.lang.Long, java.lang.Long,
	 * cc.messcat.gjfeng.entity.GjfMemberInfo)
	 */
	/*@Override
	public ResultVo updateMemberAgent(Long area, Long pri, Long city, GjfMemberInfo gjfMemberInfo, String type,
			Date startTime, Date endTime) throws ParseException {
		Map<String, Object> attrs = new HashMap<String, Object>();
		if (ObjValid.isValid(area) && ObjValid.isValid(pri) && ObjValid.isValid(city) && "1".equals(type)) {
			attrs.put("provinceId", pri);
			GjfAddressProvince gjfAddressProvince = gjfMemberInfoDao.query(GjfAddressProvince.class, attrs);
			if (ObjValid.isNotValid(gjfAddressProvince)) {
				throw new MyException(400, "省份不存在", null);
			}

			attrs.clear();
			attrs.put("cityId", city);
			GjfAddressCity gjfAddressCity = gjfMemberInfoDao.query(GjfAddressCity.class, attrs);
			if (ObjValid.isNotValid(gjfAddressCity)) {
				throw new MyException(400, "市不存在", null);
			}

			attrs.clear();
			attrs.put("areaId", area);
			GjfAddressArea gjfAddressArea = gjfMemberInfoDao.query(GjfAddressArea.class, attrs);
			if (ObjValid.isNotValid(gjfAddressArea)) {
				throw new MyException(400, "地区不存在", null);
			}
			gjfMemberInfo.setProviceId(gjfAddressProvince);
			gjfMemberInfo.setCityId(gjfAddressCity);
			gjfMemberInfo.setAreaId(gjfAddressArea);
			gjfMemberInfo.setIdentity(type);
			gjfMemberInfo.setIsActiveMember("4");
		} else if (ObjValid.isNotValid(area) && ObjValid.isValid(pri) && ObjValid.isValid(city) && "2".equals(type)) {
			attrs.put("provinceId", pri);
			GjfAddressProvince gjfAddressProvince = gjfMemberInfoDao.query(GjfAddressProvince.class, attrs);
			if (ObjValid.isNotValid(gjfAddressProvince)) {
				throw new MyException(400, "省份不存在", null);
			}

			attrs.clear();
			attrs.put("cityId", city);
			GjfAddressCity gjfAddressCity = gjfMemberInfoDao.query(GjfAddressCity.class, attrs);
			if (ObjValid.isNotValid(gjfAddressCity)) {
				throw new MyException(400, "市不存在", null);
			}

			attrs.clear();
			attrs.put("areaId", area);
			GjfAddressArea gjfAddressArea = gjfMemberInfoDao.query(GjfAddressArea.class, attrs);
			if (ObjValid.isNotValid(gjfAddressArea)) {
				throw new MyException(400, "地区不存在", null);
			}
			gjfMemberInfo.setProviceId(gjfAddressProvince);
			gjfMemberInfo.setCityId(gjfAddressCity);
			gjfMemberInfo.setAreaId(null);
			gjfMemberInfo.setIdentity(type);
			gjfMemberInfo.setIsActiveMember("5");
		} else if (ObjValid.isNotValid(area) && ObjValid.isValid(pri) && ObjValid.isNotValid(city) && "3".equals(type)) {
			attrs.put("provinceId", pri);
			GjfAddressProvince gjfAddressProvince = gjfMemberInfoDao.query(GjfAddressProvince.class, attrs);
			if (ObjValid.isNotValid(gjfAddressProvince)) {
				throw new MyException(400, "省份不存在", null);
			}

			attrs.clear();
			attrs.put("cityId", city);
			GjfAddressCity gjfAddressCity = gjfMemberInfoDao.query(GjfAddressCity.class, attrs);
			if (ObjValid.isNotValid(gjfAddressCity)) {
				throw new MyException(400, "市不存在", null);
			}

			gjfMemberInfo.setProviceId(gjfAddressProvince);
			//gjfMemberInfo.setCityId(gjfAddressCity);
			gjfMemberInfo.setAreaId(null);
			gjfMemberInfo.setIdentity(type);
			gjfMemberInfo.setIsActiveMember("6");
		}else if("4".equals(type)){
			gjfMemberInfo.setProviceId(null);
			gjfMemberInfo.setCityId(null);
			gjfMemberInfo.setAreaId(null);
			gjfMemberInfo.setIsOpcenter("1");
		}else if("5".equals(type)){
			gjfMemberInfo.setProviceId(null);
			gjfMemberInfo.setCityId(null);
			gjfMemberInfo.setAreaId(null);
			gjfMemberInfo.setIsOpcenter("0");
		}
		if(Integer.valueOf(type)==1||Integer.valueOf(type)==2||Integer.valueOf(type)==3){
			gjfMemberInfo.setAgentStartDate(startTime);
			gjfMemberInfo.setAgentEndDate(endTime);
		}		
		gjfMemberInfoDao.update(gjfMemberInfo);
		return new ResultVo(200, "操作成功", null);
	}*/


	// 发送mq消息
	public void sendMessage(GjfMemberInfo info) {
		// 发送短信消息通知
		try {
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("mobile", info.getMobile());
			dataMap.put("variable0", info.getMobile());
			if (info.getRealNameState().equals("2")) {//成功
				dataMap.put("templateCode", CommonProperties.MNS_TEMPLATE_IDCARD_SUCC);
//				content = "亲爱的" + info.getMobile() + "用户，您的实名制认证已经审核成功，如有疑问请联系工作人员！";
			} else if (info.getRealNameState().equals("3")) {//失败
				dataMap.put("templateCode", CommonProperties.MNS_TEMPLATE_IDCARD_FAIL);
//				content = "亲爱的" + info.getMobile() + "用户，您的实名制认证审核失败，请重新填写申请，如有疑问请联系工作人员！";
			}
			Object toJSON = JSONObject.fromObject(dataMap);
			final String str = toJSON.toString();
			notifyJmsTemplate.send("SmsSendNotify", new MessageCreator() {
				public Message createMessage(Session session) throws JMSException {
					Message obj = session.createTextMessage(str);
					return obj;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public ResultVo updateMemberCode(GjfMemberInfo memberInfo) {

		if (!BeanUtil.isValid(memberInfo)) {
			throw new MyException(400, "用户不存在", null);
		}
		return new ResultVo(200, "修改成功", gjfMemberInfoDao.update(memberInfo));
	}

	@Override
	public ResultVo addAccessToken(GjfAccessToken accessTokens) {

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, 80);
		accessTokens.setExpirationTime(cal.getTime());
		gjfMemberInfoDao.save(accessTokens);
		return new ResultVo(200, "添加成功", null);
	}

	@Override
	public ResultVo getAccessTonken(String type) {

		Map<String, Object> attrs = new HashMap<>();
		attrs.put("type", type);
		List<GjfAccessToken> list = gjfMemberInfoDao.queryList(GjfAccessToken.class, "expirationTime", "desc", attrs);
		GjfAccessToken accessTokens = null;
		if (list.size() > 0) {
			accessTokens = list.get(0);
		}
		return new ResultVo(200, "查询成功", accessTokens);
	}

	/*
	 * 获取用户下级
	 * @see cc.messcat.gjfeng.service.member.GjfMemberInfoService#getMemberLowerLevel(java.lang.Long, java.lang.Integer, java.lang.Integer)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ResultVo getMemberLowerLevel(Long superId, Integer pageNo, Integer pageSize) {
		Map<String, Object> attrs = new HashMap<>();
		attrs.put("superId", superId);
		attrs.put("isDel", "1");
		List<GjfMemberInfo> list = gjfMemberInfoDao.queryList(GjfMemberInfo.class, (pageNo - 1) * pageSize, pageSize,
				"addTime", "desc", attrs);
		return new ResultVo(200, "查询成功", list);
	}

	@Override
	public ResultVo findMemberTradeByH5() {
		return new ResultVo(200, "查询成功", gjfMemberInfoDao.findAllMemberTradeByH5());
	}

	/*
	 * 查询用户
	 * @see cc.messcat.gjfeng.service.member.GjfMemberInfoService#findMemberByIdAndToken(java.lang.Long, java.lang.String)
	 */
	@Override
	public ResultVo findMemberByIdAndToken(Long id, String token) {
		if (ObjValid.isNotValid(id)) {
			throw new MyException(400, "参数缺失", null);
		}
		if (ObjValid.isNotValid(token)) {
			throw new MyException(400, "参数缺失", null);
		}
		Map<String, Object> attrs = new HashMap<>();
		attrs.put("id", id);
		attrs.put("token", token);
		return new ResultVo(200, "查询成功", gjfMemberInfoDao.query(GjfMemberInfo.class, attrs));
	}

	/*
	 * 取消代理身份
	 * @see cc.messcat.gjfeng.service.member.GjfMemberInfoService#updateAgent(java.lang.Long, java.lang.String)
	 */
	@Override
	public ResultVo updateAgent(Long id, String token) {
		if (ObjValid.isNotValid(id)) {
			return new ResultVo(400, "参数缺失", null);
		}
		if (ObjValid.isNotValid(token)) {
			return new ResultVo(400, "参数缺失", null);
		}
		Map<String, Object> attrs = new HashMap<>();
		attrs.put("id", id);
		attrs.put("token", token);
		GjfMemberInfo memberInfo = gjfMemberInfoDao.query(GjfMemberInfo.class, attrs);
		if (ObjValid.isNotValid(memberInfo)) {
			return new ResultVo(400, "用户信息不存在!", null);
		}
		memberInfo.setIdentity("0");
		memberInfo.setProviceId(null);
		memberInfo.setCityId(null);
		memberInfo.setAreaId(null);
		memberInfo.setAgentStartDate(null);
		memberInfo.setAgentEndDate(null);
		gjfMemberInfoDao.update(memberInfo);
		return new ResultVo(200, "取消成功", null);
	}

	@Override
	public ResultVo findmemberInfoByIdCard(String idCard) {
		boolean falg=false;
		Map<String, Object> attr=new HashMap<>();
		attr.put("idCard", idCard);
		GjfMemberInfo memberInfo=gjfMemberInfoDao.query(GjfMemberInfo.class, attr);
		if(BeanUtil.isValid(memberInfo)){
			falg=true;
		}else{
			falg=false;
		}
		return new ResultVo(200, "取消成功", falg);
	}

	@Override
	public ResultVo findSetBaseInfoByKey(String key) {
		Map<String, Object> attr=new HashMap<>();
		attr.put("key",key);
		return new ResultVo(200,"查询成功",gjfMemberInfoDao.query(GjfSetBaseInfo.class, attr));
	}

	@SuppressWarnings("unchecked")
	@Override
	public ResultVo findAllSetBaseInfo(Integer pageNo, Integer pageSize) {
		Map<String, Object> attr=new HashMap<>();
		return new ResultVo(200,"查询成功",gjfMemberInfoDao.queryList(GjfSetBaseInfo.class, (pageNo - 1) * pageSize, pageSize,
				"id", "desc", attr));
	}

	@Override
	public ResultVo updateSetBaseInfo(Long id) {
		Map<String, Object> attr=new HashMap<>();
		attr.put("id", id);
		GjfSetBaseInfo gjfSetBaseInfo=gjfMemberInfoDao.query(GjfSetBaseInfo.class, attr);
		if (!BeanUtil.isValid(gjfSetBaseInfo)) {
			throw new MyException(400, "信息不存在", null);
		}
		if("0".equals(gjfSetBaseInfo.getStatus())){
			gjfSetBaseInfo.setStatus("1");
		}else{
			gjfSetBaseInfo.setStatus("0");
		}
		return new ResultVo(200,"设置成功",gjfMemberInfoDao.update(gjfSetBaseInfo));
	}

	/**
	 * 取消收藏
	 */
	@Override
	public ResultVo removeMyCollect(Long colId) {
		//创建查询条件集合
		Map<String, Object> attrs=new HashMap<>();
		attrs.put("id", colId);
		//查询收藏信息
		GjfMemberCollect collect=gjfMemberInfoDao.query(GjfMemberCollect.class, attrs);
		if(!BeanUtil.isValid(collect)){
			return new ResultVo(400,"收藏信息不存在",null);
		}
		//删除收藏信息
		gjfMemberInfoDao.delete(collect);
		Map<String, Object> info =new HashMap<>();
		info.put("collectStatus", "0");
		return new ResultVo(200,"已取消收藏",info);
	}

	/**
	 *用户是否收藏
	 */
	@Override
	public ResultVo findIsCollect(String account, Long id, String type) {
		String collectStatus="0";
		Map<String, Object> map=new HashMap<>();
		map.put("memberId.mobile", account);
		if("2".equals(type)){
			map.put("goodsId.id", id);
		}else{
			map.put("storeId.id", id);
		}
		GjfMemberCollect collect=gjfMemberInfoDao.query(GjfMemberCollect.class, map);
		if(collect!=null){
			collectStatus="1";
		}
		return new ResultVo(200,"已取消收藏",collectStatus);
	}

	/**
	 * 获取运营中心列表信息
	 */
	@Override
	public ResultVo findMemberOpcenterByCondition(int pageNo, int pageSize, String name) {
		Pager pager=gjfMemberInfoDao.findMemberOpcentionByCondition(pageNo, pageSize, name);
		return new ResultVo(200,"查询成功",pager);
	}

	/**
	 * 结算运营中心数据
	 */
	@Override
	public ResultVo updateMemberOpcenter(String mobile, Double actMoney) {
		//获取用户信息
		Map<String, Object> attrs=new HashMap<>();
		attrs.put("mobile", mobile);
		GjfMemberInfo memberInfo=gjfMemberInfoDao.query(GjfMemberInfo.class, attrs);
		if(!BeanUtil.isValid(memberInfo)){
			return new ResultVo(400,"用户不存在",null);
		}
		//扣除待领消费金额
		BigDecimal conAct=memberInfo.getConsumptionMoney().subtract(new BigDecimal(actMoney));
		if(conAct.doubleValue()<=0){
			return new ResultVo(400, "积分不足");
		}
		memberInfo.setConsumptionMoney(conAct);
		//扣除运营中心金额
		BigDecimal opAct=memberInfo.getOpcenterMoney().subtract(new BigDecimal(actMoney));
		if(conAct.doubleValue()<=0){
			return new ResultVo(400, "结算金额不足");
		}
		memberInfo.setOpcenterMoney(opAct);
		//获取资金池
		attrs.remove("mobile");
		GjfBenefitPool benefitPool = gjfBenefitInfoDao.query(GjfBenefitPool.class, attrs);
		benefitPool.setOpcenterPoolSpend(benefitPool.getOpcenterPoolSpend().subtract(new BigDecimal(actMoney)));
		//添加历史记录
		String  tradeNo=DateHelper.dataToString(new Date(), "yyyyMMddHHmmss" + RandUtil.getRandomStr(6));
		GjfMemberTradeOpcenter opcenter=new GjfMemberTradeOpcenter(memberInfo.getId(), Long.valueOf("0"), new BigDecimal(0), new BigDecimal(0), new BigDecimal(actMoney), tradeNo, "0", "1", new Date());
		gjfBenefitInfoDao.save(opcenter);
		gjfBenefitInfoDao.update(benefitPool);
		gjfBenefitInfoDao.update(memberInfo);
		return new ResultVo(200,"结算成功");
	}

	/**
	 * 获取运营中心数据
	 * @param account
	 * @return
	 */
	@Override
	public ResultVo findMemberOpcenterBenefitMoney(String account) {
		Map<String, Object> dataMap=new HashMap<>();
		//获取用户数据
		Map<String, Object> attrs=new HashMap<>();
		attrs.put("mobile", account);
		GjfMemberInfo  memberInfo=gjfBenefitInfoDao.query(GjfMemberInfo.class, attrs);
		dataMap.put("opcenterMoney", memberInfo.getOpcenterMoney());//待派发金额
		dataMap.put("opcenterTotalMoney", memberInfo.getOpcenterTotalMoney());//总金额		
		dataMap.put("id",memberInfo.getId());
		dataMap.put("mobile", memberInfo.getMobile());
		dataMap.put("name", memberInfo.getName());
		//统计商家当天让利金额
		BigDecimal mchBenefit=gjfMemberInfoDao.countOpentionMchBenefitMoney("1", memberInfo.getId());
		dataMap.put("mchBenefit",mchBenefit);
		BigDecimal mchTotalBenefit=gjfMemberInfoDao.countOpentionMchBenefitMoney("0", memberInfo.getId());
		dataMap.put("mchTotalBenefit",mchTotalBenefit);
		return new ResultVo(200, "查询成功",dataMap);
	}

	/**
	 * 获取运营中心历史记录
	 * @param pageNo
	 * @param pageSize
	 * @param account
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ResultVo findOpcenterHistory(Integer pageNo, Integer pageSize,Long memId) {
		Map<String, Object> attrs=new HashMap<>();
		attrs.put("memberId", memId);
		List<GjfMemberTradeOpcenter> tradeOpcenter=gjfMemberInfoDao.queryList(GjfMemberTradeOpcenter.class, (pageNo-1)*pageSize, pageSize, "addTime", "desc", attrs);
		Pager pager=new Pager(pageSize, pageNo,(int)gjfMemberInfoDao.queryTotalRecord(GjfMemberTradeOpcenter.class, attrs) , tradeOpcenter);
		return new ResultVo(200, "查询成功", pager);
	}

	/**
	 * 运营中心获取商家列表信息
	 */
	@Override
	public ResultVo findOpcenterMchInfo(Integer pageNo, Integer pageSize, Long memId) {
		Map<String, Object> dataMap=new HashMap<>();
		Object o=gjfMemberInfoDao.findOpcenterMchInfo(pageNo, pageSize, memId, "1");
		Object o1=gjfMemberInfoDao.findOpcenterMchInfo(pageNo, pageSize, memId, "0");
		dataMap.put("mchInfo", o);
		dataMap.put("mchTotal", o1);
		return new ResultVo(200,"查询成",dataMap);
	}

	/**
	 * 统计用户需要复消的金额
	 */
	@Override
	public ResultVo countMemberNiceConMoney(String account) {
		Map<String, Object> resultMap=new HashMap<>();
		resultMap.put("activityMember", 0);
		resultMap.put("noActivityMember", 0);
		resultMap.put("isActivity", 0);	
		resultMap.put("isActivityStore", 0);	
		resultMap.put("type", 0);
		resultMap.put("merchantMoney", 0);
		resultMap.put("memberMoney", 0);
		Map<String, Object> attrs=new HashMap<>();
		attrs.put("mobile", account);
		//查询用户信息
		GjfMemberInfo memberInfo=gjfMemberInfoDao.query(GjfMemberInfo.class, attrs);
		
		GjfStoreInfo storeInfo=null;
		if("1".equals(memberInfo.getType())){//如果用户是商家
			attrs.remove("mobile");
			attrs.put("memberId.id", memberInfo.getId());
			//查询商家信息
			 storeInfo=countInfoDao.query(GjfStoreInfo.class, attrs);
			
			resultMap.put("type", "1");
			resultMap.put("isActivityStore", storeInfo.getIsActivityStore());	
		}
				
		if(BeanUtil.isValid(memberInfo)){
			resultMap.put("isActivity", memberInfo.getIsActiveMember());
			if("1".equals(memberInfo.getIsActiveMember())){//如果为活跃会员
				
				if(BeanUtil.isValid(storeInfo)&&"1".equals(storeInfo.getIsActivityStore())||!BeanUtil.isValid(storeInfo)){
					//查询本周用户领回金额
					BigDecimal memberMoney=countInfoDao.findDiviMoneyByTime("0",memberInfo.getId());
					//获取用户商家领回金额
					BigDecimal storeMoney=countInfoDao.findDiviMoneyByTime("1",memberInfo.getId());
					//总金额
					BigDecimal totalNiceMoney=memberMoney.add(storeMoney);
					//获取用户本周让利金额
					BigDecimal benifityMoney=countInfoDao.findCountCousumMoney("0",memberInfo.getId());
					//获取用户本周复消金额
					BigDecimal memberReBeMoney=countInfoDao.findMemberBenefitUpgradeMoney(memberInfo.getId());
					//实际让利金额
					BigDecimal realBenefitMoney=benifityMoney.subtract(memberReBeMoney);
					//差额
					BigDecimal niceMoney=totalNiceMoney.multiply(new BigDecimal(0.5)).subtract(realBenefitMoney).setScale(2, BigDecimal.ROUND_DOWN);
					if(niceMoney.doubleValue()>0){
						niceMoney=niceMoney.divide(new BigDecimal(0.12),2,BigDecimal.ROUND_DOWN);
						resultMap.put("activityMember", niceMoney);
					}
				}else{
					//总分红去哪
				    double totalDivi=0;		    
				    //获取用户让利金额
					BigDecimal benifityMoney=countInfoDao.findCountCousumMoney("0",memberInfo.getId());
					//获取用户分红权
				    totalDivi=Math.floor(memberInfo.getDividendsNum().doubleValue());
				    BigDecimal memberMoney=new BigDecimal(totalDivi*3.5).subtract(benifityMoney);
				     //如果金额大于零
					if(memberMoney.doubleValue()>0){
						memberMoney=memberMoney.divide(new BigDecimal(0.12),2,BigDecimal.ROUND_DOWN);
						resultMap.put("memberMoney", memberMoney);
					}
					if("1".equals(memberInfo.getType())){//如果用户是商家					
						//如果商家信息不为空
						if(BeanUtil.isValid(storeInfo)&&"0".equals(storeInfo.getIsActivityStore())){
							totalDivi=totalDivi+Math.floor(storeInfo.getStoreDividendsNum().doubleValue());
							
							//获取用户本周复消金额
							BigDecimal memberReBeMoney=countInfoDao.findMemberBenefitUpgradeMoney(memberInfo.getId());
							BigDecimal storeMoney=benifityMoney.subtract(memberReBeMoney);
							BigDecimal merchantMoney=new BigDecimal(storeInfo.getStoreDividendsNum().doubleValue()*3.5).subtract(storeMoney);
						     //如果金额大于零
							if(merchantMoney.doubleValue()>0){
								merchantMoney=merchantMoney.divide(new BigDecimal(0.12),2,BigDecimal.ROUND_DOWN);
								resultMap.put("merchantMoney", merchantMoney);
							}
						}
						
					}
					
					//需要的金额
					BigDecimal niceMoney=new BigDecimal(totalDivi*3.5).subtract(benifityMoney);
					//如果金额大于零
					if(niceMoney.doubleValue()>0){
						niceMoney=niceMoney.divide(new BigDecimal(0.12),2,BigDecimal.ROUND_DOWN);
						resultMap.put("noActivityMember", niceMoney);
					}
	
				}
				
			}else{//如果非活跃会员
				//总分红去哪
			    double totalDivi=0;		    
			    //获取用户让利金额
				BigDecimal benifityMoney=countInfoDao.findCountCousumMoney("0",memberInfo.getId());
				//获取用户分红权
			    totalDivi=Math.floor(memberInfo.getDividendsNum().doubleValue());
			    BigDecimal memberMoney=new BigDecimal(totalDivi*3.5).subtract(benifityMoney);
			     //如果金额大于零
				if(memberMoney.doubleValue()>0){
					memberMoney=memberMoney.divide(new BigDecimal(0.12),2,BigDecimal.ROUND_DOWN);
					resultMap.put("memberMoney", memberMoney);
				}
				if("1".equals(memberInfo.getType())){//如果用户是商家					
					//如果商家信息不为空
					if(BeanUtil.isValid(storeInfo)&&"0".equals(storeInfo.getIsActivityStore())){
						totalDivi=totalDivi+Math.floor(storeInfo.getStoreDividendsNum().doubleValue());
						BigDecimal merchantMoney=new BigDecimal(storeInfo.getStoreDividendsNum().doubleValue()*3.5).subtract(benifityMoney);
					     //如果金额大于零
						if(merchantMoney.doubleValue()>0){
							merchantMoney=merchantMoney.divide(new BigDecimal(0.12),2,BigDecimal.ROUND_DOWN);
							resultMap.put("merchantMoney", merchantMoney);
						}
					}
					
				}
				
				//需要的金额
				BigDecimal niceMoney=new BigDecimal(totalDivi*3.5).subtract(benifityMoney);
				//如果金额大于零
				if(niceMoney.doubleValue()>0){
					niceMoney=niceMoney.divide(new BigDecimal(0.12),2,BigDecimal.ROUND_DOWN);
					resultMap.put("noActivityMember", niceMoney);
				}
			}
		}
		
		return new ResultVo(200,"查询成功",resultMap);
	}

	/**
	 * 修改用户推荐人
	 */
	@Override
	public ResultVo updateMemberSuperInfo(String account, Long memId) {
		//创建查询条件
		Map<String, Object> attrs=new HashMap<>();
		//用户id
		attrs.put("id", memId);
		//获取用户信息
		GjfMemberInfo memberInfo=gjfMemberInfoDao.query(GjfMemberInfo.class, attrs);
		//如果用户不存在返回提示信息
		if(!BeanUtil.isValid(memberInfo)){
			return new ResultVo(400,"用户不存在");
		}
	    //清除id
		attrs.clear();
		//推荐人电话
		attrs.put("mobile", account);
		//获取推荐人信息
		GjfMemberInfo superInfo=gjfMemberInfoDao.query(GjfMemberInfo.class, attrs);
		//如果推荐人信息为空返回提示
		if(!BeanUtil.isValid(superInfo)){
			return new ResultVo(400,"推荐人不存在");
		}
		//修改用户的推荐人
		memberInfo.setSuperId(superInfo.getId());
		//更新推荐人信息
		gjfMemberInfoDao.update(memberInfo);
		//返回提示信息
		return new ResultVo(200,"修改成功");
	}	
	
}
