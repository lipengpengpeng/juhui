package cc.modules.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import edu.emory.mathcs.backport.java.util.Arrays;

/**
 * 手机短信接口util
 * @author Jesse
 * 2014-07-23
 */
public class SmsUtil {

	/**
	 * 固定地址串，后面方法名需要传参数
	 */
//	private static final String  address = "http://106.ihuyi.cn/webservice/sms.php?method=";
	private static final String  address = "http://api.smsbao.com/sms";
	
	/**
	 * 用户名
	 */
//	private static final String account = "cf_kdzc"; //账号
	private static final String account = "blueava";
	
	/**
	 * 密码
	 */
//	private static final String password = "zk34172599";//密码
	private static final String password = "47bfda384241987d4b03295134ae9dfc";
	
	public static String send(String phone,String content) {
		 
        String testUsername = account; //在短信宝注册的用户名
        String testPassword = password; //在短信宝注册的密码
        String testPhone = phone;
        String testContent = content;
 
        String httpUrl = address;
 
        StringBuffer httpArg = new StringBuffer();
        httpArg.append("u=").append(testUsername).append("&");
        httpArg.append("p=").append(testPassword).append("&");
        httpArg.append("m=").append(testPhone).append("&");
        httpArg.append("c=").append(encodeUrlString(testContent, "UTF-8"));
 
        String result = request(httpUrl, httpArg.toString());
        return result;
    }
	
	//产生4位随机数字
	public static String createCode(){
		Random ran=new Random();
		int r=0;
		m1:while(true){
		    int n=ran.nextInt(10000);
		    r=n;
		    int[] bs=new int[4];
		    for(int i=0;i<bs.length;i++){
		        bs[i]=n%10;
		        n/=10;
		    }
		    Arrays.sort(bs);
		    for(int i=1;i<bs.length;i++){
		        if(bs[i-1]==bs[i]){
		            continue m1;
		        }
		    }
		    break;
		}
		
		String code = r+"";
		
		if(code.length() == 3){
			code = "0"+code;
		}
		
		return code;
	}
	
	public static String getContent(String code,String type){
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		Date date=new Date();
		String time = format.format(date);
		String res = null;
		if(type.equals("reg")){
			res = "【南粤明珠官网】您于" + time + "绑定手机号，验证码是："+code;
		}
		if(type.equals("reset")){
			res = "【南粤明珠官网】您于" + time + "修改密码，验证码是："+code;
		}
		if(type.equals("pdCash")){
			res = "【南粤明珠官网】您于" + time + "提交账户安全验证，验证码是："+code;
		}
		
		return res;
		
	}
	
	public static String request(String httpUrl, String httpArg) {
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        httpUrl = httpUrl + "?" + httpArg;
 
        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = reader.readLine();
            if (strRead != null) {
                sbf.append(strRead);
                while ((strRead = reader.readLine()) != null) {
                    sbf.append("\n");
                    sbf.append(strRead);
                }
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
 
    public static String encodeUrlString(String str, String charset) {
        String strret = null;
        if (str == null)
            return str;
        try {
            strret = java.net.URLEncoder.encode(str, charset);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return strret;
    }
	
	
	
}
