package wechat.qiye.message.entity;

/**
 * 文本消息
 *
 * @author tianslc
 */
public class TextMessage extends Message {
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
