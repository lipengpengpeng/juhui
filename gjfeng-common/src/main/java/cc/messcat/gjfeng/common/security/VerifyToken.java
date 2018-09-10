package cc.messcat.gjfeng.common.security;

import cc.messcat.gjfeng.common.constant.CommonStatus;
import cc.messcat.gjfeng.common.util.EncryptionUtil;

public class VerifyToken {

	/**
	 * Token定义组合：signKey+接口名+相关参数值，再MD5加密
	 * 
	 * @param token
	 * @return
	 */
	public static boolean verify(String token, String[] otherTokenDetail) {

		StringBuilder tokenWithNoMd5 = new StringBuilder();

		tokenWithNoMd5.append(CommonStatus.SIGN_KEY);
		
		if (otherTokenDetail != null) {
			for (int i = 0; i < otherTokenDetail.length; i++) {
				tokenWithNoMd5.append(otherTokenDetail[i]);
			}
		}
		
		String newToken = EncryptionUtil.getMD5Code(tokenWithNoMd5.toString());

		if (newToken.equals(token)) {
			return true;
		}
		return false;
	}
}
