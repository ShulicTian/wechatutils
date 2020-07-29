package wechat.common.constant;

/**
 * 缓存管理器
 *
 * @author tianslc
 */

public enum CacheManagerEnum {
    /**
     * ehcache管理器
     */
    EHCACHE_MANAGER("ehcacheManager"),
    /**
     * Redis管理器
     */
    REDIS_MANAGER("redisManager");

    private String key;

    CacheManagerEnum(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
