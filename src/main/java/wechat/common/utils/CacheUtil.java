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
    public static final String CACHE_QI_YE = "cache_qi_ye";
    public static final String CACHE_WX = "cache_wx";
    public static final String ACCESS_TOKEN_CACHE = "AccessTokenCache";
    public static final String JSAPI_TICKET_CACHE = "JsApiTicket";
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
