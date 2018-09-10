package cc.modules.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;


/**
 * 
 * cookie对象相关操作
 * 
 * @author Lich Li
 *
 */
public class CookieUtil {
	
	//把对象转换为字节数组，使用base64编码,最后返回String
	public static String Obj2String(Object obj){
		byte[] bytes = null;      
        ByteArrayOutputStream bos = new ByteArrayOutputStream();      
        try {        
            ObjectOutputStream oos = new ObjectOutputStream(bos);         
            oos.writeObject(obj);        
            oos.flush();         
            bytes = bos.toByteArray ();
            String res = SecurityTool.base64Encoder(bytes);
            oos.close();         
            bos.close();   
            return res;
        } catch (IOException ex) {        
            ex.printStackTrace();   
        }      
        return null;    
	}
	
	//把字符串转换为对象,具体对象加上强制类型转换
	public static Object String2Obj(String content){
		Object obj = null;      
        try {        
        	byte[] bytes = SecurityTool.base64DecoderTobytes(content);
            ByteArrayInputStream bis = new ByteArrayInputStream (bytes);        
            ObjectInputStream ois = new ObjectInputStream (bis);        
            obj = ois.readObject();      
            ois.close();   
            bis.close();   
        } catch (IOException ex) {        
            ex.printStackTrace();   
        } catch (ClassNotFoundException ex) {        
            ex.printStackTrace();   
        }      
        return obj;  
	}
	
	//方便读取cookie
	public static Cookie getCookieByName(String name){
	    Map<String,Cookie> cookieMap = ReadCookieMap();
	    if(cookieMap.containsKey(name)){
	        Cookie cookie = (Cookie)cookieMap.get(name);
	        return cookie;
	    }else{
	        return null;
	    }   
	}
	 
	//将cookie封装在map中
	private static Map<String,Cookie> ReadCookieMap(){  
	    Map<String,Cookie> cookieMap = new HashMap<String,Cookie>();
	    Cookie[] cookies = Struts2Utils.getRequest().getCookies();
	    if(null!=cookies){
	        for(Cookie cookie : cookies){
	            cookieMap.put(cookie.getName(), cookie);
	        }
	    }
	    return cookieMap;
	}
	
}
