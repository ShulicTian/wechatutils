package wechat.qiye.addressbook.entity;

import com.google.gson.annotations.SerializedName;
import wechat.qiye.common.entity.QiYeReceiveEntity;

/**
 * 成员信息实体
 *
 * @author tianslc
 */
public class QrcodeEntity extends QiYeReceiveEntity {
    @SerializedName("join_qrcode")
    private String joinQrcode;

    public String getJoinQrcode() {
        return joinQrcode;
    }

    public void setJoinQrcode(String joinQrcode) {
        this.joinQrcode = joinQrcode;
    }
}
