package wechat.qiye.message.entity;

import com.google.gson.annotations.SerializedName;

/**
 * 消息基础信息
 *
 * @author tianslc
 */
public class BaseMessage {

    /**
     * 发送的userId 多个以|分隔
     */
    @SerializedName("touser")
    private String toUser;

    /**
     * 发送的部门Id 多个以|分隔
     */
    @SerializedName("toparty")
    private String toParty;

    /**
     * 发送的标签Id 多个以|分隔
     */
    @SerializedName("totag")
    private String toTag;

    /**
     * 应用ID
     */
    @SerializedName("agentid")
    private String agentId;

    /**
     * 消息类型
     */
    @SerializedName("msgtype")
    private String msgType;

    /**
     * 是否保密消息 0否 1是
     */
    private String safe;

    /**
     * 是否开启ID转义 0否 1是
     */
    @SerializedName("enable_id_trans")
    private String enableIdTrans;

    /**
     * 是否开启重复消息检查 0否 1是
     */
    @SerializedName("enable_duplicate_check")
    private String enableDuplicateCheck;

    /**
     * 重复消息检查的时间间隔
     */
    @SerializedName("duplicate_check_interval")
    private String duplicateCheckInterval;

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getToParty() {
        return toParty;
    }

    public void setToParty(String toParty) {
        this.toParty = toParty;
    }

    public String getToTag() {
        return toTag;
    }

    public void setToTag(String toTag) {
        this.toTag = toTag;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getSafe() {
        return safe;
    }

    public void setSafe(String safe) {
        this.safe = safe;
    }

    public String getEnableIdTrans() {
        return enableIdTrans;
    }

    public void setEnableIdTrans(String enableIdTrans) {
        this.enableIdTrans = enableIdTrans;
    }

    public String getEnableDuplicateCheck() {
        return enableDuplicateCheck;
    }

    public void setEnableDuplicateCheck(String enableDuplicateCheck) {
        this.enableDuplicateCheck = enableDuplicateCheck;
    }

    public String getDuplicateCheckInterval() {
        return duplicateCheckInterval;
    }

    public void setDuplicateCheckInterval(String duplicateCheckInterval) {
        this.duplicateCheckInterval = duplicateCheckInterval;
    }
}
