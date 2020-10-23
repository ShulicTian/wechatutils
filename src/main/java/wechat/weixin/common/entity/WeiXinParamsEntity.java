package wechat.weixin.common.entity;

import wechat.common.entity.BaseParams;

/**
 * 微信接口基本参数
 *
 * @author tianslc
 */
public class WeiXinParamsEntity extends BaseParams {

    private String grantType = TokenEnum.CLIENT_CREDENTIAL.getValue();
    private String appId;
    private String secret;


    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
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
}
