package cc.messcat.gjfeng.web.app.v1;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.newAlipay.util.NewsOrderUtil;
import com.wechat.WeixinUtil;

import cc.messcat.gjfeng.common.constant.CommonProperties;
import cc.messcat.gjfeng.common.exception.LoggerPrint;
import cc.messcat.gjfeng.common.jd.JdNewUtil;
import cc.messcat.gjfeng.common.jd.bean.GoodDetail;
import cc.messcat.gjfeng.common.util.BeanUtil;
import cc.messcat.gjfeng.common.util.EncryptionUtil;
import cc.messcat.gjfeng.common.util.ImageCompressUtil;
import cc.messcat.gjfeng.common.util.ObjValid;
import cc.messcat.gjfeng.common.util.StringUtil;
import cc.messcat.gjfeng.common.vo.app.OrderAddModel;
import cc.messcat.gjfeng.common.vo.app.OrderAddVo;
import cc.messcat.gjfeng.common.vo.app.ProductInfoVo;
import cc.messcat.gjfeng.common.vo.app.ResultVo;
import cc.messcat.gjfeng.common.vo.app.StoreInfoVo;
import cc.messcat.gjfeng.common.web.BaseController;
import cc.messcat.gjfeng.dubbo.core.GjfAddressDubbo;
import cc.messcat.gjfeng.dubbo.core.GjfEnterpriseColumnDubbo;
import cc.messcat.gjfeng.dubbo.core.GjfIndexDubbo;
import cc.messcat.gjfeng.dubbo.core.GjfMemberInfoDubbo;
import cc.messcat.gjfeng.dubbo.core.GjfOrderInfoDubbo;
import cc.messcat.gjfeng.dubbo.core.GjfProductAttrDubbo;
import cc.messcat.gjfeng.dubbo.core.GjfProductCommentDubbo;
import cc.messcat.gjfeng.dubbo.core.GjfProductInfoDubbo;
import cc.messcat.gjfeng.dubbo.core.GjfStoreInfoDubbo;
import cc.messcat.gjfeng.dubbo.core.GjfTradeDubbo;
import cc.messcat.gjfeng.entity.GjfMemberInfo;
import cc.messcat.gjfeng.entity.GjfOrderInfo;
import cc.messcat.gjfeng.entity.GjfWeiXinPayInfo;
import cc.messcat.gjfeng.upload.UploadFileUtil;
import cc.messcat.gjfeng.util.SessionUtil;
import cc.messcat.gjfeng.web.wechat.IndexController;
import cc.messcat.gjfeng.web.wechat.OrderController;
import cc.messcat.gjfeng.web.wechat.ProductController;
import freemarker.core.ReturnInstruction.Return;

@Controller
@RequestMapping(value = "app/product/", headers = "app-version=v1.0")
public class ProductControllerV1 extends BaseController {

	@Value("${gjfeng.client.project.url}")
	private String projectName;

	@Value("${upload.product.path}")
	private String uploadFilePath;

	@Autowired
	@Qualifier("request")
	private HttpServletRequest request;

	@Autowired
	@Qualifier("response")
	private HttpServletResponse response;

	@Autowired
	@Qualifier("indexDubbo")
	private GjfIndexDubbo indexDubbo;

	@Autowired
	@Qualifier("productAttrDubbo")
	private GjfProductAttrDubbo productAttrDubbo;

	@Autowired
	@Qualifier("productInfoDubbo")
	private GjfProductInfoDubbo productInfoDubbo;

	@Autowired
	@Qualifier("storeInfoDubbo")
	private GjfStoreInfoDubbo storeInfoDubbo;

	@Autowired
	@Qualifier("enterpriseColumnDubbo")
	private GjfEnterpriseColumnDubbo enterpriseColumnDubbo;
	
	@Autowired
	private GjfProductCommentDubbo productCommetDubbo;
	
	@Autowired
	@Qualifier("memberInfoDubbo")
	private GjfMemberInfoDubbo memberInfoDubbo;
	
	@Autowired
	@Qualifier("orderInfoDubbo")
	private GjfOrderInfoDubbo orderInfoDubbo;

	@Autowired
	@Qualifier("addressDubbo")
	private GjfAddressDubbo addressDubbo;
	
	@Autowired
	@Qualifier("tradeDubbo")
	private GjfTradeDubbo tradeDubbo;


	/**
	 * @描述 查询商家商品
	 * @author Karhs
	 * @date 2017年1月9日
	 * @return
	 */
	@RequestMapping(value = "v1_0/myStorePro", method = RequestMethod.POST)
	@ResponseBody
	public Object myProduct(Integer pageNo, Integer pageSize, String account) {
		try {
			Object o = storeInfoDubbo.findStoreByAccount(account).getResult();
			if (ObjValid.isValid(o)) {
				StoreInfoVo store = (StoreInfoVo) o;
				resultVo = productInfoDubbo.findProByStore(store.getId(), ObjValid.isNotValid(pageNo) ? 1 : pageNo,
						ObjValid.isNotValid(pageSize) ? 10 : pageSize);
			} else {
				resultVo = new ResultVo(400, "店铺不存在", null);
			}
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, ProductController.class);
		}
		return resultVo;
	}

	/**
	 * @描述 删除商品
	 * @author Karhs
	 * @date 2017年1月9日
	 * @return
	 */
	@RequestMapping(value = "v1_0/delStorePro", method = RequestMethod.POST)
	@ResponseBody
	public Object delProduct(String id, String account) {
		try {
			if (ObjValid.isNotValid(id)) {
				resultVo = new ResultVo(400, "商品不存在", null);
				return resultVo;
			}
			String[] allId = id.split(",");
			Long[] ids = new Long[allId.length];
			for (int i = 0; i < allId.length; i++) {
				ids[i] = Long.parseLong(allId[i]);
			}
			StoreInfoVo store = (StoreInfoVo) storeInfoDubbo.findStoreByAccount(account).getResult();
			resultVo = productInfoDubbo.delProduct(ids, store.getId());
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, ProductController.class);
		}
		return resultVo;
	}

	/**
	 * @描述 发布商品--发布成功跳到审核页面
	 * @author Karhs
	 * @date 2017年1月9日
	 * @return
	 */
	@RequestMapping(value = "v1_0/addProduct", method = RequestMethod.POST)
	@ResponseBody
	public Object addProduct(ProductInfoVo productInfoVo, @RequestParam("columnId") Long columnId, String fileContent,
			String fileName, String account) {
		try {
			if (ObjValid.isNotValid(productInfoVo)) {
				resultVo = new ResultVo(400, "提交失败，参数不全", null);
				return resultVo;
			}
			if (!fileContent.isEmpty()) {
				String[] str = fileContent.split(",");
				String fils = UploadFileUtil.uploadBase63(fileName, str[1], request, uploadFilePath);
				String syspath = request.getSession().getServletContext().getRealPath(uploadFilePath);
				String path = syspath + "\\" + fils;
				String[] fileNames = fils.split("\\.");
				String path1 = syspath + "\\" + fileNames[0] + "_1." + fileNames[1];
				ImageCompressUtil.saveMinPhoto(path, path1, 139, 1d);
				String ziPath = projectName + uploadFilePath + "/" + fileNames[0] + "_1." + fileNames[1];
				productInfoVo.setImgUrl(ziPath);
				productInfoVo.setImgUrl1(projectName + uploadFilePath + "/" + fils);
			} else {
				resultVo = new ResultVo(400, "文件上传失败", null);
				return resultVo;
			}
			Object o = storeInfoDubbo.findStoreByAccount(account).getResult();
			if (ObjValid.isNotValid(o)&&o==null) {
				resultVo = new ResultVo(400, "店铺不存在", null);
			}
			StoreInfoVo store = (StoreInfoVo) o;
			resultVo = productInfoDubbo.addO2OProduct(productInfoVo, store.getId(), columnId, account);
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, ProductController.class);
		}
		return resultVo;
	}

	/**
	 * @描述 编辑商品
	 * @author Karhs
	 * @date 2017年1月9日
	 * @return
	 */
	@RequestMapping(value = "v1_0/updatePro", method = RequestMethod.POST)
	@ResponseBody
	public Object updateProduct(ProductInfoVo productInfoVo, @RequestParam("columnId") Long columnId,
			String fileContent, String fileName, String account) {
		try {
			if (ObjValid.isNotValid(productInfoVo)) {
				resultVo = new ResultVo(400, "提交失败，参数不全", null);
				return resultVo;
			}
			if (!fileContent.isEmpty()) {
				String[] str = fileContent.split(",");
				String fils = UploadFileUtil.uploadBase63(fileName, str[1], request, uploadFilePath);
				String syspath = request.getSession().getServletContext().getRealPath(uploadFilePath);
				String path = syspath + "\\" + fils;
				String[] fileNames = fils.split("\\.");
				String path1 = syspath + "\\" + fileNames[0] + "_1." + fileNames[1];
				ImageCompressUtil.saveMinPhoto(path, path1, 139, 1d);
				String ziPath = projectName + uploadFilePath + "/" + fileNames[0] + "_1." + fileNames[1];
				productInfoVo.setImgUrl(ziPath);
				productInfoVo.setImgUrl1(projectName + uploadFilePath + "/" + fils);
			} 
			
			StoreInfoVo store = (StoreInfoVo) storeInfoDubbo.findStoreByAccount(account).getResult();
			resultVo = productInfoDubbo.updateO2OProduct(productInfoVo, store.getId(), columnId);
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, ProductController.class);
		}
		return resultVo;
	}

	/**
	 * @描述 o2o商城一级栏目或本栏目下的商品
	 * @author Karhs
	 * @date 2017年1月18日
	 * @param pageNo
	 * @param pageSize
	 * @param reqType
	 * @param columnId
	 * @param type
	 *            1:距离近 2:人气高 3:价格低 4: 最新
	 * @param columnType
	 *            栏目类型 1：一级栏目 2：二级栏目
	 * @param likeValue
	 *            模糊查询商品和店铺值
	 * @return
	 */
	@RequestMapping(value = "v1_0/o2o/products", method = RequestMethod.POST)
	@ResponseBody
	public Object o2oProductsByAllColumn(Integer pageNo, Integer pageSize, Long columnId,
			@RequestParam("orderType") String orderType, String columnType, String likeValue, Double longitude,
			Double latitude, Long cityId) {
		try {
			resultVo = indexDubbo.findO2OSubClassPro(ObjValid.isNotValid(pageNo) ? 1 : pageNo,
					ObjValid.isNotValid(pageSize) ? 10 : pageSize, columnId, columnType, orderType, likeValue,
					longitude, latitude, cityId);
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, ProductController.class);
		}
		return resultVo;
	}

	/**
	 * @描述 查询o2o商品信息
	 * @author Karhs
	 * @date 2017年1月19日
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "v1_0/o2o/productDetail/{id}", method = RequestMethod.POST)
	@ResponseBody
	public Object o2oProduct(@PathVariable("id") Long id, Double longitude, Double latitude,String account) {
		try {
			Map<String, Object> map=new HashMap<>();
			resultVo = productInfoDubbo.findO2OProductById(id, longitude, latitude);
			map.put("product", resultVo.getResult());
			map.put("comment", productCommetDubbo.getProComByPager(1, 3, id,"1").getResult());
			map.put("comCount", productCommetDubbo.countProCom(id,"1").getResult());
			map.put("isCollect",memberInfoDubbo.findMemberCollect(account, id, "2").getResult());
			resultVo.setResult(map);
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, ProductController.class);
		}
		return resultVo;
	}

	/**
	 * @描述 o2o查询本店热销商品
	 * @author Karhs
	 * @date 2017年1月20日
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "v1_0/o2o/hotProduct", method = RequestMethod.POST)
	@ResponseBody
	public Object o2oHotProduct(@RequestParam("id") Long id, Integer pageNo, Integer pageSize) {
		try {
			// 查询本店热销商品
			resultVo = productInfoDubbo.findHotProByStoreId(id, ObjValid.isNotValid(pageNo) ? 1 : pageNo,
					ObjValid.isNotValid(pageSize) ? 10 : pageSize);
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, ProductController.class);
		}
		return resultVo;
	}

	/**
	 * @描述 查询网上商城首页一级栏目或栏目下的商品
	 * @author Karhs
	 * @date 2017年1月9日
	 * @return
	 */
	@RequestMapping(value = "v1_0/online/onlineProductsByAllColumn/{type}/{columnId}", method = RequestMethod.POST)
	@ResponseBody
	public Object onlineProductsByAllColumn(Integer pageNo, Integer pageSize, @PathVariable("columnId") Long columnId,
			@PathVariable("type") String type, String likeValue, Double longitude, Double latitude) {
		try {
			if (type.equals("1")) {

				resultVo = productInfoDubbo.findProByColumnId(columnId, ObjValid.isNotValid(pageNo) ? 1 : pageNo,
						ObjValid.isNotValid(pageSize) ? 10 : pageSize, likeValue, longitude, latitude);
			} else if (type.equals("0")) {
				resultVo = productInfoDubbo.findProByFatherColumnId(columnId, ObjValid.isNotValid(pageNo) ? 1 : pageNo,
						ObjValid.isNotValid(pageSize) ? 10 : pageSize, likeValue, longitude, latitude);
			} else {
				resultVo = new ResultVo(200, "查询成功", null);
			}
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, ProductController.class);
		}
		return resultVo;
	}
	
	/**
	 * @描述 查询网上商城商品详情
	 * @author Karhs
	 * @date 2017年1月9日
	 * @return
	 */
	@RequestMapping(value = "v1_0/online/onlineProductsDetailInfo/{id}", method = RequestMethod.POST)
	public Object onlineProductsDetailInfo(@PathVariable("id") Long id) {
		/*Map<String, Object> model = new HashMap<String, Object>();
		try {
			resultVo = productAttrDubbo.findProAttrByProIdInApp(id);			
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, ProductController.class);
			model.put("resultVo", resultVo);
		}
		return resultVo;*/		
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			// 查询商品信息
			// model.put("resultVo", productInfoDubbo.findProductById(id, 0L));
			resultVo = productAttrDubbo.findProAttrByProId(id);
			Object o = resultVo.getResult();
			if (ObjValid.isValid(o)) {
				LinkedHashMap<String, Object> data = new LinkedHashMap<String, Object>();
				data = (LinkedHashMap<String, Object>) o;
				model.put("productInfo", data.get("productInfo"));
				model.put("productAttrStock", data.get("productAttrStock"));
				data.remove("productInfo");
				data.remove("productAttrStock");
				model.put("productAttrs", data);
			}

			// 2.查询商品评论次数
			model.put("commentsNum", 0);
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, ProductController.class);
			model.put("resultVo", resultVo);
		}
		return new ModelAndView("wx/online-shop/goods-detail-info", model);
	}
	
	/**
	 * @描述 点击相应的商品属性值获取相应的价格和库存
	 * @author Karhs
	 * @date 2017年1月9日
	 * @return
	 */
	@RequestMapping(value = "v1_0/online/product/stockAndPrice", method = RequestMethod.POST)
	@ResponseBody
	public Object findProStock(@RequestParam("proId") Long proId, @RequestParam("attrIds") String attrIds) {
		try {
			resultVo = productAttrDubbo.findProAttrStockByAttrId(proId, attrIds);
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, ProductController.class);
		}
		return resultVo;
	}
	
	
	/**
	 * @描述 跳转到发布商品
	 * @author Karhs
	 * @date 2017年1月9日
	 * @return
	 */
	@RequestMapping(value = "v1_0/toAdd", method = RequestMethod.POST)
	@ResponseBody
	public Object toAddPro() {		
		try {
			// 查询商品栏目
			resultVo=indexDubbo.findO2OIndexColumnInApp();
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, ProductController.class);		
		}
		return resultVo;
	}
	
	/**
	 * @描述 根据父类ID查询子栏目
	 * @author Karhs
	 * @date 2017年2月4日
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "v1_0/subColumn", method = RequestMethod.POST)
	@ResponseBody
	public Object findSubColumn(@PathVariable("id") Long id){
		try {
			resultVo=enterpriseColumnDubbo.findColumnByFatherId(id);
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, IndexController.class);
		}
		return resultVo;
	}
	
	
	/**
	 * @描述 跳转到编辑商品
	 * @author Karhs
	 * @date 2017年1月9日
	 * @return
	 */
	@RequestMapping(value = "v1_0/toUpdate", method = RequestMethod.POST)
	@ResponseBody
	public Object toUpdatePro(Long id,String account) {
		Map<String, Object> model = new HashMap<String, Object>();
		try {			
			StoreInfoVo store = (StoreInfoVo) storeInfoDubbo.findStoreByAccount(account).getResult();
			resultVo=productInfoDubbo.findProductById(id, store.getId());
			model.put("product", productInfoDubbo.findProductById(id, store.getId()).getResult());
			// 查询商品栏目
			model.put("column", indexDubbo.findO2OIndexColumnInApp().getResult());
			resultVo.setResult(model);
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, ProductController.class);
			//model.put("resultVo", resultVo);
		}
		return resultVo;
	}
	
	
	/**
	 * @描述 查询网上商城商品详情
	 * @author Karhs
	 * @date 2017年1月9日
	 * @return
	 */
	@RequestMapping(value = "v1_0/online/onlineProductsDetailInfoInH5", method = RequestMethod.POST)
	@ResponseBody
	public Object onlineProductsDetailInfoInH5(Long id) {
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			resultVo = productAttrDubbo.findProAttrByProIdInApp(id);			
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, ProductController.class);
			model.put("resultVo", resultVo);
		}
		return resultVo;		
	}
	
	
	/**
	 * @描述 网上商城全部栏目
	 * @author Karhs
	 * @date 2017年1月9日
	 * @param account
	 * @return
	 */
	@RequestMapping(value = "v1_0/online/allColumn", method = RequestMethod.POST)
	@ResponseBody
	public Object onlineShopIndexAllColumn(){		
		try {
			resultVo=enterpriseColumnDubbo.findColumnsByFatherId("1", 1L);
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, IndexController.class);		
		}
		return resultVo;
		
	}
	
	
	/**
	 * 获取京东自营商城分类
	 * @return
	 */
	@RequestMapping(value="/v1_0/findProprietaryJdCategory",method=RequestMethod.POST)
	@ResponseBody
	public Object findProprietaryJdCategory(){
		Map<String, Object> map=new HashMap<>();
		try{
			cc.messcat.gjfeng.common.jd.bean.CatDetail c=JdNewUtil.addTestJdProductInfo();
			if(BeanUtil.isValid(c)){
				//获取第一个类别商品
				resultVo=productInfoDubbo.findJdProprietaryProduct(c.getFirstLevel().get(0).getCatId(), "1", "", "");
				
			}
			map.put("cat", c);
			map.put("product", resultVo.getResult());
			resultVo.setResult(map);
		}catch(Exception e){
			e.printStackTrace();
		}
		return resultVo;
	}
	
	/**
	 * 根据分类获取京东自营产品
	 * @param catId
	 * @return
	 */
	@RequestMapping(value="/v1_0/findJdProprietaryProByCatId",method=RequestMethod.POST)
	@ResponseBody
	public Object findJdProprietaryProByCatId(String catId,String page,String sup,String rateBegin){
		Map<String, Object> map=new HashMap<>();
		try{
			resultVo=productInfoDubbo.findJdProprietaryProduct(catId, page, sup, rateBegin);
			map.put("product", resultVo.getResult());
			/*cc.messcat.gjfeng.common.jd.bean.CatDetail c=JdNewUtil.addTestJdProductInfo();
			if(BeanUtil.isValid(c)&&BeanUtil.isValid(c.getSecondLevel())){
				List<FirstLevel> list=new ArrayList<>();
				for(int i=0;i<c.getSecondLevel().size();i++){
					if(c.getSecondLevel().get(i).getParentId().equals(catId)){
						list.add(c.getSecondLevel().get(i));
					}
				}
				map.put("secondLevel", list);
			}*/
			resultVo.setResult(map);
		}catch(Exception e){
			e.printStackTrace();
		}
		return resultVo;
	}
	
	/**
	 * 获取京东自营商品详细信息
	 * @return
	 */
	@RequestMapping(value="/v1_0/findJdProprietaryProDetail",method=RequestMethod.POST)
	@ResponseBody
	public Object findJdProprietaryProDetail(String goodsId){		
		try{
			GoodDetail goodDetail=JdNewUtil.getJdGoodDetail(goodsId);			
			resultVo=new ResultVo(200,"查询成功",goodDetail);
		}catch(Exception e){
			e.printStackTrace();
		}
		return resultVo;
	}
			
	/**
	 * 添加京东自营产品
	 * @param proId
	 * @return
	 */
	@RequestMapping(value="/v1_0/addJdProprietaryProduct",method=RequestMethod.POST)
	@ResponseBody
	public Object addJdProprietaryProduct(Long proId){
		try{
			resultVo=productInfoDubbo.addJdProprietaryProduct(proId);
		}catch(Exception e){
			e.printStackTrace();
		}
		return resultVo;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "v1_0/toAddOrder", method = RequestMethod.POST)
	@ResponseBody
	public Object toAddOrder(OrderAddModel orderAddModel, Long orderAddressId,String goodSource,String account,String logist) {
		Map<String, Object> model = new HashMap<String, Object>();
		try {			
			// 查询当前用户的最新信息
			List<OrderAddVo> orderAddVos = orderAddModel.getOrderAddVos();
			model.put("gjfMemberInfo", memberInfoDubbo.findMember(account));
			if (ObjValid.isValid(orderAddressId)) {
				model.put("memberDefAddress", addressDubbo.findAddressById(orderAddressId, account,goodSource));
			} else {
				// 查询用户默认的收货地址
				model.put("memberDefAddress", addressDubbo.findMyDefDeliveryAddress(account,goodSource));
			}
			// 查询订单信息
			resultVo = orderInfoDubbo.toAddOrder(orderAddVos,account);
			Object o = resultVo.getResult();
			if (ObjValid.isValid(o)) {
				Map<String, Object> data = (Map<String, Object>) o;
				model.put("orderSn", data.get("orderSn"));
				model.put("customerSn", data.get("customerSn"));
				model.put("totalAmount", data.get("totalAmount"));
				model.put("goodsVos", data.get("goodsVos"));
				model.put("isCanUseCou", data.get("isCanUseCou"));
				model.put("pos", data.get("pos"));
				model.put("proId", data.get("proId"));
				model.put("pointNiceAmount", data.get("pointNiceAmount"));
				model.put("goodSource", goodSource);
				model.put("pointMoney",data.get("pointMoney"));
				model.put("vocMoney", data.get("vocMoney"));
				model.put("isWholesale", data.get("isWholesale"));
				model.put("isUpgradePro", data.get("isUpgradePro"));
				model.put("standardTotalAmount", data.get("standardTotalAmount"));
				model.put("honourTotalAmount", data.get("honourTotalAmount"));
				model.put("honourPreferentialMoney", data.get("honourPreferentialMoney"));
				model.put("standardPreferentialMoney", data.get("standardPreferentialMoney"));
			}
			model.put("orderAddVos", orderAddVos);
			model.put("logist", logist);
			resultVo.setResult(model);

			// 查询物流费用
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, OrderController.class);
			model.put("memberDefAddress", null);
			model.put("orderGoods", null);
			resultVo.setResult(model);
		}
		return resultVo;
	}
					
}
