package cc.messcat.gjfeng.dubbo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import cc.messcat.gjfeng.common.dto.Arrts;
import cc.messcat.gjfeng.common.proprietary.bean.ProductInfo;
import cc.messcat.gjfeng.common.vo.app.ProductInfoVo;
import cc.messcat.gjfeng.common.vo.app.ResultVo;
import cc.messcat.gjfeng.dubbo.core.GjfProductInfoDubbo;
import cc.messcat.gjfeng.entity.GjfMemberInfo;
import cc.messcat.gjfeng.entity.GjfProductInfo;
import cc.messcat.gjfeng.entity.GjfSupplierInfo;
import cc.messcat.gjfeng.service.product.GjfProductInfoService;
import net.sf.json.JSONObject;

public class GjfProductInfoDubboImpl implements GjfProductInfoDubbo {

	@Autowired
	@Qualifier("gjfProductInfoService")
	private GjfProductInfoService gjfProductInfoService;

	/*
	 * 根据id查询商品信息
	 * 
	 * @see
	 * cc.messcat.gjfeng.dubbo.core.GjfProductInfoDubbo#findProductById(java.
	 * lang.Long)
	 */
	@Override
	public ResultVo findProductById(Long id) {
		return gjfProductInfoService.findProductById(id);
	}

	/*
	 * 根据id查询商品信息
	 * 
	 * @see
	 * cc.messcat.gjfeng.dubbo.core.GjfProductInfoDubbo#findProductById(java.
	 * lang.Long)
	 */
	@Override
	public ResultVo findProductById(Long id, Long storeId) {
		return gjfProductInfoService.findProductById(id, storeId);
	}

	/*
	 * 查询o2o商品信息
	 * 
	 * @see
	 * cc.messcat.gjfeng.dubbo.core.GjfProductInfoDubbo#findO2OProductById(java.
	 * lang.Long)
	 */
	public ResultVo findO2OProductById(Long id, Double longitude, Double latitude) {
		return gjfProductInfoService.findO2OProductById(id, longitude, latitude);
	}

	/*
	 * 根据id和token查询商品信息
	 * 
	 * @see
	 * cc.messcat.gjfeng.dubbo.core.GjfProductInfoDubbo#findProductById(java.
	 * lang.Long, java.lang.String)
	 */
	@Override
	public ResultVo findProductById(Long id, String token) {
		return gjfProductInfoService.findProductById(id, token);
	}

	/*
	 * 根据商品栏目查询商品信息
	 * 
	 * @see
	 * cc.messcat.gjfeng.dubbo.core.GjfProductInfoDubbo#findProByColumnId(java.
	 * lang.Long, int, int)
	 */
	@Override
	public ResultVo findProByColumnId(Long columnId, int pageNo, int pageSize, String likeValue, Double longitude,
		Double latitude) {
		return gjfProductInfoService.findProByColumnId(columnId, pageNo, pageSize, likeValue, longitude, latitude);
	}

	/*
	 * 查询父类栏目下的所有商品
	 * 
	 * @see
	 * cc.messcat.gjfeng.dubbo.core.GjfProductInfoDubbo#findProByFatherColumnId(
	 * java. lang.Long, int, int)
	 */
	@Override
	public ResultVo findProByFatherColumnId(Long columnId, int pageNo, int pageSize, String likeValue, Double longitude,
		Double latitude) {
		return gjfProductInfoService.findProByFatherColumnId(columnId, pageNo, pageSize, likeValue, longitude, latitude);
	}
	
	/*  
	 * 查询本店热销商品
	 * @see cc.messcat.gjfeng.dubbo.core.GjfProductInfoDubbo#findHotProByStoreId(java.lang.Long, int, int)
	 */
	public ResultVo findHotProByStoreId(Long goodsId, int pageNo, int pageSize){
		return gjfProductInfoService.findHotProByStoreId(goodsId, pageNo, pageSize);
	}

	/*
	 * 根据店铺id查询商品信息
	 * 
	 * @see
	 * cc.messcat.gjfeng.dubbo.core.GjfProductInfoDubbo#findProByStore(java.lang
	 * .Long, int, int)
	 */
	@Override
	public ResultVo findProByStore(Long storeId, int pageNo, int pageSize) {
		return gjfProductInfoService.findProByStore(storeId, pageNo, pageSize);
	}

	/*
	 * 根据店铺id和token查询商品信息
	 * 
	 * @see
	 * cc.messcat.gjfeng.dubbo.core.GjfProductInfoDubbo#findProByStore(java.lang
	 * .Long, int, int, java.lang.String)
	 */
	@Override
	public ResultVo findProByStore(Long storeId, int pageNo, int pageSize, String token) {
		return gjfProductInfoService.findProByStore(storeId, pageNo, pageSize, token);
	}

	/*
	 * 发布O2O商品
	 * 
	 * @see
	 * cc.messcat.gjfeng.dubbo.core.GjfProductInfoDubbo#addO2OProduct(cc.messcat
	 * .gjfeng.common.vo.app.ProductInfoVo, java.lang.Long, java.lang.Long,
	 * java.lang.String)
	 */
	@Override
	public ResultVo addO2OProduct(ProductInfoVo productInfoVo, Long storeId, Long columnId, String account) {
		return gjfProductInfoService.addO2OProduct(productInfoVo, storeId, columnId, account);
	}

	/*
	 * 发布网上商城商品
	 * 
	 * @see cc.messcat.gjfeng.dubbo.core.GjfProductInfoDubbo#addShopProduct(cc.
	 * messcat.gjfeng.entity.GjfProductInfo)
	 */
	@Override
	public ResultVo addShopProduct(GjfProductInfo gjfProductInfo,List<Arrts> arrts,Long columnId) {
		return gjfProductInfoService.addShopProduct(gjfProductInfo, arrts, columnId);
	}

	/*
	 * 修改O2O商品信息
	 * 
	 * @see
	 * cc.messcat.gjfeng.dubbo.core.GjfProductInfoDubbo#updateO2OProduct(cc.
	 * messcat.gjfeng.common.vo.app.ProductInfoVo, java.lang.Long,
	 * java.lang.Long)
	 */
	@Override
	public ResultVo updateO2OProduct(ProductInfoVo productInfoVo, Long storeId, Long columnId) {
		return gjfProductInfoService.updateO2OProduct(productInfoVo, storeId, columnId);
	}
	
	/*
	 * 修改网上商城商品信息
	 * @see cc.messcat.gjfeng.dubbo.core.GjfProductInfoDubbo#updateShopProduct(cc.messcat.gjfeng.entity.GjfProductInfo, java.util.List)
	 */
	@Override
	public ResultVo updateShopProduct(Long id,GjfProductInfo gjfProductInfo, List<Arrts> arrts) {
		return gjfProductInfoService.updateShopProduct(id,gjfProductInfo, arrts);
	}

	/*
	 * 修改商品的审核状态
	 * 
	 * @see
	 * cc.messcat.gjfeng.dubbo.core.GjfProductInfoDubbo#updateProAduitStatus(
	 * java.lang.Long, java.lang.String, java.lang.String)
	 */
	@Override
	public ResultVo updateProAduitStatus(Long id, String aduitStatus, String token) {
		return gjfProductInfoService.updateProAduitStatus(id, aduitStatus, token);
	}

	/*
	 * 修改商品状态
	 * 
	 * @see
	 * cc.messcat.gjfeng.dubbo.core.GjfProductInfoDubbo#updateProStatus(java.
	 * lang.Long, java.lang.String, java.lang.String)
	 */
	@Override
	public ResultVo updateProStatus(Long id, String status, String token) {
		return gjfProductInfoService.updateProStatus(id, status, token);
	}

	/*
	 * 下架商品
	 * 
	 * @see
	 * cc.messcat.gjfeng.dubbo.core.GjfProductInfoDubbo#delProduct(java.lang.
	 * Long, java.lang.Long)
	 */
	public ResultVo delProduct(Long[] ids, Long storeId) {
		return gjfProductInfoService.delProduct(ids, storeId);
	}

	/* 
	 * 添加商品浏览记录
	 * @see cc.messcat.gjfeng.dubbo.core.GjfProductInfoDubbo#addProductViewHistory(java.lang.Long, cc.messcat.gjfeng.entity.GjfMemberInfo)
	 */
	@Override
	public ResultVo addProductViewHistory(Long goodsId, GjfMemberInfo memberInfo) {
		return gjfProductInfoService.addProductViewHistory(goodsId, memberInfo);
	}

	@Override
	public ResultVo findHotProductsByStoreId(Long storeId, Map<String, Object> param) {
		return gjfProductInfoService.findHotProductsByStoreId(storeId, param);
	}

	/**
	 * 接入友品集数据
	 */
	@Override
	public ResultVo addNetFriendProductInfo(JSONObject json) {
		
		return gjfProductInfoService.addNetFriendsProductInfo(json);
	}

	/**
	 * 接入京东商品数据
	 */
	@Override
	public ResultVo addJdProductInfoToPar(String type,String page,String sup,String rateBegin) {
		
		return gjfProductInfoService.addTestJdProductInfo(type,page,sup,rateBegin);
		//return gjfProductInfoService.addTestJdProductInfo();
	}

	/**
	 * 添加京东商品信息
	 */
	@Override
	public ResultVo addJdProductByPager(Integer pageNo, Integer pageSize) {
		// TODO Auto-generated method stub
		return gjfProductInfoService.addJdProductDetail(pageNo, pageSize);
	}

	/**
	 * 更新京东商品库存
	 */
	@Override
	public ResultVo updateJdProInfo(Long proId) {
		
		return gjfProductInfoService.updateJdProductStock(proId);
	}

	/**
	 * 获取商户采购商品信息
	 */
	@Override
	public ResultVo findMerchantProcurementProductInfo(Long columnId,String likeName, Integer pageNo, Integer pageSize,String discount) {

		return gjfProductInfoService.findMerchantProcurementProductInfo(columnId,likeName, pageNo, pageSize,discount);
	}

	/**
	 * 获取京东自营商品信息
	 */
	@Override
	public ResultVo findJdProprietaryProduct(String catId, String page, String sup, String rateBegin) {
		
		return gjfProductInfoService.findJdProprietaryProduct(catId, page, sup, rateBegin);
	}

	/**
	 * 添加京东自营产品
	 */
	@Override
	public ResultVo addJdProprietaryProduct(Long goodId) {
		
		return gjfProductInfoService.addJdProprietaryProduct(goodId);
	}

	/**
	 * 添加供应商信息
	 */
	@Override
	public ResultVo addSupplierInfo(GjfSupplierInfo supplier) {
		
		return gjfProductInfoService.addSupplierInfo(supplier);
	}

	/**
	 * 修改供应商信息
	 */
	@Override
	public ResultVo updateSupplierInfo(GjfSupplierInfo supplier) {
		
		return gjfProductInfoService.updateSupplierInfo(supplier);
	}

	/**
	 * 后台获取供应商列表信息
	 */
	@Override
	public ResultVo findSupplierInfoByPage(Integer pageNo, Integer pageSize, String mobile) {
		
		return gjfProductInfoService.findSupplierInfoByPage(pageNo, pageSize, mobile);
	}

	/**
	 * 获取全部供应商信息
	 */
	@Override
	public ResultVo findAllSupplierInfo() {
		
		return gjfProductInfoService.findAllSupplierInfo();
	}

	/**
	 * 获取供应商详细信息
	 */
	@Override
	public ResultVo findSupplierInfoById(Long supId) {
		
		return gjfProductInfoService.findSupplierInfoById(supId);
	}

	/**
	 * 拉取自营商城产品
	 */
	@Override
	public ResultVo addProprietaryPro(String isOwn, String isCous, String isWho, String iSvou,String rateBegin) {
		
		return gjfProductInfoService.addProprietaryPro(isOwn, isCous, isWho, iSvou,rateBegin);
	}

	/**
	 * 天天易购商城首页
	 */
	@Override
	public ResultVo findProductInfoIndex() {
	
		return gjfProductInfoService.findProductInfoIndex();
	}

	/**
	 * 更新成品池商品信息
	 * @param pro
	 * @return
	 */
	@Override
	public ResultVo updateProprietaryPro(ProductInfo pro) {
		
		return gjfProductInfoService.updateProprietaryPro(pro);
	}

	/**
	 * 查询pc端采购商品信息
	 * @param columnId
	 * @param likeName
	 * @param pageNo
	 * @param pageSize
	 * @param discount
	 * @return
	 */
	@Override
	public ResultVo findMerchantProcurementProductInPc(Long columnId, String likeName, Integer pageNo, Integer pageSize,
			String discount) {
		
		return gjfProductInfoService.findMerchantProcurementProductInPc(columnId, likeName, pageNo, pageSize, discount);
	}
	
	
	
}
