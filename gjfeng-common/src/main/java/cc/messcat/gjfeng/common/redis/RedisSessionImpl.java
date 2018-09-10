package cc.messcat.gjfeng.common.redis;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import cc.messcat.gjfeng.common.util.SerializeUtil;

@SuppressWarnings({ "unchecked", "rawtypes" })

public class RedisSessionImpl implements RedisSession {

	@Autowired
	@Qualifier("redisTemplate")
	protected RedisTemplate redisTemplate;

	@Override
	public void put(final String key, final Object value) {
		redisTemplate.execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				connection.set(redisTemplate.getStringSerializer().serialize(key), SerializeUtil.serialize(value));
				return null;
			}
		});
	}

	@Override
	public void put(final String key, final Object value, final int seconds) {
		redisTemplate.execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				connection.set(redisTemplate.getStringSerializer().serialize(key), SerializeUtil.serialize(value));
				connection.expire(key.getBytes(), seconds);
				return null;
			}
		});
	}

	@Override
	public Object get(final String key) {
		return redisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] keyValue = redisTemplate.getStringSerializer().serialize(key);
				if (connection.exists(keyValue)) {
					byte[] value = connection.get(keyValue);
					return SerializeUtil.unserialize(value);
				}
				return null;
			}
		});
	}

	@Override
	public void add(String key, Object value) {
		redisTemplate.opsForSet().add(key, value);
	}

	@Override
	public void putHash(String key, Map<String, Object> value) {
		redisTemplate.opsForHash().putAll(key, value);
	}

	@Override
	public void putHash(String key, Map<String, Object> value, int seconds) {
		redisTemplate.opsForHash().putAll(key, value);
		//redisTemplate.expire(key, seconds);
	}

	@Override
	public Object getHash(String key) {
		return redisTemplate.opsForHash().entries(key);
	}

	@Override
	public Object getHashVal(String key, String mapKey) {
		return redisTemplate.opsForHash().hasKey(key, mapKey);
	}

	@Override
	public Object getHashVals(String key) {
		return redisTemplate.opsForHash().values(key);
	}

	@Override
	public Object getHashKeys(String key) {
		return redisTemplate.opsForHash().keys(key);
	}

	@Override
	public long leftPush(String key, Object value) {
		return redisTemplate.opsForList().leftPush(key, value);
	}

	@Override
	public Object leftPop(String key) {
		return redisTemplate.opsForList().leftPop(key);
	}

	@Override
	public long rightPush(String key, Object value) {
		return redisTemplate.opsForList().rightPush(key, value);
	}

	@Override
	public Object rightPop(String key) {
		return redisTemplate.opsForList().rightPop(key);
	}

	@Override
	public Long listLength(String key) {
		return redisTemplate.opsForList().size(key);  
	}

	@Override
	public Long hashLength(String key) {
		return redisTemplate.opsForHash().size(key);
	}

	@Override
	public List<Object> rRange(String key, int start, int end) {
		return redisTemplate.opsForList().range(key, 0, end);
	}

	@Override
	public List<Object> union(String... keys) {
		//return redisTemplate.opsForSet().union(key, otherKeys);
		return null;
	}

	@Override
	public Object index(String key, int index) {
		return redisTemplate.opsForList().index(key, index);
	}

	@Override
	public void updateSet(String key, int index, Object value) {
		redisTemplate.opsForList().set(key, index, value);
	}

	@Override
	public void updateHash(String key, String mapKey, Object mapValue) {
		
	}

	@Override
	public void updateHash(String key, String mapKey, Object mapValue, int seconds) {
		// TODO Auto-generated method stub
	}

	@Override
	public void updateHashNx(String key, String mapKey, Object mapValue) {
		
	}

	@Override
	public void updateHashInc(String key, String mapKey, Float mapLen) {
		redisTemplate.opsForHash().increment(key, mapKey, mapLen);
	}

	@Override
	public void trim(String key, int start, int end) {
		redisTemplate.opsForList().trim(key, start, end);
	}

	@Override
	public void expire(String key, int seconds) {
		//redisTemplate.expire(key, timeout, unit);
	}

	@Override
	public void exists(String key) {
		// TODO Auto-generated method stub
	}

	@Override
	public void hashExists(String key) {
		
	}

	@Override
	public void remove(String key, int index, Object value) {
		redisTemplate.opsForList().remove(key, index, value);
	}

	@Override
	public void delete(String key) {
		redisTemplate.delete(key);
	}

	@Override
	public void hashDelete(String key, String... mapKeys) {
		redisTemplate.opsForHash().delete(key, mapKeys);
	}
}
