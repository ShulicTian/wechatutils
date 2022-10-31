package wechat.mini.utils;

import wechat.common.cache.RedisSwitch;
import wechat.mini.entity.MiniParamsEntity;
import wechat.weixin.entity.WeiXinParamsEntity;

/**
 * 微信AccessToken工具类
 *
 * @author tianslic
 */
public class AccessTokenUtil extends RedisSwitch {

    /**
     * 获取微信 AccessToken
     *
     * @param miniParamsEntity
     * @return
     */
    public static String getAccessToken(MiniParamsEntity miniParamsEntity) {
        WeiXinParamsEntity weiXinParamsEntity = new WeiXinParamsEntity();
        weiXinParamsEntity.setAppId(miniParamsEntity.getAppId());
        weiXinParamsEntity.setSecret(miniParamsEntity.getSecret());
        weiXinParamsEntity.setRedisConfig(miniParamsEntity.getRedisConfig());
        weiXinParamsEntity.setOpenRedisCache(miniParamsEntity.isOpenRedisCache());
        return wechat.weixin.utils.AccessTokenUtil.getAccessToken(weiXinParamsEntity);
    }

}
