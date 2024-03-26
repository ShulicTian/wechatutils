package wechat.weixin.entity;

import wechat.common.entity.BaseParams;

import java.util.Properties;

/**
 * 微信接口基本参数
 *
 * @author tianslc
 */
public class WebParamsEntity extends BaseParams {

    private String appId;
    private String secret;
    private String code;


    public WebParamsEntity(Properties properties) {
        this.appId = properties.getProperty("appId");
        this.secret = properties.getProperty("appSecret");
        this.isOpenRedisCache = Boolean.parseBoolean(properties.getProperty("isOpenRedisCache"));
        if (this.isOpenRedisCache) {
            this.initRedisConfig(properties);
        }
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
