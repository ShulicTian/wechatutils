package wechat.common.entity;

import org.apache.commons.lang3.StringUtils;
import wechat.common.cache.RedisConfig;

import java.util.Properties;

/**
 * 基础参数实体
 *
 * @author tianslc
 */
public class BaseParamsEntity {

    /**
     * 企业ID
     */
    private String corpId;

    /**
     * 应用密钥
     */
    private String secret;

    /**
     * 应用ID
     */
    private String agentId;

    /**
     * 消息密钥
     */
    private String encodingAESKey;

    /**
     * 通讯录密钥
     */
    private String addressBookSecret;

    /**
     * 是否开启全局通讯录操作
     */
    private boolean isOpenGlobalAddressBookSecret = false;

    private boolean isOpenRedisCache = false;

    private RedisConfig redisConfig;

    public BaseParamsEntity(Properties properties) {
        this.corpId = properties.getProperty("corpId");
        this.secret = properties.getProperty("secret");
        this.agentId = properties.getProperty("agentId");
        this.encodingAESKey = properties.getProperty("encodingAESKey");
        this.addressBookSecret = properties.getProperty("addressBookSecret");
        this.isOpenGlobalAddressBookSecret = Boolean.parseBoolean(properties.getProperty("isOpenGlobalAddressBookSecret"));
        this.isOpenRedisCache = Boolean.parseBoolean(properties.getProperty("isOpenRedisCache"));
        if (this.isOpenRedisCache) {
            initRedisConfig(properties);
        }
    }

    public BaseParamsEntity(String corpId, String secret, String agentId) {
        this.corpId = corpId;
        this.secret = secret;
        this.agentId = agentId;
    }

    public BaseParamsEntity(String corpId, String addressBookSecret, boolean isOpenGlobalAddressBookSecret) {
        this.corpId = corpId;
        this.addressBookSecret = addressBookSecret;
        this.isOpenGlobalAddressBookSecret = isOpenGlobalAddressBookSecret;
    }

    public BaseParamsEntity(String corpId, String secret, String agentId, String encodingAESKey) {
        this.corpId = corpId;
        this.secret = secret;
        this.agentId = agentId;
        this.encodingAESKey = encodingAESKey;
    }

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getEncodingAESKey() {
        return encodingAESKey;
    }

    public void setEncodingAESKey(String encodingAESKey) {
        this.encodingAESKey = encodingAESKey;
    }

    public String getAddressBookSecret() {
        return addressBookSecret;
    }

    public void setAddressBookSecret(String addressBookSecret) {
        this.addressBookSecret = addressBookSecret;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public boolean isOpenGlobalAddressBookSecret() {
        return isOpenGlobalAddressBookSecret;
    }

    public void setOpenGlobalAddressBookSecret(boolean openGlobalAddressBookSecret) {
        isOpenGlobalAddressBookSecret = openGlobalAddressBookSecret;
    }

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

    private void initRedisConfig(Properties properties) {
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
