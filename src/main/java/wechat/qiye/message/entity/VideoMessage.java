package wechat.qiye.message.entity;

import com.google.gson.annotations.SerializedName;

/**
 * 视频消息
 *
 * @author tianslc
 */
public class VideoMessage extends Message {

    @SerializedName("media_id")
    private String mediaId;
    private String title;
    private String description;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
