package wechat.qiye.common.constant;

/**
 * 企业微信URI
 *
 * @author tianslc
 */

public enum QiYeUriEnum {
    GET("get"),
    CREATE("create"),
    UPDATE("update"),
    DELETE("delete"),
    LIST("list"),
    SIMPLELIST("simplelist"),
    BATCHDELETE("batchdelete");

    private final String uri;

    QiYeUriEnum(String uri) {
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }
}
