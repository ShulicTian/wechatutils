package wechat.qiye.message.entity;

/**
 * markdown消息
 *
 * @author tianslc
 */
public class MarkdownMessage extends Message {
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
