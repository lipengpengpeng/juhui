package cc.messcat.gjfeng.common.util;

import java.security.MessageDigest;

import org.apache.log4j.Logger;

import net.sf.json.JSONObject;


public class JsonMd5Utils {
	private static Logger log = Logger.getLogger(JsonMd5Utils.class);
	
	
	//验证报文MD5
	public static JSONObject isValid(JSONObject reqJson,String columns){
		if(log.isDebugEnabled()){
			log.debug("请求验证的json数据为："+reqJson);
		}
		JSONObject retJson = new JSONObject();
		String md5value = reqJson.getString("md5value");
		if(md5value.equals("") || md5value==null){
			if(log.isDebugEnabled()){
				log.debug("请求验证的json数据中MD5value为空");
			}
			retJson.put("retMsg", "请求报文数据中MD5value为空或为上送");
			retJson.put("retCode", "999999");
			return retJson;
		}
		
		String[] keys = columns.split(",");
		StringBuffer buffer = new StringBuffer();
		for(String key : keys){
			String value = reqJson.getString(key);
			if(value.equals("") || value == null){
				if(log.isDebugEnabled()){
					log.debug("所请求的："+key+"未上送，请检查");
				}
				retJson.put("retMsg", "请求报文数据中MD5value为空或为上送");
				retJson.put("retCode", "777777");
				return retJson;
			}
			buffer.append(value);
		}
		String validValue = MD5(buffer.toString());
		if(!validValue.equals(reqJson.getString("md5value"))){
			if(log.isDebugEnabled()){
				log.debug("上送报文MD5校验不通过，请检查上送报文是否符合接口文档要求");
			}
			retJson.put("retMsg", "上送报文MD5校验不通过，请检查上送报文是否符合接口文档要求");
			retJson.put("retMsg", "999999");
			return retJson;
		}
		retJson.put("retCode", "000000");
		retJson.put("retMsg", "对报文MD5校验通过");
		if(log.isDebugEnabled()){
			log.debug("报文MD5校验通过");
		}
		return retJson;
	}
	
	
	//对报文进行MD5
	public static String json2Md5(JSONObject reqJson,String columns){
		if(log.isDebugEnabled()){
			log.debug("要求进行MD5的报文为:"+reqJson);
		}
		String[] keys = columns.split(",");
		StringBuffer buffer = new StringBuffer();
		for(String key : keys){
			buffer.append(reqJson.getString(key));
		}
		return MD5(buffer.toString());
	}
	public static String MD5(String s) {
		log.debug("签名数据：["+s+"]");
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };

		try {
			byte[] btInput = s.getBytes("utf-8");
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();

			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static void main(String[] args) {
		//{"merchOrderId":"2016033020160331000006","retCode":"000000","md5value":"E941CA62416D35A2FD0EBB77757CD012"}
		String context = "20160330201603310000060000001234567890";
		String md5value = MD5(context);
		System.out.println(md5value);
	}
}
