package wechat.qiye.message.entity;

import com.google.gson.annotations.SerializedName;

/**
 * 按钮实体
 *
 * @author tianslc
 */
public class ButtonEntity {

    private String key;
    private String name;
    @SerializedName("replace_name")
    private String replaceName;
    private String color;
    @SerializedName("is_bold")
    private String isBold;

    public ButtonEntity(String key, String name, String replaceName) {
        this.key = key;
        this.name = name;
        this.replaceName = replaceName;
    }

    public ButtonEntity(String key, String name, String replaceName, String color, String isBold) {
        this.key = key;
        this.name = name;
        this.replaceName = replaceName;
        this.color = color;
        this.isBold = isBold;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReplaceName() {
        return replaceName;
    }

    public void setReplaceName(String replaceName) {
        this.replaceName = replaceName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getIsBold() {
        return isBold;
    }

    public void setIsBold(String isBold) {
        this.isBold = isBold;
    }
}
