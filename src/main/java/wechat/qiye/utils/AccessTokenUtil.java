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
        return getAccessToken(qiYeParamsEntity, false);
    }

    /**
     * 获取企业微信AccessToken
     *
     * @param qiYeParamsEntity
     * @return
     */
    public static String getAccessToken(QiYeParamsEntity qiYeParamsEntity, boolean isOpenAddressBook) {
        if (qiYeParamsEntity.isOpenRedisCache()) {
            initJedisPool(qiYeParamsEntity.getRedisConfig());
        }
        if (isOpenAddressBook) {
            return getAddressBookAccessToken(qiYeParamsEntity);
        }
        return getCorpAccessToken(qiYeParamsEntity, false);
    }

    /**
     * 获取企业AccessToken
     *
     * @param qiYeParamsEntity
     * @return
     */
    public static String getCorpAccessToken(QiYeParamsEntity qiYeParamsEntity, boolean isOpenAddressBook) {
        String accessToken = "";
        try {
            accessToken = getCacheAccessToken(qiYeParamsEntity, isOpenAddressBook);
        } catch (Exception e) {
            logger.error("【QiYeWeChat】{} {}", "缓存获取异常", e);
        }
        //如果缓存没拿到就重新请求
        if (accessToken == null || accessToken.isEmpty()) {
            accessToken = requestAccessToken(qiYeParamsEntity, isOpenAddressBook);
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
        return getCorpAccessToken(qiYeParamsEntity, true);
    }

    /**
     * 请求AccessToken
     *
     * @param qiYeParamsEntity
     * @return
     */
    public static String requestAccessToken(QiYeParamsEntity qiYeParamsEntity, boolean isOpenAddressBook) {
        String url = BaseUrlConstant.QIYE_ACCESS_TOKEN_URL.replace("ID", qiYeParamsEntity.getCorpId()).replace("SECRET", qiYeParamsEntity.getSecret());
        String result = HttpsRequestUtil.httpsGet(url);
        AccessTokenEntity accessTokenEntity = new Gson().fromJson(result, AccessTokenEntity.class);
        if (AesException.OK == accessTokenEntity.getErrcode()) {
            logger.debug("【QiYeWeChat】{} [{}] ", "重新请求AccessToken", accessTokenEntity.getAccessToken());
            cacheAccessToken(qiYeParamsEntity.getAgentId(), accessTokenEntity, isOpenAddressBook);
            return accessTokenEntity.getAccessToken();
        }
        logger.error("【QiYeWeChat】{} [{}] {}", "请求AccessToken失败", accessTokenEntity.getErrcode(), accessTokenEntity.getErrmsg());
        return null;
    }

    /**
     * 缓存AccessToken
     *
     * @param catchKey
     * @param accessTokenEntity
     */
    private static void cacheAccessToken(String catchKey, AccessTokenEntity accessTokenEntity, boolean isOpenAddressBook) {
        if (openRedisCache) {
            if (isOpenAddressBook) {
                RedisUtil.putStringWithExpire(CacheUtil.ADDRESS_BOOK_ACCESS_TOKEN_CACHE, accessTokenEntity.getAccessToken(), jedisPool, accessTokenEntity.getExpiresIn());
            } else {
                RedisUtil.putStringWithExpire(CacheUtil.ACCESS_TOKEN_CACHE + "_" + catchKey, accessTokenEntity.getAccessToken(), jedisPool, accessTokenEntity.getExpiresIn());
            }
        } else {
            CacheUtil.put(CacheUtil.CACHE_QI_YE, CacheUtil.ACCESS_TOKEN_CACHE + "_" + catchKey, accessTokenEntity.getAccessToken());
        }
        logger.debug("【QiYeWeChat】{} [{}] {}s后失效", "缓存存入AccessToken", accessTokenEntity.getAccessToken(), accessTokenEntity.getExpiresIn());
    }

    /**
     * 从缓存获取AccessToken
     *
     * @return
     */
    private static String getCacheAccessToken(QiYeParamsEntity qiYeParamsEntity, boolean isOpenAddressBook) {
        String accessToken = "";
        if (openRedisCache) {
            if (isOpenAddressBook) {
                accessToken = RedisUtil.getString(CacheUtil.ADDRESS_BOOK_ACCESS_TOKEN_CACHE, jedisPool);
            } else {
                accessToken = RedisUtil.getString(CacheUtil.ACCESS_TOKEN_CACHE + "_" + qiYeParamsEntity.getAgentId(), jedisPool);
            }
        } else {
            accessToken = (String) CacheUtil.get(CacheUtil.CACHE_QI_YE, CacheUtil.ACCESS_TOKEN_CACHE + "_" + qiYeParamsEntity.getAgentId());
        }
        if (accessToken != null) {
            logger.info("【QiYeWeChat】{} [{}]", "缓存获取AccessToken", accessToken);
            return accessToken;
        }
        logger.error("【QiYeWeChat】{}", "缓存未找到AccessToken");
        return null;
    }

}
