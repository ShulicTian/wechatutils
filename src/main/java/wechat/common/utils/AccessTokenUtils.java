package wechat.common.utils;

import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import wechat.common.aes.AesException;
import wechat.common.constant.BaseUrlConstant;
import wechat.common.entity.AccessTokenEntity;
import wechat.common.entity.BaseParamsEntity;

/**
 * AccessToken工具类
 *
 * @author tianslc
 */
public class AccessTokenUtils {

    private static Logger logger = LogManager.getLogger(AccessTokenUtils.class);

    /**
     * 获取企业微信AccessToken
     *
     * @param baseParamsEntity
     * @return
     */
    public static String getAccessToken(BaseParamsEntity baseParamsEntity) {
        if (baseParamsEntity.isOpenGlobalAddressBookSecret()) {
            return getAddressBookAccessToken(baseParamsEntity);
        }
        return getCorpAccessToken(baseParamsEntity);
    }

    /**
     * 获取企业AccessToken
     *
     * @param baseParamsEntity
     * @return
     */
    private static String getCorpAccessToken(BaseParamsEntity baseParamsEntity) {
        String accessToken = getCacheAccessToken(baseParamsEntity.getCorpId());
        //如果缓存没拿到就重新请求
        if (accessToken == null || "".equals(accessToken)) {
            accessToken = requestAccessToken(baseParamsEntity);
        }
        logger.info("【QiYeWeChat】{} [{}]", "获取企业微信AccessToken", accessToken);
        return accessToken;
    }


    /**
     * 获取通讯录的AccessToken
     *
     * @param baseParamsEntity
     * @return
     */
    private static String getAddressBookAccessToken(BaseParamsEntity baseParamsEntity) {
        baseParamsEntity.setSecret(baseParamsEntity.getAddressBookSecret());
        baseParamsEntity.setAgentId("global");
        return getCorpAccessToken(baseParamsEntity);
    }

    /**
     * 请求AccessToken
     *
     * @param baseParamsEntity
     * @return
     */
    public static String requestAccessToken(BaseParamsEntity baseParamsEntity) {
        String url = BaseUrlConstant.QIYE_ACCESS_TOKEN_URL.replace("ID", baseParamsEntity.getCorpId()).replace("SECRET", baseParamsEntity.getSecret());
        String result = HttpsRequestUtils.httpsGet(url);
        AccessTokenEntity accessTokenEntity = new Gson().fromJson(result, AccessTokenEntity.class);
        if (AesException.OK == accessTokenEntity.getErrcode()) {
            logger.info("【QiYeWeChat】{} [{}] ", "重新请求AccessToken", accessTokenEntity.getAccessToken());
            cacheAccessToken(baseParamsEntity.getCorpId(), accessTokenEntity);
            return accessTokenEntity.getAccessToken();
        }
        logger.error("【QiYeWeChat】{} [{}] {}", "请求AccessToken失败", accessTokenEntity.getErrcode(), accessTokenEntity.getErrmsg());
        return null;
    }

    /**
     * 缓存AccessToken
     *
     * @param tokenKey
     * @param accessTokenEntity
     */
    private static void cacheAccessToken(String tokenKey, AccessTokenEntity accessTokenEntity) {
        logger.info("【QiYeWeChat】{} [{}] {}s后失效", "缓存存入AccessToken", accessTokenEntity.getAccessToken(), accessTokenEntity.getExpiresIn());
    }

    /**
     * 从缓存获取AccessToken
     *
     * @param tokenKey
     * @return
     */
    private static String getCacheAccessToken(String tokenKey) {

        logger.info("【QiYeWeChat】{} [{}]", "缓存获取AccessToken", "");
//        logger.error("【QiYeWeChat】{}", "缓存未找到AccessToken");
        return null;
    }

}
