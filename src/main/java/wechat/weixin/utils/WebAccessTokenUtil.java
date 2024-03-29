package wechat.weixin.utils;

import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import wechat.common.cache.RedisSwitch;
import wechat.common.constant.BaseUrlConstant;
import wechat.common.utils.CacheUtil;
import wechat.common.utils.HttpsRequestUtil;
import wechat.common.utils.RedisUtil;
import wechat.qiye.common.aes.AesException;
import wechat.weixin.entity.WebAccessTokenEntity;
import wechat.weixin.entity.WebParamsEntity;

/**
 * 微信AccessToken工具类
 *
 * @author tianslic
 */
public class WebAccessTokenUtil extends RedisSwitch {
    /**
     * 获取微信 AccessToken
     *
     * @param webParamsEntity
     * @return
     */
    public static String getAccessToken(WebParamsEntity webParamsEntity) {
        if (webParamsEntity.isOpenRedisCache()) {
            initJedisPool(webParamsEntity.getRedisConfig());
        }
        return getAuthorizationCodeAssessToken(webParamsEntity);
    }

    /**
     * 获取微信 AuthorizationCode AccessToken
     *
     * @param webParamsEntity
     * @return
     */
    private static String getAuthorizationCodeAssessToken(WebParamsEntity webParamsEntity) {
        String accessToken = "";
        try {
            accessToken = getCacheAccessToken(webParamsEntity.getAppId(), webParamsEntity.getOpenId());
        } catch (Exception e) {
            logger.error("【WeiXinWeb】{} {}", "缓存获取异常", e);
            e.printStackTrace();
        }
        //如果缓存没拿到就重新请求
        if (StringUtils.isBlank(accessToken)) {
            String refreshAccessToken = getCacheRefreshToken(webParamsEntity.getAppId(), webParamsEntity.getOpenId());
            WebAccessTokenEntity entity;
            if (StringUtils.isNotBlank(refreshAccessToken)) {
                entity = refreshAccessToken(webParamsEntity.getAppId(), refreshAccessToken);
            } else {
                entity = requestAccessToken(webParamsEntity);
            }
            if (entity != null) {
                accessToken = entity.getAccessToken();
            }
        }
        logger.info("【WeiXinWeb】{} [{}]", "获取微信AccessToken", accessToken);
        return accessToken;
    }

    /**
     * 请求AccessToken
     *
     * @param webParamsEntity
     * @return
     */
    public static WebAccessTokenEntity requestAccessToken(WebParamsEntity webParamsEntity) {
        String url = BaseUrlConstant.WX_WEB_ACCESS_TOKEN.
                replace("APPID", webParamsEntity.getAppId()).
                replace("SECRET", webParamsEntity.getSecret()).
                replace("CODE", webParamsEntity.getCode());
        String result = HttpsRequestUtil.httpsGet(url);
        WebAccessTokenEntity accessTokenEntity = new Gson().fromJson(result, WebAccessTokenEntity.class);
        if (accessTokenEntity.getErrcode() == null || accessTokenEntity.getErrcode() == AesException.OK) {
            logger.info("【WeiXinWeb】{} [{}] ", "重新请求AccessToken", accessTokenEntity.getAccessToken());
            cacheAccessToken(accessTokenEntity, webParamsEntity.getAppId());
            cacheRefreshToken(accessTokenEntity.getRefreshToken(), webParamsEntity.getAppId(), accessTokenEntity.getOpenId());
            return accessTokenEntity;
        }
        logger.error("【WeiXinWeb】{} [{}] {}", "请求AccessToken失败", accessTokenEntity.getErrcode(), accessTokenEntity.getErrmsg());
        return null;
    }

    public static WebAccessTokenEntity refreshAccessToken(String appId, String refreshToken) {
        String url = BaseUrlConstant.WX_WEB_REFRESH_TOKEN.
                replace("APPID", appId).
                replace("REFRESH_TOKEN", refreshToken);
        String result = HttpsRequestUtil.httpsGet(url);
        WebAccessTokenEntity accessTokenEntity = new Gson().fromJson(result, WebAccessTokenEntity.class);
        if (accessTokenEntity.getErrcode() == null || accessTokenEntity.getErrcode() == AesException.OK) {
            logger.info("【WeiXinWeb】{} [{}] ", "重新请求AccessToken", accessTokenEntity.getAccessToken());
            cacheAccessToken(accessTokenEntity, appId);
            return accessTokenEntity;
        }
        logger.error("【WeiXinWeb】{} [{}] {}", "请求AccessToken失败", accessTokenEntity.getErrcode(), accessTokenEntity.getErrmsg());
        return null;
    }

    /**
     * 缓存AccessToken
     *
     * @param accessTokenEntity
     */
    private static void cacheAccessToken(WebAccessTokenEntity accessTokenEntity, String appId) {
        if (openRedisCache) {
            RedisUtil.putStringWithExpire(CacheUtil.WEB_ACCESS_TOKEN_CACHE + "_" + appId + "_" + accessTokenEntity.getOpenId(), accessTokenEntity.getAccessToken(), jedisPool, accessTokenEntity.getExpiresIn());
        } else {
            CacheUtil.put(CacheUtil.CACHE_WX_WEB, CacheUtil.WEB_ACCESS_TOKEN_CACHE + "_" + appId + "_" + accessTokenEntity.getOpenId(), accessTokenEntity.getAccessToken());
        }
        logger.info("【WeiXinWeb】{} [{}] {}s后失效", "缓存存入AccessToken、RefreshToken", accessTokenEntity.getAccessToken(), accessTokenEntity.getExpiresIn());
    }

    /**
     * 缓存RefreshToken
     *
     * @param refreshToken
     * @param appId
     */
    private static void cacheRefreshToken(String refreshToken, String appId, String openId) {
        if (openRedisCache) {
            RedisUtil.putStringWithExpire(CacheUtil.WEB_REFRESH_TOKEN_CACHE + "_" + appId + "_" + openId, refreshToken, jedisPool, 30 * 24 * 60 * 60);
        } else {
            CacheUtil.put(CacheUtil.CACHE_WX_WEB_REFRESH, CacheUtil.WEB_REFRESH_TOKEN_CACHE + "_" + appId + "_" + openId, refreshToken);
        }
        logger.info("【WeiXinWeb】{} [{}]30天后失效", "缓存存入RefreshToken", refreshToken);
    }

    /**
     * 从缓存获取AccessToken
     *
     * @return
     */
    private static String getCacheAccessToken(String appId, String openId) {
        String accessToken = "";
        if (openRedisCache) {
            accessToken = RedisUtil.getString(CacheUtil.WEB_ACCESS_TOKEN_CACHE + "_" + appId + "_" + openId, jedisPool);
        } else {
            accessToken = (String) CacheUtil.get(CacheUtil.CACHE_WX_WEB, CacheUtil.WEB_ACCESS_TOKEN_CACHE + "_" + appId + "_" + openId);
        }
        if (accessToken != null) {
            logger.info("【WeiXinWeb】{} [{}]", "缓存获取AccessToken", accessToken);
            return accessToken;
        }
        logger.error("【WeiXinWeb】{}", "缓存未找到AccessToken");
        return null;
    }

    /**
     * 从缓存获取RefreshToken
     *
     * @return
     */
    private static String getCacheRefreshToken(String appId, String openId) {
        String accessToken = "";
        if (openRedisCache) {
            accessToken = RedisUtil.getString(CacheUtil.WEB_REFRESH_TOKEN_CACHE + "_" + appId + "_" + openId, jedisPool);
        } else {
            accessToken = (String) CacheUtil.get(CacheUtil.CACHE_WX_WEB, CacheUtil.WEB_REFRESH_TOKEN_CACHE + "_" + appId + "_" + openId);
        }
        if (accessToken != null) {
            logger.info("【WeiXinWeb】{} [{}]", "缓存获取RefreshToken", accessToken);
            return accessToken;
        }
        logger.error("【WeiXinWeb】{}", "缓存未找到RefreshToken");
        return null;
    }
}
