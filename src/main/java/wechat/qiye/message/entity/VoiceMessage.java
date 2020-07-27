package wechat.qiye.message.entity;

import com.google.gson.annotations.SerializedName;

/**
 * 语音消息
 *
 * @author tianslc
 */
public class VoiceMessage extends Message {

    @SerializedName("media_id")
    private String mediaId;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
}
