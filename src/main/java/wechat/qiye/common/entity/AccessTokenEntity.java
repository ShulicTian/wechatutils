package wechat.qiye.common.entity;

import com.google.gson.annotations.SerializedName;

/**
 * AccessToken响应实体
 *
 * @author tianslc
 */
public class AccessTokenEntity extends QiYeReceiveEntity {

    /**
     * AccessToken
     */
    @SerializedName("access_token")
    private String accessToken;

    /**
     * AccessToken有效时间
     */
    @SerializedName("expires_in")
    private String expiresIn;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
    }
}
