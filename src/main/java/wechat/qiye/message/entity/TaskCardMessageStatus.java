package wechat.qiye.message.entity;

import com.google.gson.annotations.SerializedName;

/**
 * 任务卡片消息状态
 *
 * @author tianslc
 */
public class TaskCardMessageStatus {

    private String[] userids;
    private String agentid;
    @SerializedName("task_id")
    private String taskId;
    @SerializedName("clicked_key")
    private String clickedKey;

    public String[] getUserids() {
        return userids;
    }

    public void setUserids(String[] userids) {
        this.userids = userids;
    }

    public String getAgentid() {
        return agentid;
    }

    public void setAgentid(String agentid) {
        this.agentid = agentid;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getClickedKey() {
        return clickedKey;
    }

    public void setClickedKey(String clickedKey) {
        this.clickedKey = clickedKey;
    }
}
