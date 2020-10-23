package wechat.common.entity;

import org.apache.commons.lang3.StringUtils;
import wechat.common.cache.RedisConfig;

import java.util.Properties;

/**
 * 基础参数表
 *
 * @author tianslc
 */
public class BaseParams {


    protected boolean isOpenRedisCache = false;

    private RedisConfig redisConfig;

    public boolean isOpenRedisCache() {
        return isOpenRedisCache;
    }

    public void setOpenRedisCache(boolean openRedisCache) {
        isOpenRedisCache = openRedisCache;
    }

    public RedisConfig getRedisConfig() {
        return redisConfig;
    }

    public void setRedisConfig(RedisConfig redisConfig) {
        this.redisConfig = redisConfig;
    }

    /**
     * 开启redis缓存
     *
     * @param redisConfig
     */
    public void openRedisCache(RedisConfig redisConfig) {
        this.isOpenRedisCache = true;
        this.redisConfig = redisConfig;
    }

    protected void initRedisConfig(Properties properties) {
        redisConfig = new RedisConfig();
        if (StringUtils.isNotEmpty(properties.getProperty("redis.address"))) {
            redisConfig.setAddress(properties.getProperty("redis.address"));
        }
        if (StringUtils.isNotEmpty(properties.getProperty("redis.port"))) {
            redisConfig.setPort(Integer.parseInt(properties.getProperty("redis.port")));
        }
        if (StringUtils.isNotEmpty(properties.getProperty("redis.auth"))) {
            redisConfig.setAuth(properties.getProperty("redis.auth"));
        }
        if (StringUtils.isNotEmpty(properties.getProperty("redis.maxTotal"))) {
            redisConfig.setMaxTotal(Integer.parseInt(properties.getProperty("redis.maxTotal")));
        }
        if (StringUtils.isNotEmpty(properties.getProperty("redis.maxIdle"))) {
            redisConfig.setMaxIdle(Integer.parseInt(properties.getProperty("redis.maxIdle")));
        }
        if (StringUtils.isNotEmpty(properties.getProperty("redis.minIdle"))) {
            redisConfig.setMinIdle(Integer.parseInt(properties.getProperty("redis.minIdle")));
        }
        if (StringUtils.isNotEmpty(properties.getProperty("redis.maxWaitMillis"))) {
            redisConfig.setMaxWaitMillis(Long.parseLong(properties.getProperty("redis.maxWaitMillis")));
        }
        if (StringUtils.isNotEmpty(properties.getProperty("redis.testOnBorrow"))) {
            redisConfig.setTestOnBorrow(Boolean.parseBoolean(properties.getProperty("redis.testOnBorrow")));
        }
        if (StringUtils.isNotEmpty(properties.getProperty("redis.testOnReturn"))) {
            redisConfig.setTestOnReturn(Boolean.parseBoolean(properties.getProperty("redis.testOnReturn")));
        }
        if (StringUtils.isNotEmpty(properties.getProperty("redis.timeBetweenEvictionRunsMillis"))) {
            redisConfig.setTimeBetweenEvictionRunsMillis(Long.parseLong(properties.getProperty("redis.timeBetweenEvictionRunsMillis")));
        }
        if (StringUtils.isNotEmpty(properties.getProperty("redis.testWhileIdle"))) {
            redisConfig.setTestWhileIdle(Boolean.parseBoolean(properties.getProperty("redis.testWhileIdle")));
        }
        if (StringUtils.isNotEmpty(properties.getProperty("redis.numTestsPerEvictionRun"))) {
            redisConfig.setNumTestsPerEvictionRun(Integer.parseInt(properties.getProperty("redis.numTestsPerEvictionRun")));
        }
    }
}
