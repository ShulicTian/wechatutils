package wechat.weixin.utils;

import com.google.gson.Gson;
import wechat.common.cache.RedisSwitch;
import wechat.common.constant.BaseUrlConstant;
import wechat.common.utils.CacheUtil;
import wechat.common.utils.HttpsRequestUtil;
import wechat.common.utils.RedisUtil;
import wechat.qiye.common.aes.AesException;
import wechat.qiye.common.entity.AccessTokenEntity;
import wechat.weixin.common.entity.WeiXinParamsEntity;

/**
 * 微信AccessToken工具类
 *
 * @author tianslic
 */
public class AccessTokenUtil extends RedisSwitch {
    /**
     * 获取微信 AccessToken
     *
     * @param weiXinParamsEntity
     * @return
     */
    public static String getAccessToken(WeiXinParamsEntity weiXinParamsEntity) {
        if (weiXinParamsEntity.isOpenRedisCache()) {
            initJedisPool(weiXinParamsEntity.getRedisConfig());
        }
        return getClientCredentialAccessToken(weiXinParamsEntity);
    }

    /**
     * 获取微信 CLIENT_CREDENTIAL AccessToken
     *
     * @param weiXinParamsEntity
     * @return
     */
    private static String getClientCredentialAccessToken(WeiXinParamsEntity weiXinParamsEntity) {
        String accessToken = "";
        try {
            accessToken = getCacheAccessToken();
        } catch (Exception e) {
            logger.error("【WeiXin】{} {}", "缓存获取异常", e);
            e.printStackTrace();
        }
        //如果缓存没拿到就重新请求
        if (accessToken == null || "".equals(accessToken)) {
            accessToken = requestAccessToken(weiXinParamsEntity);
        }
        logger.info("【WeiXin】{} [{}]", "获取微信AccessToken", accessToken);
        return accessToken;
    }

    /**
     * 请求AccessToken
     *
     * @param weiXinParamsEntity
     * @return
     */
    public static String requestAccessToken(WeiXinParamsEntity weiXinParamsEntity) {
        String url = BaseUrlConstant.WX_GET_TOKEN.replace("GRANTTYPE", weiXinParamsEntity.getGrantType()).
                replace("APPID", weiXinParamsEntity.getAppId()).
                replace("SECRET", weiXinParamsEntity.getSecret());
        String result = HttpsRequestUtil.httpsGet(url);
        AccessTokenEntity accessTokenEntity = new Gson().fromJson(result, AccessTokenEntity.class);
        if (AesException.OK == accessTokenEntity.getErrcode()) {
            logger.info("【WeiXin】{} [{}] ", "重新请求AccessToken", accessTokenEntity.getAccessToken());
            cacheAccessToken(accessTokenEntity);
            return accessTokenEntity.getAccessToken();
        }
        logger.error("【WeiXin】{} [{}] {}", "请求AccessToken失败", accessTokenEntity.getErrcode(), accessTokenEntity.getErrmsg());
        return null;
    }

    /**
     * 缓存AccessToken
     *
     * @param accessTokenEntity
     */
    private static void cacheAccessToken(AccessTokenEntity accessTokenEntity) {
        if (openRedisCache) {
            RedisUtil.putStringWithExpire(CacheUtil.ACCESS_TOKEN_CACHE, accessTokenEntity.getAccessToken(), jedisPool, accessTokenEntity.getExpiresIn());
        } else {
            CacheUtil.put(CacheUtil.CACHE_WX, CacheUtil.ACCESS_TOKEN_CACHE, accessTokenEntity.getAccessToken());
        }
        logger.info("【WeiXin】{} [{}] {}s后失效", "缓存存入AccessToken", accessTokenEntity.getAccessToken(), accessTokenEntity.getExpiresIn());
    }

    /**
     * 从缓存获取AccessToken
     *
     * @return
     */
    private static String getCacheAccessToken() {
        String accessToken = "";
        if (openRedisCache) {
            accessToken = RedisUtil.getString(CacheUtil.ACCESS_TOKEN_CACHE, jedisPool);
        } else {
            accessToken = (String) CacheUtil.get(CacheUtil.CACHE_WX, CacheUtil.ACCESS_TOKEN_CACHE);
        }
        if (accessToken != null) {
            logger.info("【WeiXin】{} [{}]", "缓存获取AccessToken", accessToken);
            return accessToken;
        }
        logger.error("【WeiXin】{}", "缓存未找到AccessToken");
        return null;
    }
}
