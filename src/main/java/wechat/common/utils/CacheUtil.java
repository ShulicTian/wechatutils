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
    public static final String QIYE_ACCESS_TOKEN_CACHE = "QiYeAccessTokenCache";
    public static final String QIYE_JSAPI_TICKET_CACHE = "QiYeJsApiTicket";
    public static final String WX_ACCESS_TOKEN_CACHE = "WXAccessTokenCache";
    public static CacheManager cacheManager = (CacheManager) CommonCacheManager.getEhCacheManager();

    public static void put(String accessTokenCache, String key, Object value) {
        if (cacheManager != null) {
            Cache cache = cacheManager.getCache(accessTokenCache);
            Element element = new Element(key, value);
            cache.put(element);
        }
    }

    public static Object get(String accessTokenCache, String key) {
        if (cacheManager != null) {
            Cache cache = cacheManager.getCache(accessTokenCache);
            Element element = cache != null ? cache.get(key) : null;
            return element == null ? null : element.getObjectValue();
        }
        return null;
    }

    public static void remove(String accessTokenCache, String key) {
        if (cacheManager != null) {
            Cache cache = cacheManager.getCache(accessTokenCache);
            cache.remove(key);
        }
    }

}
