package cc.messcat.gjfeng.dubbo.core;

import cc.messcat.gjfeng.common.vo.app.ResultVo;

public interface GjfEnterpriseColumnDubbo {
	
	/**
	 * @描述 根据父类id查询子类栏目
	 * @author Karhs
	 * @date 2017年1月13日
	 * @param fatherId
	 * @return
	 */
	public ResultVo findColumnByFatherId(Long fatherId);

	/**
	 * @描述 查询所有一级栏目下的二级栏目
	 * @author Karhs
	 * @date 2017年1月14日
	 * @return
	 */
	public ResultVo findColumnsByFatherId(String goodsType,Long pageTypeId);
	
	/**
	 * @描述 查询附近的商品栏目
	 * @author Karhs
	 * @date 2017年1月23日
	 * @return
	 */
	public ResultVo findO2ONearColumn();
	
	/**
	 * 根据栏目标识获取栏目新闻信息
	 * @param key about_gjfeng 关于金凤凰 consumption_rules消费规则
	 * @return
	 */
	public ResultVo findBaseInfoByKey(String key);
	
	/**
	 * 
	 * @return
	 */
	public ResultVo findNearColumnInApp();
	
	/**
	 * 获取京东首页栏目
	 * @return
	 */
	public ResultVo findJdIndexColumn();
	
	/**
	 * 获取产品供应链栏目
	 * @param frontNum
	 * @return
	 */
	public ResultVo findProductModelColumn(String frontNum,String isValidInNav);
	
	
	/**
	 * 获取新闻列表
	 * @param pageNo
	 * @param pageSize
	 * @param keyWord
	 * @return
	 */
	public ResultVo findEnterpriseNewsList(Integer pageNo,Integer pageSize,String keyWord);
	
	/**
	 * 获取新闻详细信息
	 * @param id
	 * @return
	 */
	public ResultVo findEnterpriseNewsListsById(Long id);
}
