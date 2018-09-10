package cc.messcat.gjfeng.common.util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import cc.messcat.gjfeng.common.httpclient.Requests;
import net.sf.json.JSONObject;  
  
   
public class BaiduApi {  
      
    public static final String KEY = "k87Wqb5XkmEfj0uHmGZ5Tf2cdwKQX9bl";  
  
      
    /** 
     * 返回输入地址的经纬度坐标 
     * key lng(经度),lat(纬度) 
     */  
    public static Map<String,BigDecimal> getGeocoderLatitude(String addr){  
    	String address = "";
        String lat = "";
        String lng = "";
        try {  
            address = java.net.URLEncoder.encode(addr,"UTF-8");  
        } catch (UnsupportedEncodingException e1) {  
            e1.printStackTrace();  
        } 
        String url = String.format("http://api.map.baidu.com/geocoder/v2/?"
        +"ak="+KEY+"&output=json&address=%s",address);
        URL myURL = null;
        URLConnection httpsConn = null;  
        //进行转码
        try {
            myURL = new URL(url);
        } catch (MalformedURLException e) {

        }
        try {
            httpsConn = (URLConnection) myURL.openConnection();
            if (httpsConn != null) {
                InputStreamReader insr = new InputStreamReader(
                        httpsConn.getInputStream(), "UTF-8");
                BufferedReader br = new BufferedReader(insr);
                String data = null;
                if ((data = br.readLine()) != null) {
                    lat = data.substring(data.indexOf("\"lat\":") 
                    + ("\"lat\":").length(), data.indexOf("},\"precise\""));
                    lng = data.substring(data.indexOf("\"lng\":") 
                    + ("\"lng\":").length(), data.indexOf(",\"lat\""));
                }
                insr.close();
            }
        } catch (IOException e) {
        	e.printStackTrace();
        }
        Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
        map.put("lat", new BigDecimal(lat));
        map.put("lng", new BigDecimal(lng));
        return map;
    }  
    
    /** 
     * 返回输入地址的经纬度坐标 
     * key lng(经度),lat(纬度) 
     */  
    public static JSONObject getLngAndLatToAddress(Double lat,Double lng){
    	URL myURL = null;
        URLConnection httpsConn = null;  
        String data = null;
        String url = "http://api.map.baidu.com/geocoder/v2/?location="+lat+","+lng+"&output=json&pois=0&ak="+KEY;
        //进行转码
        try {
            myURL = new URL(url);
        } catch (MalformedURLException e) {
        	e.printStackTrace();
        }
        try {
            httpsConn = (URLConnection) myURL.openConnection();
            if (httpsConn != null) {
                InputStreamReader insr = new InputStreamReader(
                        httpsConn.getInputStream(), "UTF-8");
                BufferedReader br = new BufferedReader(insr);
                data = br.readLine();
                insr.close();
            }
        } catch (IOException e) {
        	e.printStackTrace();
        }
        return JSONObject.fromObject(data);
    }
    
    public static void main(String[] args) {
    	Object o1 = getLngAndLatToAddress(25.031310976713474,102.8288192470476);
    	Map<String,BigDecimal> o2 = getGeocoderLatitude("云南省昆明市官渡区吴井路221号3幢3号");
    	System.out.println(o1.toString());
    	System.out.println(o2);
	}
      
} 