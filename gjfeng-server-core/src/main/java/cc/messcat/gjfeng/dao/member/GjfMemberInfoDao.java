package cc.messcat.gjfeng.dao.member;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import cc.messcat.gjfeng.common.bean.Pager;
import cc.messcat.gjfeng.common.vo.app.MemberBankVo;
import cc.messcat.gjfeng.common.vo.app.MemberTradeBenefitVo;
import cc.messcat.gjfeng.dao.BaseHibernateDao;
import cc.messcat.gjfeng.entity.GjfMemberInfo;
import cc.messcat.gjfeng.entity.GjfMemberTrade;

public interface GjfMemberInfoDao extends BaseHibernateDao<Serializable>{

	/**
	 * @描述 查询当日获得的让利金额
	 * @author Karhs
	 * @date 2017年1月4日
	 * @param memebrId
	 * @return
	 */
	public BigDecimal findTotalBenefitByToday(Long memebrId);
	
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
	 * 查询所有会员
	 * @return
	 */
	public List<GjfMemberInfo> findAllMember();
	
	
	/**
	 * @描述 查找我的银行卡
	 * @author Karhs
	 * @date 2017年1月4日
	 * @param account
	 * @return
	 */
	public List<MemberBankVo> findMyBankCard(Long memebrId,int pageSize);
	
	/**
	 * @描述 查找前端商家查询自己的让利流水记录
	 * @author Karhs
	 * @date 2017年1月11日
	 * @param pageNo
	 * @param pageSize
	 * @param storeId
	 * @return
	 */
	public List<MemberTradeBenefitVo> findStoreBenefit(int pageNo,int pageSize,Long storeId);
	
	/**
	 * @描述 查询用户的商家给他的让利记录
	 * @author Karhs
	 * @date 2017年3月1日
	 * @param pageNo
	 * @param pageSize
	 * @param account
	 * @return
	 */
	public List<MemberTradeBenefitVo> findMemberBenefit(int pageNo,int pageSize,String account);
	
	/**
	 * 删除用户收货地址
	 * 
	 */
	
	public void delMemAdder(Long memId,Long id);

	/**
	 * 查找所有代理商
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Pager findAllAgents(int pageNo, int pageSize,String name,String startDate,String identity, String status );
	
	
	/**
	 * 获取当天让利金额
	 * @param type
	 * @return
	 */
	public Double findMemberBenefitMoney(String type,Long id);
	
	/**
	 * 统计用户分红总交易额
	 * @param id
	 * @return
	 */
	public Double findDiviTotalMoeny(Long id);
	
	/**
	 * 统计用户或者商家的总分红权数
	 * @param type
	 * @param id
	 * @return
	 */
	public Double findMemberDivNum(String type);
	
	/**
	 * 根据条件查询用户
	 * @param pageNo
	 * @param pageSize
	 * @param name
	 * @param nickName
	 * @param type
	 * @return
	 */
	public Pager findMemberByCondition(int pageNo, int pageSize, String name, String nickName,String mobile, String type);
	
	/**
	 * 
	 * @return
	 */
	public List<GjfMemberTrade> findAllMemberTradeByH5();
	
	/**
	 * 获取运营中心列表
	 * @param pageNo
	 * @param pageSize
	 * @param name
	 * @return
	 */
	public Pager findMemberOpcentionByCondition(int pageNo,int pageSize,String name);
	
	/**
	 * 统计运营中心商家总让利金额或当天天让利金额
	 * @param type 0 总让利金额  1 当天让利金额
	 * @return
	 */
	public BigDecimal countOpentionMchBenefitMoney(String type,Long memId);
	
	/**
	 * 运营中心获取商家列表
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Object findOpcenterMchInfo(Integer pageNo,Integer pageSize,Long memId,String type);
	
	/**
	 * 统计
	 * @param MemberId
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List findUnderMember(Long MemberId);
	
	
}

