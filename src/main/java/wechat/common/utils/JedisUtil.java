package wechat.common.utils;

import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.JedisPool;
import wechat.common.entity.RedisConfig;

/**
 * jedis工具类
 *
 * @author tianslc
 */
public class JedisUtil {

    /**
     * 获取JedisPool
     *
     * @param redisConfig
     * @return
     */
    public static JedisPool getJedisPool(RedisConfig redisConfig) {
        JedisPool jedisPool = null;
        if (StringUtils.isEmpty(redisConfig.getAuth())) {
            jedisPool = new JedisPool(redisConfig.getParent(), redisConfig.getAddress(), redisConfig.getPort());
        } else {
            jedisPool = new JedisPool(redisConfig.getParent(), redisConfig.getAddress(), redisConfig.getPort(), 60000, redisConfig.getAuth());
        }
        return jedisPool;
    }

}
