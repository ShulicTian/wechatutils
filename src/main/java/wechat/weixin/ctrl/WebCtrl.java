package wechat.weixin.ctrl;

import com.google.gson.Gson;
import wechat.common.cache.RedisSwitch;
import wechat.common.constant.BaseUrlConstant;
import wechat.common.entity.ReceiveEntity;
import wechat.common.utils.HttpsRequestUtil;
import wechat.qiye.common.aes.AesException;
import wechat.weixin.entity.UserInfoResponseEntity;
import wechat.weixin.entity.WebAccessTokenEntity;
import wechat.weixin.entity.WebParamsEntity;
import wechat.weixin.utils.WebAccessTokenUtil;

import java.util.Properties;

public class WebCtrl extends RedisSwitch {

    private WebParamsEntity commonParams = null;

    public WebCtrl(WebParamsEntity commonParams) {
        this.commonParams = commonParams;
    }

    public WebAccessTokenEntity getWebAccessTokenEntity(String code) {
        return WebAccessTokenUtil.requestAccessToken(new WebParamsEntity(commonParams.getAppId(), commonParams.getSecret(), code));
    }

    public UserInfoResponseEntity getUserInfo(String openId) {
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

    public ReceiveEntity validToken(String openId) {
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

    /**
     * 拼接OAuth2 Url
     *
     * @param redirectUrl
     * @param state
     * @return
     */
    public String getAuthUrl(String redirectUrl, String state, String scope) {
        return BaseUrlConstant.WX_OAUTH2.replace("APPID", commonParams.getAppId()).
                replace("REDIRECT_URI", redirectUrl).
                replace("STATE", state).
                replace("SCOPE", scope);
    }
}
