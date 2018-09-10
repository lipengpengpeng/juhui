package cc.messcat.gjfeng.web.wechat;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.messcat.gjfeng.common.exception.LoggerPrint;
import cc.messcat.gjfeng.common.util.ObjValid;
import cc.messcat.gjfeng.common.util.StringUtil;
import cc.messcat.gjfeng.common.vo.app.ResultVo;
import cc.messcat.gjfeng.common.web.BaseController;
import cc.messcat.gjfeng.dubbo.core.GjfEnterpriseColumnDubbo;
import cc.messcat.gjfeng.dubbo.core.GjfIndexDubbo;
import cc.messcat.gjfeng.dubbo.core.GjfMemberInfoDubbo;
import cc.messcat.gjfeng.dubbo.core.GjfProductInfoDubbo;
import cc.messcat.gjfeng.entity.GjfMemberInfo;
import cc.messcat.gjfeng.util.SessionUtil;

@Controller
@RequestMapping("wx/index/")
public class IndexController extends BaseController{
	
	@Autowired
	@Qualifier("request")
	private HttpServletRequest request;
	
	@Autowired
	@Qualifier("indexDubbo")
	private GjfIndexDubbo indexDubbo;
	
	@Autowired
	@Qualifier("enterpriseColumnDubbo")
	private GjfEnterpriseColumnDubbo enterpriseColumnDubbo;
	
	@Autowired
	@Qualifier("productInfoDubbo")
	private GjfProductInfoDubbo productInfoDubbo;
	
	@Autowired
	@Qualifier("memberInfoDubbo")
	private GjfMemberInfoDubbo memberInfoDubbo;

	
	/**
	 * @描述 O2O商城
	 * @author Karhs
	 * @date 2017年1月9日
	 * @return
	 */
	@RequestMapping(value = "o2o", method = RequestMethod.GET)
	public ModelAndView o2oIndex(){
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			model.put("indexColumn", indexDubbo.findO2OIndexColumn());
			model.put("indexAd", indexDubbo.findAd("O2O_INDEX_AD"));
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, IndexController.class);
			model.put("indexColumn", resultVo);
		}
		return new ModelAndView("wx/o2o-shop/index", model);
	}
	
	/**
	 * @描述 o2o商城的首页猜你喜欢
	 * @author Karhs
	 * @date 2017年1月18日
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "o2o/like", method = RequestMethod.POST)
	public ModelAndView o2oProductsLike(Integer pageNo, Integer pageSize) {
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			Double longitude = SessionUtil.getLatOrLong(request, "longitude");
			Double latitude = SessionUtil.getLatOrLong(request, "latitude");
			Long cityId = SessionUtil.getCityCode(request);
			model.put("products", indexDubbo.findO2OIndexPro(ObjValid.isNotValid(pageNo) ? 1 : pageNo,
				ObjValid.isNotValid(pageSize) ? 10 : pageSize, longitude, latitude, cityId));
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, IndexController.class);
			model.put("resultVo", resultVo);
		}
		return new ModelAndView("wx/o2o-shop/index-goods-ajax", model);
	}
	
	/**
	 * @描述 O2O商城子栏目页
	 * @author Karhs
	 * @date 2017年1月18日
	 * @param columnId
	 * @return
	 */
	@RequestMapping(value = "o2o/subColumn/{columnId}", method = RequestMethod.GET)
	public ModelAndView o2oSubColumn(@PathVariable("columnId") Long columnId) {
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			model.put("subColumns", enterpriseColumnDubbo.findColumnByFatherId(columnId));
			model.put("columnId",columnId);
			model.put("subAds",indexDubbo.findAd("O2O_SUB_INDEX_AD"));
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, IndexController.class);
			model.put("resultVo", resultVo);
		}
		return new ModelAndView("wx/o2o-shop/index-subcolumn", model);
	}
	
	/**
	 * @描述 跳转到搜索查询商品
	 * @author Karhs
	 * @date 2017年1月20日
	 * @param columnId
	 * @return
	 */
	@RequestMapping(value = "o2o/toSearch", method = RequestMethod.GET)
	public ModelAndView o2oToSearch(Long columnId,String columnType) {
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			//查询搜索记录
			Object o = request.getSession().getAttribute("gjfMemberInfo");
			model.put("searchHistory", indexDubbo.findSearchHistory(null != o ? ((GjfMemberInfo) o).getId() : 0L));
			model.put("columnId",columnId);
			model.put("columnType",columnType);
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, IndexController.class);
			model.put("searchHistory", null);
			model.put("columnId",columnId);
			model.put("columnType",columnType);
		}
		return new ModelAndView("wx/o2o-shop/index-search", model);
	}
	
	/**
	 * @描述 搜索查询商品
	 * @author Karhs
	 * @date 2017年1月20日
	 * @param columnId
	 * @return
	 */
	@RequestMapping(value = "o2o/search", method = RequestMethod.POST)
	public ModelAndView o2oSearch(Long columnId,String columnType,String likeValue) {
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			//添加搜索记录
			Object o = request.getSession().getAttribute("gjfMemberInfo");
			if (ObjValid.isValid(o) && StringUtil.isNotBlank(likeValue)) {
				indexDubbo.addSearchHistory((GjfMemberInfo) o, likeValue);
			}
			model.put("likeValue",likeValue);
			model.put("columnId",columnId);
			model.put("columnType",columnType);
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, IndexController.class);
			model.put("likeValue",likeValue);
			model.put("columnId",columnId);
			model.put("columnType",columnType);
		}
		return new ModelAndView("wx/o2o-shop/index-search-goods", model);
	}
	
	/**
	 * @描述 附近
	 * @author Karhs
	 * @date 2017年1月22日
	 * @return
	 */
	@RequestMapping(value = "near", method = RequestMethod.GET)
	public ModelAndView nearlProduct(){
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			model.put("nearColumns", enterpriseColumnDubbo.findO2ONearColumn());
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, IndexController.class);
			model.put("nearColumns",null);
		}
		return new ModelAndView("wx/o2o-shop/index-near", model);
	}
	
	/**
	 * @描述 网上商城
	 * @author Karhs
	 * @date 2017年1月9日
	 * @param account
	 * @return
	 */
	@RequestMapping(value = "online", method = RequestMethod.GET)
	public ModelAndView onlineShopIndex(){
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			model.put("indexAds", indexDubbo.findAd("SHOP_INDEX_AD"));
			model.put("indexColumns", indexDubbo.findShopIndexCloumn());
			model.put("indexProducts", indexDubbo.findShopIndexPro());
			model.put("indexType","0");
			model.put("newList", enterpriseColumnDubbo.findEnterpriseNewsList(1, 3, ""));
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, IndexController.class);
			model.put("indexAds",null);
			model.put("indexColumns",null);
			model.put("indexProducts",null);
			model.put("indexType",null);
			model.put("newList", null);
		}
		return new ModelAndView("wx/online-shop/index", model);
	}
	
	
	/**
	 * @描述 商家采购
	 * @author Karhs
	 * @date 2017年1月9日
	 * @param account
	 * @return
	 */
	/**
	 * @描述 网上商城
	 * @author Karhs
	 * @date 2017年1月9日
	 * @param account
	 * @return
	 */
	@RequestMapping(value = "merchantOnline", method = RequestMethod.GET)
	public ModelAndView merchantOnline(){
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			model.put("indexAds", indexDubbo.findAd("SHOP_INDEX_AD"));
			model.put("indexColumns", indexDubbo.findShopIndexCloumn());
			model.put("indexProducts", indexDubbo.findShopIndexPro());
			model.put("indexType","1");
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, IndexController.class);
			model.put("indexAds",null);
			model.put("indexColumns",null);
			model.put("indexProducts",null);
			model.put("indexType",null);
		}
		return new ModelAndView("wx/online-shop/index", model);
	}		
	
	/**
	 * @描述 app网上商城
	 * @author Karhs
	 * @date 2017年1月9日
	 * @param account
	 * @return
	 */
	@RequestMapping(value = "app/onlinePage", method = RequestMethod.GET)
	public ModelAndView onlinePage(){
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			model.put("indexAds", indexDubbo.findAd("SHOP_INDEX_AD"));
			model.put("indexColumns", indexDubbo.findShopIndexCloumn());
			model.put("indexProducts", indexDubbo.findShopIndexPro());
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, IndexController.class);
			model.put("indexAds",null);
			model.put("indexColumns",null);
			model.put("indexProducts",null);
		}
		return new ModelAndView("app/index", model);
	}
	
	/**
	 * @描述 跳转到等待页面
	 * @author Karhs
	 * @date 2017年1月9日
	 * @param account
	 * @return
	 */
	@RequestMapping(value = "goWaiPage", method = RequestMethod.GET)
	public ModelAndView goWaiPage(){		
		return new ModelAndView("wx/o2o-shop/waiting");
	}
	
	
	/**
	 * @描述 网上商城
	 * @author Karhs
	 * @date 2017年1月9日
	 * @param account
	 * @return
	 */
	@RequestMapping(value = "jdIndex", method = RequestMethod.GET)
	public ModelAndView jdIndex(String conlumId){
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			
			model.put("indexColumn", enterpriseColumnDubbo.findJdIndexColumn());
			model.put("indexAd", indexDubbo.findAd("JD_INDEX_AD"));	
			model.put("products", productInfoDubbo.findProByColumnId(Long.valueOf(conlumId),1,10,"",null, null));
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, IndexController.class);
			model.put("indexColumn", null);
			model.put("indexAd", null);
			model.put("products", null);
		}
		return new ModelAndView("wx/online-shop/jd-index", model);
	}
	
	
	/**
	 * @描述 商家采购
	 * @author Karhs
	 * @date 2017年1月9日
	 * @param account
	 * @return
	 */
	@RequestMapping(value = "fhMerchantOnilne", method = RequestMethod.GET)
	public ModelAndView fhMerchantOnilne(){
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			String account = SessionUtil.getAccountSession(request);
			//获取用户信息
			GjfMemberInfo member=memberInfoDubbo.findMember(account);
			//System.out.println(member.getMerchantType());
			if("1".equals(member.getMerchantType())){//如果为标准版商户
				model.put("products", productInfoDubbo.findMerchantProcurementProductInfo(null,null, 1, 20,null));
				model.put("column", enterpriseColumnDubbo.findProductModelColumn("MODELPRODUCT","0"));
				model.put("merchantType", "1");				
				return new ModelAndView("wx/online-shop/voucher/mechantModel", model);
			}else if("2".equals(member.getMerchantType())||"3".equals(member.getMerchantType())||"4".equals(member.getMerchantType())||"5".equals(member.getMerchantType())||"6".equals(member.getMerchantType())||"7".equals(member.getMerchantType())){//如果为尊享版商户
				model.put("products", productInfoDubbo.findMerchantProcurementProductInfo(null,null, 1, 20,null));
				model.put("merchantType", "2");
				model.put("column", enterpriseColumnDubbo.findProductModelColumn("MODELPRODUCT","0"));
				return new ModelAndView("wx/online-shop/voucher/mechantModel", model);
			}else if("0".equals(member.getMerchantType())){//如果为普通商户
				model.put("member", member);
				return new ModelAndView("wx/online-shop/ttyg/merchant-index", model);
			}
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, IndexController.class);			
			model.put("resultVo",null);
		}
		return null;
	}
	
	
	
	
	/**
	 * 跳转到新闻页面
	 * @return
	 */
	@RequestMapping(value = "findNewsById", method = RequestMethod.GET)
	public ModelAndView findNewsById(Long id){
		Map<String, Object> model = new HashMap<String, Object>();
		try{
			resultVo=enterpriseColumnDubbo.findEnterpriseNewsListsById(id);
			model.put("newsInfo", resultVo.getResult());
		}catch(Exception e){
			e.printStackTrace();
		}
		return new ModelAndView("wx/online-shop/news/news-detail-info", model);
	}
	
	/**
	 * 获取新闻列表
	 * @return
	 */
	@RequestMapping(value = "findNewsList", method = RequestMethod.GET)
	public ModelAndView findNewsList(Integer pageNo,Integer pageSize,String keyWord){
		Map<String, Object> model = new HashMap<String, Object>();
		try{
			resultVo=enterpriseColumnDubbo.findEnterpriseNewsList(pageNo, pageSize, keyWord);
			model.put("newsInfo", resultVo.getResult());
		}catch(Exception e){
			e.printStackTrace();
		}
		return new ModelAndView("wx/online-shop/news/news-list", model);
	}
	
	
	/**
	 * @描述 网上商城
	 * @author Karhs
	 * @date 2017年1月9日
	 * @param account
	 * @return
	 */
	@RequestMapping(value = "newTtygOnlineIndex", method = RequestMethod.GET)
	public ModelAndView newTtygOnlineIndex(){
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			model.put("indexAds", indexDubbo.findAd("SHOP_INDEX_AD"));
			model.put("indexColumns", indexDubbo.findShopIndexCloumn());
			model.put("indexProducts", indexDubbo.findShopIndexPro());
			model.put("indexType","0");
			model.put("newList", enterpriseColumnDubbo.findEnterpriseNewsList(1, 3, ""));
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, IndexController.class);
			model.put("indexAds",null);
			model.put("indexColumns",null);
			model.put("indexProducts",null);
			model.put("indexType",null);
			model.put("newList", null);
		}
		return new ModelAndView("wx/online-shop/newTtyg/tiantian", model);
	}
	
	
	/**
	 * @描述 供应链网上商城
	 * @author Karhs
	 * @date 2017年1月9日
	 * @param account
	 * @return supply chain
	 */
	@RequestMapping(value = "supplyChainOnlineShopIndex", method = RequestMethod.GET)
	public ModelAndView supplyChainOnlineShopIndex(){
		Map<String, Object> model = new HashMap<String, Object>();
		resultVo=new ResultVo();
		try {
			model.put("indexAds", indexDubbo.findAd("SHOP_INDEX_AD").getResult());
			model.put("indexActivity", indexDubbo.findAd("ACTIVITY_AD").getResult());
			model.put("indexColumns", indexDubbo.findShopIndexCloumn());
			model.put("indexProducts", productInfoDubbo.findProductInfoIndex().getResult());
			model.put("indexSupColumns", indexDubbo.findSupplyChainPoIndexColumn().getResult());
			
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, IndexController.class);
			model.put("indexAds",null);	
			model.put("indexColumns", null);
			model.put("indexProducts",null);
			model.put("indexSupColumns", null);
			
		}
		return new ModelAndView("wx/online-shop/merchantModel/tiantian",model);
	}
	
	
	
}
