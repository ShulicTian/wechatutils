package wechat.qiye.addressbook.entity;

import com.google.gson.JsonArray;

/**
 * 成员自定义字段实体
 *
 * @author tianslc
 */
public class ExtattrEntity {
    /**
     * 属性列表
     */
    private JsonArray attrs;

    public JsonArray getAttrs() {
        return attrs;
    }

    public void setAttrs(JsonArray attrs) {
        this.attrs = attrs;
    }
}
