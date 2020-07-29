package wechat.common.cache;

import net.sf.ehcache.CacheManager;
import wechat.common.constant.CacheManagerEnum;

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
                CacheManager cacheManager = CacheManager.create(System.getProperty("user.dir") + "\\src\\main\\resources\\ehcache.xml");
                managers.put(CacheManagerEnum.EHCACHE_MANAGER.getKey(), cacheManager);
            }
        }
        return managers.get(CacheManagerEnum.EHCACHE_MANAGER.getKey());
    }
}
