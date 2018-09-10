package cc.messcat.gjfeng.dubbo;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import cc.messcat.gjfeng.common.bean.Pager;
import cc.messcat.gjfeng.common.vo.app.ResultVo;
import cc.messcat.gjfeng.dubbo.core.GjfMemberInfoDubbo;
import cc.messcat.gjfeng.entity.GjfAccessToken;
import cc.messcat.gjfeng.entity.GjfMemberInfo;
import cc.messcat.gjfeng.service.benefit.GjfBenefitInfoService;
import cc.messcat.gjfeng.service.member.GjfMemberInfoService;

public class GjfMemberInfoDubboImpl implements GjfMemberInfoDubbo {

	@Autowired
	@Qualifier("gjfMemberInfoService")
	private GjfMemberInfoService gjfMemberInfoService;

	@Autowired
	@Qualifier("gjfBenefitInfoService")
	private GjfBenefitInfoService gjfBenefitInfoService;

	/*
	 * 我的个人信息
	 * 
	 * @see
	 * cc.messcat.gjfeng.dubbo.core.GjfMemberInfoDubbo#findMemberByMobile(java.
	 * lang.String)
	 */
	@Override
	public ResultVo findMemberByMobile(String account) {
		return gjfMemberInfoService.findMemberByMobile(account);
	}

	/*
	 * 我的钱包
	 * 
	 * @see
	 * cc.messcat.gjfeng.dubbo.core.GjfMemberInfoDubbo#findMyWallet(java.lang.
	 * String)
	 */
	@Override
	public ResultVo findMyWallet(String account) {
		return gjfMemberInfoService.findMyWallet(account);
	}

	/*
	 * 查询我的收藏
	 * 
	 * @see
	 * cc.messcat.gjfeng.dubbo.core.GjfMemberInfoDubbo#findMyCollect(java.lang.
	 * String, java.lang.String, int, int)
	 */
	@Override
	public ResultVo findMyCollect(String account, String collectType, int pageNo, int pageSize) {
		return gjfMemberInfoService.findMyCollect(account, collectType, pageNo, pageSize);
	}

	/*
	 * 添加我的收藏
	 * 
	 * @see
	 * cc.messcat.gjfeng.dubbo.core.GjfMemberInfoDubbo#addMyCollect(java.lang.
	 * String, java.lang.String, java.lang.Long)
	 */
	@Override
	public ResultVo addMyCollect(String account, String collectType, Long id) {
		return gjfMemberInfoService.addMyCollect(account, collectType, id);
	}

	/*
	 * 修改用户资料--微信端
	 * 
	 * @see
	 * cc.messcat.gjfeng.dubbo.core.GjfMemberInfoDubbo#updateMemberByWxOrApp(cc.
	 * messcat.gjfeng.entity.GjfMemberInfo)
	 */
	@Override
	public ResultVo updateMemberByWxOrApp(GjfMemberInfo gjfMemberInfo) {
		return gjfMemberInfoService.updateMemberByWxOrApp(gjfMemberInfo);
	}

	/*
	 * 修改用户资料--后台
	 * 
	 * @see
	 * cc.messcat.gjfeng.dubbo.core.GjfMemberInfoDubbo#updateMember(cc.messcat.
	 * gjfeng.entity.GjfMemberInfo)
	 */
	@Override
	public ResultVo updateMember(GjfMemberInfo gjfMemberInfo) {
		return gjfMemberInfoService.updateMember(gjfMemberInfo);
	}

	/*
	 * 身份证实名认证
	 * 
	 * @see
	 * cc.messcat.gjfeng.dubbo.core.GjfMemberInfoDubbo#updateMemberIdCard(java.
	 * lang.String, java.lang.String, java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public ResultVo updateMemberIdCard(String account, String idCardName, String idCardNo, String idCardBefImg,
			String idCardBehImg, String idCardHandImg, String fileName) {
		return gjfMemberInfoService.updateMemberIdCard(account, idCardName, idCardNo, idCardBefImg, idCardBehImg,
				idCardHandImg, fileName);
	}

	/*
	 * 身份证审核
	 * 
	 * @see
	 * cc.messcat.gjfeng.dubbo.core.GjfMemberInfoDubbo#updateMemberIdCardStatus(
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public ResultVo updateMemberIdCardStatus(String account, String status, String token) {
		return gjfMemberInfoService.updateMemberIdCardStatus(account, status, token);
	}

	/*
	 * 后台根据手机号查询个人资料
	 * 
	 * @see
	 * cc.messcat.gjfeng.dubbo.core.GjfMemberInfoDubbo#findMember(java.lang.
	 * String)
	 */
	@Override
	public GjfMemberInfo findMember(String account) {
		return gjfMemberInfoService.findMember(account);
	}

	/*
	 * 查询用户根据unionId
	 * 
	 * @see
	 * cc.messcat.gjfeng.dubbo.core.GjfMemberInfoDubbo#findMemberByUnionId(java.
	 * lang.String)
	 */
	public GjfMemberInfo findMemberByUnionId(String unionId) {
		return gjfMemberInfoService.findMemberByUnionId(unionId);
	}

	/*
	 * 后台根据手机号和token查询个人资料
	 * 
	 * @see
	 * cc.messcat.gjfeng.dubbo.core.GjfMemberInfoDubbo#findMemberByMobile(java.
	 * lang.String, java.lang.String)
	 */
	@Override
	public GjfMemberInfo findMemberByMobile(String account, String token) {
		return gjfMemberInfoService.findMemberByMobile(account, token);
	}

	/*
	 * 后台分页查询个人资料
	 * 
	 * @see
	 * cc.messcat.gjfeng.dubbo.core.GjfMemberInfoDubbo#findMembersByPager(int,
	 * int, java.lang.String)
	 */
	@Override
	public Pager findMembersByPager(int pageNo, int pageSize, String name, String userName, String mobile, String type,
			String identity, String realNameState,String isActivityMember) {
		return gjfMemberInfoService.findMembersByPager(pageNo, pageSize, name, userName, mobile, type, identity,
				realNameState,isActivityMember);
	}

	/*
	 * 根据条件模糊查询用户
	 * 
	 * @see
	 * cc.messcat.gjfeng.dubbo.core.GjfMemberInfoDubbo#findMembersByCondition(
	 * int, int, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public ResultVo findMembersByCondition(int pageNo, int pageSize, String name, String nickName, String mobile,
			String type) {
		return gjfMemberInfoService.findMemberByCondition(pageNo, pageSize, name, nickName, mobile, type);
	}

	/*
	 * 后台删除数据
	 */
	public ResultVo removeMemberById(Long memId) {
		return gjfMemberInfoService.modifyMember(memId);
	}

	/*
	 * 根据用户id获取用户信息
	 * 
	 * @see
	 * cc.messcat.gjfeng.dubbo.core.GjfMemberInfoDubbo#findMemberById(java.lang.
	 * Long)
	 */
	@Override
	public ResultVo findMemberById(Long memId) {
		return gjfMemberInfoService.findMemberById(memId);
	}

	/*
	 * 根据用户Id查找用户下级
	 * 
	 * @see
	 * cc.messcat.gjfeng.dubbo.core.GjfMemberInfoDubbo#findLowLevelByMemberId(
	 * java.lang.Long)
	 */
	@Override
	public ResultVo findLowLevelByMemberId(int pageNo, int pageSize, Long memberId) {
		return gjfMemberInfoService.findLowLevelByMemberId(pageNo, pageSize, memberId);
	}

	/*
	 * 查找所有代理商
	 * 
	 * @see cc.messcat.gjfeng.dubbo.core.GjfMemberInfoDubbo#findAllAgents(int,
	 * int)
	 */
	@Override
	public ResultVo findAllAgents(int pageNo, int pageSize, String name, String startDate, String identity,
			String status) {
		return gjfMemberInfoService.findAllAgents(pageNo, pageSize, name, startDate, identity, status);
	}

	/*
	 * 我的钱包首页数据统计
	 * 
	 * @see
	 * cc.messcat.gjfeng.dubbo.core.GjfMemberInfoDubbo#findMemberCountInfo(java.
	 * lang.String, java.lang.String)
	 */
	@Override
	public ResultVo findMemberCountInfo(String type, String account) {
		return gjfMemberInfoService.findMemberCountInfo(type, account);
	}

	@Override
	public ResultVo addMemberData(String url, String type, String status) {
		return gjfMemberInfoService.addMemberData(url, type, status);
	}

	@Override
	public ResultVo addMemberDataGetUnId(GjfMemberInfo info) {
		return gjfMemberInfoService.addMemberDataGetUnId(info);
	}

	/*
	 * 更新代理身份
	 * 
	 * @see
	 * cc.messcat.gjfeng.dubbo.core.GjfMemberInfoDubbo#updateMemberAgent(java.
	 * lang.Long, java.lang.Long, java.lang.Long,
	 * cc.messcat.gjfeng.entity.GjfMemberInfo)
	 */
	@Override
	public ResultVo updateMemberAgent(Long area, Long pri, Long city, GjfMemberInfo gjfMemberInfo, String type,
			Date startTime, Date endTime) throws ParseException {
		return gjfMemberInfoService.updateMemberAgent(area, pri, city, gjfMemberInfo, type, startTime, endTime);
	}

	@Override
	public ResultVo updateMemberCode(GjfMemberInfo info) {
		// TODO Auto-generated method stub
		return gjfMemberInfoService.updateMemberCode(info);
	}

	/**
	 * 获取access_token
	 */
	@Override
	public ResultVo addAccessToken(GjfAccessToken accessToken) {
		// TODO Auto-generated method stub
		return gjfMemberInfoService.addAccessToken(accessToken);
	}

	@Override
	public ResultVo getAccessToken(String type) {
		// TODO Auto-generated method stub
		return gjfMemberInfoService.getAccessTonken(type);
	}

	@Override
	public ResultVo getMemberLowerLevel(Long superId, Integer pageNo, Integer pageSize) {
		// TODO Auto-generated method stub
		return gjfMemberInfoService.getMemberLowerLevel(superId, pageNo, pageSize);
	}

	@Override
	public ResultVo findMemberTradeByH5() {
		// TODO Auto-generated method stub
		return gjfMemberInfoService.findMemberTradeByH5();
	}

	@Override
	public ResultVo findMemberByIdAndToken(Long id, String token) {
		return gjfMemberInfoService.findMemberByIdAndToken(id, token);
	}

	@Override
	public ResultVo updateAgent(Long id, String token) {
		return gjfMemberInfoService.updateAgent(id, token);
	}

	@Override
	public ResultVo findAllMembers(int pageNo, int pageSize) {
		return gjfMemberInfoService.findAllMembers(pageNo, pageSize);
	}

	@Override
	public BigDecimal[] updateMemberBenefitNumCanSetInBack(String membersMobile, String memberType,
			Double consumptionMoney, Double totalBenefit, String tradeNo, BigDecimal subSysPla) {
		// TODO Auto-generated method stub
		return gjfBenefitInfoService.updateMemberBenefitNumCanSetInBack(membersMobile, memberType, consumptionMoney,
				totalBenefit, tradeNo, subSysPla,"3");
	}

	/**
	 * 根据身份证号码获取用户信息
	 * 
	 * @param idCard
	 * @return
	 */
	@Override
	public ResultVo findMemberInfoByIdCard(String idCard) {

		return gjfMemberInfoService.findmemberInfoByIdCard(idCard);
	}

	/**
	 * 根据关键字获取基本信息
	 */
	@Override
	public ResultVo findSetBaseInfoByKey(String key) {

		return gjfMemberInfoService.findSetBaseInfoByKey(key);
	}

	/**
	 * 获取全部的控制信息
	 */
	@Override
	public ResultVo findAllSetBaseInfo(Integer pageNo, Integer pageSize) {

		return gjfMemberInfoService.findAllSetBaseInfo(pageNo, pageSize);
	}

	@Override
	public ResultVo updateSetBaseInfo(Long id) {

		return gjfMemberInfoService.updateSetBaseInfo(id);
	}

	/**
	 * 取消收藏
	 */
	@Override
	public ResultVo removeMyCollect(Long colId) {

		return gjfMemberInfoService.removeMyCollect(colId);
	}

	/**
	 * 判断是否收藏
	 */
	@Override
	public ResultVo findMemberCollect(String account, Long id, String type) {

		return gjfMemberInfoService.findIsCollect(account, id, type);
	}

	/**
	 * 获取运营中心列表信息
	 */
	@Override
	public ResultVo findMemberOpcenterByCondition(int pageNo, int pageSize, String name) {
		
		return gjfMemberInfoService.findMemberOpcenterByCondition(pageNo, pageSize, name);
	}

	/**
	 * 结算运营中心
	 */
	@Override
	public ResultVo updateMemberOpcenter(String mobile, Double actMoney) {
		
		return gjfMemberInfoService.updateMemberOpcenter(mobile, actMoney);
	}

	/**
	 * 获取运营中心数据
	 */
	@Override
	public ResultVo findMemberOpcenterBenefitMoney(String account) {
		
		return gjfMemberInfoService.findMemberOpcenterBenefitMoney(account);
	}

	/**
	 * 获取运营中心收入历史记录
	 * @param pageNo
	 * @param pageSize
	 * @param memId
	 * @return
	 */
	@Override
	public ResultVo findOpcenterHistory(Integer pageNo, Integer pageSize, Long memId) {
		
		return gjfMemberInfoService.findOpcenterHistory(pageNo, pageSize, memId);
	}

	/**
	 * 获取运营中心商家列表信息
	 * @param pageNo
	 * @param pageSize
	 * @param memId
	 * @return
	 */
	@Override
	public ResultVo findOpcenterMchInfo(Integer pageNo, Integer pageSize, Long memId) {
		
		return gjfMemberInfoService.findOpcenterMchInfo(pageNo, pageSize, memId);
	}

	/**
	 * 统计用户复消金额
	 */
	@Override
	public ResultVo countMemberNiceConMoney(String account) {

		return gjfMemberInfoService.countMemberNiceConMoney(account);
	}

	/**
	 * 修改用户推荐人信息
	 * @param account
	 * @param memId
	 * @return
	 */
	@Override
	public ResultVo updateMemberSuperInfo(String account, Long memId) {
		
		return gjfMemberInfoService.updateMemberSuperInfo(account, memId);
	}

}
