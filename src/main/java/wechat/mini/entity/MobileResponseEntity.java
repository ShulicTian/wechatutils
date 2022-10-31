package wechat.mini.entity;

import com.google.gson.annotations.SerializedName;
import wechat.common.entity.ReceiveEntity;

public class MobileResponseEntity extends ReceiveEntity {
    @SerializedName("phone_info")
    private MobileInfo phoneInfo;

    public MobileInfo getPhoneInfo() {
        return phoneInfo;
    }

    public void setPhoneInfo(MobileInfo phoneInfo) {
        this.phoneInfo = phoneInfo;
    }
}
