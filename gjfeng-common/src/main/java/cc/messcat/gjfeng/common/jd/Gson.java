package cc.messcat.gjfeng.common.jd;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


public class Gson{
	public static String toJson(Map<String,String> map){
	    Set<String> keys = map.keySet();
	    String key = "";
	    String value = "";
	    StringBuffer jsonBuffer = new StringBuffer();
	    jsonBuffer.append("{");    
	    for(Iterator<String> it = keys.iterator();it.hasNext();){
	        key =  (String)it.next();
	        value = map.get(key);
	        jsonBuffer.append(key+":"+value);
	        if(it.hasNext()){
	             jsonBuffer.append(",");
	        }
	    }
	    jsonBuffer.append("}");
	    return jsonBuffer.toString();
	}
	public static String toJson2(Map<String,Object> map){
		Set<Map.Entry<String, Object>> entrys = map.entrySet();
		Map.Entry<String, Object> entry = null;
	    String key = "";
	    Object value = "";
	    StringBuffer jsonBuffer = new StringBuffer();
	    jsonBuffer.append("{");    
	    for(Iterator<Map.Entry<String, Object>> it = entrys.iterator();it.hasNext();){
	    	entry =  (Map.Entry<String, Object>)it.next();
	    	key = entry.getKey();
	        value = entry.getValue();
	        jsonBuffer.append(key+":"+value);
	        if(it.hasNext()){
	             jsonBuffer.append(",");
	        }
	    }
	    jsonBuffer.append("}");
	    return jsonBuffer.toString();
	}
	
	public static String toJson3(Map<String,Object> map){
		Set<Map.Entry<String, Object>> entrys = map.entrySet();
		Map.Entry<String, Object> entry = null;
	    String key = "";
	    Object value = "";
	    StringBuffer jsonBuffer = new StringBuffer();
	    jsonBuffer.append("{");    
	    for(Iterator<Map.Entry<String, Object>> it = entrys.iterator();it.hasNext();){
	    	entry =  (Map.Entry<String, Object>)it.next();
	    	key = entry.getKey();
	        value = entry.getValue();
	        jsonBuffer.append(key+":"+value);
	        if(it.hasNext()){
	             jsonBuffer.append(",");
	        }
	    }
	    jsonBuffer.append("}");
	    return jsonBuffer.toString();
	}
	
	public static void main(String args[]){
		Map<String,String> map = new TreeMap<String,String>();
		map.put("1", "zhangyi");
		map.put("2", "zhanger");
		map.put("3", "zhangsan");
		map.put("4", "zhangsi");
		map.put("5", "zhangwu");
		System.out.println(toJson(map));
		//System.out.println(toJson2(map));
	}
}
