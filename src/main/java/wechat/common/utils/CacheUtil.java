package wechat.common.utils;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import wechat.common.cache.CommonCacheManager;

/**
 * 缓存工具类
 *
 * @author tianslc
 */
public class CacheUtil {
    public static final String ACCESS_TOKEN_CACHE = "accessTokenCache";
    public static CacheManager cacheManager = (CacheManager) CommonCacheManager.getEhCacheManager();

    public static void put(String accessTokenCache, String key, Object value) {
        Cache cache = cacheManager.getCache(accessTokenCache);
        Element element = new Element(key, value);
        cache.put(element);
    }

    public static Object get(String accessTokenCache, String key) {
        Cache cache = cacheManager.getCache(accessTokenCache);
        Element element = cache.get(key);
        return element == null ? null : element.getObjectValue();
    }

    public static void remove(String accessTokenCache, String key) {
        Cache cache = cacheManager.getCache(accessTokenCache);
        cache.remove(key);
    }

}
