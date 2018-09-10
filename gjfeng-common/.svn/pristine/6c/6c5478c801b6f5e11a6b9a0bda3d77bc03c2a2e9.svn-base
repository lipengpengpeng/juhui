/**
 * Copyright (c) 2015 - 2016 QianLiKa, Inc. All rights reserved.
 * This software is the confidential and proprietary information of 
 * QianliKa, Inc. You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the 
 * license agreement you entered into with QianLiKa.
 */
package cc.messcat.gjfeng.common.jd;

import java.util.UUID;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.tomcat.util.codec.binary.Base64;

import cc.messcat.gjfeng.common.util.StringUtil;



/**
 * 
 * Title:签名工具
 * <p>
 * Description:提供签名计算的工具
 * </p>
 *
 * @author yangxilin 13983865662@139.com on 2016年9月11日
 *
 */
public final class Signatures {
	
	/**
	 * 计算签名
	 * 
	 * @param operation
	 * @param requestUrl
	 * @param date
	 * @param secretKey
	 * @return
	 */
	public static String sign(String operation, String requestUrl, String date, String secretKey) {
		String signature = null;
		StringBuffer buf = new StringBuffer();
		if (StringUtil.isNotBlank(operation)) {
			buf.append(operation).append("\n");
		} else {
			buf.append("\n");
		}
		if (StringUtil.isNotBlank(requestUrl)) {
			buf.append(requestUrl).append("\n");
		} else {
			buf.append("\n");
		}
		if (StringUtil.isNotBlank(secretKey)) {
			buf.append(secretKey).append("\n");
		} else {
			buf.append("\n");
		}
		if (StringUtil.isNotBlank(date)) {
			buf.append(date);
		} else {
			buf.append("\n");
		}
		String data = buf.toString();
		try {
			Mac mac = Mac.getInstance("HmacSHA1");
			byte[] keyBytes = secretKey.getBytes("UTF8");
			SecretKeySpec signingKey = new SecretKeySpec(keyBytes, "HmacSHA1");
			mac.init(signingKey);
			byte[] signBytes = mac.doFinal(data.getBytes("UTF8"));
			signature = Base64.encodeBase64String(signBytes).toString();
			return signature;
		} catch (Exception e) {
			throw new RuntimeException("MAC计算错误 - " + e.getMessage());
		}
	}

}
