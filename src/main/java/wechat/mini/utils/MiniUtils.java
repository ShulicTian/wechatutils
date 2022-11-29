package wechat.mini.utils;

import com.google.gson.Gson;
import wechat.common.cache.RedisSwitch;
import wechat.common.constant.BaseUrlConstant;
import wechat.common.entity.ReceiveEntity;
import wechat.common.utils.HttpsRequestUtil;
import wechat.mini.entity.LoginResponseEntity;
import wechat.mini.entity.MiniParamsEntity;
import wechat.mini.entity.MobileResponseEntity;
import wechat.mini.entity.UserRankDataEntity;
import wechat.qiye.common.aes.AesException;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class MiniUtils extends RedisSwitch {

    private static MiniParamsEntity commonParams = null;

    public static void loadConfig(Properties properties) {
        commonParams = new MiniParamsEntity(properties);
    }

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
        logger.error("【MINI】获取手机号失败 [{}] {}", mobileResponseEntity.getErrcode(), mobileResponseEntity.getErrmsg());
        return null;
    }

    public static ReceiveEntity submitUserRankData(UserRankDataEntity entity) {
        if (commonParams != null) {
            String url = BaseUrlConstant.SUBMIT_USER_DATA.replace("ACCESS_TOKEN", AccessTokenUtil.getAccessToken(commonParams))
                    .replace("SIGNATURE", entity.getSignature())
                    .replace("OPENID", entity.getOpenId())
                    .replace("SIG_METHOD", entity.getSigMethod());
            Map<String, Object> map = new HashMap<>();
            map.put("kv_list", entity.getKvList());
            String result = HttpsRequestUtil.httpsPost(url, new Gson().toJson(map).getBytes(StandardCharsets.UTF_8));
            ReceiveEntity receiveEntity = new Gson().fromJson(result, ReceiveEntity.class);
            if (receiveEntity.getErrcode() == null || receiveEntity.getErrcode() == AesException.OK) {
                logger.debug("【MINI】上报用户数据成功 [{}] ", entity.getOpenId());
                return receiveEntity;
            }
            logger.error("【MINI】上报用户数据失败 [{}] {}", receiveEntity.getErrcode(), receiveEntity.getErrmsg());
        }
        return null;
    }
}
