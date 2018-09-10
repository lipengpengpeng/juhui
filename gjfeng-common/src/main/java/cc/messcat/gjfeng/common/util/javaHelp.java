package cc.messcat.gjfeng.common.util;

import java.io.UnsupportedEncodingException;
import java.math.RoundingMode;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

public class javaHelp {
	public static Pattern getEmojiRegex(){
		Pattern compile = Pattern.compile( "[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]" ,Pattern.CASE_INSENSITIVE);
		return compile;
	}
	
	public static String toString(Object obj,String def){
		if(obj==null)return def;
		 return String.valueOf(obj);
	}
	
	public static String dataToString(Date date,String pattern){
		if(null == pattern || "".equals(pattern)){
			pattern = "yyyy-MM-dd HH:mm:ss";
		}
		return new SimpleDateFormat(pattern).format(date);
	}
	/**
	 * 计算百分率
	 * @param x 分子
	 * @param total	分母
	 * @param scale	精确到几位
	 * @return
	 */
	public static String accuracy(double num, double total, int scale){  
	        DecimalFormat df = (DecimalFormat)NumberFormat.getInstance();  
	        //可以设置精确几位小数  
	        df.setMaximumFractionDigits(scale);  
	        //模式 例如四舍五入  
	        df.setRoundingMode(RoundingMode.HALF_UP);  
	        double accuracy_num = num / total * 100;  
	        return df.format(accuracy_num)+"%";  
	}
	/**
	 * 
	 * @param date
	 * @param date2
	 * @param number
	 * @return 当 date 大于  date2 而且 值等于 member时  返回true  否则false   单位天
	 */
	public static Boolean dataCompare(Date date, Date date2,Integer number) {		
		try {
			Long diff = date.getTime() - date2.getTime();
			Long days = diff / (1000 * 60 * 60 * 24);
			return days.intValue()==number?true:false;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * string 转 Integer
	 * @param cycle
	 * @param i
	 * @return Integer
	 */
	public static Integer toInteger(String cycle, Integer i) {
		if(cycle==null||"".equals(cycle))return i;
		return new Integer(cycle);
	}
	
	public static String getExtName(String fileName){
		fileName = fileName==null?"":fileName;
		Integer index = fileName.lastIndexOf(".");
		String extName = fileName.substring(index, fileName.length());
		return extName;
	}

	public static Date stringToDate(String dateString){
		Date date = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");       
			date = sdf.parse(dateString);    
		} catch (Exception e) {
		}
		return date;
	}

	public static String getFileExtName(String name) {
		String extName = name.substring(name.lastIndexOf("."), name.length());
		return extName;
	}

	public static String getUUID32() {
		return UUID.randomUUID().toString();
	}
	
	/**
	 * 对字符编码
	 * @param str
	 * @return
	 */
	public static String URLEncoder(String str){
		return URLEncoder.encode(str);
	}
	
	/**
	 * 对字符编码
	 * @param str
	 * @param encode  编码方式
	 * @return
	 */
	public static String URLEncoder(String str,String encode){
		try {
			return URLEncoder.encode(str,encode);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 对字符解码
	 * @param str
	 * @return
	 */
	public static String URLDecoder(String str){
		return URLDecoder.decode(str);
	}
	
	/**
	 * 对字符解码 
	 * @param str
	 * @param decode 解码方式
	 * @return
	 */
	public static String URLDecoder(String str,String decode){
		try {
			return URLDecoder.decode(str,decode);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static JSONObject RecordToJson(String[] args){
		 JSONObject json = new JSONObject();
		 json.put("user", javaHelp.URLEncoder(javaHelp.toString(args[0], "") , "utf-8"));
		 json.put("date", args[1]);
		 json.put("content", javaHelp.URLEncoder(javaHelp.toString(args[2], "") , "utf-8"));
		 return json;
	}
	
}
