package cc.modules.security;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthorityIntercept extends AbstractInterceptor {

	private static final long serialVersionUID = -5184793850414042013L;

	public AuthorityIntercept() {
	}

	public String intercept(ActionInvocation invocation) throws Exception {
		Map<String,Object> session = ActionContext.getContext().getSession();
		Object loginname = session.get("userID");
		if (loginname == null)
			return "login";
		else
			return invocation.invoke();
	}
}