package cc.messcat.web.front;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cc.messcat.bases.BaseAction;
import cc.modules.util.SmsUtil;
import cc.modules.util.ValidationRule;

import com.opensymphony.xwork2.ActionContext;

/**
 * 
 * 发送手机验证码
 * 
 * @author Lich Li
 *
 */
public class SmsSendAction extends BaseAction{
	

//	private String mobile;//手机号码
//	private String type;//类型：注册：reg  重置密码：reset
//	
//	HttpServletRequest request = ServletActionContext.getRequest();
//	HttpSession session = request.getSession(true);
//	
//	@Override
//	public String execute() throws Exception {
//		
//		
//		System.out.println("请求发送手机验证码");
//		//参数验证
//		if(getMobile() == null || getType() == null){
//			return null;
//		}
//		System.out.println("参数验证通过");
//		
//		//修改密码
//		if(getType().equals("chk_phone")){
//			System.out.println("修改密码请求");
//			//发送验证码
//			String code = SmsUtil.createCode();
//			String content = SmsUtil.getContent(code);
//			String result = SmsUtil.send(getMobile(),content);
//			//发送成功
//			if(result.equals("0")){
//				System.out.println("发送成功，验证码："+code);
//				session.setAttribute("rand", code);
//			}
//		}
//		
//		//注册
//		if(getType().equals("reg")){
//			System.out.println("注册请求");
//			//手机号验证
//			if(!ValidationRule.isMobile(getMobile())){
//				return null;
//			}
//			System.out.println("手机号验证通过");
//			
//			//验证手机号是否已经被注册
//			if(this.memberManagerDao.checkMobile(getMobile()) != null){
//				return null;
//			}
//			System.out.println("手机号未被注册");
//			//发送验证码
//			String code = SmsUtil.createCode();
//			String content = SmsUtil.getContent(code);
//			
//			System.out.println("手机号:"+getMobile());
//			System.out.println("内容："+content);
//			
//			String result = SmsUtil.send(getMobile(),content);
//			//发送成功
//			if(result.equals("0")){
//				
//				
//				
//				System.out.println("发送成功，验证码："+code);
//
//				
//				session.setAttribute("rand", code);
//
//			}
//			
//		}
//		
//		return null;
//	}
//	
//	public String getMobile() {
//		return mobile;
//	}
//	public void setMobile(String mobile) {
//		this.mobile = mobile;
//	}
//	public String getType() {
//		return type;
//	}
//	public void setType(String type) {
//		this.type = type;
//	}
	
	
}
