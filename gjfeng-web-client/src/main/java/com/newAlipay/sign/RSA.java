
package com.newAlipay.sign;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

public class RSA{
	
	public static final String  SIGN_ALGORITHMS = "SHA256WithRSA";
	
	/**
	* RSA2签名
	* @param content 待签名数据
	* @param privateKey 商户私钥
	* @param input_charset 编码格式
	* @return 签名值
	*/
	public static String sign(String content, String privateKey, String input_charset)
	{
        try 
        {
        	PKCS8EncodedKeySpec priPKCS8 	= new PKCS8EncodedKeySpec( Base64.decode(privateKey) ); 
        	KeyFactory keyf 				= KeyFactory.getInstance("RSA");
        	PrivateKey priKey 				= keyf.generatePrivate(priPKCS8);

            java.security.Signature signature = java.security.Signature
                .getInstance(SIGN_ALGORITHMS);

            signature.initSign(priKey);
            signature.update( content.getBytes(input_charset) );

            byte[] signed = signature.sign();
            String signStr=Base64.encode(signed);
            String encodedSign = URLEncoder.encode(signStr, "UTF-8");
            
            return encodedSign;
        }
        catch (Exception e) 
        {
        	e.printStackTrace();
        }
        
        return null;
    }
	
	/**
	* RSA2验签名检查
	* @param content 待签名数据
	* @param sign 签名值
	* @param ali_public_key 支付宝公钥
	* @param input_charset 编码格式
	* @return 布尔值
	*/
	public static boolean verify(String content, String sign, String ali_public_key, String input_charset)
	{
		try 
		{
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
	        byte[] encodedKey = Base64.decode(ali_public_key);
	        PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));

		
			java.security.Signature signature = java.security.Signature
			.getInstance(SIGN_ALGORITHMS);
		
			signature.initVerify(pubKey);
			signature.update( content.getBytes(input_charset) );
		
			boolean bverify = signature.verify( Base64.decode(sign) );
			return bverify;
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	* 解密
	* @param content 密文
	* @param private_key 商户私钥
	* @param input_charset 编码格式
	* @return 解密后的字符串
	*/
	public static String decrypt(String content, String private_key, String input_charset) throws Exception {
        PrivateKey prikey = getPrivateKey(private_key);

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, prikey);

        InputStream ins = new ByteArrayInputStream(Base64.decode(content));
        ByteArrayOutputStream writer = new ByteArrayOutputStream();
        //RSA2解密的字节大小最多是128，将需要解密的内容，按128位拆开解密
        byte[] buf = new byte[128];
        int bufl;

        while ((bufl = ins.read(buf)) != -1) {
            byte[] block = null;

            if (buf.length == bufl) {
                block = buf;
            } else {
                block = new byte[bufl];
                for (int i = 0; i < bufl; i++) {
                    block[i] = buf[i];
                }
            }

            writer.write(cipher.doFinal(block));
        }

        return new String(writer.toByteArray(), input_charset);
    }

	
	/**
	* 得到私钥
	* @param key 密钥字符串（经过base64编码）
	* @throws Exception
	*/
	public static PrivateKey getPrivateKey(String key) throws Exception {

		byte[] keyBytes;
		
		keyBytes = Base64.decode(key);
		
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
		
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		
		PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
		
		return privateKey;
	}
	
	
	public static void main(String[] args) {
		boolean isTrue=verify("app_id=2018032202424514&biz_content={\"timeout_express\":\"30m\",\"product_code\":\"QUICK_MSECURITY_PAY\",\"total_amount\":\"1.0\",\"subject\":\"赠送代金券\",\"body\":\"联盟商家赠送代金券\",\"out_trade_no\":\"20180416135225573827\"}&charset=utf-8&method=alipay.trade.app.pay&notify_url=http://yg.gjfeng.com/gjfeng-web-client/wx/notify/payVouchersNotifyAply&sign_type=RSA2&timestamp=2018-04-16 13:52:26&version=1.0",
				              "hqvGai5lpIeBCv9VRt6Z0GDnlfX1hPMhZJJgOErZaG3SYHVzS2sIx%2FekVwXauJPgm4NUd2qVKvVjMCwY%2FDQnhQHKc6e0MnuCN4WTFChlHMw46BLTJJWDPz3DwQ3PZhG2UqqlyjgZEQq4skzKsGQ1RGVCQaOeAhs7cETk46RK4mlV%2B8mSWD3CWf6NhUMPYsokBmnWQlDA9TVMkADcDHUGO6fH1OTF4z8Yj5u3ERYc4TpxSwKXCPk6WZZPD2h2%2B%2Bjt0GLqx8OSTp6KQWqIoLkPdGYNLcAzeff9jHzP1sqEGfd%2Fc%2FcZ6JwwKVVHghhlFqm9PaPDYUkayoKtl5a0n6nN8w%3D%3D",
				              "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmlRjt/21JP2/i++sNQh8BLa170FQ9gn/dKuhXwkTNLCxI7GLn9Z/MdrKpWdzNC6TbW1yQyf7bipKCqRpva/UrlRT7cHcUZWb0mHWrpH2j30wwScT0g1DxtZwwemQ83SmiYkgwvIcCIIWS4ClkiaJz/2GrpJNzcdB0vVJnY8FnjQ4lHlX9bg/8b46+eqVOokYv9UNkXymdLRBmT+kzdMwpEa9uphog0FU9w/HARP6f0k+In+8GoL22uatY1EqPMYFIfFDPZFU2k6XaGIAPXWugfV0odoxgSbjDovOwct2nAJc4zNBy0VNbbpst0f9M9oZJlbPoJaXsap2zT79TUCiJwIDAQAB",
				              "utf-8");
		System.out.println(isTrue);
	}
}
