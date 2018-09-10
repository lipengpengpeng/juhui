package cc.modules.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 
 * 提供各类加解密算法
 * 
 * jre找不到BASE64Encoder的解决办法：
 * 
 * http://liulinxia02.blog.163.com/blog/static/268687720110614346748/
 * 
 * @author Lich Li
 *
 */
public class SecurityTool {
	
	public static void main(String[] args) {
		String str = "123456";
		System.out.println(md5(str));
	}
	
	//Base64编码
	public static String base64Encoder(String content){
		BASE64Encoder b = new BASE64Encoder();
		return b.encode(content.getBytes());
	}
	
	public static String base64Encoder(byte[] bytes){
		BASE64Encoder b = new BASE64Encoder();
		return b.encode(bytes);
	}
	
	//Base64解码
	public static String base64DecoderToString(String content){
		BASE64Decoder d = new BASE64Decoder();
		try {
			return new String(d.decodeBuffer(content));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static byte[] base64DecoderTobytes(String content){
		BASE64Decoder d = new BASE64Decoder();
		try {
			return d.decodeBuffer(content);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	//md5加密
	public static String md5(String str){
			
		String md5="";
		try{
			MessageDigest md = MessageDigest.getInstance("MD5");
			//如果每次都创建一个新的MessageDigest对象，则没有必要使用这个方法
			md.reset();
			md.update(str.getBytes("UTF-8"));
			md5=byteToHex(md.digest());	
		}catch(NoSuchAlgorithmException e){
			e.printStackTrace();
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
		return md5;
	}
	
	//将byte转换为16进制，类似sha1和md5这种不可逆的加密算法加密后的密文以16进制串显示
	private static String byteToHex(final byte[] hash){
		Formatter f=new Formatter();
		for(byte b:hash){
			f.format("%02x", b);
		}
		String result=f.toString();
		f.close();
		return result;
	}
}
