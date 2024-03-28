package wechat.weixin.utils;

import com.google.gson.Gson;
import wechat.common.cache.RedisSwitch;
import wechat.common.constant.BaseUrlConstant;
import wechat.common.entity.ReceiveEntity;
import wechat.common.utils.HttpsRequestUtil;
import wechat.qiye.common.aes.AesException;
import wechat.weixin.entity.UserInfoResponseEntity;
import wechat.weixin.entity.WebAccessTokenEntity;
import wechat.weixin.entity.WebParamsEntity;

import java.util.Properties;

public class WebUtils extends RedisSwitch {

    private static WebParamsEntity commonParams = null;

    public static void loadConfig(Properties properties) {
        commonParams = new WebParamsEntity(properties);
    }

    public static WebAccessTokenEntity getWebAccessTokenEntity(String code) {
        return WebAccessTokenUtil.requestAccessToken(new WebParamsEntity(commonParams.getAppId(), commonParams.getSecret(), code));
    }

    public static UserInfoResponseEntity getUserInfo(String openId) {
        String url = BaseUrlConstant.WX_WEB_USERINFO.replace("ACCESS_TOKEN", WebAccessTokenUtil.getAccessToken(commonParams)).replace("OPENID", openId);
        String result = HttpsRequestUtil.httpsGet(url);
        UserInfoResponseEntity entity = new Gson().fromJson(result, UserInfoResponseEntity.class);
        if (entity.getErrcode() == null || entity.getErrcode() == AesException.OK) {
            logger.debug("【WeiXinWeb】获取用户信息成功 [{}]", openId);
            return entity;
        }
        logger.error("【MINI】获取用户信息失败 [{}] {}", entity.getErrcode(), entity.getErrmsg());
        return null;
    }

    public static ReceiveEntity validToken(String openId) {
        String url = BaseUrlConstant.WX_WEB_VALID_TOKEN.replace("ACCESS_TOKEN", WebAccessTokenUtil.getAccessToken(commonParams)).replace("OPENID", openId);
        String result = HttpsRequestUtil.httpsGet(url);
        ReceiveEntity entity = new Gson().fromJson(result, ReceiveEntity.class);
        if (entity.getErrcode() == null || entity.getErrcode() == AesException.OK) {
            logger.debug("【WeiXinWeb】获取用户信息成功 [{}]", openId);
            return entity;
        }
        logger.error("【MINI】获取用户信息失败 [{}] {}", entity.getErrcode(), entity.getErrmsg());
        return null;
    }
}
