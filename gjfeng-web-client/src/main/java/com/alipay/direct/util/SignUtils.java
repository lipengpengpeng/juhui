package com.alipay.direct.util;



import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import com.alipay.direct.sign.Base64;

public class SignUtils {

	private static final String ALGORITHM = "RSA";

	private static final String SIGN_ALGORITHMS = "SHA1WithRSA";

	private static final String DEFAULT_CHARSET = "UTF-8";

	public static String sign(String content, String privateKey) {
		try {
			PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(
					Base64.decode(privateKey));
			KeyFactory keyf = KeyFactory.getInstance(ALGORITHM);
			PrivateKey priKey = keyf.generatePrivate(priPKCS8);

			java.security.Signature signature = java.security.Signature
					.getInstance(SIGN_ALGORITHMS);

			signature.initSign(priKey);
			signature.update(content.getBytes(DEFAULT_CHARSET));

			byte[] signed = signature.sign();

			return Base64.encode(signed);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	/**
	* RSA验签名检查
	* @param content 待签名数据
	* @param sign 签名值
	* @param alipay_public_key 支付宝公钥
	* @param input_charset 编码格式
	* @return 布尔值
	*/
	public static boolean verify(String content, String sign, String alipay_public_key, String input_charset)
	{
		
		try 
		{
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			
	        byte[] encodedKey = Base64.decode(alipay_public_key);
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
	
	public static void main(String[] args) {
		boolean isTure=verify("app_id=2018032202424514&biz_content={\"timeout_express\":\"30m\",\"product_code\":\"QUICK_MSECURITY_PAY\",\"total_amount\":\"1.0\",\"subject\":\"赠送代金券\",\"body\":\"联盟商家赠送代金券\",\"out_trade_no\":\"20180416111945079113\"}&charset=utf-8&method=alipay.trade.app.pay&notify_url=http://yg.gjfeng.com/gjfeng-web-client/wx/notify/payVouchersNotifyAply&sign_type=RSA&timestamp=2018-04-16 11:19:45&version=1.0",
				"GckCQnS2wHJ2zI6UAxSfFeOkoAh232umqkNAypQnfoaZ6KVF4HVRZiUXu4psNbFK1ApWeOIwH0pB2%2FvkK8ATY7wg6obLzM492r4gbGg6c71AGJEgJ2zIIS7iVUEafaVxsMgKxz2WuFjlS1vikkrpGCiLi3EpZy%2Fc5VhhiFQt6u7BsGGTgIvWxjGU9TD42k%2BZPJknXq8r5ssQKKDJb0Rfz2p9Yl3cvZiNHEIOWsrTs3d41zlzC7mTdin7aWfh%2FWKS3dEdwTk%2Bbyq2Rq%2FjR%2FSULyVm8J4RG6tsGRAK0RWZgKA3qgeroYq0iBWR%2B87cz9OROgZLnZkxUegRie8jmOB6kA%3D%3D",
				"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmlRjt/21JP2/i++sNQh8BLa170FQ9gn/dKuhXwkTNLCxI7GLn9Z/MdrKpWdzNC6TbW1yQyf7bipKCqRpva/UrlRT7cHcUZWb0mHWrpH2j30wwScT0g1DxtZwwemQ83SmiYkgwvIcCIIWS4ClkiaJz/2GrpJNzcdB0vVJnY8FnjQ4lHlX9bg/8b46+eqVOokYv9UNkXymdLRBmT+kzdMwpEa9uphog0FU9w/HARP6f0k+In+8GoL22uatY1EqPMYFIfFDPZFU2k6XaGIAPXWugfV0odoxgSbjDovOwct2nAJc4zNBy0VNbbpst0f9M9oZJlbPoJaXsap2zT79TUCiJwIDAQAB","utf-8");
		
		System.out.print(isTure);
	}
}
