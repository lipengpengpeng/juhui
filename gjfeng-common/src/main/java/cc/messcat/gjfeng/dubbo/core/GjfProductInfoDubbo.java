package cc.messcat.gjfeng.dubbo.core;

import java.util.List;
import java.util.Map;

import cc.messcat.gjfeng.common.dto.Arrts;
import cc.messcat.gjfeng.common.proprietary.bean.ProductInfo;
import cc.messcat.gjfeng.common.vo.app.ProductInfoVo;
import cc.messcat.gjfeng.common.vo.app.ResultVo;
import cc.messcat.gjfeng.entity.GjfMemberInfo;
import cc.messcat.gjfeng.entity.GjfProductInfo;
import cc.messcat.gjfeng.entity.GjfSupplierInfo;
import net.sf.json.JSONObject;

public interface GjfProductInfoDubbo {

	/**
	 * 根据id查询商品信息
	 * @param id
	 * @return
	 */
	public ResultVo findProductById(Long id);
	
	/**
	 * @描述 查询o2o商品信息
	 * @author Karhs
	 * @date 2017年1月19日
	 * @param id
	 * @return
	 */
	public ResultVo findO2OProductById(Long id,Double longitude, Double latitude);
	
	/**
	 * @描述 根据id查询商品信息
	 * @author Karhs
	 * @date 2017年1月4日
	 * @param id
	 * @return
	 */
	public ResultVo findProductById(Long id,Long storeId);
	
	/**
	 * @描述 根据id和token查询商品信息
	 * @author Karhs
	 * @date 2017年1月4日
	 * @param id
	 * @param token
	 * @return
	 */
	public ResultVo findProductById(Long id,String token);
	
	/**
	 * @描述 根据商品栏目查询商品信息
	 * @author Karhs
	 * @date 2017年1月4日
	 * @param columnId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public ResultVo findProByColumnId(Long columnId,int pageNo,int pageSize, String likeValue, Double longitude, Double latitude);
	
	/**
	 * @描述 查询父类栏目下的所有商品
	 * @author Karhs
	 * @date 2017年1月14日
	 * @param columnId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public ResultVo findProByFatherColumnId(Long columnId,int pageNo,int pageSize, String likeValue, Double longitude, Double latitude);
	
	/**
	 * @描述 查询本店热销商品
	 * @author Karhs
	 * @date 2017年1月20日
	 * @param storeId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public ResultVo findHotProByStoreId(Long goodsId, int pageNo, int pageSize);
	
	/**
	 * @描述 根据店铺id查询商品信息
	 * @author Karhs
	 * @date 2017年1月4日
	 * @param storeId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public ResultVo findProByStore(Long storeId,int pageNo,int pageSize);
	
	/**
	 * @描述 根据店铺id和token查询商品信息
	 * @author Karhs
	 * @date 2017年1月4日
	 * @param storeId
	 * @param pageNo
	 * @param pageSize
	 * @param token
	 * @return
	 */
	public ResultVo findProByStore(Long storeId,int pageNo,int pageSize,String token);
	
	/**
	 * @描述 发布O2O商品
	 * @author Karhs
	 * @date 2017年1月6日
	 * @param productInfoVo
	 * @param storeId
	 * @return
	 */
	public ResultVo addO2OProduct(ProductInfoVo productInfoVo,Long storeId,Long columnId,String account);
	
	/**
	 * @描述 发布网上商城商品
	 * @author Karhs
	 * @date 2017年1月6日
	 * @param gjfProductInfo
	 * @return
	 */
	public ResultVo addShopProduct(GjfProductInfo gjfProductInfo,List<Arrts> arrts,Long columnId);
	
	/**
	 * @描述 修改O2O商品信息
	 * @author Karhs
	 * @date 2017年1月19日
	 * @param productInfoVo
	 * @param storeId
	 * @param columnId
	 * @return
	 */
	public ResultVo updateO2OProduct(ProductInfoVo productInfoVo, Long storeId, Long columnId);
	
	/**
	 * @描述 修改网上商城商品信息
	 * @param gjfProductInfo
	 * @param arrts
	 * @return
	 */
	public ResultVo updateShopProduct(Long id,GjfProductInfo gjfProductInfo,List<Arrts> arrts);
	
	/**
	 * @描述 修改商品的审核状态
	 * @author Karhs
	 * @date 2017年1月4日
	 * @param id
	 * @param aduitStatus
	 * @param token
	 * @return
	 */
	public ResultVo updateProAduitStatus(Long id,String aduitStatus,String token);
	
	/**
	 * @描述 修改商品状态
	 * @author Karhs
	 * @date 2017年1月4日
	 * @return
	 */
	public ResultVo updateProStatus(Long id,String status,String token);
	
	/**
	 * @描述 下架商品
	 * @author Karhs
	 * @date 2017年1月10日
	 * @param ids
	 * @param storeId
	 * @return
	 */
	public ResultVo delProduct(Long[] ids,Long storeId);
	
	/**
	 * @描述 添加商品浏览记录
	 * @author Karhs
	 * @date 2017年3月16日
	 * @param goodsId
	 * @param memberInfo
	 * @return
	 */
	public ResultVo addProductViewHistory(Long goodsId,GjfMemberInfo memberInfo);
	/**
	 * 查询店铺的热销产品
	 * @param param
	 * @return
	 */
	public ResultVo findHotProductsByStoreId(Long storeId,Map<String, Object> param);
	
	/**
	 * 接入友品集数据
	 * @param json
	 * @return
	 */
	public ResultVo addNetFriendProductInfo(JSONObject json);
	
	/**
	 * 获取京东商品数据
	 * @return
	 */
	public ResultVo addJdProductInfoToPar(String type,String page,String sup,String rateBegin);
	
	/**
	 * 添加京东商品信息
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public ResultVo addJdProductByPager(Integer pageNo,Integer pageSize);
	
	/**
	 * 更新商品库存
	 * @param proId
	 * @return
	 */
	public ResultVo updateJdProInfo(Long proId);
	
	/**
	 * 获取商户采购商品信息
	 * @param likeName
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public ResultVo findMerchantProcurementProductInfo(Long columnId,String likeName,Integer pageNo,Integer pageSize,String discount);
	
	/**
	 * 获取京东自营商品信息
	 * @param catId
	 * @param page
	 * @param sup
	 * @param rateBegin
	 * @return
	 */
	public ResultVo findJdProprietaryProduct(String catId, String page, String sup, String rateBegin);
	
	/**
	 * 添加京东自营产品
	 * @param goodId
	 * @return
	 */
	public ResultVo addJdProprietaryProduct(Long goodId);
	
	
	/**
	 * 添加商品供应商信息
	 * @param supplier
	 * @return
	 */
	public ResultVo addSupplierInfo(GjfSupplierInfo supplier);
	
	/**
	 * 修改商品供应商信息
	 * @param supplier
	 * @return
	 */
	public ResultVo updateSupplierInfo(GjfSupplierInfo supplier);

	/**
	 * 后台分页获取全部供应商信息
	 * @param pageNo
	 * @param pageSize
	 * @param mobile
	 * @return
	 */
	public ResultVo findSupplierInfoByPage(Integer pageNo,Integer pageSize,String mobile);
	
	/**
	 * 获取全部供应商信息
	 * @return
	 */
	public ResultVo findAllSupplierInfo();
	
	/**
	 * 后台获取供应商详细信息
	 * @param supId
	 * @return
	 */
	public ResultVo findSupplierInfoById(Long supId);
	
	/**
	 * 拉取自营商城产品
	 * @param isOwn
	 * @param isCous
	 * @param isWho
	 * @param iSvou
	 * @return
	 */
	public ResultVo addProprietaryPro(String isOwn,String isCous,String isWho,String iSvou,String rateBegin);
	
	/**
	 * 天天易购首页获取商品
	 * @return
	 */
	public ResultVo findProductInfoIndex();
	
	/**
	 * 更新成品池商品信息
	 * @param pro
	 * @return
	 */
	public ResultVo updateProprietaryPro(ProductInfo pro);
	
	/**
	 * 查询pc端采购商品信息
	 * @param columnId
	 * @param likeName
	 * @param pageNo
	 * @param pageSize
	 * @param discount
	 * @return
	 */
	public ResultVo findMerchantProcurementProductInPc(Long columnId, String likeName, Integer pageNo, Integer pageSize,
			String discount);
}
