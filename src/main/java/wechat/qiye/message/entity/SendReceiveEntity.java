package wechat.qiye.message.entity;

import wechat.common.entity.BaseReceiveEntity;

/**
 * 消息发送返回基础字段
 *
 * @author tianslc
 */
public class SendReceiveEntity extends BaseReceiveEntity {

    /**
     * 发送失败的userID
     */
    private String[] invaliduser;

    /**
     * 发送失败的部门ID
     */
    private String[] invalidparty;

    /**
     * 发送失败的标签ID
     */
    private String[] invalidtag;

    public String[] getInvaliduser() {
        return invaliduser;
    }

    public void setInvaliduser(String[] invaliduser) {
        this.invaliduser = invaliduser;
    }

    public String[] getInvalidparty() {
        return invalidparty;
    }

    public void setInvalidparty(String[] invalidparty) {
        this.invalidparty = invalidparty;
    }

    public String[] getInvalidtag() {
        return invalidtag;
    }

    public void setInvalidtag(String[] invalidtag) {
        this.invalidtag = invalidtag;
    }
}
