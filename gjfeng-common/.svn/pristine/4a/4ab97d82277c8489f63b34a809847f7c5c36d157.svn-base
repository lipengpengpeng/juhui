package cc.messcat.gjfeng.common.redis;

import org.apache.log4j.Logger;

public class RedisLoader {

	private static final Logger logger = Logger.getLogger(RedisLoader.class);

	/**
	 * @描述 load 对象
	 * @author Karhs
	 * @date 2016年11月8日
	 * @param redisSession
	 * @param key
	 * @param callBack
	 * @return
	 */
	public static Object loadObject(RedisSession redisSession, String key, RedisDoCallBack callBack) {
		Object object = null;

		// Get from redis
		try {
			object = redisSession.get(key);
		} catch (Exception e) {
			StringBuilder builder = new StringBuilder();
			builder.append("RedisLoader.loadObject getSession ket=");
			builder.append(key);
			builder.append(" exception = ");
			builder.append(e.toString());
			logger.error(builder.toString());
			object = callBack.doGetData();
			return object;
		}

		// It's not in redis then get from database
		if (object == null) {
			object = callBack.doGetData();

			// Put the new data into redis
			try {
				redisSession.put(key, object);
			} catch (Exception e) {
				StringBuilder builder = new StringBuilder();
				builder.append("RedisLoader.loadObject putSession ket=");
				builder.append(key);
				builder.append(" exception = ");
				builder.append(e.toString());
				logger.error(builder.toString());
			}
		}

		return object;
	}

	/**
	 * @描述 put redis数据
	 * @author Karhs
	 * @date 2016年11月8日
	 * @param redisSession
	 * @param obj
	 * @param function
	 * @param key
	 * @param logger
	 */
	public static void doPutRedis(RedisSession redisSession, Object obj, String function, String key, Logger logger) {
		try {
			redisSession.put(key, obj);
		} catch (Exception e) {
			StringBuilder builder = new StringBuilder(function);
			builder.append(" redis put session key=").append(key).append(" exception = ").append(e.toString());
			logger.error(builder.toString());
		}
	}

	
	/**
	 * @描述 del redis数据
	 * @author Karhs
	 * @date 2016年11月8日
	 * @param redisSession
	 * @param function
	 * @param key
	 * @param logger
	 */
	public static void doDelRedis(RedisSession redisSession, String function, String key, Logger logger) {
		try {
			redisSession.delete(key);
		} catch (Exception e) {
			StringBuilder builder = new StringBuilder(function);
			builder.append(" redis delete session key=").append(key).append(" exception = ").append(e.toString());
			logger.error(builder.toString());
		}
	}

}
