package wechat.qiye.message.entity;

import com.google.gson.annotations.SerializedName;

/**
 * 任务卡片消息
 *
 * @author tianslc
 */
public class TaskCardMessage {

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String description;

    /**
     * 跳转链接
     */
    private String url;

    /**
     * 任务ID
     */
    @SerializedName("task_id")
    private String taskId;

    /**
     * 按钮
     */
    private ButtonEntity[] btn;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public ButtonEntity[] getBtn() {
        return btn;
    }

    public void setBtn(ButtonEntity[] btn) {
        this.btn = btn;
    }
}
