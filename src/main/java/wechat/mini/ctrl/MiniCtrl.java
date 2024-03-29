package wechat.mini.ctrl;

import com.google.gson.Gson;
import wechat.common.cache.RedisSwitch;
import wechat.common.constant.BaseUrlConstant;
import wechat.common.entity.ReceiveEntity;
import wechat.common.utils.HttpsRequestUtil;
import wechat.mini.entity.LoginResponseEntity;
import wechat.mini.entity.MiniParamsEntity;
import wechat.mini.entity.MobileResponseEntity;
import wechat.mini.entity.UserRankDataEntity;
import wechat.mini.utils.AccessTokenUtil;
import wechat.qiye.common.aes.AesException;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * 登入认证工具类
 *
 * @author tianslc
 */
public class MiniCtrl extends RedisSwitch {

    private final MiniParamsEntity paramsEntity;

    public MiniCtrl(MiniParamsEntity entity) {
        this.paramsEntity = entity;
    }

    public LoginResponseEntity login(String code) {
        String url = BaseUrlConstant.MINI_LOGIN.replace("APPID", paramsEntity.getAppId()).replace("SECRET", paramsEntity.getSecret()).replace("JSCODE", code);
        String result = HttpsRequestUtil.httpsGet(url);
        LoginResponseEntity loginResponseEntity = new Gson().fromJson(result, LoginResponseEntity.class);
        if (loginResponseEntity.getErrcode() == null || loginResponseEntity.getErrcode() == AesException.OK) {
            logger.debug("【MINI】登陆成功 [{}][{}] ", loginResponseEntity.getOpenid(), loginResponseEntity.getSessionKey());
            return loginResponseEntity;
        }
        logger.error("【MINI】登录失败 [{}] {}", loginResponseEntity.getErrcode(), loginResponseEntity.getErrmsg());
        return null;
    }

    public MobileResponseEntity getMobile(String code) {
        String url = BaseUrlConstant.MINI_GET_MOBILE.replace("ACCESS_TOKEN", wechat.mini.utils.AccessTokenUtil.getAccessToken(paramsEntity));
        Map<String, Object> map = new HashMap<>();
        map.put("code", code);
        String result = HttpsRequestUtil.httpsPost(url, new Gson().toJson(map).getBytes(StandardCharsets.UTF_8));
        MobileResponseEntity mobileResponseEntity = new Gson().fromJson(result, MobileResponseEntity.class);
        if (mobileResponseEntity.getErrcode() == null || mobileResponseEntity.getErrcode() == AesException.OK) {
            logger.debug("【MINI】获取手机号成功 [{}] ", mobileResponseEntity.getPhoneInfo());
            return mobileResponseEntity;
        }
        logger.error("【MINI】获取手机号失败 [{}] {}", mobileResponseEntity.getErrcode(), mobileResponseEntity.getErrmsg());
        return null;
    }

    public ReceiveEntity submitUserRankData(UserRankDataEntity entity) {
        if (paramsEntity != null) {
            String url = BaseUrlConstant.SUBMIT_USER_DATA.replace("ACCESS_TOKEN", AccessTokenUtil.getAccessToken(paramsEntity))
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
