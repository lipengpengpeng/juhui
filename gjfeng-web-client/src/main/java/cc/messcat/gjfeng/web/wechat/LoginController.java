package cc.messcat.gjfeng.web.wechat;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cc.messcat.gjfeng.common.exception.LoggerPrint;
import cc.messcat.gjfeng.common.netFriends.NetFriendUtil;
import cc.messcat.gjfeng.common.vo.app.MemberInfoVo;
import cc.messcat.gjfeng.common.vo.app.ResultVo;
import cc.messcat.gjfeng.common.web.BaseController;
import cc.messcat.gjfeng.dubbo.core.GjfLoginDubbo;
import cc.messcat.gjfeng.dubbo.core.GjfMemberInfoDubbo;
import cc.messcat.gjfeng.entity.GjfMemberInfo;
import cc.messcat.gjfeng.util.SessionUtil;
import cc.messcat.gjfeng.web.app.v1.LoginControllerV1;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("wx/login/")
public class LoginController extends BaseController{
	
	@Autowired
	@Qualifier("request")
	private HttpServletRequest request;

	@Autowired
	@Qualifier("loginDubbo")
	private GjfLoginDubbo loginDubbo;
	
	@Autowired
	@Qualifier("memberInfoDubbo")
	private GjfMemberInfoDubbo memberInfoDubbo;

	/**
	 * @描述 绑定手机号
	 * @author Karhs
	 * @date 2016年10月14日
	 * @return
	 */
	@RequestMapping(value = "bind", method = RequestMethod.POST)
	@ResponseBody
	public Object bindMobie(@RequestParam("mobile") String mobile,String password) {
		try {
			String unionId=null;//通过session拿
			resultVo = loginDubbo.bindMobie(unionId, mobile,password);
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, LoginController.class);
		}
		return resultVo;
	}
	
	/**
	 * @描述 登陆
	 * @author Karhs
	 * @date 2017年3月14日
	 * @param account
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/loginInWeb", method = RequestMethod.POST)
	@ResponseBody
	public Object loginInWeb(@RequestParam("account") String account, @RequestParam("password") String password) {
		try {
			resultVo =loginDubbo.login(account,password,"0",null);
			MemberInfoVo member=(MemberInfoVo) resultVo.getResult();
			request.getSession().setAttribute("account", member.getMobile());
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, LoginControllerV1.class);
		}
		return resultVo;
	}
	
	/**
	 * @描述 登陆
	 * @author Karhs
	 * @date 2017年3月14日
	 * @param account
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	@ResponseBody
	public Object login(@RequestParam("account") String account, @RequestParam("password") String password,String type) {
		try {
			System.out.println("登录type");
			if("1".equals(type)){
				resultVo =loginDubbo.login(account,password,"0",null);				
			}else{
				resultVo =loginDubbo.login(account,password,"0","1");	
			}
			
			System.out.println("app登录acount"+account);
			request.getSession().setAttribute("account", account);
			GjfMemberInfo member=memberInfoDubbo.findMember(account);
			request.getSession().setAttribute("gjfMemberInfo", member);
			resultVo.setResult(member);
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, LoginControllerV1.class);
		}
		return resultVo;
	}
	
	/**
	 * @描述 
	 * @param account
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/registerNetMem", method = RequestMethod.POST)
	@ResponseBody
	public Object registerNetMem() {
		try {
			String account = SessionUtil.getAccountSession(request);
			GjfMemberInfo memberInfo=memberInfoDubbo.findMember(account);
			if("0".equals(memberInfo.getIsRegisterNet())){
				String phoenix_id=memberInfo.getToken().substring(0, 50);
				JSONObject json=NetFriendUtil.registerNetFriend(phoenix_id, memberInfo.getNickName(), memberInfo.getMobile(), memberInfo.getImgHeadUrl());
			    int errcode=(int) json.get("errcode");
				if(errcode!=0){
				   return new ResultVo(400, "数据对接失败", null);
				}
				memberInfo.setIsRegisterNet("1");
				memberInfoDubbo.updateMemberCode(memberInfo);
			}
						
		} catch (Exception e) {
			resultVo = LoggerPrint.getResult(e, LoginControllerV1.class);
		}
		return resultVo;
	}
}
