package cc.messcat.gjfeng.common.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.commons.beanutils.converters.DateConverter;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 该BeanUtils子类用来注册DateConverter，增強BeanUtils类的转换能力
 *
 */
public class BeanUtilsEx extends BeanUtils {
	static {
		ConvertUtils.register(new DateConverter(null), Date.class);
	}

	public static void copyProperties(Object dest,
			Object orig) {
		try {
			BeanUtils.copyProperties(dest, orig);
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
		} catch (InvocationTargetException ex) {
			ex.printStackTrace();
		}
	}
	
	public static <T> T copyBean(Class<T> dest,Object orig){
		Object destObject = null;
		try {
			destObject = dest.newInstance();
			BeanUtilsEx.copyProperties(destObject, orig);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (T)destObject;
	}
	
	
	/**
	 * 将list集合中的元素替换成dest目标对象
	 * 
	 * @param list
	 * @param dest
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List changeList(List list, Class dest) {
		List destList = new ArrayList();
		for (Object orig : list) {
			try {
				Object destObject = dest.newInstance();
				BeanUtils.copyProperties(destObject, orig);
				destList.add(destObject);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}

		}
		return destList;
	}

	/**
	 * 将JavaBean对象转成Map<String, Object>
	 * @param obj
	 * @return
	 */
	public static Map<String, Object> changeToMap(Object obj) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
			PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(obj);
			for (PropertyDescriptor descriptor : descriptors) {
				String name = descriptor.getName();
				map.put(name, propertyUtilsBean.getNestedProperty(obj, name));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 判断list集合中的对象是否包含Set属性且Set不为空
	 * @param list
	 * @return
	 */
	public static boolean checkPojoSet(List list) {
		try {
			for(Object obj : list){
				PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
				PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(obj);
				for (PropertyDescriptor descriptor : descriptors) {
					String name = descriptor.getName();
					Object property = propertyUtilsBean.getNestedProperty(obj, name);
					if(property instanceof Set){
						if(((Set) property).size()>0){
							return true;
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;		
	}	

	/**
	 * bean 2 map
	 * 
	 * @param obj
	 * @return
	 */
	public static Map<String, Object> beanToMap(Object obj) {

		if (obj == null) {
			return null;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();
				// 过滤class属性
				if (!key.equals("class")) {
					// 得到property对应的getter方法
					Method getter = property.getReadMethod();
					Object value = getter.invoke(obj);
					if (null == value||"".equals(value))
						continue;
					map.put(key, value);
				}
			}
		} catch (Exception e) {
			System.out.println("transBean2Map Error " + e);
		}

		return map;

	}

	/**
	 * map 2 bean
	 * 
	 * @param map
	 * @param obj
	 */
	public static void mapToBean(Map<String, Object> map, Object obj) {

		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();
				if (map.containsKey(key)) {

					Object value = map.get(key);
					Method setter = property.getWriteMethod();
					setter.invoke(obj, value);
				}

			}

		} catch (Exception e) {
			System.out.println("transMap2Bean Error " + e);
		}

		return;

	}
	
	/**
	 * 遍历json，使用反射进行bean object组装
	 * @param json
	 * @param elemtypeClass
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Object jsonToBean(JSONObject json,Class elemtypeClass){
		Object object = null;
		// 实例object
		try {
			object = elemtypeClass.newInstance();
			// 遍历jsonObject
			Iterator it = json.keys(); 
			while (it.hasNext()) {  
                String key = (String) it.next();
                String value =  String.valueOf(json.get(key));  
                // 类反射得到类属性
				Field field;
				try {
					field = elemtypeClass.getDeclaredField(key);
					// 开发访问权限
					field.setAccessible(true);
					// set data
					if(!"null".equals(value)&&!"\"null\"".equals(value)&&!"".equals(value)){
						try {
							if (field.getType().equals(Timestamp.class)) {
								if(value.length()==13&&value.indexOf("-")<0){
									Long longTime = Long.parseLong(value);
									field.set(object, new Timestamp(longTime));
								}else{
									field.set(object, Timestamp.valueOf(value));									
								}
							}else if(field.getType().equals(String.class)){
								field.set(object, value);
							}else if(field.getType().equals(Integer.class)){
								field.set(object, Integer.parseInt(value));
							}else {
								field.set(object, value);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				} catch (NoSuchFieldException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				}
            }  
			
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return object;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map<String, Object> parserToMap(String s){  
	    Map map=new HashMap();  
	    JSONObject json=JSONObject.fromObject(s);  
	    Iterator keys=json.keys();  
	    while(keys.hasNext()){  
	        String key=(String) keys.next();  
	        String value=json.get(key).toString();  
	        if(value.startsWith("{")&&value.endsWith("}")){  
	            map.put(key, parserToMap(value)); 
	        } else if(value.startsWith("[")&&value.endsWith("]")){
	        	try {
	        		map.put(key, parserToList(value)); 
				} catch (Exception e) {
					map.put(key, value); 
				}
	        }else{  
	            map.put(key, value);  
	        }  
	  
	    }  
	    return map;  
	}
	
	public static List<Map<String,Object>> parserToList(String s){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Object[] json = JSONArray.fromObject(s).toArray();
		for (int i = 0; i < json.length; i++) {
			list.add(parserToMap(String.valueOf(json[i])));
		}
		return list;
	}
	
	
	/**
	 * 对象转换成整型
	 * @param obj
	 * @return
	 */
	public static int objToInt(Object obj){
		if(ObjValid.isValid(obj)){
			return Integer.valueOf(String.valueOf(obj));
		}
		return 0;
	}
	
	/**
	 * 对象转换成整型
	 * @param obj
	 * @return
	 */
	public static long objToLong(Object obj){
		if(ObjValid.isValid(obj)){
			return Long.valueOf(String.valueOf(obj));
		}
		return 0;
	}
	
	/**
	 * 对象转换成整型
	 * @param obj
	 * @return
	 */
	public static double objToDouble(Object obj){
		if(ObjValid.isValid(obj)){
			return Double.valueOf(String.valueOf(obj));
		}
		return 0.0;
	}
	
	/**
	 * 对象转换成整型
	 * @param obj
	 * @return
	 */
	public static String objToString(Object obj){
		if(ObjValid.isValid(obj)){
			return String.valueOf(obj);
		}
		return null;
	}

}
