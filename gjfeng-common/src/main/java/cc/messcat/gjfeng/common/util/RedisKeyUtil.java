package cc.messcat.gjfeng.common.util;

/**
 * 获取redis key
 * @author Karhs
 * @date 2016-01-22 10:54
 *
 */
public class RedisKeyUtil {

	public static String getRedisKey(String prefix,String key){
		StringBuffer redisKey = new StringBuffer();
		if(StringUtil.isNotBlank(prefix) && StringUtil.isNotBlank(key)){
			return redisKey.append(prefix).append("_").append(key).toString();
		} 
		return null;
	}
}
