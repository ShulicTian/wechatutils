package wechat.weixin.entity;

import com.google.gson.annotations.SerializedName;
import wechat.common.entity.ReceiveEntity;

/**
 * @author tianslc
 * @date 2024/3/26
 */
public class UserInfoResponseEntity extends ReceiveEntity {

    @SerializedName("session_key")
    private String openId;

    @SerializedName("nickname")
    private String nickname;

    @SerializedName("sex")
    private String sex;

    @SerializedName("province")
    private String province;

    @SerializedName("city")
    private String city;

    @SerializedName("country")
    private String country;

    @SerializedName("headimgurl")
    private String headImgUrl;

    @SerializedName("privilege")
    private String[] privilege;

    @SerializedName("unionid")
    private String unionId;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public String[] getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String[] privilege) {
        this.privilege = privilege;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }
}
