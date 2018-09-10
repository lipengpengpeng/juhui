package cc.messcat.gjfeng.common.security;

import java.rmi.server.RemoteServer;
import java.util.Set;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class PermissionRMIInterceptor implements MethodInterceptor{

	private java.util.Set<String> permissionHost;

	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
		String clientHost = RemoteServer.getClientHost();
		if (permissionHost != null && permissionHost.contains(clientHost)) {
			return methodInvocation.proceed();
		} else {
			throw new SecurityException("非法访问...");
		}
	}

	public void setAllowed(Set<String> permissionHost) {
		this.permissionHost = permissionHost;
	}

	public java.util.Set<String> getPermissionHost() {
		return permissionHost;
	}

	public void setPermissionHost(java.util.Set<String> permissionHost) {
		this.permissionHost = permissionHost;
	}
	
}
