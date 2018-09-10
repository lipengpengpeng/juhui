package cc.messcat.gjfeng.service.product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.hibernate.loader.plan.spi.BidirectionalEntityReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cc.messcat.gjfeng.common.bean.Pager;
import cc.messcat.gjfeng.common.constant.CommonStatus;
import cc.messcat.gjfeng.common.dto.Arrts;
import cc.messcat.gjfeng.common.exception.MyException;
import cc.messcat.gjfeng.common.jd.JdNewUtil;
import cc.messcat.gjfeng.common.jd.JdUtil;
import cc.messcat.gjfeng.common.jd.bean.GoodDetail;
import cc.messcat.gjfeng.common.jd.bean.ProductStock;
import cc.messcat.gjfeng.common.proprietary.bean.Classify;
import cc.messcat.gjfeng.common.proprietary.bean.ProductInfo;
import cc.messcat.gjfeng.common.proprietary.bean.SperGoodsPrice;
import cc.messcat.gjfeng.common.proprietary.util.ProUtil;
import cc.messcat.gjfeng.common.util.BeanUtil;
import cc.messcat.gjfeng.common.util.BeanUtilsEx;
import cc.messcat.gjfeng.common.util.ObjValid;
import cc.messcat.gjfeng.common.util.Sha256;
import cc.messcat.gjfeng.common.util.StringUtil;
import cc.messcat.gjfeng.common.vo.app.EnterpriseColumnVo;
import cc.messcat.gjfeng.common.vo.app.NetFriendProductInfoVo;
import cc.messcat.gjfeng.common.vo.app.ProductInfoVo;
import cc.messcat.gjfeng.common.vo.app.ResultVo;
import cc.messcat.gjfeng.dao.product.GjfProductInfoDao;
import cc.messcat.gjfeng.entity.GjfAttrValue;
import cc.messcat.gjfeng.entity.GjfEnterpriseColumn;
import cc.messcat.gjfeng.entity.GjfJdGoods;
import cc.messcat.gjfeng.entity.GjfMemberInfo;
import cc.messcat.gjfeng.entity.GjfProductAttr;
import cc.messcat.gjfeng.entity.GjfProductAttrStock;
import cc.messcat.gjfeng.entity.GjfProductColumn;
import cc.messcat.gjfeng.entity.GjfProductInfo;
import cc.messcat.gjfeng.entity.GjfProductViewHistory;
import cc.messcat.gjfeng.entity.GjfSetBaseInfo;
import cc.messcat.gjfeng.entity.GjfStoreInfo;
import cc.messcat.gjfeng.entity.GjfSupplierInfo;
import cc.messcat.gjfeng.service.system.GjfEnterpriseColumnService;
import net.sf.json.JSONObject;

@Service("gjfProductInfoService")
public class GjfProductInfoServiceImpl implements GjfProductInfoService {

	@Autowired
	@Qualifier("gjfProductInfoDao")
	private GjfProductInfoDao gjfProductInfoDao;

	@Autowired
	@Qualifier("gjfEnterpriseColumnService")
	private GjfEnterpriseColumnService gjfEnterpriseColumnService;

	@Autowired
	@Qualifier("gjfProductAttrService")
	private GjfProductAttrService gjfProductAttrService;

	@Autowired
	@Qualifier("gjfProductAttrStockService")
	private GjfProductAttrStockService gjfProductAttrStockService;

	/*
	 * 查询O2O商城首页的轮播图和商品栏目类型
	 * 
	 * @see cc.messcat.gjfeng.service.product.GjfProductInfoService#
	 * findO2OIndexColumn()
	 */
	@Override
	public ResultVo findO2OIndexColumn() {
		// 查询所有属于一级商品栏目的所有商品栏目分类
		return gjfEnterpriseColumnService.findIndexColumn("0", 1L);
	}

	/*
	 * 查询O2商品的猜你喜欢
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.product.GjfProductInfoService#findO2OIndexPro(
	 * int, int, java.lang.Long, java.lang.Long, java.lang.Long)
	 */
	@Override
	public ResultVo findO2OIndexPro(int pageNo, int pageSize, Double longitude, Double latitude, Long cityId) {
		return new ResultVo(200, "查询成功",
				gjfProductInfoDao.findO2OIndexPro(pageNo, pageSize, longitude, latitude, cityId));
	}

	/*
	 * 查询O2O商城的栏目下的商品列表
	 * 
	 * @see cc.messcat.gjfeng.service.product.GjfProductInfoService#
	 * findO2OSubClassPro(int, int, java.lang.Long, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public ResultVo findO2OSubClassPro(int pageNo, int pageSize, Long columnId, String columnType, String orderType,
			String likeValue, Double longitude, Double latitude, Long cityId) {
		if (columnType.equals("2")) {
			return new ResultVo(200, "查询成功", gjfProductInfoDao.findProByColumnId(columnId, pageNo, pageSize, "1",
					orderType, likeValue, longitude, latitude, cityId));
		} else {
			return new ResultVo(200, "查询成功", gjfProductInfoDao.findProByFatherColumnId(columnId, pageNo, pageSize, "1",
					orderType, likeValue, longitude, latitude, cityId));
		}
	}

	/*
	 * 查询O2O商城的附近商品
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.product.GjfProductInfoService#findO2ONearsPro(
	 * int, int, java.lang.Long, java.lang.String, java.lang.Double,
	 * java.lang.Double, java.lang.Long)
	 */
	public ResultVo findO2ONearsPro(int pageNo, int pageSize, Long columnId, String likeValue, Double longitude,
			Double latitude, Long cityId) {
		return findO2OSubClassPro(pageNo, pageSize, columnId, "0", "1", likeValue, longitude, latitude, cityId);
	}

	/*
	 * 查询网上商城首页展示的一级栏目
	 * 
	 * @see cc.messcat.gjfeng.service.product.GjfProductInfoService#
	 * findShopIndexCloumn()
	 */
	@Override
	public ResultVo findShopIndexCloumn() {
		return gjfEnterpriseColumnService.findIndexColumn("1", 1L);
	}

	/*
	 * 查询网上商城首页推荐等商品
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.product.GjfProductInfoService#findShopIndexPro(
	 * )
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ResultVo findShopIndexPro() {
		
		// 首页展示商品，如今日推荐
		Map<String, Object> dataMap = new LinkedHashMap<String, Object>();
		Object o = gjfEnterpriseColumnService.findIndexColumn("1", 5L).getResult();
		if (ObjValid.isValid(o)) {
			List<EnterpriseColumnVo> columns = (List<EnterpriseColumnVo>) o;
			for (EnterpriseColumnVo enterpriseColumnVo : columns) {
				dataMap.put(
						enterpriseColumnVo.getShortName() + ";" + enterpriseColumnVo.getPic2() + "~"
								+ enterpriseColumnVo.getId(),
						findProByColumnId(enterpriseColumnVo.getId(), 1, 6, "", null, null).getResult()); // 所有商品
			}
		}
		return new ResultVo(200, "查询成功", dataMap);
	}

	/*
	 * 根据id查询商品信息
	 * 
	 */
	@Override
	public ResultVo findProductById(Long id) {
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("id", id);
		return new ResultVo(200, "查询成功", gjfProductInfoDao.query(GjfProductInfo.class, attrs));
	}

	/*
	 * 查询o2o商品信息
	 * 
	 * @see cc.messcat.gjfeng.service.product.GjfProductInfoService#
	 * findO2OProductById(java.lang.Long)
	 */
	@Override
	public ResultVo findO2OProductById(Long id, Double longitude, Double latitude) {
		ProductInfoVo productInfoVo = gjfProductInfoDao.findO2OProductById(id, longitude, latitude);
		return new ResultVo(200, "查询成功", productInfoVo);
	}

	/*
	 * 根据id查询商品信息
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.product.GjfProductInfoService#findProductById(
	 * java.lang.Long)
	 */
	@Override
	public ResultVo findProductById(Long id, Long storeId) {
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("id", id);
		if (ObjValid.isValid(storeId)) {
			attrs.put("storeId.id", storeId);
		}
		GjfProductInfo gjfProductInfo = gjfProductInfoDao.query(GjfProductInfo.class, attrs);
		return new ResultVo(200, "查询成功", gjfProductInfo);
	}

	/*
	 * 根据id和token查询商品信息
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.product.GjfProductInfoService#findProductById(
	 * java.lang.Long, java.lang.String)
	 */
	@Override
	public ResultVo findProductById(Long id, String token) {
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("id", id);
		attrs.put("token", token);
		return new ResultVo(200, "查询成功", gjfProductInfoDao.query(GjfProductInfo.class, attrs));
	}

	/*
	 * 根据商品栏目查询商品信息
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.product.GjfProductInfoService#findProByColumnId
	 * (java.lang.Long, int, int)
	 */
	@Override
	public ResultVo findProByColumnId(Long columnId, int pageNo, int pageSize, String likeValue, Double longitude,
			Double latitude) {
		return new ResultVo(200, "查询成功", gjfProductInfoDao.findProByColumnId(columnId, pageNo, pageSize, "2", "",
				likeValue, longitude, latitude, null));
	}

	/*
	 * 查询父类栏目下的所有商品
	 * 
	 * @see cc.messcat.gjfeng.service.product.GjfProductInfoService#
	 * findProByFatherColumnId(java.lang.Long, int, int)
	 */
	public ResultVo findProByFatherColumnId(Long columnId, int pageNo, int pageSize, String likeValue, Double longitude,
			Double latitude) {
		return new ResultVo(200, "查询成功", gjfProductInfoDao.findProByFatherColumnId(columnId, pageNo, pageSize, "2", "",
				likeValue, longitude, latitude, null));
	}

	/*
	 * 查询本店热销商品
	 * 
	 * @see cc.messcat.gjfeng.service.product.GjfProductInfoService#
	 * findHotProByStoreId(java.lang.Long, int, int)
	 */
	public ResultVo findHotProByStoreId(Long goodsId, int pageNo, int pageSize) {
		return new ResultVo(200, "查询成功", gjfProductInfoDao.findHotProByStoreId(goodsId, pageNo, pageSize));

	}

	/*
	 * 根据店铺id查询商品信息
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.product.GjfProductInfoService#findProByStore(
	 * java.lang.Long, int, int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ResultVo findProByStore(Long storeId, int pageNo, int pageSize) {
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("storeId.id", storeId);
		attrs.put("status", "1");
		return new ResultVo(200, "查询成功", BeanUtilsEx.changeList(gjfProductInfoDao.queryList(GjfProductInfo.class,
				(pageNo - 1) * pageSize, pageSize, "addTime", "desc", attrs), ProductInfoVo.class));
	}

	/*
	 * 根据店铺id和token查询商品信息
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.product.GjfProductInfoService#findProByStore(
	 * java.lang.Long, int, int, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ResultVo findProByStore(Long storeId, int pageNo, int pageSize, String token) {
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("storeId.id", storeId);
		attrs.put("storeId.token", token);
		return new ResultVo(200, "查询成功", gjfProductInfoDao.queryList(GjfProductInfo.class, (pageNo - 1) * pageSize,
				pageSize, "addTime", "desc", attrs));
	}

	/*
	 * 发布O2O商品
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.product.GjfProductInfoService#addO2OProduct(cc.
	 * messcat.gjfeng.common.vo.app.ProductInfoVo, java.lang.Long,
	 * java.lang.String)
	 */
	@Override
	public ResultVo addO2OProduct(ProductInfoVo productInfoVo, Long storeId, Long columnId, String account) {
		if (ObjValid.isNotValid(productInfoVo, storeId, columnId) || StringUtil.isBlank(account)) {
			throw new MyException(400, "信息有误，请重新填写", null);
		}
		Object o = gjfProductInfoDao.get(columnId, GjfEnterpriseColumn.class.getName());
		if (ObjValid.isNotValid(o)) {
			throw new MyException(400, "请选择商品栏目", null);
		}
		Object o1 = gjfProductInfoDao.get(storeId, GjfStoreInfo.class.getName());
		if (ObjValid.isNotValid(o)) {
			throw new MyException(400, "请选择商品栏目", null);
		}

		if (StringUtil.isBlank(productInfoVo.getImgUrl())) {
			throw new MyException(400, "请上传商品图片", null);
		}

		if (StringUtil.isBlank(productInfoVo.getName())) {
			throw new MyException(400, "请填写商品名称", null);
		}
		if (StringUtil.isBlank(productInfoVo.getNotice())) {
			throw new MyException(400, "请填写商品购买须知", null);
		}
		if (null == productInfoVo.getPrice()) {
			throw new MyException(400, "请填写门市价格", null);
		}
		if (null == productInfoVo.getMarketPrice()) {
			throw new MyException(400, "请填写商品价格", null);
		}
		GjfProductInfo gjfProductInfo = new GjfProductInfo();
		gjfProductInfo.setStoreId((GjfStoreInfo) o1);
		gjfProductInfo.setColumnId((GjfEnterpriseColumn) o);
		gjfProductInfo.setName(productInfoVo.getName());
		gjfProductInfo.setImgUrl(productInfoVo.getImgUrl());
		gjfProductInfo.setPara1(productInfoVo.getImgUrl1());
		gjfProductInfo.setNotice(productInfoVo.getNotice());
		gjfProductInfo.setPrice(productInfoVo.getPrice());
		gjfProductInfo.setMarketPrice(productInfoVo.getMarketPrice());
		gjfProductInfo.setGcostPrice(new BigDecimal(0));
		gjfProductInfo.setIndate(productInfoVo.getIndate());
		gjfProductInfo.setCollectionNum(0L);
		gjfProductInfo.setSalesNum(0L);
		gjfProductInfo.setViewNum(0L);
		gjfProductInfo.setIsHot("0");
		gjfProductInfo.setIsNew("1");
		gjfProductInfo.setIsRecommend("0");
		gjfProductInfo.setIsQbuy("0");
		gjfProductInfo.setScore(new BigDecimal(5));
		gjfProductInfo.setAddTime(new Date());
		gjfProductInfo.setAduitStatus("1");
		gjfProductInfo.setStatus("1");
		gjfProductInfo.setPostage(new BigDecimal(0));
		gjfProductInfo.setPointNicePrice(new BigDecimal(0));
		gjfProductInfo.setBenerfitMoney(new BigDecimal(0));
		gjfProductInfo.setHonourPrice(new BigDecimal(0));
		gjfProductInfo.setStandardPrice(new BigDecimal(0));
		gjfProductInfo.setIsWholesale("0");
		gjfProductInfo.setDerectMemberVor(new BigDecimal(0));
		gjfProductInfo.setFirstDerectMemberVor(new BigDecimal(0));
		
		gjfProductInfo.setToken(Sha256.getSha256Hash(gjfProductInfo.getName(), gjfProductInfo.getAduitStatus(),
				CommonStatus.SIGN_KEY_NUM));
		gjfProductInfoDao.save(gjfProductInfo);

		GjfProductColumn gjfProductColumn = new GjfProductColumn();
		gjfProductColumn.setColumnId((GjfEnterpriseColumn) o);
		gjfProductColumn.setProductId(gjfProductInfo);
		gjfProductInfoDao.save(gjfProductColumn);
		return new ResultVo(200, "商品发布成功，请等待审核", null);
	}

	/*
	 * 发布网上商城商品
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.product.GjfProductInfoService#addShopProduct(cc
	 * .messcat.gjfeng.entity.GjfProductInfo)
	 */
	@Override
	public ResultVo addShopProduct(GjfProductInfo gjfProductInfo, List<Arrts> arrts, Long columnId) {
		if (StringUtil.isBlank(gjfProductInfo.getName())) {
			throw new MyException(400, "商品名称不能为空", null);
		}
		if (StringUtil.isBlank(gjfProductInfo.getSerialNum())) {
			throw new MyException(400, "商品货号不能为空", null);
		}
		if (StringUtil.isBlank(gjfProductInfo.getPrice().toString())) {
			throw new MyException(400, "商品价格不能为空", null);
		}
		if (StringUtil.isBlank(gjfProductInfo.getGcostPrice().toString())) {
			throw new MyException(400, "成本价格不能为空", null);
		}
		if (StringUtil.isBlank(gjfProductInfo.getMarketPrice().toString())) {
			throw new MyException(400, "市场价格不能为空", null);
		}
		if (ObjValid.isNotValid(columnId)) {
			throw new MyException(400, "请选择商品栏目", null);
		}
		if (null != arrts && arrts.size() > 0) {
			for (int i = 0; i < arrts.size(); i++) {
				if (ObjValid.isValid(arrts.get(i))) {
					StringBuffer sb = new StringBuffer();
					for (int x = 0; x < arrts.get(i).getId().size(); x++) {
						sb.append(arrts.get(i).getId().get(x));
					}
					List<Arrts> temp = new ArrayList<>();
					temp.addAll(arrts);
					temp.remove(i);
					for (int n = 0; n < temp.size(); n++) {
						StringBuffer id = new StringBuffer();
						for (int z = 0; z < temp.get(n).getId().size(); z++) {
							id.append(temp.get(n).getId().get(z));
						}
						if (sb.toString().equals(id.toString())) {
							return new ResultVo(400, "商品属性重复啦！", null);
						}
					}
				}
			}
		}
		Object o = gjfProductInfoDao.get(columnId, GjfEnterpriseColumn.class.getName());
		if (ObjValid.isNotValid(o)) {
			throw new MyException(400, "商品栏目为空", null);
		}
		GjfEnterpriseColumn enterpriseColumn = (GjfEnterpriseColumn) o;
		gjfProductInfo.setColumnId(enterpriseColumn);
		// gjfStoreInfo自营店铺
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("storePro", "1");
		GjfStoreInfo gjfStoreInfo = gjfProductInfoDao.query(GjfStoreInfo.class, attrs);
		if (null == gjfStoreInfo) {
			throw new MyException(400, "添加失败!自营店铺不存在", null);
		} else {
			gjfProductInfo.setStoreId(gjfStoreInfo);
			gjfProductInfo.setAduitStatus("1"); // 审核通过
			gjfProductInfo.setStatus("1"); // 状态
			//gjfProductInfo.setSalesNum(0L); // 销量
			gjfProductInfo.setViewNum(0L); // 浏览量
			gjfProductInfo.setCollectionNum(0L); // 收藏数量
			gjfProductInfo.setToken(Sha256.getSha256Hash(gjfProductInfo.getName(), gjfProductInfo.getSerialNum(),
					CommonStatus.SIGN_KEY_NUM)); // 安全token
			gjfProductInfo.setAddTime(new Date());
			gjfProductInfo.setUpdateTime(new Date());
			gjfProductInfo.setId(null);
			gjfProductInfo.setSuorceGoods("0");
		}
		gjfProductInfoDao.save(gjfProductInfo);

		GjfProductColumn productColumn = new GjfProductColumn();
		productColumn.setProductId(gjfProductInfo);
		productColumn.setColumnId(enterpriseColumn);
		Map<String, Object> attrs1 = new HashMap<>();
		attrs1.put("productId.id", productColumn.getProductId().getId());
		attrs1.put("columnId.id", productColumn.getColumnId().getId());

		gjfProductInfoDao.delete(GjfProductColumn.class, attrs1);
		gjfProductInfoDao.save(productColumn);

		for (int i = 0; i < arrts.size(); i++) {
			if (ObjValid.isValid(arrts.get(i))) {
				String ids = ""; // 属性组合
				for (int j = 0; j < arrts.get(i).getId().size(); j++) {
					Object attrValueObj = gjfProductInfoDao.get(
							Long.valueOf(String.valueOf(arrts.get(i).getId().get(j))), GjfAttrValue.class.getName());
					if (ObjValid.isNotValid(attrValueObj)) {
						throw new MyException(400, "添加失败,商品属性不存在!", null);
					}
					GjfAttrValue gjfAttrValue = (GjfAttrValue) attrValueObj;
					Map<String, Object> attrs2 = new HashMap<String, Object>();
					attrs2.put("productId.id", gjfProductInfo.getId());
					attrs2.put("attrValueId.id", gjfAttrValue.getId());
					GjfProductAttr productAttr = gjfProductInfoDao.query(GjfProductAttr.class, attrs2);
					if (ObjValid.isNotValid(productAttr)) {
						GjfProductAttr gjfProductAttr = new GjfProductAttr();
						gjfProductAttr.setAttrValueId(gjfAttrValue);
						gjfProductAttr.setProductId(gjfProductInfo);
						gjfProductAttr.setOrderBy((long) j + 1);
						ResultVo resultVo = gjfProductAttrService.addProductAttr(gjfProductAttr);
						ids += resultVo.getResult() + ",";
					} else {
						ids += productAttr.getId() + ",";
					}
				}
				GjfProductAttrStock gjfProductAttrStock = new GjfProductAttrStock();
				gjfProductAttrStock.setProductId(gjfProductInfo);
				gjfProductAttrStock.setAddTime(new Date());
				gjfProductAttrStock.setEditTime(new Date());
				gjfProductAttrStock.setPrice(arrts.get(i).getPrice());
				gjfProductAttrStock.setRepertory(arrts.get(i).getRepertory());
				gjfProductAttrStock.setStatus("1");
				gjfProductAttrStock.setProductAttrIds(ids);
				gjfProductAttrStock.setStandardPrice(arrts.get(i).getStandardPrice());
				gjfProductAttrStock.setHonourPrice(arrts.get(i).getHonourPrice());
				gjfProductAttrStockService.addProductAttrStock(gjfProductAttrStock);
			}
		}
		return new ResultVo(200, "商品发布成功", null);
	}

	/*
	 * 修改O2O商品信息
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.product.GjfProductInfoService#updateO2OProduct(
	 * cc.messcat.gjfeng.common.vo.app.ProductInfoVo, java.lang.Long,
	 * java.lang.Long)
	 */
	public ResultVo updateO2OProduct(ProductInfoVo productInfoVo, Long storeId, Long columnId) {
		if (ObjValid.isNotValid(productInfoVo, storeId, columnId) || ObjValid.isNotValid(productInfoVo.getId())) {
			throw new MyException(400, "信息有误，请重新填写", null);
		}
		Object o = gjfProductInfoDao.get(columnId, GjfEnterpriseColumn.class.getName());
		if (ObjValid.isNotValid(o)) {
			throw new MyException(400, "请选择商品栏目", null);
		}
		Object o1 = gjfProductInfoDao.get(storeId, GjfStoreInfo.class.getName());
		if (ObjValid.isNotValid(o)) {
			throw new MyException(400, "请选择店铺栏目", null);
		}
		if (StringUtil.isBlank(productInfoVo.getName())) {
			throw new MyException(400, "请填写商品名称", null);
		}
		if (StringUtil.isBlank(productInfoVo.getNotice())) {
			throw new MyException(400, "请填写商品购买须知", null);
		}
		if (null == productInfoVo.getPrice()) {
			throw new MyException(400, "请填写门市价格", null);
		}
		if (null == productInfoVo.getMarketPrice()) {
			throw new MyException(400, "请填写商品价格", null);
		}
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("id", productInfoVo.getId());
		attrs.put("storeId.id", storeId);
		GjfProductInfo gjfProductInfo = gjfProductInfoDao.query(GjfProductInfo.class, attrs);
		if (ObjValid.isNotValid(gjfProductInfo)) {
			throw new MyException(400, "请选择商品", null);
		}
		Long ProColumnId = gjfProductInfo.getColumnId().getId();
		gjfProductInfo.setStoreId((GjfStoreInfo) o1);
		gjfProductInfo.setColumnId((GjfEnterpriseColumn) o);
		gjfProductInfo.setName(productInfoVo.getName());
		if (StringUtil.isNotBlank(productInfoVo.getImgUrl())) {
			gjfProductInfo.setImgUrl(productInfoVo.getImgUrl());
		}
		gjfProductInfo.setNotice(productInfoVo.getNotice());
		gjfProductInfo.setPrice(productInfoVo.getPrice());
		gjfProductInfo.setMarketPrice(productInfoVo.getMarketPrice());
		gjfProductInfo.setGcostPrice(new BigDecimal(0));
		gjfProductInfo.setIndate(productInfoVo.getIndate());
		gjfProductInfo.setUpdateTime(new Date());
		gjfProductInfo.setAduitStatus("2");
		gjfProductInfo.setStatus("1");
		gjfProductInfo.setToken(Sha256.getSha256Hash(gjfProductInfo.getName(), gjfProductInfo.getAduitStatus(),
				CommonStatus.SIGN_KEY_NUM));
		gjfProductInfoDao.update(gjfProductInfo);

		Map<String, Object> attrsColumn = new HashMap<String, Object>();
		attrsColumn.put("productId.id", gjfProductInfo.getId());
		attrsColumn.put("columnId.id", ProColumnId);
		GjfProductColumn gjfProductColumn = gjfProductInfoDao.query(GjfProductColumn.class, attrsColumn);
		if (ObjValid.isNotValid(gjfProductColumn)) {
			throw new MyException(400, "商品栏目信息错误", null);
		}
		gjfProductColumn.setColumnId((GjfEnterpriseColumn) o);
		gjfProductInfoDao.update(gjfProductColumn);
		return new ResultVo(200, "商品修改成功，请等待审核", null);
	}

	/*
	 * 修改网上商城商品信息
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.product.GjfProductInfoService#updateShopProduct
	 * (cc.messcat.gjfeng.entity.GjfProductInfo, java.util.List)
	 */
	@Override
	public ResultVo updateShopProduct(Long id, GjfProductInfo gjfProductInfo, List<Arrts> arrts) {
		if (StringUtil.isBlank(gjfProductInfo.getName())) {
			throw new MyException(400, "商品名称不能为空", null);
		}
		if (StringUtil.isBlank(gjfProductInfo.getSerialNum())) {
			throw new MyException(400, "商品货号不能为空", null);
		}
		/*
		 * if (StringUtil.isBlank(gjfProductInfo.getRepertory().toString())) {
		 * throw new MyException(400, "商品库存不能为空", null); }
		 */
		if (StringUtil.isBlank(gjfProductInfo.getPrice().toString())) {
			throw new MyException(400, "商品价格不能为空", null);
		}
		if (StringUtil.isBlank(gjfProductInfo.getMarketPrice().toString())) {
			throw new MyException(400, "市场价格不能为空", null);
		}
		if (StringUtil.isBlank(gjfProductInfo.getGcostPrice().toString())) {
			throw new MyException(400, "成本价不能为空", null);
		}
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("id", id);
		GjfProductInfo productInfo = gjfProductInfoDao.query(GjfProductInfo.class, attrs);
		if (ObjValid.isNotValid(productInfo)) {
			throw new MyException(400, "商品不存在", null);
		}
		productInfo.setName(gjfProductInfo.getName());
		productInfo.setSerialNum(gjfProductInfo.getSerialNum());
		productInfo.setKeywords(gjfProductInfo.getKeywords());
		productInfo.setDescription(gjfProductInfo.getDescription());
		productInfo.setContent(gjfProductInfo.getContent());
		productInfo.setIsNew(gjfProductInfo.getIsNew());
		productInfo.setIsSale(gjfProductInfo.getIsSale());
		productInfo.setIsHot(gjfProductInfo.getIsHot());
		productInfo.setIsQbuy(gjfProductInfo.getIsQbuy());
		productInfo.setIsNew(gjfProductInfo.getIsNew());
		productInfo.setIsRecommend(gjfProductInfo.getIsRecommend());
		productInfo.setUpdateTime(new Date());
		productInfo.setRemarkInfo(gjfProductInfo.getRemarkInfo());
		// productInfo.setRepertory(gjfProductInfo.getRepertory());
		productInfo.setPrice(gjfProductInfo.getPrice());
		productInfo.setGcostPrice(gjfProductInfo.getGcostPrice());
		productInfo.setMarketPrice(gjfProductInfo.getMarketPrice());
		productInfo.setIsCanUserCou(gjfProductInfo.getIsCanUserCou());
		productInfo.setPostage(gjfProductInfo.getPostage());
		productInfo.setPointNum(gjfProductInfo.getPointNum());
		productInfo.setPointNicePrice(gjfProductInfo.getPointNicePrice());
		productInfo.setPurchasNum(gjfProductInfo.getPurchasNum());
		productInfo.setBenerfitMoney(gjfProductInfo.getBenerfitMoney());
		productInfo.setSalesNum(gjfProductInfo.getSalesNum());
		productInfo.setIsWholesale(gjfProductInfo.getIsWholesale());
		productInfo.setStandardPrice(gjfProductInfo.getStandardPrice());
		productInfo.setHonourPrice(gjfProductInfo.getHonourPrice());
		productInfo.setIsUpgradePro(gjfProductInfo.getIsUpgradePro());
		productInfo.setMultipleNumber(gjfProductInfo.getMultipleNumber());
		productInfo.setDerectMemberVor(gjfProductInfo.getDerectMemberVor());
		productInfo.setFirstDerectMemberVor(gjfProductInfo.getFirstDerectMemberVor());
		if (null != gjfProductInfo.getImgUrl()) {
			productInfo.setImgUrl(gjfProductInfo.getImgUrl());
		}
		if (null != gjfProductInfo.getPara1()) {
			productInfo.setPara1(gjfProductInfo.getPara1());
		}
		if (null != gjfProductInfo.getPara2()) {
			productInfo.setPara2(gjfProductInfo.getPara2());
		}
		if (null != gjfProductInfo.getPara3()) {
			productInfo.setPara3(gjfProductInfo.getPara3());
		}
		if (null != gjfProductInfo.getPara4()) {
			productInfo.setPara4(gjfProductInfo.getPara4());
		}
		if (null != gjfProductInfo.getPara5()) {
			productInfo.setPara5(gjfProductInfo.getPara5());
		}
		gjfProductInfoDao.update(productInfo);
		if (arrts != null && arrts.size() > 0) {
			for (int i = 0; i < arrts.size(); i++) {
				GjfProductAttrStock gjfProductAttrStock = new GjfProductAttrStock();
				gjfProductAttrStock = (GjfProductAttrStock) gjfProductAttrStockService
						.findProductAttrStockById(arrts.get(i).getProStockId()).getResult();
				if (gjfProductAttrStock != null) {
					gjfProductAttrStock.setPrice(arrts.get(i).getPrice());
					gjfProductAttrStock.setRepertory(arrts.get(i).getRepertory());
					gjfProductAttrStock.setEditTime(new Date());
					gjfProductAttrStock.setHonourPrice(arrts.get(i).getHonourPrice());
					gjfProductAttrStock.setStandardPrice(arrts.get(i).getStandardPrice());
					gjfProductAttrStockService.updateProductAttrStock(gjfProductAttrStock);
				}
			}
		}
		return new ResultVo(200, "商品修改成功", null);
	}

	/*
	 * 修改商品的审核状态
	 * 
	 * @see cc.messcat.gjfeng.service.product.GjfProductInfoService#
	 * updateProAduitStatus(java.lang.Long, java.lang.String, java.lang.String)
	 */
	@Override
	public ResultVo updateProAduitStatus(Long id, String aduitStatus, String token) {
		if (StringUtil.isBlank(aduitStatus)
				|| (!"0".equals(aduitStatus) && !"1".equals(aduitStatus) && !"2".equals(aduitStatus))) {
			throw new MyException(400, "修改状态有误", null);
		}
		Object o = findProductById(id, token).getResult();
		if (ObjValid.isNotValid(o)) {
			throw new MyException(400, "商品不存在", null);
		}
		GjfProductInfo gjfProductInfo = (GjfProductInfo) o;
		gjfProductInfo.setAduitStatus(aduitStatus);
		gjfProductInfoDao.update(gjfProductInfo);
		return new ResultVo(200, "修改成功", null);
	}

	/*
	 * 修改商品状态
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.product.GjfProductInfoService#updateProStatus(
	 * java.lang.Long, java.lang.String, java.lang.String)
	 */
	@Override
	public ResultVo updateProStatus(Long id, String status, String token) {
		if (StringUtil.isBlank(status) || (!"0".equals(status) && !"1".equals(status) && !"2".equals(status))) {
			throw new MyException(400, "修改状态有误", null);
		}
		Object o = findProductById(id, token).getResult();
		if (ObjValid.isNotValid(o)) {
			throw new MyException(400, "商品不存在", null);
		}
		GjfProductInfo gjfProductInfo = (GjfProductInfo) o;
		gjfProductInfo.setStatus(status);
		gjfProductInfoDao.update(gjfProductInfo);
		return new ResultVo(200, "修改成功", null);
	}

	/*
	 * 下架商品
	 * 
	 * @see
	 * cc.messcat.gjfeng.service.product.GjfProductInfoService#delProduct(java.
	 * lang.Long, java.lang.Long)
	 */
	@Override
	public ResultVo delProduct(Long[] ids, Long storeId) {
		int row = gjfProductInfoDao.delProduct(ids, storeId);
		return new ResultVo(row > 0 ? 200 : 400, row > 0 ? "下架成功" : "下架失败", null);
	}

	/*
	 * 添加商品浏览记录
	 * 
	 * @see cc.messcat.gjfeng.service.product.GjfProductInfoService#
	 * addProductViewHistory(java.lang.Long,
	 * cc.messcat.gjfeng.entity.GjfMemberInfo)
	 */
	@Override
	public ResultVo addProductViewHistory(Long goodsId, GjfMemberInfo memberInfo) {
		if (ObjValid.isNotValid(memberInfo)) {
			return new ResultVo(400, "操作失败", null);
		}
		// 添加用户浏览记录
		Map<String, Object> attrs = new HashMap<String, Object>();
		attrs.put("id", goodsId);
		GjfProductInfo productInfo = gjfProductInfoDao.query(GjfProductInfo.class, attrs);
		if (ObjValid.isNotValid(productInfo)) {
			return new ResultVo(400, "操作失败", null);
		}
		productInfo.setViewNum(productInfo.getViewNum() + 1);
		gjfProductInfoDao.update(productInfo);
		// 修改商品的浏览量
		if (ObjValid.isNotValid(productInfo.getColumnId())) {
			return new ResultVo(400, "操作失败", null);
		}
		String columnName = productInfo.getColumnId().getNames();
		GjfProductViewHistory history = new GjfProductViewHistory(null, memberInfo, productInfo, columnName,
				new Date());
		gjfProductInfoDao.save(history);
		return new ResultVo(200, "操作成功", null);
	}

	@Override
	public ResultVo findHotProductsByStoreId(Long storeId, Map<String, Object> param) {
		return new ResultVo(200, "操作成功", gjfProductInfoDao.findHotProductsByStoreId(storeId, param));
	}

	/**
	 * @描述 app商品栏目类型
	 * @date 2017年1月4日
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ResultVo findO2OIndexColumnInApp() {
		List allInfoList = new ArrayList<>();
		List<EnterpriseColumnVo> columns = (List<EnterpriseColumnVo>) gjfEnterpriseColumnService
				.findIndexColumn("0", 1L).getResult();
		for (EnterpriseColumnVo coL : columns) {
			Map map = new HashMap<>();
			map.put("colunm", coL);
			List<EnterpriseColumnVo> subCol = (List<EnterpriseColumnVo>) gjfEnterpriseColumnService
					.findColumnByFatherId(coL.getId()).getResult();
			map.put("subColunm", subCol);
			allInfoList.add(map);
		}
		return new ResultVo(200, "操作成功", allInfoList);
	}

	/**
	 * 接入友品集商品信息
	 */
	@SuppressWarnings({ "rawtypes" })
	@Override
	public ResultVo addNetFriendsProductInfo(JSONObject json) {
		int errcode = (int) json.get("errcode");
		if (errcode != 0) {
			return new ResultVo(400, "请求失败", null);
		}
		// 查询友品集栏目
		Map<String, Object> attrs = new HashMap<>();
		attrs.put("frontNum", "netFriends");
		GjfEnterpriseColumn column = gjfProductInfoDao.query(GjfEnterpriseColumn.class, attrs);
		// 查询店铺
		attrs.remove("frontNum");
		attrs.put("id", 18L);
		GjfStoreInfo store = gjfProductInfoDao.query(GjfStoreInfo.class, attrs);
		// 获取商品集合
		JSONObject JsonObject = (JSONObject) json.get("data");
		List list = JsonObject.getJSONArray("list");
		// 迭代集合
		Iterator it = list.iterator();
		while (it.hasNext()) {
			// 获取友集网商品信息
			JSONObject object = (JSONObject) it.next();
			NetFriendProductInfoVo netProduct = (NetFriendProductInfoVo) JSONObject.toBean(object,
					NetFriendProductInfoVo.class);
			// 创建平台商品
			GjfProductInfo productInfo = new GjfProductInfo();
			// 添加友集网商品id
			productInfo.setNetProId(netProduct.getGid());
			// 商品名称
			productInfo.setName(netProduct.getTitle());
			// 商品关键字
			productInfo.setKeywords(netProduct.getShort_title());
			// 商品标签
			productInfo.setRemark(netProduct.getGoodslabel());
			// 国家图片
			productInfo.setPara8(netProduct.getCountry_img());
			// 实际价格
			productInfo.setPrice(netProduct.getMarketprice());
			//
			productInfo.setGcostPrice(netProduct.getSaleprice());
			// 销售价
			productInfo.setMarketPrice(netProduct.getMarketprice());
			
			BigDecimal bene = netProduct.getMarketprice().multiply(new BigDecimal(0.92));
			if (netProduct.getSaleprice().doubleValue() > bene.doubleValue()) {				
               continue;
			}
			
			//计算商品的消费积分
			BigDecimal banerfit=netProduct.getMarketprice().subtract(netProduct.getSaleprice());
			banerfit=banerfit.subtract(banerfit.multiply(new BigDecimal(0.3))).setScale(2, BigDecimal.ROUND_DOWN);
			BigDecimal totalBanefit=banerfit.divide(new BigDecimal(0.12),2,BigDecimal.ROUND_DOWN);
			if(totalBanefit.doubleValue()>netProduct.getMarketprice().doubleValue()){			
				productInfo.setBenerfitMoney(netProduct.getMarketprice());	
			}else{	
				productInfo.setBenerfitMoney(totalBanefit);	
			}						
			// 库存
			if (netProduct.getTotal() == -1) {
				productInfo.setRepertory(99999L);
			} else {
				productInfo.setRepertory(Long.valueOf(netProduct.getTotal()));
			}

			// 商品详情
			productInfo.setContent(netProduct.getContent());
			// 商品描述
			productInfo.setDescription(netProduct.getDesciption());
			// 商品展示缩略图
			productInfo.setImgUrl(netProduct.getThumb());
			// 商品图片
			productInfo.setPara1(netProduct.getThumb());
			// 销量
			productInfo.setSalesNum(Long.valueOf(netProduct.getSales()));
			// 新品
			productInfo.setIsNew(String.valueOf(netProduct.getIsNew()));
			// 热销
			productInfo.setIsHot(String.valueOf(netProduct.getIshot()));
			if (netProduct.getIssendfree() == 1) {
				productInfo.setPostage(new BigDecimal(0));
			}

			productInfo.setIsHavaRep("1");

			// 平台数据库需要设置的字段
			productInfo.setViewNum(0L);
			productInfo.setCollectionNum(0L);

			productInfo.setIsSale("0");
			productInfo.setIsQbuy("0");
			productInfo.setIsCanUserCou("0");
			productInfo.setIsRecommend("0");
			productInfo.setAddTime(new Date());
			productInfo.setScore(new BigDecimal(0));
			productInfo.setAduitStatus("1");
			productInfo.setStatus("1");
			productInfo.setToken(Sha256.getSha256Hash(productInfo.getName(), productInfo.getAduitStatus(),
					CommonStatus.SIGN_KEY_NUM));
			productInfo.setType("1");
			productInfo.setPostage(new BigDecimal(0));
			productInfo.setSalesNum(Long.valueOf((new Random().nextInt(10))));
			// 设置栏目
			productInfo.setColumnId(column);
			// 设置店铺
			productInfo.setStoreId(store);
			// 设置来源
			productInfo.setSuorceGoods("1");
			productInfo.setPurchasNum(999);
			gjfProductInfoDao.save(productInfo);

			// 添加关联
			gjfProductInfoDao.deleteProductColumn(productInfo.getId(), column.getId());
			GjfProductColumn gjfProductColumn = new GjfProductColumn();
			gjfProductColumn.setColumnId(column);
			gjfProductColumn.setProductId(productInfo);
			gjfProductInfoDao.save(gjfProductColumn);
		}
		return new ResultVo(200, "添加成功", null);
	}

	/**
	 * 接入京东商品数据
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public ResultVo addTestJdProductInfo(String type, String page,String sup,String rateBegin) {

		// 获取分类下的商品id
		// List goodIds = JdUtil.getJdGoodPool(type);
		for (int i = 1; i < 10000; i++) {
			List goodIds = JdUtil.goodsPoolOmni(type, i + "",sup,rateBegin);
			if (!BeanUtil.isValid(goodIds)) {
				return new ResultVo(200, "添加结束", null);
			}
			// 遍历商品id
			Iterator goodIt = goodIds.iterator();

			while (goodIt.hasNext()) {
				// 获取商品id
				String goodsId = (String) goodIt.next();
				Map<String, Object> map = new HashMap<>();
				map.put("jdGoodsId", goodsId);
				GjfJdGoods jdGoods = gjfProductInfoDao.query(GjfJdGoods.class, map);
				if (!BeanUtil.isValid(jdGoods)) {
					GjfJdGoods jdGood = new GjfJdGoods();
					jdGood.setJdGoodsId(goodsId);
					jdGood.setCatId(type);
					jdGood.setAddTime(new Date());

					gjfProductInfoDao.save(jdGood);
				}
				/*// 查询商品库存
				List<ProductStock> proList = JdUtil.getProductStore(goodsId.toString(), "27606,27607,27608,0");
				for (ProductStock stock : proList) {
					BigDecimal bene = stock.getShopPrice().multiply(new BigDecimal(0.92));
					if (stock.getPrice().doubleValue() < bene.doubleValue()) {
						

					}
				}*/
			}
		}

		return new ResultVo(200, "添加成功", null);
	}

	/**
	 * 
	 * @param goodDetail
	 * @return
	 */
	public void addJdProductToPlatform(GoodDetail goodDetail) {

		Map<String, Object> proMap=new HashMap<>();
		proMap.put("netProId", goodDetail.getGoodsId());
		GjfProductInfo pro=gjfProductInfoDao.query(GjfProductInfo.class, proMap);
		if(BeanUtil.isValid(pro)){
			return;
		}
		// 查询京东栏目
		Map<String, Object> attrs = new HashMap<>();
		attrs.put("frontNum", "JDSHOP");
		GjfEnterpriseColumn column = gjfProductInfoDao.query(GjfEnterpriseColumn.class, attrs);

		// 查询店铺
		attrs.remove("frontNum");
		attrs.put("storeIsOwnShop", "1");
		GjfStoreInfo store = gjfProductInfoDao.query(GjfStoreInfo.class, attrs);
		// 创建平台商品
		GjfProductInfo productInfo = new GjfProductInfo();
		// 添加j京东商品id
		productInfo.setNetProId(goodDetail.getGoodsId());
		// 商品名称
		productInfo.setName(goodDetail.getGoodsName());
		// 商品关键字
		productInfo.setKeywords(goodDetail.getBrandName());
		// 商品标签
		productInfo.setRemark(goodDetail.getFrom());
		// 国家图片
		productInfo.setPara8("");
		// 实际价格
		productInfo.setPrice(goodDetail.getShopPrice());
		//
		productInfo.setGcostPrice(goodDetail.getPrice());
		// 销售价
		productInfo.setMarketPrice(goodDetail.getShopPrice());
		// 库存
		if ("1".equals(goodDetail.getGoodNumber())) {
			productInfo.setRepertory(999L);
		} else {
			productInfo.setRepertory(0L);
		}
		// 商品详情
		productInfo.setContent(goodDetail.getGoodsDesc());
		// 商品描述
		productInfo.setDescription(goodDetail.getBrandName());
		// 商品展示缩略图
		productInfo.setImgUrl(goodDetail.getGoodsThumb());
		// 商品图片
		// productInfo.setPara1(netProduct.getThumb());
		// 销量
		productInfo.setSalesNum(0L);

		productInfo.setStatus(goodDetail.getIsOnSale());

		if ("1".equals(goodDetail.getGoodNumber())) {
			productInfo.setIsHavaRep("1");
		} else {
			productInfo.setIsHavaRep("0");
		}

		// 处理商品图片
		if (goodDetail.getGallery() != null && goodDetail.getGallery().size() > 0) {
			for (int i = 0; i < goodDetail.getGallery().size(); i++) {
				if (i == 0) {
					productInfo.setPara1(goodDetail.getGallery().get(i).getImgUrl());
				} else if (i == 1) {
					productInfo.setPara2(goodDetail.getGallery().get(i).getImgUrl());
				} else if (i == 2) {
					productInfo.setPara3(goodDetail.getGallery().get(i).getImgUrl());
				} else if (i == 3) {
					productInfo.setPara4(goodDetail.getGallery().get(i).getImgUrl());
				} else if (i == 4) {
					productInfo.setPara5(goodDetail.getGallery().get(i).getImgUrl());
				}
			}
		}

		// 平台数据库需要设置的字段
		productInfo.setViewNum(0L);
		productInfo.setCollectionNum(0L);
		productInfo.setIsNew("0");
		productInfo.setIsSale("0");
		productInfo.setIsHot("0");
		productInfo.setIsQbuy("0");
		productInfo.setIsCanUserCou("0");
		productInfo.setIsRecommend("0");
		productInfo.setAddTime(new Date());
		productInfo.setScore(new BigDecimal(0));
		productInfo.setAduitStatus("1");

		productInfo.setPointNicePrice(new BigDecimal(0.00));
		productInfo.setToken(
				Sha256.getSha256Hash(productInfo.getName(), productInfo.getAduitStatus(), CommonStatus.SIGN_KEY_NUM));
		productInfo.setType("1");
		productInfo.setPostage(new BigDecimal(0));
		productInfo.setSalesNum(Long.valueOf((new Random().nextInt(10))));
		// 设置栏目
		productInfo.setColumnId(column);
		// 设置店铺
		productInfo.setStoreId(store);
		// 设置来源
		productInfo.setSuorceGoods("2");
		productInfo.setPurchasNum(999);
		productInfo.setIsWholesale("0");
		productInfo.setHonourPrice(new BigDecimal(0));
		productInfo.setStandardPrice(new BigDecimal(0));
		gjfProductInfoDao.save(productInfo);

		// 添加关联
		/*gjfProductInfoDao.deleteProductColumn(productInfo.getId(), column.getId());
		GjfProductColumn gjfProductColumn = new GjfProductColumn();
		gjfProductColumn.setColumnId(column);
		gjfProductColumn.setProductId(productInfo);
		gjfProductInfoDao.save(gjfProductColumn);*/

	}

	/**
	 * 分页添加京东商品
	 */
	@Override
	public ResultVo addJdProductDetail(Integer pageNo, Integer pageSize) {
		Map<String, Object> attrs = new HashMap<>();
		@SuppressWarnings("unchecked")
		List<GjfJdGoods> list = gjfProductInfoDao.queryList(GjfJdGoods.class, (pageNo - 1) * pageSize, pageSize, "id",
				"asc", attrs);
		Iterator<GjfJdGoods> it = list.iterator();
		while (it.hasNext()) {
			GjfJdGoods gjfJdGoods = it.next();
			GoodDetail goodDetail = JdUtil.getJdGoodDetail(gjfJdGoods.getJdGoodsId());
			addJdProductToPlatform(goodDetail);

		}
		return new ResultVo(200, "添加成功", null);
	}

	/**
	 * 更新商品库存
	 */
	@Override
	public ResultVo updateJdProductStock(Long proId) {
		Map<String, String> resultMap=new HashMap<>();
		//获取商品信息
	    Map<String, Object> attr=new HashMap<>();
	    attr.put("id", proId);
	    GjfProductInfo productInfo=gjfProductInfoDao.query(GjfProductInfo.class, attr);
	    if(!BeanUtil.isValid(productInfo)){
	    	return new ResultVo(400,"商品不存在",null);
	    }
	    //查询商品库存
	    List<ProductStock> proList = JdUtil.getProductStore(productInfo.getNetProId().toString(),
				"27606,27607,27608,0");
	    //遍历商品信息
		for (ProductStock stock : proList) {
			//如果商品的结算价等于成本价，下架商品
			/*if(stock.getPrice().doubleValue()==stock.getShopPrice().doubleValue()){
				productInfo.setStatus("0");
			}*/
			//更新商品信息
			resultMap.put("proPrice", String.valueOf(stock.getShopPrice().doubleValue()));
			productInfo.setGcostPrice(stock.getPrice());
			productInfo.setMarketPrice(stock.getShopPrice());
			productInfo.setPrice(stock.getShopPrice());
			//计算商品的消费积分
			BigDecimal banerfit=stock.getShopPrice().subtract(stock.getPrice());
			banerfit=banerfit.subtract(banerfit.multiply(new BigDecimal(0.3))).setScale(2, BigDecimal.ROUND_DOWN);
			BigDecimal totalBanefit=banerfit.divide(new BigDecimal(0.12),2,BigDecimal.ROUND_DOWN);
			if(totalBanefit.doubleValue()>stock.getShopPrice().doubleValue()){
				resultMap.put("benefitMoney", String.valueOf(stock.getShopPrice().doubleValue()));
				productInfo.setBenerfitMoney(stock.getShopPrice());	
			}else{	
				resultMap.put("benefitMoney",  String.valueOf(totalBanefit.doubleValue()));
				productInfo.setBenerfitMoney(totalBanefit);	
			}
			
		}
		gjfProductInfoDao.update(productInfo);
		return new ResultVo(200, "更新成功",resultMap);
	}

	/**
	 * 获取商户采购商品信息
	 */
	@Override
	public ResultVo findMerchantProcurementProductInfo(Long columnId,String likeName, Integer pageNo, Integer pageSize,String discount) {
		List<ProductInfoVo> list=gjfProductInfoDao.findMerchantProcurementProduct(columnId,likeName, pageNo, pageSize,discount);
		return new ResultVo(200,"查询成功",list);
	}

	/**
	 * 获取京东自营产品
	 * @param catId
	 * @param page
	 * @param sup
	 * @param rateBegin
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public ResultVo findJdProprietaryProduct(String catId, String page, String sup, String rateBegin) {
		//获取京东商家商品区分率
		String rateBegins="";
		Map<String, Object> baseInfo=new HashMap<>();
		baseInfo.put("key", "JDRATEBEGIN");
		baseInfo.put("status", "1");
		GjfSetBaseInfo jdBaseInfo=gjfProductInfoDao.query(GjfSetBaseInfo.class, baseInfo);
		if(BeanUtil.isValid(jdBaseInfo)){
			rateBegins=jdBaseInfo.getValue();
		}
				
		//返回数据结果集
		List<GoodDetail> goodList=new ArrayList<>();
		//获取商品id
		List proIdList=JdNewUtil.goodsPoolOmni(catId, page, sup, rateBegins);
		String proIds="";
		//如果分类下的商品id不为空
		if(BeanUtil.isValid(proIdList)){
			for(int i=0;i<10;i++){
				String proId=(String) proIdList.get(i);
				proIds=proIds+proId+",";
				//GoodDetail good=JdNewUtil.getJdGoodDetail(proId);
				//goodList.add(good);
			}
			
			if(BeanUtil.isValid(proIds)){
				proIds=proIds.substring(0, proIds.length()-1);
				List<ProductStock> stockList = JdNewUtil.getProductStore(proIds, "27606,27607,27608,0");
				if (BeanUtil.isValid(stockList)) {
					Iterator<ProductStock> it = stockList.iterator();
					while (it.hasNext()) {
						ProductStock productStock = it.next();
						if(!BeanUtil.isValid(productStock.getGoodsNumber())||!BeanUtil.isValid(productStock.getIsOnSale())){
							continue;
						}
						if (productStock.getGoodsNumber() != 2&&productStock.getIsOnSale() != 0) {
							GoodDetail good=JdNewUtil.getJdGoodDetail(productStock.getGoodsId().toString());
							goodList.add(good);
						}							
					}
					
				}	
			}
			
		}
		return new ResultVo(200,"查询成功",goodList);
	}

	/**
	 * 添加京东自营产品
	 */
	@Override
	public ResultVo addJdProprietaryProduct(Long goodId) {
		//获取商品信息
		GoodDetail good=JdNewUtil.getJdGoodDetail(goodId.toString());
		if(! BeanUtil.isValid(good)){
			return new ResultVo(400,"商品不存在");
		}
		//添加信息
		addJdProductToPlatform(good);
		return new ResultVo(200, "添加成功");
	}

	/**
	 *添加供应商信息
	 */
	@Override
	public ResultVo addSupplierInfo(GjfSupplierInfo supplier) {
		if(!BeanUtil.isValid(supplier)){
			return new ResultVo(400,"供应商信息不存在");
		}
		if(!BeanUtil.isValid(supplier.getSupplierMobile())){
			return new ResultVo(400,"供应商电话号码");
		}
		gjfProductInfoDao.save(supplier);
		return new ResultVo(200,"添加成功");
	}

	/**
	 * 修改供应商信息
	 */
	@Override
	public ResultVo updateSupplierInfo(GjfSupplierInfo supplier) {
		if(!BeanUtil.isValid(supplier)){
			return new ResultVo(400,"供应商信息不存在");
		}
		if(!BeanUtil.isValid(supplier.getSupplierMobile())){
			return new ResultVo(400,"供应商电话号码");
		}
		gjfProductInfoDao.update(supplier);
		return new ResultVo(200,"更新成功");
	}

	/**
	 * 后台分页获取供应商信息
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ResultVo findSupplierInfoByPage(Integer pageNo, Integer pageSize, String mobile) {
		Map<String, Object> attrs=new HashMap<>();
		if(BeanUtil.isValid(mobile)){
			attrs.put("mobile", mobile);
		}
		
		List<GjfSupplierInfo> list=gjfProductInfoDao.queryList(GjfSupplierInfo.class, "addTime", "desc", attrs);
		Long count=gjfProductInfoDao.queryTotalRecord(GjfSupplierInfo.class, attrs);
		Pager pager=new Pager(pageSize,pageNo,count.intValue(),list);
		return new ResultVo(200,"查询成功",pager);
	}

	/**
	 * 获取全部供应商信息
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ResultVo findAllSupplierInfo() {
		Map<String, Object> attrs=new HashMap<>();
		List<GjfSupplierInfo> supList=gjfProductInfoDao.queryList(GjfSupplierInfo.class, "addTime", "desc", attrs);
		return new ResultVo(200,"查询成功",supList);
	}

	/**
	 * 获取供应商详细信息
	 */
	@Override
	public ResultVo findSupplierInfoById(Long supId) {
		Map<String, Object> attrs=new HashMap<>();
		attrs.put("id", supId);
		GjfSupplierInfo supplierInfo=gjfProductInfoDao.query(GjfSupplierInfo.class, attrs);
		return new ResultVo(200,"查询成功",supplierInfo);
	}

	/**
	 * 拉取自营商城产品
	 */

	@Override
	public ResultVo addProprietaryPro(String isOwn,String isCous,String isWho,String iSvou,String rateBegin) {
		int i=0;
		if("1".equals(isOwn)){
			i=i+1;
		}
		if("1".equals(isCous)){
			i=i+1;
		}
		
		if("1".equals(isWho)){
			i=i+1;
		}
		if("1".equals(iSvou)){
			i=i+1;
		}
		
		/*if(!BeanUtil.isValid(isOwn)&&!BeanUtil.isValid(isCous)&&!BeanUtil.isValid(isWho)&&!BeanUtil.isValid(iSvou)){
			i=i+1;
		}*/
		
		//获取分类
		List<Classify> claList=ProUtil.getCatId();
		//如果分类不为空
		if(BeanUtil.isValid(claList)){
			for(Classify cart:claList){
				/*if(cart.getId()!=1){
					break;
				}*/
				//获取分类下的商品列表
				List<ProductInfo> proList=ProUtil.getProductInfo("", cart.getId().toString(), "1", "1", rateBegin);
				//判断获取的集合是否为空
				if(BeanUtil.isValid(proList)){
					//如果不为空遍历商品集合
					for(ProductInfo pro:proList){
						
						Map<String, Object> proMap=new HashMap<>();
						proMap.put("netProId", Long.valueOf(pro.getGoods_id()));
						proMap.put("suorceGoods", "5");
						if("1".equals(isOwn)){
							proMap.put("isCanUserCou", "0");
							proMap.put("isWholesale", "0");	
							proMap.put("status", "1");
							GjfProductInfo netPro=gjfProductInfoDao.query(GjfProductInfo.class, proMap);
							if(BeanUtil.isValid(netPro)){
								continue;
							}
						}
						if("1".equals(isCous)){
							proMap.put("isCanUserCou", "1");
							proMap.put("isWholesale", "0");	
							proMap.put("status", "1");
							proMap.put("para6", "0");
							GjfProductInfo netPro=gjfProductInfoDao.query(GjfProductInfo.class, proMap);
							if(BeanUtil.isValid(netPro)){
								continue;
							}
						}
						if("1".equals(isWho)){
							proMap.put("isCanUserCou", "0");
							proMap.put("isWholesale", "1");
							proMap.put("status", "1");
							GjfProductInfo netPro=gjfProductInfoDao.query(GjfProductInfo.class, proMap);
							if(BeanUtil.isValid(netPro)){
								continue;
							}
						}
						if("1".equals(iSvou)){
							proMap.put("isCanUserCou", "3");
							proMap.put("isWholesale", "0");	
							proMap.put("status", "1");
							GjfProductInfo netPro=gjfProductInfoDao.query(GjfProductInfo.class, proMap);
							if(BeanUtil.isValid(netPro)){
								continue;
							}
						}
						
						/*if(!BeanUtil.isValid(isOwn)&&!BeanUtil.isValid(isCous)&&!BeanUtil.isValid(isWho)&&!BeanUtil.isValid(iSvou)){
							proMap.put("isCanUserCou", "1");
							proMap.put("isWholesale", "0");	
							proMap.put("status", "1");
							proMap.put("para6", "1");
							GjfProductInfo netPro=gjfProductInfoDao.query(GjfProductInfo.class, proMap);
							if(BeanUtil.isValid(netPro)){
								continue;
							}
						}*/
					
						
						if(i>=1){
							for(int j=1;j<=i;j++){
								List<SperGoodsPrice> specList=pro.getSpec_goods_price();
								if(!BeanUtil.isValid(specList)){
									//创建接收商品数据的对象
									BigDecimal zero=new BigDecimal(0);
									GjfProductInfo productInfo=new GjfProductInfo();
									productInfo.setAddTime(new Date());
									productInfo.setAduitStatus("1");
									productInfo.setBenerfitMoney(zero);
									productInfo.setCollectionNum(0L);
									productInfo.setContent(pro.getGoods_content());
									productInfo.setDescription(pro.getGoods_name());
									productInfo.setGcostPrice(pro.getCost_price());
									productInfo.setIsHavaRep("1");
									productInfo.setIsHot("0");
									productInfo.setIsNew("0");
									productInfo.setIsQbuy("0");
									productInfo.setIsRecommend("0");
									productInfo.setIsSale("0");
									productInfo.setGcostPrice(pro.getCost_price());
									productInfo.setIsUpgradePro("0");
									productInfo.setKeywords(pro.getKeywords());
									productInfo.setMarketPrice(pro.getShop_price());
									productInfo.setPara7(pro.getCat_id());
									
									
									productInfo.setPrice(pro.getMarket_price());

									productInfo.setName(pro.getGoods_name());
									productInfo.setPointNicePrice(zero);
									if(BeanUtil.isValid(pro.getSort())){
										if(Integer.valueOf(pro.getSort())>1&&Integer.valueOf(pro.getSort())<50){
											productInfo.setSalesNum(99L);
										}else{
											productInfo.setSalesNum(0L);
										}
									}else{
										productInfo.setSalesNum(0L);
									}
									
									if("1".equals(pro.getIs_on_sale())){
										productInfo.setStatus("1");
									}else{
										productInfo.setStatus("0");
									}
									
									productInfo.setPostage(zero);
									productInfo.setPointNum(999);
									productInfo.setRepertory(999L);
									productInfo.setScore(zero);
									productInfo.setViewNum(0L);	
									productInfo.setPurchasNum(999);
									productInfo.setScore(new BigDecimal(5));
									productInfo.setIsWholesale("0");
									productInfo.setPara8("");
									productInfo.setToken(Sha256.getSha256Hash(productInfo.getName(), productInfo.getAduitStatus(),CommonStatus.SIGN_KEY_NUM));
									
									//图片处理
									String imgUrl=pro.getGoods_images();
									if(BeanUtil.isValid(imgUrl)){
										//json字符串转数组
										com.alibaba.fastjson.JSONArray imgJson=com.alibaba.fastjson.JSONObject.parseArray(imgUrl);
										// 处理商品图片
										if ( imgJson.size() > 0) {
											for (int s = 0; s < imgJson.size(); s++) {
												com.alibaba.fastjson.JSONObject obj=imgJson.getJSONObject(s);
												if (s == 0) {
													productInfo.setPara1(obj.getString("image_url"));
													productInfo.setImgUrl(obj.getString("image_url"));
												} else if (s == 1) {
													productInfo.setPara2(obj.getString("image_url"));
												} else if (s == 2) {
													productInfo.setPara3(obj.getString("image_url"));
												} else if (s == 3) {
													productInfo.setPara4(obj.getString("image_url"));
												} else if (s == 4) {
													productInfo.setPara5(obj.getString("image_url"));
												}
											}
										}
									}
									
								
									productInfo.setNetProId(Long.valueOf(pro.getGoods_id()));
									productInfo.setMultipleNumber(pro.getPacking());
									
									// 查询店铺
									Map<String, Object> attrs = new HashMap<>();								
									attrs.put("storeIsOwnShop", "1");
									GjfStoreInfo store = gjfProductInfoDao.query(GjfStoreInfo.class, attrs);
									productInfo.setStoreId(store);
									
									/*//创建商品属性关联对象
									GjfProductAttrStock attrStock=new GjfProductAttrStock();
									attrStock.setAddTime(new Date());
									attrStock.setEditTime(new Date());
									attrStock.setPrice(pro.getShop_price());
									attrStock.setRepertory(999L);
									attrStock.setStatus("1");
									//创建属性对象
									GjfProductAttr attr=new GjfProductAttr();
									attr.setOrderBy(1L);	*/	
									// 查询栏目
									attrs.remove("storeIsOwnShop");
									GjfEnterpriseColumn column =new GjfEnterpriseColumn();
									
									//添加栏目关联
									GjfProductColumn proColumn=new GjfProductColumn();
									
									//采购产品分类
									GjfProductColumn vorColumn=new GjfProductColumn();
									//获取自营商城栏目
									if("1".equals(isOwn)){
										
										
										attrs.put("frontNum", "brand");
										//attrs.put("frontNum", "beauty");
										column = gjfProductInfoDao.query(GjfEnterpriseColumn.class, attrs);
										
										//添加商品显示价格
										productInfo.setStandardPrice(zero);
										productInfo.setHonourPrice(zero);
										productInfo.setColumnId(column);
										productInfo.setIsCanUserCou("0");
										productInfo.setSuorceGoods("5");
										
										proColumn.setColumnId(column);
									}
									//获取积分商城栏目
									if("1".equals(isCous)){
										attrs.put("frontNum", "ZERO_DOLLARS_1");
										column = gjfProductInfoDao.query(GjfEnterpriseColumn.class, attrs);
		
										//添加商品显示价格
										productInfo.setPara6("0");
										productInfo.setStandardPrice(zero);
										productInfo.setHonourPrice(zero);
										productInfo.setPrice(pro.getMarket_price().divide(new BigDecimal(0.2),0,BigDecimal.ROUND_DOWN));
										productInfo.setPointNicePrice(zero);

										productInfo.setColumnId(column);
										productInfo.setIsCanUserCou("1");
										productInfo.setSuorceGoods("5");
										
										proColumn.setColumnId(column);
										
										
									}
									
									//积分加现金
									/*if(!BeanUtil.isValid(isOwn)&&!BeanUtil.isValid(isCous)&&!BeanUtil.isValid(isWho)&&!BeanUtil.isValid(iSvou)){
										attrs.put("frontNum", "education");
										column = gjfProductInfoDao.query(GjfEnterpriseColumn.class, attrs);
										
									
										//添加商品显示价格
										productInfo.setPara6("1");
										productInfo.setStandardPrice(zero);
										productInfo.setHonourPrice(zero);
										productInfo.setPrice(pro.getShop_price().subtract(pro.getCost_price()));
										productInfo.setPointNicePrice(pro.getCost_price());
										productInfo.setColumnId(column);
										productInfo.setIsCanUserCou("1");
										productInfo.setSuorceGoods("5");
										
										proColumn.setColumnId(column);
									}*/
									
									//获取采购栏目
									if("1".equals(isWho)){
										attrs.put("frontNum", "MODELPRODUCT");
										column = gjfProductInfoDao.query(GjfEnterpriseColumn.class, attrs);
										
										//添加价格
                                        if(!BeanUtil.isValid(pro.getVip_price())||pro.getVip_price().doubleValue()==0){
                                        	continue;
                                        }
										//添加商品显示价格										
										productInfo.setStandardPrice(pro.getShop_price());
										productInfo.setHonourPrice(pro.getVip_price());
										productInfo.setColumnId(column);
										productInfo.setIsWholesale("1");
										productInfo.setSuorceGoods("5");
										productInfo.setIsCanUserCou("0");
										
										proColumn.setColumnId(column);
										
										if("7".equals(cart.getId().toString())||"7".equals(cart.getParent_id())){
											attrs.put("frontNum", "MODELPRO1");
											GjfEnterpriseColumn column1 = gjfProductInfoDao.query(GjfEnterpriseColumn.class, attrs);
											vorColumn.setColumnId(column1);
										}
										if("4".equals(cart.getId().toString())||"4".equals(cart.getParent_id())||"11".equals(cart.getId())||"11".equals(cart.getParent_id())){
											attrs.put("frontNum", "MODELPRO2");
											GjfEnterpriseColumn column1 = gjfProductInfoDao.query(GjfEnterpriseColumn.class, attrs);
											vorColumn.setColumnId(column1);
										}
										if("5".equals(cart.getId().toString())||"5".equals(cart.getParent_id())){
											attrs.put("frontNum", "MODELPRO3");
											GjfEnterpriseColumn column1 = gjfProductInfoDao.query(GjfEnterpriseColumn.class, attrs);
											vorColumn.setColumnId(column1);
										}
										if("844".equals(cart.getId().toString())||"844".equals(cart.getParent_id())){
											attrs.put("frontNum", "MODELPRO4");
											GjfEnterpriseColumn column1 = gjfProductInfoDao.query(GjfEnterpriseColumn.class, attrs);
											vorColumn.setColumnId(column1);
										}
										if("6".equals(cart.getId().toString())||"6".equals(cart.getParent_id())){
											attrs.put("frontNum", "MODELPRO5");
											GjfEnterpriseColumn column1 = gjfProductInfoDao.query(GjfEnterpriseColumn.class, attrs);
											vorColumn.setColumnId(column1);
										}
										if("10".equals(cart.getId().toString())||"10".equals(cart.getParent_id())){
											attrs.put("frontNum", "MODELPRO6");
											GjfEnterpriseColumn column1 = gjfProductInfoDao.query(GjfEnterpriseColumn.class, attrs);
											vorColumn.setColumnId(column1);
										}
										if("9".equals(cart.getId().toString())||"9".equals(cart.getParent_id())){
											attrs.put("frontNum", "MODELPRO7");
											GjfEnterpriseColumn column1 = gjfProductInfoDao.query(GjfEnterpriseColumn.class, attrs);
											vorColumn.setColumnId(column1);
										}
										if("2".equals(cart.getId().toString())||"2".equals(cart.getParent_id())){
											attrs.put("frontNum", "MODELPRO8");
											GjfEnterpriseColumn column1 = gjfProductInfoDao.query(GjfEnterpriseColumn.class, attrs);
											vorColumn.setColumnId(column1);
										}
										if("3".equals(cart.getId().toString())||"3".equals(cart.getParent_id())||"1".equals(cart.getId())||"1".equals(cart.getParent_id())){
											attrs.put("frontNum", "MODELPRO9");
											GjfEnterpriseColumn column1 = gjfProductInfoDao.query(GjfEnterpriseColumn.class, attrs);
											vorColumn.setColumnId(column1);
										}
										if("8".equals(cart.getId().toString())||"8".equals(cart.getParent_id())){
											attrs.put("frontNum", "MODELPRO10");
											GjfEnterpriseColumn column1 = gjfProductInfoDao.query(GjfEnterpriseColumn.class, attrs);
											vorColumn.setColumnId(column1);
										}
																														
									}
									//获取代金券栏目
									if("1".equals(iSvou)){
										attrs.put("frontNum", "VOUCHERS");
										column = gjfProductInfoDao.query(GjfEnterpriseColumn.class, attrs);
																				
										productInfo.setPrice(pro.getMarket_price().multiply(new BigDecimal(10)).setScale(0, BigDecimal.ROUND_DOWN));										
										productInfo.setStandardPrice(zero);
										productInfo.setHonourPrice(zero);
										productInfo.setColumnId(column);
										productInfo.setSuorceGoods("5");
										productInfo.setIsCanUserCou("3");
										productInfo.setPurchasNum(1);
										proColumn.setColumnId(column);
									}
									
									//保存商品信息
									gjfProductInfoDao.save(productInfo);
									proColumn.setProductId(productInfo);
									if("1".equals(iSvou)||"1".equals(isOwn)||"1".equals(isCous)||(!BeanUtil.isValid(isOwn)&&!BeanUtil.isValid(isCous)&&!BeanUtil.isValid(isWho)&&!BeanUtil.isValid(iSvou))){
										gjfProductInfoDao.save(proColumn);	
									}
									if(BeanUtil.isValid(vorColumn.getColumnId())&&"1".equals(isWho)){
										vorColumn.setProductId(productInfo);
										gjfProductInfoDao.save(vorColumn);
									}
									
								}else{
									for(SperGoodsPrice sper:specList){
										//创建接收商品数据的对象
										BigDecimal zero=new BigDecimal(0);
										GjfProductInfo productInfo=new GjfProductInfo();
										productInfo.setAddTime(new Date());
										productInfo.setAduitStatus("1");
										productInfo.setBenerfitMoney(zero);
										productInfo.setCollectionNum(0L);
										productInfo.setContent(pro.getGoods_content());
										productInfo.setDescription(pro.getGoods_name());
										productInfo.setGcostPrice(pro.getCost_price());
										productInfo.setIsHavaRep("1");
										productInfo.setIsHot("0");
										productInfo.setIsNew("0");
										productInfo.setIsQbuy("0");
										productInfo.setIsRecommend("0");
										productInfo.setIsSale("0");
										productInfo.setGcostPrice(pro.getCost_price());
										productInfo.setIsUpgradePro("0");
										productInfo.setKeywords(pro.getKeywords());
										productInfo.setMarketPrice(pro.getShop_price());
										productInfo.setPara7(pro.getCat_id());
										
										productInfo.setPrice(pro.getMarket_price());
										
										productInfo.setName(pro.getGoods_name()+sper.getKey_name());
										productInfo.setPointNicePrice(zero);
										if(BeanUtil.isValid(pro.getSort())){
											if(Integer.valueOf(pro.getSort())>1&&Integer.valueOf(pro.getSort())<50){
												productInfo.setSalesNum(99L);
											}else{
												productInfo.setSalesNum(0L);
											}
										}else{
											productInfo.setSalesNum(0L);
										}
										if("1".equals(pro.getIs_on_sale())){
											productInfo.setStatus("1");
										}else{
											productInfo.setStatus("0");
										}
										
										productInfo.setPostage(zero);
										productInfo.setPointNum(999);
										productInfo.setRepertory(999L);
										productInfo.setScore(zero);
										productInfo.setViewNum(0L);	
										productInfo.setPurchasNum(999);
										productInfo.setScore(new BigDecimal(5));
										productInfo.setIsWholesale("0");
										productInfo.setPara8(sper.getKey());
										productInfo.setToken(Sha256.getSha256Hash(productInfo.getName(), productInfo.getAduitStatus(),CommonStatus.SIGN_KEY_NUM));
										
										//图片处理
										String imgUrl=pro.getGoods_images();
										if(BeanUtil.isValid(imgUrl)){
											//json字符串转数组
											com.alibaba.fastjson.JSONArray imgJson=com.alibaba.fastjson.JSONObject.parseArray(imgUrl);
											// 处理商品图片
											if ( imgJson.size() > 0) {
												for (int s = 0; s < imgJson.size(); s++) {
													com.alibaba.fastjson.JSONObject obj=imgJson.getJSONObject(s);
													if (s == 0) {
														productInfo.setPara1(obj.getString("image_url"));
														productInfo.setImgUrl(obj.getString("image_url"));
													} else if (s == 1) {
														productInfo.setPara2(obj.getString("image_url"));
													} else if (s == 2) {
														productInfo.setPara3(obj.getString("image_url"));
													} else if (s == 3) {
														productInfo.setPara4(obj.getString("image_url"));
													} else if (s == 4) {
														productInfo.setPara5(obj.getString("image_url"));
													}
												}
											}
										}
										
									
										productInfo.setNetProId(Long.valueOf(pro.getGoods_id()));
										productInfo.setMultipleNumber(pro.getPacking());
										
										// 查询店铺
										Map<String, Object> attrs = new HashMap<>();								
										attrs.put("storeIsOwnShop", "1");
										GjfStoreInfo store = gjfProductInfoDao.query(GjfStoreInfo.class, attrs);
										productInfo.setStoreId(store);
										
										/*//创建商品属性关联对象
										GjfProductAttrStock attrStock=new GjfProductAttrStock();
										attrStock.setAddTime(new Date());
										attrStock.setEditTime(new Date());
										attrStock.setPrice(pro.getShop_price());
										attrStock.setRepertory(999L);
										attrStock.setStatus("1");
										//创建属性对象
										GjfProductAttr attr=new GjfProductAttr();
										attr.setOrderBy(1L);	*/	
										// 查询栏目
										attrs.remove("storeIsOwnShop");
										GjfEnterpriseColumn column =new GjfEnterpriseColumn();
										
										//添加栏目关联
										GjfProductColumn proColumn=new GjfProductColumn();
										//获取自营商城栏目
										if("1".equals(isOwn)){
											attrs.put("frontNum", "brand");
											//attrs.put("frontNum", "beauty");
											column = gjfProductInfoDao.query(GjfEnterpriseColumn.class, attrs);										
											//添加商品显示价格
											productInfo.setStandardPrice(zero);
											productInfo.setHonourPrice(zero);
											productInfo.setColumnId(column);
											productInfo.setIsCanUserCou("0");
											productInfo.setSuorceGoods("5");
											
											proColumn.setColumnId(column);
										}
										//获取积分商城栏目
										if("1".equals(isCous)){
											attrs.put("frontNum", "ZERO_DOLLARS_1");
											column = gjfProductInfoDao.query(GjfEnterpriseColumn.class, attrs);
									
											//添加商品显示价格
											productInfo.setPara6("0");
											productInfo.setStandardPrice(zero);
											productInfo.setHonourPrice(zero);
											productInfo.setPrice(pro.getMarket_price().divide(new BigDecimal(0.2),0,BigDecimal.ROUND_DOWN));
											productInfo.setPointNicePrice(zero);
			
											productInfo.setColumnId(column);
											productInfo.setIsCanUserCou("1");
											productInfo.setSuorceGoods("5");
											productInfo.setPurchasNum(1);
											
											proColumn.setColumnId(column);
										}																				
										
										//积分加现金
										/*if(!BeanUtil.isValid(isOwn)&&!BeanUtil.isValid(isCous)&&!BeanUtil.isValid(isWho)&&!BeanUtil.isValid(iSvou)){
											//attrs.put("frontNum", "ZERO_DOLLARS_1");
											attrs.put("frontNum", "education");
											column = gjfProductInfoDao.query(GjfEnterpriseColumn.class, attrs);
											
										
											//添加商品显示价格
											productInfo.setPara6("1");
											productInfo.setStandardPrice(zero);
											productInfo.setHonourPrice(zero);
											productInfo.setPrice(sper.getPrice().subtract(pro.getCost_price()));
											productInfo.setPointNicePrice(pro.getCost_price());
											productInfo.setColumnId(column);
											productInfo.setIsCanUserCou("1");
											productInfo.setSuorceGoods("5");
											
											proColumn.setColumnId(column);
										}*/
										
										//采购产品分类
										GjfProductColumn vorColumn=new GjfProductColumn();
										//获取采购栏目
										if("1".equals(isWho)){
											attrs.put("frontNum", "MODELPRODUCT");
											column = gjfProductInfoDao.query(GjfEnterpriseColumn.class, attrs);
											
											//添加价格
											//添加价格
	                                        if(!BeanUtil.isValid(sper.getVip_price())||sper.getVip_price().doubleValue()==0){
	                                        	continue;
	                                        }
											//添加商品显示价格
											
											productInfo.setStandardPrice(sper.getPrice());
											productInfo.setHonourPrice(sper.getVip_price());
											
											
											productInfo.setColumnId(column);
											productInfo.setIsWholesale("1");
											productInfo.setSuorceGoods("5");
											productInfo.setIsCanUserCou("0");
											
											proColumn.setColumnId(column);
											
											if("7".equals(cart.getId().toString())||"7".equals(cart.getParent_id())){
												attrs.put("frontNum", "MODELPRO1");
												GjfEnterpriseColumn column1 = gjfProductInfoDao.query(GjfEnterpriseColumn.class, attrs);
												vorColumn.setColumnId(column1);
											}
											if("4".equals(cart.getId().toString())||"4".equals(cart.getParent_id())||"11".equals(cart.getId())||"11".equals(cart.getParent_id())){
												attrs.put("frontNum", "MODELPRO2");
												GjfEnterpriseColumn column1 = gjfProductInfoDao.query(GjfEnterpriseColumn.class, attrs);
												vorColumn.setColumnId(column1);
											}
											if("5".equals(cart.getId().toString())||"5".equals(cart.getParent_id())){
												attrs.put("frontNum", "MODELPRO3");
												GjfEnterpriseColumn column1 = gjfProductInfoDao.query(GjfEnterpriseColumn.class, attrs);
												vorColumn.setColumnId(column1);
											}
											if("844".equals(cart.getId().toString())||"844".equals(cart.getParent_id())){
												attrs.put("frontNum", "MODELPRO4");
												GjfEnterpriseColumn column1 = gjfProductInfoDao.query(GjfEnterpriseColumn.class, attrs);
												vorColumn.setColumnId(column1);
											}
											if("6".equals(cart.getId().toString())||"6".equals(cart.getParent_id())){
												attrs.put("frontNum", "MODELPRO5");
												GjfEnterpriseColumn column1 = gjfProductInfoDao.query(GjfEnterpriseColumn.class, attrs);
												vorColumn.setColumnId(column1);
											}
											if("10".equals(cart.getId().toString())||"10".equals(cart.getParent_id())){
												attrs.put("frontNum", "MODELPRO6");
												GjfEnterpriseColumn column1 = gjfProductInfoDao.query(GjfEnterpriseColumn.class, attrs);
												vorColumn.setColumnId(column1);
											}
											if("9".equals(cart.getId().toString())||"9".equals(cart.getParent_id())){
												attrs.put("frontNum", "MODELPRO7");
												GjfEnterpriseColumn column1 = gjfProductInfoDao.query(GjfEnterpriseColumn.class, attrs);
												vorColumn.setColumnId(column1);
											}
											if("2".equals(cart.getId().toString())||"2".equals(cart.getParent_id())){
												attrs.put("frontNum", "MODELPRO8");
												GjfEnterpriseColumn column1 = gjfProductInfoDao.query(GjfEnterpriseColumn.class, attrs);
												vorColumn.setColumnId(column1);
											}
											if("3".equals(cart.getId().toString())||"3".equals(cart.getParent_id())||"1".equals(cart.getId())||"1".equals(cart.getParent_id())){
												attrs.put("frontNum", "MODELPRO9");
												GjfEnterpriseColumn column1 = gjfProductInfoDao.query(GjfEnterpriseColumn.class, attrs);
												vorColumn.setColumnId(column1);
											}
											if("8".equals(cart.getId().toString())||"8".equals(cart.getParent_id())){
												attrs.put("frontNum", "MODELPRO10");
												GjfEnterpriseColumn column1 = gjfProductInfoDao.query(GjfEnterpriseColumn.class, attrs);
												vorColumn.setColumnId(column1);
											}
										}
										//获取代金券栏目
										if("1".equals(iSvou)){
											attrs.put("frontNum", "VOUCHERS");
											column = gjfProductInfoDao.query(GjfEnterpriseColumn.class, attrs);
											
											productInfo.setPrice(sper.getPrice().multiply(new BigDecimal(10)).setScale(0, BigDecimal.ROUND_DOWN));

											productInfo.setStandardPrice(zero);
											productInfo.setHonourPrice(zero);
											productInfo.setColumnId(column);
											productInfo.setSuorceGoods("5");
											productInfo.setIsCanUserCou("3");
											productInfo.setPurchasNum(1);
											proColumn.setColumnId(column);
										}
										
										//保存商品信息
										gjfProductInfoDao.save(productInfo);
										proColumn.setProductId(productInfo);
										if("1".equals(iSvou)||"1".equals(isOwn)||"1".equals(isCous)||!BeanUtil.isValid(isOwn)&&!BeanUtil.isValid(isCous)&&!BeanUtil.isValid(isWho)&&!BeanUtil.isValid(iSvou)){
											gjfProductInfoDao.save(proColumn);	
										}
										
										if(BeanUtil.isValid(vorColumn.getColumnId())&&"1".equals(isWho)){
											vorColumn.setProductId(productInfo);
											gjfProductInfoDao.save(vorColumn);
										}
										
									}
									
								}
								
							}
						}
						
												
					}
				}
			}
		}
		return new ResultVo(200,"添加成功");
	}
	


	/**
	 * 天天易购首页展示
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ResultVo findProductInfoIndex() {
		// 首页展示商品，如今日推荐
		//Map<String, Object> dataMap = new LinkedHashMap<String, Object>();
		List<GjfEnterpriseColumn> columns = gjfProductInfoDao.findIndexPro("1", 5L);
		List reusltList=new ArrayList<>();
		if(BeanUtil.isValid(columns)){
			for (GjfEnterpriseColumn enterpriseColumnVo : columns) {
				Map<String, Object> resultMap=new HashMap<>(); 
				resultMap.put("columnName",enterpriseColumnVo.getNames()); // 所有商品
				resultMap.put("columnId", enterpriseColumnVo.getId());
				resultMap.put("columnImg", enterpriseColumnVo.getPic1());
				resultMap.put("product", findProByColumnId(enterpriseColumnVo.getId(), 1, 3, "", null, null).getResult());
				reusltList.add(resultMap);
	        }
		}
		
	    return new ResultVo(200, "查询成功", reusltList);
	}
	
	/**
	 * 更新商品池商品信息
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ResultVo updateProprietaryPro(ProductInfo pro) {
		for(int i=0;i<4;i++){
			if(BeanUtil.isValid(pro)){
				//查询商品信息
				Map<String, Object> attrs=new HashMap<>();
				attrs.put("netProId",Long.valueOf(pro.getGoods_id()) );
				attrs.put("suorceGoods", "5");
				attrs.put("status", "1");
				if(i==0){
					attrs.put("isCanUserCou", "0");
					attrs.put("isWholesale", "0");
				}
				if(i==1){
					attrs.put("isCanUserCou", "0");
					attrs.put("isWholesale", "1");
				}
				if(i==2){
					attrs.put("isCanUserCou", "3");
					attrs.put("isWholesale", "0");
				}
				if(i==3){
					attrs.put("isCanUserCou", "1");
					attrs.put("isWholesale", "0");
				}
				List<GjfProductInfo> proList=gjfProductInfoDao.queryList(GjfProductInfo.class, "addTime", "desc", attrs);
				//如果商品已经存在更新商品信息
				if(BeanUtil.isValid(proList)){
					for(GjfProductInfo productInfo:proList){				
						productInfo.setContent(pro.getGoods_content());
						productInfo.setDescription(pro.getGoods_name());
						productInfo.setGcostPrice(pro.getCost_price());				
						productInfo.setGcostPrice(pro.getCost_price());
						productInfo.setKeywords(pro.getKeywords());
						productInfo.setMarketPrice(pro.getShop_price());
						productInfo.setPara7(pro.getCat_id());	
						productInfo.setPrice(pro.getMarket_price());
						productInfo.setName(pro.getGoods_name());
						if("0".equals(pro.getIs_on_sale())){
							productInfo.setStatus("0");
						}else if("1".equals(pro.getIs_on_sale())){
							productInfo.setStatus("1");
						}
						if(BeanUtil.isValid(pro.getSort())){
							if(Integer.valueOf(pro.getSort())>1&&Integer.valueOf(pro.getSort())<50){
								productInfo.setSalesNum(99L);
							}else{
								productInfo.setSalesNum(0L);
							}
						}else{
							productInfo.setSalesNum(0L);
						}																
						productInfo.setPara8("");
						productInfo.setToken(Sha256.getSha256Hash(productInfo.getName(), productInfo.getAduitStatus(),CommonStatus.SIGN_KEY_NUM));
						
						//图片处理
						String imgUrl=pro.getGoods_images();
						if(BeanUtil.isValid(imgUrl)){
							//json字符串转数组
							com.alibaba.fastjson.JSONArray imgJson=com.alibaba.fastjson.JSONObject.parseArray(imgUrl);
							// 处理商品图片
							if ( imgJson.size() > 0) {
								for (int s = 0; s < imgJson.size(); s++) {
									com.alibaba.fastjson.JSONObject obj=imgJson.getJSONObject(s);
									if (s == 0) {
										productInfo.setPara1(obj.getString("image_url"));
										productInfo.setImgUrl(obj.getString("image_url"));
									} else if (s == 1) {
										productInfo.setPara2(obj.getString("image_url"));
									} else if (s == 2) {
										productInfo.setPara3(obj.getString("image_url"));
									} else if (s == 3) {
										productInfo.setPara4(obj.getString("image_url"));
									} else if (s == 4) {
										productInfo.setPara5(obj.getString("image_url"));
									}
								}
							}
						}
						productInfo.setNetProId(Long.valueOf(pro.getGoods_id()));
						productInfo.setMultipleNumber(pro.getPacking());
						
						//获取积分栏目
						if("1".equals(productInfo.getIsCanUserCou())){	
							if(BeanUtil.isValid(pro.getSpec_goods_price())){
								List<SperGoodsPrice> spers=pro.getSpec_goods_price();							
								for(SperGoodsPrice sper:spers){
									String pName=pro.getGoods_name()+sper.getKey();
									if(pName.equals(productInfo.getName())){
										productInfo.setPrice(pro.getMarket_price().divide(new BigDecimal(0.2),0,BigDecimal.ROUND_DOWN));			
									}
								}
							}else{
								productInfo.setPrice(pro.getMarket_price().divide(new BigDecimal(0.2),0,BigDecimal.ROUND_DOWN));	
							}	
							
							productInfo.setPointNicePrice(new BigDecimal(0));
						}	

						//获取采购栏目
						if("1".equals(productInfo.getIsWholesale())){
							if(BeanUtil.isValid(pro.getSpec_goods_price())){
								List<SperGoodsPrice> spers=pro.getSpec_goods_price();							
								for(SperGoodsPrice sper:spers){
									String pName=pro.getGoods_name()+sper.getKey();
									if(pName.equals(productInfo.getName())){
										productInfo.setStandardPrice(sper.getPrice());
										productInfo.setHonourPrice(sper.getVip_price());		
									}
								}
							}else{
								productInfo.setStandardPrice(pro.getShop_price());
								productInfo.setHonourPrice(pro.getVip_price());		
							}
																										
						}
						//获取代金券栏目
						if("3".equals(productInfo.getIsCanUserCou())){	
							if(BeanUtil.isValid(pro.getSpec_goods_price())){
								List<SperGoodsPrice> spers=pro.getSpec_goods_price();							
								for(SperGoodsPrice sper:spers){
									String pName=pro.getGoods_name()+sper.getKey();
									if(pName.equals(productInfo.getName())){
										productInfo.setPrice(pro.getMarket_price().multiply(new BigDecimal(10).setScale(0, BigDecimal.ROUND_DOWN)));				
									}
								}
							}else{
								productInfo.setPrice(pro.getMarket_price().multiply(new BigDecimal(10)));		
							}	
						}					
						//保存商品信息
						gjfProductInfoDao.update(productInfo);
						
					}
				}else{//如果商品不存在则添加商品
					if(BeanUtil.isValid(pro.getSpec_goods_price())){
						List<SperGoodsPrice> spers=pro.getSpec_goods_price();							
						for(SperGoodsPrice sper:spers){		
							if(i==3&&sper.getVip_price().doubleValue()==0){
								
							}else{
								addProperProInfo(pro,sper.getKey_name(),sper.getKey(),sper.getPrice(),sper.getVip_price(),"1",i);
							}							
						}
					}else{
						if(i==3&&pro.getVip_price().doubleValue()==0){
							
						}else{
							addProperProInfo(pro,"","",new BigDecimal(0),new BigDecimal(0),"0",i);	
						}
						
					}
				}
			}
		}
		
		return new ResultVo(200,"更新成功");
	}
	
	//添加产品信息
	public void addProperProInfo(ProductInfo pro,String keyName,String key,BigDecimal price,BigDecimal vip_price,String type,int i){
		//for(int i=0;i<3;i++){
			//创建接收商品数据的对象
			BigDecimal zero=new BigDecimal(0);
			GjfProductInfo productInfo=new GjfProductInfo();
			productInfo.setAddTime(new Date());
			productInfo.setAduitStatus("1");
			productInfo.setBenerfitMoney(zero);
			productInfo.setCollectionNum(0L);
			productInfo.setContent(pro.getGoods_content());
			productInfo.setDescription(pro.getGoods_name());
			productInfo.setGcostPrice(pro.getCost_price());
			productInfo.setIsHavaRep("1");
			productInfo.setIsHot("0");
			productInfo.setIsNew("0");
			productInfo.setIsQbuy("0");
			productInfo.setIsRecommend("0");
			productInfo.setIsSale("0");
			productInfo.setGcostPrice(pro.getCost_price());
			productInfo.setIsUpgradePro("0");
			productInfo.setKeywords(pro.getKeywords());
			productInfo.setMarketPrice(pro.getShop_price());
			productInfo.setPara7(pro.getCat_id());
			
			
			productInfo.setPrice(pro.getMarket_price());

			productInfo.setName(pro.getGoods_name()+keyName);
			productInfo.setPointNicePrice(zero);
			if(BeanUtil.isValid(pro.getSort())){
				if(Integer.valueOf(pro.getSort())>1&&Integer.valueOf(pro.getSort())<50){
					productInfo.setSalesNum(99L);
				}else{
					productInfo.setSalesNum(0L);
				}
			}else{
				productInfo.setSalesNum(0L);
			}
			
			
			productInfo.setStatus("1");
			productInfo.setPostage(zero);
			productInfo.setPointNum(999);
			productInfo.setRepertory(999L);
			productInfo.setScore(zero);
			productInfo.setViewNum(0L);	
			productInfo.setPurchasNum(999);
			productInfo.setScore(new BigDecimal(5));
			productInfo.setIsWholesale("0");
			if("1".equals(type)){
				productInfo.setPara8(key);	
			}else{
				productInfo.setPara8("");
			}
			
			productInfo.setToken(Sha256.getSha256Hash(productInfo.getName(), productInfo.getAduitStatus(),CommonStatus.SIGN_KEY_NUM));
			
			//图片处理
			String imgUrl=pro.getGoods_images();
			if(BeanUtil.isValid(imgUrl)){
				//json字符串转数组
				com.alibaba.fastjson.JSONArray imgJson=com.alibaba.fastjson.JSONObject.parseArray(imgUrl);
				// 处理商品图片
				if ( imgJson.size() > 0) {
					for (int s = 0; s < imgJson.size(); s++) {
						com.alibaba.fastjson.JSONObject obj=imgJson.getJSONObject(s);
						if (s == 0) {
							productInfo.setPara1(obj.getString("image_url"));
							productInfo.setImgUrl(obj.getString("image_url"));
						} else if (s == 1) {
							productInfo.setPara2(obj.getString("image_url"));
						} else if (s == 2) {
							productInfo.setPara3(obj.getString("image_url"));
						} else if (s == 3) {
							productInfo.setPara4(obj.getString("image_url"));
						} else if (s == 4) {
							productInfo.setPara5(obj.getString("image_url"));
						}
					}
				}
			}
			
		
			productInfo.setNetProId(Long.valueOf(pro.getGoods_id()));
			productInfo.setMultipleNumber(pro.getPacking());
			
			// 查询店铺
			Map<String, Object> attrs = new HashMap<>();								
			attrs.put("storeIsOwnShop", "1");
			GjfStoreInfo store = gjfProductInfoDao.query(GjfStoreInfo.class, attrs);
			productInfo.setStoreId(store);
					
			// 查询栏目
			attrs.remove("storeIsOwnShop");
			GjfEnterpriseColumn column =new GjfEnterpriseColumn();
			
			//添加栏目关联
			GjfProductColumn proColumn=new GjfProductColumn();
			
			//采购产品分类
			GjfProductColumn vorColumn=new GjfProductColumn();
			//获取自营商城栏目
			if(i==0){
				attrs.put("frontNum", "brand");
				column = gjfProductInfoDao.query(GjfEnterpriseColumn.class, attrs);				
				//添加商品显示价格
				productInfo.setStandardPrice(zero);
				productInfo.setHonourPrice(zero);
				productInfo.setColumnId(column);
				productInfo.setIsCanUserCou("0");
				productInfo.setSuorceGoods("5");
				
				proColumn.setColumnId(column);
			}
			//获取积分商城栏目
			
			
			//获取采购栏目
			if(i==1){
				attrs.put("frontNum", "MODELPRODUCT");
				column = gjfProductInfoDao.query(GjfEnterpriseColumn.class, attrs);
				
				if("1".equals(type)){
					
					productInfo.setStandardPrice(price);
					productInfo.setHonourPrice(vip_price);
				}else{
					productInfo.setStandardPrice(pro.getShop_price());
					productInfo.setHonourPrice(pro.getVip_price());
				}
				
				productInfo.setColumnId(column);
				productInfo.setIsWholesale("1");
				productInfo.setSuorceGoods("5");
				productInfo.setIsCanUserCou("0");
				
				proColumn.setColumnId(column);
				
				if("7".equals(pro.getCat_id().toString())){
					attrs.put("frontNum", "MODELPRO1");
					GjfEnterpriseColumn column1 = gjfProductInfoDao.query(GjfEnterpriseColumn.class, attrs);
					vorColumn.setColumnId(column1);
				}
				if("4".equals(pro.getCat_id().toString())||"11".equals(pro.getCat_id())){
					attrs.put("frontNum", "MODELPRO2");
					GjfEnterpriseColumn column1 = gjfProductInfoDao.query(GjfEnterpriseColumn.class, attrs);
					vorColumn.setColumnId(column1);
				}
				if("5".equals(pro.getCat_id().toString())){
					attrs.put("frontNum", "MODELPRO3");
					GjfEnterpriseColumn column1 = gjfProductInfoDao.query(GjfEnterpriseColumn.class, attrs);
					vorColumn.setColumnId(column1);
				}
				if("844".equals(pro.getCat_id().toString())){
					attrs.put("frontNum", "MODELPRO4");
					GjfEnterpriseColumn column1 = gjfProductInfoDao.query(GjfEnterpriseColumn.class, attrs);
					vorColumn.setColumnId(column1);
				}
				if("6".equals(pro.getCat_id().toString())){
					attrs.put("frontNum", "MODELPRO5");
					GjfEnterpriseColumn column1 = gjfProductInfoDao.query(GjfEnterpriseColumn.class, attrs);
					vorColumn.setColumnId(column1);
				}
				if("10".equals(pro.getCat_id().toString())){
					attrs.put("frontNum", "MODELPRO6");
					GjfEnterpriseColumn column1 = gjfProductInfoDao.query(GjfEnterpriseColumn.class, attrs);
					vorColumn.setColumnId(column1);
				}
				if("9".equals(pro.getCat_id().toString())){
					attrs.put("frontNum", "MODELPRO7");
					GjfEnterpriseColumn column1 = gjfProductInfoDao.query(GjfEnterpriseColumn.class, attrs);
					vorColumn.setColumnId(column1);
				}
				if("2".equals(pro.getCat_id().toString())){
					attrs.put("frontNum", "MODELPRO8");
					GjfEnterpriseColumn column1 = gjfProductInfoDao.query(GjfEnterpriseColumn.class, attrs);
					vorColumn.setColumnId(column1);
				}
				if("3".equals(pro.getCat_id().toString())||"1".equals(pro.getCat_id())){
					attrs.put("frontNum", "MODELPRO9");
					GjfEnterpriseColumn column1 = gjfProductInfoDao.query(GjfEnterpriseColumn.class, attrs);
					vorColumn.setColumnId(column1);
				}
				if("8".equals(pro.getCat_id().toString())){
					attrs.put("frontNum", "MODELPRO10");
					GjfEnterpriseColumn column1 = gjfProductInfoDao.query(GjfEnterpriseColumn.class, attrs);
					vorColumn.setColumnId(column1);
				}
																								
			}
			//获取代金券栏目
			if(i==2){
				attrs.put("frontNum", "VOUCHERS");
				column = gjfProductInfoDao.query(GjfEnterpriseColumn.class, attrs);
				if("1".equals(type)){
					productInfo.setPrice(pro.getMarket_price().multiply(new BigDecimal(10)).setScale(0,BigDecimal.ROUND_DOWN));			
				}else{
					productInfo.setPrice(price.multiply(new BigDecimal(10)).setScale(0,BigDecimal.ROUND_DOWN));
				}
				
				if(BeanUtil.isValid(pro.getProfit())){
					if(Float.valueOf(pro.getProfit())>0.1){
						return;
					}
				}else{
					return;
				}
				
				productInfo.setStandardPrice(zero);
				productInfo.setHonourPrice(zero);
				productInfo.setColumnId(column);
				productInfo.setSuorceGoods("5");
				productInfo.setIsCanUserCou("3");
				productInfo.setPurchasNum(1);
				proColumn.setColumnId(column);
			}
			if(i==3){
				
				attrs.put("frontNum", "ZERO_DOLLARS_1");
				column = gjfProductInfoDao.query(GjfEnterpriseColumn.class, attrs);
					
				//添加商品显示价格
				productInfo.setStandardPrice(zero);
				productInfo.setHonourPrice(zero);
				productInfo.setPrice(pro.getMarket_price().divide(new BigDecimal(0.12),0,BigDecimal.ROUND_DOWN));
				productInfo.setPointNicePrice(zero);
				productInfo.setColumnId(column);
				productInfo.setIsCanUserCou("1");
				productInfo.setSuorceGoods("5");
				productInfo.setPurchasNum(1);
					
				proColumn.setColumnId(column);
	
			}
			
			//保存商品信息
			gjfProductInfoDao.save(productInfo);
			proColumn.setProductId(productInfo);
			if(i==0||i==2||i==3){
				gjfProductInfoDao.save(proColumn);	
			}
			if(BeanUtil.isValid(vorColumn.getColumnId())&&i==1){
				vorColumn.setProductId(productInfo);
				gjfProductInfoDao.save(vorColumn);
			}
		//}
	}

	/**
	 * 查询pc端采购商品信息
	 */
	@Override
	public ResultVo findMerchantProcurementProductInPc(Long columnId, String likeName, Integer pageNo, Integer pageSize,
			String discount) {
		Pager pager=gjfProductInfoDao.findMerchantProcurementProductInPc( columnId,  likeName,  pageNo,  pageSize, discount);
		return new ResultVo(200,"查询成功",pager);
	}
	
	

}
