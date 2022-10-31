package wechat.mini.entity;

import com.google.gson.annotations.SerializedName;
import wechat.common.entity.ReceiveEntity;

public class LoginResponseEntity extends ReceiveEntity {
    private String openid;
    /**
     * 所在的部门内是否为上级
     */
    @SerializedName("session_key")
    private String sessionKey;
    private String unionid;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }
}
