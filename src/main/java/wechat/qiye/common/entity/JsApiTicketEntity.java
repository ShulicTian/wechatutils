package wechat.qiye.common.entity;

import com.google.gson.annotations.SerializedName;

/**
 * JsApiTicket响应实体
 *
 * @author tianslc
 */
public class JsApiTicketEntity extends BaseReceiveEntity {

    /**
     * ticket
     */
    private String ticket;

    /**
     * ticket有效时间
     */
    @SerializedName("expires_in")
    private String expiresIn;

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
    }
}
