package cc.messcat.gjfeng.service.member;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cc.messcat.gjfeng.common.constant.CommonStatus;
import cc.messcat.gjfeng.common.exception.MyException;
import cc.messcat.gjfeng.common.util.BeanUtil;
import cc.messcat.gjfeng.common.util.BeanUtilsEx;
import cc.messcat.gjfeng.common.util.EncryptionUtil;
import cc.messcat.gjfeng.common.util.ObjValid;
import cc.messcat.gjfeng.common.util.Sha256;
import cc.messcat.gjfeng.common.util.StringUtil;
import cc.messcat.gjfeng.common.vo.app.MemberInfoVo;
import cc.messcat.gjfeng.common.vo.app.ResultVo;
import cc.messcat.gjfeng.dao.benefit.GjfBenefitInfoDao;
import cc.messcat.gjfeng.dao.member.GjfMemberInfoDao;
import cc.messcat.gjfeng.entity.GjfMemberInfo;
import cc.messcat.gjfeng.entity.GjfStoreInfo;
import cc.messcat.gjfeng.service.benefit.GjfBenefitInfoService;

@Service("gjfLoginService")
public class GjfLoginServiceImpl implements GjfLoginService {

	@Autowired
	@Qualifier("gjfMemberInfoDao")
	private GjfMemberInfoDao gjfMemberInfoDao;

	@Value("${dubbo.application.web.client}")
	private String projectName;

	@Value("${upload.member.head.path}")
	private String imageFolderName;
	
	@Autowired
	@Qualifier("gjfBenefitInfoDao")
	private GjfBenefitInfoDao gjfBenefitInfoDao;

	


	/*
	 * 注册 --微信快捷登陆需要绑定手机号
	 * 
	 * @see cc.messcat.gjfeng.service.member.GjfLoginService#register(java.lang.
	 * String, java.lang.String, java.lang.String, java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see cc.messcat.gjfeng.service.member.GjfLoginService#register(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Long, java.lang.String)
	 */
	@Override
	public ResultVo register(String account, String password, String nickName, String sex, String imgHeadUrl,
			String registerType, Long superId, String url) {
		if (StringUtil.isBlank(registerType) || (!"0".equals(registerType) && !"1".equals(registerType))) {
			throw new MyException(400, "注册失败", null);
		}
		if (StringUtil.isBlank(account)) {
			throw new MyException(400, "账号不能为空", null);
		}
		Map<String, Object> attrs = new HashMap<String, Object>();
		if ("0".equals(registerType)) {
			attrs.put("mobile", account);
		} else if ("1".equals(registerType)) {
			attrs.put("unionId", account);
		}
		GjfMemberInfo info = gjfMemberInfoDao.query(GjfMemberInfo.class, attrs);
		if (ObjValid.isValid(info)) {
			throw new MyException(400, "账号已经存在", null);
		}
		info = new GjfMemberInfo();
		if ("0".equals(registerType)) {
			info.setMobile(account);
			info.setPassword(EncryptionUtil.getMD5Code(account + password));
		} else if ("1".equals(registerType)) {
			info.setUnionId(account);
			info.setImgHeadUrl(imgHeadUrl);
		}
		if (ObjValid.isValid(superId)) {
			Object o = gjfMemberInfoDao.get(superId, GjfMemberInfo.class.getName());
			if (ObjValid.isNotValid(o)) {
				throw new MyException(400, "推荐人不存在", null);
			} else {
				info.setSuperId(superId);
				//更新推荐人底下人数
				updateSuperMemTotalNum(o);
			}
		} else {
			info.setSuperId(0L);
		}
		info.setNickName(nickName);
		info.setSex(StringUtil.isBlank(sex) ? "0" : sex);
		info.setAddTime(new Date());
		info.setBindTime(new Date());
		info.setBalanceMoney(new BigDecimal(0.00));
		info.setConsumptionMoney(new BigDecimal(0.00));
		info.setCumulativeMoney(new BigDecimal(0.00));
		info.setWithdrawalMoney(new BigDecimal(0.00));
		info.setDividendsMoneyBla(new BigDecimal(0.00));
		info.setDividendsTotalMoney(new BigDecimal(0.00));
		info.setDividendsNum(new BigDecimal(0.000000));
		info.setDirectMemberTotalMoney(new BigDecimal(0.00));
		info.setDirectMerchantsTotalMoney(new BigDecimal(0.00));
		info.setAgentMoney(new BigDecimal(0.00));
		info.setAgentTotalMoney(new BigDecimal(0.00));
		info.setLineOfCrade(new BigDecimal(0.00));
		info.setInsuranceMoney(new BigDecimal(0.00));
		info.setDirectMemberTotalMoney(new BigDecimal(0.00));
		info.setIsReadName("0");
		info.setType("0");
		info.setIdentity("0");
		info.setIsReport("1");
		info.setIsBuy("1");
		info.setIsComments("1");
		info.setIsMessage("1");
		info.setIsDivi("1");
		info.setStatus("1");
		info.setIsDel("1");
		info.setRealNameState("0");
		info.setIsConfirm("0");
		info.setToken(Sha256.getSha256Hash(registerType.equals("0") ? info.getMobile() : info.getUnionId(),
				info.getStatus(), CommonStatus.SIGN_KEY_NUM));
		info.setOpenId(url);
		info.setDividendsRewardMoney(new BigDecimal(0.00));
		info.setRecommendRewardMoney(new BigDecimal(0.00));
		info.setIndiRewardMoney(new BigDecimal(0.00));
		info.setLastDeductDiviNum(new BigDecimal(0.000000));
		info.setReserveDiviNum(new BigDecimal(0.000000));
		info.setLastGetBackDiviMoney(new BigDecimal(0.00));
		info.setDeductDiviNum(new BigDecimal(0.000000));
		info.setLastTimeDiviMoney(new BigDecimal(0.00));
		info.setOpcenterMoney(new BigDecimal(0.00));
		info.setOpcenterTotalMoney(new BigDecimal(0.00));
		info.setIsOpcenter("0");
		info.setIsActiveMember("1");
		info.setMerchantType("0");
		info.setMerchantModel("0");
		info.setDirectMemberMoney(new BigDecimal(0.00));
		info.setMemberVoucherMoney(new BigDecimal(0.00));
		info.setAgentMoney(new BigDecimal(0.00));
		info.setMerchantOperationMoney(new BigDecimal(0.00));
		info.setTwoStarMemberNum(0);
		info.setThreeStarMemberNum(0);
		info.setTotalMemberNum(0);
		info.setMerchantFirstCousumptionMoney(new BigDecimal(0));
		info.setMerchantFirstCumulativeMoney(new BigDecimal(0));
		info.setMerchantSecondCousumptionMoney(new BigDecimal(0));
		info.setMerchantSecondCumulativeMoney(new BigDecimal(0));
		info.setMerchantThreeCousumptionMoney(new BigDecimal(0));
		info.setMerchantThreeCumulativeMoney(new BigDecimal(0));
		info.setMerchantFirstDiviNum(new BigDecimal(0));
		info.setMerchantSecondDiviNum(new BigDecimal(0));
		info.setMerchantThreeDiviNum(new BigDecimal(0));
		gjfMemberInfoDao.save(info);
		return new ResultVo(200, "注册成功", BeanUtilsEx.copyBean(MemberInfoVo.class, info));
	}
	
	/*
	 * 统计推荐人底下用户数量
	 */
	@SuppressWarnings("rawtypes")
	public void updateSuperMemTotalNum(Object o){
		//转化为member对象
		GjfMemberInfo member=(GjfMemberInfo) o;
		//如果对象不为空
		if(BeanUtil.isValid(member)){
			//创建结果list
			List reusltList=new ArrayList();
			//获取底下会员数据
			reusltList=findUnderMem(member.getId(),reusltList);
			//如果集合不为空
			if(BeanUtil.isValid(reusltList)){
				//记录底下人数
				member.setTotalMemberNum(reusltList.size()+1);
				//如果用户为三星以下
				if(Integer.valueOf(member.getIsActiveMember())<2&&member.getTotalMemberNum()>=30){
					//推荐人升级
					member.setIsActiveMember("2");
				}
				//如果用户为四星以下
				if(Integer.valueOf(member.getIsActiveMember())<3&&member.getTotalMemberNum()>=900){
					//推荐人升级
					member.setIsActiveMember("3");
				}
			}
					    
			//更新推荐人信息
			gjfBenefitInfoDao.update(member);
	    }
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List findUnderMem(Long memId,List resultList){
		//查询用户信息
		List list=gjfMemberInfoDao.findUnderMember(memId);
		//如果集合不为空
		if(BeanUtil.isValid(list)){
			//遍历集合
			for(int i=0;i<list.size();i++){
				//获取用户id
				int mId= (int) list.get(i);
				//把数据添加到list集合中
				resultList.add(mId);
				//再次查找
				findUnderMem(Long.valueOf(mId),resultList);
			}
		}
		return resultList;
	}

	/*
	 * 登陆
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.member.GjfLoginService#login(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public ResultVo login(String account, String password, String loginType, String type) {
		if (StringUtil.isBlank(loginType) || (!"0".equals(loginType) && !"1".equals(loginType))) {
			throw new MyException(400, "登陆失败", null);
		}
		if (StringUtil.isBlank(account)) {
			throw new MyException(400, "账号不能为空", null);
		}
		Map<String, Object> attrs = new HashMap<String, Object>();
		if ("0".equals(loginType)) {
			if (StringUtil.isBlank(password)) {
				throw new MyException(400, "密码不能为空", null);
			}
			attrs.put("mobile", account);
			if (type == null) {
				attrs.put("password", EncryptionUtil.getMD5Code(account + password));
			} else {
				attrs.put("password", password);
			}

		} else {
			attrs.put("unionId", account);
		}
		GjfMemberInfo info = gjfMemberInfoDao.query(GjfMemberInfo.class, attrs);
		if (ObjValid.isNotValid(info)) {
			throw new MyException(400, "账号或密码错误", null);
		}
		return new ResultVo(200, "登陆成功", BeanUtilsEx.copyBean(MemberInfoVo.class, info));
	}

	/*
	 * 忘记密码
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.member.GjfLoginService#updateForgetPwd(java.
	 * lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public ResultVo updateForgetPwd(String account, String password, String rePassword) {
		if (StringUtil.isBlank(account)) {
			return new ResultVo(400, "账号不能为空", null);
		}
		if (StringUtil.isBlank(password)) {
			return new ResultVo(400, "密码不能为空", null);
		}
		if (StringUtil.isBlank(rePassword)) {
			return new ResultVo(400, "重复密码不能为空", null);
		}
		if (!password.equals(rePassword)) {
			return new ResultVo(400, "密码不一致", null);
		}
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("mobile", account);
		GjfMemberInfo gjfMemberInfo = gjfMemberInfoDao.query(GjfMemberInfo.class, attrs);
		if (ObjValid.isNotValid(gjfMemberInfo)) {
			return new ResultVo(400, "用户不存在", null);
		}
		gjfMemberInfo.setPassword(EncryptionUtil.getMD5Code(account + password));
		gjfMemberInfoDao.update(gjfMemberInfo);
		return new ResultVo(200, "修改成功", BeanUtilsEx.copyBean(MemberInfoVo.class, gjfMemberInfo));
	}

	/*
	 * 修改密码
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.member.GjfLoginService#updatePwd(java.lang.
	 * String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public ResultVo updatePwd(String account, String oldPassword, String newPassword, String rePassword) {
		if (StringUtil.isBlank(account)) {
			return new ResultVo(400, "账号不能为空", null);
		}
		if (StringUtil.isBlank(oldPassword)) {
			return new ResultVo(400, "原密码不能为空", null);
		}
		if (StringUtil.isBlank(newPassword)) {
			return new ResultVo(400, "新密码不能为空", null);
		}
		if (StringUtil.isBlank(rePassword)) {
			return new ResultVo(400, "重复密码不能为空", null);
		}
		if (!newPassword.equals(rePassword)) {
			return new ResultVo(400, "密码不一致", null);
		}
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("mobile", account);
		attrs.put("password", EncryptionUtil.getMD5Code(account + oldPassword));
		GjfMemberInfo gjfMemberInfo = gjfMemberInfoDao.query(GjfMemberInfo.class, attrs);
		if (ObjValid.isNotValid(gjfMemberInfo)) {
			return new ResultVo(400, "密码错误", null);
		}
		gjfMemberInfo.setPassword(EncryptionUtil.getMD5Code(account + newPassword));
		gjfMemberInfoDao.update(gjfMemberInfo);
		return new ResultVo(200, "修改成功", BeanUtilsEx.copyBean(MemberInfoVo.class, gjfMemberInfo));
	}

	/*
	 * 微信快捷登陆绑定手机号码
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.member.GjfLoginService#updateBindMobie(java.
	 * lang.String, java.lang.String)
	 */
	@Override
	public ResultVo updateBindMobie(String unionId, String mobile,String password) {

		// 如果微信unionId为空，返回提示
		if (StringUtil.isBlank(unionId)) {
			return new ResultVo(400, "微信账号不能为空", null);
		}
		// 如果电话号码为空，返回提示信息
		if (StringUtil.isBlank(mobile)) {
			return new ResultVo(400, "手机号不能为空", null);
		}

		// 根据unionId获取用户信息
		Map<String, Object> attrs = new HashMap<String, Object>();

		attrs.put("unionId", unionId);
		GjfMemberInfo gjfMemberInfo = gjfMemberInfoDao.query(GjfMemberInfo.class, attrs);
		if (ObjValid.isNotValid(gjfMemberInfo)) {
			return new ResultVo(400, "用户不存在", null);
		}
		
		
		// 根据手机号码获取用户信息
		attrs.remove("unionId");
		attrs.put("mobile", mobile);
		GjfMemberInfo memberInfo = gjfMemberInfoDao.query(GjfMemberInfo.class, attrs);
		if (ObjValid.isValid(memberInfo) && BeanUtil.isValid(memberInfo.getUnionId())) {
			
			return new ResultVo(400, "手机号已经存在，请更换其他手机号", null);
			
		} else if(ObjValid.isValid(memberInfo) && !BeanUtil.isValid(memberInfo.getUnionId())){// 如果已经存在用户并且uid为空,整合数据
			//设置uid
			memberInfo.setUnionId(gjfMemberInfo.getUnionId());
			//设置openId
			memberInfo.setOpenId(gjfMemberInfo.getOpenId());
			if(memberInfo.getSuperId()==0&&gjfMemberInfo.getSuperId()!=0){
				memberInfo.setSuperId(gjfMemberInfo.getSuperId());
			}
			if(memberInfo.getImgHeadUrl()==null||memberInfo.getImgHeadUrl()==""){
				memberInfo.setImgHeadUrl(gjfMemberInfo.getImgHeadUrl());
			}
			
			// 更新用户数据
			gjfMemberInfoDao.update(memberInfo);
			gjfMemberInfo.setMobile("");
			gjfMemberInfo.setIsDel("0");
		}else{
			gjfMemberInfo.setMobile(mobile);
		}
		//如果密码不为空
		if(BeanUtil.isValid(password)){
			gjfMemberInfo.setPassword(EncryptionUtil.getMD5Code(mobile + password));
		}
		
		gjfMemberInfoDao.update(gjfMemberInfo);
		return new ResultVo(200, "绑定成功", BeanUtilsEx.copyBean(MemberInfoVo.class, gjfMemberInfo));
	}

	/*
	 * 设置修改支付密码
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.member.GjfLoginService#updatePayPwd(java.lang.
	 * String, java.lang.String, java.lang.String)
	 */
	@Override
	public ResultVo updatePayPwd(String account, String payPwd, String rePayPwd) {
		if (StringUtil.isBlank(account)) {
			return new ResultVo(400, "账号不能为空", null);
		}
		if (StringUtil.isBlank(payPwd)) {
			return new ResultVo(400, "新支付密码不能为空", null);
		}
		if (StringUtil.isBlank(rePayPwd)) {
			return new ResultVo(400, "重复支付密码不能为空", null);
		}
		if (!payPwd.equals(rePayPwd)) {
			return new ResultVo(400, "支付密码不一致", null);
		}
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("mobile", account);
		GjfMemberInfo gjfMemberInfo = gjfMemberInfoDao.query(GjfMemberInfo.class, attrs);
		if (ObjValid.isNotValid(gjfMemberInfo)) {
			return new ResultVo(400, "用户不存在", null);
		}
		gjfMemberInfo.setPayPassword(EncryptionUtil.getMD5Code(account + payPwd));
		gjfMemberInfoDao.update(gjfMemberInfo);
		return new ResultVo(200, "设置成功", BeanUtilsEx.copyBean(MemberInfoVo.class, gjfMemberInfo));

	}

	@Override
	public ResultVo upateBindMobieInApp(String uid, String Mobile, String password) {
		if (StringUtil.isBlank(Mobile)) {
			return new ResultVo(400, "账号不能为空", null);
		}
		if (StringUtil.isBlank(password)) {
			return new ResultVo(400, "密码不能为空", null);
		}
		Map<String, Object> attr = new HashMap<>();
		attr.put("unionId", uid);
		GjfMemberInfo gjfMemberInfo = gjfMemberInfoDao.query(GjfMemberInfo.class, attr);
		if (!BeanUtil.isValid(gjfMemberInfo)) {
			return new ResultVo(400, "用户不存在", null);
		}
		if (gjfMemberInfo.getMobile() != Mobile) {
			attr.remove("unionId");
			attr.put("mobile", Mobile);
			GjfMemberInfo mem = gjfMemberInfoDao.query(GjfMemberInfo.class, attr);
			if (BeanUtil.isValid(mem)) {
				return new ResultVo(400, "用户已存在", null);
			}
		}

		gjfMemberInfo.setMobile(Mobile);
		gjfMemberInfo.setPassword(EncryptionUtil.getMD5Code(Mobile + password));
		gjfMemberInfoDao.update(gjfMemberInfo);
		return new ResultVo(200, "绑定成功", gjfMemberInfo);
	}

}
