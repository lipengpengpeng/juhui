package cc.messcat.gjfeng.common.redis;

import java.util.List;
import java.util.Map;

public interface RedisSession {

	/**
	 * @描述 添加数据
	 * @author Karhs
	 * @date 2016年11月8日
	 * @param key
	 * @param value
	 */
	public void put(String key, Object value);
	
	/**
	 * @描述 添加数据并设置过期时间
	 * @author Karhs
	 * @date 2016年11月8日
	 * @param key
	 * @param value
	 * @param seconds
	 */
	public void put(String key, Object value, int seconds);
	
	/**
	 * @描述 获取数值
	 * @author Karhs
	 * @date 2016年11月8日
	 * @param key
	 * @return
	 */
	public Object get(String key);
	
	/**
	 * @描述 一个 value元素加入到集合 key当中，已经存在于集合的 value元素将被忽略。
	 * @author Karhs
	 * @date 2016年11月8日
	 * @param key
	 * @param value
	 */
	public void add(String key, Object value);
	
	/**
	 * @描述 put hash值
	 * @author Karhs
	 * @date 2016年11月8日
	 * @param key
	 * @param value
	 */
	public void putHash(String key, Map<String, Object> value);
	
	/**
	 * @描述 put hash值并设置过期时间
	 * @author Karhs
	 * @date 2016年11月8日
	 * @param key
	 * @param value
	 * @param seconds
	 */
	public void putHash(String key, Map<String, Object> value, int seconds);
	
	/**
	 * @描述 获取hash的值
	 * @author Karhs
	 * @date 2016年11月8日
	 * @param key
	 * @return
	 */
	public Object getHash(String key);
	
	/**
	 * @描述 返回哈希表 key 中给定域 mapKey 的值
	 * @author Karhs
	 * @date 2016年11月8日
	 * @param key
	 * @return
	 */
	public Object getHashVal(String key,String mapKey);
	
	/**
	 * @描述 返回哈希表 key 中所有域的值
	 * @author Karhs
	 * @date 2016年11月8日
	 * @param key
	 * @return
	 */
	public Object getHashVals(String key);
	
	/**
	 * @描述 返回哈希表 key 中的所有域
	 * @author Karhs
	 * @date 2016年11月8日
	 * @param key
	 * @return
	 */
	public Object getHashKeys(String key);
	
	/**
	 * @描述 将value 加入key数组的开头,如果该元素是第一个元素，那么会自动创建key数组,返回列表的长度  
	 * @author Karhs
	 * @date 2016年11月8日
	 * @param key
	 * @param value
	 */
	public long leftPush(String key, Object value);
	
	/**
	 * @描述 移除第一个元素  并返回列表 key的头元素
	 * @author Karhs
	 * @date 2016年11月8日
	 * @param key
	 */
	public Object leftPop(String key);
	
	/**
	 * @描述 将value加入到key的末尾，返回列表的长度  
	 * @author Karhs
	 * @date 2016年11月8日
	 * @param key
	 * @param value
	 */
	public long rightPush(String key, Object value);
	
	/**
	 * @描述 移除最后一个元素   并返回列表 key的末元素
	 * @author Karhs
	 * @date 2016年11月8日
	 * @param key
	 */
	public Object rightPop(String key);
	
	/**
	 * @描述 list的长度
	 * @author Karhs
	 * @date 2016年11月8日
	 * @param key
	 * @return
	 */
	public Long listLength(String key);
	
	/**
	 * @描述 返回哈希表 key 中域的数量
	 * @author Karhs
	 * @date 2016年11月8日
	 * @param key
	 * @return
	 */
	public Long hashLength(String key);
	
	/**
	 * @描述 获取key数组的元素，start表示开头，end表示结尾，end为-1表示所有  
	 * @author Karhs
	 * @date 2016年11月8日
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public List<Object> rRange(String key, int start, int end);
	
	/**
	 * @描述 多个集合合并
	 * @author Karhs
	 * @date 2016年11月8日
	 * @param keys
	 * @return
	 */
	public List<Object> union(String... keys);
	
	/**
	 * @描述 检索key下index下标的值
	 * @author Karhs
	 * @date 2016年11月8日
	 * @param key
	 * @param index
	 * @return
	 */
	public Object index(String key, int index);
	
	/**
	 * @描述 更新set索引index的值
	 * @author Karhs
	 * @date 2016年11月8日
	 * @param key
	 * @param index
	 * @param value
	 */
	public void updateSet(String key, int index, Object value);
	
	/**
	 * @描述 将哈希表 key 中的域 mapKey 的值设为 mapValue 。 
            	如果 key 不存在，一个新的哈希表被创建并进行HSET 操作。 
            	如果域 mapKey 已经存在于哈希表中，旧值将被覆盖。 
	 * @author Karhs
	 * @date 2016年11月8日
	 * @param key
	 * @param mapKey
	 * @param mapValue
	 */
	public void updateHash(String key, String mapKey, Object mapValue);
	
	/**
	 * @描述 更新hash 的key的域的值，并且修改过期时间
	 * @author Karhs
	 * @date 2016年11月8日
	 * @param key
	 * @param mapKey
	 * @param mapValue
	 * @param seconds
	 */
	public void updateHash(String key, String mapKey, Object mapValue, int seconds);
	
	/**
	 * @描述 将哈希表 key 中的域 mapKey 的值设置为 mapValue ，当且仅当域 mapKey 不存在。 
            	若域 mapKey 已经存在，该操作无效。 
            	如果 key 不存在，一个新哈希表被创建并执行HSETNX 命令
	 * @author Karhs
	 * @date 2016年11月8日
	 * @param key
	 * @param mapKey
	 * @param mapValue
	 */
	public void updateHashNx(String key, String mapKey, Object mapValue);
	
	/**
	 * @描述 为哈希表 key 中的域 mapKey 的值加上增量 increment 。 
            增量也可以为负数，相当于对给定域进行减法操作。 
            如果 key 不存在，一个新的哈希表被创建并执行HINCRBY 命令。 
            如果域 mapKey 不存在，那么在执行命令前，域的值被初始化为 0 。 
            对一个储存字符串值的域 mapKey 执行HINCRBY 命令将造成一个错误。 
            本操作的值被限制在 64 位 (bit) 有符号数字表示之内
	 * @author Karhs
	 * @date 2016年11月8日
	 * @param key
	 * @param mapKey
	 * @param mapLen
	 */
	public void updateHashInc(String key, String mapKey, Float mapLen);
	
	/**
	 * @描述 裁剪
	 * @author Karhs
	 * @date 2016年11月8日
	 * @param key
	 * @param start
	 * @param end
	 */
	public void trim(String key, int start, int end);
	
	/**
	 * @描述 续期
	 * @author Karhs
	 * @date 2016年11月8日
	 * @param key
	 * @param seconds
	 */
	public void expire(String key, int seconds);
	
	/**
	 * @描述 判断是否存在
	 * @author Karhs
	 * @date 2016年11月8日
	 * @param key
	 */
	public void exists(String key);
	
	/**
	 * @描述 判断是否存在
	 * @author Karhs
	 * @date 2016年11月8日
	 * @param key
	 */
	public void hashExists(String key);
	
	/**
	 * @描述 移除指定的元素,第二个参数表示要移除的个数，因为list中是允许出现重复元素的  
	 * @author Karhs
	 * @date 2016年11月8日
	 * @param key
	 * @param index
	 * @param value
	 */
	public void remove(String key, int index, Object value);
	
	/**
	 * @描述 删除
	 * @author Karhs
	 * @date 2016年11月8日
	 * @param key
	 */
	public void delete(String key);
	
	/**
	 * @描述 删除哈希表 key 中的一个或多个指定域，不存在的域将被忽略
	 * @author Karhs
	 * @date 2016年11月8日
	 * @param key
	 */
	public void hashDelete(String key,String... mapKeys);
	
}
