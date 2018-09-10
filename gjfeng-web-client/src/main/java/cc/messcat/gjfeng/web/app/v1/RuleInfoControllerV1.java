package cc.messcat.gjfeng.web.app.v1;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.messcat.gjfeng.common.exception.LoggerPrint;
import cc.messcat.gjfeng.common.vo.app.ResultVo;
import cc.messcat.gjfeng.common.web.BaseController;
import cc.messcat.gjfeng.dubbo.core.GjfEnterpriseColumnDubbo;
import cc.messcat.gjfeng.web.wechat.MemberController;

@Controller
@RequestMapping(value="app/rule/",headers="app-version=v1.0")
public class RuleInfoControllerV1 extends BaseController {

	@Autowired
	@Qualifier("enterpriseColumnDubbo")
	private GjfEnterpriseColumnDubbo enterpriseColumnDubbo;
	
	@Value("${gjfeng.client.project.url}")
	private String projectName;
	
	/**
	 * @描述 关于金凤凰
	 * @author Karhs
	 * @date 2017年1月9日
	 * @return
	 */
	@RequestMapping(value = "v1_0/aboutGjf", method = RequestMethod.POST)
	@ResponseBody
	public Object toAboutGfh(){			
		resultVo=new ResultVo(200,"查询成功",projectName+"/wx/rule/about");
		return resultVo;
	}
	
	/**
	 * @描述 消费规则
	 * @date 2017年1月9日
	 * @return
	 */
	@RequestMapping(value = "v1_0/consumption", method = RequestMethod.POST)
	@ResponseBody
	public Object toConsumptionRules(){	
		resultVo=new ResultVo(200,"查询成功",projectName+"/wx/rule/consumption");
		return resultVo;
	}
	
	/**
	 * @描述 代理规则
	 * @author Karhs
	 * @date 2017年1月9日
	 * @return
	 */
	@RequestMapping(value = "v1_0/agent", method = RequestMethod.POST)
	@ResponseBody
	public Object toAgentRules(){
		resultVo=new ResultVo(200,"查询成功",projectName+"/wx/rule/agent");
		return resultVo;
	}
	
	/**
	 * @描述 金凤凰服务协议
	 * @author Karhs
	 * @date 2017年1月9日
	 * @return
	 */
	@RequestMapping(value = "v1_0/service", method = RequestMethod.POST)
	@ResponseBody
	public Object toServiceRules(){
		resultVo=new ResultVo(200,"查询成功",projectName+"/wx/rule/service");
		return resultVo;
	}
	
	/**
	 * @描述 下载
	 * @author Karhs
	 * @date 2017年1月9日
	 * @return
	 */
	@RequestMapping(value = "v1_0/download", method = RequestMethod.POST)
	@ResponseBody
	public Object download(){
		resultVo=new ResultVo(200,"查询成功",projectName+"/wx/rule/download");
		return resultVo;
	}
	
	/**
	 * @描述 用户注册协议
	 * @author Karhs
	 * @date 2017年1月9日
	 * @return
	 */
	@RequestMapping(value = "v1_0/memService", method = RequestMethod.POST)
	@ResponseBody
	public Object toMemServiceRules(){
		resultVo=new ResultVo(200,"查询成功",projectName+"/wx/rule/memService");
		return resultVo;
	}
	
	/**
	 * @描述 店铺注册协议
	 * @author Karhs
	 * @date 2017年1月9日
	 * @return
	 */
	@RequestMapping(value = "v1_0/toStoreServiceRules", method = RequestMethod.POST)
	@ResponseBody
	public Object toStoreServiceRules(){
		resultVo=new ResultVo(200,"查询成功",projectName+"/wx/rule/storeService");
		return resultVo;
	}
	
	
	/**
	 * @描述 消费规则
	 * @author Karhs
	 * @date 2017年1月9日
	 * @return
	 */
	@RequestMapping(value = "v1_0/consumptionInH5", method = RequestMethod.POST)
	@ResponseBody
	public Object consumptionInH5(){	
		try {	
			resultVo=enterpriseColumnDubbo.findBaseInfoByKey("consumption_rules");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultVo;
	}
	
	/**
	 * 合作协议
	 * @return
	 */
	@RequestMapping(value = "v1_0/memberCooperationRule", method = RequestMethod.POST)
	@ResponseBody
	public Object memberCooperationRule(String key){		
		try {	
			resultVo=enterpriseColumnDubbo.findBaseInfoByKey(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultVo;
	}
	
}
