package cc.messcat.gjfeng.web.app.v1;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cc.messcat.gjfeng.common.exception.LoggerPrint;
import cc.messcat.gjfeng.common.vo.app.MemberInfoVo;
import cc.messcat.gjfeng.common.vo.app.ResultVo;
import cc.messcat.gjfeng.common.web.BaseController;
import cc.messcat.gjfeng.dubbo.core.GjfLoginDubbo;
import cc.messcat.gjfeng.dubbo.core.GjfMemberInfoDubbo;
import cc.messcat.gjfeng.dubbo.core.GjfTradeDubbo;

@Controller
@RequestMapping(value = "api/integtal", headers = "api-version=v1.0")
public class OpeningController  extends BaseController {
	
	@Autowired
	@Qualifier("loginDubbo")
	private GjfLoginDubbo loginDubbo;
	
	@Autowired
	@Qualifier("request")
	private HttpServletRequest request;
	
	@Autowired
	@Qualifier("memberInfoDubbo")
	private GjfMemberInfoDubbo memberInfoDubbo;
	
	@Autowired
	@Qualifier("tradeDubbo")
	private GjfTradeDubbo tradeDubbo;


	/**
	 * @描述 登陆
	 * @date 2017年3月14日
	 * @param account
	 * @param password
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "v1_0/login", method = RequestMethod.POST)
	@ResponseBody
	public Object login(@RequestParam("account") String account, @RequestParam("password") String password) {
		try {
			resultVo =loginDubbo.login(account,password,"0",null);
			//如果登录成功
			if(resultVo.getCode()==200){
				MemberInfoVo member=(MemberInfoVo) resultVo.getResult();
				//创建接收数据的map
				Map<String, Object> resultMap=new HashMap();
				//记录用户信息
				resultMap.put("id", member.getId());
				resultMap.put("mobile", member.getMobile());
				resultMap.put("sex", member.getSex());
				resultMap.put("name", member.getName());
				resultMap.put("nickName", member.getNickName());
				resultMap.put("consumptionMoney", member.getConsumptionMoney());
				resultMap.put("imgHeadUrl", member.getImgHeadUrl());
				resultVo.setResult(resultMap);
			}
			
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, LoginControllerV1.class);
		}
		return resultVo;
	}
	
	/**
	 * 转移积分
	 * @param type
	 * @param memberMobile
	 * @param transferMemberMobile
	 * @param transferMoney
	 * @return
	 */
	@RequestMapping(value = "/v1_0/memberIntegralTransfer", method = RequestMethod.POST)
	@ResponseBody
	public Object memberIntegralTransfer(String account,String type,String transferMemberMobile,BigDecimal transferMoney){
		try{
			if(!"2".equals(type)){
				resultVo=new ResultVo(400, "类型不正确", null);
			}
			resultVo=tradeDubbo.updateMemberPointTransfer(type, account, transferMemberMobile, transferMoney);
		}catch(Exception e){
			e.printStackTrace();
			resultVo=new ResultVo(500, "网络异常", null);
		}
		return resultVo;
	}

}
