package cc.messcat.gjfeng.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import cc.messcat.gjfeng.common.util.ObjValid;
import cc.messcat.gjfeng.common.vo.app.StoreInfoVo;
import cc.messcat.gjfeng.dubbo.core.GjfAddressDubbo;
import cc.messcat.gjfeng.entity.GjfMemberInfo;

public class SessionUtil {
	
	@Autowired
	@Qualifier("addressDubbo")
	private static GjfAddressDubbo addressDubbo;

	/**
	 * @描述 获取账号session值
	 * @author Karhs
	 * @date 2017年1月19日
	 * @param request
	 * @return
	 */
	public static String getAccountSession(HttpServletRequest request){
		Object o=request.getSession().getAttribute("account");
		return (String) (ObjValid.isNotValid(o) ? "" : o);
	}
	
	/**
	 * @描述 获取unionId session值
	 * @author Karhs
	 * @date 2017年1月19日
	 * @param request
	 * @return
	 */
	public static String getUnionIdSession(HttpServletRequest request){
		Object o=request.getSession().getAttribute("unionId");
		return (String) (ObjValid.isNotValid(o) ? "" : o);
	}
	
	/**
	 * @描述 获取用户 session值
	 * @author Karhs
	 * @date 2017年1月19日
	 * @param request
	 * @return
	 */
	public static GjfMemberInfo getMemberSession(HttpServletRequest request){
		Object o=request.getSession().getAttribute("gjfMemberInfo");
		return ObjValid.isNotValid(o) ? null : (GjfMemberInfo)o;
	}
	
	/**
	 * @描述 获取店铺 session值
	 * @author Karhs
	 * @date 2017年1月19日
	 * @param request
	 * @return
	 */
	public static Long getStoreIdSession(HttpServletRequest request){
		Object o=request.getSession().getAttribute("gjfStoreInfo");
		if (ObjValid.isNotValid(o)) {
			return null;
		}
		return ((StoreInfoVo)o).getId();
	}
	
	/**
	 * @描述 获取店铺 session值
	 * @author Karhs
	 * @date 2017年1月19日
	 * @param request
	 * @return
	 */
	public static StoreInfoVo getStoreSession(HttpServletRequest request){
		Object o=request.getSession().getAttribute("gjfStoreInfo");
		return ObjValid.isNotValid(o) ? null : (StoreInfoVo)o;
	}
	
	/**
	 * @描述 获取经纬度的session值
	 * @author Karhs
	 * @date 2017年1月23日
	 * @param request
	 * @param key
	 * @return
	 */
	public static Double getLatOrLong(HttpServletRequest request,String key){
		Object o=request.getSession().getAttribute(key);
		if(ObjValid.isNotValid(o)){
			if(key.equals("longitude")){
				return 113.2416165593;
			}else if(key.equals("latitude")){
				return 23.1357768997;
			}
		}
		return Double.parseDouble(String.valueOf(o));
	}
	
	/**
	 * @描述 获取城市编号
	 * @author Karhs
	 * @date 2017年2月3日
	 * @param request
	 * @return
	 */
	public static Long getCityCode(HttpServletRequest request){
		Object o=request.getSession().getAttribute("cityCode");
		if (ObjValid.isNotValid(o)) {
			return null;
		}
		return null;
		//return Long.parseLong(String.valueOf(o));
	}
}
