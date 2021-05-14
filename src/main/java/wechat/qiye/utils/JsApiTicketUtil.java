package wechat.qiye.utils;

import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import wechat.common.cache.RedisSwitch;
import wechat.common.constant.BaseUrlConstant;
import wechat.common.utils.CacheUtil;
import wechat.common.utils.HttpsRequestUtil;
import wechat.common.utils.RedisUtil;
import wechat.qiye.common.aes.AesException;
import wechat.qiye.common.entity.JsApiTicketEntity;
import wechat.qiye.common.entity.QiYeParamsEntity;

/**
 * JsApiTicket工具类
 *
 * @author tianslc
 */
public class JsApiTicketUtil extends RedisSwitch {

    /**
     * 获取企业微信 jsapi_ticket
     *
     * @param qiYeParamsEntity
     * @return
     */
    public static String getJsApiTicket(QiYeParamsEntity qiYeParamsEntity) {

        if (qiYeParamsEntity.isOpenRedisCache()) {
            initJedisPool(qiYeParamsEntity.getRedisConfig());
        }

        String jsApiTicket = getCacheJsApiTicket(qiYeParamsEntity.getAgentId());
        if (StringUtils.isEmpty(jsApiTicket)) {
            jsApiTicket = requestJsApiTicket(qiYeParamsEntity);
        }
        return jsApiTicket;
    }

    /**
     * 请求企业微信 jsapi_ticket
     *
     * @param qiYeParamsEntity
     * @return
     */
    public static String requestJsApiTicket(QiYeParamsEntity qiYeParamsEntity) {
        String url = BaseUrlConstant.QIYE_JSAPI_TICKET.replace("ACCESS_TOKEN", AccessTokenUtil.getAccessToken(qiYeParamsEntity));
        String result = HttpsRequestUtil.httpsGet(url);
        JsApiTicketEntity jsApiTicketEntity = new Gson().fromJson(result, JsApiTicketEntity.class);
        if (AesException.OK == jsApiTicketEntity.getErrcode()) {
            logger.info("【QiYeWeChat】{} [{}] ", "重新请求JsApiTicket", jsApiTicketEntity.getTicket());
            cacheJsApiTicket(qiYeParamsEntity.getAgentId(), jsApiTicketEntity);
            return jsApiTicketEntity.getTicket();
        }
        logger.error("【QiYeWeChat】{} [{}] {}", "请求JsApiTicket失败", jsApiTicketEntity.getErrcode(), jsApiTicketEntity.getErrmsg());
        return null;
    }

    /**
     * 缓存 jsapi_ticket
     *
     * @param tokenKey
     * @param jsApiTicketEntity
     */
    private static void cacheJsApiTicket(String tokenKey, JsApiTicketEntity jsApiTicketEntity) {
        if (openRedisCache) {
            RedisUtil.putString(tokenKey, jsApiTicketEntity.getTicket(), jedisPool);
        } else {
            CacheUtil.put(CacheUtil.QIYE_JSAPI_TICKET_CACHE, tokenKey, jsApiTicketEntity.getTicket());
        }
        logger.info("【QiYeWeChat】{} [{}] {}s后失效", "缓存存入JsApiTicket", jsApiTicketEntity.getTicket(), jsApiTicketEntity.getExpiresIn());
    }

    /**
     * 从缓存获取 jsapi_ticket
     *
     * @param tokenKey
     * @return
     */
    private static String getCacheJsApiTicket(String tokenKey) {
        String jsApiTicket = "";
        if (openRedisCache) {
            jsApiTicket = RedisUtil.getString(tokenKey, jedisPool);
        } else {
            jsApiTicket = (String) CacheUtil.get(CacheUtil.QIYE_JSAPI_TICKET_CACHE, tokenKey);
        }
        if (jsApiTicket != null) {
            logger.info("【QiYeWeChat】{} [{}]", "缓存获取JsApiTicket", jsApiTicket);
            return jsApiTicket;
        }
        logger.error("【QiYeWeChat】{}", "缓存未找到JsApiTicket");
        return null;
    }
}
