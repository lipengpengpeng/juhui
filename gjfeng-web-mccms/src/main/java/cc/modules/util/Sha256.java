package cc.modules.util;

import org.apache.shiro.crypto.hash.Sha256Hash;

/**
 * 使用SHA256算法生成相应的散列数据
 * @author Karhs
 * @date 2016-03-16 11:18
 */
public class Sha256 {

	/**
	 * 对数据进行sha256hash加密
	 * @author Karhs
	 * @date 2016-03-16 11:18
	 * @param source
	 * @param salt
	 * @param hashIterations
	 * @return
	 */
	public static String getSha256Hash(Object source, Object salt, int hashIterations){
		return new Sha256Hash(source, salt, hashIterations).toString();
	}
	
	/**
	 * 对sha256hash加密数据校检是否一致
	 * @author Karhs
	 * @date 2016-03-16 11:18
	 * @param source
	 * @param salt
	 * @param hashIterations
	 * @return
	 */
	public static Boolean checkSha256Hash(Object source, Object salt, int hashIterations,String oldEncrypt){
		String encrypt = getSha256Hash(source, salt, hashIterations);
		if(StringUtil.isNotBlank(oldEncrypt) && StringUtil.isNotBlank(encrypt) && oldEncrypt.equals(encrypt)){
			return true;
		}
		return false;
	}
}
