package cc.messcat.gjfeng.dubbo.core;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

import cc.messcat.gjfeng.common.bean.Pager;
import cc.messcat.gjfeng.common.vo.app.ResultVo;
import cc.messcat.gjfeng.entity.GjfAccessToken;
import cc.messcat.gjfeng.entity.GjfMemberInfo;

public interface GjfMemberInfoDubbo {

	//前端查询
	
		/**
		 * @描述 我的个人信息
		 * @author Karhs
		 * @date 2017年1月4日
		 * @param account
		 * @return
		 */
		public ResultVo findMemberByMobile(String account);
		
		/**
		 * @描述 我的钱包
		 * @author Karhs
		 * @date 2017年1月4日
		 * @param account
		 * @return
		 */
		public ResultVo findMyWallet(String account);
		
		/**
		 * @描述  查询我的收藏
		 * @author Karhs
		 * @date 2017年1月4日
		 * @param account
		 * @param collectType 0:收藏的店铺 1:收藏的商品
		 * @param pageNo
		 * @param pageSize
		 * @return
		 */
		public ResultVo findMyCollect(String account,String collectType,int pageNo,int pageSize);
		
		/**
		 * @描述 添加我的收藏
		 * @author Karhs
		 * @date 2017年1月4日
		 * @param account
		 * @param collectType 0:收藏店铺 1:收藏商品
		 * @param id 店铺id或商品id
		 * @return
		 */
		public ResultVo addMyCollect(String account,String collectType,Long id);
		
		/**
		 * @描述 修改用户资料--微信端
		 * @author Karhs
		 * @date 2017年3月16日
		 * @param gjfMemberInfo
		 * @return
		 */
		public ResultVo updateMemberByWxOrApp(GjfMemberInfo gjfMemberInfo);
		
		/**
		 * @描述 修改用户资料--后台
		 * @author Karhs
		 * @date 2017年1月4日
		 * @param gjfMemberInfo
		 * @return
		 */
		public ResultVo updateMember(GjfMemberInfo gjfMemberInfo);
		
		/**
		 * @描述 身份证实名认证
		 * @author Karhs
		 * @date 2017年1月4日
		 * @param account
		 * @param idCardName
		 * @param idCardNo
		 * @param idCardBefImg
		 * @param idCardBehImg
		 * @return
		 */
		public ResultVo updateMemberIdCard(String account,String idCardName,String idCardNo,String idCardBefImg,String idCardBehImg,String idCardHandImg,String fileName);
		
		//后台查询
		/**
		 * @描述 身份证审核
		 * @author Karhs
		 * @date 2017年1月4日
		 * @param account
		 * @param status
		 * @param token
		 * @return
		 */
		public ResultVo updateMemberIdCardStatus(String account,String status,String token);
		
		/**
		 * @描述 后台根据手机号查询个人资料
		 * @author Karhs
		 * @date 2017年1月4日
		 * @param mobile
		 * @param token
		 * @return
		 */
		public GjfMemberInfo findMember(String account);
		
		/**
		 * @描述 查询用户根据unionId
		 * @author Karhs
		 * @date 2017年1月11日
		 * @param unionId
		 * @return
		 */
		public GjfMemberInfo findMemberByUnionId(String unionId);
		
		/**
		 * @描述 后台根据手机号和token查询个人资料
		 * @author Karhs
		 * @date 2017年1月4日
		 * @param mobile
		 * @param token
		 * @return
		 */
		public GjfMemberInfo findMemberByMobile(String account,String token);
		
		/**
		 * @描述 后台分页查询个人资料
		 * @author Karhs
		 * @date 2017年1月4日
		 * @param pageNo
		 * @param pageSize
		 * @param likeValue
		 * @return
		 */
		public Pager findMembersByPager(int pageNo,int pageSize,String name,String userName,String mobile,String type,String identity,String realNameState,String isActivityMember);

		/**
		 * 根据条件模糊查询用户
		 * @param pageNo
		 * @param pageSize
		 * @param name 用户名称
		 * @param nickName 用户昵称
		 * @param type 用户类型（0：普通用户   1：商家）
		 * @return
		 */
		public ResultVo findMembersByCondition(int pageNo,int pageSize,String name,String nickName,String mobile,String type);
		
		/**
		 * 后台删除会员
		 * @param id
		 * @return
		 */
		public ResultVo removeMemberById(Long id);
		
		/**
		 * 根据用户id获取用户信息
		 * @param memId
		 * @return
		 */
		public ResultVo findMemberById(Long memId);
		
		/**
		 * 根据用户Id查找用户下级
		 * @param memberId
		 * @return
		 */
		public ResultVo findLowLevelByMemberId(int pageNo,int pageSize,Long memberId);
		
		/**
		 * 查找所有代理商
		 * @return
		 */
		public ResultVo findAllAgents(int pageNo,int pageSize,String name,String startDate,String identity, String status );
		
		/**
		 * 我的钱包首页统计数据
		 * @param type
		 * @param account
		 * @return
		 */
		public ResultVo findMemberCountInfo(String type,String account);
		
		/**
		 * 接入用户数据
		 * @param url
		 * @return
		 */
		public ResultVo addMemberData(String url,String type,String status);
		
		/**
		 * 接入用户数据
		 * @param url
		 * @return
		 */
		public ResultVo addMemberDataGetUnId(GjfMemberInfo info);
		
		/**
		 * 修改用户代理情况
		 * @param area
		 * @param pri
		 * @param city
		 * @param gjfMemberInfo
		 * @param type 
		 * @return
		 */
		public ResultVo updateMemberAgent(Long area,Long pri,Long city,GjfMemberInfo gjfMemberInfo,String type,Date startTime,Date endTime)throws ParseException;
		
		/**
		 * 给用户添加二维码
		 * @param info
		 * @return
		 */
		public ResultVo updateMemberCode(GjfMemberInfo info);
		
		/**
		 * 添加access_token
		 * @param accessToken
		 * @return
		 */
		public ResultVo addAccessToken(GjfAccessToken accessToken);
		
		/**
		 * 获取access——token
		 * @date 2017年3月1日
		 * @return
		 */
		public ResultVo getAccessToken(String type);
		
		/**
		 * 獲取用戶的下级
		 * @param superId
		 * @return
		 */
		public ResultVo getMemberLowerLevel(Long superId,Integer pageNo,Integer pageSize);
		
		/**
		 * 获取授信信息
		 * @return
		 */
		public ResultVo findMemberTradeByH5();
		
		/**
		 * 查询用户
		 * @return
		 */
		public ResultVo findMemberByIdAndToken(Long id,String token);
		
		/**
		 * 取消代理身份
		 * @return
		 */
		public ResultVo updateAgent(Long id,String token);
		
		/**
		 * 获取所有会员
		 * @param pageNo
		 * @param pageSize
		 * @return
		 */
		ResultVo findAllMembers(int pageNo, int pageSize);
		
		public BigDecimal[] updateMemberBenefitNumCanSetInBack(String membersMobile, String memberType, Double consumptionMoney,
				Double totalBenefit, String tradeNo, BigDecimal subSysPla);
		
		/**
		 * 根据身份证号码获取用户信息
		 * @param idCard
		 * @return
		 */
		public ResultVo findMemberInfoByIdCard(String idCard);
		
		/**
		 * 获取控制实名认证次数信息
		 * @param key
		 * @return
		 */
		public ResultVo findSetBaseInfoByKey(String key);
		
		/**
		 * 获取所有控制信息
		 * @param pageNo
		 * @param pageSize
		 * @return
		 */
		public ResultVo findAllSetBaseInfo(Integer pageNo, Integer pageSize);
		
		/**
		 * 启用获取禁用控制信息
		 * @param id
		 * @return
		 */
		public ResultVo updateSetBaseInfo(Long id);
		
		/**
		 * 取消收藏
		 * @param colId
		 * @return
		 */
		public ResultVo removeMyCollect(Long colId);
		
		/**
		 *绑定用户是否收藏
		 * @return
		 */
		public ResultVo findMemberCollect(String account,Long id,String type);
		
		/**
		 * 获取运营中心列表
		 * @param pageNo
		 * @param pageSize
		 * @param name
		 * @return
		 */
		public ResultVo findMemberOpcenterByCondition(int pageNo,int pageSize,String name);
		
		/**
		 * 结算运营中心
		 * @param mobile
		 * @param actMoney
		 * @return
		 */
		public ResultVo updateMemberOpcenter(String mobile,Double actMoney);
		
		/**
		 * 获取运营中心数据
		 * @param account
		 * @return
		 */
		public ResultVo findMemberOpcenterBenefitMoney(String account);
		
		/**
		 * 获取运营中心收入历史记录
		 * @param pageNo
		 * @param pageSize
		 * @param memId
		 * @return
		 */
		public ResultVo findOpcenterHistory(Integer pageNo, Integer pageSize,Long memId);
		
		/**
		 * 获取运营中心商家列表信息
		 * @param pageNo
		 * @param pageSize
		 * @param memId
		 * @return
		 */
		public ResultVo findOpcenterMchInfo(Integer pageNo,Integer pageSize,Long memId);
		
		/**
		 * 统计用户复消金额
		 * @param account
		 * @return
		 */
		public ResultVo countMemberNiceConMoney(String account);
		
		/**
		 * 修改用户推荐人信息
		 * @param account
		 * @param memId
		 * @return
		 */
		public ResultVo updateMemberSuperInfo(String account, Long memId);
				
}
