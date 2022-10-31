package wechat.qiye.addressbook.entity;

import com.google.gson.annotations.SerializedName;
import wechat.common.entity.ReceiveEntity;

/**
 * 成员信息实体
 *
 * @author tianslc
 */
public class QrcodeEntity extends ReceiveEntity {
    @SerializedName("join_qrcode")
    private String joinQrcode;

    public String getJoinQrcode() {
        return joinQrcode;
    }

    public void setJoinQrcode(String joinQrcode) {
        this.joinQrcode = joinQrcode;
    }
}
