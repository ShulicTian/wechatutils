package wechat.common.constant;

/**
 * @author tianslc
 * @date 2024/3/26
 */
public enum AuthScopeEnum {
    SNS_API_BASE("snsapi_base"),
    SNS_API_USERINFO("snsapi_userinfo"),
    SNS_API_LOGIN("snsapi_login");

    private final String scope;

    AuthScopeEnum(String scope) {
        this.scope = scope;
    }

    public String getScope() {
        return scope;
    }
}
