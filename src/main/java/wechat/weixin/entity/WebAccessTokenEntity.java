package wechat.weixin.entity;

import com.google.gson.annotations.SerializedName;
import wechat.common.entity.ReceiveEntity;

/**
 * AccessToken响应实体
 *
 * @author tianslc
 */
public class WebAccessTokenEntity extends ReceiveEntity {

    /**
     * AccessToken
     */
    @SerializedName("access_token")
    private String accessToken;

    /**
     * refreshToken
     */
    @SerializedName("refresh_token")
    private String refreshToken;

    /**
     * AccessToken有效时间
     */
    @SerializedName("expires_in")
    private int expiresIn;

    /**
     * 用户唯一标识，请注意，在未关注公众号时，用户访问公众号的网页，也会产生一个用户和公众号唯一的OpenID
     */
    @SerializedName("openid")
    private int openId;

    /**
     * 用户统一标识（针对一个微信开放平台账号下的应用，同一用户的 unionid 是唯一的），只有当scope为"snsapi_userinfo"时返回
     */
    @SerializedName("unionid")
    private int unionId;

    /**
     * 是否为快照页模式虚拟账号，只有当用户是快照页模式虚拟账号时返回，值为1
     */
    @SerializedName("is_snapshotuser")
    private int isSnapshotUser;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public int getOpenId() {
        return openId;
    }

    public void setOpenId(int openId) {
        this.openId = openId;
    }

    public int getUnionId() {
        return unionId;
    }

    public void setUnionId(int unionId) {
        this.unionId = unionId;
    }

    public int getIsSnapshotUser() {
        return isSnapshotUser;
    }

    public void setIsSnapshotUser(int isSnapshotUser) {
        this.isSnapshotUser = isSnapshotUser;
    }
}
