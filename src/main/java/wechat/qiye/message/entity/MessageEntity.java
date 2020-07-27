package wechat.qiye.message.entity;

/**
 * 消息实体
 *
 * @author tianslc
 */
public class MessageEntity<T> extends BaseMessage {

    T message;

    public T getMessage() {
        return message;
    }

    public void setMessage(T message) {
        this.message = message;
        this.setMsgType(this.getMessageType());
    }

    public String getMessageType() {
        return message.getClass().getSimpleName().replace("Message", "").toLowerCase();
    }
}
