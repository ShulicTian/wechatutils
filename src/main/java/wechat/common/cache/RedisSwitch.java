package wechat.common.cache;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import redis.clients.jedis.JedisPool;
import wechat.common.utils.JedisUtil;

/**
 * Redis开关
 *
 * @author tianslc
 */
public abstract class RedisSwitch {

    protected static JedisPool jedisPool;
    protected static boolean openRedisCache = false;
    protected static Logger logger = LogManager.getLogger(RedisSwitch.class);

    protected static void initJedisPool(RedisConfig redisConfig) {
        try {
            jedisPool = JedisUtil.getJedisPool(redisConfig);
            openRedisCache = true;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("【QiYeWeChat】{} {}", "Redis开启异常", e);
        }
    }
}
