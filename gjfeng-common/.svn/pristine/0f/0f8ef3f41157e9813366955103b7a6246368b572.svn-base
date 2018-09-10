package cc.messcat.gjfeng.common.fastpay.util;

import java.io.IOException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;


public class DesCrypt {
	
//	private static Cipher cipherInstance = null;

//	private static Cipher getCipherInstance(){
//		try{
//			if(cipherInstance==null)
//				cipherInstance = Cipher.getInstance("DESede/ECB/PKCS5Padding");
//		}catch(Exception e)
//		{
//			e.printStackTrace();
//		}   
//		return cipherInstance;
//	}
	/**
	 * BASE64编码
	 *
	 * @param inputByte 待编码数捿
	 * @return 解码后的数据
	 * @throws IOException
	 */
	public static byte[] base64Encode(byte[] inputByte) throws IOException {
		return Base64.encodeBase64(inputByte);
	}
	
	public static final byte[] Key = "e143cc8236aed7f7786da932".getBytes();
    private static final String Algorithm = "DESede";  //定义 加密算法
    //加密字符丿
    public static byte[] encrypt(byte[] data,String key) throws Exception{
    	return encryptMode(key.getBytes(),data);
    }
    public static byte[] encrypt(String data,String key) throws Exception{ 
    	return encrypt(data.getBytes("GBK"),key);
    }
    
    /**
     * type
     * 1 卡号
     * 2 手机叿
     * 3 姓名
     * 4 身份诿
     * 5 有效朿
     * 6 CVN2
     * data 待脱敏数捿
     * @throws Exception
     */
    public static String encrypt(int type,String data) throws Exception{ 
    	String dataEncrypt = "";
    	switch (type) {
    		case 1 : 
    			if (data.length() < 18) {
    				dataEncrypt = data;
    			} else {
    				dataEncrypt = data;
    			}
    			break;
    		case 2 : 
    			if (data.length() < 11) {
    				dataEncrypt = data;
    			} else {
    				dataEncrypt = data;
    			}
    			break;
    		case 3 : 
    			if (data.length() < 2) {
    				dataEncrypt = data;
    			} else {
    				dataEncrypt = data;
    			}
    			break;
    		case 4 : 
    			if (data.length() < 18) {
    				dataEncrypt = data;
    			} else {
    				dataEncrypt = data;
    			}
    			break;
    		case 5 : 
    			if (data.length() < 18) {
    				dataEncrypt = data;
    			} else {
    				dataEncrypt = data;
    			}
    			break;
    		case 6 : 
    			if (data.length() < 18) {
    				dataEncrypt = data;
    			} else {
    				dataEncrypt = data;
    			}
    			break;
    		default : 
    			dataEncrypt = data;
    			break;
    	}
    	return dataEncrypt;
    }
    /*public static byte[] encrypt(String data) throws Exception{
    	return encryptMode(Key,data.getBytes("GBK"));
    }
    public static byte[] encrypt(byte[] data) throws Exception{
    	return encryptMode(Key,data);
    }*/
    //解密字符丿
    public static byte[] decrypt(byte[] data,String key){
    	return decryptMode(key.getBytes(),data);
    }
    /*public static byte[] decrypt(String data) throws Exception{
    	return decryptMode(Key,data.getBytes("GBK"));
    }
    public static byte[] decrypt(byte[] data) throws Exception{
    	return decryptMode(Key,data);
    }*/
    // 加密字符丿
    public static byte[] encryptMode(byte[] keybyte, byte[] src) throws Exception{
        try { // 生成密钥
            SecretKey deskey = new SecretKeySpec(keybyte, Algorithm); // 加密
            Cipher cipherInstance = Cipher.getInstance("DESede/ECB/PKCS5Padding");
//            cipherInstance = getCipherInstance();
            cipherInstance.init(Cipher.ENCRYPT_MODE, deskey);
            return cipherInstance.doFinal(src);
        }  catch (java.lang.Exception e3) {
            throw e3;
        }
    }
 
    // 解密字符丿
    public static byte[] decryptMode(byte[] keybyte, byte[] src) {
        try { // 生成密钥
            SecretKey deskey = new SecretKeySpec(keybyte, Algorithm); // 解密
            Cipher cipherInstance = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            //Cipher cipherInstance = Cipher.getInstance("DES/ECB/NoPadding");
//            cipherInstance = getCipherInstance();
            cipherInstance.init(Cipher.DECRYPT_MODE, deskey);
            return cipherInstance.doFinal(src);
        }  catch (java.lang.Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }
}
