package cc.messcat.gjfeng.web.wechat;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.messcat.gjfeng.common.exception.LoggerPrint;
import cc.messcat.gjfeng.common.web.BaseController;
import cc.messcat.gjfeng.dubbo.core.GjfEnterpriseColumnDubbo;

@Controller
@RequestMapping("wx/column/")
public class ColumnController extends BaseController {

	@Autowired
	@Qualifier("enterpriseColumnDubbo")
	private GjfEnterpriseColumnDubbo enterpriseColumnDubbo;
	
	/**
	 * @描述 网上商城全部栏目
	 * @author Karhs
	 * @date 2017年1月9日
	 * @param account
	 * @return
	 */
	@RequestMapping(value = "online/allColumn", method = RequestMethod.GET)
	public ModelAndView onlineShopIndexAllColumn(){
		Map<String, Object> model = new HashMap<String, Object>();
		try {
			model.put("resultVo", enterpriseColumnDubbo.findColumnsByFatherId("1", 1L));
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, IndexController.class);
			model.put("resultVo",null);
		}
		return new ModelAndView("wx/online-shop/goods-all-class", model);
	}
	
	/**
	 * @描述 根据父类ID查询子栏目
	 * @author Karhs
	 * @date 2017年2月4日
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "subColumn/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Object findSubColumn(@PathVariable("id") Long id){
		try {
			resultVo=enterpriseColumnDubbo.findColumnByFatherId(id);
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, IndexController.class);
		}
		return resultVo;
	}
	
}
