package wechat.common.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Redis工具类
 *
 * @author tianslc
 */
public class RedisUtil {

    public static void putString(String key, String value, JedisPool jedisPool) {
        Jedis jedis = jedisPool.getResource();
        jedis.append(key, value);
    }

    public static void putStringWithExpire(String key, String value, JedisPool jedisPool, int expire) {
        Jedis jedis = jedisPool.getResource();
        jedis.append(key, value);
        jedis.expire(key, expire);
    }

    public static void putHashWithExpire(String key, String field, String value, JedisPool jedisPool, int expire) {
        Jedis jedis = jedisPool.getResource();
        jedis.hset(key, field, value);
        jedis.expire(key, expire);
    }

    public static String getString(String key, JedisPool jedisPool) {
        Jedis jedis = jedisPool.getResource();
        return jedis.get(key);
    }

}
