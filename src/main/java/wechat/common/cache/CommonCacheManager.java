package wechat.common.cache;

import net.sf.ehcache.CacheManager;
import wechat.common.constant.CacheManagerEnum;

import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 缓存管理器
 *
 * @author tianslc
 */
public class CommonCacheManager {

    private static Map<String, Object> managers = new ConcurrentHashMap<String, Object>();

    private CommonCacheManager() {
    }

    /**
     * 获取ehcache管理器
     *
     * @return
     */
    public static Object getEhCacheManager() {
        synchronized (managers) {
            if (!managers.containsKey(CacheManagerEnum.EHCACHE_MANAGER.getKey())) {
                InputStream resource = CommonCacheManager.class.getClassLoader().getResourceAsStream("ehcache.xml");
                if (resource == null) {
                    System.err.println("InputStream: NULL");
                    return null;
                }
                CacheManager cacheManager = CacheManager.create(resource);
                managers.put(CacheManagerEnum.EHCACHE_MANAGER.getKey(), cacheManager);
            }
        }
        return managers.get(CacheManagerEnum.EHCACHE_MANAGER.getKey());
    }
}
