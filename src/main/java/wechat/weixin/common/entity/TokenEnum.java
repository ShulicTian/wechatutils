package wechat.weixin.common.entity;

/**
 * 微信 Token类型
 *
 * @author tianslc
 */

public enum TokenEnum {
    CLIENT_CREDENTIAL("client_credential"),
    AUTHORIZATION_CODE("authorization_code");
    private final String value;

    TokenEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
