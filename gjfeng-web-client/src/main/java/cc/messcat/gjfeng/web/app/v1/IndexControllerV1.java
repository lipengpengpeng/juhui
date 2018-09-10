package cc.messcat.gjfeng.web.app.v1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import cc.messcat.gjfeng.common.util.BeanUtil;
import cc.messcat.gjfeng.common.util.ObjValid;
import cc.messcat.gjfeng.common.util.StringUtil;
import cc.messcat.gjfeng.common.vo.app.ResultVo;
import cc.messcat.gjfeng.common.web.BaseController;
import cc.messcat.gjfeng.dubbo.core.GjfEnterpriseColumnDubbo;
import cc.messcat.gjfeng.dubbo.core.GjfIndexDubbo;
import cc.messcat.gjfeng.dubbo.core.GjfMemberInfoDubbo;
import cc.messcat.gjfeng.dubbo.core.GjfProductInfoDubbo;
import cc.messcat.gjfeng.entity.GjfMemberInfo;
import cc.messcat.gjfeng.web.wechat.IndexController;

@Controller
@RequestMapping(value = "app/index/", headers = "app-version=v1.0")
public class IndexControllerV1 extends BaseController {

	@Autowired
	@Qualifier("indexDubbo")
	private GjfIndexDubbo indexDubbo;

	@Autowired
	@Qualifier("enterpriseColumnDubbo")
	private GjfEnterpriseColumnDubbo enterpriseColumnDubbo;
	
	@Autowired
	@Qualifier("memberInfoDubbo")
	private GjfMemberInfoDubbo memberInfoDubbo;
	
	@Autowired
	@Qualifier("productInfoDubbo")
	private GjfProductInfoDubbo productInfoDubbo;
	
	
	@Value("${gjfeng.client.project.url}")
	private String projectName;

	/**
	 * @描述 O2O商城
	 * @author Karhs
	 * @date 2017年3月14日
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "v1_0/o2o", method = RequestMethod.POST)
	@ResponseBody
	public Object o2oIndex() {
		Map map = new HashMap();
		try {
			resultVo = indexDubbo.findO2OIndexColumn();
			List list0 = (List) indexDubbo.findAd("O2O_INDEX_AD").getResult();
			map.put("indexColumn", indexDubbo.findO2OIndexColumn().getResult());
			map.put("indexAd", list0);
			resultVo.setResult(map);
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, IndexControllerV1.class);
		}
		return resultVo;
	}

	/**
	 * @描述 o2o商城的首页猜你喜欢
	 * @author Karhs
	 * @date 2017年3月14日
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "v1_0/o2o/like", method = RequestMethod.POST)
	@ResponseBody
	public Object o2oProductsLike(Integer pageNo, Integer pageSize, Double longitude, Double latitude, Long cityId) {
		try {
			resultVo = indexDubbo.findO2OIndexPro(ObjValid.isNotValid(pageNo) ? 1 : pageNo,
					ObjValid.isNotValid(pageSize) ? 10 : pageSize, longitude, latitude, cityId);
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, IndexControllerV1.class);
		}
		return resultVo;
	}

	/**
	 * @描述 O2O商城子栏目页
	 * @author Karhs
	 * @date 2017年3月14日
	 * @param columnId
	 * @return
	 */
	@RequestMapping(value = "v1_0/o2o/subColumn/{columnId}", method = RequestMethod.POST)
	@ResponseBody
	public Object o2oSubColumn(@PathVariable("columnId") Long columnId) {
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			resultVo = enterpriseColumnDubbo.findColumnByFatherId(columnId);
			model.put("subColumns", enterpriseColumnDubbo.findColumnByFatherId(columnId).getResult());
			model.put("columnId", columnId);
			model.put("subAds", indexDubbo.findAd("O2O_SUB_INDEX_AD").getResult());
			resultVo.setResult(model);
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, IndexControllerV1.class);
		}
		return resultVo;
	}

	/**
	 * @描述 获取用户搜索历史记录
	 * @author Karhs
	 * @date 2017年3月14日
	 * @param columnId
	 * @param columnType
	 * @param likeValue
	 * @return
	 */
	@RequestMapping(value = "v1_0/o2o/search", method = RequestMethod.POST)
	@ResponseBody
	public Object o2oSearch(Long memberId) {
		try {
			if (!BeanUtil.isValid(memberId)) {
				memberId = 0L;
			}
			resultVo=indexDubbo.findSearchHistory(memberId);
			
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, IndexControllerV1.class);
		}
		return resultVo;
	}
	
	/**
	 * @描述 搜索查询商品
	 * @author Karhs
	 * @date 2017年1月20日
	 * @param columnId
	 * @return
	 */
	@RequestMapping(value = "v1_0/o2o/addSearch", method = RequestMethod.POST)
	@ResponseBody
	public Object addSearch(String account,String likeValue) {
		try {
			//添加搜索记录
			GjfMemberInfo member=memberInfoDubbo.findMember(account);
			if (ObjValid.isValid(member) && StringUtil.isNotBlank(likeValue)) {
				resultVo=indexDubbo.addSearchHistory(member, likeValue);
			}
			
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, IndexController.class);
			
		}
		return resultVo;
	}

	/**
	 * @描述 附近
	 * @author Karhs
	 * @date 2017年3月14日
	 * @return
	 */
	@RequestMapping(value = "v1_0/near", method = RequestMethod.POST)
	@ResponseBody
	public Object nearlProduct() {		
		try {
			//resultVo=enterpriseColumnDubbo.findO2ONearColumn();findNearColumnInApp
			resultVo=enterpriseColumnDubbo.findNearColumnInApp();
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, IndexController.class);
		}
		return resultVo;
	}
	
	/**
	 * 获取附近一级栏目
	 * @return
	 */
	@RequestMapping(value = "v1_0/findNearColum", method = RequestMethod.POST)
	@ResponseBody
	public Object findNearColum(){
		try {
			resultVo=enterpriseColumnDubbo.findNearColumnInApp();
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, IndexController.class);
		}
		return resultVo;
	}
	
	/**
	 * 获取附近一级栏目下的子栏目
	 * @return
	 */
	@RequestMapping(value = "v1_0/findNearSubColum", method = RequestMethod.POST)
	@ResponseBody
	public Object findNearSubColum(Long columId){
		try {
			resultVo=enterpriseColumnDubbo.findColumnByFatherId(columId);
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, IndexController.class);
		}
		return resultVo;
	}

	/**
	 * @描述 网上商城
	 * @author Karhs
	 * @date 2017年1月9日
	 * @param account
	 * @return
	 */
	@RequestMapping(value = "v1_0/online", method = RequestMethod.POST)
	@ResponseBody
	public Object onlineShopIndex(){
		/*Map<String, Object> model = new HashMap<String, Object>();
		try {
			model.put("indexAds", indexDubbo.findAd("SHOP_INDEX_AD"));
			model.put("indexColumns", indexDubbo.findShopIndexCloumn());
			model.put("indexProducts", indexDubbo.findShopIndexPro());
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, IndexController.class);
			model.put("indexAds",null);
			model.put("indexColumns",null);
			model.put("indexProducts",null);
		}*/
		resultVo=new ResultVo(200,"查询成功",projectName+"/wx/index/app/onlinePage");
		return resultVo;
	}
	
	
	/**
	 * @描述 网上商城
	 * @author Karhs
	 * @date 2017年1月9日
	 * @param account
	 * @return
	 */
	@RequestMapping(value = "v1_0/onlineInH5", method = RequestMethod.POST)
	@ResponseBody
	public Object onlineShopIndexInH5(){
		Map<String, Object> model = new HashMap<String, Object>();
		resultVo=new ResultVo();
		try {
			model.put("indexAds", indexDubbo.findAd("SHOP_INDEX_AD").getResult());
			model.put("indexColumns", indexDubbo.findShopIndexCloumn().getResult());
			model.put("indexProducts", indexDubbo.findShopIndexPro().getResult());
			model.put("newList", enterpriseColumnDubbo.findEnterpriseNewsList(1, 3, ""));
			resultVo.setResult(model);
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, IndexController.class);
			model.put("indexAds",null);
			model.put("indexColumns",null);
			model.put("indexProducts",null);
			model.put("newList", null);
			resultVo.setResult(model);
		}
		return resultVo;
	}
	
	
	/**
	 * @描述 网上商城
	 * @author Karhs
	 * @date 2017年1月9日
	 * @param account
	 * @return
	 */
	@RequestMapping(value = "v1_0/ygOnlineShopIndex", method = RequestMethod.POST)
	@ResponseBody
	public Object ygOnlineShopIndex(){
		Map<String, Object> model = new HashMap<String, Object>();
		resultVo=new ResultVo();
		try {
			model.put("indexAds", indexDubbo.findAd("SHOP_INDEX_AD").getResult());			
			model.put("indexProducts", productInfoDubbo.findProductInfoIndex().getResult());
			resultVo.setResult(model);
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, IndexController.class);
			model.put("indexAds",null);			
			model.put("indexProducts",null);
			resultVo.setResult(model);
		}
		return resultVo;
	}
	
	
	/**
	 * 跳转到新闻页面
	 * @return
	 */
	@RequestMapping(value = "v1_0/findNewsById", method = RequestMethod.POST)
	@ResponseBody
	public Object findNewsById(Long id){		
		try{
			resultVo=enterpriseColumnDubbo.findEnterpriseNewsListsById(id);		
		}catch(Exception e){
			e.printStackTrace();
		}
		return  resultVo;
	}
	
	/**
	 * 获取新闻列表
	 * @return
	 */
	@RequestMapping(value = "v1_0/findNewsList", method = RequestMethod.POST)
	@ResponseBody
	public Object findNewsList(Integer pageNo,Integer pageSize,String keyWord){		
		try{
			resultVo=enterpriseColumnDubbo.findEnterpriseNewsList(pageNo, pageSize, keyWord);			
		}catch(Exception e){
			e.printStackTrace();
		}
		return resultVo;
	}
	
	
	/**
	 * @描述 供应链网上商城
	 * @author Karhs
	 * @date 2017年1月9日
	 * @param account
	 * @return supply chain
	 */
	@RequestMapping(value = "v1_0/supplyChainOnlineShopIndex", method = RequestMethod.POST)
	@ResponseBody
	public Object supplyChainOnlineShopIndex(){
		Map<String, Object> model = new HashMap<String, Object>();
		resultVo=new ResultVo();
		try {
			model.put("indexAds", indexDubbo.findAd("SHOP_INDEX_AD").getResult());
			model.put("indexActivity", indexDubbo.findAd("ACTIVITY_AD").getResult());
			model.put("indexColumns", indexDubbo.findShopIndexCloumn());
			model.put("indexProducts", productInfoDubbo.findProductInfoIndex().getResult());
			model.put("indexSupColumns", indexDubbo.findSupplyChainPoIndexColumn().getResult());
			resultVo.setResult(model);
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, IndexController.class);
			model.put("indexAds",null);	
			model.put("indexColumns", null);
			model.put("indexProducts",null);
			model.put("indexSupColumns", null);
			resultVo.setResult(model);
		}
		return resultVo;
	}
	
	
	
		
}
