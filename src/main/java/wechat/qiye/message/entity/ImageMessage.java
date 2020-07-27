package wechat.qiye.message.entity;

import com.google.gson.annotations.SerializedName;

/**
 * 图片消息
 *
 * @author tianslc
 */
public class ImageMessage {

    @SerializedName("media_id")
    private String mediaId;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
}
