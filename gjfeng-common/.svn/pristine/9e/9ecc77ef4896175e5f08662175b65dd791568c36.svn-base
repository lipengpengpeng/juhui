package cc.messcat.gjfeng.dubbo.core;

import java.util.Map;

import cc.messcat.gjfeng.common.vo.app.ResultVo;
import cc.messcat.gjfeng.common.vo.app.StoreInfoVo;
import cc.messcat.gjfeng.common.vo.app.StoreJoinVo;
import cc.messcat.gjfeng.entity.GjfStoreInfo;

public interface GjfStoreInfoDubbo {

	/**
	 * @描述 提交店铺申请
	 * @author Karhs
	 * @date 2017年1月4日
	 * @param storeInfoVo
	 * @return
	 */
	public ResultVo addStore(StoreInfoVo storeInfoVo, StoreJoinVo storeJoinVo, String account) throws Exception;

	/**
	 * @描述 修改店铺信息
	 * @author Karhs
	 * @date 2017年1月4日
	 * @param gjfStoreInfo
	 * @return
	 */
	public ResultVo updateStore(GjfStoreInfo gjfStoreInfo);
	
	/**
	 * @描述 修改店铺的banner
	 * @author Karhs
	 * @date 2017年1月6日
	 * @param storeId
	 * @param bannerImgUrl
	 * @return
	 */
	public ResultVo updateStoreBanner(Long storeId,String bannerImgUrl);
	
	
	/**
	 * @描述 修改店铺的简介
	 * @author Karhs
	 * @date 2017年1月6日
	 * @param storeId
	 * @param description
	 * @return
	 */
	public ResultVo updateStoreDescription(Long storeId,String description);

	/**
	 * @描述 修改店铺审核状态
	 * @author Karhs
	 * @date 2017年1月4日
	 * @param id
	 * @param aduitStatus
	 * @param token
	 * @return
	 */
	public ResultVo updateAduitStatus(Long id, String aduitStatus, String token,String auditStatusReason);

	/**
	 * @描述 删除店铺
	 * @author Karhs
	 * @date 2017年1月4日
	 * @param id
	 * @param token
	 * @return
	 */
	public ResultVo delStore(Long id, String token);

	/**
	 * @描述 根据账户查询店铺
	 * @author Karhs
	 * @date 2017年1月4日
	 * @param account
	 * @return
	 */
	public ResultVo findStoreByAccount(String account);

	/**
	 * @描述 根据id查询店铺
	 * @author Karhs
	 * @date 2017年1月4日
	 * @param id
	 * @param token
	 * @return
	 */
	public ResultVo findStoreById(Long id, String token);
	
	/**
	 * 根据商家Id查询店铺
	 * @return
	 */
	public ResultVo findStoreByMemberId(Long id);
	
	/**
	 * @描述 分页查询店铺
	 * @author Karhs
	 * @date 2017年1月4日
	 * @param pageNo
	 * @param pageSize
	 * @param likeValue
	 * @return
	 */
	public ResultVo findStoreByPager(int pageNo, int pageSize, String likeValue, String storePro, String storeType,
		String storeStatus,String isActivityStore);
	
	/**
	 * 根据地区查找店铺
	 * @param pageNo
	 * @param pageSize
	 * @param provinceId
	 * @param cityId
	 * @param areaId
	 * @return
	 */
	public ResultVo findStoreByCondition(int pageNo,int pageSize,Long provinceId,Long cityId,Long areaId);
	
	/**
	 * 修改店铺地址
	 * @param sellerMobile
	 * @param cityValue
	 * @param addressDetail
	 * @return
	 */
	public ResultVo modifyStoreAddress(Long storeId,String sellerMobile,String cityValue,String addressDetail);
	
	/**
	 * 后台充值消费授信额度
	 * @return
	 */
	public ResultVo updateLineOfCreadit(Long id,String token,String account,Double tradeMoney,String type);
	
	/**
	 * 查询授信充值列表
	 * @return
	 */
	public ResultVo findRechargeLineCreditByPage(int pageNo,int pageSize,String tradeNo,String  name,String addTime,String endTime,String payType,String tradeStatus,String ste);
	
	/**
	 * 汇总当前查询条件授信充值列表
	 * @return
	 */
	public ResultVo findRechargeLineCredit(String tradeNo,String  name,String addTime,String endTime,String payType,String tradeStatus);
	
	/**
	 * 獲取店鋪其他信息
	 * @param id
	 * @param token
	 * @return
	 */
	public ResultVo findStoreJoin(Long id, String token);
	
	/**
	 * 后台重新定位店铺
	 * @param id
	 * @param token
	 * @return
	 */
	public ResultVo updateLocation(Long id, String token);
	
	/**
	 * 查询代理下店铺
	 * @param id
	 * @param token
	 * @param identity
	 * @return
	 */
	public ResultVo findStoreByAgent(int pageNo,int pageSize,Long id,String token,String identity);
	
	/**
	 * 查询代理下商家的流水
	 * @param id
	 * @param token
	 * @param identity
	 * @return
	 */
	public ResultVo findStoreBenefitByAgent(int pageNo,int pageSize,Long id,String token,String identity);
	/**
	 * 根据分类查询附近的店铺
	 * @param param
	 * @return
	 */
	public ResultVo findStoreByColumn(Long columnId, Map<String, Object> param);
	/**
	 * 查询店铺
	 * @param param
	 * @return
	 */
	public ResultVo findStores(Map<String, Object> param);
	/**
	 * 根据店铺id获取店铺信息
	 * @param storeId
	 * @return
	 */
	public ResultVo findStoreByStoreId(Long storeId);
	
		
}
