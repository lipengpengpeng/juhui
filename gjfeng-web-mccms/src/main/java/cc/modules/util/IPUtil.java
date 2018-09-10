package cc.modules.util;

import javax.servlet.http.HttpServletRequest;

/**
 * 获取终端访问ip地址工具
 * @ClassName: IPUtil 
 * @Description: TODO
 * @author StevenWang
 * @date 2016-7-7 下午02:39:09
 */
public class IPUtil {

	public static String getIp(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (StringUtil.isNotBlank(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个ip值，第一个ip才是真实ip
			int index = ip.indexOf(",");
			if (index != -1) {
				return ip.substring(0, index);
			} else {
				return ip;
			}
		}
		ip = request.getHeader("X-Real-IP");
		if (StringUtil.isNotBlank(ip) && !"unKnown".equalsIgnoreCase(ip)) {
			return ip;
		}
		return request.getRemoteAddr();
	}
}
