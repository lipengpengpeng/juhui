package cc.messcat.gjfeng.common.security;

import java.util.Date;

import cc.messcat.gjfeng.common.util.DateHelper;
import cc.messcat.gjfeng.common.util.EncryptionUtil;


public class VerifySessionKey {

	/**
	 * 获取加密生成的sessionKey
	 * （player+登录机器号+时间）MD5加密
	 * @param player  用户登录名
	 * @param machineNumber  登录机器号
	 * @return  sessionKey
	 */
	public static String getLoginSessionKey(String player, String machineNumber){
		String time = DateHelper.dataToString(new Date(), "yyyyMMddhhmmss");
		StringBuilder keyBuffer = new StringBuilder();
		keyBuffer.append(player).append(machineNumber).append(time);
		//MD5加密
		String sessionKey = EncryptionUtil.getMD5Code(keyBuffer.toString());
		return sessionKey;
	}
	
	public static void main(String[] args) {
		VerifySessionKey.getLoginSessionKey("admin", "abcd1234a");
	}
}
