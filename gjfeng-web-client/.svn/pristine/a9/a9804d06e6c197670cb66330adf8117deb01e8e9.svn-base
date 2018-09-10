package cc.messcat.gjfeng.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cc.messcat.gjfeng.common.util.BeanUtil;
import cc.messcat.gjfeng.common.util.ObjValid;
import cc.messcat.gjfeng.entity.GjfMemberInfo;
import cc.messcat.gjfeng.util.SessionUtil;

public class MobileInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String account=SessionUtil.getAccountSession(request);
		System.out.println("拦截中的电话号码："+account);
		if(BeanUtil.isValid(account)){	
			return true;
		}else{
			GjfMemberInfo gjfMemberInfo = SessionUtil.getMemberSession(request);
			if (ObjValid.isNotValid(gjfMemberInfo)) {
				response.sendRedirect("/gjfeng-web-client/wx/member/noMember");
				return false;
			}
			response.sendRedirect("/gjfeng-web-client/wx/member/toBindMobile");
			return false;
		}
		//return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
