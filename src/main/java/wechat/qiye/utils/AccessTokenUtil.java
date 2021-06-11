package wechat.qiye.utils;

import com.google.gson.Gson;
import wechat.common.cache.RedisSwitch;
import wechat.common.constant.BaseUrlConstant;
import wechat.common.utils.CacheUtil;
import wechat.common.utils.HttpsRequestUtil;
import wechat.common.utils.RedisUtil;
import wechat.qiye.common.aes.AesException;
import wechat.qiye.common.entity.AccessTokenEntity;
import wechat.qiye.common.entity.QiYeParamsEntity;

/**
 * AccessToken工具类
 *
 * @author tianslc
 */
public class AccessTokenUtil extends RedisSwitch {

    /**
     * 获取企业微信AccessToken
     *
     * @param qiYeParamsEntity
     * @return
     */
    public static String getAccessToken(QiYeParamsEntity qiYeParamsEntity) {
        if (qiYeParamsEntity.isOpenRedisCache()) {
            initJedisPool(qiYeParamsEntity.getRedisConfig());
        }
        if (qiYeParamsEntity.isOpenGlobalAddressBookSecret()) {
            return getAddressBookAccessToken(qiYeParamsEntity);
        }
        return getCorpAccessToken(qiYeParamsEntity);
    }

    /**
     * 获取企业AccessToken
     *
     * @param qiYeParamsEntity
     * @return
     */
    private static String getCorpAccessToken(QiYeParamsEntity qiYeParamsEntity) {
        String accessToken = "";
        try {
            accessToken = getCacheAccessToken();
        } catch (Exception e) {
            logger.error("【QiYeWeChat】{} {}", "缓存获取异常", e);
            e.printStackTrace();
        }
        //如果缓存没拿到就重新请求
        if (accessToken == null || "".equals(accessToken)) {
            accessToken = requestAccessToken(qiYeParamsEntity);
        }
        logger.info("【QiYeWeChat】{} [{}]", "获取企业微信AccessToken", accessToken);
        return accessToken;
    }

    /**
     * 获取通讯录的AccessToken
     *
     * @param qiYeParamsEntity
     * @return
     */
    private static String getAddressBookAccessToken(QiYeParamsEntity qiYeParamsEntity) {
        qiYeParamsEntity.setSecret(qiYeParamsEntity.getAddressBookSecret());
        qiYeParamsEntity.setAgentId("AddressBook");
        return getCorpAccessToken(qiYeParamsEntity);
    }

    /**
     * 请求AccessToken
     *
     * @param qiYeParamsEntity
     * @return
     */
    public static String requestAccessToken(QiYeParamsEntity qiYeParamsEntity) {
        String url = BaseUrlConstant.QIYE_ACCESS_TOKEN_URL.replace("ID", qiYeParamsEntity.getCorpId()).replace("SECRET", qiYeParamsEntity.getSecret());
        String result = HttpsRequestUtil.httpsGet(url);
        AccessTokenEntity accessTokenEntity = new Gson().fromJson(result, AccessTokenEntity.class);
        if (AesException.OK == accessTokenEntity.getErrcode()) {
            logger.debug("【QiYeWeChat】{} [{}] ", "重新请求AccessToken", accessTokenEntity.getAccessToken());
            cacheAccessToken(accessTokenEntity);
            return accessTokenEntity.getAccessToken();
        }
        logger.error("【QiYeWeChat】{} [{}] {}", "请求AccessToken失败", accessTokenEntity.getErrcode(), accessTokenEntity.getErrmsg());
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
            CacheUtil.put(CacheUtil.CACHE_QI_YE, CacheUtil.ACCESS_TOKEN_CACHE, accessTokenEntity.getAccessToken());
        }
        logger.debug("【QiYeWeChat】{} [{}] {}s后失效", "缓存存入AccessToken", accessTokenEntity.getAccessToken(), accessTokenEntity.getExpiresIn());
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
            accessToken = (String) CacheUtil.get(CacheUtil.CACHE_QI_YE, CacheUtil.ACCESS_TOKEN_CACHE);
        }
        if (accessToken != null) {
            logger.info("【QiYeWeChat】{} [{}]", "缓存获取AccessToken", accessToken);
            return accessToken;
        }
        logger.error("【QiYeWeChat】{}", "缓存未找到AccessToken");
        return null;
    }

}
