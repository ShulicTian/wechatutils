package wechat.common.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Redis工具类
 *
 * @author tianslc
 */
public class RedisUtils {

    public static void putString(String key, String value, JedisPool jedisPool) {
        Jedis jedis = jedisPool.getResource();
        jedis.append(key, value);
    }

    public static String getString(String key, JedisPool jedisPool) {
        Jedis jedis = jedisPool.getResource();
        return jedis.get(key);
    }

}
