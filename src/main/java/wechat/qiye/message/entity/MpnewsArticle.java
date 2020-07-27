package wechat.qiye.message.entity;

import com.google.gson.annotations.SerializedName;

/**
 * 图文消息内容实体
 *
 * @author tianslc
 */
public class MpnewsArticle {

    private String title;
    @SerializedName("thumb_media_id")
    private String thumbMediaId;
    private String author;
    @SerializedName("content_source_url")
    private String contentSourceUrl;
    private String content;
    private String digest;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbMediaId() {
        return thumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContentSourceUrl() {
        return contentSourceUrl;
    }

    public void setContentSourceUrl(String contentSourceUrl) {
        this.contentSourceUrl = contentSourceUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }
}
