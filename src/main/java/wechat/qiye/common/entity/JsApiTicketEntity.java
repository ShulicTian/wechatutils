package wechat.qiye.common.entity;

import com.google.gson.annotations.SerializedName;
import wechat.common.entity.ReceiveEntity;

/**
 * JsApiTicket响应实体
 *
 * @author tianslc
 */
public class JsApiTicketEntity extends ReceiveEntity {

    /**
     * ticket
     */
    private String ticket;

    /**
     * ticket有效时间
     */
    @SerializedName("expires_in")
    private int expiresIn;

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }
}
