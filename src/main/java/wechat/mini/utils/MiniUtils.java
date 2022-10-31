package wechat.mini.utils;

import com.google.gson.Gson;
import wechat.common.cache.RedisSwitch;
import wechat.common.constant.BaseUrlConstant;
import wechat.common.utils.HttpsRequestUtil;
import wechat.mini.entity.LoginResponseEntity;
import wechat.mini.entity.MiniParamsEntity;
import wechat.mini.entity.MobileResponseEntity;
import wechat.qiye.common.aes.AesException;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class MiniUtils extends RedisSwitch {

    public static LoginResponseEntity login(MiniParamsEntity entity) {
        String url = BaseUrlConstant.MINI_LOGIN.replace("APPID", entity.getAppId()).replace("SECRET", entity.getSecret()).replace("JSCODE", entity.getJsCode());
        String result = HttpsRequestUtil.httpsGet(url);
        LoginResponseEntity loginResponseEntity = new Gson().fromJson(result, LoginResponseEntity.class);
        if (loginResponseEntity.getErrcode() == null || loginResponseEntity.getErrcode() == AesException.OK) {
            logger.debug("【MINI】登陆成功 [{}][{}] ", loginResponseEntity.getOpenid(), loginResponseEntity.getSessionKey());
            return loginResponseEntity;
        }
        logger.error("【MINI】登录失败 [{}] {}", loginResponseEntity.getErrcode(), loginResponseEntity.getErrmsg());
        return null;
    }

    public static MobileResponseEntity getMobile(MiniParamsEntity entity) {
        String url = BaseUrlConstant.MINI_GET_MOBILE.replace("ACCESS_TOKEN", AccessTokenUtil.getAccessToken(entity));
        Map<String, Object> map = new HashMap<>();
        map.put("code", entity.getJsCode());
        String result = HttpsRequestUtil.httpsPost(url, new Gson().toJson(map).getBytes(StandardCharsets.UTF_8));
        MobileResponseEntity mobileResponseEntity = new Gson().fromJson(result, MobileResponseEntity.class);
        if (mobileResponseEntity.getErrcode() == null || mobileResponseEntity.getErrcode() == AesException.OK) {
            logger.debug("【MINI】获取手机号成功 [{}] ", mobileResponseEntity.getPhoneInfo());
            return mobileResponseEntity;
        }
        logger.error("【MINI】登录失败 [{}] {}", mobileResponseEntity.getErrcode(), mobileResponseEntity.getErrmsg());
        return null;
    }
}
