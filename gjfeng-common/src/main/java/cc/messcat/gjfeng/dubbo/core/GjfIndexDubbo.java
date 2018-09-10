package cc.messcat.gjfeng.dubbo.core;

import cc.messcat.gjfeng.common.vo.app.ResultVo;
import cc.messcat.gjfeng.entity.GjfMemberInfo;

public interface GjfIndexDubbo {

	/**
	 * @描述 查询O2O商城首页的轮播图和商品栏目类型
	 * @author Karhs
	 * @date 2017年1月4日
	 * @return
	 */
	public ResultVo findO2OIndexColumn();
	
	/**
	 * @描述 查询O2商品的猜你喜欢
	 * @author Karhs
	 * @date 2017年1月18日
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public ResultVo findO2OIndexPro(int pageNo,int pageSize, Double longitude, Double latitude,Long cityId);
	
	/**
	 * @描述 查询O2O商城的栏目下的商品列表
	 * @author Karhs
	 * @date 2017年1月4日
	 * @param type
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public ResultVo findO2OSubClassPro(int pageNo,int pageSize,Long columnId,String columnType,String orderType, String likeValue,
		Double longitude, Double latitude,Long cityId);
	
	/**
	 * @描述 查询网上商城首页栏目
	 * @author Karhs
	 * @date 2017年1月4日
	 * @return
	 */
	public ResultVo findShopIndexCloumn();
	
	/**
	 * @描述  查询网上商城首页推荐等商品
	 * @author Karhs
	 * @date 2017年1月14日
	 * @return
	 */
	public ResultVo findShopIndexPro();
	
	/**
	 * @描述 添加搜索历史
	 * @author Karhs
	 * @date 2017年2月4日
	 * @param gjfMemberInfo
	 * @param keyWord
	 * @return
	 */
	public ResultVo addSearchHistory(GjfMemberInfo gjfMemberInfo,String keyWord);
	
	/**
	 * @描述 查询搜索历史
	 * @author Karhs
	 * @date 2017年2月4日
	 * @param memberId
	 * @return
	 */
	public ResultVo findSearchHistory(Long memberId);
	
	/**
	 * @描述 查询o2o商城首页的广告
	 * @author Karhs
	 * @date 2017年2月4日
	 * @return
	 */
	public ResultVo findAd(String adKey);
	
	
	/**
	 * @描述 app商品栏目类型
	 * @author Karhs
	 * @date 2017年1月4日
	 * @return
	 */
	public ResultVo findO2OIndexColumnInApp();
	
	/**
	 * 供应链获取首页栏目和栏目下的子栏目
	 * @return
	 */
	public ResultVo findSupplyChainPoIndexColumn();
}
