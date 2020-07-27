package wechat.common.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * AccessToken响应实体
 *
 * @author tianslc
 */
public class AccessTokenEntity extends BaseReceiveEntity implements Serializable {

    private static final long serialVersionUID = -1445461603574337537L;

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
