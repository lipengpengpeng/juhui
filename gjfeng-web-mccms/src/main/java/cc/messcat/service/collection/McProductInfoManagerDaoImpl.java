package cc.messcat.service.collection;

import java.util.List;
import java.util.Map;

import cc.messcat.bases.BaseManagerDaoImpl;
import cc.messcat.entity.GjfProductInfo;
import cc.messcat.entity.McProductInfo;
import cc.messcat.gjfeng.common.bean.Pager;

public class McProductInfoManagerDaoImpl extends BaseManagerDaoImpl implements McProductInfoManagerDao {

	public void addMcProductInfo(McProductInfo mcProductInfo) {
		this.mcProductInfoDao.save(mcProductInfo);
	}

	public void modifyMcProductInfo(McProductInfo mcProductInfo,Boolean flag) {
		this.mcProductInfoDao.update(mcProductInfo,flag);
	}

	public void removeMcProductInfo(McProductInfo mcProductInfo) {
		this.mcProductInfoDao.delete(mcProductInfo);
	}

	public void removeMcProductInfo(Long id) {
		this.mcProductInfoDao.delete(id);
	}

	public McProductInfo retrieveMcProductInfo(Long id) {
		return (McProductInfo) this.mcProductInfoDao.get(id);
		/*McProductInfo info = (McProductInfo) this.mcProductInfoDao.get(id);
		if (info != null){
			if (info.getProductImage() != null) {
				info.setProductImageList(info.getProductImage().split(","));
			}
			return info;
		}
		return null;*/
	}
	
	public McProductInfo retrieveMcProductInfo2(Long id) {
//		return (McProductInfo) this.mcProductInfoDao.get(id);
		McProductInfo info = (McProductInfo) this.mcProductInfoDao.get(id);
		if (info != null){
			if (info.getProductImage() != null) {
				info.setProductImageList(info.getProductImage().split(","));
			}
			return info;
		}
		return null;
	}
	
	@Override
	public GjfProductInfo retrieveProductById(Long id) {
		return (GjfProductInfo) this.mcProductInfoDao.getProduct(id);
	}

	@SuppressWarnings("unchecked")
	public List retrieveAllMcProductInfos() {
		return this.mcProductInfoDao.findAll();
	}

	public Pager retrieveMcProductInfosPager(int pageSize, int pageNo) {
		return this.mcProductInfoDao.getPager(pageSize, pageNo);
	}

	/**
	 * 带权限校验的产品搜索
	 */
	public Pager findMCProductInfo(int pageSize, int pageNo, McProductInfo mcProductInfo) {

		StringBuffer sb = new StringBuffer(" where 1 = 1 ");

		List result = null;
		
		//商品名称
		if (mcProductInfo.getName() != null && !"".equals(mcProductInfo.getName().toString()))
		{
			sb.append(" and name like  '%").append(mcProductInfo.getName().trim().toString()).append("%' ");
		}
		if (mcProductInfo.getStoreId() != null) {
			//店铺名称
			if (mcProductInfo.getStoreId().getStoreName() != null && !"".equals(mcProductInfo.getStoreId().getStoreName())) {
				sb.append(" and storeId.storeName like '%").append(mcProductInfo.getStoreId().getStoreName()).append("%' ");
			}
		}	
		//商品状态
		if (mcProductInfo.getStatus() != null && !"".equals(mcProductInfo.getStatus())) 
		{
			sb.append(" and status like '%").append(mcProductInfo.getStatus()).append("%' ");
		}
		//审核状态
		if (mcProductInfo.getAduitStatus() != null && !"".equals(mcProductInfo.getAduitStatus()))
		{
			sb.append(" and aduitStatus like '%").append(mcProductInfo.getAduitStatus()).append("%' ");
		}
		
		return mcProductInfoDao.findProductByCondition(pageNo, pageSize, sb.toString());
		/*result = this.mcProductInfoDao.findMcProductInfoByWhere(sb.toString());
		int rowCount = result.size();
		if (rowCount < pageSize)
			pageNo = 1;
		int startIndex = pageSize * (pageNo - 1);
		result = result.subList(startIndex, (pageSize + startIndex) <= result.size() ? (pageSize + startIndex) : result.size());
		return new Pager(pageSize, pageNo, rowCount, result);*/
	}

	/**
	 * 不带权限的产品列表查询
	 * @param pageSize
	 * @param pageNo
	 * @param mcProductInfo
	 * @return
	 */
	public Pager findFrontMCProductInfo(int pageSize, int pageNo, McProductInfo mcProductInfo) {
		
		StringBuffer sb = new StringBuffer(" where 1 = 1 ");

		List result = null;
		
		if (mcProductInfo.getIsNew()!=null && !"".equals(mcProductInfo.getIsNew()))
			sb.append(" and isNew = " + mcProductInfo.getIsNew());

		if (mcProductInfo.getName() != null  && !"".equals(mcProductInfo.getName().toString()))
			sb.append(" and name like  '%").append(mcProductInfo.getName().trim().toString()).append("%' ");

		result = this.mcProductInfoDao.findMcProductInfoByWhere(sb.toString());

		int rowCount = result.size();

		if (rowCount < pageSize)
			pageNo = 1;
		int startIndex = pageSize * (pageNo - 1);

		result = result.subList(startIndex, (pageSize + startIndex) <= result.size() ? (pageSize + startIndex) : result.size());

		return new Pager(pageSize, pageNo, rowCount, result);
	}
	/**
	 * 获取商家商品
	 */
	public Pager findMcProductByStoreId(Map paramMap){
		return  mcProductInfoDao.findMcProductByStoreId(paramMap);
		
	}
	
	
	public List retrieveAllDateClass(){
		return this.mcProductInfoDao.retrieveAllDateClass();
	}
	
	public List retrieveAllProductByNewsAndDateClass(String date){
		return this.mcProductInfoDao.retrieveAllProductByNewsAndDateClass(date);
	}
	

	@Override
	public List<McProductInfo> retrieveProductByMember(Long memberId, String status) {
		return this.mcProductInfoDao.findProductByMember(memberId, status);
	}
	@Override
	public Pager findProduct4PageByMember( Map<String ,Object> paramMap ) {
		return this.mcProductInfoDao.findProduct4PageByMember( paramMap);
	}

	 
 
	@Override
	public McProductInfo findMcProductByTitle(String title) {
		return this.mcProductInfoDao.findMcProductByTitle( title);
	}


	@Override
	public List<McProductInfo> findMcProductInfosByCondition(Map<String, Object> paramMap) {
		return this.mcProductInfoDao.findMcProductInfosByCondition(paramMap);
	}
	 
	public McProductInfo findMcProductByStoreIdAndSerial(Long storeId,String serial) {
		return this.mcProductInfoDao.findMcProductByStoreIdAndSerial(storeId, serial);
	}
    /**
     * 统计商品数量
     */
	@Override
	public Integer countTotalProduct() {
		
		return this.mcProductInfoDao.countTotalProduct();
	}

	@Override
	public Pager retrieveMcProductInfosNumPager(int pageSize, int pageNo) {
		return this.mcProductInfoDao.getProNum(pageSize, pageNo);
	}

	
	
}