package cc.messcat.gjfeng.interceptor;

import java.util.HashMap;
import java.util.Map;

/** 
 * 拦截器公共类
 * @author sherry
 *
 */
public class InterceptorUtil {
	
	/**
	 * 从将queryString的参数（用get请求的）转为map
	 * @param queryString
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map queryStringToMap(String queryString){
		Map map = new HashMap();
		String[] params = queryString.split("&");
		for (int i = 0; i < params.length; i++) {
			int charIndex = params[i].indexOf('=');
			map.put(params[i].substring(0, charIndex), params[i].substring(charIndex+1));
		}
		return map;
	}
}
