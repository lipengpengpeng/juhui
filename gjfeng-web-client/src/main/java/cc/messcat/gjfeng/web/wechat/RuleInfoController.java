package cc.messcat.gjfeng.web.wechat;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cc.messcat.gjfeng.common.exception.LoggerPrint;
import cc.messcat.gjfeng.common.web.BaseController;
import cc.messcat.gjfeng.dubbo.core.GjfEnterpriseColumnDubbo;

/**
 * 基础信息，如协议类
 * @author Karhs
 */
@Controller
@RequestMapping("wx/rule/")
public class RuleInfoController extends BaseController {
	
	@Autowired
	@Qualifier("enterpriseColumnDubbo")
	private GjfEnterpriseColumnDubbo enterpriseColumnDubbo;

	/**
	 * @描述 消费规则
	 * @author Karhs
	 * @date 2017年1月9日
	 * @return
	 */
	@RequestMapping(value = "consumption", method = RequestMethod.GET)
	public ModelAndView toConsumptionRules(){
		Map<String, Object> model = new HashMap<String, Object>();
		try {	
			model.put("resultVo", enterpriseColumnDubbo.findBaseInfoByKey("consumption_rules"));
		} catch (Exception e) {
			model.put("resultVo", LoggerPrint.getResult(e, MemberController.class));
		}
		return new ModelAndView("wx/o2o-shop/rule-consumption",model);
	}
	
	/**
	 * @描述 代理规则
	 * @author Karhs
	 * @date 2017年1月9日
	 * @return
	 */
	@RequestMapping(value = "agent", method = RequestMethod.GET)
	public ModelAndView toAgentRules(){
		Map<String, Object> model = new HashMap<String, Object>();
		try {	
			model.put("resultVo", enterpriseColumnDubbo.findBaseInfoByKey("agent_rules"));
		} catch (Exception e) {
			model.put("resultVo", LoggerPrint.getResult(e, MemberController.class));
		}
		return new ModelAndView("wx/o2o-shop/rule-agent",model);
	}
	
	/**
	 * @描述 金凤凰服务协议
	 * @author Karhs
	 * @date 2017年1月9日
	 * @return
	 */
	@RequestMapping(value = "service", method = RequestMethod.GET)
	public ModelAndView toServiceRules(){
		Map<String, Object> model = new HashMap<String, Object>();
		try {	
			model.put("resultVo", enterpriseColumnDubbo.findBaseInfoByKey("service_rules"));
		} catch (Exception e) {
			model.put("resultVo", LoggerPrint.getResult(e, MemberController.class));
		}
		return new ModelAndView("wx/o2o-shop/rule-service",model);
	}
	
	/**
	 * @描述 金凤凰用户注册服务协议
	 * @author Karhs
	 * @date 2017年1月9日
	 * @return
	 */
	@RequestMapping(value = "memService", method = RequestMethod.GET)
	public ModelAndView toMemServiceRules(){
		Map<String, Object> model = new HashMap<String, Object>();
		try {	
			model.put("resultVo", enterpriseColumnDubbo.findBaseInfoByKey("mem_service_rules"));
		} catch (Exception e) {
			model.put("resultVo", LoggerPrint.getResult(e, MemberController.class));
		}
		return new ModelAndView("wx/o2o-shop/rule-member-service",model);
	}
	
	/**
	 * @描述 金凤凰用户注册服务协议
	 * @author Karhs
	 * @date 2017年1月9日
	 * @return
	 */
	@RequestMapping(value = "storeService", method = RequestMethod.GET)
	public ModelAndView tostoreServiceRules(){
		Map<String, Object> model = new HashMap<String, Object>();
		try {	
			model.put("resultVo", enterpriseColumnDubbo.findBaseInfoByKey("store_service_rules"));
		} catch (Exception e) {
			model.put("resultVo", LoggerPrint.getResult(e, MemberController.class));
		}
		return new ModelAndView("wx/o2o-shop/rule-store-service",model);
	}
	
	/**
	 * @描述 关于金凤凰
	 * @author Karhs
	 * @date 2017年1月9日
	 * @return
	 */
	@RequestMapping(value = "about", method = RequestMethod.GET)
	public ModelAndView toAboutGfh(){
		Map<String, Object> model = new HashMap<String, Object>();
		try {	
			model.put("resultVo", enterpriseColumnDubbo.findBaseInfoByKey("about_gjfeng"));
		} catch (Exception e) {
			model.put("resultVo", LoggerPrint.getResult(e, MemberController.class));
		}
		
		return new ModelAndView("wx/o2o-shop/rule-about-us",model);
	}
	
	/**
	 * @描述 下载
	 * @author Karhs
	 * @date 2017年1月9日
	 * @return
	 */
	@RequestMapping(value = "download", method = RequestMethod.GET)
	public ModelAndView download(){
		return new ModelAndView("wx/o2o-shop/download");
	}
	
	/**
	 * 合作协议
	 * @return
	 */
	@RequestMapping(value = "memberCooperationRule", method = RequestMethod.GET)
	public ModelAndView memberCooperationRule(String key){
		Map<String, Object> model = new HashMap<String, Object>();
		try {	
			model.put("resultVo", enterpriseColumnDubbo.findBaseInfoByKey(key));
		} catch (Exception e) {
			model.put("resultVo", LoggerPrint.getResult(e, MemberController.class));
		}
		return new ModelAndView("wx/o2o-shop/rule-service2",model);
	}
}
