package wechat.qiye.message.entity;

import com.google.gson.annotations.SerializedName;

/**
 * 小程序消息
 *
 * @author tianslc
 */
public class MiniprogramNoticeMessage extends Message {

    private String appid;
    private String page;
    private String title;
    private String description;
    @SerializedName("emphasis_first_item")
    private String emphasisFirstItem;
    @SerializedName("content_item")
    private Object[] contentItem;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
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

    public String getEmphasisFirstItem() {
        return emphasisFirstItem;
    }

    public void setEmphasisFirstItem(String emphasisFirstItem) {
        this.emphasisFirstItem = emphasisFirstItem;
    }

    public Object[] getContentItem() {
        return contentItem;
    }

    public void setContentItem(Object[] contentItem) {
        this.contentItem = contentItem;
    }
}
