package cc.messcat.service.collection;

import java.util.List;
import java.util.Map;

import cc.messcat.entity.GjfProductInfo;
import cc.messcat.entity.McProductInfo;
import cc.messcat.gjfeng.common.bean.Pager;

public interface McProductInfoManagerDao {

	public void addMcProductInfo(McProductInfo mcProductInfo);
	
	public void modifyMcProductInfo(McProductInfo mcProductInfo,Boolean flag);
	
	public void removeMcProductInfo(McProductInfo mcProductInfo);
	
	public void removeMcProductInfo(Long id);
	
	public McProductInfo retrieveMcProductInfo(Long id);
	public McProductInfo retrieveMcProductInfo2(Long id);
	
	public GjfProductInfo retrieveProductById(Long id);
	@SuppressWarnings("unchecked")
	public List retrieveAllMcProductInfos();
	
	public Pager retrieveMcProductInfosPager(int pageSize, int pageNo);
	
	public Pager retrieveMcProductInfosNumPager(int pageSize,int pageNo);
	
	public Pager findFrontMCProductInfo(int pageSize, int pageNo, McProductInfo mcProductInfo);
	
	public Pager findMCProductInfo(int pageSize, int pageNo, McProductInfo mcProductInfo);
	
	
	/**
	 * @return
	 */
	public List retrieveAllDateClass();
	
	public List retrieveAllProductByNewsAndDateClass(String date);


	/**
	 * 根据商家ID和状态（在售和仓库）取店铺的所有商品
	 * @param memberId
	 * @param status
	 * @return
	 */
	public  List<McProductInfo> retrieveProductByMember(Long memberId, String status);

	Pager findProduct4PageByMember(Map<String, Object> paramMap);
	
	public Pager  findMcProductByStoreId(Map<String, Object> paramMap);
	public McProductInfo findMcProductByTitle(String title);
	
	/**
	 * 根据店铺id查询热销、新、收藏产品
	 */
	public List<McProductInfo> findMcProductInfosByCondition(Map<String, Object> paramMap);

	/**
	 * 根据店铺id,商品货号查询商品
	 * @param storeId
	 * @param serial
	 * @return
	 */
	McProductInfo findMcProductByStoreIdAndSerial(Long storeId,String serial);
	
	/**
	 * 统计商品数量
	 */
	public Integer countTotalProduct();
}